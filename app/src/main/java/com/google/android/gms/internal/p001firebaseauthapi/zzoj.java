package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzoj {
    private final Map<zzol, zzod<?, ?>> zza;
    private final Map<Class<?>, zzcp<?, ?>> zzb;

    public final <KeyT extends zzbt, PrimitiveT> zzoj zza(zzod<KeyT, PrimitiveT> zzodVar) throws GeneralSecurityException {
        if (zzodVar == null) {
            throw new NullPointerException("primitive constructor must be non-null");
        }
        zzol zzolVar = new zzol(zzodVar.zza(), zzodVar.zzb());
        if (this.zza.containsKey(zzolVar)) {
            zzod<?, ?> zzodVar2 = this.zza.get(zzolVar);
            if (!zzodVar2.equals(zzodVar) || !zzodVar.equals(zzodVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal PrimitiveConstructor object for already existing object of type: " + String.valueOf(zzolVar));
            }
        } else {
            this.zza.put(zzolVar, zzodVar);
        }
        return this;
    }

    public final <InputPrimitiveT, WrapperPrimitiveT> zzoj zza(zzcp<InputPrimitiveT, WrapperPrimitiveT> zzcpVar) throws GeneralSecurityException {
        if (zzcpVar == null) {
            throw new NullPointerException("wrapper must be non-null");
        }
        Class<WrapperPrimitiveT> clsZzb = zzcpVar.zzb();
        if (this.zzb.containsKey(clsZzb)) {
            zzcp<?, ?> zzcpVar2 = this.zzb.get(clsZzb);
            if (!zzcpVar2.equals(zzcpVar) || !zzcpVar.equals(zzcpVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal PrimitiveWrapper object or input class object for already existing object of type" + String.valueOf(clsZzb));
            }
        } else {
            this.zzb.put(clsZzb, zzcpVar);
        }
        return this;
    }

    public final zzok zza() {
        return new zzok(this);
    }

    private zzoj() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
    }

    private zzoj(zzok zzokVar) {
        this.zza = new HashMap(zzokVar.zza);
        this.zzb = new HashMap(zzokVar.zzb);
    }
}
