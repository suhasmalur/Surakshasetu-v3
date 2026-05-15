package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzqv implements zzce {
    private final zzcg<zzce> zza;
    private final zzrr zzb;
    private final zzrr zzc;

    private zzqv(zzcg<zzce> zzcgVar) {
        this.zza = zzcgVar;
        if (!zzcgVar.zzf()) {
            this.zzb = zznh.zza;
            this.zzc = zznh.zza;
        } else {
            zzro zzroVarZzb = zznn.zza().zzb();
            zzrq zzrqVarZza = zznh.zza(zzcgVar);
            this.zzb = zzroVarZzb.zza(zzrqVarZza, "mac", "compute");
            this.zzc = zzroVarZzb.zza(zzrqVarZza, "mac", "verify");
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzce
    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length <= 5) {
            this.zzc.zza();
            throw new GeneralSecurityException("tag too short");
        }
        for (zzcl<zzce> zzclVar : this.zza.zza(Arrays.copyOf(bArr, 5))) {
            try {
                zzclVar.zze().zza(bArr, bArr2);
                this.zzc.zza(zzclVar.zza(), bArr2.length);
                return;
            } catch (GeneralSecurityException e) {
            }
        }
        for (zzcl<zzce> zzclVar2 : this.zza.zze()) {
            try {
                zzclVar2.zze().zza(bArr, bArr2);
                this.zzc.zza(zzclVar2.zza(), bArr2.length);
                return;
            } catch (GeneralSecurityException e2) {
            }
        }
        this.zzc.zza();
        throw new GeneralSecurityException("invalid MAC");
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzce
    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        try {
            byte[] bArrZza = this.zza.zza().zze().zza(bArr);
            this.zzb.zza(this.zza.zza().zza(), bArr.length);
            return bArrZza;
        } catch (GeneralSecurityException e) {
            this.zzb.zza();
            throw e;
        }
    }
}
