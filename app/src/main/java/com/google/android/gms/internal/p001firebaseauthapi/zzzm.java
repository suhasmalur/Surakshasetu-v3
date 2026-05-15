package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzan;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzzm implements zzadk<zzagv> {
    private final /* synthetic */ zzacd zza;
    private final /* synthetic */ zzyj zzb;

    zzzm(zzyj zzyjVar, zzacd zzacdVar) {
        this.zzb = zzyjVar;
        this.zza = zzacdVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zza.zza(zzan.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzagv zzagvVar) {
        zzagv zzagvVar2 = zzagvVar;
        if (!zzagvVar2.zzl()) {
            zzyj.zza(this.zzb, zzagvVar2, this.zza, this);
        } else {
            this.zza.zza(new zzyk(zzagvVar2.zzf(), zzagvVar2.zzk(), zzagvVar2.zzb()));
        }
    }
}
