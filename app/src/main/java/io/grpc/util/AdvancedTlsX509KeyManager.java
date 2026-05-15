package io.grpc.util;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;

/* JADX INFO: loaded from: classes10.dex */
public final class AdvancedTlsX509KeyManager extends X509ExtendedKeyManager {
    private static final Logger log = Logger.getLogger(AdvancedTlsX509KeyManager.class.getName());
    private volatile KeyInfo keyInfo;

    public interface Closeable extends java.io.Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        void close();
    }

    @Override // javax.net.ssl.X509KeyManager
    public PrivateKey getPrivateKey(String alias) {
        if (alias.equals("default")) {
            return this.keyInfo.key;
        }
        return null;
    }

    @Override // javax.net.ssl.X509KeyManager
    public X509Certificate[] getCertificateChain(String alias) {
        if (alias.equals("default")) {
            return (X509Certificate[]) Arrays.copyOf(this.keyInfo.certs, this.keyInfo.certs.length);
        }
        return null;
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getClientAliases(String keyType, Principal[] issuers) {
        return new String[]{"default"};
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
        return "default";
    }

    @Override // javax.net.ssl.X509ExtendedKeyManager
    public String chooseEngineClientAlias(String[] keyType, Principal[] issuers, SSLEngine engine) {
        return "default";
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getServerAliases(String keyType, Principal[] issuers) {
        return new String[]{"default"};
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        return "default";
    }

    @Override // javax.net.ssl.X509ExtendedKeyManager
    public String chooseEngineServerAlias(String keyType, Principal[] issuers, SSLEngine engine) {
        return "default";
    }

    public void updateIdentityCredentials(PrivateKey key, X509Certificate[] certs) {
        this.keyInfo = new KeyInfo((PrivateKey) Preconditions.checkNotNull(key, "key"), (X509Certificate[]) Preconditions.checkNotNull(certs, "certs"));
    }

    public Closeable updateIdentityCredentialsFromFile(File keyFile, File certFile, long period, TimeUnit unit, ScheduledExecutorService executor) throws Throwable {
        UpdateResult newResult = readAndUpdate(keyFile, certFile, 0L, 0L);
        if (!newResult.success) {
            throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
        }
        final ScheduledFuture<?> future = executor.scheduleWithFixedDelay(new LoadFilePathExecution(keyFile, certFile), period, period, unit);
        return new Closeable() { // from class: io.grpc.util.AdvancedTlsX509KeyManager.1
            @Override // io.grpc.util.AdvancedTlsX509KeyManager.Closeable, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                future.cancel(false);
            }
        };
    }

    public void updateIdentityCredentialsFromFile(File keyFile, File certFile) throws Throwable {
        UpdateResult newResult = readAndUpdate(keyFile, certFile, 0L, 0L);
        if (!newResult.success) {
            throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
        }
    }

    private static class KeyInfo {
        final X509Certificate[] certs;
        final PrivateKey key;

        public KeyInfo(PrivateKey key, X509Certificate[] certs) {
            this.key = key;
            this.certs = certs;
        }
    }

    private class LoadFilePathExecution implements Runnable {
        File certFile;
        File keyFile;
        long currentKeyTime = 0;
        long currentCertTime = 0;

        public LoadFilePathExecution(File keyFile, File certFile) {
            this.keyFile = keyFile;
            this.certFile = certFile;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                UpdateResult newResult = AdvancedTlsX509KeyManager.this.readAndUpdate(this.keyFile, this.certFile, this.currentKeyTime, this.currentCertTime);
                if (newResult.success) {
                    this.currentKeyTime = newResult.keyTime;
                    this.currentCertTime = newResult.certTime;
                }
            } catch (IOException | GeneralSecurityException e) {
                AdvancedTlsX509KeyManager.log.log(Level.SEVERE, "Failed refreshing private key and certificate chain from files. Using previous ones", (Throwable) e);
            }
        }
    }

    private static class UpdateResult {
        long certTime;
        long keyTime;
        boolean success;

        public UpdateResult(boolean success, long keyTime, long certTime) {
            this.success = success;
            this.keyTime = keyTime;
            this.certTime = certTime;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UpdateResult readAndUpdate(File keyFile, File certFile, long oldKeyTime, long oldCertTime) throws Throwable {
        PrivateKey key;
        long newKeyTime = keyFile.lastModified();
        long newCertTime = certFile.lastModified();
        if (newKeyTime != oldKeyTime && newCertTime != oldCertTime) {
            FileInputStream certInputStream = new FileInputStream(keyFile);
            try {
                key = CertificateUtils.getPrivateKey(certInputStream);
            } catch (Throwable th) {
                th = th;
            }
            try {
                certInputStream = new FileInputStream(certFile);
                try {
                    X509Certificate[] certs = CertificateUtils.getX509Certificates(certInputStream);
                    updateIdentityCredentials(key, certs);
                    UpdateResult updateResult = new UpdateResult(true, newKeyTime, newCertTime);
                    certInputStream.close();
                    return updateResult;
                } finally {
                    certInputStream.close();
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
        return new UpdateResult(false, oldKeyTime, oldCertTime);
    }
}
