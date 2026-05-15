package com.google.android.recaptcha.internal;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
abstract class zzhm {
    private static final zzhm zza = new zzhi(null);
    private static final zzhm zzb = new zzhk(0 == true ? 1 : 0);

    /* synthetic */ zzhm(zzhl zzhlVar) {
    }

    static zzhm zzd() {
        return zza;
    }

    static zzhm zze() {
        return zzb;
    }

    abstract List zza(Object obj, long j);

    abstract void zzb(Object obj, long j);

    abstract void zzc(Object obj, Object obj2, long j);
}
