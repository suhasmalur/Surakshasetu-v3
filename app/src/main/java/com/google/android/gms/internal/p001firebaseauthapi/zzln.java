package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzln extends zzop<zzuu, zzuv> {
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzif.zza zzb() {
        return zzif.zza.zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzne<zzuq, zzuu> zzc() {
        return new zzlp(this, zzuq.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzuy.zza zzd() {
        return zzuy.zza.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzop
    public final /* synthetic */ zzakn zzb(zzakn zzaknVar) throws GeneralSecurityException {
        return ((zzuu) zzaknVar).zzd();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zzuu.zza(zzahpVar, zzaio.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final String zze() {
        return "type.googleapis.com/google.crypto.tink.HpkePrivateKey";
    }

    public zzln() {
        super(zzuu.class, zzuv.class, new zzlm(zzbo.class));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ void zza(zzakn zzaknVar) throws GeneralSecurityException {
        zzuu zzuuVar = (zzuu) zzaknVar;
        if (zzuuVar.zze().zze()) {
            throw new GeneralSecurityException("Private key is empty.");
        }
        if (!zzuuVar.zzf()) {
            throw new GeneralSecurityException("Missing public key.");
        }
        zzxo.zza(zzuuVar.zza(), 0);
        zzlt.zza(zzuuVar.zzd().zzb());
    }
}
