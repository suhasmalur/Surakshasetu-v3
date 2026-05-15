package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.internal.zzbf;
import com.google.firebase.auth.zzf;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzyk {
    private String zza;
    private List<zzafr> zzb;
    private zzf zzc;

    public final zzf zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zza;
    }

    public final List<MultiFactorInfo> zzc() {
        return zzbf.zza(this.zzb);
    }

    public zzyk(String str, List<zzafr> list, zzf zzfVar) {
        this.zza = str;
        this.zzb = list;
        this.zzc = zzfVar;
    }
}
