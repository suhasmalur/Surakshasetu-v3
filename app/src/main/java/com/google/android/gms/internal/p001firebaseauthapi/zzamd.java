package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzamd {
    private static final zzamd zza = new zzamd(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    public final int zza() {
        int iZze;
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int i5 = i4 >>> 3;
            switch (i4 & 7) {
                case 0:
                    iZze = zzaik.zze(i5, ((Long) this.zzd[i3]).longValue());
                    break;
                case 1:
                    iZze = zzaik.zza(i5, ((Long) this.zzd[i3]).longValue());
                    break;
                case 2:
                    iZze = zzaik.zza(i5, (zzahp) this.zzd[i3]);
                    break;
                case 3:
                    iZze = (zzaik.zzi(i5) << 1) + ((zzamd) this.zzd[i3]).zza();
                    break;
                case 4:
                default:
                    throw new IllegalStateException(zzaji.zza());
                case 5:
                    iZze = zzaik.zzc(i5, ((Integer) this.zzd[i3]).intValue());
                    break;
            }
            i2 += iZze;
        }
        this.zze = i2;
        return i2;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzb = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            iZzb += zzaik.zzb(this.zzc[i2] >>> 3, (zzahp) this.zzd[i2]);
        }
        this.zze = iZzb;
        return iZzb;
    }

    public final int hashCode() {
        int i = (this.zzb + 527) * 31;
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i + i3) * 31;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    public static zzamd zzc() {
        return zza;
    }

    final zzamd zza(zzamd zzamdVar) {
        if (zzamdVar.equals(zza)) {
            return this;
        }
        zzf();
        int i = this.zzb + zzamdVar.zzb;
        zza(i);
        System.arraycopy(zzamdVar.zzc, 0, this.zzc, this.zzb, zzamdVar.zzb);
        System.arraycopy(zzamdVar.zzd, 0, this.zzd, this.zzb, zzamdVar.zzb);
        this.zzb = i;
        return this;
    }

    static zzamd zza(zzamd zzamdVar, zzamd zzamdVar2) {
        int i = zzamdVar.zzb + zzamdVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzamdVar.zzc, i);
        System.arraycopy(zzamdVar2.zzc, 0, iArrCopyOf, zzamdVar.zzb, zzamdVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzamdVar.zzd, i);
        System.arraycopy(zzamdVar2.zzd, 0, objArrCopyOf, zzamdVar.zzb, zzamdVar2.zzb);
        return new zzamd(i, iArrCopyOf, objArrCopyOf, true);
    }

    static zzamd zzd() {
        return new zzamd();
    }

    private zzamd() {
        this(0, new int[8], new Object[8], true);
    }

    private zzamd(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    private final void zzf() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    private final void zza(int i) {
        if (i > this.zzc.length) {
            int i2 = this.zzb + (this.zzb / 2);
            if (i2 >= i) {
                i = i2;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(this.zzc, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final void zze() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzako.zza(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zza(int i, Object obj) {
        zzf();
        zza(this.zzb + 1);
        this.zzc[this.zzb] = i;
        this.zzd[this.zzb] = obj;
        this.zzb++;
    }

    final void zza(zzana zzanaVar) throws IOException {
        if (zzanaVar.zza() == zzand.zzb) {
            for (int i = this.zzb - 1; i >= 0; i--) {
                zzanaVar.zza(this.zzc[i] >>> 3, this.zzd[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzanaVar.zza(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    private static void zza(int i, Object obj, zzana zzanaVar) throws IOException {
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                zzanaVar.zzb(i2, ((Long) obj).longValue());
                return;
            case 1:
                zzanaVar.zza(i2, ((Long) obj).longValue());
                return;
            case 2:
                zzanaVar.zza(i2, (zzahp) obj);
                return;
            case 3:
                if (zzanaVar.zza() == zzand.zza) {
                    zzanaVar.zzb(i2);
                    ((zzamd) obj).zzb(zzanaVar);
                    zzanaVar.zza(i2);
                    return;
                } else {
                    zzanaVar.zza(i2);
                    ((zzamd) obj).zzb(zzanaVar);
                    zzanaVar.zzb(i2);
                    return;
                }
            case 4:
            default:
                throw new RuntimeException(zzaji.zza());
            case 5:
                zzanaVar.zzb(i2, ((Integer) obj).intValue());
                return;
        }
    }

    public final void zzb(zzana zzanaVar) throws IOException {
        if (this.zzb == 0) {
            return;
        }
        if (zzanaVar.zza() == zzand.zza) {
            for (int i = 0; i < this.zzb; i++) {
                zza(this.zzc[i], this.zzd[i], zzanaVar);
            }
            return;
        }
        for (int i2 = this.zzb - 1; i2 >= 0; i2--) {
            zza(this.zzc[i2], this.zzd[i2], zzanaVar);
        }
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzamd)) {
            return false;
        }
        zzamd zzamdVar = (zzamd) obj;
        if (this.zzb == zzamdVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzamdVar.zzc;
            int i = this.zzb;
            int i2 = 0;
            while (true) {
                if (i2 < i) {
                    if (iArr[i2] != iArr2[i2]) {
                        z = false;
                        break;
                    }
                    i2++;
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                Object[] objArr = this.zzd;
                Object[] objArr2 = zzamdVar.zzd;
                int i3 = this.zzb;
                int i4 = 0;
                while (true) {
                    if (i4 < i3) {
                        if (!objArr[i4].equals(objArr2[i4])) {
                            z2 = false;
                            break;
                        }
                        i4++;
                    } else {
                        z2 = true;
                        break;
                    }
                }
                if (z2) {
                    return true;
                }
            }
        }
        return false;
    }
}
