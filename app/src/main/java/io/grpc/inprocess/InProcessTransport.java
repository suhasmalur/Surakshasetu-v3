package io.grpc.inprocess;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.Decompressor;
import io.grpc.DecompressorRegistry;
import io.grpc.Grpc;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.InsightBuilder;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.NoopClientStream;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerListener;
import io.grpc.internal.ServerStream;
import io.grpc.internal.ServerStreamListener;
import io.grpc.internal.ServerTransport;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.lang.Thread;
import java.net.SocketAddress;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
final class InProcessTransport implements ServerTransport, ConnectionClientTransport {
    private static final Logger log = Logger.getLogger(InProcessTransport.class.getName());
    private final SocketAddress address;
    private final Attributes attributes;
    private final String authority;
    private final int clientMaxInboundMetadataSize;
    private ManagedClientTransport.Listener clientTransportListener;
    private final InUseStateAggregator<InProcessStream> inUseState;
    private final boolean includeCauseWithStatus;
    private final InternalLogId logId;
    private final Optional<ServerListener> optionalServerListener;
    private int serverMaxInboundMetadataSize;
    private ScheduledExecutorService serverScheduler;
    private ObjectPool<ScheduledExecutorService> serverSchedulerPool;
    private Attributes serverStreamAttributes;
    private List<ServerStreamTracer.Factory> serverStreamTracerFactories;
    private ServerTransportListener serverTransportListener;
    private boolean shutdown;
    private Status shutdownStatus;
    private final Set<InProcessStream> streams;
    private boolean terminated;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private final String userAgent;

    private InProcessTransport(SocketAddress address, int maxInboundMetadataSize, String authority, String userAgent, Attributes eagAttrs, Optional<ServerListener> optionalServerListener, boolean includeCauseWithStatus) {
        this.streams = Collections.newSetFromMap(new IdentityHashMap());
        this.uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() { // from class: io.grpc.inprocess.InProcessTransport.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread t, Throwable e) {
                if (e instanceof Error) {
                    throw new Error(e);
                }
                throw new RuntimeException(e);
            }
        };
        this.inUseState = new InUseStateAggregator<InProcessStream>() { // from class: io.grpc.inprocess.InProcessTransport.2
            @Override // io.grpc.internal.InUseStateAggregator
            protected void handleInUse() {
                InProcessTransport.this.clientTransportListener.transportInUse(true);
            }

            @Override // io.grpc.internal.InUseStateAggregator
            protected void handleNotInUse() {
                InProcessTransport.this.clientTransportListener.transportInUse(false);
            }
        };
        this.address = address;
        this.clientMaxInboundMetadataSize = maxInboundMetadataSize;
        this.authority = authority;
        this.userAgent = GrpcUtil.getGrpcUserAgent("inprocess", userAgent);
        Preconditions.checkNotNull(eagAttrs, "eagAttrs");
        this.attributes = Attributes.newBuilder().set(GrpcAttributes.ATTR_SECURITY_LEVEL, SecurityLevel.PRIVACY_AND_INTEGRITY).set(GrpcAttributes.ATTR_CLIENT_EAG_ATTRS, eagAttrs).set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, address).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, address).build();
        this.optionalServerListener = optionalServerListener;
        this.logId = InternalLogId.allocate(getClass(), address.toString());
        this.includeCauseWithStatus = includeCauseWithStatus;
    }

    public InProcessTransport(SocketAddress address, int maxInboundMetadataSize, String authority, String userAgent, Attributes eagAttrs, boolean includeCauseWithStatus) {
        this(address, maxInboundMetadataSize, authority, userAgent, eagAttrs, Optional.absent(), includeCauseWithStatus);
    }

    InProcessTransport(String name, int maxInboundMetadataSize, String authority, String userAgent, Attributes eagAttrs, ObjectPool<ScheduledExecutorService> serverSchedulerPool, List<ServerStreamTracer.Factory> serverStreamTracerFactories, ServerListener serverListener, boolean includeCauseWithStatus) {
        this(new InProcessSocketAddress(name), maxInboundMetadataSize, authority, userAgent, eagAttrs, Optional.of(serverListener), includeCauseWithStatus);
        this.serverMaxInboundMetadataSize = maxInboundMetadataSize;
        this.serverSchedulerPool = serverSchedulerPool;
        this.serverStreamTracerFactories = serverStreamTracerFactories;
    }

    @Override // io.grpc.internal.ManagedClientTransport
    @CheckReturnValue
    public synchronized Runnable start(ManagedClientTransport.Listener listener) {
        this.clientTransportListener = listener;
        if (this.optionalServerListener.isPresent()) {
            this.serverScheduler = this.serverSchedulerPool.getObject();
            this.serverTransportListener = this.optionalServerListener.get().transportCreated(this);
        } else {
            InProcessServer server = InProcessServer.findServer(this.address);
            if (server != null) {
                this.serverMaxInboundMetadataSize = server.getMaxInboundMetadataSize();
                this.serverSchedulerPool = server.getScheduledExecutorServicePool();
                this.serverScheduler = this.serverSchedulerPool.getObject();
                this.serverStreamTracerFactories = server.getStreamTracerFactories();
                this.serverTransportListener = server.register(this);
            }
        }
        if (this.serverTransportListener == null) {
            this.shutdownStatus = Status.UNAVAILABLE.withDescription("Could not find server: " + this.address);
            final Status localShutdownStatus = this.shutdownStatus;
            return new Runnable() { // from class: io.grpc.inprocess.InProcessTransport.3
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (InProcessTransport.this) {
                        InProcessTransport.this.notifyShutdown(localShutdownStatus);
                        InProcessTransport.this.notifyTerminated();
                    }
                }
            };
        }
        return new Runnable() { // from class: io.grpc.inprocess.InProcessTransport.4
            @Override // java.lang.Runnable
            public void run() {
                synchronized (InProcessTransport.this) {
                    Attributes serverTransportAttrs = Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, InProcessTransport.this.address).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, InProcessTransport.this.address).build();
                    InProcessTransport.this.serverStreamAttributes = InProcessTransport.this.serverTransportListener.transportReady(serverTransportAttrs);
                    InProcessTransport.this.clientTransportListener.transportReady();
                }
            }
        };
    }

    @Override // io.grpc.internal.ClientTransport
    public synchronized ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions, ClientStreamTracer[] tracers) {
        int metadataSize;
        StatsTraceContext statsTraceContext = StatsTraceContext.newClientContext(tracers, getAttributes(), headers);
        if (this.shutdownStatus != null) {
            return failedClientStream(statsTraceContext, this.shutdownStatus);
        }
        headers.put(GrpcUtil.USER_AGENT_KEY, this.userAgent);
        if (this.serverMaxInboundMetadataSize != Integer.MAX_VALUE && (metadataSize = metadataSize(headers)) > this.serverMaxInboundMetadataSize) {
            Status status = Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "Request metadata larger than %d: %d", Integer.valueOf(this.serverMaxInboundMetadataSize), Integer.valueOf(metadataSize)));
            return failedClientStream(statsTraceContext, status);
        }
        return new InProcessStream(method, headers, callOptions, this.authority, statsTraceContext).clientStream;
    }

    private ClientStream failedClientStream(final StatsTraceContext statsTraceCtx, final Status status) {
        return new NoopClientStream() { // from class: io.grpc.inprocess.InProcessTransport.5
            @Override // io.grpc.internal.NoopClientStream, io.grpc.internal.ClientStream
            public void start(ClientStreamListener listener) {
                statsTraceCtx.clientOutboundHeaders();
                statsTraceCtx.streamClosed(status);
                listener.closed(status, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
            }
        };
    }

    @Override // io.grpc.internal.ClientTransport
    public synchronized void ping(final ClientTransport.PingCallback callback, Executor executor) {
        if (this.terminated) {
            final Status shutdownStatus = this.shutdownStatus;
            executor.execute(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport.6
                @Override // java.lang.Runnable
                public void run() {
                    callback.onFailure(shutdownStatus.asRuntimeException());
                }
            });
        } else {
            executor.execute(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport.7
                @Override // java.lang.Runnable
                public void run() {
                    callback.onSuccess(0L);
                }
            });
        }
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public synchronized void shutdown(Status reason) {
        if (this.shutdown) {
            return;
        }
        this.shutdownStatus = reason;
        notifyShutdown(reason);
        if (this.streams.isEmpty()) {
            notifyTerminated();
        }
    }

    @Override // io.grpc.internal.ServerTransport
    public synchronized void shutdown() {
        shutdown(Status.UNAVAILABLE.withDescription("InProcessTransport shutdown by the server-side"));
    }

    @Override // io.grpc.internal.ServerTransport, io.grpc.internal.ManagedClientTransport
    public void shutdownNow(Status reason) {
        Preconditions.checkNotNull(reason, "reason");
        synchronized (this) {
            shutdown(reason);
            if (this.terminated) {
                return;
            }
            List<InProcessStream> streamsCopy = new ArrayList<>(this.streams);
            for (InProcessStream stream : streamsCopy) {
                stream.clientStream.cancel(reason);
            }
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("address", this.address).toString();
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    @Override // io.grpc.internal.ConnectionClientTransport
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override // io.grpc.internal.ServerTransport
    public ScheduledExecutorService getScheduledExecutorService() {
        return this.serverScheduler;
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture<InternalChannelz.SocketStats> ret = SettableFuture.create();
        ret.set(null);
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void notifyShutdown(Status s) {
        if (this.shutdown) {
            return;
        }
        this.shutdown = true;
        this.clientTransportListener.transportShutdown(s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void notifyTerminated() {
        if (this.terminated) {
            return;
        }
        this.terminated = true;
        if (this.serverScheduler != null) {
            this.serverScheduler = this.serverSchedulerPool.returnObject(this.serverScheduler);
        }
        this.clientTransportListener.transportTerminated();
        if (this.serverTransportListener != null) {
            this.serverTransportListener.transportTerminated();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int metadataSize(Metadata metadata) {
        byte[][] serialized = InternalMetadata.serialize(metadata);
        if (serialized == null) {
            return 0;
        }
        long size = 0;
        for (int i = 0; i < serialized.length; i += 2) {
            size += (long) (serialized[i].length + 32 + serialized[i + 1].length);
        }
        return (int) Math.min(size, 2147483647L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class InProcessStream {
        private volatile String authority;
        private final CallOptions callOptions;
        private final InProcessClientStream clientStream;
        private final Metadata headers;
        private final MethodDescriptor<?, ?> method;
        private final InProcessServerStream serverStream;

        private InProcessStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions, String authority, StatsTraceContext statsTraceContext) {
            this.method = (MethodDescriptor) Preconditions.checkNotNull(method, "method");
            this.headers = (Metadata) Preconditions.checkNotNull(headers, "headers");
            this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions, "callOptions");
            this.authority = authority;
            this.clientStream = new InProcessClientStream(callOptions, statsTraceContext);
            this.serverStream = new InProcessServerStream(method, headers);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void streamClosed() {
            synchronized (InProcessTransport.this) {
                boolean justRemovedAnElement = InProcessTransport.this.streams.remove(this);
                if (GrpcUtil.shouldBeCountedForInUse(this.callOptions)) {
                    InProcessTransport.this.inUseState.updateObjectInUse(this, false);
                }
                if (InProcessTransport.this.streams.isEmpty() && justRemovedAnElement && InProcessTransport.this.shutdown) {
                    InProcessTransport.this.notifyTerminated();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        class InProcessServerStream implements ServerStream {
            private Status clientNotifyStatus;
            private Metadata clientNotifyTrailers;
            private ArrayDeque<StreamListener.MessageProducer> clientReceiveQueue = new ArrayDeque<>();
            private int clientRequested;
            private ClientStreamListener clientStreamListener;
            private boolean closed;
            private int outboundSeqNo;
            final StatsTraceContext statsTraceCtx;
            private final SynchronizationContext syncContext;

            InProcessServerStream(MethodDescriptor<?, ?> method, Metadata headers) {
                this.syncContext = new SynchronizationContext(InProcessTransport.this.uncaughtExceptionHandler);
                this.statsTraceCtx = StatsTraceContext.newServerContext(InProcessTransport.this.serverStreamTracerFactories, method.getFullMethodName(), headers);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public synchronized void setListener(ClientStreamListener listener) {
                this.clientStreamListener = listener;
            }

            @Override // io.grpc.internal.ServerStream
            public void setListener(ServerStreamListener serverStreamListener) {
                InProcessStream.this.clientStream.setListener(serverStreamListener);
            }

            @Override // io.grpc.internal.Stream
            public void request(int numMessages) {
                boolean onReady = InProcessStream.this.clientStream.serverRequested(numMessages);
                if (onReady) {
                    synchronized (this) {
                        if (!this.closed) {
                            this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda6
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f$0.m452x211aea5f();
                                }
                            });
                        }
                    }
                }
                this.syncContext.drain();
            }

            /* JADX INFO: renamed from: lambda$request$0$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            /* synthetic */ void m452x211aea5f() {
                this.clientStreamListener.onReady();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public boolean clientRequested(int numMessages) {
                synchronized (this) {
                    if (this.closed) {
                        return false;
                    }
                    boolean previouslyReady = this.clientRequested > 0;
                    this.clientRequested += numMessages;
                    while (this.clientRequested > 0 && !this.clientReceiveQueue.isEmpty()) {
                        this.clientRequested--;
                        final StreamListener.MessageProducer producer = this.clientReceiveQueue.poll();
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m448x66a40dea(producer);
                            }
                        });
                    }
                    if (this.clientReceiveQueue.isEmpty() && this.clientNotifyStatus != null) {
                        this.closed = true;
                        InProcessStream.this.clientStream.statsTraceCtx.clientInboundTrailers(this.clientNotifyTrailers);
                        InProcessStream.this.clientStream.statsTraceCtx.streamClosed(this.clientNotifyStatus);
                        final Status notifyStatus = this.clientNotifyStatus;
                        final Metadata notifyTrailers = this.clientNotifyTrailers;
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m449xa8bb3b49(notifyStatus, notifyTrailers);
                            }
                        });
                    }
                    boolean nowReady = this.clientRequested > 0;
                    this.syncContext.drain();
                    return !previouslyReady && nowReady;
                }
            }

            /* JADX INFO: renamed from: lambda$clientRequested$1$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            /* synthetic */ void m448x66a40dea(StreamListener.MessageProducer producer) {
                this.clientStreamListener.messagesAvailable(producer);
            }

            /* JADX INFO: renamed from: lambda$clientRequested$2$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            /* synthetic */ void m449xa8bb3b49(Status notifyStatus, Metadata notifyTrailers) {
                this.clientStreamListener.closed(notifyStatus, ClientStreamListener.RpcProgress.PROCESSED, notifyTrailers);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clientCancelled(Status status) {
                internalCancel(status);
            }

            @Override // io.grpc.internal.Stream
            public void writeMessage(InputStream message) {
                synchronized (this) {
                    if (this.closed) {
                        return;
                    }
                    this.statsTraceCtx.outboundMessage(this.outboundSeqNo);
                    this.statsTraceCtx.outboundMessageSent(this.outboundSeqNo, -1L, -1L);
                    InProcessStream.this.clientStream.statsTraceCtx.inboundMessage(this.outboundSeqNo);
                    InProcessStream.this.clientStream.statsTraceCtx.inboundMessageRead(this.outboundSeqNo, -1L, -1L);
                    this.outboundSeqNo++;
                    final StreamListener.MessageProducer producer = new SingleMessageProducer(message);
                    if (this.clientRequested > 0) {
                        this.clientRequested--;
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda4
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m454x4a642b29(producer);
                            }
                        });
                    } else {
                        this.clientReceiveQueue.add(producer);
                    }
                    this.syncContext.drain();
                }
            }

            /* JADX INFO: renamed from: lambda$writeMessage$3$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            /* synthetic */ void m454x4a642b29(StreamListener.MessageProducer producer) {
                this.clientStreamListener.messagesAvailable(producer);
            }

            @Override // io.grpc.internal.Stream
            public void flush() {
            }

            @Override // io.grpc.internal.Stream
            public synchronized boolean isReady() {
                if (this.closed) {
                    return false;
                }
                return this.clientRequested > 0;
            }

            @Override // io.grpc.internal.ServerStream
            public void writeHeaders(final Metadata headers) {
                int metadataSize;
                if (InProcessTransport.this.clientMaxInboundMetadataSize != Integer.MAX_VALUE && (metadataSize = InProcessTransport.metadataSize(headers)) > InProcessTransport.this.clientMaxInboundMetadataSize) {
                    Status serverStatus = Status.CANCELLED.withDescription("Client cancelled the RPC");
                    InProcessStream.this.clientStream.serverClosed(serverStatus, serverStatus);
                    Status failedStatus = Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "Response header metadata larger than %d: %d", Integer.valueOf(InProcessTransport.this.clientMaxInboundMetadataSize), Integer.valueOf(metadataSize)));
                    notifyClientClose(failedStatus, new Metadata());
                    return;
                }
                synchronized (this) {
                    if (this.closed) {
                        return;
                    }
                    InProcessStream.this.clientStream.statsTraceCtx.clientInboundHeaders();
                    this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m453xd98ac769(headers);
                        }
                    });
                    this.syncContext.drain();
                }
            }

            /* JADX INFO: renamed from: lambda$writeHeaders$4$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            /* synthetic */ void m453xd98ac769(Metadata headers) {
                this.clientStreamListener.headersRead(headers);
            }

            @Override // io.grpc.internal.ServerStream
            public void close(Status status, Metadata trailers) {
                InProcessStream.this.clientStream.serverClosed(Status.OK, status);
                if (InProcessTransport.this.clientMaxInboundMetadataSize != Integer.MAX_VALUE) {
                    int statusSize = status.getDescription() == null ? 0 : status.getDescription().length();
                    int metadataSize = InProcessTransport.metadataSize(trailers) + statusSize;
                    if (metadataSize > InProcessTransport.this.clientMaxInboundMetadataSize) {
                        status = Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "Response header metadata larger than %d: %d", Integer.valueOf(InProcessTransport.this.clientMaxInboundMetadataSize), Integer.valueOf(metadataSize)));
                        trailers = new Metadata();
                    }
                }
                notifyClientClose(status, trailers);
            }

            private void notifyClientClose(Status status, final Metadata trailers) {
                final Status clientStatus = InProcessTransport.cleanStatus(status, InProcessTransport.this.includeCauseWithStatus);
                synchronized (this) {
                    if (this.closed) {
                        return;
                    }
                    if (this.clientReceiveQueue.isEmpty()) {
                        this.closed = true;
                        InProcessStream.this.clientStream.statsTraceCtx.clientInboundTrailers(trailers);
                        InProcessStream.this.clientStream.statsTraceCtx.streamClosed(clientStatus);
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda5
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m451xce68b7c5(clientStatus, trailers);
                            }
                        });
                    } else {
                        this.clientNotifyStatus = clientStatus;
                        this.clientNotifyTrailers = trailers;
                    }
                    this.syncContext.drain();
                    InProcessStream.this.streamClosed();
                }
            }

            /* JADX INFO: renamed from: lambda$notifyClientClose$5$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            /* synthetic */ void m451xce68b7c5(Status clientStatus, Metadata trailers) {
                this.clientStreamListener.closed(clientStatus, ClientStreamListener.RpcProgress.PROCESSED, trailers);
            }

            @Override // io.grpc.internal.ServerStream
            public void cancel(Status status) {
                if (!internalCancel(Status.CANCELLED.withDescription("server cancelled stream"))) {
                    return;
                }
                InProcessStream.this.clientStream.serverClosed(status, status);
                InProcessStream.this.streamClosed();
            }

            private boolean internalCancel(final Status clientStatus) {
                synchronized (this) {
                    if (this.closed) {
                        return false;
                    }
                    this.closed = true;
                    while (true) {
                        StreamListener.MessageProducer producer = this.clientReceiveQueue.poll();
                        if (producer == null) {
                            InProcessStream.this.clientStream.statsTraceCtx.streamClosed(clientStatus);
                            this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f$0.m450x6881a277(clientStatus);
                                }
                            });
                            this.syncContext.drain();
                            return true;
                        }
                        while (true) {
                            InputStream message = producer.next();
                            if (message != null) {
                                try {
                                    message.close();
                                } catch (Throwable t) {
                                    InProcessTransport.log.log(Level.WARNING, "Exception closing stream", t);
                                }
                            }
                        }
                    }
                }
            }

            /* JADX INFO: renamed from: lambda$internalCancel$6$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            /* synthetic */ void m450x6881a277(Status clientStatus) {
                this.clientStreamListener.closed(clientStatus, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
            }

            @Override // io.grpc.internal.Stream
            public void setMessageCompression(boolean enable) {
            }

            @Override // io.grpc.internal.Stream
            public void optimizeForDirectExecutor() {
            }

            @Override // io.grpc.internal.Stream
            public void setCompressor(Compressor compressor) {
            }

            @Override // io.grpc.internal.ServerStream
            public void setDecompressor(Decompressor decompressor) {
            }

            @Override // io.grpc.internal.ServerStream
            public Attributes getAttributes() {
                return InProcessTransport.this.serverStreamAttributes;
            }

            @Override // io.grpc.internal.ServerStream
            public String getAuthority() {
                return InProcessStream.this.authority;
            }

            @Override // io.grpc.internal.ServerStream
            public StatsTraceContext statsTraceContext() {
                return this.statsTraceCtx;
            }

            @Override // io.grpc.internal.ServerStream
            public int streamId() {
                return -1;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        class InProcessClientStream implements ClientStream {
            final CallOptions callOptions;
            private boolean closed;
            private int outboundSeqNo;
            private boolean serverNotifyHalfClose;
            private ArrayDeque<StreamListener.MessageProducer> serverReceiveQueue = new ArrayDeque<>();
            private int serverRequested;
            private ServerStreamListener serverStreamListener;
            final StatsTraceContext statsTraceCtx;
            private final SynchronizationContext syncContext;

            InProcessClientStream(CallOptions callOptions, StatsTraceContext statsTraceContext) {
                this.syncContext = new SynchronizationContext(InProcessTransport.this.uncaughtExceptionHandler);
                this.callOptions = callOptions;
                this.statsTraceCtx = statsTraceContext;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public synchronized void setListener(ServerStreamListener listener) {
                this.serverStreamListener = listener;
            }

            @Override // io.grpc.internal.Stream
            public void request(int numMessages) {
                boolean onReady = InProcessStream.this.serverStream.clientRequested(numMessages);
                if (onReady) {
                    synchronized (this) {
                        if (!this.closed) {
                            this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f$0.m444x59d828e7();
                                }
                            });
                        }
                    }
                    this.syncContext.drain();
                }
            }

            /* JADX INFO: renamed from: lambda$request$0$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            /* synthetic */ void m444x59d828e7() {
                this.serverStreamListener.onReady();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public boolean serverRequested(int numMessages) {
                synchronized (this) {
                    if (this.closed) {
                        return false;
                    }
                    boolean previouslyReady = this.serverRequested > 0;
                    this.serverRequested += numMessages;
                    while (this.serverRequested > 0 && !this.serverReceiveQueue.isEmpty()) {
                        this.serverRequested--;
                        final StreamListener.MessageProducer producer = this.serverReceiveQueue.poll();
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda4
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m445x455a5dea(producer);
                            }
                        });
                    }
                    if (this.serverReceiveQueue.isEmpty() && this.serverNotifyHalfClose) {
                        this.serverNotifyHalfClose = false;
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda5
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m446x87718b49();
                            }
                        });
                    }
                    boolean nowReady = this.serverRequested > 0;
                    this.syncContext.drain();
                    return !previouslyReady && nowReady;
                }
            }

            /* JADX INFO: renamed from: lambda$serverRequested$1$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            /* synthetic */ void m445x455a5dea(StreamListener.MessageProducer producer) {
                this.serverStreamListener.messagesAvailable(producer);
            }

            /* JADX INFO: renamed from: lambda$serverRequested$2$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            /* synthetic */ void m446x87718b49() {
                this.serverStreamListener.halfClosed();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void serverClosed(Status serverListenerStatus, Status serverTracerStatus) {
                internalCancel(serverListenerStatus, serverTracerStatus);
            }

            @Override // io.grpc.internal.Stream
            public void writeMessage(InputStream message) {
                synchronized (this) {
                    if (this.closed) {
                        return;
                    }
                    this.statsTraceCtx.outboundMessage(this.outboundSeqNo);
                    this.statsTraceCtx.outboundMessageSent(this.outboundSeqNo, -1L, -1L);
                    InProcessStream.this.serverStream.statsTraceCtx.inboundMessage(this.outboundSeqNo);
                    InProcessStream.this.serverStream.statsTraceCtx.inboundMessageRead(this.outboundSeqNo, -1L, -1L);
                    this.outboundSeqNo++;
                    final StreamListener.MessageProducer producer = new SingleMessageProducer(message);
                    if (this.serverRequested > 0) {
                        this.serverRequested--;
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m447x832169b1(producer);
                            }
                        });
                    } else {
                        this.serverReceiveQueue.add(producer);
                    }
                    this.syncContext.drain();
                }
            }

            /* JADX INFO: renamed from: lambda$writeMessage$3$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            /* synthetic */ void m447x832169b1(StreamListener.MessageProducer producer) {
                this.serverStreamListener.messagesAvailable(producer);
            }

            @Override // io.grpc.internal.Stream
            public void flush() {
            }

            @Override // io.grpc.internal.Stream
            public synchronized boolean isReady() {
                if (this.closed) {
                    return false;
                }
                return this.serverRequested > 0;
            }

            @Override // io.grpc.internal.ClientStream
            public void cancel(Status reason) {
                Status serverStatus = InProcessTransport.cleanStatus(reason, InProcessTransport.this.includeCauseWithStatus);
                if (internalCancel(serverStatus, serverStatus)) {
                    InProcessStream.this.serverStream.clientCancelled(reason);
                    InProcessStream.this.streamClosed();
                }
            }

            private boolean internalCancel(final Status serverListenerStatus, Status serverTracerStatus) {
                synchronized (this) {
                    if (this.closed) {
                        return false;
                    }
                    this.closed = true;
                    while (true) {
                        StreamListener.MessageProducer producer = this.serverReceiveQueue.poll();
                        if (producer == null) {
                            InProcessStream.this.serverStream.statsTraceCtx.streamClosed(serverTracerStatus);
                            this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f$0.m443x1d108641(serverListenerStatus);
                                }
                            });
                            this.syncContext.drain();
                            return true;
                        }
                        while (true) {
                            InputStream message = producer.next();
                            if (message != null) {
                                try {
                                    message.close();
                                } catch (Throwable t) {
                                    InProcessTransport.log.log(Level.WARNING, "Exception closing stream", t);
                                }
                            }
                        }
                    }
                }
            }

            /* JADX INFO: renamed from: lambda$internalCancel$4$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            /* synthetic */ void m443x1d108641(Status serverListenerStatus) {
                this.serverStreamListener.closed(serverListenerStatus);
            }

            @Override // io.grpc.internal.ClientStream
            public void halfClose() {
                synchronized (this) {
                    if (this.closed) {
                        return;
                    }
                    if (this.serverReceiveQueue.isEmpty()) {
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m442x9d711d4c();
                            }
                        });
                    } else {
                        this.serverNotifyHalfClose = true;
                    }
                    this.syncContext.drain();
                }
            }

            /* JADX INFO: renamed from: lambda$halfClose$5$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            /* synthetic */ void m442x9d711d4c() {
                this.serverStreamListener.halfClosed();
            }

            @Override // io.grpc.internal.Stream
            public void setMessageCompression(boolean enable) {
            }

            @Override // io.grpc.internal.ClientStream
            public void setAuthority(String string) {
                InProcessStream.this.authority = string;
            }

            @Override // io.grpc.internal.ClientStream
            public void start(ClientStreamListener listener) {
                InProcessStream.this.serverStream.setListener(listener);
                synchronized (InProcessTransport.this) {
                    this.statsTraceCtx.clientOutboundHeaders();
                    InProcessTransport.this.streams.add(InProcessStream.this);
                    if (GrpcUtil.shouldBeCountedForInUse(this.callOptions)) {
                        InProcessTransport.this.inUseState.updateObjectInUse(InProcessStream.this, true);
                    }
                    InProcessTransport.this.serverTransportListener.streamCreated(InProcessStream.this.serverStream, InProcessStream.this.method.getFullMethodName(), InProcessStream.this.headers);
                }
            }

            @Override // io.grpc.internal.ClientStream
            public Attributes getAttributes() {
                return InProcessTransport.this.attributes;
            }

            @Override // io.grpc.internal.Stream
            public void optimizeForDirectExecutor() {
            }

            @Override // io.grpc.internal.Stream
            public void setCompressor(Compressor compressor) {
            }

            @Override // io.grpc.internal.ClientStream
            public void setFullStreamDecompression(boolean fullStreamDecompression) {
            }

            @Override // io.grpc.internal.ClientStream
            public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
            }

            @Override // io.grpc.internal.ClientStream
            public void setMaxInboundMessageSize(int maxSize) {
            }

            @Override // io.grpc.internal.ClientStream
            public void setMaxOutboundMessageSize(int maxSize) {
            }

            @Override // io.grpc.internal.ClientStream
            public void setDeadline(Deadline deadline) {
                InProcessStream.this.headers.discardAll(GrpcUtil.TIMEOUT_KEY);
                long effectiveTimeout = Math.max(0L, deadline.timeRemaining(TimeUnit.NANOSECONDS));
                InProcessStream.this.headers.put(GrpcUtil.TIMEOUT_KEY, Long.valueOf(effectiveTimeout));
            }

            @Override // io.grpc.internal.ClientStream
            public void appendTimeoutInsight(InsightBuilder insight) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status cleanStatus(Status status, boolean includeCauseWithStatus) {
        if (status == null) {
            return null;
        }
        Status clientStatus = Status.fromCodeValue(status.getCode().value()).withDescription(status.getDescription());
        if (includeCauseWithStatus) {
            return clientStatus.withCause(status.getCause());
        }
        return clientStatus;
    }

    private static class SingleMessageProducer implements StreamListener.MessageProducer {
        private InputStream message;

        private SingleMessageProducer(InputStream message) {
            this.message = message;
        }

        @Override // io.grpc.internal.StreamListener.MessageProducer
        @Nullable
        public InputStream next() {
            InputStream messageToReturn = this.message;
            this.message = null;
            return messageToReturn;
        }
    }
}
