package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import io.grpc.internal.SerializingExecutor;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Settings;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import java.io.IOException;
import java.net.Socket;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;

/* JADX INFO: loaded from: classes10.dex */
final class AsyncSink implements Sink {
    private boolean controlFramesExceeded;
    private int controlFramesInWrite;
    private final int maxQueuedControlFrames;
    private int queuedControlFrames;
    private final SerializingExecutor serializingExecutor;

    @Nullable
    private Sink sink;

    @Nullable
    private Socket socket;
    private final ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler;
    private final Object lock = new Object();
    private final Buffer buffer = new Buffer();
    private boolean writeEnqueued = false;
    private boolean flushEnqueued = false;
    private boolean closed = false;

    static /* synthetic */ int access$420(AsyncSink x0, int x1) {
        int i = x0.queuedControlFrames - x1;
        x0.queuedControlFrames = i;
        return i;
    }

    static /* synthetic */ int access$908(AsyncSink x0) {
        int i = x0.controlFramesInWrite;
        x0.controlFramesInWrite = i + 1;
        return i;
    }

    private AsyncSink(SerializingExecutor executor, ExceptionHandlingFrameWriter.TransportExceptionHandler exceptionHandler, int maxQueuedControlFrames) {
        this.serializingExecutor = (SerializingExecutor) Preconditions.checkNotNull(executor, "executor");
        this.transportExceptionHandler = (ExceptionHandlingFrameWriter.TransportExceptionHandler) Preconditions.checkNotNull(exceptionHandler, "exceptionHandler");
        this.maxQueuedControlFrames = maxQueuedControlFrames;
    }

    static AsyncSink sink(SerializingExecutor executor, ExceptionHandlingFrameWriter.TransportExceptionHandler exceptionHandler, int maxQueuedControlFrames) {
        return new AsyncSink(executor, exceptionHandler, maxQueuedControlFrames);
    }

    void becomeConnected(Sink sink, Socket socket) {
        Preconditions.checkState(this.sink == null, "AsyncSink's becomeConnected should only be called once.");
        this.sink = (Sink) Preconditions.checkNotNull(sink, "sink");
        this.socket = (Socket) Preconditions.checkNotNull(socket, "socket");
    }

    FrameWriter limitControlFramesWriter(FrameWriter delegate) {
        return new LimitControlFramesWriter(delegate);
    }

    @Override // okio.Sink
    public void write(Buffer source, long byteCount) throws IOException {
        Preconditions.checkNotNull(source, Constants.ScionAnalytics.PARAM_SOURCE);
        if (this.closed) {
            throw new IOException("closed");
        }
        PerfMark.startTask("AsyncSink.write");
        boolean closeSocket = false;
        try {
            synchronized (this.lock) {
                this.buffer.write(source, byteCount);
                this.queuedControlFrames += this.controlFramesInWrite;
                this.controlFramesInWrite = 0;
                if (this.controlFramesExceeded || this.queuedControlFrames <= this.maxQueuedControlFrames) {
                    if (!this.writeEnqueued && !this.flushEnqueued && this.buffer.completeSegmentByteCount() > 0) {
                        this.writeEnqueued = true;
                    }
                    return;
                }
                this.controlFramesExceeded = true;
                closeSocket = true;
                if (!closeSocket) {
                    this.serializingExecutor.execute(new WriteRunnable() { // from class: io.grpc.okhttp.AsyncSink.1
                        final Link link = PerfMark.linkOut();

                        @Override // io.grpc.okhttp.AsyncSink.WriteRunnable
                        public void doRun() throws IOException {
                            int writingControlFrames;
                            PerfMark.startTask("WriteRunnable.runWrite");
                            PerfMark.linkIn(this.link);
                            Buffer buf = new Buffer();
                            try {
                                synchronized (AsyncSink.this.lock) {
                                    buf.write(AsyncSink.this.buffer, AsyncSink.this.buffer.completeSegmentByteCount());
                                    AsyncSink.this.writeEnqueued = false;
                                    writingControlFrames = AsyncSink.this.queuedControlFrames;
                                }
                                AsyncSink.this.sink.write(buf, buf.size());
                                synchronized (AsyncSink.this.lock) {
                                    AsyncSink.access$420(AsyncSink.this, writingControlFrames);
                                }
                            } finally {
                                PerfMark.stopTask("WriteRunnable.runWrite");
                            }
                        }
                    });
                    return;
                }
                try {
                    this.socket.close();
                } catch (IOException e) {
                    this.transportExceptionHandler.onException(e);
                }
            }
        } finally {
            PerfMark.stopTask("AsyncSink.write");
        }
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        PerfMark.startTask("AsyncSink.flush");
        try {
            synchronized (this.lock) {
                if (this.flushEnqueued) {
                    return;
                }
                this.flushEnqueued = true;
                this.serializingExecutor.execute(new WriteRunnable() { // from class: io.grpc.okhttp.AsyncSink.2
                    final Link link = PerfMark.linkOut();

                    @Override // io.grpc.okhttp.AsyncSink.WriteRunnable
                    public void doRun() throws IOException {
                        PerfMark.startTask("WriteRunnable.runFlush");
                        PerfMark.linkIn(this.link);
                        Buffer buf = new Buffer();
                        try {
                            synchronized (AsyncSink.this.lock) {
                                buf.write(AsyncSink.this.buffer, AsyncSink.this.buffer.size());
                                AsyncSink.this.flushEnqueued = false;
                            }
                            AsyncSink.this.sink.write(buf, buf.size());
                            AsyncSink.this.sink.flush();
                        } finally {
                            PerfMark.stopTask("WriteRunnable.runFlush");
                        }
                    }
                });
            }
        } finally {
            PerfMark.stopTask("AsyncSink.flush");
        }
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.serializingExecutor.execute(new Runnable() { // from class: io.grpc.okhttp.AsyncSink.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (AsyncSink.this.sink != null && AsyncSink.this.buffer.size() > 0) {
                        AsyncSink.this.sink.write(AsyncSink.this.buffer, AsyncSink.this.buffer.size());
                    }
                } catch (IOException e) {
                    AsyncSink.this.transportExceptionHandler.onException(e);
                }
                AsyncSink.this.buffer.close();
                try {
                    if (AsyncSink.this.sink != null) {
                        AsyncSink.this.sink.close();
                    }
                } catch (IOException e2) {
                    AsyncSink.this.transportExceptionHandler.onException(e2);
                }
                try {
                    if (AsyncSink.this.socket != null) {
                        AsyncSink.this.socket.close();
                    }
                } catch (IOException e3) {
                    AsyncSink.this.transportExceptionHandler.onException(e3);
                }
            }
        });
    }

    private abstract class WriteRunnable implements Runnable {
        public abstract void doRun() throws IOException;

        private WriteRunnable() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (AsyncSink.this.sink == null) {
                    throw new IOException("Unable to perform write due to unavailable sink.");
                }
                doRun();
            } catch (Exception e) {
                AsyncSink.this.transportExceptionHandler.onException(e);
            }
        }
    }

    private class LimitControlFramesWriter extends ForwardingFrameWriter {
        public LimitControlFramesWriter(FrameWriter delegate) {
            super(delegate);
        }

        @Override // io.grpc.okhttp.ForwardingFrameWriter, io.grpc.okhttp.internal.framed.FrameWriter
        public void ackSettings(Settings peerSettings) throws IOException {
            AsyncSink.access$908(AsyncSink.this);
            super.ackSettings(peerSettings);
        }

        @Override // io.grpc.okhttp.ForwardingFrameWriter, io.grpc.okhttp.internal.framed.FrameWriter
        public void rstStream(int streamId, ErrorCode errorCode) throws IOException {
            AsyncSink.access$908(AsyncSink.this);
            super.rstStream(streamId, errorCode);
        }

        @Override // io.grpc.okhttp.ForwardingFrameWriter, io.grpc.okhttp.internal.framed.FrameWriter
        public void ping(boolean ack, int payload1, int payload2) throws IOException {
            if (ack) {
                AsyncSink.access$908(AsyncSink.this);
            }
            super.ping(ack, payload1, payload2);
        }
    }
}
