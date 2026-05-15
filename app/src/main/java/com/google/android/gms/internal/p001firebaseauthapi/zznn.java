package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zznn {
    private static final zznn zza = new zznn();
    private static final zznq zzb = new zznq();
    private final AtomicReference<zzro> zzc = new AtomicReference<>();

    public static zznn zza() {
        return zza;
    }

    public final zzro zzb() {
        zzro zzroVar = this.zzc.get();
        return zzroVar == null ? zzb : zzroVar;
    }
}
