package com.google.android.recaptcha.internal;

import kotlin.UInt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final /* synthetic */ class zzbh {
    public static String zza(zzbi zzbiVar, String str, byte b) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            sb.append((char) UInt.m559constructorimpl(UInt.m559constructorimpl(str.charAt(i)) ^ UInt.m559constructorimpl(b)));
        }
        return sb.toString();
    }
}
