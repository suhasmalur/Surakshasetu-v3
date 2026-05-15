package com.google.android.play.integrity.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class o extends l {
    final /* synthetic */ l a;
    final /* synthetic */ v b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    o(v vVar, TaskCompletionSource taskCompletionSource, l lVar) {
        super(taskCompletionSource);
        this.b = vVar;
        this.a = lVar;
    }

    @Override // com.google.android.play.integrity.internal.l
    public final void b() {
        v.m(this.b, this.a);
    }
}
