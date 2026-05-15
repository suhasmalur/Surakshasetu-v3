package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzgi extends zzne<zzvr, zzvo> {
    private final /* synthetic */ zzgg zza;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzakn zzaknVar) throws GeneralSecurityException {
        return (zzvo) ((zzajc) zzvo.zzb().zza((zzvr) zzaknVar).zza(0).zzf());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zzvr.zza(zzahpVar, zzaio.zza());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzgi(zzgg zzggVar, Class cls) {
        super(cls);
        this.zza = zzggVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ void zzb(zzakn zzaknVar) throws GeneralSecurityException {
        zzvr zzvrVar = (zzvr) zzaknVar;
        if (!zzgh.zza(zzvrVar.zza().zzf())) {
            throw new GeneralSecurityException("Unsupported DEK key type: " + zzvrVar.zza().zzf() + ". Only Tink AEAD key types are supported.");
        }
        if (zzvrVar.zze().isEmpty() || !zzvrVar.zzf()) {
            throw new GeneralSecurityException("invalid key format: missing KEK URI or DEK template");
        }
    }
}
