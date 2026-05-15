package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzakj implements zzakg {
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakg
    public final int zza(int i, Object obj, Object obj2) {
        zzakh zzakhVar = (zzakh) obj;
        if (zzakhVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzakhVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakg
    public final zzake<?, ?> zza(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakg
    public final Object zza(Object obj, Object obj2) {
        zzakh zzakhVarZzb = (zzakh) obj;
        zzakh zzakhVar = (zzakh) obj2;
        if (!zzakhVar.isEmpty()) {
            if (!zzakhVarZzb.zzd()) {
                zzakhVarZzb = zzakhVarZzb.zzb();
            }
            zzakhVarZzb.zza(zzakhVar);
        }
        return zzakhVarZzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakg
    public final Object zzb(Object obj) {
        return zzakh.zza().zzb();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakg
    public final Object zzc(Object obj) {
        ((zzakh) obj).zzc();
        return obj;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakg
    public final Map<?, ?> zzd(Object obj) {
        return (zzakh) obj;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakg
    public final Map<?, ?> zze(Object obj) {
        return (zzakh) obj;
    }

    zzakj() {
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakg
    public final boolean zzf(Object obj) {
        return !((zzakh) obj).zzd();
    }
}
