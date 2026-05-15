package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.DatabaseId;

/* JADX INFO: loaded from: classes10.dex */
public final class DatabaseInfo {
    private final DatabaseId databaseId;
    private final String host;
    private final String persistenceKey;
    private final boolean sslEnabled;

    public DatabaseInfo(DatabaseId databaseId, String persistenceKey, String host, boolean sslEnabled) {
        this.databaseId = databaseId;
        this.persistenceKey = persistenceKey;
        this.host = host;
        this.sslEnabled = sslEnabled;
    }

    public DatabaseId getDatabaseId() {
        return this.databaseId;
    }

    public String getPersistenceKey() {
        return this.persistenceKey;
    }

    public String getHost() {
        return this.host;
    }

    public boolean isSslEnabled() {
        return this.sslEnabled;
    }

    public String toString() {
        return "DatabaseInfo(databaseId:" + this.databaseId + " host:" + this.host + ")";
    }
}
