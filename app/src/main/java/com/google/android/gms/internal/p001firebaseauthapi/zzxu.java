package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzxu {
    private final zzxt zza;

    public final int zza() {
        return this.zza.zza();
    }

    public static zzxu zza(byte[] bArr, zzcs zzcsVar) {
        if (zzcsVar != null) {
            return new zzxu(zzxt.zza(bArr));
        }
        throw new NullPointerException("SecretKeyAccess required");
    }

    private zzxu(zzxt zzxtVar) {
        this.zza = zzxtVar;
    }

    public final byte[] zza(zzcs zzcsVar) {
        if (zzcsVar == null) {
            throw new NullPointerException("SecretKeyAccess required");
        }
        return this.zza.zzb();
    }
}
