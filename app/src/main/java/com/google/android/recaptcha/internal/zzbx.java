package com.google.android.recaptcha.internal;

import kotlin.UInt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final /* synthetic */ class zzbx {
    public static String zza(zzby zzbyVar, String str, byte b) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            sb.append((char) UInt.m559constructorimpl(UInt.m559constructorimpl(str.charAt(i)) ^ UInt.m559constructorimpl(b)));
        }
        return sb.toString();
    }

    public static void zzb(zzby zzbyVar, int i, int i2) throws zzt {
        if (i != i2) {
            throw new zzt(4, 24, null);
        }
    }
}
