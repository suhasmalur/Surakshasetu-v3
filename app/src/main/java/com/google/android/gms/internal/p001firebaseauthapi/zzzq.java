package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzan;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzzq implements zzadk<zzaes> {
    private final /* synthetic */ zzacd zza;
    private final /* synthetic */ zzyj zzb;

    zzzq(zzyj zzyjVar, zzacd zzacdVar) {
        this.zzb = zzyjVar;
        this.zza = zzacdVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zza.zza(zzan.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzaes zzaesVar) {
        zzaes zzaesVar2 = zzaesVar;
        this.zzb.zza(new zzafn(zzaesVar2.zzb(), zzaesVar2.zza(), Long.valueOf(zzafp.zza(zzaesVar2.zza())), "Bearer"), null, null, false, null, this.zza, this);
    }
}
