package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.aa;
import com.google.android.play.integrity.internal.ac;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class v implements aa {
    private final ac a;
    private final ac b;

    public v(ac acVar, ac acVar2) {
        this.a = acVar;
        this.b = acVar2;
    }

    @Override // com.google.android.play.integrity.internal.ac
    public final /* bridge */ /* synthetic */ Object a() {
        return new t((Context) this.a.a(), (com.google.android.play.integrity.internal.k) this.b.a());
    }
}
