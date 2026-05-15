package com.google.android.play.integrity.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class f extends a implements h {
    f(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.integrity.protocol.IIntegrityService");
    }

    @Override // com.google.android.play.integrity.internal.h
    public final void c(Bundle bundle, j jVar) throws RemoteException {
        Parcel parcelA = a();
        c.c(parcelA, bundle);
        c.d(parcelA, jVar);
        b(2, parcelA);
    }
}
