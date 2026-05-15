package com.google.android.gms.location;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.0.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzai {
    public static String zza(int i) {
        switch (i) {
            case 0:
                return "THROTTLE_BACKGROUND";
            case 1:
                return "THROTTLE_ALWAYS";
            case 2:
                return "THROTTLE_NEVER";
            default:
                throw new IllegalArgumentException();
        }
    }
}
