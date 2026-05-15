package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zztu extends zzajc<zztu, zza> implements zzakp {
    private static final zztu zzc;
    private static volatile zzakw<zztu> zzd;
    private int zze;
    private int zzf;
    private zztq zzg;
    private zzahp zzh = zzahp.zza;
    private zzahp zzi = zzahp.zza;

    public final int zza() {
        return this.zzf;
    }

    public final zztq zzb() {
        return this.zzg == null ? zztq.zze() : this.zzg;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zztu, zza> implements zzakp {
        public final zza zza(zztq zztqVar) {
            zzi();
            ((zztu) this.zza).zza(zztqVar);
            return this;
        }

        public final zza zza(int i) {
            zzi();
            ((zztu) this.zza).zza(0);
            return this;
        }

        public final zza zza(zzahp zzahpVar) {
            zzi();
            ((zztu) this.zza).zza(zzahpVar);
            return this;
        }

        public final zza zzb(zzahp zzahpVar) {
            zzi();
            ((zztu) this.zza).zzb(zzahpVar);
            return this;
        }

        private zza() {
            super(zztu.zzc);
        }

        /* synthetic */ zza(zztt zzttVar) {
            this();
        }
    }

    public static zza zzc() {
        return zzc.zzm();
    }

    public static zztu zze() {
        return zzc;
    }

    public static zztu zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zztu) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzahp zzf() {
        return this.zzh;
    }

    public final zzahp zzg() {
        return this.zzi;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zztu>] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r3;
        zztt zzttVar = null;
        switch (zztt.zza[i - 1]) {
            case 1:
                return new zztu();
            case 2:
                return new zza(zzttVar);
            case 3:
                return zza(zzc, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n\u0004\n", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
            case 4:
                return zzc;
            case 5:
                zzakw<zztu> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zztu.class) {
                        zzakw<zztu> zzakwVar2 = zzd;
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
        zztu zztuVar = new zztu();
        zzc = zztuVar;
        zzajc.zza((Class<zztu>) zztu.class, zztuVar);
    }

    private zztu() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zztq zztqVar) {
        zztqVar.getClass();
        this.zzg = zztqVar;
        this.zze |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzf = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzahp zzahpVar) {
        zzahpVar.getClass();
        this.zzh = zzahpVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(zzahp zzahpVar) {
        zzahpVar.getClass();
        this.zzi = zzahpVar;
    }
}
