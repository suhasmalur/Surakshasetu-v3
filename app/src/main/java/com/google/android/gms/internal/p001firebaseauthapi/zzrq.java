package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzrq {
    private final zzrn zza;
    private final List<zzrs> zzb;

    @Nullable
    private final Integer zzc;

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        return String.format("(annotations=%s, entries=%s, primaryKeyId=%s)", this.zza, this.zzb, this.zzc);
    }

    private zzrq(zzrn zzrnVar, List<zzrs> list, Integer num) {
        this.zza = zzrnVar;
        this.zzb = list;
        this.zzc = num;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzrq)) {
            return false;
        }
        zzrq zzrqVar = (zzrq) obj;
        if (this.zza.equals(zzrqVar.zza) && this.zzb.equals(zzrqVar.zzb)) {
            Integer num = this.zzc;
            Integer num2 = zzrqVar.zzc;
            if (num == num2 || (num != null && num.equals(num2))) {
                return true;
            }
        }
        return false;
    }
}
