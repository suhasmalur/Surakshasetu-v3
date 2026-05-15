package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ServerStreamTracer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
final class GlobalInterceptors {
    private static boolean isGlobalInterceptorsTracersGet;
    private static boolean isGlobalInterceptorsTracersSet;
    private static List<ClientInterceptor> clientInterceptors = null;
    private static List<ServerInterceptor> serverInterceptors = null;
    private static List<ServerStreamTracer.Factory> serverStreamTracerFactories = null;

    private GlobalInterceptors() {
    }

    static synchronized void setInterceptorsTracers(List<ClientInterceptor> clientInterceptorList, List<ServerInterceptor> serverInterceptorList, List<ServerStreamTracer.Factory> serverStreamTracerFactoryList) {
        if (isGlobalInterceptorsTracersGet) {
            throw new IllegalStateException("Set cannot be called after any get call");
        }
        if (isGlobalInterceptorsTracersSet) {
            throw new IllegalStateException("Global interceptors and tracers are already set");
        }
        Preconditions.checkNotNull(clientInterceptorList);
        Preconditions.checkNotNull(serverInterceptorList);
        Preconditions.checkNotNull(serverStreamTracerFactoryList);
        clientInterceptors = Collections.unmodifiableList(new ArrayList(clientInterceptorList));
        serverInterceptors = Collections.unmodifiableList(new ArrayList(serverInterceptorList));
        serverStreamTracerFactories = Collections.unmodifiableList(new ArrayList(serverStreamTracerFactoryList));
        isGlobalInterceptorsTracersSet = true;
    }

    static synchronized List<ClientInterceptor> getClientInterceptors() {
        isGlobalInterceptorsTracersGet = true;
        return clientInterceptors;
    }

    static synchronized List<ServerInterceptor> getServerInterceptors() {
        isGlobalInterceptorsTracersGet = true;
        return serverInterceptors;
    }

    static synchronized List<ServerStreamTracer.Factory> getServerStreamTracerFactories() {
        isGlobalInterceptorsTracersGet = true;
        return serverStreamTracerFactories;
    }
}
