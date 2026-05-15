package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzlv implements zzlj {
    private final zzwo zza;
    private final zzla zzb;

    static zzlv zza(zzwo zzwoVar) throws GeneralSecurityException {
        switch (zzwoVar) {
            case NIST_P256:
                return new zzlv(new zzla("HmacSha256"), zzwo.NIST_P256);
            case NIST_P384:
                return new zzlv(new zzla("HmacSha384"), zzwo.NIST_P384);
            case NIST_P521:
                return new zzlv(new zzla("HmacSha512"), zzwo.NIST_P521);
            default:
                throw new GeneralSecurityException("invalid curve type: " + String.valueOf(zzwoVar));
        }
    }

    private zzlv(zzla zzlaVar, zzwo zzwoVar) {
        this.zzb = zzlaVar;
        this.zza = zzwoVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzlj
    public final byte[] zza(byte[] bArr, zzll zzllVar) throws GeneralSecurityException {
        return this.zzb.zza(null, zzwp.zza(zzwp.zza(this.zza, zzllVar.zza().zzb()), zzwp.zza(this.zza, zzwr.UNCOMPRESSED, bArr)), "eae_prk", zzwg.zza(bArr, zzllVar.zzb().zzb()), "shared_secret", zzlt.zza(zza()), this.zzb.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzlj
    public final byte[] zza() throws GeneralSecurityException {
        switch (this.zza) {
            case NIST_P256:
                return zzlt.zzc;
            case NIST_P384:
                return zzlt.zzd;
            case NIST_P521:
                return zzlt.zze;
            default:
                throw new GeneralSecurityException("Could not determine HPKE KEM ID");
        }
    }
}
