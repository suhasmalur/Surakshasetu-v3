package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
final class MetadataApplierImpl extends CallCredentials.MetadataApplier {
    private final CallOptions callOptions;
    DelayedStream delayedStream;
    boolean finalized;
    private final MetadataApplierListener listener;
    private final MethodDescriptor<?, ?> method;
    private final Metadata origHeaders;

    @Nullable
    private ClientStream returnedStream;
    private final ClientStreamTracer[] tracers;
    private final ClientTransport transport;
    private final Object lock = new Object();
    private final Context ctx = Context.current();

    public interface MetadataApplierListener {
        void onComplete();
    }

    MetadataApplierImpl(ClientTransport transport, MethodDescriptor<?, ?> method, Metadata origHeaders, CallOptions callOptions, MetadataApplierListener listener, ClientStreamTracer[] tracers) {
        this.transport = transport;
        this.method = method;
        this.origHeaders = origHeaders;
        this.callOptions = callOptions;
        this.listener = listener;
        this.tracers = tracers;
    }

    @Override // io.grpc.CallCredentials.MetadataApplier
    public void apply(Metadata headers) {
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        Preconditions.checkNotNull(headers, "headers");
        this.origHeaders.merge(headers);
        Context origCtx = this.ctx.attach();
        try {
            ClientStream realStream = this.transport.newStream(this.method, this.origHeaders, this.callOptions, this.tracers);
            this.ctx.detach(origCtx);
            finalizeWith(realStream);
        } catch (Throwable th) {
            this.ctx.detach(origCtx);
            throw th;
        }
    }

    @Override // io.grpc.CallCredentials.MetadataApplier
    public void fail(Status status) {
        Preconditions.checkArgument(!status.isOk(), "Cannot fail with OK status");
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        finalizeWith(new FailingClientStream(GrpcUtil.replaceInappropriateControlPlaneStatus(status), this.tracers));
    }

    private void finalizeWith(ClientStream stream) {
        Preconditions.checkState(!this.finalized, "already finalized");
        this.finalized = true;
        boolean directStream = false;
        synchronized (this.lock) {
            if (this.returnedStream == null) {
                this.returnedStream = stream;
                directStream = true;
            }
        }
        if (directStream) {
            this.listener.onComplete();
            return;
        }
        Preconditions.checkState(this.delayedStream != null, "delayedStream is null");
        Runnable slow = this.delayedStream.setStream(stream);
        if (slow != null) {
            slow.run();
        }
        this.listener.onComplete();
    }

    ClientStream returnStream() {
        synchronized (this.lock) {
            if (this.returnedStream == null) {
                this.delayedStream = new DelayedStream();
                DelayedStream delayedStream = this.delayedStream;
                this.returnedStream = delayedStream;
                return delayedStream;
            }
            return this.returnedStream;
        }
    }
}
