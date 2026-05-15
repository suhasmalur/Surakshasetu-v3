package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaeh extends zzafu {
    private final String zza;
    private final String zzb;

    public final int hashCode() {
        return (((this.zza == null ? 0 : this.zza.hashCode()) ^ 1000003) * 1000003) ^ (this.zzb != null ? this.zzb.hashCode() : 0);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafu
    final String zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafu
    final String zzb() {
        return this.zza;
    }

    public final String toString() {
        return "RecaptchaEnforcementState{provider=" + this.zza + ", enforcementState=" + this.zzb + "}";
    }

    zzaeh(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzafu)) {
            return false;
        }
        zzafu zzafuVar = (zzafu) obj;
        if (this.zza != null ? this.zza.equals(zzafuVar.zzb()) : zzafuVar.zzb() == null) {
            if (this.zzb != null ? this.zzb.equals(zzafuVar.zza()) : zzafuVar.zza() == null) {
                return true;
            }
        }
        return false;
    }
}
