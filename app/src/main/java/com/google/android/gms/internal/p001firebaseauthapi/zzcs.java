package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcs {
    private static final zzcs zza = new zzcs();

    static zzcs zza() {
        return zza;
    }

    public static zzcs zza(@Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (zzcsVar != null) {
            return zzcsVar;
        }
        throw new GeneralSecurityException("SecretKeyAccess is required");
    }

    private zzcs() {
    }
}
