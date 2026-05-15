package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzmn extends zzgo implements zzhz {
    private static final zzmn zzb;
    private zzgv zzd = zzw();
    private int zze;

    static {
        zzmn zzmnVar = new zzmn();
        zzb = zzmnVar;
        zzgo.zzC(zzmn.class, zzmnVar);
    }

    private zzmn() {
    }

    public static zzmk zzf() {
        return (zzmk) zzb.zzp();
    }

    static /* synthetic */ void zzi(zzmn zzmnVar, zzmm zzmmVar) {
        zzmmVar.getClass();
        zzmnVar.zzk();
        zzmnVar.zzd.add(zzmmVar);
    }

    static /* synthetic */ void zzj(zzmn zzmnVar, Iterable iterable) {
        zzmnVar.zzk();
        zzei.zzc(iterable, zzmnVar.zzd);
    }

    private final void zzk() {
        zzgv zzgvVar = this.zzd;
        if (zzgvVar.zzc()) {
            return;
        }
        this.zzd = zzgo.zzx(zzgvVar);
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
                return zzz(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u000b", new Object[]{"zzd", zzmm.class, "zze"});
            case 3:
                return new zzmn();
            case 4:
                return new zzmk(zzlvVar);
            case 5:
                return zzb;
        }
    }
}
