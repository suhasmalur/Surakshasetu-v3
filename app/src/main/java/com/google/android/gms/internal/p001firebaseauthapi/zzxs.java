package com.google.android.gms.internal.p001firebaseauthapi;

import java.math.BigInteger;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzxs {
    private final BigInteger zza;

    public static zzxs zza(BigInteger bigInteger, zzcs zzcsVar) {
        if (zzcsVar != null) {
            return new zzxs(bigInteger);
        }
        throw new NullPointerException("SecretKeyAccess required");
    }

    public final BigInteger zza(zzcs zzcsVar) {
        if (zzcsVar == null) {
            throw new NullPointerException("SecretKeyAccess required");
        }
        return this.zza;
    }

    private zzxs(BigInteger bigInteger) {
        this.zza = bigInteger;
    }
}
