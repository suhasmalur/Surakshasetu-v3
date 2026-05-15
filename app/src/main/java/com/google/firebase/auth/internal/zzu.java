package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzu implements AuthResult {
    public static final Parcelable.Creator<zzu> CREATOR = new zzx();
    private zzaa zza;
    private zzs zzb;
    private com.google.firebase.auth.zzf zzc;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.firebase.auth.AuthResult
    public final AdditionalUserInfo getAdditionalUserInfo() {
        return this.zzb;
    }

    @Override // com.google.firebase.auth.AuthResult
    public final AuthCredential getCredential() {
        return this.zzc;
    }

    @Override // com.google.firebase.auth.AuthResult
    public final FirebaseUser getUser() {
        return this.zza;
    }

    public zzu(zzaa zzaaVar) {
        this.zza = (zzaa) Preconditions.checkNotNull(zzaaVar);
        List<zzw> listZzi = this.zza.zzi();
        this.zzb = null;
        for (int i = 0; i < listZzi.size(); i++) {
            if (!TextUtils.isEmpty(listZzi.get(i).zza())) {
                this.zzb = new zzs(listZzi.get(i).getProviderId(), listZzi.get(i).zza(), zzaaVar.zzj());
            }
        }
        if (this.zzb == null) {
            this.zzb = new zzs(zzaaVar.zzj());
        }
        this.zzc = zzaaVar.zzg();
    }

    zzu(zzaa zzaaVar, zzs zzsVar, com.google.firebase.auth.zzf zzfVar) {
        this.zza = zzaaVar;
        this.zzb = zzsVar;
        this.zzc = zzfVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getUser(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getAdditionalUserInfo(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
