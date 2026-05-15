package com.google.android.recaptcha.internal;

import java.lang.reflect.Array;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbv implements zzby {
    public static final zzbv zza = new zzbv();

    private zzbv() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length != 2) {
            throw new zzt(4, 3, null);
        }
        Object objZza = objArr[0];
        if (true != (objZza instanceof Object)) {
            objZza = null;
        }
        if (objZza == null) {
            throw new zzt(4, 5, null);
        }
        Object obj = objArr[1];
        if (true != (obj instanceof Integer)) {
            obj = null;
        }
        Integer num = (Integer) obj;
        if (num == null) {
            throw new zzt(4, 5, null);
        }
        int iIntValue = num.intValue();
        try {
            if (objZza instanceof String) {
                objZza = zzbx.zza(this, (String) objZza, zzblVar.zza());
            }
            zzblVar.zzc().zzf(i, Array.newInstance((Class<?>) zzbk.zza(objZza), iIntValue));
        } catch (Exception e) {
            throw new zzt(6, 21, e);
        }
    }
}
