package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzqj extends zzne<zzug, zzuc> {
    private final /* synthetic */ zzqg zza;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzakn zzaknVar) throws GeneralSecurityException {
        zzug zzugVar = (zzug) zzaknVar;
        return (zzuc) ((zzajc) zzuc.zzb().zza(zzqg.zza()).zza(zzugVar.zzf()).zza(zzahp.zza(zzou.zza(zzugVar.zza()))).zzf());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zzug.zza(zzahpVar, zzaio.zza());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzqj(zzqg zzqgVar, Class cls) {
        super(cls);
        this.zza = zzqgVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ void zzb(zzakn zzaknVar) throws GeneralSecurityException {
        zzug zzugVar = (zzug) zzaknVar;
        if (zzugVar.zza() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zzqg.zzb(zzugVar.zzf());
    }
}
