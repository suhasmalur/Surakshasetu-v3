package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.p001firebaseauthapi.zzafn;
import com.google.android.gms.internal.p001firebaseauthapi.zzxw;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorGenerator;
import com.google.firebase.auth.TotpMultiFactorInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbx {
    private Context zza;
    private String zzb;
    private SharedPreferences zzc;
    private Logger zzd;

    public final FirebaseUser zza() {
        String string = this.zzc.getString("com.google.firebase.auth.FIREBASE_USER", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("type") && "com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(jSONObject.optString("type"))) {
                return zza(jSONObject);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public final zzafn zza(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String string = this.zzc.getString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", firebaseUser.getUid()), null);
        if (string != null) {
            return zzafn.zzb(string);
        }
        return null;
    }

    private final zzaa zza(JSONObject jSONObject) {
        JSONArray jSONArray;
        MultiFactorInfo multiFactorInfoZza;
        zzac zzacVarZza;
        try {
            String string = jSONObject.getString("cachedTokenState");
            String string2 = jSONObject.getString("applicationName");
            boolean z = jSONObject.getBoolean("anonymous");
            String str = "2";
            String string3 = jSONObject.getString("version");
            if (string3 != null) {
                str = string3;
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray("userInfos");
            int length = jSONArray2.length();
            if (length == 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                arrayList.add(zzw.zza(jSONArray2.getString(i)));
            }
            zzaa zzaaVar = new zzaa(FirebaseApp.getInstance(string2), arrayList);
            if (!TextUtils.isEmpty(string)) {
                zzaaVar.zza(zzafn.zzb(string));
            }
            if (!z) {
                zzaaVar.zzb();
            }
            zzaaVar.zza(str);
            if (jSONObject.has("userMetadata") && (zzacVarZza = zzac.zza(jSONObject.getJSONObject("userMetadata"))) != null) {
                zzaaVar.zza(zzacVarZza);
            }
            if (jSONObject.has("userMultiFactorInfo") && (jSONArray = jSONObject.getJSONArray("userMultiFactorInfo")) != null) {
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject2 = new JSONObject(jSONArray.getString(i2));
                    String strOptString = jSONObject2.optString(MultiFactorInfo.FACTOR_ID_KEY);
                    if ("phone".equals(strOptString)) {
                        multiFactorInfoZza = PhoneMultiFactorInfo.zza(jSONObject2);
                    } else if (strOptString == TotpMultiFactorGenerator.FACTOR_ID || (strOptString != null && strOptString.equals(TotpMultiFactorGenerator.FACTOR_ID))) {
                        multiFactorInfoZza = TotpMultiFactorInfo.zza(jSONObject2);
                    } else {
                        multiFactorInfoZza = null;
                    }
                    arrayList2.add(multiFactorInfoZza);
                }
                zzaaVar.zzb(arrayList2);
            }
            return zzaaVar;
        } catch (zzxw | ArrayIndexOutOfBoundsException | IllegalArgumentException | JSONException e) {
            this.zzd.wtf(e);
            return null;
        }
    }

    private final String zzc(FirebaseUser firebaseUser) {
        boolean z;
        JSONObject jSONObject = new JSONObject();
        if (zzaa.class.isAssignableFrom(firebaseUser.getClass())) {
            zzaa zzaaVar = (zzaa) firebaseUser;
            try {
                jSONObject.put("cachedTokenState", zzaaVar.zze());
                jSONObject.put("applicationName", zzaaVar.zza().getName());
                jSONObject.put("type", "com.google.firebase.auth.internal.DefaultFirebaseUser");
                if (zzaaVar.zzi() != null) {
                    JSONArray jSONArray = new JSONArray();
                    List<zzw> listZzi = zzaaVar.zzi();
                    int size = listZzi.size();
                    if (listZzi.size() > 30) {
                        this.zzd.w("Provider user info list size larger than max size, truncating list to %d. Actual list size: %d", 30, Integer.valueOf(listZzi.size()));
                        size = 30;
                    }
                    int i = 0;
                    boolean z2 = false;
                    while (true) {
                        z = true;
                        if (i >= size) {
                            break;
                        }
                        zzw zzwVar = listZzi.get(i);
                        if (zzwVar.getProviderId().equals(FirebaseAuthProvider.PROVIDER_ID)) {
                            z2 = true;
                        }
                        if (i == size - 1 && !z2) {
                            break;
                        }
                        jSONArray.put(zzwVar.zzb());
                        i++;
                    }
                    if (!z2) {
                        for (int i2 = size - 1; i2 < listZzi.size() && i2 >= 0; i2++) {
                            zzw zzwVar2 = listZzi.get(i2);
                            if (zzwVar2.getProviderId().equals(FirebaseAuthProvider.PROVIDER_ID)) {
                                jSONArray.put(zzwVar2.zzb());
                                break;
                            }
                            if (i2 == listZzi.size() - 1) {
                                jSONArray.put(zzwVar2.zzb());
                            }
                        }
                        z = z2;
                        if (!z) {
                            this.zzd.w("Malformed user object! No Firebase Auth provider id found. Provider user info list size: %d, trimmed size: %d", Integer.valueOf(listZzi.size()), Integer.valueOf(size));
                            if (listZzi.size() < 5) {
                                StringBuilder sb = new StringBuilder("Provider user info list:\n");
                                Iterator<zzw> it = listZzi.iterator();
                                while (it.hasNext()) {
                                    sb.append(String.format("Provider - %s\n", it.next().getProviderId()));
                                }
                                this.zzd.w(sb.toString(), new Object[0]);
                            }
                        }
                    }
                    jSONObject.put("userInfos", jSONArray);
                }
                jSONObject.put("anonymous", zzaaVar.isAnonymous());
                jSONObject.put("version", "2");
                if (zzaaVar.getMetadata() != null) {
                    jSONObject.put("userMetadata", ((zzac) zzaaVar.getMetadata()).zza());
                }
                List<MultiFactorInfo> enrolledFactors = ((zzae) zzaaVar.getMultiFactor()).getEnrolledFactors();
                if (enrolledFactors != null && !enrolledFactors.isEmpty()) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i3 = 0; i3 < enrolledFactors.size(); i3++) {
                        jSONArray2.put(enrolledFactors.get(i3).toJson());
                    }
                    jSONObject.put("userMultiFactorInfo", jSONArray2);
                }
                return jSONObject.toString();
            } catch (Exception e) {
                this.zzd.wtf("Failed to turn object into JSON", e, new Object[0]);
                throw new zzxw(e);
            }
        }
        return null;
    }

    public zzbx(Context context, String str) {
        Preconditions.checkNotNull(context);
        this.zzb = Preconditions.checkNotEmpty(str);
        this.zza = context.getApplicationContext();
        this.zzc = this.zza.getSharedPreferences(String.format("com.google.firebase.auth.api.Store.%s", this.zzb), 0);
        this.zzd = new Logger("StorageHelpers", new String[0]);
    }

    public final void zza(String str) {
        this.zzc.edit().remove(str).apply();
    }

    public final void zzb(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String strZzc = zzc(firebaseUser);
        if (!TextUtils.isEmpty(strZzc)) {
            this.zzc.edit().putString("com.google.firebase.auth.FIREBASE_USER", strZzc).apply();
        }
    }

    public final void zza(FirebaseUser firebaseUser, zzafn zzafnVar) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzafnVar);
        this.zzc.edit().putString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", firebaseUser.getUid()), zzafnVar.zzf()).apply();
    }
}
