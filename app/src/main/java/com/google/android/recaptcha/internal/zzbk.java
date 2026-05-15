package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbk {
    public static final zzbk zza = new zzbk();

    private zzbk() {
    }

    public static final Class zza(Object obj) throws zzt {
        if (obj instanceof Class) {
            return (Class) obj;
        }
        if (obj instanceof Integer) {
            int iIntValue = ((Number) obj).intValue();
            Class cls = iIntValue == 1 ? Integer.TYPE : iIntValue == 2 ? Short.TYPE : iIntValue == 3 ? Byte.TYPE : iIntValue == 4 ? Long.TYPE : iIntValue == 5 ? Character.TYPE : iIntValue == 6 ? Float.TYPE : iIntValue == 7 ? Double.TYPE : iIntValue == 8 ? Boolean.TYPE : iIntValue == 9 ? zzcr.class : null;
            if (cls != null) {
                return cls;
            }
            throw new zzt(4, 6, null);
        }
        if (!(obj instanceof String)) {
            throw new zzt(6, 8, null);
        }
        String str = (String) obj;
        if (!zzbj.zzc(str)) {
            throw new zzt(6, 47, null);
        }
        try {
            return Class.forName(str);
        } catch (Exception e) {
            throw new zzt(6, 8, e);
        }
    }
}
