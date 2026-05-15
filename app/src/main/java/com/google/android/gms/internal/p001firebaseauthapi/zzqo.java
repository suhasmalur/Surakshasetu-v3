package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzqo {
    private static final String zza = new zzqg().zze();

    @Deprecated
    private static final zzvt zzb;

    @Deprecated
    private static final zzvt zzc;

    @Deprecated
    private static final zzvt zzd;

    static {
        zzvt zzvtVarZzb = zzvt.zzb();
        zzb = zzvtVarZzb;
        zzc = zzvtVarZzb;
        zzd = zzb;
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzqt.zzc();
        zzpy.zzc();
        zzqg.zza(true);
        if (zzif.zzb()) {
            return;
        }
        zzpl.zza(true);
    }
}
