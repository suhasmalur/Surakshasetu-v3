package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
public final class LocalDocumentsResult {
    private final int batchId;
    private final ImmutableSortedMap<DocumentKey, Document> documents;

    LocalDocumentsResult(int batchId, ImmutableSortedMap<DocumentKey, Document> documents) {
        this.batchId = batchId;
        this.documents = documents;
    }

    public static LocalDocumentsResult fromOverlayedDocuments(int batchId, Map<DocumentKey, OverlayedDocument> overlays) {
        ImmutableSortedMap<DocumentKey, Document> documents = DocumentCollections.emptyDocumentMap();
        for (Map.Entry<DocumentKey, OverlayedDocument> entry : overlays.entrySet()) {
            documents = documents.insert(entry.getKey(), entry.getValue().getDocument());
        }
        return new LocalDocumentsResult(batchId, documents);
    }

    public int getBatchId() {
        return this.batchId;
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocuments() {
        return this.documents;
    }
}
