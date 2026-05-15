package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzakf<K, V> {
    static <K, V> int zza(zzake<K, V> zzakeVar, K k, V v) {
        return zzaiv.zza(zzakeVar.zza, 1, k) + zzaiv.zza(zzakeVar.zzc, 2, v);
    }

    static <K, V> void zza(zzaik zzaikVar, zzake<K, V> zzakeVar, K k, V v) throws IOException {
        zzaiv.zza(zzaikVar, zzakeVar.zza, 1, k);
        zzaiv.zza(zzaikVar, zzakeVar.zzc, 2, v);
    }
}
