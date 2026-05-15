package com.google.android.gms.internal.p001firebaseauthapi;

import com.airbnb.lottie.utils.Utils;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzanc {
    private static final zzamb zza = (zzamb) ((zzajc) zzamb.zzc().zza(-62135596800L).zza(0).zzf());
    private static final zzamb zzb = (zzamb) ((zzajc) zzamb.zzc().zza(253402300799L).zza(999999999).zzf());
    private static final zzamb zzc = (zzamb) ((zzajc) zzamb.zzc().zza(0L).zza(0).zzf());
    private static final ThreadLocal<SimpleDateFormat> zzd = new zzane();

    @Nullable
    private static final Method zze = zzc("now");

    @Nullable
    private static final Method zzf = zzc("getEpochSecond");

    @Nullable
    private static final Method zzg = zzc("getNano");

    private static long zzb(String str) throws ParseException {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            throw new ParseException("Invalid offset value: " + str, 0);
        }
        try {
            return ((Long.parseLong(str.substring(0, iIndexOf)) * 60) + Long.parseLong(str.substring(iIndexOf + 1))) * 60;
        } catch (NumberFormatException e) {
            ParseException parseException = new ParseException("Invalid offset value: " + str, 0);
            parseException.initCause(e);
            throw parseException;
        }
    }

    public static long zza(zzamb zzambVar) {
        return zzb(zzambVar).zzb();
    }

    private static zzamb zzb(zzamb zzambVar) {
        long jZzb = zzambVar.zzb();
        int iZza = zzambVar.zza();
        boolean z = false;
        if (jZzb >= -62135596800L && jZzb <= 253402300799L && iZza >= 0 && iZza < 1000000000) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(String.format("Timestamp is not valid. See proto definition for valid values. Seconds (%s) must be in range [-62,135,596,800, +253,402,300,799]. Nanos (%s) must be in range [0, +999,999,999].", Long.valueOf(jZzb), Integer.valueOf(iZza)));
        }
        return zzambVar;
    }

    public static zzamb zza(String str) throws ParseException {
        String strSubstring;
        int iCharAt;
        int iIndexOf = str.indexOf(84);
        if (iIndexOf == -1) {
            throw new ParseException("Failed to parse timestamp: invalid timestamp \"" + str + "\"", 0);
        }
        int iIndexOf2 = str.indexOf(90, iIndexOf);
        if (iIndexOf2 == -1) {
            iIndexOf2 = str.indexOf(43, iIndexOf);
        }
        if (iIndexOf2 == -1) {
            iIndexOf2 = str.indexOf(45, iIndexOf);
        }
        if (iIndexOf2 == -1) {
            throw new ParseException("Failed to parse timestamp: missing valid timezone offset.", 0);
        }
        String strSubstring2 = str.substring(0, iIndexOf2);
        int iIndexOf3 = strSubstring2.indexOf(46);
        if (iIndexOf3 == -1) {
            strSubstring = "";
        } else {
            String strSubstring3 = strSubstring2.substring(0, iIndexOf3);
            strSubstring = strSubstring2.substring(iIndexOf3 + 1);
            strSubstring2 = strSubstring3;
        }
        long time = zzd.get().parse(strSubstring2).getTime() / 1000;
        if (strSubstring.isEmpty()) {
            iCharAt = 0;
        } else {
            iCharAt = 0;
            for (int i = 0; i < 9; i++) {
                iCharAt *= 10;
                if (i < strSubstring.length()) {
                    if (strSubstring.charAt(i) < '0' || strSubstring.charAt(i) > '9') {
                        throw new ParseException("Invalid nanoseconds.", 0);
                    }
                    iCharAt += strSubstring.charAt(i) - '0';
                }
            }
        }
        if (str.charAt(iIndexOf2) == 'Z') {
            if (str.length() != iIndexOf2 + 1) {
                throw new ParseException("Failed to parse timestamp: invalid trailing data \"" + str.substring(iIndexOf2) + "\"", 0);
            }
        } else {
            long jZzb = zzb(str.substring(iIndexOf2 + 1));
            if (str.charAt(iIndexOf2) == '+') {
                time -= jZzb;
            } else {
                time += jZzb;
            }
        }
        if (iCharAt <= -1000000000 || iCharAt >= 1000000000) {
            try {
                time = zzbe.zza(time, iCharAt / Utils.SECOND_IN_NANOS);
                iCharAt %= Utils.SECOND_IN_NANOS;
            } catch (IllegalArgumentException e) {
                ParseException parseException = new ParseException("Failed to parse timestamp " + str + " Timestamp is out of range.", 0);
                parseException.initCause(e);
                throw parseException;
            }
        }
        if (iCharAt < 0) {
            iCharAt += Utils.SECOND_IN_NANOS;
            time = zzbe.zzb(time, 1L);
        }
        return zzb((zzamb) ((zzajc) zzamb.zzc().zza(time).zza(iCharAt).zzf()));
    }

    @Nullable
    private static Method zzc(String str) {
        try {
            return Class.forName("java.time.Instant").getMethod(str, new Class[0]);
        } catch (Exception e) {
            return null;
        }
    }

    static /* synthetic */ SimpleDateFormat zza() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        simpleDateFormat.setCalendar(gregorianCalendar);
        return simpleDateFormat;
    }
}
