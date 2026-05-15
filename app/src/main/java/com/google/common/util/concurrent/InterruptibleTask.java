package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes10.dex */
@ElementTypesAreNonnullByDefault
abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable DONE;
    private static final int MAX_BUSY_WAIT_SPINS = 1000;
    private static final Runnable PARKED;

    abstract void afterRanInterruptiblyFailure(Throwable th);

    abstract void afterRanInterruptiblySuccess(@ParametricNullness T t);

    abstract boolean isDone();

    @ParametricNullness
    abstract T runInterruptibly() throws Exception;

    abstract String toPendingString();

    InterruptibleTask() {
    }

    static {
        DONE = new DoNothingRunnable();
        PARKED = new DoNothingRunnable();
    }

    private static final class DoNothingRunnable implements Runnable {
        private DoNothingRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() throws java.lang.Exception {
        /*
            r5 = this;
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r1 = 0
            boolean r1 = r5.compareAndSet(r1, r0)
            if (r1 != 0) goto Lc
            return
        Lc:
            boolean r1 = r5.isDone()
            r1 = r1 ^ 1
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L30
            java.lang.Object r4 = r5.runInterruptibly()     // Catch: java.lang.Throwable -> L1c
            r2 = r4
            goto L30
        L1c:
            r4 = move-exception
            r3 = r4
            java.lang.Runnable r4 = com.google.common.util.concurrent.InterruptibleTask.DONE
            boolean r4 = r5.compareAndSet(r0, r4)
            if (r4 != 0) goto L29
            r5.waitForInterrupt(r0)
        L29:
            if (r1 == 0) goto L46
        L2c:
            r5.afterRanInterruptiblyFailure(r3)
            goto L46
        L30:
            java.lang.Runnable r4 = com.google.common.util.concurrent.InterruptibleTask.DONE
            boolean r4 = r5.compareAndSet(r0, r4)
            if (r4 != 0) goto L3b
            r5.waitForInterrupt(r0)
        L3b:
            if (r1 == 0) goto L46
            if (r3 != 0) goto L2c
            java.lang.Object r4 = com.google.common.util.concurrent.NullnessCasts.uncheckedCastNullableTToT(r2)
            r5.afterRanInterruptiblySuccess(r4)
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.InterruptibleTask.run():void");
    }

    private void waitForInterrupt(Thread currentThread) {
        boolean restoreInterruptedBit = false;
        int spinCount = 0;
        Runnable state = get();
        Blocker blocker = null;
        while (true) {
            if (!(state instanceof Blocker) && state != PARKED) {
                break;
            }
            if (state instanceof Blocker) {
                blocker = (Blocker) state;
            }
            spinCount++;
            if (spinCount > 1000) {
                if (state == PARKED || compareAndSet(state, PARKED)) {
                    restoreInterruptedBit = Thread.interrupted() || restoreInterruptedBit;
                    LockSupport.park(blocker);
                }
            } else {
                Thread.yield();
            }
            Runnable state2 = get();
            state = state2;
        }
        if (restoreInterruptedBit) {
            currentThread.interrupt();
        }
    }

    final void interruptTask() {
        Runnable currentRunner = get();
        if (currentRunner instanceof Thread) {
            Blocker blocker = new Blocker();
            blocker.setOwner(Thread.currentThread());
            if (compareAndSet(currentRunner, blocker)) {
                try {
                    ((Thread) currentRunner).interrupt();
                } finally {
                    Runnable prev = getAndSet(DONE);
                    if (prev == PARKED) {
                        LockSupport.unpark((Thread) currentRunner);
                    }
                }
            }
        }
    }

    static final class Blocker extends AbstractOwnableSynchronizer implements Runnable {
        private final InterruptibleTask<?> task;

        private Blocker(InterruptibleTask<?> task) {
            this.task = task;
        }

        @Override // java.lang.Runnable
        public void run() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOwner(Thread thread) {
            super.setExclusiveOwnerThread(thread);
        }

        public String toString() {
            return this.task.toString();
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String result;
        Runnable state = get();
        if (state == DONE) {
            result = "running=[DONE]";
        } else if (state instanceof Blocker) {
            result = "running=[INTERRUPTED]";
        } else if (state instanceof Thread) {
            String name = ((Thread) state).getName();
            result = new StringBuilder(String.valueOf(name).length() + 21).append("running=[RUNNING ON ").append(name).append("]").toString();
        } else {
            result = "running=[NOT STARTED YET]";
        }
        String pendingString = toPendingString();
        return new StringBuilder(String.valueOf(result).length() + 2 + String.valueOf(pendingString).length()).append(result).append(", ").append(pendingString).toString();
    }
}
