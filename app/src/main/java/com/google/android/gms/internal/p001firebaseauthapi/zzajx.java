package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzajx extends zzajs {
    private static <E> zzajj<E> zzc(Object obj, long j) {
        return (zzajj) zzamk.zze(obj, j);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajs
    final <L> List<L> zza(Object obj, long j) {
        zzajj zzajjVarZzc = zzc(obj, j);
        if (zzajjVarZzc.zzc()) {
            return zzajjVarZzc;
        }
        int size = zzajjVarZzc.size();
        zzajj zzajjVarZza = zzajjVarZzc.zza(size == 0 ? 10 : size << 1);
        zzamk.zza(obj, j, zzajjVarZza);
        return zzajjVarZza;
    }

    private zzajx() {
        super();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajs
    final void zzb(Object obj, long j) {
        zzc(obj, j).zzb();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.internal.firebase-auth-api.zzajj] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.google.android.gms.internal.firebase-auth-api.zzajj, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v3 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajs
    final <E> void zza(Object obj, Object obj2, long j) {
        zzajj zzajjVarZzc = zzc(obj, j);
        ?? Zzc = zzc(obj2, j);
        int size = zzajjVarZzc.size();
        int size2 = Zzc.size();
        ?? r0 = zzajjVarZzc;
        r0 = zzajjVarZzc;
        if (size > 0 && size2 > 0) {
            boolean zZzc = zzajjVarZzc.zzc();
            ?? Zza = zzajjVarZzc;
            if (!zZzc) {
                Zza = zzajjVarZzc.zza(size2 + size);
            }
            Zza.addAll(Zzc);
            r0 = Zza;
        }
        if (size > 0) {
            Zzc = r0;
        }
        zzamk.zza(obj, j, (Object) Zzc);
    }
}
