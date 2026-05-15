package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzgn {
    private static final zzxt zza = zzpf.zzb("type.googleapis.com/google.crypto.tink.KmsAeadKey");
    private static final zznz<zzgk, zzor> zzb = zznz.zza(new zzob() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgm
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzob
        public final zzov zza(zzch zzchVar) {
            return zzor.zzb((zzvb) ((zzajc) zzvb.zza().zza("type.googleapis.com/google.crypto.tink.KmsAeadKey").zza(((zzvn) ((zzajc) zzvn.zza().zza(((zzgk) zzchVar).zzb()).zzf())).zzi()).zza(zzvs.RAW).zzf()));
        }
    }, zzgk.class, zzor.class);
    private static final zznv<zzor> zzc = zznv.zza(new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgp
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zznx
        public final zzch zza(zzov zzovVar) {
            return zzgn.zzb((zzor) zzovVar);
        }
    }, zza, zzor.class);
    private static final zzmx<zzgl, zzos> zzd = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgo
    }, zzgl.class, zzos.class);
    private static final zzmu<zzos> zze = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgr
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzgn.zzb((zzos) zzovVar, zzcsVar);
        }
    }, zza, zzos.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzgl zzb(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseKey");
        }
        if (zzosVar.zzc() != zzvs.RAW) {
            throw new GeneralSecurityException("KmsAeadKey are only accepted with RAW, got " + String.valueOf(zzosVar.zzc()));
        }
        try {
            zzvk zzvkVarZza = zzvk.zza(zzosVar.zzd(), zzaio.zza());
            if (zzvkVarZza.zza() == 0) {
                return zzgl.zza(zzgk.zza(zzvkVarZza.zzd().zzd()));
            }
            throw new GeneralSecurityException("KmsAeadKey are only accepted with version 0, got " + String.valueOf(zzvkVarZza));
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing KmsAeadKey failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzgk zzb(zzor zzorVar) throws GeneralSecurityException {
        if (!zzorVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseParameters: " + zzorVar.zza().zzf());
        }
        try {
            zzvn zzvnVarZza = zzvn.zza(zzorVar.zza().zze(), zzaio.zza());
            if (zzorVar.zza().zzd() != zzvs.RAW) {
                throw new GeneralSecurityException("Only key templates with RAW are accepted, but got " + String.valueOf(zzorVar.zza().zzd()) + " with format " + String.valueOf(zzvnVarZza));
            }
            return zzgk.zza(zzvnVarZza.zzd());
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing KmsAeadKeyFormat failed: ", e);
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
