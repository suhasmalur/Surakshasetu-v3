package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.common.base.Ascii;
import java.io.IOException;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzahl {
    static double zza(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzd(bArr, i));
    }

    static float zzb(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzc(bArr, i));
    }

    static int zza(byte[] bArr, int i, zzahk zzahkVar) throws zzaji {
        int iZzc = zzc(bArr, i, zzahkVar);
        int i2 = zzahkVar.zza;
        if (i2 < 0) {
            throw zzaji.zzf();
        }
        if (i2 > bArr.length - iZzc) {
            throw zzaji.zzi();
        }
        if (i2 == 0) {
            zzahkVar.zzc = zzahp.zza;
            return iZzc;
        }
        zzahkVar.zzc = zzahp.zza(bArr, iZzc, i2);
        return iZzc + i2;
    }

    static int zzc(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    static int zza(zzalf zzalfVar, byte[] bArr, int i, int i2, int i3, zzahk zzahkVar) throws IOException {
        Object objZza = zzalfVar.zza();
        int iZza = zza(objZza, zzalfVar, bArr, i, i2, i3, zzahkVar);
        zzalfVar.zzc(objZza);
        zzahkVar.zzc = objZza;
        return iZza;
    }

    static int zza(zzalf zzalfVar, byte[] bArr, int i, int i2, zzahk zzahkVar) throws IOException {
        Object objZza = zzalfVar.zza();
        int iZza = zza(objZza, zzalfVar, bArr, i, i2, zzahkVar);
        zzalfVar.zzc(objZza);
        zzahkVar.zzc = objZza;
        return iZza;
    }

    static int zza(zzalf<?> zzalfVar, int i, byte[] bArr, int i2, int i3, zzajj<?> zzajjVar, zzahk zzahkVar) throws IOException {
        int iZza = zza(zzalfVar, bArr, i2, i3, zzahkVar);
        zzajjVar.add(zzahkVar.zzc);
        while (iZza < i3) {
            int iZzc = zzc(bArr, iZza, zzahkVar);
            if (i != zzahkVar.zza) {
                break;
            }
            iZza = zza(zzalfVar, bArr, iZzc, i3, zzahkVar);
            zzajjVar.add(zzahkVar.zzc);
        }
        return iZza;
    }

    static int zza(byte[] bArr, int i, zzajj<?> zzajjVar, zzahk zzahkVar) throws IOException {
        zzajd zzajdVar = (zzajd) zzajjVar;
        int iZzc = zzc(bArr, i, zzahkVar);
        int i2 = zzahkVar.zza + iZzc;
        while (iZzc < i2) {
            iZzc = zzc(bArr, iZzc, zzahkVar);
            zzajdVar.zzc(zzahkVar.zza);
        }
        if (iZzc != i2) {
            throw zzaji.zzi();
        }
        return iZzc;
    }

    static int zzb(byte[] bArr, int i, zzahk zzahkVar) throws zzaji {
        int iZzc = zzc(bArr, i, zzahkVar);
        int i2 = zzahkVar.zza;
        if (i2 < 0) {
            throw zzaji.zzf();
        }
        if (i2 == 0) {
            zzahkVar.zzc = "";
            return iZzc;
        }
        zzahkVar.zzc = zzaml.zzb(bArr, iZzc, i2);
        return iZzc + i2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzamd zzamdVar, zzahk zzahkVar) throws zzaji {
        if ((i >>> 3) == 0) {
            throw zzaji.zzc();
        }
        switch (i & 7) {
            case 0:
                int iZzd = zzd(bArr, i2, zzahkVar);
                zzamdVar.zza(i, Long.valueOf(zzahkVar.zzb));
                return iZzd;
            case 1:
                zzamdVar.zza(i, Long.valueOf(zzd(bArr, i2)));
                return i2 + 8;
            case 2:
                int iZzc = zzc(bArr, i2, zzahkVar);
                int i4 = zzahkVar.zza;
                if (i4 < 0) {
                    throw zzaji.zzf();
                }
                if (i4 > bArr.length - iZzc) {
                    throw zzaji.zzi();
                }
                if (i4 == 0) {
                    zzamdVar.zza(i, zzahp.zza);
                } else {
                    zzamdVar.zza(i, zzahp.zza(bArr, iZzc, i4));
                }
                return iZzc + i4;
            case 3:
                zzamd zzamdVarZzd = zzamd.zzd();
                int i5 = (i & (-8)) | 4;
                int i6 = 0;
                while (true) {
                    if (i2 < i3) {
                        int iZzc2 = zzc(bArr, i2, zzahkVar);
                        int i7 = zzahkVar.zza;
                        if (i7 == i5) {
                            i6 = i7;
                            i2 = iZzc2;
                        } else {
                            i6 = i7;
                            i2 = zza(i7, bArr, iZzc2, i3, zzamdVarZzd, zzahkVar);
                        }
                    }
                }
                if (i2 > i3 || i6 != i5) {
                    throw zzaji.zzg();
                }
                zzamdVar.zza(i, zzamdVarZzd);
                return i2;
            case 4:
            default:
                throw zzaji.zzc();
            case 5:
                zzamdVar.zza(i, Integer.valueOf(zzc(bArr, i2)));
                return i2 + 4;
        }
    }

    static int zzc(byte[] bArr, int i, zzahk zzahkVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            zzahkVar.zza = b;
            return i2;
        }
        return zza(b, bArr, i2, zzahkVar);
    }

    static int zza(int i, byte[] bArr, int i2, zzahk zzahkVar) {
        int i3 = i & WorkQueueKt.MASK;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzahkVar.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & 127) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzahkVar.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & 127) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzahkVar.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & 127) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzahkVar.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                zzahkVar.zza = i11;
                return i12;
            }
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzajj<?> zzajjVar, zzahk zzahkVar) {
        zzajd zzajdVar = (zzajd) zzajjVar;
        int iZzc = zzc(bArr, i2, zzahkVar);
        zzajdVar.zzc(zzahkVar.zza);
        while (iZzc < i3) {
            int iZzc2 = zzc(bArr, iZzc, zzahkVar);
            if (i != zzahkVar.zza) {
                break;
            }
            iZzc = zzc(bArr, iZzc2, zzahkVar);
            zzajdVar.zzc(zzahkVar.zza);
        }
        return iZzc;
    }

    static int zzd(byte[] bArr, int i, zzahk zzahkVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzahkVar.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & 127)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & 127)) << i4;
            b = b2;
            i3 = i5;
        }
        zzahkVar.zzb = j2;
        return i3;
    }

    static int zza(Object obj, zzalf zzalfVar, byte[] bArr, int i, int i2, int i3, zzahk zzahkVar) throws IOException {
        int iZza = ((zzakr) zzalfVar).zza(obj, bArr, i, i2, i3, zzahkVar);
        zzahkVar.zzc = obj;
        return iZza;
    }

    static int zza(Object obj, zzalf zzalfVar, byte[] bArr, int i, int i2, zzahk zzahkVar) throws IOException {
        int i3;
        int i4 = i + 1;
        int i5 = bArr[i];
        if (i5 >= 0) {
            i3 = i4;
        } else {
            int iZza = zza(i5, bArr, i4, zzahkVar);
            i5 = zzahkVar.zza;
            i3 = iZza;
        }
        if (i5 < 0 || i5 > i2 - i3) {
            throw zzaji.zzi();
        }
        int i6 = i5 + i3;
        zzalfVar.zza(obj, bArr, i3, i6, zzahkVar);
        zzahkVar.zzc = obj;
        return i6;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzahk zzahkVar) throws zzaji {
        if ((i >>> 3) == 0) {
            throw zzaji.zzc();
        }
        switch (i & 7) {
            case 0:
                return zzd(bArr, i2, zzahkVar);
            case 1:
                return i2 + 8;
            case 2:
                return zzc(bArr, i2, zzahkVar) + zzahkVar.zza;
            case 3:
                int i4 = (i & (-8)) | 4;
                int i5 = 0;
                while (i2 < i3) {
                    i2 = zzc(bArr, i2, zzahkVar);
                    i5 = zzahkVar.zza;
                    if (i5 != i4) {
                        i2 = zza(i5, bArr, i2, i3, zzahkVar);
                    } else {
                        if (i2 <= i3 || i5 != i4) {
                            throw zzaji.zzg();
                        }
                        return i2;
                    }
                }
                if (i2 <= i3) {
                }
                throw zzaji.zzg();
            case 4:
            default:
                throw zzaji.zzc();
            case 5:
                return i2 + 4;
        }
    }

    static long zzd(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }
}
