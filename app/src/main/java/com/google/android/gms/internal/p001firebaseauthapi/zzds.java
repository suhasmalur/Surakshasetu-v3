package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class zzds {
    static final /* synthetic */ int[] zza;
    static final /* synthetic */ int[] zzb = new int[zzub.values().length];

    static {
        try {
            zzb[zzub.SHA1.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zzb[zzub.SHA224.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zzb[zzub.SHA256.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzb[zzub.SHA384.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zzb[zzub.SHA512.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        zza = new int[zzvs.values().length];
        try {
            zza[zzvs.TINK.ordinal()] = 1;
        } catch (NoSuchFieldError e6) {
        }
        try {
            zza[zzvs.CRUNCHY.ordinal()] = 2;
        } catch (NoSuchFieldError e7) {
        }
        try {
            zza[zzvs.LEGACY.ordinal()] = 3;
        } catch (NoSuchFieldError e8) {
        }
        try {
            zza[zzvs.RAW.ordinal()] = 4;
        } catch (NoSuchFieldError e9) {
        }
    }
}
