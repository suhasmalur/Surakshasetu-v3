package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzjg {
    private static final zzjg zza = new zzjg(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzjg() {
        this(0, new int[8], new Object[8], true);
    }

    private zzjg(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzjg zzc() {
        return zza;
    }

    static zzjg zze(zzjg zzjgVar, zzjg zzjgVar2) {
        int i = zzjgVar.zzb + zzjgVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzjgVar.zzc, i);
        System.arraycopy(zzjgVar2.zzc, 0, iArrCopyOf, zzjgVar.zzb, zzjgVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzjgVar.zzd, i);
        System.arraycopy(zzjgVar2.zzd, 0, objArrCopyOf, zzjgVar.zzb, zzjgVar2.zzb);
        return new zzjg(i, iArrCopyOf, objArrCopyOf, true);
    }

    static zzjg zzf() {
        return new zzjg(0, new int[8], new Object[8], true);
    }

    private final void zzm(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzjg)) {
            return false;
        }
        zzjg zzjgVar = (zzjg) obj;
        int i = this.zzb;
        if (i == zzjgVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzjgVar.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzjgVar.zzd;
                    int i3 = this.zzb;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (objArr[i4].equals(objArr2[i4])) {
                        }
                    }
                    return true;
                }
                if (iArr[i2] != iArr2[i2]) {
                    break;
                }
                i2++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = i + 527;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 * 31) + i3;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return (i5 * 31) + iHashCode;
    }

    public final int zza() {
        int iZzy;
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
                    iZzy = zzfk.zzy(i5 << 3) + zzfk.zzz(((Long) this.zzd[i3]).longValue());
                    break;
                case 1:
                    ((Long) this.zzd[i3]).longValue();
                    iZzy = zzfk.zzy(i5 << 3) + 8;
                    break;
                case 2:
                    zzez zzezVar = (zzez) this.zzd[i3];
                    int i6 = zzfk.zzb;
                    int iZzd = zzezVar.zzd();
                    iZzy = zzfk.zzy(i5 << 3) + zzfk.zzy(iZzd) + iZzd;
                    break;
                case 3:
                    int i7 = i5 << 3;
                    int i8 = zzfk.zzb;
                    int iZza = ((zzjg) this.zzd[i3]).zza();
                    int iZzy2 = zzfk.zzy(i7);
                    iZzy = iZzy2 + iZzy2 + iZza;
                    break;
                case 4:
                default:
                    throw new IllegalStateException(zzgy.zza());
                case 5:
                    ((Integer) this.zzd[i3]).intValue();
                    iZzy = zzfk.zzy(i5 << 3) + 4;
                    break;
            }
            i2 += iZzy;
        }
        this.zze = i2;
        return i2;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzy = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2] >>> 3;
            zzez zzezVar = (zzez) this.zzd[i2];
            int i4 = zzfk.zzb;
            int iZzd = zzezVar.zzd();
            int iZzy2 = zzfk.zzy(iZzd) + iZzd;
            int iZzy3 = zzfk.zzy(16);
            int iZzy4 = zzfk.zzy(i3);
            int iZzy5 = zzfk.zzy(8);
            iZzy += iZzy5 + iZzy5 + iZzy3 + iZzy4 + zzfk.zzy(24) + iZzy2;
        }
        this.zze = iZzy;
        return iZzy;
    }

    final zzjg zzd(zzjg zzjgVar) {
        if (zzjgVar.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzjgVar.zzb;
        zzm(i);
        System.arraycopy(zzjgVar.zzc, 0, this.zzc, this.zzb, zzjgVar.zzb);
        System.arraycopy(zzjgVar.zzd, 0, this.zzd, this.zzb, zzjgVar.zzb);
        this.zzb = i;
        return this;
    }

    final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    final void zzi(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzia.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zzj(int i, Object obj) {
        zzg();
        zzm(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    final void zzk(zzjx zzjxVar) throws IOException {
        for (int i = 0; i < this.zzb; i++) {
            zzjxVar.zzw(this.zzc[i] >>> 3, this.zzd[i]);
        }
    }

    public final void zzl(zzjx zzjxVar) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                switch (i3) {
                    case 0:
                        zzjxVar.zzt(i4, ((Long) obj).longValue());
                        break;
                    case 1:
                        zzjxVar.zzm(i4, ((Long) obj).longValue());
                        break;
                    case 2:
                        zzjxVar.zzd(i4, (zzez) obj);
                        break;
                    case 3:
                        zzjxVar.zzF(i4);
                        ((zzjg) obj).zzl(zzjxVar);
                        zzjxVar.zzh(i4);
                        break;
                    case 4:
                    default:
                        throw new RuntimeException(zzgy.zza());
                    case 5:
                        zzjxVar.zzk(i4, ((Integer) obj).intValue());
                        break;
                }
            }
        }
    }
}
