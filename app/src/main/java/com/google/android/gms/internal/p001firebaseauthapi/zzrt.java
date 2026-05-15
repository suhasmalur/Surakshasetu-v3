package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzrt {

    @Nullable
    private ArrayList<zzrs> zza = new ArrayList<>();
    private zzrn zzb = zzrn.zza;

    @Nullable
    private Integer zzc = null;

    public final zzrt zza(zzbv zzbvVar, int i, String str, String str2) {
        if (this.zza == null) {
            throw new IllegalStateException("addEntry cannot be called after build()");
        }
        this.zza.add(new zzrs(zzbvVar, i, str, str2));
        return this;
    }

    public final zzrt zza(zzrn zzrnVar) {
        if (this.zza == null) {
            throw new IllegalStateException("setAnnotations cannot be called after build()");
        }
        this.zzb = zzrnVar;
        return this;
    }

    public final zzrt zza(int i) {
        if (this.zza == null) {
            throw new IllegalStateException("setPrimaryKeyId cannot be called after build()");
        }
        this.zzc = Integer.valueOf(i);
        return this;
    }

    public final zzrq zza() throws GeneralSecurityException {
        if (this.zza == null) {
            throw new IllegalStateException("cannot call build() twice");
        }
        if (this.zzc != null) {
            int iIntValue = this.zzc.intValue();
            ArrayList<zzrs> arrayList = this.zza;
            int size = arrayList.size();
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                zzrs zzrsVar = arrayList.get(i);
                i++;
                if (zzrsVar.zza() == iIntValue) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                throw new GeneralSecurityException("primary key ID is not present in entries");
            }
        }
        zzrq zzrqVar = new zzrq(this.zzb, Collections.unmodifiableList(this.zza), this.zzc);
        this.zza = null;
        return zzrqVar;
    }
}
