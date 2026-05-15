package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzht {
    private static final zzif.zza zza = zzif.zza.zzb;
    private static final ThreadLocal<Cipher> zzb = new zzhw();
    private final SecretKey zzc;
    private final boolean zzd;

    private static AlgorithmParameterSpec zza(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        Integer numZzb = zzpf.zzb();
        return (numZzb == null || numZzb.intValue() > 19) ? new GCMParameterSpec(128, bArr, 0, length) : new IvParameterSpec(bArr, 0, length);
    }

    public zzht(byte[] bArr, boolean z) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        zzxo.zza(bArr.length);
        this.zzc = new SecretKeySpec(bArr, "AES");
        this.zzd = z;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        }
        if (bArr2.length < (this.zzd ? 28 : 16)) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        if (this.zzd && !ByteBuffer.wrap(bArr).equals(ByteBuffer.wrap(bArr2, 0, 12))) {
            throw new GeneralSecurityException("iv does not match prepended iv");
        }
        zzb.get().init(2, this.zzc, zza(bArr));
        if (bArr3 != null && bArr3.length != 0) {
            zzb.get().updateAAD(bArr3);
        }
        return zzb.get().doFinal(bArr2, this.zzd ? 12 : 0, this.zzd ? bArr2.length - 12 : bArr2.length);
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        int length;
        if (bArr.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        }
        if (bArr2.length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        if (this.zzd) {
            length = bArr2.length + 12 + 16;
        } else {
            length = bArr2.length + 16;
        }
        byte[] bArr4 = new byte[length];
        if (this.zzd) {
            System.arraycopy(bArr, 0, bArr4, 0, 12);
        }
        zzb.get().init(1, this.zzc, zza(bArr));
        if (bArr3 != null && bArr3.length != 0) {
            zzb.get().updateAAD(bArr3);
        }
        int iDoFinal = zzb.get().doFinal(bArr2, 0, bArr2.length, bArr4, this.zzd ? 12 : 0);
        if (iDoFinal != bArr2.length + 16) {
            throw new GeneralSecurityException(String.format("encryption failed; GCM tag must be %s bytes, but got only %s bytes", 16, Integer.valueOf(iDoFinal - bArr2.length)));
        }
        return bArr4;
    }
}
