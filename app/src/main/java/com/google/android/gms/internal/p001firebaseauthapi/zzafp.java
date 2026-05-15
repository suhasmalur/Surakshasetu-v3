package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import java.io.UnsupportedEncodingException;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzafp {
    public static long zza(String str) {
        zzafo zzafoVarZzb = zzb(str);
        return zzafoVarZzb.zza().longValue() - zzafoVarZzb.zzb().longValue();
    }

    private static zzafo zzb(String str) {
        Preconditions.checkNotEmpty(str);
        List<String> listZza = zzab.zza('.').zza((CharSequence) str);
        if (listZza.size() < 2) {
            throw new RuntimeException("Invalid idToken " + str);
        }
        try {
            return zzafo.zza(new String(Base64Utils.decodeUrlSafeNoPadding(listZza.get(1)), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unable to decode token", e);
        }
    }
}
