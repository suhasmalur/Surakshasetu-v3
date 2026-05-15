package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzlt {
    public static final byte[] zza = zza(1, 0);
    private static final byte[] zzm = zza(1, 2);
    public static final byte[] zzb = zza(2, 32);
    public static final byte[] zzc = zza(2, 16);
    public static final byte[] zzd = zza(2, 17);
    public static final byte[] zze = zza(2, 18);
    public static final byte[] zzf = zza(2, 1);
    public static final byte[] zzg = zza(2, 2);
    public static final byte[] zzh = zza(2, 3);
    public static final byte[] zzi = zza(2, 1);
    public static final byte[] zzj = zza(2, 2);
    public static final byte[] zzk = zza(2, 3);
    public static final byte[] zzl = new byte[0];
    private static final byte[] zzn = "KEM".getBytes(zzpf.zza);
    private static final byte[] zzo = "HPKE".getBytes(zzpf.zza);
    private static final byte[] zzp = "HPKE-v1".getBytes(zzpf.zza);

    public static int zza(zzuo zzuoVar) throws GeneralSecurityException {
        switch (zzuoVar) {
            case DHKEM_P256_HKDF_SHA256:
                return 32;
            case DHKEM_P384_HKDF_SHA384:
                return 48;
            case DHKEM_P521_HKDF_SHA512:
                return 66;
            case DHKEM_X25519_HKDF_SHA256:
                return 32;
            default:
                throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
        }
    }

    public static int zzb(zzuo zzuoVar) throws GeneralSecurityException {
        switch (zzuoVar) {
            case DHKEM_P256_HKDF_SHA256:
                return 65;
            case DHKEM_P384_HKDF_SHA384:
                return 97;
            case DHKEM_P521_HKDF_SHA512:
                return 133;
            case DHKEM_X25519_HKDF_SHA256:
                return 32;
            default:
                throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
        }
    }

    static zzwo zzc(zzuo zzuoVar) throws GeneralSecurityException {
        switch (zzuoVar) {
            case DHKEM_P256_HKDF_SHA256:
                return zzwo.NIST_P256;
            case DHKEM_P384_HKDF_SHA384:
                return zzwo.NIST_P384;
            case DHKEM_P521_HKDF_SHA512:
                return zzwo.NIST_P521;
            default:
                throw new GeneralSecurityException("Unrecognized NIST HPKE KEM identifier");
        }
    }

    static void zza(zzur zzurVar) throws GeneralSecurityException {
        if (zzurVar.zzc() == zzuo.KEM_UNKNOWN || zzurVar.zzc() == zzuo.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid KEM param: " + zzurVar.zzc().name());
        }
        if (zzurVar.zzb() == zzum.KDF_UNKNOWN || zzurVar.zzb() == zzum.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid KDF param: " + zzurVar.zzb().name());
        }
        if (zzurVar.zza() == zzuk.AEAD_UNKNOWN || zzurVar.zza() == zzuk.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid AEAD param: " + zzurVar.zza().name());
        }
    }

    static byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        return zzwg.zza(zzo, bArr, bArr2, bArr3);
    }

    private static byte[] zza(int i, int i2) {
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) (i2 >> (((i - i3) - 1) * 8));
        }
        return bArr;
    }

    static byte[] zza(byte[] bArr) throws GeneralSecurityException {
        return zzwg.zza(zzn, bArr);
    }

    static byte[] zza(String str, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return zzwg.zza(zzp, bArr2, str.getBytes(zzpf.zza), bArr);
    }

    static byte[] zza(String str, byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        return zzwg.zza(zza(2, i), zzp, bArr2, str.getBytes(zzpf.zza), bArr);
    }
}
