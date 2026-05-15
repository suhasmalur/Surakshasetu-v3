package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaki {
    private static final zzakg zza = zzc();
    private static final zzakg zzb = new zzakj();

    static zzakg zza() {
        return zza;
    }

    static zzakg zzb() {
        return zzb;
    }

    private static zzakg zzc() {
        try {
            return (zzakg) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
