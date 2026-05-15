package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p001firebaseauthapi.zzafn;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
final class zzy implements com.google.firebase.auth.internal.zzat, com.google.firebase.auth.internal.zzg {
    private final /* synthetic */ FirebaseAuth zza;

    zzy(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    @Override // com.google.firebase.auth.internal.zzg
    public final void zza(zzafn zzafnVar, FirebaseUser firebaseUser) {
        this.zza.zza(firebaseUser, zzafnVar, true, true);
    }

    @Override // com.google.firebase.auth.internal.zzat
    public final void zza(Status status) {
        int statusCode = status.getStatusCode();
        if (statusCode == 17011 || statusCode == 17021 || statusCode == 17005) {
            this.zza.signOut();
        }
    }
}
