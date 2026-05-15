package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzdl;
import com.google.android.gms.internal.p001firebaseauthapi.zzes;
import com.google.android.gms.internal.p001firebaseauthapi.zzjl;
import com.google.android.gms.internal.p001firebaseauthapi.zzka;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzkm {
    private static final String zza = new zzjj().zze();
    private static final String zzb = new zzji().zze();

    @Deprecated
    private static final zzvt zzc = zzvt.zzb();

    @Deprecated
    private static final zzvt zzd = zzvt.zzb();

    @Deprecated
    private static final zzvt zze = zzvt.zzb();

    static {
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzkp.zzc();
        zzkr.zzc();
        zzcw.zza();
        zziz.zza();
        if (zzif.zzb()) {
            return;
        }
        zzct.zza((zzop) new zzji(), (zznb) new zzjj(), true);
        zzjt.zza();
        zzns zznsVarZza = zzns.zza();
        HashMap map = new HashMap();
        map.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM", zzjl.zzc().zza(zzjl.zzc.zza).zza(zzjl.zzb.zzc).zza(zzjl.zze.zzb).zza(zzjl.zzd.zza).zza(zzes.zze().zza(12).zzb(16).zzc(16).zza(zzes.zzb.zzc).zza()).zza());
        map.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzjl.zzc().zza(zzjl.zzc.zza).zza(zzjl.zzb.zzc).zza(zzjl.zze.zzb).zza(zzjl.zzd.zzc).zza(zzes.zze().zza(12).zzb(16).zzc(16).zza(zzes.zzb.zzc).zza()).zza());
        map.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM", zzjl.zzc().zza(zzjl.zzc.zza).zza(zzjl.zzb.zzc).zza(zzjl.zze.zza).zza(zzjl.zzd.zza).zza(zzes.zze().zza(12).zzb(16).zzc(16).zza(zzes.zzb.zzc).zza()).zza());
        map.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzjl.zzc().zza(zzjl.zzc.zza).zza(zzjl.zzb.zzc).zza(zzjl.zze.zza).zza(zzjl.zzd.zzc).zza(zzes.zze().zza(12).zzb(16).zzc(16).zza(zzes.zzb.zzc).zza()).zza());
        map.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX", zzjl.zzc().zza(zzjl.zzc.zza).zza(zzjl.zzb.zzc).zza(zzjl.zze.zza).zza(zzjl.zzd.zzc).zza(zzes.zze().zza(12).zzb(16).zzc(16).zza(zzes.zzb.zzc).zza()).zza());
        map.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzjl.zzc().zza(zzjl.zzc.zza).zza(zzjl.zzb.zzc).zza(zzjl.zze.zzb).zza(zzjl.zzd.zza).zza(zzdl.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzdl.zzb.zzc).zza(zzdl.zzc.zzc).zza()).zza());
        map.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzjl.zzc().zza(zzjl.zzc.zza).zza(zzjl.zzb.zzc).zza(zzjl.zze.zzb).zza(zzjl.zzd.zzc).zza(zzdl.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzdl.zzb.zzc).zza(zzdl.zzc.zzc).zza()).zza());
        map.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzjl.zzc().zza(zzjl.zzc.zza).zza(zzjl.zzb.zzc).zza(zzjl.zze.zza).zza(zzjl.zzd.zza).zza(zzdl.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzdl.zzb.zzc).zza(zzdl.zzc.zzc).zza()).zza());
        map.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzjl.zzc().zza(zzjl.zzc.zza).zza(zzjl.zzb.zzc).zza(zzjl.zze.zza).zza(zzjl.zzd.zzc).zza(zzdl.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzdl.zzb.zzc).zza(zzdl.zzc.zzc).zza()).zza());
        zznsVarZza.zza(Collections.unmodifiableMap(map));
        zzct.zza((zzop) new zzln(), (zznb) new zzlr(), true);
        zzke.zza();
        zzns zznsVarZza2 = zzns.zza();
        HashMap map2 = new HashMap();
        map2.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zzka.zzc().zza(zzka.zzf.zza).zza(zzka.zzd.zzd).zza(zzka.zze.zza).zza(zzka.zza.zza).zza());
        map2.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzka.zzc().zza(zzka.zzf.zzc).zza(zzka.zzd.zzd).zza(zzka.zze.zza).zza(zzka.zza.zza).zza());
        map2.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzka.zzc().zza(zzka.zzf.zza).zza(zzka.zzd.zzd).zza(zzka.zze.zza).zza(zzka.zza.zzb).zza());
        map2.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzka.zzc().zza(zzka.zzf.zzc).zza(zzka.zzd.zzd).zza(zzka.zze.zza).zza(zzka.zza.zzb).zza());
        map2.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305", zzka.zzc().zza(zzka.zzf.zza).zza(zzka.zzd.zzd).zza(zzka.zze.zza).zza(zzka.zza.zzc).zza());
        map2.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305_RAW", zzka.zzc().zza(zzka.zzf.zzc).zza(zzka.zzd.zzd).zza(zzka.zze.zza).zza(zzka.zza.zzc).zza());
        map2.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zzka.zzc().zza(zzka.zzf.zza).zza(zzka.zzd.zza).zza(zzka.zze.zza).zza(zzka.zza.zza).zza());
        map2.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzka.zzc().zza(zzka.zzf.zzc).zza(zzka.zzd.zza).zza(zzka.zze.zza).zza(zzka.zza.zza).zza());
        map2.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzka.zzc().zza(zzka.zzf.zza).zza(zzka.zzd.zza).zza(zzka.zze.zza).zza(zzka.zza.zzb).zza());
        map2.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzka.zzc().zza(zzka.zzf.zzc).zza(zzka.zzd.zza).zza(zzka.zze.zza).zza(zzka.zza.zzb).zza());
        map2.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM", zzka.zzc().zza(zzka.zzf.zza).zza(zzka.zzd.zzb).zza(zzka.zze.zzb).zza(zzka.zza.zza).zza());
        map2.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM_RAW", zzka.zzc().zza(zzka.zzf.zzc).zza(zzka.zzd.zzb).zza(zzka.zze.zzb).zza(zzka.zza.zza).zza());
        map2.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM", zzka.zzc().zza(zzka.zzf.zza).zza(zzka.zzd.zzb).zza(zzka.zze.zzb).zza(zzka.zza.zzb).zza());
        map2.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM_RAW", zzka.zzc().zza(zzka.zzf.zzc).zza(zzka.zzd.zzb).zza(zzka.zze.zzb).zza(zzka.zza.zzb).zza());
        map2.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM", zzka.zzc().zza(zzka.zzf.zza).zza(zzka.zzd.zzc).zza(zzka.zze.zzc).zza(zzka.zza.zza).zza());
        map2.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM_RAW", zzka.zzc().zza(zzka.zzf.zzc).zza(zzka.zzd.zzc).zza(zzka.zze.zzc).zza(zzka.zza.zza).zza());
        map2.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM", zzka.zzc().zza(zzka.zzf.zza).zza(zzka.zzd.zzc).zza(zzka.zze.zzc).zza(zzka.zza.zzb).zza());
        map2.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM_RAW", zzka.zzc().zza(zzka.zzf.zzc).zza(zzka.zzd.zzc).zza(zzka.zze.zzc).zza(zzka.zza.zzb).zza());
        zznsVarZza2.zza(Collections.unmodifiableMap(map2));
    }
}
