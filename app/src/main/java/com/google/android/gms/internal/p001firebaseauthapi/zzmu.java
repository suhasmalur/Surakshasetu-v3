package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzov;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzmu<SerializationT extends zzov> {
    private final zzxt zza;
    private final Class<SerializationT> zzb;

    public static <SerializationT extends zzov> zzmu<SerializationT> zza(zzmv<SerializationT> zzmvVar, zzxt zzxtVar, Class<SerializationT> cls) {
        return new zzmw(zzxtVar, cls, zzmvVar);
    }

    public abstract zzbt zza(SerializationT serializationt, @Nullable zzcs zzcsVar) throws GeneralSecurityException;

    public final zzxt zza() {
        return this.zza;
    }

    public final Class<SerializationT> zzb() {
        return this.zzb;
    }

    private zzmu(zzxt zzxtVar, Class<SerializationT> cls) {
        this.zza = zzxtVar;
        this.zzb = cls;
    }
}
