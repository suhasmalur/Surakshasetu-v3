package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzqt implements zzcp<zzce, zzce> {
    private static final zzqt zza = new zzqt();
    private static final zzod<zznd, zzce> zzb = zzod.zza(new zzof() { // from class: com.google.android.gms.internal.firebase-auth-api.zzqs
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzof
        public final Object zza(zzbt zzbtVar) {
            return zzrl.zza((zznd) zzbtVar);
        }
    }, zznd.class, zzce.class);

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcp
    public final Class<zzce> zza() {
        return zzce.class;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcp
    public final Class<zzce> zzb() {
        return zzce.class;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcp
    public final /* synthetic */ zzce zza(zzcg<zzce> zzcgVar) throws GeneralSecurityException {
        Iterator<List<zzcl<zzce>>> it = zzcgVar.zzd().iterator();
        while (it.hasNext()) {
            for (zzcl<zzce> zzclVar : it.next()) {
                if (zzclVar.zzb() instanceof zzqr) {
                    zzqr zzqrVar = (zzqr) zzclVar.zzb();
                    zzxt zzxtVarZza = zzxt.zza(zzclVar.zzh());
                    if (!zzxtVarZza.equals(zzqrVar.zzb())) {
                        throw new GeneralSecurityException("Mac Key with parameters " + String.valueOf(zzqrVar.zza()) + " has wrong output prefix (" + String.valueOf(zzqrVar.zzb()) + ") instead of (" + String.valueOf(zzxtVarZza) + ")");
                    }
                }
            }
        }
        return new zzqv(zzcgVar);
    }

    zzqt() {
    }

    public static void zzc() throws GeneralSecurityException {
        zzct.zza(zza);
        zznr.zza().zza(zzb);
    }
}
