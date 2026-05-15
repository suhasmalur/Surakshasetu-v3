package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthProvider;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
final class zzk implements OnCompleteListener<com.google.firebase.auth.internal.zze> {
    private final /* synthetic */ PhoneAuthOptions zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ FirebaseAuth zzc;

    zzk(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions, String str) {
        this.zzc = firebaseAuth;
        this.zza = phoneAuthOptions;
        this.zzb = str;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<com.google.firebase.auth.internal.zze> task) {
        String strZzc;
        String strZza;
        if (task.isSuccessful()) {
            strZzc = task.getResult().zzc();
            strZza = task.getResult().zza();
        } else {
            Exception exception = task.getException();
            Log.e("FirebaseAuth", exception != null ? "Error while validating application identity: " + exception.getMessage() : "Error while validating application identity: ");
            if (exception != null && com.google.firebase.auth.internal.zzb.zza(exception)) {
                FirebaseAuth.zza((FirebaseException) exception, this.zza, this.zzb);
                return;
            } else {
                Log.e("FirebaseAuth", "Proceeding without any application identifier.");
                strZzc = null;
                strZza = null;
            }
        }
        long jLongValue = this.zza.zzg().longValue();
        PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacksZza = this.zzc.zza(this.zza.zzh(), this.zza.zze());
        PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacksZza2 = TextUtils.isEmpty(strZzc) ? this.zzc.zza(this.zza, onVerificationStateChangedCallbacksZza) : onVerificationStateChangedCallbacksZza;
        com.google.firebase.auth.internal.zzal zzalVar = (com.google.firebase.auth.internal.zzal) Preconditions.checkNotNull(this.zza.zzc());
        if (zzalVar.zzd()) {
            this.zzc.zze.zza(zzalVar, (String) Preconditions.checkNotNull(this.zza.zzh()), this.zzc.zzi, jLongValue, this.zza.zzd() != null, this.zza.zzk(), strZzc, strZza, this.zzc.zzi(), onVerificationStateChangedCallbacksZza2, this.zza.zzi(), this.zza.zza());
        } else {
            this.zzc.zze.zza(zzalVar, (PhoneMultiFactorInfo) Preconditions.checkNotNull(this.zza.zzf()), this.zzc.zzi, jLongValue, this.zza.zzd() != null, this.zza.zzk(), strZzc, strZza, this.zzc.zzi(), onVerificationStateChangedCallbacksZza2, this.zza.zzi(), this.zza.zza());
        }
    }
}
