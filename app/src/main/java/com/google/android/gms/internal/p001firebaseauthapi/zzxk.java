package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzif;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.util.Arrays;
import javax.crypto.Mac;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzxk implements zzrx {
    private static final zzif.zza zza = zzif.zza.zzb;
    private final ThreadLocal<Mac> zzb = new zzxn(this);
    private final String zzc;
    private final Key zzd;
    private final int zze;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public zzxk(java.lang.String r3, java.security.Key r4) throws java.security.GeneralSecurityException {
        /*
            r2 = this;
            r2.<init>()
            com.google.android.gms.internal.firebase-auth-api.zzxn r0 = new com.google.android.gms.internal.firebase-auth-api.zzxn
            r0.<init>(r2)
            r2.zzb = r0
            com.google.android.gms.internal.firebase-auth-api.zzif$zza r0 = com.google.android.gms.internal.p001firebaseauthapi.zzxk.zza
            boolean r0 = r0.zza()
            if (r0 == 0) goto L9a
            r2.zzc = r3
            r2.zzd = r4
            byte[] r4 = r4.getEncoded()
            int r4 = r4.length
            r0 = 16
            if (r4 < r0) goto L92
            int r4 = r3.hashCode()
            switch(r4) {
                case -1823053428: goto L4f;
                case 392315023: goto L45;
                case 392315118: goto L3b;
                case 392316170: goto L31;
                case 392317873: goto L27;
                default: goto L26;
            }
        L26:
            goto L59
        L27:
            java.lang.String r4 = "HMACSHA512"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L26
            r4 = 4
            goto L5a
        L31:
            java.lang.String r4 = "HMACSHA384"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L26
            r4 = 3
            goto L5a
        L3b:
            java.lang.String r4 = "HMACSHA256"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L26
            r4 = 2
            goto L5a
        L45:
            java.lang.String r4 = "HMACSHA224"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L26
            r4 = 1
            goto L5a
        L4f:
            java.lang.String r4 = "HMACSHA1"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L26
            r4 = 0
            goto L5a
        L59:
            r4 = -1
        L5a:
            switch(r4) {
                case 0: goto L87;
                case 1: goto L82;
                case 2: goto L7d;
                case 3: goto L78;
                case 4: goto L73;
                default: goto L5d;
            }
        L5d:
            java.security.NoSuchAlgorithmException r4 = new java.security.NoSuchAlgorithmException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "unknown Hmac algorithm: "
            r0.<init>(r1)
            java.lang.StringBuilder r3 = r0.append(r3)
            java.lang.String r3 = r3.toString()
            r4.<init>(r3)
            throw r4
        L73:
            r3 = 64
            r2.zze = r3
            goto L8c
        L78:
            r3 = 48
            r2.zze = r3
            goto L8c
        L7d:
            r3 = 32
            r2.zze = r3
            goto L8c
        L82:
            r3 = 28
            r2.zze = r3
            goto L8c
        L87:
            r3 = 20
            r2.zze = r3
        L8c:
            java.lang.ThreadLocal<javax.crypto.Mac> r3 = r2.zzb
            r3.get()
            return
        L92:
            java.security.InvalidAlgorithmParameterException r3 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r4 = "key size too small, need at least 16 bytes"
            r3.<init>(r4)
            throw r3
        L9a:
            java.security.GeneralSecurityException r3 = new java.security.GeneralSecurityException
            java.lang.String r4 = "Can not use HMAC in FIPS-mode, as BoringCrypto module is not available."
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p001firebaseauthapi.zzxk.<init>(java.lang.String, java.security.Key):void");
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzrx
    public final byte[] zza(byte[] bArr, int i) throws GeneralSecurityException {
        if (i > this.zze) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        }
        this.zzb.get().update(bArr);
        return Arrays.copyOf(this.zzb.get().doFinal(), i);
    }
}
