package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzel;
import com.google.android.gms.internal.p001firebaseauthapi.zzes;
import com.google.android.gms.internal.p001firebaseauthapi.zzvb;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzex {
    private static final zzxt zza = zzpf.zzb("type.googleapis.com/google.crypto.tink.AesGcmKey");
    private static final zznz<zzes, zzor> zzb = zznz.zza(new zzob() { // from class: com.google.android.gms.internal.firebase-auth-api.zzew
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzob
        public final zzov zza(zzch zzchVar) {
            return zzex.zza((zzes) zzchVar);
        }
    }, zzes.class, zzor.class);
    private static final zznv<zzor> zzc = zznv.zza(new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzez
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zznx
        public final zzch zza(zzov zzovVar) {
            return zzex.zzb((zzor) zzovVar);
        }
    }, zza, zzor.class);
    private static final zzmx<zzel, zzos> zzd = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzey
    }, zzel.class, zzos.class);
    private static final zzmu<zzos> zze = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfb
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzex.zzb((zzos) zzovVar, zzcsVar);
        }
    }, zza, zzos.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzel zzb(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmProtoSerialization.parseKey");
        }
        try {
            zzsu zzsuVarZza = zzsu.zza(zzosVar.zzd(), zzaio.zza());
            if (zzsuVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return new zzel.zza().zza(zzes.zze().zzb(zzsuVarZza.zzd().zzb()).zza(12).zzc(16).zza(zza(zzosVar.zzc())).zza()).zza(zzxu.zza(zzsuVarZza.zzd().zzg(), zzcs.zza(zzcsVar))).zza(zzosVar.zze()).zza();
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing AesGcmKey failed");
        }
    }

    private static zzes.zzb zza(zzvs zzvsVar) throws GeneralSecurityException {
        switch (zzfa.zza[zzvsVar.ordinal()]) {
            case 1:
                return zzes.zzb.zza;
            case 2:
            case 3:
                return zzes.zzb.zzb;
            case 4:
                return zzes.zzb.zzc;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzvsVar.zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzes zzb(zzor zzorVar) throws GeneralSecurityException {
        if (!zzorVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmProtoSerialization.parseParameters: " + zzorVar.zza().zzf());
        }
        try {
            zzsv zzsvVarZza = zzsv.zza(zzorVar.zza().zze(), zzaio.zza());
            if (zzsvVarZza.zzb() != 0) {
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            }
            return zzes.zze().zzb(zzsvVarZza.zza()).zza(12).zzc(16).zza(zza(zzorVar.zza().zzd())).zza();
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing AesGcmParameters failed: ", e);
        }
    }

    public static /* synthetic */ zzor zza(zzes zzesVar) throws GeneralSecurityException {
        zzvs zzvsVar;
        if (zzesVar.zzd() != 16) {
            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d. Currently Tink only supports serialization of AES GCM keys with tag size equal to 16 bytes.", Integer.valueOf(zzesVar.zzd())));
        }
        if (zzesVar.zzb() != 12) {
            throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d. Currently Tink only supports serialization of AES GCM keys with IV size equal to 12 bytes.", Integer.valueOf(zzesVar.zzb())));
        }
        zzvb.zza zzaVarZza = zzvb.zza().zza("type.googleapis.com/google.crypto.tink.AesGcmKey").zza(((zzsv) ((zzajc) zzsv.zzc().zza(zzesVar.zzc()).zzf())).zzi());
        zzes.zzb zzbVarZzf = zzesVar.zzf();
        if (zzes.zzb.zza.equals(zzbVarZzf)) {
            zzvsVar = zzvs.TINK;
        } else if (zzes.zzb.zzb.equals(zzbVarZzf)) {
            zzvsVar = zzvs.CRUNCHY;
        } else if (zzes.zzb.zzc.equals(zzbVarZzf)) {
            zzvsVar = zzvs.RAW;
        } else {
            throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzbVarZzf));
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
