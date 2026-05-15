package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzais {
    private static final zzaiq<?> zza = new zzait();
    private static final zzaiq<?> zzb = zzc();

    static zzaiq<?> zza() {
        if (zzb != null) {
            return zzb;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzaiq<?> zzb() {
        return zza;
    }

    private static zzaiq<?> zzc() {
        try {
            return (zzaiq) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
