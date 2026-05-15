package com.google.firebase.firestore.core;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes10.dex */
public class AsyncEventListener<T> implements EventListener<T> {
    private final EventListener<T> eventListener;
    private final Executor executor;
    private volatile boolean muted = false;

    public AsyncEventListener(Executor executor, EventListener<T> eventListener) {
        this.executor = executor;
        this.eventListener = eventListener;
    }

    @Override // com.google.firebase.firestore.EventListener
    public void onEvent(final T value, final FirebaseFirestoreException error) {
        this.executor.execute(new Runnable() { // from class: com.google.firebase.firestore.core.AsyncEventListener$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m281xdc9f5a86(value, error);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onEvent$0$com-google-firebase-firestore-core-AsyncEventListener, reason: not valid java name */
    /* synthetic */ void m281xdc9f5a86(Object value, FirebaseFirestoreException error) {
        if (!this.muted) {
            this.eventListener.onEvent(value, error);
        }
    }

    public void mute() {
        this.muted = true;
    }
}
