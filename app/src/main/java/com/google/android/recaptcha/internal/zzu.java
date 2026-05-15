package com.google.android.recaptcha.internal;

import android.content.Context;
import android.os.Build;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzu {
    public static final zzu zza = new zzu();
    private static final String zzb = String.valueOf(Build.VERSION.SDK_INT);
    private static final GoogleApiAvailabilityLight zzc = GoogleApiAvailabilityLight.getInstance();

    private zzu() {
    }

    public static final String zza(Context context) {
        switch (zzc.isGooglePlayServicesAvailable(context)) {
            case 1:
            case 3:
            case 9:
                return "ANDROID_OFFPLAY";
            default:
                return "ANDROID_ONPLAY";
        }
    }

    public static final String zzb() {
        return zzb;
    }
}
