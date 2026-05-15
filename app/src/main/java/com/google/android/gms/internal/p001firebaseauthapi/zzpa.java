package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzpa {
    private final Class<? extends zzov> zza;
    private final zzxt zzb;

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        return this.zza.getSimpleName() + ", object identifier: " + String.valueOf(this.zzb);
    }

    private zzpa(Class<? extends zzov> cls, zzxt zzxtVar) {
        this.zza = cls;
        this.zzb = zzxtVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzpa)) {
            return false;
        }
        zzpa zzpaVar = (zzpa) obj;
        return zzpaVar.zza.equals(this.zza) && zzpaVar.zzb.equals(this.zzb);
    }
}
