package com.google.firebase.firestore;

import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public class DocumentChange {
    private final QueryDocumentSnapshot document;
    private final int newIndex;
    private final int oldIndex;
    private final Type type;

    public enum Type {
        ADDED,
        MODIFIED,
        REMOVED
    }

    DocumentChange(QueryDocumentSnapshot document, Type type, int oldIndex, int newIndex) {
        this.type = type;
        this.document = document;
        this.oldIndex = oldIndex;
        this.newIndex = newIndex;
    }

    public boolean equals(Object object) {
        if (!(object instanceof DocumentChange)) {
            return false;
        }
        DocumentChange that = (DocumentChange) object;
        return this.type.equals(that.type) && this.document.equals(that.document) && this.oldIndex == that.oldIndex && this.newIndex == that.newIndex;
    }

    public int hashCode() {
        int result = this.type.hashCode();
        return (((((result * 31) + this.document.hashCode()) * 31) + this.oldIndex) * 31) + this.newIndex;
    }

    public Type getType() {
        return this.type;
    }

    public QueryDocumentSnapshot getDocument() {
        return this.document;
    }

    public int getOldIndex() {
        return this.oldIndex;
    }

    public int getNewIndex() {
        return this.newIndex;
    }

    static List<DocumentChange> changesFromSnapshot(FirebaseFirestore firestore, MetadataChanges metadataChanges, ViewSnapshot snapshot) {
        int oldIndex;
        int newIndex;
        List<DocumentChange> documentChanges = new ArrayList<>();
        if (snapshot.getOldDocuments().isEmpty()) {
            int index = 0;
            Document lastDoc = null;
            for (DocumentViewChange change : snapshot.getChanges()) {
                Document document = change.getDocument();
                QueryDocumentSnapshot documentSnapshot = QueryDocumentSnapshot.fromDocument(firestore, document, snapshot.isFromCache(), snapshot.getMutatedKeys().contains(document.getKey()));
                Assert.hardAssert(change.getType() == DocumentViewChange.Type.ADDED, "Invalid added event for first snapshot", new Object[0]);
                Assert.hardAssert(lastDoc == null || snapshot.getQuery().comparator().compare(lastDoc, document) < 0, "Got added events in wrong order", new Object[0]);
                documentChanges.add(new DocumentChange(documentSnapshot, Type.ADDED, -1, index));
                lastDoc = document;
                index++;
            }
        } else {
            DocumentSet indexTracker = snapshot.getOldDocuments();
            for (DocumentViewChange change2 : snapshot.getChanges()) {
                if (metadataChanges != MetadataChanges.EXCLUDE || change2.getType() != DocumentViewChange.Type.METADATA) {
                    Document document2 = change2.getDocument();
                    QueryDocumentSnapshot documentSnapshot2 = QueryDocumentSnapshot.fromDocument(firestore, document2, snapshot.isFromCache(), snapshot.getMutatedKeys().contains(document2.getKey()));
                    Type type = getType(change2);
                    if (type != Type.ADDED) {
                        oldIndex = indexTracker.indexOf(document2.getKey());
                        Assert.hardAssert(oldIndex >= 0, "Index for document not found", new Object[0]);
                        indexTracker = indexTracker.remove(document2.getKey());
                    } else {
                        oldIndex = -1;
                    }
                    if (type != Type.REMOVED) {
                        indexTracker = indexTracker.add(document2);
                        newIndex = indexTracker.indexOf(document2.getKey());
                        Assert.hardAssert(newIndex >= 0, "Index for document not found", new Object[0]);
                    } else {
                        newIndex = -1;
                    }
                    documentChanges.add(new DocumentChange(documentSnapshot2, type, oldIndex, newIndex));
                }
            }
        }
        return documentChanges;
    }

    private static Type getType(DocumentViewChange change) {
        switch (change.getType()) {
            case ADDED:
                return Type.ADDED;
            case METADATA:
            case MODIFIED:
                return Type.MODIFIED;
            case REMOVED:
                return Type.REMOVED;
            default:
                throw new IllegalArgumentException("Unknown view change type: " + change.getType());
        }
    }
}
