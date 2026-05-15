package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzdl;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzde extends zzcz {
    private final zzdl zza;
    private final zzxu zzb;
    private final zzxu zzc;
    private final zzxt zzd;

    @Nullable
    private final Integer zze;

    private zzde(zzdl zzdlVar, zzxu zzxuVar, zzxu zzxuVar2, zzxt zzxtVar, @Nullable Integer num) {
        this.zza = zzdlVar;
        this.zzb = zzxuVar;
        this.zzc = zzxuVar2;
        this.zzd = zzxtVar;
        this.zze = num;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static class zza {

        @Nullable
        private zzdl zza;

        @Nullable
        private zzxu zzb;

        @Nullable
        private zzxu zzc;

        @Nullable
        private Integer zzd;

        public final zza zza(zzxu zzxuVar) {
            this.zzb = zzxuVar;
            return this;
        }

        public final zza zzb(zzxu zzxuVar) {
            this.zzc = zzxuVar;
            return this;
        }

        public final zza zza(@Nullable Integer num) {
            this.zzd = num;
            return this;
        }

        public final zza zza(zzdl zzdlVar) {
            this.zza = zzdlVar;
            return this;
        }

        public final zzde zza() throws GeneralSecurityException {
            zzxt zzxtVarZza;
            if (this.zza == null) {
                throw new GeneralSecurityException("Cannot build without parameters");
            }
            if (this.zzb == null || this.zzc == null) {
                throw new GeneralSecurityException("Cannot build without key material");
            }
            if (this.zza.zzb() != this.zzb.zza()) {
                throw new GeneralSecurityException("AES key size mismatch");
            }
            if (this.zza.zzc() != this.zzc.zza()) {
                throw new GeneralSecurityException("HMAC key size mismatch");
            }
            if (this.zza.zza() && this.zzd == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            }
            if (!this.zza.zza() && this.zzd != null) {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            if (this.zza.zzh() == zzdl.zzc.zzc) {
                zzxtVarZza = zzxt.zza(new byte[0]);
            } else if (this.zza.zzh() == zzdl.zzc.zzb) {
                zzxtVarZza = zzxt.zza(ByteBuffer.allocate(5).put((byte) 0).putInt(this.zzd.intValue()).array());
            } else if (this.zza.zzh() == zzdl.zzc.zza) {
                zzxtVarZza = zzxt.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(this.zzd.intValue()).array());
            } else {
                throw new IllegalStateException("Unknown AesCtrHmacAeadParameters.Variant: " + String.valueOf(this.zza.zzh()));
            }
            return new zzde(this.zza, this.zzb, this.zzc, zzxtVarZza, this.zzd);
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            this.zzd = null;
        }
    }
}
