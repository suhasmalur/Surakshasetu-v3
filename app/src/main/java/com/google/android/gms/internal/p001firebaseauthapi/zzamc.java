package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzamc extends RuntimeException {
    private final List<String> zza;

    public final zzaji zza() {
        return new zzaji(getMessage());
    }

    public zzamc(zzakn zzaknVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zza = null;
    }
}
