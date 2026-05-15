package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzmq {
    private static final Logger zza = Logger.getLogger(zzmq.class.getName());
    private final ConcurrentMap<String, zza> zzb;
    private final ConcurrentMap<String, Boolean> zzc;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    interface zza {
        zzbs<?> zza();

        <P> zzbs<P> zza(Class<P> cls) throws GeneralSecurityException;

        Class<?> zzb();

        Set<Class<?>> zzc();
    }

    public final <P> zzbs<P> zza(String str, Class<P> cls) throws GeneralSecurityException {
        zza zzaVarZzc = zzc(str);
        if (zzaVarZzc.zzc().contains(cls)) {
            return zzaVarZzc.zza(cls);
        }
        String name = cls.getName();
        String strValueOf = String.valueOf(zzaVarZzc.zzb());
        Set<Class<?>> setZzc = zzaVarZzc.zzc();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class<?> cls2 : setZzc) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(cls2.getCanonicalName());
            z = false;
        }
        throw new GeneralSecurityException("Primitive type " + name + " not supported by key manager of type " + strValueOf + ", supported primitives: " + sb.toString());
    }

    public final zzbs<?> zza(String str) throws GeneralSecurityException {
        return zzc(str).zza();
    }

    private static <KeyProtoT extends zzakn> zza zza(zznb<KeyProtoT> zznbVar) {
        return new zzmt(zznbVar);
    }

    private final synchronized zza zzc(String str) throws GeneralSecurityException {
        if (!this.zzb.containsKey(str)) {
            throw new GeneralSecurityException("No key manager found for key type " + str);
        }
        return this.zzb.get(str);
    }

    public zzmq() {
        this.zzb = new ConcurrentHashMap();
        this.zzc = new ConcurrentHashMap();
    }

    public zzmq(zzmq zzmqVar) {
        this.zzb = new ConcurrentHashMap(zzmqVar.zzb);
        this.zzc = new ConcurrentHashMap(zzmqVar.zzc);
    }

    public final synchronized <KeyProtoT extends zzakn, PublicKeyProtoT extends zzakn> void zza(zzop<KeyProtoT, PublicKeyProtoT> zzopVar, zznb<PublicKeyProtoT> zznbVar, boolean z) throws GeneralSecurityException {
        zzif.zza zzaVarZzb = zzopVar.zzb();
        zzif.zza zzaVarZzb2 = zznbVar.zzb();
        if (!zzaVarZzb.zza()) {
            throw new GeneralSecurityException("failed to register key manager " + String.valueOf(zzopVar.getClass()) + " as it is not FIPS compatible.");
        }
        if (!zzaVarZzb2.zza()) {
            throw new GeneralSecurityException("failed to register key manager " + String.valueOf(zznbVar.getClass()) + " as it is not FIPS compatible.");
        }
        zza((zza) new zzms(zzopVar, zznbVar), true, true);
        zza(zza(zznbVar), false, false);
    }

    public final synchronized <KeyProtoT extends zzakn> void zza(zznb<KeyProtoT> zznbVar, boolean z) throws GeneralSecurityException {
        if (!zznbVar.zzb().zza()) {
            throw new GeneralSecurityException("failed to register key manager " + String.valueOf(zznbVar.getClass()) + " as it is not FIPS compatible.");
        }
        zza(zza(zznbVar), false, true);
    }

    private final synchronized void zza(zza zzaVar, boolean z, boolean z2) throws GeneralSecurityException {
        String strZza = zzaVar.zza().zza();
        if (z2 && this.zzc.containsKey(strZza) && !this.zzc.get(strZza).booleanValue()) {
            throw new GeneralSecurityException("New keys are already disallowed for key type " + strZza);
        }
        zza zzaVar2 = this.zzb.get(strZza);
        if (zzaVar2 != null && !zzaVar2.zzb().equals(zzaVar.zzb())) {
            zza.logp(Level.WARNING, "com.google.crypto.tink.internal.KeyManagerRegistry", "registerKeyManagerContainer", "Attempted overwrite of a registered key manager for key type " + strZza);
            throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", strZza, zzaVar2.zzb().getName(), zzaVar.zzb().getName()));
        }
        if (!z) {
            this.zzb.putIfAbsent(strZza, zzaVar);
        } else {
            this.zzb.put(strZza, zzaVar);
        }
        this.zzc.put(strZza, Boolean.valueOf(z2));
    }

    public final boolean zzb(String str) {
        return this.zzc.get(str).booleanValue();
    }
}
