package com.google.firebase.auth.internal;

import com.google.android.gms.internal.p001firebaseauthapi.zzafk;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaTasksClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbs {
    Map<String, Task<RecaptchaTasksClient>> zza;
    FirebaseApp zzb;
    zzbr zzc;
    private zzafk zzd;
    private FirebaseAuth zze;

    private final Task<RecaptchaTasksClient> zzb(String str) {
        return this.zza.get(str);
    }

    public final Task<String> zza(String str, Boolean bool, RecaptchaAction recaptchaAction) {
        String strZzc = zzc(str);
        Task<RecaptchaTasksClient> taskZzb = zzb(strZzc);
        if (bool.booleanValue() || taskZzb == null) {
            taskZzb = zza(strZzc, bool);
        }
        return taskZzb.continueWithTask(new zzbu(this, recaptchaAction));
    }

    public final Task<RecaptchaTasksClient> zza(String str, Boolean bool) {
        Task<RecaptchaTasksClient> taskZzb;
        String strZzc = zzc(str);
        if (!bool.booleanValue() && (taskZzb = zzb(strZzc)) != null) {
            return taskZzb;
        }
        return this.zze.zza("RECAPTCHA_ENTERPRISE").continueWithTask(new zzbv(this, strZzc));
    }

    private static String zzc(String str) {
        if (com.google.android.gms.internal.p001firebaseauthapi.zzag.zzc(str)) {
            return "*";
        }
        return str;
    }

    public zzbs(FirebaseApp firebaseApp, FirebaseAuth firebaseAuth) {
        this(firebaseApp, firebaseAuth, new zzbq());
    }

    private zzbs(FirebaseApp firebaseApp, FirebaseAuth firebaseAuth, zzbr zzbrVar) {
        this.zza = new HashMap();
        this.zzb = firebaseApp;
        this.zze = firebaseAuth;
        this.zzc = zzbrVar;
    }

    public final boolean zza(String str) {
        return this.zzd != null && this.zzd.zzb(str);
    }
}
