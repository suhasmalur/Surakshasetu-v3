package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzka extends zzks {
    private final zzd zza;
    private final zze zzb;
    private final zza zzc;
    private final zzf zzd;

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    private static class zzb {
        private final String zza;
        private final int zzb;

        public String toString() {
            return String.format("%s(0x%04x)", this.zza, Integer.valueOf(this.zzb));
        }

        private zzb(String str, int i) {
            this.zza = str;
            this.zzb = i;
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzka.class, this.zza, this.zzb, this.zzc, this.zzd});
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zzc {
        private zzd zza;
        private zze zzb;
        private zza zzc;
        private zzf zzd;

        public final zzc zza(zza zzaVar) {
            this.zzc = zzaVar;
            return this;
        }

        public final zzc zza(zze zzeVar) {
            this.zzb = zzeVar;
            return this;
        }

        public final zzc zza(zzd zzdVar) {
            this.zza = zzdVar;
            return this;
        }

        public final zzc zza(zzf zzfVar) {
            this.zzd = zzfVar;
            return this;
        }

        public final zzka zza() throws GeneralSecurityException {
            if (this.zza == null) {
                throw new GeneralSecurityException("HPKE KEM parameter is not set");
            }
            if (this.zzb == null) {
                throw new GeneralSecurityException("HPKE KDF parameter is not set");
            }
            if (this.zzc == null) {
                throw new GeneralSecurityException("HPKE AEAD parameter is not set");
            }
            if (this.zzd == null) {
                throw new GeneralSecurityException("HPKE variant is not set");
            }
            return new zzka(this.zza, this.zzb, this.zzc, this.zzd);
        }

        private zzc() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            this.zzd = zzf.zzc;
        }
    }

    public final zza zzb() {
        return this.zzc;
    }

    public static zzc zzc() {
        return new zzc();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zza extends zzb {
        public static final zza zza = new zza("AES_128_GCM", 1);
        public static final zza zzb = new zza("AES_256_GCM", 2);
        public static final zza zzc = new zza("CHACHA20_POLY1305", 3);

        @Override // com.google.android.gms.internal.firebase-auth-api.zzka.zzb
        public final /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        private zza(String str, int i) {
            super(str, i);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zze extends zzb {
        public static final zze zza = new zze("HKDF_SHA256", 1);
        public static final zze zzb = new zze("HKDF_SHA384", 2);
        public static final zze zzc = new zze("HKDF_SHA512", 3);

        @Override // com.google.android.gms.internal.firebase-auth-api.zzka.zzb
        public final /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        private zze(String str, int i) {
            super(str, i);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zzf {
        public static final zzf zza = new zzf("TINK");
        public static final zzf zzb = new zzf("CRUNCHY");
        public static final zzf zzc = new zzf("NO_PREFIX");
        private final String zzd;

        public final String toString() {
            return this.zzd;
        }

        private zzf(String str) {
            this.zzd = str;
        }
    }

    public final zze zzd() {
        return this.zzb;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static final class zzd extends zzb {
        public static final zzd zza = new zzd("DHKEM_P256_HKDF_SHA256", 16);
        public static final zzd zzb = new zzd("DHKEM_P384_HKDF_SHA384", 17);
        public static final zzd zzc = new zzd("DHKEM_P521_HKDF_SHA512", 18);
        public static final zzd zzd = new zzd("DHKEM_X25519_HKDF_SHA256", 32);

        @Override // com.google.android.gms.internal.firebase-auth-api.zzka.zzb
        public final /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        private zzd(String str, int i) {
            super(str, i);
        }
    }

    public final zzd zze() {
        return this.zza;
    }

    public final zzf zzf() {
        return this.zzd;
    }

    private zzka(zzd zzdVar, zze zzeVar, zza zzaVar, zzf zzfVar) {
        this.zza = zzdVar;
        this.zzb = zzeVar;
        this.zzc = zzaVar;
        this.zzd = zzfVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzka)) {
            return false;
        }
        zzka zzkaVar = (zzka) obj;
        return this.zza == zzkaVar.zza && this.zzb == zzkaVar.zzb && this.zzc == zzkaVar.zzc && this.zzd == zzkaVar.zzd;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzch
    public final boolean zza() {
        return this.zzd != zzf.zzc;
    }
}
