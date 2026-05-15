package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzhm;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzhg extends zzcz {
    private final zzhm zza;
    private final zzxu zzb;
    private final zzxt zzc;

    @Nullable
    private final Integer zzd;

    public static zzhg zza(zzhm.zza zzaVar, zzxu zzxuVar, @Nullable Integer num) throws GeneralSecurityException {
        zzxt zzxtVarZza;
        if (zzaVar != zzhm.zza.zzc && num == null) {
            throw new GeneralSecurityException("For given Variant " + String.valueOf(zzaVar) + " the value of idRequirement must be non-null");
        }
        if (zzaVar == zzhm.zza.zzc && num != null) {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        }
        if (zzxuVar.zza() != 32) {
            throw new GeneralSecurityException("XChaCha20Poly1305 key must be constructed with key of length 32 bytes, not " + zzxuVar.zza());
        }
        zzhm zzhmVarZza = zzhm.zza(zzaVar);
        if (zzhmVarZza.zzb() == zzhm.zza.zzc) {
            zzxtVarZza = zzxt.zza(new byte[0]);
        } else if (zzhmVarZza.zzb() == zzhm.zza.zzb) {
            zzxtVarZza = zzxt.zza(ByteBuffer.allocate(5).put((byte) 0).putInt(num.intValue()).array());
        } else {
            if (zzhmVarZza.zzb() != zzhm.zza.zza) {
                throw new IllegalStateException("Unknown Variant: " + String.valueOf(zzhmVarZza.zzb()));
            }
            zzxtVarZza = zzxt.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
        }
        return new zzhg(zzhmVarZza, zzxuVar, zzxtVarZza, num);
    }

    private zzhg(zzhm zzhmVar, zzxu zzxuVar, zzxt zzxtVar, @Nullable Integer num) {
        this.zza = zzhmVar;
        this.zzb = zzxuVar;
        this.zzc = zzxtVar;
        this.zzd = num;
    }
}
