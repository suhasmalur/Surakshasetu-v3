package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzdd implements zzbg {
    private final zzcg<zzbg> zza;
    private final zzrr zzb;
    private final zzrr zzc;

    private zzdd(zzcg<zzbg> zzcgVar) {
        this.zza = zzcgVar;
        if (!zzcgVar.zzf()) {
            this.zzb = zznh.zza;
            this.zzc = zznh.zza;
        } else {
            zzro zzroVarZzb = zznn.zza().zzb();
            zzrq zzrqVarZza = zznh.zza(zzcgVar);
            this.zzb = zzroVarZzb.zza(zzrqVarZza, "aead", "encrypt");
            this.zzc = zzroVarZzb.zza(zzrqVarZza, "aead", "decrypt");
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbg
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 5) {
            byte[] bArrCopyOf = Arrays.copyOf(bArr, 5);
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 5, bArr.length);
            for (zzcl<zzbg> zzclVar : this.zza.zza(bArrCopyOf)) {
                try {
                    byte[] bArrZza = zzclVar.zzf().zza(bArrCopyOfRange, bArr2);
                    this.zzc.zza(zzclVar.zza(), bArrCopyOfRange.length);
                    return bArrZza;
                } catch (GeneralSecurityException e) {
                }
            }
        }
        for (zzcl<zzbg> zzclVar2 : this.zza.zze()) {
            try {
                byte[] bArrZza2 = zzclVar2.zzf().zza(bArr, bArr2);
                this.zzc.zza(zzclVar2.zza(), bArr.length);
                return bArrZza2;
            } catch (GeneralSecurityException e2) {
            }
        }
        this.zzc.zza();
        throw new GeneralSecurityException("decryption failed");
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbg
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            byte[] bArrZza = zzwg.zza(this.zza.zza().zzh(), this.zza.zza().zzf().zzb(bArr, bArr2));
            this.zzb.zza(this.zza.zza().zza(), bArr.length);
            return bArrZza;
        } catch (GeneralSecurityException e) {
            this.zzb.zza();
            throw e;
        }
    }
}
