package com.google.android.recaptcha.internal;

import java.util.Comparator;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzer implements Comparator {
    zzer() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzez zzezVar = (zzez) obj;
        zzez zzezVar2 = (zzez) obj2;
        zzeq zzeqVar = new zzeq(zzezVar);
        zzeq zzeqVar2 = new zzeq(zzezVar2);
        while (zzeqVar.hasNext() && zzeqVar2.hasNext()) {
            int iCompareTo = Integer.valueOf(zzeqVar.zza() & 255).compareTo(Integer.valueOf(zzeqVar2.zza() & 255));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Integer.valueOf(zzezVar.zzd()).compareTo(Integer.valueOf(zzezVar2.zzd()));
    }
}
