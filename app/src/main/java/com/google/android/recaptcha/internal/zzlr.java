package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzlr extends zzgo implements zzhz {
    private static final zzlr zzb;
    private zzlp zzd;
    private zzlp zze;

    static {
        zzlr zzlrVar = new zzlr();
        zzb = zzlrVar;
        zzgo.zzC(zzlr.class, zzlrVar);
    }

    private zzlr() {
    }

    public static zzlr zzj(InputStream inputStream) throws IOException {
        return (zzlr) zzgo.zzt(zzb, inputStream);
    }

    public final zzlp zzf() {
        zzlp zzlpVar = this.zzd;
        return zzlpVar == null ? zzlp.zzg() : zzlpVar;
    }

    public final zzlp zzg() {
        zzlp zzlpVar = this.zze;
        return zzlpVar == null ? zzlp.zzg() : zzlpVar;
    }

    @Override // com.google.android.recaptcha.internal.zzgo
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzln zzlnVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzz(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"zzd", "zze"});
            case 3:
                return new zzlr();
            case 4:
                return new zzlq(zzlnVar);
            case 5:
                return zzb;
        }
    }
}
