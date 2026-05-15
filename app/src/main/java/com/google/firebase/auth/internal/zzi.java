package com.google.firebase.auth.internal;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
final class zzi extends zze {
    private final String zza;
    private final String zzb;
    private final String zzc;

    public final int hashCode() {
        return (((((this.zza == null ? 0 : this.zza.hashCode()) ^ 1000003) * 1000003) ^ (this.zzb == null ? 0 : this.zzb.hashCode())) * 1000003) ^ (this.zzc != null ? this.zzc.hashCode() : 0);
    }

    @Override // com.google.firebase.auth.internal.zze
    public final String zza() {
        return this.zzb;
    }

    @Override // com.google.firebase.auth.internal.zze
    public final String zzb() {
        return this.zzc;
    }

    @Override // com.google.firebase.auth.internal.zze
    public final String zzc() {
        return this.zza;
    }

    public final String toString() {
        return "AttestationResult{recaptchaV2Token=" + this.zza + ", playIntegrityToken=" + this.zzb + ", recaptchaEnterpriseToken=" + this.zzc + "}";
    }

    private zzi(String str, String str2, String str3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zze)) {
            return false;
        }
        zze zzeVar = (zze) obj;
        if (this.zza != null ? this.zza.equals(zzeVar.zzc()) : zzeVar.zzc() == null) {
            if (this.zzb != null ? this.zzb.equals(zzeVar.zza()) : zzeVar.zza() == null) {
                if (this.zzc != null ? this.zzc.equals(zzeVar.zzb()) : zzeVar.zzb() == null) {
                    return true;
                }
            }
        }
        return false;
    }
}
