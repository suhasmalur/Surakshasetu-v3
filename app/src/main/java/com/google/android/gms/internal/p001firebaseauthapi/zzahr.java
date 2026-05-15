package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Comparator;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzahr implements Comparator<zzahp> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzahp zzahpVar, zzahp zzahpVar2) {
        zzahp zzahpVar3 = zzahpVar;
        zzahp zzahpVar4 = zzahpVar2;
        zzahv zzahvVar = (zzahv) zzahpVar3.iterator();
        zzahv zzahvVar2 = (zzahv) zzahpVar4.iterator();
        while (zzahvVar.hasNext() && zzahvVar2.hasNext()) {
            int iCompareTo = Integer.valueOf(zzahp.zza(zzahvVar.zza())).compareTo(Integer.valueOf(zzahp.zza(zzahvVar2.zza())));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Integer.valueOf(zzahpVar3.zzb()).compareTo(Integer.valueOf(zzahpVar4.zzb()));
    }

    zzahr() {
    }
}
