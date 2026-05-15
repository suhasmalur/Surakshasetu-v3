package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzalk extends zzals {
    private final /* synthetic */ zzalg zza;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzals, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzali(this.zza);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzalk(zzalg zzalgVar) {
        super(zzalgVar);
        this.zza = zzalgVar;
    }
}
