package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ManagedChannelProvider;

/* JADX INFO: loaded from: classes10.dex */
public abstract class ServerProvider {
    protected abstract ServerBuilder<?> builderForPort(int i);

    protected abstract boolean isAvailable();

    protected abstract int priority();

    public static ServerProvider provider() {
        ServerProvider provider = ServerRegistry.getDefaultRegistry().provider();
        if (provider == null) {
            throw new ManagedChannelProvider.ProviderNotFoundException("No functional server found. Try adding a dependency on the grpc-netty or grpc-netty-shaded artifact");
        }
        return provider;
    }

    protected NewServerBuilderResult newServerBuilderForPort(int port, ServerCredentials creds) {
        return NewServerBuilderResult.error("ServerCredentials are unsupported");
    }

    public static final class NewServerBuilderResult {
        private final String error;
        private final ServerBuilder<?> serverBuilder;

        private NewServerBuilderResult(ServerBuilder<?> serverBuilder, String error) {
            this.serverBuilder = serverBuilder;
            this.error = error;
        }

        public static NewServerBuilderResult serverBuilder(ServerBuilder<?> builder) {
            return new NewServerBuilderResult((ServerBuilder) Preconditions.checkNotNull(builder), null);
        }

        public static NewServerBuilderResult error(String error) {
            return new NewServerBuilderResult(null, (String) Preconditions.checkNotNull(error));
        }

        public ServerBuilder<?> getServerBuilder() {
            return this.serverBuilder;
        }

        public String getError() {
            return this.error;
        }
    }
}
