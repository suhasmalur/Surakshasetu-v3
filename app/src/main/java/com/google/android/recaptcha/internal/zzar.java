package com.google.android.recaptcha.internal;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzar {
    public static final zzar zza = new zzar();

    private zzar() {
    }

    public static final zzlg zza(zzn zznVar, zzn zznVar2) {
        zzlf zzlfVarZzf = zzlg.zzf();
        zzlfVarZzf.zzp(zzka.zzb(zznVar.zzb()));
        zzlfVarZzf.zzq(zzjy.zzb(zznVar.zza(TimeUnit.NANOSECONDS)));
        zzlfVarZzf.zzd(zzka.zzb(zznVar2.zzb()));
        zzlfVarZzf.zze(zzjy.zzb(zznVar2.zza(TimeUnit.NANOSECONDS)));
        return (zzlg) zzlfVarZzf.zzj();
    }
}
