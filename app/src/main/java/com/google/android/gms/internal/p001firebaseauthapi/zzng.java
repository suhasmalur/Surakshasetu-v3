package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzuy;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class zzng {
    static final /* synthetic */ int[] zza = new int[zzuy.zza.values().length];
    private static final /* synthetic */ int[] zzb;

    static {
        try {
            zza[zzuy.zza.SYMMETRIC.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zza[zzuy.zza.ASYMMETRIC_PRIVATE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        zzb = new int[zzvs.values().length];
        try {
            zzb[zzvs.TINK.ordinal()] = 1;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzb[zzvs.LEGACY.ordinal()] = 2;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zzb[zzvs.RAW.ordinal()] = 3;
        } catch (NoSuchFieldError e5) {
        }
        try {
            zzb[zzvs.CRUNCHY.ordinal()] = 4;
        } catch (NoSuchFieldError e6) {
        }
    }
}
