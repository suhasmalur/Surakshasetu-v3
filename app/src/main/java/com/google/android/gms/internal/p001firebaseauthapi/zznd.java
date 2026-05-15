package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zznd extends zzbt {
    private final zzos zza;

    public final zzos zza(@Nullable zzcs zzcsVar) throws GeneralSecurityException {
        zza(this.zza, zzcsVar);
        return this.zza;
    }

    @Nullable
    public final Integer zza() {
        return this.zza.zze();
    }

    public zznd(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        zza(zzosVar, zzcsVar);
        this.zza = zzosVar;
    }

    private static void zza(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        switch (zzosVar.zza()) {
            case SYMMETRIC:
            case ASYMMETRIC_PRIVATE:
                zzcs.zza(zzcsVar);
                break;
        }
    }
}
