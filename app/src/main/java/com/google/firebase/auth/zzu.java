package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p001firebaseauthapi.zzagj;
import com.google.android.gms.internal.p001firebaseauthapi.zzagp;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.internal.zzby;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
final class zzu implements Continuation<zzagj, Task<TotpSecret>> {
    private final /* synthetic */ FirebaseAuth zza;

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Task<TotpSecret> then(Task<zzagj> task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException((Exception) Preconditions.checkNotNull(task.getException()));
        }
        zzagj result = task.getResult();
        if (result instanceof zzagp) {
            zzagp zzagpVar = (zzagp) result;
            return Tasks.forResult(new zzby(Preconditions.checkNotEmpty(zzagpVar.zzf()), Preconditions.checkNotEmpty(zzagpVar.zze()), zzagpVar.zzc(), zzagpVar.zzb(), zzagpVar.zzd(), Preconditions.checkNotEmpty(zzagpVar.zza()), this.zza));
        }
        throw new IllegalArgumentException("Response should be an instance of StartTotpMfaEnrollmentResponse but was " + result.getClass().getName() + ".");
    }

    zzu(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }
}
