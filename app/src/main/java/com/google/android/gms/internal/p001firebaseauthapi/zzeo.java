package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzes;
import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzeo extends zznb<zzsu> {
    private static final zzno<zzes> zza = new zzno() { // from class: com.google.android.gms.internal.firebase-auth-api.zzer
    };

    public static int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzif.zza zzb() {
        return zzif.zza.zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzne<zzsv, zzsu> zzc() {
        return new zzet(this, zzsv.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzuy.zza zzd() {
        return zzuy.zza.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zzsu.zza(zzahpVar, zzaio.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final String zze() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    zzeo() {
        super(zzsu.class, new zzeq(zzbg.class));
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        zzct.zza((zznb) new zzeo(), true);
        zzex.zza();
        zzns zznsVarZza = zzns.zza();
        HashMap map = new HashMap();
        map.put("AES128_GCM", zzhb.zza);
        map.put("AES128_GCM_RAW", zzes.zze().zza(12).zzb(16).zzc(16).zza(zzes.zzb.zzc).zza());
        map.put("AES256_GCM", zzhb.zzb);
        map.put("AES256_GCM_RAW", zzes.zze().zza(12).zzb(32).zzc(16).zza(zzes.zzb.zzc).zza());
        zznsVarZza.zza(Collections.unmodifiableMap(map));
        zznl.zza().zza(zza, zzes.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ void zza(zzakn zzaknVar) throws GeneralSecurityException {
        zzsu zzsuVar = (zzsu) zzaknVar;
        zzxo.zza(zzsuVar.zza(), 0);
        zzxo.zza(zzsuVar.zzd().zzb());
    }
}
