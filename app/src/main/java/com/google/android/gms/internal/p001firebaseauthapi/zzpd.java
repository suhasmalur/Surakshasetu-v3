package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzpd extends RuntimeException {
    public static <T> T zza(zzpg<T> zzpgVar) {
        try {
            return zzpgVar.zza();
        } catch (Exception e) {
            throw new zzpd(e);
        }
    }

    public zzpd(String str) {
        super(str);
    }

    private zzpd(Throwable th) {
        super(th);
    }

    public zzpd(String str, Throwable th) {
        super(str, th);
    }
}
