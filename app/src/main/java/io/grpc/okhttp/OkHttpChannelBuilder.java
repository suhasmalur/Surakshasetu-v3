package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import io.grpc.CallCredentials;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ChoiceChannelCredentials;
import io.grpc.CompositeCallCredentials;
import io.grpc.CompositeChannelCredentials;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannelBuilder;
import io.grpc.TlsChannelCredentials;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.AtomicBackoff;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.SharedResourcePool;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.SslSocketFactoryChannelCredentials;
import io.grpc.okhttp.internal.CipherSuite;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.TlsVersion;
import io.grpc.util.CertificateUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes10.dex */
public final class OkHttpChannelBuilder extends AbstractManagedChannelImplBuilder<OkHttpChannelBuilder> {
    public static final int DEFAULT_FLOW_CONTROL_WINDOW = 65535;
    private ConnectionSpec connectionSpec;
    private int flowControlWindow;
    private final boolean freezeSecurityConfiguration;
    private HostnameVerifier hostnameVerifier;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    private final ManagedChannelImplBuilder managedChannelImplBuilder;
    private int maxInboundMetadataSize;
    private NegotiationType negotiationType;
    private ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool;
    private SocketFactory socketFactory;
    private SSLSocketFactory sslSocketFactory;
    private ObjectPool<Executor> transportExecutorPool;
    private TransportTracer.Factory transportTracerFactory;
    private final boolean useGetForSafeMethods;
    private static final Logger log = Logger.getLogger(OkHttpChannelBuilder.class.getName());
    static final ConnectionSpec INTERNAL_DEFAULT_CONNECTION_SPEC = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256).tlsVersions(TlsVersion.TLS_1_2).supportsTlsExtensions(true).build();
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000);
    private static final SharedResourceHolder.Resource<Executor> SHARED_EXECUTOR = new SharedResourceHolder.Resource<Executor>() { // from class: io.grpc.okhttp.OkHttpChannelBuilder.1
        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public Executor create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-okhttp-%d", true));
        }

        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public void close(Executor executor) {
            ((ExecutorService) executor).shutdown();
        }
    };
    static final ObjectPool<Executor> DEFAULT_TRANSPORT_EXECUTOR_POOL = SharedResourcePool.forResource(SHARED_EXECUTOR);
    private static final EnumSet<TlsChannelCredentials.Feature> understoodTlsFeatures = EnumSet.of(TlsChannelCredentials.Feature.MTLS, TlsChannelCredentials.Feature.CUSTOM_MANAGERS);

    private enum NegotiationType {
        TLS,
        PLAINTEXT
    }

    public static OkHttpChannelBuilder forAddress(String host, int port) {
        return new OkHttpChannelBuilder(host, port);
    }

    public static OkHttpChannelBuilder forAddress(String host, int port, ChannelCredentials creds) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(host, port), creds);
    }

    public static OkHttpChannelBuilder forTarget(String target) {
        return new OkHttpChannelBuilder(target);
    }

    public static OkHttpChannelBuilder forTarget(String target, ChannelCredentials creds) throws GeneralSecurityException {
        SslSocketFactoryResult result = sslSocketFactoryFrom(creds);
        if (result.error != null) {
            throw new IllegalArgumentException(result.error);
        }
        return new OkHttpChannelBuilder(target, creds, result.callCredentials, result.factory);
    }

    private OkHttpChannelBuilder(String host, int port) {
        this(GrpcUtil.authorityFromHostAndPort(host, port));
    }

    private OkHttpChannelBuilder(String target) {
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        this.scheduledExecutorServicePool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = 65535;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.useGetForSafeMethods = false;
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(target, new OkHttpChannelTransportFactoryBuilder(), new OkHttpChannelDefaultPortProvider());
        this.freezeSecurityConfiguration = false;
    }

    OkHttpChannelBuilder(String target, ChannelCredentials channelCreds, CallCredentials callCreds, SSLSocketFactory factory) {
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        this.scheduledExecutorServicePool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = 65535;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.useGetForSafeMethods = false;
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(target, channelCreds, callCreds, new OkHttpChannelTransportFactoryBuilder(), new OkHttpChannelDefaultPortProvider());
        this.sslSocketFactory = factory;
        this.negotiationType = factory == null ? NegotiationType.PLAINTEXT : NegotiationType.TLS;
        this.freezeSecurityConfiguration = true;
    }

    private final class OkHttpChannelTransportFactoryBuilder implements ManagedChannelImplBuilder.ClientTransportFactoryBuilder {
        private OkHttpChannelTransportFactoryBuilder() {
        }

        @Override // io.grpc.internal.ManagedChannelImplBuilder.ClientTransportFactoryBuilder
        public ClientTransportFactory buildClientTransportFactory() {
            return OkHttpChannelBuilder.this.buildTransportFactory();
        }
    }

    private final class OkHttpChannelDefaultPortProvider implements ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider {
        private OkHttpChannelDefaultPortProvider() {
        }

        @Override // io.grpc.internal.ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider
        public int getDefaultPort() {
            return OkHttpChannelBuilder.this.getDefaultPort();
        }
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder
    protected ManagedChannelBuilder<?> delegate() {
        return this.managedChannelImplBuilder;
    }

    OkHttpChannelBuilder setTransportTracerFactory(TransportTracer.Factory transportTracerFactory) {
        this.transportTracerFactory = transportTracerFactory;
        return this;
    }

    public OkHttpChannelBuilder transportExecutor(@Nullable Executor transportExecutor) {
        if (transportExecutor == null) {
            this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        } else {
            this.transportExecutorPool = new FixedObjectPool(transportExecutor);
        }
        return this;
    }

    public OkHttpChannelBuilder socketFactory(@Nullable SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
        return this;
    }

    @Deprecated
    public OkHttpChannelBuilder negotiationType(io.grpc.okhttp.NegotiationType type) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkNotNull(type, "type");
        switch (type) {
            case TLS:
                this.negotiationType = NegotiationType.TLS;
                return this;
            case PLAINTEXT:
                this.negotiationType = NegotiationType.PLAINTEXT;
                return this;
            default:
                throw new AssertionError("Unknown negotiation type: " + type);
        }
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTime > 0, "keepalive time must be positive");
        this.keepAliveTimeNanos = timeUnit.toNanos(keepAliveTime);
        this.keepAliveTimeNanos = KeepAliveManager.clampKeepAliveTimeInNanos(this.keepAliveTimeNanos);
        if (this.keepAliveTimeNanos >= AS_LARGE_AS_INFINITE) {
            this.keepAliveTimeNanos = Long.MAX_VALUE;
        }
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTimeout > 0, "keepalive timeout must be positive");
        this.keepAliveTimeoutNanos = timeUnit.toNanos(keepAliveTimeout);
        this.keepAliveTimeoutNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(this.keepAliveTimeoutNanos);
        return this;
    }

    public OkHttpChannelBuilder flowControlWindow(int flowControlWindow) {
        Preconditions.checkState(flowControlWindow > 0, "flowControlWindow must be positive");
        this.flowControlWindow = flowControlWindow;
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder keepAliveWithoutCalls(boolean enable) {
        this.keepAliveWithoutCalls = enable;
        return this;
    }

    public OkHttpChannelBuilder sslSocketFactory(SSLSocketFactory factory) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.sslSocketFactory = factory;
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public OkHttpChannelBuilder hostnameVerifier(@Nullable HostnameVerifier hostnameVerifier) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    public OkHttpChannelBuilder connectionSpec(com.squareup.okhttp.ConnectionSpec connectionSpec) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkArgument(connectionSpec.isTls(), "plaintext ConnectionSpec is not accepted");
        this.connectionSpec = Utils.convertSpec(connectionSpec);
        return this;
    }

    public OkHttpChannelBuilder tlsConnectionSpec(String[] tlsVersions, String[] cipherSuites) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkNotNull(tlsVersions, "tls versions must not null");
        Preconditions.checkNotNull(cipherSuites, "ciphers must not null");
        this.connectionSpec = new ConnectionSpec.Builder(true).supportsTlsExtensions(true).tlsVersions(tlsVersions).cipherSuites(cipherSuites).build();
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder usePlaintext() {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.negotiationType = NegotiationType.PLAINTEXT;
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder useTransportSecurity() {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public OkHttpChannelBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorServicePool = new FixedObjectPool((ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder maxInboundMetadataSize(int bytes) {
        Preconditions.checkArgument(bytes > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = bytes;
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder maxInboundMessageSize(int max) {
        Preconditions.checkArgument(max >= 0, "negative max");
        this.maxInboundMessageSize = max;
        return this;
    }

    OkHttpTransportFactory buildTransportFactory() {
        boolean enableKeepAlive = this.keepAliveTimeNanos != Long.MAX_VALUE;
        return new OkHttpTransportFactory(this.transportExecutorPool, this.scheduledExecutorServicePool, this.socketFactory, createSslSocketFactory(), this.hostnameVerifier, this.connectionSpec, this.maxInboundMessageSize, enableKeepAlive, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory, false);
    }

    OkHttpChannelBuilder disableCheckAuthority() {
        this.managedChannelImplBuilder.disableCheckAuthority();
        return this;
    }

    OkHttpChannelBuilder enableCheckAuthority() {
        this.managedChannelImplBuilder.enableCheckAuthority();
        return this;
    }

    int getDefaultPort() {
        switch (this.negotiationType) {
            case PLAINTEXT:
                return 80;
            case TLS:
                return GrpcUtil.DEFAULT_PORT_SSL;
            default:
                throw new AssertionError(this.negotiationType + " not handled");
        }
    }

    void setStatsEnabled(boolean value) {
        this.managedChannelImplBuilder.setStatsEnabled(value);
    }

    @Nullable
    SSLSocketFactory createSslSocketFactory() {
        switch (this.negotiationType) {
            case PLAINTEXT:
                return null;
            case TLS:
                try {
                    if (this.sslSocketFactory == null) {
                        SSLContext sslContext = SSLContext.getInstance("Default", Platform.get().getProvider());
                        this.sslSocketFactory = sslContext.getSocketFactory();
                    }
                    return this.sslSocketFactory;
                } catch (GeneralSecurityException gse) {
                    throw new RuntimeException("TLS Provider failure", gse);
                }
            default:
                throw new RuntimeException("Unknown negotiation type: " + this.negotiationType);
        }
    }

    static SslSocketFactoryResult sslSocketFactoryFrom(ChannelCredentials creds) throws GeneralSecurityException {
        if (creds instanceof TlsChannelCredentials) {
            TlsChannelCredentials tlsCreds = (TlsChannelCredentials) creds;
            Set<TlsChannelCredentials.Feature> incomprehensible = tlsCreds.incomprehensible(understoodTlsFeatures);
            if (!incomprehensible.isEmpty()) {
                return SslSocketFactoryResult.error("TLS features not understood: " + incomprehensible);
            }
            KeyManager[] km = null;
            if (tlsCreds.getKeyManagers() != null) {
                km = (KeyManager[]) tlsCreds.getKeyManagers().toArray(new KeyManager[0]);
            } else if (tlsCreds.getPrivateKey() != null) {
                if (tlsCreds.getPrivateKeyPassword() != null) {
                    return SslSocketFactoryResult.error("byte[]-based private key with password unsupported. Use unencrypted file or KeyManager");
                }
                try {
                    km = createKeyManager(tlsCreds.getCertificateChain(), tlsCreds.getPrivateKey());
                } catch (GeneralSecurityException gse) {
                    log.log(Level.FINE, "Exception loading private key from credential", (Throwable) gse);
                    return SslSocketFactoryResult.error("Unable to load private key: " + gse.getMessage());
                }
            }
            TrustManager[] tm = null;
            if (tlsCreds.getTrustManagers() != null) {
                tm = (TrustManager[]) tlsCreds.getTrustManagers().toArray(new TrustManager[0]);
            } else if (tlsCreds.getRootCertificates() != null) {
                try {
                    tm = createTrustManager(tlsCreds.getRootCertificates());
                } catch (GeneralSecurityException gse2) {
                    log.log(Level.FINE, "Exception loading root certificates from credential", (Throwable) gse2);
                    return SslSocketFactoryResult.error("Unable to load root certificates: " + gse2.getMessage());
                }
            }
            try {
                SSLContext sslContext = SSLContext.getInstance("TLS", Platform.get().getProvider());
                sslContext.init(km, tm, null);
                return SslSocketFactoryResult.factory(sslContext.getSocketFactory());
            } catch (GeneralSecurityException gse3) {
                throw new RuntimeException("TLS Provider failure", gse3);
            }
        }
        if (creds instanceof InsecureChannelCredentials) {
            return SslSocketFactoryResult.plaintext();
        }
        if (creds instanceof CompositeChannelCredentials) {
            CompositeChannelCredentials compCreds = (CompositeChannelCredentials) creds;
            return sslSocketFactoryFrom(compCreds.getChannelCredentials()).withCallCredentials(compCreds.getCallCredentials());
        }
        if (creds instanceof SslSocketFactoryChannelCredentials.ChannelCredentials) {
            SslSocketFactoryChannelCredentials.ChannelCredentials factoryCreds = (SslSocketFactoryChannelCredentials.ChannelCredentials) creds;
            return SslSocketFactoryResult.factory(factoryCreds.getFactory());
        }
        if (creds instanceof ChoiceChannelCredentials) {
            ChoiceChannelCredentials choiceCreds = (ChoiceChannelCredentials) creds;
            StringBuilder error = new StringBuilder();
            for (ChannelCredentials innerCreds : choiceCreds.getCredentialsList()) {
                SslSocketFactoryResult result = sslSocketFactoryFrom(innerCreds);
                if (result.error == null) {
                    return result;
                }
                error.append(", ");
                error.append(result.error);
            }
            return SslSocketFactoryResult.error(error.substring(2));
        }
        return SslSocketFactoryResult.error("Unsupported credential type: " + creds.getClass().getName());
    }

    static KeyManager[] createKeyManager(byte[] certChain, byte[] privateKey) throws GeneralSecurityException {
        ByteArrayInputStream inPrivateKey = new ByteArrayInputStream(certChain);
        try {
            X509Certificate[] chain = CertificateUtils.getX509Certificates(inPrivateKey);
            GrpcUtil.closeQuietly(inPrivateKey);
            inPrivateKey = new ByteArrayInputStream(privateKey);
            try {
                try {
                    PrivateKey key = CertificateUtils.getPrivateKey(inPrivateKey);
                    GrpcUtil.closeQuietly(inPrivateKey);
                    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
                    try {
                        ks.load(null, null);
                        ks.setKeyEntry("key", key, new char[0], chain);
                        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                        keyManagerFactory.init(ks, new char[0]);
                        return keyManagerFactory.getKeyManagers();
                    } catch (IOException ex) {
                        throw new GeneralSecurityException(ex);
                    }
                } finally {
                }
            } catch (IOException uee) {
                throw new GeneralSecurityException("Unable to decode private key", uee);
            }
        } finally {
        }
    }

    static TrustManager[] createTrustManager(byte[] rootCerts) throws GeneralSecurityException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        try {
            ks.load(null, null);
            ByteArrayInputStream in = new ByteArrayInputStream(rootCerts);
            try {
                X509Certificate[] certs = CertificateUtils.getX509Certificates(in);
                GrpcUtil.closeQuietly(in);
                for (X509Certificate cert : certs) {
                    X500Principal principal = cert.getSubjectX500Principal();
                    ks.setCertificateEntry(principal.getName("RFC2253"), cert);
                }
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(ks);
                return trustManagerFactory.getTrustManagers();
            } catch (Throwable th) {
                GrpcUtil.closeQuietly(in);
                throw th;
            }
        } catch (IOException ex) {
            throw new GeneralSecurityException(ex);
        }
    }

    static final class SslSocketFactoryResult {
        public final CallCredentials callCredentials;
        public final String error;
        public final SSLSocketFactory factory;

        private SslSocketFactoryResult(SSLSocketFactory factory, CallCredentials creds, String error) {
            this.factory = factory;
            this.callCredentials = creds;
            this.error = error;
        }

        public static SslSocketFactoryResult error(String error) {
            return new SslSocketFactoryResult(null, null, (String) Preconditions.checkNotNull(error, Constants.IPC_BUNDLE_KEY_SEND_ERROR));
        }

        public static SslSocketFactoryResult plaintext() {
            return new SslSocketFactoryResult(null, null, null);
        }

        public static SslSocketFactoryResult factory(SSLSocketFactory factory) {
            return new SslSocketFactoryResult((SSLSocketFactory) Preconditions.checkNotNull(factory, "factory"), null, null);
        }

        public SslSocketFactoryResult withCallCredentials(CallCredentials callCreds) {
            Preconditions.checkNotNull(callCreds, "callCreds");
            if (this.error != null) {
                return this;
            }
            if (this.callCredentials != null) {
                callCreds = new CompositeCallCredentials(this.callCredentials, callCreds);
            }
            return new SslSocketFactoryResult(this.factory, callCreds, null);
        }
    }

    static final class OkHttpTransportFactory implements ClientTransportFactory {
        private boolean closed;
        final ConnectionSpec connectionSpec;
        private final boolean enableKeepAlive;
        final Executor executor;
        private final ObjectPool<Executor> executorPool;
        final int flowControlWindow;

        @Nullable
        final HostnameVerifier hostnameVerifier;
        private final AtomicBackoff keepAliveBackoff;
        private final long keepAliveTimeNanos;
        private final long keepAliveTimeoutNanos;
        private final boolean keepAliveWithoutCalls;
        final int maxInboundMetadataSize;
        final int maxMessageSize;
        final ScheduledExecutorService scheduledExecutorService;
        private final ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool;
        final SocketFactory socketFactory;

        @Nullable
        final SSLSocketFactory sslSocketFactory;
        final TransportTracer.Factory transportTracerFactory;
        final boolean useGetForSafeMethods;

        private OkHttpTransportFactory(ObjectPool<Executor> executorPool, ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool, @Nullable SocketFactory socketFactory, @Nullable SSLSocketFactory sslSocketFactory, @Nullable HostnameVerifier hostnameVerifier, ConnectionSpec connectionSpec, int maxMessageSize, boolean enableKeepAlive, long keepAliveTimeNanos, long keepAliveTimeoutNanos, int flowControlWindow, boolean keepAliveWithoutCalls, int maxInboundMetadataSize, TransportTracer.Factory transportTracerFactory, boolean useGetForSafeMethods) {
            this.executorPool = executorPool;
            this.executor = executorPool.getObject();
            this.scheduledExecutorServicePool = scheduledExecutorServicePool;
            this.scheduledExecutorService = scheduledExecutorServicePool.getObject();
            this.socketFactory = socketFactory;
            this.sslSocketFactory = sslSocketFactory;
            this.hostnameVerifier = hostnameVerifier;
            this.connectionSpec = connectionSpec;
            this.maxMessageSize = maxMessageSize;
            this.enableKeepAlive = enableKeepAlive;
            this.keepAliveTimeNanos = keepAliveTimeNanos;
            this.keepAliveBackoff = new AtomicBackoff("keepalive time nanos", keepAliveTimeNanos);
            this.keepAliveTimeoutNanos = keepAliveTimeoutNanos;
            this.flowControlWindow = flowControlWindow;
            this.keepAliveWithoutCalls = keepAliveWithoutCalls;
            this.maxInboundMetadataSize = maxInboundMetadataSize;
            this.useGetForSafeMethods = useGetForSafeMethods;
            this.transportTracerFactory = (TransportTracer.Factory) Preconditions.checkNotNull(transportTracerFactory, "transportTracerFactory");
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public ConnectionClientTransport newClientTransport(SocketAddress addr, ClientTransportFactory.ClientTransportOptions options, ChannelLogger channelLogger) {
            if (this.closed) {
                throw new IllegalStateException("The transport factory is closed.");
            }
            final AtomicBackoff.State keepAliveTimeNanosState = this.keepAliveBackoff.getState();
            Runnable tooManyPingsRunnable = new Runnable() { // from class: io.grpc.okhttp.OkHttpChannelBuilder.OkHttpTransportFactory.1
                @Override // java.lang.Runnable
                public void run() {
                    keepAliveTimeNanosState.backoff();
                }
            };
            InetSocketAddress inetSocketAddr = (InetSocketAddress) addr;
            OkHttpClientTransport transport = new OkHttpClientTransport(this, inetSocketAddr, options.getAuthority(), options.getUserAgent(), options.getEagAttributes(), options.getHttpConnectProxiedSocketAddress(), tooManyPingsRunnable);
            if (this.enableKeepAlive) {
                transport.enableKeepAlive(true, keepAliveTimeNanosState.get(), this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
            }
            return transport;
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public ScheduledExecutorService getScheduledExecutorService() {
            return this.scheduledExecutorService;
        }

        @Override // io.grpc.internal.ClientTransportFactory
        @CheckReturnValue
        @Nullable
        public ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCreds) throws GeneralSecurityException {
            SslSocketFactoryResult result = OkHttpChannelBuilder.sslSocketFactoryFrom(channelCreds);
            if (result.error != null) {
                return null;
            }
            ClientTransportFactory factory = new OkHttpTransportFactory(this.executorPool, this.scheduledExecutorServicePool, this.socketFactory, result.factory, this.hostnameVerifier, this.connectionSpec, this.maxMessageSize, this.enableKeepAlive, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory, this.useGetForSafeMethods);
            return new ClientTransportFactory.SwapChannelCredentialsResult(factory, result.callCredentials);
        }

        @Override // io.grpc.internal.ClientTransportFactory, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            this.executorPool.returnObject(this.executor);
            this.scheduledExecutorServicePool.returnObject(this.scheduledExecutorService);
        }
    }
}
