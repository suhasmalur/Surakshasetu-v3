package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p001firebaseauthapi.zzacf;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
final class zzbb extends BroadcastReceiver {
    private final WeakReference<Activity> zza;
    private final TaskCompletionSource<AuthResult> zzb;
    private final FirebaseAuth zzc;
    private final FirebaseUser zzd;
    private final /* synthetic */ zzas zze;

    zzbb(zzas zzasVar, Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        this.zze = zzasVar;
        this.zza = new WeakReference<>(activity);
        this.zzb = taskCompletionSource;
        this.zzc = firebaseAuth;
        this.zzd = firebaseUser;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (this.zza.get() == null) {
            Log.e("FederatedAuthReceiver", "Failed to unregister BroadcastReceiver because the Activity that launched this flow has been garbage collected; please do not finish() your Activity while performing a FederatedAuthProvider operation.");
            this.zzb.setException(zzacf.zza(new Status(FirebaseError.ERROR_INTERNAL_ERROR, "Activity that started the web operation is no longer alive; see logcat for details")));
            zzas.zza(context);
            return;
        }
        if (!intent.hasExtra("com.google.firebase.auth.internal.OPERATION")) {
            if (zzca.zzb(intent)) {
                this.zzb.setException(zzacf.zza(zzca.zza(intent)));
                zzas.zza(context);
                return;
            } else {
                if (intent.hasExtra("com.google.firebase.auth.internal.EXTRA_CANCELED")) {
                    this.zzb.setException(zzacf.zza(zzan.zza("WEB_CONTEXT_CANCELED")));
                    zzas.zza(context);
                    return;
                }
                return;
            }
        }
        String stringExtra = intent.getStringExtra("com.google.firebase.auth.internal.OPERATION");
        if ("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN".equals(stringExtra)) {
            zzas zzasVar = this.zze;
            TaskCompletionSource<AuthResult> taskCompletionSource = this.zzb;
            this.zzc.signInWithCredential(zzas.zza(intent)).addOnSuccessListener(new zzau(zzasVar, taskCompletionSource, context)).addOnFailureListener(new zzav(zzasVar, taskCompletionSource, context));
        } else if ("com.google.firebase.auth.internal.NONGMSCORE_LINK".equals(stringExtra)) {
            zzas zzasVar2 = this.zze;
            TaskCompletionSource<AuthResult> taskCompletionSource2 = this.zzb;
            this.zzd.linkWithCredential(zzas.zza(intent)).addOnSuccessListener(new zzaw(zzasVar2, taskCompletionSource2, context)).addOnFailureListener(new zzax(zzasVar2, taskCompletionSource2, context));
        } else {
            if ("com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE".equals(stringExtra)) {
                zzas zzasVar3 = this.zze;
                TaskCompletionSource<AuthResult> taskCompletionSource3 = this.zzb;
                this.zzd.reauthenticateAndRetrieveData(zzas.zza(intent)).addOnSuccessListener(new zzay(zzasVar3, taskCompletionSource3, context)).addOnFailureListener(new zzaz(zzasVar3, taskCompletionSource3, context));
                return;
            }
            this.zzb.setException(zzacf.zza(zzan.zza("WEB_CONTEXT_CANCELED:Unknown operation received (" + stringExtra + ")")));
        }
    }
}
