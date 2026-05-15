package com.google.android.gms.internal.p001firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.internal.zzan;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzzj implements zzadk<zzahd> {
    private final /* synthetic */ zzzk zza;

    zzzj(zzzk zzzkVar) {
        this.zza = zzzkVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zza.zza.zza(zzan.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzahd zzahdVar) {
        zzahd zzahdVar2 = zzahdVar;
        if (TextUtils.isEmpty(zzahdVar2.zza()) || TextUtils.isEmpty(zzahdVar2.zzb())) {
            this.zza.zza.zza(zzan.zza("INTERNAL_SUCCESS_SIGN_OUT"));
            return;
        }
        this.zza.zzb.zza(new zzafn(zzahdVar2.zzb(), zzahdVar2.zza(), Long.valueOf(zzafp.zza(zzahdVar2.zza())), "Bearer"), null, null, false, null, this.zza.zza, this);
    }
}
