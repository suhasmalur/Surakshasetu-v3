package com.google.android.gms.internal.p001firebaseauthapi;

import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import com.google.android.gms.stats.CodePackage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.KeyGenerator;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmd implements zzcc {
    private static final Object zza = new Object();
    private static final String zzb = zzmd.class.getSimpleName();
    private final String zzc;
    private KeyStore zzd;

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza {
        KeyStore zza;
        private String zzb = null;

        public zza() {
            this.zza = null;
            if (!zzmd.zza()) {
                throw new IllegalStateException("need Android Keystore on Android M or newer");
            }
            try {
                this.zza = KeyStore.getInstance("AndroidKeyStore");
                this.zza.load(null);
            } catch (IOException | GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcc
    public final synchronized zzbg zza(String str) throws GeneralSecurityException {
        zzmb zzmbVar;
        zzmbVar = new zzmb(zzxo.zza("android-keystore://", str), this.zzd);
        byte[] bArrZza = zzou.zza(10);
        byte[] bArr = new byte[0];
        if (!Arrays.equals(bArrZza, zzmbVar.zza(zzmbVar.zzb(bArrZza, bArr), bArr))) {
            throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
        }
        return zzmbVar;
    }

    public zzmd() throws GeneralSecurityException {
        this(new zza());
    }

    private zzmd(zza zzaVar) {
        this.zzc = null;
        this.zzd = zzaVar.zza;
    }

    static /* synthetic */ boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcc
    public final synchronized boolean zzb(String str) {
        return str.toLowerCase(Locale.US).startsWith("android-keystore://");
    }

    static boolean zzc(String str) throws GeneralSecurityException {
        zzmd zzmdVar = new zzmd();
        synchronized (zza) {
            if (zzmdVar.zzd(str)) {
                return false;
            }
            String strZza = zzxo.zza("android-keystore://", str);
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder(strZza, 3).setKeySize(256).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").build());
            keyGenerator.generateKey();
            return true;
        }
    }

    private final synchronized boolean zzd(String str) throws GeneralSecurityException {
        String strZza;
        strZza = zzxo.zza("android-keystore://", str);
        try {
        } catch (NullPointerException e) {
            Log.w(zzb, "Keystore is temporarily unavailable, wait, reinitialize Keystore and try again.");
            try {
                try {
                    Thread.sleep((int) (Math.random() * 40.0d));
                } catch (InterruptedException e2) {
                }
                this.zzd = KeyStore.getInstance("AndroidKeyStore");
                this.zzd.load(null);
                return this.zzd.containsAlias(strZza);
            } catch (IOException e3) {
                throw new GeneralSecurityException(e3);
            }
        }
        return this.zzd.containsAlias(strZza);
    }
}
