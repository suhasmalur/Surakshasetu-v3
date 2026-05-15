package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import com.google.android.gms.internal.p001firebaseauthapi.zzir;
import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzin extends zznb<zztc> {
    private static final zzno<zzir> zza = new zzno() { // from class: com.google.android.gms.internal.firebase-auth-api.zziq
    };

    public static int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzif.zza zzb() {
        return zzif.zza.zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzne<zztd, zztc> zzc() {
        return new zzis(this, zztd.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzuy.zza zzd() {
        return zzuy.zza.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zztc.zza(zzahpVar, zzaio.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final String zze() {
        return "type.googleapis.com/google.crypto.tink.AesSivKey";
    }

    zzin() {
        super(zztc.class, new zzip(zzbp.class));
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        zzct.zza((zznb) new zzin(), true);
        zziw.zza();
        zzns zznsVarZza = zzns.zza();
        HashMap map = new HashMap();
        map.put("AES256_SIV", zzjg.zza);
        map.put("AES256_SIV_RAW", zzir.zzc().zza(64).zza(zzir.zzb.zzc).zza());
        zznsVarZza.zza(Collections.unmodifiableMap(map));
        zznl.zza().zza(zza, zzir.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ void zza(zzakn zzaknVar) throws GeneralSecurityException {
        zztc zztcVar = (zztc) zzaknVar;
        zzxo.zza(zztcVar.zza(), 0);
        if (zztcVar.zzd().zzb() != 64) {
            throw new InvalidKeyException("invalid key size: " + zztcVar.zzd().zzb() + ". Valid keys must have 64 bytes.");
        }
    }
}
