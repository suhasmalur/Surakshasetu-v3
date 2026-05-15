package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.BinaryLog;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Deadline;
import io.grpc.Decompressor;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.InternalServerInterceptors;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.Server;
import io.grpc.ServerCall;
import io.grpc.ServerCallExecutorSupplier;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerTransportFilter;
import io.grpc.Status;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes10.dex */
public final class ServerImpl extends Server implements InternalInstrumented<InternalChannelz.ServerStats> {
    private final BinaryLog binlog;
    private final InternalChannelz channelz;
    private final CompressorRegistry compressorRegistry;
    private final DecompressorRegistry decompressorRegistry;
    private Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    private final ServerCallExecutorSupplier executorSupplier;
    private final HandlerRegistry fallbackRegistry;
    private final long handshakeTimeoutMillis;
    private final ServerInterceptor[] interceptors;
    private final HandlerRegistry registry;
    private final Context rootContext;
    private final CallTracer serverCallTracer;
    private boolean serverShutdownCallbackInvoked;
    private boolean shutdown;
    private Status shutdownNowStatus;
    private boolean started;
    private boolean terminated;
    private final Deadline.Ticker ticker;
    private final List<ServerTransportFilter> transportFilters;
    private final InternalServer transportServer;
    private boolean transportServersTerminated;
    private static final Logger log = Logger.getLogger(ServerImpl.class.getName());
    private static final ServerStreamListener NOOP_LISTENER = new NoopListener();
    private final Object lock = new Object();
    private final Set<ServerTransport> transports = new HashSet();
    private final InternalLogId logId = InternalLogId.allocate(HttpHeaders.SERVER, String.valueOf(getListenSocketsIgnoringLifecycle()));

    ServerImpl(ServerImplBuilder builder, InternalServer transportServer, Context rootContext) {
        this.executorPool = (ObjectPool) Preconditions.checkNotNull(builder.executorPool, "executorPool");
        this.registry = (HandlerRegistry) Preconditions.checkNotNull(builder.registryBuilder.build(), "registryBuilder");
        this.fallbackRegistry = (HandlerRegistry) Preconditions.checkNotNull(builder.fallbackRegistry, "fallbackRegistry");
        this.transportServer = (InternalServer) Preconditions.checkNotNull(transportServer, "transportServer");
        this.rootContext = ((Context) Preconditions.checkNotNull(rootContext, "rootContext")).fork();
        this.decompressorRegistry = builder.decompressorRegistry;
        this.compressorRegistry = builder.compressorRegistry;
        this.transportFilters = Collections.unmodifiableList(new ArrayList(builder.transportFilters));
        this.interceptors = (ServerInterceptor[]) builder.interceptors.toArray(new ServerInterceptor[builder.interceptors.size()]);
        this.handshakeTimeoutMillis = builder.handshakeTimeoutMillis;
        this.binlog = builder.binlog;
        this.channelz = builder.channelz;
        this.serverCallTracer = builder.callTracerFactory.create();
        this.ticker = (Deadline.Ticker) Preconditions.checkNotNull(builder.ticker, "ticker");
        this.channelz.addServer(this);
        this.executorSupplier = builder.executorSupplier;
    }

    @Override // io.grpc.Server
    public ServerImpl start() throws IOException {
        synchronized (this.lock) {
            Preconditions.checkState(!this.started, "Already started");
            Preconditions.checkState(this.shutdown ? false : true, "Shutting down");
            ServerListenerImpl listener = new ServerListenerImpl();
            this.transportServer.start(listener);
            this.executor = (Executor) Preconditions.checkNotNull(this.executorPool.getObject(), "executor");
            this.started = true;
        }
        return this;
    }

    @Override // io.grpc.Server
    public int getPort() {
        synchronized (this.lock) {
            Preconditions.checkState(this.started, "Not started");
            Preconditions.checkState(!this.terminated, "Already terminated");
            for (SocketAddress addr : this.transportServer.getListenSocketAddresses()) {
                if (addr instanceof InetSocketAddress) {
                    return ((InetSocketAddress) addr).getPort();
                }
            }
            return -1;
        }
    }

    @Override // io.grpc.Server
    public List<SocketAddress> getListenSockets() {
        List<SocketAddress> listenSocketsIgnoringLifecycle;
        synchronized (this.lock) {
            Preconditions.checkState(this.started, "Not started");
            Preconditions.checkState(!this.terminated, "Already terminated");
            listenSocketsIgnoringLifecycle = getListenSocketsIgnoringLifecycle();
        }
        return listenSocketsIgnoringLifecycle;
    }

    private List<SocketAddress> getListenSocketsIgnoringLifecycle() {
        List<SocketAddress> listUnmodifiableList;
        synchronized (this.lock) {
            listUnmodifiableList = Collections.unmodifiableList(this.transportServer.getListenSocketAddresses());
        }
        return listUnmodifiableList;
    }

    @Override // io.grpc.Server
    public List<ServerServiceDefinition> getServices() {
        List<ServerServiceDefinition> fallbackServices = this.fallbackRegistry.getServices();
        if (fallbackServices.isEmpty()) {
            return this.registry.getServices();
        }
        List<ServerServiceDefinition> registryServices = this.registry.getServices();
        int servicesCount = registryServices.size() + fallbackServices.size();
        List<ServerServiceDefinition> services = new ArrayList<>(servicesCount);
        services.addAll(registryServices);
        services.addAll(fallbackServices);
        return Collections.unmodifiableList(services);
    }

    @Override // io.grpc.Server
    public List<ServerServiceDefinition> getImmutableServices() {
        return this.registry.getServices();
    }

    @Override // io.grpc.Server
    public List<ServerServiceDefinition> getMutableServices() {
        return Collections.unmodifiableList(this.fallbackRegistry.getServices());
    }

    @Override // io.grpc.Server
    public ServerImpl shutdown() {
        synchronized (this.lock) {
            if (this.shutdown) {
                return this;
            }
            this.shutdown = true;
            boolean shutdownTransportServers = this.started;
            if (!shutdownTransportServers) {
                this.transportServersTerminated = true;
                checkForTermination();
            }
            if (shutdownTransportServers) {
                this.transportServer.shutdown();
            }
            return this;
        }
    }

    @Override // io.grpc.Server
    public ServerImpl shutdownNow() {
        shutdown();
        Status nowStatus = Status.UNAVAILABLE.withDescription("Server shutdownNow invoked");
        synchronized (this.lock) {
            if (this.shutdownNowStatus != null) {
                return this;
            }
            this.shutdownNowStatus = nowStatus;
            Collection<ServerTransport> transportsCopy = new ArrayList<>(this.transports);
            boolean savedServerShutdownCallbackInvoked = this.serverShutdownCallbackInvoked;
            if (savedServerShutdownCallbackInvoked) {
                for (ServerTransport transport : transportsCopy) {
                    transport.shutdownNow(nowStatus);
                }
            }
            return this;
        }
    }

    @Override // io.grpc.Server
    public boolean isShutdown() {
        boolean z;
        synchronized (this.lock) {
            z = this.shutdown;
        }
        return z;
    }

    @Override // io.grpc.Server
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        boolean z;
        synchronized (this.lock) {
            long endTimeNanos = System.nanoTime() + unit.toNanos(timeout);
            while (!this.terminated) {
                long timeoutNanos = endTimeNanos - System.nanoTime();
                if (timeoutNanos <= 0) {
                    break;
                }
                TimeUnit.NANOSECONDS.timedWait(this.lock, timeoutNanos);
            }
            z = this.terminated;
        }
        return z;
    }

    @Override // io.grpc.Server
    public void awaitTermination() throws InterruptedException {
        synchronized (this.lock) {
            while (!this.terminated) {
                this.lock.wait();
            }
        }
    }

    @Override // io.grpc.Server
    public boolean isTerminated() {
        boolean z;
        synchronized (this.lock) {
            z = this.terminated;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transportClosed(ServerTransport transport) {
        synchronized (this.lock) {
            if (!this.transports.remove(transport)) {
                throw new AssertionError("Transport already removed");
            }
            this.channelz.removeServerSocket(this, transport);
            checkForTermination();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkForTermination() {
        synchronized (this.lock) {
            if (this.shutdown && this.transports.isEmpty() && this.transportServersTerminated) {
                if (this.terminated) {
                    throw new AssertionError("Server already terminated");
                }
                this.terminated = true;
                this.channelz.removeServer(this);
                if (this.executor != null) {
                    this.executor = this.executorPool.returnObject(this.executor);
                }
                this.lock.notifyAll();
            }
        }
    }

    private final class ServerListenerImpl implements ServerListener {
        private ServerListenerImpl() {
        }

        @Override // io.grpc.internal.ServerListener
        public ServerTransportListener transportCreated(ServerTransport transport) {
            synchronized (ServerImpl.this.lock) {
                ServerImpl.this.transports.add(transport);
            }
            ServerTransportListenerImpl stli = ServerImpl.this.new ServerTransportListenerImpl(transport);
            stli.init();
            return stli;
        }

        @Override // io.grpc.internal.ServerListener
        public void serverShutdown() {
            synchronized (ServerImpl.this.lock) {
                if (ServerImpl.this.serverShutdownCallbackInvoked) {
                    return;
                }
                ArrayList<ServerTransport> copiedTransports = new ArrayList<>(ServerImpl.this.transports);
                Status shutdownNowStatusCopy = ServerImpl.this.shutdownNowStatus;
                ServerImpl.this.serverShutdownCallbackInvoked = true;
                for (ServerTransport transport : copiedTransports) {
                    if (shutdownNowStatusCopy == null) {
                        transport.shutdown();
                    } else {
                        transport.shutdownNow(shutdownNowStatusCopy);
                    }
                }
                synchronized (ServerImpl.this.lock) {
                    ServerImpl.this.transportServersTerminated = true;
                    ServerImpl.this.checkForTermination();
                }
            }
        }
    }

    private final class ServerTransportListenerImpl implements ServerTransportListener {
        private Attributes attributes;
        private Future<?> handshakeTimeoutFuture;
        private final ServerTransport transport;

        ServerTransportListenerImpl(ServerTransport transport) {
            this.transport = transport;
        }

        public void init() {
            if (ServerImpl.this.handshakeTimeoutMillis != Long.MAX_VALUE) {
                this.handshakeTimeoutFuture = this.transport.getScheduledExecutorService().schedule(new Runnable() { // from class: io.grpc.internal.ServerImpl.ServerTransportListenerImpl.1TransportShutdownNow
                    @Override // java.lang.Runnable
                    public void run() {
                        ServerTransportListenerImpl.this.transport.shutdownNow(Status.CANCELLED.withDescription("Handshake timeout exceeded"));
                    }
                }, ServerImpl.this.handshakeTimeoutMillis, TimeUnit.MILLISECONDS);
            } else {
                this.handshakeTimeoutFuture = new FutureTask(new Runnable() { // from class: io.grpc.internal.ServerImpl.ServerTransportListenerImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                    }
                }, null);
            }
            ServerImpl.this.channelz.addServerSocket(ServerImpl.this, this.transport);
        }

        @Override // io.grpc.internal.ServerTransportListener
        public Attributes transportReady(Attributes attributes) {
            this.handshakeTimeoutFuture.cancel(false);
            this.handshakeTimeoutFuture = null;
            for (ServerTransportFilter filter : ServerImpl.this.transportFilters) {
                attributes = (Attributes) Preconditions.checkNotNull(filter.transportReady(attributes), "Filter %s returned null", filter);
            }
            this.attributes = attributes;
            return attributes;
        }

        @Override // io.grpc.internal.ServerTransportListener
        public void transportTerminated() {
            if (this.handshakeTimeoutFuture != null) {
                this.handshakeTimeoutFuture.cancel(false);
                this.handshakeTimeoutFuture = null;
            }
            for (ServerTransportFilter filter : ServerImpl.this.transportFilters) {
                filter.transportTerminated(this.attributes);
            }
            ServerImpl.this.transportClosed(this.transport);
        }

        @Override // io.grpc.internal.ServerTransportListener
        public void streamCreated(ServerStream stream, String methodName, Metadata headers) {
            Tag tag = PerfMark.createTag(methodName, stream.streamId());
            PerfMark.startTask("ServerTransportListener.streamCreated", tag);
            try {
                streamCreatedInternal(stream, methodName, headers, tag);
            } finally {
                PerfMark.stopTask("ServerTransportListener.streamCreated", tag);
            }
        }

        private void streamCreatedInternal(ServerStream stream, String methodName, Metadata headers, Tag tag) {
            Executor wrappedExecutor;
            if (ServerImpl.this.executorSupplier != null || ServerImpl.this.executor != MoreExecutors.directExecutor()) {
                wrappedExecutor = new SerializingExecutor(ServerImpl.this.executor);
            } else {
                Executor wrappedExecutor2 = new SerializeReentrantCallsDirectExecutor();
                stream.optimizeForDirectExecutor();
                wrappedExecutor = wrappedExecutor2;
            }
            if (headers.containsKey(GrpcUtil.MESSAGE_ENCODING_KEY)) {
                String encoding = (String) headers.get(GrpcUtil.MESSAGE_ENCODING_KEY);
                Decompressor decompressor = ServerImpl.this.decompressorRegistry.lookupDecompressor(encoding);
                if (decompressor == null) {
                    stream.setListener(ServerImpl.NOOP_LISTENER);
                    stream.close(Status.UNIMPLEMENTED.withDescription(String.format("Can't find decompressor for %s", encoding)), new Metadata());
                    return;
                }
                stream.setDecompressor(decompressor);
            }
            StatsTraceContext statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(stream.statsTraceContext(), "statsTraceCtx not present from stream");
            Context.CancellableContext context = createContext(headers, statsTraceCtx);
            Link link = PerfMark.linkOut();
            JumpToApplicationThreadServerStreamListener jumpListener = new JumpToApplicationThreadServerStreamListener(wrappedExecutor, ServerImpl.this.executor, stream, context, tag);
            stream.setListener(jumpListener);
            SettableFuture<ServerCallParameters<?, ?>> future = SettableFuture.create();
            wrappedExecutor.execute(new ContextRunnable(context, tag, link, methodName, stream, jumpListener, future, statsTraceCtx, headers, wrappedExecutor) { // from class: io.grpc.internal.ServerImpl.ServerTransportListenerImpl.1MethodLookup
                final /* synthetic */ Context.CancellableContext val$context;
                final /* synthetic */ SettableFuture val$future;
                final /* synthetic */ Metadata val$headers;
                final /* synthetic */ JumpToApplicationThreadServerStreamListener val$jumpListener;
                final /* synthetic */ Link val$link;
                final /* synthetic */ String val$methodName;
                final /* synthetic */ StatsTraceContext val$statsTraceCtx;
                final /* synthetic */ ServerStream val$stream;
                final /* synthetic */ Tag val$tag;
                final /* synthetic */ Executor val$wrappedExecutor;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(context);
                    this.val$context = context;
                    this.val$tag = tag;
                    this.val$link = link;
                    this.val$methodName = methodName;
                    this.val$stream = stream;
                    this.val$jumpListener = jumpListener;
                    this.val$future = future;
                    this.val$statsTraceCtx = statsTraceCtx;
                    this.val$headers = headers;
                    this.val$wrappedExecutor = wrappedExecutor;
                }

                @Override // io.grpc.internal.ContextRunnable
                public void runInContext() {
                    PerfMark.startTask("ServerTransportListener$MethodLookup.startCall", this.val$tag);
                    PerfMark.linkIn(this.val$link);
                    try {
                        runInternal();
                    } finally {
                        PerfMark.stopTask("ServerTransportListener$MethodLookup.startCall", this.val$tag);
                    }
                }

                private void runInternal() {
                    try {
                        ServerMethodDefinition<?, ?> method = ServerImpl.this.registry.lookupMethod(this.val$methodName);
                        if (method == null) {
                            method = ServerImpl.this.fallbackRegistry.lookupMethod(this.val$methodName, this.val$stream.getAuthority());
                        }
                        if (method != null) {
                            ServerMethodDefinition<?, ?> wrapMethod = ServerTransportListenerImpl.this.wrapMethod(this.val$stream, method, this.val$statsTraceCtx);
                            ServerCallParameters<?, ?> callParams = maySwitchExecutor(wrapMethod, this.val$stream, this.val$headers, this.val$context, this.val$tag);
                            this.val$future.set(callParams);
                        } else {
                            Status status = Status.UNIMPLEMENTED.withDescription("Method not found: " + this.val$methodName);
                            this.val$jumpListener.setListener(ServerImpl.NOOP_LISTENER);
                            this.val$stream.close(status, new Metadata());
                            this.val$context.cancel(null);
                            this.val$future.cancel(false);
                        }
                    } catch (Throwable t) {
                        this.val$jumpListener.setListener(ServerImpl.NOOP_LISTENER);
                        this.val$stream.close(Status.fromThrowable(t), new Metadata());
                        this.val$context.cancel(null);
                        this.val$future.cancel(false);
                        throw t;
                    }
                }

                private <ReqT, RespT> ServerCallParameters<ReqT, RespT> maySwitchExecutor(ServerMethodDefinition<ReqT, RespT> methodDef, ServerStream stream2, Metadata headers2, Context.CancellableContext context2, Tag tag2) {
                    Executor switchingExecutor;
                    ServerCallImpl<ReqT, RespT> call = new ServerCallImpl<>(stream2, methodDef.getMethodDescriptor(), headers2, context2, ServerImpl.this.decompressorRegistry, ServerImpl.this.compressorRegistry, ServerImpl.this.serverCallTracer, tag2);
                    if (ServerImpl.this.executorSupplier != null && (switchingExecutor = ServerImpl.this.executorSupplier.getExecutor(call, headers2)) != null) {
                        ((SerializingExecutor) this.val$wrappedExecutor).setExecutor(switchingExecutor);
                    }
                    return ServerTransportListenerImpl.this.new ServerCallParameters<>(call, methodDef.getServerCallHandler());
                }
            });
            wrappedExecutor.execute(new ContextRunnable(context, tag, link, future, methodName, headers, stream, jumpListener) { // from class: io.grpc.internal.ServerImpl.ServerTransportListenerImpl.1HandleServerCall
                final /* synthetic */ Context.CancellableContext val$context;
                final /* synthetic */ SettableFuture val$future;
                final /* synthetic */ Metadata val$headers;
                final /* synthetic */ JumpToApplicationThreadServerStreamListener val$jumpListener;
                final /* synthetic */ Link val$link;
                final /* synthetic */ String val$methodName;
                final /* synthetic */ ServerStream val$stream;
                final /* synthetic */ Tag val$tag;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(context);
                    this.val$context = context;
                    this.val$tag = tag;
                    this.val$link = link;
                    this.val$future = future;
                    this.val$methodName = methodName;
                    this.val$headers = headers;
                    this.val$stream = stream;
                    this.val$jumpListener = jumpListener;
                }

                @Override // io.grpc.internal.ContextRunnable
                public void runInContext() {
                    PerfMark.startTask("ServerTransportListener$HandleServerCall.startCall", this.val$tag);
                    PerfMark.linkIn(this.val$link);
                    try {
                        runInternal();
                    } finally {
                        PerfMark.stopTask("ServerTransportListener$HandleServerCall.startCall", this.val$tag);
                    }
                }

                private void runInternal() {
                    ServerStreamListener listener = ServerImpl.NOOP_LISTENER;
                    if (!this.val$future.isCancelled()) {
                        try {
                            ServerStreamListener listener2 = ServerTransportListenerImpl.this.startWrappedCall(this.val$methodName, (ServerCallParameters) Futures.getDone(this.val$future), this.val$headers);
                            this.val$jumpListener.setListener(listener2);
                            this.val$context.addListener(new Context.CancellationListener() { // from class: io.grpc.internal.ServerImpl.ServerTransportListenerImpl.1HandleServerCall.1ServerStreamCancellationListener
                                @Override // io.grpc.Context.CancellationListener
                                public void cancelled(Context context2) {
                                    Status status = Contexts.statusFromCancelled(context2);
                                    if (Status.DEADLINE_EXCEEDED.getCode().equals(status.getCode())) {
                                        C1HandleServerCall.this.val$stream.cancel(status);
                                    }
                                }
                            }, MoreExecutors.directExecutor());
                        } finally {
                        }
                    }
                }
            });
        }

        private Context.CancellableContext createContext(Metadata headers, StatsTraceContext statsTraceCtx) {
            Long timeoutNanos = (Long) headers.get(GrpcUtil.TIMEOUT_KEY);
            Context baseContext = statsTraceCtx.serverFilterContext(ServerImpl.this.rootContext).withValue(io.grpc.InternalServer.SERVER_CONTEXT_KEY, ServerImpl.this);
            if (timeoutNanos == null) {
                return baseContext.withCancellation();
            }
            Context.CancellableContext context = baseContext.withDeadline(Deadline.after(timeoutNanos.longValue(), TimeUnit.NANOSECONDS, ServerImpl.this.ticker), this.transport.getScheduledExecutorService());
            return context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <ReqT, RespT> ServerMethodDefinition<?, ?> wrapMethod(ServerStream serverStream, ServerMethodDefinition<ReqT, RespT> serverMethodDefinition, StatsTraceContext statsTraceContext) {
            statsTraceContext.serverCallStarted(new ServerCallInfoImpl(serverMethodDefinition.getMethodDescriptor(), serverStream.getAttributes(), serverStream.getAuthority()));
            ServerCallHandler<ReqT, RespT> serverCallHandler = serverMethodDefinition.getServerCallHandler();
            for (ServerInterceptor serverInterceptor : ServerImpl.this.interceptors) {
                serverCallHandler = InternalServerInterceptors.interceptCallHandlerCreate(serverInterceptor, serverCallHandler);
            }
            ServerMethodDefinition<ReqT, RespT> serverMethodDefinitionWithServerCallHandler = serverMethodDefinition.withServerCallHandler(serverCallHandler);
            if (ServerImpl.this.binlog != null) {
                return ServerImpl.this.binlog.wrapMethodDefinition(serverMethodDefinitionWithServerCallHandler);
            }
            return serverMethodDefinitionWithServerCallHandler;
        }

        private final class ServerCallParameters<ReqT, RespT> {
            ServerCallImpl<ReqT, RespT> call;
            ServerCallHandler<ReqT, RespT> callHandler;

            public ServerCallParameters(ServerCallImpl<ReqT, RespT> call, ServerCallHandler<ReqT, RespT> callHandler) {
                this.call = call;
                this.callHandler = callHandler;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <WReqT, WRespT> ServerStreamListener startWrappedCall(String fullMethodName, ServerCallParameters<WReqT, WRespT> params, Metadata headers) {
            ServerCall.Listener<WReqT> callListener = params.callHandler.startCall(params.call, headers);
            if (callListener == null) {
                throw new NullPointerException("startCall() returned a null listener for method " + fullMethodName);
            }
            return params.call.newServerStreamListener(callListener);
        }
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.ServerStats> getStats() {
        InternalChannelz.ServerStats.Builder builder = new InternalChannelz.ServerStats.Builder();
        List<InternalInstrumented<InternalChannelz.SocketStats>> stats = this.transportServer.getListenSocketStatsList();
        if (stats != null) {
            builder.addListenSockets(stats);
        }
        this.serverCallTracer.updateBuilder(builder);
        SettableFuture<InternalChannelz.ServerStats> ret = SettableFuture.create();
        ret.set(builder.build());
        return ret;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("transportServer", this.transportServer).toString();
    }

    private static final class NoopListener implements ServerStreamListener {
        private NoopListener() {
        }

        @Override // io.grpc.internal.StreamListener
        public void messagesAvailable(StreamListener.MessageProducer producer) {
            while (true) {
                InputStream message = producer.next();
                if (message != null) {
                    try {
                        message.close();
                    } catch (IOException e) {
                        while (true) {
                            InputStream message2 = producer.next();
                            if (message2 != null) {
                                try {
                                    message2.close();
                                } catch (IOException ioException) {
                                    ServerImpl.log.log(Level.WARNING, "Exception closing stream", (Throwable) ioException);
                                }
                            } else {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }

        @Override // io.grpc.internal.ServerStreamListener
        public void halfClosed() {
        }

        @Override // io.grpc.internal.ServerStreamListener
        public void closed(Status status) {
        }

        @Override // io.grpc.internal.StreamListener
        public void onReady() {
        }
    }

    static final class JumpToApplicationThreadServerStreamListener implements ServerStreamListener {
        private final Executor callExecutor;
        private final Executor cancelExecutor;
        private final Context.CancellableContext context;
        private ServerStreamListener listener;
        private final ServerStream stream;
        private final Tag tag;

        public JumpToApplicationThreadServerStreamListener(Executor executor, Executor cancelExecutor, ServerStream stream, Context.CancellableContext context, Tag tag) {
            this.callExecutor = executor;
            this.cancelExecutor = cancelExecutor;
            this.stream = stream;
            this.context = context;
            this.tag = tag;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ServerStreamListener getListener() {
            if (this.listener == null) {
                throw new IllegalStateException("listener unset");
            }
            return this.listener;
        }

        void setListener(ServerStreamListener listener) {
            Preconditions.checkNotNull(listener, "listener must not be null");
            Preconditions.checkState(this.listener == null, "Listener already set");
            this.listener = listener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void internalClose(Throwable t) {
            this.stream.close(Status.UNKNOWN.withCause(t), new Metadata());
        }

        @Override // io.grpc.internal.StreamListener
        public void messagesAvailable(final StreamListener.MessageProducer producer) {
            PerfMark.startTask("ServerStreamListener.messagesAvailable", this.tag);
            final Link link = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ServerImpl.JumpToApplicationThreadServerStreamListener.1MessagesAvailable
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(JumpToApplicationThreadServerStreamListener.this.context);
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).messagesAvailable", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(link);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().messagesAvailable(producer);
                        } finally {
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.messagesAvailable", this.tag);
            }
        }

        @Override // io.grpc.internal.ServerStreamListener
        public void halfClosed() {
            PerfMark.startTask("ServerStreamListener.halfClosed", this.tag);
            final Link link = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ServerImpl.JumpToApplicationThreadServerStreamListener.1HalfClosed
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(JumpToApplicationThreadServerStreamListener.this.context);
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).halfClosed", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(link);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().halfClosed();
                        } finally {
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.halfClosed", this.tag);
            }
        }

        @Override // io.grpc.internal.ServerStreamListener
        public void closed(Status status) {
            PerfMark.startTask("ServerStreamListener.closed", this.tag);
            try {
                closedInternal(status);
            } finally {
                PerfMark.stopTask("ServerStreamListener.closed", this.tag);
            }
        }

        private void closedInternal(final Status status) {
            if (!status.isOk()) {
                Throwable cancelCause = status.getCause();
                if (cancelCause == null) {
                    cancelCause = InternalStatus.asRuntimeException(Status.CANCELLED.withDescription("RPC cancelled"), null, false);
                }
                this.cancelExecutor.execute(new ContextCloser(this.context, cancelCause));
            }
            final Link link = PerfMark.linkOut();
            this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ServerImpl.JumpToApplicationThreadServerStreamListener.1Closed
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(JumpToApplicationThreadServerStreamListener.this.context);
                }

                @Override // io.grpc.internal.ContextRunnable
                public void runInContext() {
                    PerfMark.startTask("ServerCallListener(app).closed", JumpToApplicationThreadServerStreamListener.this.tag);
                    PerfMark.linkIn(link);
                    try {
                        JumpToApplicationThreadServerStreamListener.this.getListener().closed(status);
                    } finally {
                        PerfMark.stopTask("ServerCallListener(app).closed", JumpToApplicationThreadServerStreamListener.this.tag);
                    }
                }
            });
        }

        @Override // io.grpc.internal.StreamListener
        public void onReady() {
            PerfMark.startTask("ServerStreamListener.onReady", this.tag);
            final Link link = PerfMark.linkOut();
            try {
                this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ServerImpl.JumpToApplicationThreadServerStreamListener.1OnReady
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(JumpToApplicationThreadServerStreamListener.this.context);
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public void runInContext() {
                        PerfMark.startTask("ServerCallListener(app).onReady", JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(link);
                        try {
                            JumpToApplicationThreadServerStreamListener.this.getListener().onReady();
                        } finally {
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ServerStreamListener.onReady", this.tag);
            }
        }
    }

    static final class ContextCloser implements Runnable {
        private final Throwable cause;
        private final Context.CancellableContext context;

        ContextCloser(Context.CancellableContext context, Throwable cause) {
            this.context = context;
            this.cause = cause;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.context.cancel(this.cause);
        }
    }
}
