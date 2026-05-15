package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzik;
import com.google.android.gms.internal.p001firebaseauthapi.zzir;
import com.google.android.gms.internal.p001firebaseauthapi.zzvb;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zziw {
    private static final zzxt zza = zzpf.zzb("type.googleapis.com/google.crypto.tink.AesSivKey");
    private static final zznz<zzir, zzor> zzb = zznz.zza(new zzob() { // from class: com.google.android.gms.internal.firebase-auth-api.zziv
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzob
        public final zzov zza(zzch zzchVar) {
            return zziw.zza((zzir) zzchVar);
        }
    }, zzir.class, zzor.class);
    private static final zznv<zzor> zzc = zznv.zza(new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zziy
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zznx
        public final zzch zza(zzov zzovVar) {
            return zziw.zzb((zzor) zzovVar);
        }
    }, zza, zzor.class);
    private static final zzmx<zzik, zzos> zzd = zzmx.zza(new Object() { // from class: com.google.android.gms.internal.firebase-auth-api.zzix
    }, zzik.class, zzos.class);
    private static final zzmu<zzos> zze = zzmu.zza(new zzmv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzja
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmv
        public final zzbt zza(zzov zzovVar, zzcs zzcsVar) {
            return zziw.zzb((zzos) zzovVar, zzcsVar);
        }
    }, zza, zzos.class);
    private static final Map<zzir.zzb, zzvs> zzf;
    private static final Map<zzvs, zzir.zzb> zzg;

    /* JADX INFO: Access modifiers changed from: private */
    public static zzik zzb(zzos zzosVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        if (!zzosVar.zzf().equals("type.googleapis.com/google.crypto.tink.AesSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesSivParameters.parseParameters");
        }
        try {
            zztc zztcVarZza = zztc.zza(zzosVar.zzd(), zzaio.zza());
            if (zztcVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return new zzik.zza().zza(zzir.zzc().zza(zztcVarZza.zzd().zzb()).zza(zza(zzosVar.zzc())).zza()).zza(zzxu.zza(zztcVarZza.zzd().zzg(), zzcs.zza(zzcsVar))).zza(zzosVar.zze()).zza();
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing AesSivKey failed");
        }
    }

    private static zzir.zzb zza(zzvs zzvsVar) throws GeneralSecurityException {
        if (zzg.containsKey(zzvsVar)) {
            return zzg.get(zzvsVar);
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzvsVar.zza());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzir zzb(zzor zzorVar) throws GeneralSecurityException {
        if (!zzorVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesSivParameters.parseParameters: " + zzorVar.zza().zzf());
        }
        try {
            zztd zztdVarZza = zztd.zza(zzorVar.zza().zze(), zzaio.zza());
            if (zztdVarZza.zzb() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return zzir.zzc().zza(zztdVarZza.zza()).zza(zza(zzorVar.zza().zzd())).zza();
        } catch (zzaji e) {
            throw new GeneralSecurityException("Parsing AesSivParameters failed: ", e);
        }
    }

    public static /* synthetic */ zzor zza(zzir zzirVar) throws GeneralSecurityException {
        zzvb.zza zzaVarZza = zzvb.zza().zza("type.googleapis.com/google.crypto.tink.AesSivKey").zza(((zztd) ((zzajc) zztd.zzc().zza(zzirVar.zzb()).zzf())).zzi());
        zzir.zzb zzbVarZzd = zzirVar.zzd();
        if (!zzf.containsKey(zzbVarZzd)) {
            throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzbVarZzd));
        }
        return zzor.zzb((zzvb) ((zzajc) zzaVarZza.zza(zzf.get(zzbVarZzd)).zzf()));
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.google.android.gms.internal.firebase-auth-api.zzix, com.google.android.gms.internal.firebase-auth-api.zzmz] */
    static {
        HashMap map = new HashMap();
        map.put(zzir.zzb.zzc, zzvs.RAW);
        map.put(zzir.zzb.zza, zzvs.TINK);
        map.put(zzir.zzb.zzb, zzvs.CRUNCHY);
        zzf = Collections.unmodifiableMap(map);
        EnumMap enumMap = new EnumMap(zzvs.class);
        enumMap.put(zzvs.RAW, zzir.zzb.zzc);
        enumMap.put(zzvs.TINK, zzir.zzb.zza);
        enumMap.put(zzvs.CRUNCHY, zzir.zzb.zzb);
        enumMap.put(zzvs.LEGACY, zzir.zzb.zzb);
        zzg = Collections.unmodifiableMap(enumMap);
    }

    public static void zza() throws GeneralSecurityException {
        zznu zznuVarZza = zznu.zza();
        zznuVarZza.zza(zzb);
        zznuVarZza.zza(zzc);
        zznuVarZza.zza(zzd);
        zznuVarZza.zza(zze);
    }
}
