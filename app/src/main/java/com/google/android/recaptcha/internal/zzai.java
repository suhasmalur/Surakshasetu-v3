package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzai {
    public static final zzai zza = new zzai();
    private static final HashMap zzb = new HashMap();

    private zzai() {
    }

    public static final void zza(zzaf zzafVar, Long l, int i) {
        zzah zzahVar = (zzah) zzb.get(zzafVar);
        if (zzahVar != null) {
            zzkf zzkfVarZzf = zzkg.zzf();
            zzkfVarZzf.zze(i);
            if (l != null) {
                zzkfVarZzf.zzd(zzjy.zza(l.longValue() - zzahVar.zza()));
            }
            zzahVar.zzb().zzd((zzkg) zzkfVarZzf.zzj());
        }
    }

    public static final void zzb(zzaf zzafVar, String str, zzs zzsVar) {
        zzb.put(zzafVar, new zzah(zzafVar, str, zzsVar));
    }

    public static final void zzc(zzaf zzafVar, Context context, zzr zzrVar) {
        zze(zzafVar, 3, null, context, zzrVar);
    }

    public static final void zzd(zzaf zzafVar, String str, int i, Context context, zzr zzrVar, String str2) {
        zzkl zzklVarZzg = zzkm.zzg();
        zzklVarZzg.zzp(str);
        zzklVarZzg.zzd(i);
        if (str2 != null) {
            zzklVarZzg.zze(str2);
        }
        zze(zzafVar, 4, (zzkm) zzklVarZzg.zzj(), context, zzrVar);
    }

    private static final void zze(zzaf zzafVar, int i, zzkm zzkmVar, Context context, zzr zzrVar) {
        zzl zzlVar;
        zzah zzahVar = (zzah) zzb.get(zzafVar);
        if (zzahVar != null) {
            zzkx zzkxVarZzc = zzahVar.zzc(i, zzkmVar, context);
            zzj zzjVar = zzj.zza;
            zzkw zzkwVarZza = zzafVar.zza();
            long jZzf = zzkxVarZzc.zzf() * 1000;
            zzkw zzkwVar = zzkw.UNKNOWN;
            switch (zzkwVarZza.ordinal()) {
                case 1:
                    zzlVar = zzl.zzd;
                    break;
                case 2:
                    zzlVar = zzl.zze;
                    break;
                case 3:
                    zzlVar = zzl.zzf;
                    break;
                case 4:
                    zzlVar = zzl.zzg;
                    break;
                case 5:
                    zzlVar = zzl.zzh;
                    break;
                case 6:
                    zzlVar = zzl.zzi;
                    break;
                case 7:
                    zzlVar = zzl.zzj;
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                default:
                    zzlVar = zzl.zzb;
                    break;
                case 14:
                    zzlVar = zzl.zzk;
                    break;
            }
            zzj.zza(zzlVar.zza(), jZzf);
            new zzao(context, new zzaq(zzrVar.zzc()), null, 4, null).zzf(zzkxVarZzc);
        }
    }
}
