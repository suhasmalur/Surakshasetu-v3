package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframer;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import java.io.InputStream;

/* JADX INFO: loaded from: classes10.dex */
public abstract class AbstractStream implements Stream {
    protected abstract Framer framer();

    protected abstract TransportState transportState();

    @Override // io.grpc.internal.Stream
    public void optimizeForDirectExecutor() {
        transportState().optimizeForDirectExecutor();
    }

    @Override // io.grpc.internal.Stream
    public final void setMessageCompression(boolean enable) {
        framer().setMessageCompression(enable);
    }

    @Override // io.grpc.internal.Stream
    public final void request(int numMessages) {
        transportState().requestMessagesFromDeframer(numMessages);
    }

    @Override // io.grpc.internal.Stream
    public final void writeMessage(InputStream message) {
        Preconditions.checkNotNull(message, "message");
        try {
            if (!framer().isClosed()) {
                framer().writePayload(message);
            }
        } finally {
            GrpcUtil.closeQuietly(message);
        }
    }

    @Override // io.grpc.internal.Stream
    public final void flush() {
        if (!framer().isClosed()) {
            framer().flush();
        }
    }

    protected final void endOfMessages() {
        framer().close();
    }

    @Override // io.grpc.internal.Stream
    public final void setCompressor(Compressor compressor) {
        framer().setCompressor((Compressor) Preconditions.checkNotNull(compressor, "compressor"));
    }

    @Override // io.grpc.internal.Stream
    public boolean isReady() {
        return transportState().isReady();
    }

    protected final void onSendingBytes(int numBytes) {
        transportState().onSendingBytes(numBytes);
    }

    public static abstract class TransportState implements ApplicationThreadDeframer.TransportExecutor, MessageDeframer.Listener {
        public static final int DEFAULT_ONREADY_THRESHOLD = 32768;
        private boolean allocated;
        private boolean deallocated;
        private Deframer deframer;
        private int numSentBytesQueued;
        private final Object onReadyLock = new Object();
        private final MessageDeframer rawDeframer;
        private final StatsTraceContext statsTraceCtx;
        private final TransportTracer transportTracer;

        protected abstract StreamListener listener();

        protected TransportState(int maxMessageSize, StatsTraceContext statsTraceCtx, TransportTracer transportTracer) {
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceCtx, "statsTraceCtx");
            this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
            this.rawDeframer = new MessageDeframer(this, Codec.Identity.NONE, maxMessageSize, statsTraceCtx, transportTracer);
            this.deframer = this.rawDeframer;
        }

        final void optimizeForDirectExecutor() {
            this.rawDeframer.setListener(this);
            this.deframer = this.rawDeframer;
        }

        protected void setFullStreamDecompressor(GzipInflatingBuffer fullStreamDecompressor) {
            this.rawDeframer.setFullStreamDecompressor(fullStreamDecompressor);
            this.deframer = new ApplicationThreadDeframer(this, this, this.rawDeframer);
        }

        final void setMaxInboundMessageSize(int maxSize) {
            this.deframer.setMaxInboundMessageSize(maxSize);
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void messagesAvailable(StreamListener.MessageProducer producer) {
            listener().messagesAvailable(producer);
        }

        protected final void closeDeframer(boolean stopDelivery) {
            if (stopDelivery) {
                this.deframer.close();
            } else {
                this.deframer.closeWhenComplete();
            }
        }

        protected final void deframe(ReadableBuffer frame) {
            try {
                this.deframer.deframe(frame);
            } catch (Throwable t) {
                deframeFailed(t);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestMessagesFromDeframer(final int numMessages) {
            if (this.deframer instanceof ThreadOptimizedDeframer) {
                PerfMark.startTask("AbstractStream.request");
                try {
                    this.deframer.request(numMessages);
                    return;
                } finally {
                    PerfMark.stopTask("AbstractStream.request");
                }
            }
            final Link link = PerfMark.linkOut();
            runOnTransportThread(new Runnable() { // from class: io.grpc.internal.AbstractStream.TransportState.1RequestRunnable
                @Override // java.lang.Runnable
                public void run() {
                    PerfMark.startTask("AbstractStream.request");
                    PerfMark.linkIn(link);
                    try {
                        TransportState.this.deframer.request(numMessages);
                    } finally {
                        try {
                        } finally {
                        }
                    }
                }
            });
        }

        public final void requestMessagesFromDeframerForTesting(int numMessages) {
            requestMessagesFromDeframer(numMessages);
        }

        public final StatsTraceContext getStatsTraceContext() {
            return this.statsTraceCtx;
        }

        protected final void setDecompressor(Decompressor decompressor) {
            this.deframer.setDecompressor(decompressor);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isReady() {
            boolean z;
            synchronized (this.onReadyLock) {
                z = this.allocated && this.numSentBytesQueued < 32768 && !this.deallocated;
            }
            return z;
        }

        protected void onStreamAllocated() {
            Preconditions.checkState(listener() != null);
            synchronized (this.onReadyLock) {
                Preconditions.checkState(this.allocated ? false : true, "Already allocated");
                this.allocated = true;
            }
            notifyIfReady();
        }

        protected final void onStreamDeallocated() {
            synchronized (this.onReadyLock) {
                this.deallocated = true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onSendingBytes(int numBytes) {
            synchronized (this.onReadyLock) {
                this.numSentBytesQueued += numBytes;
            }
        }

        public final void onSentBytes(int numBytes) {
            boolean belowThresholdBefore;
            synchronized (this.onReadyLock) {
                Preconditions.checkState(this.allocated, "onStreamAllocated was not called, but it seems the stream is active");
                boolean z = true;
                boolean belowThresholdBefore2 = this.numSentBytesQueued < 32768;
                this.numSentBytesQueued -= numBytes;
                boolean belowThresholdAfter = this.numSentBytesQueued < 32768;
                if (belowThresholdBefore2 || !belowThresholdAfter) {
                    z = false;
                }
                belowThresholdBefore = z;
            }
            if (belowThresholdBefore) {
                notifyIfReady();
            }
        }

        protected TransportTracer getTransportTracer() {
            return this.transportTracer;
        }

        private void notifyIfReady() {
            boolean doNotify;
            synchronized (this.onReadyLock) {
                doNotify = isReady();
            }
            if (doNotify) {
                listener().onReady();
            }
        }
    }
}
