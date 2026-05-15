package com.google.android.recaptcha.internal;

import android.content.Context;
import android.net.TrafficStats;
import androidx.browser.trusted.sharing.ShareTarget;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbj {
    public static final zzbj zza = new zzbj();
    private static Set zzb;
    private static Set zzc;
    private static Long zzd;
    private static int zze;

    private zzbj() {
    }

    public static final void zza(zzlr zzlrVar) {
        zzb = CollectionsKt.toSet(zzlrVar.zzf().zzi());
        zzc = CollectionsKt.toSet(zzlrVar.zzg().zzi());
    }

    public static final Object zzb(String str, String str2, String str3, String str4, String str5, Context context, zzr zzrVar, Continuation continuation) {
        zzh zzhVar;
        String str6;
        if (zzb != null && zzc != null) {
            return Unit.INSTANCE;
        }
        try {
            zzai zzaiVar = zzai.zza;
            zzai.zzb(new zzaf(zzkw.FETCH_ALLOWLIST, str4, str5, str5, null), str2, new zzs());
            zzhVar = null;
            zzd = null;
            zze = 0;
            String strEncode = URLEncoder.encode(str2, "UTF-8");
            String strEncode2 = URLEncoder.encode("18.1.2", "UTF-8");
            String strEncode3 = URLEncoder.encode(str3, "UTF-8");
            zzu zzuVar = zzu.zza;
            URL url = new URL(str + "?k=" + strEncode + "&msv=" + strEncode2 + "&mst=" + strEncode3 + "&mov=" + URLEncoder.encode(zzu.zzb(), "UTF-8"));
            TrafficStats.setThreadStatsTag((int) Thread.currentThread().getId());
            URLConnection uRLConnectionOpenConnection = url.openConnection();
            Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnectionOpenConnection;
            httpsURLConnection.setRequestMethod(ShareTarget.METHOD_GET);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setRequestProperty(HttpHeaders.ACCEPT, "application/x-protobuffer");
            httpsURLConnection.connect();
            try {
                if (httpsURLConnection.getResponseCode() == 200) {
                    zza(zzlr.zzj(httpsURLConnection.getInputStream()));
                    zzai.zza(new zzaf(zzkw.FETCH_ALLOWLIST, str4, str5, str5, null), zzd, zze);
                    zzai.zzc(new zzaf(zzkw.FETCH_ALLOWLIST, str4, str5, str5, null), context, zzrVar);
                    str6 = null;
                } else {
                    zzh zzhVar2 = new zzh(zzf.zzk, new zzd(httpsURLConnection.getResponseCode()));
                    Boxing.boxInt(httpsURLConnection.getResponseCode());
                    str6 = null;
                    zzhVar = zzhVar2;
                }
            } catch (Exception e) {
                e = e;
                zzhVar = e instanceof MalformedURLException ? new zzh(zzf.zze, zzd.zzc) : e instanceof zzgy ? new zzh(zzf.zze, zzd.zzS) : e instanceof IOException ? new zzh(zzf.zze, zzd.zzR) : new zzh(zzf.zzb, zzd.zzb);
                String message = e.getMessage();
                e.toString();
                str6 = message;
            }
        } catch (Exception e2) {
            e = e2;
        }
        if (zzhVar != null) {
            zzai zzaiVar2 = zzai.zza;
            zzai.zzd(new zzaf(zzkw.FETCH_ALLOWLIST, str4, str5, str5, null), String.valueOf(zzhVar.zzb().zza()), zzhVar.zza().zza(), context, zzrVar, str6);
        }
        return Unit.INSTANCE;
    }

    public static final boolean zzc(String str) {
        Set set = zzb;
        if (set == null || zzc == null) {
            if (zzd == null) {
                zzd = Long.valueOf(System.currentTimeMillis());
            }
            zze++;
            return true;
        }
        Intrinsics.checkNotNull(set, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
        if (set.isEmpty()) {
            return true;
        }
        Set set2 = zzc;
        Intrinsics.checkNotNull(set2, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
        if (zzd(str, set2)) {
            return false;
        }
        return zzd(str, set);
    }

    private static final boolean zzd(String str, Set set) {
        Iterator it = StringsKt.split$default((CharSequence) str, new char[]{'.'}, false, 0, 6, (Object) null).iterator();
        String strConcat = "";
        while (it.hasNext()) {
            String strConcat2 = strConcat.concat(String.valueOf((String) it.next()));
            if (set.contains(strConcat2)) {
                return true;
            }
            strConcat = strConcat2.concat(".");
        }
        return false;
    }
}
