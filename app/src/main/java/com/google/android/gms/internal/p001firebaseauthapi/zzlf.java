package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzlf implements zzbo {
    private static final byte[] zza = new byte[0];
    private final zzll zzb;
    private final zzlj zzc;
    private final zzlg zzd;
    private final zzld zze;
    private final int zzf;

    static zzlf zza(zzuu zzuuVar) throws GeneralSecurityException {
        int i;
        zzll zzllVarZza;
        if (!zzuuVar.zzf()) {
            throw new IllegalArgumentException("HpkePrivateKey is missing public_key field.");
        }
        if (!zzuuVar.zzd().zzg()) {
            throw new IllegalArgumentException("HpkePrivateKey.public_key is missing params field.");
        }
        if (zzuuVar.zze().zze()) {
            throw new IllegalArgumentException("HpkePrivateKey.private_key is empty.");
        }
        zzur zzurVarZzb = zzuuVar.zzd().zzb();
        zzlj zzljVarZzc = zzlk.zzc(zzurVarZzb);
        zzlg zzlgVarZzb = zzlk.zzb(zzurVarZzb);
        zzld zzldVarZza = zzlk.zza(zzurVarZzb);
        zzuo zzuoVarZzc = zzurVarZzb.zzc();
        switch (zzuoVarZzc) {
            case DHKEM_X25519_HKDF_SHA256:
                i = 32;
                break;
            case DHKEM_P256_HKDF_SHA256:
                i = 65;
                break;
            case DHKEM_P384_HKDF_SHA384:
                i = 97;
                break;
            case DHKEM_P521_HKDF_SHA512:
                i = 133;
                break;
            default:
                throw new IllegalArgumentException("Unable to determine KEM-encoding length for " + zzuoVarZzc.name());
        }
        switch (zzuuVar.zzd().zzb().zzc()) {
            case DHKEM_X25519_HKDF_SHA256:
                zzllVarZza = zzlz.zza(zzuuVar.zze().zzg());
                break;
            case DHKEM_P256_HKDF_SHA256:
            case DHKEM_P384_HKDF_SHA384:
            case DHKEM_P521_HKDF_SHA512:
                zzllVarZza = zzlx.zza(zzuuVar.zze().zzg(), zzuuVar.zzd().zzf().zzg(), zzlt.zzc(zzuuVar.zzd().zzb().zzc()));
                break;
            default:
                throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
        }
        return new zzlf(zzllVarZza, zzljVarZzc, zzlgVarZzb, zzldVarZza, i);
    }

    private zzlf(zzll zzllVar, zzlj zzljVar, zzlg zzlgVar, zzld zzldVar, int i) {
        this.zzb = zzllVar;
        this.zzc = zzljVar;
        this.zzd = zzlgVar;
        this.zze = zzldVar;
        this.zzf = i;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbo
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3;
        if (bArr.length < this.zzf) {
            throw new GeneralSecurityException("Ciphertext is too short.");
        }
        if (bArr2 != null) {
            bArr3 = bArr2;
        } else {
            bArr3 = new byte[0];
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, this.zzf);
        return zzlc.zza(bArrCopyOf, this.zzb, this.zzc, this.zzd, this.zze, bArr3).zza(Arrays.copyOfRange(bArr, this.zzf, bArr.length), zza);
    }
}
