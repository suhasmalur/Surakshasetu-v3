package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.internal.GrpcUtil;
import io.grpc.okhttp.internal.OptionalMethod;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.Protocol;
import io.grpc.okhttp.internal.Util;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes10.dex */
class OkHttpProtocolNegotiator {
    protected final Platform platform;
    private static final Logger logger = Logger.getLogger(OkHttpProtocolNegotiator.class.getName());
    private static final Platform DEFAULT_PLATFORM = Platform.get();
    private static OkHttpProtocolNegotiator NEGOTIATOR = createNegotiator(OkHttpProtocolNegotiator.class.getClassLoader());

    OkHttpProtocolNegotiator(Platform platform) {
        this.platform = (Platform) Preconditions.checkNotNull(platform, "platform");
    }

    public static OkHttpProtocolNegotiator get() {
        return NEGOTIATOR;
    }

    static OkHttpProtocolNegotiator createNegotiator(ClassLoader loader) {
        boolean android2 = true;
        try {
            loader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
        } catch (ClassNotFoundException e1) {
            logger.log(Level.FINE, "Unable to find Conscrypt. Skipping", (Throwable) e1);
            try {
                loader.loadClass("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            } catch (ClassNotFoundException e2) {
                logger.log(Level.FINE, "Unable to find any OpenSSLSocketImpl. Skipping", (Throwable) e2);
                android2 = false;
            }
        }
        if (android2) {
            return new AndroidNegotiator(DEFAULT_PLATFORM);
        }
        return new OkHttpProtocolNegotiator(DEFAULT_PLATFORM);
    }

    public String negotiate(SSLSocket sslSocket, String hostname, @Nullable List<Protocol> protocols) throws IOException {
        if (protocols != null) {
            configureTlsExtensions(sslSocket, hostname, protocols);
        }
        try {
            sslSocket.startHandshake();
            String negotiatedProtocol = getSelectedProtocol(sslSocket);
            if (negotiatedProtocol == null) {
                throw new RuntimeException("TLS ALPN negotiation failed with protocols: " + protocols);
            }
            return negotiatedProtocol;
        } finally {
            this.platform.afterHandshake(sslSocket);
        }
    }

    protected void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
        this.platform.configureTlsExtensions(sslSocket, hostname, protocols);
    }

    public String getSelectedProtocol(SSLSocket socket) {
        return this.platform.getSelectedProtocol(socket);
    }

    static final class AndroidNegotiator extends OkHttpProtocolNegotiator {
        private static final Method GET_APPLICATION_PROTOCOL;
        private static final Method GET_APPLICATION_PROTOCOLS;
        private static final Method SET_APPLICATION_PROTOCOLS;
        private static final Method SET_SERVER_NAMES;
        private static final Constructor<?> SNI_HOST_NAME;
        private static final Method SSL_SOCKETS_IS_SUPPORTED_SOCKET;
        private static final Method SSL_SOCKETS_SET_USE_SESSION_TICKET;
        private static final OptionalMethod<Socket> SET_USE_SESSION_TICKETS = new OptionalMethod<>(null, "setUseSessionTickets", Boolean.TYPE);
        private static final OptionalMethod<Socket> SET_HOSTNAME = new OptionalMethod<>(null, "setHostname", String.class);
        private static final OptionalMethod<Socket> GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod<>(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod<Socket> SET_ALPN_PROTOCOLS = new OptionalMethod<>(null, "setAlpnProtocols", byte[].class);
        private static final OptionalMethod<Socket> GET_NPN_SELECTED_PROTOCOL = new OptionalMethod<>(byte[].class, "getNpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod<Socket> SET_NPN_PROTOCOLS = new OptionalMethod<>(null, "setNpnProtocols", byte[].class);

        static {
            Method setApplicationProtocolsMethod = null;
            Method getApplicationProtocolsMethod = null;
            Method getApplicationProtocolMethod = null;
            Method sslSocketsIsSupportedSocketMethod = null;
            Method sslSocketsSetUseSessionTicketsMethod = null;
            try {
                setApplicationProtocolsMethod = SSLParameters.class.getMethod("setApplicationProtocols", String[].class);
                getApplicationProtocolsMethod = SSLParameters.class.getMethod("getApplicationProtocols", new Class[0]);
                getApplicationProtocolMethod = SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
                Class<?> sslSockets = Class.forName("android.net.ssl.SSLSockets");
                sslSocketsIsSupportedSocketMethod = sslSockets.getMethod("isSupportedSocket", SSLSocket.class);
                sslSocketsSetUseSessionTicketsMethod = sslSockets.getMethod("setUseSessionTickets", SSLSocket.class, Boolean.TYPE);
            } catch (ClassNotFoundException e) {
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", (Throwable) e);
            } catch (NoSuchMethodException e2) {
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 10.0+ APIs", (Throwable) e2);
            }
            SET_APPLICATION_PROTOCOLS = setApplicationProtocolsMethod;
            GET_APPLICATION_PROTOCOLS = getApplicationProtocolsMethod;
            GET_APPLICATION_PROTOCOL = getApplicationProtocolMethod;
            SSL_SOCKETS_IS_SUPPORTED_SOCKET = sslSocketsIsSupportedSocketMethod;
            SSL_SOCKETS_SET_USE_SESSION_TICKET = sslSocketsSetUseSessionTicketsMethod;
            Method setServerNamesMethod = null;
            Constructor<?> sniHostNameConstructor = null;
            try {
                setServerNamesMethod = SSLParameters.class.getMethod("setServerNames", List.class);
                sniHostNameConstructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(String.class);
            } catch (ClassNotFoundException e3) {
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 7.0+ APIs", (Throwable) e3);
            } catch (NoSuchMethodException e4) {
                OkHttpProtocolNegotiator.logger.log(Level.FINER, "Failed to find Android 7.0+ APIs", (Throwable) e4);
            }
            SET_SERVER_NAMES = setServerNamesMethod;
            SNI_HOST_NAME = sniHostNameConstructor;
        }

        AndroidNegotiator(Platform platform) {
            super(platform);
        }

        @Override // io.grpc.okhttp.OkHttpProtocolNegotiator
        public String negotiate(SSLSocket sslSocket, String hostname, List<Protocol> protocols) throws IOException {
            String negotiatedProtocol = getSelectedProtocol(sslSocket);
            if (negotiatedProtocol == null) {
                return super.negotiate(sslSocket, hostname, protocols);
            }
            return negotiatedProtocol;
        }

        @Override // io.grpc.okhttp.OkHttpProtocolNegotiator
        protected void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
            String[] protocolNames = OkHttpProtocolNegotiator.protocolIds(protocols);
            SSLParameters sslParams = sslSocket.getSSLParameters();
            if (hostname != null) {
                try {
                    try {
                        if (isValidHostName(hostname)) {
                            if (SSL_SOCKETS_IS_SUPPORTED_SOCKET != null && ((Boolean) SSL_SOCKETS_IS_SUPPORTED_SOCKET.invoke(null, sslSocket)).booleanValue()) {
                                SSL_SOCKETS_SET_USE_SESSION_TICKET.invoke(null, sslSocket, true);
                            } else {
                                SET_USE_SESSION_TICKETS.invokeOptionalWithoutCheckedException(sslSocket, true);
                            }
                            if (SET_SERVER_NAMES != null && SNI_HOST_NAME != null) {
                                SET_SERVER_NAMES.invoke(sslParams, Collections.singletonList(SNI_HOST_NAME.newInstance(hostname)));
                            } else {
                                SET_HOSTNAME.invokeOptionalWithoutCheckedException(sslSocket, hostname);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InstantiationException e2) {
                        throw new RuntimeException(e2);
                    }
                } catch (InvocationTargetException e3) {
                    throw new RuntimeException(e3);
                }
            }
            boolean alpnEnabled = false;
            if (GET_APPLICATION_PROTOCOL != null) {
                try {
                    GET_APPLICATION_PROTOCOL.invoke(sslSocket, new Object[0]);
                    SET_APPLICATION_PROTOCOLS.invoke(sslParams, protocolNames);
                    alpnEnabled = true;
                } catch (InvocationTargetException e4) {
                    Throwable targetException = e4.getTargetException();
                    if (!(targetException instanceof UnsupportedOperationException)) {
                        throw e4;
                    }
                    OkHttpProtocolNegotiator.logger.log(Level.FINER, "setApplicationProtocol unsupported, will try old methods");
                }
            }
            sslSocket.setSSLParameters(sslParams);
            if (alpnEnabled && GET_APPLICATION_PROTOCOLS != null) {
                String[] configuredProtocols = (String[]) GET_APPLICATION_PROTOCOLS.invoke(sslSocket.getSSLParameters(), new Object[0]);
                if (Arrays.equals(protocolNames, configuredProtocols)) {
                    return;
                }
            }
            Object[] parameters = {Platform.concatLengthPrefixed(protocols)};
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.ALPN_AND_NPN) {
                SET_ALPN_PROTOCOLS.invokeWithoutCheckedException(sslSocket, parameters);
            }
            if (this.platform.getTlsExtensionType() != Platform.TlsExtensionType.NONE) {
                SET_NPN_PROTOCOLS.invokeWithoutCheckedException(sslSocket, parameters);
                return;
            }
            throw new RuntimeException("We can not do TLS handshake on this Android version, please install the Google Play Services Dynamic Security Provider to use TLS");
        }

        @Override // io.grpc.okhttp.OkHttpProtocolNegotiator
        public String getSelectedProtocol(SSLSocket socket) {
            if (GET_APPLICATION_PROTOCOL != null) {
                try {
                    return (String) GET_APPLICATION_PROTOCOL.invoke(socket, new Object[0]);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    Throwable targetException = e2.getTargetException();
                    if (targetException instanceof UnsupportedOperationException) {
                        OkHttpProtocolNegotiator.logger.log(Level.FINER, "Socket unsupported for getApplicationProtocol, will try old methods");
                    } else {
                        throw new RuntimeException(e2);
                    }
                }
            }
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.ALPN_AND_NPN) {
                try {
                    byte[] alpnResult = (byte[]) GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(socket, new Object[0]);
                    if (alpnResult != null) {
                        return new String(alpnResult, Util.UTF_8);
                    }
                } catch (Exception e3) {
                    OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getAlpnSelectedProtocol()", (Throwable) e3);
                }
            }
            if (this.platform.getTlsExtensionType() != Platform.TlsExtensionType.NONE) {
                try {
                    byte[] npnResult = (byte[]) GET_NPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(socket, new Object[0]);
                    if (npnResult != null) {
                        return new String(npnResult, Util.UTF_8);
                    }
                    return null;
                } catch (Exception e4) {
                    OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getNpnSelectedProtocol()", (Throwable) e4);
                    return null;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] protocolIds(List<Protocol> protocols) {
        List<String> result = new ArrayList<>();
        for (Protocol protocol : protocols) {
            result.add(protocol.toString());
        }
        return (String[]) result.toArray(new String[0]);
    }

    static boolean isValidHostName(String name) {
        if (name.contains("_")) {
            return false;
        }
        try {
            GrpcUtil.checkAuthority(name);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
