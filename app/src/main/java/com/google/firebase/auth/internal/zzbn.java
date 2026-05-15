package com.google.firebase.auth.internal;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p001firebaseauthapi.zzacf;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.firebase.auth.FirebaseAuth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public abstract class zzbn<T> {
    /* JADX WARN: Multi-variable type inference failed */
    private static <T> Task<T> zza(zzbs zzbsVar, RecaptchaAction recaptchaAction, String str, Continuation<String, Task<T>> continuation) {
        Task<String> taskZza = zzbsVar.zza(str, false, recaptchaAction);
        return taskZza.continueWithTask(continuation).continueWithTask(new zzbo(str, zzbsVar, recaptchaAction, continuation));
    }

    public abstract Task<T> zza(String str);

    public final Task<T> zza(final FirebaseAuth firebaseAuth, final String str, final RecaptchaAction recaptchaAction, String str2) {
        final Continuation continuation = new Continuation() { // from class: com.google.firebase.auth.internal.zzbp
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                zzbn zzbnVar = this.zza;
                if (task.isSuccessful()) {
                    return zzbnVar.zza((String) task.getResult());
                }
                Log.e("RecaptchaCallWrapper", "Failed to get Recaptcha token, error - " + ((Exception) Preconditions.checkNotNull(task.getException())).getMessage() + "\n\n Failing open with a fake token.");
                return zzbnVar.zza("NO_RECAPTCHA");
            }
        };
        zzbs zzbsVarZzb = firebaseAuth.zzb();
        if (zzbsVarZzb != null && zzbsVarZzb.zza(str2)) {
            return zza(zzbsVarZzb, recaptchaAction, str, continuation);
        }
        return (Task<T>) zza(null).continueWithTask(new Continuation() { // from class: com.google.firebase.auth.internal.zzbm
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzbn.zza(recaptchaAction, firebaseAuth, str, continuation, task);
            }
        });
    }

    static /* synthetic */ Task zza(RecaptchaAction recaptchaAction, FirebaseAuth firebaseAuth, String str, Continuation continuation, Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(task.getResult());
        }
        Exception exc = (Exception) Preconditions.checkNotNull(task.getException());
        if (zzacf.zzc(exc)) {
            if (Log.isLoggable("RecaptchaCallWrapper", 4)) {
                Log.i("RecaptchaCallWrapper", "Falling back to recaptcha enterprise flow for action " + String.valueOf(recaptchaAction));
            }
            if (firebaseAuth.zzb() == null) {
                firebaseAuth.zza(new zzbs(firebaseAuth.getApp(), firebaseAuth));
            }
            return zza(firebaseAuth.zzb(), recaptchaAction, str, continuation);
        }
        Log.e("RecaptchaCallWrapper", "Initial task failed for action " + String.valueOf(recaptchaAction) + "with exception - " + exc.getMessage());
        return Tasks.forException(exc);
    }
}
