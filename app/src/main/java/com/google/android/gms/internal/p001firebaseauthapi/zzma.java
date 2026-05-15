package com.google.android.gms.internal.p001firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import java.io.CharConversionException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.ProviderException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzma {
    private static final Object zza = new Object();
    private static final String zzb = zzma.class.getSimpleName();
    private final zzcd zzc;
    private final zzbg zzd;
    private zzcb zze;

    public final synchronized zzbx zza() throws GeneralSecurityException {
        return this.zze.zza();
    }

    static /* synthetic */ void zza(zzbx zzbxVar, zzcd zzcdVar, zzbg zzbgVar) throws GeneralSecurityException {
        try {
            if (zzbgVar != null) {
                zzbxVar.zza(zzcdVar, zzbgVar);
            } else {
                zzbl.zza(zzbxVar, zzcdVar);
            }
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        }
    }

    private zzma(zza zzaVar) {
        this.zzc = new zzmf(zzaVar.zza, zzaVar.zzb, zzaVar.zzc);
        this.zzd = zzaVar.zze;
        this.zze = zzaVar.zzi;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza {
        private Context zza = null;
        private String zzb = null;
        private String zzc = null;
        private String zzd = null;
        private zzbg zze = null;
        private boolean zzf = true;
        private zzbu zzg = null;
        private zzvb zzh = null;
        private zzcb zzi;

        private final zzbg zzb() throws GeneralSecurityException {
            if (!zzma.zzd()) {
                Log.w(zzma.zzb, "Android Keystore requires at least Android M");
                return null;
            }
            zzmd zzmdVar = new zzmd();
            try {
                boolean zZzc = zzmd.zzc(this.zzd);
                try {
                    return zzmdVar.zza(this.zzd);
                } catch (GeneralSecurityException | ProviderException e) {
                    if (!zZzc) {
                        throw new KeyStoreException(String.format("the master key %s exists but is unusable", this.zzd), e);
                    }
                    Log.w(zzma.zzb, "cannot use Android Keystore, it'll be disabled", e);
                    return null;
                }
            } catch (GeneralSecurityException | ProviderException e2) {
                Log.w(zzma.zzb, "cannot use Android Keystore, it'll be disabled", e2);
                return null;
            }
        }

        private static zzcb zza(byte[] bArr) throws GeneralSecurityException, IOException {
            return zzcb.zza(zzbl.zza(zzbj.zza(bArr)));
        }

        private final zzcb zzb(byte[] bArr) throws GeneralSecurityException, IOException {
            try {
                this.zze = new zzmd().zza(this.zzd);
                try {
                    return zzcb.zza(zzbx.zza(zzbj.zza(bArr), this.zze));
                } catch (IOException | GeneralSecurityException e) {
                    try {
                        return zza(bArr);
                    } catch (IOException e2) {
                        throw e;
                    }
                }
            } catch (GeneralSecurityException | ProviderException e3) {
                try {
                    zzcb zzcbVarZza = zza(bArr);
                    Log.w(zzma.zzb, "cannot use Android Keystore, it'll be disabled", e3);
                    return zzcbVarZza;
                } catch (IOException e4) {
                    throw e3;
                }
            }
        }

        public final zza zza(zzvb zzvbVar) {
            this.zzh = zzvbVar;
            return this;
        }

        public final zza zza(String str) {
            if (!str.startsWith("android-keystore://")) {
                throw new IllegalArgumentException("key URI must start with android-keystore://");
            }
            if (!this.zzf) {
                throw new IllegalArgumentException("cannot call withMasterKeyUri() after calling doNotUseKeystore()");
            }
            this.zzd = str;
            return this;
        }

        public final zza zza(Context context, String str, String str2) throws IOException {
            if (context == null) {
                throw new IllegalArgumentException("need an Android context");
            }
            this.zza = context;
            this.zzb = str;
            this.zzc = str2;
            return this;
        }

        public final synchronized zzma zza() throws GeneralSecurityException, IOException {
            zzma zzmaVar;
            if (this.zzb == null) {
                throw new IllegalArgumentException("keysetName cannot be null");
            }
            if (this.zzh != null && this.zzg == null) {
                this.zzg = zzbu.zza(zzcu.zza(this.zzh.zzj()));
            }
            synchronized (zzma.zza) {
                byte[] bArrZzb = zzb(this.zza, this.zzb, this.zzc);
                if (bArrZzb == null) {
                    if (this.zzd != null) {
                        this.zze = zzb();
                    }
                    if (this.zzg == null) {
                        throw new GeneralSecurityException("cannot read or generate keyset");
                    }
                    zzcb zzcbVarZza = zzcb.zzb().zza(this.zzg);
                    zzcb zzcbVarZza2 = zzcbVarZza.zza(zzcbVarZza.zza().zzc().zza(0).zza());
                    zzma.zza(zzcbVarZza2.zza(), new zzmf(this.zza, this.zzb, this.zzc), this.zze);
                    this.zzi = zzcbVarZza2;
                } else if (this.zzd == null || !zzma.zzd()) {
                    this.zzi = zza(bArrZzb);
                } else {
                    this.zzi = zzb(bArrZzb);
                }
                zzmaVar = new zzma(this);
            }
            return zzmaVar;
        }

        private static byte[] zzb(Context context, String str, String str2) throws IOException {
            SharedPreferences sharedPreferences;
            if (str == null) {
                throw new IllegalArgumentException("keysetName cannot be null");
            }
            Context applicationContext = context.getApplicationContext();
            if (str2 == null) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
            } else {
                sharedPreferences = applicationContext.getSharedPreferences(str2, 0);
            }
            try {
                String string = sharedPreferences.getString(str, null);
                if (string == null) {
                    return null;
                }
                if (string.length() % 2 != 0) {
                    throw new IllegalArgumentException("Expected a string of even length");
                }
                int length = string.length() / 2;
                byte[] bArr = new byte[length];
                for (int i = 0; i < length; i++) {
                    int i2 = i * 2;
                    int iDigit = Character.digit(string.charAt(i2), 16);
                    int iDigit2 = Character.digit(string.charAt(i2 + 1), 16);
                    if (iDigit == -1 || iDigit2 == -1) {
                        throw new IllegalArgumentException("input is not hexadecimal");
                    }
                    bArr[i] = (byte) ((iDigit << 4) + iDigit2);
                }
                return bArr;
            } catch (ClassCastException | IllegalArgumentException e) {
                throw new CharConversionException(String.format("can't read keyset; the pref value %s is not a valid hex string", str));
            }
        }
    }

    static /* synthetic */ boolean zzd() {
        return true;
    }
}
