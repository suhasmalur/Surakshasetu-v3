package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzrw extends zzajc<zzrw, zza> implements zzakp {
    private static final zzrw zzc;
    private static volatile zzakw<zzrw> zzd;
    private int zze;
    private int zzf;
    private zzahp zzg = zzahp.zza;
    private zzsb zzh;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzrw, zza> implements zzakp {
        public final zza zza(zzahp zzahpVar) {
            zzi();
            ((zzrw) this.zza).zza(zzahpVar);
            return this;
        }

        public final zza zza(zzsb zzsbVar) {
            zzi();
            ((zzrw) this.zza).zza(zzsbVar);
            return this;
        }

        public final zza zza(int i) {
            zzi();
            ((zzrw) this.zza).zza(0);
            return this;
        }

        private zza() {
            super(zzrw.zzc);
        }

        /* synthetic */ zza(zzry zzryVar) {
            this();
        }
    }

    public static zzrw zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzrw) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzsb zzd() {
        return this.zzh == null ? zzsb.zzd() : this.zzh;
    }

    public final zzahp zze() {
        return this.zzg;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzrw>] */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r2;
        zzry zzryVar = null;
        switch (zzry.zza[i - 1]) {
            case 1:
                return new zzrw();
            case 2:
                return new zza(zzryVar);
            case 3:
                return zza(zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003ဉ\u0000", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzrw> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzrw.class) {
                        zzakw<zzrw> zzakwVar2 = zzd;
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
        zzrw zzrwVar = new zzrw();
        zzc = zzrwVar;
        zzajc.zza((Class<zzrw>) zzrw.class, zzrwVar);
    }

    private zzrw() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzahp zzahpVar) {
        zzahpVar.getClass();
        this.zzg = zzahpVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzsb zzsbVar) {
        zzsbVar.getClass();
        this.zzh = zzsbVar;
        this.zze |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzf = 0;
    }
}
