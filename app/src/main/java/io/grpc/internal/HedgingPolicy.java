package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import io.grpc.Status;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: classes10.dex */
final class HedgingPolicy {
    final long hedgingDelayNanos;
    final int maxAttempts;
    final Set<Status.Code> nonFatalStatusCodes;

    HedgingPolicy(int maxAttempts, long hedgingDelayNanos, Set<Status.Code> nonFatalStatusCodes) {
        this.maxAttempts = maxAttempts;
        this.hedgingDelayNanos = hedgingDelayNanos;
        this.nonFatalStatusCodes = ImmutableSet.copyOf((Collection) nonFatalStatusCodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        HedgingPolicy that = (HedgingPolicy) other;
        if (this.maxAttempts == that.maxAttempts && this.hedgingDelayNanos == that.hedgingDelayNanos && Objects.equal(this.nonFatalStatusCodes, that.nonFatalStatusCodes)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.maxAttempts), Long.valueOf(this.hedgingDelayNanos), this.nonFatalStatusCodes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("maxAttempts", this.maxAttempts).add("hedgingDelayNanos", this.hedgingDelayNanos).add("nonFatalStatusCodes", this.nonFatalStatusCodes).toString();
    }
}
