package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzvo extends zzajc<zzvo, zza> implements zzakp {
    private static final zzvo zzc;
    private static volatile zzakw<zzvo> zzd;
    private int zze;
    private int zzf;
    private zzvr zzg;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzvo, zza> implements zzakp {
        public final zza zza(zzvr zzvrVar) {
            zzi();
            ((zzvo) this.zza).zza(zzvrVar);
            return this;
        }

        public final zza zza(int i) {
            zzi();
            ((zzvo) this.zza).zza(0);
            return this;
        }

        private zza() {
            super(zzvo.zzc);
        }

        /* synthetic */ zza(zzvp zzvpVar) {
            this();
        }
    }

    public static zzvo zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzvo) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzvr zzd() {
        return this.zzg == null ? zzvr.zzd() : this.zzg;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzvo>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzvp zzvpVar = null;
        switch (zzvp.zza[i - 1]) {
            case 1:
                return new zzvo();
            case 2:
                return new zza(zzvpVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzvo> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzvo.class) {
                        zzakw<zzvo> zzakwVar2 = zzd;
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
        zzvo zzvoVar = new zzvo();
        zzc = zzvoVar;
        zzajc.zza((Class<zzvo>) zzvo.class, zzvoVar);
    }

    private zzvo() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzvr zzvrVar) {
        zzvrVar.getClass();
        this.zzg = zzvrVar;
        this.zze |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzf = i;
    }
}
