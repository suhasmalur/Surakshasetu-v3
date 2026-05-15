package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.squareup.okhttp.ConnectionSpec;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes10.dex */
public final class SslSocketFactoryServerCredentials {
    private SslSocketFactoryServerCredentials() {
    }

    public static io.grpc.ServerCredentials create(SSLSocketFactory factory) {
        return new ServerCredentials(factory);
    }

    public static io.grpc.ServerCredentials create(SSLSocketFactory factory, ConnectionSpec connectionSpec) {
        return new ServerCredentials(factory, Utils.convertSpec(connectionSpec));
    }

    static final class ServerCredentials extends io.grpc.ServerCredentials {
        private final io.grpc.okhttp.internal.ConnectionSpec connectionSpec;
        private final SSLSocketFactory factory;

        ServerCredentials(SSLSocketFactory factory) {
            this(factory, OkHttpChannelBuilder.INTERNAL_DEFAULT_CONNECTION_SPEC);
        }

        ServerCredentials(SSLSocketFactory factory, io.grpc.okhttp.internal.ConnectionSpec connectionSpec) {
            this.factory = (SSLSocketFactory) Preconditions.checkNotNull(factory, "factory");
            this.connectionSpec = (io.grpc.okhttp.internal.ConnectionSpec) Preconditions.checkNotNull(connectionSpec, "connectionSpec");
        }

        public SSLSocketFactory getFactory() {
            return this.factory;
        }

        public io.grpc.okhttp.internal.ConnectionSpec getConnectionSpec() {
            return this.connectionSpec;
        }
    }
}
