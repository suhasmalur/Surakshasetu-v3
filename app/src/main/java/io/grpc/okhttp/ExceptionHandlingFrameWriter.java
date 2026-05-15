package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.okhttp.OkHttpFrameLogger;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Header;
import io.grpc.okhttp.internal.framed.Settings;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.ByteString;

/* JADX INFO: loaded from: classes10.dex */
final class ExceptionHandlingFrameWriter implements FrameWriter {
    private static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());
    private final OkHttpFrameLogger frameLogger = new OkHttpFrameLogger(Level.FINE, (Class<?>) OkHttpClientTransport.class);
    private final FrameWriter frameWriter;
    private final TransportExceptionHandler transportExceptionHandler;

    interface TransportExceptionHandler {
        void onException(Throwable th);
    }

    ExceptionHandlingFrameWriter(TransportExceptionHandler transportExceptionHandler, FrameWriter frameWriter) {
        this.transportExceptionHandler = (TransportExceptionHandler) Preconditions.checkNotNull(transportExceptionHandler, "transportExceptionHandler");
        this.frameWriter = (FrameWriter) Preconditions.checkNotNull(frameWriter, "frameWriter");
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void connectionPreface() {
        try {
            this.frameWriter.connectionPreface();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void ackSettings(Settings peerSettings) {
        this.frameLogger.logSettingsAck(OkHttpFrameLogger.Direction.OUTBOUND);
        try {
            this.frameWriter.ackSettings(peerSettings);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void pushPromise(int streamId, int promisedStreamId, List<Header> requestHeaders) {
        this.frameLogger.logPushPromise(OkHttpFrameLogger.Direction.OUTBOUND, streamId, promisedStreamId, requestHeaders);
        try {
            this.frameWriter.pushPromise(streamId, promisedStreamId, requestHeaders);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void flush() {
        try {
            this.frameWriter.flush();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void synStream(boolean outFinished, boolean inFinished, int streamId, int associatedStreamId, List<Header> headerBlock) {
        try {
            this.frameWriter.synStream(outFinished, inFinished, streamId, associatedStreamId, headerBlock);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void synReply(boolean outFinished, int streamId, List<Header> headerBlock) {
        try {
            this.frameWriter.synReply(outFinished, streamId, headerBlock);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void headers(int streamId, List<Header> headerBlock) {
        this.frameLogger.logHeaders(OkHttpFrameLogger.Direction.OUTBOUND, streamId, headerBlock, false);
        try {
            this.frameWriter.headers(streamId, headerBlock);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void rstStream(int streamId, ErrorCode errorCode) {
        this.frameLogger.logRstStream(OkHttpFrameLogger.Direction.OUTBOUND, streamId, errorCode);
        try {
            this.frameWriter.rstStream(streamId, errorCode);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public int maxDataLength() {
        return this.frameWriter.maxDataLength();
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void data(boolean outFinished, int streamId, Buffer source, int byteCount) {
        this.frameLogger.logData(OkHttpFrameLogger.Direction.OUTBOUND, streamId, source.buffer(), byteCount, outFinished);
        try {
            this.frameWriter.data(outFinished, streamId, source, byteCount);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void settings(Settings okHttpSettings) {
        this.frameLogger.logSettings(OkHttpFrameLogger.Direction.OUTBOUND, okHttpSettings);
        try {
            this.frameWriter.settings(okHttpSettings);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void ping(boolean ack, int payload1, int payload2) {
        if (ack) {
            this.frameLogger.logPingAck(OkHttpFrameLogger.Direction.OUTBOUND, (4294967295L & ((long) payload2)) | (((long) payload1) << 32));
        } else {
            this.frameLogger.logPing(OkHttpFrameLogger.Direction.OUTBOUND, (4294967295L & ((long) payload2)) | (((long) payload1) << 32));
        }
        try {
            this.frameWriter.ping(ack, payload1, payload2);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void goAway(int lastGoodStreamId, ErrorCode errorCode, byte[] debugData) {
        this.frameLogger.logGoAway(OkHttpFrameLogger.Direction.OUTBOUND, lastGoodStreamId, errorCode, ByteString.of(debugData));
        try {
            this.frameWriter.goAway(lastGoodStreamId, errorCode, debugData);
            this.frameWriter.flush();
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void windowUpdate(int streamId, long windowSizeIncrement) {
        this.frameLogger.logWindowsUpdate(OkHttpFrameLogger.Direction.OUTBOUND, streamId, windowSizeIncrement);
        try {
            this.frameWriter.windowUpdate(streamId, windowSizeIncrement);
        } catch (IOException e) {
            this.transportExceptionHandler.onException(e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.frameWriter.close();
        } catch (IOException e) {
            log.log(getLogLevel(e), "Failed closing connection", (Throwable) e);
        }
    }

    static Level getLogLevel(Throwable t) {
        if (t.getClass().equals(IOException.class)) {
            return Level.FINE;
        }
        return Level.INFO;
    }
}
