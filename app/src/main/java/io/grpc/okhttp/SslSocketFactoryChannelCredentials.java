package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes10.dex */
public final class SslSocketFactoryChannelCredentials {
    private SslSocketFactoryChannelCredentials() {
    }

    public static io.grpc.ChannelCredentials create(SSLSocketFactory factory) {
        return new ChannelCredentials(factory);
    }

    static final class ChannelCredentials extends io.grpc.ChannelCredentials {
        private final SSLSocketFactory factory;

        private ChannelCredentials(SSLSocketFactory factory) {
            this.factory = (SSLSocketFactory) Preconditions.checkNotNull(factory, "factory");
        }

        public SSLSocketFactory getFactory() {
            return this.factory;
        }

        @Override // io.grpc.ChannelCredentials
        public io.grpc.ChannelCredentials withoutBearerTokens() {
            return this;
        }
    }
}
