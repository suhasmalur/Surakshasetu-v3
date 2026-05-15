package com.google.firebase.auth.internal;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.p001firebaseauthapi.zzcd;
import com.google.android.gms.internal.p001firebaseauthapi.zzkm;
import com.google.android.gms.internal.p001firebaseauthapi.zzkt;
import com.google.android.gms.internal.p001firebaseauthapi.zzma;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzp {
    private static zzp zza;
    private final String zzb;
    private final zzma zzc;

    public static zzp zza(Context context, String str) {
        if (zza == null || !com.google.android.gms.internal.p001firebaseauthapi.zzw.zza(zza.zzb, str)) {
            zza = new zzp(context, str, true);
        }
        return zza;
    }

    public final String zza(String str) {
        String str2;
        if (this.zzc == null) {
            Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to decrypt payload");
            return null;
        }
        try {
            synchronized (this.zzc) {
                str2 = new String(((com.google.android.gms.internal.p001firebaseauthapi.zzbo) this.zzc.zza().zza(com.google.android.gms.internal.p001firebaseauthapi.zzbo.class)).zza(Base64.decode(str, 8), null), "UTF-8");
            }
            return str2;
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            Log.e("FirebearCryptoHelper", "Exception encountered while decrypting bytes:\n" + e.getMessage());
            return null;
        }
    }

    public final String zza() {
        if (this.zzc == null) {
            Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to get Public key");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zzcd zzcdVarZza = com.google.android.gms.internal.p001firebaseauthapi.zzbi.zza(byteArrayOutputStream);
        try {
            synchronized (this.zzc) {
                this.zzc.zza().zza().zza(zzcdVarZza);
            }
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 8);
        } catch (IOException | GeneralSecurityException e) {
            Log.e("FirebearCryptoHelper", "Exception encountered when attempting to get Public Key:\n" + e.getMessage());
            return null;
        }
    }

    private zzp(Context context, String str, boolean z) {
        zzma zzmaVarZza;
        this.zzb = str;
        try {
            zzkm.zza();
            zzma.zza zzaVarZza = new zzma.zza().zza(context, "GenericIdpKeyset", String.format("com.google.firebase.auth.api.crypto.%s", str)).zza(zzkt.zza);
            zzaVarZza.zza(String.format("android-keystore://firebear_master_key_id.%s", str));
            zzmaVarZza = zzaVarZza.zza();
        } catch (IOException | GeneralSecurityException e) {
            Log.e("FirebearCryptoHelper", "Exception encountered during crypto setup:\n" + e.getMessage());
            zzmaVarZza = null;
        }
        this.zzc = zzmaVarZza;
    }
}
