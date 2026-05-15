package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzfo extends zzgk implements zzhz {
    private static final zzfo zzd;
    private int zze;
    private boolean zzf;
    private byte zzh = 2;
    private zzgv zzg = zzii.zze();

    static {
        zzfo zzfoVar = new zzfo();
        zzd = zzfoVar;
        zzgo.zzC(zzfo.class, zzfoVar);
    }

    private zzfo() {
    }

    public static zzfo zzg() {
        return zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzgo
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzfm zzfmVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzh);
            case 1:
            default:
                this.zzh = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return new zzij(zzd, "\u0001\u0002\u0000\u0001\u0001ϧ\u0002\u0000\u0001\u0001\u0001ဇ\u0000ϧЛ", new Object[]{"zze", "zzf", "zzg", zzfs.class});
            case 3:
                return new zzfo();
            case 4:
                return new zzfn(zzfmVar);
            case 5:
                return zzd;
        }
    }
}
