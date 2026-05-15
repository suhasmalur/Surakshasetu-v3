package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzsj extends zzajc<zzsj, zza> implements zzakp {
    private static final zzsj zzc;
    private static volatile zzakw<zzsj> zzd;
    private int zze;
    private zzsm zzf;
    private int zzg;

    public final int zza() {
        return this.zzg;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzsj, zza> implements zzakp {
        public final zza zza(int i) {
            zzi();
            ((zzsj) this.zza).zza(i);
            return this;
        }

        public final zza zza(zzsm zzsmVar) {
            zzi();
            ((zzsj) this.zza).zza(zzsmVar);
            return this;
        }

        private zza() {
            super(zzsj.zzc);
        }

        /* synthetic */ zza(zzsk zzskVar) {
            this();
        }
    }

    public static zzsj zzd() {
        return zzc;
    }

    public static zzsj zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzsj) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzsm zze() {
        return this.zzf == null ? zzsm.zzd() : this.zzf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzsj>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzsk zzskVar = null;
        switch (zzsk.zza[i - 1]) {
            case 1:
                return new zzsj();
            case 2:
                return new zza(zzskVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzsj> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzsj.class) {
                        zzakw<zzsj> zzakwVar2 = zzd;
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
        zzsj zzsjVar = new zzsj();
        zzc = zzsjVar;
        zzajc.zza((Class<zzsj>) zzsj.class, zzsjVar);
    }

    private zzsj() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzg = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzsm zzsmVar) {
        zzsmVar.getClass();
        this.zzf = zzsmVar;
        this.zze |= 1;
    }
}
