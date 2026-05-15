package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzgs extends zzdb {
    private final String zza;
    private final zzb zzb;
    private final zzdb zzc;

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzgs.class, this.zza, this.zzb, this.zzc});
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static class zza {

        @Nullable
        private String zza;

        @Nullable
        private zzb zzb;

        @Nullable
        private zzdb zzc;

        public final zza zza(zzdb zzdbVar) {
            this.zzc = zzdbVar;
            return this;
        }

        public final zza zza(zzb zzbVar) {
            this.zzb = zzbVar;
            return this;
        }

        public final zza zza(String str) {
            this.zza = str;
            return this;
        }

        public final zzgs zza() throws GeneralSecurityException {
            if (this.zza == null) {
                throw new GeneralSecurityException("kekUri must be set");
            }
            if (this.zzb == null) {
                throw new GeneralSecurityException("dekParsingStrategy must be set");
            }
            if (this.zzc == null) {
                throw new GeneralSecurityException("dekParametersForNewKeys must be set");
            }
            if (this.zzc.zza()) {
                throw new GeneralSecurityException("dekParametersForNewKeys must note have ID Requirements");
            }
            zzb zzbVar = this.zzb;
            zzdb zzdbVar = this.zzc;
            boolean z = true;
            if ((!zzbVar.equals(zzb.zza) || !(zzdbVar instanceof zzes)) && ((!zzbVar.equals(zzb.zzc) || !(zzdbVar instanceof zzfv)) && ((!zzbVar.equals(zzb.zzb) || !(zzdbVar instanceof zzhm)) && ((!zzbVar.equals(zzb.zzd) || !(zzdbVar instanceof zzdl)) && ((!zzbVar.equals(zzb.zze) || !(zzdbVar instanceof zzed)) && (!zzbVar.equals(zzb.zzf) || !(zzdbVar instanceof zzfj))))))) {
                z = false;
            }
            if (!z) {
                throw new GeneralSecurityException("Cannot use parsing strategy " + this.zzb.toString() + " when new keys are picked according to " + String.valueOf(this.zzc) + ".");
            }
            return new zzgs(this.zza, this.zzb, this.zzc);
        }

        private zza() {
        }
    }

    public final zzdb zzb() {
        return this.zzc;
    }

    public final String zzc() {
        return this.zza;
    }

    public final String toString() {
        return "LegacyKmsEnvelopeAead Parameters (kekUri: " + this.zza + ", dekParsingStrategy: " + String.valueOf(this.zzb) + ", dekParametersForNewKeys: " + String.valueOf(this.zzc) + ")";
    }

    private zzgs(String str, zzb zzbVar, zzdb zzdbVar) {
        this.zza = str;
        this.zzb = zzbVar;
        this.zzc = zzdbVar;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zzb {
        public static final zzb zza = new zzb("ASSUME_AES_GCM");
        public static final zzb zzb = new zzb("ASSUME_XCHACHA20POLY1305");
        public static final zzb zzc = new zzb("ASSUME_CHACHA20POLY1305");
        public static final zzb zzd = new zzb("ASSUME_AES_CTR_HMAC");
        public static final zzb zze = new zzb("ASSUME_AES_EAX");
        public static final zzb zzf = new zzb("ASSUME_AES_GCM_SIV");
        private final String zzg;

        public final String toString() {
            return this.zzg;
        }

        private zzb(String str) {
            this.zzg = str;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzgs)) {
            return false;
        }
        zzgs zzgsVar = (zzgs) obj;
        return zzgsVar.zzb.equals(this.zzb) && zzgsVar.zzc.equals(this.zzc) && zzgsVar.zza.equals(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzch
    public final boolean zza() {
        return false;
    }
}
