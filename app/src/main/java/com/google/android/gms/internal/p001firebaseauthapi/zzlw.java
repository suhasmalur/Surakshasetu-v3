package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzlw implements zzlj {
    private final zzla zza;

    zzlw(zzla zzlaVar) {
        this.zza = zzlaVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzlj
    public final byte[] zza(byte[] bArr, zzll zzllVar) throws GeneralSecurityException {
        return this.zza.zza(null, zzxr.zza(zzllVar.zza().zzb(), bArr), "eae_prk", zzwg.zza(bArr, zzllVar.zzb().zzb()), "shared_secret", zzlt.zza(zzlt.zzb), this.zza.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzlj
    public final byte[] zza() throws GeneralSecurityException {
        if (Arrays.equals(this.zza.zzb(), zzlt.zzf)) {
            return zzlt.zzb;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
