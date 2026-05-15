package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzph;
import com.google.android.gms.internal.p001firebaseauthapi.zzpo;
import com.google.android.gms.internal.p001firebaseauthapi.zzvb;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzpt {
    private static final zzxt zza = zzpf.zzb("type.googleapis.com/google.crypto.tink.AesCmacKey");
    private static final zznz<zzpo, zzor> zzb = zznz.zza(new zzob() { // from class: com.google.android.gms.internal.firebase-auth-api.zzps
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzob
        public final zzov zza(zzch zzchVar) {
            return zzpt.zza((zzpo) zzchVar);
        }
    }, zzpo.class, zzor.class);
    private static final zznv<zzor> zzc = zznv.zza(new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzpv
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zznx
        public final zzch zza(zzov zzovVar) {
            return zzpt.zzb((zzor) zzovVar);
        }
    }, zza, zzor.class);
    private static final zzmx<zzph, zzos> zzd = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzpu
    }, zzph.class, zzos.class);
    private static final zzmu<zzos> zze = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzpx
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzpt.zzb((zzos) zzovVar, zzcsVar);
        }
    }, zza, zzos.class);

    public static /* synthetic */ zzor zza(zzpo zzpoVar) throws GeneralSecurityException {
        zzvs zzvsVar;
        zzvb.zza zzaVarZza = zzvb.zza().zza("type.googleapis.com/google.crypto.tink.AesCmacKey").zza(((zzsa) ((zzajc) zzsa.zzb().zza((zzsb) ((zzajc) zzsb.zzb().zza(zzpoVar.zzb()).zzf())).zza(zzpoVar.zzc()).zzf())).zzi());
        zzpo.zzb zzbVarZze = zzpoVar.zze();
        if (zzpo.zzb.zza.equals(zzbVarZze)) {
            zzvsVar = zzvs.TINK;
        } else if (zzpo.zzb.zzb.equals(zzbVarZze)) {
            zzvsVar = zzvs.CRUNCHY;
        } else if (zzpo.zzb.zzd.equals(zzbVarZze)) {
            zzvsVar = zzvs.RAW;
        } else {
            if (!zzpo.zzb.zzc.equals(zzbVarZze)) {
                throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzbVarZze));
            }
            zzvsVar = zzvs.LEGACY;
        }
        return zzor.zzb((zzvb) ((zzajc) zzaVarZza.zza(zzvsVar).zzf()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzph zzb(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesCmacProtoSerialization.parseKey");
        }
        try {
            zzrw zzrwVarZza = zzrw.zza(zzosVar.zzd(), zzaio.zza());
            if (zzrwVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return new zzph.zza().zza(zzpo.zzd().zza(zzrwVarZza.zze().zzb()).zzb(zzrwVarZza.zzd().zza()).zza(zza(zzosVar.zzc())).zza()).zza(zzxu.zza(zzrwVarZza.zze().zzg(), zzcs.zza(zzcsVar))).zza(zzosVar.zze()).zza();
        } catch (zzaji | IllegalArgumentException e) {
            throw new GeneralSecurityException("Parsing AesCmacKey failed");
        }
    }

    private static zzpo.zzb zza(zzvs zzvsVar) throws GeneralSecurityException {
        switch (zzpw.zza[zzvsVar.ordinal()]) {
            case 1:
                return zzpo.zzb.zza;
            case 2:
                return zzpo.zzb.zzb;
            case 3:
                return zzpo.zzb.zzc;
            case 4:
                return zzpo.zzb.zzd;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzvsVar.zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzpo zzb(zzor zzorVar) throws GeneralSecurityException {
        if (!zzorVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesCmacProtoSerialization.parseParameters: " + zzorVar.zza().zzf());
        }
        try {
            zzsa zzsaVarZza = zzsa.zza(zzorVar.zza().zze(), zzaio.zza());
            return zzpo.zzd().zza(zzsaVarZza.zza()).zzb(zzsaVarZza.zzd().zza()).zza(zza(zzorVar.zza().zzd())).zza();
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing AesCmacParameters failed: ", e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zznu zznuVarZza = zznu.zza();
        zznuVarZza.zza(zzb);
        zznuVarZza.zza(zzc);
        zznuVarZza.zza(zzd);
        zznuVarZza.zza(zze);
    }
}
