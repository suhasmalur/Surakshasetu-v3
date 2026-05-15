package com.google.android.gms.internal.p001firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzlc {
    private static final byte[] zza = new byte[0];
    private final zzld zzb;
    private final BigInteger zzc;
    private final byte[] zzd;
    private final byte[] zze;
    private final byte[] zzf;
    private BigInteger zzg = BigInteger.ZERO;

    public static zzlc zza(byte[] bArr, zzll zzllVar, zzlj zzljVar, zzlg zzlgVar, zzld zzldVar, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArrZza = zzljVar.zza(bArr, zzllVar);
        byte[] bArr3 = zzlt.zza;
        byte[] bArrZza2 = zzlt.zza(zzljVar.zza(), zzlgVar.zzb(), zzldVar.zzc());
        byte[] bArrZza3 = zzwg.zza(bArr3, zzlgVar.zza(zzlt.zzl, zza, "psk_id_hash", bArrZza2), zzlgVar.zza(zzlt.zzl, bArr2, "info_hash", bArrZza2));
        byte[] bArrZza4 = zzlgVar.zza(bArrZza, zza, "secret", bArrZza2);
        byte[] bArrZza5 = zzlgVar.zza(bArrZza4, bArrZza3, "key", bArrZza2, zzldVar.zza());
        byte[] bArrZza6 = zzlgVar.zza(bArrZza4, bArrZza3, "base_nonce", bArrZza2, zzldVar.zzb());
        zzldVar.zzb();
        return new zzlc(bArr, bArrZza5, bArrZza6, BigInteger.ONE.shiftLeft(96).subtract(BigInteger.ONE), zzldVar);
    }

    private zzlc(byte[] bArr, byte[] bArr2, byte[] bArr3, BigInteger bigInteger, zzld zzldVar) {
        this.zzf = bArr;
        this.zzd = bArr2;
        this.zze = bArr3;
        this.zzc = bigInteger;
        this.zzb = zzldVar;
    }

    private final synchronized byte[] zza() throws GeneralSecurityException {
        byte[] bArrZza;
        bArrZza = zzwg.zza(this.zze, zzme.zza(this.zzg, this.zzb.zzb()));
        if (this.zzg.compareTo(this.zzc) >= 0) {
            throw new GeneralSecurityException("message limit reached");
        }
        this.zzg = this.zzg.add(BigInteger.ONE);
        return bArrZza;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return this.zzb.zza(this.zzd, zza(), bArr, bArr2);
    }
}
