package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzqk extends zzoh<zzce, zzuc> {
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzoh
    public final /* synthetic */ zzce zza(zzakn zzaknVar) throws GeneralSecurityException {
        zzuc zzucVar = (zzuc) zzaknVar;
        zzub zzubVarZzb = zzucVar.zze().zzb();
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzucVar.zzf().zzg(), "HMAC");
        int iZza = zzucVar.zze().zza();
        switch (zzubVarZzb) {
            case SHA1:
                return new zzxm(new zzxk("HMACSHA1", secretKeySpec), iZza);
            case SHA224:
                return new zzxm(new zzxk("HMACSHA224", secretKeySpec), iZza);
            case SHA256:
                return new zzxm(new zzxk("HMACSHA256", secretKeySpec), iZza);
            case SHA384:
                return new zzxm(new zzxk("HMACSHA384", secretKeySpec), iZza);
            case SHA512:
                return new zzxm(new zzxk("HMACSHA512", secretKeySpec), iZza);
            default:
                throw new GeneralSecurityException("unknown hash");
        }
    }

    zzqk(Class cls) {
        super(cls);
    }
}
