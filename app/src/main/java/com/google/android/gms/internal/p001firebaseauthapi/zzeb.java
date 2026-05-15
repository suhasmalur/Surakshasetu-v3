package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzeb extends zzne<zzsq, zzsn> {
    private final /* synthetic */ zzdz zza;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzakn zzaknVar) throws GeneralSecurityException {
        zzsq zzsqVar = (zzsq) zzaknVar;
        return (zzsn) ((zzajc) zzsn.zzb().zza(zzahp.zza(zzou.zza(zzsqVar.zza()))).zza(zzsqVar.zzd()).zza(0).zzf());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zzsq.zza(zzahpVar, zzaio.zza());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzeb(zzdz zzdzVar, Class cls) {
        super(cls);
        this.zza = zzdzVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ void zzb(zzakn zzaknVar) throws GeneralSecurityException {
        zzsq zzsqVar = (zzsq) zzaknVar;
        zzxo.zza(zzsqVar.zza());
        if (zzsqVar.zzd().zza() != 12 && zzsqVar.zzd().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
