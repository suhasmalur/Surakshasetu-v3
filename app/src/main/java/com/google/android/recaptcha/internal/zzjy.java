package com.google.android.recaptcha.internal;

import com.airbnb.lottie.utils.Utils;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzjy {
    public static final zzfw zza;
    public static final zzfw zzb;
    public static final zzfw zzc;

    static {
        zzfv zzfvVarZzi = zzfw.zzi();
        zzfvVarZzi.zze(-315576000000L);
        zzfvVarZzi.zzd(-999999999);
        zza = (zzfw) zzfvVarZzi.zzj();
        zzfv zzfvVarZzi2 = zzfw.zzi();
        zzfvVarZzi2.zze(315576000000L);
        zzfvVarZzi2.zzd(999999999);
        zzb = (zzfw) zzfvVarZzi2.zzj();
        zzfv zzfvVarZzi3 = zzfw.zzi();
        zzfvVarZzi3.zze(0L);
        zzfvVarZzi3.zzd(0);
        zzc = (zzfw) zzfvVarZzi3.zzj();
    }

    public static zzfw zza(long j) {
        return zzc(j / 1000, (int) ((j % 1000) * 1000000));
    }

    public static zzfw zzb(long j) {
        return zzc(j / 1000000000, (int) (j % 1000000000));
    }

    static zzfw zzc(long j, int i) {
        if (i <= -1000000000 || i >= 1000000000) {
            j = zzee.zza(j, i / Utils.SECOND_IN_NANOS);
            i %= Utils.SECOND_IN_NANOS;
        }
        if (j > 0 && i < 0) {
            i += Utils.SECOND_IN_NANOS;
            j--;
        }
        if (j < 0 && i > 0) {
            i -= 1000000000;
            j++;
        }
        zzfv zzfvVarZzi = zzfw.zzi();
        zzfvVarZzi.zze(j);
        zzfvVarZzi.zzd(i);
        zzfw zzfwVar = (zzfw) zzfvVarZzi.zzj();
        long jZzg = zzfwVar.zzg();
        int iZzf = zzfwVar.zzf();
        if (jZzg < -315576000000L || jZzg > 315576000000L || iZzf < -999999999 || iZzf >= 1000000000 || ((jZzg < 0 || iZzf < 0) && (jZzg > 0 || iZzf > 0))) {
            throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", Long.valueOf(jZzg), Integer.valueOf(iZzf)));
        }
        return zzfwVar;
    }
}
