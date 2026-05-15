package io.grpc;

import com.google.common.base.Preconditions;
import java.net.SocketAddress;
import java.util.Collection;

/* JADX INFO: loaded from: classes10.dex */
public abstract class ManagedChannelProvider {
    protected abstract ManagedChannelBuilder<?> builderForAddress(String str, int i);

    protected abstract ManagedChannelBuilder<?> builderForTarget(String str);

    protected abstract Collection<Class<? extends SocketAddress>> getSupportedSocketAddressTypes();

    protected abstract boolean isAvailable();

    protected abstract int priority();

    public static ManagedChannelProvider provider() {
        ManagedChannelProvider provider = ManagedChannelRegistry.getDefaultRegistry().provider();
        if (provider == null) {
            throw new ProviderNotFoundException("No functional channel service provider found. Try adding a dependency on the grpc-okhttp, grpc-netty, or grpc-netty-shaded artifact");
        }
        return provider;
    }

    protected NewChannelBuilderResult newChannelBuilder(String target, ChannelCredentials creds) {
        return NewChannelBuilderResult.error("ChannelCredentials are unsupported");
    }

    public static final class NewChannelBuilderResult {
        private final ManagedChannelBuilder<?> channelBuilder;
        private final String error;

        private NewChannelBuilderResult(ManagedChannelBuilder<?> channelBuilder, String error) {
            this.channelBuilder = channelBuilder;
            this.error = error;
        }

        public static NewChannelBuilderResult channelBuilder(ManagedChannelBuilder<?> builder) {
            return new NewChannelBuilderResult((ManagedChannelBuilder) Preconditions.checkNotNull(builder), null);
        }

        public static NewChannelBuilderResult error(String error) {
            return new NewChannelBuilderResult(null, (String) Preconditions.checkNotNull(error));
        }

        public ManagedChannelBuilder<?> getChannelBuilder() {
            return this.channelBuilder;
        }

        public String getError() {
            return this.error;
        }
    }

    public static final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException(String msg) {
            super(msg);
        }
    }
}
