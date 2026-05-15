package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzxb;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzwt<T_WRAPPER extends zzxb<JcePrimitiveT>, JcePrimitiveT> {
    private final zzwy<JcePrimitiveT> zzh;
    public static final zzwt<zzxa, Cipher> zza = new zzwt<>(new zzxa());
    public static final zzwt<zzxe, Mac> zzb = new zzwt<>(new zzxe());
    private static final zzwt<zzxg, Signature> zzf = new zzwt<>(new zzxg());
    private static final zzwt<zzxh, MessageDigest> zzg = new zzwt<>(new zzxh());
    public static final zzwt<zzxd, KeyAgreement> zzc = new zzwt<>(new zzxd());
    public static final zzwt<zzxf, KeyPairGenerator> zzd = new zzwt<>(new zzxf());
    public static final zzwt<zzxc, KeyFactory> zze = new zzwt<>(new zzxc());

    public final JcePrimitiveT zza(String str) throws GeneralSecurityException {
        return this.zzh.zza(str);
    }

    public static List<Provider> zza(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Provider provider = Security.getProvider(str);
            if (provider != null) {
                arrayList.add(provider);
            }
        }
        return arrayList;
    }

    private zzwt(T_WRAPPER t_wrapper) {
        if (zzif.zzb()) {
            this.zzh = new zzww(t_wrapper);
        } else if (zzxp.zza()) {
            this.zzh = new zzws(t_wrapper);
        } else {
            this.zzh = new zzwu(t_wrapper);
        }
    }
}
