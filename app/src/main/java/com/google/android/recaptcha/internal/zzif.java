package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzif {
    private static final zzie zza;
    private static final zzie zzb;

    static {
        zzie zzieVar;
        try {
            zzieVar = (zzie) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzieVar = null;
        }
        zza = zzieVar;
        zzb = new zzie();
    }

    static zzie zza() {
        return zza;
    }

    static zzie zzb() {
        return zzb;
    }
}
