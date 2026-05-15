package com.google.android.gms.internal.p001firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthCredential;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzze implements zzadk<zzahb> {
    private final /* synthetic */ zzadk zza;
    private final /* synthetic */ zzzb zzb;

    zzze(zzzb zzzbVar, zzadk zzadkVar) {
        this.zzb = zzzbVar;
        this.zza = zzadkVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzahb zzahbVar) {
        zzahb zzahbVar2 = zzahbVar;
        if (!TextUtils.isEmpty(zzahbVar2.zze())) {
            this.zzb.zza.zza(new Status(FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE), PhoneAuthCredential.zzb(zzahbVar2.zzc(), zzahbVar2.zze()));
        } else {
            this.zzb.zzb.zza(new zzafn(zzahbVar2.zzd(), zzahbVar2.zzb(), Long.valueOf(zzahbVar2.zza()), "Bearer"), null, "phone", Boolean.valueOf(zzahbVar2.zzf()), null, this.zzb.zza, this.zza);
        }
    }
}
