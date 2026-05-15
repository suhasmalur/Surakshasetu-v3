package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzba extends zzap<Object> {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzy.zza(i, this.zzc);
        Object obj = this.zza[(i * 2) + this.zzb];
        obj.getClass();
        return obj;
    }

    zzba(Object[] objArr, int i, int i2) {
        this.zza = objArr;
        this.zzb = i;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak
    final boolean zze() {
        return true;
    }
}
