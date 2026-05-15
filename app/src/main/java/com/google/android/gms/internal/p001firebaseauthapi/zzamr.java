package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public class zzamr {
    public static final zzamr zza = new zzamr("DOUBLE", 0, zzanb.DOUBLE, 1);
    public static final zzamr zzb = new zzamr("FLOAT", 1, zzanb.FLOAT, 5);
    public static final zzamr zzc = new zzamr("INT64", 2, zzanb.LONG, 0);
    public static final zzamr zzd = new zzamr("UINT64", 3, zzanb.LONG, 0);
    public static final zzamr zze = new zzamr("INT32", 4, zzanb.INT, 0);
    public static final zzamr zzf = new zzamr("FIXED64", 5, zzanb.LONG, 1);
    public static final zzamr zzg = new zzamr("FIXED32", 6, zzanb.INT, 5);
    public static final zzamr zzh = new zzamr("BOOL", 7, zzanb.BOOLEAN, 0);
    public static final zzamr zzi = new zzamq("STRING", zzanb.STRING);
    public static final zzamr zzj = new zzams("GROUP", zzanb.MESSAGE);
    public static final zzamr zzk = new zzamu("MESSAGE", zzanb.MESSAGE);
    public static final zzamr zzl = new zzamw("BYTES", zzanb.BYTE_STRING);
    public static final zzamr zzm = new zzamr("UINT32", 12, zzanb.INT, 0);
    public static final zzamr zzn = new zzamr("ENUM", 13, zzanb.ENUM, 0);
    public static final zzamr zzo = new zzamr("SFIXED32", 14, zzanb.INT, 5);
    public static final zzamr zzp = new zzamr("SFIXED64", 15, zzanb.LONG, 1);
    public static final zzamr zzq = new zzamr("SINT32", 16, zzanb.INT, 0);
    public static final zzamr zzr = new zzamr("SINT64", 17, zzanb.LONG, 0);
    private static final /* synthetic */ zzamr[] zzs = {zza, zzb, zzc, zzd, zze, zzf, zzg, zzh, zzi, zzj, zzk, zzl, zzm, zzn, zzo, zzp, zzq, zzr};
    private final zzanb zzt;
    private final int zzu;

    public final int zza() {
        return this.zzu;
    }

    public final zzanb zzb() {
        return this.zzt;
    }

    private zzamr(String str, int i, zzanb zzanbVar, int i2) {
        this.zzt = zzanbVar;
        this.zzu = i2;
    }

    public static zzamr[] values() {
        return (zzamr[]) zzs.clone();
    }
}
