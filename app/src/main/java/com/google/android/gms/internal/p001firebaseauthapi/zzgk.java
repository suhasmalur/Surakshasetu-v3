package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzgk extends zzdb {
    private final String zza;

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzgk.class, this.zza});
    }

    public static zzgk zza(String str) throws GeneralSecurityException {
        return new zzgk(str);
    }

    public final String zzb() {
        return this.zza;
    }

    public final String toString() {
        return "LegacyKmsAead Parameters (keyUri: " + this.zza + ")";
    }

    private zzgk(String str) {
        this.zza = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgk)) {
            return false;
        }
        return ((zzgk) obj).zza.equals(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzch
    public final boolean zza() {
        return false;
    }
}
