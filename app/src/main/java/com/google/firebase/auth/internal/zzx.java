package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzx implements Parcelable.Creator<zzu> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzu createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzaa zzaaVar = null;
        zzs zzsVar = null;
        com.google.firebase.auth.zzf zzfVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    zzaaVar = (zzaa) SafeParcelReader.createParcelable(parcel, header, zzaa.CREATOR);
                    break;
                case 2:
                    zzsVar = (zzs) SafeParcelReader.createParcelable(parcel, header, zzs.CREATOR);
                    break;
                case 3:
                    zzfVar = (com.google.firebase.auth.zzf) SafeParcelReader.createParcelable(parcel, header, com.google.firebase.auth.zzf.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzu(zzaaVar, zzsVar, zzfVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzu[] newArray(int i) {
        return new zzu[i];
    }
}
