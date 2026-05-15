package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzis extends zzne<zztd, zztc> {
    private final /* synthetic */ zzin zza;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzakn zzaknVar) throws GeneralSecurityException {
        return (zztc) ((zzajc) zztc.zzb().zza(zzahp.zza(zzou.zza(((zztd) zzaknVar).zza()))).zza(zzin.zza()).zzf());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zztd.zza(zzahpVar, zzaio.zza());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzis(zzin zzinVar, Class cls) {
        super(cls);
        this.zza = zzinVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ void zzb(zzakn zzaknVar) throws GeneralSecurityException {
        zztd zztdVar = (zztd) zzaknVar;
        if (zztdVar.zza() != 64) {
            throw new InvalidAlgorithmParameterException("invalid key size: " + zztdVar.zza() + ". Valid keys must have 64 bytes.");
        }
    }
}
