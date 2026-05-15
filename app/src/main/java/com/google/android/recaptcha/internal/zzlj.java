package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzlj extends zzgo implements zzhz {
    private static final zzlj zzb;
    private zzkp zze;
    private zzkj zzf;
    private zzks zzg;
    private String zzd = "";
    private String zzh = "";
    private String zzi = "";

    static {
        zzlj zzljVar = new zzlj();
        zzb = zzljVar;
        zzgo.zzC(zzlj.class, zzljVar);
    }

    private zzlj() {
    }

    static /* synthetic */ void zzG(zzlj zzljVar, zzkj zzkjVar) {
        zzkjVar.getClass();
        zzljVar.zzf = zzkjVar;
    }

    public static zzli zzf() {
        return (zzli) zzb.zzp();
    }

    static /* synthetic */ void zzi(zzlj zzljVar, String str) {
        str.getClass();
        zzljVar.zzd = str;
    }

    static /* synthetic */ void zzj(zzlj zzljVar, String str) {
        str.getClass();
        zzljVar.zzh = str;
    }

    static /* synthetic */ void zzk(zzlj zzljVar, String str) {
        str.getClass();
        zzljVar.zzi = str;
    }

    @Override // com.google.android.recaptcha.internal.zzgo
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzlh zzlhVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzz(zzb, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003\t\u0004\t\u0005Ȉ\u0006Ȉ", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
            case 3:
                return new zzlj();
            case 4:
                return new zzli(zzlhVar);
            case 5:
                return zzb;
        }
    }
}
