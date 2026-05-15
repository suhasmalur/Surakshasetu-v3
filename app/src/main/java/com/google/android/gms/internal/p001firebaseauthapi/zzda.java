package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzda implements zzcp<zzbg, zzbg> {
    private static final zzda zza = new zzda();

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcp
    public final Class<zzbg> zza() {
        return zzbg.class;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcp
    public final Class<zzbg> zzb() {
        return zzbg.class;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcp
    public final /* synthetic */ zzbg zza(zzcg<zzbg> zzcgVar) throws GeneralSecurityException {
        return new zzdd(zzcgVar);
    }

    zzda() {
    }

    public static void zzc() throws GeneralSecurityException {
        zzct.zza(zza);
    }
}
