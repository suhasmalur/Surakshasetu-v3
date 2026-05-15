package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzgs;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzgu {
    private static final zzxt zza = zzpf.zzb("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey");
    private static final zznz<zzgs, zzor> zzb = zznz.zza(new zzob() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgx
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzob
        public final zzov zza(zzch zzchVar) {
            return zzor.zzb((zzvb) ((zzajc) zzvb.zza().zza("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey").zza(zzgu.zzb((zzgs) zzchVar).zzi()).zza(zzvs.RAW).zzf()));
        }
    }, zzgs.class, zzor.class);
    private static final zznv<zzor> zzc = zznv.zza(new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgw
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zznx
        public final zzch zza(zzov zzovVar) {
            return zzgu.zzb((zzor) zzovVar);
        }
    }, zza, zzor.class);
    private static final zzmx<zzgq, zzos> zzd = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgz
    }, zzgq.class, zzos.class);
    private static final zzmu<zzos> zze = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgy
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzgu.zzb((zzos) zzovVar, zzcsVar);
        }
    }, zza, zzos.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzgq zzb(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseKey");
        }
        try {
            zzvo zzvoVarZza = zzvo.zza(zzosVar.zzd(), zzaio.zza());
            if (zzosVar.zzc() != zzvs.RAW) {
                throw new GeneralSecurityException("KmsEnvelopeAeadKeys are only accepted with OutputPrefixType RAW, got " + String.valueOf(zzvoVarZza));
            }
            if (zzvoVarZza.zza() == 0) {
                return zzgq.zza(zza(zzvoVarZza.zzd()));
            }
            throw new GeneralSecurityException("KmsEnvelopeAeadKeys are only accepted with version 0, got " + String.valueOf(zzvoVarZza));
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKey failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzgs zzb(zzor zzorVar) throws GeneralSecurityException {
        if (!zzorVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseParameters: " + zzorVar.zza().zzf());
        }
        try {
            return zza(zzvr.zza(zzorVar.zza().zze(), zzaio.zza()));
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e);
        }
    }

    private static zzgs zza(zzvr zzvrVar) throws GeneralSecurityException {
        zzgs.zzb zzbVar;
        zzch zzchVarZza = zzcu.zza(((zzvb) ((zzajc) zzvb.zza().zza(zzvrVar.zza().zzf()).zza(zzvrVar.zza().zze()).zza(zzvs.RAW).zzf())).zzj());
        if (zzchVarZza instanceof zzes) {
            zzbVar = zzgs.zzb.zza;
        } else if (zzchVarZza instanceof zzfv) {
            zzbVar = zzgs.zzb.zzc;
        } else if (zzchVarZza instanceof zzhm) {
            zzbVar = zzgs.zzb.zzb;
        } else if (zzchVarZza instanceof zzdl) {
            zzbVar = zzgs.zzb.zzd;
        } else if (zzchVarZza instanceof zzed) {
            zzbVar = zzgs.zzb.zze;
        } else if (zzchVarZza instanceof zzfj) {
            zzbVar = zzgs.zzb.zzf;
        } else {
            throw new GeneralSecurityException("Unsupported DEK parameters when parsing " + String.valueOf(zzchVarZza));
        }
        return new zzgs.zza().zza(zzvrVar.zze()).zza((zzdb) zzchVarZza).zza(zzbVar).zza();
    }

    private static zzvr zzb(zzgs zzgsVar) throws GeneralSecurityException {
        try {
            return (zzvr) ((zzajc) zzvr.zzb().zza(zzgsVar.zzc()).zza(zzvb.zza(zzcu.zza(zzgsVar.zzb()), zzaio.zza())).zzf());
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e);
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
