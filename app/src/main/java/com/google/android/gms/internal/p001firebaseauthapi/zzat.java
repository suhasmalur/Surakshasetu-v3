package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzat<K, V> implements Serializable, Map<K, V> {
    private static final Map.Entry<?, ?>[] zza = new Map.Entry[0];

    @CheckForNull
    private transient zzau<Map.Entry<K, V>> zzb;

    @CheckForNull
    private transient zzau<K> zzc;

    @CheckForNull
    private transient zzak<V> zzd;

    @Override // java.util.Map
    @CheckForNull
    public abstract V get(@CheckForNull Object obj);

    @Override // java.util.Map
    public int hashCode() {
        return zzbd.zza((zzau) entrySet());
    }

    abstract zzak<V> zza();

    abstract zzau<Map.Entry<K, V>> zzb();

    abstract zzau<K> zzc();

    abstract boolean zzd();

    public static <K, V> zzat<K, V> zza(Map<? extends K, ? extends V> map) {
        if ((map instanceof zzat) && !(map instanceof SortedMap)) {
            zzat<K, V> zzatVar = (zzat) map;
            zzatVar.zzd();
            return zzatVar;
        }
        Set<Map.Entry<? extends K, ? extends V>> setEntrySet = map.entrySet();
        zzas zzasVar = new zzas(setEntrySet instanceof Collection ? setEntrySet.size() : 4);
        zzasVar.zza(setEntrySet);
        return zzasVar.zza();
    }

    @Override // java.util.Map
    @CheckForNull
    public final V getOrDefault(@CheckForNull Object obj, @CheckForNull V v) {
        V v2 = get(obj);
        if (v2 != null) {
            return v2;
        }
        return v;
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final V remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        int size = size();
        zzai.zza(size, "size");
        StringBuilder sbAppend = new StringBuilder((int) Math.min(((long) size) << 3, 1073741824L)).append('{');
        boolean z = true;
        for (Map.Entry<K, V> entry : entrySet()) {
            if (!z) {
                sbAppend.append(", ");
            }
            sbAppend.append(entry.getKey()).append('=').append(entry.getValue());
            z = false;
        }
        return sbAppend.append('}').toString();
    }

    @Override // java.util.Map
    public /* synthetic */ Collection values() {
        zzak<V> zzakVar = this.zzd;
        if (zzakVar != null) {
            return zzakVar;
        }
        zzak<V> zzakVarZza = zza();
        this.zzd = zzakVarZza;
        return zzakVarZza;
    }

    @Override // java.util.Map
    public /* synthetic */ Set entrySet() {
        zzau<Map.Entry<K, V>> zzauVar = this.zzb;
        if (zzauVar != null) {
            return zzauVar;
        }
        zzau<Map.Entry<K, V>> zzauVarZzb = zzb();
        this.zzb = zzauVarZzb;
        return zzauVarZzb;
    }

    @Override // java.util.Map
    public /* synthetic */ Set keySet() {
        zzau<K> zzauVar = this.zzc;
        if (zzauVar != null) {
            return zzauVar;
        }
        zzau<K> zzauVarZzc = zzc();
        this.zzc = zzauVarZzc;
        return zzauVarZzc;
    }

    zzat() {
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(@CheckForNull Object obj) {
        return ((zzak) values()).contains(obj);
    }

    @Override // java.util.Map
    public boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }
}
