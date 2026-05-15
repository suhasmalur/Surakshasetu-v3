package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzxl implements zzrx {
    private static final zzif.zza zza = zzif.zza.zza;
    private final SecretKey zzb;
    private byte[] zzc;
    private byte[] zzd;

    private static Cipher zza() throws GeneralSecurityException {
        if (zza.zza()) {
            return zzwt.zza.zza("AES/ECB/NoPadding");
        }
        throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
    }

    public zzxl(byte[] bArr) throws GeneralSecurityException {
        zzxo.zza(bArr.length);
        this.zzb = new SecretKeySpec(bArr, "AES");
        Cipher cipherZza = zza();
        cipherZza.init(1, this.zzb);
        this.zzc = zzrd.zzb(cipherZza.doFinal(new byte[16]));
        this.zzd = zzrd.zzb(this.zzc);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzrx
    public final byte[] zza(byte[] bArr, int i) throws GeneralSecurityException {
        byte[] bArrZza;
        if (i > 16) {
            throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
        }
        Cipher cipherZza = zza();
        cipherZza.init(1, this.zzb);
        int iMax = Math.max(1, (int) Math.ceil(((double) bArr.length) / 16.0d));
        if ((iMax << 4) == bArr.length) {
            bArrZza = zzwg.zza(bArr, (iMax - 1) << 4, this.zzc, 0, 16);
        } else {
            bArrZza = zzwg.zza(zzrd.zza(Arrays.copyOfRange(bArr, (iMax - 1) << 4, bArr.length)), this.zzd);
        }
        byte[] bArrDoFinal = new byte[16];
        for (int i2 = 0; i2 < iMax - 1; i2++) {
            bArrDoFinal = cipherZza.doFinal(zzwg.zza(bArrDoFinal, 0, bArr, i2 << 4, 16));
        }
        return Arrays.copyOf(cipherZza.doFinal(zzwg.zza(bArrZza, bArrDoFinal)), i);
    }
}
