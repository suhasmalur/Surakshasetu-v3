package io.grpc;

import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: loaded from: classes10.dex */
public interface InternalInstrumented<T> extends InternalWithLogId {
    ListenableFuture<T> getStats();
}
