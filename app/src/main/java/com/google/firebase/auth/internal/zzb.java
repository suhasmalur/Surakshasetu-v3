package com.google.firebase.auth.internal;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p001firebaseauthapi.zzacf;
import com.google.android.gms.internal.p001firebaseauthapi.zzadr;
import com.google.android.gms.internal.p001firebaseauthapi.zzaed;
import com.google.android.gms.internal.p001firebaseauthapi.zzafj;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.integrity.IntegrityManagerFactory;
import com.google.android.play.core.integrity.IntegrityTokenResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public class zzb {
    private static final String zza = zzb.class.getSimpleName();
    private static final zzb zzb = new zzb();
    private String zzc;

    public final Task<zze> zza(final FirebaseAuth firebaseAuth, String str, final Activity activity, boolean z, boolean z2) {
        zzab zzabVar = (zzab) firebaseAuth.getFirebaseAuthSettings();
        final zzcc zzccVarZzc = zzcc.zzc();
        if (zzaed.zza(firebaseAuth.getApp()) || zzabVar.zze()) {
            return Tasks.forResult(new zzl().zza());
        }
        Log.i(zza, "ForceRecaptchaFlow from phoneAuthOptions = " + z2 + ", ForceRecaptchaFlow from firebaseSettings = " + zzabVar.zzc());
        boolean zZzc = z2 | zzabVar.zzc();
        final TaskCompletionSource<zze> taskCompletionSource = new TaskCompletionSource<>();
        Task<String> taskZzb = zzccVarZzc.zzb();
        if (taskZzb != null) {
            if (taskZzb.isSuccessful()) {
                return Tasks.forResult(new zzl().zzb(taskZzb.getResult()).zza());
            }
            Log.e(zza, "Error in previous reCAPTCHA flow: " + taskZzb.getException().getMessage());
            Log.e(zza, "Continuing with application verification as normal");
        }
        if (!z || zZzc) {
            zza(firebaseAuth, zzccVarZzc, activity, taskCompletionSource);
        } else {
            (!TextUtils.isEmpty(this.zzc) ? Tasks.forResult(new zzafj(this.zzc)) : firebaseAuth.zza()).continueWithTask(firebaseAuth.zzf(), new zzd(this, str, IntegrityManagerFactory.create(firebaseAuth.getApp().getApplicationContext()))).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.firebase.auth.internal.zza
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    this.zza.zza(taskCompletionSource, firebaseAuth, zzccVarZzc, activity, task);
                }
            });
        }
        return taskCompletionSource.getTask();
    }

    public static zzb zza() {
        return zzb;
    }

    private zzb() {
    }

    private final void zza(FirebaseAuth firebaseAuth, zzcc zzccVar, Activity activity, TaskCompletionSource<zze> taskCompletionSource) {
        Task<String> task;
        if (activity == null) {
            taskCompletionSource.setException(new FirebaseAuthMissingActivityForRecaptchaException());
            return;
        }
        zzbj.zza(firebaseAuth.getApp().getApplicationContext(), firebaseAuth);
        Preconditions.checkNotNull(activity);
        TaskCompletionSource<String> taskCompletionSource2 = new TaskCompletionSource<>();
        if (!zzas.zza().zza(activity, taskCompletionSource2)) {
            task = Tasks.forException(zzacf.zza(new Status(17057, "reCAPTCHA flow already in progress")));
        } else {
            new zzadr(firebaseAuth, activity).zza();
            task = taskCompletionSource2.getTask();
        }
        task.addOnSuccessListener(new zzf(this, taskCompletionSource)).addOnFailureListener(new zzc(this, taskCompletionSource));
    }

    final /* synthetic */ void zza(TaskCompletionSource taskCompletionSource, FirebaseAuth firebaseAuth, zzcc zzccVar, Activity activity, Task task) {
        if ((!task.isSuccessful() || task.getResult() == null || TextUtils.isEmpty(((IntegrityTokenResponse) task.getResult()).token())) ? false : true) {
            taskCompletionSource.setResult(new zzl().zza(((IntegrityTokenResponse) task.getResult()).token()).zza());
        } else {
            Log.e(zza, "Play Integrity Token fetch failed, falling back to Recaptcha" + (task.getException() == null ? "" : task.getException().getMessage()));
            zza(firebaseAuth, zzccVar, activity, taskCompletionSource);
        }
    }

    public static boolean zza(Exception exc) {
        if (!(exc instanceof FirebaseAuthMissingActivityForRecaptchaException)) {
            if ((exc instanceof FirebaseAuthException) && ((FirebaseAuthException) exc).getErrorCode().endsWith("UNAUTHORIZED_DOMAIN")) {
                return true;
            }
            return false;
        }
        return true;
    }
}
