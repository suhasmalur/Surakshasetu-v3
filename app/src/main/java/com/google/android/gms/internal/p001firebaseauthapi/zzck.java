package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzck implements Comparable<zzck> {
    private final byte[] zza;

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzck zzckVar) {
        zzck zzckVar2 = zzckVar;
        if (this.zza.length != zzckVar2.zza.length) {
            return this.zza.length - zzckVar2.zza.length;
        }
        for (int i = 0; i < this.zza.length; i++) {
            if (this.zza[i] != zzckVar2.zza[i]) {
                return this.zza[i] - zzckVar2.zza[i];
            }
        }
        return 0;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        return zzxj.zza(this.zza);
    }

    private zzck(byte[] bArr) {
        this.zza = Arrays.copyOf(bArr, bArr.length);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzck)) {
            return false;
        }
        return Arrays.equals(this.zza, ((zzck) obj).zza);
    }
}
