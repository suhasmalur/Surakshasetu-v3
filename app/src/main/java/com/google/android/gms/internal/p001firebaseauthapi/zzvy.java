package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzvy extends zzajc<zzvy, zza> implements zzakp {
    private static final zzvy zzc;
    private static volatile zzakw<zzvy> zzd;
    private int zze;

    public final int zza() {
        return this.zze;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzvy, zza> implements zzakp {
        private zza() {
            super(zzvy.zzc);
        }

        /* synthetic */ zza(zzvz zzvzVar) {
            this();
        }
    }

    public static zzvy zzc() {
        return zzc;
    }

    public static zzvy zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzvy) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzvy>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzvz zzvzVar = null;
        switch (zzvz.zza[i - 1]) {
            case 1:
                return new zzvy();
            case 2:
                return new zza(zzvzVar);
            case 3:
                return zza(zzc, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzvy> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzvy.class) {
                        zzakw<zzvy> zzakwVar2 = zzd;
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
        zzvy zzvyVar = new zzvy();
        zzc = zzvyVar;
        zzajc.zza((Class<zzvy>) zzvy.class, zzvyVar);
    }

    private zzvy() {
    }
}
