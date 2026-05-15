package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class zzamf extends AbstractList<String> implements zzajt, RandomAccess {
    private final zzajt zza;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajt
    public final zzajt zzd() {
        return this;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return (String) this.zza.get(i);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajt
    public final Object zzb(int i) {
        return this.zza.zzb(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zzamh(this);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajt
    public final List<?> zze() {
        return this.zza.zze();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new zzami(this, i);
    }

    public zzamf(zzajt zzajtVar) {
        this.zza = zzajtVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajt
    public final void zza(zzahp zzahpVar) {
        throw new UnsupportedOperationException();
    }
}
