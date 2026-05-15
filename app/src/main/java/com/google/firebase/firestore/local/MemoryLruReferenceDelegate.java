package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.firestore.core.ListenSequence;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
class MemoryLruReferenceDelegate implements ReferenceDelegate, LruDelegate {
    private final LruGarbageCollector garbageCollector;
    private ReferenceSet inMemoryPins;
    private final ListenSequence listenSequence;
    private final MemoryPersistence persistence;
    private final LocalSerializer serializer;
    private final Map<DocumentKey, Long> orphanedSequenceNumbers = new HashMap();
    private long currentSequenceNumber = -1;

    MemoryLruReferenceDelegate(MemoryPersistence persistence, LruGarbageCollector.Params params, LocalSerializer serializer) {
        this.persistence = persistence;
        this.serializer = serializer;
        this.listenSequence = new ListenSequence(persistence.getTargetCache().getHighestListenSequenceNumber());
        this.garbageCollector = new LruGarbageCollector(this, params);
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public LruGarbageCollector getGarbageCollector() {
        return this.garbageCollector;
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void onTransactionStarted() {
        Assert.hardAssert(this.currentSequenceNumber == -1, "Starting a transaction without committing the previous one", new Object[0]);
        this.currentSequenceNumber = this.listenSequence.next();
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void onTransactionCommitted() {
        Assert.hardAssert(this.currentSequenceNumber != -1, "Committing a transaction without having started one", new Object[0]);
        this.currentSequenceNumber = -1L;
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public long getCurrentSequenceNumber() {
        Assert.hardAssert(this.currentSequenceNumber != -1, "Attempting to get a sequence number outside of a transaction", new Object[0]);
        return this.currentSequenceNumber;
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public void forEachTarget(Consumer<TargetData> consumer) {
        this.persistence.getTargetCache().forEachTarget(consumer);
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public long getSequenceNumberCount() {
        long targetCount = this.persistence.getTargetCache().getTargetCount();
        final long[] orphanedCount = new long[1];
        forEachOrphanedDocumentSequenceNumber(new Consumer() { // from class: com.google.firebase.firestore.local.MemoryLruReferenceDelegate$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                MemoryLruReferenceDelegate.lambda$getSequenceNumberCount$0(orphanedCount, (Long) obj);
            }
        });
        return orphanedCount[0] + targetCount;
    }

    static /* synthetic */ void lambda$getSequenceNumberCount$0(long[] orphanedCount, Long sequenceNumber) {
        orphanedCount[0] = orphanedCount[0] + 1;
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public void forEachOrphanedDocumentSequenceNumber(Consumer<Long> consumer) {
        for (Map.Entry<DocumentKey, Long> entry : this.orphanedSequenceNumbers.entrySet()) {
            if (!isPinned(entry.getKey(), entry.getValue().longValue())) {
                consumer.accept(entry.getValue());
            }
        }
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void setInMemoryPins(ReferenceSet inMemoryPins) {
        this.inMemoryPins = inMemoryPins;
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public int removeTargets(long upperBound, SparseArray<?> activeTargetIds) {
        return this.persistence.getTargetCache().removeQueries(upperBound, activeTargetIds);
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public int removeOrphanedDocuments(long upperBound) {
        MemoryRemoteDocumentCache cache = this.persistence.getRemoteDocumentCache();
        List<DocumentKey> docsToRemove = new ArrayList<>();
        for (Document doc : cache.getDocuments()) {
            DocumentKey key = doc.getKey();
            if (!isPinned(key, upperBound)) {
                docsToRemove.add(key);
                this.orphanedSequenceNumbers.remove(key);
            }
        }
        cache.removeAll(docsToRemove);
        return docsToRemove.size();
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeMutationReference(DocumentKey key) {
        this.orphanedSequenceNumbers.put(key, Long.valueOf(getCurrentSequenceNumber()));
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeTarget(TargetData targetData) {
        TargetData updated = targetData.withSequenceNumber(getCurrentSequenceNumber());
        this.persistence.getTargetCache().updateTargetData(updated);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void addReference(DocumentKey key) {
        this.orphanedSequenceNumbers.put(key, Long.valueOf(getCurrentSequenceNumber()));
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeReference(DocumentKey key) {
        this.orphanedSequenceNumbers.put(key, Long.valueOf(getCurrentSequenceNumber()));
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void updateLimboDocument(DocumentKey key) {
        this.orphanedSequenceNumbers.put(key, Long.valueOf(getCurrentSequenceNumber()));
    }

    private boolean mutationQueuesContainsKey(DocumentKey key) {
        for (MemoryMutationQueue mutationQueue : this.persistence.getMutationQueues()) {
            if (mutationQueue.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPinned(DocumentKey key, long upperBound) {
        if (mutationQueuesContainsKey(key) || this.inMemoryPins.containsKey(key) || this.persistence.getTargetCache().containsKey(key)) {
            return true;
        }
        Long sequenceNumber = this.orphanedSequenceNumbers.get(key);
        return sequenceNumber != null && sequenceNumber.longValue() > upperBound;
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public long getByteSize() {
        long count = 0 + this.persistence.getTargetCache().getByteSize(this.serializer);
        long count2 = count + this.persistence.getRemoteDocumentCache().getByteSize(this.serializer);
        for (MemoryMutationQueue queue : this.persistence.getMutationQueues()) {
            count2 += queue.getByteSize(this.serializer);
        }
        return count2;
    }
}
