package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zznr {
    private static zznr zza = new zznr();
    private final AtomicReference<zzok> zzb = new AtomicReference<>(new zzoj().zza());

    public static zznr zza() {
        return zza;
    }

    public final <WrapperPrimitiveT> Class<?> zza(Class<WrapperPrimitiveT> cls) throws GeneralSecurityException {
        return this.zzb.get().zza((Class<?>) cls);
    }

    public final <KeyT extends zzbt, PrimitiveT> PrimitiveT zza(KeyT keyt, Class<PrimitiveT> cls) throws GeneralSecurityException {
        return (PrimitiveT) this.zzb.get().zza(keyt, cls);
    }

    public final <InputPrimitiveT, WrapperPrimitiveT> WrapperPrimitiveT zza(zzcg<InputPrimitiveT> zzcgVar, Class<WrapperPrimitiveT> cls) throws GeneralSecurityException {
        return (WrapperPrimitiveT) this.zzb.get().zza(zzcgVar, cls);
    }

    zznr() {
    }

    public final synchronized <KeyT extends zzbt, PrimitiveT> void zza(zzod<KeyT, PrimitiveT> zzodVar) throws GeneralSecurityException {
        this.zzb.set(zzok.zza(this.zzb.get()).zza(zzodVar).zza());
    }

    public final synchronized <InputPrimitiveT, WrapperPrimitiveT> void zza(zzcp<InputPrimitiveT, WrapperPrimitiveT> zzcpVar) throws GeneralSecurityException {
        this.zzb.set(zzok.zza(this.zzb.get()).zza(zzcpVar).zza());
    }
}
