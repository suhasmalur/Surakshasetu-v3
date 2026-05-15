package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzuq extends zzajc<zzuq, zza> implements zzakp {
    private static final zzuq zzc;
    private static volatile zzakw<zzuq> zzd;
    private int zze;
    private zzur zzf;

    public static zza zza() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzuq, zza> implements zzakp {
        public final zza zza(zzur zzurVar) {
            zzi();
            ((zzuq) this.zza).zza(zzurVar);
            return this;
        }

        private zza() {
            super(zzuq.zzc);
        }

        /* synthetic */ zza(zzup zzupVar) {
            this();
        }
    }

    public static zzuq zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zzuq) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zzur zzc() {
        return this.zzf == null ? zzur.zzf() : this.zzf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzuq>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzup zzupVar = null;
        switch (zzup.zza[i - 1]) {
            case 1:
                return new zzuq();
            case 2:
                return new zza(zzupVar);
            case 3:
                return zza(zzc, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzuq> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzuq.class) {
                        zzakw<zzuq> zzakwVar2 = zzd;
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
        zzuq zzuqVar = new zzuq();
        zzc = zzuqVar;
        zzajc.zza((Class<zzuq>) zzuq.class, zzuqVar);
    }

    private zzuq() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzur zzurVar) {
        zzurVar.getClass();
        this.zzf = zzurVar;
        this.zze |= 1;
    }
}
