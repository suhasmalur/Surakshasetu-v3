package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzalh {
    private static final Class<?> zza = zzd();
    private static final zzame<?, ?> zzb = zzc();
    private static final zzame<?, ?> zzc = new zzamg();

    static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzaik.zza(i, true);
    }

    static int zza(List<?> list) {
        return list.size();
    }

    static int zza(int i, List<zzahp> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzi = size * zzaik.zzi(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzi += zzaik.zza(list.get(i2));
        }
        return iZzi;
    }

    static int zzb(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzaik.zzi(i));
    }

    static int zzb(List<Integer> list) {
        int iZzc;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajd) {
            zzajd zzajdVar = (zzajd) list;
            iZzc = 0;
            while (i < size) {
                iZzc += zzaik.zzc(zzajdVar.zzb(i));
                i++;
            }
        } else {
            iZzc = 0;
            while (i < size) {
                iZzc += zzaik.zzc(list.get(i).intValue());
                i++;
            }
        }
        return iZzc;
    }

    static int zzc(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzaik.zzc(i, 0);
    }

    static int zzc(List<?> list) {
        return list.size() << 2;
    }

    static int zzd(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzaik.zza(i, 0L);
    }

    static int zzd(List<?> list) {
        return list.size() << 3;
    }

    static int zza(int i, List<zzakn> list, zzalf zzalfVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZza = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZza += zzaik.zza(i, list.get(i2), zzalfVar);
        }
        return iZza;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzaik.zzi(i));
    }

    static int zze(List<Integer> list) {
        int iZze;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajd) {
            zzajd zzajdVar = (zzajd) list;
            iZze = 0;
            while (i < size) {
                iZze += zzaik.zze(zzajdVar.zzb(i));
                i++;
            }
        } else {
            iZze = 0;
            while (i < size) {
                iZze += zzaik.zze(list.get(i).intValue());
                i++;
            }
        }
        return iZze;
    }

    static int zzf(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzf(list) + (list.size() * zzaik.zzi(i));
    }

    static int zzf(List<Long> list) {
        int iZzd;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajy) {
            zzajy zzajyVar = (zzajy) list;
            iZzd = 0;
            while (i < size) {
                iZzd += zzaik.zzd(zzajyVar.zzb(i));
                i++;
            }
        } else {
            iZzd = 0;
            while (i < size) {
                iZzd += zzaik.zzd(list.get(i).longValue());
                i++;
            }
        }
        return iZzd;
    }

    static int zza(int i, Object obj, zzalf zzalfVar) {
        if (obj instanceof zzajr) {
            return zzaik.zzb(i, (zzajr) obj);
        }
        return zzaik.zzb(i, (zzakn) obj, zzalfVar);
    }

    static int zzb(int i, List<?> list, zzalf zzalfVar) {
        int iZza;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzi = zzaik.zzi(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzajr) {
                iZza = zzaik.zza((zzajr) obj);
            } else {
                iZza = zzaik.zza((zzakn) obj, zzalfVar);
            }
            iZzi += iZza;
        }
        return iZzi;
    }

    static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzaik.zzi(i));
    }

    static int zzg(List<Integer> list) {
        int iZzh;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajd) {
            zzajd zzajdVar = (zzajd) list;
            iZzh = 0;
            while (i < size) {
                iZzh += zzaik.zzh(zzajdVar.zzb(i));
                i++;
            }
        } else {
            iZzh = 0;
            while (i < size) {
                iZzh += zzaik.zzh(list.get(i).intValue());
                i++;
            }
        }
        return iZzh;
    }

    static int zzh(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzh(list) + (size * zzaik.zzi(i));
    }

    static int zzh(List<Long> list) {
        int iZzf;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajy) {
            zzajy zzajyVar = (zzajy) list;
            iZzf = 0;
            while (i < size) {
                iZzf += zzaik.zzf(zzajyVar.zzb(i));
                i++;
            }
        } else {
            iZzf = 0;
            while (i < size) {
                iZzf += zzaik.zzf(list.get(i).longValue());
                i++;
            }
        }
        return iZzf;
    }

    static int zzb(int i, List<?> list) {
        int iZza;
        int iZza2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzi = zzaik.zzi(i) * size;
        if (list instanceof zzajt) {
            zzajt zzajtVar = (zzajt) list;
            while (i2 < size) {
                Object objZzb = zzajtVar.zzb(i2);
                if (objZzb instanceof zzahp) {
                    iZza2 = zzaik.zza((zzahp) objZzb);
                } else {
                    iZza2 = zzaik.zza((String) objZzb);
                }
                iZzi += iZza2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzahp) {
                    iZza = zzaik.zza((zzahp) obj);
                } else {
                    iZza = zzaik.zza((String) obj);
                }
                iZzi += iZza;
                i2++;
            }
        }
        return iZzi;
    }

    static int zzi(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzi(list) + (size * zzaik.zzi(i));
    }

    static int zzi(List<Integer> list) {
        int iZzj;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajd) {
            zzajd zzajdVar = (zzajd) list;
            iZzj = 0;
            while (i < size) {
                iZzj += zzaik.zzj(zzajdVar.zzb(i));
                i++;
            }
        } else {
            iZzj = 0;
            while (i < size) {
                iZzj += zzaik.zzj(list.get(i).intValue());
                i++;
            }
        }
        return iZzj;
    }

    static int zzj(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzj(list) + (size * zzaik.zzi(i));
    }

    static int zzj(List<Long> list) {
        int iZzg;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajy) {
            zzajy zzajyVar = (zzajy) list;
            iZzg = 0;
            while (i < size) {
                iZzg += zzaik.zzg(zzajyVar.zzb(i));
                i++;
            }
        } else {
            iZzg = 0;
            while (i < size) {
                iZzg += zzaik.zzg(list.get(i).longValue());
                i++;
            }
        }
        return iZzg;
    }

    private static zzame<?, ?> zzc() {
        try {
            Class<?> clsZze = zze();
            if (clsZze == null) {
                return null;
            }
            return (zzame) clsZze.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public static zzame<?, ?> zza() {
        return zzb;
    }

    public static zzame<?, ?> zzb() {
        return zzc;
    }

    private static Class<?> zzd() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zze() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static <UT, UB> UB zza(Object obj, int i, List<Integer> list, zzajg zzajgVar, UB ub, zzame<UT, UB> zzameVar) {
        if (zzajgVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (zzajgVar.zza(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub = (UB) zza(obj, i, iIntValue, ub, zzameVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (!zzajgVar.zza(iIntValue2)) {
                    ub = (UB) zza(obj, i, iIntValue2, ub, zzameVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zza(Object obj, int i, int i2, UB ub, zzame<UT, UB> zzameVar) {
        if (ub == null) {
            ub = zzameVar.zzc(obj);
        }
        zzameVar.zzb(ub, i, i2);
        return ub;
    }

    static <T, FT extends zzaix<FT>> void zza(zzaiq<FT> zzaiqVar, T t, T t2) {
        zzaiv<T> zzaivVarZza = zzaiqVar.zza(t2);
        if (!zzaivVarZza.zza.isEmpty()) {
            zzaiqVar.zzb(t).zza((zzaiv) zzaivVarZza);
        }
    }

    static <T> void zza(zzakg zzakgVar, T t, T t2, long j) {
        zzamk.zza(t, j, zzakgVar.zza(zzamk.zze(t, j), zzamk.zze(t2, j)));
    }

    static <T, UT, UB> void zza(zzame<UT, UB> zzameVar, T t, T t2) {
        zzameVar.zzc(t, zzameVar.zza(zzameVar.zzd(t), zzameVar.zzd(t2)));
    }

    public static void zza(Class<?> cls) {
        if (!zzajc.class.isAssignableFrom(cls) && zza != null && !zza.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Boolean> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zza(i, list, z);
        }
    }

    public static void zza(int i, List<zzahp> list, zzana zzanaVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zza(i, list);
        }
    }

    public static void zzb(int i, List<Double> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzb(i, list, z);
        }
    }

    public static void zzc(int i, List<Integer> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Integer> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zze(i, list, z);
        }
    }

    public static void zzf(int i, List<Float> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzf(i, list, z);
        }
    }

    public static void zza(int i, List<?> list, zzana zzanaVar, zzalf zzalfVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zza(i, list, zzalfVar);
        }
    }

    public static void zzg(int i, List<Integer> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzg(i, list, z);
        }
    }

    public static void zzh(int i, List<Long> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzh(i, list, z);
        }
    }

    public static void zzb(int i, List<?> list, zzana zzanaVar, zzalf zzalfVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzb(i, list, zzalfVar);
        }
    }

    public static void zzi(int i, List<Integer> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzi(i, list, z);
        }
    }

    public static void zzj(int i, List<Long> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzj(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzk(i, list, z);
        }
    }

    public static void zzl(int i, List<Long> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzl(i, list, z);
        }
    }

    public static void zzb(int i, List<String> list, zzana zzanaVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzb(i, list);
        }
    }

    public static void zzm(int i, List<Integer> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzm(i, list, z);
        }
    }

    public static void zzn(int i, List<Long> list, zzana zzanaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanaVar.zzn(i, list, z);
        }
    }

    static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
