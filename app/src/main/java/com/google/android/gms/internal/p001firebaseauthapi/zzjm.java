package com.google.android.gms.internal.p001firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzjm extends zzoh<zzbr, zztu> {
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzoh
    public final /* synthetic */ zzbr zza(zzakn zzaknVar) throws GeneralSecurityException {
        zztu zztuVar = (zztu) zzaknVar;
        zztq zztqVarZzb = zztuVar.zzb();
        zztv zztvVarZzf = zztqVarZzb.zzf();
        zzwo zzwoVarZza = zzkx.zza(zztvVarZzf.zzd());
        byte[] bArrZzg = zztuVar.zzf().zzg();
        byte[] bArrZzg2 = zztuVar.zzg().zzg();
        ECParameterSpec eCParameterSpecZza = zzwp.zza(zzwoVarZza);
        ECPoint eCPoint = new ECPoint(new BigInteger(1, bArrZzg), new BigInteger(1, bArrZzg2));
        zzmg.zza(eCPoint, eCParameterSpecZza.getCurve());
        return new zzwk((ECPublicKey) zzwt.zze.zza("EC").generatePublic(new ECPublicKeySpec(eCPoint, eCParameterSpecZza)), zztvVarZzf.zzf().zzg(), zzkx.zza(zztvVarZzf.zze()), zzkx.zza(zztqVarZzb.zza()), new zzkz(zztqVarZzb.zzb().zzd()));
    }

    zzjm(Class cls) {
        super(cls);
    }
}
