package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzan;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaac implements zzadk<zzafn> {
    private final /* synthetic */ UserProfileChangeRequest zza;
    private final /* synthetic */ zzacd zzb;
    private final /* synthetic */ zzyj zzc;

    zzaac(zzyj zzyjVar, UserProfileChangeRequest userProfileChangeRequest, zzacd zzacdVar) {
        this.zzc = zzyjVar;
        this.zza = userProfileChangeRequest;
        this.zzb = zzacdVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zzb.zza(zzan.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzafn zzafnVar) {
        zzafn zzafnVar2 = zzafnVar;
        zzagc zzagcVar = new zzagc();
        zzagcVar.zzd(zzafnVar2.zzc());
        if (this.zza.zzb() || this.zza.getDisplayName() != null) {
            zzagcVar.zzb(this.zza.getDisplayName());
        }
        if (this.zza.zzc() || this.zza.getPhotoUri() != null) {
            zzagcVar.zzg(this.zza.zza());
        }
        zzyj.zza(this.zzc, this.zzb, zzafnVar2, zzagcVar, this);
    }
}
