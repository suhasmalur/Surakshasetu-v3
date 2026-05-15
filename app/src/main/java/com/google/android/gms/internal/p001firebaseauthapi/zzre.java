package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzql;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzre {
    private static final zzxt zza = zzpf.zzb("type.googleapis.com/google.crypto.tink.HmacKey");
    private static final zzmi<zzvs, zzql.zzb> zzb = zzmi.zza().zza(zzvs.RAW, zzql.zzb.zzd).zza(zzvs.TINK, zzql.zzb.zza).zza(zzvs.LEGACY, zzql.zzb.zzc).zza(zzvs.CRUNCHY, zzql.zzb.zzb).zza();
    private static final zzmi<zzub, zzql.zzc> zzc = zzmi.zza().zza(zzub.SHA1, zzql.zzc.zza).zza(zzub.SHA224, zzql.zzc.zzb).zza(zzub.SHA256, zzql.zzc.zzc).zza(zzub.SHA384, zzql.zzc.zzd).zza(zzub.SHA512, zzql.zzc.zze).zza();
    private static final zznz<zzql, zzor> zzd = zznz.zza(new zzob() { // from class: com.google.android.gms.internal.firebase-auth-api.zzrh
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzob
        public final zzov zza(zzch zzchVar) {
            zzql zzqlVar = (zzql) zzchVar;
            return zzor.zzb((zzvb) ((zzajc) zzvb.zza().zza("type.googleapis.com/google.crypto.tink.HmacKey").zza(((zzug) ((zzajc) zzug.zzc().zza((zzuh) ((zzajc) zzuh.zzc().zza(zzqlVar.zzb()).zza((zzub) zzre.zzc.zza(zzqlVar.zze())).zzf())).zza(zzqlVar.zzc()).zzf())).zzi()).zza((zzvs) zzre.zzb.zza(zzqlVar.zzf())).zzf()));
        }
    }, zzql.class, zzor.class);
    private static final zznv<zzor> zze = zznv.zza(new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzrg
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zznx
        public final zzch zza(zzov zzovVar) {
            return zzre.zzb((zzor) zzovVar);
        }
    }, zza, zzor.class);
    private static final zzmx<zzqc, zzos> zzf = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzrj
    }, zzqc.class, zzos.class);
    private static final zzmu<zzos> zzg = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzri
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzre.zzb((zzos) zzovVar, zzcsVar);
        }
    }, zza, zzos.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzqc zzb(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseKey");
        }
        try {
            zzuc zzucVarZza = zzuc.zza(zzosVar.zzd(), zzaio.zza());
            if (zzucVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return zzqc.zzc().zza(zzql.zzd().zza(zzucVarZza.zzf().zzb()).zzb(zzucVarZza.zze().zza()).zza(zzc.zza(zzucVarZza.zze().zzb())).zza(zzb.zza(zzosVar.zzc())).zza()).zza(zzxu.zza(zzucVarZza.zzf().zzg(), zzcs.zza(zzcsVar))).zza(zzosVar.zze()).zza();
        } catch (zzaji | IllegalArgumentException e) {
            throw new GeneralSecurityException("Parsing HmacKey failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzql zzb(zzor zzorVar) throws GeneralSecurityException {
        if (!zzorVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseParameters: " + zzorVar.zza().zzf());
        }
        try {
            zzug zzugVarZza = zzug.zza(zzorVar.zza().zze(), zzaio.zza());
            if (zzugVarZza.zzb() != 0) {
                throw new GeneralSecurityException("Parsing HmacParameters failed: unknown Version " + zzugVarZza.zzb());
            }
            return zzql.zzd().zza(zzugVarZza.zza()).zzb(zzugVarZza.zzf().zza()).zza(zzc.zza(zzugVarZza.zzf().zzb())).zza(zzb.zza(zzorVar.zza().zzd())).zza();
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing HmacParameters failed: ", e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zznu zznuVarZza = zznu.zza();
        zznuVarZza.zza(zzd);
        zznuVarZza.zza(zze);
        zznuVarZza.zza(zzf);
        zznuVarZza.zza(zzg);
    }
}
