package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzfj;
import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzfg extends zznb<zzsy> {
    private static final zzno<zzfj> zza = new zzno() { // from class: com.google.android.gms.internal.firebase-auth-api.zzff
    };

    public static int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzif.zza zzb() {
        return zzif.zza.zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzne<zzsz, zzsy> zzc() {
        return new zzfh(this, zzsz.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzuy.zza zzd() {
        return zzuy.zza.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zzsy.zza(zzahpVar, zzaio.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final String zze() {
        return "type.googleapis.com/google.crypto.tink.AesGcmSivKey";
    }

    zzfg() {
        super(zzsy.class, new zzfi(zzbg.class));
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzi()) {
            zzct.zza((zznb) new zzfg(), true);
            zzfl.zza();
            zzns zznsVarZza = zzns.zza();
            HashMap map = new HashMap();
            map.put("AES128_GCM_SIV", zzfj.zzc().zza(16).zza(zzfj.zzb.zza).zza());
            map.put("AES128_GCM_SIV_RAW", zzfj.zzc().zza(16).zza(zzfj.zzb.zzc).zza());
            map.put("AES256_GCM_SIV", zzfj.zzc().zza(32).zza(zzfj.zzb.zza).zza());
            map.put("AES256_GCM_SIV_RAW", zzfj.zzc().zza(32).zza(zzfj.zzb.zzc).zza());
            zznsVarZza.zza(Collections.unmodifiableMap(map));
            zznl.zza().zza(zza, zzfj.class);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ void zza(zzakn zzaknVar) throws GeneralSecurityException {
        zzsy zzsyVar = (zzsy) zzaknVar;
        zzxo.zza(zzsyVar.zza(), 0);
        zzxo.zza(zzsyVar.zzd().zzb());
    }

    private static boolean zzi() {
        try {
            Cipher.getInstance("AES/GCM-SIV/NoPadding");
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            return false;
        }
    }
}
