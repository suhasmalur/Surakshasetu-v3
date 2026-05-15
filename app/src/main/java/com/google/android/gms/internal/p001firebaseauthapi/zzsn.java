package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzsn extends zzajc<zzsn, zza> implements zzakp {
    private static final zzsn zzc;
    private static volatile zzakw<zzsn> zzd;
    private int zze;
    private int zzf;
    private zzsr zzg;
    private zzahp zzh = zzahp.zza;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzsn, zza> implements zzakp {
        public final zza zza(zzahp zzahpVar) {
            zzi();
            ((zzsn) this.zza).zza(zzahpVar);
            return this;
        }

        public final zza zza(zzsr zzsrVar) {
            zzi();
            ((zzsn) this.zza).zza(zzsrVar);
            return this;
        }

        public final zza zza(int i) {
            zzi();
            ((zzsn) this.zza).zza(0);
            return this;
        }

        private zza() {
            super(zzsn.zzc);
        }

        /* synthetic */ zza(zzso zzsoVar) {
            this();
        }
    }

    public static zzsn zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzsn) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzsr zzd() {
        return this.zzg == null ? zzsr.zzd() : this.zzg;
    }

    public final zzahp zze() {
        return this.zzh;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzsn>] */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r2;
        zzso zzsoVar = null;
        switch (zzso.zza[i - 1]) {
            case 1:
                return new zzsn();
            case 2:
                return new zza(zzsoVar);
            case 3:
                return zza(zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzsn> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzsn.class) {
                        zzakw<zzsn> zzakwVar2 = zzd;
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
        zzsn zzsnVar = new zzsn();
        zzc = zzsnVar;
        zzajc.zza((Class<zzsn>) zzsn.class, zzsnVar);
    }

    private zzsn() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzahp zzahpVar) {
        zzahpVar.getClass();
        this.zzh = zzahpVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzsr zzsrVar) {
        zzsrVar.getClass();
        this.zzg = zzsrVar;
        this.zze |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzf = i;
    }
}
