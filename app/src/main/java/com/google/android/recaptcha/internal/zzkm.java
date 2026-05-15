package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzkm extends zzgo implements zzhz {
    private static final zzkm zzb;
    private int zzf;
    private int zzi;
    private String zzd = "";
    private String zze = "";
    private String zzg = "";
    private String zzh = "";

    static {
        zzkm zzkmVar = new zzkm();
        zzb = zzkmVar;
        zzgo.zzC(zzkm.class, zzkmVar);
    }

    private zzkm() {
    }

    static /* synthetic */ void zzG(zzkm zzkmVar, String str) {
        str.getClass();
        zzkmVar.zzd = str;
    }

    public static zzkl zzg() {
        return (zzkl) zzb.zzp();
    }

    public static zzkm zzj() {
        return zzb;
    }

    public final int zzf() {
        return this.zzf;
    }

    public final String zzk() {
        return this.zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzgo
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzkk zzkkVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzz(zzb, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001Ȉ\u0002\u0004\u0003Ȉ\u0004\u0004\u0005Ȉ\u0006Ȉ", new Object[]{"zzd", "zzf", "zzh", "zzi", "zze", "zzg"});
            case 3:
                return new zzkm();
            case 4:
                return new zzkl(zzkkVar);
            case 5:
                return zzb;
        }
    }
}
