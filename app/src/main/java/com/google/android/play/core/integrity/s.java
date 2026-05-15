package com.google.android.play.core.integrity;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.messaging.Constants;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class s extends com.google.android.play.integrity.internal.i {
    final /* synthetic */ t a;
    private final com.google.android.play.integrity.internal.k b = new com.google.android.play.integrity.internal.k("OnRequestIntegrityTokenCallback");
    private final TaskCompletionSource c;

    s(t tVar, TaskCompletionSource taskCompletionSource) {
        this.a = tVar;
        this.c = taskCompletionSource;
    }

    @Override // com.google.android.play.integrity.internal.j
    public final void b(Bundle bundle) {
        this.a.a.r(this.c);
        this.b.d("onRequestIntegrityToken", new Object[0]);
        int i = bundle.getInt(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        if (i != 0) {
            this.c.trySetException(new IntegrityServiceException(i, null));
            return;
        }
        String string = bundle.getString("token");
        if (string == null) {
            this.c.trySetException(new IntegrityServiceException(-100, null));
            return;
        }
        TaskCompletionSource taskCompletionSource = this.c;
        d dVar = new d();
        dVar.a(string);
        taskCompletionSource.trySetResult(dVar.b());
    }
}
