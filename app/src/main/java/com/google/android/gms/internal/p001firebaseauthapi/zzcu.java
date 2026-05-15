package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcu {
    public static zzch zza(byte[] bArr) throws GeneralSecurityException {
        try {
            zzvb zzvbVarZza = zzvb.zza(bArr, zzaio.zza());
            zznu zznuVarZza = zznu.zza();
            zzor zzorVarZza = zzor.zza(zzvbVarZza);
            return !zznuVarZza.zzb(zzorVarZza) ? new zznf(zzorVarZza) : zznuVarZza.zza(zzorVarZza);
        } catch (IOException e) {
            throw new GeneralSecurityException("Failed to parse proto", e);
        }
    }

    public static byte[] zza(zzch zzchVar) throws GeneralSecurityException {
        if (zzchVar instanceof zznf) {
            return ((zznf) zzchVar).zzb().zza().zzj();
        }
        return ((zzor) zznu.zza().zza(zzchVar, zzor.class)).zza().zzj();
    }
}
