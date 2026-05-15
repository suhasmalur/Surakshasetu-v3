package com.google.firebase.auth.internal;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p001firebaseauthapi.zzafk;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaTasksClient;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
final class zzbv implements Continuation<zzafk, Task<RecaptchaTasksClient>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzbs zzb;

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Task<RecaptchaTasksClient> then(Task<zzafk> task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(new zzbt((String) Preconditions.checkNotNull(((Exception) Preconditions.checkNotNull(task.getException())).getMessage())));
        }
        zzafk result = task.getResult();
        String strZza = result.zza();
        if (com.google.android.gms.internal.p001firebaseauthapi.zzag.zzc(strZza)) {
            return Tasks.forException(new zzbt("No Recaptcha Enterprise siteKey configured for tenant/project " + this.zza));
        }
        List<String> listZza = com.google.android.gms.internal.p001firebaseauthapi.zzab.zza('/').zza((CharSequence) strZza);
        String str = listZza.size() != 4 ? null : listZza.get(3);
        if (TextUtils.isEmpty(str)) {
            return Tasks.forException(new Exception("Invalid siteKey format " + strZza));
        }
        if (Log.isLoggable("RecaptchaHandler", 4)) {
            Log.i("RecaptchaHandler", "Successfully obtained site key for tenant " + this.zza);
        }
        this.zzb.zzd = result;
        Task<RecaptchaTasksClient> taskZza = this.zzb.zzc.zza((Application) this.zzb.zzb.getApplicationContext(), str);
        this.zzb.zza.put(this.zza, taskZza);
        return taskZza;
    }

    zzbv(zzbs zzbsVar, String str) {
        this.zzb = zzbsVar;
        this.zza = str;
    }
}
