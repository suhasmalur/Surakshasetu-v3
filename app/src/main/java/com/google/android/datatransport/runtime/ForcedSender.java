package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.Transport;

/* JADX INFO: loaded from: classes.dex */
public final class ForcedSender {
    public static void sendBlocking(Transport<?> transport, Priority priority) {
        TransportContext context = getTransportContextOrThrow(transport).withPriority(priority);
        TransportRuntime.getInstance().getUploader().logAndUpdateState(context, 1);
    }

    private static TransportContext getTransportContextOrThrow(Transport<?> transport) {
        if (transport instanceof TransportImpl) {
            return ((TransportImpl) transport).getTransportContext();
        }
        throw new IllegalArgumentException("Expected instance of TransportImpl.");
    }

    private ForcedSender() {
    }
}
