package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.EllipticCurve;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzwl implements zzbo {
    private static final byte[] zza = new byte[0];
    private final ECPrivateKey zzb;
    private final zzwn zzc;
    private final String zzd;
    private final byte[] zze;
    private final zzwr zzf;
    private final zzwi zzg;

    public zzwl(ECPrivateKey eCPrivateKey, byte[] bArr, String str, zzwr zzwrVar, zzwi zzwiVar) throws GeneralSecurityException {
        this.zzb = eCPrivateKey;
        this.zzc = new zzwn(eCPrivateKey);
        this.zze = bArr;
        this.zzd = str;
        this.zzf = zzwrVar;
        this.zzg = zzwiVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbo
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int i;
        EllipticCurve curve = this.zzb.getParams().getCurve();
        zzwr zzwrVar = this.zzf;
        int iZza = zzwp.zza(curve);
        switch (zzwrVar) {
            case UNCOMPRESSED:
                i = (iZza * 2) + 1;
                break;
            case COMPRESSED:
                i = iZza + 1;
                break;
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                i = iZza * 2;
                break;
            default:
                throw new GeneralSecurityException("unknown EC point format");
        }
        if (bArr.length < i) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        return this.zzg.zza(this.zzc.zza(Arrays.copyOfRange(bArr, 0, i), this.zzd, this.zze, bArr2, this.zzg.zza(), this.zzf)).zza(Arrays.copyOfRange(bArr, i, bArr.length), zza);
    }
}
