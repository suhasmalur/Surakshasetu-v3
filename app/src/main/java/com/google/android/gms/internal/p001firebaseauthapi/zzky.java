package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzky implements zzld {
    private final int zza;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzld
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzld
    public final int zzb() {
        return 12;
    }

    zzky(int i) throws InvalidAlgorithmParameterException {
        if (i != 16 && i != 32) {
            throw new InvalidAlgorithmParameterException("Unsupported key length: " + i);
        }
        this.zza = i;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzld
    public final byte[] zzc() throws GeneralSecurityException {
        switch (this.zza) {
            case 16:
                return zzlt.zzi;
            case 32:
                return zzlt.zzj;
            default:
                throw new GeneralSecurityException("Could not determine HPKE AEAD ID");
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzld
    public final byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws GeneralSecurityException {
        if (bArr.length != this.zza) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + bArr.length);
        }
        return new zzht(bArr, false).zza(bArr2, bArr3, bArr4);
    }
}
