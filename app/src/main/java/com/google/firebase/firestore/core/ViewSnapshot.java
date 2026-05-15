package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public class ViewSnapshot {
    private final List<DocumentViewChange> changes;
    private final boolean didSyncStateChange;
    private final DocumentSet documents;
    private boolean excludesMetadataChanges;
    private boolean hasCachedResults;
    private final boolean isFromCache;
    private final ImmutableSortedSet<DocumentKey> mutatedKeys;
    private final DocumentSet oldDocuments;
    private final Query query;

    public enum SyncState {
        NONE,
        LOCAL,
        SYNCED
    }

    public ViewSnapshot(Query query, DocumentSet documents, DocumentSet oldDocuments, List<DocumentViewChange> changes, boolean isFromCache, ImmutableSortedSet<DocumentKey> mutatedKeys, boolean didSyncStateChange, boolean excludesMetadataChanges, boolean hasCachedResults) {
        this.query = query;
        this.documents = documents;
        this.oldDocuments = oldDocuments;
        this.changes = changes;
        this.isFromCache = isFromCache;
        this.mutatedKeys = mutatedKeys;
        this.didSyncStateChange = didSyncStateChange;
        this.excludesMetadataChanges = excludesMetadataChanges;
        this.hasCachedResults = hasCachedResults;
    }

    public static ViewSnapshot fromInitialDocuments(Query query, DocumentSet documents, ImmutableSortedSet<DocumentKey> mutatedKeys, boolean fromCache, boolean excludesMetadataChanges, boolean hasCachedResults) {
        List<DocumentViewChange> viewChanges = new ArrayList<>();
        for (Document doc : documents) {
            viewChanges.add(DocumentViewChange.create(DocumentViewChange.Type.ADDED, doc));
        }
        return new ViewSnapshot(query, documents, DocumentSet.emptySet(query.comparator()), viewChanges, fromCache, mutatedKeys, true, excludesMetadataChanges, hasCachedResults);
    }

    public Query getQuery() {
        return this.query;
    }

    public DocumentSet getDocuments() {
        return this.documents;
    }

    public DocumentSet getOldDocuments() {
        return this.oldDocuments;
    }

    public List<DocumentViewChange> getChanges() {
        return this.changes;
    }

    public boolean isFromCache() {
        return this.isFromCache;
    }

    public boolean hasPendingWrites() {
        return !this.mutatedKeys.isEmpty();
    }

    public ImmutableSortedSet<DocumentKey> getMutatedKeys() {
        return this.mutatedKeys;
    }

    public boolean didSyncStateChange() {
        return this.didSyncStateChange;
    }

    public boolean excludesMetadataChanges() {
        return this.excludesMetadataChanges;
    }

    public boolean hasCachedResults() {
        return this.hasCachedResults;
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewSnapshot)) {
            return false;
        }
        ViewSnapshot that = (ViewSnapshot) o;
        if (this.isFromCache == that.isFromCache && this.didSyncStateChange == that.didSyncStateChange && this.excludesMetadataChanges == that.excludesMetadataChanges && this.query.equals(that.query) && this.mutatedKeys.equals(that.mutatedKeys) && this.documents.equals(that.documents) && this.oldDocuments.equals(that.oldDocuments) && this.hasCachedResults == that.hasCachedResults) {
            return this.changes.equals(that.changes);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((this.query.hashCode() * 31) + this.documents.hashCode()) * 31) + this.oldDocuments.hashCode()) * 31) + this.changes.hashCode()) * 31) + this.mutatedKeys.hashCode()) * 31) + (this.isFromCache ? 1 : 0)) * 31) + (this.didSyncStateChange ? 1 : 0)) * 31) + (this.excludesMetadataChanges ? 1 : 0)) * 31) + (this.hasCachedResults ? 1 : 0);
    }

    public String toString() {
        return "ViewSnapshot(" + this.query + ", " + this.documents + ", " + this.oldDocuments + ", " + this.changes + ", isFromCache=" + this.isFromCache + ", mutatedKeys=" + this.mutatedKeys.size() + ", didSyncStateChange=" + this.didSyncStateChange + ", excludesMetadataChanges=" + this.excludesMetadataChanges + ", hasCachedResults=" + this.hasCachedResults + ")";
    }
}
