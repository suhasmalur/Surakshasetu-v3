package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zznu {
    private static final zznu zza = (zznu) zzpd.zza(new zzpg() { // from class: com.google.android.gms.internal.firebase-auth-api.zznt
        /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.gms.internal.firebase-auth-api.zzmz, com.google.android.gms.internal.firebase-auth-api.zznw] */
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzpg
        public final Object zza() throws GeneralSecurityException {
            zznu zznuVar = new zznu();
            zznuVar.zza(zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zznw
            }, zznd.class, zzos.class));
            return zznuVar;
        }
    });
    private final AtomicReference<zzoy> zzb = new AtomicReference<>(new zzox().zza());

    public final zzbt zza(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        return !this.zzb.get().zzb(zzosVar) ? new zznd(zzosVar, zzcsVar) : this.zzb.get().zza(zzosVar, zzcsVar);
    }

    public final <SerializationT extends zzov> zzch zza(SerializationT serializationt) throws GeneralSecurityException {
        return this.zzb.get().zza(serializationt);
    }

    public static zznu zza() {
        return zza;
    }

    public final <ParametersT extends zzch, SerializationT extends zzov> SerializationT zza(ParametersT parameterst, Class<SerializationT> cls) throws GeneralSecurityException {
        return (SerializationT) this.zzb.get().zza(parameterst, cls);
    }

    public final synchronized <SerializationT extends zzov> void zza(zzmu<SerializationT> zzmuVar) throws GeneralSecurityException {
        this.zzb.set(new zzox(this.zzb.get()).zza(zzmuVar).zza());
    }

    public final synchronized <KeyT extends zzbt, SerializationT extends zzov> void zza(zzmx<KeyT, SerializationT> zzmxVar) throws GeneralSecurityException {
        this.zzb.set(new zzox(this.zzb.get()).zza(zzmxVar).zza());
    }

    public final synchronized <SerializationT extends zzov> void zza(zznv<SerializationT> zznvVar) throws GeneralSecurityException {
        this.zzb.set(new zzox(this.zzb.get()).zza(zznvVar).zza());
    }

    public final synchronized <ParametersT extends zzch, SerializationT extends zzov> void zza(zznz<ParametersT, SerializationT> zznzVar) throws GeneralSecurityException {
        this.zzb.set(new zzox(this.zzb.get()).zza(zznzVar).zza());
    }

    public final <SerializationT extends zzov> boolean zzb(SerializationT serializationt) {
        return this.zzb.get().zzc(serializationt);
    }
}
