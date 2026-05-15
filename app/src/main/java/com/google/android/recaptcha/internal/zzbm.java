package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbm {
    private final Map zza = new LinkedHashMap();
    private final Set zzb = new LinkedHashSet();
    private final Map zzc = this.zza;

    private final List zzh(List list) {
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(zza((zzmu) it.next()));
        }
        return arrayList;
    }

    public final Object zza(zzmu zzmuVar) throws zzt {
        int iZzN = zzmuVar.zzN();
        int i = iZzN - 1;
        if (iZzN == 0) {
            throw null;
        }
        switch (i) {
            case 0:
                return this.zza.get(Integer.valueOf(zzmuVar.zzi()));
            case 1:
                return Boolean.valueOf(zzmuVar.zzL());
            case 2:
                byte[] bArrZzo = zzmuVar.zzH().zzo();
                if (bArrZzo.length == 1) {
                    return Byte.valueOf(bArrZzo[0]);
                }
                throw new zzt(4, 6, null);
            case 3:
                String strZzJ = zzmuVar.zzJ();
                if (strZzJ.length() == 1) {
                    return Character.valueOf(strZzJ.charAt(0));
                }
                throw new zzt(4, 6, null);
            case 4:
                int iZzj = zzmuVar.zzj();
                if (iZzj < -32768 || iZzj > 32767) {
                    throw new zzt(4, 6, null);
                }
                return Short.valueOf((short) iZzj);
            case 5:
                return Integer.valueOf(zzmuVar.zzk());
            case 6:
            case 8:
                throw new zzt(4, 6, null);
            case 7:
                return Long.valueOf(zzmuVar.zzG());
            case 9:
                return Float.valueOf(zzmuVar.zzg());
            case 10:
                return Double.valueOf(zzmuVar.zzf());
            case 11:
                return zzmuVar.zzK();
            case 12:
                return null;
            default:
                throw new zzt(4, 5, null);
        }
    }

    public final Object zzb(int i) {
        return this.zza.remove(Integer.valueOf(i));
    }

    public final Map zzc() {
        return this.zzc;
    }

    public final void zzd() {
        this.zza.clear();
    }

    public final void zze(int i, Object obj) {
        zzf(173, obj);
        this.zzb.add(173);
    }

    public final void zzf(int i, Object obj) {
        this.zza.put(Integer.valueOf(i), obj);
    }

    public final Object[] zzg(List list) {
        return zzh(list).toArray(new Object[0]);
    }
}
