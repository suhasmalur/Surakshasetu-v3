package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzyp implements zzadk<zzafd> {
    private final /* synthetic */ zzadl zza;
    private final /* synthetic */ zzacd zzb;
    private final /* synthetic */ zzafn zzc;
    private final /* synthetic */ zzagc zzd;
    private final /* synthetic */ zzyj zze;

    zzyp(zzyj zzyjVar, zzadl zzadlVar, zzacd zzacdVar, zzafn zzafnVar, zzagc zzagcVar) {
        this.zze = zzyjVar;
        this.zza = zzadlVar;
        this.zzb = zzacdVar;
        this.zzc = zzafnVar;
        this.zzd = zzagcVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzafd zzafdVar) {
        List<zzafc> listZza = zzafdVar.zza();
        if (listZza == null || listZza.isEmpty()) {
            this.zza.zza("No users");
        } else {
            zzyj.zza(this.zze, this.zzb, this.zzc, listZza.get(0), this.zzd, this.zza);
        }
    }
}
