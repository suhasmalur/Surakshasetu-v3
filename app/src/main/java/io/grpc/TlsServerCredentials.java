package io.grpc;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;

/* JADX INFO: loaded from: classes10.dex */
public final class TlsServerCredentials extends ServerCredentials {
    private final byte[] certificateChain;
    private final ClientAuth clientAuth;
    private final boolean fakeFeature;
    private final List<KeyManager> keyManagers;
    private final byte[] privateKey;
    private final String privateKeyPassword;
    private final byte[] rootCertificates;
    private final List<TrustManager> trustManagers;

    public enum ClientAuth {
        NONE,
        OPTIONAL,
        REQUIRE
    }

    public enum Feature {
        FAKE,
        MTLS,
        CUSTOM_MANAGERS
    }

    public static ServerCredentials create(File certChain, File privateKey) throws IOException {
        return newBuilder().keyManager(certChain, privateKey).build();
    }

    public static ServerCredentials create(InputStream certChain, InputStream privateKey) throws IOException {
        return newBuilder().keyManager(certChain, privateKey).build();
    }

    TlsServerCredentials(Builder builder) {
        this.fakeFeature = builder.fakeFeature;
        this.certificateChain = builder.certificateChain;
        this.privateKey = builder.privateKey;
        this.privateKeyPassword = builder.privateKeyPassword;
        this.keyManagers = builder.keyManagers;
        this.clientAuth = builder.clientAuth;
        this.rootCertificates = builder.rootCertificates;
        this.trustManagers = builder.trustManagers;
    }

    public byte[] getCertificateChain() {
        if (this.certificateChain == null) {
            return null;
        }
        return Arrays.copyOf(this.certificateChain, this.certificateChain.length);
    }

    public byte[] getPrivateKey() {
        if (this.privateKey == null) {
            return null;
        }
        return Arrays.copyOf(this.privateKey, this.privateKey.length);
    }

    public String getPrivateKeyPassword() {
        return this.privateKeyPassword;
    }

    public List<KeyManager> getKeyManagers() {
        return this.keyManagers;
    }

    public ClientAuth getClientAuth() {
        return this.clientAuth;
    }

    public byte[] getRootCertificates() {
        if (this.rootCertificates == null) {
            return null;
        }
        return Arrays.copyOf(this.rootCertificates, this.rootCertificates.length);
    }

    public List<TrustManager> getTrustManagers() {
        return this.trustManagers;
    }

    public Set<Feature> incomprehensible(Set<Feature> understoodFeatures) {
        Set<Feature> incomprehensible = EnumSet.noneOf(Feature.class);
        if (this.fakeFeature) {
            requiredFeature(understoodFeatures, incomprehensible, Feature.FAKE);
        }
        if (this.clientAuth != ClientAuth.NONE) {
            requiredFeature(understoodFeatures, incomprehensible, Feature.MTLS);
        }
        if (this.keyManagers != null || this.trustManagers != null) {
            requiredFeature(understoodFeatures, incomprehensible, Feature.CUSTOM_MANAGERS);
        }
        return Collections.unmodifiableSet(incomprehensible);
    }

    private static void requiredFeature(Set<Feature> understoodFeatures, Set<Feature> incomprehensible, Feature feature) {
        if (!understoodFeatures.contains(feature)) {
            incomprehensible.add(feature);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private byte[] certificateChain;
        private ClientAuth clientAuth;
        private boolean fakeFeature;
        private List<KeyManager> keyManagers;
        private byte[] privateKey;
        private String privateKeyPassword;
        private byte[] rootCertificates;
        private List<TrustManager> trustManagers;

        private Builder() {
            this.clientAuth = ClientAuth.NONE;
        }

        public Builder requireFakeFeature() {
            this.fakeFeature = true;
            return this;
        }

        public Builder keyManager(File certChain, File privateKey) throws IOException {
            return keyManager(certChain, privateKey, (String) null);
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x001a, code lost:
        
            r1 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x001e, code lost:
        
            throw r1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public io.grpc.TlsServerCredentials.Builder keyManager(java.io.File r4, java.io.File r5, java.lang.String r6) throws java.io.IOException {
            /*
                r3 = this;
                java.io.FileInputStream r0 = new java.io.FileInputStream
                r0.<init>(r4)
                java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L1a
                r1.<init>(r5)     // Catch: java.lang.Throwable -> L1a
                io.grpc.TlsServerCredentials$Builder r2 = r3.keyManager(r0, r1, r6)     // Catch: java.lang.Throwable -> L15
                r1.close()     // Catch: java.lang.Throwable -> L1a
                r0.close()
                return r2
            L15:
                r2 = move-exception
                r1.close()     // Catch: java.lang.Throwable -> L1a
                throw r2     // Catch: java.lang.Throwable -> L1a
            L1a:
                r1 = move-exception
                r0.close()
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.TlsServerCredentials.Builder.keyManager(java.io.File, java.io.File, java.lang.String):io.grpc.TlsServerCredentials$Builder");
        }

        public Builder keyManager(InputStream certChain, InputStream privateKey) throws IOException {
            return keyManager(certChain, privateKey, (String) null);
        }

        public Builder keyManager(InputStream certChain, InputStream privateKey, String privateKeyPassword) throws IOException {
            byte[] certChainBytes = ByteStreams.toByteArray(certChain);
            byte[] privateKeyBytes = ByteStreams.toByteArray(privateKey);
            clearKeyManagers();
            this.certificateChain = certChainBytes;
            this.privateKey = privateKeyBytes;
            this.privateKeyPassword = privateKeyPassword;
            return this;
        }

        public Builder keyManager(KeyManager... keyManagers) {
            List<KeyManager> keyManagerList = Collections.unmodifiableList(new ArrayList(Arrays.asList(keyManagers)));
            clearKeyManagers();
            this.keyManagers = keyManagerList;
            return this;
        }

        private void clearKeyManagers() {
            this.certificateChain = null;
            this.privateKey = null;
            this.privateKeyPassword = null;
            this.keyManagers = null;
        }

        public Builder clientAuth(ClientAuth clientAuth) {
            Preconditions.checkNotNull(clientAuth, "clientAuth");
            this.clientAuth = clientAuth;
            return this;
        }

        public Builder trustManager(File rootCerts) throws IOException {
            InputStream rootCertsIs = new FileInputStream(rootCerts);
            try {
                return trustManager(rootCertsIs);
            } finally {
                rootCertsIs.close();
            }
        }

        public Builder trustManager(InputStream rootCerts) throws IOException {
            byte[] rootCertsBytes = ByteStreams.toByteArray(rootCerts);
            clearTrustManagers();
            this.rootCertificates = rootCertsBytes;
            return this;
        }

        public Builder trustManager(TrustManager... trustManagers) {
            List<TrustManager> trustManagerList = Collections.unmodifiableList(new ArrayList(Arrays.asList(trustManagers)));
            clearTrustManagers();
            this.trustManagers = trustManagerList;
            return this;
        }

        private void clearTrustManagers() {
            this.rootCertificates = null;
            this.trustManagers = null;
        }

        public ServerCredentials build() {
            if (this.certificateChain == null && this.keyManagers == null) {
                throw new IllegalStateException("A key manager is required");
            }
            return new TlsServerCredentials(this);
        }
    }
}
