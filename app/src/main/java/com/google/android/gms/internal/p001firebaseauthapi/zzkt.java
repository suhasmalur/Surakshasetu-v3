package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zztn;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkt {
    private static final byte[] zzb = new byte[0];
    public static final zzvb zza = zza(zzty.NIST_P256, zzub.SHA256, zztk.UNCOMPRESSED, zzcy.zza, zzvs.TINK, zzb);
    private static final zzvb zzc = zza(zzty.NIST_P256, zzub.SHA256, zztk.COMPRESSED, zzcy.zza, zzvs.RAW, zzb);
    private static final zzvb zzd = zza(zzty.NIST_P256, zzub.SHA256, zztk.UNCOMPRESSED, zzcy.zzb, zzvs.TINK, zzb);

    @Deprecated
    private static zzvb zza(zzty zztyVar, zzub zzubVar, zztk zztkVar, zzvb zzvbVar, zzvs zzvsVar, byte[] bArr) {
        zztn.zza zzaVarZza = zztn.zza();
        zztv zztvVar = (zztv) ((zzajc) zztv.zza().zza(zztyVar).zza(zzubVar).zza(zzahp.zza(bArr)).zzf());
        return (zzvb) ((zzajc) zzvb.zza().zza(new zzji().zze()).zza(zzvsVar).zza(((zztn) ((zzajc) zzaVarZza.zza((zztq) ((zzajc) zztq.zzc().zza(zztvVar).zza((zztm) ((zzajc) zztm.zza().zza(zzvbVar).zzf())).zza(zztkVar).zzf())).zzf())).zzi()).zzf());
    }
}
