package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzow extends zzmp {
    private static final zzow zza = new zzow();

    public static zzow zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmp
    @Nullable
    public final Class<?> zza(Class<?> cls) {
        return zzct.zza(cls);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmp
    public final <P> P zza(zzuy zzuyVar, Class<P> cls) throws GeneralSecurityException {
        return (P) zzct.zza(zzuyVar, cls);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmp
    public final <P> P zza(zzbt zzbtVar, Class<P> cls) throws GeneralSecurityException {
        return (P) zznr.zza().zza(zzbtVar, cls);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmp
    public final <B, P> P zza(zzcg<B> zzcgVar, Class<P> cls) throws GeneralSecurityException {
        return (P) zzct.zza(zzcgVar, cls);
    }

    private zzow() {
    }
}
