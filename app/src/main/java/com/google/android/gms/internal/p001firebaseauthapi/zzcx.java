package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import com.google.android.gms.internal.p001firebaseauthapi.zzvg;
import com.google.android.gms.internal.p001firebaseauthapi.zzvj;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzcx {
    private static final Charset zza = Charset.forName("UTF-8");

    public static zzvj zza(zzvg zzvgVar) {
        zzvj.zzb zzbVarZza = zzvj.zza().zza(zzvgVar.zzb());
        for (zzvg.zzb zzbVar : zzvgVar.zze()) {
            zzbVarZza.zza((zzvj.zza) ((zzajc) zzvj.zza.zzb().zza(zzbVar.zzb().zzf()).zza(zzbVar.zzc()).zza(zzbVar.zzf()).zza(zzbVar.zza()).zzf()));
        }
        return (zzvj) ((zzajc) zzbVarZza.zzf());
    }

    public static void zzb(zzvg zzvgVar) throws GeneralSecurityException {
        int iZzb = zzvgVar.zzb();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (zzvg.zzb zzbVar : zzvgVar.zze()) {
            if (zzbVar.zzc() == zzuz.ENABLED) {
                if (!zzbVar.zzg()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", Integer.valueOf(zzbVar.zza())));
                }
                if (zzbVar.zzf() == zzvs.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", Integer.valueOf(zzbVar.zza())));
                }
                if (zzbVar.zzc() == zzuz.UNKNOWN_STATUS) {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", Integer.valueOf(zzbVar.zza())));
                }
                if (zzbVar.zza() == iZzb) {
                    if (z) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    z = true;
                }
                if (zzbVar.zzb().zzb() != zzuy.zza.ASYMMETRIC_PUBLIC) {
                    z2 = false;
                }
                i++;
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        }
        if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
