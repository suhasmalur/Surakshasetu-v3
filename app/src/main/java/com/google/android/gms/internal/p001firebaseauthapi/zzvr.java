package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzvr extends zzajc<zzvr, zza> implements zzakp {
    private static final zzvr zzc;
    private static volatile zzakw<zzvr> zzd;
    private int zze;
    private String zzf = "";
    private zzvb zzg;

    public final zzvb zza() {
        return this.zzg == null ? zzvb.zzc() : this.zzg;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzvr, zza> implements zzakp {
        public final zza zza(zzvb zzvbVar) {
            zzi();
            ((zzvr) this.zza).zza(zzvbVar);
            return this;
        }

        public final zza zza(String str) {
            zzi();
            ((zzvr) this.zza).zza(str);
            return this;
        }

        private zza() {
            super(zzvr.zzc);
        }

        /* synthetic */ zza(zzvq zzvqVar) {
            this();
        }
    }

    public static zzvr zzd() {
        return zzc;
    }

    public static zzvr zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzvr) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzvr>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzvq zzvqVar = null;
        switch (zzvq.zza[i - 1]) {
            case 1:
                return new zzvr();
            case 2:
                return new zza(zzvqVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzvr> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzvr.class) {
                        zzakw<zzvr> zzakwVar2 = zzd;
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

    public final String zze() {
        return this.zzf;
    }

    static {
        zzvr zzvrVar = new zzvr();
        zzc = zzvrVar;
        zzajc.zza((Class<zzvr>) zzvr.class, zzvrVar);
    }

    private zzvr() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzvb zzvbVar) {
        zzvbVar.getClass();
        this.zzg = zzvbVar;
        this.zze |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str) {
        str.getClass();
        this.zzf = str;
    }

    public final boolean zzf() {
        return (this.zze & 1) != 0;
    }
}
