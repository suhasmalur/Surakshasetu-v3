package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzaw extends com.google.android.gms.internal.maps.zzb implements zzax {
    public zzaw() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                com.google.android.gms.internal.maps.zzad zzadVarZzb = com.google.android.gms.internal.maps.zzac.zzb(parcel.readStrongBinder());
                com.google.android.gms.internal.maps.zzc.zzc(parcel);
                zzd(zzadVarZzb);
                break;
            case 2:
                com.google.android.gms.internal.maps.zzad zzadVarZzb2 = com.google.android.gms.internal.maps.zzac.zzb(parcel.readStrongBinder());
                com.google.android.gms.internal.maps.zzc.zzc(parcel);
                zzb(zzadVarZzb2);
                break;
            case 3:
                com.google.android.gms.internal.maps.zzad zzadVarZzb3 = com.google.android.gms.internal.maps.zzac.zzb(parcel.readStrongBinder());
                com.google.android.gms.internal.maps.zzc.zzc(parcel);
                zzc(zzadVarZzb3);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
