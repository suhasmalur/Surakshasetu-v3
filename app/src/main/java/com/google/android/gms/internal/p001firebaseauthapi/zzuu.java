package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzuu extends zzajc<zzuu, zza> implements zzakp {
    private static final zzuu zzc;
    private static volatile zzakw<zzuu> zzd;
    private int zze;
    private int zzf;
    private zzuv zzg;
    private zzahp zzh = zzahp.zza;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzuu, zza> implements zzakp {
        public final zza zza(zzahp zzahpVar) {
            zzi();
            ((zzuu) this.zza).zza(zzahpVar);
            return this;
        }

        public final zza zza(zzuv zzuvVar) {
            zzi();
            ((zzuu) this.zza).zza(zzuvVar);
            return this;
        }

        public final zza zza(int i) {
            zzi();
            ((zzuu) this.zza).zza(0);
            return this;
        }

        private zza() {
            super(zzuu.zzc);
        }

        /* synthetic */ zza(zzut zzutVar) {
            this();
        }
    }

    public static zzuu zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzuu) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzuv zzd() {
        return this.zzg == null ? zzuv.zze() : this.zzg;
    }

    public final zzahp zze() {
        return this.zzh;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzuu>] */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r2;
        zzut zzutVar = null;
        switch (zzut.zza[i - 1]) {
            case 1:
                return new zzuu();
            case 2:
                return new zza(zzutVar);
            case 3:
                return zza(zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzuu> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzuu.class) {
                        zzakw<zzuu> zzakwVar2 = zzd;
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
        zzuu zzuuVar = new zzuu();
        zzc = zzuuVar;
        zzajc.zza((Class<zzuu>) zzuu.class, zzuuVar);
    }

    private zzuu() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzahp zzahpVar) {
        zzahpVar.getClass();
        this.zzh = zzahpVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzuv zzuvVar) {
        zzuvVar.getClass();
        this.zzg = zzuvVar;
        this.zze |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzf = i;
    }

    public final boolean zzf() {
        return (this.zze & 1) != 0;
    }
}
