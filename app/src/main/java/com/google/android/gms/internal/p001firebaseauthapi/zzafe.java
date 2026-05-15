package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.ActionCodeSettings;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzafe implements zzacp {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private ActionCodeSettings zze;
    private String zzf;
    private String zzg;

    public final ActionCodeSettings zzb() {
        return this.zze;
    }

    public static zzafe zza(ActionCodeSettings actionCodeSettings, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(actionCodeSettings);
        return new zzafe(7, actionCodeSettings, null, str2, str, null, null);
    }

    public final zzafe zza(ActionCodeSettings actionCodeSettings) {
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        return this;
    }

    public final zzafe zza(String str) {
        this.zzg = str;
        return this;
    }

    public final zzafe zzb(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzafe zzc(String str) {
        this.zzf = str;
        return this;
    }

    public final zzafe zzd(String str) {
        this.zzd = Preconditions.checkNotEmpty(str);
        return this;
    }

    private static String zza(int i) {
        switch (i) {
            case 1:
                return "PASSWORD_RESET";
            case 2:
            case 3:
            case 5:
            default:
                return "REQUEST_TYPE_UNSET_ENUM_VALUE";
            case 4:
                return "VERIFY_EMAIL";
            case 6:
                return "EMAIL_SIGNIN";
            case 7:
                return "VERIFY_AND_CHANGE_EMAIL";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003a  */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String zza() throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p001firebaseauthapi.zzafe.zza():java.lang.String");
    }

    public zzafe(int i) {
        this.zza = zza(i);
    }

    private zzafe(int i, ActionCodeSettings actionCodeSettings, String str, String str2, String str3, String str4, String str5) {
        this.zza = zza(7);
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        this.zzb = null;
        this.zzc = str2;
        this.zzd = str3;
        this.zzf = null;
        this.zzg = null;
    }
}
