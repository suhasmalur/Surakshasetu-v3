package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzmm extends zzgo implements zzhz {
    private static final zzmm zzb;
    private int zzd = 0;
    private Object zze;
    private int zzf;

    static {
        zzmm zzmmVar = new zzmm();
        zzb = zzmmVar;
        zzgo.zzC(zzmm.class, zzmmVar);
    }

    private zzmm() {
    }

    static /* synthetic */ void zzG(zzmm zzmmVar, String str) {
        str.getClass();
        zzmmVar.zzd = 11;
        zzmmVar.zze = str;
    }

    static /* synthetic */ void zzH(zzmm zzmmVar, boolean z) {
        zzmmVar.zzd = 1;
        zzmmVar.zze = Boolean.valueOf(z);
    }

    static /* synthetic */ void zzI(zzmm zzmmVar, zzez zzezVar) {
        zzmmVar.zzd = 2;
        zzmmVar.zze = zzezVar;
    }

    static /* synthetic */ void zzJ(zzmm zzmmVar, String str) {
        str.getClass();
        zzmmVar.zzd = 3;
        zzmmVar.zze = str;
    }

    static /* synthetic */ void zzK(zzmm zzmmVar, int i) {
        zzmmVar.zzd = 4;
        zzmmVar.zze = Integer.valueOf(i);
    }

    static /* synthetic */ void zzL(zzmm zzmmVar, int i) {
        zzmmVar.zzd = 5;
        zzmmVar.zze = Integer.valueOf(i);
    }

    public static zzml zzf() {
        return (zzml) zzb.zzp();
    }

    static /* synthetic */ void zzi(zzmm zzmmVar, long j) {
        zzmmVar.zzd = 7;
        zzmmVar.zze = Long.valueOf(j);
    }

    static /* synthetic */ void zzj(zzmm zzmmVar, float f) {
        zzmmVar.zzd = 9;
        zzmmVar.zze = Float.valueOf(f);
    }

    static /* synthetic */ void zzk(zzmm zzmmVar, double d) {
        zzmmVar.zzd = 10;
        zzmmVar.zze = Double.valueOf(d);
    }

    @Override // com.google.android.recaptcha.internal.zzgo
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzlv zzlvVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzz(zzb, "\u0000\f\u0001\u0000\u0001\f\f\u0000\u0000\u0000\u0001:\u0000\u0002=\u0000\u0003Ȼ\u0000\u0004B\u0000\u0005B\u0000\u0006>\u0000\u0007C\u0000\b6\u0000\t4\u0000\n3\u0000\u000bȻ\u0000\f\u000b", new Object[]{"zze", "zzd", "zzf"});
            case 3:
                return new zzmm();
            case 4:
                return new zzml(zzlvVar);
            case 5:
                return zzb;
        }
    }
}
