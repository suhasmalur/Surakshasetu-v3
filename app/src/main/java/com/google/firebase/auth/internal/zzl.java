package com.google.firebase.auth.internal;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
final class zzl extends zzh {
    private String zza;
    private String zzb;

    @Override // com.google.firebase.auth.internal.zzh
    public final zzh zza(String str) {
        this.zzb = str;
        return this;
    }

    @Override // com.google.firebase.auth.internal.zzh
    public final zzh zzb(String str) {
        this.zza = str;
        return this;
    }

    @Override // com.google.firebase.auth.internal.zzh
    public final zze zza() {
        return new zzi(this.zza, this.zzb);
    }

    zzl() {
    }
}
