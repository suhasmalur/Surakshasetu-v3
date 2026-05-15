package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public class zzafk implements zzacs<zzafk> {
    private static final String zza = zzafk.class.getSimpleName();
    private String zzb;
    private zzap<zzafu> zzc;

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacs
    /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final zzafk zza(String str) throws zzaaf {
        zzap<zzafu> zzapVarZza;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("recaptchaKey"));
            if (jSONObject.has("recaptchaEnforcementState")) {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("recaptchaEnforcementState");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
                    zzapVarZza = zzap.zza(new ArrayList());
                } else {
                    zzao zzaoVarZzg = zzap.zzg();
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                        zzaoVarZzg.zza(jSONObject2 == null ? zzafu.zza(null, null) : zzafu.zza(Strings.emptyToNull(jSONObject2.optString("provider")), Strings.emptyToNull(jSONObject2.optString("enforcementState"))));
                    }
                    zzapVarZza = zzaoVarZzg.zza();
                }
                this.zzc = zzapVarZza;
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzahc.zza(e, zza, str);
        }
    }

    public final String zza() {
        return this.zzb;
    }

    public final boolean zzb(String str) {
        if (this.zzc == null || this.zzc.isEmpty()) {
            return false;
        }
        zzap<zzafu> zzapVar = this.zzc;
        int size = zzapVar.size();
        int i = 0;
        while (i < size) {
            zzafu zzafuVar = zzapVar.get(i);
            i++;
            zzafu zzafuVar2 = zzafuVar;
            String strZza = zzafuVar2.zza();
            String strZzb = zzafuVar2.zzb();
            if (strZza != null && strZzb != null) {
                if ((strZza.equals("ENFORCE") || strZza.equals("AUDIT")) && strZzb.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
