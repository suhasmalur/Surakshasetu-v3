package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzakn;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzoq<PrimitiveT, KeyProtoT extends zzakn, PublicKeyProtoT extends zzakn> extends zzmo<PrimitiveT, KeyProtoT> implements zzco<PrimitiveT> {
    private final zzop<KeyProtoT, PublicKeyProtoT> zza;
    private final zznb<PublicKeyProtoT> zzb;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzco
    public final zzuy zzc(zzahp zzahpVar) throws GeneralSecurityException {
        try {
            KeyProtoT keyprototZza = this.zza.zza(zzahpVar);
            this.zza.zza(keyprototZza);
            zzakn zzaknVarZzb = this.zza.zzb(keyprototZza);
            this.zzb.zza(zzaknVarZzb);
            return (zzuy) ((zzajc) zzuy.zza().zza(this.zzb.zze()).zza(zzaknVarZzb.zzi()).zza(this.zzb.zzd()).zzf());
        } catch (zzaji e) {
            throw new GeneralSecurityException("expected serialized proto of type ", e);
        }
    }

    public zzoq(zzop<KeyProtoT, PublicKeyProtoT> zzopVar, zznb<PublicKeyProtoT> zznbVar, Class<PrimitiveT> cls) {
        super(zzopVar, cls);
        this.zza = zzopVar;
        this.zzb = zznbVar;
    }
}
