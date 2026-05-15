package com.google.android.gms.internal.p001firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzagy implements zzacp {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private boolean zzf;

    public static zzagy zza(String str, String str2, boolean z) {
        zzagy zzagyVar = new zzagy();
        zzagyVar.zzb = Preconditions.checkNotEmpty(str);
        zzagyVar.zzc = Preconditions.checkNotEmpty(str2);
        zzagyVar.zzf = z;
        return zzagyVar;
    }

    public static zzagy zzb(String str, String str2, boolean z) {
        zzagy zzagyVar = new zzagy();
        zzagyVar.zza = Preconditions.checkNotEmpty(str);
        zzagyVar.zzd = Preconditions.checkNotEmpty(str2);
        zzagyVar.zzf = z;
        return zzagyVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacp
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.zzd)) {
            jSONObject.put("phoneNumber", this.zza);
            jSONObject.put("temporaryProof", this.zzd);
        } else {
            jSONObject.put("sessionInfo", this.zzb);
            jSONObject.put("code", this.zzc);
        }
        if (this.zze != null) {
            jSONObject.put("idToken", this.zze);
        }
        if (!this.zzf) {
            jSONObject.put("operation", 2);
        }
        return jSONObject.toString();
    }

    private zzagy() {
    }

    public final void zza(String str) {
        this.zze = str;
    }
}
