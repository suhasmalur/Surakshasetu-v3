package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import io.grpc.ChoiceServerCredentials;
import io.grpc.ForwardingServerBuilder;
import io.grpc.InsecureServerCredentials;
import io.grpc.ServerBuilder;
import io.grpc.ServerCredentials;
import io.grpc.ServerStreamTracer;
import io.grpc.TlsServerCredentials;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InternalServer;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerImplBuilder;
import io.grpc.internal.SharedResourcePool;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.SslSocketFactoryServerCredentials;
import io.grpc.okhttp.internal.Platform;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* JADX INFO: loaded from: classes10.dex */
public final class OkHttpServerBuilder extends ForwardingServerBuilder<OkHttpServerBuilder> {
    private static final int DEFAULT_FLOW_CONTROL_WINDOW = 65535;
    static final long MAX_CONNECTION_AGE_GRACE_NANOS_INFINITE = Long.MAX_VALUE;
    static final long MAX_CONNECTION_AGE_NANOS_DISABLED = Long.MAX_VALUE;
    static final long MAX_CONNECTION_IDLE_NANOS_DISABLED = Long.MAX_VALUE;
    final HandshakerSocketFactory handshakerSocketFactory;
    final SocketAddress listenAddress;
    boolean permitKeepAliveWithoutCalls;
    private static final Logger log = Logger.getLogger(OkHttpServerBuilder.class.getName());
    private static final long MIN_MAX_CONNECTION_IDLE_NANO = TimeUnit.SECONDS.toNanos(1);
    private static final long MIN_MAX_CONNECTION_AGE_NANO = TimeUnit.SECONDS.toNanos(1);
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000);
    private static final ObjectPool<Executor> DEFAULT_TRANSPORT_EXECUTOR_POOL = OkHttpChannelBuilder.DEFAULT_TRANSPORT_EXECUTOR_POOL;
    private static final EnumSet<TlsServerCredentials.Feature> understoodTlsFeatures = EnumSet.of(TlsServerCredentials.Feature.MTLS, TlsServerCredentials.Feature.CUSTOM_MANAGERS);
    final ServerImplBuilder serverImplBuilder = new ServerImplBuilder(new ServerImplBuilder.ClientTransportServersBuilder() { // from class: io.grpc.okhttp.OkHttpServerBuilder$$ExternalSyntheticLambda0
        @Override // io.grpc.internal.ServerImplBuilder.ClientTransportServersBuilder
        public final InternalServer buildClientTransportServers(List list) {
            return this.f$0.buildTransportServers(list);
        }
    });
    TransportTracer.Factory transportTracerFactory = TransportTracer.getDefaultFactory();
    ObjectPool<Executor> transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
    ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
    ServerSocketFactory socketFactory = ServerSocketFactory.getDefault();
    long keepAliveTimeNanos = GrpcUtil.DEFAULT_SERVER_KEEPALIVE_TIME_NANOS;
    long keepAliveTimeoutNanos = GrpcUtil.DEFAULT_SERVER_KEEPALIVE_TIMEOUT_NANOS;
    int flowControlWindow = 65535;
    int maxInboundMetadataSize = 8192;
    int maxInboundMessageSize = 4194304;
    long maxConnectionIdleInNanos = Long.MAX_VALUE;
    long permitKeepAliveTimeInNanos = TimeUnit.MINUTES.toNanos(5);
    long maxConnectionAgeInNanos = Long.MAX_VALUE;
    long maxConnectionAgeGraceInNanos = Long.MAX_VALUE;

    @Deprecated
    public static OkHttpServerBuilder forPort(int port) {
        throw new UnsupportedOperationException();
    }

    public static OkHttpServerBuilder forPort(int port, ServerCredentials creds) {
        return forPort(new InetSocketAddress(port), creds);
    }

    public static OkHttpServerBuilder forPort(SocketAddress address, ServerCredentials creds) throws GeneralSecurityException {
        HandshakerSocketFactoryResult result = handshakerSocketFactoryFrom(creds);
        if (result.error != null) {
            throw new IllegalArgumentException(result.error);
        }
        return new OkHttpServerBuilder(address, result.factory);
    }

    OkHttpServerBuilder(SocketAddress address, HandshakerSocketFactory handshakerSocketFactory) {
        this.listenAddress = (SocketAddress) Preconditions.checkNotNull(address, "address");
        this.handshakerSocketFactory = (HandshakerSocketFactory) Preconditions.checkNotNull(handshakerSocketFactory, "handshakerSocketFactory");
    }

    @Override // io.grpc.ForwardingServerBuilder
    protected ServerBuilder<?> delegate() {
        return this.serverImplBuilder;
    }

    OkHttpServerBuilder setTransportTracerFactory(TransportTracer.Factory transportTracerFactory) {
        this.transportTracerFactory = transportTracerFactory;
        return this;
    }

    public OkHttpServerBuilder transportExecutor(Executor transportExecutor) {
        if (transportExecutor == null) {
            this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        } else {
            this.transportExecutorPool = new FixedObjectPool(transportExecutor);
        }
        return this;
    }

    public OkHttpServerBuilder socketFactory(ServerSocketFactory socketFactory) {
        if (socketFactory == null) {
            this.socketFactory = ServerSocketFactory.getDefault();
        } else {
            this.socketFactory = socketFactory;
        }
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTime > 0, "keepalive time must be positive");
        this.keepAliveTimeNanos = timeUnit.toNanos(keepAliveTime);
        this.keepAliveTimeNanos = KeepAliveManager.clampKeepAliveTimeInNanos(this.keepAliveTimeNanos);
        if (this.keepAliveTimeNanos >= AS_LARGE_AS_INFINITE) {
            this.keepAliveTimeNanos = Long.MAX_VALUE;
        }
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder maxConnectionIdle(long maxConnectionIdle, TimeUnit timeUnit) {
        Preconditions.checkArgument(maxConnectionIdle > 0, "max connection idle must be positive: %s", maxConnectionIdle);
        this.maxConnectionIdleInNanos = timeUnit.toNanos(maxConnectionIdle);
        if (this.maxConnectionIdleInNanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionIdleInNanos = Long.MAX_VALUE;
        }
        if (this.maxConnectionIdleInNanos < MIN_MAX_CONNECTION_IDLE_NANO) {
            this.maxConnectionIdleInNanos = MIN_MAX_CONNECTION_IDLE_NANO;
        }
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder maxConnectionAge(long maxConnectionAge, TimeUnit timeUnit) {
        Preconditions.checkArgument(maxConnectionAge > 0, "max connection age must be positive: %s", maxConnectionAge);
        this.maxConnectionAgeInNanos = timeUnit.toNanos(maxConnectionAge);
        if (this.maxConnectionAgeInNanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionAgeInNanos = Long.MAX_VALUE;
        }
        if (this.maxConnectionAgeInNanos < MIN_MAX_CONNECTION_AGE_NANO) {
            this.maxConnectionAgeInNanos = MIN_MAX_CONNECTION_AGE_NANO;
        }
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder maxConnectionAgeGrace(long maxConnectionAgeGrace, TimeUnit timeUnit) {
        Preconditions.checkArgument(maxConnectionAgeGrace >= 0, "max connection age grace must be non-negative: %s", maxConnectionAgeGrace);
        this.maxConnectionAgeGraceInNanos = timeUnit.toNanos(maxConnectionAgeGrace);
        if (this.maxConnectionAgeGraceInNanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionAgeGraceInNanos = Long.MAX_VALUE;
        }
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTimeout > 0, "keepalive timeout must be positive");
        this.keepAliveTimeoutNanos = timeUnit.toNanos(keepAliveTimeout);
        this.keepAliveTimeoutNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(this.keepAliveTimeoutNanos);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder permitKeepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTime >= 0, "permit keepalive time must be non-negative: %s", keepAliveTime);
        this.permitKeepAliveTimeInNanos = timeUnit.toNanos(keepAliveTime);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder permitKeepAliveWithoutCalls(boolean permit) {
        this.permitKeepAliveWithoutCalls = permit;
        return this;
    }

    public OkHttpServerBuilder flowControlWindow(int flowControlWindow) {
        Preconditions.checkState(flowControlWindow > 0, "flowControlWindow must be positive");
        this.flowControlWindow = flowControlWindow;
        return this;
    }

    public OkHttpServerBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorServicePool = new FixedObjectPool((ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder maxInboundMetadataSize(int bytes) {
        Preconditions.checkArgument(bytes > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = bytes;
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder maxInboundMessageSize(int bytes) {
        Preconditions.checkArgument(bytes >= 0, "negative max bytes");
        this.maxInboundMessageSize = bytes;
        return this;
    }

    void setStatsEnabled(boolean value) {
        this.serverImplBuilder.setStatsEnabled(value);
    }

    InternalServer buildTransportServers(List<? extends ServerStreamTracer.Factory> streamTracerFactories) {
        return new OkHttpServer(this, streamTracerFactories, this.serverImplBuilder.getChannelz());
    }

    static HandshakerSocketFactoryResult handshakerSocketFactoryFrom(ServerCredentials creds) throws GeneralSecurityException {
        if (creds instanceof TlsServerCredentials) {
            TlsServerCredentials tlsCreds = (TlsServerCredentials) creds;
            Set<TlsServerCredentials.Feature> incomprehensible = tlsCreds.incomprehensible(understoodTlsFeatures);
            if (!incomprehensible.isEmpty()) {
                return HandshakerSocketFactoryResult.error("TLS features not understood: " + incomprehensible);
            }
            KeyManager[] km = null;
            if (tlsCreds.getKeyManagers() != null) {
                km = (KeyManager[]) tlsCreds.getKeyManagers().toArray(new KeyManager[0]);
            } else if (tlsCreds.getPrivateKey() != null) {
                if (tlsCreds.getPrivateKeyPassword() != null) {
                    return HandshakerSocketFactoryResult.error("byte[]-based private key with password unsupported. Use unencrypted file or KeyManager");
                }
                try {
                    km = OkHttpChannelBuilder.createKeyManager(tlsCreds.getCertificateChain(), tlsCreds.getPrivateKey());
                } catch (GeneralSecurityException gse) {
                    log.log(Level.FINE, "Exception loading private key from credential", (Throwable) gse);
                    return HandshakerSocketFactoryResult.error("Unable to load private key: " + gse.getMessage());
                }
            }
            TrustManager[] tm = null;
            if (tlsCreds.getTrustManagers() != null) {
                tm = (TrustManager[]) tlsCreds.getTrustManagers().toArray(new TrustManager[0]);
            } else if (tlsCreds.getRootCertificates() != null) {
                try {
                    tm = OkHttpChannelBuilder.createTrustManager(tlsCreds.getRootCertificates());
                } catch (GeneralSecurityException gse2) {
                    log.log(Level.FINE, "Exception loading root certificates from credential", (Throwable) gse2);
                    return HandshakerSocketFactoryResult.error("Unable to load root certificates: " + gse2.getMessage());
                }
            }
            try {
                SSLContext sslContext = SSLContext.getInstance("TLS", Platform.get().getProvider());
                sslContext.init(km, tm, null);
                SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                switch (tlsCreds.getClientAuth()) {
                    case OPTIONAL:
                        sslSocketFactory = new ClientCertRequestingSocketFactory(sslSocketFactory, false);
                        break;
                    case REQUIRE:
                        sslSocketFactory = new ClientCertRequestingSocketFactory(sslSocketFactory, true);
                        break;
                    case NONE:
                        break;
                    default:
                        return HandshakerSocketFactoryResult.error("Unknown TlsServerCredentials.ClientAuth value: " + tlsCreds.getClientAuth());
                }
                return HandshakerSocketFactoryResult.factory(new TlsServerHandshakerSocketFactory(new SslSocketFactoryServerCredentials.ServerCredentials(sslSocketFactory)));
            } catch (GeneralSecurityException gse3) {
                throw new RuntimeException("TLS Provider failure", gse3);
            }
        }
        if (creds instanceof InsecureServerCredentials) {
            return HandshakerSocketFactoryResult.factory(new PlaintextHandshakerSocketFactory());
        }
        if (creds instanceof SslSocketFactoryServerCredentials.ServerCredentials) {
            SslSocketFactoryServerCredentials.ServerCredentials factoryCreds = (SslSocketFactoryServerCredentials.ServerCredentials) creds;
            return HandshakerSocketFactoryResult.factory(new TlsServerHandshakerSocketFactory(factoryCreds));
        }
        if (creds instanceof ChoiceServerCredentials) {
            ChoiceServerCredentials choiceCreds = (ChoiceServerCredentials) creds;
            StringBuilder error = new StringBuilder();
            for (ServerCredentials innerCreds : choiceCreds.getCredentialsList()) {
                HandshakerSocketFactoryResult result = handshakerSocketFactoryFrom(innerCreds);
                if (result.error == null) {
                    return result;
                }
                error.append(", ");
                error.append(result.error);
            }
            return HandshakerSocketFactoryResult.error(error.substring(2));
        }
        return HandshakerSocketFactoryResult.error("Unsupported credential type: " + creds.getClass().getName());
    }

    static final class HandshakerSocketFactoryResult {
        public final String error;
        public final HandshakerSocketFactory factory;

        private HandshakerSocketFactoryResult(HandshakerSocketFactory factory, String error) {
            this.factory = factory;
            this.error = error;
        }

        public static HandshakerSocketFactoryResult error(String error) {
            return new HandshakerSocketFactoryResult(null, (String) Preconditions.checkNotNull(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR));
        }

        public static HandshakerSocketFactoryResult factory(HandshakerSocketFactory factory) {
            return new HandshakerSocketFactoryResult((HandshakerSocketFactory) Preconditions.checkNotNull(factory, "factory"), null);
        }
    }

    static final class ClientCertRequestingSocketFactory extends SSLSocketFactory {
        private final boolean required;
        private final SSLSocketFactory socketFactory;

        public ClientCertRequestingSocketFactory(SSLSocketFactory socketFactory, boolean required) {
            this.socketFactory = (SSLSocketFactory) Preconditions.checkNotNull(socketFactory, "socketFactory");
            this.required = required;
        }

        private Socket apply(Socket s) throws IOException {
            if (!(s instanceof SSLSocket)) {
                throw new IOException("SocketFactory " + this.socketFactory + " did not produce an SSLSocket: " + s.getClass());
            }
            SSLSocket sslSocket = (SSLSocket) s;
            if (this.required) {
                sslSocket.setNeedClientAuth(true);
            } else {
                sslSocket.setWantClientAuth(true);
            }
            return sslSocket;
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
            return apply(this.socketFactory.createSocket(s, host, port, autoClose));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String host, int port) throws IOException {
            return apply(this.socketFactory.createSocket(host, port));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
            return apply(this.socketFactory.createSocket(host, port, localHost, localPort));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress host, int port) throws IOException {
            return apply(this.socketFactory.createSocket(host, port));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress host, int port, InetAddress localAddress, int localPort) throws IOException {
            return apply(this.socketFactory.createSocket(host, port, localAddress, localPort));
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getDefaultCipherSuites() {
            return this.socketFactory.getDefaultCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getSupportedCipherSuites() {
            return this.socketFactory.getSupportedCipherSuites();
        }
    }
}
