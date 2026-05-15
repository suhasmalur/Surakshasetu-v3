package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzos implements zzov {
    private final String zza;
    private final zzxt zzb;
    private final zzahp zzc;
    private final zzuy.zza zzd;
    private final zzvs zze;

    @Nullable
    private final Integer zzf;

    public static zzos zza(String str, zzahp zzahpVar, zzuy.zza zzaVar, zzvs zzvsVar, @Nullable Integer num) throws GeneralSecurityException {
        if (zzvsVar == zzvs.RAW) {
            if (num != null) {
                throw new GeneralSecurityException("Keys with output prefix type raw should not have an id requirement.");
            }
        } else if (num == null) {
            throw new GeneralSecurityException("Keys with output prefix type different from raw should have an id requirement.");
        }
        return new zzos(str, zzahpVar, zzaVar, zzvsVar, num);
    }

    public final zzuy.zza zza() {
        return this.zzd;
    }

    public final zzvs zzc() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzov
    public final zzxt zzb() {
        return this.zzb;
    }

    public final zzahp zzd() {
        return this.zzc;
    }

    @Nullable
    public final Integer zze() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zza;
    }

    private zzos(String str, zzahp zzahpVar, zzuy.zza zzaVar, zzvs zzvsVar, @Nullable Integer num) {
        this.zza = str;
        this.zzb = zzpf.zzb(str);
        this.zzc = zzahpVar;
        this.zzd = zzaVar;
        this.zze = zzvsVar;
        this.zzf = num;
    }
}
