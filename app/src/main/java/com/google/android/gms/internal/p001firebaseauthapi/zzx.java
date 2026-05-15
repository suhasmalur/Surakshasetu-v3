package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzx {
    private static final Logger zza = Logger.getLogger(zzx.class.getName());
    private static final zzv zzb = new zza();

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    private static final class zza implements zzv {
        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzv
        public final zzs zza(String str) {
            return new zzu(Pattern.compile(str));
        }

        private zza() {
        }
    }

    static zzs zza(String str) {
        zzy.zza(str);
        return zzb.zza(str);
    }

    @CheckForNull
    static String zzb(@CheckForNull String str) {
        if (zzd(str)) {
            return null;
        }
        return str;
    }

    static String zzc(@CheckForNull String str) {
        return str == null ? "" : str;
    }

    private zzx() {
    }

    static boolean zzd(@CheckForNull String str) {
        return str == null || str.isEmpty();
    }
}
