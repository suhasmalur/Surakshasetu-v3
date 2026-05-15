package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import com.google.android.gms.internal.p001firebaseauthapi.zzql;
import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzqg extends zznb<zzuc> {
    private static final zzod<zzqc, zzpz> zza = zzod.zza(new zzof() { // from class: com.google.android.gms.internal.firebase-auth-api.zzqf
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzof
        public final Object zza(zzbt zzbtVar) {
            return new zzrf((zzqc) zzbtVar);
        }
    }, zzqc.class, zzpz.class);
    private static final zzod<zzqc, zzce> zzb = zzod.zza(new zzof() { // from class: com.google.android.gms.internal.firebase-auth-api.zzqi
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzof
        public final Object zza(zzbt zzbtVar) {
            return zzxm.zza((zzqc) zzbtVar);
        }
    }, zzqc.class, zzce.class);
    private static final zzno<zzql> zzc = new zzno() { // from class: com.google.android.gms.internal.firebase-auth-api.zzqh
    };

    public static int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzif.zza zzb() {
        return zzif.zza.zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzne<zzug, zzuc> zzc() {
        return new zzqj(this, zzug.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzuy.zza zzd() {
        return zzuy.zza.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zzuc.zza(zzahpVar, zzaio.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final String zze() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    public zzqg() {
        super(zzuc.class, new zzqk(zzce.class));
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        zzct.zza((zznb) new zzqg(), true);
        zzre.zza();
        zznr.zza().zza(zza);
        zznr.zza().zza(zzb);
        zzns zznsVarZza = zzns.zza();
        HashMap map = new HashMap();
        map.put("HMAC_SHA256_128BITTAG", zzqx.zza);
        map.put("HMAC_SHA256_128BITTAG_RAW", zzql.zzd().zza(32).zzb(16).zza(zzql.zzb.zzd).zza(zzql.zzc.zzc).zza());
        map.put("HMAC_SHA256_256BITTAG", zzql.zzd().zza(32).zzb(32).zza(zzql.zzb.zza).zza(zzql.zzc.zzc).zza());
        map.put("HMAC_SHA256_256BITTAG_RAW", zzql.zzd().zza(32).zzb(32).zza(zzql.zzb.zzd).zza(zzql.zzc.zzc).zza());
        map.put("HMAC_SHA512_128BITTAG", zzql.zzd().zza(64).zzb(16).zza(zzql.zzb.zza).zza(zzql.zzc.zze).zza());
        map.put("HMAC_SHA512_128BITTAG_RAW", zzql.zzd().zza(64).zzb(16).zza(zzql.zzb.zzd).zza(zzql.zzc.zze).zza());
        map.put("HMAC_SHA512_256BITTAG", zzql.zzd().zza(64).zzb(32).zza(zzql.zzb.zza).zza(zzql.zzc.zze).zza());
        map.put("HMAC_SHA512_256BITTAG_RAW", zzql.zzd().zza(64).zzb(32).zza(zzql.zzb.zzd).zza(zzql.zzc.zze).zza());
        map.put("HMAC_SHA512_512BITTAG", zzqx.zzb);
        map.put("HMAC_SHA512_512BITTAG_RAW", zzql.zzd().zza(64).zzb(64).zza(zzql.zzb.zzd).zza(zzql.zzc.zze).zza());
        zznsVarZza.zza(Collections.unmodifiableMap(map));
        zznl.zza().zza(zzc, zzql.class);
    }

    public static void zza(zzuc zzucVar) throws GeneralSecurityException {
        zzxo.zza(zzucVar.zza(), 0);
        if (zzucVar.zzf().zzb() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zzb(zzucVar.zze());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* bridge */ /* synthetic */ void zza(zzakn zzaknVar) throws GeneralSecurityException {
        zza((zzuc) zzaknVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(zzuh zzuhVar) throws GeneralSecurityException {
        if (zzuhVar.zza() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        switch (zzuhVar.zzb()) {
            case SHA1:
                if (zzuhVar.zza() > 20) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            case SHA224:
                if (zzuhVar.zza() > 28) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            case SHA256:
                if (zzuhVar.zza() > 32) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            case SHA384:
                if (zzuhVar.zza() > 48) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            case SHA512:
                if (zzuhVar.zza() > 64) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            default:
                throw new GeneralSecurityException("unknown hash type");
        }
    }
}
