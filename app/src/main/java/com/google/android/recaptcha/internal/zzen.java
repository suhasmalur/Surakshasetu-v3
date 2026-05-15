package com.google.android.recaptcha.internal;

import com.google.common.base.Ascii;
import java.io.IOException;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzen {
    static int zza(byte[] bArr, int i, zzem zzemVar) throws zzgy {
        int iZzj = zzj(bArr, i, zzemVar);
        int i2 = zzemVar.zza;
        if (i2 < 0) {
            throw zzgy.zzf();
        }
        if (i2 > bArr.length - iZzj) {
            throw zzgy.zzj();
        }
        if (i2 == 0) {
            zzemVar.zzc = zzez.zzb;
            return iZzj;
        }
        zzemVar.zzc = zzez.zzm(bArr, iZzj, i2);
        return iZzj + i2;
    }

    static int zzb(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = bArr[i + 1] & 255;
        int i4 = bArr[i + 2] & 255;
        return ((bArr[i + 3] & 255) << 24) | (i3 << 8) | i2 | (i4 << 16);
    }

    static int zzc(zzil zzilVar, byte[] bArr, int i, int i2, int i3, zzem zzemVar) throws IOException {
        Object objZze = zzilVar.zze();
        int iZzn = zzn(objZze, zzilVar, bArr, i, i2, i3, zzemVar);
        zzilVar.zzf(objZze);
        zzemVar.zzc = objZze;
        return iZzn;
    }

    static int zzd(zzil zzilVar, byte[] bArr, int i, int i2, zzem zzemVar) throws IOException {
        Object objZze = zzilVar.zze();
        int iZzo = zzo(objZze, zzilVar, bArr, i, i2, zzemVar);
        zzilVar.zzf(objZze);
        zzemVar.zzc = objZze;
        return iZzo;
    }

    static int zze(zzil zzilVar, int i, byte[] bArr, int i2, int i3, zzgv zzgvVar, zzem zzemVar) throws IOException {
        int iZzd = zzd(zzilVar, bArr, i2, i3, zzemVar);
        zzgvVar.add(zzemVar.zzc);
        while (iZzd < i3) {
            int iZzj = zzj(bArr, iZzd, zzemVar);
            if (i != zzemVar.zza) {
                break;
            }
            iZzd = zzd(zzilVar, bArr, iZzj, i3, zzemVar);
            zzgvVar.add(zzemVar.zzc);
        }
        return iZzd;
    }

    static int zzf(byte[] bArr, int i, zzgv zzgvVar, zzem zzemVar) throws IOException {
        zzgp zzgpVar = (zzgp) zzgvVar;
        int iZzj = zzj(bArr, i, zzemVar);
        int i2 = zzemVar.zza + iZzj;
        while (iZzj < i2) {
            iZzj = zzj(bArr, iZzj, zzemVar);
            zzgpVar.zzg(zzemVar.zza);
        }
        if (iZzj == i2) {
            return iZzj;
        }
        throw zzgy.zzj();
    }

    static int zzg(byte[] bArr, int i, zzem zzemVar) throws zzgy {
        int iZzj = zzj(bArr, i, zzemVar);
        int i2 = zzemVar.zza;
        if (i2 < 0) {
            throw zzgy.zzf();
        }
        if (i2 == 0) {
            zzemVar.zzc = "";
            return iZzj;
        }
        zzemVar.zzc = new String(bArr, iZzj, i2, zzgw.zzb);
        return iZzj + i2;
    }

    static int zzh(byte[] bArr, int i, zzem zzemVar) throws zzgy {
        int iZzj = zzj(bArr, i, zzemVar);
        int i2 = zzemVar.zza;
        if (i2 < 0) {
            throw zzgy.zzf();
        }
        if (i2 == 0) {
            zzemVar.zzc = "";
            return iZzj;
        }
        zzemVar.zzc = zzju.zzd(bArr, iZzj, i2);
        return iZzj + i2;
    }

    static int zzj(byte[] bArr, int i, zzem zzemVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzk(b, bArr, i2, zzemVar);
        }
        zzemVar.zza = b;
        return i2;
    }

    static int zzk(int i, byte[] bArr, int i2, zzem zzemVar) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & WorkQueueKt.MASK;
        if (b >= 0) {
            zzemVar.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & 127) << 7);
        int i6 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzemVar.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & 127) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzemVar.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & 127) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzemVar.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzemVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzl(int i, byte[] bArr, int i2, int i3, zzgv zzgvVar, zzem zzemVar) {
        zzgp zzgpVar = (zzgp) zzgvVar;
        int iZzj = zzj(bArr, i2, zzemVar);
        zzgpVar.zzg(zzemVar.zza);
        while (iZzj < i3) {
            int iZzj2 = zzj(bArr, iZzj, zzemVar);
            if (i != zzemVar.zza) {
                break;
            }
            iZzj = zzj(bArr, iZzj2, zzemVar);
            zzgpVar.zzg(zzemVar.zza);
        }
        return iZzj;
    }

    static int zzm(byte[] bArr, int i, zzem zzemVar) {
        long j = bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzemVar.zzb = j;
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
            i3 = i5;
            b = b2;
        }
        zzemVar.zzb = j2;
        return i3;
    }

    static int zzn(Object obj, zzil zzilVar, byte[] bArr, int i, int i2, int i3, zzem zzemVar) throws IOException {
        int iZzc = ((zzib) zzilVar).zzc(obj, bArr, i, i2, i3, zzemVar);
        zzemVar.zzc = obj;
        return iZzc;
    }

    static int zzo(Object obj, zzil zzilVar, byte[] bArr, int i, int i2, zzem zzemVar) throws IOException {
        int i3;
        int i4 = i + 1;
        int i5 = bArr[i];
        if (i5 < 0) {
            int iZzk = zzk(i5, bArr, i4, zzemVar);
            i5 = zzemVar.zza;
            i3 = iZzk;
        } else {
            i3 = i4;
        }
        if (i5 < 0 || i5 > i2 - i3) {
            throw zzgy.zzj();
        }
        int i6 = i5 + i3;
        zzilVar.zzi(obj, bArr, i3, i6, zzemVar);
        zzemVar.zzc = obj;
        return i6;
    }

    static int zzp(int i, byte[] bArr, int i2, int i3, zzem zzemVar) throws zzgy {
        if ((i >>> 3) == 0) {
            throw zzgy.zzc();
        }
        switch (i & 7) {
            case 0:
                return zzm(bArr, i2, zzemVar);
            case 1:
                return i2 + 8;
            case 2:
                return zzj(bArr, i2, zzemVar) + zzemVar.zza;
            case 3:
                int i4 = (i & (-8)) | 4;
                int i5 = 0;
                while (i2 < i3) {
                    i2 = zzj(bArr, i2, zzemVar);
                    i5 = zzemVar.zza;
                    if (i5 == i4) {
                        if (i2 <= i3 || i5 != i4) {
                            throw zzgy.zzg();
                        }
                        return i2;
                    }
                    i2 = zzp(i5, bArr, i2, i3, zzemVar);
                }
                if (i2 <= i3) {
                }
                throw zzgy.zzg();
            case 4:
            default:
                throw zzgy.zzc();
            case 5:
                return i2 + 4;
        }
    }

    static long zzq(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    static int zzi(int i, byte[] bArr, int i2, int i3, zzjg zzjgVar, zzem zzemVar) throws zzgy {
        if ((i >>> 3) == 0) {
            throw zzgy.zzc();
        }
        switch (i & 7) {
            case 0:
                int iZzm = zzm(bArr, i2, zzemVar);
                zzjgVar.zzj(i, Long.valueOf(zzemVar.zzb));
                return iZzm;
            case 1:
                zzjgVar.zzj(i, Long.valueOf(zzq(bArr, i2)));
                return i2 + 8;
            case 2:
                int iZzj = zzj(bArr, i2, zzemVar);
                int i4 = zzemVar.zza;
                if (i4 < 0) {
                    throw zzgy.zzf();
                }
                if (i4 > bArr.length - iZzj) {
                    throw zzgy.zzj();
                }
                if (i4 == 0) {
                    zzjgVar.zzj(i, zzez.zzb);
                } else {
                    zzjgVar.zzj(i, zzez.zzm(bArr, iZzj, i4));
                }
                return iZzj + i4;
            case 3:
                int i5 = (i & (-8)) | 4;
                zzjg zzjgVarZzf = zzjg.zzf();
                int i6 = 0;
                while (true) {
                    if (i2 < i3) {
                        int iZzj2 = zzj(bArr, i2, zzemVar);
                        int i7 = zzemVar.zza;
                        if (i7 != i5) {
                            i6 = i7;
                            i2 = zzi(i7, bArr, iZzj2, i3, zzjgVarZzf, zzemVar);
                        } else {
                            i6 = i7;
                            i2 = iZzj2;
                        }
                    }
                }
                if (i2 > i3 || i6 != i5) {
                    throw zzgy.zzg();
                }
                zzjgVar.zzj(i, zzjgVarZzf);
                return i2;
            case 4:
            default:
                throw zzgy.zzc();
            case 5:
                zzjgVar.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                return i2 + 4;
        }
    }
}
