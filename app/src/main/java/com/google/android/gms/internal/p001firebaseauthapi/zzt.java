package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.regex.Matcher;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzt extends zzp {
    private final Matcher zza;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzp
    public final int zza() {
        return this.zza.end();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzp
    public final int zzb() {
        return this.zza.start();
    }

    zzt(Matcher matcher) {
        this.zza = (Matcher) zzy.zza(matcher);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzp
    public final boolean zza(int i) {
        return this.zza.find(i);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzp
    public final boolean zzc() {
        return this.zza.matches();
    }
}
