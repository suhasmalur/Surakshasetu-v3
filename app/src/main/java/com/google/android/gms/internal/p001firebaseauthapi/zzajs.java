package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzajs {
    private static final zzajs zza = new zzajv();
    private static final zzajs zzb = new zzajx();

    static zzajs zza() {
        return zza;
    }

    abstract <L> List<L> zza(Object obj, long j);

    abstract <L> void zza(Object obj, Object obj2, long j);

    abstract void zzb(Object obj, long j);

    static zzajs zzb() {
        return zzb;
    }

    private zzajs() {
    }
}
