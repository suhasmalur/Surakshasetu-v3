package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public class zzajr {
    private static final zzaio zza = zzaio.zza;
    private zzahp zzb;
    private volatile zzakn zzc;
    private volatile zzahp zzd;

    public final int zzb() {
        if (this.zzd != null) {
            return this.zzd.zzb();
        }
        if (this.zzc != null) {
            return this.zzc.zzl();
        }
        return 0;
    }

    public int hashCode() {
        return 1;
    }

    public final zzahp zzc() {
        if (this.zzd != null) {
            return this.zzd;
        }
        synchronized (this) {
            if (this.zzd != null) {
                return this.zzd;
            }
            if (this.zzc == null) {
                this.zzd = zzahp.zza;
            } else {
                this.zzd = this.zzc.zzi();
            }
            return this.zzd;
        }
    }

    private final zzakn zzb(zzakn zzaknVar) {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    try {
                        this.zzc = zzaknVar;
                        this.zzd = zzahp.zza;
                    } catch (zzaji e) {
                        this.zzc = zzaknVar;
                        this.zzd = zzahp.zza;
                    }
                }
            }
        }
        return this.zzc;
    }

    public final zzakn zza(zzakn zzaknVar) {
        zzakn zzaknVar2 = this.zzc;
        this.zzb = null;
        this.zzd = null;
        this.zzc = zzaknVar;
        return zzaknVar2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzajr)) {
            return false;
        }
        zzajr zzajrVar = (zzajr) obj;
        zzakn zzaknVar = this.zzc;
        zzakn zzaknVar2 = zzajrVar.zzc;
        if (zzaknVar == null && zzaknVar2 == null) {
            return zzc().equals(zzajrVar.zzc());
        }
        if (zzaknVar != null && zzaknVar2 != null) {
            return zzaknVar.equals(zzaknVar2);
        }
        if (zzaknVar != null) {
            return zzaknVar.equals(zzajrVar.zzb(zzaknVar.zzh()));
        }
        return zzb(zzaknVar2.zzh()).equals(zzaknVar2);
    }
}
