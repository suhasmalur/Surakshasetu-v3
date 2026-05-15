package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcy {
    public static final zzvb zza = zza(16);
    private static final zzvb zzc = zza(32);
    private static final zzvb zzd = zza(16, 16);
    private static final zzvb zze = zza(32, 16);
    public static final zzvb zzb = zza(16, 16, 32, 16, zzub.SHA256);
    private static final zzvb zzf = zza(32, 16, 32, 32, zzub.SHA256);
    private static final zzvb zzg = (zzvb) ((zzajc) zzvb.zza().zza(new zzfu().zze()).zza(zzvs.TINK).zzf());
    private static final zzvb zzh = (zzvb) ((zzajc) zzvb.zza().zza(new zzhj().zze()).zza(zzvs.TINK).zzf());

    private static zzvb zza(int i, int i2, int i3, int i4, zzub zzubVar) {
        zzsj zzsjVar = (zzsj) ((zzajc) zzsj.zzb().zza((zzsm) ((zzajc) zzsm.zzb().zza(16).zzf())).zza(i).zzf());
        return (zzvb) ((zzajc) zzvb.zza().zza(((zzsf) ((zzajc) zzsf.zza().zza(zzsjVar).zza((zzug) ((zzajc) zzug.zzc().zza((zzuh) ((zzajc) zzuh.zzc().zza(zzubVar).zza(i4).zzf())).zza(32).zzf())).zzf())).zzi()).zza(new zzdi().zze()).zza(zzvs.TINK).zzf());
    }

    private static zzvb zza(int i, int i2) {
        return (zzvb) ((zzajc) zzvb.zza().zza(((zzsq) ((zzajc) zzsq.zzb().zza(i).zza((zzsr) ((zzajc) zzsr.zzb().zza(16).zzf())).zzf())).zzi()).zza(new zzdz().zze()).zza(zzvs.TINK).zzf());
    }

    private static zzvb zza(int i) {
        return (zzvb) ((zzajc) zzvb.zza().zza(((zzsv) ((zzajc) zzsv.zzc().zza(i).zzf())).zzi()).zza(new zzeo().zze()).zza(zzvs.TINK).zzf());
    }
}
