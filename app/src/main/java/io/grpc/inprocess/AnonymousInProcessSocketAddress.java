package io.grpc.inprocess;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.SocketAddress;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public final class AnonymousInProcessSocketAddress extends SocketAddress {
    private static final long serialVersionUID = -8567592561863414695L;

    @Nullable
    private InProcessServer server;

    @Nullable
    synchronized InProcessServer getServer() {
        return this.server;
    }

    synchronized void setServer(InProcessServer server) throws IOException {
        if (this.server != null) {
            throw new IOException("Server instance already registered");
        }
        this.server = server;
    }

    synchronized void clearServer(InProcessServer server) {
        Preconditions.checkState(this.server == server);
        this.server = null;
    }
}
