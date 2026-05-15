package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzaik extends zzahm {
    private static final Logger zza = Logger.getLogger(zzaik.class.getName());
    private static final boolean zzb = zzamk.zzc();
    zzain zze;

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    private static abstract class zza extends zzaik {
        final byte[] zza;
        final int zzb;
        int zzc;
        int zzd;

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final int zza() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }

        zza(int i) {
            super();
            if (i < 0) {
                throw new IllegalArgumentException("bufferSize must be >= 0");
            }
            this.zza = new byte[Math.max(i, 20)];
            this.zzb = this.zza.length;
        }

        final void zza(byte b) {
            byte[] bArr = this.zza;
            int i = this.zzc;
            this.zzc = i + 1;
            bArr[i] = b;
            this.zzd++;
        }

        final void zza(int i) {
            byte[] bArr = this.zza;
            int i2 = this.zzc;
            this.zzc = i2 + 1;
            bArr[i2] = (byte) i;
            byte[] bArr2 = this.zza;
            int i3 = this.zzc;
            this.zzc = i3 + 1;
            bArr2[i3] = (byte) (i >> 8);
            byte[] bArr3 = this.zza;
            int i4 = this.zzc;
            this.zzc = i4 + 1;
            bArr3[i4] = (byte) (i >> 16);
            byte[] bArr4 = this.zza;
            int i5 = this.zzc;
            this.zzc = i5 + 1;
            bArr4[i5] = (byte) (i >>> 24);
            this.zzd += 4;
        }

        final void zza(long j) {
            byte[] bArr = this.zza;
            int i = this.zzc;
            this.zzc = i + 1;
            bArr[i] = (byte) (j & 255);
            byte[] bArr2 = this.zza;
            int i2 = this.zzc;
            this.zzc = i2 + 1;
            bArr2[i2] = (byte) ((j >> 8) & 255);
            byte[] bArr3 = this.zza;
            int i3 = this.zzc;
            this.zzc = i3 + 1;
            bArr3[i3] = (byte) ((j >> 16) & 255);
            byte[] bArr4 = this.zza;
            int i4 = this.zzc;
            this.zzc = i4 + 1;
            bArr4[i4] = (byte) (255 & (j >> 24));
            byte[] bArr5 = this.zza;
            int i5 = this.zzc;
            this.zzc = i5 + 1;
            bArr5[i5] = (byte) (j >> 32);
            byte[] bArr6 = this.zza;
            int i6 = this.zzc;
            this.zzc = i6 + 1;
            bArr6[i6] = (byte) (j >> 40);
            byte[] bArr7 = this.zza;
            int i7 = this.zzc;
            this.zzc = i7 + 1;
            bArr7[i7] = (byte) (j >> 48);
            byte[] bArr8 = this.zza;
            int i8 = this.zzc;
            this.zzc = i8 + 1;
            bArr8[i8] = (byte) (j >> 56);
            this.zzd += 8;
        }

        final void zza(int i, int i2) {
            zzb((i << 3) | i2);
        }

        final void zzb(int i) {
            if (zzaik.zzb) {
                long j = this.zzc;
                while ((i & (-128)) != 0) {
                    byte[] bArr = this.zza;
                    int i2 = this.zzc;
                    this.zzc = i2 + 1;
                    zzamk.zza(bArr, i2, (byte) ((i & WorkQueueKt.MASK) | 128));
                    i >>>= 7;
                }
                byte[] bArr2 = this.zza;
                int i3 = this.zzc;
                this.zzc = i3 + 1;
                zzamk.zza(bArr2, i3, (byte) i);
                this.zzd += (int) (((long) this.zzc) - j);
                return;
            }
            while ((i & (-128)) != 0) {
                byte[] bArr3 = this.zza;
                int i4 = this.zzc;
                this.zzc = i4 + 1;
                bArr3[i4] = (byte) ((i & WorkQueueKt.MASK) | 128);
                this.zzd++;
                i >>>= 7;
            }
            byte[] bArr4 = this.zza;
            int i5 = this.zzc;
            this.zzc = i5 + 1;
            bArr4[i5] = (byte) i;
            this.zzd++;
        }

        final void zzb(long j) {
            if (zzaik.zzb) {
                long j2 = this.zzc;
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.zza;
                    int i = this.zzc;
                    this.zzc = i + 1;
                    zzamk.zza(bArr, i, (byte) ((((int) j) & WorkQueueKt.MASK) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.zza;
                int i2 = this.zzc;
                this.zzc = i2 + 1;
                zzamk.zza(bArr2, i2, (byte) j);
                this.zzd += (int) (((long) this.zzc) - j2);
                return;
            }
            while ((j & (-128)) != 0) {
                byte[] bArr3 = this.zza;
                int i3 = this.zzc;
                this.zzc = i3 + 1;
                bArr3[i3] = (byte) ((((int) j) & WorkQueueKt.MASK) | 128);
                this.zzd++;
                j >>>= 7;
            }
            byte[] bArr4 = this.zza;
            int i4 = this.zzc;
            this.zzc = i4 + 1;
            bArr4[i4] = (byte) j;
            this.zzd++;
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    private static class zzc extends zzaik {
        private final byte[] zza;
        private final int zzb;
        private final int zzc;
        private int zzd;

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final int zza() {
            return this.zzc - this.zzd;
        }

        zzc(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            if ((i2 | 0 | (bArr.length - i2)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)));
            }
            this.zza = bArr;
            this.zzb = 0;
            this.zzd = 0;
            this.zzc = i2;
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzc() {
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(byte b) throws IOException {
            try {
                byte[] bArr = this.zza;
                int i = this.zzd;
                this.zzd = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
            }
        }

        private final void zzc(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.zza, this.zzd, i2);
                this.zzd += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(i2)), e);
            }
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(int i, boolean z) throws IOException {
            zzk(i, 0);
            zzb(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzn(i2);
            zzc(bArr, 0, i2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzc(int i, zzahp zzahpVar) throws IOException {
            zzk(i, 2);
            zzb(zzahpVar);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(zzahp zzahpVar) throws IOException {
            zzn(zzahpVar.zzb());
            zzahpVar.zza(this);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzh(int i, int i2) throws IOException {
            zzk(i, 5);
            zzk(i2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzk(int i) throws IOException {
            try {
                byte[] bArr = this.zza;
                int i2 = this.zzd;
                this.zzd = i2 + 1;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.zza;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.zza;
                int i4 = this.zzd;
                this.zzd = i4 + 1;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.zza;
                int i5 = this.zzd;
                this.zzd = i5 + 1;
                bArr4[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzf(int i, long j) throws IOException {
            zzk(i, 1);
            zzh(j);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzh(long j) throws IOException {
            try {
                byte[] bArr = this.zza;
                int i = this.zzd;
                this.zzd = i + 1;
                bArr[i] = (byte) j;
                byte[] bArr2 = this.zza;
                int i2 = this.zzd;
                this.zzd = i2 + 1;
                bArr2[i2] = (byte) (j >> 8);
                byte[] bArr3 = this.zza;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                bArr3[i3] = (byte) (j >> 16);
                byte[] bArr4 = this.zza;
                int i4 = this.zzd;
                this.zzd = i4 + 1;
                bArr4[i4] = (byte) (j >> 24);
                byte[] bArr5 = this.zza;
                int i5 = this.zzd;
                this.zzd = i5 + 1;
                bArr5[i5] = (byte) (j >> 32);
                byte[] bArr6 = this.zza;
                int i6 = this.zzd;
                this.zzd = i6 + 1;
                bArr6[i6] = (byte) (j >> 40);
                byte[] bArr7 = this.zza;
                int i7 = this.zzd;
                this.zzd = i7 + 1;
                bArr7[i7] = (byte) (j >> 48);
                byte[] bArr8 = this.zza;
                int i8 = this.zzd;
                this.zzd = i8 + 1;
                bArr8[i8] = (byte) (j >> 56);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzi(int i, int i2) throws IOException {
            zzk(i, 0);
            zzl(i2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzl(int i) throws IOException {
            if (i >= 0) {
                zzn(i);
            } else {
                zzj(i);
            }
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahm
        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            zzc(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        final void zzc(int i, zzakn zzaknVar, zzalf zzalfVar) throws IOException {
            zzk(i, 2);
            zzn(((zzahf) zzaknVar).zza(zzalfVar));
            zzalfVar.zza(zzaknVar, this.zze);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzc(zzakn zzaknVar) throws IOException {
            zzn(zzaknVar.zzl());
            zzaknVar.zza(this);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        final void zzb(zzakn zzaknVar, zzalf zzalfVar) throws IOException {
            zzn(((zzahf) zzaknVar).zza(zzalfVar));
            zzalfVar.zza(zzaknVar, this.zze);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(int i, zzakn zzaknVar) throws IOException {
            zzk(1, 3);
            zzl(2, i);
            zzk(3, 2);
            zzc(zzaknVar);
            zzk(1, 4);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzd(int i, zzahp zzahpVar) throws IOException {
            zzk(1, 3);
            zzl(2, i);
            zzc(3, zzahpVar);
            zzk(1, 4);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(int i, String str) throws IOException {
            zzk(i, 2);
            zzb(str);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(String str) throws IOException {
            int i = this.zzd;
            try {
                int iZzj = zzj(str.length() * 3);
                int iZzj2 = zzj(str.length());
                if (iZzj2 == iZzj) {
                    this.zzd = i + iZzj2;
                    int iZza = zzaml.zza(str, this.zza, this.zzd, zza());
                    this.zzd = i;
                    zzn((iZza - i) - iZzj2);
                    this.zzd = iZza;
                    return;
                }
                zzn(zzaml.zza(str));
                this.zzd = zzaml.zza(str, this.zza, this.zzd, zza());
            } catch (zzamo e) {
                this.zzd = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzk(int i, int i2) throws IOException {
            zzn((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzl(int i, int i2) throws IOException {
            zzk(i, 0);
            zzn(i2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzn(int i) throws IOException {
            while ((i & (-128)) != 0) {
                try {
                    byte[] bArr = this.zza;
                    int i2 = this.zzd;
                    this.zzd = i2 + 1;
                    bArr[i2] = (byte) ((i & WorkQueueKt.MASK) | 128);
                    i >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
                }
            }
            byte[] bArr2 = this.zza;
            int i3 = this.zzd;
            this.zzd = i3 + 1;
            bArr2[i3] = (byte) i;
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzh(int i, long j) throws IOException {
            zzk(i, 0);
            zzj(j);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzj(long j) throws IOException {
            if (zzaik.zzb && zza() >= 10) {
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.zza;
                    int i = this.zzd;
                    this.zzd = i + 1;
                    zzamk.zza(bArr, i, (byte) ((((int) j) & WorkQueueKt.MASK) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.zza;
                int i2 = this.zzd;
                this.zzd = i2 + 1;
                zzamk.zza(bArr2, i2, (byte) j);
                return;
            }
            while ((j & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.zza;
                    int i3 = this.zzd;
                    this.zzd = i3 + 1;
                    bArr3[i3] = (byte) ((((int) j) & WorkQueueKt.MASK) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
                }
            }
            byte[] bArr4 = this.zza;
            int i4 = this.zzd;
            this.zzd = i4 + 1;
            bArr4[i4] = (byte) j;
        }
    }

    public static int zza(int i, boolean z) {
        return zzj(i << 3) + 1;
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzb(int i, zzakn zzaknVar) throws IOException;

    public abstract void zzb(int i, String str) throws IOException;

    public abstract void zzb(int i, boolean z) throws IOException;

    public abstract void zzb(zzahp zzahpVar) throws IOException;

    abstract void zzb(zzakn zzaknVar, zzalf zzalfVar) throws IOException;

    public abstract void zzb(String str) throws IOException;

    abstract void zzb(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzc() throws IOException;

    public abstract void zzc(int i, zzahp zzahpVar) throws IOException;

    abstract void zzc(int i, zzakn zzaknVar, zzalf zzalfVar) throws IOException;

    public abstract void zzc(zzakn zzaknVar) throws IOException;

    public abstract void zzd(int i, zzahp zzahpVar) throws IOException;

    public abstract void zzf(int i, long j) throws IOException;

    public abstract void zzh(int i, int i2) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzh(long j) throws IOException;

    public abstract void zzi(int i, int i2) throws IOException;

    public abstract void zzj(long j) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzk(int i, int i2) throws IOException;

    public abstract void zzl(int i) throws IOException;

    public abstract void zzl(int i, int i2) throws IOException;

    public abstract void zzn(int i) throws IOException;

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        zzb(String str, Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + str, th);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    private static final class zzd extends zza {
        private final OutputStream zzf;

        zzd(OutputStream outputStream, int i) {
            super(i);
            if (outputStream == null) {
                throw new NullPointerException("out");
            }
            this.zzf = outputStream;
        }

        private final void zze() throws IOException {
            this.zzf.write(this.zza, 0, this.zzc);
            this.zzc = 0;
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzc() throws IOException {
            if (this.zzc > 0) {
                zze();
            }
        }

        private final void zzo(int i) throws IOException {
            if (this.zzb - this.zzc < i) {
                zze();
            }
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(byte b) throws IOException {
            if (this.zzc == this.zzb) {
                zze();
            }
            zza(b);
        }

        private final void zzc(byte[] bArr, int i, int i2) throws IOException {
            if (this.zzb - this.zzc >= i2) {
                System.arraycopy(bArr, i, this.zza, this.zzc, i2);
                this.zzc += i2;
            } else {
                int i3 = this.zzb - this.zzc;
                System.arraycopy(bArr, i, this.zza, this.zzc, i3);
                int i4 = i + i3;
                i2 -= i3;
                this.zzc = this.zzb;
                this.zzd += i3;
                zze();
                if (i2 <= this.zzb) {
                    System.arraycopy(bArr, i4, this.zza, 0, i2);
                    this.zzc = i2;
                } else {
                    this.zzf.write(bArr, i4, i2);
                }
            }
            this.zzd += i2;
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(int i, boolean z) throws IOException {
            zzo(11);
            zza(i, 0);
            zza(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzn(i2);
            zzc(bArr, 0, i2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzc(int i, zzahp zzahpVar) throws IOException {
            zzk(i, 2);
            zzb(zzahpVar);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(zzahp zzahpVar) throws IOException {
            zzn(zzahpVar.zzb());
            zzahpVar.zza(this);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzh(int i, int i2) throws IOException {
            zzo(14);
            zza(i, 5);
            zza(i2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzk(int i) throws IOException {
            zzo(4);
            zza(i);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzf(int i, long j) throws IOException {
            zzo(18);
            zza(i, 1);
            zza(j);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzh(long j) throws IOException {
            zzo(8);
            zza(j);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzi(int i, int i2) throws IOException {
            zzo(20);
            zza(i, 0);
            if (i2 >= 0) {
                zzb(i2);
            } else {
                zzb(i2);
            }
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzl(int i) throws IOException {
            if (i >= 0) {
                zzn(i);
            } else {
                zzj(i);
            }
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahm
        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            zzc(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        final void zzc(int i, zzakn zzaknVar, zzalf zzalfVar) throws IOException {
            zzk(i, 2);
            zzb(zzaknVar, zzalfVar);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzc(zzakn zzaknVar) throws IOException {
            zzn(zzaknVar.zzl());
            zzaknVar.zza(this);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        final void zzb(zzakn zzaknVar, zzalf zzalfVar) throws IOException {
            zzn(((zzahf) zzaknVar).zza(zzalfVar));
            zzalfVar.zza(zzaknVar, this.zze);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(int i, zzakn zzaknVar) throws IOException {
            zzk(1, 3);
            zzl(2, i);
            zzk(3, 2);
            zzc(zzaknVar);
            zzk(1, 4);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzd(int i, zzahp zzahpVar) throws IOException {
            zzk(1, 3);
            zzl(2, i);
            zzc(3, zzahpVar);
            zzk(1, 4);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(int i, String str) throws IOException {
            zzk(i, 2);
            zzb(str);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzb(String str) throws IOException {
            int iZza;
            try {
                int length = str.length() * 3;
                int iZzj = zzj(length);
                int i = iZzj + length;
                if (i > this.zzb) {
                    byte[] bArr = new byte[length];
                    int iZza2 = zzaml.zza(str, bArr, 0, length);
                    zzn(iZza2);
                    zza(bArr, 0, iZza2);
                    return;
                }
                if (i > this.zzb - this.zzc) {
                    zze();
                }
                int iZzj2 = zzj(str.length());
                int i2 = this.zzc;
                try {
                    try {
                        if (iZzj2 == iZzj) {
                            this.zzc = i2 + iZzj2;
                            int iZza3 = zzaml.zza(str, this.zza, this.zzc, this.zzb - this.zzc);
                            this.zzc = i2;
                            iZza = (iZza3 - i2) - iZzj2;
                            zzb(iZza);
                            this.zzc = iZza3;
                        } else {
                            iZza = zzaml.zza(str);
                            zzb(iZza);
                            this.zzc = zzaml.zza(str, this.zza, this.zzc, iZza);
                        }
                        this.zzd += iZza;
                    } catch (zzamo e) {
                        this.zzd -= this.zzc - i2;
                        this.zzc = i2;
                        throw e;
                    }
                } catch (ArrayIndexOutOfBoundsException e2) {
                    throw new zzb(e2);
                }
            } catch (zzamo e3) {
                zza(str, e3);
            }
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzk(int i, int i2) throws IOException {
            zzn((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzl(int i, int i2) throws IOException {
            zzo(20);
            zza(i, 0);
            zzb(i2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzn(int i) throws IOException {
            zzo(5);
            zzb(i);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzh(int i, long j) throws IOException {
            zzo(20);
            zza(i, 0);
            zzb(j);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaik
        public final void zzj(long j) throws IOException {
            zzo(10);
            zzb(j);
        }
    }

    public static int zza(boolean z) {
        return 1;
    }

    public static int zza(byte[] bArr) {
        int length = bArr.length;
        return zzj(length) + length;
    }

    public static int zza(int i, zzahp zzahpVar) {
        int iZzj = zzj(i << 3);
        int iZzb = zzahpVar.zzb();
        return iZzj + zzj(iZzb) + iZzb;
    }

    public static int zza(zzahp zzahpVar) {
        int iZzb = zzahpVar.zzb();
        return zzj(iZzb) + iZzb;
    }

    public static int zza(int i, double d) {
        return zzj(i << 3) + 8;
    }

    public static int zza(double d) {
        return 8;
    }

    public static int zzb(int i, int i2) {
        return zzj(i << 3) + zze(i2);
    }

    public static int zzc(int i) {
        return zze(i);
    }

    public static int zzc(int i, int i2) {
        return zzj(i << 3) + 4;
    }

    public static int zzd(int i) {
        return 4;
    }

    public static int zza(int i, long j) {
        return zzj(i << 3) + 8;
    }

    public static int zzc(long j) {
        return 8;
    }

    public static int zza(int i, float f) {
        return zzj(i << 3) + 4;
    }

    public static int zza(float f) {
        return 4;
    }

    @Deprecated
    static int zza(int i, zzakn zzaknVar, zzalf zzalfVar) {
        return (zzj(i << 3) << 1) + ((zzahf) zzaknVar).zza(zzalfVar);
    }

    @Deprecated
    public static int zza(zzakn zzaknVar) {
        return zzaknVar.zzl();
    }

    public static int zzd(int i, int i2) {
        return zzj(i << 3) + zze(i2);
    }

    public static int zze(int i) {
        if (i >= 0) {
            return zzj(i);
        }
        return 10;
    }

    public static int zzb(int i, long j) {
        return zzj(i << 3) + zzg(j);
    }

    public static int zzd(long j) {
        return zzg(j);
    }

    public static int zza(int i, zzajr zzajrVar) {
        return (zzj(8) << 1) + zzg(2, i) + zzb(3, zzajrVar);
    }

    public static int zzb(int i, zzajr zzajrVar) {
        int iZzj = zzj(i << 3);
        int iZzb = zzajrVar.zzb();
        return iZzj + zzj(iZzb) + iZzb;
    }

    public static int zza(zzajr zzajrVar) {
        int iZzb = zzajrVar.zzb();
        return zzj(iZzb) + iZzb;
    }

    public static int zza(int i, zzakn zzaknVar) {
        return (zzj(8) << 1) + zzg(2, i) + zzj(24) + zzb(zzaknVar);
    }

    static int zzb(int i, zzakn zzaknVar, zzalf zzalfVar) {
        return zzj(i << 3) + zza(zzaknVar, zzalfVar);
    }

    public static int zzb(zzakn zzaknVar) {
        int iZzl = zzaknVar.zzl();
        return zzj(iZzl) + iZzl;
    }

    static int zza(zzakn zzaknVar, zzalf zzalfVar) {
        int iZza = ((zzahf) zzaknVar).zza(zzalfVar);
        return zzj(iZza) + iZza;
    }

    static int zzf(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    public static int zzb(int i, zzahp zzahpVar) {
        return (zzj(8) << 1) + zzg(2, i) + zza(3, zzahpVar);
    }

    public static int zze(int i, int i2) {
        return zzj(i << 3) + 4;
    }

    public static int zzg(int i) {
        return 4;
    }

    public static int zzc(int i, long j) {
        return zzj(i << 3) + 8;
    }

    public static int zze(long j) {
        return 8;
    }

    public static int zzf(int i, int i2) {
        return zzj(i << 3) + zzj(zza(i2));
    }

    public static int zzh(int i) {
        return zzj(zza(i));
    }

    public static int zzd(int i, long j) {
        return zzj(i << 3) + zzg(zza(j));
    }

    public static int zzf(long j) {
        return zzg(zza(j));
    }

    public static int zza(int i, String str) {
        return zzj(i << 3) + zza(str);
    }

    public static int zza(String str) {
        int length;
        try {
            length = zzaml.zza(str);
        } catch (zzamo e) {
            length = str.getBytes(zzajf.zza).length;
        }
        return zzj(length) + length;
    }

    public static int zzi(int i) {
        return zzj(i << 3);
    }

    public static int zzg(int i, int i2) {
        return zzj(i << 3) + zzj(i2);
    }

    public static int zzj(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        if ((i & (-268435456)) == 0) {
            return 4;
        }
        return 5;
    }

    public static int zze(int i, long j) {
        return zzj(i << 3) + zzg(j);
    }

    public static int zzg(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) == 0) {
            i = 2;
        } else {
            j >>>= 28;
            i = 6;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        if ((j & (-16384)) != 0) {
            return i + 1;
        }
        return i;
    }

    private static int zza(int i) {
        return (i >> 31) ^ (i << 1);
    }

    private static long zza(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static zzaik zzb(byte[] bArr) {
        return new zzc(bArr, 0, bArr.length);
    }

    public static zzaik zza(OutputStream outputStream, int i) {
        return new zzd(outputStream, i);
    }

    private zzaik() {
    }

    public final void zzb() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zza(String str, zzamo zzamoVar) throws IOException {
        zza.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzamoVar);
        byte[] bytes = str.getBytes(zzajf.zza);
        try {
            zzn(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzb(e);
        }
    }

    public final void zzb(boolean z) throws IOException {
        zzb(z ? (byte) 1 : (byte) 0);
    }

    public final void zzb(int i, double d) throws IOException {
        zzf(i, Double.doubleToRawLongBits(d));
    }

    public final void zzb(double d) throws IOException {
        zzh(Double.doubleToRawLongBits(d));
    }

    public final void zzb(int i, float f) throws IOException {
        zzh(i, Float.floatToRawIntBits(f));
    }

    public final void zzb(float f) throws IOException {
        zzk(Float.floatToRawIntBits(f));
    }

    public final void zzj(int i, int i2) throws IOException {
        zzl(i, zza(i2));
    }

    public final void zzm(int i) throws IOException {
        zzn(zza(i));
    }

    public final void zzg(int i, long j) throws IOException {
        zzh(i, zza(j));
    }

    public final void zzi(long j) throws IOException {
        zzj(zza(j));
    }
}
