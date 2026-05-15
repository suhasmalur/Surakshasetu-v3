package io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframerListener;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import java.io.Closeable;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: loaded from: classes10.dex */
final class MigratingThreadDeframer implements ThreadOptimizedDeframer {
    private final ApplicationThreadDeframerListener appListener;
    private final MessageDeframer deframer;
    private boolean deframerOnTransportThread;
    private boolean messageProducerEnqueued;
    private final MigratingDeframerListener migratingListener;
    private final ApplicationThreadDeframerListener.TransportExecutor transportExecutor;
    private final MessageDeframer.Listener transportListener;
    private final DeframeMessageProducer messageProducer = new DeframeMessageProducer();
    private final Object lock = new Object();
    private final Queue<Op> opQueue = new ArrayDeque();

    private interface Op {
        void run(boolean z);
    }

    public MigratingThreadDeframer(MessageDeframer.Listener listener, ApplicationThreadDeframerListener.TransportExecutor transportExecutor, MessageDeframer deframer) {
        this.transportListener = new SquelchLateMessagesAvailableDeframerListener((MessageDeframer.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
        this.transportExecutor = (ApplicationThreadDeframerListener.TransportExecutor) Preconditions.checkNotNull(transportExecutor, "transportExecutor");
        this.appListener = new ApplicationThreadDeframerListener(this.transportListener, transportExecutor);
        this.migratingListener = new MigratingDeframerListener(this.appListener);
        deframer.setListener(this.migratingListener);
        this.deframer = deframer;
    }

    @Override // io.grpc.internal.Deframer
    public void setMaxInboundMessageSize(int messageSize) {
        this.deframer.setMaxInboundMessageSize(messageSize);
    }

    @Override // io.grpc.internal.Deframer
    public void setDecompressor(Decompressor decompressor) {
        this.deframer.setDecompressor(decompressor);
    }

    @Override // io.grpc.internal.Deframer
    public void setFullStreamDecompressor(GzipInflatingBuffer fullStreamDecompressor) {
        this.deframer.setFullStreamDecompressor(fullStreamDecompressor);
    }

    private boolean runWhereAppropriate(Op op) {
        return runWhereAppropriate(op, true);
    }

    private boolean runWhereAppropriate(Op op, boolean currentThreadIsTransportThread) {
        boolean deframerOnTransportThreadCopy;
        boolean alreadyEnqueued;
        synchronized (this.lock) {
            deframerOnTransportThreadCopy = this.deframerOnTransportThread;
            alreadyEnqueued = this.messageProducerEnqueued;
            if (!deframerOnTransportThreadCopy) {
                this.opQueue.offer(op);
                this.messageProducerEnqueued = true;
            }
        }
        if (deframerOnTransportThreadCopy) {
            op.run(true);
            return true;
        }
        if (!alreadyEnqueued) {
            if (currentThreadIsTransportThread) {
                PerfMark.startTask("MigratingThreadDeframer.messageAvailable");
                try {
                    this.transportListener.messagesAvailable(this.messageProducer);
                    return false;
                } finally {
                    PerfMark.stopTask("MigratingThreadDeframer.messageAvailable");
                }
            }
            final Link link = PerfMark.linkOut();
            this.transportExecutor.runOnTransportThread(new Runnable() { // from class: io.grpc.internal.MigratingThreadDeframer.1
                @Override // java.lang.Runnable
                public void run() {
                    PerfMark.startTask("MigratingThreadDeframer.messageAvailable");
                    PerfMark.linkIn(link);
                    try {
                        MigratingThreadDeframer.this.transportListener.messagesAvailable(MigratingThreadDeframer.this.messageProducer);
                    } finally {
                        PerfMark.stopTask("MigratingThreadDeframer.messageAvailable");
                    }
                }
            });
            return false;
        }
        return false;
    }

    @Override // io.grpc.internal.ThreadOptimizedDeframer, io.grpc.internal.Deframer
    public void request(final int numMessages) {
        runWhereAppropriate(new Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1RequestOp
            @Override // io.grpc.internal.MigratingThreadDeframer.Op
            public void run(boolean isDeframerOnTransportThread) {
                if (isDeframerOnTransportThread) {
                    final Link link = PerfMark.linkOut();
                    MigratingThreadDeframer.this.transportExecutor.runOnTransportThread(new Runnable() { // from class: io.grpc.internal.MigratingThreadDeframer.1RequestOp.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PerfMark.startTask("MigratingThreadDeframer.request");
                            PerfMark.linkIn(link);
                            try {
                                MigratingThreadDeframer.this.requestFromTransportThread(numMessages);
                            } finally {
                                PerfMark.stopTask("MigratingThreadDeframer.request");
                            }
                        }
                    });
                } else {
                    PerfMark.startTask("MigratingThreadDeframer.request");
                    try {
                        MigratingThreadDeframer.this.deframer.request(numMessages);
                    } finally {
                        try {
                        } finally {
                        }
                    }
                }
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFromTransportThread(final int numMessages) {
        runWhereAppropriate(new Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1RequestAgainOp
            @Override // io.grpc.internal.MigratingThreadDeframer.Op
            public void run(boolean isDeframerOnTransportThread) {
                if (isDeframerOnTransportThread) {
                    try {
                        MigratingThreadDeframer.this.deframer.request(numMessages);
                    } catch (Throwable t) {
                        MigratingThreadDeframer.this.appListener.deframeFailed(t);
                        MigratingThreadDeframer.this.deframer.close();
                    }
                    if (!MigratingThreadDeframer.this.deframer.hasPendingDeliveries()) {
                        synchronized (MigratingThreadDeframer.this.lock) {
                            PerfMark.event("MigratingThreadDeframer.deframerOnApplicationThread");
                            MigratingThreadDeframer.this.migratingListener.setDelegate(MigratingThreadDeframer.this.appListener);
                            MigratingThreadDeframer.this.deframerOnTransportThread = false;
                        }
                        return;
                    }
                    return;
                }
                MigratingThreadDeframer.this.request(numMessages);
            }
        });
    }

    /* JADX INFO: renamed from: io.grpc.internal.MigratingThreadDeframer$1DeframeOp, reason: invalid class name */
    class C1DeframeOp implements Op, Closeable {
        final /* synthetic */ ReadableBuffer val$data;

        C1DeframeOp(ReadableBuffer readableBuffer) {
            this.val$data = readableBuffer;
        }

        @Override // io.grpc.internal.MigratingThreadDeframer.Op
        public void run(boolean isDeframerOnTransportThread) {
            PerfMark.startTask("MigratingThreadDeframer.deframe");
            try {
                if (isDeframerOnTransportThread) {
                    MigratingThreadDeframer.this.deframer.deframe(this.val$data);
                    return;
                }
                try {
                    MigratingThreadDeframer.this.deframer.deframe(this.val$data);
                } catch (Throwable t) {
                    MigratingThreadDeframer.this.appListener.deframeFailed(t);
                    MigratingThreadDeframer.this.deframer.close();
                }
            } finally {
                PerfMark.stopTask("MigratingThreadDeframer.deframe");
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.val$data.close();
        }
    }

    @Override // io.grpc.internal.Deframer
    public void deframe(ReadableBuffer data) {
        runWhereAppropriate(new C1DeframeOp(data));
    }

    @Override // io.grpc.internal.Deframer
    public void closeWhenComplete() {
        runWhereAppropriate(new Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1CloseWhenCompleteOp
            @Override // io.grpc.internal.MigratingThreadDeframer.Op
            public void run(boolean isDeframerOnTransportThread) {
                MigratingThreadDeframer.this.deframer.closeWhenComplete();
            }
        });
    }

    @Override // io.grpc.internal.Deframer
    public void close() {
        if (!runWhereAppropriate(new Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1CloseOp
            @Override // io.grpc.internal.MigratingThreadDeframer.Op
            public void run(boolean isDeframerOnTransportThread) {
                MigratingThreadDeframer.this.deframer.close();
            }
        })) {
            this.deframer.stopDelivery();
        }
    }

    class DeframeMessageProducer implements StreamListener.MessageProducer, Closeable {
        DeframeMessageProducer() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x002e, code lost:
        
            if (r6.this$0.deframer.hasPendingDeliveries() == false) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0030, code lost:
        
            io.perfmark.PerfMark.event("MigratingThreadDeframer.deframerOnTransportThread");
            r6.this$0.migratingListener.setDelegate(r6.this$0.transportListener);
            r6.this$0.deframerOnTransportThread = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
        
            r6.this$0.messageProducerEnqueued = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0050, code lost:
        
            return null;
         */
        @Override // io.grpc.internal.StreamListener.MessageProducer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.io.InputStream next() {
            /*
                r6 = this;
            L1:
                io.grpc.internal.MigratingThreadDeframer r0 = io.grpc.internal.MigratingThreadDeframer.this
                io.grpc.internal.ApplicationThreadDeframerListener r0 = io.grpc.internal.MigratingThreadDeframer.access$500(r0)
                java.io.InputStream r0 = r0.messageReadQueuePoll()
                if (r0 == 0) goto Le
                return r0
            Le:
                io.grpc.internal.MigratingThreadDeframer r1 = io.grpc.internal.MigratingThreadDeframer.this
                java.lang.Object r1 = io.grpc.internal.MigratingThreadDeframer.access$600(r1)
                monitor-enter(r1)
                io.grpc.internal.MigratingThreadDeframer r2 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L57
                java.util.Queue r2 = io.grpc.internal.MigratingThreadDeframer.access$900(r2)     // Catch: java.lang.Throwable -> L57
                java.lang.Object r2 = r2.poll()     // Catch: java.lang.Throwable -> L57
                io.grpc.internal.MigratingThreadDeframer$Op r2 = (io.grpc.internal.MigratingThreadDeframer.Op) r2     // Catch: java.lang.Throwable -> L57
                r3 = 0
                if (r2 != 0) goto L52
                io.grpc.internal.MigratingThreadDeframer r4 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L57
                io.grpc.internal.MessageDeframer r4 = io.grpc.internal.MigratingThreadDeframer.access$400(r4)     // Catch: java.lang.Throwable -> L57
                boolean r4 = r4.hasPendingDeliveries()     // Catch: java.lang.Throwable -> L57
                if (r4 == 0) goto L4a
                java.lang.String r4 = "MigratingThreadDeframer.deframerOnTransportThread"
                io.perfmark.PerfMark.event(r4)     // Catch: java.lang.Throwable -> L57
                io.grpc.internal.MigratingThreadDeframer r4 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L57
                io.grpc.internal.MigratingThreadDeframer$MigratingDeframerListener r4 = io.grpc.internal.MigratingThreadDeframer.access$700(r4)     // Catch: java.lang.Throwable -> L57
                io.grpc.internal.MigratingThreadDeframer r5 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L57
                io.grpc.internal.MessageDeframer$Listener r5 = io.grpc.internal.MigratingThreadDeframer.access$100(r5)     // Catch: java.lang.Throwable -> L57
                r4.setDelegate(r5)     // Catch: java.lang.Throwable -> L57
                io.grpc.internal.MigratingThreadDeframer r4 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L57
                r5 = 1
                io.grpc.internal.MigratingThreadDeframer.access$802(r4, r5)     // Catch: java.lang.Throwable -> L57
            L4a:
                io.grpc.internal.MigratingThreadDeframer r4 = io.grpc.internal.MigratingThreadDeframer.this     // Catch: java.lang.Throwable -> L57
                io.grpc.internal.MigratingThreadDeframer.access$1002(r4, r3)     // Catch: java.lang.Throwable -> L57
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L57
                r1 = 0
                return r1
            L52:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L57
                r2.run(r3)
                goto L1
            L57:
                r2 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L57
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.MigratingThreadDeframer.DeframeMessageProducer.next():java.io.InputStream");
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Op op;
            while (true) {
                synchronized (MigratingThreadDeframer.this.lock) {
                    do {
                        op = (Op) MigratingThreadDeframer.this.opQueue.poll();
                        if (op == null) {
                            break;
                        }
                    } while (!(op instanceof Closeable));
                    if (op == null) {
                        MigratingThreadDeframer.this.messageProducerEnqueued = false;
                        return;
                    }
                }
                GrpcUtil.closeQuietly((Closeable) op);
            }
        }
    }

    static class MigratingDeframerListener extends ForwardingDeframerListener {
        private MessageDeframer.Listener delegate;

        public MigratingDeframerListener(MessageDeframer.Listener delegate) {
            setDelegate(delegate);
        }

        @Override // io.grpc.internal.ForwardingDeframerListener
        protected MessageDeframer.Listener delegate() {
            return this.delegate;
        }

        public void setDelegate(MessageDeframer.Listener delegate) {
            this.delegate = (MessageDeframer.Listener) Preconditions.checkNotNull(delegate, "delegate");
        }
    }
}
