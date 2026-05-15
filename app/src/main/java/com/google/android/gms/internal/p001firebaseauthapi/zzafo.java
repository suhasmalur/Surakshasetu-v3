package com.google.android.gms.internal.p001firebaseauthapi;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzafo {
    private String zza;
    private String zzb;
    private String zzc;
    private Long zzd;
    private Long zze;
    private boolean zzf;

    public static zzafo zza(String str) throws UnsupportedEncodingException {
        try {
            zzafo zzafoVar = new zzafo();
            JSONObject jSONObject = new JSONObject(str);
            zzafoVar.zza = jSONObject.optString("iss");
            zzafoVar.zzb = jSONObject.optString("aud");
            zzafoVar.zzc = jSONObject.optString("sub");
            zzafoVar.zzd = Long.valueOf(jSONObject.optLong("iat"));
            zzafoVar.zze = Long.valueOf(jSONObject.optLong("exp"));
            zzafoVar.zzf = jSONObject.optBoolean("is_anonymous");
            return zzafoVar;
        } catch (JSONException e) {
            if (Log.isLoggable("JwtToken", 3)) {
                Log.d("JwtToken", "Failed to read JwtToken from JSONObject. " + String.valueOf(e));
            }
            throw new UnsupportedEncodingException("Failed to read JwtToken from JSONObject. " + String.valueOf(e));
        }
    }

    public final Long zza() {
        return this.zze;
    }

    public final Long zzb() {
        return this.zzd;
    }
}
