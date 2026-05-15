package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import io.grpc.Status;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
final class RetryPolicy {
    final double backoffMultiplier;
    final long initialBackoffNanos;
    final int maxAttempts;
    final long maxBackoffNanos;

    @Nullable
    final Long perAttemptRecvTimeoutNanos;
    final Set<Status.Code> retryableStatusCodes;

    RetryPolicy(int maxAttempts, long initialBackoffNanos, long maxBackoffNanos, double backoffMultiplier, @Nullable Long perAttemptRecvTimeoutNanos, @Nonnull Set<Status.Code> retryableStatusCodes) {
        this.maxAttempts = maxAttempts;
        this.initialBackoffNanos = initialBackoffNanos;
        this.maxBackoffNanos = maxBackoffNanos;
        this.backoffMultiplier = backoffMultiplier;
        this.perAttemptRecvTimeoutNanos = perAttemptRecvTimeoutNanos;
        this.retryableStatusCodes = ImmutableSet.copyOf((Collection) retryableStatusCodes);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxAttempts), Long.valueOf(this.initialBackoffNanos), Long.valueOf(this.maxBackoffNanos), Double.valueOf(this.backoffMultiplier), this.perAttemptRecvTimeoutNanos, this.retryableStatusCodes);
    }

    public boolean equals(Object other) {
        if (!(other instanceof RetryPolicy)) {
            return false;
        }
        RetryPolicy that = (RetryPolicy) other;
        return this.maxAttempts == that.maxAttempts && this.initialBackoffNanos == that.initialBackoffNanos && this.maxBackoffNanos == that.maxBackoffNanos && Double.compare(this.backoffMultiplier, that.backoffMultiplier) == 0 && Objects.equal(this.perAttemptRecvTimeoutNanos, that.perAttemptRecvTimeoutNanos) && Objects.equal(this.retryableStatusCodes, that.retryableStatusCodes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("maxAttempts", this.maxAttempts).add("initialBackoffNanos", this.initialBackoffNanos).add("maxBackoffNanos", this.maxBackoffNanos).add("backoffMultiplier", this.backoffMultiplier).add("perAttemptRecvTimeoutNanos", this.perAttemptRecvTimeoutNanos).add("retryableStatusCodes", this.retryableStatusCodes).toString();
    }
}
