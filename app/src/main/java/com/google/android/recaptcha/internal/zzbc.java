package com.google.android.recaptcha.internal;

import android.webkit.WebView;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbc {
    private final WebView zza;
    private final CoroutineScope zzb;

    public zzbc(WebView webView, CoroutineScope coroutineScope) {
        this.zza = webView;
        this.zzb = coroutineScope;
    }

    public final void zzb(String str, String... strArr) {
        BuildersKt__Builders_commonKt.launch$default(this.zzb, null, null, new zzbb(strArr, this, str, null), 3, null);
    }
}
