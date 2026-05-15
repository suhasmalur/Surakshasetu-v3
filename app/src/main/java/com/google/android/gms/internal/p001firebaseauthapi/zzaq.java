package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: Add missing generic type declarations: [E] */
/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaq<E> extends zzap<E> {
    private final transient int zza;
    private final transient int zzb;
    private final /* synthetic */ zzap zzc;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak
    final int zza() {
        return this.zzc.zzb() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak
    final int zzb() {
        return this.zzc.zzb() + this.zza;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzap
    /* JADX INFO: renamed from: zza */
    public final zzap<E> subList(int i, int i2) {
        zzy.zza(i, i2, this.zzb);
        return (zzap) this.zzc.subList(i + this.zza, i2 + this.zza);
    }

    @Override // java.util.List
    public final E get(int i) {
        zzy.zza(i, this.zzb);
        return this.zzc.get(i + this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzap, java.util.List
    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    zzaq(zzap zzapVar, int i, int i2) {
        this.zzc = zzapVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak
    final boolean zze() {
        return true;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak
    @CheckForNull
    final Object[] zzf() {
        return this.zzc.zzf();
    }
}
