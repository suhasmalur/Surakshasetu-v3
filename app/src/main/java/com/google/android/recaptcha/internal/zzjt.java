package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzjt extends IllegalArgumentException {
    zzjt(int i, int i2) {
        super("Unpaired surrogate at index " + i + " of " + i2);
    }
}
