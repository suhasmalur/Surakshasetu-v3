package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaif extends zzaia {
    private final InputStream zze;
    private final byte[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private zzaie zzm;

    private final byte zzv() throws IOException {
        if (this.zzi == this.zzg) {
            zzg(1);
        }
        byte[] bArr = this.zzf;
        int i = this.zzi;
        this.zzi = i + 1;
        return bArr[i];
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final double zza() throws IOException {
        return Double.longBitsToDouble(zzy());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final float zzb() throws IOException {
        return Float.intBitsToFloat(zzw());
    }

    private static int zza(InputStream inputStream) throws IOException {
        try {
            return inputStream.available();
        } catch (zzaji e) {
            e.zzj();
            throw e;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final int zzc() {
        return this.zzk + this.zzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final int zzb(int i) throws zzaji {
        if (i < 0) {
            throw zzaji.zzf();
        }
        int i2 = i + this.zzk + this.zzi;
        int i3 = this.zzl;
        if (i2 > i3) {
            throw zzaji.zzi();
        }
        this.zzl = i2;
        zzaa();
        return i3;
    }

    private static int zza(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        try {
            return inputStream.read(bArr, i, i2);
        } catch (zzaji e) {
            e.zzj();
            throw e;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final int zzd() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final int zze() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final int zzf() throws IOException {
        return zzx();
    }

    private final int zzw() throws IOException {
        int i = this.zzi;
        if (this.zzg - i < 4) {
            zzg(4);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0068, code lost:
    
        if (r1[r2] >= 0) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006b A[PHI: r2
      0x006b: PHI (r2v7 int) = (r2v6 int), (r2v9 int), (r2v11 int) binds: [B:20:0x004a, B:24:0x0056, B:28:0x0062] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zzx() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzi
            int r1 = r5.zzg
            if (r1 == r0) goto L6f
            byte[] r1 = r5.zzf
            int r2 = r0 + 1
            r0 = r1[r0]
            if (r0 < 0) goto L11
            r5.zzi = r2
            return r0
        L11:
            int r3 = r5.zzg
            int r3 = r3 - r2
            r4 = 9
            if (r3 < r4) goto L6f
            int r3 = r2 + 1
            r2 = r1[r2]
            int r2 = r2 << 7
            r0 = r0 ^ r2
            if (r0 >= 0) goto L24
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L6c
        L24:
            int r2 = r3 + 1
            r3 = r1[r3]
            int r3 = r3 << 14
            r0 = r0 ^ r3
            if (r0 < 0) goto L31
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r3 = r2
            goto L6c
        L31:
            int r3 = r2 + 1
            r2 = r1[r2]
            int r2 = r2 << 21
            r0 = r0 ^ r2
            if (r0 >= 0) goto L3f
            r1 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r1
            goto L6c
        L3f:
            int r2 = r3 + 1
            r3 = r1[r3]
            int r4 = r3 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r3 >= 0) goto L6b
            int r3 = r2 + 1
            r2 = r1[r2]
            if (r2 >= 0) goto L6c
            int r2 = r3 + 1
            r3 = r1[r3]
            if (r3 >= 0) goto L6b
            int r3 = r2 + 1
            r2 = r1[r2]
            if (r2 >= 0) goto L6c
            int r2 = r3 + 1
            r3 = r1[r3]
            if (r3 >= 0) goto L6b
            int r3 = r2 + 1
            r1 = r1[r2]
            if (r1 < 0) goto L6f
            goto L6c
        L6b:
            r3 = r2
        L6c:
            r5.zzi = r3
            return r0
        L6f:
            long r0 = r5.zzm()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p001firebaseauthapi.zzaif.zzx():int");
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final int zzg() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final int zzh() throws IOException {
        return zza(zzx());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final int zzi() throws IOException {
        if (zzt()) {
            this.zzj = 0;
            return 0;
        }
        this.zzj = zzx();
        if ((this.zzj >>> 3) == 0) {
            throw zzaji.zzc();
        }
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final int zzj() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final long zzk() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final long zzl() throws IOException {
        return zzz();
    }

    private final long zzy() throws IOException {
        int i = this.zzi;
        if (this.zzg - i < 8) {
            zzg(8);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    private final long zzz() throws IOException {
        long j;
        int i = this.zzi;
        if (this.zzg != i) {
            byte[] bArr = this.zzf;
            int i2 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.zzi = i2;
                return b;
            }
            if (this.zzg - i2 >= 9) {
                int i3 = i2 + 1;
                int i4 = b ^ (bArr[i2] << 7);
                if (i4 < 0) {
                    j = i4 ^ (-128);
                } else {
                    int i5 = i3 + 1;
                    int i6 = i4 ^ (bArr[i3] << Ascii.SO);
                    if (i6 >= 0) {
                        j = i6 ^ 16256;
                        i3 = i5;
                    } else {
                        i3 = i5 + 1;
                        int i7 = i6 ^ (bArr[i5] << Ascii.NAK);
                        if (i7 < 0) {
                            j = i7 ^ (-2080896);
                        } else {
                            long j2 = i7;
                            int i8 = i3 + 1;
                            long j3 = (((long) bArr[i3]) << 28) ^ j2;
                            if (j3 >= 0) {
                                i3 = i8;
                                j = j3 ^ 266354560;
                            } else {
                                int i9 = i8 + 1;
                                long j4 = j3 ^ (((long) bArr[i8]) << 35);
                                if (j4 < 0) {
                                    j = (-34093383808L) ^ j4;
                                    i3 = i9;
                                } else {
                                    int i10 = i9 + 1;
                                    long j5 = j4 ^ (((long) bArr[i9]) << 42);
                                    if (j5 >= 0) {
                                        i3 = i10;
                                        j = j5 ^ 4363953127296L;
                                    } else {
                                        int i11 = i10 + 1;
                                        long j6 = j5 ^ (((long) bArr[i10]) << 49);
                                        if (j6 < 0) {
                                            j = (-558586000294016L) ^ j6;
                                            i3 = i11;
                                        } else {
                                            int i12 = i11 + 1;
                                            long j7 = (j6 ^ (((long) bArr[i11]) << 56)) ^ 71499008037633920L;
                                            if (j7 >= 0) {
                                                i3 = i12;
                                                j = j7;
                                            } else {
                                                int i13 = i12 + 1;
                                                if (bArr[i12] >= 0) {
                                                    j = j7;
                                                    i3 = i13;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.zzi = i3;
                return j;
            }
        }
        return zzm();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    final long zzm() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte bZzv = zzv();
            j |= ((long) (bZzv & 127)) << i;
            if ((bZzv & 128) == 0) {
                return j;
            }
        }
        throw zzaji.zze();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final long zzn() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final long zzo() throws IOException {
        return zza(zzz());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final long zzp() throws IOException {
        return zzz();
    }

    private static long zza(InputStream inputStream, long j) throws IOException {
        try {
            return inputStream.skip(j);
        } catch (zzaji e) {
            e.zzj();
            throw e;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final zzahp zzq() throws IOException {
        int iZzx = zzx();
        if (iZzx <= this.zzg - this.zzi && iZzx > 0) {
            zzahp zzahpVarZza = zzahp.zza(this.zzf, this.zzi, iZzx);
            this.zzi += iZzx;
            return zzahpVarZza;
        }
        if (iZzx == 0) {
            return zzahp.zza;
        }
        byte[] bArrZzj = zzj(iZzx);
        if (bArrZzj != null) {
            return zzahp.zza(bArrZzj);
        }
        int i = this.zzi;
        int length = this.zzg - this.zzi;
        this.zzk += this.zzg;
        this.zzi = 0;
        this.zzg = 0;
        List<byte[]> listZzf = zzf(iZzx - length);
        byte[] bArr = new byte[iZzx];
        System.arraycopy(this.zzf, i, bArr, 0, length);
        for (byte[] bArr2 : listZzf) {
            System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
            length += bArr2.length;
        }
        return zzahp.zzb(bArr);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final String zzr() throws IOException {
        int iZzx = zzx();
        if (iZzx > 0 && iZzx <= this.zzg - this.zzi) {
            String str = new String(this.zzf, this.zzi, iZzx, zzajf.zza);
            this.zzi += iZzx;
            return str;
        }
        if (iZzx == 0) {
            return "";
        }
        if (iZzx <= this.zzg) {
            zzg(iZzx);
            String str2 = new String(this.zzf, this.zzi, iZzx, zzajf.zza);
            this.zzi += iZzx;
            return str2;
        }
        return new String(zza(iZzx, false), zzajf.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final String zzs() throws IOException {
        byte[] bArrZza;
        int iZzx = zzx();
        int i = this.zzi;
        if (iZzx <= this.zzg - i && iZzx > 0) {
            bArrZza = this.zzf;
            this.zzi = i + iZzx;
        } else {
            if (iZzx == 0) {
                return "";
            }
            if (iZzx <= this.zzg) {
                zzg(iZzx);
                byte[] bArr = this.zzf;
                this.zzi = iZzx;
                bArrZza = bArr;
                i = 0;
            } else {
                bArrZza = zza(iZzx, false);
                i = 0;
            }
        }
        return zzaml.zzb(bArrZza, i, iZzx);
    }

    private final List<byte[]> zzf(int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            int iMin = Math.min(i, 4096);
            byte[] bArr = new byte[iMin];
            int i2 = 0;
            while (i2 < iMin) {
                int i3 = this.zze.read(bArr, i2, iMin - i2);
                if (i3 == -1) {
                    throw zzaji.zzi();
                }
                this.zzk += i3;
                i2 += i3;
            }
            i -= iMin;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private zzaif(InputStream inputStream, int i) {
        super();
        this.zzl = Integer.MAX_VALUE;
        this.zzm = null;
        zzajf.zza(inputStream, "input");
        this.zze = inputStream;
        this.zzf = new byte[4096];
        this.zzg = 0;
        this.zzi = 0;
        this.zzk = 0;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final void zzc(int i) throws zzaji {
        if (this.zzj != i) {
            throw zzaji.zzb();
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final void zzd(int i) {
        this.zzl = i;
        zzaa();
    }

    private final void zzaa() {
        this.zzg += this.zzh;
        int i = this.zzk + this.zzg;
        if (i > this.zzl) {
            this.zzh = i - this.zzl;
            this.zzg -= this.zzh;
        } else {
            this.zzh = 0;
        }
    }

    private final void zzg(int i) throws IOException {
        if (!zzi(i)) {
            if (i > (this.zzc - this.zzk) - this.zzi) {
                throw zzaji.zzh();
            }
            throw zzaji.zzi();
        }
    }

    private final void zzh(int i) throws IOException {
        if (i <= this.zzg - this.zzi && i >= 0) {
            this.zzi += i;
            return;
        }
        if (i < 0) {
            throw zzaji.zzf();
        }
        if (this.zzk + this.zzi + i > this.zzl) {
            zzh((this.zzl - this.zzk) - this.zzi);
            throw zzaji.zzi();
        }
        this.zzk += this.zzi;
        int i2 = this.zzg - this.zzi;
        this.zzg = 0;
        this.zzi = 0;
        while (i2 < i) {
            try {
                long j = i - i2;
                long jZza = zza(this.zze, j);
                if (jZza < 0 || jZza > j) {
                    throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#skip returned invalid result: " + jZza + "\nThe InputStream implementation is buggy.");
                }
                if (jZza == 0) {
                    break;
                } else {
                    i2 += (int) jZza;
                }
            } finally {
                this.zzk += i2;
                zzaa();
            }
        }
        if (i2 < i) {
            int i3 = this.zzg - this.zzi;
            this.zzi = this.zzg;
            zzg(1);
            while (true) {
                int i4 = i - i3;
                if (i4 > this.zzg) {
                    i3 += this.zzg;
                    this.zzi = this.zzg;
                    zzg(1);
                } else {
                    this.zzi = i4;
                    return;
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final boolean zzt() throws IOException {
        return this.zzi == this.zzg && !zzi(1);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final boolean zzu() throws IOException {
        return zzz() != 0;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaia
    public final boolean zze(int i) throws IOException {
        int iZzi;
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.zzg - this.zzi >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.zzf;
                        int i3 = this.zzi;
                        this.zzi = i3 + 1;
                        if (bArr[i3] < 0) {
                            i2++;
                        }
                    }
                    throw zzaji.zze();
                }
                while (i2 < 10) {
                    if (zzv() < 0) {
                        i2++;
                    }
                }
                throw zzaji.zze();
                return true;
            case 1:
                zzh(8);
                return true;
            case 2:
                zzh(zzx());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzh(4);
                return true;
            default:
                throw zzaji.zza();
        }
        do {
            iZzi = zzi();
            if (iZzi != 0) {
            }
            zzc(((i >>> 3) << 3) | 4);
            return true;
        } while (zze(iZzi));
        zzc(((i >>> 3) << 3) | 4);
        return true;
    }

    private final boolean zzi(int i) throws IOException {
        while (this.zzi + i > this.zzg) {
            if (i > (this.zzc - this.zzk) - this.zzi || this.zzk + this.zzi + i > this.zzl) {
                return false;
            }
            int i2 = this.zzi;
            if (i2 > 0) {
                if (this.zzg > i2) {
                    System.arraycopy(this.zzf, i2, this.zzf, 0, this.zzg - i2);
                }
                this.zzk += i2;
                this.zzg -= i2;
                this.zzi = 0;
            }
            int iZza = zza(this.zze, this.zzf, this.zzg, Math.min(this.zzf.length - this.zzg, (this.zzc - this.zzk) - this.zzg));
            if (iZza == 0 || iZza < -1 || iZza > this.zzf.length) {
                throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#read(byte[]) returned invalid result: " + iZza + "\nThe InputStream implementation is buggy.");
            }
            if (iZza <= 0) {
                return false;
            }
            this.zzg += iZza;
            zzaa();
            if (this.zzg >= i) {
                return true;
            }
        }
        throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
    }

    private final byte[] zza(int i, boolean z) throws IOException {
        byte[] bArrZzj = zzj(i);
        if (bArrZzj != null) {
            return bArrZzj;
        }
        int i2 = this.zzi;
        int length = this.zzg - this.zzi;
        this.zzk += this.zzg;
        this.zzi = 0;
        this.zzg = 0;
        List<byte[]> listZzf = zzf(i - length);
        byte[] bArr = new byte[i];
        System.arraycopy(this.zzf, i2, bArr, 0, length);
        for (byte[] bArr2 : listZzf) {
            System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
            length += bArr2.length;
        }
        return bArr;
    }

    private final byte[] zzj(int i) throws IOException {
        if (i == 0) {
            return zzajf.zzb;
        }
        if (i < 0) {
            throw zzaji.zzf();
        }
        int i2 = this.zzk + this.zzi + i;
        if (i2 - this.zzc > 0) {
            throw zzaji.zzh();
        }
        if (i2 > this.zzl) {
            zzh((this.zzl - this.zzk) - this.zzi);
            throw zzaji.zzi();
        }
        int i3 = this.zzg - this.zzi;
        int i4 = i - i3;
        if (i4 < 4096 || i4 <= zza(this.zze)) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.zzf, this.zzi, bArr, 0, i3);
            this.zzk += this.zzg;
            this.zzi = 0;
            this.zzg = 0;
            while (i3 < i) {
                int iZza = zza(this.zze, bArr, i3, i - i3);
                if (iZza == -1) {
                    throw zzaji.zzi();
                }
                this.zzk += iZza;
                i3 += iZza;
            }
            return bArr;
        }
        return null;
    }
}
