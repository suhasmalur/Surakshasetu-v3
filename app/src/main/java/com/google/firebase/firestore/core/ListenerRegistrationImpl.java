package com.google.firebase.firestore.core;

import com.google.firebase.firestore.ListenerRegistration;

/* JADX INFO: loaded from: classes10.dex */
public class ListenerRegistrationImpl implements ListenerRegistration {
    private final AsyncEventListener<ViewSnapshot> asyncEventListener;
    private final FirestoreClient client;
    private final QueryListener queryListener;

    public ListenerRegistrationImpl(FirestoreClient client, QueryListener queryListener, AsyncEventListener<ViewSnapshot> asyncEventListener) {
        this.client = client;
        this.queryListener = queryListener;
        this.asyncEventListener = asyncEventListener;
    }

    @Override // com.google.firebase.firestore.ListenerRegistration
    public void remove() {
        this.asyncEventListener.mute();
        this.client.stopListening(this.queryListener);
    }
}
