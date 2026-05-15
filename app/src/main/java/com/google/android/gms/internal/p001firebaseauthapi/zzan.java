package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
class zzan<E> extends zzam<E> {
    Object[] zza;
    int zzb;
    boolean zzc;

    public zzan<E> zza(E e) {
        zzy.zza(e);
        int i = this.zzb + 1;
        if (this.zza.length < i) {
            this.zza = Arrays.copyOf(this.zza, zza(this.zza.length, i));
            this.zzc = false;
        } else if (this.zzc) {
            this.zza = (Object[]) this.zza.clone();
            this.zzc = false;
        }
        Object[] objArr = this.zza;
        int i2 = this.zzb;
        this.zzb = i2 + 1;
        objArr[i2] = e;
        return this;
    }

    zzan(int i) {
        zzai.zza(4, "initialCapacity");
        this.zza = new Object[4];
        this.zzb = 0;
    }
}
