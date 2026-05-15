package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzhi extends zzhm {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzhi() {
        super(null);
    }

    /* synthetic */ zzhi(zzhh zzhhVar) {
        super(null);
    }

    private static List zzf(Object obj, long j, int i) {
        List list = (List) zzjp.zzf(obj, j);
        if (list.isEmpty()) {
            List zzhfVar = list instanceof zzhg ? new zzhf(i) : ((list instanceof zzig) && (list instanceof zzgv)) ? ((zzgv) list).zzd(i) : new ArrayList(i);
            zzjp.zzs(obj, j, zzhfVar);
            return zzhfVar;
        }
        if (zza.isAssignableFrom(list.getClass())) {
            ArrayList arrayList = new ArrayList(list.size() + i);
            arrayList.addAll(list);
            zzjp.zzs(obj, j, arrayList);
            return arrayList;
        }
        if (list instanceof zzjk) {
            zzhf zzhfVar2 = new zzhf(list.size() + i);
            zzhfVar2.addAll(zzhfVar2.size(), (zzjk) list);
            zzjp.zzs(obj, j, zzhfVar2);
            return zzhfVar2;
        }
        if (!(list instanceof zzig) || !(list instanceof zzgv)) {
            return list;
        }
        zzgv zzgvVar = (zzgv) list;
        if (zzgvVar.zzc()) {
            return list;
        }
        zzgv zzgvVarZzd = zzgvVar.zzd(list.size() + i);
        zzjp.zzs(obj, j, zzgvVarZzd);
        return zzgvVarZzd;
    }

    @Override // com.google.android.recaptcha.internal.zzhm
    final List zza(Object obj, long j) {
        return zzf(obj, j, 10);
    }

    @Override // com.google.android.recaptcha.internal.zzhm
    final void zzb(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzjp.zzf(obj, j);
        if (list instanceof zzhg) {
            objUnmodifiableList = ((zzhg) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzig) && (list instanceof zzgv)) {
                zzgv zzgvVar = (zzgv) list;
                if (zzgvVar.zzc()) {
                    zzgvVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzjp.zzs(obj, j, objUnmodifiableList);
    }

    @Override // com.google.android.recaptcha.internal.zzhm
    final void zzc(Object obj, Object obj2, long j) {
        List list = (List) zzjp.zzf(obj2, j);
        List listZzf = zzf(obj, j, list.size());
        int size = listZzf.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            listZzf.addAll(list);
        }
        if (size > 0) {
            list = listZzf;
        }
        zzjp.zzs(obj, j, list);
    }
}
