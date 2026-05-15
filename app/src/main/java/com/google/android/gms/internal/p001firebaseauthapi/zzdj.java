package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzdj extends zzne<zzsf, zzse> {
    private final /* synthetic */ zzdi zza;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzakn zzaknVar) throws GeneralSecurityException {
        zzsf zzsfVar = (zzsf) zzaknVar;
        zzsi zzsiVar = (zzsi) new zzdv().zzc().zza(zzsfVar.zzc());
        return (zzse) ((zzajc) zzse.zzb().zza(zzsiVar).zza((zzuc) new zzqg().zzc().zza(zzsfVar.zzd())).zza(zzdi.zza()).zzf());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zzsf.zza(zzahpVar, zzaio.zza());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdj(zzdi zzdiVar, Class cls) {
        super(cls);
        this.zza = zzdiVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ void zzb(zzakn zzaknVar) throws GeneralSecurityException {
        zzsf zzsfVar = (zzsf) zzaknVar;
        new zzdv().zzc().zzb(zzsfVar.zzc());
        new zzqg().zzc().zzb(zzsfVar.zzd());
        zzxo.zza(zzsfVar.zzc().zza());
    }
}
