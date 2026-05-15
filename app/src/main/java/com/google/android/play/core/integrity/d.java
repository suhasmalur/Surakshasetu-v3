package com.google.android.play.core.integrity;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class d extends w {
    private String a;

    d() {
    }

    @Override // com.google.android.play.core.integrity.w
    final w a(String str) {
        this.a = str;
        return this;
    }

    @Override // com.google.android.play.core.integrity.w
    final IntegrityTokenResponse b() {
        String str = this.a;
        if (str != null) {
            return new f(str, null);
        }
        throw new IllegalStateException("Missing required properties: token");
    }
}
