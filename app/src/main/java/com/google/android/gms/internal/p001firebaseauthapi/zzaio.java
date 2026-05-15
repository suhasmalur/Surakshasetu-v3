package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzaio {
    private final Map<zzair, zzajc.zzd<?, ?>> zzd;
    private static volatile boolean zzb = false;
    private static boolean zzc = true;
    static final zzaio zza = new zzaio(true);

    public static zzaio zza() {
        return zza;
    }

    public final <ContainingType extends zzakn> zzajc.zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzajc.zzd) this.zzd.get(new zzair(containingtype, i));
    }

    zzaio() {
        this.zzd = new HashMap();
    }

    private zzaio(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
