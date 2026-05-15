package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Header;
import io.grpc.okhttp.internal.framed.Settings;
import java.io.IOException;
import java.util.List;
import okio.Buffer;

/* JADX INFO: loaded from: classes10.dex */
abstract class ForwardingFrameWriter implements FrameWriter {
    private final FrameWriter delegate;

    public ForwardingFrameWriter(FrameWriter delegate) {
        this.delegate = (FrameWriter) Preconditions.checkNotNull(delegate, "delegate");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void connectionPreface() throws IOException {
        this.delegate.connectionPreface();
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void ackSettings(Settings peerSettings) throws IOException {
        this.delegate.ackSettings(peerSettings);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void pushPromise(int streamId, int promisedStreamId, List<Header> requestHeaders) throws IOException {
        this.delegate.pushPromise(streamId, promisedStreamId, requestHeaders);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void synStream(boolean outFinished, boolean inFinished, int streamId, int associatedStreamId, List<Header> headerBlock) throws IOException {
        this.delegate.synStream(outFinished, inFinished, streamId, associatedStreamId, headerBlock);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void synReply(boolean outFinished, int streamId, List<Header> headerBlock) throws IOException {
        this.delegate.synReply(outFinished, streamId, headerBlock);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void headers(int streamId, List<Header> headerBlock) throws IOException {
        this.delegate.headers(streamId, headerBlock);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void rstStream(int streamId, ErrorCode errorCode) throws IOException {
        this.delegate.rstStream(streamId, errorCode);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public int maxDataLength() {
        return this.delegate.maxDataLength();
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void data(boolean outFinished, int streamId, Buffer source, int byteCount) throws IOException {
        this.delegate.data(outFinished, streamId, source, byteCount);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void settings(Settings okHttpSettings) throws IOException {
        this.delegate.settings(okHttpSettings);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void ping(boolean ack, int payload1, int payload2) throws IOException {
        this.delegate.ping(ack, payload1, payload2);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void goAway(int lastGoodStreamId, ErrorCode errorCode, byte[] debugData) throws IOException {
        this.delegate.goAway(lastGoodStreamId, errorCode, debugData);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void windowUpdate(int streamId, long windowSizeIncrement) throws IOException {
        this.delegate.windowUpdate(streamId, windowSizeIncrement);
    }
}
