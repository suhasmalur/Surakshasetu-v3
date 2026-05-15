package com.google.android.recaptcha.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.os.EnvironmentCompat;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzah {
    public static final zzag zza = new zzag(null);
    private static zzkj zzb;
    private final String zzc;
    private final zzs zzd;
    private final zzku zze = zzkx.zzi();
    private final long zzf = System.currentTimeMillis();

    public zzah(zzaf zzafVar, String str, zzs zzsVar) {
        this.zzc = str;
        this.zzd = zzsVar;
        zzku zzkuVar = this.zze;
        zzkuVar.zzq(zzafVar.zza());
        zzkuVar.zze(zzafVar.zzb());
        zzkuVar.zzs(zzafVar.zzc());
        zzkuVar.zzv(zzafVar.zzd());
        zzkuVar.zzu(zzka.zzc(zzka.zzb(System.currentTimeMillis())));
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x004a -> B:28:0x004b). Please report as a decompilation issue!!! */
    private static final zzkj zzd(Context context) {
        String strValueOf;
        String strValueOf2 = EnvironmentCompat.MEDIA_UNKNOWN;
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                int i = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.ApplicationInfoFlags.of(128L)).metaData.getInt("com.google.android.gms.version", -1);
                strValueOf = i == -1 ? EnvironmentCompat.MEDIA_UNKNOWN : String.valueOf(i);
            } else {
                int i2 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt("com.google.android.gms.version", -1);
                strValueOf = i2 == -1 ? EnvironmentCompat.MEDIA_UNKNOWN : String.valueOf(i2);
            }
        } catch (PackageManager.NameNotFoundException e) {
            strValueOf = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        try {
            strValueOf2 = Build.VERSION.SDK_INT >= 33 ? String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L)).getLongVersionCode()) : Build.VERSION.SDK_INT >= 28 ? String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode()) : String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e2) {
        }
        zzki zzkiVarZzf = zzkj.zzf();
        zzkiVarZzf.zzd(Build.VERSION.SDK_INT);
        zzkiVarZzf.zzq(strValueOf);
        zzkiVarZzf.zzs("18.1.2");
        zzkiVarZzf.zzp(Build.MODEL);
        zzkiVarZzf.zzr(Build.MANUFACTURER);
        zzkiVarZzf.zze(strValueOf2);
        return (zzkj) zzkiVarZzf.zzj();
    }

    public final long zza() {
        return this.zzf;
    }

    public final zzku zzb() {
        return this.zze;
    }

    public final zzkx zzc(int i, zzkm zzkmVar, Context context) {
        zzku zzkuVar = this.zze;
        zzkuVar.zzp(System.currentTimeMillis() - this.zzf);
        zzkuVar.zzw(i);
        if (zzkmVar != null) {
            this.zze.zzr(zzkmVar);
        }
        if (zzb == null) {
            zzb = zzd(context);
        }
        zzku zzkuVar2 = this.zze;
        zzli zzliVarZzf = zzlj.zzf();
        zzliVarZzf.zzq(this.zzc);
        zzkj zzkjVarZzd = zzb;
        if (zzkjVarZzd == null) {
            zzkjVarZzd = zzd(context);
        }
        zzliVarZzf.zzd(zzkjVarZzd);
        zzliVarZzf.zzp(Locale.getDefault().getISO3Language());
        zzliVarZzf.zze(Locale.getDefault().getISO3Country());
        zzkuVar2.zzt((zzlj) zzliVarZzf.zzj());
        return (zzkx) this.zze.zzj();
    }
}
