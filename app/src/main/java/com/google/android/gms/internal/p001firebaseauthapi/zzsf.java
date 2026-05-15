package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzsf extends zzajc<zzsf, zza> implements zzakp {
    private static final zzsf zzc;
    private static volatile zzakw<zzsf> zzd;
    private int zze;
    private zzsj zzf;
    private zzug zzg;

    public static zza zza() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzsf, zza> implements zzakp {
        public final zza zza(zzsj zzsjVar) {
            zzi();
            ((zzsf) this.zza).zza(zzsjVar);
            return this;
        }

        public final zza zza(zzug zzugVar) {
            zzi();
            ((zzsf) this.zza).zza(zzugVar);
            return this;
        }

        private zza() {
            super(zzsf.zzc);
        }

        /* synthetic */ zza(zzsg zzsgVar) {
            this();
        }
    }

    public static zzsf zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzsf) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzsj zzc() {
        return this.zzf == null ? zzsj.zzd() : this.zzf;
    }

    public final zzug zzd() {
        return this.zzg == null ? zzug.zze() : this.zzg;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzsf>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzsg zzsgVar = null;
        switch (zzsg.zza[i - 1]) {
            case 1:
                return new zzsf();
            case 2:
                return new zza(zzsgVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzsf> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzsf.class) {
                        zzakw<zzsf> zzakwVar2 = zzd;
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
        zzsf zzsfVar = new zzsf();
        zzc = zzsfVar;
        zzajc.zza((Class<zzsf>) zzsf.class, zzsfVar);
    }

    private zzsf() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzsj zzsjVar) {
        zzsjVar.getClass();
        this.zzf = zzsjVar;
        this.zze |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzug zzugVar) {
        zzugVar.getClass();
        this.zzg = zzugVar;
        this.zze |= 2;
    }
}
