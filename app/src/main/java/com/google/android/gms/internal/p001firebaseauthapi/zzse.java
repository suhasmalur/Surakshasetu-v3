package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzse extends zzajc<zzse, zza> implements zzakp {
    private static final zzse zzc;
    private static volatile zzakw<zzse> zzd;
    private int zze;
    private int zzf;
    private zzsi zzg;
    private zzuc zzh;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzse, zza> implements zzakp {
        public final zza zza(zzsi zzsiVar) {
            zzi();
            ((zzse) this.zza).zza(zzsiVar);
            return this;
        }

        public final zza zza(zzuc zzucVar) {
            zzi();
            ((zzse) this.zza).zza(zzucVar);
            return this;
        }

        public final zza zza(int i) {
            zzi();
            ((zzse) this.zza).zza(i);
            return this;
        }

        private zza() {
            super(zzse.zzc);
        }

        /* synthetic */ zza(zzsd zzsdVar) {
            this();
        }
    }

    public static zzse zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzse) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzsi zzd() {
        return this.zzg == null ? zzsi.zzd() : this.zzg;
    }

    public final zzuc zze() {
        return this.zzh == null ? zzuc.zzd() : this.zzh;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzse>] */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r2;
        zzsd zzsdVar = null;
        switch (zzsd.zza[i - 1]) {
            case 1:
                return new zzse();
            case 2:
                return new zza(zzsdVar);
            case 3:
                return zza(zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003ဉ\u0001", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzse> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzse.class) {
                        zzakw<zzse> zzakwVar2 = zzd;
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
        zzse zzseVar = new zzse();
        zzc = zzseVar;
        zzajc.zza((Class<zzse>) zzse.class, zzseVar);
    }

    private zzse() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzsi zzsiVar) {
        zzsiVar.getClass();
        this.zzg = zzsiVar;
        this.zze |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzuc zzucVar) {
        zzucVar.getClass();
        this.zzh = zzucVar;
        this.zze |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzf = i;
    }
}
