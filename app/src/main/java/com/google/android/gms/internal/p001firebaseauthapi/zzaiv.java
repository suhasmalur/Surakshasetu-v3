package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzaix;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaiv<T extends zzaix<T>> {
    private static final zzaiv zzb = new zzaiv(true);
    final zzalg<T, Object> zza;
    private boolean zzc;
    private boolean zzd;

    static int zza(zzamr zzamrVar, int i, Object obj) {
        int iZzi = zzaik.zzi(i);
        if (zzamrVar == zzamr.zzj) {
            zzajf.zza((zzakn) obj);
            iZzi <<= 1;
        }
        return iZzi + zza(zzamrVar, obj);
    }

    private static int zza(zzamr zzamrVar, Object obj) {
        switch (zzaiu.zzb[zzamrVar.ordinal()]) {
            case 1:
                return zzaik.zza(((Double) obj).doubleValue());
            case 2:
                return zzaik.zza(((Float) obj).floatValue());
            case 3:
                return zzaik.zzd(((Long) obj).longValue());
            case 4:
                return zzaik.zzg(((Long) obj).longValue());
            case 5:
                return zzaik.zze(((Integer) obj).intValue());
            case 6:
                return zzaik.zzc(((Long) obj).longValue());
            case 7:
                return zzaik.zzd(((Integer) obj).intValue());
            case 8:
                return zzaik.zza(((Boolean) obj).booleanValue());
            case 9:
                return zzaik.zza((zzakn) obj);
            case 10:
                if (obj instanceof zzajn) {
                    return zzaik.zza((zzajn) obj);
                }
                return zzaik.zzb((zzakn) obj);
            case 11:
                if (obj instanceof zzahp) {
                    return zzaik.zza((zzahp) obj);
                }
                return zzaik.zza((String) obj);
            case 12:
                if (obj instanceof zzahp) {
                    return zzaik.zza((zzahp) obj);
                }
                return zzaik.zza((byte[]) obj);
            case 13:
                return zzaik.zzj(((Integer) obj).intValue());
            case 14:
                return zzaik.zzg(((Integer) obj).intValue());
            case 15:
                return zzaik.zze(((Long) obj).longValue());
            case 16:
                return zzaik.zzh(((Integer) obj).intValue());
            case 17:
                return zzaik.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzaje) {
                    return zzaik.zzc(((zzaje) obj).zza());
                }
                return zzaik.zzc(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zza(zzaix<?> zzaixVar, Object obj) {
        zzamr zzamrVarZzb = zzaixVar.zzb();
        int iZza = zzaixVar.zza();
        if (zzaixVar.zze()) {
            List list = (List) obj;
            int iZza2 = 0;
            if (zzaixVar.zzd()) {
                if (list.isEmpty()) {
                    return 0;
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    iZza2 += zza(zzamrVarZzb, it.next());
                }
                return zzaik.zzi(iZza) + iZza2 + zzaik.zzj(iZza2);
            }
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                iZza2 += zza(zzamrVarZzb, iZza, it2.next());
            }
            return iZza2;
        }
        return zza(zzamrVarZzb, iZza, obj);
    }

    public final int zza() {
        int iZza = 0;
        for (int i = 0; i < this.zza.zza(); i++) {
            iZza += zza((Map.Entry) this.zza.zzb(i));
        }
        Iterator it = this.zza.zzb().iterator();
        while (it.hasNext()) {
            iZza += zza((Map.Entry) it.next());
        }
        return iZza;
    }

    private static int zza(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzc() == zzanb.MESSAGE && !key.zze() && !key.zzd()) {
            if (value instanceof zzajn) {
                return zzaik.zza(entry.getKey().zza(), (zzajn) value);
            }
            return zzaik.zza(entry.getKey().zza(), (zzakn) value);
        }
        return zza((zzaix<?>) key, value);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public static <T extends zzaix<T>> zzaiv<T> zzb() {
        return zzb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzaiv zzaivVar = new zzaiv();
        for (int i = 0; i < this.zza.zza(); i++) {
            Map.Entry<K, Object> entryZzb = this.zza.zzb(i);
            zzaivVar.zzb((zzaix) entryZzb.getKey(), entryZzb.getValue());
        }
        Iterator it = this.zza.zzb().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zzaivVar.zzb((zzaix) entry.getKey(), entry.getValue());
        }
        zzaivVar.zzd = this.zzd;
        return zzaivVar;
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzaks) {
            return ((zzaks) obj).clone();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
        return obj;
    }

    private final Object zza(T t) {
        Object obj = this.zza.get(t);
        if (obj instanceof zzajn) {
            return zzajn.zza();
        }
        return obj;
    }

    final Iterator<Map.Entry<T, Object>> zzc() {
        if (this.zzd) {
            return new zzajo(this.zza.zzc().iterator());
        }
        return this.zza.zzc().iterator();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        if (this.zzd) {
            return new zzajo(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    private zzaiv() {
        this.zza = zzalg.zza(16);
    }

    private zzaiv(zzalg<T, Object> zzalgVar) {
        this.zza = zzalgVar;
        zze();
    }

    private zzaiv(boolean z) {
        this(zzalg.zza(0));
        zze();
    }

    public final void zze() {
        if (this.zzc) {
            return;
        }
        for (int i = 0; i < this.zza.zza(); i++) {
            Map.Entry<K, Object> entryZzb = this.zza.zzb(i);
            if (entryZzb.getValue() instanceof zzajc) {
                ((zzajc) entryZzb.getValue()).zzs();
            }
        }
        this.zza.zzd();
        this.zzc = true;
    }

    public final void zza(zzaiv<T> zzaivVar) {
        for (int i = 0; i < zzaivVar.zza.zza(); i++) {
            zzb((Map.Entry) zzaivVar.zza.zzb(i));
        }
        Iterator it = zzaivVar.zza.zzb().iterator();
        while (it.hasNext()) {
            zzb((Map.Entry) it.next());
        }
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        zzakn zzaknVarZzf;
        T key = entry.getKey();
        Object value = entry.getValue();
        boolean z = value instanceof zzajn;
        if (key.zze()) {
            if (z) {
                throw new IllegalStateException("Lazy fields can not be repeated");
            }
            Object objZza = zza((zzaix) key);
            if (objZza == null) {
                objZza = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objZza).add(zza(it.next()));
            }
            this.zza.put(key, objZza);
            return;
        }
        if (key.zzc() == zzanb.MESSAGE) {
            Object objZza2 = zza((zzaix) key);
            if (objZza2 == null) {
                this.zza.put(key, zza(value));
                if (z) {
                    this.zzd = true;
                    return;
                }
                return;
            }
            if (z) {
                value = zzajn.zza();
            }
            if (objZza2 instanceof zzaks) {
                zzaknVarZzf = key.zza((zzaks) objZza2, (zzaks) value);
            } else {
                zzaknVarZzf = key.zza(((zzakn) objZza2).zzr(), (zzakn) value).zzf();
            }
            this.zza.put(key, zzaknVarZzf);
            return;
        }
        if (z) {
            throw new IllegalStateException("Lazy fields must be message-valued");
        }
        this.zza.put(key, zza(value));
    }

    private final void zzb(T t, Object obj) {
        if (t.zze()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList.get(i);
                i++;
                zzc(t, obj2);
            }
            obj = arrayList;
        } else {
            zzc(t, obj);
        }
        if (obj instanceof zzajn) {
            this.zzd = true;
        }
        this.zza.put(t, obj);
    }

    private static void zzc(T t, Object obj) {
        zzamr zzamrVarZzb = t.zzb();
        zzajf.zza(obj);
        boolean z = true;
        switch (zzaiu.zza[zzamrVarZzb.zzb().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if (!(obj instanceof zzahp) && !(obj instanceof byte[])) {
                    z = false;
                }
                break;
            case 8:
                if (!(obj instanceof Integer) && !(obj instanceof zzaje)) {
                    z = false;
                }
                break;
            case 9:
                if (!(obj instanceof zzakn) && !(obj instanceof zzajn)) {
                    z = false;
                }
                break;
            default:
                z = false;
                break;
        }
        if (!z) {
            throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(t.zza()), t.zzb().zzb(), obj.getClass().getName()));
        }
    }

    static void zza(zzaik zzaikVar, zzamr zzamrVar, int i, Object obj) throws IOException {
        if (zzamrVar == zzamr.zzj) {
            zzakn zzaknVar = (zzakn) obj;
            zzajf.zza(zzaknVar);
            zzaikVar.zzk(i, 3);
            zzaknVar.zza(zzaikVar);
            zzaikVar.zzk(i, 4);
        }
        zzaikVar.zzk(i, zzamrVar.zza());
        switch (zzaiu.zzb[zzamrVar.ordinal()]) {
            case 1:
                zzaikVar.zzb(((Double) obj).doubleValue());
                break;
            case 2:
                zzaikVar.zzb(((Float) obj).floatValue());
                break;
            case 3:
                zzaikVar.zzj(((Long) obj).longValue());
                break;
            case 4:
                zzaikVar.zzj(((Long) obj).longValue());
                break;
            case 5:
                zzaikVar.zzl(((Integer) obj).intValue());
                break;
            case 6:
                zzaikVar.zzh(((Long) obj).longValue());
                break;
            case 7:
                zzaikVar.zzk(((Integer) obj).intValue());
                break;
            case 8:
                zzaikVar.zzb(((Boolean) obj).booleanValue());
                break;
            case 9:
                ((zzakn) obj).zza(zzaikVar);
                break;
            case 10:
                zzaikVar.zzc((zzakn) obj);
                break;
            case 11:
                if (obj instanceof zzahp) {
                    zzaikVar.zzb((zzahp) obj);
                } else {
                    zzaikVar.zzb((String) obj);
                }
                break;
            case 12:
                if (obj instanceof zzahp) {
                    zzaikVar.zzb((zzahp) obj);
                } else {
                    byte[] bArr = (byte[]) obj;
                    zzaikVar.zzb(bArr, 0, bArr.length);
                }
                break;
            case 13:
                zzaikVar.zzn(((Integer) obj).intValue());
                break;
            case 14:
                zzaikVar.zzk(((Integer) obj).intValue());
                break;
            case 15:
                zzaikVar.zzh(((Long) obj).longValue());
                break;
            case 16:
                zzaikVar.zzm(((Integer) obj).intValue());
                break;
            case 17:
                zzaikVar.zzi(((Long) obj).longValue());
                break;
            case 18:
                if (obj instanceof zzaje) {
                    zzaikVar.zzl(((zzaje) obj).zza());
                } else {
                    zzaikVar.zzl(((Integer) obj).intValue());
                }
                break;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaiv)) {
            return false;
        }
        return this.zza.equals(((zzaiv) obj).zza);
    }

    public final boolean zzf() {
        return this.zzc;
    }

    public final boolean zzg() {
        for (int i = 0; i < this.zza.zza(); i++) {
            if (!zzc(this.zza.zzb(i))) {
                return false;
            }
        }
        Iterator it = this.zza.zzb().iterator();
        while (it.hasNext()) {
            if (!zzc((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzaix<T>> boolean zzc(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zzc() == zzanb.MESSAGE) {
            if (key.zze()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (!zzb(it.next())) {
                        return false;
                    }
                }
                return true;
            }
            return zzb(entry.getValue());
        }
        return true;
    }

    private static boolean zzb(Object obj) {
        if (obj instanceof zzakp) {
            return ((zzakp) obj).zzk();
        }
        if (obj instanceof zzajn) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }
}
