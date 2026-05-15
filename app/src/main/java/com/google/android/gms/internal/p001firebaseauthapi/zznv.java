package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzov;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zznv<SerializationT extends zzov> {
    private final zzxt zza;
    private final Class<SerializationT> zzb;

    public static <SerializationT extends zzov> zznv<SerializationT> zza(zznx<SerializationT> zznxVar, zzxt zzxtVar, Class<SerializationT> cls) {
        return new zzny(zzxtVar, cls, zznxVar);
    }

    public abstract zzch zza(SerializationT serializationt) throws GeneralSecurityException;

    public final zzxt zza() {
        return this.zza;
    }

    public final Class<SerializationT> zzb() {
        return this.zzb;
    }

    private zznv(zzxt zzxtVar, Class<SerializationT> cls) {
        this.zza = zzxtVar;
        this.zzb = cls;
    }
}
