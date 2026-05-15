package com.google.android.recaptcha.internal;

import android.content.Context;
import android.os.Build;
import android.webkit.WebView;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzda {
    public static final zzct zza = new zzct(null);
    public CompletableDeferred zzb;
    private final WebView zzc;
    private final String zzd;
    private final Context zze;
    private final zzr zzf;
    private final String zzg;
    private final String zzh;
    private final zzaj zzi;
    private final Map zzj;
    private final Map zzk = new LinkedHashMap();
    private final Map zzl = this.zzk;
    private final zzas zzm;
    private final zzdk zzn;
    private final Mutex zzo;
    private final zzcu zzp;

    public zzda(WebView webView, String str, Context context, zzr zzrVar, String str2, String str3, zzaj zzajVar, CoroutineScope coroutineScope) {
        this.zzc = webView;
        this.zzd = str;
        this.zze = context;
        this.zzf = zzrVar;
        this.zzg = str2;
        this.zzh = str3;
        this.zzi = zzajVar;
        zzbc zzbcVar = new zzbc(this.zzc, coroutineScope);
        Context context2 = this.zze;
        zzp zzpVar = zzp.zza;
        this.zzm = new zzba(zzbcVar, zzp.zzc(), context2);
        this.zzn = zzdk.zzc();
        this.zzo = MutexKt.Mutex$default(false, 1, null);
        this.zzp = new zzcu(this);
        this.zzj = zzq();
        this.zzc.getSettings().setJavaScriptEnabled(true);
        this.zzc.addJavascriptInterface(this.zzp, "RN");
        this.zzc.setWebViewClient(new zzcs(this));
    }

    private final zzh zzo(Exception exc) {
        return exc instanceof TimeoutCancellationException ? new zzh(zzf.zzc, zzd.zzj) : exc instanceof zzh ? (zzh) exc : new zzh(zzf.zzc, zzd.zzu);
    }

    private final void zzp(List list, zzh zzhVar) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzkw zzkwVar = (zzkw) it.next();
            zzai zzaiVar = zzai.zza;
            String str = this.zzg;
            String str2 = this.zzh;
            zzai.zzd(new zzaf(zzkwVar, str, str2, str2, null), String.valueOf(zzhVar.zzb().zza()), zzhVar.zza().zza(), this.zze, this.zzf, null);
        }
    }

    private static final Map zzq() {
        Map mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to(-4, zzd.zzA), TuplesKt.to(-12, zzd.zzB), TuplesKt.to(-6, zzd.zzw), TuplesKt.to(-11, zzd.zzy), TuplesKt.to(-13, zzd.zzC), TuplesKt.to(-14, zzd.zzD), TuplesKt.to(-2, zzd.zzx), TuplesKt.to(-7, zzd.zzE), TuplesKt.to(-5, zzd.zzF), TuplesKt.to(-9, zzd.zzG), TuplesKt.to(-8, zzd.zzQ), TuplesKt.to(-15, zzd.zzz), TuplesKt.to(-1, zzd.zzH), TuplesKt.to(-3, zzd.zzJ), TuplesKt.to(-10, zzd.zzK));
        if (Build.VERSION.SDK_INT >= 26) {
            mapMutableMapOf.put(-16, zzd.zzI);
        }
        if (Build.VERSION.SDK_INT >= 27) {
            mapMutableMapOf.put(1, zzd.zzM);
            mapMutableMapOf.put(2, zzd.zzN);
            mapMutableMapOf.put(0, zzd.zzO);
            mapMutableMapOf.put(3, zzd.zzP);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            mapMutableMapOf.put(4, zzd.zzL);
        }
        return mapMutableMapOf;
    }

    public final WebView zzb() {
        return this.zzc;
    }

    public final zzas zzd() {
        return this.zzm;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object zzf(com.google.android.recaptcha.RecaptchaAction r11, kotlin.coroutines.Continuation r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzda.zzf(com.google.android.recaptcha.RecaptchaAction, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object zzg(kotlin.coroutines.Continuation r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzda.zzg(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final CompletableDeferred zzm() {
        CompletableDeferred completableDeferred = this.zzb;
        if (completableDeferred != null) {
            return completableDeferred;
        }
        return null;
    }
}
