package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzin {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzjf zzc;
    private static final zzjf zzd;
    private static final zzjf zze;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            cls = null;
        }
        zzb = cls;
        zzc = zzZ(false);
        zzd = zzZ(true);
        zze = new zzjh();
    }

    public static zzjf zzA() {
        return zze;
    }

    static Object zzB(Object obj, int i, List list, zzgs zzgsVar, Object obj2, zzjf zzjfVar) {
        if (zzgsVar == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = ((Integer) list.get(i3)).intValue();
                if (zzgsVar.zza()) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    obj2 = zzC(obj, i, iIntValue, obj2, zzjfVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = ((Integer) it.next()).intValue();
                if (!zzgsVar.zza()) {
                    obj2 = zzC(obj, i, iIntValue2, obj2, zzjfVar);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    static Object zzC(Object obj, int i, int i2, Object obj2, zzjf zzjfVar) {
        if (obj2 == null) {
            obj2 = zzjfVar.zzc(obj);
        }
        zzjfVar.zzl(obj2, i, i2);
        return obj2;
    }

    static void zzD(zzga zzgaVar, Object obj, Object obj2) {
        zzge zzgeVarZzb = zzgaVar.zzb(obj2);
        if (zzgeVarZzb.zza.isEmpty()) {
            return;
        }
        zzgaVar.zzc(obj).zzh(zzgeVarZzb);
    }

    static void zzE(zzjf zzjfVar, Object obj, Object obj2) {
        zzjfVar.zzo(obj, zzjfVar.zze(zzjfVar.zzd(obj), zzjfVar.zzd(obj2)));
    }

    public static void zzF(Class cls) {
        Class cls2;
        if (!zzgo.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzG(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzc(i, list, z);
    }

    public static void zzH(int i, List list, zzjx zzjxVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zze(i, list);
    }

    public static void zzI(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzg(i, list, z);
    }

    public static void zzJ(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzj(i, list, z);
    }

    public static void zzK(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzl(i, list, z);
    }

    public static void zzL(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzn(i, list, z);
    }

    public static void zzM(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzp(i, list, z);
    }

    public static void zzN(int i, List list, zzjx zzjxVar, zzil zzilVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzfl) zzjxVar).zzq(i, list.get(i2), zzilVar);
        }
    }

    public static void zzO(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzs(i, list, z);
    }

    public static void zzP(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzu(i, list, z);
    }

    public static void zzQ(int i, List list, zzjx zzjxVar, zzil zzilVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzfl) zzjxVar).zzv(i, list.get(i2), zzilVar);
        }
    }

    public static void zzR(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzy(i, list, z);
    }

    public static void zzS(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzA(i, list, z);
    }

    public static void zzT(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzC(i, list, z);
    }

    public static void zzU(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzE(i, list, z);
    }

    public static void zzV(int i, List list, zzjx zzjxVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzH(i, list);
    }

    public static void zzW(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzJ(i, list, z);
    }

    public static void zzX(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzL(i, list, z);
    }

    static boolean zzY(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    private static zzjf zzZ(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzjf) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable th2) {
            return null;
        }
    }

    static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzfk.zzy(i << 3) + 1);
    }

    static int zzb(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzy = size * zzfk.zzy(i << 3);
        for (int i2 = 0; i2 < list.size(); i2++) {
            int iZzd = ((zzez) list.get(i2)).zzd();
            iZzy += zzfk.zzy(iZzd) + iZzd;
        }
        return iZzy;
    }

    static int zzc(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzfk.zzy(i << 3));
    }

    static int zzd(List list) {
        int iZzu;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgp) {
            zzgp zzgpVar = (zzgp) list;
            iZzu = 0;
            while (i < size) {
                iZzu += zzfk.zzu(zzgpVar.zze(i));
                i++;
            }
        } else {
            iZzu = 0;
            while (i < size) {
                iZzu += zzfk.zzu(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzu;
    }

    static int zze(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzfk.zzy(i << 3) + 4);
    }

    static int zzf(List list) {
        return list.size() * 4;
    }

    static int zzg(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzfk.zzy(i << 3) + 8);
    }

    static int zzh(List list) {
        return list.size() * 8;
    }

    static int zzi(int i, List list, zzil zzilVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzt = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzt += zzfk.zzt(i, (zzhy) list.get(i2), zzilVar);
        }
        return iZzt;
    }

    static int zzj(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzk(list) + (size * zzfk.zzy(i << 3));
    }

    static int zzk(List list) {
        int iZzu;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgp) {
            zzgp zzgpVar = (zzgp) list;
            iZzu = 0;
            while (i < size) {
                iZzu += zzfk.zzu(zzgpVar.zze(i));
                i++;
            }
        } else {
            iZzu = 0;
            while (i < size) {
                iZzu += zzfk.zzu(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzu;
    }

    static int zzl(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzm(list) + (list.size() * zzfk.zzy(i << 3));
    }

    static int zzm(List list) {
        int iZzz;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhn) {
            zzhn zzhnVar = (zzhn) list;
            iZzz = 0;
            while (i < size) {
                iZzz += zzfk.zzz(zzhnVar.zze(i));
                i++;
            }
        } else {
            iZzz = 0;
            while (i < size) {
                iZzz += zzfk.zzz(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzz;
    }

    static int zzn(int i, Object obj, zzil zzilVar) {
        if (!(obj instanceof zzhe)) {
            return zzfk.zzy(i << 3) + zzfk.zzw((zzhy) obj, zzilVar);
        }
        int i2 = zzfk.zzb;
        int iZza = ((zzhe) obj).zza();
        return zzfk.zzy(i << 3) + zzfk.zzy(iZza) + iZza;
    }

    static int zzo(int i, List list, zzil zzilVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzy = zzfk.zzy(i << 3) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzhe) {
                int iZza = ((zzhe) obj).zza();
                iZzy += zzfk.zzy(iZza) + iZza;
            } else {
                iZzy += zzfk.zzw((zzhy) obj, zzilVar);
            }
        }
        return iZzy;
    }

    static int zzp(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzq(list) + (size * zzfk.zzy(i << 3));
    }

    static int zzq(List list) {
        int iZzy;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgp) {
            zzgp zzgpVar = (zzgp) list;
            iZzy = 0;
            while (i < size) {
                int iZze = zzgpVar.zze(i);
                iZzy += zzfk.zzy((iZze >> 31) ^ (iZze + iZze));
                i++;
            }
        } else {
            iZzy = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzy += zzfk.zzy((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i++;
            }
        }
        return iZzy;
    }

    static int zzr(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzs(list) + (size * zzfk.zzy(i << 3));
    }

    static int zzs(List list) {
        int iZzz;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhn) {
            zzhn zzhnVar = (zzhn) list;
            iZzz = 0;
            while (i < size) {
                long jZze = zzhnVar.zze(i);
                iZzz += zzfk.zzz((jZze >> 63) ^ (jZze + jZze));
                i++;
            }
        } else {
            iZzz = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzz += zzfk.zzz((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i++;
            }
        }
        return iZzz;
    }

    static int zzt(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int i3 = zzfk.zzb;
        boolean z = list instanceof zzhg;
        int iZzy = zzfk.zzy(i << 3) * size;
        if (z) {
            zzhg zzhgVar = (zzhg) list;
            while (i2 < size) {
                Object objZzf = zzhgVar.zzf(i2);
                if (objZzf instanceof zzez) {
                    int iZzd = ((zzez) objZzf).zzd();
                    iZzy += zzfk.zzy(iZzd) + iZzd;
                } else {
                    iZzy += zzfk.zzx((String) objZzf);
                }
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzez) {
                    int iZzd2 = ((zzez) obj).zzd();
                    iZzy += zzfk.zzy(iZzd2) + iZzd2;
                } else {
                    iZzy += zzfk.zzx((String) obj);
                }
                i2++;
            }
        }
        return iZzy;
    }

    static int zzu(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzv(list) + (size * zzfk.zzy(i << 3));
    }

    static int zzv(List list) {
        int iZzy;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgp) {
            zzgp zzgpVar = (zzgp) list;
            iZzy = 0;
            while (i < size) {
                iZzy += zzfk.zzy(zzgpVar.zze(i));
                i++;
            }
        } else {
            iZzy = 0;
            while (i < size) {
                iZzy += zzfk.zzy(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzy;
    }

    static int zzw(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzx(list) + (size * zzfk.zzy(i << 3));
    }

    static int zzx(List list) {
        int iZzz;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhn) {
            zzhn zzhnVar = (zzhn) list;
            iZzz = 0;
            while (i < size) {
                iZzz += zzfk.zzz(zzhnVar.zze(i));
                i++;
            }
        } else {
            iZzz = 0;
            while (i < size) {
                iZzz += zzfk.zzz(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzz;
    }

    public static zzjf zzy() {
        return zzc;
    }

    public static zzjf zzz() {
        return zzd;
    }
}
