package com.google.android.recaptcha.internal;

import androidx.core.view.PointerIconCompat;
import com.google.android.gms.location.GeofenceStatusCodes;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzl {
    public static final zzk zza = new zzk(null);
    public static final zzl zzb = new zzl(9999);
    public static final zzl zzc = new zzl(1000);
    public static final zzl zzd = new zzl(1001);
    public static final zzl zze = new zzl(1002);
    public static final zzl zzf = new zzl(PointerIconCompat.TYPE_HELP);
    public static final zzl zzg = new zzl(1004);
    public static final zzl zzh = new zzl(GeofenceStatusCodes.GEOFENCE_REQUEST_TOO_FREQUENT);
    public static final zzl zzi = new zzl(PointerIconCompat.TYPE_CELL);
    public static final zzl zzj = new zzl(PointerIconCompat.TYPE_CROSSHAIR);
    public static final zzl zzk = new zzl(PointerIconCompat.TYPE_TEXT);
    public static final zzl zzl = new zzl(PointerIconCompat.TYPE_VERTICAL_TEXT);
    public static final zzl zzm = new zzl(PointerIconCompat.TYPE_ALIAS);
    private final int zzn;

    private zzl(int i) {
        this.zzn = i;
    }

    public final int zza() {
        return this.zzn;
    }
}
