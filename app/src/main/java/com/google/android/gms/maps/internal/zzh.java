package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzh extends com.google.android.gms.internal.maps.zzb implements zzi {
    public zzh() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                com.google.android.gms.internal.maps.zzad zzadVarZzb = com.google.android.gms.internal.maps.zzac.zzb(parcel.readStrongBinder());
                com.google.android.gms.internal.maps.zzc.zzc(parcel);
                IObjectWrapper iObjectWrapperZzc = zzc(zzadVarZzb);
                parcel2.writeNoException();
                com.google.android.gms.internal.maps.zzc.zze(parcel2, iObjectWrapperZzc);
                return true;
            case 2:
                com.google.android.gms.internal.maps.zzad zzadVarZzb2 = com.google.android.gms.internal.maps.zzac.zzb(parcel.readStrongBinder());
                com.google.android.gms.internal.maps.zzc.zzc(parcel);
                IObjectWrapper iObjectWrapperZzb = zzb(zzadVarZzb2);
                parcel2.writeNoException();
                com.google.android.gms.internal.maps.zzc.zze(parcel2, iObjectWrapperZzb);
                return true;
            default:
                return false;
        }
    }
}
