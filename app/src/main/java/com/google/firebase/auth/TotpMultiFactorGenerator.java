package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public final class TotpMultiFactorGenerator {
    public static final String FACTOR_ID = "totp";

    public static Task<TotpSecret> generateSecret(MultiFactorSession multiFactorSession) {
        Preconditions.checkNotNull(multiFactorSession);
        com.google.firebase.auth.internal.zzal zzalVar = (com.google.firebase.auth.internal.zzal) multiFactorSession;
        return FirebaseAuth.getInstance(zzalVar.zza().zza()).zza(zzalVar);
    }

    public static TotpMultiFactorAssertion getAssertionForEnrollment(TotpSecret totpSecret, String str) {
        return new TotpMultiFactorAssertion((String) Preconditions.checkNotNull(str), (TotpSecret) Preconditions.checkNotNull(totpSecret), null);
    }

    public static TotpMultiFactorAssertion getAssertionForSignIn(String str, String str2) {
        return new TotpMultiFactorAssertion((String) Preconditions.checkNotNull(str2), null, (String) Preconditions.checkNotNull(str));
    }

    private TotpMultiFactorGenerator() {
    }
}
