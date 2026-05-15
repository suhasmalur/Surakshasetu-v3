package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzdl;
import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzdi extends zznb<zzse> {
    private static final zzno<zzdl> zza = new zzno() { // from class: com.google.android.gms.internal.firebase-auth-api.zzdh
    };

    public static int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzif.zza zzb() {
        return zzif.zza.zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzne<zzsf, zzse> zzc() {
        return new zzdj(this, zzsf.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzuy.zza zzd() {
        return zzuy.zza.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zzse.zza(zzahpVar, zzaio.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final String zze() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    zzdi() {
        super(zzse.class, new zzdk(zzbg.class));
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        zzct.zza((zznb) new zzdi(), true);
        zzdp.zza();
        zzns zznsVarZza = zzns.zza();
        HashMap map = new HashMap();
        map.put("AES128_CTR_HMAC_SHA256", zzhb.zze);
        map.put("AES128_CTR_HMAC_SHA256_RAW", zzdl.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzdl.zzb.zzc).zza(zzdl.zzc.zzc).zza());
        map.put("AES256_CTR_HMAC_SHA256", zzhb.zzf);
        map.put("AES256_CTR_HMAC_SHA256_RAW", zzdl.zzf().zza(32).zzb(32).zzd(32).zzc(16).zza(zzdl.zzb.zzc).zza(zzdl.zzc.zzc).zza());
        zznsVarZza.zza(Collections.unmodifiableMap(map));
        zznl.zza().zza(zza, zzdl.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ void zza(zzakn zzaknVar) throws GeneralSecurityException {
        zzse zzseVar = (zzse) zzaknVar;
        zzxo.zza(zzseVar.zza(), 0);
        new zzdv();
        zzdv.zza(zzseVar.zzd());
        new zzqg();
        zzqg.zza(zzseVar.zze());
    }
}
