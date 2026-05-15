package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkp implements zzcp<zzbo, zzbo> {
    private static final zzkp zza = new zzkp();

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcp
    public final Class<zzbo> zza() {
        return zzbo.class;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcp
    public final Class<zzbo> zzb() {
        return zzbo.class;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcp
    public final /* synthetic */ zzbo zza(zzcg<zzbo> zzcgVar) throws GeneralSecurityException {
        return new zzko(zzcgVar);
    }

    zzkp() {
    }

    public static void zzc() throws GeneralSecurityException {
        zzct.zza(zza);
    }
}
