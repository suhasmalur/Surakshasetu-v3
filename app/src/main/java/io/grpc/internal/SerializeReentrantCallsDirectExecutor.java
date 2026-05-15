package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes10.dex */
class SerializeReentrantCallsDirectExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SerializeReentrantCallsDirectExecutor.class.getName());
    private boolean executing;
    private ArrayDeque<Runnable> taskQueue;

    SerializeReentrantCallsDirectExecutor() {
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable task) {
        Preconditions.checkNotNull(task, "'task' must not be null.");
        if (!this.executing) {
            this.executing = true;
            try {
                task.run();
            } catch (Throwable t) {
                try {
                    log.log(Level.SEVERE, "Exception while executing runnable " + task, t);
                    if (this.taskQueue != null) {
                    }
                } finally {
                    if (this.taskQueue != null) {
                        completeQueuedTasks();
                    }
                    this.executing = false;
                }
            }
            return;
        }
        enqueue(task);
    }

    private void completeQueuedTasks() {
        while (true) {
            Runnable task = this.taskQueue.poll();
            if (task != null) {
                try {
                    task.run();
                } catch (Throwable t) {
                    log.log(Level.SEVERE, "Exception while executing runnable " + task, t);
                }
            } else {
                return;
            }
        }
    }

    private void enqueue(Runnable r) {
        if (this.taskQueue == null) {
            this.taskQueue = new ArrayDeque<>(4);
        }
        this.taskQueue.add(r);
    }
}
