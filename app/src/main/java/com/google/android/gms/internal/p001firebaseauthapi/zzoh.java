package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzakn;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzoh<PrimitiveT, KeyProtoT extends zzakn> {
    private final Class<PrimitiveT> zza;

    final Class<PrimitiveT> zza() {
        return this.zza;
    }

    public abstract PrimitiveT zza(KeyProtoT keyprotot) throws GeneralSecurityException;

    public zzoh(Class<PrimitiveT> cls) {
        this.zza = cls;
    }
}
