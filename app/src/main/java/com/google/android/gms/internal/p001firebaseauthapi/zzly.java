package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzly {
    private final zzbg zza;
    private final zzbp zzb;

    public zzly(zzbg zzbgVar) {
        this.zza = zzbgVar;
        this.zzb = null;
    }

    public zzly(zzbp zzbpVar) {
        this.zza = null;
        this.zzb = zzbpVar;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (this.zza != null) {
            return this.zza.zza(bArr, bArr2);
        }
        return this.zzb.zza(bArr, bArr2);
    }
}
