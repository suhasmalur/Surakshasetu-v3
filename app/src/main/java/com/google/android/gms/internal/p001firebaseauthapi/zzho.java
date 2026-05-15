package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzhm;
import com.google.android.gms.internal.p001firebaseauthapi.zzvb;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzho {
    private static final zzxt zza = zzpf.zzb("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
    private static final zznz<zzhm, zzor> zzb = zznz.zza(new zzob() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhn
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzob
        public final zzov zza(zzch zzchVar) {
            return zzho.zza((zzhm) zzchVar);
        }
    }, zzhm.class, zzor.class);
    private static final zznv<zzor> zzc = zznv.zza(new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhq
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zznx
        public final zzch zza(zzov zzovVar) {
            return zzho.zzb((zzor) zzovVar);
        }
    }, zza, zzor.class);
    private static final zzmx<zzhg, zzos> zzd = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhp
    }, zzhg.class, zzos.class);
    private static final zzmu<zzos> zze = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhs
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzho.zzb((zzos) zzovVar, zzcsVar);
        }
    }, zza, zzos.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzhg zzb(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseKey");
        }
        try {
            zzvx zzvxVarZza = zzvx.zza(zzosVar.zzd(), zzaio.zza());
            if (zzvxVarZza.zza() == 0) {
                return zzhg.zza(zza(zzosVar.zzc()), zzxu.zza(zzvxVarZza.zzd().zzg(), zzcs.zza(zzcsVar)), zzosVar.zze());
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing XChaCha20Poly1305Key failed");
        }
    }

    private static zzhm.zza zza(zzvs zzvsVar) throws GeneralSecurityException {
        switch (zzhr.zza[zzvsVar.ordinal()]) {
            case 1:
                return zzhm.zza.zza;
            case 2:
            case 3:
                return zzhm.zza.zzb;
            case 4:
                return zzhm.zza.zzc;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzvsVar.zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzhm zzb(zzor zzorVar) throws GeneralSecurityException {
        if (!zzorVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseParameters: " + zzorVar.zza().zzf());
        }
        try {
            if (zzvy.zza(zzorVar.zza().zze(), zzaio.zza()).zza() != 0) {
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            }
            return zzhm.zza(zza(zzorVar.zza().zzd()));
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing XChaCha20Poly1305Parameters failed: ", e);
        }
    }

    public static /* synthetic */ zzor zza(zzhm zzhmVar) throws GeneralSecurityException {
        zzvs zzvsVar;
        zzvb.zza zzaVarZza = zzvb.zza().zza("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key").zza(zzvy.zzc().zzi());
        zzhm.zza zzaVarZzb = zzhmVar.zzb();
        if (zzhm.zza.zza.equals(zzaVarZzb)) {
            zzvsVar = zzvs.TINK;
        } else if (zzhm.zza.zzb.equals(zzaVarZzb)) {
            zzvsVar = zzvs.CRUNCHY;
        } else if (zzhm.zza.zzc.equals(zzaVarZzb)) {
            zzvsVar = zzvs.RAW;
        } else {
            throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzaVarZzb));
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
