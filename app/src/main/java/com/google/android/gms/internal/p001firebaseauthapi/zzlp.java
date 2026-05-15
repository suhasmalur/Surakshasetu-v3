package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzlp extends zzne<zzuq, zzuu> {
    private final /* synthetic */ zzln zza;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzakn zzaknVar) throws GeneralSecurityException {
        byte[] bArrZza;
        byte[] bArrZza2;
        zzuq zzuqVar = (zzuq) zzaknVar;
        zzuo zzuoVarZzc = zzuqVar.zzc().zzc();
        switch (zzuoVarZzc) {
            case DHKEM_X25519_HKDF_SHA256:
                bArrZza = zzou.zza(32);
                bArrZza[0] = (byte) (bArrZza[0] | 7);
                bArrZza[31] = (byte) (bArrZza[31] & 63);
                bArrZza[31] = (byte) (bArrZza[31] | 128);
                bArrZza2 = zzxr.zza(bArrZza);
                break;
            case DHKEM_P256_HKDF_SHA256:
            case DHKEM_P384_HKDF_SHA384:
            case DHKEM_P521_HKDF_SHA512:
                zzwo zzwoVarZzc = zzlt.zzc(zzuqVar.zzc().zzc());
                ECParameterSpec eCParameterSpecZza = zzwp.zza(zzwoVarZzc);
                KeyPairGenerator keyPairGeneratorZza = zzwt.zzd.zza("EC");
                keyPairGeneratorZza.initialize(eCParameterSpecZza);
                KeyPair keyPairGenerateKeyPair = keyPairGeneratorZza.generateKeyPair();
                zzwr zzwrVar = zzwr.UNCOMPRESSED;
                ECPoint w = ((ECPublicKey) keyPairGenerateKeyPair.getPublic()).getW();
                EllipticCurve curve = zzwp.zza(zzwoVarZzc).getCurve();
                zzmg.zza(w, curve);
                int iZza = zzwp.zza(curve);
                switch (zzwrVar) {
                    case UNCOMPRESSED:
                        int i = (iZza * 2) + 1;
                        bArrZza2 = new byte[i];
                        byte[] bArrZza3 = zzme.zza(w.getAffineX());
                        byte[] bArrZza4 = zzme.zza(w.getAffineY());
                        System.arraycopy(bArrZza4, 0, bArrZza2, i - bArrZza4.length, bArrZza4.length);
                        System.arraycopy(bArrZza3, 0, bArrZza2, (iZza + 1) - bArrZza3.length, bArrZza3.length);
                        bArrZza2[0] = 4;
                        break;
                    case COMPRESSED:
                        int i2 = iZza + 1;
                        bArrZza2 = new byte[i2];
                        byte[] bArrZza5 = zzme.zza(w.getAffineX());
                        System.arraycopy(bArrZza5, 0, bArrZza2, i2 - bArrZza5.length, bArrZza5.length);
                        bArrZza2[0] = (byte) (w.getAffineY().testBit(0) ? 3 : 2);
                        break;
                    case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                        int i3 = iZza * 2;
                        bArrZza2 = new byte[i3];
                        byte[] bArrZza6 = zzme.zza(w.getAffineX());
                        if (bArrZza6.length > iZza) {
                            bArrZza6 = Arrays.copyOfRange(bArrZza6, bArrZza6.length - iZza, bArrZza6.length);
                        }
                        byte[] bArrZza7 = zzme.zza(w.getAffineY());
                        if (bArrZza7.length > iZza) {
                            bArrZza7 = Arrays.copyOfRange(bArrZza7, bArrZza7.length - iZza, bArrZza7.length);
                        }
                        System.arraycopy(bArrZza7, 0, bArrZza2, i3 - bArrZza7.length, bArrZza7.length);
                        System.arraycopy(bArrZza6, 0, bArrZza2, iZza - bArrZza6.length, bArrZza6.length);
                        break;
                    default:
                        throw new GeneralSecurityException("invalid format:" + String.valueOf(zzwrVar));
                }
                bArrZza = zzme.zza(((ECPrivateKey) keyPairGenerateKeyPair.getPrivate()).getS(), zzlt.zza(zzuoVarZzc));
                break;
            default:
                throw new GeneralSecurityException("Invalid KEM");
        }
        return (zzuu) ((zzajc) zzuu.zzb().zza(0).zza((zzuv) ((zzajc) zzuv.zzc().zza(0).zza(zzuqVar.zzc()).zza(zzahp.zza(bArrZza2)).zzf())).zza(zzahp.zza(bArrZza)).zzf());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ zzakn zza(zzahp zzahpVar) throws zzaji {
        return zzuq.zza(zzahpVar, zzaio.zza());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzlp(zzln zzlnVar, Class cls) {
        super(cls);
        this.zza = zzlnVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzne
    public final /* synthetic */ void zzb(zzakn zzaknVar) throws GeneralSecurityException {
        zzlt.zza(((zzuq) zzaknVar).zzc());
    }
}
