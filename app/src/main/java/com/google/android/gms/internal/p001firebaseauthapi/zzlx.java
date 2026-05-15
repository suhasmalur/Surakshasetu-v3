package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzlx implements zzll {
    private final zzxt zza;
    private final zzxt zzb;

    static zzlx zza(byte[] bArr, byte[] bArr2, zzwo zzwoVar) throws GeneralSecurityException {
        zzwp.zza(zzwp.zza(zzwoVar, zzwr.UNCOMPRESSED, bArr2), zzwp.zza(zzwoVar, bArr));
        return new zzlx(bArr, bArr2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzll
    public final zzxt zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzll
    public final zzxt zzb() {
        return this.zzb;
    }

    private zzlx(byte[] bArr, byte[] bArr2) {
        this.zza = zzxt.zza(bArr);
        this.zzb = zzxt.zza(bArr2);
    }
}
