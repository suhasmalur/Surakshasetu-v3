package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ClientCall;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.Metadata;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public class DelayedClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Executor callExecutor;
    private final Context context;
    private DelayedListener<RespT> delayedListener;
    private Status error;

    @Nullable
    private final ScheduledFuture<?> initialDeadlineMonitor;
    private ClientCall.Listener<RespT> listener;
    private volatile boolean passThrough;
    private List<Runnable> pendingRunnables = new ArrayList();
    private ClientCall<ReqT, RespT> realCall;
    private static final Logger logger = Logger.getLogger(DelayedClientCall.class.getName());
    private static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() { // from class: io.grpc.internal.DelayedClientCall.8
        @Override // io.grpc.ClientCall
        public void start(ClientCall.Listener<Object> responseListener, Metadata headers) {
        }

        @Override // io.grpc.ClientCall
        public void request(int numMessages) {
        }

        @Override // io.grpc.ClientCall
        public void cancel(String message, Throwable cause) {
        }

        @Override // io.grpc.ClientCall
        public void halfClose() {
        }

        @Override // io.grpc.ClientCall
        public void sendMessage(Object message) {
        }

        @Override // io.grpc.ClientCall
        public boolean isReady() {
            return false;
        }
    };

    protected DelayedClientCall(Executor callExecutor, ScheduledExecutorService scheduler, @Nullable Deadline deadline) {
        this.callExecutor = (Executor) Preconditions.checkNotNull(callExecutor, "callExecutor");
        Preconditions.checkNotNull(scheduler, "scheduler");
        this.context = Context.current();
        this.initialDeadlineMonitor = scheduleDeadlineIfNeeded(scheduler, deadline);
    }

    private boolean isAbeforeB(@Nullable Deadline a, @Nullable Deadline b) {
        if (b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        return a.isBefore(b);
    }

    @Nullable
    private ScheduledFuture<?> scheduleDeadlineIfNeeded(ScheduledExecutorService scheduler, @Nullable Deadline deadline) {
        Deadline contextDeadline = this.context.getDeadline();
        if (deadline == null && contextDeadline == null) {
            return null;
        }
        long remainingNanos = Long.MAX_VALUE;
        if (deadline != null) {
            remainingNanos = deadline.timeRemaining(TimeUnit.NANOSECONDS);
        }
        if (contextDeadline != null && contextDeadline.timeRemaining(TimeUnit.NANOSECONDS) < remainingNanos) {
            remainingNanos = contextDeadline.timeRemaining(TimeUnit.NANOSECONDS);
            if (logger.isLoggable(Level.FINE)) {
                StringBuilder builder = new StringBuilder(String.format(Locale.US, "Call timeout set to '%d' ns, due to context deadline.", Long.valueOf(remainingNanos)));
                if (deadline == null) {
                    builder.append(" Explicit call timeout was not set.");
                } else {
                    long callTimeout = deadline.timeRemaining(TimeUnit.NANOSECONDS);
                    builder.append(String.format(Locale.US, " Explicit call timeout was '%d' ns.", Long.valueOf(callTimeout)));
                }
                logger.fine(builder.toString());
            }
        }
        long seconds = Math.abs(remainingNanos) / TimeUnit.SECONDS.toNanos(1L);
        long nanos = Math.abs(remainingNanos) % TimeUnit.SECONDS.toNanos(1L);
        final StringBuilder buf = new StringBuilder();
        String deadlineName = isAbeforeB(contextDeadline, deadline) ? "Context" : "CallOptions";
        if (remainingNanos < 0) {
            buf.append("ClientCall started after ");
            buf.append(deadlineName);
            buf.append(" deadline was exceeded. Deadline has been exceeded for ");
        } else {
            buf.append("Deadline ");
            buf.append(deadlineName);
            buf.append(" will be exceeded in ");
        }
        buf.append(seconds);
        buf.append(String.format(Locale.US, ".%09d", Long.valueOf(nanos)));
        buf.append("s. ");
        return scheduler.schedule(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.1DeadlineExceededRunnable
            @Override // java.lang.Runnable
            public void run() {
                DelayedClientCall.this.cancel(Status.DEADLINE_EXCEEDED.withDescription(buf.toString()), true);
            }
        }, remainingNanos, TimeUnit.NANOSECONDS);
    }

    public final Runnable setCall(ClientCall<ReqT, RespT> call) {
        synchronized (this) {
            if (this.realCall != null) {
                return null;
            }
            setRealCall((ClientCall) Preconditions.checkNotNull(call, NotificationCompat.CATEGORY_CALL));
            return new ContextRunnable(this.context) { // from class: io.grpc.internal.DelayedClientCall.1
                @Override // io.grpc.internal.ContextRunnable
                public void runInContext() {
                    DelayedClientCall.this.drainPendingCalls();
                }
            };
        }
    }

    @Override // io.grpc.ClientCall
    public final void start(ClientCall.Listener<RespT> listener, final Metadata metadata) {
        Status status;
        boolean z;
        Preconditions.checkState(this.listener == null, "already started");
        synchronized (this) {
            this.listener = (ClientCall.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            status = this.error;
            z = this.passThrough;
            if (!z) {
                DelayedListener<RespT> delayedListener = new DelayedListener<>(listener);
                this.delayedListener = delayedListener;
                listener = delayedListener;
            }
        }
        if (status != null) {
            this.callExecutor.execute(new CloseListenerRunnable(listener, status));
        } else if (z) {
            this.realCall.start(listener, metadata);
        } else {
            final ClientCall.Listener<RespT> listener2 = listener;
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.2
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.start(listener2, metadata);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void cancel(@Nullable String message, @Nullable Throwable cause) {
        Status status;
        Status status2 = Status.CANCELLED;
        if (message != null) {
            status = status2.withDescription(message);
        } else {
            status = status2.withDescription("Call cancelled without message");
        }
        if (cause != null) {
            status = status.withCause(cause);
        }
        cancel(status, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void cancel(final Status status, boolean onlyCancelPendingCall) {
        boolean delegateToRealCall = true;
        ClientCall.Listener<RespT> listenerToClose = null;
        synchronized (this) {
            if (this.realCall == null) {
                setRealCall(NOOP_CALL);
                delegateToRealCall = false;
                listenerToClose = this.listener;
                this.error = status;
            } else if (onlyCancelPendingCall) {
                return;
            }
            if (delegateToRealCall) {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.3
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedClientCall.this.realCall.cancel(status.getDescription(), status.getCause());
                    }
                });
            } else {
                if (listenerToClose != null) {
                    this.callExecutor.execute(new CloseListenerRunnable(listenerToClose, status));
                }
                drainPendingCalls();
            }
            callCancelled();
        }
    }

    protected void callCancelled() {
    }

    private void delayOrExecute(Runnable runnable) {
        synchronized (this) {
            if (!this.passThrough) {
                this.pendingRunnables.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0034, code lost:
    
        r1 = r0.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
    
        if (r1.hasNext() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003e, code lost:
    
        r2 = r1.next();
        r2.run();
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void drainPendingCalls() {
        /*
            r5 = this;
            io.grpc.ClientCall<ReqT, RespT> r0 = r5.realCall
            if (r0 == 0) goto L55
            boolean r0 = r5.passThrough
            if (r0 != 0) goto L4f
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        Ld:
            monitor-enter(r5)
            java.util.List<java.lang.Runnable> r1 = r5.pendingRunnables     // Catch: java.lang.Throwable -> L4c
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L4c
            if (r1 == 0) goto L2d
            r1 = 0
            r5.pendingRunnables = r1     // Catch: java.lang.Throwable -> L4c
            r1 = 1
            r5.passThrough = r1     // Catch: java.lang.Throwable -> L4c
            io.grpc.internal.DelayedClientCall$DelayedListener<RespT> r1 = r5.delayedListener     // Catch: java.lang.Throwable -> L4c
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L4c
            if (r1 == 0) goto L2c
            r2 = r1
            java.util.concurrent.Executor r3 = r5.callExecutor
            io.grpc.internal.DelayedClientCall$1DrainListenerRunnable r4 = new io.grpc.internal.DelayedClientCall$1DrainListenerRunnable
            r4.<init>()
            r3.execute(r4)
        L2c:
            return
        L2d:
            r1 = r0
            java.util.List<java.lang.Runnable> r2 = r5.pendingRunnables     // Catch: java.lang.Throwable -> L4c
            r0 = r2
            r5.pendingRunnables = r1     // Catch: java.lang.Throwable -> L4c
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L4c
            java.util.Iterator r1 = r0.iterator()
        L38:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L48
            java.lang.Object r2 = r1.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L38
        L48:
            r0.clear()
            goto Ld
        L4c:
            r1 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L4c
            throw r1
        L4f:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L55:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedClientCall.drainPendingCalls():void");
    }

    private void setRealCall(ClientCall<ReqT, RespT> realCall) {
        Preconditions.checkState(this.realCall == null, "realCall already set to %s", this.realCall);
        if (this.initialDeadlineMonitor != null) {
            this.initialDeadlineMonitor.cancel(false);
        }
        this.realCall = realCall;
    }

    final ClientCall<ReqT, RespT> getRealCall() {
        return this.realCall;
    }

    @Override // io.grpc.ClientCall
    public final void sendMessage(final ReqT message) {
        if (this.passThrough) {
            this.realCall.sendMessage(message);
        } else {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.4
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.sendMessage(message);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void setMessageCompression(final boolean enable) {
        if (this.passThrough) {
            this.realCall.setMessageCompression(enable);
        } else {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.5
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.setMessageCompression(enable);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void request(final int numMessages) {
        if (this.passThrough) {
            this.realCall.request(numMessages);
        } else {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.6
                @Override // java.lang.Runnable
                public void run() {
                    DelayedClientCall.this.realCall.request(numMessages);
                }
            });
        }
    }

    @Override // io.grpc.ClientCall
    public final void halfClose() {
        delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.7
            @Override // java.lang.Runnable
            public void run() {
                DelayedClientCall.this.realCall.halfClose();
            }
        });
    }

    @Override // io.grpc.ClientCall
    public final boolean isReady() {
        if (this.passThrough) {
            return this.realCall.isReady();
        }
        return false;
    }

    @Override // io.grpc.ClientCall
    public final Attributes getAttributes() {
        ClientCall<ReqT, RespT> savedRealCall;
        synchronized (this) {
            savedRealCall = this.realCall;
        }
        if (savedRealCall != null) {
            return savedRealCall.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("realCall", this.realCall).toString();
    }

    private final class CloseListenerRunnable extends ContextRunnable {
        final ClientCall.Listener<RespT> listener;
        final Status status;

        CloseListenerRunnable(ClientCall.Listener<RespT> listener, Status status) {
            super(DelayedClientCall.this.context);
            this.listener = listener;
            this.status = status;
        }

        @Override // io.grpc.internal.ContextRunnable
        public void runInContext() {
            this.listener.onClose(this.status, new Metadata());
        }
    }

    private static final class DelayedListener<RespT> extends ClientCall.Listener<RespT> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private volatile boolean passThrough;
        private List<Runnable> pendingCallbacks = new ArrayList();
        private final ClientCall.Listener<RespT> realListener;

        public DelayedListener(ClientCall.Listener<RespT> listener) {
            this.realListener = listener;
        }

        private void delayOrExecute(Runnable runnable) {
            synchronized (this) {
                if (!this.passThrough) {
                    this.pendingCallbacks.add(runnable);
                } else {
                    runnable.run();
                }
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onHeaders(final Metadata headers) {
            if (this.passThrough) {
                this.realListener.onHeaders(headers);
            } else {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedListener.this.realListener.onHeaders(headers);
                    }
                });
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onMessage(final RespT message) {
            if (this.passThrough) {
                this.realListener.onMessage(message);
            } else {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.2
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedListener.this.realListener.onMessage(message);
                    }
                });
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onClose(final Status status, final Metadata trailers) {
            delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.3
                @Override // java.lang.Runnable
                public void run() {
                    DelayedListener.this.realListener.onClose(status, trailers);
                }
            });
        }

        @Override // io.grpc.ClientCall.Listener
        public void onReady() {
            if (this.passThrough) {
                this.realListener.onReady();
            } else {
                delayOrExecute(new Runnable() { // from class: io.grpc.internal.DelayedClientCall.DelayedListener.4
                    @Override // java.lang.Runnable
                    public void run() {
                        DelayedListener.this.realListener.onReady();
                    }
                });
            }
        }

        void drainPendingCallbacks() {
            if (this.passThrough) {
                throw new AssertionError();
            }
            List<Runnable> toRun = new ArrayList<>();
            while (true) {
                synchronized (this) {
                    if (this.pendingCallbacks.isEmpty()) {
                        this.pendingCallbacks = null;
                        this.passThrough = true;
                        return;
                    } else {
                        List<Runnable> tmp = toRun;
                        toRun = this.pendingCallbacks;
                        this.pendingCallbacks = tmp;
                    }
                }
                for (Runnable runnable : toRun) {
                    runnable.run();
                }
                toRun.clear();
            }
        }
    }
}
