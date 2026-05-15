package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzakn;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzmr<KeyFormatProtoT extends zzakn, KeyProtoT extends zzakn> {
    private final zzne<KeyFormatProtoT, KeyProtoT> zza;

    final KeyProtoT zza(zzahp zzahpVar) throws GeneralSecurityException, zzaji {
        zzakn zzaknVarZza = this.zza.zza(zzahpVar);
        this.zza.zzb(zzaknVarZza);
        return (KeyProtoT) this.zza.zza(zzaknVarZza);
    }

    zzmr(zzne<KeyFormatProtoT, KeyProtoT> zzneVar) {
        this.zza = zzneVar;
    }
}
