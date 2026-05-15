package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzsq extends zzajc<zzsq, zza> implements zzakp {
    private static final zzsq zzc;
    private static volatile zzakw<zzsq> zzd;
    private int zze;
    private zzsr zzf;
    private int zzg;

    public final int zza() {
        return this.zzg;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzsq, zza> implements zzakp {
        public final zza zza(int i) {
            zzi();
            ((zzsq) this.zza).zza(i);
            return this;
        }

        public final zza zza(zzsr zzsrVar) {
            zzi();
            ((zzsq) this.zza).zza(zzsrVar);
            return this;
        }

        private zza() {
            super(zzsq.zzc);
        }

        /* synthetic */ zza(zzsp zzspVar) {
            this();
        }
    }

    public static zzsq zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzsq) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzsr zzd() {
        return this.zzf == null ? zzsr.zzd() : this.zzf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzsq>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzsp zzspVar = null;
        switch (zzsp.zza[i - 1]) {
            case 1:
                return new zzsq();
            case 2:
                return new zza(zzspVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzsq> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzsq.class) {
                        zzakw<zzsq> zzakwVar2 = zzd;
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
        zzsq zzsqVar = new zzsq();
        zzc = zzsqVar;
        zzajc.zza((Class<zzsq>) zzsq.class, zzsqVar);
    }

    private zzsq() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzg = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzsr zzsrVar) {
        zzsrVar.getClass();
        this.zzf = zzsrVar;
        this.zze |= 1;
    }
}
