package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.DocumentKey;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes10.dex */
public class ReferenceSet {
    private ImmutableSortedSet<DocumentReference> referencesByKey = new ImmutableSortedSet<>(Collections.emptyList(), DocumentReference.BY_KEY);
    private ImmutableSortedSet<DocumentReference> referencesByTarget = new ImmutableSortedSet<>(Collections.emptyList(), DocumentReference.BY_TARGET);

    public boolean isEmpty() {
        return this.referencesByKey.isEmpty();
    }

    public void addReference(DocumentKey key, int targetOrBatchId) {
        DocumentReference ref = new DocumentReference(key, targetOrBatchId);
        this.referencesByKey = this.referencesByKey.insert(ref);
        this.referencesByTarget = this.referencesByTarget.insert(ref);
    }

    public void addReferences(ImmutableSortedSet<DocumentKey> keys, int targetOrBatchId) {
        for (DocumentKey key : keys) {
            addReference(key, targetOrBatchId);
        }
    }

    public void removeReference(DocumentKey key, int targetOrBatchId) {
        removeReference(new DocumentReference(key, targetOrBatchId));
    }

    public void removeReferences(ImmutableSortedSet<DocumentKey> keys, int targetOrBatchId) {
        for (DocumentKey key : keys) {
            removeReference(key, targetOrBatchId);
        }
    }

    public ImmutableSortedSet<DocumentKey> removeReferencesForId(int targetId) {
        DocumentKey emptyKey = DocumentKey.empty();
        DocumentReference startRef = new DocumentReference(emptyKey, targetId);
        Iterator<DocumentReference> it = this.referencesByTarget.iteratorFrom(startRef);
        ImmutableSortedSet<DocumentKey> keys = DocumentKey.emptyKeySet();
        while (it.hasNext()) {
            DocumentReference ref = it.next();
            if (ref.getId() != targetId) {
                break;
            }
            keys = keys.insert(ref.getKey());
            removeReference(ref);
        }
        return keys;
    }

    public void removeAllReferences() {
        for (DocumentReference reference : this.referencesByKey) {
            removeReference(reference);
        }
    }

    private void removeReference(DocumentReference ref) {
        this.referencesByKey = this.referencesByKey.remove(ref);
        this.referencesByTarget = this.referencesByTarget.remove(ref);
    }

    public ImmutableSortedSet<DocumentKey> referencesForId(int target) {
        DocumentKey emptyKey = DocumentKey.empty();
        DocumentReference startRef = new DocumentReference(emptyKey, target);
        Iterator<DocumentReference> iterator = this.referencesByTarget.iteratorFrom(startRef);
        ImmutableSortedSet<DocumentKey> keys = DocumentKey.emptyKeySet();
        while (iterator.hasNext()) {
            DocumentReference reference = iterator.next();
            if (reference.getId() != target) {
                break;
            }
            keys = keys.insert(reference.getKey());
        }
        return keys;
    }

    public boolean containsKey(DocumentKey key) {
        DocumentReference ref = new DocumentReference(key, 0);
        Iterator<DocumentReference> iterator = this.referencesByKey.iteratorFrom(ref);
        if (!iterator.hasNext()) {
            return false;
        }
        DocumentKey firstKey = iterator.next().getKey();
        return firstKey.equals(key);
    }
}
