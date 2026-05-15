package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzks extends zzgo implements zzhz {
    private static final zzks zzb;
    private String zzd = "";
    private String zze = "";

    static {
        zzks zzksVar = new zzks();
        zzb = zzksVar;
        zzgo.zzC(zzks.class, zzksVar);
    }

    private zzks() {
    }

    @Override // com.google.android.recaptcha.internal.zzgo
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzkq zzkqVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzz(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"zzd", "zze"});
            case 3:
                return new zzks();
            case 4:
                return new zzkr(zzkqVar);
            case 5:
                return zzb;
        }
    }
}
