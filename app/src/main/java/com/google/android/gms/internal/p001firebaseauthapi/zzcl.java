package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcl<P> {

    @Nullable
    private final P zza;

    @Nullable
    private final P zzb;
    private final byte[] zzc;
    private final zzuz zzd;
    private final zzvs zze;
    private final int zzf;
    private final String zzg;
    private final zzbt zzh;

    public final int zza() {
        return this.zzf;
    }

    public final zzbt zzb() {
        return this.zzh;
    }

    public final zzuz zzc() {
        return this.zzd;
    }

    public final zzvs zzd() {
        return this.zze;
    }

    @Nullable
    public final P zze() {
        return this.zza;
    }

    @Nullable
    public final P zzf() {
        return this.zzb;
    }

    public final String zzg() {
        return this.zzg;
    }

    zzcl(@Nullable P p, @Nullable P p2, byte[] bArr, zzuz zzuzVar, zzvs zzvsVar, int i, String str, zzbt zzbtVar) {
        this.zza = p;
        this.zzb = p2;
        this.zzc = Arrays.copyOf(bArr, bArr.length);
        this.zzd = zzuzVar;
        this.zze = zzvsVar;
        this.zzf = i;
        this.zzg = str;
        this.zzh = zzbtVar;
    }

    @Nullable
    public final byte[] zzh() {
        if (this.zzc == null) {
            return null;
        }
        return Arrays.copyOf(this.zzc, this.zzc.length);
    }
}
