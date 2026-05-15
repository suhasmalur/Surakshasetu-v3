package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zztq extends zzajc<zztq, zza> implements zzakp {
    private static final zztq zzc;
    private static volatile zzakw<zztq> zzd;
    private int zze;
    private zztv zzf;
    private zztm zzg;
    private int zzh;

    public final zztk zza() {
        zztk zztkVarZza = zztk.zza(this.zzh);
        return zztkVarZza == null ? zztk.UNRECOGNIZED : zztkVarZza;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zztq, zza> implements zzakp {
        public final zza zza(zztm zztmVar) {
            zzi();
            ((zztq) this.zza).zza(zztmVar);
            return this;
        }

        public final zza zza(zztk zztkVar) {
            zzi();
            ((zztq) this.zza).zza(zztkVar);
            return this;
        }

        public final zza zza(zztv zztvVar) {
            zzi();
            ((zztq) this.zza).zza(zztvVar);
            return this;
        }

        private zza() {
            super(zztq.zzc);
        }

        /* synthetic */ zza(zztp zztpVar) {
            this();
        }
    }

    public final zztm zzb() {
        return this.zzg == null ? zztm.zzc() : this.zzg;
    }

    public static zza zzc() {
        return zzc.zzm();
    }

    public static zztq zze() {
        return zzc;
    }

    public final zztv zzf() {
        return this.zzf == null ? zztv.zzc() : this.zzf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zztq>] */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r2;
        zztp zztpVar = null;
        switch (zztp.zza[i - 1]) {
            case 1:
                return new zztq();
            case 2:
                return new zza(zztpVar);
            case 3:
                return zza(zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzakw<zztq> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zztq.class) {
                        zzakw<zztq> zzakwVar2 = zzd;
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
        zztq zztqVar = new zztq();
        zzc = zztqVar;
        zzajc.zza((Class<zztq>) zztq.class, zztqVar);
    }

    private zztq() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zztm zztmVar) {
        zztmVar.getClass();
        this.zzg = zztmVar;
        this.zze |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zztk zztkVar) {
        this.zzh = zztkVar.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zztv zztvVar) {
        zztvVar.getClass();
        this.zzf = zztvVar;
        this.zze |= 1;
    }
}
