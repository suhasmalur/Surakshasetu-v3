package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzvg;
import java.security.GeneralSecurityException;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcb {
    private final zzvg.zza zza;

    private final synchronized int zza(zzvb zzvbVar, boolean z) throws GeneralSecurityException {
        zzvg.zzb zzbVarZza;
        zzbVarZza = zza(zzvbVar);
        this.zza.zza(zzbVarZza);
        return zzbVarZza.zza();
    }

    private final synchronized int zzc() {
        int iZza;
        iZza = zzpf.zza();
        while (zzb(iZza)) {
            iZza = zzpf.zza();
        }
        return iZza;
    }

    public final synchronized zzbx zza() throws GeneralSecurityException {
        return zzbx.zza((zzvg) ((zzajc) this.zza.zzf()));
    }

    public final synchronized zzcb zza(zzbu zzbuVar) throws GeneralSecurityException {
        zza(zzbuVar.zza(), false);
        return this;
    }

    public final synchronized zzcb zza(int i) throws GeneralSecurityException {
        for (int i2 = 0; i2 < this.zza.zza(); i2++) {
            zzvg.zzb zzbVarZzb = this.zza.zzb(i2);
            if (zzbVarZzb.zza() == i) {
                if (!zzbVarZzb.zzc().equals(zzuz.ENABLED)) {
                    throw new GeneralSecurityException("cannot set key as primary because it's not enabled: " + i);
                }
                this.zza.zza(i);
            }
        }
        throw new GeneralSecurityException("key not found: " + i);
        return this;
    }

    public static zzcb zzb() {
        return new zzcb(zzvg.zzc());
    }

    public static zzcb zza(zzbx zzbxVar) {
        return new zzcb(zzbxVar.zzb().zzn());
    }

    private final synchronized zzvg.zzb zza(zzuy zzuyVar, zzvs zzvsVar) throws GeneralSecurityException {
        int iZzc;
        iZzc = zzc();
        if (zzvsVar == zzvs.UNKNOWN_PREFIX) {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        return (zzvg.zzb) ((zzajc) zzvg.zzb.zzd().zza(zzuyVar).zza(iZzc).zza(zzuz.ENABLED).zza(zzvsVar).zzf());
    }

    private final synchronized zzvg.zzb zza(zzvb zzvbVar) throws GeneralSecurityException {
        return zza(zzct.zza(zzvbVar), zzvbVar.zzd());
    }

    private zzcb(zzvg.zza zzaVar) {
        this.zza = zzaVar;
    }

    private final synchronized boolean zzb(int i) {
        Iterator<zzvg.zzb> it = this.zza.zzb().iterator();
        while (it.hasNext()) {
            if (it.next().zza() == i) {
                return true;
            }
        }
        return false;
    }
}
