package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zztg extends zzajc<zztg, zza> implements zzakp {
    private static final zztg zzc;
    private static volatile zzakw<zztg> zzd;
    private int zze;
    private zzahp zzf = zzahp.zza;

    public final int zza() {
        return this.zze;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zztg, zza> implements zzakp {
        public final zza zza(zzahp zzahpVar) {
            zzi();
            ((zztg) this.zza).zza(zzahpVar);
            return this;
        }

        public final zza zza(int i) {
            zzi();
            ((zztg) this.zza).zza(0);
            return this;
        }

        private zza() {
            super(zztg.zzc);
        }

        /* synthetic */ zza(zztf zztfVar) {
            this();
        }
    }

    public static zztg zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zztg) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzahp zzd() {
        return this.zzf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zztg>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zztf zztfVar = null;
        switch (zztf.zza[i - 1]) {
            case 1:
                return new zztg();
            case 2:
                return new zza(zztfVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzakw<zztg> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zztg.class) {
                        zzakw<zztg> zzakwVar2 = zzd;
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
        zztg zztgVar = new zztg();
        zzc = zztgVar;
        zzajc.zza((Class<zztg>) zztg.class, zztgVar);
    }

    private zztg() {
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
