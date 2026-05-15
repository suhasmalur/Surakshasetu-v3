package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzka;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzke {
    private static final zzxt zza = zzpf.zzb("type.googleapis.com/google.crypto.tink.HpkePrivateKey");
    private static final zzxt zzb = zzpf.zzb("type.googleapis.com/google.crypto.tink.HpkePublicKey");
    private static final zznz<zzka, zzor> zzc = zznz.zza(new zzob() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkh
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzob
        public final zzov zza(zzch zzchVar) {
            zzka zzkaVar = (zzka) zzchVar;
            return zzor.zzb((zzvb) ((zzajc) zzvb.zza().zza("type.googleapis.com/google.crypto.tink.HpkePrivateKey").zza(((zzuq) ((zzajc) zzuq.zza().zza((zzur) ((zzajc) zzur.zzd().zza((zzuo) zzke.zzj.zza(zzkaVar.zze())).zza((zzum) zzke.zzk.zza(zzkaVar.zzd())).zza((zzuk) zzke.zzl.zza(zzkaVar.zzb())).zzf())).zzf())).zzi()).zza((zzvs) zzke.zzi.zza(zzkaVar.zzf())).zzf()));
        }
    }, zzka.class, zzor.class);
    private static final zznv<zzor> zzd = zznv.zza(new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkg
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zznx
        public final zzch zza(zzov zzovVar) {
            return zzke.zzb((zzor) zzovVar);
        }
    }, zza, zzor.class);
    private static final zzmx<zzkn, zzos> zze = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkj
    }, zzkn.class, zzos.class);
    private static final zzmu<zzos> zzf = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzki
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzke.zzd((zzos) zzovVar, zzcsVar);
        }
    }, zzb, zzos.class);
    private static final zzmx<zzkf, zzos> zzg = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkl
    }, zzkf.class, zzos.class);
    private static final zzmu<zzos> zzh = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkk
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zzke.zzc((zzos) zzovVar, zzcsVar);
        }
    }, zza, zzos.class);
    private static final zzmi<zzvs, zzka.zzf> zzi = zzmi.zza().zza(zzvs.RAW, zzka.zzf.zzc).zza(zzvs.TINK, zzka.zzf.zza).zza(zzvs.LEGACY, zzka.zzf.zzb).zza(zzvs.CRUNCHY, zzka.zzf.zzb).zza();
    private static final zzmi<zzuo, zzka.zzd> zzj = zzmi.zza().zza(zzuo.DHKEM_P256_HKDF_SHA256, zzka.zzd.zza).zza(zzuo.DHKEM_P384_HKDF_SHA384, zzka.zzd.zzb).zza(zzuo.DHKEM_P521_HKDF_SHA512, zzka.zzd.zzc).zza(zzuo.DHKEM_X25519_HKDF_SHA256, zzka.zzd.zzd).zza();
    private static final zzmi<zzum, zzka.zze> zzk = zzmi.zza().zza(zzum.HKDF_SHA256, zzka.zze.zza).zza(zzum.HKDF_SHA384, zzka.zze.zzb).zza(zzum.HKDF_SHA512, zzka.zze.zzc).zza();
    private static final zzmi<zzuk, zzka.zza> zzl = zzmi.zza().zza(zzuk.AES_128_GCM, zzka.zza.zza).zza(zzuk.AES_256_GCM, zzka.zza.zzb).zza(zzuk.CHACHA20_POLY1305, zzka.zza.zzc).zza();

    private static zzka zza(zzvs zzvsVar, zzur zzurVar) throws GeneralSecurityException {
        return zzka.zzc().zza(zzi.zza(zzvsVar)).zza(zzj.zza(zzurVar.zzc())).zza(zzk.zza(zzurVar.zzb())).zza(zzl.zza(zzurVar.zza())).zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzka zzb(zzor zzorVar) throws GeneralSecurityException {
        if (!zzorVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.HpkePrivateKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to HpkeProtoSerialization.parseParameters: " + zzorVar.zza().zzf());
        }
        try {
            return zza(zzorVar.zza().zzd(), zzuq.zza(zzorVar.zza().zze(), zzaio.zza()).zzc());
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing HpkeParameters failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzkf zzc(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.HpkePrivateKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to HpkeProtoSerialization.parsePrivateKey: " + zzosVar.zzf());
        }
        try {
            zzuu zzuuVarZza = zzuu.zza(zzosVar.zzd(), zzaio.zza());
            if (zzuuVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            zzuv zzuvVarZzd = zzuuVarZza.zzd();
            return zzkf.zza(zzkn.zza(zza(zzosVar.zzc(), zzuvVarZzd.zzb()), zza(zzuvVarZzd.zzb().zzc(), zzuvVarZzd.zzf().zzg()), zzosVar.zze()), zzxu.zza(zzme.zza(zzme.zza(zzuuVarZza.zze().zzg()), zzlt.zza(zzuvVarZzd.zzb().zzc())), zzcs.zza(zzcsVar)));
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing HpkePrivateKey failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzkn zzd(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.HpkePublicKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to HpkeProtoSerialization.parsePublicKey: " + zzosVar.zzf());
        }
        try {
            zzuv zzuvVarZza = zzuv.zza(zzosVar.zzd(), zzaio.zza());
            if (zzuvVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return zzkn.zza(zza(zzosVar.zzc(), zzuvVarZza.zzb()), zza(zzuvVarZza.zzb().zzc(), zzuvVarZza.zzf().zzg()), zzosVar.zze());
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing HpkePublicKey failed");
        }
    }

    private static zzxt zza(zzuo zzuoVar, byte[] bArr) throws GeneralSecurityException {
        return zzxt.zza(zzme.zza(zzme.zza(bArr), zzlt.zzb(zzuoVar)));
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
