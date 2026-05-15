package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzok {
    private final Map<zzol, zzod<?, ?>> zza;
    private final Map<Class<?>, zzcp<?, ?>> zzb;

    public static zzoj zza(zzok zzokVar) {
        return new zzoj(zzokVar);
    }

    public final Class<?> zza(Class<?> cls) throws GeneralSecurityException {
        if (this.zzb.containsKey(cls)) {
            return this.zzb.get(cls).zza();
        }
        throw new GeneralSecurityException("No input primitive class for " + String.valueOf(cls) + " available");
    }

    public final <KeyT extends zzbt, PrimitiveT> PrimitiveT zza(KeyT keyt, Class<PrimitiveT> cls) throws GeneralSecurityException {
        zzol zzolVar = new zzol(keyt.getClass(), cls);
        if (!this.zza.containsKey(zzolVar)) {
            throw new GeneralSecurityException("No PrimitiveConstructor for " + String.valueOf(zzolVar) + " available");
        }
        return (PrimitiveT) this.zza.get(zzolVar).zza(keyt);
    }

    public final <InputPrimitiveT, WrapperPrimitiveT> WrapperPrimitiveT zza(zzcg<InputPrimitiveT> zzcgVar, Class<WrapperPrimitiveT> cls) throws GeneralSecurityException {
        if (!this.zzb.containsKey(cls)) {
            throw new GeneralSecurityException("No wrapper found for " + String.valueOf(cls));
        }
        zzcp<?, ?> zzcpVar = this.zzb.get(cls);
        if (!zzcgVar.zzc().equals(zzcpVar.zza()) || !zzcpVar.zza().equals(zzcgVar.zzc())) {
            throw new GeneralSecurityException("Input primitive type of the wrapper doesn't match the type of primitives in the provided PrimitiveSet");
        }
        return (WrapperPrimitiveT) zzcpVar.zza(zzcgVar);
    }

    private zzok(zzoj zzojVar) {
        this.zza = new HashMap(zzojVar.zza);
        this.zzb = new HashMap(zzojVar.zzb);
    }
}
