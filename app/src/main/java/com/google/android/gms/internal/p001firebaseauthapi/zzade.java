package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzade<ResultT, CallbackT> implements zzacv<ResultT> {
    private final zzacx<ResultT, CallbackT> zza;
    private final TaskCompletionSource<ResultT> zzb;

    public zzade(zzacx<ResultT, CallbackT> zzacxVar, TaskCompletionSource<ResultT> taskCompletionSource) {
        this.zza = zzacxVar;
        this.zzb = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacv
    public final void zza(ResultT resultt, Status status) {
        FirebaseUser firebaseUser;
        Preconditions.checkNotNull(this.zzb, "completion source cannot be null");
        if (status != null) {
            if (this.zza.zzs != null) {
                TaskCompletionSource<ResultT> taskCompletionSource = this.zzb;
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(this.zza.zzc);
                zzyk zzykVar = this.zza.zzs;
                if ("reauthenticateWithCredential".equals(this.zza.zza()) || "reauthenticateWithCredentialWithData".equals(this.zza.zza())) {
                    firebaseUser = this.zza.zzd;
                } else {
                    firebaseUser = null;
                }
                taskCompletionSource.setException(zzacf.zza(firebaseAuth, zzykVar, firebaseUser));
                return;
            }
            if (this.zza.zzp != null) {
                this.zzb.setException(zzacf.zza(status, this.zza.zzp, this.zza.zzq, this.zza.zzr));
                return;
            } else {
                this.zzb.setException(zzacf.zza(status));
                return;
            }
        }
        this.zzb.setResult(resultt);
    }
}
