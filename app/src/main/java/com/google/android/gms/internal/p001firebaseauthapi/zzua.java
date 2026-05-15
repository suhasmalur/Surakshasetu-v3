package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzua extends zzajc<zzua, zza> implements zzakp {
    private static final zzua zzc;
    private static volatile zzakw<zzua> zzd;
    private int zze;
    private zzahp zzf = zzahp.zza;
    private zzvj zzg;

    public static zza zza() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzajc.zza<zzua, zza> implements zzakp {
        public final zza zza(zzahp zzahpVar) {
            zzi();
            ((zzua) this.zza).zza(zzahpVar);
            return this;
        }

        public final zza zza(zzvj zzvjVar) {
            zzi();
            ((zzua) this.zza).zza(zzvjVar);
            return this;
        }

        private zza() {
            super(zzua.zzc);
        }

        /* synthetic */ zza(zztz zztzVar) {
            this();
        }
    }

    public static zzua zza(InputStream inputStream, zzaio zzaioVar) throws IOException {
        return (zzua) zzajc.zza(zzc, inputStream, zzaioVar);
    }

    public final zzahp zzc() {
        return this.zzf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.google.android.gms.internal.firebase-auth-api.zzajc$zzc, com.google.android.gms.internal.firebase-auth-api.zzakw<com.google.android.gms.internal.firebase-auth-api.zzua>] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzajc
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r1;
        zztz zztzVar = null;
        switch (zztz.zza[i - 1]) {
            case 1:
                return new zzua();
            case 2:
                return new zza(zztzVar);
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0001\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzakw<zzua> zzakwVar = zzd;
                Object obj3 = zzakwVar;
                if (zzakwVar == null) {
                    synchronized (zzua.class) {
                        zzakw<zzua> zzakwVar2 = zzd;
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
        zzua zzuaVar = new zzua();
        zzc = zzuaVar;
        zzajc.zza((Class<zzua>) zzua.class, zzuaVar);
    }

    private zzua() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzahp zzahpVar) {
        zzahpVar.getClass();
        this.zzf = zzahpVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzvj zzvjVar) {
        zzvjVar.getClass();
        this.zzg = zzvjVar;
        this.zze |= 1;
    }
}
