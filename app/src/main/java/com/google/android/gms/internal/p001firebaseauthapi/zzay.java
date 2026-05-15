package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.AbstractMap;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzay<K, V> extends zzap<Map.Entry<K, V>> {
    private final /* synthetic */ zzaz zza;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzd;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int i) {
        zzy.zza(i, this.zza.zzd);
        int i2 = i * 2;
        Object obj = this.zza.zzb[i2];
        obj.getClass();
        Object obj2 = this.zza.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    zzay(zzaz zzazVar) {
        this.zza = zzazVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak
    public final boolean zze() {
        return true;
    }
}
