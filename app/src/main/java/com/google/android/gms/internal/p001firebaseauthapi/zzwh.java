package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzwh implements zzbp {
    private static final zzif.zza zza = zzif.zza.zza;
    private static final Collection<Integer> zzb = Arrays.asList(64);
    private static final byte[] zzc = new byte[16];
    private static final byte[] zzd = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
    private final zzxl zze;
    private final byte[] zzf;
    private final byte[] zzg;

    public zzwh(byte[] bArr) throws GeneralSecurityException {
        this(bArr, zzxt.zza(new byte[0]));
    }

    private zzwh(byte[] bArr, zzxt zzxtVar) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use AES-SIV in FIPS-mode.");
        }
        if (!zzb.contains(Integer.valueOf(bArr.length))) {
            throw new InvalidKeyException("invalid key size: " + bArr.length + " bytes; key must have 64 bytes");
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length / 2);
        this.zzf = Arrays.copyOfRange(bArr, bArr.length / 2, bArr.length);
        this.zze = new zzxl(bArrCopyOfRange);
        this.zzg = zzxtVar.zzb();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbp
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArrZza;
        byte[] bArr3;
        if (bArr.length < this.zzg.length + 16) {
            throw new GeneralSecurityException("Ciphertext too short.");
        }
        if (!zzpf.zza(this.zzg, bArr)) {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        Cipher cipherZza = zzwt.zza.zza("AES/CTR/NoPadding");
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, this.zzg.length, this.zzg.length + 16);
        byte[] bArr4 = (byte[]) bArrCopyOfRange.clone();
        bArr4[8] = (byte) (bArr4[8] & 127);
        bArr4[12] = (byte) (bArr4[12] & 127);
        cipherZza.init(2, new SecretKeySpec(this.zzf, "AES"), new IvParameterSpec(bArr4));
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, this.zzg.length + 16, bArr.length);
        byte[] bArrDoFinal = cipherZza.doFinal(bArrCopyOfRange2);
        if (bArrCopyOfRange2.length == 0 && bArrDoFinal == null && zzxp.zza()) {
            bArrDoFinal = new byte[0];
        }
        byte[][] bArr5 = {bArr2, bArrDoFinal};
        byte[] bArrZza2 = this.zze.zza(zzc, 16);
        for (int i = 0; i <= 0; i++) {
            if (bArr5[i] == null) {
                bArr3 = new byte[0];
            } else {
                bArr3 = bArr5[i];
            }
            bArrZza2 = zzwg.zza(zzrd.zzb(bArrZza2), this.zze.zza(bArr3, 16));
        }
        byte[] bArr6 = bArr5[1];
        if (bArr6.length >= 16) {
            if (bArr6.length < bArrZza2.length) {
                throw new IllegalArgumentException("xorEnd requires a.length >= b.length");
            }
            int length = bArr6.length - bArrZza2.length;
            bArrZza = Arrays.copyOf(bArr6, bArr6.length);
            for (int i2 = 0; i2 < bArrZza2.length; i2++) {
                int i3 = length + i2;
                bArrZza[i3] = (byte) (bArrZza[i3] ^ bArrZza2[i2]);
            }
        } else {
            bArrZza = zzwg.zza(zzrd.zza(bArr6), zzrd.zzb(bArrZza2));
        }
        if (MessageDigest.isEqual(bArrCopyOfRange, this.zze.zza(bArrZza, 16))) {
            return bArrDoFinal;
        }
        throw new AEADBadTagException("Integrity check failed.");
    }
}
