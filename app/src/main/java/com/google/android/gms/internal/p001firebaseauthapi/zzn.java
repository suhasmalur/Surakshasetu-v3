package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzn extends zzo {
    static final zzj zza = new zzn();

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzj
    public final int zza(CharSequence charSequence, int i) {
        zzy.zza(i, charSequence.length(), "index");
        return -1;
    }

    private zzn() {
        super("CharMatcher.none()");
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzj
    public final boolean zza(char c) {
        return false;
    }
}
