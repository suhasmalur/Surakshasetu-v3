package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzakn;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
class zzmo<PrimitiveT, KeyProtoT extends zzakn> implements zzbs<PrimitiveT> {
    private final zznb<KeyProtoT> zza;
    private final Class<PrimitiveT> zzb;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbs
    public final zzuy zza(zzahp zzahpVar) throws GeneralSecurityException {
        try {
            return (zzuy) ((zzajc) zzuy.zza().zza(this.zza.zze()).zza(new zzmr(this.zza.zzc()).zza(zzahpVar).zzi()).zza(this.zza.zzd()).zzf());
        } catch (zzaji e) {
            throw new GeneralSecurityException("Unexpected proto", e);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbs
    public final PrimitiveT zzb(zzahp zzahpVar) throws GeneralSecurityException {
        try {
            zzakn zzaknVarZza = this.zza.zza(zzahpVar);
            if (Void.class.equals(this.zzb)) {
                throw new GeneralSecurityException("Cannot create a primitive for Void");
            }
            this.zza.zza(zzaknVarZza);
            return (PrimitiveT) this.zza.zza(zzaknVarZza, this.zzb);
        } catch (zzaji e) {
            throw new GeneralSecurityException("Failures parsing proto of type " + this.zza.zzg().getName(), e);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbs
    public final String zza() {
        return this.zza.zze();
    }

    public zzmo(zznb<KeyProtoT> zznbVar, Class<PrimitiveT> cls) {
        if (!zznbVar.zzh().contains(cls) && !Void.class.equals(cls)) {
            throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", zznbVar.toString(), cls.getName()));
        }
        this.zza = zznbVar;
        this.zzb = cls;
    }
}
