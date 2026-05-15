package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzau<E> extends zzak<E> implements Set<E> {

    @CheckForNull
    private transient zzap<E> zza;

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzbd.zza(this);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak
    public zzap<E> zzc() {
        zzap<E> zzapVar = this.zza;
        if (zzapVar != null) {
            return zzapVar;
        }
        zzap<E> zzapVarZzg = zzg();
        this.zza = zzapVarZzg;
        return zzapVarZzg;
    }

    zzap<E> zzg() {
        return zzap.zza(toArray());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzak, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    zzau() {
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        return zzbd.zza(this, obj);
    }
}
