package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzalb {
    private static final zzalb zza = new zzalb();
    private final ConcurrentMap<Class<?>, zzalf<?>> zzc = new ConcurrentHashMap();
    private final zzale zzb = new zzakb();

    public static zzalb zza() {
        return zza;
    }

    public final <T> zzalf<T> zza(Class<T> cls) {
        zzajf.zza(cls, "messageType");
        zzalf<T> zzalfVar = (zzalf) this.zzc.get(cls);
        if (zzalfVar != null) {
            return zzalfVar;
        }
        zzalf<T> zzalfVarZza = this.zzb.zza(cls);
        zzajf.zza(cls, "messageType");
        zzajf.zza(zzalfVarZza, "schema");
        zzalf<T> zzalfVar2 = (zzalf) this.zzc.putIfAbsent(cls, zzalfVarZza);
        return zzalfVar2 != null ? zzalfVar2 : zzalfVarZza;
    }

    public final <T> zzalf<T> zza(T t) {
        return zza((Class) t.getClass());
    }

    private zzalb() {
    }
}
