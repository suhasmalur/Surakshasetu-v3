package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzamb extends zzajc<zzamb, zza> implements zzakp {
    private static final zzamb zzc;
    private static volatile zzakw<zzamb> zzd;
    private long zze;
    private int zzf;

    public final int zza() {
        return this.zzf;
    }

    public final long zzb() {
        return this.zze;
    }

    public static zza zzc() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzamb, zza> implements zzakp {
        public final zza zza(int i) {
            if (!this.zza.zzu()) {
                zzj();
            }
            ((zzamb) this.zza).zza(i);
            return this;
        }

        public final zza zza(long j) {
            if (!this.zza.zzu()) {
                zzj();
            }
            ((zzamb) this.zza).zza(j);
            return this;
        }

        private zza() {
            super(zzamb.zzc);
        }

        /* synthetic */ zza(zzama zzamaVar) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzamb>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzama zzamaVar = null;
        switch (zzama.zza[i - 1]) {
            case 1:
                return new zzamb();
            case 2:
                return new zza(zzamaVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzamb> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzamb.class) {
                        zzakw<zzamb> zzakwVar2 = zzd;
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
        zzamb zzambVar = new zzamb();
        zzc = zzambVar;
        zzajc.zza((Class<zzamb>) zzamb.class, zzambVar);
    }

    private zzamb() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i) {
        this.zzf = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(long j) {
        this.zze = j;
    }
}
