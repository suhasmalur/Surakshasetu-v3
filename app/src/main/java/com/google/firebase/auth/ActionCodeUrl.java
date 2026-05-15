package com.google.firebase.auth;

import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;
import java.util.Set;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public class ActionCodeUrl {
    private static final com.google.android.gms.internal.p001firebaseauthapi.zzat<String, Integer> zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;

    public int getOperation() {
        if (zza.containsKey(this.zzd)) {
            return zza.get(this.zzd).intValue();
        }
        return 3;
    }

    public static ActionCodeUrl parseLink(String str) {
        Preconditions.checkNotEmpty(str);
        try {
            return new ActionCodeUrl(str);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getApiKey() {
        return this.zzb;
    }

    public String getCode() {
        return this.zzc;
    }

    public String getContinueUrl() {
        return this.zze;
    }

    public String getLanguageCode() {
        return this.zzf;
    }

    public final String zza() {
        return this.zzg;
    }

    private static String zza(String str, String str2) {
        Uri uri = Uri.parse(str);
        try {
            Set<String> queryParameterNames = uri.getQueryParameterNames();
            if (queryParameterNames.contains(str2)) {
                return uri.getQueryParameter(str2);
            }
            if (queryParameterNames.contains("link")) {
                return Uri.parse(Preconditions.checkNotEmpty(uri.getQueryParameter("link"))).getQueryParameter(str2);
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        } catch (UnsupportedOperationException e2) {
            return null;
        }
    }

    static {
        HashMap map = new HashMap();
        map.put("recoverEmail", 2);
        map.put("resetPassword", 0);
        map.put("signIn", 4);
        map.put("verifyEmail", 1);
        map.put("verifyBeforeChangeEmail", 5);
        map.put("revertSecondFactorAddition", 6);
        zza = com.google.android.gms.internal.p001firebaseauthapi.zzat.zza(map);
    }

    private ActionCodeUrl(String str) {
        String strZza = zza(str, "apiKey");
        String strZza2 = zza(str, "oobCode");
        String strZza3 = zza(str, "mode");
        if (strZza == null || strZza2 == null || strZza3 == null) {
            throw new IllegalArgumentException(String.format("%s, %s and %s are required in a valid action code URL", "apiKey", "oobCode", "mode"));
        }
        this.zzb = Preconditions.checkNotEmpty(strZza);
        this.zzc = Preconditions.checkNotEmpty(strZza2);
        this.zzd = Preconditions.checkNotEmpty(strZza3);
        this.zze = zza(str, "continueUrl");
        this.zzf = zza(str, "languageCode");
        this.zzg = zza(str, "tenantId");
    }
}
