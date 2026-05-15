package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzch;
import com.google.android.gms.internal.p001firebaseauthapi.zzov;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zznz<ParametersT extends zzch, SerializationT extends zzov> {
    private final Class<ParametersT> zza;
    private final Class<SerializationT> zzb;

    public static <ParametersT extends zzch, SerializationT extends zzov> zznz<ParametersT, SerializationT> zza(zzob<ParametersT, SerializationT> zzobVar, Class<ParametersT> cls, Class<SerializationT> cls2) {
        return new zzoc(cls, cls2, zzobVar);
    }

    public abstract SerializationT zza(ParametersT parameterst) throws GeneralSecurityException;

    public final Class<ParametersT> zza() {
        return this.zza;
    }

    public final Class<SerializationT> zzb() {
        return this.zzb;
    }

    private zznz(Class<ParametersT> cls, Class<SerializationT> cls2) {
        this.zza = cls;
        this.zzb = cls2;
    }
}
