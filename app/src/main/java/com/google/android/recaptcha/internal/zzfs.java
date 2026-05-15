package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzfs extends zzgo implements zzhz {
    private static final zzfs zzb;
    private int zzd;
    private long zzg;
    private long zzh;
    private double zzi;
    private byte zzl = 2;
    private zzgv zze = zzii.zze();
    private String zzf = "";
    private zzez zzj = zzez.zzb;
    private String zzk = "";

    static {
        zzfs zzfsVar = new zzfs();
        zzb = zzfsVar;
        zzgo.zzC(zzfs.class, zzfsVar);
    }

    private zzfs() {
    }

    @Override // com.google.android.recaptcha.internal.zzgo
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzfm zzfmVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzl);
            case 1:
            default:
                this.zzl = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return new zzij(zzb, "\u0001\u0007\u0000\u0001\u0002\b\u0007\u0000\u0001\u0001\u0002Л\u0003ဈ\u0000\u0004ဃ\u0001\u0005ဂ\u0002\u0006က\u0003\u0007ည\u0004\bဈ\u0005", new Object[]{"zzd", "zze", zzfr.class, "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
            case 3:
                return new zzfs();
            case 4:
                return new zzfp(zzfmVar);
            case 5:
                return zzb;
        }
    }
}
