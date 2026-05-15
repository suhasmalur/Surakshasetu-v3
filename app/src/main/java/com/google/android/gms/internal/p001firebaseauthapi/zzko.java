package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzko implements zzbo {
    private final zzcg<zzbo> zza;
    private final zzrr zzb;

    public zzko(zzcg<zzbo> zzcgVar) {
        this.zza = zzcgVar;
        if (zzcgVar.zzf()) {
            this.zzb = zznn.zza().zzb().zza(zznh.zza(zzcgVar), "hybrid_decrypt", "decrypt");
        } else {
            this.zzb = zznh.zza;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbo
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 5) {
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, 5);
            byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, 5, bArr.length);
            for (zzcl<zzbo> zzclVar : this.zza.zza(bArrCopyOfRange)) {
                try {
                    byte[] bArrZza = zzclVar.zzf().zza(bArrCopyOfRange2, bArr2);
                    this.zzb.zza(zzclVar.zza(), bArrCopyOfRange2.length);
                    return bArrZza;
                } catch (GeneralSecurityException e) {
                }
            }
        }
        for (zzcl<zzbo> zzclVar2 : this.zza.zze()) {
            try {
                byte[] bArrZza2 = zzclVar2.zzf().zza(bArr, bArr2);
                this.zzb.zza(zzclVar2.zza(), bArr.length);
                return bArrZza2;
            } catch (GeneralSecurityException e2) {
            }
        }
        this.zzb.zza();
        throw new GeneralSecurityException("decryption failed");
    }
}
