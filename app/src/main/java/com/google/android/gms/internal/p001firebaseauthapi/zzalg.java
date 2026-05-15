package com.google.android.gms.internal.p001firebaseauthapi;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
class zzalg<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private final int zza;
    private List<zzalr> zzb;
    private Map<K, V> zzc;
    private boolean zzd;
    private volatile zzals zze;
    private Map<K, V> zzf;
    private volatile zzalk zzg;

    private final int zza(K k) {
        int size = this.zzb.size() - 1;
        if (size >= 0) {
            int iCompareTo = k.compareTo((Comparable) this.zzb.get(size).getKey());
            if (iCompareTo > 0) {
                return -(size + 2);
            }
            if (iCompareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int iCompareTo2 = k.compareTo((Comparable) this.zzb.get(i2).getKey());
            if (iCompareTo2 < 0) {
                size = i2 - 1;
            } else {
                if (iCompareTo2 <= 0) {
                    return i2;
                }
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    public final int zza() {
        return this.zzb.size();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int iZza = zza();
        int iHashCode = 0;
        for (int i = 0; i < iZza; i++) {
            iHashCode += this.zzb.get(i).hashCode();
        }
        if (this.zzc.size() > 0) {
            return iHashCode + this.zzc.hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzb.size() + this.zzc.size();
    }

    static <FieldDescriptorType extends zzaix<FieldDescriptorType>> zzalg<FieldDescriptorType, Object> zza(int i) {
        return new zzalj(i);
    }

    public final Iterable<Map.Entry<K, V>> zzb() {
        if (this.zzc.isEmpty()) {
            return zzalm.zza();
        }
        return this.zzc.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iZza = zza(comparable);
        if (iZza >= 0) {
            return (V) this.zzb.get(iZza).getValue();
        }
        return this.zzc.get(comparable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final V put(K k, V v) {
        zzg();
        int iZza = zza(k);
        if (iZza >= 0) {
            return (V) this.zzb.get(iZza).setValue(v);
        }
        zzg();
        if (this.zzb.isEmpty() && !(this.zzb instanceof ArrayList)) {
            this.zzb = new ArrayList(this.zza);
        }
        int i = -(iZza + 1);
        if (i >= this.zza) {
            return zzf().put(k, v);
        }
        if (this.zzb.size() == this.zza) {
            zzalr zzalrVarRemove = this.zzb.remove(this.zza - 1);
            zzf().put((Comparable) zzalrVarRemove.getKey(), zzalrVarRemove.getValue());
        }
        this.zzb.add(i, new zzalr(this, k, v));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zzg();
        Comparable comparable = (Comparable) obj;
        int iZza = zza(comparable);
        if (iZza >= 0) {
            return zzc(iZza);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzc(int i) {
        zzg();
        V v = (V) this.zzb.remove(i).getValue();
        if (!this.zzc.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = zzf().entrySet().iterator();
            this.zzb.add(new zzalr(this, it.next()));
            it.remove();
        }
        return v;
    }

    public final Map.Entry<K, V> zzb(int i) {
        return this.zzb.get(i);
    }

    final Set<Map.Entry<K, V>> zzc() {
        if (this.zzg == null) {
            this.zzg = new zzalk(this);
        }
        return this.zzg;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zze == null) {
            this.zze = new zzals(this);
        }
        return this.zze;
    }

    private final SortedMap<K, V> zzf() {
        zzg();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            this.zzc = new TreeMap();
            this.zzf = ((TreeMap) this.zzc).descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    private zzalg(int i) {
        this.zza = i;
        this.zzb = Collections.emptyList();
        this.zzc = Collections.emptyMap();
        this.zzf = Collections.emptyMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzg();
        if (!this.zzb.isEmpty()) {
            this.zzb.clear();
        }
        if (!this.zzc.isEmpty()) {
            this.zzc.clear();
        }
    }

    public void zzd() {
        Map<K, V> mapUnmodifiableMap;
        Map<K, V> mapUnmodifiableMap2;
        if (!this.zzd) {
            if (this.zzc.isEmpty()) {
                mapUnmodifiableMap = Collections.emptyMap();
            } else {
                mapUnmodifiableMap = Collections.unmodifiableMap(this.zzc);
            }
            this.zzc = mapUnmodifiableMap;
            if (this.zzf.isEmpty()) {
                mapUnmodifiableMap2 = Collections.emptyMap();
            } else {
                mapUnmodifiableMap2 = Collections.unmodifiableMap(this.zzf);
            }
            this.zzf = mapUnmodifiableMap2;
            this.zzd = true;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzalg)) {
            return super.equals(obj);
        }
        zzalg zzalgVar = (zzalg) obj;
        int size = size();
        if (size != zzalgVar.size()) {
            return false;
        }
        int iZza = zza();
        if (iZza != zzalgVar.zza()) {
            return entrySet().equals(zzalgVar.entrySet());
        }
        for (int i = 0; i < iZza; i++) {
            if (!zzb(i).equals(zzalgVar.zzb(i))) {
                return false;
            }
        }
        if (iZza == size) {
            return true;
        }
        return this.zzc.equals(zzalgVar.zzc);
    }

    public final boolean zze() {
        return this.zzd;
    }
}
