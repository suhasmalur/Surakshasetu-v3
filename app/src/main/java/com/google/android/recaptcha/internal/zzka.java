package com.google.android.recaptcha.internal;

import com.airbnb.lottie.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.time.DurationKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzka {
    public static final zzjd zza;
    public static final zzjd zzb;
    public static final zzjd zzc;
    private static final ThreadLocal zzd;

    static {
        zzjc zzjcVarZzi = zzjd.zzi();
        zzjcVarZzi.zze(-62135596800L);
        zzjcVarZzi.zzd(0);
        zza = (zzjd) zzjcVarZzi.zzj();
        zzjc zzjcVarZzi2 = zzjd.zzi();
        zzjcVarZzi2.zze(253402300799L);
        zzjcVarZzi2.zzd(999999999);
        zzb = (zzjd) zzjcVarZzi2.zzj();
        zzjc zzjcVarZzi3 = zzjd.zzi();
        zzjcVarZzi3.zze(0L);
        zzjcVarZzi3.zzd(0);
        zzc = (zzjd) zzjcVarZzi3.zzj();
        zzd = new zzjz();
    }

    public static zzjd zza(zzjd zzjdVar) {
        long jZzg = zzjdVar.zzg();
        int iZzf = zzjdVar.zzf();
        if (jZzg < -62135596800L || jZzg > 253402300799L || iZzf < 0 || iZzf >= 1000000000) {
            throw new IllegalArgumentException(String.format("Timestamp is not valid. See proto definition for valid values. Seconds (%s) must be in range [-62,135,596,800, +253,402,300,799]. Nanos (%s) must be in range [0, +999,999,999].", Long.valueOf(jZzg), Integer.valueOf(iZzf)));
        }
        return zzjdVar;
    }

    public static zzjd zzb(long j) {
        int i = (int) ((j % 1000) * 1000000);
        long jZza = j / 1000;
        if (i <= -1000000000 || i >= 1000000000) {
            jZza = zzee.zza(jZza, i / Utils.SECOND_IN_NANOS);
            i %= Utils.SECOND_IN_NANOS;
        }
        if (i < 0) {
            i += Utils.SECOND_IN_NANOS;
            jZza = zzee.zzb(jZza, 1L);
        }
        zzjc zzjcVarZzi = zzjd.zzi();
        zzjcVarZzi.zze(jZza);
        zzjcVarZzi.zzd(i);
        zzjd zzjdVar = (zzjd) zzjcVarZzi.zzj();
        zza(zzjdVar);
        return zzjdVar;
    }

    public static String zzc(zzjd zzjdVar) {
        zza(zzjdVar);
        long jZzg = zzjdVar.zzg();
        int iZzf = zzjdVar.zzf();
        StringBuilder sb = new StringBuilder();
        sb.append(((SimpleDateFormat) zzd.get()).format(new Date(jZzg * 1000)));
        if (iZzf != 0) {
            sb.append(".");
            sb.append(iZzf % DurationKt.NANOS_IN_MILLIS == 0 ? String.format(Locale.ENGLISH, "%1$03d", Integer.valueOf(iZzf / DurationKt.NANOS_IN_MILLIS)) : iZzf % 1000 == 0 ? String.format(Locale.ENGLISH, "%1$06d", Integer.valueOf(iZzf / 1000)) : String.format(Locale.ENGLISH, "%1$09d", Integer.valueOf(iZzf)));
        }
        sb.append("Z");
        return sb.toString();
    }
}
