package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzth extends zzajc<zzth, zza> implements zzakp {
    private static final zzth zzc;
    private static volatile zzakw<zzth> zzd;

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzth, zza> implements zzakp {
        private zza() {
            super(zzth.zzc);
        }

        /* synthetic */ zza(zzti zztiVar) {
            this();
        }
    }

    public static zzth zzb() {
        return zzc;
    }

    public static zzth zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzth) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzth>] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzti zztiVar = null;
        switch (zzti.zza[i - 1]) {
            case 1:
                return new zzth();
            case 2:
                return new zza(zztiVar);
            case 3:
                return zza(zzc, "\u0000\u0000", (Object[]) null);
            case 4:
                return zzc;
            case 5:
                zzakw<zzth> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzth.class) {
                        zzakw<zzth> zzakwVar2 = zzd;
                        r1 = zzakwVar2;
                        if (zzakwVar2 == null) {
                            ?? zzcVar = new zzajc.zzc(zzc);
                            zzd = zzcVar;
                            r1 = zzcVar;
                        }
                        break;
                    }
                    obj3 = r1;
                }
                return obj3;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        zzth zzthVar = new zzth();
        zzc = zzthVar;
        zzajc.zza((Class<zzth>) zzth.class, zzthVar);
    }

    private zzth() {
    }
}
