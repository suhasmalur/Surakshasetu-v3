package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes10.dex */
class MemoryEagerReferenceDelegate implements ReferenceDelegate {
    private ReferenceSet inMemoryPins;
    private Set<DocumentKey> orphanedDocuments;
    private final MemoryPersistence persistence;

    MemoryEagerReferenceDelegate(MemoryPersistence persistence) {
        this.persistence = persistence;
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public long getCurrentSequenceNumber() {
        return -1L;
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void setInMemoryPins(ReferenceSet inMemoryPins) {
        this.inMemoryPins = inMemoryPins;
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void addReference(DocumentKey key) {
        this.orphanedDocuments.remove(key);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeReference(DocumentKey key) {
        this.orphanedDocuments.add(key);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeMutationReference(DocumentKey key) {
        this.orphanedDocuments.add(key);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeTarget(TargetData targetData) {
        MemoryTargetCache targetCache = this.persistence.getTargetCache();
        for (DocumentKey key : targetCache.getMatchingKeysForTargetId(targetData.getTargetId())) {
            this.orphanedDocuments.add(key);
        }
        targetCache.removeTargetData(targetData);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void onTransactionStarted() {
        this.orphanedDocuments = new HashSet();
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void onTransactionCommitted() {
        MemoryRemoteDocumentCache remoteDocuments = this.persistence.getRemoteDocumentCache();
        List<DocumentKey> docsToRemove = new ArrayList<>();
        for (DocumentKey key : this.orphanedDocuments) {
            if (!isReferenced(key)) {
                docsToRemove.add(key);
            }
        }
        remoteDocuments.removeAll(docsToRemove);
        this.orphanedDocuments = null;
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void updateLimboDocument(DocumentKey key) {
        if (isReferenced(key)) {
            this.orphanedDocuments.remove(key);
        } else {
            this.orphanedDocuments.add(key);
        }
    }

    private boolean mutationQueuesContainKey(DocumentKey key) {
        for (MemoryMutationQueue queue : this.persistence.getMutationQueues()) {
            if (queue.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    private boolean isReferenced(DocumentKey key) {
        if (this.persistence.getTargetCache().containsKey(key) || mutationQueuesContainKey(key)) {
            return true;
        }
        return this.inMemoryPins != null && this.inMemoryPins.containsKey(key);
    }
}
