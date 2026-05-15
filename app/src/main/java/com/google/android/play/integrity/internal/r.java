package com.google.android.play.integrity.internal;

import android.os.IBinder;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class r extends l {
    final /* synthetic */ IBinder a;
    final /* synthetic */ u b;

    r(u uVar, IBinder iBinder) {
        this.b = uVar;
        this.a = iBinder;
    }

    @Override // com.google.android.play.integrity.internal.l
    public final void b() {
        this.b.a.n = g.b(this.a);
        v.n(this.b.a);
        this.b.a.h = false;
        Iterator it = this.b.a.e.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.b.a.e.clear();
    }
}
