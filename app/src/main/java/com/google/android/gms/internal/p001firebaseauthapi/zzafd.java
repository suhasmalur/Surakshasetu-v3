package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public class zzafd implements zzacs<zzafd> {
    private static final String zza = zzafd.class.getSimpleName();
    private zzaff zzb;

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacs
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzafd zza(String str) throws zzaaf {
        zzaff zzaffVar;
        int i;
        zzafc zzafcVar;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("users")) {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("users");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
                    zzaffVar = new zzaff(new ArrayList());
                } else {
                    ArrayList arrayList = new ArrayList(jSONArrayOptJSONArray.length());
                    boolean z = false;
                    int i2 = 0;
                    while (i2 < jSONArrayOptJSONArray.length()) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                        if (jSONObject2 == null) {
                            zzafcVar = new zzafc();
                            i = i2;
                        } else {
                            i = i2;
                            zzafcVar = new zzafc(Strings.emptyToNull(jSONObject2.optString("localId", null)), Strings.emptyToNull(jSONObject2.optString("email", null)), jSONObject2.optBoolean("emailVerified", z), Strings.emptyToNull(jSONObject2.optString("displayName", null)), Strings.emptyToNull(jSONObject2.optString("photoUrl", null)), zzafv.zza(jSONObject2.optJSONArray("providerUserInfo")), Strings.emptyToNull(jSONObject2.optString("rawPassword", null)), Strings.emptyToNull(jSONObject2.optString("phoneNumber", null)), jSONObject2.optLong("createdAt", 0L), jSONObject2.optLong("lastLoginAt", 0L), false, null, zzafr.zza(jSONObject2.optJSONArray("mfaInfo")), zzafq.zza(jSONObject2.optJSONArray("passkeyInfo")));
                        }
                        arrayList.add(zzafcVar);
                        i2 = i + 1;
                        z = false;
                    }
                    zzaffVar = new zzaff(arrayList);
                }
            } else {
                zzaffVar = new zzaff();
            }
            this.zzb = zzaffVar;
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzahc.zza(e, zza, str);
        }
    }

    public final List<zzafc> zza() {
        return this.zzb.zza();
    }
}
