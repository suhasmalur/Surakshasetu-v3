package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzan;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzzn implements zzadk<zzaeq> {
    private final /* synthetic */ zzzo zza;

    zzzn(zzzo zzzoVar) {
        this.zza = zzzoVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zza.zza.zza(zzan.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzaeq zzaeqVar) {
        zzaeq zzaeqVar2 = zzaeqVar;
        this.zza.zzb.zza(new zzafn(zzaeqVar2.zzb(), zzaeqVar2.zza(), Long.valueOf(zzafp.zza(zzaeqVar2.zza())), "Bearer"), null, null, false, null, this.zza.zza, this);
    }
}
