package io.grpc.stub;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import java.util.Iterator;

/* JADX INFO: loaded from: classes10.dex */
public final class StreamObservers {
    public static <V> void copyWithFlowControl(final Iterator<V> source, final CallStreamObserver<V> target) {
        Preconditions.checkNotNull(source, Constants.ScionAnalytics.PARAM_SOURCE);
        Preconditions.checkNotNull(target, TypedValues.AttributesType.S_TARGET);
        target.setOnReadyHandler(new Runnable() { // from class: io.grpc.stub.StreamObservers.1FlowControllingOnReadyHandler
            private boolean completed;

            @Override // java.lang.Runnable
            public void run() {
                if (this.completed) {
                    return;
                }
                while (target.isReady() && source.hasNext()) {
                    target.onNext(source.next());
                }
                if (!source.hasNext()) {
                    this.completed = true;
                    target.onCompleted();
                }
            }
        });
    }

    public static <V> void copyWithFlowControl(Iterable<V> source, CallStreamObserver<V> target) {
        Preconditions.checkNotNull(source, Constants.ScionAnalytics.PARAM_SOURCE);
        copyWithFlowControl(source.iterator(), target);
    }
}
