package io.grpc.inprocess;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Preconditions;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ManagedChannelBuilder;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.SharedResourceHolder;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public final class InProcessChannelBuilder extends AbstractManagedChannelImplBuilder<InProcessChannelBuilder> {
    private final ManagedChannelImplBuilder managedChannelImplBuilder;
    private ScheduledExecutorService scheduledExecutorService;
    private int maxInboundMetadataSize = Integer.MAX_VALUE;
    private boolean transportIncludeStatusCause = false;

    public static InProcessChannelBuilder forName(String name) {
        return forAddress(new InProcessSocketAddress((String) Preconditions.checkNotNull(name, "name")));
    }

    public static InProcessChannelBuilder forTarget(String target) {
        return new InProcessChannelBuilder(null, (String) Preconditions.checkNotNull(target, TypedValues.AttributesType.S_TARGET));
    }

    public static InProcessChannelBuilder forAddress(SocketAddress address) {
        return new InProcessChannelBuilder((SocketAddress) Preconditions.checkNotNull(address, "address"), null);
    }

    public static InProcessChannelBuilder forAddress(String name, int port) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    private InProcessChannelBuilder(@Nullable SocketAddress directAddress, @Nullable String target) {
        if (directAddress != null) {
            this.managedChannelImplBuilder = new ManagedChannelImplBuilder(directAddress, "localhost", new ManagedChannelImplBuilder.ClientTransportFactoryBuilder() { // from class: io.grpc.inprocess.InProcessChannelBuilder.1InProcessChannelTransportFactoryBuilder
                @Override // io.grpc.internal.ManagedChannelImplBuilder.ClientTransportFactoryBuilder
                public ClientTransportFactory buildClientTransportFactory() {
                    return InProcessChannelBuilder.this.buildTransportFactory();
                }
            }, null);
        } else {
            this.managedChannelImplBuilder = new ManagedChannelImplBuilder(target, new ManagedChannelImplBuilder.ClientTransportFactoryBuilder() { // from class: io.grpc.inprocess.InProcessChannelBuilder.1InProcessChannelTransportFactoryBuilder
                @Override // io.grpc.internal.ManagedChannelImplBuilder.ClientTransportFactoryBuilder
                public ClientTransportFactory buildClientTransportFactory() {
                    return InProcessChannelBuilder.this.buildTransportFactory();
                }
            }, null);
        }
        this.managedChannelImplBuilder.setStatsRecordStartedRpcs(false);
        this.managedChannelImplBuilder.setStatsRecordFinishedRpcs(false);
        this.managedChannelImplBuilder.setStatsRecordRetryMetrics(false);
        this.managedChannelImplBuilder.disableRetry();
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder
    protected ManagedChannelBuilder<?> delegate() {
        return this.managedChannelImplBuilder;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder maxInboundMessageSize(int max) {
        return (InProcessChannelBuilder) super.maxInboundMessageSize(max);
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder useTransportSecurity() {
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder usePlaintext() {
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit) {
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder keepAliveWithoutCalls(boolean enable) {
        return this;
    }

    public InProcessChannelBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService");
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder maxInboundMetadataSize(int bytes) {
        Preconditions.checkArgument(bytes > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = bytes;
        return this;
    }

    public InProcessChannelBuilder propagateCauseWithStatus(boolean enable) {
        this.transportIncludeStatusCause = enable;
        return this;
    }

    ClientTransportFactory buildTransportFactory() {
        return new InProcessClientTransportFactory(this.scheduledExecutorService, this.maxInboundMetadataSize, this.transportIncludeStatusCause);
    }

    void setStatsEnabled(boolean value) {
        this.managedChannelImplBuilder.setStatsEnabled(value);
    }

    static final class InProcessClientTransportFactory implements ClientTransportFactory {
        private boolean closed;
        private final boolean includeCauseWithStatus;
        private final int maxInboundMetadataSize;
        private final ScheduledExecutorService timerService;
        private final boolean useSharedTimer;

        private InProcessClientTransportFactory(@Nullable ScheduledExecutorService scheduledExecutorService, int maxInboundMetadataSize, boolean includeCauseWithStatus) {
            this.useSharedTimer = scheduledExecutorService == null;
            this.timerService = this.useSharedTimer ? (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE) : scheduledExecutorService;
            this.maxInboundMetadataSize = maxInboundMetadataSize;
            this.includeCauseWithStatus = includeCauseWithStatus;
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public ConnectionClientTransport newClientTransport(SocketAddress addr, ClientTransportFactory.ClientTransportOptions options, ChannelLogger channelLogger) {
            if (this.closed) {
                throw new IllegalStateException("The transport factory is closed.");
            }
            return new InProcessTransport(addr, this.maxInboundMetadataSize, options.getAuthority(), options.getUserAgent(), options.getEagAttributes(), this.includeCauseWithStatus);
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public ScheduledExecutorService getScheduledExecutorService() {
            return this.timerService;
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCreds) {
            return null;
        }

        @Override // io.grpc.internal.ClientTransportFactory, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.useSharedTimer) {
                SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.timerService);
            }
        }
    }
}
