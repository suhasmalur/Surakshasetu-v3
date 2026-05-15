package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzkd extends zzgo implements zzhz {
    private static final zzkd zzb;
    private zzgv zzd = zzw();
    private zzfw zze;

    static {
        zzkd zzkdVar = new zzkd();
        zzb = zzkdVar;
        zzgo.zzC(zzkd.class, zzkdVar);
    }

    private zzkd() {
    }

    @Override // com.google.android.recaptcha.internal.zzgo
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzkb zzkbVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzz(zzb, "\u0000\u0002\u0000\u0000\f\r\u0002\u0000\u0001\u0000\f\u001b\r\t", new Object[]{"zzd", zzlm.class, "zze"});
            case 3:
                return new zzkd();
            case 4:
                return new zzkc(zzkbVar);
            case 5:
                return zzb;
        }
    }
}
