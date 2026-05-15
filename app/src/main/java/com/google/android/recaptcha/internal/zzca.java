package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzca implements zzby {
    public static final zzca zza = new zzca();

    private zzca() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length != 1) {
            throw new zzt(4, 3, null);
        }
        Object objZza = objArr[0];
        if (true != (objZza instanceof Object)) {
            objZza = null;
        }
        if (objZza == null) {
            throw new zzt(4, 5, null);
        }
        try {
            if (objZza instanceof String) {
                objZza = zzbx.zza(this, (String) objZza, zzblVar.zza());
            }
            zzblVar.zzc().zzf(i, zzbk.zza(objZza));
        } catch (Exception e) {
            throw new zzt(6, 8, e);
        }
    }
}
