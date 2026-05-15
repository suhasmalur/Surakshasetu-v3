package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzakc implements zzakk {
    private zzakk[] zza;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakk
    public final zzakl zza(Class<?> cls) {
        for (zzakk zzakkVar : this.zza) {
            if (zzakkVar.zzb(cls)) {
                return zzakkVar.zza(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: " + cls.getName());
    }

    zzakc(zzakk... zzakkVarArr) {
        this.zza = zzakkVarArr;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakk
    public final boolean zzb(Class<?> cls) {
        for (zzakk zzakkVar : this.zza) {
            if (zzakkVar.zzb(cls)) {
                return true;
            }
        }
        return false;
    }
}
