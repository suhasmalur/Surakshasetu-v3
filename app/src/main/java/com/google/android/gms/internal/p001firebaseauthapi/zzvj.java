package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzvj extends zzajc<zzvj, zzb> implements zzakp {
    private static final zzvj zzc;
    private static volatile zzakw<zzvj> zzd;
    private int zze;
    private zzajj<zza> zzf = zzp();

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc<zza, C0009zza> implements zzakp {
        private static final zza zzc;
        private static volatile zzakw<zza> zzd;
        private String zze = "";
        private int zzf;
        private int zzg;
        private int zzh;

        public final int zza() {
            return this.zzg;
        }

        public static C0009zza zzb() {
            return zzc.zzm();
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.firebase-auth-api.zzvj$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
        public static final class C0009zza extends zzajc.zza<zza, C0009zza> implements zzakp {
            public final C0009zza zza(int i) {
                zzi();
                ((zza) this.zza).zza(i);
                return this;
            }

            public final C0009zza zza(zzvs zzvsVar) {
                zzi();
                ((zza) this.zza).zza(zzvsVar);
                return this;
            }

            public final C0009zza zza(zzuz zzuzVar) {
                zzi();
                ((zza) this.zza).zza(zzuzVar);
                return this;
            }

            public final C0009zza zza(String str) {
                zzi();
                ((zza) this.zza).zza(str);
                return this;
            }

            private C0009zza() {
                super(zza.zzc);
            }

            /* synthetic */ C0009zza(zzvi zzviVar) {
                this();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v13 */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzvj$zza>] */
        /* JADX WARN: Type inference failed for: r2v17 */
        /* JADX WARN: Type inference failed for: r2v18 */
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
        protected final Object zza(int i, Object obj, Object obj2) {
            ?? r2;
            zzvi zzviVar = null;
            switch (zzvi.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0009zza(zzviVar);
                case 3:
                    return zza(zzc, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzc;
                case 5:
                    zzakw<zza> zzakwVar = zzd;
                    Object obj3 = zzakwVar;
                    if (zzakwVar == null) {
                        synchronized (zza.class) {
                            zzakw<zza> zzakwVar2 = zzd;
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
            zza zzaVar = new zza();
            zzc = zzaVar;
            zzajc.zza((Class<zza>) zza.class, zzaVar);
        }

        private zza() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i) {
            this.zzg = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzvs zzvsVar) {
            this.zzh = zzvsVar.zza();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzuz zzuzVar) {
            this.zzf = zzuzVar.zza();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zze = str;
        }
    }

    public static zzb zza() {
        return zzc.zzm();
    }

    public final zza zza(int i) {
        return this.zzf.get(0);
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zzb extends zzajc.zza<zzvj, zzb> implements zzakp {
        public final zzb zza(zza zzaVar) {
            zzi();
            ((zzvj) this.zza).zza(zzaVar);
            return this;
        }

        public final zzb zza(int i) {
            zzi();
            ((zzvj) this.zza).zzc(i);
            return this;
        }

        private zzb() {
            super(zzvj.zzc);
        }

        /* synthetic */ zzb(zzvi zzviVar) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzvj>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzvi zzviVar = null;
        switch (zzvi.zza[i - 1]) {
            case 1:
                return new zzvj();
            case 2:
                return new zzb(zzviVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zza.class});
            case 4:
                return zzc;
            case 5:
                zzakw<zzvj> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzvj.class) {
                        zzakw<zzvj> zzakwVar2 = zzd;
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
        zzvj zzvjVar = new zzvj();
        zzc = zzvjVar;
        zzajc.zza((Class<zzvj>) zzvj.class, zzvjVar);
    }

    private zzvj() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zza zzaVar) {
        zzaVar.getClass();
        zzajj<zza> zzajjVar = this.zzf;
        if (!zzajjVar.zzc()) {
            this.zzf = zzajc.zza(zzajjVar);
        }
        this.zzf.add(zzaVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc(int i) {
        this.zze = i;
    }
}
