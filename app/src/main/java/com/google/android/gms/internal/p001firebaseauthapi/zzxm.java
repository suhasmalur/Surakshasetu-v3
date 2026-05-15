package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzpo;
import com.google.android.gms.internal.p001firebaseauthapi.zzql;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzxm implements zzce {
    private static final byte[] zza = {0};
    private final zzrx zzb;
    private final int zzc;
    private final byte[] zzd;
    private final byte[] zze;

    public static zzce zza(zzph zzphVar) throws GeneralSecurityException {
        return new zzxm(zzphVar);
    }

    public static zzce zza(zzqc zzqcVar) throws GeneralSecurityException {
        return new zzxm(zzqcVar);
    }

    private zzxm(zzph zzphVar) throws GeneralSecurityException {
        this.zzb = new zzxl(zzphVar.zzc().zza(zzbq.zza()));
        this.zzc = ((zzpo) zzphVar.zza()).zzb();
        this.zzd = zzphVar.zzb().zzb();
        if (((zzpo) zzphVar.zza()).zze().equals(zzpo.zzb.zzc)) {
            this.zze = Arrays.copyOf(zza, zza.length);
        } else {
            this.zze = new byte[0];
        }
    }

    private zzxm(zzqc zzqcVar) throws GeneralSecurityException {
        this.zzb = new zzxk("HMAC" + String.valueOf(((zzql) zzqcVar.zza()).zze()), new SecretKeySpec(zzqcVar.zzd().zza(zzbq.zza()), "HMAC"));
        this.zzc = ((zzql) zzqcVar.zza()).zzb();
        this.zzd = zzqcVar.zzb().zzb();
        if (((zzql) zzqcVar.zza()).zzf().equals(zzql.zzb.zzc)) {
            this.zze = Arrays.copyOf(zza, zza.length);
        } else {
            this.zze = new byte[0];
        }
    }

    public zzxm(zzrx zzrxVar, int i) throws GeneralSecurityException {
        this.zzb = zzrxVar;
        this.zzc = i;
        this.zzd = new byte[0];
        this.zze = new byte[0];
        if (i < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        zzrxVar.zza(new byte[0], i);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzce
    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!MessageDigest.isEqual(zza(bArr2), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzce
    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        if (this.zze.length > 0) {
            return zzwg.zza(this.zzd, this.zzb.zza(zzwg.zza(bArr, this.zze), this.zzc));
        }
        return zzwg.zza(this.zzd, this.zzb.zza(bArr, this.zzc));
    }
}
