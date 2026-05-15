package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzakn;
import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zznb<KeyProtoT extends zzakn> {
    private final Class<KeyProtoT> zza;
    private final Map<Class<?>, zzoh<?, KeyProtoT>> zzb;
    private final Class<?> zzc;

    public abstract KeyProtoT zza(zzahp zzahpVar) throws zzaji;

    public abstract void zza(KeyProtoT keyprotot) throws GeneralSecurityException;

    public abstract zzif.zza zzb();

    public zzne<?, KeyProtoT> zzc() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }

    public abstract zzuy.zza zzd();

    public abstract String zze();

    public final Class<?> zzf() {
        return this.zzc;
    }

    public final Class<KeyProtoT> zzg() {
        return this.zza;
    }

    public final <P> P zza(KeyProtoT keyprotot, Class<P> cls) throws GeneralSecurityException {
        zzoh<?, KeyProtoT> zzohVar = this.zzb.get(cls);
        if (zzohVar == null) {
            throw new IllegalArgumentException("Requested primitive class " + cls.getCanonicalName() + " not supported.");
        }
        return (P) zzohVar.zza(keyprotot);
    }

    public final Set<Class<?>> zzh() {
        return this.zzb.keySet();
    }

    @SafeVarargs
    protected zznb(Class<KeyProtoT> cls, zzoh<?, KeyProtoT>... zzohVarArr) {
        this.zza = cls;
        HashMap map = new HashMap();
        for (zzoh<?, KeyProtoT> zzohVar : zzohVarArr) {
            if (map.containsKey(zzohVar.zza())) {
                throw new IllegalArgumentException("KeyTypeManager constructed with duplicate factories for primitive " + zzohVar.zza().getCanonicalName());
            }
            map.put(zzohVar.zza(), zzohVar);
        }
        if (zzohVarArr.length > 0) {
            this.zzc = zzohVarArr[0].zza();
        } else {
            this.zzc = Void.class;
        }
        this.zzb = Collections.unmodifiableMap(map);
    }
}
