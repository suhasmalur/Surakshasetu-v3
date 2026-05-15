package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzvb extends zzajc<zzvb, zza> implements zzakp {
    private static final zzvb zzc;
    private static volatile zzakw<zzvb> zzd;
    private String zze = "";
    private zzahp zzf = zzahp.zza;
    private int zzg;

    public static zza zza() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzvb, zza> implements zzakp {
        public final zza zza(zzvs zzvsVar) {
            zzi();
            ((zzvb) this.zza).zza(zzvsVar);
            return this;
        }

        public final zza zza(String str) {
            zzi();
            ((zzvb) this.zza).zza(str);
            return this;
        }

        public final zza zza(zzahp zzahpVar) {
            zzi();
            ((zzvb) this.zza).zza(zzahpVar);
            return this;
        }

        private zza() {
            super(zzvb.zzc);
        }

        /* synthetic */ zza(zzvd zzvdVar) {
            this();
        }
    }

    public static zzvb zzc() {
        return zzc;
    }

    public static zzvb zza(byte[] bArr, zzaio zzaioVar) throws zzaji {
        return (zzvb) zzajc.zza(zzc, bArr, zzaioVar);
    }

    public final zzvs zzd() {
        zzvs zzvsVarZza = zzvs.zza(this.zzg);
        return zzvsVarZza == null ? zzvs.UNRECOGNIZED : zzvsVarZza;
    }

    public final zzahp zze() {
        return this.zzf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzvb>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzvd zzvdVar = null;
        switch (zzvd.zza[i - 1]) {
            case 1:
                return new zzvb();
            case 2:
                return new zza(zzvdVar);
            case 3:
                return zza(zzc, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzvb> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzvb.class) {
                        zzakw<zzvb> zzakwVar2 = zzd;
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

    public final String zzf() {
        return this.zze;
    }

    static {
        zzvb zzvbVar = new zzvb();
        zzc = zzvbVar;
        zzajc.zza((Class<zzvb>) zzvb.class, zzvbVar);
    }

    private zzvb() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzvs zzvsVar) {
        this.zzg = zzvsVar.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str) {
        str.getClass();
        this.zze = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzahp zzahpVar) {
        zzahpVar.getClass();
        this.zzf = zzahpVar;
    }
}
