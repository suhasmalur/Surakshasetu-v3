package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;
import java.util.Date;

/* JADX INFO: loaded from: classes10.dex */
public class ExponentialBackoff {
    public static final double DEFAULT_BACKOFF_FACTOR = 1.5d;
    public static final long DEFAULT_BACKOFF_INITIAL_DELAY_MS = 1000;
    public static final long DEFAULT_BACKOFF_MAX_DELAY_MS = 60000;
    private final double backoffFactor;
    private long currentBaseMs;
    private final long initialDelayMs;
    private long lastAttemptTime;
    private final long maxDelayMs;
    private long nextMaxDelayMs;
    private final AsyncQueue queue;
    private final AsyncQueue.TimerId timerId;
    private AsyncQueue.DelayedTask timerTask;

    public ExponentialBackoff(AsyncQueue queue, AsyncQueue.TimerId timerId, long initialDelayMs, double backoffFactor, long maxDelayMs) {
        this.queue = queue;
        this.timerId = timerId;
        this.initialDelayMs = initialDelayMs;
        this.backoffFactor = backoffFactor;
        this.maxDelayMs = maxDelayMs;
        this.nextMaxDelayMs = maxDelayMs;
        this.lastAttemptTime = new Date().getTime();
        reset();
    }

    public ExponentialBackoff(AsyncQueue queue, AsyncQueue.TimerId timerId) {
        this(queue, timerId, 1000L, 1.5d, DEFAULT_BACKOFF_MAX_DELAY_MS);
    }

    public void reset() {
        this.currentBaseMs = 0L;
    }

    public void resetToMax() {
        this.currentBaseMs = this.nextMaxDelayMs;
    }

    public void setTemporaryMaxDelay(long newMax) {
        this.nextMaxDelayMs = newMax;
    }

    public void backoffAndRun(final Runnable task) {
        cancel();
        long desiredDelayWithJitterMs = this.currentBaseMs + jitterDelayMs();
        long delaySoFarMs = Math.max(0L, new Date().getTime() - this.lastAttemptTime);
        long remainingDelayMs = Math.max(0L, desiredDelayWithJitterMs - delaySoFarMs);
        if (this.currentBaseMs > 0) {
            Logger.debug(getClass().getSimpleName(), "Backing off for %d ms (base delay: %d ms, delay with jitter: %d ms, last attempt: %d ms ago)", Long.valueOf(remainingDelayMs), Long.valueOf(this.currentBaseMs), Long.valueOf(desiredDelayWithJitterMs), Long.valueOf(delaySoFarMs));
        }
        this.timerTask = this.queue.enqueueAfterDelay(this.timerId, remainingDelayMs, new Runnable() { // from class: com.google.firebase.firestore.util.ExponentialBackoff$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m400x589b7455(task);
            }
        });
        this.currentBaseMs = (long) (this.currentBaseMs * this.backoffFactor);
        if (this.currentBaseMs < this.initialDelayMs) {
            this.currentBaseMs = this.initialDelayMs;
        } else if (this.currentBaseMs > this.nextMaxDelayMs) {
            this.currentBaseMs = this.nextMaxDelayMs;
        }
        this.nextMaxDelayMs = this.maxDelayMs;
    }

    /* JADX INFO: renamed from: lambda$backoffAndRun$0$com-google-firebase-firestore-util-ExponentialBackoff, reason: not valid java name */
    /* synthetic */ void m400x589b7455(Runnable task) {
        this.lastAttemptTime = new Date().getTime();
        task.run();
    }

    public void cancel() {
        if (this.timerTask != null) {
            this.timerTask.cancel();
            this.timerTask = null;
        }
    }

    private long jitterDelayMs() {
        return (long) ((Math.random() - 0.5d) * this.currentBaseMs);
    }
}
