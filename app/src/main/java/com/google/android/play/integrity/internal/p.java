package com.google.android.play.integrity.internal;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class p extends l {
    final /* synthetic */ v a;

    p(v vVar) {
        this.a = vVar;
    }

    @Override // com.google.android.play.integrity.internal.l
    public final void b() {
        v vVar = this.a;
        if (vVar.n != null) {
            vVar.c.d("Unbind from service.", new Object[0]);
            v vVar2 = this.a;
            vVar2.b.unbindService(vVar2.m);
            this.a.h = false;
            this.a.n = null;
            this.a.m = null;
        }
        this.a.t();
    }
}
