package io.grpc.inprocess;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.InternalServer;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerListener;
import io.grpc.internal.ServerTransportListener;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes10.dex */
final class InProcessServer implements InternalServer {
    private static final ConcurrentMap<String, InProcessServer> registry = new ConcurrentHashMap();
    private final SocketAddress listenAddress;
    private ServerListener listener;
    private final int maxInboundMetadataSize;
    private ScheduledExecutorService scheduler;
    private final ObjectPool<ScheduledExecutorService> schedulerPool;
    private boolean shutdown;
    private final List<ServerStreamTracer.Factory> streamTracerFactories;

    static InProcessServer findServer(SocketAddress addr) {
        if (addr instanceof AnonymousInProcessSocketAddress) {
            return ((AnonymousInProcessSocketAddress) addr).getServer();
        }
        if (addr instanceof InProcessSocketAddress) {
            return registry.get(((InProcessSocketAddress) addr).getName());
        }
        return null;
    }

    InProcessServer(InProcessServerBuilder builder, List<? extends ServerStreamTracer.Factory> streamTracerFactories) {
        this.listenAddress = builder.listenAddress;
        this.schedulerPool = builder.schedulerPool;
        this.maxInboundMetadataSize = builder.maxInboundMetadataSize;
        this.streamTracerFactories = Collections.unmodifiableList((List) Preconditions.checkNotNull(streamTracerFactories, "streamTracerFactories"));
    }

    @Override // io.grpc.internal.InternalServer
    public void start(ServerListener serverListener) throws IOException {
        this.listener = serverListener;
        this.scheduler = this.schedulerPool.getObject();
        registerInstance();
    }

    private void registerInstance() throws IOException {
        if (this.listenAddress instanceof AnonymousInProcessSocketAddress) {
            ((AnonymousInProcessSocketAddress) this.listenAddress).setServer(this);
        } else {
            if (this.listenAddress instanceof InProcessSocketAddress) {
                String name = ((InProcessSocketAddress) this.listenAddress).getName();
                if (registry.putIfAbsent(name, this) != null) {
                    throw new IOException("name already registered: " + name);
                }
                return;
            }
            throw new AssertionError();
        }
    }

    @Override // io.grpc.internal.InternalServer
    public SocketAddress getListenSocketAddress() {
        return this.listenAddress;
    }

    @Override // io.grpc.internal.InternalServer
    public List<? extends SocketAddress> getListenSocketAddresses() {
        return Collections.singletonList(getListenSocketAddress());
    }

    @Override // io.grpc.internal.InternalServer
    public InternalInstrumented<InternalChannelz.SocketStats> getListenSocketStats() {
        return null;
    }

    @Override // io.grpc.internal.InternalServer
    public List<InternalInstrumented<InternalChannelz.SocketStats>> getListenSocketStatsList() {
        return null;
    }

    @Override // io.grpc.internal.InternalServer
    public void shutdown() {
        unregisterInstance();
        this.scheduler = this.schedulerPool.returnObject(this.scheduler);
        synchronized (this) {
            this.shutdown = true;
            this.listener.serverShutdown();
        }
    }

    private void unregisterInstance() {
        if (this.listenAddress instanceof AnonymousInProcessSocketAddress) {
            ((AnonymousInProcessSocketAddress) this.listenAddress).clearServer(this);
        } else {
            if (this.listenAddress instanceof InProcessSocketAddress) {
                String name = ((InProcessSocketAddress) this.listenAddress).getName();
                if (!registry.remove(name, this)) {
                    throw new AssertionError();
                }
                return;
            }
            throw new AssertionError();
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("listenAddress", this.listenAddress).toString();
    }

    synchronized ServerTransportListener register(InProcessTransport transport) {
        if (this.shutdown) {
            return null;
        }
        return this.listener.transportCreated(transport);
    }

    ObjectPool<ScheduledExecutorService> getScheduledExecutorServicePool() {
        return this.schedulerPool;
    }

    int getMaxInboundMetadataSize() {
        return this.maxInboundMetadataSize;
    }

    List<ServerStreamTracer.Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }
}
