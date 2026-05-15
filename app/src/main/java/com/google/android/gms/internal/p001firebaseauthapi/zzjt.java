package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzjl;
import com.google.android.gms.internal.p001firebaseauthapi.zztv;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzjt {
    private static final zzxt zza = zzpf.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
    private static final zzxt zzb = zzpf.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey");
    private static final zznz<zzjl, zzor> zzc = zznz.zza(new zzob() { // from class: com.google.android.gms.internal.firebase-auth-api.zzjs
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzob
        public final zzov zza(zzch zzchVar) {
            zzjl zzjlVar = (zzjl) zzchVar;
            return zzor.zzb((zzvb) ((zzajc) zzvb.zza().zza("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey").zza(((zztn) ((zzajc) zztn.zza().zza(zzjt.zzb(zzjlVar)).zzf())).zzi()).zza((zzvs) zzjt.zzi.zza(zzjlVar.zzg())).zzf()));
        }
    }, zzjl.class, zzor.class);
    private static final zznv<zzor> zzd = zznv.zza(new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzjv
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zznx
        public final zzch zza(zzov zzovVar) {
            return zzjt.zzb((zzor) zzovVar);
        }
    }, zza, zzor.class);
    private static final zzmx<zzjy, zzos> zze = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzju
    }, zzjy.class, zzos.class);
    private static final zzmu<zzos> zzf = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzjx
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzjt.zzd((zzos) zzovVar, zzcsVar);
        }
    }, zzb, zzos.class);
    private static final zzmx<zzjq, zzos> zzg = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzjw
    }, zzjq.class, zzos.class);
    private static final zzmu<zzos> zzh = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzjz
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzjt.zzc((zzos) zzovVar, zzcsVar);
        }
    }, zza, zzos.class);
    private static final zzmi<zzvs, zzjl.zzd> zzi = zzmi.zza().zza(zzvs.RAW, zzjl.zzd.zzc).zza(zzvs.TINK, zzjl.zzd.zza).zza(zzvs.LEGACY, zzjl.zzd.zzb).zza(zzvs.CRUNCHY, zzjl.zzd.zzb).zza();
    private static final zzmi<zzub, zzjl.zzb> zzj = zzmi.zza().zza(zzub.SHA1, zzjl.zzb.zza).zza(zzub.SHA224, zzjl.zzb.zzb).zza(zzub.SHA256, zzjl.zzb.zzc).zza(zzub.SHA384, zzjl.zzb.zzd).zza(zzub.SHA512, zzjl.zzb.zze).zza();
    private static final zzmi<zzty, zzjl.zzc> zzk = zzmi.zza().zza(zzty.NIST_P256, zzjl.zzc.zza).zza(zzty.NIST_P384, zzjl.zzc.zzb).zza(zzty.NIST_P521, zzjl.zzc.zzc).zza(zzty.CURVE25519, zzjl.zzc.zzd).zza();
    private static final zzmi<zztk, zzjl.zze> zzl = zzmi.zza().zza(zztk.UNCOMPRESSED, zzjl.zze.zzb).zza(zztk.COMPRESSED, zzjl.zze.zza).zza(zztk.DO_NOT_USE_CRUNCHY_UNCOMPRESSED, zzjl.zze.zzc).zza();

    private static zzjl zza(zzvs zzvsVar, zztq zztqVar) throws GeneralSecurityException {
        zzjl.zza zzaVarZza = zzjl.zzc().zza(zzi.zza(zzvsVar)).zza(zzk.zza(zztqVar.zzf().zzd())).zza(zzj.zza(zztqVar.zzf().zze())).zza(zzcu.zza(((zzvb) ((zzajc) zzvb.zza().zza(zztqVar.zzb().zzd().zzf()).zza(zzvs.RAW).zza(zztqVar.zzb().zzd().zze()).zzf())).zzj())).zza(zzxt.zza(zztqVar.zzf().zzf().zzg()));
        if (!zztqVar.zzf().zzd().equals(zzty.CURVE25519)) {
            zzaVarZza.zza(zzl.zza(zztqVar.zza()));
        } else if (!zztqVar.zza().equals(zztk.COMPRESSED)) {
            throw new GeneralSecurityException("For CURVE25519 EcPointFormat must be compressed");
        }
        return zzaVarZza.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzjl zzb(zzor zzorVar) throws GeneralSecurityException {
        if (!zzorVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parseParameters: " + zzorVar.zza().zzf());
        }
        try {
            return zza(zzorVar.zza().zzd(), zztn.zza(zzorVar.zza().zze(), zzaio.zza()).zzc());
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing EciesParameters failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzjq zzc(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parsePrivateKey: " + zzosVar.zzf());
        }
        try {
            zztr zztrVarZza = zztr.zza(zzosVar.zzd(), zzaio.zza());
            if (zztrVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            zztu zztuVarZzd = zztrVarZza.zzd();
            zzjl zzjlVarZza = zza(zzosVar.zzc(), zztuVarZzd.zzb());
            if (zzjlVarZza.zzd().equals(zzjl.zzc.zzd)) {
                return zzjq.zza(zzjy.zza(zzjlVarZza, zzxt.zza(zztuVarZzd.zzf().zzg()), zzosVar.zze()), zzxu.zza(zztrVarZza.zze().zzg(), zzcs.zza(zzcsVar)));
            }
            return zzjq.zza(zzjy.zza(zzjlVarZza, new ECPoint(zzme.zza(zztuVarZzd.zzf().zzg()), zzme.zza(zztuVarZzd.zzg().zzg())), zzosVar.zze()), zzxs.zza(zzme.zza(zztrVarZza.zze().zzg()), zzcs.zza(zzcsVar)));
        } catch (zzaji | IllegalArgumentException e) {
            throw new GeneralSecurityException("Parsing EcdsaPrivateKey failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzjy zzd(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parsePublicKey: " + zzosVar.zzf());
        }
        try {
            zztu zztuVarZza = zztu.zza(zzosVar.zzd(), zzaio.zza());
            if (zztuVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            zzjl zzjlVarZza = zza(zzosVar.zzc(), zztuVarZza.zzb());
            if (zzjlVarZza.zzd().equals(zzjl.zzc.zzd)) {
                if (!zztuVarZza.zzg().zze()) {
                    throw new GeneralSecurityException("Y must be empty for X25519 points");
                }
                return zzjy.zza(zzjlVarZza, zzxt.zza(zztuVarZza.zzf().zzg()), zzosVar.zze());
            }
            return zzjy.zza(zzjlVarZza, new ECPoint(zzme.zza(zztuVarZza.zzf().zzg()), zzme.zza(zztuVarZza.zzg().zzg())), zzosVar.zze());
        } catch (zzaji | IllegalArgumentException e) {
            throw new GeneralSecurityException("Parsing EcdsaPublicKey failed");
        }
    }

    private static zztq zzb(zzjl zzjlVar) throws GeneralSecurityException {
        zztv.zza zzaVarZza = zztv.zza().zza((zzty) zzk.zza(zzjlVar.zzd())).zza((zzub) zzj.zza(zzjlVar.zze()));
        if (zzjlVar.zzh() != null && zzjlVar.zzh().zza() > 0) {
            zzaVarZza.zza(zzahp.zza(zzjlVar.zzh().zzb()));
        }
        zztv zztvVar = (zztv) ((zzajc) zzaVarZza.zzf());
        try {
            zzvb zzvbVarZza = zzvb.zza(zzcu.zza(zzjlVar.zzb()), zzaio.zza());
            zztm zztmVar = (zztm) ((zzajc) zztm.zza().zza((zzvb) ((zzajc) zzvb.zza().zza(zzvbVarZza.zzf()).zza(zzvs.TINK).zza(zzvbVarZza.zze()).zzf())).zzf());
            zzjl.zze zzeVarZzf = zzjlVar.zzf();
            if (zzeVarZzf == null) {
                zzeVarZzf = zzjl.zze.zza;
            }
            return (zztq) ((zzajc) zztq.zzc().zza(zztvVar).zza(zztmVar).zza((zztk) zzl.zza(zzeVarZzf)).zzf());
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing EciesParameters failed: ", e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zznu zznuVarZza = zznu.zza();
        zznuVarZza.zza(zzc);
        zznuVarZza.zza(zzd);
        zznuVarZza.zza(zze);
        zznuVarZza.zza(zzf);
        zznuVarZza.zza(zzg);
        zznuVarZza.zza(zzh);
    }
}
