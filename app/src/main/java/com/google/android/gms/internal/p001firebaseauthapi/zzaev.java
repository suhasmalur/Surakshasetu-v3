package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzaev implements zzaer {
    private final String zza;
    private String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaer
    public final /* synthetic */ zzaer zza(String str) {
        this.zzb = str;
        return this;
    }

    public static zzaev zza(String str, String str2, String str3, String str4, String str5) {
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotEmpty(str2);
        return new zzaev("phone", str, str2, str3, str4, str5);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacp
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("idToken", this.zzb);
        jSONObject.put("mfaProvider", 1);
        if (this.zzc != null) {
            jSONObject.put("displayName", this.zzc);
        }
        JSONObject jSONObject2 = new JSONObject();
        if (this.zzd != null) {
            jSONObject2.put("sessionInfo", this.zzd);
        }
        if (this.zze != null) {
            jSONObject2.put("code", this.zze);
        }
        jSONObject.put("phoneVerificationInfo", jSONObject2);
        if (this.zzf != null) {
            jSONObject.put("tenantId", this.zzf);
        }
        return jSONObject.toString();
    }

    private zzaev(String str, String str2, String str3, String str4, String str5, String str6) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = Preconditions.checkNotEmpty(str2);
        this.zzd = str3;
        this.zze = str4;
        this.zzc = str5;
        this.zzf = str6;
    }
}
