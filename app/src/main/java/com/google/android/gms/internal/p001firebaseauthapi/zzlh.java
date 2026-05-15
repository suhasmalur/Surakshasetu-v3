package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzlh implements zzbr {
    private static final byte[] zza = new byte[0];
    private final zzuv zzb;
    private final zzlj zzc;
    private final zzlg zzd;
    private final zzld zze;

    static zzlh zza(zzuv zzuvVar) throws GeneralSecurityException {
        if (zzuvVar.zzf().zze()) {
            throw new IllegalArgumentException("HpkePublicKey.public_key is empty.");
        }
        zzur zzurVarZzb = zzuvVar.zzb();
        return new zzlh(zzuvVar, zzlk.zzc(zzurVarZzb), zzlk.zzb(zzurVarZzb), zzlk.zza(zzurVarZzb));
    }

    private zzlh(zzuv zzuvVar, zzlj zzljVar, zzlg zzlgVar, zzld zzldVar) {
        this.zzb = zzuvVar;
        this.zzc = zzljVar;
        this.zzd = zzlgVar;
        this.zze = zzldVar;
    }
}
