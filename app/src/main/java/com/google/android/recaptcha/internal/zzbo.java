package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbo implements zzby {
    public static final zzbo zza = new zzbo();

    private zzbo() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        Object objValueOf;
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
        if (obj instanceof Integer) {
            objValueOf = Integer.valueOf(((Number) obj).intValue() + iIntValue);
        } else {
            if (!(obj instanceof int[])) {
                throw new zzt(4, 5, null);
            }
            int[] iArr = (int[]) obj;
            ArrayList arrayList = new ArrayList(iArr.length);
            for (int i2 : iArr) {
                arrayList.add(Integer.valueOf(i2 + iIntValue));
            }
            objValueOf = (Serializable) arrayList.toArray(new Integer[0]);
        }
        zzblVar.zzc().zzf(i, objValueOf);
    }
}
