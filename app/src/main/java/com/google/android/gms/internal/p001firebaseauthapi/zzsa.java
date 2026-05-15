package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzsa extends zzajc<zzsa, zza> implements zzakp {
    private static final zzsa zzc;
    private static volatile zzakw<zzsa> zzd;
    private int zze;
    private int zzf;
    private zzsb zzg;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzsa, zza> implements zzakp {
        public final zza zza(int i) {
            zzi();
            ((zzsa) this.zza).zza(i);
            return this;
        }

        public final zza zza(zzsb zzsbVar) {
            zzi();
            ((zzsa) this.zza).zza(zzsbVar);
            return this;
        }

        private zza() {
            super(zzsa.zzc);
        }

        /* synthetic */ zza(zzrz zzrzVar) {
            this();
        }
    }

    public static zzsa zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzsa) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzsb zzd() {
        return this.zzg == null ? zzsb.zzd() : this.zzg;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzsa>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzrz zzrzVar = null;
        switch (zzrz.zza[i - 1]) {
            case 1:
                return new zzsa();
            case 2:
                return new zza(zzrzVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzsa> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzsa.class) {
                        zzakw<zzsa> zzakwVar2 = zzd;
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
        zzsa zzsaVar = new zzsa();
        zzc = zzsaVar;
        zzajc.zza((Class<zzsa>) zzsa.class, zzsaVar);
    }

    private zzsa() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzf = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzsb zzsbVar) {
        zzsbVar.getClass();
        this.zzg = zzsbVar;
        this.zze |= 1;
    }
}
