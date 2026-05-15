package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzpo;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzph extends zzqr {
    private final zzpo zza;
    private final zzxu zzb;
    private final zzxt zzc;

    @Nullable
    private final Integer zzd;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzqr
    public final /* synthetic */ zzqq zza() {
        return this.zza;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static class zza {

        @Nullable
        private zzpo zza;

        @Nullable
        private zzxu zzb;

        @Nullable
        private Integer zzc;

        public final zza zza(zzxu zzxuVar) throws GeneralSecurityException {
            this.zzb = zzxuVar;
            return this;
        }

        public final zza zza(@Nullable Integer num) {
            this.zzc = num;
            return this;
        }

        public final zza zza(zzpo zzpoVar) {
            this.zza = zzpoVar;
            return this;
        }

        public final zzph zza() throws GeneralSecurityException {
            zzxt zzxtVarZza;
            if (this.zza == null || this.zzb == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if (this.zza.zzc() != this.zzb.zza()) {
                throw new GeneralSecurityException("Key size mismatch");
            }
            if (this.zza.zza() && this.zzc == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            }
            if (!this.zza.zza() && this.zzc != null) {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            if (this.zza.zze() == zzpo.zzb.zzd) {
                zzxtVarZza = zzxt.zza(new byte[0]);
            } else if (this.zza.zze() == zzpo.zzb.zzc || this.zza.zze() == zzpo.zzb.zzb) {
                zzxtVarZza = zzxt.zza(ByteBuffer.allocate(5).put((byte) 0).putInt(this.zzc.intValue()).array());
            } else if (this.zza.zze() == zzpo.zzb.zza) {
                zzxtVarZza = zzxt.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(this.zzc.intValue()).array());
            } else {
                throw new IllegalStateException("Unknown AesCmacParametersParameters.Variant: " + String.valueOf(this.zza.zze()));
            }
            return new zzph(this.zza, this.zzb, zzxtVarZza, this.zzc);
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzqr
    public final zzxt zzb() {
        return this.zzc;
    }

    public final zzxu zzc() {
        return this.zzb;
    }

    private zzph(zzpo zzpoVar, zzxu zzxuVar, zzxt zzxtVar, @Nullable Integer num) {
        this.zza = zzpoVar;
        this.zzb = zzxuVar;
        this.zzc = zzxtVar;
        this.zzd = num;
    }
}
