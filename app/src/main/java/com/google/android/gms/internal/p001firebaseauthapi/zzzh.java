package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzan;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzzh implements zzadk<zzafd> {
    private final /* synthetic */ zzadk zza;
    private final /* synthetic */ zzafn zzb;
    private final /* synthetic */ zzzi zzc;

    zzzh(zzzi zzziVar, zzadk zzadkVar, zzafn zzafnVar) {
        this.zzc = zzziVar;
        this.zza = zzadkVar;
        this.zzb = zzafnVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zzc.zzb.zza(zzan.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzafd zzafdVar) {
        List<zzafc> listZza = zzafdVar.zza();
        if (listZza == null || listZza.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        zzafc zzafcVar = listZza.get(0);
        zzagc zzagcVar = new zzagc();
        zzagcVar.zzd(this.zzb.zzc()).zza(this.zzc.zza);
        zzyj.zza(this.zzc.zzc, this.zzc.zzb, this.zzb, zzafcVar, zzagcVar, this.zza);
    }
}
