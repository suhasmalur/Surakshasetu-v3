package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzka;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkf extends zzkv {
    private final zzkn zza;
    private final zzxu zzb;

    public static zzkf zza(zzkn zzknVar, zzxu zzxuVar) throws GeneralSecurityException {
        ECParameterSpec eCParameterSpec;
        if (zzknVar == null) {
            throw new GeneralSecurityException("HPKE private key cannot be constructed without an HPKE public key");
        }
        if (zzxuVar == null) {
            throw new GeneralSecurityException("HPKE private key cannot be constructed without secret");
        }
        zzka.zzd zzdVarZze = zzknVar.zza().zze();
        int iZza = zzxuVar.zza();
        String str = "Encoded private key byte length for " + String.valueOf(zzdVarZze) + " must be %d, not " + iZza;
        if (zzdVarZze == zzka.zzd.zza) {
            if (iZza != 32) {
                throw new GeneralSecurityException(String.format(str, 32));
            }
        } else if (zzdVarZze == zzka.zzd.zzb) {
            if (iZza != 48) {
                throw new GeneralSecurityException(String.format(str, 48));
            }
        } else if (zzdVarZze == zzka.zzd.zzc) {
            if (iZza != 66) {
                throw new GeneralSecurityException(String.format(str, 66));
            }
        } else {
            if (zzdVarZze != zzka.zzd.zzd) {
                throw new GeneralSecurityException("Unable to validate private key length for " + String.valueOf(zzdVarZze));
            }
            if (iZza != 32) {
                throw new GeneralSecurityException(String.format(str, 32));
            }
        }
        zzka.zzd zzdVarZze2 = zzknVar.zza().zze();
        byte[] bArrZzb = zzknVar.zzb().zzb();
        byte[] bArrZza = zzxuVar.zza(zzbq.zza());
        if (zzdVarZze2 == zzka.zzd.zza || zzdVarZze2 == zzka.zzd.zzb || zzdVarZze2 == zzka.zzd.zzc) {
            if (zzdVarZze2 == zzka.zzd.zza) {
                eCParameterSpec = zzmg.zza;
            } else if (zzdVarZze2 == zzka.zzd.zzb) {
                eCParameterSpec = zzmg.zzb;
            } else {
                if (zzdVarZze2 != zzka.zzd.zzc) {
                    throw new IllegalArgumentException("Unable to determine NIST curve params for " + String.valueOf(zzdVarZze2));
                }
                eCParameterSpec = zzmg.zzc;
            }
            BigInteger order = eCParameterSpec.getOrder();
            BigInteger bigIntegerZza = zzme.zza(bArrZza);
            if (bigIntegerZza.signum() <= 0 || bigIntegerZza.compareTo(order) >= 0) {
                throw new GeneralSecurityException("Invalid private key.");
            }
            if (!zzmg.zza(bigIntegerZza, eCParameterSpec).equals(zzwp.zza(eCParameterSpec.getCurve(), zzwr.UNCOMPRESSED, bArrZzb))) {
                throw new GeneralSecurityException("Invalid private key for public key.");
            }
        } else {
            if (zzdVarZze2 != zzka.zzd.zzd) {
                throw new IllegalArgumentException("Unable to validate key pair for " + String.valueOf(zzdVarZze2));
            }
            if (!Arrays.equals(zzxr.zza(bArrZza), bArrZzb)) {
                throw new GeneralSecurityException("Invalid private key for public key.");
            }
        }
        return new zzkf(zzknVar, zzxuVar);
    }

    private zzkf(zzkn zzknVar, zzxu zzxuVar) {
        this.zza = zzknVar;
        this.zzb = zzxuVar;
    }
}
