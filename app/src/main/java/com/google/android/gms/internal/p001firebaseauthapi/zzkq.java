package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzkq implements zzbr {
    private final zzcg<zzbr> zza;
    private final zzrr zzb;

    public zzkq(zzcg<zzbr> zzcgVar) {
        this.zza = zzcgVar;
        if (zzcgVar.zzf()) {
            this.zzb = zznn.zza().zzb().zza(zznh.zza(zzcgVar), "hybrid_encrypt", "encrypt");
        } else {
            this.zzb = zznh.zza;
        }
    }
}
