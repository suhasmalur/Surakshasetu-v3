package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzala<E> extends zzahj<E> implements RandomAccess {
    private static final zzala<Object> zza = new zzala<>(new Object[0], 0, false);
    private E[] zzb;
    private int zzc;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajj
    public final /* synthetic */ zzajj zza(int i) {
        if (i >= this.zzc) {
            return new zzala(Arrays.copyOf(this.zzb, i), this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public static <E> zzala<E> zzd() {
        return (zzala<E>) zza;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int i) {
        zzc(i);
        return this.zzb[i];
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahj, java.util.AbstractList, java.util.List
    public final E remove(int i) {
        zza();
        zzc(i);
        E e = this.zzb[i];
        if (i < this.zzc - 1) {
            System.arraycopy(this.zzb, i + 1, this.zzb, i, (this.zzc - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return e;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahj, java.util.AbstractList, java.util.List
    public final E set(int i, E e) {
        zza();
        zzc(i);
        E e2 = this.zzb[i];
        this.zzb[i] = e;
        this.modCount++;
        return e2;
    }

    private final String zzb(int i) {
        return "Index:" + i + ", Size:" + this.zzc;
    }

    zzala() {
        this(new Object[10], 0, true);
    }

    private zzala(E[] eArr, int i, boolean z) {
        super(z);
        this.zzb = eArr;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahj, java.util.AbstractList, java.util.List
    public final void add(int i, E e) {
        zza();
        if (i < 0 || i > this.zzc) {
            throw new IndexOutOfBoundsException(zzb(i));
        }
        if (this.zzc < this.zzb.length) {
            System.arraycopy(this.zzb, i, this.zzb, i + 1, this.zzc - i);
        } else {
            E[] eArr = (E[]) new Object[((this.zzc * 3) / 2) + 1];
            System.arraycopy(this.zzb, 0, eArr, 0, i);
            System.arraycopy(this.zzb, i, eArr, i + 1, this.zzc - i);
            this.zzb = eArr;
        }
        this.zzb[i] = e;
        this.zzc++;
        this.modCount++;
    }

    private final void zzc(int i) {
        if (i < 0 || i >= this.zzc) {
            throw new IndexOutOfBoundsException(zzb(i));
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahj, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(E e) {
        zza();
        if (this.zzc == this.zzb.length) {
            this.zzb = (E[]) Arrays.copyOf(this.zzb, ((this.zzc * 3) / 2) + 1);
        }
        E[] eArr = this.zzb;
        int i = this.zzc;
        this.zzc = i + 1;
        eArr[i] = e;
        this.modCount++;
        return true;
    }
}
