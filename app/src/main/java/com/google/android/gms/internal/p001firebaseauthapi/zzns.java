package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzns {
    private static final zzns zza = new zzns();
    private final Map<String, zzch> zzb = new HashMap();

    public static zzns zza() {
        return zza;
    }

    zzns() {
    }

    private final synchronized void zza(String str, zzch zzchVar) throws GeneralSecurityException {
        if (this.zzb.containsKey(str)) {
            if (this.zzb.get(str).equals(zzchVar)) {
                return;
            }
            throw new GeneralSecurityException("Parameters object with name " + str + " already exists (" + String.valueOf(this.zzb.get(str)) + "), cannot insert " + String.valueOf(zzchVar));
        }
        this.zzb.put(str, zzchVar);
    }

    public final synchronized void zza(Map<String, zzch> map) throws GeneralSecurityException {
        for (Map.Entry<String, zzch> entry : map.entrySet()) {
            zza(entry.getKey(), entry.getValue());
        }
    }
}
