package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzakn;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzne<KeyFormatProtoT extends zzakn, KeyProtoT extends zzakn> {
    private final Class<KeyFormatProtoT> zza;

    public zzne(Class<KeyFormatProtoT> cls) {
        this.zza = cls;
    }

    public abstract KeyFormatProtoT zza(zzahp zzahpVar) throws zzaji;

    public abstract KeyProtoT zza(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;

    public abstract void zzb(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;
}
