package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzan;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzzl implements zzadk<zzagj> {
    private final /* synthetic */ zzagg zza;
    private final /* synthetic */ zzacd zzb;

    zzzl(zzyj zzyjVar, zzagg zzaggVar, zzacd zzacdVar) {
        this.zza = zzaggVar;
        this.zzb = zzacdVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zzb.zza(zzan.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzagj zzagjVar) {
        zzagj zzagjVar2 = zzagjVar;
        if (this.zza instanceof zzagk) {
            this.zzb.zzb(zzagjVar2.zza());
        } else if (this.zza instanceof zzagm) {
            this.zzb.zza(zzagjVar2);
        } else {
            throw new IllegalArgumentException("startMfaEnrollmentRequest must be an instance of either StartPhoneMfaEnrollmentRequest or StartTotpMfaEnrollmentRequest but was " + this.zza.getClass().getName() + ".");
        }
    }
}
