package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzvg;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcj<P> {
    private final Class<P> zza;
    private ConcurrentMap<zzck, List<zzcl<P>>> zzb;
    private final List<zzcl<P>> zzc;
    private zzcl<P> zzd;
    private zzrn zze;

    public final zzcj<P> zza(@Nullable P p, @Nullable P p2, zzvg.zzb zzbVar) throws GeneralSecurityException {
        return zza(p, p2, zzbVar, false);
    }

    public final zzcj<P> zzb(@Nullable P p, @Nullable P p2, zzvg.zzb zzbVar) throws GeneralSecurityException {
        return zza(p, p2, zzbVar, true);
    }

    private final zzcj<P> zza(@Nullable P p, @Nullable P p2, zzvg.zzb zzbVar, boolean z) throws GeneralSecurityException {
        byte[] bArrArray;
        if (this.zzb == null) {
            throw new IllegalStateException("addPrimitive cannot be called after build");
        }
        if (p == null && p2 == null) {
            throw new GeneralSecurityException("at least one of the `fullPrimitive` or `primitive` must be set");
        }
        if (zzbVar.zzc() != zzuz.ENABLED) {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
        Integer numValueOf = Integer.valueOf(zzbVar.zza());
        if (zzbVar.zzf() == zzvs.RAW) {
            numValueOf = null;
        }
        zzbt zzbtVarZza = zznu.zza().zza(zzos.zza(zzbVar.zzb().zzf(), zzbVar.zzb().zze(), zzbVar.zzb().zzb(), zzbVar.zzf(), numValueOf), zzcs.zza());
        switch (zzbm.zza[zzbVar.zzf().ordinal()]) {
            case 1:
            case 2:
                bArrArray = ByteBuffer.allocate(5).put((byte) 0).putInt(zzbVar.zza()).array();
                break;
            case 3:
                bArrArray = ByteBuffer.allocate(5).put((byte) 1).putInt(zzbVar.zza()).array();
                break;
            case 4:
                bArrArray = zzbn.zza;
                break;
            default:
                throw new GeneralSecurityException("unknown output prefix type");
        }
        zzcl<P> zzclVar = new zzcl<>(p, p2, bArrArray, zzbVar.zzc(), zzbVar.zzf(), zzbVar.zza(), zzbVar.zzb().zzf(), zzbtVarZza);
        ConcurrentMap<zzck, List<zzcl<P>>> concurrentMap = this.zzb;
        List<zzcl<P>> list = this.zzc;
        ArrayList arrayList = new ArrayList();
        arrayList.add(zzclVar);
        zzck zzckVar = new zzck(zzclVar.zzh());
        List<zzcl<P>> listPut = concurrentMap.put(zzckVar, Collections.unmodifiableList(arrayList));
        if (listPut != null) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(listPut);
            arrayList2.add(zzclVar);
            concurrentMap.put(zzckVar, Collections.unmodifiableList(arrayList2));
        }
        list.add(zzclVar);
        if (z) {
            if (this.zzd != null) {
                throw new IllegalStateException("you cannot set two primary primitives");
            }
            this.zzd = zzclVar;
        }
        return this;
    }

    public final zzcj<P> zza(zzrn zzrnVar) {
        if (this.zzb == null) {
            throw new IllegalStateException("setAnnotations cannot be called after build");
        }
        this.zze = zzrnVar;
        return this;
    }

    public final zzcg<P> zza() throws GeneralSecurityException {
        if (this.zzb == null) {
            throw new IllegalStateException("build cannot be called twice");
        }
        zzcg<P> zzcgVar = new zzcg<>(this.zzb, this.zzc, this.zzd, this.zze, this.zza);
        this.zzb = null;
        return zzcgVar;
    }

    private zzcj(Class<P> cls) {
        this.zzb = new ConcurrentHashMap();
        this.zzc = new ArrayList();
        this.zza = cls;
        this.zze = zzrn.zza;
    }
}
