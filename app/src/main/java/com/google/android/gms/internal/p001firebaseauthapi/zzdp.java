package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzde;
import com.google.android.gms.internal.p001firebaseauthapi.zzdl;
import com.google.android.gms.internal.p001firebaseauthapi.zzsf;
import com.google.android.gms.internal.p001firebaseauthapi.zzug;
import com.google.android.gms.internal.p001firebaseauthapi.zzuh;
import com.google.android.gms.internal.p001firebaseauthapi.zzvb;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzdp {
    private static final zzxt zza = zzpf.zzb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
    private static final zznz<zzdl, zzor> zzb = zznz.zza(new zzob() { // from class: com.google.android.gms.internal.firebase-auth-api.zzdo
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzob
        public final zzov zza(zzch zzchVar) {
            return zzdp.zza((zzdl) zzchVar);
        }
    }, zzdl.class, zzor.class);
    private static final zznv<zzor> zzc = zznv.zza(new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzdr
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zznx
        public final zzch zza(zzov zzovVar) {
            return zzdp.zzb((zzor) zzovVar);
        }
    }, zza, zzor.class);
    private static final zzmx<zzde, zzos> zzd = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzdq
    }, zzde.class, zzos.class);
    private static final zzmu<zzos> zze = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzdt
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzdp.zzb((zzos) zzovVar, zzcsVar);
        }
    }, zza, zzos.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzde zzb(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseKey");
        }
        try {
            zzse zzseVarZza = zzse.zza(zzosVar.zzd(), zzaio.zza());
            if (zzseVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            if (zzseVarZza.zzd().zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys inner AES CTR keys are accepted");
            }
            if (zzseVarZza.zze().zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys inner HMAC keys are accepted");
            }
            return new zzde.zza().zza(zzdl.zzf().zza(zzseVarZza.zzd().zzf().zzb()).zzb(zzseVarZza.zze().zzf().zzb()).zzc(zzseVarZza.zzd().zze().zza()).zzd(zzseVarZza.zze().zze().zza()).zza(zza(zzseVarZza.zze().zze().zzb())).zza(zza(zzosVar.zzc())).zza()).zza(zzxu.zza(zzseVarZza.zzd().zzf().zzg(), zzcs.zza(zzcsVar))).zzb(zzxu.zza(zzseVarZza.zze().zzf().zzg(), zzcs.zza(zzcsVar))).zza(zzosVar.zze()).zza();
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing AesCtrHmacAeadKey failed");
        }
    }

    private static zzdl.zzb zza(zzub zzubVar) throws GeneralSecurityException {
        switch (zzds.zzb[zzubVar.ordinal()]) {
            case 1:
                return zzdl.zzb.zza;
            case 2:
                return zzdl.zzb.zzb;
            case 3:
                return zzdl.zzb.zzc;
            case 4:
                return zzdl.zzb.zzd;
            case 5:
                return zzdl.zzb.zze;
            default:
                throw new GeneralSecurityException("Unable to parse HashType: " + zzubVar.zza());
        }
    }

    private static zzdl.zzc zza(zzvs zzvsVar) throws GeneralSecurityException {
        switch (zzds.zza[zzvsVar.ordinal()]) {
            case 1:
                return zzdl.zzc.zza;
            case 2:
            case 3:
                return zzdl.zzc.zzb;
            case 4:
                return zzdl.zzc.zzc;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzvsVar.zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzdl zzb(zzor zzorVar) throws GeneralSecurityException {
        if (!zzorVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseParameters: " + zzorVar.zza().zzf());
        }
        try {
            zzsf zzsfVarZza = zzsf.zza(zzorVar.zza().zze(), zzaio.zza());
            if (zzsfVarZza.zzd().zzb() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return zzdl.zzf().zza(zzsfVarZza.zzc().zza()).zzb(zzsfVarZza.zzd().zza()).zzc(zzsfVarZza.zzc().zze().zza()).zzd(zzsfVarZza.zzd().zzf().zza()).zza(zza(zzsfVarZza.zzd().zzf().zzb())).zza(zza(zzorVar.zza().zzd())).zza();
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing AesCtrHmacAeadParameters failed: ", e);
        }
    }

    public static /* synthetic */ zzor zza(zzdl zzdlVar) throws GeneralSecurityException {
        zzub zzubVar;
        zzvs zzvsVar;
        zzvb.zza zzaVarZza = zzvb.zza().zza("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        zzsf.zza zzaVarZza2 = zzsf.zza().zza((zzsj) ((zzajc) zzsj.zzb().zza((zzsm) ((zzajc) zzsm.zzb().zza(zzdlVar.zzd()).zzf())).zza(zzdlVar.zzb()).zzf()));
        zzug.zza zzaVarZzc = zzug.zzc();
        zzuh.zza zzaVarZza3 = zzuh.zzc().zza(zzdlVar.zze());
        zzdl.zzb zzbVarZzg = zzdlVar.zzg();
        if (zzdl.zzb.zza.equals(zzbVarZzg)) {
            zzubVar = zzub.SHA1;
        } else if (zzdl.zzb.zzb.equals(zzbVarZzg)) {
            zzubVar = zzub.SHA224;
        } else if (zzdl.zzb.zzc.equals(zzbVarZzg)) {
            zzubVar = zzub.SHA256;
        } else if (zzdl.zzb.zzd.equals(zzbVarZzg)) {
            zzubVar = zzub.SHA384;
        } else if (zzdl.zzb.zze.equals(zzbVarZzg)) {
            zzubVar = zzub.SHA512;
        } else {
            throw new GeneralSecurityException("Unable to serialize HashType " + String.valueOf(zzbVarZzg));
        }
        zzvb.zza zzaVarZza4 = zzaVarZza.zza(((zzsf) ((zzajc) zzaVarZza2.zza((zzug) ((zzajc) zzaVarZzc.zza((zzuh) ((zzajc) zzaVarZza3.zza(zzubVar).zzf())).zza(zzdlVar.zzc()).zzf())).zzf())).zzi());
        zzdl.zzc zzcVarZzh = zzdlVar.zzh();
        if (zzdl.zzc.zza.equals(zzcVarZzh)) {
            zzvsVar = zzvs.TINK;
        } else if (zzdl.zzc.zzb.equals(zzcVarZzh)) {
            zzvsVar = zzvs.CRUNCHY;
        } else if (zzdl.zzc.zzc.equals(zzcVarZzh)) {
            zzvsVar = zzvs.RAW;
        } else {
            throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzcVarZzh));
        }
        return zzor.zzb((zzvb) ((zzajc) zzaVarZza4.zza(zzvsVar).zzf()));
    }

    public static void zza() throws GeneralSecurityException {
        zznu zznuVarZza = zznu.zza();
        zznuVarZza.zza(zzb);
        zznuVarZza.zza(zzc);
        zznuVarZza.zza(zzd);
        zznuVarZza.zza(zze);
    }
}
