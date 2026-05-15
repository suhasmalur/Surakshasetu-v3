package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzajv extends zzajs {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzamk.zze(obj, j);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajs
    final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> arrayList;
        List<L> listZzc = zzc(obj, j);
        if (listZzc.isEmpty()) {
            if (listZzc instanceof zzajt) {
                arrayList = new zzajq(i);
            } else if ((listZzc instanceof zzakz) && (listZzc instanceof zzajj)) {
                arrayList = ((zzajj) listZzc).zza(i);
            } else {
                arrayList = new ArrayList<>(i);
            }
            zzamk.zza(obj, j, arrayList);
            return arrayList;
        }
        if (zza.isAssignableFrom(listZzc.getClass())) {
            ArrayList arrayList2 = new ArrayList(listZzc.size() + i);
            arrayList2.addAll(listZzc);
            zzamk.zza(obj, j, arrayList2);
            return arrayList2;
        }
        if (listZzc instanceof zzamf) {
            zzajq zzajqVar = new zzajq(listZzc.size() + i);
            zzajqVar.addAll((zzamf) listZzc);
            zzamk.zza(obj, j, zzajqVar);
            return zzajqVar;
        }
        if ((listZzc instanceof zzakz) && (listZzc instanceof zzajj)) {
            zzajj zzajjVar = (zzajj) listZzc;
            if (!zzajjVar.zzc()) {
                zzajj zzajjVarZza = zzajjVar.zza(listZzc.size() + i);
                zzamk.zza(obj, j, zzajjVarZza);
                return zzajjVarZza;
            }
            return listZzc;
        }
        return listZzc;
    }

    private zzajv() {
        super();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajs
    final void zzb(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzamk.zze(obj, j);
        if (list instanceof zzajt) {
            objUnmodifiableList = ((zzajt) list).zzd();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzakz) && (list instanceof zzajj)) {
                zzajj zzajjVar = (zzajj) list;
                if (zzajjVar.zzc()) {
                    zzajjVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzamk.zza(obj, j, objUnmodifiableList);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajs
    final <E> void zza(Object obj, Object obj2, long j) {
        List listZzc = zzc(obj2, j);
        List listZza = zza(obj, j, listZzc.size());
        int size = listZza.size();
        int size2 = listZzc.size();
        if (size > 0 && size2 > 0) {
            listZza.addAll(listZzc);
        }
        if (size > 0) {
            listZzc = listZza;
        }
        zzamk.zza(obj, j, listZzc);
    }
}
