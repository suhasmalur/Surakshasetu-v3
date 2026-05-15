package com.google.firebase.firestore.util;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.util.AsyncQueue;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;

/* JADX INFO: loaded from: classes10.dex */
public class AsyncQueue {
    private final ArrayList<TimerId> timerIdsToSkip = new ArrayList<>();
    private final ArrayList<DelayedTask> delayedTasks = new ArrayList<>();
    private final SynchronizedShutdownAwareExecutor executor = new SynchronizedShutdownAwareExecutor();

    public enum TimerId {
        ALL,
        LISTEN_STREAM_IDLE,
        LISTEN_STREAM_CONNECTION_BACKOFF,
        WRITE_STREAM_IDLE,
        WRITE_STREAM_CONNECTION_BACKOFF,
        HEALTH_CHECK_TIMEOUT,
        ONLINE_STATE_TIMEOUT,
        GARBAGE_COLLECTION,
        RETRY_TRANSACTION,
        CONNECTIVITY_ATTEMPT_TIMER,
        INDEX_BACKFILL
    }

    public class DelayedTask {
        private ScheduledFuture scheduledFuture;
        private final long targetTimeMs;
        private final Runnable task;
        private final TimerId timerId;

        private DelayedTask(TimerId timerId, long targetTimeMs, Runnable task) {
            this.timerId = timerId;
            this.targetTimeMs = targetTimeMs;
            this.task = task;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void start(long delayMs) {
            this.scheduledFuture = AsyncQueue.this.executor.schedule(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$DelayedTask$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.handleDelayElapsed();
                }
            }, delayMs, TimeUnit.MILLISECONDS);
        }

        void skipDelay() {
            handleDelayElapsed();
        }

        public void cancel() {
            AsyncQueue.this.verifyIsCurrentThread();
            if (this.scheduledFuture != null) {
                this.scheduledFuture.cancel(false);
                markDone();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleDelayElapsed() {
            AsyncQueue.this.verifyIsCurrentThread();
            if (this.scheduledFuture != null) {
                markDone();
                this.task.run();
            }
        }

        private void markDone() {
            Assert.hardAssert(this.scheduledFuture != null, "Caller should have verified scheduledFuture is non-null.", new Object[0]);
            this.scheduledFuture = null;
            AsyncQueue.this.removeDelayedTask(this);
        }
    }

    public static <TResult> Task<TResult> callTask(final Executor executor, final Callable<Task<TResult>> task) {
        final TaskCompletionSource<TResult> tcs = new TaskCompletionSource<>();
        executor.execute(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                AsyncQueue.lambda$callTask$1(task, executor, tcs);
            }
        });
        return tcs.getTask();
    }

    static /* synthetic */ void lambda$callTask$1(Callable task, Executor executor, final TaskCompletionSource tcs) {
        try {
            ((Task) task.call()).continueWith(executor, new Continuation() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda2
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task2) {
                    return AsyncQueue.lambda$callTask$0(tcs, task2);
                }
            });
        } catch (Exception e) {
            tcs.setException(e);
        } catch (Throwable t) {
            Exception e2 = new IllegalStateException("Unhandled throwable in callTask.", t);
            tcs.setException(e2);
        }
    }

    static /* synthetic */ Void lambda$callTask$0(TaskCompletionSource tcs, Task task1) throws Exception {
        if (task1.isSuccessful()) {
            tcs.setResult(task1.getResult());
            return null;
        }
        tcs.setException(task1.getException());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class SynchronizedShutdownAwareExecutor implements Executor {
        private final ScheduledThreadPoolExecutor internalExecutor;
        private boolean isShuttingDown;
        private final Thread thread;

        private class DelayedStartFactory implements Runnable, ThreadFactory {
            private Runnable delegate;
            private final CountDownLatch latch;

            private DelayedStartFactory() {
                this.latch = new CountDownLatch(1);
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.latch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                this.delegate.run();
            }

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Assert.hardAssert(this.delegate == null, "Only one thread may be created in an AsyncQueue.", new Object[0]);
                this.delegate = runnable;
                this.latch.countDown();
                return SynchronizedShutdownAwareExecutor.this.thread;
            }
        }

        SynchronizedShutdownAwareExecutor() {
            DelayedStartFactory threadFactory = new DelayedStartFactory();
            this.thread = java.util.concurrent.Executors.defaultThreadFactory().newThread(threadFactory);
            this.thread.setName("FirestoreWorker");
            this.thread.setDaemon(true);
            this.thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.google.firebase.firestore.util.AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public final void uncaughtException(Thread thread, Throwable th) {
                    this.f$0.m398x23fada6e(thread, th);
                }
            });
            this.internalExecutor = new ScheduledThreadPoolExecutor(1, threadFactory) { // from class: com.google.firebase.firestore.util.AsyncQueue.SynchronizedShutdownAwareExecutor.1
                @Override // java.util.concurrent.ThreadPoolExecutor
                protected void afterExecute(Runnable r, Throwable t) {
                    super.afterExecute(r, t);
                    if (t == null && (r instanceof Future)) {
                        Future<?> future = (Future) r;
                        try {
                            if (future.isDone()) {
                                future.get();
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } catch (CancellationException e2) {
                        } catch (ExecutionException ee) {
                            t = ee.getCause();
                        }
                    }
                    if (t != null) {
                        AsyncQueue.this.panic(t);
                    }
                }
            };
            this.internalExecutor.setKeepAliveTime(3L, TimeUnit.SECONDS);
            this.isShuttingDown = false;
        }

        /* JADX INFO: renamed from: lambda$new$0$com-google-firebase-firestore-util-AsyncQueue$SynchronizedShutdownAwareExecutor, reason: not valid java name */
        /* synthetic */ void m398x23fada6e(Thread crashingThread, Throwable throwable) {
            AsyncQueue.this.panic(throwable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized boolean isShuttingDown() {
            return this.isShuttingDown;
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(Runnable command) {
            if (!this.isShuttingDown) {
                this.internalExecutor.execute(command);
            }
        }

        public void executeEvenAfterShutdown(Runnable command) {
            try {
                this.internalExecutor.execute(command);
            } catch (RejectedExecutionException e) {
                Logger.warn(AsyncQueue.class.getSimpleName(), "Refused to enqueue task after panic", new Object[0]);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T> Task<T> executeAndReportResult(final Callable<T> task) {
            final TaskCompletionSource<T> completionSource = new TaskCompletionSource<>();
            try {
                execute(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        AsyncQueue.SynchronizedShutdownAwareExecutor.lambda$executeAndReportResult$1(completionSource, task);
                    }
                });
            } catch (RejectedExecutionException e) {
                Logger.warn(AsyncQueue.class.getSimpleName(), "Refused to enqueue task after panic", new Object[0]);
            }
            return completionSource.getTask();
        }

        static /* synthetic */ void lambda$executeAndReportResult$1(TaskCompletionSource completionSource, Callable task) {
            try {
                completionSource.setResult(task.call());
            } catch (Exception e) {
                completionSource.setException(e);
                throw new RuntimeException(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized Task<Void> executeAndInitiateShutdown(final Runnable task) {
            if (isShuttingDown()) {
                TaskCompletionSource<Void> source = new TaskCompletionSource<>();
                source.setResult(null);
                return source.getTask();
            }
            Task<Void> t = executeAndReportResult(new Callable() { // from class: com.google.firebase.firestore.util.AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return AsyncQueue.SynchronizedShutdownAwareExecutor.lambda$executeAndInitiateShutdown$2(task);
                }
            });
            this.isShuttingDown = true;
            return t;
        }

        static /* synthetic */ Void lambda$executeAndInitiateShutdown$2(Runnable task) throws Exception {
            task.run();
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
            if (this.isShuttingDown) {
                return null;
            }
            return this.internalExecutor.schedule(command, delay, unit);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void shutdownNow() {
            this.internalExecutor.shutdownNow();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCorePoolSize(int size) {
            this.internalExecutor.setCorePoolSize(size);
        }
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public void verifyIsCurrentThread() {
        Thread current = Thread.currentThread();
        if (this.executor.thread != current) {
            throw Assert.fail("We are running on the wrong thread. Expected to be on the AsyncQueue thread %s/%d but was %s/%d", this.executor.thread.getName(), Long.valueOf(this.executor.thread.getId()), current.getName(), Long.valueOf(current.getId()));
        }
    }

    @CheckReturnValue
    public <T> Task<T> enqueue(Callable<T> task) {
        return this.executor.executeAndReportResult(task);
    }

    @CheckReturnValue
    public Task<Void> enqueue(final Runnable task) {
        return enqueue(new Callable() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AsyncQueue.lambda$enqueue$2(task);
            }
        });
    }

    static /* synthetic */ Void lambda$enqueue$2(Runnable task) throws Exception {
        task.run();
        return null;
    }

    public Task<Void> enqueueAndInitiateShutdown(Runnable task) {
        return this.executor.executeAndInitiateShutdown(task);
    }

    public void enqueueAndForgetEvenAfterShutdown(Runnable task) {
        this.executor.executeEvenAfterShutdown(task);
    }

    public boolean isShuttingDown() {
        return this.executor.isShuttingDown();
    }

    public void enqueueAndForget(Runnable task) {
        enqueue(task);
    }

    public DelayedTask enqueueAfterDelay(TimerId timerId, long delayMs, Runnable task) {
        if (this.timerIdsToSkip.contains(timerId)) {
            delayMs = 0;
        }
        DelayedTask delayedTask = createAndScheduleDelayedTask(timerId, delayMs, task);
        this.delayedTasks.add(delayedTask);
        return delayedTask;
    }

    public void skipDelaysForTimerId(TimerId timerId) {
        this.timerIdsToSkip.add(timerId);
    }

    public void panic(final Throwable t) {
        this.executor.shutdownNow();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AsyncQueue.lambda$panic$3(t);
            }
        });
    }

    static /* synthetic */ void lambda$panic$3(Throwable t) {
        if (t instanceof OutOfMemoryError) {
            OutOfMemoryError error = new OutOfMemoryError("Firestore (24.10.0) ran out of memory. Check your queries to make sure they are not loading an excessive amount of data.");
            error.initCause(t);
            throw error;
        }
        throw new RuntimeException("Internal error in Cloud Firestore (24.10.0).", t);
    }

    public void runSync(final Runnable task) throws InterruptedException {
        final Semaphore done = new Semaphore(0);
        final Throwable[] t = new Throwable[1];
        enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                AsyncQueue.lambda$runSync$4(task, t, done);
            }
        });
        done.acquire(1);
        if (t[0] != null) {
            throw new RuntimeException("Synchronous task failed", t[0]);
        }
    }

    static /* synthetic */ void lambda$runSync$4(Runnable task, Throwable[] t, Semaphore done) {
        try {
            task.run();
        } catch (Throwable throwable) {
            t[0] = throwable;
        }
        done.release();
    }

    public boolean containsDelayedTask(TimerId timerId) {
        for (DelayedTask delayedTask : this.delayedTasks) {
            if (delayedTask.timerId == timerId) {
                return true;
            }
        }
        return false;
    }

    public void runDelayedTasksUntil(final TimerId lastTimerId) throws InterruptedException {
        runSync(new Runnable() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m397xc3727432(lastTimerId);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$runDelayedTasksUntil$6$com-google-firebase-firestore-util-AsyncQueue, reason: not valid java name */
    /* synthetic */ void m397xc3727432(TimerId lastTimerId) {
        Assert.hardAssert(lastTimerId == TimerId.ALL || containsDelayedTask(lastTimerId), "Attempted to run tasks until missing TimerId: %s", lastTimerId);
        Collections.sort(this.delayedTasks, new Comparator() { // from class: com.google.firebase.firestore.util.AsyncQueue$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Long.compare(((AsyncQueue.DelayedTask) obj).targetTimeMs, ((AsyncQueue.DelayedTask) obj2).targetTimeMs);
            }
        });
        for (DelayedTask delayedTask : new ArrayList(this.delayedTasks)) {
            delayedTask.skipDelay();
            if (lastTimerId != TimerId.ALL && delayedTask.timerId == lastTimerId) {
                return;
            }
        }
    }

    public void shutdown() {
        this.executor.setCorePoolSize(0);
    }

    private DelayedTask createAndScheduleDelayedTask(TimerId timerId, long delayMs, Runnable task) {
        long targetTimeMs = System.currentTimeMillis() + delayMs;
        DelayedTask delayedTask = new DelayedTask(timerId, targetTimeMs, task);
        delayedTask.start(delayMs);
        return delayedTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDelayedTask(DelayedTask task) {
        boolean found = this.delayedTasks.remove(task);
        Assert.hardAssert(found, "Delayed task not found.", new Object[0]);
    }
}
