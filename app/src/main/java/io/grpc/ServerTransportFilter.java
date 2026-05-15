package io.grpc;

/* JADX INFO: loaded from: classes10.dex */
public abstract class ServerTransportFilter {
    public Attributes transportReady(Attributes transportAttrs) {
        return transportAttrs;
    }

    public void transportTerminated(Attributes transportAttrs) {
    }
}
