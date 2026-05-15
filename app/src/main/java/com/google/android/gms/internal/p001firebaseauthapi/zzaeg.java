package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaeg extends zzafz {
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final zzaey zzd;
    private final String zze;

    public final int hashCode() {
        return ((((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ (this.zzb == null ? 0 : this.zzb.hashCode())) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ this.zzd.hashCode()) * 1000003) ^ this.zze.hashCode();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafz
    public final zzaey zzb() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafz
    public final String zzc() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafz
    public final String zzd() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafz
    public final String zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafz
    public final String zzf() {
        return this.zzc;
    }

    public final String toString() {
        return "RevokeTokenRequest{providerId=" + this.zza + ", tenantId=" + this.zzb + ", token=" + this.zzc + ", tokenType=" + String.valueOf(this.zzd) + ", idToken=" + this.zze + "}";
    }

    private zzaeg(String str, String str2, String str3, zzaey zzaeyVar, String str4) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzaeyVar;
        this.zze = str4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzafz)) {
            return false;
        }
        zzafz zzafzVar = (zzafz) obj;
        return this.zza.equals(zzafzVar.zzd()) && (this.zzb != null ? this.zzb.equals(zzafzVar.zze()) : zzafzVar.zze() == null) && this.zzc.equals(zzafzVar.zzf()) && this.zzd.equals(zzafzVar.zzb()) && this.zze.equals(zzafzVar.zzc());
    }
}
