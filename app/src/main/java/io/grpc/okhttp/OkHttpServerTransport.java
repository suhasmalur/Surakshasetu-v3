package io.grpc.okhttp;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.logging.type.LogSeverity;
import io.grpc.Attributes;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.KeepAliveEnforcer;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.MaxConnectionIdleManager;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.SerializingExecutor;
import io.grpc.internal.ServerTransport;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.grpc.okhttp.OkHttpFrameLogger;
import io.grpc.okhttp.OkHttpServerStream;
import io.grpc.okhttp.OutboundFlowController;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameReader;
import io.grpc.okhttp.internal.framed.Header;
import io.grpc.okhttp.internal.framed.HeadersMode;
import io.grpc.okhttp.internal.framed.Http2;
import io.grpc.okhttp.internal.framed.Settings;
import io.grpc.okhttp.internal.framed.Variant;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: loaded from: classes10.dex */
final class OkHttpServerTransport implements ServerTransport, ExceptionHandlingFrameWriter.TransportExceptionHandler, OutboundFlowController.Transport {
    private static final int GRACEFUL_SHUTDOWN_PING = 4369;
    private static final int KEEPALIVE_PING = 57005;
    private boolean abruptShutdown;
    private Attributes attributes;
    private final Socket bareSocket;
    private final Config config;
    private ScheduledFuture<?> forcefulCloseTimer;
    private ExceptionHandlingFrameWriter frameWriter;
    private Status goAwayStatus;
    private boolean gracefulShutdown;
    private boolean handshakeShutdown;
    private final KeepAliveEnforcer keepAliveEnforcer;
    private KeepAliveManager keepAliveManager;
    private int lastStreamId;
    private ServerTransportListener listener;
    private final InternalLogId logId;
    private ScheduledFuture<?> maxConnectionAgeMonitor;
    private MaxConnectionIdleManager maxConnectionIdleManager;
    private OutboundFlowController outboundFlow;
    private ScheduledExecutorService scheduledExecutorService;
    private ScheduledFuture<?> secondGoawayTimer;
    private InternalChannelz.Security securityInfo;
    private final TransportTracer tracer;
    private Executor transportExecutor;
    private static final Logger log = Logger.getLogger(OkHttpServerTransport.class.getName());
    private static final ByteString HTTP_METHOD = ByteString.encodeUtf8(":method");
    private static final ByteString CONNECT_METHOD = ByteString.encodeUtf8("CONNECT");
    private static final ByteString POST_METHOD = ByteString.encodeUtf8("POST");
    private static final ByteString SCHEME = ByteString.encodeUtf8(":scheme");
    private static final ByteString PATH = ByteString.encodeUtf8(":path");
    private static final ByteString AUTHORITY = ByteString.encodeUtf8(":authority");
    private static final ByteString CONNECTION = ByteString.encodeUtf8("connection");
    private static final ByteString HOST = ByteString.encodeUtf8("host");
    private static final ByteString TE = ByteString.encodeUtf8("te");
    private static final ByteString TE_TRAILERS = ByteString.encodeUtf8(GrpcUtil.TE_TRAILERS);
    private static final ByteString CONTENT_TYPE = ByteString.encodeUtf8("content-type");
    private static final ByteString CONTENT_LENGTH = ByteString.encodeUtf8("content-length");
    private final Variant variant = new Http2();
    private final Object lock = new Object();
    private final Map<Integer, StreamState> streams = new TreeMap();
    private int goAwayStreamId = Integer.MAX_VALUE;

    interface StreamState {
        OutboundFlowController.StreamState getOutboundFlowState();

        boolean hasReceivedEndOfStream();

        void inboundDataReceived(Buffer buffer, int i, boolean z);

        void inboundRstReceived(Status status);

        int inboundWindowAvailable();

        void transportReportStatus(Status status);
    }

    public OkHttpServerTransport(Config config, Socket bareSocket) {
        this.config = (Config) Preconditions.checkNotNull(config, "config");
        this.bareSocket = (Socket) Preconditions.checkNotNull(bareSocket, "bareSocket");
        this.tracer = config.transportTracerFactory.create();
        this.tracer.setFlowControlWindowReader(new TransportTracer.FlowControlReader() { // from class: io.grpc.okhttp.OkHttpServerTransport$$ExternalSyntheticLambda4
            @Override // io.grpc.internal.TransportTracer.FlowControlReader
            public final TransportTracer.FlowControlWindows read() {
                return this.f$0.readFlowControlWindow();
            }
        });
        this.logId = InternalLogId.allocate(getClass(), bareSocket.getRemoteSocketAddress().toString());
        this.transportExecutor = config.transportExecutorPool.getObject();
        this.scheduledExecutorService = config.scheduledExecutorServicePool.getObject();
        this.keepAliveEnforcer = new KeepAliveEnforcer(config.permitKeepAliveWithoutCalls, config.permitKeepAliveTimeInNanos, TimeUnit.NANOSECONDS);
    }

    public void start(ServerTransportListener listener) {
        this.listener = (ServerTransportListener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        final SerializingExecutor serializingExecutor = new SerializingExecutor(this.transportExecutor);
        serializingExecutor.execute(new Runnable() { // from class: io.grpc.okhttp.OkHttpServerTransport$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f$0.m457lambda$start$0$iogrpcokhttpOkHttpServerTransport(serializingExecutor);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:57:0x015e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: startIo, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m457lambda$start$0$iogrpcokhttpOkHttpServerTransport(io.grpc.internal.SerializingExecutor r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpServerTransport.m457lambda$start$0$iogrpcokhttpOkHttpServerTransport(io.grpc.internal.SerializingExecutor):void");
    }

    /* JADX INFO: renamed from: lambda$startIo$1$io-grpc-okhttp-OkHttpServerTransport, reason: not valid java name */
    /* synthetic */ void m458lambda$startIo$1$iogrpcokhttpOkHttpServerTransport() {
        shutdown(Long.valueOf(this.config.maxConnectionAgeGraceInNanos));
    }

    @Override // io.grpc.internal.ServerTransport
    public void shutdown() {
        shutdown(Long.valueOf(TimeUnit.SECONDS.toNanos(1L)));
    }

    private void shutdown(Long graceTimeInNanos) {
        synchronized (this.lock) {
            if (!this.gracefulShutdown && !this.abruptShutdown) {
                this.gracefulShutdown = true;
                if (this.frameWriter == null) {
                    this.handshakeShutdown = true;
                    GrpcUtil.closeQuietly(this.bareSocket);
                } else {
                    this.secondGoawayTimer = this.scheduledExecutorService.schedule(new Runnable() { // from class: io.grpc.okhttp.OkHttpServerTransport$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.triggerGracefulSecondGoaway();
                        }
                    }, graceTimeInNanos.longValue(), TimeUnit.NANOSECONDS);
                    this.frameWriter.goAway(Integer.MAX_VALUE, ErrorCode.NO_ERROR, new byte[0]);
                    this.frameWriter.ping(false, 0, GRACEFUL_SHUTDOWN_PING);
                    this.frameWriter.flush();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerGracefulSecondGoaway() {
        synchronized (this.lock) {
            if (this.secondGoawayTimer == null) {
                return;
            }
            this.secondGoawayTimer.cancel(false);
            this.secondGoawayTimer = null;
            this.frameWriter.goAway(this.lastStreamId, ErrorCode.NO_ERROR, new byte[0]);
            this.goAwayStreamId = this.lastStreamId;
            if (this.streams.isEmpty()) {
                this.frameWriter.close();
            } else {
                this.frameWriter.flush();
            }
        }
    }

    @Override // io.grpc.internal.ServerTransport, io.grpc.internal.ManagedClientTransport
    public void shutdownNow(Status reason) {
        synchronized (this.lock) {
            if (this.frameWriter == null) {
                this.handshakeShutdown = true;
                GrpcUtil.closeQuietly(this.bareSocket);
            } else {
                abruptShutdown(ErrorCode.NO_ERROR, "", reason, true);
            }
        }
    }

    @Override // io.grpc.okhttp.ExceptionHandlingFrameWriter.TransportExceptionHandler
    public void onException(Throwable failureCause) {
        Preconditions.checkNotNull(failureCause, "failureCause");
        Status status = Status.UNAVAILABLE.withCause(failureCause);
        abruptShutdown(ErrorCode.INTERNAL_ERROR, "I/O failure", status, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void abruptShutdown(ErrorCode errorCode, String moreDetail, Status reason, boolean rstStreams) {
        synchronized (this.lock) {
            if (this.abruptShutdown) {
                return;
            }
            this.abruptShutdown = true;
            this.goAwayStatus = reason;
            if (this.secondGoawayTimer != null) {
                this.secondGoawayTimer.cancel(false);
                this.secondGoawayTimer = null;
            }
            for (Map.Entry<Integer, StreamState> entry : this.streams.entrySet()) {
                if (rstStreams) {
                    this.frameWriter.rstStream(entry.getKey().intValue(), ErrorCode.CANCEL);
                }
                entry.getValue().transportReportStatus(reason);
            }
            this.streams.clear();
            this.frameWriter.goAway(this.lastStreamId, errorCode, moreDetail.getBytes(GrpcUtil.US_ASCII));
            this.goAwayStreamId = this.lastStreamId;
            this.frameWriter.close();
            this.forcefulCloseTimer = this.scheduledExecutorService.schedule(new Runnable() { // from class: io.grpc.okhttp.OkHttpServerTransport$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.triggerForcefulClose();
                }
            }, 1L, TimeUnit.SECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerForcefulClose() {
        GrpcUtil.closeQuietly(this.bareSocket);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void terminated() {
        synchronized (this.lock) {
            if (this.forcefulCloseTimer != null) {
                this.forcefulCloseTimer.cancel(false);
                this.forcefulCloseTimer = null;
            }
        }
        if (this.keepAliveManager != null) {
            this.keepAliveManager.onTransportTermination();
        }
        if (this.maxConnectionIdleManager != null) {
            this.maxConnectionIdleManager.onTransportTermination();
        }
        if (this.maxConnectionAgeMonitor != null) {
            this.maxConnectionAgeMonitor.cancel(false);
        }
        this.transportExecutor = this.config.transportExecutorPool.returnObject(this.transportExecutor);
        this.scheduledExecutorService = this.config.scheduledExecutorServicePool.returnObject(this.scheduledExecutorService);
        this.listener.transportTerminated();
    }

    @Override // io.grpc.internal.ServerTransport
    public ScheduledExecutorService getScheduledExecutorService() {
        return this.scheduledExecutorService;
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        ListenableFuture<InternalChannelz.SocketStats> listenableFutureImmediateFuture;
        synchronized (this.lock) {
            listenableFutureImmediateFuture = Futures.immediateFuture(new InternalChannelz.SocketStats(this.tracer.getStats(), this.bareSocket.getLocalSocketAddress(), this.bareSocket.getRemoteSocketAddress(), Utils.getSocketOptions(this.bareSocket), this.securityInfo));
        }
        return listenableFutureImmediateFuture;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TransportTracer.FlowControlWindows readFlowControlWindow() {
        TransportTracer.FlowControlWindows flowControlWindows;
        synchronized (this.lock) {
            long local = this.outboundFlow == null ? -1L : this.outboundFlow.windowUpdate(null, 0);
            long remote = (long) (this.config.flowControlWindow * 0.5f);
            flowControlWindows = new TransportTracer.FlowControlWindows(local, remote);
        }
        return flowControlWindows;
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    @Override // io.grpc.okhttp.OutboundFlowController.Transport
    public OutboundFlowController.StreamState[] getActiveStreams() {
        OutboundFlowController.StreamState[] flowStreams;
        synchronized (this.lock) {
            flowStreams = new OutboundFlowController.StreamState[this.streams.size()];
            int i = 0;
            for (StreamState stream : this.streams.values()) {
                flowStreams[i] = stream.getOutboundFlowState();
                i++;
            }
        }
        return flowStreams;
    }

    void streamClosed(int streamId, boolean flush) {
        synchronized (this.lock) {
            this.streams.remove(Integer.valueOf(streamId));
            if (this.streams.isEmpty()) {
                this.keepAliveEnforcer.onTransportIdle();
                if (this.maxConnectionIdleManager != null) {
                    this.maxConnectionIdleManager.onTransportIdle();
                }
            }
            if (this.gracefulShutdown && this.streams.isEmpty()) {
                this.frameWriter.close();
            } else if (flush) {
                this.frameWriter.flush();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String asciiString(ByteString value) {
        for (int i = 0; i < value.size(); i++) {
            if (value.getByte(i) >= 128) {
                return value.string(GrpcUtil.US_ASCII);
            }
        }
        return value.utf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int headerFind(List<Header> header, ByteString key, int startIndex) {
        for (int i = startIndex; i < header.size(); i++) {
            if (header.get(i).name.equals(key)) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean headerContains(List<Header> header, ByteString key) {
        return headerFind(header, key, 0) != -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void headerRemove(List<Header> header, ByteString key) {
        int i = 0;
        while (true) {
            int iHeaderFind = headerFind(header, key, i);
            i = iHeaderFind;
            if (iHeaderFind != -1) {
                header.remove(i);
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteString headerGetRequiredSingle(List<Header> header, ByteString key) {
        int i = headerFind(header, key, 0);
        if (i == -1 || headerFind(header, key, i + 1) != -1) {
            return null;
        }
        return header.get(i).value;
    }

    static final class Config {
        final int flowControlWindow;
        final HandshakerSocketFactory handshakerSocketFactory;
        final long keepAliveTimeNanos;
        final long keepAliveTimeoutNanos;
        final long maxConnectionAgeGraceInNanos;
        final long maxConnectionAgeInNanos;
        final long maxConnectionIdleNanos;
        final int maxInboundMessageSize;
        final int maxInboundMetadataSize;
        final long permitKeepAliveTimeInNanos;
        final boolean permitKeepAliveWithoutCalls;
        final ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool;
        final List<? extends ServerStreamTracer.Factory> streamTracerFactories;
        final ObjectPool<Executor> transportExecutorPool;
        final TransportTracer.Factory transportTracerFactory;

        public Config(OkHttpServerBuilder builder, List<? extends ServerStreamTracer.Factory> streamTracerFactories) {
            this.streamTracerFactories = (List) Preconditions.checkNotNull(streamTracerFactories, "streamTracerFactories");
            this.transportExecutorPool = (ObjectPool) Preconditions.checkNotNull(builder.transportExecutorPool, "transportExecutorPool");
            this.scheduledExecutorServicePool = (ObjectPool) Preconditions.checkNotNull(builder.scheduledExecutorServicePool, "scheduledExecutorServicePool");
            this.transportTracerFactory = (TransportTracer.Factory) Preconditions.checkNotNull(builder.transportTracerFactory, "transportTracerFactory");
            this.handshakerSocketFactory = (HandshakerSocketFactory) Preconditions.checkNotNull(builder.handshakerSocketFactory, "handshakerSocketFactory");
            this.keepAliveTimeNanos = builder.keepAliveTimeNanos;
            this.keepAliveTimeoutNanos = builder.keepAliveTimeoutNanos;
            this.flowControlWindow = builder.flowControlWindow;
            this.maxInboundMessageSize = builder.maxInboundMessageSize;
            this.maxInboundMetadataSize = builder.maxInboundMetadataSize;
            this.maxConnectionIdleNanos = builder.maxConnectionIdleInNanos;
            this.permitKeepAliveWithoutCalls = builder.permitKeepAliveWithoutCalls;
            this.permitKeepAliveTimeInNanos = builder.permitKeepAliveTimeInNanos;
            this.maxConnectionAgeInNanos = builder.maxConnectionAgeInNanos;
            this.maxConnectionAgeGraceInNanos = builder.maxConnectionAgeGraceInNanos;
        }
    }

    class FrameHandler implements FrameReader.Handler, Runnable {
        private int connectionUnacknowledgedBytesRead;
        private final OkHttpFrameLogger frameLogger = new OkHttpFrameLogger(Level.FINE, (Class<?>) OkHttpServerTransport.class);
        private final FrameReader frameReader;
        private boolean receivedSettings;

        public FrameHandler(FrameReader frameReader) {
            this.frameReader = frameReader;
        }

        @Override // java.lang.Runnable
        public void run() {
            Status status;
            String threadName = Thread.currentThread().getName();
            Thread.currentThread().setName("OkHttpServerTransport");
            try {
                this.frameReader.readConnectionPreface();
                if (!this.frameReader.nextFrame(this)) {
                    connectionError(ErrorCode.INTERNAL_ERROR, "Failed to read initial SETTINGS");
                    return;
                }
                if (!this.receivedSettings) {
                    connectionError(ErrorCode.PROTOCOL_ERROR, "First HTTP/2 frame must be SETTINGS. RFC7540 section 3.5");
                    try {
                        GrpcUtil.exhaust(OkHttpServerTransport.this.bareSocket.getInputStream());
                    } catch (IOException e) {
                    }
                    GrpcUtil.closeQuietly(OkHttpServerTransport.this.bareSocket);
                    OkHttpServerTransport.this.terminated();
                    Thread.currentThread().setName(threadName);
                    return;
                }
                while (this.frameReader.nextFrame(this)) {
                    if (OkHttpServerTransport.this.keepAliveManager != null) {
                        OkHttpServerTransport.this.keepAliveManager.onDataReceived();
                    }
                }
                synchronized (OkHttpServerTransport.this.lock) {
                    status = OkHttpServerTransport.this.goAwayStatus;
                }
                if (status == null) {
                    status = Status.UNAVAILABLE.withDescription("TCP connection closed or IOException");
                }
                OkHttpServerTransport.this.abruptShutdown(ErrorCode.INTERNAL_ERROR, "I/O failure", status, false);
                try {
                    GrpcUtil.exhaust(OkHttpServerTransport.this.bareSocket.getInputStream());
                } catch (IOException e2) {
                }
            } catch (Throwable t) {
                try {
                    OkHttpServerTransport.log.log(Level.WARNING, "Error decoding HTTP/2 frames", t);
                    OkHttpServerTransport.this.abruptShutdown(ErrorCode.INTERNAL_ERROR, "Error in frame decoder", Status.INTERNAL.withDescription("Error decoding HTTP/2 frames").withCause(t), false);
                    try {
                        GrpcUtil.exhaust(OkHttpServerTransport.this.bareSocket.getInputStream());
                    } catch (IOException e3) {
                    }
                } finally {
                    try {
                        GrpcUtil.exhaust(OkHttpServerTransport.this.bareSocket.getInputStream());
                    } catch (IOException e4) {
                    }
                    GrpcUtil.closeQuietly(OkHttpServerTransport.this.bareSocket);
                    OkHttpServerTransport.this.terminated();
                    Thread.currentThread().setName(threadName);
                }
            }
            GrpcUtil.closeQuietly(OkHttpServerTransport.this.bareSocket);
            OkHttpServerTransport.this.terminated();
            Thread.currentThread().setName(threadName);
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void headers(boolean outFinished, boolean inFinished, int streamId, int associatedStreamId, List<Header> headerBlock, HeadersMode headersMode) throws Throwable {
            ByteString authority;
            int i;
            this.frameLogger.logHeaders(OkHttpFrameLogger.Direction.INBOUND, streamId, headerBlock, inFinished);
            if ((streamId & 1) != 0) {
                synchronized (OkHttpServerTransport.this.lock) {
                    if (streamId > OkHttpServerTransport.this.goAwayStreamId) {
                        return;
                    }
                    boolean newStream = streamId > OkHttpServerTransport.this.lastStreamId;
                    if (newStream) {
                        OkHttpServerTransport.this.lastStreamId = streamId;
                    }
                    int metadataSize = headerBlockSize(headerBlock);
                    if (metadataSize <= OkHttpServerTransport.this.config.maxInboundMetadataSize) {
                        OkHttpServerTransport.headerRemove(headerBlock, ByteString.EMPTY);
                        ByteString httpMethod = null;
                        ByteString scheme = null;
                        ByteString path = null;
                        ByteString authority2 = null;
                        while (headerBlock.size() > 0 && headerBlock.get(0).name.getByte(0) == 58) {
                            Header header = headerBlock.remove(0);
                            if (!OkHttpServerTransport.HTTP_METHOD.equals(header.name) || httpMethod != null) {
                                ByteString httpMethod2 = OkHttpServerTransport.SCHEME;
                                if (!httpMethod2.equals(header.name) || scheme != null) {
                                    ByteString scheme2 = OkHttpServerTransport.PATH;
                                    if (!scheme2.equals(header.name) || path != null) {
                                        ByteString path2 = OkHttpServerTransport.AUTHORITY;
                                        if (!path2.equals(header.name) || authority2 != null) {
                                            streamError(streamId, ErrorCode.PROTOCOL_ERROR, "Unexpected pseudo header. RFC7540 section 8.1.2.1");
                                            return;
                                        }
                                        authority2 = header.value;
                                    } else {
                                        path = header.value;
                                    }
                                } else {
                                    scheme = header.value;
                                }
                            } else {
                                httpMethod = header.value;
                            }
                        }
                        for (int i2 = 0; i2 < headerBlock.size(); i2++) {
                            if (headerBlock.get(i2).name.getByte(0) == 58) {
                                streamError(streamId, ErrorCode.PROTOCOL_ERROR, "Pseudo header not before regular headers. RFC7540 section 8.1.2.1");
                                return;
                            }
                        }
                        if (OkHttpServerTransport.CONNECT_METHOD.equals(httpMethod) || !newStream || (httpMethod != null && scheme != null && path != null)) {
                            if (OkHttpServerTransport.headerContains(headerBlock, OkHttpServerTransport.CONNECTION)) {
                                streamError(streamId, ErrorCode.PROTOCOL_ERROR, "Connection-specific headers not permitted. RFC7540 section 8.1.2.2");
                                return;
                            }
                            if (!newStream) {
                                if (inFinished) {
                                    synchronized (OkHttpServerTransport.this.lock) {
                                        StreamState stream = (StreamState) OkHttpServerTransport.this.streams.get(Integer.valueOf(streamId));
                                        if (stream == null) {
                                            streamError(streamId, ErrorCode.STREAM_CLOSED, "Received headers for closed stream");
                                            return;
                                        } else if (stream.hasReceivedEndOfStream()) {
                                            streamError(streamId, ErrorCode.STREAM_CLOSED, "Received HEADERS for half-closed (remote) stream. RFC7540 section 5.1");
                                            return;
                                        } else {
                                            stream.inboundDataReceived(new Buffer(), 0, true);
                                            return;
                                        }
                                    }
                                }
                                streamError(streamId, ErrorCode.PROTOCOL_ERROR, "Headers disallowed in the middle of the stream. RFC7540 section 8.1");
                                return;
                            }
                            if (authority2 == null && (i = OkHttpServerTransport.headerFind(headerBlock, OkHttpServerTransport.HOST, 0)) != -1) {
                                if (OkHttpServerTransport.headerFind(headerBlock, OkHttpServerTransport.HOST, i + 1) != -1) {
                                    respondWithHttpError(streamId, inFinished, LogSeverity.WARNING_VALUE, Status.Code.INTERNAL, "Multiple host headers disallowed. RFC7230 section 5.4");
                                    return;
                                } else {
                                    ByteString authority3 = headerBlock.get(i).value;
                                    authority = authority3;
                                }
                            } else {
                                authority = authority2;
                            }
                            OkHttpServerTransport.headerRemove(headerBlock, OkHttpServerTransport.HOST);
                            if (path.size() != 0 && path.getByte(0) == 47) {
                                String method = OkHttpServerTransport.asciiString(path).substring(1);
                                ByteString contentType = OkHttpServerTransport.headerGetRequiredSingle(headerBlock, OkHttpServerTransport.CONTENT_TYPE);
                                if (contentType != null) {
                                    String contentTypeString = OkHttpServerTransport.asciiString(contentType);
                                    if (GrpcUtil.isGrpcContentType(contentTypeString)) {
                                        if (OkHttpServerTransport.POST_METHOD.equals(httpMethod)) {
                                            ByteString te = OkHttpServerTransport.headerGetRequiredSingle(headerBlock, OkHttpServerTransport.TE);
                                            if (OkHttpServerTransport.TE_TRAILERS.equals(te)) {
                                                OkHttpServerTransport.headerRemove(headerBlock, OkHttpServerTransport.CONTENT_LENGTH);
                                                Metadata metadata = Utils.convertHeaders(headerBlock);
                                                StatsTraceContext statsTraceCtx = StatsTraceContext.newServerContext(OkHttpServerTransport.this.config.streamTracerFactories, method, metadata);
                                                synchronized (OkHttpServerTransport.this.lock) {
                                                    try {
                                                        try {
                                                            try {
                                                                try {
                                                                    try {
                                                                        OkHttpServerStream.TransportState stream2 = new OkHttpServerStream.TransportState(OkHttpServerTransport.this, streamId, OkHttpServerTransport.this.config.maxInboundMessageSize, statsTraceCtx, OkHttpServerTransport.this.lock, OkHttpServerTransport.this.frameWriter, OkHttpServerTransport.this.outboundFlow, OkHttpServerTransport.this.config.flowControlWindow, OkHttpServerTransport.this.tracer, method);
                                                                        OkHttpServerStream streamForApp = new OkHttpServerStream(stream2, OkHttpServerTransport.this.attributes, authority == null ? null : OkHttpServerTransport.asciiString(authority), statsTraceCtx, OkHttpServerTransport.this.tracer);
                                                                        if (OkHttpServerTransport.this.streams.isEmpty()) {
                                                                            try {
                                                                                OkHttpServerTransport.this.keepAliveEnforcer.onTransportActive();
                                                                                if (OkHttpServerTransport.this.maxConnectionIdleManager != null) {
                                                                                    OkHttpServerTransport.this.maxConnectionIdleManager.onTransportActive();
                                                                                }
                                                                            } catch (Throwable th) {
                                                                                th = th;
                                                                                throw th;
                                                                            }
                                                                        }
                                                                        OkHttpServerTransport.this.streams.put(Integer.valueOf(streamId), stream2);
                                                                        OkHttpServerTransport.this.listener.streamCreated(streamForApp, method, metadata);
                                                                        stream2.onStreamAllocated();
                                                                        if (inFinished) {
                                                                            stream2.inboundDataReceived(new Buffer(), 0, inFinished);
                                                                        }
                                                                    } catch (Throwable th2) {
                                                                        th = th2;
                                                                    }
                                                                } catch (Throwable th3) {
                                                                    th = th3;
                                                                }
                                                            } catch (Throwable th4) {
                                                                th = th4;
                                                            }
                                                        } catch (Throwable th5) {
                                                            th = th5;
                                                        }
                                                    } catch (Throwable th6) {
                                                        th = th6;
                                                    }
                                                }
                                            } else {
                                                respondWithGrpcError(streamId, inFinished, Status.Code.INTERNAL, String.format("Expected header TE: %s, but %s is received. Some intermediate proxy may not support trailers", OkHttpServerTransport.asciiString(OkHttpServerTransport.TE_TRAILERS), te == null ? "<missing>" : OkHttpServerTransport.asciiString(te)));
                                            }
                                        } else {
                                            respondWithHttpError(streamId, inFinished, 405, Status.Code.INTERNAL, "HTTP Method is not supported: " + OkHttpServerTransport.asciiString(httpMethod));
                                        }
                                    } else {
                                        respondWithHttpError(streamId, inFinished, 415, Status.Code.INTERNAL, "Content-Type is not supported: " + contentTypeString);
                                    }
                                } else {
                                    respondWithHttpError(streamId, inFinished, 415, Status.Code.INTERNAL, "Content-Type is missing or duplicated");
                                }
                            } else {
                                ByteString path3 = path;
                                respondWithHttpError(streamId, inFinished, 404, Status.Code.UNIMPLEMENTED, "Expected path to start with /: " + OkHttpServerTransport.asciiString(path3));
                            }
                        } else {
                            streamError(streamId, ErrorCode.PROTOCOL_ERROR, "Missing required pseudo header. RFC7540 section 8.1.2.3");
                        }
                    } else {
                        respondWithHttpError(streamId, inFinished, 431, Status.Code.RESOURCE_EXHAUSTED, String.format(Locale.US, "Request metadata larger than %d: %d", Integer.valueOf(OkHttpServerTransport.this.config.maxInboundMetadataSize), Integer.valueOf(metadataSize)));
                    }
                }
            } else {
                connectionError(ErrorCode.PROTOCOL_ERROR, "Clients cannot open even numbered streams. RFC7540 section 5.1.1");
            }
        }

        private int headerBlockSize(List<Header> headerBlock) {
            long size = 0;
            for (int i = 0; i < headerBlock.size(); i++) {
                Header header = headerBlock.get(i);
                size += (long) (header.name.size() + 32 + header.value.size());
            }
            return (int) Math.min(size, 2147483647L);
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void data(boolean inFinished, int streamId, BufferedSource in, int length) throws IOException {
            this.frameLogger.logData(OkHttpFrameLogger.Direction.INBOUND, streamId, in.getBuffer(), length, inFinished);
            if (streamId == 0) {
                connectionError(ErrorCode.PROTOCOL_ERROR, "Stream 0 is reserved for control messages. RFC7540 section 5.1.1");
                return;
            }
            if ((streamId & 1) == 0) {
                connectionError(ErrorCode.PROTOCOL_ERROR, "Clients cannot open even numbered streams. RFC7540 section 5.1.1");
                return;
            }
            in.require(length);
            synchronized (OkHttpServerTransport.this.lock) {
                StreamState stream = (StreamState) OkHttpServerTransport.this.streams.get(Integer.valueOf(streamId));
                if (stream == null) {
                    in.skip(length);
                    streamError(streamId, ErrorCode.STREAM_CLOSED, "Received data for closed stream");
                    return;
                }
                if (stream.hasReceivedEndOfStream()) {
                    in.skip(length);
                    streamError(streamId, ErrorCode.STREAM_CLOSED, "Received DATA for half-closed (remote) stream. RFC7540 section 5.1");
                    return;
                }
                if (stream.inboundWindowAvailable() < length) {
                    in.skip(length);
                    streamError(streamId, ErrorCode.FLOW_CONTROL_ERROR, "Received DATA size exceeded window size. RFC7540 section 6.9");
                    return;
                }
                Buffer buf = new Buffer();
                buf.write(in.getBuffer(), length);
                stream.inboundDataReceived(buf, length, inFinished);
                this.connectionUnacknowledgedBytesRead += length;
                if (this.connectionUnacknowledgedBytesRead >= OkHttpServerTransport.this.config.flowControlWindow * 0.5f) {
                    synchronized (OkHttpServerTransport.this.lock) {
                        OkHttpServerTransport.this.frameWriter.windowUpdate(0, this.connectionUnacknowledgedBytesRead);
                        OkHttpServerTransport.this.frameWriter.flush();
                    }
                    this.connectionUnacknowledgedBytesRead = 0;
                }
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void rstStream(int streamId, ErrorCode errorCode) {
            this.frameLogger.logRstStream(OkHttpFrameLogger.Direction.INBOUND, streamId, errorCode);
            if (!ErrorCode.NO_ERROR.equals(errorCode) && !ErrorCode.CANCEL.equals(errorCode) && !ErrorCode.STREAM_CLOSED.equals(errorCode)) {
                OkHttpServerTransport.log.log(Level.INFO, "Received RST_STREAM: " + errorCode);
            }
            Status status = GrpcUtil.Http2Error.statusForCode(errorCode.httpCode).withDescription("RST_STREAM");
            synchronized (OkHttpServerTransport.this.lock) {
                StreamState stream = (StreamState) OkHttpServerTransport.this.streams.get(Integer.valueOf(streamId));
                if (stream != null) {
                    stream.inboundRstReceived(status);
                    OkHttpServerTransport.this.streamClosed(streamId, false);
                }
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void settings(boolean clearPrevious, Settings settings) {
            this.frameLogger.logSettings(OkHttpFrameLogger.Direction.INBOUND, settings);
            synchronized (OkHttpServerTransport.this.lock) {
                boolean outboundWindowSizeIncreased = false;
                if (OkHttpSettingsUtil.isSet(settings, 7)) {
                    int initialWindowSize = OkHttpSettingsUtil.get(settings, 7);
                    outboundWindowSizeIncreased = OkHttpServerTransport.this.outboundFlow.initialOutboundWindowSize(initialWindowSize);
                }
                OkHttpServerTransport.this.frameWriter.ackSettings(settings);
                OkHttpServerTransport.this.frameWriter.flush();
                if (!this.receivedSettings) {
                    this.receivedSettings = true;
                    OkHttpServerTransport.this.attributes = OkHttpServerTransport.this.listener.transportReady(OkHttpServerTransport.this.attributes);
                }
                if (outboundWindowSizeIncreased) {
                    OkHttpServerTransport.this.outboundFlow.writeStreams();
                }
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void ping(boolean ack, int payload1, int payload2) {
            if (!OkHttpServerTransport.this.keepAliveEnforcer.pingAcceptable()) {
                OkHttpServerTransport.this.abruptShutdown(ErrorCode.ENHANCE_YOUR_CALM, "too_many_pings", Status.RESOURCE_EXHAUSTED.withDescription("Too many pings from client"), false);
                return;
            }
            long payload = (((long) payload1) << 32) | (((long) payload2) & 4294967295L);
            if (!ack) {
                this.frameLogger.logPing(OkHttpFrameLogger.Direction.INBOUND, payload);
                synchronized (OkHttpServerTransport.this.lock) {
                    OkHttpServerTransport.this.frameWriter.ping(true, payload1, payload2);
                    OkHttpServerTransport.this.frameWriter.flush();
                }
                return;
            }
            this.frameLogger.logPingAck(OkHttpFrameLogger.Direction.INBOUND, payload);
            if (57005 == payload) {
                return;
            }
            if (4369 == payload) {
                OkHttpServerTransport.this.triggerGracefulSecondGoaway();
            } else {
                OkHttpServerTransport.log.log(Level.INFO, "Received unexpected ping ack: " + payload);
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void ackSettings() {
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void goAway(int lastGoodStreamId, ErrorCode errorCode, ByteString debugData) {
            this.frameLogger.logGoAway(OkHttpFrameLogger.Direction.INBOUND, lastGoodStreamId, errorCode, debugData);
            String description = String.format("Received GOAWAY: %s '%s'", errorCode, debugData.utf8());
            Status status = GrpcUtil.Http2Error.statusForCode(errorCode.httpCode).withDescription(description);
            if (!ErrorCode.NO_ERROR.equals(errorCode)) {
                OkHttpServerTransport.log.log(Level.WARNING, "Received GOAWAY: {0} {1}", new Object[]{errorCode, debugData.utf8()});
            }
            synchronized (OkHttpServerTransport.this.lock) {
                OkHttpServerTransport.this.goAwayStatus = status;
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void pushPromise(int streamId, int promisedStreamId, List<Header> requestHeaders) throws IOException {
            this.frameLogger.logPushPromise(OkHttpFrameLogger.Direction.INBOUND, streamId, promisedStreamId, requestHeaders);
            connectionError(ErrorCode.PROTOCOL_ERROR, "PUSH_PROMISE only allowed on peer-initiated streams. RFC7540 section 6.6");
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void windowUpdate(int streamId, long delta) {
            this.frameLogger.logWindowsUpdate(OkHttpFrameLogger.Direction.INBOUND, streamId, delta);
            synchronized (OkHttpServerTransport.this.lock) {
                if (streamId == 0) {
                    OkHttpServerTransport.this.outboundFlow.windowUpdate(null, (int) delta);
                } else {
                    StreamState stream = (StreamState) OkHttpServerTransport.this.streams.get(Integer.valueOf(streamId));
                    if (stream != null) {
                        OkHttpServerTransport.this.outboundFlow.windowUpdate(stream.getOutboundFlowState(), (int) delta);
                    }
                }
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void priority(int streamId, int streamDependency, int weight, boolean exclusive) {
            this.frameLogger.logPriority(OkHttpFrameLogger.Direction.INBOUND, streamId, streamDependency, weight, exclusive);
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void alternateService(int streamId, String origin, ByteString protocol, String host, int port, long maxAge) {
        }

        private void connectionError(ErrorCode errorCode, String moreDetail) {
            Status status = GrpcUtil.Http2Error.statusForCode(errorCode.httpCode).withDescription(String.format("HTTP2 connection error: %s '%s'", errorCode, moreDetail));
            OkHttpServerTransport.this.abruptShutdown(errorCode, moreDetail, status, false);
        }

        private void streamError(int streamId, ErrorCode errorCode, String reason) {
            if (errorCode == ErrorCode.PROTOCOL_ERROR) {
                OkHttpServerTransport.log.log(Level.FINE, "Responding with RST_STREAM {0}: {1}", new Object[]{errorCode, reason});
            }
            synchronized (OkHttpServerTransport.this.lock) {
                OkHttpServerTransport.this.frameWriter.rstStream(streamId, errorCode);
                OkHttpServerTransport.this.frameWriter.flush();
                StreamState stream = (StreamState) OkHttpServerTransport.this.streams.get(Integer.valueOf(streamId));
                if (stream != null) {
                    stream.transportReportStatus(Status.INTERNAL.withDescription(String.format("Responded with RST_STREAM %s: %s", errorCode, reason)));
                    OkHttpServerTransport.this.streamClosed(streamId, false);
                }
            }
        }

        private void respondWithHttpError(int streamId, boolean inFinished, int httpCode, Status.Code statusCode, String msg) {
            Metadata metadata = new Metadata();
            metadata.put(InternalStatus.CODE_KEY, statusCode.toStatus());
            metadata.put(InternalStatus.MESSAGE_KEY, msg);
            List<Header> headers = Headers.createHttpResponseHeaders(httpCode, "text/plain; charset=utf-8", metadata);
            Buffer data = new Buffer().writeUtf8(msg);
            synchronized (OkHttpServerTransport.this.lock) {
                final Http2ErrorStreamState stream = new Http2ErrorStreamState(streamId, OkHttpServerTransport.this.lock, OkHttpServerTransport.this.outboundFlow, OkHttpServerTransport.this.config.flowControlWindow);
                if (OkHttpServerTransport.this.streams.isEmpty()) {
                    OkHttpServerTransport.this.keepAliveEnforcer.onTransportActive();
                    if (OkHttpServerTransport.this.maxConnectionIdleManager != null) {
                        OkHttpServerTransport.this.maxConnectionIdleManager.onTransportActive();
                    }
                }
                OkHttpServerTransport.this.streams.put(Integer.valueOf(streamId), stream);
                if (inFinished) {
                    stream.inboundDataReceived(new Buffer(), 0, true);
                }
                OkHttpServerTransport.this.frameWriter.headers(streamId, headers);
                OkHttpServerTransport.this.outboundFlow.data(true, stream.getOutboundFlowState(), data, true);
                OkHttpServerTransport.this.outboundFlow.notifyWhenNoPendingData(stream.getOutboundFlowState(), new Runnable() { // from class: io.grpc.okhttp.OkHttpServerTransport$FrameHandler$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m459x6fc8eaa(stream);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: rstOkAtEndOfHttpError, reason: merged with bridge method [inline-methods] */
        public void m459x6fc8eaa(Http2ErrorStreamState stream) {
            synchronized (OkHttpServerTransport.this.lock) {
                if (!stream.hasReceivedEndOfStream()) {
                    OkHttpServerTransport.this.frameWriter.rstStream(stream.streamId, ErrorCode.NO_ERROR);
                }
                OkHttpServerTransport.this.streamClosed(stream.streamId, true);
            }
        }

        private void respondWithGrpcError(int streamId, boolean inFinished, Status.Code statusCode, String msg) {
            Metadata metadata = new Metadata();
            metadata.put(InternalStatus.CODE_KEY, statusCode.toStatus());
            metadata.put(InternalStatus.MESSAGE_KEY, msg);
            List<Header> headers = Headers.createResponseTrailers(metadata, false);
            synchronized (OkHttpServerTransport.this.lock) {
                OkHttpServerTransport.this.frameWriter.synReply(true, streamId, headers);
                if (!inFinished) {
                    OkHttpServerTransport.this.frameWriter.rstStream(streamId, ErrorCode.NO_ERROR);
                }
                OkHttpServerTransport.this.frameWriter.flush();
            }
        }
    }

    private final class KeepAlivePinger implements KeepAliveManager.KeepAlivePinger {
        private KeepAlivePinger() {
        }

        @Override // io.grpc.internal.KeepAliveManager.KeepAlivePinger
        public void ping() {
            synchronized (OkHttpServerTransport.this.lock) {
                OkHttpServerTransport.this.frameWriter.ping(false, 0, OkHttpServerTransport.KEEPALIVE_PING);
                OkHttpServerTransport.this.frameWriter.flush();
            }
            OkHttpServerTransport.this.tracer.reportKeepAliveSent();
        }

        @Override // io.grpc.internal.KeepAliveManager.KeepAlivePinger
        public void onPingTimeout() {
            synchronized (OkHttpServerTransport.this.lock) {
                OkHttpServerTransport.this.goAwayStatus = Status.UNAVAILABLE.withDescription("Keepalive failed. Considering connection dead");
                GrpcUtil.closeQuietly(OkHttpServerTransport.this.bareSocket);
            }
        }
    }

    static class Http2ErrorStreamState implements StreamState, OutboundFlowController.Stream {
        private final Object lock;
        private final OutboundFlowController.StreamState outboundFlowState;
        private boolean receivedEndOfStream;
        private final int streamId;
        private int window;

        Http2ErrorStreamState(int streamId, Object lock, OutboundFlowController outboundFlow, int initialWindowSize) {
            this.streamId = streamId;
            this.lock = lock;
            this.outboundFlowState = outboundFlow.createState(this, streamId);
            this.window = initialWindowSize;
        }

        @Override // io.grpc.okhttp.OutboundFlowController.Stream
        public void onSentBytes(int frameBytes) {
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public void inboundDataReceived(Buffer frame, int windowConsumed, boolean endOfStream) {
            synchronized (this.lock) {
                if (endOfStream) {
                    this.receivedEndOfStream = true;
                    this.window -= windowConsumed;
                    try {
                        frame.skip(frame.size());
                    } catch (IOException ex) {
                        throw new AssertionError(ex);
                    }
                } else {
                    this.window -= windowConsumed;
                    frame.skip(frame.size());
                }
            }
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

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public void transportReportStatus(Status status) {
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public void inboundRstReceived(Status status) {
        }

        @Override // io.grpc.okhttp.OkHttpServerTransport.StreamState
        public OutboundFlowController.StreamState getOutboundFlowState() {
            OutboundFlowController.StreamState streamState;
            synchronized (this.lock) {
                streamState = this.outboundFlowState;
            }
            return streamState;
        }
    }
}
