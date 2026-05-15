package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzoy {
    private final Map<zzpc, zzmx<?, ?>> zza;
    private final Map<zzpa, zzmu<?>> zzb;
    private final Map<zzpc, zznz<?, ?>> zzc;
    private final Map<zzpa, zznv<?>> zzd;

    public final <SerializationT extends zzov> zzbt zza(SerializationT serializationt, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        zzpa zzpaVar = new zzpa(serializationt.getClass(), serializationt.zzb());
        if (this.zzb.containsKey(zzpaVar)) {
            return this.zzb.get(zzpaVar).zza(serializationt, zzcsVar);
        }
        throw new GeneralSecurityException("No Key Parser for requested key type " + String.valueOf(zzpaVar) + " available");
    }

    public final <SerializationT extends zzov> zzch zza(SerializationT serializationt) throws GeneralSecurityException {
        zzpa zzpaVar = new zzpa(serializationt.getClass(), serializationt.zzb());
        if (!this.zzd.containsKey(zzpaVar)) {
            throw new GeneralSecurityException("No Parameters Parser for requested key type " + String.valueOf(zzpaVar) + " available");
        }
        return this.zzd.get(zzpaVar).zza(serializationt);
    }

    public final <ParametersT extends zzch, SerializationT extends zzov> SerializationT zza(ParametersT parameterst, Class<SerializationT> cls) throws GeneralSecurityException {
        zzpc zzpcVar = new zzpc(parameterst.getClass(), cls);
        if (!this.zzc.containsKey(zzpcVar)) {
            throw new GeneralSecurityException("No Key Format serializer for " + String.valueOf(zzpcVar) + " available");
        }
        return (SerializationT) this.zzc.get(zzpcVar).zza(parameterst);
    }

    private zzoy(zzox zzoxVar) {
        this.zza = new HashMap(zzoxVar.zza);
        this.zzb = new HashMap(zzoxVar.zzb);
        this.zzc = new HashMap(zzoxVar.zzc);
        this.zzd = new HashMap(zzoxVar.zzd);
    }

    public final <SerializationT extends zzov> boolean zzb(SerializationT serializationt) {
        return this.zzb.containsKey(new zzpa(serializationt.getClass(), serializationt.zzb()));
    }

    public final <SerializationT extends zzov> boolean zzc(SerializationT serializationt) {
        return this.zzd.containsKey(new zzpa(serializationt.getClass(), serializationt.zzb()));
    }
}
