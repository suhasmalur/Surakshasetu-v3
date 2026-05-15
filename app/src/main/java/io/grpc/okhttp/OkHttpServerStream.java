package io.grpc.okhttp;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.AbstractServerStream;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WritableBuffer;
import io.grpc.okhttp.OkHttpServerTransport;
import io.grpc.okhttp.OutboundFlowController;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Header;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.util.List;
import okio.Buffer;

/* JADX INFO: loaded from: classes10.dex */
class OkHttpServerStream extends AbstractServerStream {
    private final Attributes attributes;
    private final String authority;
    private final Sink sink;
    private final TransportState state;
    private final TransportTracer transportTracer;

    public OkHttpServerStream(TransportState state, Attributes transportAttrs, String authority, StatsTraceContext statsTraceCtx, TransportTracer transportTracer) {
        super(new OkHttpWritableBufferAllocator(), statsTraceCtx);
        this.sink = new Sink();
        this.state = (TransportState) Preconditions.checkNotNull(state, "state");
        this.attributes = (Attributes) Preconditions.checkNotNull(transportAttrs, "transportAttrs");
        this.authority = authority;
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractServerStream, io.grpc.internal.AbstractStream
    public TransportState transportState() {
        return this.state;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractServerStream
    public Sink abstractServerStreamSink() {
        return this.sink;
    }

    @Override // io.grpc.internal.ServerStream
    public int streamId() {
        return this.state.streamId;
    }

    @Override // io.grpc.internal.AbstractServerStream, io.grpc.internal.ServerStream
    public String getAuthority() {
        return this.authority;
    }

    @Override // io.grpc.internal.AbstractServerStream, io.grpc.internal.ServerStream
    public Attributes getAttributes() {
        return this.attributes;
    }

    class Sink implements AbstractServerStream.Sink {
        Sink() {
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void writeHeaders(Metadata metadata) {
            PerfMark.startTask("OkHttpServerStream$Sink.writeHeaders");
            try {
                List<Header> responseHeaders = Headers.createResponseHeaders(metadata);
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.sendHeaders(responseHeaders);
                }
            } finally {
                PerfMark.stopTask("OkHttpServerStream$Sink.writeHeaders");
            }
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void writeFrame(WritableBuffer frame, boolean flush, int numMessages) {
            PerfMark.startTask("OkHttpServerStream$Sink.writeFrame");
            Buffer buffer = ((OkHttpWritableBuffer) frame).buffer();
            int size = (int) buffer.size();
            if (size > 0) {
                OkHttpServerStream.this.onSendingBytes(size);
            }
            try {
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.sendBuffer(buffer, flush);
                    OkHttpServerStream.this.transportTracer.reportMessageSent(numMessages);
                }
            } finally {
                PerfMark.stopTask("OkHttpServerStream$Sink.writeFrame");
            }
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void writeTrailers(Metadata trailers, boolean headersSent, Status status) {
            PerfMark.startTask("OkHttpServerStream$Sink.writeTrailers");
            try {
                List<Header> responseTrailers = Headers.createResponseTrailers(trailers, headersSent);
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.sendTrailers(responseTrailers);
                }
            } finally {
                PerfMark.stopTask("OkHttpServerStream$Sink.writeTrailers");
            }
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void cancel(Status reason) {
            PerfMark.startTask("OkHttpServerStream$Sink.cancel");
            try {
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.cancel(ErrorCode.CANCEL, reason);
                }
            } finally {
                PerfMark.stopTask("OkHttpServerStream$Sink.cancel");
            }
        }
    }

    static class TransportState extends AbstractServerStream.TransportState implements OutboundFlowController.Stream, OkHttpServerTransport.StreamState {
        private boolean cancelSent;
        private final ExceptionHandlingFrameWriter frameWriter;
        private final int initialWindowSize;
        private final Object lock;
        private final OutboundFlowController outboundFlow;
        private final OutboundFlowController.StreamState outboundFlowState;
        private int processedWindow;
        private boolean receivedEndOfStream;
        private final int streamId;
        private final Tag tag;
        private final OkHttpServerTransport transport;
        private int window;

        public TransportState(OkHttpServerTransport transport, int streamId, int maxMessageSize, StatsTraceContext statsTraceCtx, Object lock, ExceptionHandlingFrameWriter frameWriter, OutboundFlowController outboundFlow, int initialWindowSize, TransportTracer transportTracer, String methodName) {
            super(maxMessageSize, statsTraceCtx, transportTracer);
            this.cancelSent = false;
            this.transport = (OkHttpServerTransport) Preconditions.checkNotNull(transport, NotificationCompat.CATEGORY_TRANSPORT);
            this.streamId = streamId;
            this.lock = Preconditions.checkNotNull(lock, "lock");
            this.frameWriter = frameWriter;
            this.outboundFlow = outboundFlow;
            this.window = initialWindowSize;
            this.processedWindow = initialWindowSize;
            this.initialWindowSize = initialWindowSize;
            this.tag = PerfMark.createTag(methodName);
            this.outboundFlowState = outboundFlow.createState(this, streamId);
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void deframeFailed(Throwable cause) {
            cancel(ErrorCode.INTERNAL_ERROR, Status.fromThrowable(cause));
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void bytesRead(int processedBytes) {
            this.processedWindow -= processedBytes;
            if (this.processedWindow <= this.initialWindowSize * 0.5f) {
                int delta = this.initialWindowSize - this.processedWindow;
                this.window += delta;
                this.processedWindow += delta;
                this.frameWriter.windowUpdate(this.streamId, delta);
                this.frameWriter.flush();
            }
        }

        @Override // io.grpc.internal.ApplicationThreadDeframerListener.TransportExecutor
        public void runOnTransportThread(Runnable r) {
            synchronized (this.lock) {
                r.run();
            }
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public void inboundDataReceived(Buffer frame, int windowConsumed, boolean endOfStream) {
            synchronized (this.lock) {
                PerfMark.event("OkHttpServerTransport$FrameHandler.data", this.tag);
                if (endOfStream) {
                    this.receivedEndOfStream = true;
                }
                this.window -= windowConsumed;
                super.inboundDataReceived(new OkHttpReadableBuffer(frame), endOfStream);
            }
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public void inboundRstReceived(Status status) {
            PerfMark.event("OkHttpServerTransport$FrameHandler.rstStream", this.tag);
            transportReportStatus(status);
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public boolean hasReceivedEndOfStream() {
            boolean z;
            synchronized (this.lock) {
                z = this.receivedEndOfStream;
            }
            return z;
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public int inboundWindowAvailable() {
            int i;
            synchronized (this.lock) {
                i = this.window;
            }
            return i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendBuffer(Buffer buffer, boolean flush) {
            if (this.cancelSent) {
                return;
            }
            this.outboundFlow.data(false, this.outboundFlowState, buffer, flush);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendHeaders(List<Header> responseHeaders) {
            this.frameWriter.synReply(false, this.streamId, responseHeaders);
            this.frameWriter.flush();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendTrailers(final List<Header> responseTrailers) {
            this.outboundFlow.notifyWhenNoPendingData(this.outboundFlowState, new Runnable() { // from class: io.grpc.okhttp.OkHttpServerStream$TransportState$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m455x223b3517(responseTrailers);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: sendTrailersAfterFlowControlled, reason: merged with bridge method [inline-methods] */
        public void m455x223b3517(List<Header> responseTrailers) {
            synchronized (this.lock) {
                this.frameWriter.synReply(true, this.streamId, responseTrailers);
                if (!this.receivedEndOfStream) {
                    this.frameWriter.rstStream(this.streamId, ErrorCode.NO_ERROR);
                }
                this.transport.streamClosed(this.streamId, true);
                complete();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancel(ErrorCode http2Error, Status reason) {
            if (this.cancelSent) {
                return;
            }
            this.cancelSent = true;
            this.frameWriter.rstStream(this.streamId, http2Error);
            transportReportStatus(reason);
            this.transport.streamClosed(this.streamId, true);
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public OutboundFlowController.StreamState getOutboundFlowState() {
            return this.outboundFlowState;
        }
    }
}
