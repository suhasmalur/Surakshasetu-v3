package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzxt {
    private final byte[] zza;

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final int zza() {
        return this.zza.length;
    }

    public static zzxt zza(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("data must be non-null");
        }
        int length = bArr.length;
        if (bArr == null) {
            throw new NullPointerException("data must be non-null");
        }
        if (length > bArr.length) {
            length = bArr.length;
        }
        return new zzxt(bArr, 0, length);
    }

    public final String toString() {
        return "Bytes(" + zzxj.zza(this.zza) + ")";
    }

    private zzxt(byte[] bArr, int i, int i2) {
        this.zza = new byte[i2];
        System.arraycopy(bArr, 0, this.zza, 0, i2);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzxt)) {
            return false;
        }
        return Arrays.equals(((zzxt) obj).zza, this.zza);
    }

    public final byte[] zzb() {
        byte[] bArr = new byte[this.zza.length];
        System.arraycopy(this.zza, 0, bArr, 0, this.zza.length);
        return bArr;
    }
}
