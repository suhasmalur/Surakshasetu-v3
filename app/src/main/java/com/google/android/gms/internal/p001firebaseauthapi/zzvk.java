package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzvk extends zzajc<zzvk, zza> implements zzakp {
    private static final zzvk zzc;
    private static volatile zzakw<zzvk> zzd;
    private int zze;
    private int zzf;
    private zzvn zzg;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzvk, zza> implements zzakp {
        public final zza zza(zzvn zzvnVar) {
            zzi();
            ((zzvk) this.zza).zza(zzvnVar);
            return this;
        }

        public final zza zza(int i) {
            zzi();
            ((zzvk) this.zza).zza(0);
            return this;
        }

        private zza() {
            super(zzvk.zzc);
        }

        /* synthetic */ zza(zzvl zzvlVar) {
            this();
        }
    }

    public static zzvk zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzvk) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzvn zzd() {
        return this.zzg == null ? zzvn.zzc() : this.zzg;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzvk>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzvl zzvlVar = null;
        switch (zzvl.zza[i - 1]) {
            case 1:
                return new zzvk();
            case 2:
                return new zza(zzvlVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzvk> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzvk.class) {
                        zzakw<zzvk> zzakwVar2 = zzd;
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
        zzvk zzvkVar = new zzvk();
        zzc = zzvkVar;
        zzajc.zza((Class<zzvk>) zzvk.class, zzvkVar);
    }

    private zzvk() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzvn zzvnVar) {
        zzvnVar.getClass();
        this.zzg = zzvnVar;
        this.zze |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzf = i;
    }
}
