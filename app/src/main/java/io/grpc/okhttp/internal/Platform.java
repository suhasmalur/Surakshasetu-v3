package io.grpc.okhttp.internal;

import com.google.android.gms.security.ProviderInstaller;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.AccessController;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import okio.Buffer;

/* JADX INFO: loaded from: classes10.dex */
public class Platform {
    private final Provider sslProvider;
    public static final Logger logger = Logger.getLogger(Platform.class.getName());
    private static final String[] ANDROID_SECURITY_PROVIDERS = {"com.google.android.gms.org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLProvider", "org.apache.harmony.xnet.provider.jsse.OpenSSLProvider", "com.google.android.libraries.stitch.sslguard.SslGuardProvider"};
    private static final Platform PLATFORM = findPlatform();

    public enum TlsExtensionType {
        ALPN_AND_NPN,
        NPN,
        NONE
    }

    public static Platform get() {
        return PLATFORM;
    }

    public Platform(Provider sslProvider) {
        this.sslProvider = sslProvider;
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public void logW(String warning) {
        System.out.println(warning);
    }

    public void tagSocket(Socket socket) throws SocketException {
    }

    public void untagSocket(Socket socket) throws SocketException {
    }

    public Provider getProvider() {
        return this.sslProvider;
    }

    public TlsExtensionType getTlsExtensionType() {
        return TlsExtensionType.NONE;
    }

    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
    }

    public void afterHandshake(SSLSocket sslSocket) {
    }

    public String getSelectedProtocol(SSLSocket socket) {
        return null;
    }

    public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
        socket.connect(address, connectTimeout);
    }

    private static Platform findPlatform() throws NoSuchMethodException {
        Method trafficStatsTagSocket;
        Method trafficStatsUntagSocket;
        TlsExtensionType tlsExtensionType;
        Provider androidOrAppEngineProvider = getAndroidSecurityProvider();
        if (androidOrAppEngineProvider != null) {
            OptionalMethod<Socket> setUseSessionTickets = new OptionalMethod<>(null, "setUseSessionTickets", Boolean.TYPE);
            OptionalMethod<Socket> setHostname = new OptionalMethod<>(null, "setHostname", String.class);
            Method trafficStatsTagSocket2 = null;
            Method trafficStatsUntagSocket2 = null;
            OptionalMethod<Socket> getAlpnSelectedProtocol = new OptionalMethod<>(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
            OptionalMethod<Socket> setAlpnProtocols = new OptionalMethod<>(null, "setAlpnProtocols", byte[].class);
            try {
                Class<?> trafficStats = Class.forName("android.net.TrafficStats");
                trafficStatsTagSocket2 = trafficStats.getMethod("tagSocket", Socket.class);
                trafficStatsUntagSocket2 = trafficStats.getMethod("untagSocket", Socket.class);
            } catch (ClassNotFoundException e) {
            } catch (NoSuchMethodException e2) {
                trafficStatsTagSocket = trafficStatsTagSocket2;
                trafficStatsUntagSocket = null;
            }
            trafficStatsTagSocket = trafficStatsTagSocket2;
            trafficStatsUntagSocket = trafficStatsUntagSocket2;
            if (androidOrAppEngineProvider.getName().equals(ProviderInstaller.PROVIDER_NAME) || androidOrAppEngineProvider.getName().equals("Conscrypt") || androidOrAppEngineProvider.getName().equals("Ssl_Guard")) {
                TlsExtensionType tlsExtensionType2 = TlsExtensionType.ALPN_AND_NPN;
                tlsExtensionType = tlsExtensionType2;
            } else if (isAtLeastAndroid5()) {
                tlsExtensionType = TlsExtensionType.ALPN_AND_NPN;
            } else if (isAtLeastAndroid41()) {
                tlsExtensionType = TlsExtensionType.NPN;
            } else {
                TlsExtensionType tlsExtensionType3 = TlsExtensionType.NONE;
                tlsExtensionType = tlsExtensionType3;
            }
            return new Android(setUseSessionTickets, setHostname, trafficStatsTagSocket, trafficStatsUntagSocket, getAlpnSelectedProtocol, setAlpnProtocols, androidOrAppEngineProvider, tlsExtensionType);
        }
        try {
            Provider sslProvider = SSLContext.getDefault().getProvider();
            try {
                SSLContext context = SSLContext.getInstance("TLS", sslProvider);
                context.init(null, null, null);
                SSLEngine engine = context.createSSLEngine();
                Method getEngineApplicationProtocol = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() { // from class: io.grpc.okhttp.internal.Platform.1
                    @Override // java.security.PrivilegedExceptionAction
                    public Method run() throws Exception {
                        return SSLEngine.class.getMethod("getApplicationProtocol", new Class[0]);
                    }
                });
                getEngineApplicationProtocol.invoke(engine, new Object[0]);
                Method setApplicationProtocols = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() { // from class: io.grpc.okhttp.internal.Platform.2
                    @Override // java.security.PrivilegedExceptionAction
                    public Method run() throws Exception {
                        return SSLParameters.class.getMethod("setApplicationProtocols", String[].class);
                    }
                });
                Method getApplicationProtocol = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() { // from class: io.grpc.okhttp.internal.Platform.3
                    @Override // java.security.PrivilegedExceptionAction
                    public Method run() throws Exception {
                        return SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
                    }
                });
                return new JdkAlpnPlatform(sslProvider, setApplicationProtocols, getApplicationProtocol);
            } catch (IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException e3) {
                try {
                    Class<?> negoClass = Class.forName("org.eclipse.jetty.alpn.ALPN");
                    Class<?> providerClass = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
                    Class<?> clientProviderClass = Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider");
                    Class<?> serverProviderClass = Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider");
                    Method putMethod = negoClass.getMethod("put", SSLSocket.class, providerClass);
                    Method getMethod = negoClass.getMethod("get", SSLSocket.class);
                    Method removeMethod = negoClass.getMethod("remove", SSLSocket.class);
                    return new JdkWithJettyBootPlatform(putMethod, getMethod, removeMethod, clientProviderClass, serverProviderClass, sslProvider);
                } catch (ClassNotFoundException | NoSuchMethodException e4) {
                    return new Platform(sslProvider);
                }
            }
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }
    }

    private static boolean isAtLeastAndroid5() {
        try {
            Platform.class.getClassLoader().loadClass("android.net.Network");
            return true;
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Can't find class", (Throwable) e);
            return false;
        }
    }

    private static boolean isAtLeastAndroid41() {
        try {
            Platform.class.getClassLoader().loadClass("android.app.ActivityOptions");
            return true;
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Can't find class", (Throwable) e);
            return false;
        }
    }

    private static Provider getAndroidSecurityProvider() {
        Provider[] providers = Security.getProviders();
        for (Provider availableProvider : providers) {
            for (String providerClassName : ANDROID_SECURITY_PROVIDERS) {
                if (providerClassName.equals(availableProvider.getClass().getName())) {
                    logger.log(Level.FINE, "Found registered provider {0}", providerClassName);
                    return availableProvider;
                }
            }
        }
        logger.log(Level.WARNING, "Unable to find Conscrypt");
        return null;
    }

    private static class Android extends Platform {
        private final OptionalMethod<Socket> getAlpnSelectedProtocol;
        private final OptionalMethod<Socket> setAlpnProtocols;
        private final OptionalMethod<Socket> setHostname;
        private final OptionalMethod<Socket> setUseSessionTickets;
        private final TlsExtensionType tlsExtensionType;
        private final Method trafficStatsTagSocket;
        private final Method trafficStatsUntagSocket;

        public Android(OptionalMethod<Socket> setUseSessionTickets, OptionalMethod<Socket> setHostname, Method trafficStatsTagSocket, Method trafficStatsUntagSocket, OptionalMethod<Socket> getAlpnSelectedProtocol, OptionalMethod<Socket> setAlpnProtocols, Provider provider, TlsExtensionType tlsExtensionType) {
            super(provider);
            this.setUseSessionTickets = setUseSessionTickets;
            this.setHostname = setHostname;
            this.trafficStatsTagSocket = trafficStatsTagSocket;
            this.trafficStatsUntagSocket = trafficStatsUntagSocket;
            this.getAlpnSelectedProtocol = getAlpnSelectedProtocol;
            this.setAlpnProtocols = setAlpnProtocols;
            this.tlsExtensionType = tlsExtensionType;
        }

        @Override // io.grpc.okhttp.internal.Platform
        public TlsExtensionType getTlsExtensionType() {
            return this.tlsExtensionType;
        }

        @Override // io.grpc.okhttp.internal.Platform
        public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
            try {
                socket.connect(address, connectTimeout);
            } catch (SecurityException se) {
                IOException ioException = new IOException("Exception in connect");
                ioException.initCause(se);
                throw ioException;
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
            if (hostname != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sslSocket, true);
                this.setHostname.invokeOptionalWithoutCheckedException(sslSocket, hostname);
            }
            if (this.setAlpnProtocols.isSupported(sslSocket)) {
                Object[] parameters = {concatLengthPrefixed(protocols)};
                this.setAlpnProtocols.invokeWithoutCheckedException(sslSocket, parameters);
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public String getSelectedProtocol(SSLSocket socket) {
            byte[] alpnResult;
            if (this.getAlpnSelectedProtocol.isSupported(socket) && (alpnResult = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(socket, new Object[0])) != null) {
                return new String(alpnResult, Util.UTF_8);
            }
            return null;
        }

        @Override // io.grpc.okhttp.internal.Platform
        public void tagSocket(Socket socket) throws SocketException {
            if (this.trafficStatsTagSocket == null) {
                return;
            }
            try {
                this.trafficStatsTagSocket.invoke(null, socket);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public void untagSocket(Socket socket) throws SocketException {
            if (this.trafficStatsUntagSocket == null) {
                return;
            }
            try {
                this.trafficStatsUntagSocket.invoke(null, socket);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    private static class JdkAlpnPlatform extends Platform {
        private final Method getApplicationProtocol;
        private final Method setApplicationProtocols;

        private JdkAlpnPlatform(Provider provider, Method setApplicationProtocols, Method getApplicationProtocol) {
            super(provider);
            this.setApplicationProtocols = setApplicationProtocols;
            this.getApplicationProtocol = getApplicationProtocol;
        }

        @Override // io.grpc.okhttp.internal.Platform
        public TlsExtensionType getTlsExtensionType() {
            return TlsExtensionType.ALPN_AND_NPN;
        }

        @Override // io.grpc.okhttp.internal.Platform
        public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
            SSLParameters parameters = sslSocket.getSSLParameters();
            List<String> names = new ArrayList<>(protocols.size());
            for (Protocol protocol : protocols) {
                if (protocol != Protocol.HTTP_1_0) {
                    names.add(protocol.toString());
                }
            }
            try {
                this.setApplicationProtocols.invoke(parameters, names.toArray(new String[names.size()]));
                sslSocket.setSSLParameters(parameters);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public String getSelectedProtocol(SSLSocket socket) {
            try {
                return (String) this.getApplicationProtocol.invoke(socket, new Object[0]);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    private static class JdkWithJettyBootPlatform extends Platform {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class<?> serverProviderClass;

        public JdkWithJettyBootPlatform(Method putMethod, Method getMethod, Method removeMethod, Class<?> clientProviderClass, Class<?> serverProviderClass, Provider provider) {
            super(provider);
            this.putMethod = putMethod;
            this.getMethod = getMethod;
            this.removeMethod = removeMethod;
            this.clientProviderClass = clientProviderClass;
            this.serverProviderClass = serverProviderClass;
        }

        @Override // io.grpc.okhttp.internal.Platform
        public TlsExtensionType getTlsExtensionType() {
            return TlsExtensionType.ALPN_AND_NPN;
        }

        @Override // io.grpc.okhttp.internal.Platform
        public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
            List<String> names = new ArrayList<>(protocols.size());
            int size = protocols.size();
            for (int i = 0; i < size; i++) {
                Protocol protocol = protocols.get(i);
                if (protocol != Protocol.HTTP_1_0) {
                    names.add(protocol.toString());
                }
            }
            try {
                Object provider = Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new JettyNegoProvider(names));
                this.putMethod.invoke(null, sslSocket, provider);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public void afterHandshake(SSLSocket sslSocket) {
            try {
                this.removeMethod.invoke(null, sslSocket);
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            } catch (InvocationTargetException ex) {
                logger.log(Level.FINE, "Failed to remove SSLSocket from Jetty ALPN", (Throwable) ex);
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public String getSelectedProtocol(SSLSocket socket) {
            try {
                JettyNegoProvider provider = (JettyNegoProvider) Proxy.getInvocationHandler(this.getMethod.invoke(null, socket));
                if (!provider.unsupported && provider.selected == null) {
                    logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                }
                if (provider.unsupported) {
                    return null;
                }
                return provider.selected;
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                throw new AssertionError();
            }
        }
    }

    private static class JettyNegoProvider implements InvocationHandler {
        private final List<String> protocols;
        private String selected;
        private boolean unsupported;

        public JettyNegoProvider(List<String> protocols) {
            this.protocols = protocols;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            Class<?> returnType = method.getReturnType();
            if (args == null) {
                args = Util.EMPTY_STRING_ARRAY;
            }
            if (methodName.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (methodName.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            }
            if (methodName.equals("protocols") && args.length == 0) {
                return this.protocols;
            }
            if ((methodName.equals("selectProtocol") || methodName.equals("select")) && String.class == returnType && args.length == 1 && (args[0] instanceof List)) {
                List<String> peerProtocols = (List) args[0];
                int size = peerProtocols.size();
                for (int i = 0; i < size; i++) {
                    if (this.protocols.contains(peerProtocols.get(i))) {
                        String str = peerProtocols.get(i);
                        this.selected = str;
                        return str;
                    }
                }
                String str2 = this.protocols.get(0);
                this.selected = str2;
                return str2;
            }
            if ((methodName.equals("protocolSelected") || methodName.equals("selected")) && args.length == 1) {
                this.selected = (String) args[0];
                return null;
            }
            return method.invoke(this, args);
        }
    }

    public static byte[] concatLengthPrefixed(List<Protocol> protocols) {
        Buffer result = new Buffer();
        int size = protocols.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = protocols.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                result.writeByte(protocol.toString().length());
                result.writeUtf8(protocol.toString());
            }
        }
        return result.readByteArray();
    }
}
