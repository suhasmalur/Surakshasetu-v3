package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: Add missing generic type declarations: [SerializationT] */
/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzmw<SerializationT> extends zzmu<SerializationT> {
    private final /* synthetic */ zzmv zza;

    /* JADX WARN: Incorrect types in method signature: (TSerializationT;Lcom/google/android/gms/internal/firebase-auth-api/zzcs;)Lcom/google/android/gms/internal/firebase-auth-api/zzbt; */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzmu
    public final zzbt zza(zzov zzovVar, @Nullable zzcs zzcsVar) throws GeneralSecurityException {
        return this.zza.zza(zzovVar, zzcsVar);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzmw(zzxt zzxtVar, Class cls, zzmv zzmvVar) {
        super(zzxtVar, cls);
        this.zza = zzmvVar;
    }
}
