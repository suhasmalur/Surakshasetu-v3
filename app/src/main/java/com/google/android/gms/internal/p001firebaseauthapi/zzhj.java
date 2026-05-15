package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzhm;
import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzhj extends zznb<zzvx> {
    private static final zzno<zzhm> zza = new zzno() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhi
    };

    public static int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzif.zza zzb() {
        return zzif.zza.zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzne<zzvy, zzvx> zzc() {
        return new zzhk(this, zzvy.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final zzuy.zza zzd() {
        return zzuy.zza.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zzvx.zza(zzahpVar, zzaio.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final String zze() {
        return "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    }

    zzhj() {
        super(zzvx.class, new zzhl(zzbg.class));
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        zzct.zza((zznb) new zzhj(), true);
        zzho.zza();
        zzns zznsVarZza = zzns.zza();
        HashMap map = new HashMap();
        map.put("XCHACHA20_POLY1305", zzhm.zza(zzhm.zza.zza));
        map.put("XCHACHA20_POLY1305_RAW", zzhm.zza(zzhm.zza.zzc));
        zznsVarZza.zza(Collections.unmodifiableMap(map));
        zznl.zza().zza(zza, zzhm.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zznb
    public final /* synthetic */ void zza(zzakn zzaknVar) throws GeneralSecurityException {
        zzvx zzvxVar = (zzvx) zzaknVar;
        zzxo.zza(zzvxVar.zza(), 0);
        if (zzvxVar.zzd().zzb() != 32) {
            throw new GeneralSecurityException("invalid XChaCha20Poly1305Key: incorrect key length");
        }
    }
}
