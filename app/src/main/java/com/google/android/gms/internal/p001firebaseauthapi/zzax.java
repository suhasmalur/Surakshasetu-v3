package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzax<E> extends zzap<E> {
    static final zzap<Object> zza = new zzax(new Object[0], 0);
    private final transient Object[] zzb;
    private final transient int zzc;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzap, com.google.android.gms.internal.p001firebaseauthapi.zzak
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, i, this.zzc);
        return i + this.zzc;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak
    final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak
    final int zzb() {
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzy.zza(i, this.zzc);
        E e = (E) this.zzb[i];
        e.getClass();
        return e;
    }

    zzax(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak
    final boolean zze() {
        return false;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak
    final Object[] zzf() {
        return this.zzb;
    }
}
