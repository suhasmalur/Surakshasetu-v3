package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zziz {
    public static final String zza = new zzin().zze();

    @Deprecated
    private static final zzvt zzb = zzvt.zzb();

    @Deprecated
    private static final zzvt zzc = zzvt.zzb();

    static {
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzje.zzc();
        if (zzif.zzb()) {
            return;
        }
        zzin.zza(true);
    }
}
