package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzamg extends zzame<zzamd, zzamd> {
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ int zza(zzamd zzamdVar) {
        return zzamdVar.zza();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ int zzb(zzamd zzamdVar) {
        return zzamdVar.zzb();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ zzamd zzc(Object obj) {
        zzamd zzamdVar = ((zzajc) obj).zzb;
        if (zzamdVar == zzamd.zzc()) {
            zzamd zzamdVarZzd = zzamd.zzd();
            zza(obj, zzamdVarZzd);
            return zzamdVarZzd;
        }
        return zzamdVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ zzamd zzd(Object obj) {
        return ((zzajc) obj).zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ zzamd zza(zzamd zzamdVar, zzamd zzamdVar2) {
        zzamd zzamdVar3 = zzamdVar;
        zzamd zzamdVar4 = zzamdVar2;
        if (zzamd.zzc().equals(zzamdVar4)) {
            return zzamdVar3;
        }
        if (zzamd.zzc().equals(zzamdVar3)) {
            return zzamd.zza(zzamdVar3, zzamdVar4);
        }
        return zzamdVar3.zza(zzamdVar4);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ zzamd zza() {
        return zzamd.zzd();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ zzamd zze(zzamd zzamdVar) {
        zzamd zzamdVar2 = zzamdVar;
        zzamdVar2.zze();
        return zzamdVar2;
    }

    zzamg() {
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ void zza(zzamd zzamdVar, int i, int i2) {
        zzamdVar.zza((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ void zza(zzamd zzamdVar, int i, long j) {
        zzamdVar.zza((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ void zza(zzamd zzamdVar, int i, zzamd zzamdVar2) {
        zzamdVar.zza((i << 3) | 3, zzamdVar2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ void zza(zzamd zzamdVar, int i, zzahp zzahpVar) {
        zzamdVar.zza((i << 3) | 2, zzahpVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ void zzb(zzamd zzamdVar, int i, long j) {
        zzamdVar.zza(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final void zzf(Object obj) {
        ((zzajc) obj).zzb.zze();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ void zzb(Object obj, zzamd zzamdVar) {
        zza(obj, zzamdVar);
    }

    private static void zza(Object obj, zzamd zzamdVar) {
        ((zzajc) obj).zzb = zzamdVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ void zzc(Object obj, zzamd zzamdVar) {
        zza(obj, zzamdVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ void zza(zzamd zzamdVar, zzana zzanaVar) throws IOException {
        zzamdVar.zza(zzanaVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final /* synthetic */ void zzb(zzamd zzamdVar, zzana zzanaVar) throws IOException {
        zzamdVar.zzb(zzanaVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzame
    final boolean zza(zzalc zzalcVar) {
        return false;
    }
}
