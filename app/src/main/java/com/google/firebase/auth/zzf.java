package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.p001firebaseauthapi.zzagt;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public class zzf extends OAuthCredential {
    public static final Parcelable.Creator<zzf> CREATOR = new zze();
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final zzagt zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;

    @Override // com.google.firebase.auth.AuthCredential
    public final AuthCredential zza() {
        return new zzf(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
    }

    public static zzf zza(zzagt zzagtVar) {
        Preconditions.checkNotNull(zzagtVar, "Must specify a non-null webSignInCredential");
        return new zzf(null, null, null, zzagtVar, null, null, null);
    }

    public static zzf zza(String str, String str2, String str3) {
        return zza(str, str2, str3, null, null);
    }

    public static zzf zza(String str, String str2, String str3, String str4, String str5) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
        }
        return new zzf(str, str2, str3, null, str4, str5, null);
    }

    static zzf zza(String str, String str2, String str3, String str4) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
        }
        return new zzf(str, str2, str3, null, null, null, str4);
    }

    public static zzagt zza(zzf zzfVar, String str) {
        Preconditions.checkNotNull(zzfVar);
        zzagt zzagtVar = zzfVar.zzd;
        if (zzagtVar != null) {
            return zzagtVar;
        }
        return new zzagt(zzfVar.getIdToken(), zzfVar.getAccessToken(), zzfVar.getProvider(), null, zzfVar.getSecret(), null, str, zzfVar.zze, zzfVar.zzg);
    }

    @Override // com.google.firebase.auth.OAuthCredential
    public String getAccessToken() {
        return this.zzc;
    }

    @Override // com.google.firebase.auth.OAuthCredential
    public String getIdToken() {
        return this.zzb;
    }

    @Override // com.google.firebase.auth.AuthCredential
    public String getProvider() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.OAuthCredential
    public String getSecret() {
        return this.zzf;
    }

    @Override // com.google.firebase.auth.AuthCredential
    public String getSignInMethod() {
        return this.zza;
    }

    zzf(String str, String str2, String str3, zzagt zzagtVar, String str4, String str5, String str6) {
        this.zza = com.google.android.gms.internal.p001firebaseauthapi.zzag.zzb(str);
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzagtVar;
        this.zze = str4;
        this.zzf = str5;
        this.zzg = str6;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getProvider(), false);
        SafeParcelWriter.writeString(parcel, 2, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 3, getAccessToken(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, getSecret(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
