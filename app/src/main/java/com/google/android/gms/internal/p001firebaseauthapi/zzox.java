package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzox {
    private final Map<zzpc, zzmx<?, ?>> zza;
    private final Map<zzpa, zzmu<?>> zzb;
    private final Map<zzpc, zznz<?, ?>> zzc;
    private final Map<zzpa, zznv<?>> zzd;

    public final <SerializationT extends zzov> zzox zza(zzmu<SerializationT> zzmuVar) throws GeneralSecurityException {
        zzpa zzpaVar = new zzpa(zzmuVar.zzb(), zzmuVar.zza());
        if (this.zzb.containsKey(zzpaVar)) {
            zzmu<?> zzmuVar2 = this.zzb.get(zzpaVar);
            if (!zzmuVar2.equals(zzmuVar) || !zzmuVar.equals(zzmuVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + String.valueOf(zzpaVar));
            }
        } else {
            this.zzb.put(zzpaVar, zzmuVar);
        }
        return this;
    }

    public final <KeyT extends zzbt, SerializationT extends zzov> zzox zza(zzmx<KeyT, SerializationT> zzmxVar) throws GeneralSecurityException {
        zzpc zzpcVar = new zzpc(zzmxVar.zza(), zzmxVar.zzb());
        if (this.zza.containsKey(zzpcVar)) {
            zzmx<?, ?> zzmxVar2 = this.zza.get(zzpcVar);
            if (!zzmxVar2.equals(zzmxVar) || !zzmxVar.equals(zzmxVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + String.valueOf(zzpcVar));
            }
        } else {
            this.zza.put(zzpcVar, zzmxVar);
        }
        return this;
    }

    public final <SerializationT extends zzov> zzox zza(zznv<SerializationT> zznvVar) throws GeneralSecurityException {
        zzpa zzpaVar = new zzpa(zznvVar.zzb(), zznvVar.zza());
        if (this.zzd.containsKey(zzpaVar)) {
            zznv<?> zznvVar2 = this.zzd.get(zzpaVar);
            if (!zznvVar2.equals(zznvVar) || !zznvVar.equals(zznvVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + String.valueOf(zzpaVar));
            }
        } else {
            this.zzd.put(zzpaVar, zznvVar);
        }
        return this;
    }

    public final <ParametersT extends zzch, SerializationT extends zzov> zzox zza(zznz<ParametersT, SerializationT> zznzVar) throws GeneralSecurityException {
        zzpc zzpcVar = new zzpc(zznzVar.zza(), zznzVar.zzb());
        if (this.zzc.containsKey(zzpcVar)) {
            zznz<?, ?> zznzVar2 = this.zzc.get(zzpcVar);
            if (!zznzVar2.equals(zznzVar) || !zznzVar.equals(zznzVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + String.valueOf(zzpcVar));
            }
        } else {
            this.zzc.put(zzpcVar, zznzVar);
        }
        return this;
    }

    final zzoy zza() {
        return new zzoy(this);
    }

    public zzox() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new HashMap();
        this.zzd = new HashMap();
    }

    public zzox(zzoy zzoyVar) {
        this.zza = new HashMap(zzoyVar.zza);
        this.zzb = new HashMap(zzoyVar.zzb);
        this.zzc = new HashMap(zzoyVar.zzc);
        this.zzd = new HashMap(zzoyVar.zzd);
    }
}
