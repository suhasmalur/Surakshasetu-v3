package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzi<T> implements Iterator<T> {
    private int zza = zzh.zzb;

    @CheckForNull
    private T zzb;

    @CheckForNull
    protected abstract T zza();

    @CheckForNull
    protected final T zzb() {
        this.zza = zzh.zzc;
        return null;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.zza = zzh.zzb;
        T t = this.zzb;
        this.zzb = null;
        return t;
    }

    protected zzi() {
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (!(this.zza != zzh.zzd)) {
            throw new IllegalStateException();
        }
        switch (this.zza - 1) {
            case 0:
                return true;
            case 1:
            default:
                this.zza = zzh.zzd;
                this.zzb = zza();
                if (this.zza == zzh.zzc) {
                    return false;
                }
                this.zza = zzh.zza;
                return true;
            case 2:
                return false;
        }
    }
}
