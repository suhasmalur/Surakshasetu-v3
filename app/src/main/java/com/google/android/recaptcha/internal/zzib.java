package com.google.android.recaptcha.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzib<T> implements zzil<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzjp.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzhy zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzhm zzn;
    private final zzjf zzo;
    private final zzga zzp;
    private final zzie zzq;
    private final zzht zzr;

    private zzib(int[] iArr, Object[] objArr, int i, int i2, zzhy zzhyVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzie zzieVar, zzhm zzhmVar, zzjf zzjfVar, zzga zzgaVar, zzht zzhtVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzhyVar instanceof zzgo;
        this.zzj = z;
        boolean z3 = false;
        if (zzgaVar != null && zzgaVar.zzj(zzhyVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzk = iArr2;
        this.zzl = i3;
        this.zzm = i4;
        this.zzq = zzieVar;
        this.zzn = zzhmVar;
        this.zzo = zzjfVar;
        this.zzp = zzgaVar;
        this.zzg = zzhyVar;
        this.zzr = zzhtVar;
    }

    private static long zzA(Object obj, long j) {
        return ((Long) zzjp.zzf(obj, j)).longValue();
    }

    private final zzgs zzB(int i) {
        int i2 = i / 3;
        return (zzgs) this.zzd[i2 + i2 + 1];
    }

    private final zzil zzC(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzil zzilVar = (zzil) this.zzd[i3];
        if (zzilVar != null) {
            return zzilVar;
        }
        zzil zzilVarZzb = zzih.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzilVarZzb;
        return zzilVarZzb;
    }

    private final Object zzD(Object obj, int i, Object obj2, zzjf zzjfVar, Object obj3) {
        int i2 = this.zzc[i];
        Object objZzf = zzjp.zzf(obj, zzz(i) & 1048575);
        if (objZzf == null || zzB(i) == null) {
            return obj2;
        }
        throw null;
    }

    private final Object zzE(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzF(Object obj, int i) {
        zzil zzilVarZzC = zzC(i);
        int iZzz = zzz(i) & 1048575;
        if (!zzT(obj, i)) {
            return zzilVarZzC.zze();
        }
        Object object = zzb.getObject(obj, iZzz);
        if (zzW(object)) {
            return object;
        }
        Object objZze = zzilVarZzC.zze();
        if (object != null) {
            zzilVarZzC.zzg(objZze, object);
        }
        return objZze;
    }

    private final Object zzG(Object obj, int i, int i2) {
        zzil zzilVarZzC = zzC(i2);
        if (!zzX(obj, i, i2)) {
            return zzilVarZzC.zze();
        }
        Object object = zzb.getObject(obj, zzz(i2) & 1048575);
        if (zzW(object)) {
            return object;
        }
        Object objZze = zzilVarZzC.zze();
        if (object != null) {
            zzilVarZzC.zzg(objZze, object);
        }
        return objZze;
    }

    private static Field zzH(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzI(Object obj) {
        if (!zzW(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzJ(Object obj, Object obj2, int i) {
        if (zzT(obj2, i)) {
            long jZzz = zzz(i) & 1048575;
            Object object = zzb.getObject(obj2, jZzz);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzil zzilVarZzC = zzC(i);
            if (!zzT(obj, i)) {
                if (zzW(object)) {
                    Object objZze = zzilVarZzC.zze();
                    zzilVarZzC.zzg(objZze, object);
                    zzb.putObject(obj, jZzz, objZze);
                } else {
                    zzb.putObject(obj, jZzz, object);
                }
                zzM(obj, i);
                return;
            }
            Object object2 = zzb.getObject(obj, jZzz);
            if (!zzW(object2)) {
                Object objZze2 = zzilVarZzC.zze();
                zzilVarZzC.zzg(objZze2, object2);
                zzb.putObject(obj, jZzz, objZze2);
                object2 = objZze2;
            }
            zzilVarZzC.zzg(object2, object);
        }
    }

    private final void zzK(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzX(obj2, i2, i)) {
            long jZzz = zzz(i) & 1048575;
            Object object = zzb.getObject(obj2, jZzz);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzil zzilVarZzC = zzC(i);
            if (!zzX(obj, i2, i)) {
                if (zzW(object)) {
                    Object objZze = zzilVarZzC.zze();
                    zzilVarZzC.zzg(objZze, object);
                    zzb.putObject(obj, jZzz, objZze);
                } else {
                    zzb.putObject(obj, jZzz, object);
                }
                zzN(obj, i2, i);
                return;
            }
            Object object2 = zzb.getObject(obj, jZzz);
            if (!zzW(object2)) {
                Object objZze2 = zzilVarZzC.zze();
                zzilVarZzC.zzg(objZze2, object2);
                zzb.putObject(obj, jZzz, objZze2);
                object2 = objZze2;
            }
            zzilVarZzC.zzg(object2, object);
        }
    }

    private final void zzL(Object obj, int i, zzik zzikVar) throws IOException {
        if (zzS(i)) {
            zzjp.zzs(obj, i & 1048575, zzikVar.zzs());
        } else if (this.zzi) {
            zzjp.zzs(obj, i & 1048575, zzikVar.zzr());
        } else {
            zzjp.zzs(obj, i & 1048575, zzikVar.zzp());
        }
    }

    private final void zzM(Object obj, int i) {
        int iZzw = zzw(i);
        long j = 1048575 & iZzw;
        if (j == 1048575) {
            return;
        }
        zzjp.zzq(obj, j, (1 << (iZzw >>> 20)) | zzjp.zzc(obj, j));
    }

    private final void zzN(Object obj, int i, int i2) {
        zzjp.zzq(obj, zzw(i2) & 1048575, i);
    }

    private final void zzO(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzz(i) & 1048575, obj2);
        zzM(obj, i);
    }

    private final void zzP(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzz(i2) & 1048575, obj2);
        zzN(obj, i, i2);
    }

    private final void zzQ(zzjx zzjxVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private final boolean zzR(Object obj, Object obj2, int i) {
        return zzT(obj, i) == zzT(obj2, i);
    }

    private static boolean zzS(int i) {
        return (i & 536870912) != 0;
    }

    private final boolean zzT(Object obj, int i) {
        int iZzw = zzw(i);
        long j = iZzw & 1048575;
        if (j != 1048575) {
            return (zzjp.zzc(obj, j) & (1 << (iZzw >>> 20))) != 0;
        }
        int iZzz = zzz(i);
        long j2 = iZzz & 1048575;
        switch (zzy(iZzz)) {
            case 0:
                return Double.doubleToRawLongBits(zzjp.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzjp.zzb(obj, j2)) != 0;
            case 2:
                return zzjp.zzd(obj, j2) != 0;
            case 3:
                return zzjp.zzd(obj, j2) != 0;
            case 4:
                return zzjp.zzc(obj, j2) != 0;
            case 5:
                return zzjp.zzd(obj, j2) != 0;
            case 6:
                return zzjp.zzc(obj, j2) != 0;
            case 7:
                return zzjp.zzw(obj, j2);
            case 8:
                Object objZzf = zzjp.zzf(obj, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzez) {
                    return !zzez.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzjp.zzf(obj, j2) != null;
            case 10:
                return !zzez.zzb.equals(zzjp.zzf(obj, j2));
            case 11:
                return zzjp.zzc(obj, j2) != 0;
            case 12:
                return zzjp.zzc(obj, j2) != 0;
            case 13:
                return zzjp.zzc(obj, j2) != 0;
            case 14:
                return zzjp.zzd(obj, j2) != 0;
            case 15:
                return zzjp.zzc(obj, j2) != 0;
            case 16:
                return zzjp.zzd(obj, j2) != 0;
            case 17:
                return zzjp.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzU(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzT(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzV(Object obj, int i, zzil zzilVar) {
        return zzilVar.zzl(zzjp.zzf(obj, i & 1048575));
    }

    private static boolean zzW(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzgo) {
            return ((zzgo) obj).zzF();
        }
        return true;
    }

    private final boolean zzX(Object obj, int i, int i2) {
        return zzjp.zzc(obj, (long) (zzw(i2) & 1048575)) == i;
    }

    private static boolean zzY(Object obj, long j) {
        return ((Boolean) zzjp.zzf(obj, j)).booleanValue();
    }

    private static final void zzZ(int i, Object obj, zzjx zzjxVar) throws IOException {
        if (obj instanceof String) {
            zzjxVar.zzG(i, (String) obj);
        } else {
            zzjxVar.zzd(i, (zzez) obj);
        }
    }

    static zzjg zzd(Object obj) {
        zzgo zzgoVar = (zzgo) obj;
        zzjg zzjgVar = zzgoVar.zzc;
        if (zzjgVar != zzjg.zzc()) {
            return zzjgVar;
        }
        zzjg zzjgVarZzf = zzjg.zzf();
        zzgoVar.zzc = zzjgVarZzf;
        return zzjgVarZzf;
    }

    static zzib zzm(Class cls, zzhv zzhvVar, zzie zzieVar, zzhm zzhmVar, zzjf zzjfVar, zzga zzgaVar, zzht zzhtVar) {
        int i;
        int iCharAt;
        int iCharAt2;
        int[] iArr;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        char cCharAt;
        int i7;
        char cCharAt2;
        int i8;
        char cCharAt3;
        int i9;
        char cCharAt4;
        int i10;
        char cCharAt5;
        int i11;
        char cCharAt6;
        int i12;
        char cCharAt7;
        int i13;
        char cCharAt8;
        int i14;
        int i15;
        int i16;
        Object[] objArr;
        int iObjectFieldOffset;
        int iObjectFieldOffset2;
        int i17;
        int i18;
        Field fieldZzH;
        char cCharAt9;
        int i19;
        Field fieldZzH2;
        Field fieldZzH3;
        int i20;
        char cCharAt10;
        int i21;
        char cCharAt11;
        int i22;
        char cCharAt12;
        int i23;
        char cCharAt13;
        if (!(zzhvVar instanceof zzij)) {
            throw null;
        }
        zzij zzijVar = (zzij) zzhvVar;
        int iZzc = zzijVar.zzc();
        String strZzd = zzijVar.zzd();
        int length = strZzd.length();
        int i24 = 0;
        int i25 = 55296;
        if (strZzd.charAt(0) >= 55296) {
            int i26 = 1;
            while (true) {
                i = i26 + 1;
                if (strZzd.charAt(i26) < 55296) {
                    break;
                }
                i26 = i;
            }
        } else {
            i = 1;
        }
        int i27 = i + 1;
        int iCharAt3 = strZzd.charAt(i);
        if (iCharAt3 >= 55296) {
            int i28 = iCharAt3 & 8191;
            int i29 = 13;
            while (true) {
                i23 = i27 + 1;
                cCharAt13 = strZzd.charAt(i27);
                if (cCharAt13 < 55296) {
                    break;
                }
                i28 |= (cCharAt13 & 8191) << i29;
                i29 += 13;
                i27 = i23;
            }
            iCharAt3 = i28 | (cCharAt13 << i29);
            i27 = i23;
        }
        if (iCharAt3 == 0) {
            i3 = 0;
            iCharAt = 0;
            iCharAt2 = 0;
            i5 = 0;
            i4 = 0;
            iArr = zza;
            i2 = 0;
        } else {
            int i30 = i27 + 1;
            int iCharAt4 = strZzd.charAt(i27);
            if (iCharAt4 >= 55296) {
                int i31 = iCharAt4 & 8191;
                int i32 = 13;
                while (true) {
                    i13 = i30 + 1;
                    cCharAt8 = strZzd.charAt(i30);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i31 |= (cCharAt8 & 8191) << i32;
                    i32 += 13;
                    i30 = i13;
                }
                iCharAt4 = i31 | (cCharAt8 << i32);
                i30 = i13;
            }
            int i33 = i30 + 1;
            int iCharAt5 = strZzd.charAt(i30);
            if (iCharAt5 >= 55296) {
                int i34 = iCharAt5 & 8191;
                int i35 = 13;
                while (true) {
                    i12 = i33 + 1;
                    cCharAt7 = strZzd.charAt(i33);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i34 |= (cCharAt7 & 8191) << i35;
                    i35 += 13;
                    i33 = i12;
                }
                iCharAt5 = i34 | (cCharAt7 << i35);
                i33 = i12;
            }
            int i36 = i33 + 1;
            int iCharAt6 = strZzd.charAt(i33);
            if (iCharAt6 >= 55296) {
                int i37 = iCharAt6 & 8191;
                int i38 = 13;
                while (true) {
                    i11 = i36 + 1;
                    cCharAt6 = strZzd.charAt(i36);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i37 |= (cCharAt6 & 8191) << i38;
                    i38 += 13;
                    i36 = i11;
                }
                iCharAt6 = i37 | (cCharAt6 << i38);
                i36 = i11;
            }
            int i39 = i36 + 1;
            int iCharAt7 = strZzd.charAt(i36);
            if (iCharAt7 >= 55296) {
                int i40 = iCharAt7 & 8191;
                int i41 = 13;
                while (true) {
                    i10 = i39 + 1;
                    cCharAt5 = strZzd.charAt(i39);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i40 |= (cCharAt5 & 8191) << i41;
                    i41 += 13;
                    i39 = i10;
                }
                iCharAt7 = i40 | (cCharAt5 << i41);
                i39 = i10;
            }
            int i42 = i39 + 1;
            iCharAt = strZzd.charAt(i39);
            if (iCharAt >= 55296) {
                int i43 = iCharAt & 8191;
                int i44 = 13;
                while (true) {
                    i9 = i42 + 1;
                    cCharAt4 = strZzd.charAt(i42);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i43 |= (cCharAt4 & 8191) << i44;
                    i44 += 13;
                    i42 = i9;
                }
                iCharAt = i43 | (cCharAt4 << i44);
                i42 = i9;
            }
            int i45 = i42 + 1;
            iCharAt2 = strZzd.charAt(i42);
            if (iCharAt2 >= 55296) {
                int i46 = iCharAt2 & 8191;
                int i47 = 13;
                while (true) {
                    i8 = i45 + 1;
                    cCharAt3 = strZzd.charAt(i45);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i46 |= (cCharAt3 & 8191) << i47;
                    i47 += 13;
                    i45 = i8;
                }
                iCharAt2 = i46 | (cCharAt3 << i47);
                i45 = i8;
            }
            int i48 = i45 + 1;
            int iCharAt8 = strZzd.charAt(i45);
            if (iCharAt8 >= 55296) {
                int i49 = iCharAt8 & 8191;
                int i50 = 13;
                while (true) {
                    i7 = i48 + 1;
                    cCharAt2 = strZzd.charAt(i48);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i49 |= (cCharAt2 & 8191) << i50;
                    i50 += 13;
                    i48 = i7;
                }
                iCharAt8 = i49 | (cCharAt2 << i50);
                i48 = i7;
            }
            int i51 = i48 + 1;
            int iCharAt9 = strZzd.charAt(i48);
            if (iCharAt9 >= 55296) {
                int i52 = iCharAt9 & 8191;
                int i53 = i51;
                int i54 = 13;
                while (true) {
                    i6 = i53 + 1;
                    cCharAt = strZzd.charAt(i53);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i52 |= (cCharAt & 8191) << i54;
                    i54 += 13;
                    i53 = i6;
                }
                iCharAt9 = i52 | (cCharAt << i54);
                i51 = i6;
            }
            int i55 = iCharAt9 + iCharAt2 + iCharAt8;
            int i56 = iCharAt4 + iCharAt4 + iCharAt5;
            int[] iArr2 = new int[i55];
            i24 = iCharAt4;
            iArr = iArr2;
            i2 = iCharAt6;
            i3 = i56;
            i4 = iCharAt9;
            i27 = i51;
            i5 = iCharAt7;
        }
        Unsafe unsafe = zzb;
        Object[] objArrZze = zzijVar.zze();
        Class<?> cls2 = zzijVar.zza().getClass();
        int i57 = i4 + iCharAt2;
        int i58 = iCharAt + iCharAt;
        int[] iArr3 = new int[iCharAt * 3];
        Object[] objArr2 = new Object[i58];
        int i59 = i4;
        int i60 = i57;
        int i61 = 0;
        int i62 = 0;
        while (true) {
            boolean z = iZzc == 2;
            if (i27 >= length) {
                return new zzib(iArr3, objArr2, i2, i5, zzijVar.zza(), z, false, iArr, i4, i57, zzieVar, zzhmVar, zzjfVar, zzgaVar, zzhtVar);
            }
            int i63 = i27 + 1;
            int iCharAt10 = strZzd.charAt(i27);
            if (iCharAt10 >= i25) {
                int i64 = iCharAt10 & 8191;
                int i65 = i63;
                int i66 = 13;
                while (true) {
                    i22 = i65 + 1;
                    cCharAt12 = strZzd.charAt(i65);
                    i14 = iZzc;
                    if (cCharAt12 < 55296) {
                        break;
                    }
                    i64 |= (cCharAt12 & 8191) << i66;
                    i66 += 13;
                    i65 = i22;
                    iZzc = i14;
                }
                iCharAt10 = i64 | (cCharAt12 << i66);
                i15 = i22;
            } else {
                i14 = iZzc;
                i15 = i63;
            }
            int i67 = i15 + 1;
            int iCharAt11 = strZzd.charAt(i15);
            int i68 = length;
            char c = 55296;
            if (iCharAt11 >= 55296) {
                int i69 = iCharAt11 & 8191;
                int i70 = 13;
                while (true) {
                    i21 = i67 + 1;
                    cCharAt11 = strZzd.charAt(i67);
                    if (cCharAt11 < c) {
                        break;
                    }
                    i69 |= (cCharAt11 & 8191) << i70;
                    i70 += 13;
                    i67 = i21;
                    c = 55296;
                }
                iCharAt11 = i69 | (cCharAt11 << i70);
                i67 = i21;
            }
            if ((iCharAt11 & 1024) != 0) {
                iArr[i61] = i62;
                i61++;
            }
            int i71 = iCharAt11 & 255;
            int i72 = i5;
            if (i71 >= 51) {
                int i73 = i67 + 1;
                int iCharAt12 = strZzd.charAt(i67);
                if (iCharAt12 >= 55296) {
                    int i74 = iCharAt12 & 8191;
                    int i75 = i73;
                    int i76 = 13;
                    while (true) {
                        i20 = i75 + 1;
                        cCharAt10 = strZzd.charAt(i75);
                        i16 = i2;
                        if (cCharAt10 < 55296) {
                            break;
                        }
                        i74 |= (cCharAt10 & 8191) << i76;
                        i76 += 13;
                        i75 = i20;
                        i2 = i16;
                    }
                    iCharAt12 = i74 | (cCharAt10 << i76);
                    i19 = i20;
                } else {
                    i16 = i2;
                    i19 = i73;
                }
                int i77 = i71 - 51;
                int i78 = i19;
                if (i77 == 9 || i77 == 17) {
                    int i79 = i62 / 3;
                    objArr2[i79 + i79 + 1] = objArrZze[i3];
                    i3++;
                } else if (i77 == 12 && !z) {
                    int i80 = i62 / 3;
                    objArr2[i80 + i80 + 1] = objArrZze[i3];
                    i3++;
                }
                int i81 = iCharAt12 + iCharAt12;
                Object obj = objArrZze[i81];
                if (obj instanceof Field) {
                    fieldZzH2 = (Field) obj;
                } else {
                    fieldZzH2 = zzH(cls2, (String) obj);
                    objArrZze[i81] = fieldZzH2;
                }
                int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZzH2);
                int i82 = i81 + 1;
                Object obj2 = objArrZze[i82];
                if (obj2 instanceof Field) {
                    fieldZzH3 = (Field) obj2;
                } else {
                    fieldZzH3 = zzH(cls2, (String) obj2);
                    objArrZze[i82] = fieldZzH3;
                }
                iObjectFieldOffset = iObjectFieldOffset3;
                objArr = objArrZze;
                i17 = i78;
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzH3);
                i18 = 0;
            } else {
                i16 = i2;
                int i83 = i3 + 1;
                Field fieldZzH4 = zzH(cls2, (String) objArrZze[i3]);
                if (i71 == 9 || i71 == 17) {
                    int i84 = i62 / 3;
                    objArr2[i84 + i84 + 1] = fieldZzH4.getType();
                } else if (i71 == 27 || i71 == 49) {
                    int i85 = i62 / 3;
                    objArr2[i85 + i85 + 1] = objArrZze[i83];
                    i83++;
                } else if (i71 == 12 || i71 == 30 || i71 == 44) {
                    if (!z) {
                        int i86 = i62 / 3;
                        objArr2[i86 + i86 + 1] = objArrZze[i83];
                        i83++;
                    }
                } else if (i71 == 50) {
                    int i87 = i59 + 1;
                    iArr[i59] = i62;
                    int i88 = i62 / 3;
                    int i89 = i83 + 1;
                    int i90 = i88 + i88;
                    objArr2[i90] = objArrZze[i83];
                    if ((iCharAt11 & 2048) != 0) {
                        i83 = i89 + 1;
                        objArr2[i90 + 1] = objArrZze[i89];
                        i59 = i87;
                    } else {
                        i83 = i89;
                        i59 = i87;
                    }
                }
                objArr = objArrZze;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzH4);
                iObjectFieldOffset2 = 1048575;
                if ((iCharAt11 & 4096) != 4096 || i71 > 17) {
                    i17 = i67;
                    i18 = 0;
                } else {
                    int i91 = i67 + 1;
                    int iCharAt13 = strZzd.charAt(i67);
                    if (iCharAt13 >= 55296) {
                        int i92 = iCharAt13 & 8191;
                        int i93 = 13;
                        while (true) {
                            i17 = i91 + 1;
                            cCharAt9 = strZzd.charAt(i91);
                            if (cCharAt9 < 55296) {
                                break;
                            }
                            i92 |= (cCharAt9 & 8191) << i93;
                            i93 += 13;
                            i91 = i17;
                        }
                        iCharAt13 = i92 | (cCharAt9 << i93);
                    } else {
                        i17 = i91;
                    }
                    int i94 = i24 + i24 + (iCharAt13 / 32);
                    Object obj3 = objArr[i94];
                    if (obj3 instanceof Field) {
                        fieldZzH = (Field) obj3;
                    } else {
                        fieldZzH = zzH(cls2, (String) obj3);
                        objArr[i94] = fieldZzH;
                    }
                    i18 = iCharAt13 % 32;
                    iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzH);
                }
                if (i71 < 18 || i71 > 49) {
                    i3 = i83;
                } else {
                    iArr[i60] = iObjectFieldOffset;
                    i60++;
                    i3 = i83;
                }
            }
            int i95 = i62 + 1;
            iArr3[i62] = iCharAt10;
            int i96 = i95 + 1;
            iArr3[i95] = ((iCharAt11 & 256) != 0 ? 268435456 : 0) | ((iCharAt11 & 512) != 0 ? 536870912 : 0) | (i71 << 20) | iObjectFieldOffset;
            i62 = i96 + 1;
            iArr3[i96] = (i18 << 20) | iObjectFieldOffset2;
            objArrZze = objArr;
            length = i68;
            i27 = i17;
            i5 = i72;
            iZzc = i14;
            i2 = i16;
            i25 = 55296;
        }
    }

    private static double zzn(Object obj, long j) {
        return ((Double) zzjp.zzf(obj, j)).doubleValue();
    }

    private static float zzo(Object obj, long j) {
        return ((Float) zzjp.zzf(obj, j)).floatValue();
    }

    private final int zzp(Object obj) {
        int i;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 1048575;
        int i4 = 0;
        int iZzy = 0;
        int i5 = 0;
        while (i4 < this.zzc.length) {
            int iZzz = zzz(i4);
            int[] iArr = this.zzc;
            int i6 = iArr[i4];
            int iZzy2 = zzy(iZzz);
            if (iZzy2 <= 17) {
                int i7 = iArr[i4 + 2];
                int i8 = i7 & i2;
                int i9 = i7 >>> 20;
                if (i8 != i3) {
                    i5 = unsafe.getInt(obj, i8);
                    i3 = i8;
                }
                i = 1 << i9;
            } else {
                i = 0;
            }
            long j = iZzz & i2;
            switch (iZzy2) {
                case 0:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + 8;
                    }
                    break;
                case 1:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + 4;
                    }
                    break;
                case 2:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzz(unsafe.getLong(obj, j));
                    }
                    break;
                case 3:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzz(unsafe.getLong(obj, j));
                    }
                    break;
                case 4:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzu(unsafe.getInt(obj, j));
                    }
                    break;
                case 5:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + 8;
                    }
                    break;
                case 6:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + 4;
                    }
                    break;
                case 7:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + 1;
                    }
                    break;
                case 8:
                    if ((i5 & i) != 0) {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzez) {
                            int i10 = zzfk.zzb;
                            int iZzd = ((zzez) object).zzd();
                            iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzd) + iZzd;
                        } else {
                            iZzy += zzfk.zzy(i6 << 3) + zzfk.zzx((String) object);
                        }
                    }
                    break;
                case 9:
                    if ((i5 & i) != 0) {
                        iZzy += zzin.zzn(i6, unsafe.getObject(obj, j), zzC(i4));
                    }
                    break;
                case 10:
                    if ((i5 & i) != 0) {
                        zzez zzezVar = (zzez) unsafe.getObject(obj, j);
                        int i11 = zzfk.zzb;
                        int iZzd2 = zzezVar.zzd();
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzd2) + iZzd2;
                    }
                    break;
                case 11:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(unsafe.getInt(obj, j));
                    }
                    break;
                case 12:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzu(unsafe.getInt(obj, j));
                    }
                    break;
                case 13:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + 4;
                    }
                    break;
                case 14:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzy(i6 << 3) + 8;
                    }
                    break;
                case 15:
                    if ((i5 & i) != 0) {
                        int i12 = unsafe.getInt(obj, j);
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy((i12 >> 31) ^ (i12 + i12));
                    }
                    break;
                case 16:
                    if ((i & i5) != 0) {
                        long j2 = unsafe.getLong(obj, j);
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzz((j2 >> 63) ^ (j2 + j2));
                    }
                    break;
                case 17:
                    if ((i5 & i) != 0) {
                        iZzy += zzfk.zzt(i6, (zzhy) unsafe.getObject(obj, j), zzC(i4));
                    }
                    break;
                case 18:
                    iZzy += zzin.zzg(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 19:
                    iZzy += zzin.zze(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 20:
                    iZzy += zzin.zzl(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 21:
                    iZzy += zzin.zzw(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 22:
                    iZzy += zzin.zzj(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 23:
                    iZzy += zzin.zzg(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 24:
                    iZzy += zzin.zze(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 25:
                    iZzy += zzin.zza(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 26:
                    iZzy += zzin.zzt(i6, (List) unsafe.getObject(obj, j));
                    break;
                case 27:
                    iZzy += zzin.zzo(i6, (List) unsafe.getObject(obj, j), zzC(i4));
                    break;
                case 28:
                    iZzy += zzin.zzb(i6, (List) unsafe.getObject(obj, j));
                    break;
                case 29:
                    iZzy += zzin.zzu(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 30:
                    iZzy += zzin.zzc(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 31:
                    iZzy += zzin.zze(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 32:
                    iZzy += zzin.zzg(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 33:
                    iZzy += zzin.zzp(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 34:
                    iZzy += zzin.zzr(i6, (List) unsafe.getObject(obj, j), false);
                    break;
                case 35:
                    int iZzh = zzin.zzh((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzh) + iZzh;
                    }
                    break;
                case 36:
                    int iZzf = zzin.zzf((List) unsafe.getObject(obj, j));
                    if (iZzf > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzf) + iZzf;
                    }
                    break;
                case 37:
                    int iZzm = zzin.zzm((List) unsafe.getObject(obj, j));
                    if (iZzm > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzm) + iZzm;
                    }
                    break;
                case 38:
                    int iZzx = zzin.zzx((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzx) + iZzx;
                    }
                    break;
                case 39:
                    int iZzk = zzin.zzk((List) unsafe.getObject(obj, j));
                    if (iZzk > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzk) + iZzk;
                    }
                    break;
                case 40:
                    int iZzh2 = zzin.zzh((List) unsafe.getObject(obj, j));
                    if (iZzh2 > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzh2) + iZzh2;
                    }
                    break;
                case 41:
                    int iZzf2 = zzin.zzf((List) unsafe.getObject(obj, j));
                    if (iZzf2 > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzf2) + iZzf2;
                    }
                    break;
                case 42:
                    List list = (List) unsafe.getObject(obj, j);
                    int i13 = zzin.zza;
                    int size = list.size();
                    if (size > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(size) + size;
                    }
                    break;
                case 43:
                    int iZzv = zzin.zzv((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzv) + iZzv;
                    }
                    break;
                case 44:
                    int iZzd3 = zzin.zzd((List) unsafe.getObject(obj, j));
                    if (iZzd3 > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzd3) + iZzd3;
                    }
                    break;
                case 45:
                    int iZzf3 = zzin.zzf((List) unsafe.getObject(obj, j));
                    if (iZzf3 > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzf3) + iZzf3;
                    }
                    break;
                case 46:
                    int iZzh3 = zzin.zzh((List) unsafe.getObject(obj, j));
                    if (iZzh3 > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzh3) + iZzh3;
                    }
                    break;
                case 47:
                    int iZzq = zzin.zzq((List) unsafe.getObject(obj, j));
                    if (iZzq > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzq) + iZzq;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    int iZzs = zzin.zzs((List) unsafe.getObject(obj, j));
                    if (iZzs > 0) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzs) + iZzs;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    iZzy += zzin.zzi(i6, (List) unsafe.getObject(obj, j), zzC(i4));
                    break;
                case 50:
                    zzht.zza(i6, unsafe.getObject(obj, j), zzE(i4));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + 8;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + 4;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzz(zzA(obj, j));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzz(zzA(obj, j));
                    }
                    break;
                case 55:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzu(zzq(obj, j));
                    }
                    break;
                case 56:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + 8;
                    }
                    break;
                case 57:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + 4;
                    }
                    break;
                case 58:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + 1;
                    }
                    break;
                case 59:
                    if (zzX(obj, i6, i4)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzez) {
                            int i14 = zzfk.zzb;
                            int iZzd4 = ((zzez) object2).zzd();
                            iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzd4) + iZzd4;
                        } else {
                            iZzy += zzfk.zzy(i6 << 3) + zzfk.zzx((String) object2);
                        }
                    }
                    break;
                case 60:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzin.zzn(i6, unsafe.getObject(obj, j), zzC(i4));
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzX(obj, i6, i4)) {
                        zzez zzezVar2 = (zzez) unsafe.getObject(obj, j);
                        int i15 = zzfk.zzb;
                        int iZzd5 = zzezVar2.zzd();
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(iZzd5) + iZzd5;
                    }
                    break;
                case 62:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy(zzq(obj, j));
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzu(zzq(obj, j));
                    }
                    break;
                case 64:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + 4;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzy(i6 << 3) + 8;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzX(obj, i6, i4)) {
                        int iZzq2 = zzq(obj, j);
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzy((iZzq2 >> 31) ^ (iZzq2 + iZzq2));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzX(obj, i6, i4)) {
                        long jZzA = zzA(obj, j);
                        iZzy += zzfk.zzy(i6 << 3) + zzfk.zzz((jZzA >> 63) ^ (jZzA + jZzA));
                    }
                    break;
                case 68:
                    if (zzX(obj, i6, i4)) {
                        iZzy += zzfk.zzt(i6, (zzhy) unsafe.getObject(obj, j), zzC(i4));
                    }
                    break;
            }
            i4 += 3;
            i2 = 1048575;
        }
        int iZza = 0;
        zzjf zzjfVar = this.zzo;
        int iZza2 = iZzy + zzjfVar.zza(zzjfVar.zzd(obj));
        if (!this.zzh) {
            return iZza2;
        }
        zzge zzgeVarZzb = this.zzp.zzb(obj);
        for (int i16 = 0; i16 < zzgeVarZzb.zza.zzb(); i16++) {
            Map.Entry entryZzg = zzgeVarZzb.zza.zzg(i16);
            iZza += zzge.zza((zzgd) entryZzg.getKey(), entryZzg.getValue());
        }
        for (Map.Entry entry : zzgeVarZzb.zza.zzc()) {
            iZza += zzge.zza((zzgd) entry.getKey(), entry.getValue());
        }
        return iZza2 + iZza;
    }

    private static int zzq(Object obj, long j) {
        return ((Integer) zzjp.zzf(obj, j)).intValue();
    }

    private final int zzr(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzem zzemVar) throws IOException {
        Unsafe unsafe = zzb;
        Object objZzE = zzE(i3);
        Object object = unsafe.getObject(obj, j);
        if (zzht.zzb(object)) {
            zzhs zzhsVarZzb = zzhs.zza().zzb();
            zzht.zzc(zzhsVarZzb, object);
            unsafe.putObject(obj, j, zzhsVarZzb);
        }
        throw null;
    }

    private final int zzs(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzem zzemVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzen.zzq(bArr, i))));
                int i9 = i + 8;
                unsafe.putInt(obj, j2, i4);
                return i9;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzen.zzb(bArr, i))));
                int i10 = i + 4;
                unsafe.putInt(obj, j2, i4);
                return i10;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                if (i5 != 0) {
                    return i;
                }
                int iZzm = zzen.zzm(bArr, i, zzemVar);
                unsafe.putObject(obj, j, Long.valueOf(zzemVar.zzb));
                unsafe.putInt(obj, j2, i4);
                return iZzm;
            case 55:
            case 62:
                if (i5 != 0) {
                    return i;
                }
                int iZzj = zzen.zzj(bArr, i, zzemVar);
                unsafe.putObject(obj, j, Integer.valueOf(zzemVar.zza));
                unsafe.putInt(obj, j2, i4);
                return iZzj;
            case 56:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(obj, j, Long.valueOf(zzen.zzq(bArr, i)));
                int i11 = i + 8;
                unsafe.putInt(obj, j2, i4);
                return i11;
            case 57:
            case 64:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(obj, j, Integer.valueOf(zzen.zzb(bArr, i)));
                int i12 = i + 4;
                unsafe.putInt(obj, j2, i4);
                return i12;
            case 58:
                if (i5 != 0) {
                    return i;
                }
                int iZzm2 = zzen.zzm(bArr, i, zzemVar);
                unsafe.putObject(obj, j, Boolean.valueOf(zzemVar.zzb != 0));
                unsafe.putInt(obj, j2, i4);
                return iZzm2;
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int iZzj2 = zzen.zzj(bArr, i, zzemVar);
                int i13 = zzemVar.zza;
                if (i13 == 0) {
                    unsafe.putObject(obj, j, "");
                } else {
                    if ((i6 & 536870912) != 0 && !zzju.zzf(bArr, iZzj2, iZzj2 + i13)) {
                        throw zzgy.zzd();
                    }
                    unsafe.putObject(obj, j, new String(bArr, iZzj2, i13, zzgw.zzb));
                    iZzj2 += i13;
                }
                unsafe.putInt(obj, j2, i4);
                return iZzj2;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                Object objZzG = zzG(obj, i4, i8);
                int iZzo = zzen.zzo(objZzG, zzC(i8), bArr, i, i2, zzemVar);
                zzP(obj, i4, i8, objZzG);
                return iZzo;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                if (i5 != 2) {
                    return i;
                }
                int iZza = zzen.zza(bArr, i, zzemVar);
                unsafe.putObject(obj, j, zzemVar.zzc);
                unsafe.putInt(obj, j2, i4);
                return iZza;
            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                if (i5 != 0) {
                    return i;
                }
                int iZzj3 = zzen.zzj(bArr, i, zzemVar);
                int i14 = zzemVar.zza;
                zzgs zzgsVarZzB = zzB(i8);
                if (zzgsVarZzB == null || zzgsVarZzB.zza()) {
                    unsafe.putObject(obj, j, Integer.valueOf(i14));
                    unsafe.putInt(obj, j2, i4);
                } else {
                    zzd(obj).zzj(i3, Long.valueOf(i14));
                }
                return iZzj3;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                if (i5 != 0) {
                    return i;
                }
                int iZzj4 = zzen.zzj(bArr, i, zzemVar);
                unsafe.putObject(obj, j, Integer.valueOf(zzff.zzF(zzemVar.zza)));
                unsafe.putInt(obj, j2, i4);
                return iZzj4;
            case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                if (i5 != 0) {
                    return i;
                }
                int iZzm3 = zzen.zzm(bArr, i, zzemVar);
                unsafe.putObject(obj, j, Long.valueOf(zzff.zzG(zzemVar.zzb)));
                unsafe.putInt(obj, j2, i4);
                return iZzm3;
            case 68:
                if (i5 != 3) {
                    return i;
                }
                Object objZzG2 = zzG(obj, i4, i8);
                int iZzn = zzen.zzn(objZzG2, zzC(i8), bArr, i, i2, (i3 & (-8)) | 4, zzemVar);
                zzP(obj, i4, i8, objZzG2);
                return iZzn;
            default:
                return i;
        }
    }

    private final int zzt(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzem zzemVar) throws IOException {
        int iZzl;
        zzgv zzgvVarZzd = (zzgv) zzb.getObject(obj, j2);
        if (!zzgvVarZzd.zzc()) {
            int size = zzgvVarZzd.size();
            zzgvVarZzd = zzgvVarZzd.zzd(size == 0 ? 10 : size + size);
            zzb.putObject(obj, j2, zzgvVarZzd);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzft zzftVar = (zzft) zzgvVarZzd;
                    int iZzj = zzen.zzj(bArr, i, zzemVar);
                    int i8 = zzemVar.zza + iZzj;
                    while (iZzj < i8) {
                        zzftVar.zze(Double.longBitsToDouble(zzen.zzq(bArr, iZzj)));
                        iZzj += 8;
                    }
                    if (iZzj == i8) {
                        return iZzj;
                    }
                    throw zzgy.zzj();
                }
                if (i5 == 1) {
                    zzft zzftVar2 = (zzft) zzgvVarZzd;
                    zzftVar2.zze(Double.longBitsToDouble(zzen.zzq(bArr, i)));
                    int i9 = i + 8;
                    while (i9 < i2) {
                        int iZzj2 = zzen.zzj(bArr, i9, zzemVar);
                        if (i3 != zzemVar.zza) {
                            return i9;
                        }
                        zzftVar2.zze(Double.longBitsToDouble(zzen.zzq(bArr, iZzj2)));
                        i9 = iZzj2 + 8;
                    }
                    return i9;
                }
                break;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzgg zzggVar = (zzgg) zzgvVarZzd;
                    int iZzj3 = zzen.zzj(bArr, i, zzemVar);
                    int i10 = zzemVar.zza + iZzj3;
                    while (iZzj3 < i10) {
                        zzggVar.zze(Float.intBitsToFloat(zzen.zzb(bArr, iZzj3)));
                        iZzj3 += 4;
                    }
                    if (iZzj3 == i10) {
                        return iZzj3;
                    }
                    throw zzgy.zzj();
                }
                if (i5 == 5) {
                    zzgg zzggVar2 = (zzgg) zzgvVarZzd;
                    zzggVar2.zze(Float.intBitsToFloat(zzen.zzb(bArr, i)));
                    int i11 = i + 4;
                    while (i11 < i2) {
                        int iZzj4 = zzen.zzj(bArr, i11, zzemVar);
                        if (i3 != zzemVar.zza) {
                            return i11;
                        }
                        zzggVar2.zze(Float.intBitsToFloat(zzen.zzb(bArr, iZzj4)));
                        i11 = iZzj4 + 4;
                    }
                    return i11;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzhn zzhnVar = (zzhn) zzgvVarZzd;
                    int iZzj5 = zzen.zzj(bArr, i, zzemVar);
                    int i12 = zzemVar.zza + iZzj5;
                    while (iZzj5 < i12) {
                        iZzj5 = zzen.zzm(bArr, iZzj5, zzemVar);
                        zzhnVar.zzf(zzemVar.zzb);
                    }
                    if (iZzj5 == i12) {
                        return iZzj5;
                    }
                    throw zzgy.zzj();
                }
                if (i5 == 0) {
                    zzhn zzhnVar2 = (zzhn) zzgvVarZzd;
                    int iZzm = zzen.zzm(bArr, i, zzemVar);
                    zzhnVar2.zzf(zzemVar.zzb);
                    while (iZzm < i2) {
                        int iZzj6 = zzen.zzj(bArr, iZzm, zzemVar);
                        if (i3 != zzemVar.zza) {
                            return iZzm;
                        }
                        iZzm = zzen.zzm(bArr, iZzj6, zzemVar);
                        zzhnVar2.zzf(zzemVar.zzb);
                    }
                    return iZzm;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzen.zzf(bArr, i, zzgvVarZzd, zzemVar);
                }
                if (i5 == 0) {
                    return zzen.zzl(i3, bArr, i, i2, zzgvVarZzd, zzemVar);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzhn zzhnVar3 = (zzhn) zzgvVarZzd;
                    int iZzj7 = zzen.zzj(bArr, i, zzemVar);
                    int i13 = zzemVar.zza + iZzj7;
                    while (iZzj7 < i13) {
                        zzhnVar3.zzf(zzen.zzq(bArr, iZzj7));
                        iZzj7 += 8;
                    }
                    if (iZzj7 == i13) {
                        return iZzj7;
                    }
                    throw zzgy.zzj();
                }
                if (i5 == 1) {
                    zzhn zzhnVar4 = (zzhn) zzgvVarZzd;
                    zzhnVar4.zzf(zzen.zzq(bArr, i));
                    int i14 = i + 8;
                    while (i14 < i2) {
                        int iZzj8 = zzen.zzj(bArr, i14, zzemVar);
                        if (i3 != zzemVar.zza) {
                            return i14;
                        }
                        zzhnVar4.zzf(zzen.zzq(bArr, iZzj8));
                        i14 = iZzj8 + 8;
                    }
                    return i14;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzgp zzgpVar = (zzgp) zzgvVarZzd;
                    int iZzj9 = zzen.zzj(bArr, i, zzemVar);
                    int i15 = zzemVar.zza + iZzj9;
                    while (iZzj9 < i15) {
                        zzgpVar.zzg(zzen.zzb(bArr, iZzj9));
                        iZzj9 += 4;
                    }
                    if (iZzj9 == i15) {
                        return iZzj9;
                    }
                    throw zzgy.zzj();
                }
                if (i5 == 5) {
                    zzgp zzgpVar2 = (zzgp) zzgvVarZzd;
                    zzgpVar2.zzg(zzen.zzb(bArr, i));
                    int i16 = i + 4;
                    while (i16 < i2) {
                        int iZzj10 = zzen.zzj(bArr, i16, zzemVar);
                        if (i3 != zzemVar.zza) {
                            return i16;
                        }
                        zzgpVar2.zzg(zzen.zzb(bArr, iZzj10));
                        i16 = iZzj10 + 4;
                    }
                    return i16;
                }
                break;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzeo zzeoVar = (zzeo) zzgvVarZzd;
                    int iZzj11 = zzen.zzj(bArr, i, zzemVar);
                    int i17 = zzemVar.zza + iZzj11;
                    while (iZzj11 < i17) {
                        iZzj11 = zzen.zzm(bArr, iZzj11, zzemVar);
                        zzeoVar.zze(zzemVar.zzb != 0);
                    }
                    if (iZzj11 == i17) {
                        return iZzj11;
                    }
                    throw zzgy.zzj();
                }
                if (i5 == 0) {
                    zzeo zzeoVar2 = (zzeo) zzgvVarZzd;
                    int iZzm2 = zzen.zzm(bArr, i, zzemVar);
                    zzeoVar2.zze(zzemVar.zzb != 0);
                    while (iZzm2 < i2) {
                        int iZzj12 = zzen.zzj(bArr, iZzm2, zzemVar);
                        if (i3 != zzemVar.zza) {
                            return iZzm2;
                        }
                        iZzm2 = zzen.zzm(bArr, iZzj12, zzemVar);
                        zzeoVar2.zze(zzemVar.zzb != 0);
                    }
                    return iZzm2;
                }
                break;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        int iZzj13 = zzen.zzj(bArr, i, zzemVar);
                        int i18 = zzemVar.zza;
                        if (i18 < 0) {
                            throw zzgy.zzf();
                        }
                        if (i18 == 0) {
                            zzgvVarZzd.add("");
                        } else {
                            zzgvVarZzd.add(new String(bArr, iZzj13, i18, zzgw.zzb));
                            iZzj13 += i18;
                        }
                        while (iZzj13 < i2) {
                            int iZzj14 = zzen.zzj(bArr, iZzj13, zzemVar);
                            if (i3 != zzemVar.zza) {
                                return iZzj13;
                            }
                            iZzj13 = zzen.zzj(bArr, iZzj14, zzemVar);
                            int i19 = zzemVar.zza;
                            if (i19 < 0) {
                                throw zzgy.zzf();
                            }
                            if (i19 == 0) {
                                zzgvVarZzd.add("");
                            } else {
                                zzgvVarZzd.add(new String(bArr, iZzj13, i19, zzgw.zzb));
                                iZzj13 += i19;
                            }
                        }
                        return iZzj13;
                    }
                    int iZzj15 = zzen.zzj(bArr, i, zzemVar);
                    int i20 = zzemVar.zza;
                    if (i20 < 0) {
                        throw zzgy.zzf();
                    }
                    if (i20 == 0) {
                        zzgvVarZzd.add("");
                    } else {
                        int i21 = iZzj15 + i20;
                        if (!zzju.zzf(bArr, iZzj15, i21)) {
                            throw zzgy.zzd();
                        }
                        zzgvVarZzd.add(new String(bArr, iZzj15, i20, zzgw.zzb));
                        iZzj15 = i21;
                    }
                    while (iZzj15 < i2) {
                        int iZzj16 = zzen.zzj(bArr, iZzj15, zzemVar);
                        if (i3 != zzemVar.zza) {
                            return iZzj15;
                        }
                        iZzj15 = zzen.zzj(bArr, iZzj16, zzemVar);
                        int i22 = zzemVar.zza;
                        if (i22 < 0) {
                            throw zzgy.zzf();
                        }
                        if (i22 == 0) {
                            zzgvVarZzd.add("");
                        } else {
                            int i23 = iZzj15 + i22;
                            if (!zzju.zzf(bArr, iZzj15, i23)) {
                                throw zzgy.zzd();
                            }
                            zzgvVarZzd.add(new String(bArr, iZzj15, i22, zzgw.zzb));
                            iZzj15 = i23;
                        }
                    }
                    return iZzj15;
                }
                break;
            case 27:
                if (i5 == 2) {
                    return zzen.zze(zzC(i6), i3, bArr, i, i2, zzgvVarZzd, zzemVar);
                }
                break;
            case 28:
                if (i5 == 2) {
                    int iZzj17 = zzen.zzj(bArr, i, zzemVar);
                    int i24 = zzemVar.zza;
                    if (i24 < 0) {
                        throw zzgy.zzf();
                    }
                    if (i24 > bArr.length - iZzj17) {
                        throw zzgy.zzj();
                    }
                    if (i24 == 0) {
                        zzgvVarZzd.add(zzez.zzb);
                    } else {
                        zzgvVarZzd.add(zzez.zzm(bArr, iZzj17, i24));
                        iZzj17 += i24;
                    }
                    while (iZzj17 < i2) {
                        int iZzj18 = zzen.zzj(bArr, iZzj17, zzemVar);
                        if (i3 != zzemVar.zza) {
                            return iZzj17;
                        }
                        iZzj17 = zzen.zzj(bArr, iZzj18, zzemVar);
                        int i25 = zzemVar.zza;
                        if (i25 < 0) {
                            throw zzgy.zzf();
                        }
                        if (i25 > bArr.length - iZzj17) {
                            throw zzgy.zzj();
                        }
                        if (i25 == 0) {
                            zzgvVarZzd.add(zzez.zzb);
                        } else {
                            zzgvVarZzd.add(zzez.zzm(bArr, iZzj17, i25));
                            iZzj17 += i25;
                        }
                    }
                    return iZzj17;
                }
                break;
            case 30:
            case 44:
                if (i5 == 2) {
                    iZzl = zzen.zzf(bArr, i, zzgvVarZzd, zzemVar);
                } else if (i5 == 0) {
                    iZzl = zzen.zzl(i3, bArr, i, i2, zzgvVarZzd, zzemVar);
                }
                zzin.zzB(obj, i4, zzgvVarZzd, zzB(i6), null, this.zzo);
                return iZzl;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzgp zzgpVar3 = (zzgp) zzgvVarZzd;
                    int iZzj19 = zzen.zzj(bArr, i, zzemVar);
                    int i26 = zzemVar.zza + iZzj19;
                    while (iZzj19 < i26) {
                        iZzj19 = zzen.zzj(bArr, iZzj19, zzemVar);
                        zzgpVar3.zzg(zzff.zzF(zzemVar.zza));
                    }
                    if (iZzj19 == i26) {
                        return iZzj19;
                    }
                    throw zzgy.zzj();
                }
                if (i5 == 0) {
                    zzgp zzgpVar4 = (zzgp) zzgvVarZzd;
                    int iZzj20 = zzen.zzj(bArr, i, zzemVar);
                    zzgpVar4.zzg(zzff.zzF(zzemVar.zza));
                    while (iZzj20 < i2) {
                        int iZzj21 = zzen.zzj(bArr, iZzj20, zzemVar);
                        if (i3 != zzemVar.zza) {
                            return iZzj20;
                        }
                        iZzj20 = zzen.zzj(bArr, iZzj21, zzemVar);
                        zzgpVar4.zzg(zzff.zzF(zzemVar.zza));
                    }
                    return iZzj20;
                }
                break;
            case 34:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                if (i5 == 2) {
                    zzhn zzhnVar5 = (zzhn) zzgvVarZzd;
                    int iZzj22 = zzen.zzj(bArr, i, zzemVar);
                    int i27 = zzemVar.zza + iZzj22;
                    while (iZzj22 < i27) {
                        iZzj22 = zzen.zzm(bArr, iZzj22, zzemVar);
                        zzhnVar5.zzf(zzff.zzG(zzemVar.zzb));
                    }
                    if (iZzj22 == i27) {
                        return iZzj22;
                    }
                    throw zzgy.zzj();
                }
                if (i5 == 0) {
                    zzhn zzhnVar6 = (zzhn) zzgvVarZzd;
                    int iZzm3 = zzen.zzm(bArr, i, zzemVar);
                    zzhnVar6.zzf(zzff.zzG(zzemVar.zzb));
                    while (iZzm3 < i2) {
                        int iZzj23 = zzen.zzj(bArr, iZzm3, zzemVar);
                        if (i3 != zzemVar.zza) {
                            return iZzm3;
                        }
                        iZzm3 = zzen.zzm(bArr, iZzj23, zzemVar);
                        zzhnVar6.zzf(zzff.zzG(zzemVar.zzb));
                    }
                    return iZzm3;
                }
                break;
            default:
                if (i5 == 3) {
                    zzil zzilVarZzC = zzC(i6);
                    int i28 = (i3 & (-8)) | 4;
                    int iZzc = zzen.zzc(zzilVarZzC, bArr, i, i2, i28, zzemVar);
                    zzgvVarZzd.add(zzemVar.zzc);
                    while (iZzc < i2) {
                        int iZzj24 = zzen.zzj(bArr, iZzc, zzemVar);
                        if (i3 != zzemVar.zza) {
                            return iZzc;
                        }
                        iZzc = zzen.zzc(zzilVarZzC, bArr, iZzj24, i2, i28, zzemVar);
                        zzgvVarZzd.add(zzemVar.zzc);
                    }
                    return iZzc;
                }
                break;
        }
        return i;
    }

    private final int zzu(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzx(i, 0);
    }

    private final int zzv(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzx(i, i2);
    }

    private final int zzw(int i) {
        return this.zzc[i + 2];
    }

    private final int zzx(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzy(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzz(int i) {
        return this.zzc[i + 1];
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final int zza(Object obj) {
        if (!this.zzj) {
            return zzp(obj);
        }
        Unsafe unsafe = zzb;
        int iZzy = 0;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzz = zzz(i);
            int iZzy2 = zzy(iZzz);
            int i2 = this.zzc[i];
            int i3 = iZzz & 1048575;
            if (iZzy2 >= zzgf.DOUBLE_LIST_PACKED.zza() && iZzy2 <= zzgf.SINT64_LIST_PACKED.zza()) {
                int i4 = this.zzc[i + 2];
            }
            long j = i3;
            switch (iZzy2) {
                case 0:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 8;
                    }
                    break;
                case 1:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 4;
                    }
                    break;
                case 2:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzz(zzjp.zzd(obj, j));
                    }
                    break;
                case 3:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzz(zzjp.zzd(obj, j));
                    }
                    break;
                case 4:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzu(zzjp.zzc(obj, j));
                    }
                    break;
                case 5:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 8;
                    }
                    break;
                case 6:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 4;
                    }
                    break;
                case 7:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 1;
                    }
                    break;
                case 8:
                    if (zzT(obj, i)) {
                        Object objZzf = zzjp.zzf(obj, j);
                        if (objZzf instanceof zzez) {
                            int i5 = i2 << 3;
                            int i6 = zzfk.zzb;
                            int iZzd = ((zzez) objZzf).zzd();
                            iZzy += zzfk.zzy(i5) + zzfk.zzy(iZzd) + iZzd;
                        } else {
                            iZzy += zzfk.zzy(i2 << 3) + zzfk.zzx((String) objZzf);
                        }
                    }
                    break;
                case 9:
                    if (zzT(obj, i)) {
                        iZzy += zzin.zzn(i2, zzjp.zzf(obj, j), zzC(i));
                    }
                    break;
                case 10:
                    if (zzT(obj, i)) {
                        zzez zzezVar = (zzez) zzjp.zzf(obj, j);
                        int i7 = i2 << 3;
                        int i8 = zzfk.zzb;
                        int iZzd2 = zzezVar.zzd();
                        iZzy += zzfk.zzy(i7) + zzfk.zzy(iZzd2) + iZzd2;
                    }
                    break;
                case 11:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(zzjp.zzc(obj, j));
                    }
                    break;
                case 12:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzu(zzjp.zzc(obj, j));
                    }
                    break;
                case 13:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 4;
                    }
                    break;
                case 14:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 8;
                    }
                    break;
                case 15:
                    if (zzT(obj, i)) {
                        int iZzc = zzjp.zzc(obj, j);
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy((iZzc >> 31) ^ (iZzc + iZzc));
                    }
                    break;
                case 16:
                    if (zzT(obj, i)) {
                        long jZzd = zzjp.zzd(obj, j);
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzz((jZzd + jZzd) ^ (jZzd >> 63));
                    }
                    break;
                case 17:
                    if (zzT(obj, i)) {
                        iZzy += zzfk.zzt(i2, (zzhy) zzjp.zzf(obj, j), zzC(i));
                    }
                    break;
                case 18:
                    iZzy += zzin.zzg(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 19:
                    iZzy += zzin.zze(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 20:
                    iZzy += zzin.zzl(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 21:
                    iZzy += zzin.zzw(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 22:
                    iZzy += zzin.zzj(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 23:
                    iZzy += zzin.zzg(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 24:
                    iZzy += zzin.zze(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 25:
                    iZzy += zzin.zza(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 26:
                    iZzy += zzin.zzt(i2, (List) zzjp.zzf(obj, j));
                    break;
                case 27:
                    iZzy += zzin.zzo(i2, (List) zzjp.zzf(obj, j), zzC(i));
                    break;
                case 28:
                    iZzy += zzin.zzb(i2, (List) zzjp.zzf(obj, j));
                    break;
                case 29:
                    iZzy += zzin.zzu(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 30:
                    iZzy += zzin.zzc(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 31:
                    iZzy += zzin.zze(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 32:
                    iZzy += zzin.zzg(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 33:
                    iZzy += zzin.zzp(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 34:
                    iZzy += zzin.zzr(i2, (List) zzjp.zzf(obj, j), false);
                    break;
                case 35:
                    int iZzh = zzin.zzh((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzh) + iZzh;
                    }
                    break;
                case 36:
                    int iZzf = zzin.zzf((List) unsafe.getObject(obj, j));
                    if (iZzf > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzf) + iZzf;
                    }
                    break;
                case 37:
                    int iZzm = zzin.zzm((List) unsafe.getObject(obj, j));
                    if (iZzm > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzm) + iZzm;
                    }
                    break;
                case 38:
                    int iZzx = zzin.zzx((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzx) + iZzx;
                    }
                    break;
                case 39:
                    int iZzk = zzin.zzk((List) unsafe.getObject(obj, j));
                    if (iZzk > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzk) + iZzk;
                    }
                    break;
                case 40:
                    int iZzh2 = zzin.zzh((List) unsafe.getObject(obj, j));
                    if (iZzh2 > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzh2) + iZzh2;
                    }
                    break;
                case 41:
                    int iZzf2 = zzin.zzf((List) unsafe.getObject(obj, j));
                    if (iZzf2 > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzf2) + iZzf2;
                    }
                    break;
                case 42:
                    List list = (List) unsafe.getObject(obj, j);
                    int i9 = zzin.zza;
                    int size = list.size();
                    if (size > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(size) + size;
                    }
                    break;
                case 43:
                    int iZzv = zzin.zzv((List) unsafe.getObject(obj, j));
                    if (iZzv > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzv) + iZzv;
                    }
                    break;
                case 44:
                    int iZzd3 = zzin.zzd((List) unsafe.getObject(obj, j));
                    if (iZzd3 > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzd3) + iZzd3;
                    }
                    break;
                case 45:
                    int iZzf3 = zzin.zzf((List) unsafe.getObject(obj, j));
                    if (iZzf3 > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzf3) + iZzf3;
                    }
                    break;
                case 46:
                    int iZzh3 = zzin.zzh((List) unsafe.getObject(obj, j));
                    if (iZzh3 > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzh3) + iZzh3;
                    }
                    break;
                case 47:
                    int iZzq = zzin.zzq((List) unsafe.getObject(obj, j));
                    if (iZzq > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzq) + iZzq;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    int iZzs = zzin.zzs((List) unsafe.getObject(obj, j));
                    if (iZzs > 0) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(iZzs) + iZzs;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    iZzy += zzin.zzi(i2, (List) zzjp.zzf(obj, j), zzC(i));
                    break;
                case 50:
                    zzht.zza(i2, zzjp.zzf(obj, j), zzE(i));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 8;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 4;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzz(zzA(obj, j));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzz(zzA(obj, j));
                    }
                    break;
                case 55:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzu(zzq(obj, j));
                    }
                    break;
                case 56:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 8;
                    }
                    break;
                case 57:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 4;
                    }
                    break;
                case 58:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 1;
                    }
                    break;
                case 59:
                    if (zzX(obj, i2, i)) {
                        Object objZzf2 = zzjp.zzf(obj, j);
                        if (objZzf2 instanceof zzez) {
                            int i10 = i2 << 3;
                            int i11 = zzfk.zzb;
                            int iZzd4 = ((zzez) objZzf2).zzd();
                            iZzy += zzfk.zzy(i10) + zzfk.zzy(iZzd4) + iZzd4;
                        } else {
                            iZzy += zzfk.zzy(i2 << 3) + zzfk.zzx((String) objZzf2);
                        }
                    }
                    break;
                case 60:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzin.zzn(i2, zzjp.zzf(obj, j), zzC(i));
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzX(obj, i2, i)) {
                        zzez zzezVar2 = (zzez) zzjp.zzf(obj, j);
                        int i12 = i2 << 3;
                        int i13 = zzfk.zzb;
                        int iZzd5 = zzezVar2.zzd();
                        iZzy += zzfk.zzy(i12) + zzfk.zzy(iZzd5) + iZzd5;
                    }
                    break;
                case 62:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy(zzq(obj, j));
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzu(zzq(obj, j));
                    }
                    break;
                case 64:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 4;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzy(i2 << 3) + 8;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzX(obj, i2, i)) {
                        int iZzq2 = zzq(obj, j);
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzy((iZzq2 >> 31) ^ (iZzq2 + iZzq2));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzX(obj, i2, i)) {
                        long jZzA = zzA(obj, j);
                        iZzy += zzfk.zzy(i2 << 3) + zzfk.zzz((jZzA + jZzA) ^ (jZzA >> 63));
                    }
                    break;
                case 68:
                    if (zzX(obj, i2, i)) {
                        iZzy += zzfk.zzt(i2, (zzhy) zzjp.zzf(obj, j), zzC(i));
                    }
                    break;
            }
        }
        zzjf zzjfVar = this.zzo;
        return iZzy + zzjfVar.zza(zzjfVar.zzd(obj));
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final int zzb(Object obj) {
        int length = this.zzc.length;
        int iFloatToIntBits = 0;
        for (int i = 0; i < length; i += 3) {
            int iZzz = zzz(i);
            int i2 = this.zzc[i];
            long j = 1048575 & iZzz;
            switch (zzy(iZzz)) {
                case 0:
                    long jDoubleToLongBits = Double.doubleToLongBits(zzjp.zza(obj, j));
                    byte[] bArr = zzgw.zzd;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
                    break;
                case 1:
                    iFloatToIntBits = (iFloatToIntBits * 53) + Float.floatToIntBits(zzjp.zzb(obj, j));
                    break;
                case 2:
                    long jZzd = zzjp.zzd(obj, j);
                    byte[] bArr2 = zzgw.zzd;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzd ^ (jZzd >>> 32)));
                    break;
                case 3:
                    long jZzd2 = zzjp.zzd(obj, j);
                    byte[] bArr3 = zzgw.zzd;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzd2 ^ (jZzd2 >>> 32)));
                    break;
                case 4:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzc(obj, j);
                    break;
                case 5:
                    long jZzd3 = zzjp.zzd(obj, j);
                    byte[] bArr4 = zzgw.zzd;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzd3 ^ (jZzd3 >>> 32)));
                    break;
                case 6:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzc(obj, j);
                    break;
                case 7:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzgw.zza(zzjp.zzw(obj, j));
                    break;
                case 8:
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((String) zzjp.zzf(obj, j)).hashCode();
                    break;
                case 9:
                    Object objZzf = zzjp.zzf(obj, j);
                    iFloatToIntBits = (iFloatToIntBits * 53) + (objZzf != null ? objZzf.hashCode() : 37);
                    break;
                case 10:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzf(obj, j).hashCode();
                    break;
                case 11:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzc(obj, j);
                    break;
                case 12:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzc(obj, j);
                    break;
                case 13:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzc(obj, j);
                    break;
                case 14:
                    long jZzd4 = zzjp.zzd(obj, j);
                    byte[] bArr5 = zzgw.zzd;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzd4 ^ (jZzd4 >>> 32)));
                    break;
                case 15:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzc(obj, j);
                    break;
                case 16:
                    long jZzd5 = zzjp.zzd(obj, j);
                    byte[] bArr6 = zzgw.zzd;
                    iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzd5 ^ (jZzd5 >>> 32)));
                    break;
                case 17:
                    Object objZzf2 = zzjp.zzf(obj, j);
                    iFloatToIntBits = (iFloatToIntBits * 53) + (objZzf2 != null ? objZzf2.hashCode() : 37);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzf(obj, j).hashCode();
                    break;
                case 50:
                    iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzf(obj, j).hashCode();
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzX(obj, i2, i)) {
                        long jDoubleToLongBits2 = Double.doubleToLongBits(zzn(obj, j));
                        byte[] bArr7 = zzgw.zzd;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >>> 32)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + Float.floatToIntBits(zzo(obj, j));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (zzX(obj, i2, i)) {
                        long jZzA = zzA(obj, j);
                        byte[] bArr8 = zzgw.zzd;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzA ^ (jZzA >>> 32)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzX(obj, i2, i)) {
                        long jZzA2 = zzA(obj, j);
                        byte[] bArr9 = zzgw.zzd;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzA2 ^ (jZzA2 >>> 32)));
                    }
                    break;
                case 55:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzq(obj, j);
                    }
                    break;
                case 56:
                    if (zzX(obj, i2, i)) {
                        long jZzA3 = zzA(obj, j);
                        byte[] bArr10 = zzgw.zzd;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzA3 ^ (jZzA3 >>> 32)));
                    }
                    break;
                case 57:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzq(obj, j);
                    }
                    break;
                case 58:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzgw.zza(zzY(obj, j));
                    }
                    break;
                case 59:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((String) zzjp.zzf(obj, j)).hashCode();
                    }
                    break;
                case 60:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzf(obj, j).hashCode();
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzf(obj, j).hashCode();
                    }
                    break;
                case 62:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzq(obj, j);
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzq(obj, j);
                    }
                    break;
                case 64:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzq(obj, j);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (zzX(obj, i2, i)) {
                        long jZzA4 = zzA(obj, j);
                        byte[] bArr11 = zzgw.zzd;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzA4 ^ (jZzA4 >>> 32)));
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzq(obj, j);
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzX(obj, i2, i)) {
                        long jZzA5 = zzA(obj, j);
                        byte[] bArr12 = zzgw.zzd;
                        iFloatToIntBits = (iFloatToIntBits * 53) + ((int) (jZzA5 ^ (jZzA5 >>> 32)));
                    }
                    break;
                case 68:
                    if (zzX(obj, i2, i)) {
                        iFloatToIntBits = (iFloatToIntBits * 53) + zzjp.zzf(obj, j).hashCode();
                    }
                    break;
            }
        }
        int iHashCode = (iFloatToIntBits * 53) + this.zzo.zzd(obj).hashCode();
        return this.zzh ? (iHashCode * 53) + this.zzp.zzb(obj).zza.hashCode() : iHashCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:175:0x05dd, code lost:
    
        if (r0 == 1048575) goto L177;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x05df, code lost:
    
        r30.putInt(r12, r0, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x05e5, code lost:
    
        r10 = r8.zzl;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x05ea, code lost:
    
        if (r10 >= r8.zzm) goto L283;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x05ec, code lost:
    
        zzD(r32, r8.zzk[r10], null, r8.zzo, r32);
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x05ff, code lost:
    
        if (r9 != 0) goto L187;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x0603, code lost:
    
        if (r6 != r35) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x060a, code lost:
    
        throw com.google.android.recaptcha.internal.zzgy.zzg();
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x060d, code lost:
    
        if (r6 > r35) goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x060f, code lost:
    
        if (r7 != r9) goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x0611, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x0616, code lost:
    
        throw com.google.android.recaptcha.internal.zzgy.zzg();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zzc(java.lang.Object r32, byte[] r33, int r34, int r35, int r36, com.google.android.recaptcha.internal.zzem r37) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1638
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzib.zzc(java.lang.Object, byte[], int, int, int, com.google.android.recaptcha.internal.zzem):int");
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final Object zze() {
        return ((zzgo) this.zzg).zzs();
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final void zzf(Object obj) {
        if (zzW(obj)) {
            if (obj instanceof zzgo) {
                zzgo zzgoVar = (zzgo) obj;
                zzgoVar.zzD(Integer.MAX_VALUE);
                zzgoVar.zza = 0;
                zzgoVar.zzB();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int iZzz = zzz(i);
                long j = 1048575 & iZzz;
                switch (zzy(iZzz)) {
                    case 9:
                    case 17:
                        if (zzT(obj, i)) {
                            zzC(i).zzf(zzb.getObject(obj, j));
                        }
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                        this.zzn.zzb(obj, j);
                        break;
                    case 50:
                        Object object = zzb.getObject(obj, j);
                        if (object != null) {
                            Unsafe unsafe = zzb;
                            ((zzhs) object).zzc();
                            unsafe.putObject(obj, j, object);
                        }
                        break;
                    case 60:
                    case 68:
                        if (zzX(obj, this.zzc[i], i)) {
                            zzC(i).zzf(zzb.getObject(obj, j));
                        }
                        break;
                }
            }
            this.zzo.zzm(obj);
            if (this.zzh) {
                this.zzp.zzf(obj);
            }
        }
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final void zzg(Object obj, Object obj2) {
        zzI(obj);
        if (obj2 == null) {
            throw null;
        }
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzz = zzz(i);
            int i2 = this.zzc[i];
            long j = 1048575 & iZzz;
            switch (zzy(iZzz)) {
                case 0:
                    if (zzT(obj2, i)) {
                        zzjp.zzo(obj, j, zzjp.zza(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 1:
                    if (zzT(obj2, i)) {
                        zzjp.zzp(obj, j, zzjp.zzb(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 2:
                    if (zzT(obj2, i)) {
                        zzjp.zzr(obj, j, zzjp.zzd(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 3:
                    if (zzT(obj2, i)) {
                        zzjp.zzr(obj, j, zzjp.zzd(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 4:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 5:
                    if (zzT(obj2, i)) {
                        zzjp.zzr(obj, j, zzjp.zzd(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 6:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 7:
                    if (zzT(obj2, i)) {
                        zzjp.zzm(obj, j, zzjp.zzw(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 8:
                    if (zzT(obj2, i)) {
                        zzjp.zzs(obj, j, zzjp.zzf(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 9:
                    zzJ(obj, obj2, i);
                    break;
                case 10:
                    if (zzT(obj2, i)) {
                        zzjp.zzs(obj, j, zzjp.zzf(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 11:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 12:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 13:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 14:
                    if (zzT(obj2, i)) {
                        zzjp.zzr(obj, j, zzjp.zzd(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 15:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 16:
                    if (zzT(obj2, i)) {
                        zzjp.zzr(obj, j, zzjp.zzd(obj2, j));
                        zzM(obj, i);
                    }
                    break;
                case 17:
                    zzJ(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    this.zzn.zzc(obj, obj2, j);
                    break;
                case 50:
                    int i3 = zzin.zza;
                    zzjp.zzs(obj, j, zzht.zzc(zzjp.zzf(obj, j), zzjp.zzf(obj2, j)));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzX(obj2, i2, i)) {
                        zzjp.zzs(obj, j, zzjp.zzf(obj2, j));
                        zzN(obj, i2, i);
                    }
                    break;
                case 60:
                    zzK(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzX(obj2, i2, i)) {
                        zzjp.zzs(obj, j, zzjp.zzf(obj2, j));
                        zzN(obj, i2, i);
                    }
                    break;
                case 68:
                    zzK(obj, obj2, i);
                    break;
            }
        }
        zzin.zzE(this.zzo, obj, obj2);
        if (this.zzh) {
            zzin.zzD(this.zzp, obj, obj2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:194:0x0769 A[Catch: all -> 0x079e, TRY_LEAVE, TryCatch #16 {all -> 0x079e, blocks: (B:192:0x0764, B:194:0x0769), top: B:246:0x0764 }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0793  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x07b4 A[LOOP:2: B:218:0x07b0->B:220:0x07b4, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x07c8  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0774 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:356:? A[SYNTHETIC] */
    @Override // com.google.android.recaptcha.internal.zzil
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzh(java.lang.Object r18, com.google.android.recaptcha.internal.zzik r19, com.google.android.recaptcha.internal.zzfz r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 2140
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzib.zzh(java.lang.Object, com.google.android.recaptcha.internal.zzik, com.google.android.recaptcha.internal.zzfz):void");
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzem zzemVar) throws IOException {
        int i3;
        int iZzk;
        int i4;
        int i5;
        Unsafe unsafe;
        int i6;
        int i7;
        int i8;
        int i9;
        Unsafe unsafe2;
        int i10;
        Unsafe unsafe3;
        Unsafe unsafe4;
        int i11;
        int i12;
        int i13;
        zzib<T> zzibVar = this;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i14 = i2;
        zzem zzemVar2 = zzemVar;
        if (!zzibVar.zzj) {
            zzc(obj, bArr, i, i2, 0, zzemVar);
            return;
        }
        zzI(obj);
        Unsafe unsafe5 = zzb;
        int i15 = -1;
        int i16 = 1048575;
        int iZzi = i;
        int i17 = 1048575;
        int i18 = -1;
        int i19 = 0;
        int i20 = 0;
        while (iZzi < i14) {
            int i21 = iZzi + 1;
            byte b = bArr2[iZzi];
            if (b < 0) {
                iZzk = zzen.zzk(b, bArr2, i21, zzemVar2);
                i3 = zzemVar2.zza;
            } else {
                i3 = b;
                iZzk = i21;
            }
            int i22 = i3 >>> 3;
            int iZzv = i22 > i18 ? zzibVar.zzv(i22, i19 / 3) : zzibVar.zzu(i22);
            if (iZzv == i15) {
                i4 = iZzk;
                i5 = i22;
                unsafe = unsafe5;
                i6 = i15;
                i7 = 0;
            } else {
                int i23 = i3 & 7;
                int[] iArr = zzibVar.zzc;
                int i24 = iArr[iZzv + 1];
                int iZzy = zzy(i24);
                Unsafe unsafe6 = unsafe5;
                long j = i24 & i16;
                if (iZzy <= 17) {
                    int i25 = iArr[iZzv + 2];
                    int i26 = 1 << (i25 >>> 20);
                    int i27 = i25 & 1048575;
                    if (i27 != i17) {
                        if (i17 != 1048575) {
                            i8 = i24;
                            i9 = iZzv;
                            long j2 = i17;
                            unsafe4 = unsafe6;
                            unsafe4.putInt(obj2, j2, i20);
                        } else {
                            i8 = i24;
                            i9 = iZzv;
                            unsafe4 = unsafe6;
                        }
                        if (i27 != 1048575) {
                            i20 = unsafe4.getInt(obj2, i27);
                        }
                        unsafe2 = unsafe4;
                        i17 = i27;
                    } else {
                        i8 = i24;
                        i9 = iZzv;
                        unsafe2 = unsafe6;
                    }
                    switch (iZzy) {
                        case 0:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 1) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                zzjp.zzo(obj2, j, Double.longBitsToDouble(zzen.zzq(bArr2, iZzk)));
                                iZzi = iZzk + 8;
                                i20 |= i26;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                i16 = 1048575;
                                i17 = i10;
                                i18 = i5;
                                i15 = -1;
                                zzibVar = this;
                                i14 = i2;
                            }
                            break;
                        case 1:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 5) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                zzjp.zzp(obj2, j, Float.intBitsToFloat(zzen.zzb(bArr2, iZzk)));
                                iZzi = iZzk + 4;
                                i20 |= i26;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                i16 = 1048575;
                                i17 = i10;
                                i18 = i5;
                                i15 = -1;
                                zzibVar = this;
                                i14 = i2;
                            }
                            break;
                        case 2:
                        case 3:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 0) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                int iZzm = zzen.zzm(bArr2, iZzk, zzemVar2);
                                unsafe3.putLong(obj, j, zzemVar2.zzb);
                                i20 |= i26;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                iZzi = iZzm;
                                i16 = 1048575;
                                i17 = i10;
                                i18 = i5;
                                i15 = -1;
                                zzibVar = this;
                                i14 = i2;
                            }
                            break;
                        case 4:
                        case 11:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 0) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                iZzi = zzen.zzj(bArr2, iZzk, zzemVar2);
                                unsafe3.putInt(obj2, j, zzemVar2.zza);
                                i20 |= i26;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                i16 = 1048575;
                                i17 = i10;
                                i18 = i5;
                                i15 = -1;
                                zzibVar = this;
                                i14 = i2;
                            }
                            break;
                        case 5:
                        case 14:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 1) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                unsafe3.putLong(obj, j, zzen.zzq(bArr2, iZzk));
                                iZzi = iZzk + 8;
                                i20 |= i26;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                i16 = 1048575;
                                i17 = i10;
                                i18 = i5;
                                i15 = -1;
                                zzibVar = this;
                                i14 = i2;
                            }
                            break;
                        case 6:
                        case 13:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 5) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                unsafe3.putInt(obj2, j, zzen.zzb(bArr2, iZzk));
                                iZzi = iZzk + 4;
                                i20 |= i26;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                i16 = 1048575;
                                i17 = i10;
                                i18 = i5;
                                i15 = -1;
                                zzibVar = this;
                                i14 = i2;
                            }
                            break;
                        case 7:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 0) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                iZzi = zzen.zzm(bArr2, iZzk, zzemVar2);
                                zzjp.zzm(obj2, j, zzemVar2.zzb != 0);
                                i20 |= i26;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                i16 = 1048575;
                                i17 = i10;
                                i18 = i5;
                                i15 = -1;
                                zzibVar = this;
                                i14 = i2;
                            }
                            break;
                        case 8:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 2) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                iZzi = (i8 & 536870912) == 0 ? zzen.zzg(bArr2, iZzk, zzemVar2) : zzen.zzh(bArr2, iZzk, zzemVar2);
                                unsafe3.putObject(obj2, j, zzemVar2.zzc);
                                i20 |= i26;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                i16 = 1048575;
                                i17 = i10;
                                i18 = i5;
                                i15 = -1;
                                zzibVar = this;
                                i14 = i2;
                            }
                            break;
                        case 9:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 2) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                Object objZzF = zzF(obj2, i7);
                                iZzi = zzen.zzo(objZzF, zzC(i7), bArr, iZzk, i2, zzemVar);
                                zzO(obj2, i7, objZzF);
                                i20 |= i26;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                i16 = 1048575;
                                i17 = i10;
                                i18 = i5;
                                i15 = -1;
                                zzibVar = this;
                                i14 = i2;
                            }
                            break;
                        case 10:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 2) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                iZzi = zzen.zza(bArr2, iZzk, zzemVar2);
                                unsafe3.putObject(obj2, j, zzemVar2.zzc);
                                i20 |= i26;
                                i14 = i2;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                i17 = i10;
                                i18 = i5;
                                i16 = 1048575;
                                i15 = -1;
                                zzibVar = this;
                            }
                            break;
                        case 12:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 0) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                iZzi = zzen.zzj(bArr2, iZzk, zzemVar2);
                                unsafe3.putInt(obj2, j, zzemVar2.zza);
                                i20 |= i26;
                                i14 = i2;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                i17 = i10;
                                i18 = i5;
                                i16 = 1048575;
                                i15 = -1;
                                zzibVar = this;
                            }
                            break;
                        case 15:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            if (i23 != 0) {
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                iZzi = zzen.zzj(bArr2, iZzk, zzemVar2);
                                unsafe3.putInt(obj2, j, zzff.zzF(zzemVar2.zza));
                                i20 |= i26;
                                i14 = i2;
                                unsafe5 = unsafe3;
                                i19 = i7;
                                i17 = i10;
                                i18 = i5;
                                i16 = 1048575;
                                i15 = -1;
                                zzibVar = this;
                            }
                            break;
                        case 16:
                            if (i23 != 0) {
                                i5 = i22;
                                i7 = i9;
                                i10 = i17;
                                unsafe3 = unsafe2;
                                i4 = iZzk;
                                unsafe = unsafe3;
                                i17 = i10;
                                i6 = -1;
                            } else {
                                int iZzm2 = zzen.zzm(bArr2, iZzk, zzemVar2);
                                unsafe2.putLong(obj, j, zzff.zzG(zzemVar2.zzb));
                                i20 |= i26;
                                unsafe5 = unsafe2;
                                iZzi = iZzm2;
                                i19 = i9;
                                i17 = i17;
                                i18 = i22;
                                i16 = 1048575;
                                i15 = -1;
                                zzibVar = this;
                                i14 = i2;
                            }
                            break;
                        default:
                            i5 = i22;
                            i7 = i9;
                            i10 = i17;
                            unsafe3 = unsafe2;
                            i4 = iZzk;
                            unsafe = unsafe3;
                            i17 = i10;
                            i6 = -1;
                            break;
                    }
                } else {
                    i5 = i22;
                    int i28 = i17;
                    zzib<T> zzibVar2 = zzibVar;
                    i7 = iZzv;
                    if (iZzy == 27) {
                        if (i23 == 2) {
                            zzgv zzgvVarZzd = (zzgv) unsafe6.getObject(obj2, j);
                            if (!zzgvVarZzd.zzc()) {
                                int size = zzgvVarZzd.size();
                                zzgvVarZzd = zzgvVarZzd.zzd(size == 0 ? 10 : size + size);
                                unsafe6.putObject(obj2, j, zzgvVarZzd);
                            }
                            iZzi = zzen.zze(zzibVar2.zzC(i7), i3, bArr, iZzk, i2, zzgvVarZzd, zzemVar);
                            i14 = i2;
                            unsafe5 = unsafe6;
                            i20 = i20;
                            i19 = i7;
                            i16 = 1048575;
                            i17 = i28;
                            i18 = i5;
                            zzibVar = zzibVar2;
                            i15 = -1;
                        } else {
                            i11 = iZzk;
                            unsafe = unsafe6;
                            i12 = i20;
                            i13 = i28;
                            i6 = -1;
                            i4 = i11;
                            i20 = i12;
                            i17 = i13;
                        }
                    } else if (iZzy <= 49) {
                        int i29 = iZzk;
                        int i30 = i20;
                        unsafe = unsafe6;
                        i6 = -1;
                        iZzi = zzt(obj, bArr, iZzk, i2, i3, i5, i23, i7, i24, iZzy, j, zzemVar);
                        if (iZzi != i29) {
                            obj2 = obj;
                            bArr2 = bArr;
                            i14 = i2;
                            zzemVar2 = zzemVar;
                            i19 = i7;
                            i15 = -1;
                            i18 = i5;
                            i20 = i30;
                            i17 = i28;
                            unsafe5 = unsafe;
                            i16 = 1048575;
                            zzibVar = this;
                        } else {
                            i4 = iZzi;
                            i20 = i30;
                            i17 = i28;
                        }
                    } else {
                        i11 = iZzk;
                        i12 = i20;
                        unsafe = unsafe6;
                        i13 = i28;
                        i6 = -1;
                        if (iZzy != 50) {
                            iZzi = zzs(obj, bArr, i11, i2, i3, i5, i23, i24, iZzy, j, i7, zzemVar);
                            if (iZzi != i11) {
                                obj2 = obj;
                                bArr2 = bArr;
                                i14 = i2;
                                zzemVar2 = zzemVar;
                                i19 = i7;
                                i15 = -1;
                                i18 = i5;
                                i20 = i12;
                                i17 = i13;
                                unsafe5 = unsafe;
                                i16 = 1048575;
                                zzibVar = this;
                            } else {
                                i4 = iZzi;
                                i20 = i12;
                                i17 = i13;
                            }
                        } else if (i23 == 2) {
                            iZzi = zzr(obj, bArr, i11, i2, i7, j, zzemVar);
                            if (iZzi != i11) {
                                obj2 = obj;
                                bArr2 = bArr;
                                i14 = i2;
                                zzemVar2 = zzemVar;
                                i19 = i7;
                                i15 = -1;
                                i18 = i5;
                                i20 = i12;
                                i17 = i13;
                                unsafe5 = unsafe;
                                i16 = 1048575;
                                zzibVar = this;
                            } else {
                                i4 = iZzi;
                                i20 = i12;
                                i17 = i13;
                            }
                        } else {
                            i4 = i11;
                            i20 = i12;
                            i17 = i13;
                        }
                    }
                }
            }
            iZzi = zzen.zzi(i3, bArr, i4, i2, zzd(obj), zzemVar);
            obj2 = obj;
            bArr2 = bArr;
            i14 = i2;
            zzemVar2 = zzemVar;
            i19 = i7;
            i15 = i6;
            i18 = i5;
            unsafe5 = unsafe;
            i16 = 1048575;
            zzibVar = this;
        }
        int i31 = i20;
        Unsafe unsafe7 = unsafe5;
        if (i17 != 1048575) {
            unsafe7.putInt(obj, i17, i31);
        }
        if (iZzi != i2) {
            throw zzgy.zzg();
        }
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final boolean zzk(Object obj, Object obj2) {
        boolean zZzY;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzz = zzz(i);
            long j = iZzz & 1048575;
            switch (zzy(iZzz)) {
                case 0:
                    if (!zzR(obj, obj2, i) || Double.doubleToLongBits(zzjp.zza(obj, j)) != Double.doubleToLongBits(zzjp.zza(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 1:
                    if (!zzR(obj, obj2, i) || Float.floatToIntBits(zzjp.zzb(obj, j)) != Float.floatToIntBits(zzjp.zzb(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 2:
                    if (!zzR(obj, obj2, i) || zzjp.zzd(obj, j) != zzjp.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 3:
                    if (!zzR(obj, obj2, i) || zzjp.zzd(obj, j) != zzjp.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 4:
                    if (!zzR(obj, obj2, i) || zzjp.zzc(obj, j) != zzjp.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 5:
                    if (!zzR(obj, obj2, i) || zzjp.zzd(obj, j) != zzjp.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 6:
                    if (!zzR(obj, obj2, i) || zzjp.zzc(obj, j) != zzjp.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 7:
                    if (!zzR(obj, obj2, i) || zzjp.zzw(obj, j) != zzjp.zzw(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 8:
                    if (!zzR(obj, obj2, i) || !zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 9:
                    if (!zzR(obj, obj2, i) || !zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 10:
                    if (!zzR(obj, obj2, i) || !zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 11:
                    if (!zzR(obj, obj2, i) || zzjp.zzc(obj, j) != zzjp.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 12:
                    if (!zzR(obj, obj2, i) || zzjp.zzc(obj, j) != zzjp.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 13:
                    if (!zzR(obj, obj2, i) || zzjp.zzc(obj, j) != zzjp.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 14:
                    if (!zzR(obj, obj2, i) || zzjp.zzd(obj, j) != zzjp.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 15:
                    if (!zzR(obj, obj2, i) || zzjp.zzc(obj, j) != zzjp.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 16:
                    if (!zzR(obj, obj2, i) || zzjp.zzd(obj, j) != zzjp.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 17:
                    if (!zzR(obj, obj2, i) || !zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zZzY = zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j));
                    break;
                case 50:
                    zZzY = zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                case 68:
                    long jZzw = zzw(i) & 1048575;
                    if (zzjp.zzc(obj, jZzw) != zzjp.zzc(obj2, jZzw) || !zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                default:
                    break;
            }
            if (!zZzY) {
                return false;
            }
        }
        if (!this.zzo.zzd(obj).equals(this.zzo.zzd(obj2))) {
            return false;
        }
        if (this.zzh) {
            return this.zzp.zzb(obj).equals(this.zzp.zzb(obj2));
        }
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final boolean zzl(Object obj) {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i4 < this.zzl) {
            int i6 = this.zzk[i4];
            int i7 = this.zzc[i6];
            int iZzz = zzz(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 == i5) {
                i = i5;
                i2 = i3;
            } else if (i9 != 1048575) {
                i2 = zzb.getInt(obj, i9);
                i = i9;
            } else {
                i2 = i3;
                i = i9;
            }
            if ((268435456 & iZzz) != 0 && !zzU(obj, i6, i, i2, i10)) {
                return false;
            }
            switch (zzy(iZzz)) {
                case 9:
                case 17:
                    if (zzU(obj, i6, i, i2, i10) && !zzV(obj, iZzz, zzC(i6))) {
                        return false;
                    }
                    break;
                    break;
                case 27:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    List list = (List) zzjp.zzf(obj, iZzz & 1048575);
                    if (list.isEmpty()) {
                        continue;
                    } else {
                        zzil zzilVarZzC = zzC(i6);
                        for (int i11 = 0; i11 < list.size(); i11++) {
                            if (!zzilVarZzC.zzl(list.get(i11))) {
                                return false;
                            }
                        }
                    }
                    break;
                case 50:
                    if (!((zzhs) zzjp.zzf(obj, iZzz & 1048575)).isEmpty()) {
                        throw null;
                    }
                    break;
                    break;
                case 60:
                case 68:
                    if (zzX(obj, i7, i6) && !zzV(obj, iZzz, zzC(i6))) {
                        return false;
                    }
                    break;
                    break;
            }
            i4++;
            i5 = i;
            i3 = i2;
        }
        return !this.zzh || this.zzp.zzb(obj).zzk();
    }

    /* JADX WARN: Removed duplicated region for block: B:177:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    @Override // com.google.android.recaptcha.internal.zzil
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzj(java.lang.Object r18, com.google.android.recaptcha.internal.zzjx r19) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 3070
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzib.zzj(java.lang.Object, com.google.android.recaptcha.internal.zzjx):void");
    }
}
