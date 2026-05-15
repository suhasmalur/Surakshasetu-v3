package com.google.android.gms.internal.p001firebaseauthapi;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Base64;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.auth.PhoneAuthCredential;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzadu {
    private static final Logger zza = new Logger("FirebaseAuth", "SmsRetrieverHelper");
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    private final HashMap<String, zzaeb> zzd = new HashMap<>();

    final zzacd zza(zzacd zzacdVar, String str) {
        return new zzadz(this, zzacdVar, str);
    }

    static String zza(String str) {
        Matcher matcher = Pattern.compile("(?<!\\d)\\d{6}(?!\\d)").matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    final String zzb() {
        Signature[] apkContentsSigners;
        try {
            String packageName = this.zzb.getPackageName();
            if (Build.VERSION.SDK_INT < 28) {
                apkContentsSigners = Wrappers.packageManager(this.zzb).getPackageInfo(packageName, 64).signatures;
            } else {
                apkContentsSigners = Wrappers.packageManager(this.zzb).getPackageInfo(packageName, 134217728).signingInfo.getApkContentsSigners();
            }
            String strZza = zza(packageName, apkContentsSigners[0].toCharsString());
            if (strZza == null) {
                zza.e("Hash generation failed.", new Object[0]);
                return null;
            }
            return strZza;
        } catch (PackageManager.NameNotFoundException e) {
            zza.e("Unable to find package to obtain hash.", new Object[0]);
            return null;
        }
    }

    private static String zza(String str, String str2) {
        String str3 = str + " " + str2;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str3.getBytes(zzq.zza));
            String strSubstring = Base64.encodeToString(Arrays.copyOf(messageDigest.digest(), 9), 3).substring(0, 11);
            zza.d("Package: " + str + " -- Hash: " + strSubstring, new Object[0]);
            return strSubstring;
        } catch (NoSuchAlgorithmException e) {
            zza.e("NoSuchAlgorithm: " + e.getMessage(), new Object[0]);
            return null;
        }
    }

    static /* synthetic */ void zza(zzadu zzaduVar, String str) {
        zzaeb zzaebVar = zzaduVar.zzd.get(str);
        if (zzaebVar == null || zzag.zzc(zzaebVar.zzd) || zzag.zzc(zzaebVar.zze) || zzaebVar.zzb.isEmpty()) {
            return;
        }
        Iterator<zzacd> it = zzaebVar.zzb.iterator();
        while (it.hasNext()) {
            it.next().zza(PhoneAuthCredential.zza(zzaebVar.zzd, zzaebVar.zze));
        }
        zzaebVar.zzh = true;
    }

    zzadu(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzb = context;
        this.zzc = scheduledExecutorService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zze(String str) {
        zzaeb zzaebVar = this.zzd.get(str);
        if (zzaebVar != null && !zzaebVar.zzh && !zzag.zzc(zzaebVar.zzd)) {
            zza.w("Timed out waiting for SMS.", new Object[0]);
            Iterator<zzacd> it = zzaebVar.zzb.iterator();
            while (it.hasNext()) {
                it.next().zza(zzaebVar.zzd);
            }
            zzaebVar.zzi = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public final void zzb(String str) {
        zzaeb zzaebVar = this.zzd.get(str);
        if (zzaebVar == null) {
            return;
        }
        if (!zzaebVar.zzi) {
            zze(str);
        }
        zzc(str);
    }

    final void zzb(zzacd zzacdVar, String str) {
        zzaeb zzaebVar = this.zzd.get(str);
        if (zzaebVar == null) {
            return;
        }
        zzaebVar.zzb.add(zzacdVar);
        if (zzaebVar.zzg) {
            zzacdVar.zzb(zzaebVar.zzd);
        }
        if (zzaebVar.zzh) {
            zzacdVar.zza(PhoneAuthCredential.zza(zzaebVar.zzd, zzaebVar.zze));
        }
        if (zzaebVar.zzi) {
            zzacdVar.zza(zzaebVar.zzd);
        }
    }

    final void zzc(String str) {
        zzaeb zzaebVar = this.zzd.get(str);
        if (zzaebVar == null) {
            return;
        }
        if (zzaebVar.zzf != null && !zzaebVar.zzf.isDone()) {
            zzaebVar.zzf.cancel(false);
        }
        zzaebVar.zzb.clear();
        this.zzd.remove(str);
    }

    final void zza(final String str, zzacd zzacdVar, long j, boolean z) {
        this.zzd.put(str, new zzaeb(j, z));
        zzb(zzacdVar, str);
        zzaeb zzaebVar = this.zzd.get(str);
        if (zzaebVar.zza <= 0) {
            zza.w("Timeout of 0 specified; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzaebVar.zzf = this.zzc.schedule(new Runnable() { // from class: com.google.android.gms.internal.firebase-auth-api.zzadx
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb(str);
            }
        }, zzaebVar.zza, TimeUnit.SECONDS);
        if (!zzaebVar.zzc) {
            zza.w("SMS auto-retrieval unavailable; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzady zzadyVar = new zzady(this, str);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
        zzc.zza(this.zzb.getApplicationContext(), zzadyVar, intentFilter);
        SmsRetriever.getClient(this.zzb).startSmsRetriever().addOnFailureListener(new zzadw(this));
    }

    final boolean zzd(String str) {
        return this.zzd.get(str) != null;
    }
}
