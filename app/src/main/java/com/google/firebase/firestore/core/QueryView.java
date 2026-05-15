package com.google.firebase.firestore.core;

/* JADX INFO: loaded from: classes10.dex */
final class QueryView {
    private final Query query;
    private final int targetId;
    private final View view;

    QueryView(Query query, int targetId, View view) {
        this.query = query;
        this.targetId = targetId;
        this.view = view;
    }

    public Query getQuery() {
        return this.query;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public View getView() {
        return this.view;
    }
}
