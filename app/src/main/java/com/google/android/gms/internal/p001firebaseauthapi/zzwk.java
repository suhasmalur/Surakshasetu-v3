package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzwk implements zzbr {
    private static final byte[] zza = new byte[0];
    private final zzwm zzb;
    private final String zzc;
    private final byte[] zzd;
    private final zzwr zze;
    private final zzwi zzf;

    public zzwk(ECPublicKey eCPublicKey, byte[] bArr, String str, zzwr zzwrVar, zzwi zzwiVar) throws GeneralSecurityException {
        zzmg.zza(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        this.zzb = new zzwm(eCPublicKey);
        this.zzd = bArr;
        this.zzc = str;
        this.zze = zzwrVar;
        this.zzf = zzwiVar;
    }
}
