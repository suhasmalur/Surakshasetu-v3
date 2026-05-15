package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzji extends zzop<zztr, zztu> {
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzif.zza zzb() {
        return zzif.zza.zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzne<zztn, zztr> zzc() {
        return new zzjk(this, zztn.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzuy.zza zzd() {
        return zzuy.zza.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzop
    public final /* synthetic */ zzakn zzb(zzakn zzaknVar) throws GeneralSecurityException {
        return ((zztr) zzaknVar).zzd();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zztr.zza(zzahpVar, zzaio.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final String zze() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    zzji() {
        super(zztr.class, zztu.class, new zzjh(zzbo.class));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ void zza(zzakn zzaknVar) throws GeneralSecurityException {
        zztr zztrVar = (zztr) zzaknVar;
        if (zztrVar.zze().zze()) {
            throw new GeneralSecurityException("invalid ECIES private key");
        }
        zzxo.zza(zztrVar.zza(), 0);
        zzkx.zza(zztrVar.zzd().zzb());
    }
}
