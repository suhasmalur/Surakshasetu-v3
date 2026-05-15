package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzie implements zzbg {
    private static final ThreadLocal<Cipher> zza = new zzid();
    private static final boolean zzb = zza();
    private final SecretKey zzc;
    private final byte[] zzd;

    private static AlgorithmParameterSpec zza(byte[] bArr, int i, int i2) throws GeneralSecurityException {
        if (zzb) {
            return new GCMParameterSpec(128, bArr, 0, i2);
        }
        if (zzxp.zza()) {
            return new IvParameterSpec(bArr, 0, i2);
        }
        throw new GeneralSecurityException("cannot use AES-GCM: javax.crypto.spec.GCMParameterSpec not found");
    }

    public zzie(byte[] bArr) throws GeneralSecurityException {
        this(bArr, new byte[0]);
    }

    private zzie(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        this.zzd = bArr2;
        zzxo.zza(bArr.length);
        this.zzc = new SecretKeySpec(bArr, "AES");
    }

    private static boolean zza() {
        try {
            Class.forName("javax.crypto.spec.GCMParameterSpec");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbg
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (this.zzd.length == 0) {
            return zzc(bArr, bArr2);
        }
        if (!zzpf.zza(this.zzd, bArr)) {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        return zzc(Arrays.copyOfRange(bArr, this.zzd.length, bArr.length), bArr2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbg
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] bArr3 = new byte[bArr.length + 12 + 16];
        byte[] bArrZza = zzou.zza(12);
        System.arraycopy(bArrZza, 0, bArr3, 0, 12);
        zza.get().init(1, this.zzc, zza(bArrZza, 0, bArrZza.length));
        if (bArr2 != null && bArr2.length != 0) {
            zza.get().updateAAD(bArr2);
        }
        int iDoFinal = zza.get().doFinal(bArr, 0, bArr.length, bArr3, 12);
        if (iDoFinal != bArr.length + 16) {
            throw new GeneralSecurityException(String.format("encryption failed; GCM tag must be %s bytes, but got only %s bytes", 16, Integer.valueOf(iDoFinal - bArr.length)));
        }
        if (this.zzd.length == 0) {
            return bArr3;
        }
        return zzwg.zza(this.zzd, bArr3);
    }

    private final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length < 28) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        zza.get().init(2, this.zzc, zza(bArr, 0, 12));
        if (bArr2 != null && bArr2.length != 0) {
            zza.get().updateAAD(bArr2);
        }
        return zza.get().doFinal(bArr, 12, bArr.length - 12);
    }
}
