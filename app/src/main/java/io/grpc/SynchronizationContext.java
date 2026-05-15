package io.grpc;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.base.Preconditions;
import java.lang.Thread;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes10.dex */
public final class SynchronizationContext implements Executor {
    private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private final Queue<Runnable> queue = new ConcurrentLinkedQueue();
    private final AtomicReference<Thread> drainingThread = new AtomicReference<>();

    public SynchronizationContext(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = (Thread.UncaughtExceptionHandler) Preconditions.checkNotNull(uncaughtExceptionHandler, "uncaughtExceptionHandler");
    }

    public final void drain() {
        while (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.drainingThread, null, Thread.currentThread())) {
            while (true) {
                try {
                    Runnable runnable = this.queue.poll();
                    if (runnable == null) {
                        break;
                    }
                    try {
                        runnable.run();
                    } catch (Throwable t) {
                        this.uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), t);
                    }
                } catch (Throwable th) {
                    this.drainingThread.set(null);
                    throw th;
                }
            }
            this.drainingThread.set(null);
            if (this.queue.isEmpty()) {
                return;
            }
        }
    }

    public final void executeLater(Runnable runnable) {
        this.queue.add((Runnable) Preconditions.checkNotNull(runnable, "runnable is null"));
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable task) {
        executeLater(task);
        drain();
    }

    public void throwIfNotInThisSynchronizationContext() {
        Preconditions.checkState(Thread.currentThread() == this.drainingThread.get(), "Not called from the SynchronizationContext");
    }

    public final ScheduledHandle schedule(final Runnable task, long delay, TimeUnit unit, ScheduledExecutorService timerService) {
        final ManagedRunnable runnable = new ManagedRunnable(task);
        ScheduledFuture<?> future = timerService.schedule(new Runnable() { // from class: io.grpc.SynchronizationContext.1
            @Override // java.lang.Runnable
            public void run() {
                SynchronizationContext.this.execute(runnable);
            }

            public String toString() {
                return task.toString() + "(scheduled in SynchronizationContext)";
            }
        }, delay, unit);
        return new ScheduledHandle(runnable, future);
    }

    public final ScheduledHandle scheduleWithFixedDelay(final Runnable task, long initialDelay, final long delay, TimeUnit unit, ScheduledExecutorService timerService) {
        final ManagedRunnable runnable = new ManagedRunnable(task);
        ScheduledFuture<?> future = timerService.scheduleWithFixedDelay(new Runnable() { // from class: io.grpc.SynchronizationContext.2
            @Override // java.lang.Runnable
            public void run() {
                SynchronizationContext.this.execute(runnable);
            }

            public String toString() {
                return task.toString() + "(scheduled in SynchronizationContext with delay of " + delay + ")";
            }
        }, initialDelay, delay, unit);
        return new ScheduledHandle(runnable, future);
    }

    private static class ManagedRunnable implements Runnable {
        boolean hasStarted;
        boolean isCancelled;
        final Runnable task;

        ManagedRunnable(Runnable task) {
            this.task = (Runnable) Preconditions.checkNotNull(task, "task");
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.isCancelled) {
                this.hasStarted = true;
                this.task.run();
            }
        }
    }

    public static final class ScheduledHandle {
        private final ScheduledFuture<?> future;
        private final ManagedRunnable runnable;

        private ScheduledHandle(ManagedRunnable runnable, ScheduledFuture<?> future) {
            this.runnable = (ManagedRunnable) Preconditions.checkNotNull(runnable, "runnable");
            this.future = (ScheduledFuture) Preconditions.checkNotNull(future, "future");
        }

        public void cancel() {
            this.runnable.isCancelled = true;
            this.future.cancel(false);
        }

        public boolean isPending() {
            return (this.runnable.hasStarted || this.runnable.isCancelled) ? false : true;
        }
    }
}
