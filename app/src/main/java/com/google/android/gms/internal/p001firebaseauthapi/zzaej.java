package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaej extends zzafy {
    private String zza;
    private String zzb;
    private String zzc;
    private zzaey zzd;
    private String zze;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafy
    public final zzafy zza(String str) {
        if (str == null) {
            throw new NullPointerException("Null idToken");
        }
        this.zze = str;
        return this;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafy
    public final zzafy zzb(String str) {
        if (str == null) {
            throw new NullPointerException("Null providerId");
        }
        this.zza = str;
        return this;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafy
    public final zzafy zzc(String str) {
        this.zzb = str;
        return this;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafy
    public final zzafy zzd(String str) {
        if (str == null) {
            throw new NullPointerException("Null token");
        }
        this.zzc = str;
        return this;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafy
    public final zzafy zza(zzaey zzaeyVar) {
        if (zzaeyVar == null) {
            throw new NullPointerException("Null tokenType");
        }
        this.zzd = zzaeyVar;
        return this;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafy
    public final zzafz zza() {
        if (this.zza == null || this.zzc == null || this.zzd == null || this.zze == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" providerId");
            }
            if (this.zzc == null) {
                sb.append(" token");
            }
            if (this.zzd == null) {
                sb.append(" tokenType");
            }
            if (this.zze == null) {
                sb.append(" idToken");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }
        return new zzaeg(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
    }

    zzaej() {
    }
}
