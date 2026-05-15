package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zznf extends zzch {
    private final zzor zza;

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza.zza(), this.zza.zzb()});
    }

    public final zzor zzb() {
        return this.zza;
    }

    public final String toString() {
        String str;
        String strZzf = this.zza.zza().zzf();
        switch (this.zza.zza().zzd()) {
            case TINK:
                str = "TINK";
                break;
            case LEGACY:
                str = "LEGACY";
                break;
            case RAW:
                str = "RAW";
                break;
            case CRUNCHY:
                str = "CRUNCHY";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        return String.format("(typeUrl=%s, outputPrefixType=%s)", strZzf, str);
    }

    public zznf(zzor zzorVar) {
        this.zza = zzorVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zznf)) {
            return false;
        }
        zzor zzorVar = ((zznf) obj).zza;
        return this.zza.zza().zzd().equals(zzorVar.zza().zzd()) && this.zza.zza().zzf().equals(zzorVar.zza().zzf()) && this.zza.zza().zze().equals(zzorVar.zza().zze());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzch
    public final boolean zza() {
        return this.zza.zza().zzd() != zzvs.RAW;
    }
}
