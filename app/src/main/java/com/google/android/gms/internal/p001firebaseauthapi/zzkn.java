package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzka;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.EllipticCurve;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkn extends zzku {
    private final zzka zza;
    private final zzxt zzb;
    private final zzxt zzc;

    @Nullable
    private final Integer zzd;

    public final zzka zza() {
        return this.zza;
    }

    public static zzkn zza(zzka zzkaVar, zzxt zzxtVar, @Nullable Integer num) throws GeneralSecurityException {
        zzxt zzxtVarZza;
        EllipticCurve curve;
        zzka.zzf zzfVarZzf = zzkaVar.zzf();
        if (!zzfVarZzf.equals(zzka.zzf.zzc) && num == null) {
            throw new GeneralSecurityException("'idRequirement' must be non-null for " + String.valueOf(zzfVarZzf) + " variant.");
        }
        if (zzfVarZzf.equals(zzka.zzf.zzc) && num != null) {
            throw new GeneralSecurityException("'idRequirement' must be null for NO_PREFIX variant.");
        }
        zzka.zzd zzdVarZze = zzkaVar.zze();
        int iZza = zzxtVar.zza();
        String str = "Encoded public key byte length for " + String.valueOf(zzdVarZze) + " must be %d, not " + iZza;
        if (zzdVarZze == zzka.zzd.zza) {
            if (iZza != 65) {
                throw new GeneralSecurityException(String.format(str, 65));
            }
        } else if (zzdVarZze == zzka.zzd.zzb) {
            if (iZza != 97) {
                throw new GeneralSecurityException(String.format(str, 97));
            }
        } else if (zzdVarZze == zzka.zzd.zzc) {
            if (iZza != 133) {
                throw new GeneralSecurityException(String.format(str, 133));
            }
        } else {
            if (zzdVarZze != zzka.zzd.zzd) {
                throw new GeneralSecurityException("Unable to validate public key length for " + String.valueOf(zzdVarZze));
            }
            if (iZza != 32) {
                throw new GeneralSecurityException(String.format(str, 32));
            }
        }
        if (zzdVarZze == zzka.zzd.zza || zzdVarZze == zzka.zzd.zzb || zzdVarZze == zzka.zzd.zzc) {
            if (zzdVarZze == zzka.zzd.zza) {
                curve = zzmg.zza.getCurve();
            } else if (zzdVarZze == zzka.zzd.zzb) {
                curve = zzmg.zzb.getCurve();
            } else {
                if (zzdVarZze != zzka.zzd.zzc) {
                    throw new IllegalArgumentException("Unable to determine NIST curve type for " + String.valueOf(zzdVarZze));
                }
                curve = zzmg.zzc.getCurve();
            }
            zzmg.zza(zzwp.zza(curve, zzwr.UNCOMPRESSED, zzxtVar.zzb()), curve);
        }
        zzka.zzf zzfVarZzf2 = zzkaVar.zzf();
        if (zzfVarZzf2 == zzka.zzf.zzc) {
            zzxtVarZza = zzxt.zza(new byte[0]);
        } else {
            if (num == null) {
                throw new IllegalStateException("idRequirement must be non-null for HpkeParameters.Variant " + String.valueOf(zzfVarZzf2));
            }
            if (zzfVarZzf2 == zzka.zzf.zzb) {
                zzxtVarZza = zzxt.zza(ByteBuffer.allocate(5).put((byte) 0).putInt(num.intValue()).array());
            } else {
                if (zzfVarZzf2 != zzka.zzf.zza) {
                    throw new IllegalStateException("Unknown HpkeParameters.Variant: " + String.valueOf(zzfVarZzf2));
                }
                zzxtVarZza = zzxt.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
            }
        }
        return new zzkn(zzkaVar, zzxtVar, zzxtVarZza, num);
    }

    public final zzxt zzb() {
        return this.zzb;
    }

    private zzkn(zzka zzkaVar, zzxt zzxtVar, zzxt zzxtVar2, @Nullable Integer num) {
        this.zza = zzkaVar;
        this.zzb = zzxtVar;
        this.zzc = zzxtVar2;
        this.zzd = num;
    }
}
