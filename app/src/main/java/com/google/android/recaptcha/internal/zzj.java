package com.google.android.recaptcha.internal;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzj {
    public static final zzj zza = new zzj();
    private static final ConcurrentHashMap zzb = new ConcurrentHashMap();

    private zzj() {
    }

    public static final void zza(int i, long j) {
        ConcurrentHashMap concurrentHashMap = zzb;
        Integer numValueOf = Integer.valueOf(i);
        Object zziVar = concurrentHashMap.get(numValueOf);
        if (zziVar == null) {
            zziVar = new zzi();
        }
        zzi zziVar2 = (zzi) zziVar;
        zziVar2.zzg(zziVar2.zzb() + 1);
        zziVar2.zzf(zziVar2.zzd() + j);
        zziVar2.zze(Math.max(j, zziVar2.zzc()));
        zzb.put(numValueOf, zziVar2);
    }
}
