package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzar<E> extends zzaj<E> {
    private final zzap<E> zza;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaj
    protected final E zza(int i) {
        return this.zza.get(i);
    }

    zzar(zzap<E> zzapVar, int i) {
        super(zzapVar.size(), i);
        this.zza = zzapVar;
    }
}
