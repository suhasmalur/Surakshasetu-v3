package com.google.android.recaptcha.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzfz {
    public static final /* synthetic */ int zzb = 0;
    private final Map zzd;
    private static volatile boolean zzc = false;
    static final zzfz zza = new zzfz(true);

    zzfz() {
        this.zzd = new HashMap();
    }

    public final zzgm zza(zzhy zzhyVar, int i) {
        return (zzgm) this.zzd.get(new zzfy(zzhyVar, i));
    }

    zzfz(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
