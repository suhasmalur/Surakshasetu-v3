package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzwd implements zzbg {
    private static final zzif.zza zza = zzif.zza.zza;
    private static final ThreadLocal<Cipher> zzb = new zzwc();
    private static final ThreadLocal<Cipher> zzc = new zzwf();
    private final byte[] zzd;
    private final byte[] zze;
    private final byte[] zzf;
    private final SecretKeySpec zzg;
    private final int zzh;

    public zzwd(byte[] bArr, int i) throws GeneralSecurityException {
        this(bArr, i, new byte[0]);
    }

    private zzwd(byte[] bArr, int i, byte[] bArr2) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        }
        if (i != 12 && i != 16) {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
        this.zzh = i;
        zzxo.zza(bArr.length);
        this.zzg = new SecretKeySpec(bArr, "AES");
        Cipher cipher = zzb.get();
        cipher.init(1, this.zzg);
        this.zzd = zza(cipher.doFinal(new byte[16]));
        this.zze = zza(this.zzd);
        this.zzf = bArr2;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbg
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (this.zzf.length == 0) {
            return zzc(bArr, bArr2);
        }
        if (!zzpf.zza(this.zzf, bArr)) {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        return zzc(Arrays.copyOfRange(bArr, this.zzf.length, bArr.length), bArr2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbg
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3;
        if (bArr.length > (Integer.MAX_VALUE - this.zzh) - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] bArr4 = new byte[this.zzh + bArr.length + 16];
        byte[] bArrZza = zzou.zza(this.zzh);
        System.arraycopy(bArrZza, 0, bArr4, 0, this.zzh);
        Cipher cipher = zzb.get();
        cipher.init(1, this.zzg);
        byte[] bArrZza2 = zza(cipher, 0, bArrZza, 0, bArrZza.length);
        if (bArr2 != null) {
            bArr3 = bArr2;
        } else {
            bArr3 = new byte[0];
        }
        byte[] bArrZza3 = zza(cipher, 1, bArr3, 0, bArr3.length);
        Cipher cipher2 = zzc.get();
        cipher2.init(1, this.zzg, new IvParameterSpec(bArrZza2));
        cipher2.doFinal(bArr, 0, bArr.length, bArr4, this.zzh);
        byte[] bArrZza4 = zza(cipher, 2, bArr4, this.zzh, bArr.length);
        int length = bArr.length + this.zzh;
        for (int i = 0; i < 16; i++) {
            bArr4[length + i] = (byte) ((bArrZza3[i] ^ bArrZza2[i]) ^ bArrZza4[i]);
        }
        if (this.zzf.length == 0) {
            return bArr4;
        }
        return zzwg.zza(this.zzf, bArr4);
    }

    private static byte[] zza(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i = 0;
        while (i < 15) {
            int i2 = i + 1;
            bArr2[i] = (byte) ((bArr[i] << 1) ^ ((bArr[i2] & 255) >>> 7));
            i = i2;
        }
        bArr2[15] = (byte) (((bArr[0] >> 7) & 135) ^ (bArr[15] << 1));
        return bArr2;
    }

    private final byte[] zza(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws BadPaddingException, IllegalBlockSizeException {
        byte[] bArrZzd;
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) i;
        if (i3 == 0) {
            return cipher.doFinal(zzd(bArr2, this.zzd));
        }
        byte[] bArrDoFinal = cipher.doFinal(bArr2);
        int i4 = 0;
        while (i3 - i4 > 16) {
            for (int i5 = 0; i5 < 16; i5++) {
                bArrDoFinal[i5] = (byte) (bArrDoFinal[i5] ^ bArr[(i2 + i4) + i5]);
            }
            bArrDoFinal = cipher.doFinal(bArrDoFinal);
            i4 += 16;
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i4 + i2, i2 + i3);
        if (bArrCopyOfRange.length != 16) {
            byte[] bArrCopyOf = Arrays.copyOf(this.zze, 16);
            for (int i6 = 0; i6 < bArrCopyOfRange.length; i6++) {
                bArrCopyOf[i6] = (byte) (bArrCopyOf[i6] ^ bArrCopyOfRange[i6]);
            }
            bArrCopyOf[bArrCopyOfRange.length] = (byte) (bArrCopyOf[bArrCopyOfRange.length] ^ 128);
            bArrZzd = bArrCopyOf;
        } else {
            bArrZzd = zzd(bArrCopyOfRange, this.zzd);
        }
        return cipher.doFinal(zzd(bArrDoFinal, bArrZzd));
    }

    private final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3;
        int length = (bArr.length - this.zzh) - 16;
        if (length < 0) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        Cipher cipher = zzb.get();
        cipher.init(1, this.zzg);
        byte[] bArrZza = zza(cipher, 0, bArr, 0, this.zzh);
        if (bArr2 != null) {
            bArr3 = bArr2;
        } else {
            bArr3 = new byte[0];
        }
        byte[] bArrZza2 = zza(cipher, 1, bArr3, 0, bArr3.length);
        byte[] bArrZza3 = zza(cipher, 2, bArr, this.zzh, length);
        int length2 = bArr.length - 16;
        byte b = 0;
        for (int i = 0; i < 16; i++) {
            b = (byte) (b | (((bArr[length2 + i] ^ bArrZza2[i]) ^ bArrZza[i]) ^ bArrZza3[i]));
        }
        if (b != 0) {
            throw new AEADBadTagException("tag mismatch");
        }
        Cipher cipher2 = zzc.get();
        cipher2.init(1, this.zzg, new IvParameterSpec(bArrZza));
        return cipher2.doFinal(bArr, this.zzh, length);
    }

    private static byte[] zzd(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }
}
