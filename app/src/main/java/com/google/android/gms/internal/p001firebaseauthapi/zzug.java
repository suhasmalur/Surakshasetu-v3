package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzug extends zzajc<zzug, zza> implements zzakp {
    private static final zzug zzc;
    private static volatile zzakw<zzug> zzd;
    private int zze;
    private zzuh zzf;
    private int zzg;
    private int zzh;

    public final int zza() {
        return this.zzg;
    }

    public final int zzb() {
        return this.zzh;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzug, zza> implements zzakp {
        public final zza zza(int i) {
            zzi();
            ((zzug) this.zza).zza(i);
            return this;
        }

        public final zza zza(zzuh zzuhVar) {
            zzi();
            ((zzug) this.zza).zza(zzuhVar);
            return this;
        }

        private zza() {
            super(zzug.zzc);
        }

        /* synthetic */ zza(zzuf zzufVar) {
            this();
        }
    }

    public static zza zzc() {
        return zzc.zzm();
    }

    public static zzug zze() {
        return zzc;
    }

    public static zzug zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzug) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzuh zzf() {
        return this.zzf == null ? zzuh.zze() : this.zzf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzug>] */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r2;
        zzuf zzufVar = null;
        switch (zzuf.zza[i - 1]) {
            case 1:
                return new zzug();
            case 2:
                return new zza(zzufVar);
            case 3:
                return zza(zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b\u0003\u000b", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzug> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzug.class) {
                        zzakw<zzug> zzakwVar2 = zzd;
                        r2 = zzakwVar2;
                        if (zzakwVar2 == null) {
                            ?? zzcVar = new zzajc.zzc(zzc);
                            zzd = zzcVar;
                            r2 = zzcVar;
                        }
                        break;
                    }
                    obj3 = r2;
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
        zzug zzugVar = new zzug();
        zzc = zzugVar;
        zzajc.zza((Class<zzug>) zzug.class, zzugVar);
    }

    private zzug() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzg = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzuh zzuhVar) {
        zzuhVar.getClass();
        this.zzf = zzuhVar;
        this.zze |= 1;
    }
}
