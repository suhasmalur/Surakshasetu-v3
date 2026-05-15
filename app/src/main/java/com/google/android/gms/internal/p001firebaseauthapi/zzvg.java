package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzvg extends zzajc<zzvg, zza> implements zzakp {
    private static final zzvg zzc;
    private static volatile zzakw<zzvg> zzd;
    private int zze;
    private zzajj<zzb> zzf = zzp();

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzvg, zza> implements zzakp {
        public final int zza() {
            return ((zzvg) this.zza).zza();
        }

        public final zza zza(zzb zzbVar) {
            zzi();
            ((zzvg) this.zza).zza(zzbVar);
            return this;
        }

        public final zza zza(int i) {
            zzi();
            ((zzvg) this.zza).zzc(i);
            return this;
        }

        public final zzb zzb(int i) {
            return ((zzvg) this.zza).zza(i);
        }

        public final List<zzb> zzb() {
            return Collections.unmodifiableList(((zzvg) this.zza).zze());
        }

        private zza() {
            super(zzvg.zzc);
        }

        /* synthetic */ zza(zzvh zzvhVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zzb extends zzajc<zzb, zza> implements zzakp {
        private static final zzb zzc;
        private static volatile zzakw<zzb> zzd;
        private int zze;
        private zzuy zzf;
        private int zzg;
        private int zzh;
        private int zzi;

        public final int zza() {
            return this.zzh;
        }

        public final zzuy zzb() {
            return this.zzf == null ? zzuy.zzd() : this.zzf;
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
        public static final class zza extends zzajc.zza<zzb, zza> implements zzakp {
            public final zza zza(zzuy zzuyVar) {
                zzi();
                ((zzb) this.zza).zza(zzuyVar);
                return this;
            }

            public final zza zza(int i) {
                zzi();
                ((zzb) this.zza).zza(i);
                return this;
            }

            public final zza zza(zzvs zzvsVar) {
                zzi();
                ((zzb) this.zza).zza(zzvsVar);
                return this;
            }

            public final zza zza(zzuz zzuzVar) {
                zzi();
                ((zzb) this.zza).zza(zzuzVar);
                return this;
            }

            private zza() {
                super(zzb.zzc);
            }

            /* synthetic */ zza(zzvh zzvhVar) {
                this();
            }
        }

        public final zzuz zzc() {
            zzuz zzuzVarZza = zzuz.zza(this.zzg);
            return zzuzVarZza == null ? zzuz.UNRECOGNIZED : zzuzVarZza;
        }

        public static zza zzd() {
            return zzc.zzm();
        }

        public final zzvs zzf() {
            zzvs zzvsVarZza = zzvs.zza(this.zzi);
            return zzvsVarZza == null ? zzvs.UNRECOGNIZED : zzvsVarZza;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v13 */
        /* JADX WARN: Type inference failed for: r3v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzvg$zzb>] */
        /* JADX WARN: Type inference failed for: r3v17 */
        /* JADX WARN: Type inference failed for: r3v18 */
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
        protected final Object zza(int i, Object obj, Object obj2) {
            ?? r3;
            zzvh zzvhVar = null;
            switch (zzvh.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzvhVar);
                case 3:
                    return zza(zzc, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzc;
                case 5:
                    zzakw<zzb> zzakwVar = zzd;
                    Object obj3 = zzakwVar;
                    if (zzakwVar == null) {
                        synchronized (zzb.class) {
                            zzakw<zzb> zzakwVar2 = zzd;
                            r3 = zzakwVar2;
                            if (zzakwVar2 == null) {
                                ?? zzcVar = new zzajc.zzc(zzc);
                                zzd = zzcVar;
                                r3 = zzcVar;
                            }
                            break;
                        }
                        obj3 = r3;
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
            zzb zzbVar = new zzb();
            zzc = zzbVar;
            zzajc.zza((Class<zzb>) zzb.class, zzbVar);
        }

        private zzb() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzuy zzuyVar) {
            zzuyVar.getClass();
            this.zzf = zzuyVar;
            this.zze |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i) {
            this.zzh = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzvs zzvsVar) {
            this.zzi = zzvsVar.zza();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzuz zzuzVar) {
            this.zzg = zzuzVar.zza();
        }

        public final boolean zzg() {
            return (this.zze & 1) != 0;
        }
    }

    public final int zza() {
        return this.zzf.size();
    }

    public final int zzb() {
        return this.zze;
    }

    public static zza zzc() {
        return zzc.zzm();
    }

    public final zzb zza(int i) {
        return this.zzf.get(i);
    }

    public static zzvg zza(InputStream inputStream, zzaio zzaioVar) throws IOException {
        return (zzvg) zzajc.zza(zzc, inputStream, zzaioVar);
    }

    public static zzvg zza(byte[] bArr, zzaio zzaioVar) throws zzaji {
        return (zzvg) zzajc.zza(zzc, bArr, zzaioVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzvg>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzvh zzvhVar = null;
        switch (zzvh.zza[i - 1]) {
            case 1:
                return new zzvg();
            case 2:
                return new zza(zzvhVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zzb.class});
            case 4:
                return zzc;
            case 5:
                zzakw<zzvg> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzvg.class) {
                        zzakw<zzvg> zzakwVar2 = zzd;
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

    public final List<zzb> zze() {
        return this.zzf;
    }

    static {
        zzvg zzvgVar = new zzvg();
        zzc = zzvgVar;
        zzajc.zza((Class<zzvg>) zzvg.class, zzvgVar);
    }

    private zzvg() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzb zzbVar) {
        zzbVar.getClass();
        zzajj<zzb> zzajjVar = this.zzf;
        if (!zzajjVar.zzc()) {
            this.zzf = zzajc.zza(zzajjVar);
        }
        this.zzf.add(zzbVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc(int i) {
        this.zze = i;
    }
}
