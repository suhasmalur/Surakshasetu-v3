package com.google.android.recaptcha.internal;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzdk {
    private final zzdn zza = zzdn.zza();
    private boolean zzb;
    private long zzc;
    private long zzd;

    zzdk() {
    }

    public static zzdk zzb() {
        zzdk zzdkVar = new zzdk();
        zzdkVar.zze();
        return zzdkVar;
    }

    public static zzdk zzc() {
        return new zzdk();
    }

    private final long zzg() {
        return this.zzb ? (System.nanoTime() - this.zzd) + this.zzc : this.zzc;
    }

    public final String toString() {
        String str;
        long jZzg = zzg();
        TimeUnit timeUnit = TimeUnit.DAYS.convert(jZzg, TimeUnit.NANOSECONDS) > 0 ? TimeUnit.DAYS : TimeUnit.HOURS.convert(jZzg, TimeUnit.NANOSECONDS) > 0 ? TimeUnit.HOURS : TimeUnit.MINUTES.convert(jZzg, TimeUnit.NANOSECONDS) > 0 ? TimeUnit.MINUTES : TimeUnit.SECONDS.convert(jZzg, TimeUnit.NANOSECONDS) > 0 ? TimeUnit.SECONDS : TimeUnit.MILLISECONDS.convert(jZzg, TimeUnit.NANOSECONDS) > 0 ? TimeUnit.MILLISECONDS : TimeUnit.MICROSECONDS.convert(jZzg, TimeUnit.NANOSECONDS) > 0 ? TimeUnit.MICROSECONDS : TimeUnit.NANOSECONDS;
        String str2 = String.format(Locale.ROOT, "%.4g", Double.valueOf(jZzg / TimeUnit.NANOSECONDS.convert(1L, timeUnit)));
        switch (zzdj.zza[timeUnit.ordinal()]) {
            case 1:
                str = "ns";
                break;
            case 2:
                str = "μs";
                break;
            case 3:
                str = "ms";
                break;
            case 4:
                str = "s";
                break;
            case 5:
                str = "min";
                break;
            case 6:
                str = "h";
                break;
            case 7:
                str = "d";
                break;
            default:
                throw new AssertionError();
        }
        return str2 + " " + str;
    }

    public final long zza(TimeUnit timeUnit) {
        return timeUnit.convert(zzg(), TimeUnit.NANOSECONDS);
    }

    public final zzdk zzd() {
        this.zzc = 0L;
        this.zzb = false;
        return this;
    }

    public final zzdk zze() {
        zzdi.zze(!this.zzb, "This stopwatch is already running.");
        this.zzb = true;
        this.zzd = System.nanoTime();
        return this;
    }

    public final zzdk zzf() {
        long jNanoTime = System.nanoTime();
        zzdi.zze(this.zzb, "This stopwatch is already stopped.");
        this.zzb = false;
        this.zzc += jNanoTime - this.zzd;
        return this;
    }
}
