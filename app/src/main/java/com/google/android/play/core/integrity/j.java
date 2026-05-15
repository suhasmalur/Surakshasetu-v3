package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.ab;
import com.google.android.play.integrity.internal.ac;
import com.google.android.play.integrity.internal.z;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class j {
    private final ac b;
    private final ac d;
    private final ac e;
    private final j a = this;
    private final ac c = z.b(o.a);

    /* synthetic */ j(Context context, i iVar) {
        this.b = ab.b(context);
        this.d = z.b(new v(this.b, this.c));
        this.e = z.b(new n(this.d));
    }

    public final IntegrityManager a() {
        return (IntegrityManager) this.e.a();
    }
}
