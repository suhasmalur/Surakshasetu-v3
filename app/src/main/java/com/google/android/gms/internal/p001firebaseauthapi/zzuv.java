package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzuv extends zzajc<zzuv, zza> implements zzakp {
    private static final zzuv zzc;
    private static volatile zzakw<zzuv> zzd;
    private int zze;
    private int zzf;
    private zzur zzg;
    private zzahp zzh = zzahp.zza;

    public final int zza() {
        return this.zzf;
    }

    public final zzur zzb() {
        return this.zzg == null ? zzur.zzf() : this.zzg;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzuv, zza> implements zzakp {
        public final zza zza(zzur zzurVar) {
            zzi();
            ((zzuv) this.zza).zza(zzurVar);
            return this;
        }

        public final zza zza(zzahp zzahpVar) {
            zzi();
            ((zzuv) this.zza).zza(zzahpVar);
            return this;
        }

        public final zza zza(int i) {
            zzi();
            ((zzuv) this.zza).zza(0);
            return this;
        }

        private zza() {
            super(zzuv.zzc);
        }

        /* synthetic */ zza(zzuw zzuwVar) {
            this();
        }
    }

    public static zza zzc() {
        return zzc.zzm();
    }

    public static zzuv zze() {
        return zzc;
    }

    public static zzuv zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzuv) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzahp zzf() {
        return this.zzh;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzuv>] */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r2;
        zzuw zzuwVar = null;
        switch (zzuw.zza[i - 1]) {
            case 1:
                return new zzuv();
            case 2:
                return new zza(zzuwVar);
            case 3:
                return zza(zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzuv> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzuv.class) {
                        zzakw<zzuv> zzakwVar2 = zzd;
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
        zzuv zzuvVar = new zzuv();
        zzc = zzuvVar;
        zzajc.zza((Class<zzuv>) zzuv.class, zzuvVar);
    }

    private zzuv() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzur zzurVar) {
        zzurVar.getClass();
        this.zzg = zzurVar;
        this.zze |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzahp zzahpVar) {
        zzahpVar.getClass();
        this.zzh = zzahpVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzf = i;
    }

    public final boolean zzg() {
        return (this.zze & 1) != 0;
    }
}
