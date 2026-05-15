package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public class zzacl {
    final zzact zza;
    private final String zzb;

    final String zza(String str, String str2) {
        return this.zzb + str + "?key=" + str2;
    }

    public zzacl(String str, zzact zzactVar) {
        this.zzb = str;
        this.zza = zzactVar;
    }
}
