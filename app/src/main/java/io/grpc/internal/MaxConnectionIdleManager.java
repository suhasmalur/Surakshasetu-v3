package io.grpc.internal;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes10.dex */
public final class MaxConnectionIdleManager {
    private static final Ticker systemTicker = new Ticker() { // from class: io.grpc.internal.MaxConnectionIdleManager.1
        @Override // io.grpc.internal.MaxConnectionIdleManager.Ticker
        public long nanoTime() {
            return System.nanoTime();
        }
    };
    private boolean isActive;
    private final long maxConnectionIdleInNanos;
    private long nextIdleMonitorTime;
    private ScheduledExecutorService scheduler;
    private boolean shutdownDelayed;

    @CheckForNull
    private ScheduledFuture<?> shutdownFuture;
    private Runnable shutdownTask;
    private final Ticker ticker;

    public interface Ticker {
        long nanoTime();
    }

    public MaxConnectionIdleManager(long maxConnectionIdleInNanos) {
        this(maxConnectionIdleInNanos, systemTicker);
    }

    public MaxConnectionIdleManager(long maxConnectionIdleInNanos, Ticker ticker) {
        this.maxConnectionIdleInNanos = maxConnectionIdleInNanos;
        this.ticker = ticker;
    }

    public void start(final Runnable closeJob, final ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
        this.nextIdleMonitorTime = this.ticker.nanoTime() + this.maxConnectionIdleInNanos;
        this.shutdownTask = new LogExceptionRunnable(new Runnable() { // from class: io.grpc.internal.MaxConnectionIdleManager.2
            @Override // java.lang.Runnable
            public void run() {
                if (MaxConnectionIdleManager.this.shutdownDelayed) {
                    if (!MaxConnectionIdleManager.this.isActive) {
                        MaxConnectionIdleManager.this.shutdownFuture = scheduler.schedule(MaxConnectionIdleManager.this.shutdownTask, MaxConnectionIdleManager.this.nextIdleMonitorTime - MaxConnectionIdleManager.this.ticker.nanoTime(), TimeUnit.NANOSECONDS);
                        MaxConnectionIdleManager.this.shutdownDelayed = false;
                        return;
                    }
                    return;
                }
                closeJob.run();
                MaxConnectionIdleManager.this.shutdownFuture = null;
            }
        });
        this.shutdownFuture = scheduler.schedule(this.shutdownTask, this.maxConnectionIdleInNanos, TimeUnit.NANOSECONDS);
    }

    public void onTransportActive() {
        this.isActive = true;
        this.shutdownDelayed = true;
    }

    public void onTransportIdle() {
        this.isActive = false;
        if (this.shutdownFuture == null) {
            return;
        }
        if (this.shutdownFuture.isDone()) {
            this.shutdownDelayed = false;
            this.shutdownFuture = this.scheduler.schedule(this.shutdownTask, this.maxConnectionIdleInNanos, TimeUnit.NANOSECONDS);
        } else {
            this.nextIdleMonitorTime = this.ticker.nanoTime() + this.maxConnectionIdleInNanos;
        }
    }

    public void onTransportTermination() {
        if (this.shutdownFuture != null) {
            this.shutdownFuture.cancel(false);
            this.shutdownFuture = null;
        }
    }
}
