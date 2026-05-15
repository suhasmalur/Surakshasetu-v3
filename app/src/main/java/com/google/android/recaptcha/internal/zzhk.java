package com.google.android.recaptcha.internal;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzhk extends zzhm {
    private zzhk() {
        super(null);
    }

    /* synthetic */ zzhk(zzhj zzhjVar) {
        super(null);
    }

    @Override // com.google.android.recaptcha.internal.zzhm
    final List zza(Object obj, long j) {
        zzgv zzgvVar = (zzgv) zzjp.zzf(obj, j);
        if (zzgvVar.zzc()) {
            return zzgvVar;
        }
        int size = zzgvVar.size();
        zzgv zzgvVarZzd = zzgvVar.zzd(size == 0 ? 10 : size + size);
        zzjp.zzs(obj, j, zzgvVarZzd);
        return zzgvVarZzd;
    }

    @Override // com.google.android.recaptcha.internal.zzhm
    final void zzb(Object obj, long j) {
        ((zzgv) zzjp.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.recaptcha.internal.zzhm
    final void zzc(Object obj, Object obj2, long j) {
        zzgv zzgvVarZzd = (zzgv) zzjp.zzf(obj, j);
        zzgv zzgvVar = (zzgv) zzjp.zzf(obj2, j);
        int size = zzgvVarZzd.size();
        int size2 = zzgvVar.size();
        if (size > 0 && size2 > 0) {
            if (!zzgvVarZzd.zzc()) {
                zzgvVarZzd = zzgvVarZzd.zzd(size2 + size);
            }
            zzgvVarZzd.addAll(zzgvVar);
        }
        if (size > 0) {
            zzgvVar = zzgvVarZzd;
        }
        zzjp.zzs(obj, j, zzgvVar);
    }
}
