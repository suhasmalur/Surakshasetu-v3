package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzkx {
    public static zzwo zza(zzty zztyVar) throws GeneralSecurityException {
        switch (zztyVar) {
            case NIST_P256:
                return zzwo.NIST_P256;
            case NIST_P384:
                return zzwo.NIST_P384;
            case NIST_P521:
                return zzwo.NIST_P521;
            default:
                throw new GeneralSecurityException("unknown curve type: " + String.valueOf(zztyVar));
        }
    }

    public static zzwr zza(zztk zztkVar) throws GeneralSecurityException {
        switch (zztkVar) {
            case UNCOMPRESSED:
                return zzwr.UNCOMPRESSED;
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                return zzwr.DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
            case COMPRESSED:
                return zzwr.COMPRESSED;
            default:
                throw new GeneralSecurityException("unknown point format: " + String.valueOf(zztkVar));
        }
    }

    public static String zza(zzub zzubVar) throws NoSuchAlgorithmException {
        switch (zzubVar) {
            case SHA1:
                return "HmacSha1";
            case SHA224:
                return "HmacSha224";
            case SHA256:
                return "HmacSha256";
            case SHA384:
                return "HmacSha384";
            case SHA512:
                return "HmacSha512";
            default:
                throw new NoSuchAlgorithmException("hash unsupported for HMAC: " + String.valueOf(zzubVar));
        }
    }

    public static void zza(zztq zztqVar) throws GeneralSecurityException {
        zzwp.zza(zza(zztqVar.zzf().zzd()));
        zza(zztqVar.zzf().zze());
        if (zztqVar.zza() == zztk.UNKNOWN_FORMAT) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        zzct.zza(zztqVar.zzb().zzd());
    }
}
