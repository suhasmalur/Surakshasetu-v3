package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zznh {
    public static final zzrr zza = new zznj();

    public static <P> zzrq zza(zzcg<P> zzcgVar) {
        zzbv zzbvVar;
        zzrt zzrtVar = new zzrt();
        zzrtVar.zza(zzcgVar.zzb());
        Iterator<List<zzcl<P>>> it = zzcgVar.zzd().iterator();
        while (it.hasNext()) {
            for (zzcl<P> zzclVar : it.next()) {
                switch (zznk.zza[zzclVar.zzc().ordinal()]) {
                    case 1:
                        zzbvVar = zzbv.zza;
                        break;
                    case 2:
                        zzbvVar = zzbv.zzb;
                        break;
                    case 3:
                        zzbvVar = zzbv.zzc;
                        break;
                    default:
                        throw new IllegalStateException("Unknown key status");
                }
                int iZza = zzclVar.zza();
                String strZzg = zzclVar.zzg();
                if (strZzg.startsWith("type.googleapis.com/google.crypto.")) {
                    strZzg = strZzg.substring(34);
                }
                zzrtVar.zza(zzbvVar, iZza, strZzg, zzclVar.zzd().name());
            }
        }
        if (zzcgVar.zza() != null) {
            zzrtVar.zza(zzcgVar.zza().zza());
        }
        try {
            return zzrtVar.zza();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
