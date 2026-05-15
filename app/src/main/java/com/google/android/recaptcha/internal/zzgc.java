package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzgc {
    private static final zzga zza = new zzgb();
    private static final zzga zzb;

    static {
        zzga zzgaVar;
        try {
            zzgaVar = (zzga) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzgaVar = null;
        }
        zzb = zzgaVar;
    }

    static zzga zza() {
        zzga zzgaVar = zzb;
        if (zzgaVar != null) {
            return zzgaVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzga zzb() {
        return zza;
    }
}
