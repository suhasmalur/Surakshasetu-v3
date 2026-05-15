package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthProvider;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzadd implements zzadf {
    private final /* synthetic */ Status zza;

    zzadd(zzacz zzaczVar, Status status) {
        this.zza = status;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadf
    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationFailed(zzacf.zza(this.zza));
    }
}
