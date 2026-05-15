package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzfv;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzfr extends zzcz {
    private final zzfv zza;
    private final zzxu zzb;
    private final zzxt zzc;

    @Nullable
    private final Integer zzd;

    public static zzfr zza(zzfv.zza zzaVar, zzxu zzxuVar, @Nullable Integer num) throws GeneralSecurityException {
        zzxt zzxtVarZza;
        if (zzaVar != zzfv.zza.zzc && num == null) {
            throw new GeneralSecurityException("For given Variant " + String.valueOf(zzaVar) + " the value of idRequirement must be non-null");
        }
        if (zzaVar == zzfv.zza.zzc && num != null) {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        }
        if (zzxuVar.zza() != 32) {
            throw new GeneralSecurityException("ChaCha20Poly1305 key must be constructed with key of length 32 bytes, not " + zzxuVar.zza());
        }
        zzfv zzfvVarZza = zzfv.zza(zzaVar);
        if (zzfvVarZza.zzb() == zzfv.zza.zzc) {
            zzxtVarZza = zzxt.zza(new byte[0]);
        } else if (zzfvVarZza.zzb() == zzfv.zza.zzb) {
            zzxtVarZza = zzxt.zza(ByteBuffer.allocate(5).put((byte) 0).putInt(num.intValue()).array());
        } else {
            if (zzfvVarZza.zzb() != zzfv.zza.zza) {
                throw new IllegalStateException("Unknown Variant: " + String.valueOf(zzfvVarZza.zzb()));
            }
            zzxtVarZza = zzxt.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
        }
        return new zzfr(zzfvVarZza, zzxuVar, zzxtVarZza, num);
    }

    private zzfr(zzfv zzfvVar, zzxu zzxuVar, zzxt zzxtVar, @Nullable Integer num) {
        this.zza = zzfvVar;
        this.zzb = zzxuVar;
        this.zzc = zzxtVar;
        this.zzd = num;
    }
}
