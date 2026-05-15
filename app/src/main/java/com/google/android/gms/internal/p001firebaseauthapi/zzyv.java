package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzan;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzyv implements zzadk<zzagx> {
    private final /* synthetic */ zzacd zza;
    private final /* synthetic */ zzyj zzb;

    zzyv(zzyj zzyjVar, zzacd zzacdVar) {
        this.zzb = zzyjVar;
        this.zza = zzacdVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zza.zza(zzan.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzagx zzagxVar) {
        zzagx zzagxVar2 = zzagxVar;
        this.zzb.zza(new zzafn(zzagxVar2.zzc(), zzagxVar2.zzb(), Long.valueOf(zzagxVar2.zza()), "Bearer"), null, null, Boolean.valueOf(zzagxVar2.zzd()), null, this.zza, this);
    }
}
