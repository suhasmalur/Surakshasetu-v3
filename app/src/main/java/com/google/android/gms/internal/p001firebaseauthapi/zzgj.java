package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzgj extends zzoh<zzbg, zzvo> {
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzoh
    public final /* synthetic */ zzbg zza(zzakn zzaknVar) throws GeneralSecurityException {
        zzvo zzvoVar = (zzvo) zzaknVar;
        String strZze = zzvoVar.zzd().zze();
        return new zzgh(zzvoVar.zzd().zza(), zzcf.zza(strZze).zza(strZze));
    }

    zzgj(Class cls) {
        super(cls);
    }
}
