package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzrm {
    private HashMap<String, String> zza = new HashMap<>();

    public final zzrn zza() {
        if (this.zza == null) {
            throw new IllegalStateException("cannot call build() twice");
        }
        zzrn zzrnVar = new zzrn(Collections.unmodifiableMap(this.zza));
        this.zza = null;
        return zzrnVar;
    }
}
