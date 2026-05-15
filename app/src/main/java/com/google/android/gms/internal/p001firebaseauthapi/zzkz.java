package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzkz implements zzwi {
    private final String zza;
    private final int zzb;
    private zzsu zzc;
    private zzse zzd;
    private int zze;
    private zztc zzf;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzwi
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzwi
    public final zzly zza(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != this.zzb) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        }
        if (this.zza.equals(zzcw.zzb)) {
            return new zzly((zzbg) zzct.zza(this.zza, ((zzsu) ((zzajc) zzsu.zzb().zza(this.zzc).zza(zzahp.zza(bArr, 0, this.zzb)).zzf())).zzi(), zzbg.class));
        }
        if (!this.zza.equals(zzcw.zza)) {
            if (!this.zza.equals(zziz.zza)) {
                throw new GeneralSecurityException("unknown DEM key type");
            }
            return new zzly((zzbp) zzct.zza(this.zza, ((zztc) ((zzajc) zztc.zzb().zza(this.zzf).zza(zzahp.zza(bArr, 0, this.zzb)).zzf())).zzi(), zzbp.class));
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, this.zze);
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, this.zze, this.zzb);
        zzsi zzsiVar = (zzsi) ((zzajc) zzsi.zzb().zza(this.zzd.zzd()).zza(zzahp.zza(bArrCopyOfRange)).zzf());
        return new zzly((zzbg) zzct.zza(this.zza, ((zzse) ((zzajc) zzse.zzb().zza(this.zzd.zza()).zza(zzsiVar).zza((zzuc) ((zzajc) zzuc.zzb().zza(this.zzd.zze()).zza(zzahp.zza(bArrCopyOfRange2)).zzf())).zzf())).zzi(), zzbg.class));
    }

    zzkz(zzvb zzvbVar) throws GeneralSecurityException {
        this.zza = zzvbVar.zzf();
        if (this.zza.equals(zzcw.zzb)) {
            try {
                zzsv zzsvVarZza = zzsv.zza(zzvbVar.zze(), zzaio.zza());
                this.zzc = zzsu.zza(zzct.zza(zzvbVar).zze(), zzaio.zza());
                this.zzb = zzsvVarZza.zza();
                return;
            } catch (zzaji e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        }
        if (this.zza.equals(zzcw.zza)) {
            try {
                zzsf zzsfVarZza = zzsf.zza(zzvbVar.zze(), zzaio.zza());
                this.zzd = zzse.zza(zzct.zza(zzvbVar).zze(), zzaio.zza());
                this.zze = zzsfVarZza.zzc().zza();
                this.zzb = this.zze + zzsfVarZza.zzd().zza();
                return;
            } catch (zzaji e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e2);
            }
        }
        if (this.zza.equals(zziz.zza)) {
            try {
                zztd zztdVarZza = zztd.zza(zzvbVar.zze(), zzaio.zza());
                this.zzf = zztc.zza(zzct.zza(zzvbVar).zze(), zzaio.zza());
                this.zzb = zztdVarZza.zza();
                return;
            } catch (zzaji e3) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e3);
            }
        }
        throw new GeneralSecurityException("unsupported AEAD DEM key type: " + this.zza);
    }
}
