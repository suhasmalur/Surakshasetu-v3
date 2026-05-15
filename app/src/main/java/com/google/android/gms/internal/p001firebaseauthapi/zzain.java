package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzain implements zzana {
    private final zzaik zza;

    public static zzain zza(zzaik zzaikVar) {
        return zzaikVar.zze != null ? zzaikVar.zze : new zzain(zzaikVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final int zza() {
        return zzand.zza;
    }

    private zzain(zzaik zzaikVar) {
        this.zza = (zzaik) zzajf.zza(zzaikVar, "output");
        this.zza.zze = this;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, boolean z) throws IOException {
        this.zza.zzb(i, z);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZza = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZza += zzaik.zza(list.get(i3).booleanValue());
            }
            this.zza.zzn(iZza);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, zzahp zzahpVar) throws IOException {
        this.zza.zzc(i, zzahpVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, List<zzahp> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zzc(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, double d) throws IOException {
        this.zza.zzb(i, d);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzb(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZza = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZza += zzaik.zza(list.get(i3).doubleValue());
            }
            this.zza.zzn(iZza);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    @Deprecated
    public final void zza(int i) throws IOException {
        this.zza.zzk(i, 4);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, int i2) throws IOException {
        this.zza.zzi(i, i2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzc(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZzc = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzc += zzaik.zzc(list.get(i3).intValue());
            }
            this.zza.zzn(iZzc);
            while (i2 < list.size()) {
                this.zza.zzl(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzi(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzb(int i, int i2) throws IOException {
        this.zza.zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzd(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZzd = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzd += zzaik.zzd(list.get(i3).intValue());
            }
            this.zza.zzn(iZzd);
            while (i2 < list.size()) {
                this.zza.zzk(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, long j) throws IOException {
        this.zza.zzf(i, j);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZzc = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzc += zzaik.zzc(list.get(i3).longValue());
            }
            this.zza.zzn(iZzc);
            while (i2 < list.size()) {
                this.zza.zzh(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzf(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, float f) throws IOException {
        this.zza.zzb(i, f);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZza = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZza += zzaik.zza(list.get(i3).floatValue());
            }
            this.zza.zzn(iZza);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, Object obj, zzalf zzalfVar) throws IOException {
        zzaik zzaikVar = this.zza;
        zzaikVar.zzk(i, 3);
        zzalfVar.zza((zzakn) obj, zzaikVar.zze);
        zzaikVar.zzk(i, 4);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, List<?> list, zzalf zzalfVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzalfVar);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzc(int i, int i2) throws IOException {
        this.zza.zzi(i, i2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzg(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZze = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZze += zzaik.zze(list.get(i3).intValue());
            }
            this.zza.zzn(iZze);
            while (i2 < list.size()) {
                this.zza.zzl(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzi(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzb(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzh(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZzd = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzd += zzaik.zzd(list.get(i3).longValue());
            }
            this.zza.zzn(iZzd);
            while (i2 < list.size()) {
                this.zza.zzj(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzh(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final <K, V> void zza(int i, zzake<K, V> zzakeVar, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zza.zzk(i, 2);
            this.zza.zzn(zzakf.zza(zzakeVar, entry.getKey(), entry.getValue()));
            zzakf.zza(this.zza, zzakeVar, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzb(int i, Object obj, zzalf zzalfVar) throws IOException {
        this.zza.zzc(i, (zzakn) obj, zzalfVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzb(int i, List<?> list, zzalf zzalfVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzalfVar);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzahp) {
            this.zza.zzd(i, (zzahp) obj);
        } else {
            this.zza.zzb(i, (zzakn) obj);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzd(int i, int i2) throws IOException {
        this.zza.zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzi(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZzg = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzg += zzaik.zzg(list.get(i3).intValue());
            }
            this.zza.zzn(iZzg);
            while (i2 < list.size()) {
                this.zza.zzk(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzc(int i, long j) throws IOException {
        this.zza.zzf(i, j);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzj(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZze = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZze += zzaik.zze(list.get(i3).longValue());
            }
            this.zza.zzn(iZze);
            while (i2 < list.size()) {
                this.zza.zzh(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzf(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zze(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZzh = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzh += zzaik.zzh(list.get(i3).intValue());
            }
            this.zza.zzn(iZzh);
            while (i2 < list.size()) {
                this.zza.zzm(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzj(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzd(int i, long j) throws IOException {
        this.zza.zzg(i, j);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZzf = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzf += zzaik.zzf(list.get(i3).longValue());
            }
            this.zza.zzn(iZzf);
            while (i2 < list.size()) {
                this.zza.zzi(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzg(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    @Deprecated
    public final void zzb(int i) throws IOException {
        this.zza.zzk(i, 3);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zza(int i, String str) throws IOException {
        this.zza.zzb(i, str);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzb(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzajt) {
            zzajt zzajtVar = (zzajt) list;
            while (i2 < list.size()) {
                Object objZzb = zzajtVar.zzb(i2);
                if (objZzb instanceof String) {
                    this.zza.zzb(i, (String) objZzb);
                } else {
                    this.zza.zzc(i, (zzahp) objZzb);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzf(int i, int i2) throws IOException {
        this.zza.zzl(i, i2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZzj = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzj += zzaik.zzj(list.get(i3).intValue());
            }
            this.zza.zzn(iZzj);
            while (i2 < list.size()) {
                this.zza.zzn(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzl(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zze(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzana
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zzk(i, 2);
            int iZzg = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzg += zzaik.zzg(list.get(i3).longValue());
            }
            this.zza.zzn(iZzg);
            while (i2 < list.size()) {
                this.zza.zzj(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzh(i, list.get(i2).longValue());
            i2++;
        }
    }
}
