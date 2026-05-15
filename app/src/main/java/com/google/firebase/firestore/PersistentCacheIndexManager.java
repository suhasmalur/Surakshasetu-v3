package com.google.firebase.firestore;

import com.google.firebase.firestore.core.FirestoreClient;

/* JADX INFO: loaded from: classes10.dex */
public final class PersistentCacheIndexManager {
    private FirestoreClient client;

    PersistentCacheIndexManager(FirestoreClient client) {
        this.client = client;
    }

    public void enableIndexAutoCreation() {
        this.client.setIndexAutoCreationEnabled(true);
    }

    public void disableIndexAutoCreation() {
        this.client.setIndexAutoCreationEnabled(false);
    }

    public void deleteAllIndexes() {
        this.client.deleteAllFieldIndexes();
    }
}
