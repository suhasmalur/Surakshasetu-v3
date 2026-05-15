package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbu {

    @Nullable
    private final zzvb zza = null;

    @Nullable
    private final zzch zzb;

    public static zzbu zza(zzch zzchVar) throws GeneralSecurityException {
        return new zzbu(zzchVar);
    }

    final zzvb zza() throws GeneralSecurityException {
        return this.zzb instanceof zznf ? ((zznf) this.zzb).zzb().zza() : ((zzor) zznu.zza().zza(this.zzb, zzor.class)).zza();
    }

    private zzbu(zzch zzchVar) {
        this.zzb = zzchVar;
    }
}
