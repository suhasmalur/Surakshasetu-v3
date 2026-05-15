package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zztn extends zzajc<zztn, zza> implements zzakp {
    private static final zztn zzc;
    private static volatile zzakw<zztn> zzd;
    private int zze;
    private zztq zzf;

    public static zza zza() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zztn, zza> implements zzakp {
        public final zza zza(zztq zztqVar) {
            zzi();
            ((zztn) this.zza).zza(zztqVar);
            return this;
        }

        private zza() {
            super(zztn.zzc);
        }

        /* synthetic */ zza(zzto zztoVar) {
            this();
        }
    }

    public static zztn zza(zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (zztn) zzajc.zza(zzc, zzahpVar, zzaioVar);
    }

    public final zztq zzc() {
        return this.zzf == null ? zztq.zze() : this.zzf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zztn>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zzto zztoVar = null;
        switch (zzto.zza[i - 1]) {
            case 1:
                return new zztn();
            case 2:
                return new zza(zztoVar);
            case 3:
                return zza(zzc, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzakw<zztn> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zztn.class) {
                        zzakw<zztn> zzakwVar2 = zzd;
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
        zztn zztnVar = new zztn();
        zzc = zztnVar;
        zzajc.zza((Class<zztn>) zztn.class, zztnVar);
    }

    private zztn() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zztq zztqVar) {
        zztqVar.getClass();
        this.zzf = zztqVar;
        this.zze |= 1;
    }
}
