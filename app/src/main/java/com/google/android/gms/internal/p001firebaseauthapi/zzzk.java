package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzan;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzzk implements zzadk<zzafn> {
    final /* synthetic */ zzacd zza;
    final /* synthetic */ zzyj zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;

    zzzk(zzyj zzyjVar, String str, String str2, zzacd zzacdVar) {
        this.zzb = zzyjVar;
        this.zzc = str;
        this.zzd = str2;
        this.zza = zzacdVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zza.zza(zzan.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzafn zzafnVar) {
        this.zzb.zza.zza(new zzaha(zzafnVar.zzc(), this.zzc, this.zzd), new zzzj(this));
    }
}
