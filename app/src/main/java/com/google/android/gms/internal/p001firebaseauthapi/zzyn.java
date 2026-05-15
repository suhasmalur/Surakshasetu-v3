package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzan;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzyn implements zzadk<zzafn> {
    private final /* synthetic */ EmailAuthCredential zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzacd zzc;
    private final /* synthetic */ zzyj zzd;

    zzyn(zzyj zzyjVar, EmailAuthCredential emailAuthCredential, String str, zzacd zzacdVar) {
        this.zzd = zzyjVar;
        this.zza = emailAuthCredential;
        this.zzb = str;
        this.zzc = zzacdVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zzc.zza(zzan.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzafn zzafnVar) {
        this.zzd.zza(new zzaep(this.zza, zzafnVar.zzc(), this.zzb), this.zzc);
    }
}
