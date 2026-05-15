package io.grpc.okhttp;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Grpc;
import io.grpc.HttpConnectProxiedSocketAddress;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.SerializingExecutor;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.grpc.okhttp.OkHttpChannelBuilder;
import io.grpc.okhttp.OkHttpFrameLogger;
import io.grpc.okhttp.OutboundFlowController;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.Credentials;
import io.grpc.okhttp.internal.StatusLine;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameReader;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Header;
import io.grpc.okhttp.internal.framed.HeadersMode;
import io.grpc.okhttp.internal.framed.Http2;
import io.grpc.okhttp.internal.framed.Settings;
import io.grpc.okhttp.internal.framed.Variant;
import io.grpc.okhttp.internal.proxy.HttpUrl;
import io.grpc.okhttp.internal.proxy.Request;
import io.perfmark.PerfMark;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import okio.Timeout;

/* JADX INFO: loaded from: classes10.dex */
class OkHttpClientTransport implements ConnectionClientTransport, ExceptionHandlingFrameWriter.TransportExceptionHandler, OutboundFlowController.Transport {
    private static final Map<ErrorCode, Status> ERROR_CODE_TO_STATUS = buildErrorCodeToStatusMap();
    private static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());
    private final InetSocketAddress address;
    private Attributes attributes;
    private ClientFrameHandler clientFrameHandler;
    SettableFuture<Void> connectedFuture;
    Runnable connectingCallback;
    private final ConnectionSpec connectionSpec;
    private int connectionUnacknowledgedBytesRead;
    private final String defaultAuthority;
    private boolean enableKeepAlive;
    private final Executor executor;
    private ExceptionHandlingFrameWriter frameWriter;
    private boolean goAwaySent;
    private Status goAwayStatus;
    private boolean hasStream;
    private HostnameVerifier hostnameVerifier;
    private final InUseStateAggregator<OkHttpClientStream> inUseState;
    private final int initialWindowSize;
    private KeepAliveManager keepAliveManager;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    private ManagedClientTransport.Listener listener;
    private final Object lock;
    private final InternalLogId logId;
    private int maxConcurrentStreams;
    private final int maxInboundMetadataSize;
    private final int maxMessageSize;
    private int nextStreamId;
    private OutboundFlowController outboundFlow;
    private final Deque<OkHttpClientStream> pendingStreams;
    private Http2Ping ping;

    @Nullable
    final HttpConnectProxiedSocketAddress proxiedAddr;
    int proxySocketTimeout;
    private final Random random;
    private final ScheduledExecutorService scheduler;
    private InternalChannelz.Security securityInfo;
    private final SerializingExecutor serializingExecutor;
    private Socket socket;
    private final SocketFactory socketFactory;
    private SSLSocketFactory sslSocketFactory;
    private boolean stopped;
    private final Supplier<Stopwatch> stopwatchFactory;
    private final Map<Integer, OkHttpClientStream> streams;
    private final Runnable tooManyPingsRunnable;
    private final TransportTracer transportTracer;
    private final boolean useGetForSafeMethods;
    private final String userAgent;
    private final Variant variant;

    static /* synthetic */ int access$2412(OkHttpClientTransport x0, int x1) {
        int i = x0.connectionUnacknowledgedBytesRead + x1;
        x0.connectionUnacknowledgedBytesRead = i;
        return i;
    }

    @Override // io.grpc.internal.ClientTransport
    public /* bridge */ /* synthetic */ ClientStream newStream(MethodDescriptor methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
        return newStream((MethodDescriptor<?, ?>) methodDescriptor, metadata, callOptions, clientStreamTracerArr);
    }

    private static Map<ErrorCode, Status> buildErrorCodeToStatusMap() {
        Map<ErrorCode, Status> errorToStatus = new EnumMap<>(ErrorCode.class);
        errorToStatus.put(ErrorCode.NO_ERROR, Status.INTERNAL.withDescription("No error: A GRPC status of OK should have been sent"));
        errorToStatus.put(ErrorCode.PROTOCOL_ERROR, Status.INTERNAL.withDescription("Protocol error"));
        errorToStatus.put(ErrorCode.INTERNAL_ERROR, Status.INTERNAL.withDescription("Internal error"));
        errorToStatus.put(ErrorCode.FLOW_CONTROL_ERROR, Status.INTERNAL.withDescription("Flow control error"));
        errorToStatus.put(ErrorCode.STREAM_CLOSED, Status.INTERNAL.withDescription("Stream closed"));
        errorToStatus.put(ErrorCode.FRAME_TOO_LARGE, Status.INTERNAL.withDescription("Frame too large"));
        errorToStatus.put(ErrorCode.REFUSED_STREAM, Status.UNAVAILABLE.withDescription("Refused stream"));
        errorToStatus.put(ErrorCode.CANCEL, Status.CANCELLED.withDescription("Cancelled"));
        errorToStatus.put(ErrorCode.COMPRESSION_ERROR, Status.INTERNAL.withDescription("Compression error"));
        errorToStatus.put(ErrorCode.CONNECT_ERROR, Status.INTERNAL.withDescription("Connect error"));
        errorToStatus.put(ErrorCode.ENHANCE_YOUR_CALM, Status.RESOURCE_EXHAUSTED.withDescription("Enhance your calm"));
        errorToStatus.put(ErrorCode.INADEQUATE_SECURITY, Status.PERMISSION_DENIED.withDescription("Inadequate security"));
        return Collections.unmodifiableMap(errorToStatus);
    }

    public OkHttpClientTransport(OkHttpChannelBuilder.OkHttpTransportFactory transportFactory, InetSocketAddress address, String authority, @Nullable String userAgent, Attributes eagAttrs, @Nullable HttpConnectProxiedSocketAddress proxiedAddr, Runnable tooManyPingsRunnable) {
        this(transportFactory, address, authority, userAgent, eagAttrs, GrpcUtil.STOPWATCH_SUPPLIER, new Http2(), proxiedAddr, tooManyPingsRunnable);
    }

    private OkHttpClientTransport(OkHttpChannelBuilder.OkHttpTransportFactory transportFactory, InetSocketAddress address, String authority, @Nullable String userAgent, Attributes eagAttrs, Supplier<Stopwatch> stopwatchFactory, Variant variant, @Nullable HttpConnectProxiedSocketAddress proxiedAddr, Runnable tooManyPingsRunnable) {
        this.random = new Random();
        this.lock = new Object();
        this.streams = new HashMap();
        this.maxConcurrentStreams = 0;
        this.pendingStreams = new LinkedList();
        this.inUseState = new InUseStateAggregator<OkHttpClientStream>() { // from class: io.grpc.okhttp.OkHttpClientTransport.1
            @Override // io.grpc.internal.InUseStateAggregator
            protected void handleInUse() {
                OkHttpClientTransport.this.listener.transportInUse(true);
            }

            @Override // io.grpc.internal.InUseStateAggregator
            protected void handleNotInUse() {
                OkHttpClientTransport.this.listener.transportInUse(false);
            }
        };
        this.proxySocketTimeout = 30000;
        this.address = (InetSocketAddress) Preconditions.checkNotNull(address, "address");
        this.defaultAuthority = authority;
        this.maxMessageSize = transportFactory.maxMessageSize;
        this.initialWindowSize = transportFactory.flowControlWindow;
        this.executor = (Executor) Preconditions.checkNotNull(transportFactory.executor, "executor");
        this.serializingExecutor = new SerializingExecutor(transportFactory.executor);
        this.scheduler = (ScheduledExecutorService) Preconditions.checkNotNull(transportFactory.scheduledExecutorService, "scheduledExecutorService");
        this.nextStreamId = 3;
        this.socketFactory = transportFactory.socketFactory == null ? SocketFactory.getDefault() : transportFactory.socketFactory;
        this.sslSocketFactory = transportFactory.sslSocketFactory;
        this.hostnameVerifier = transportFactory.hostnameVerifier;
        this.connectionSpec = (ConnectionSpec) Preconditions.checkNotNull(transportFactory.connectionSpec, "connectionSpec");
        this.stopwatchFactory = (Supplier) Preconditions.checkNotNull(stopwatchFactory, "stopwatchFactory");
        this.variant = (Variant) Preconditions.checkNotNull(variant, "variant");
        this.userAgent = GrpcUtil.getGrpcUserAgent("okhttp", userAgent);
        this.proxiedAddr = proxiedAddr;
        this.tooManyPingsRunnable = (Runnable) Preconditions.checkNotNull(tooManyPingsRunnable, "tooManyPingsRunnable");
        this.maxInboundMetadataSize = transportFactory.maxInboundMetadataSize;
        this.transportTracer = transportFactory.transportTracerFactory.create();
        this.logId = InternalLogId.allocate(getClass(), address.toString());
        this.attributes = Attributes.newBuilder().set(GrpcAttributes.ATTR_CLIENT_EAG_ATTRS, eagAttrs).build();
        this.useGetForSafeMethods = transportFactory.useGetForSafeMethods;
        initTransportTracer();
    }

    OkHttpClientTransport(OkHttpChannelBuilder.OkHttpTransportFactory transportFactory, String userAgent, Supplier<Stopwatch> stopwatchFactory, Variant variant, @Nullable Runnable connectingCallback, SettableFuture<Void> connectedFuture, Runnable tooManyPingsRunnable) {
        this(transportFactory, new InetSocketAddress("127.0.0.1", 80), "notarealauthority:80", userAgent, Attributes.EMPTY, stopwatchFactory, variant, null, tooManyPingsRunnable);
        this.connectingCallback = connectingCallback;
        this.connectedFuture = (SettableFuture) Preconditions.checkNotNull(connectedFuture, "connectedFuture");
    }

    boolean isUsingPlaintext() {
        return this.sslSocketFactory == null;
    }

    private void initTransportTracer() {
        synchronized (this.lock) {
            this.transportTracer.setFlowControlWindowReader(new TransportTracer.FlowControlReader() { // from class: io.grpc.okhttp.OkHttpClientTransport.2
                @Override // io.grpc.internal.TransportTracer.FlowControlReader
                public TransportTracer.FlowControlWindows read() {
                    TransportTracer.FlowControlWindows flowControlWindows;
                    synchronized (OkHttpClientTransport.this.lock) {
                        long local = OkHttpClientTransport.this.outboundFlow == null ? -1L : OkHttpClientTransport.this.outboundFlow.windowUpdate(null, 0);
                        long remote = (long) (OkHttpClientTransport.this.initialWindowSize * 0.5f);
                        flowControlWindows = new TransportTracer.FlowControlWindows(local, remote);
                    }
                    return flowControlWindows;
                }
            });
        }
    }

    void enableKeepAlive(boolean enable, long keepAliveTimeNanos, long keepAliveTimeoutNanos, boolean keepAliveWithoutCalls) {
        this.enableKeepAlive = enable;
        this.keepAliveTimeNanos = keepAliveTimeNanos;
        this.keepAliveTimeoutNanos = keepAliveTimeoutNanos;
        this.keepAliveWithoutCalls = keepAliveWithoutCalls;
    }

    @Override // io.grpc.internal.ClientTransport
    public void ping(ClientTransport.PingCallback callback, Executor executor) {
        Http2Ping p;
        boolean writePing;
        long data = 0;
        synchronized (this.lock) {
            Preconditions.checkState(this.frameWriter != null);
            if (this.stopped) {
                Http2Ping.notifyFailed(callback, executor, getPingFailure());
                return;
            }
            if (this.ping != null) {
                p = this.ping;
                writePing = false;
            } else {
                data = this.random.nextLong();
                Stopwatch stopwatch = this.stopwatchFactory.get();
                stopwatch.start();
                Http2Ping p2 = new Http2Ping(data, stopwatch);
                this.ping = p2;
                this.transportTracer.reportKeepAliveSent();
                p = p2;
                writePing = true;
            }
            if (writePing) {
                this.frameWriter.ping(false, (int) (data >>> 32), (int) data);
            }
            p.addCallback(callback, executor);
        }
    }

    @Override // io.grpc.internal.ClientTransport
    public OkHttpClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions, ClientStreamTracer[] tracers) throws Throwable {
        Preconditions.checkNotNull(method, "method");
        Preconditions.checkNotNull(headers, "headers");
        StatsTraceContext statsTraceContext = StatsTraceContext.newClientContext(tracers, getAttributes(), headers);
        synchronized (this.lock) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                return new OkHttpClientStream(method, headers, this.frameWriter, this, this.outboundFlow, this.lock, this.maxMessageSize, this.initialWindowSize, this.defaultAuthority, this.userAgent, statsTraceContext, this.transportTracer, callOptions, this.useGetForSafeMethods);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    void streamReadyToStart(OkHttpClientStream clientStream) {
        if (this.goAwayStatus != null) {
            clientStream.transportState().transportReportStatus(this.goAwayStatus, ClientStreamListener.RpcProgress.MISCARRIED, true, new Metadata());
        } else if (this.streams.size() >= this.maxConcurrentStreams) {
            this.pendingStreams.add(clientStream);
            setInUse(clientStream);
        } else {
            startStream(clientStream);
        }
    }

    private void startStream(OkHttpClientStream stream) {
        Preconditions.checkState(stream.transportState().id() == -1, "StreamId already assigned");
        this.streams.put(Integer.valueOf(this.nextStreamId), stream);
        setInUse(stream);
        stream.transportState().start(this.nextStreamId);
        if ((stream.getType() != MethodDescriptor.MethodType.UNARY && stream.getType() != MethodDescriptor.MethodType.SERVER_STREAMING) || stream.useGet()) {
            this.frameWriter.flush();
        }
        if (this.nextStreamId >= 2147483645) {
            this.nextStreamId = Integer.MAX_VALUE;
            startGoAway(Integer.MAX_VALUE, ErrorCode.NO_ERROR, Status.UNAVAILABLE.withDescription("Stream ids exhausted"));
        } else {
            this.nextStreamId += 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startPendingStreams() {
        boolean hasStreamStarted = false;
        while (!this.pendingStreams.isEmpty() && this.streams.size() < this.maxConcurrentStreams) {
            OkHttpClientStream stream = this.pendingStreams.poll();
            startStream(stream);
            hasStreamStarted = true;
        }
        return hasStreamStarted;
    }

    void removePendingStream(OkHttpClientStream pendingStream) {
        this.pendingStreams.remove(pendingStream);
        maybeClearInUse(pendingStream);
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public Runnable start(ManagedClientTransport.Listener listener) {
        this.listener = (ManagedClientTransport.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        if (this.enableKeepAlive) {
            this.keepAliveManager = new KeepAliveManager(new KeepAliveManager.ClientKeepAlivePinger(this), this.scheduler, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
            this.keepAliveManager.onTransportStarted();
        }
        final AsyncSink asyncSink = AsyncSink.sink(this.serializingExecutor, this, 10000);
        FrameWriter rawFrameWriter = asyncSink.limitControlFramesWriter(this.variant.newWriter(Okio.buffer(asyncSink), true));
        synchronized (this.lock) {
            this.frameWriter = new ExceptionHandlingFrameWriter(this, rawFrameWriter);
            this.outboundFlow = new OutboundFlowController(this, this.frameWriter);
        }
        final CountDownLatch latch = new CountDownLatch(1);
        this.serializingExecutor.execute(new Runnable() { // from class: io.grpc.okhttp.OkHttpClientTransport.3
            @Override // java.lang.Runnable
            public void run() {
                Socket sock;
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                BufferedSource source = Okio.buffer(new Source() { // from class: io.grpc.okhttp.OkHttpClientTransport.3.1
                    @Override // okio.Source
                    public long read(Buffer sink, long byteCount) {
                        return -1L;
                    }

                    @Override // okio.Source
                    public Timeout timeout() {
                        return Timeout.NONE;
                    }

                    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                    }
                });
                SSLSession sslSession = null;
                try {
                    try {
                        if (OkHttpClientTransport.this.proxiedAddr == null) {
                            sock = OkHttpClientTransport.this.socketFactory.createSocket(OkHttpClientTransport.this.address.getAddress(), OkHttpClientTransport.this.address.getPort());
                        } else {
                            if (!(OkHttpClientTransport.this.proxiedAddr.getProxyAddress() instanceof InetSocketAddress)) {
                                throw Status.INTERNAL.withDescription("Unsupported SocketAddress implementation " + OkHttpClientTransport.this.proxiedAddr.getProxyAddress().getClass()).asException();
                            }
                            sock = OkHttpClientTransport.this.createHttpProxySocket(OkHttpClientTransport.this.proxiedAddr.getTargetAddress(), (InetSocketAddress) OkHttpClientTransport.this.proxiedAddr.getProxyAddress(), OkHttpClientTransport.this.proxiedAddr.getUsername(), OkHttpClientTransport.this.proxiedAddr.getPassword());
                        }
                        if (OkHttpClientTransport.this.sslSocketFactory != null) {
                            SSLSocket sslSocket = OkHttpTlsUpgrader.upgrade(OkHttpClientTransport.this.sslSocketFactory, OkHttpClientTransport.this.hostnameVerifier, sock, OkHttpClientTransport.this.getOverridenHost(), OkHttpClientTransport.this.getOverridenPort(), OkHttpClientTransport.this.connectionSpec);
                            sslSession = sslSocket.getSession();
                            sock = sslSocket;
                        }
                        sock.setTcpNoDelay(true);
                        BufferedSource source2 = Okio.buffer(Okio.source(sock));
                        asyncSink.becomeConnected(Okio.sink(sock), sock);
                        OkHttpClientTransport.this.attributes = OkHttpClientTransport.this.attributes.toBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, sock.getRemoteSocketAddress()).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, sock.getLocalSocketAddress()).set(Grpc.TRANSPORT_ATTR_SSL_SESSION, sslSession).set(GrpcAttributes.ATTR_SECURITY_LEVEL, sslSession == null ? SecurityLevel.NONE : SecurityLevel.PRIVACY_AND_INTEGRITY).build();
                        OkHttpClientTransport.this.clientFrameHandler = OkHttpClientTransport.this.new ClientFrameHandler(OkHttpClientTransport.this.variant.newReader(source2, true));
                        synchronized (OkHttpClientTransport.this.lock) {
                            OkHttpClientTransport.this.socket = (Socket) Preconditions.checkNotNull(sock, "socket");
                            if (sslSession != null) {
                                OkHttpClientTransport.this.securityInfo = new InternalChannelz.Security(new InternalChannelz.Tls(sslSession));
                            }
                        }
                    } catch (StatusException e2) {
                        OkHttpClientTransport.this.startGoAway(0, ErrorCode.INTERNAL_ERROR, e2.getStatus());
                        OkHttpClientTransport.this.clientFrameHandler = OkHttpClientTransport.this.new ClientFrameHandler(OkHttpClientTransport.this.variant.newReader(source, true));
                    } catch (Exception e3) {
                        OkHttpClientTransport.this.onException(e3);
                        OkHttpClientTransport.this.clientFrameHandler = OkHttpClientTransport.this.new ClientFrameHandler(OkHttpClientTransport.this.variant.newReader(source, true));
                    }
                } catch (Throwable th) {
                    OkHttpClientTransport.this.clientFrameHandler = OkHttpClientTransport.this.new ClientFrameHandler(OkHttpClientTransport.this.variant.newReader(source, true));
                    throw th;
                }
            }
        });
        try {
            sendConnectionPrefaceAndSettings();
            latch.countDown();
            this.serializingExecutor.execute(new Runnable() { // from class: io.grpc.okhttp.OkHttpClientTransport.4
                @Override // java.lang.Runnable
                public void run() {
                    if (OkHttpClientTransport.this.connectingCallback != null) {
                        OkHttpClientTransport.this.connectingCallback.run();
                    }
                    OkHttpClientTransport.this.executor.execute(OkHttpClientTransport.this.clientFrameHandler);
                    synchronized (OkHttpClientTransport.this.lock) {
                        OkHttpClientTransport.this.maxConcurrentStreams = Integer.MAX_VALUE;
                        OkHttpClientTransport.this.startPendingStreams();
                    }
                    if (OkHttpClientTransport.this.connectedFuture != null) {
                        OkHttpClientTransport.this.connectedFuture.set(null);
                    }
                }
            });
            return null;
        } catch (Throwable th) {
            latch.countDown();
            throw th;
        }
    }

    private void sendConnectionPrefaceAndSettings() {
        synchronized (this.lock) {
            this.frameWriter.connectionPreface();
            Settings settings = new Settings();
            OkHttpSettingsUtil.set(settings, 7, this.initialWindowSize);
            this.frameWriter.settings(settings);
            if (this.initialWindowSize > 65535) {
                this.frameWriter.windowUpdate(0, this.initialWindowSize - 65535);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Socket createHttpProxySocket(InetSocketAddress address, InetSocketAddress proxyAddress, String proxyUsername, String proxyPassword) throws StatusException {
        Source source;
        BufferedSink sink;
        Socket sock = null;
        try {
            if (proxyAddress.getAddress() != null) {
                sock = this.socketFactory.createSocket(proxyAddress.getAddress(), proxyAddress.getPort());
            } else {
                sock = this.socketFactory.createSocket(proxyAddress.getHostName(), proxyAddress.getPort());
            }
            sock.setTcpNoDelay(true);
            sock.setSoTimeout(this.proxySocketTimeout);
            source = Okio.source(sock);
            sink = Okio.buffer(Okio.sink(sock));
        } catch (IOException e) {
            e = e;
        }
        try {
            Request proxyRequest = createHttpProxyRequest(address, proxyUsername, proxyPassword);
            HttpUrl url = proxyRequest.httpUrl();
            String requestLine = String.format(Locale.US, "CONNECT %s:%d HTTP/1.1", url.host(), Integer.valueOf(url.port()));
            sink.writeUtf8(requestLine).writeUtf8("\r\n");
            int size = proxyRequest.headers().size();
            for (int i = 0; i < size; i++) {
                sink.writeUtf8(proxyRequest.headers().name(i)).writeUtf8(": ").writeUtf8(proxyRequest.headers().value(i)).writeUtf8("\r\n");
            }
            sink.writeUtf8("\r\n");
            sink.flush();
            StatusLine statusLine = StatusLine.parse(readUtf8LineStrictUnbuffered(source));
            while (!readUtf8LineStrictUnbuffered(source).equals("")) {
            }
            if (statusLine.code < 200 || statusLine.code >= 300) {
                Buffer body = new Buffer();
                try {
                    sock.shutdownOutput();
                    source.read(body, 1024L);
                } catch (IOException ex) {
                    body.writeUtf8("Unable to read body: " + ex.toString());
                }
                try {
                    sock.close();
                } catch (IOException e2) {
                }
                String message = String.format(Locale.US, "Response returned from proxy was not successful (expected 2xx, got %d %s). Response body:\n%s", Integer.valueOf(statusLine.code), statusLine.message, body.readUtf8());
                throw Status.UNAVAILABLE.withDescription(message).asException();
            }
            sock.setSoTimeout(0);
            return sock;
        } catch (IOException e3) {
            e = e3;
            if (sock != null) {
                GrpcUtil.closeQuietly(sock);
            }
            throw Status.UNAVAILABLE.withDescription("Failed trying to connect with proxy").withCause(e).asException();
        }
    }

    private Request createHttpProxyRequest(InetSocketAddress address, String proxyUsername, String proxyPassword) {
        HttpUrl tunnelUrl = new HttpUrl.Builder().scheme("https").host(address.getHostName()).port(address.getPort()).build();
        Request.Builder request = new Request.Builder().url(tunnelUrl).header(HttpHeaders.HOST, tunnelUrl.host() + ":" + tunnelUrl.port()).header(HttpHeaders.USER_AGENT, this.userAgent);
        if (proxyUsername != null && proxyPassword != null) {
            request.header(HttpHeaders.PROXY_AUTHORIZATION, Credentials.basic(proxyUsername, proxyPassword));
        }
        return request.build();
    }

    private static String readUtf8LineStrictUnbuffered(Source source) throws IOException {
        Buffer buffer = new Buffer();
        while (source.read(buffer, 1L) != -1) {
            if (buffer.getByte(buffer.size() - 1) == 10) {
                return buffer.readUtf8LineStrict();
            }
        }
        throw new EOFException("\\n not found: " + buffer.readByteString().hex());
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("address", this.address).toString();
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    String getOverridenHost() {
        URI uri = GrpcUtil.authorityToUri(this.defaultAuthority);
        if (uri.getHost() != null) {
            return uri.getHost();
        }
        return this.defaultAuthority;
    }

    int getOverridenPort() {
        URI uri = GrpcUtil.authorityToUri(this.defaultAuthority);
        if (uri.getPort() != -1) {
            return uri.getPort();
        }
        return this.address.getPort();
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public void shutdown(Status reason) {
        synchronized (this.lock) {
            if (this.goAwayStatus != null) {
                return;
            }
            this.goAwayStatus = reason;
            this.listener.transportShutdown(this.goAwayStatus);
            stopIfNecessary();
        }
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public void shutdownNow(Status reason) {
        shutdown(reason);
        synchronized (this.lock) {
            Iterator<Map.Entry<Integer, OkHttpClientStream>> it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, OkHttpClientStream> entry = it.next();
                it.remove();
                entry.getValue().transportState().transportReportStatus(reason, false, new Metadata());
                maybeClearInUse(entry.getValue());
            }
            for (OkHttpClientStream stream : this.pendingStreams) {
                stream.transportState().transportReportStatus(reason, ClientStreamListener.RpcProgress.MISCARRIED, true, new Metadata());
                maybeClearInUse(stream);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    @Override // io.grpc.internal.ConnectionClientTransport
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override // io.grpc.okhttp.OutboundFlowController.Transport
    public OutboundFlowController.StreamState[] getActiveStreams() {
        OutboundFlowController.StreamState[] flowStreams;
        synchronized (this.lock) {
            flowStreams = new OutboundFlowController.StreamState[this.streams.size()];
            int i = 0;
            for (OkHttpClientStream stream : this.streams.values()) {
                flowStreams[i] = stream.transportState().getOutboundFlowState();
                i++;
            }
        }
        return flowStreams;
    }

    ClientFrameHandler getHandler() {
        return this.clientFrameHandler;
    }

    SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    int getPendingStreamSize() {
        int size;
        synchronized (this.lock) {
            size = this.pendingStreams.size();
        }
        return size;
    }

    void setNextStreamId(int nextStreamId) {
        synchronized (this.lock) {
            this.nextStreamId = nextStreamId;
        }
    }

    @Override // io.grpc.okhttp.ExceptionHandlingFrameWriter.TransportExceptionHandler
    public void onException(Throwable failureCause) {
        Preconditions.checkNotNull(failureCause, "failureCause");
        Status status = Status.UNAVAILABLE.withCause(failureCause);
        startGoAway(0, ErrorCode.INTERNAL_ERROR, status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onError(ErrorCode errorCode, String moreDetail) {
        startGoAway(0, errorCode, toGrpcStatus(errorCode).augmentDescription(moreDetail));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGoAway(int lastKnownStreamId, ErrorCode errorCode, Status status) {
        synchronized (this.lock) {
            if (this.goAwayStatus == null) {
                this.goAwayStatus = status;
                this.listener.transportShutdown(status);
            }
            if (errorCode != null && !this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway(0, errorCode, new byte[0]);
            }
            Iterator<Map.Entry<Integer, OkHttpClientStream>> it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, OkHttpClientStream> entry = it.next();
                if (entry.getKey().intValue() > lastKnownStreamId) {
                    it.remove();
                    entry.getValue().transportState().transportReportStatus(status, ClientStreamListener.RpcProgress.REFUSED, false, new Metadata());
                    maybeClearInUse(entry.getValue());
                }
            }
            for (OkHttpClientStream stream : this.pendingStreams) {
                stream.transportState().transportReportStatus(status, ClientStreamListener.RpcProgress.MISCARRIED, true, new Metadata());
                maybeClearInUse(stream);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    void finishStream(int streamId, @Nullable Status status, ClientStreamListener.RpcProgress rpcProgress, boolean stopDelivery, @Nullable ErrorCode errorCode, @Nullable Metadata trailers) {
        synchronized (this.lock) {
            OkHttpClientStream stream = this.streams.remove(Integer.valueOf(streamId));
            if (stream != null) {
                if (errorCode != null) {
                    this.frameWriter.rstStream(streamId, ErrorCode.CANCEL);
                }
                if (status != null) {
                    stream.transportState().transportReportStatus(status, rpcProgress, stopDelivery, trailers != null ? trailers : new Metadata());
                }
                if (!startPendingStreams()) {
                    stopIfNecessary();
                    maybeClearInUse(stream);
                }
            }
        }
    }

    private void stopIfNecessary() {
        if (this.goAwayStatus == null || !this.streams.isEmpty() || !this.pendingStreams.isEmpty() || this.stopped) {
            return;
        }
        this.stopped = true;
        if (this.keepAliveManager != null) {
            this.keepAliveManager.onTransportTermination();
        }
        if (this.ping != null) {
            this.ping.failed(getPingFailure());
            this.ping = null;
        }
        if (!this.goAwaySent) {
            this.goAwaySent = true;
            this.frameWriter.goAway(0, ErrorCode.NO_ERROR, new byte[0]);
        }
        this.frameWriter.close();
    }

    private void maybeClearInUse(OkHttpClientStream stream) {
        if (this.hasStream && this.pendingStreams.isEmpty() && this.streams.isEmpty()) {
            this.hasStream = false;
            if (this.keepAliveManager != null) {
                this.keepAliveManager.onTransportIdle();
            }
        }
        if (stream.shouldBeCountedForInUse()) {
            this.inUseState.updateObjectInUse(stream, false);
        }
    }

    private void setInUse(OkHttpClientStream stream) {
        if (!this.hasStream) {
            this.hasStream = true;
            if (this.keepAliveManager != null) {
                this.keepAliveManager.onTransportActive();
            }
        }
        if (stream.shouldBeCountedForInUse()) {
            this.inUseState.updateObjectInUse(stream, true);
        }
    }

    private Throwable getPingFailure() {
        synchronized (this.lock) {
            if (this.goAwayStatus != null) {
                return this.goAwayStatus.asException();
            }
            return Status.UNAVAILABLE.withDescription("Connection closed").asException();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean mayHaveCreatedStream(int r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            int r1 = r3.nextStreamId     // Catch: java.lang.Throwable -> L10
            if (r4 >= r1) goto Ld
            r1 = r4 & 1
            r2 = 1
            if (r1 != r2) goto Ld
            goto Le
        Ld:
            r2 = 0
        Le:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L10
            return r2
        L10:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L10
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.mayHaveCreatedStream(int):boolean");
    }

    OkHttpClientStream getStream(int streamId) {
        OkHttpClientStream okHttpClientStream;
        synchronized (this.lock) {
            okHttpClientStream = this.streams.get(Integer.valueOf(streamId));
        }
        return okHttpClientStream;
    }

    static Status toGrpcStatus(ErrorCode code) {
        Status status = ERROR_CODE_TO_STATUS.get(code);
        return status != null ? status : Status.UNKNOWN.withDescription("Unknown http2 error code: " + code.httpCode);
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture<InternalChannelz.SocketStats> ret = SettableFuture.create();
        synchronized (this.lock) {
            if (this.socket == null) {
                ret.set(new InternalChannelz.SocketStats(this.transportTracer.getStats(), null, null, new InternalChannelz.SocketOptions.Builder().build(), null));
            } else {
                ret.set(new InternalChannelz.SocketStats(this.transportTracer.getStats(), this.socket.getLocalSocketAddress(), this.socket.getRemoteSocketAddress(), Utils.getSocketOptions(this.socket), this.securityInfo));
            }
        }
        return ret;
    }

    class ClientFrameHandler implements FrameReader.Handler, Runnable {
        FrameReader frameReader;
        private final OkHttpFrameLogger logger = new OkHttpFrameLogger(Level.FINE, (Class<?>) OkHttpClientTransport.class);
        boolean firstSettings = true;

        ClientFrameHandler(FrameReader frameReader) {
            this.frameReader = frameReader;
        }

        @Override // java.lang.Runnable
        public void run() {
            Status status;
            String threadName = Thread.currentThread().getName();
            Thread.currentThread().setName("OkHttpClientTransport");
            while (this.frameReader.nextFrame(this)) {
                try {
                    if (OkHttpClientTransport.this.keepAliveManager != null) {
                        OkHttpClientTransport.this.keepAliveManager.onDataReceived();
                    }
                } catch (Throwable t) {
                    try {
                        OkHttpClientTransport.this.startGoAway(0, ErrorCode.PROTOCOL_ERROR, Status.INTERNAL.withDescription("error in frame handler").withCause(t));
                    } finally {
                        try {
                            this.frameReader.close();
                        } catch (IOException ex) {
                            OkHttpClientTransport.log.log(Level.INFO, "Exception closing frame reader", (Throwable) ex);
                        }
                        OkHttpClientTransport.this.listener.transportTerminated();
                        Thread.currentThread().setName(threadName);
                    }
                }
            }
            synchronized (OkHttpClientTransport.this.lock) {
                status = OkHttpClientTransport.this.goAwayStatus;
            }
            if (status == null) {
                status = Status.UNAVAILABLE.withDescription("End of stream or IOException");
            }
            OkHttpClientTransport.this.startGoAway(0, ErrorCode.INTERNAL_ERROR, status);
            try {
                this.frameReader.close();
            } catch (IOException e) {
                ex = e;
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void data(boolean inFinished, int streamId, BufferedSource in, int length) throws IOException {
            this.logger.logData(OkHttpFrameLogger.Direction.INBOUND, streamId, in.getBuffer(), length, inFinished);
            OkHttpClientStream stream = OkHttpClientTransport.this.getStream(streamId);
            if (stream == null) {
                if (OkHttpClientTransport.this.mayHaveCreatedStream(streamId)) {
                    synchronized (OkHttpClientTransport.this.lock) {
                        OkHttpClientTransport.this.frameWriter.rstStream(streamId, ErrorCode.STREAM_CLOSED);
                    }
                    in.skip(length);
                } else {
                    OkHttpClientTransport.this.onError(ErrorCode.PROTOCOL_ERROR, "Received data for unknown stream: " + streamId);
                    return;
                }
            } else {
                in.require(length);
                Buffer buf = new Buffer();
                buf.write(in.getBuffer(), length);
                PerfMark.event("OkHttpClientTransport$ClientFrameHandler.data", stream.transportState().tag());
                synchronized (OkHttpClientTransport.this.lock) {
                    stream.transportState().transportDataReceived(buf, inFinished);
                }
            }
            OkHttpClientTransport.access$2412(OkHttpClientTransport.this, length);
            if (OkHttpClientTransport.this.connectionUnacknowledgedBytesRead >= OkHttpClientTransport.this.initialWindowSize * 0.5f) {
                synchronized (OkHttpClientTransport.this.lock) {
                    OkHttpClientTransport.this.frameWriter.windowUpdate(0, OkHttpClientTransport.this.connectionUnacknowledgedBytesRead);
                }
                OkHttpClientTransport.this.connectionUnacknowledgedBytesRead = 0;
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void headers(boolean outFinished, boolean inFinished, int streamId, int associatedStreamId, List<Header> headerBlock, HeadersMode headersMode) {
            int metadataSize;
            this.logger.logHeaders(OkHttpFrameLogger.Direction.INBOUND, streamId, headerBlock, inFinished);
            boolean unknownStream = false;
            Status failedStatus = null;
            if (OkHttpClientTransport.this.maxInboundMetadataSize != Integer.MAX_VALUE && (metadataSize = headerBlockSize(headerBlock)) > OkHttpClientTransport.this.maxInboundMetadataSize) {
                failedStatus = Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "Response %s metadata larger than %d: %d", inFinished ? "trailer" : "header", Integer.valueOf(OkHttpClientTransport.this.maxInboundMetadataSize), Integer.valueOf(metadataSize)));
            }
            synchronized (OkHttpClientTransport.this.lock) {
                OkHttpClientStream stream = (OkHttpClientStream) OkHttpClientTransport.this.streams.get(Integer.valueOf(streamId));
                if (stream == null) {
                    if (OkHttpClientTransport.this.mayHaveCreatedStream(streamId)) {
                        OkHttpClientTransport.this.frameWriter.rstStream(streamId, ErrorCode.STREAM_CLOSED);
                    } else {
                        unknownStream = true;
                    }
                } else if (failedStatus == null) {
                    PerfMark.event("OkHttpClientTransport$ClientFrameHandler.headers", stream.transportState().tag());
                    stream.transportState().transportHeadersReceived(headerBlock, inFinished);
                } else {
                    if (!inFinished) {
                        OkHttpClientTransport.this.frameWriter.rstStream(streamId, ErrorCode.CANCEL);
                    }
                    stream.transportState().transportReportStatus(failedStatus, false, new Metadata());
                }
            }
            if (unknownStream) {
                OkHttpClientTransport.this.onError(ErrorCode.PROTOCOL_ERROR, "Received header for unknown stream: " + streamId);
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
        public void rstStream(int streamId, ErrorCode errorCode) {
            this.logger.logRstStream(OkHttpFrameLogger.Direction.INBOUND, streamId, errorCode);
            Status status = OkHttpClientTransport.toGrpcStatus(errorCode).augmentDescription("Rst Stream");
            boolean stopDelivery = status.getCode() == Status.Code.CANCELLED || status.getCode() == Status.Code.DEADLINE_EXCEEDED;
            synchronized (OkHttpClientTransport.this.lock) {
                OkHttpClientStream stream = (OkHttpClientStream) OkHttpClientTransport.this.streams.get(Integer.valueOf(streamId));
                if (stream != null) {
                    PerfMark.event("OkHttpClientTransport$ClientFrameHandler.rstStream", stream.transportState().tag());
                    OkHttpClientTransport.this.finishStream(streamId, status, errorCode == ErrorCode.REFUSED_STREAM ? ClientStreamListener.RpcProgress.REFUSED : ClientStreamListener.RpcProgress.PROCESSED, stopDelivery, null, null);
                }
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void settings(boolean clearPrevious, Settings settings) {
            this.logger.logSettings(OkHttpFrameLogger.Direction.INBOUND, settings);
            boolean outboundWindowSizeIncreased = false;
            synchronized (OkHttpClientTransport.this.lock) {
                if (OkHttpSettingsUtil.isSet(settings, 4)) {
                    int receivedMaxConcurrentStreams = OkHttpSettingsUtil.get(settings, 4);
                    OkHttpClientTransport.this.maxConcurrentStreams = receivedMaxConcurrentStreams;
                }
                if (OkHttpSettingsUtil.isSet(settings, 7)) {
                    int initialWindowSize = OkHttpSettingsUtil.get(settings, 7);
                    outboundWindowSizeIncreased = OkHttpClientTransport.this.outboundFlow.initialOutboundWindowSize(initialWindowSize);
                }
                if (this.firstSettings) {
                    OkHttpClientTransport.this.listener.transportReady();
                    this.firstSettings = false;
                }
                OkHttpClientTransport.this.frameWriter.ackSettings(settings);
                if (outboundWindowSizeIncreased) {
                    OkHttpClientTransport.this.outboundFlow.writeStreams();
                }
                OkHttpClientTransport.this.startPendingStreams();
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void ping(boolean ack, int payload1, int payload2) {
            long ackPayload = (((long) payload1) << 32) | (((long) payload2) & 4294967295L);
            this.logger.logPing(OkHttpFrameLogger.Direction.INBOUND, ackPayload);
            if (!ack) {
                synchronized (OkHttpClientTransport.this.lock) {
                    OkHttpClientTransport.this.frameWriter.ping(true, payload1, payload2);
                }
                return;
            }
            Http2Ping p = null;
            synchronized (OkHttpClientTransport.this.lock) {
                if (OkHttpClientTransport.this.ping == null) {
                    OkHttpClientTransport.log.warning("Received unexpected ping ack. No ping outstanding");
                } else if (OkHttpClientTransport.this.ping.payload() == ackPayload) {
                    p = OkHttpClientTransport.this.ping;
                    OkHttpClientTransport.this.ping = null;
                } else {
                    OkHttpClientTransport.log.log(Level.WARNING, String.format(Locale.US, "Received unexpected ping ack. Expecting %d, got %d", Long.valueOf(OkHttpClientTransport.this.ping.payload()), Long.valueOf(ackPayload)));
                }
            }
            if (p != null) {
                p.complete();
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void ackSettings() {
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void goAway(int lastGoodStreamId, ErrorCode errorCode, ByteString debugData) {
            this.logger.logGoAway(OkHttpFrameLogger.Direction.INBOUND, lastGoodStreamId, errorCode, debugData);
            if (errorCode == ErrorCode.ENHANCE_YOUR_CALM) {
                String data = debugData.utf8();
                OkHttpClientTransport.log.log(Level.WARNING, String.format("%s: Received GOAWAY with ENHANCE_YOUR_CALM. Debug data: %s", this, data));
                if ("too_many_pings".equals(data)) {
                    OkHttpClientTransport.this.tooManyPingsRunnable.run();
                }
            }
            Status status = GrpcUtil.Http2Error.statusForCode(errorCode.httpCode).augmentDescription("Received Goaway");
            if (debugData.size() > 0) {
                status = status.augmentDescription(debugData.utf8());
            }
            OkHttpClientTransport.this.startGoAway(lastGoodStreamId, null, status);
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void pushPromise(int streamId, int promisedStreamId, List<Header> requestHeaders) throws IOException {
            this.logger.logPushPromise(OkHttpFrameLogger.Direction.INBOUND, streamId, promisedStreamId, requestHeaders);
            synchronized (OkHttpClientTransport.this.lock) {
                OkHttpClientTransport.this.frameWriter.rstStream(streamId, ErrorCode.PROTOCOL_ERROR);
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void windowUpdate(int streamId, long delta) {
            this.logger.logWindowsUpdate(OkHttpFrameLogger.Direction.INBOUND, streamId, delta);
            if (delta == 0) {
                if (streamId == 0) {
                    OkHttpClientTransport.this.onError(ErrorCode.PROTOCOL_ERROR, "Received 0 flow control window increment.");
                    return;
                } else {
                    OkHttpClientTransport.this.finishStream(streamId, Status.INTERNAL.withDescription("Received 0 flow control window increment."), ClientStreamListener.RpcProgress.PROCESSED, false, ErrorCode.PROTOCOL_ERROR, null);
                    return;
                }
            }
            boolean unknownStream = false;
            synchronized (OkHttpClientTransport.this.lock) {
                if (streamId == 0) {
                    OkHttpClientTransport.this.outboundFlow.windowUpdate(null, (int) delta);
                    return;
                }
                OkHttpClientStream stream = (OkHttpClientStream) OkHttpClientTransport.this.streams.get(Integer.valueOf(streamId));
                if (stream != null) {
                    OkHttpClientTransport.this.outboundFlow.windowUpdate(stream.transportState().getOutboundFlowState(), (int) delta);
                } else if (!OkHttpClientTransport.this.mayHaveCreatedStream(streamId)) {
                    unknownStream = true;
                }
                if (unknownStream) {
                    OkHttpClientTransport.this.onError(ErrorCode.PROTOCOL_ERROR, "Received window_update for unknown stream: " + streamId);
                }
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void priority(int streamId, int streamDependency, int weight, boolean exclusive) {
        }

        @Override // io.grpc.okhttp.internal.framed.FrameReader.Handler
        public void alternateService(int streamId, String origin, ByteString protocol, String host, int port, long maxAge) {
        }
    }
}
