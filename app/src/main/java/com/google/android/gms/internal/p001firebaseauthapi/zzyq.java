package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzan;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzyq implements zzadk<zzaeo> {
    private final /* synthetic */ zzacd zza;
    private final /* synthetic */ zzyj zzb;

    zzyq(zzyj zzyjVar, zzacd zzacdVar) {
        this.zzb = zzyjVar;
        this.zza = zzacdVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zza.zza(zzan.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzaeo zzaeoVar) {
        zzaeo zzaeoVar2 = zzaeoVar;
        if (zzaeoVar2.zzf()) {
            this.zza.zza(new zzyk(zzaeoVar2.zzc(), zzaeoVar2.zze(), null));
        } else {
            this.zzb.zza(new zzafn(zzaeoVar2.zzd(), zzaeoVar2.zzb(), Long.valueOf(zzaeoVar2.zza()), "Bearer"), null, null, Boolean.valueOf(zzaeoVar2.zzg()), null, this.zza, this);
        }
    }
}
