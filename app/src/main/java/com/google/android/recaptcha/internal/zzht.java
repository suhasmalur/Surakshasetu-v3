package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzht {
    zzht() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzhs zzhsVar = (zzhs) obj;
        if (zzhsVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzhsVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static final boolean zzb(Object obj) {
        return !((zzhs) obj).zze();
    }

    public static final Object zzc(Object obj, Object obj2) {
        zzhs zzhsVarZzb = (zzhs) obj;
        zzhs zzhsVar = (zzhs) obj2;
        if (!zzhsVar.isEmpty()) {
            if (!zzhsVarZzb.zze()) {
                zzhsVarZzb = zzhsVarZzb.zzb();
            }
            zzhsVarZzb.zzd(zzhsVar);
        }
        return zzhsVarZzb;
    }
}
