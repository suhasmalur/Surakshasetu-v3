package io.grpc;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes10.dex */
public final class InternalMethodDescriptor {
    private final InternalKnownTransport transport;

    public InternalMethodDescriptor(InternalKnownTransport transport) {
        this.transport = (InternalKnownTransport) Preconditions.checkNotNull(transport, NotificationCompat.CATEGORY_TRANSPORT);
    }

    public Object geRawMethodName(MethodDescriptor<?, ?> descriptor) {
        return descriptor.getRawMethodName(this.transport.ordinal());
    }

    public void setRawMethodName(MethodDescriptor<?, ?> descriptor, Object o) {
        descriptor.setRawMethodName(this.transport.ordinal(), o);
    }
}
