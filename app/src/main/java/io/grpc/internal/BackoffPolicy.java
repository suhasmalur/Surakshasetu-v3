package io.grpc.internal;

/* JADX INFO: loaded from: classes10.dex */
public interface BackoffPolicy {

    public interface Provider {
        BackoffPolicy get();
    }

    long nextBackoffNanos();
}
