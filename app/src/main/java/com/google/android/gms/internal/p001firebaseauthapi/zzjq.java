package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzjl;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.Arrays;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzjq extends zzkv {
    private final zzjy zza;

    @Nullable
    private final zzxs zzb;

    @Nullable
    private final zzxu zzc;

    public static zzjq zza(zzjy zzjyVar, zzxu zzxuVar) throws GeneralSecurityException {
        if (zzjyVar == null) {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without an ECIES public key");
        }
        if (zzjyVar.zzb() == null) {
            throw new GeneralSecurityException("ECIES private key for X25519 curve cannot be constructed with NIST-curve public key");
        }
        if (zzxuVar == null) {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without secret");
        }
        byte[] bArrZza = zzxuVar.zza(zzbq.zza());
        byte[] bArrZzb = zzjyVar.zzb().zzb();
        if (bArrZza.length != 32) {
            throw new GeneralSecurityException("Private key bytes length for X25519 curve must be 32");
        }
        if (Arrays.equals(zzxr.zza(bArrZza), bArrZzb)) {
            return new zzjq(zzjyVar, null, zzxuVar);
        }
        throw new GeneralSecurityException("Invalid private key for public key.");
    }

    public static zzjq zza(zzjy zzjyVar, zzxs zzxsVar) throws GeneralSecurityException {
        if (zzjyVar == null) {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without an ECIES public key");
        }
        if (zzjyVar.zzc() == null) {
            throw new GeneralSecurityException("ECIES private key for NIST curve cannot be constructed with X25519-curve public key");
        }
        if (zzxsVar == null) {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without secret");
        }
        BigInteger bigIntegerZza = zzxsVar.zza(zzbq.zza());
        ECPoint eCPointZzc = zzjyVar.zzc();
        zzjl.zzc zzcVarZzd = zzjyVar.zza().zzd();
        BigInteger order = zza(zzcVarZzd).getOrder();
        if (bigIntegerZza.signum() <= 0 || bigIntegerZza.compareTo(order) >= 0) {
            throw new GeneralSecurityException("Invalid private value");
        }
        if (!zzmg.zza(bigIntegerZza, zza(zzcVarZzd)).equals(eCPointZzc)) {
            throw new GeneralSecurityException("Invalid private value");
        }
        return new zzjq(zzjyVar, zzxsVar, null);
    }

    private static ECParameterSpec zza(zzjl.zzc zzcVar) {
        if (zzcVar == zzjl.zzc.zza) {
            return zzmg.zza;
        }
        if (zzcVar == zzjl.zzc.zzb) {
            return zzmg.zzb;
        }
        if (zzcVar == zzjl.zzc.zzc) {
            return zzmg.zzc;
        }
        throw new IllegalArgumentException("Unable to determine NIST curve type for " + String.valueOf(zzcVar));
    }

    private zzjq(zzjy zzjyVar, @Nullable zzxs zzxsVar, @Nullable zzxu zzxuVar) {
        this.zza = zzjyVar;
        this.zzb = zzxsVar;
        this.zzc = zzxuVar;
    }
}
