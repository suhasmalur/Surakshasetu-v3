package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzfc;
import com.google.android.gms.internal.p001firebaseauthapi.zzfj;
import com.google.android.gms.internal.p001firebaseauthapi.zzvb;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzfl {
    private static final zzxt zza = zzpf.zzb("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
    private static final zznz<zzfj, zzor> zzb = zznz.zza(new zzob() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfo
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzob
        public final zzov zza(zzch zzchVar) {
            return zzfl.zza((zzfj) zzchVar);
        }
    }, zzfj.class, zzor.class);
    private static final zznv<zzor> zzc = zznv.zza(new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfn
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zznx
        public final zzch zza(zzov zzovVar) {
            return zzfl.zzb((zzor) zzovVar);
        }
    }, zza, zzor.class);
    private static final zzmx<zzfc, zzos> zzd = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfq
    }, zzfc.class, zzos.class);
    private static final zzmu<zzos> zze = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfp
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzfl.zzb((zzos) zzovVar, zzcsVar);
        }
    }, zza, zzos.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzfc zzb(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivProtoSerialization.parseKey");
        }
        try {
            zzsy zzsyVarZza = zzsy.zza(zzosVar.zzd(), zzaio.zza());
            if (zzsyVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return new zzfc.zza().zza(zzfj.zzc().zza(zzsyVarZza.zzd().zzb()).zza(zza(zzosVar.zzc())).zza()).zza(zzxu.zza(zzsyVarZza.zzd().zzg(), zzcs.zza(zzcsVar))).zza(zzosVar.zze()).zza();
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing AesGcmSivKey failed");
        }
    }

    private static zzfj.zzb zza(zzvs zzvsVar) throws GeneralSecurityException {
        switch (zzfs.zza[zzvsVar.ordinal()]) {
            case 1:
                return zzfj.zzb.zza;
            case 2:
            case 3:
                return zzfj.zzb.zzb;
            case 4:
                return zzfj.zzb.zzc;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzvsVar.zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzfj zzb(zzor zzorVar) throws GeneralSecurityException {
        if (!zzorVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivProtoSerialization.parseParameters: " + zzorVar.zza().zzf());
        }
        try {
            zzsz zzszVarZza = zzsz.zza(zzorVar.zza().zze(), zzaio.zza());
            if (zzszVarZza.zzb() != 0) {
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            }
            return zzfj.zzc().zza(zzszVarZza.zza()).zza(zza(zzorVar.zza().zzd())).zza();
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing AesGcmSivParameters failed: ", e);
        }
    }

    public static /* synthetic */ zzor zza(zzfj zzfjVar) throws GeneralSecurityException {
        zzvs zzvsVar;
        zzvb.zza zzaVarZza = zzvb.zza().zza("type.googleapis.com/google.crypto.tink.AesGcmSivKey").zza(((zzsz) ((zzajc) zzsz.zzc().zza(zzfjVar.zzb()).zzf())).zzi());
        zzfj.zzb zzbVarZzd = zzfjVar.zzd();
        if (zzfj.zzb.zza.equals(zzbVarZzd)) {
            zzvsVar = zzvs.TINK;
        } else if (zzfj.zzb.zzb.equals(zzbVarZzd)) {
            zzvsVar = zzvs.CRUNCHY;
        } else if (zzfj.zzb.zzc.equals(zzbVarZzd)) {
            zzvsVar = zzvs.RAW;
        } else {
            throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzbVarZzd));
        }
        return zzor.zzb((zzvb) ((zzajc) zzaVarZza.zza(zzvsVar).zzf()));
    }

    public static void zza() throws GeneralSecurityException {
        zznu zznuVarZza = zznu.zza();
        zznuVarZza.zza(zzb);
        zznuVarZza.zza(zzc);
        zznuVarZza.zza(zzd);
        zznuVarZza.zza(zze);
    }
}
