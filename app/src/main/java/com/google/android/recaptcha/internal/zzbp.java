package com.google.android.recaptcha.internal;

import java.lang.reflect.Array;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbp implements zzby {
    public static final zzbp zza = new zzbp();

    private zzbp() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length != 2) {
            throw new zzt(4, 3, null);
        }
        Object obj = objArr[0];
        if (true != (obj instanceof Object)) {
            obj = null;
        }
        if (obj == null) {
            throw new zzt(4, 5, null);
        }
        Object obj2 = objArr[1];
        if (true != (obj2 instanceof Integer)) {
            obj2 = null;
        }
        Integer num = (Integer) obj2;
        if (num == null) {
            throw new zzt(4, 5, null);
        }
        int iIntValue = num.intValue();
        try {
            zzblVar.zzc().zzf(i, obj instanceof String ? String.valueOf(((String) obj).charAt(iIntValue)) : obj instanceof List ? ((List) obj).get(iIntValue) : Array.get(obj, iIntValue));
        } catch (Exception e) {
            if (!(e instanceof ArrayIndexOutOfBoundsException)) {
                throw new zzt(4, 23, e);
            }
            throw new zzt(4, 22, e);
        }
    }
}
