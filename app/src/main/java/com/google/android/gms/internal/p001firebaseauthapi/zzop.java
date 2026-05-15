package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzakn;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzop<KeyProtoT extends zzakn, PublicKeyProtoT extends zzakn> extends zznb<KeyProtoT> {
    private final Class<PublicKeyProtoT> zza;

    @SafeVarargs
    protected zzop(Class<KeyProtoT> cls, Class<PublicKeyProtoT> cls2, zzoh<?, KeyProtoT>... zzohVarArr) {
        super(cls, zzohVarArr);
        this.zza = cls2;
    }

    public abstract PublicKeyProtoT zzb(KeyProtoT keyprotot) throws GeneralSecurityException;
}
