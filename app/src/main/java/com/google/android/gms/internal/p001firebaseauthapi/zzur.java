package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzur extends zzajc<zzur, zza> implements zzakp {
    private static final zzur zzc;
    private static volatile zzakw<zzur> zzd;
    private int zze;
    private int zzf;
    private int zzg;

    public final zzuk zza() {
        zzuk zzukVarZza = zzuk.zza(this.zzg);
        return zzukVarZza == null ? zzuk.UNRECOGNIZED : zzukVarZza;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzur, zza> implements zzakp {
        public final zza zza(zzuk zzukVar) {
            zzi();
            ((zzur) this.zza).zza(zzukVar);
            return this;
        }

        public final zza zza(zzum zzumVar) {
            zzi();
            ((zzur) this.zza).zza(zzumVar);
            return this;
        }

        public final zza zza(zzuo zzuoVar) {
            zzi();
            ((zzur) this.zza).zza(zzuoVar);
            return this;
        }

        private zza() {
            super(zzur.zzc);
        }

        /* synthetic */ zza(zzus zzusVar) {
            this();
        }
    }

    public final zzum zzb() {
        zzum zzumVarZza = zzum.zza(this.zzf);
        return zzumVarZza == null ? zzum.UNRECOGNIZED : zzumVarZza;
    }

    public final zzuo zzc() {
        zzuo zzuoVarZza = zzuo.zza(this.zze);
        return zzuoVarZza == null ? zzuo.UNRECOGNIZED : zzuoVarZza;
    }

    public static zza zzd() {
        return zzc.zzm();
    }

    public static zzur zzf() {
        return zzc;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzur>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzus zzusVar = null;
        switch (zzus.zza[i - 1]) {
            case 1:
                return new zzur();
            case 2:
                return new zza(zzusVar);
            case 3:
                return zza(zzc, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u0003\f", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzur> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzur.class) {
                        zzakw<zzur> zzakwVar2 = zzd;
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
        zzur zzurVar = new zzur();
        zzc = zzurVar;
        zzajc.zza((Class<zzur>) zzur.class, zzurVar);
    }

    private zzur() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzuk zzukVar) {
        this.zzg = zzukVar.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzum zzumVar) {
        this.zzf = zzumVar.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzuo zzuoVar) {
        this.zze = zzuoVar.zza();
    }
}
