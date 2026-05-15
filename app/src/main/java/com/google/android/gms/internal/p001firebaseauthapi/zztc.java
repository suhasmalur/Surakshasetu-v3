package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zztc extends zzajc<zztc, zza> implements zzakp {
    private static final zztc zzc;
    private static volatile zzakw<zztc> zzd;
    private int zze;
    private zzahp zzf = zzahp.zza;

    public final int zza() {
        return this.zze;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zztc, zza> implements zzakp {
        public final zza zza(zzahp zzahpVar) {
            zzi();
            ((zztc) this.zza).zza(zzahpVar);
            return this;
        }

        public final zza zza(int i) {
            zzi();
            ((zztc) this.zza).zza(i);
            return this;
        }

        private zza() {
            super(zztc.zzc);
        }

        /* synthetic */ zza(zztb zztbVar) {
            this();
        }
    }

    public static zztc zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zztc) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzahp zzd() {
        return this.zzf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zztc>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zztb zztbVar = null;
        switch (zztb.zza[i - 1]) {
            case 1:
                return new zztc();
            case 2:
                return new zza(zztbVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzakw<zztc> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zztc.class) {
                        zzakw<zztc> zzakwVar2 = zzd;
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
        zztc zztcVar = new zztc();
        zzc = zztcVar;
        zzajc.zza((Class<zztc>) zztc.class, zztcVar);
    }

    private zztc() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzahp zzahpVar) {
        zzahpVar.getClass();
        this.zzf = zzahpVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zze = i;
    }
}
