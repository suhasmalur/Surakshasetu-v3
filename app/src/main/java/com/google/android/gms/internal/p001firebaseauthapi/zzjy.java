package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzjl;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzjy extends zzku {
    private final zzjl zza;

    @Nullable
    private final ECPoint zzb;

    @Nullable
    private final zzxt zzc;
    private final zzxt zzd;

    @Nullable
    private final Integer zze;

    public final zzjl zza() {
        return this.zza;
    }

    public static zzjy zza(zzjl zzjlVar, zzxt zzxtVar, @Nullable Integer num) throws GeneralSecurityException {
        if (!zzjlVar.zzd().equals(zzjl.zzc.zzd)) {
            throw new GeneralSecurityException("createForCurveX25519 may only be called with parameters for curve X25519");
        }
        zzb(zzjlVar.zzg(), num);
        if (zzxtVar.zza() == 32) {
            return new zzjy(zzjlVar, null, zzxtVar, zza(zzjlVar.zzg(), num), num);
        }
        throw new GeneralSecurityException("Encoded public point byte length for X25519 curve must be 32");
    }

    public static zzjy zza(zzjl zzjlVar, ECPoint eCPoint, @Nullable Integer num) throws GeneralSecurityException {
        EllipticCurve curve;
        if (zzjlVar.zzd().equals(zzjl.zzc.zzd)) {
            throw new GeneralSecurityException("createForNistCurve may only be called with parameters for NIST curve");
        }
        zzb(zzjlVar.zzg(), num);
        zzjl.zzc zzcVarZzd = zzjlVar.zzd();
        if (zzcVarZzd == zzjl.zzc.zza) {
            curve = zzmg.zza.getCurve();
        } else if (zzcVarZzd == zzjl.zzc.zzb) {
            curve = zzmg.zzb.getCurve();
        } else if (zzcVarZzd == zzjl.zzc.zzc) {
            curve = zzmg.zzc.getCurve();
        } else {
            throw new IllegalArgumentException("Unable to determine NIST curve type for " + String.valueOf(zzcVarZzd));
        }
        zzmg.zza(eCPoint, curve);
        return new zzjy(zzjlVar, eCPoint, null, zza(zzjlVar.zzg(), num), num);
    }

    private static zzxt zza(zzjl.zzd zzdVar, @Nullable Integer num) {
        if (zzdVar == zzjl.zzd.zzc) {
            return zzxt.zza(new byte[0]);
        }
        if (num == null) {
            throw new IllegalStateException("idRequirement must be non-null for EciesParameters.Variant: " + String.valueOf(zzdVar));
        }
        if (zzdVar == zzjl.zzd.zzb) {
            return zzxt.zza(ByteBuffer.allocate(5).put((byte) 0).putInt(num.intValue()).array());
        }
        if (zzdVar == zzjl.zzd.zza) {
            return zzxt.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
        }
        throw new IllegalStateException("Unknown EciesParameters.Variant: " + String.valueOf(zzdVar));
    }

    @Nullable
    public final zzxt zzb() {
        return this.zzc;
    }

    @Nullable
    public final ECPoint zzc() {
        return this.zzb;
    }

    private zzjy(zzjl zzjlVar, @Nullable ECPoint eCPoint, @Nullable zzxt zzxtVar, zzxt zzxtVar2, @Nullable Integer num) {
        this.zza = zzjlVar;
        this.zzb = eCPoint;
        this.zzc = zzxtVar;
        this.zzd = zzxtVar2;
        this.zze = num;
    }

    private static void zzb(zzjl.zzd zzdVar, @Nullable Integer num) throws GeneralSecurityException {
        if (!zzdVar.equals(zzjl.zzd.zzc) && num == null) {
            throw new GeneralSecurityException("'idRequirement' must be non-null for " + String.valueOf(zzdVar) + " variant.");
        }
        if (zzdVar.equals(zzjl.zzd.zzc) && num != null) {
            throw new GeneralSecurityException("'idRequirement' must be null for NO_PREFIX variant.");
        }
    }
}
