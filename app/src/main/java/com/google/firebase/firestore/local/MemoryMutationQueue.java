package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
final class MemoryMutationQueue implements MutationQueue {
    private final MemoryIndexManager indexManager;
    private final MemoryPersistence persistence;
    private final List<MutationBatch> queue = new ArrayList();
    private ImmutableSortedSet<DocumentReference> batchesByDocumentKey = new ImmutableSortedSet<>(Collections.emptyList(), DocumentReference.BY_KEY);
    private int nextBatchId = 1;
    private ByteString lastStreamToken = WriteStream.EMPTY_STREAM_TOKEN;

    MemoryMutationQueue(MemoryPersistence persistence, User user) {
        this.persistence = persistence;
        this.indexManager = persistence.getIndexManager(user);
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void start() {
        if (isEmpty()) {
            this.nextBatchId = 1;
        }
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void acknowledgeBatch(MutationBatch batch, ByteString streamToken) {
        int batchId = batch.getBatchId();
        int batchIndex = indexOfExistingBatchId(batchId, "acknowledged");
        Assert.hardAssert(batchIndex == 0, "Can only acknowledge the first batch in the mutation queue", new Object[0]);
        MutationBatch check = this.queue.get(batchIndex);
        Assert.hardAssert(batchId == check.getBatchId(), "Queue ordering failure: expected batch %d, got batch %d", Integer.valueOf(batchId), Integer.valueOf(check.getBatchId()));
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(streamToken);
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public ByteString getLastStreamToken() {
        return this.lastStreamToken;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void setLastStreamToken(ByteString streamToken) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(streamToken);
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch addMutationBatch(Timestamp localWriteTime, List<Mutation> baseMutations, List<Mutation> mutations) {
        Assert.hardAssert(!mutations.isEmpty(), "Mutation batches should not be empty", new Object[0]);
        int batchId = this.nextBatchId;
        this.nextBatchId++;
        int size = this.queue.size();
        if (size > 0) {
            MutationBatch prior = this.queue.get(size - 1);
            Assert.hardAssert(prior.getBatchId() < batchId, "Mutation batchIds must be monotonically increasing order", new Object[0]);
        }
        MutationBatch batch = new MutationBatch(batchId, localWriteTime, baseMutations, mutations);
        this.queue.add(batch);
        for (Mutation mutation : mutations) {
            this.batchesByDocumentKey = this.batchesByDocumentKey.insert(new DocumentReference(mutation.getKey(), batchId));
            this.indexManager.addToCollectionParentIndex(mutation.getKey().getCollectionPath());
        }
        return batch;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch lookupMutationBatch(int batchId) {
        int index = indexOfBatchId(batchId);
        if (index < 0 || index >= this.queue.size()) {
            return null;
        }
        MutationBatch batch = this.queue.get(index);
        Assert.hardAssert(batch.getBatchId() == batchId, "If found batch must match", new Object[0]);
        return batch;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch getNextMutationBatchAfterBatchId(int batchId) {
        int nextBatchId = batchId + 1;
        int rawIndex = indexOfBatchId(nextBatchId);
        int index = rawIndex < 0 ? 0 : rawIndex;
        if (this.queue.size() > index) {
            return this.queue.get(index);
        }
        return null;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public int getHighestUnacknowledgedBatchId() {
        if (this.queue.isEmpty()) {
            return -1;
        }
        return this.nextBatchId - 1;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatches() {
        return Collections.unmodifiableList(this.queue);
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKey(DocumentKey documentKey) {
        DocumentReference start = new DocumentReference(documentKey, 0);
        List<MutationBatch> result = new ArrayList<>();
        Iterator<DocumentReference> iterator = this.batchesByDocumentKey.iteratorFrom(start);
        while (iterator.hasNext()) {
            DocumentReference reference = iterator.next();
            if (!documentKey.equals(reference.getKey())) {
                break;
            }
            MutationBatch batch = lookupMutationBatch(reference.getId());
            Assert.hardAssert(batch != null, "Batches in the index must exist in the main table", new Object[0]);
            result.add(batch);
        }
        return result;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKeys(Iterable<DocumentKey> documentKeys) {
        ImmutableSortedSet<Integer> uniqueBatchIDs = new ImmutableSortedSet<>(Collections.emptyList(), Util.comparator());
        for (DocumentKey key : documentKeys) {
            DocumentReference start = new DocumentReference(key, 0);
            Iterator<DocumentReference> batchesIter = this.batchesByDocumentKey.iteratorFrom(start);
            while (batchesIter.hasNext()) {
                DocumentReference reference = batchesIter.next();
                if (!key.equals(reference.getKey())) {
                    break;
                }
                uniqueBatchIDs = uniqueBatchIDs.insert(Integer.valueOf(reference.getId()));
            }
        }
        return lookupMutationBatches(uniqueBatchIDs);
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingQuery(Query query) {
        Assert.hardAssert(!query.isCollectionGroupQuery(), "CollectionGroup queries should be handled in LocalDocumentsView", new Object[0]);
        ResourcePath prefix = query.getPath();
        int immediateChildrenPathLength = prefix.length() + 1;
        ResourcePath startPath = prefix;
        if (!DocumentKey.isDocumentKey(startPath)) {
            startPath = startPath.append("");
        }
        DocumentReference start = new DocumentReference(DocumentKey.fromPath(startPath), 0);
        ImmutableSortedSet<Integer> uniqueBatchIDs = new ImmutableSortedSet<>(Collections.emptyList(), Util.comparator());
        Iterator<DocumentReference> iterator = this.batchesByDocumentKey.iteratorFrom(start);
        while (iterator.hasNext()) {
            DocumentReference reference = iterator.next();
            ResourcePath rowKeyPath = reference.getKey().getPath();
            if (!prefix.isPrefixOf(rowKeyPath)) {
                break;
            }
            if (rowKeyPath.length() == immediateChildrenPathLength) {
                uniqueBatchIDs = uniqueBatchIDs.insert(Integer.valueOf(reference.getId()));
            }
        }
        return lookupMutationBatches(uniqueBatchIDs);
    }

    private List<MutationBatch> lookupMutationBatches(ImmutableSortedSet<Integer> batchIds) {
        List<MutationBatch> result = new ArrayList<>();
        for (Integer batchId : batchIds) {
            MutationBatch batch = lookupMutationBatch(batchId.intValue());
            if (batch != null) {
                result.add(batch);
            }
        }
        return result;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void removeMutationBatch(MutationBatch batch) {
        int batchIndex = indexOfExistingBatchId(batch.getBatchId(), "removed");
        Assert.hardAssert(batchIndex == 0, "Can only remove the first entry of the mutation queue", new Object[0]);
        this.queue.remove(0);
        ImmutableSortedSet<DocumentReference> references = this.batchesByDocumentKey;
        for (Mutation mutation : batch.getMutations()) {
            DocumentKey key = mutation.getKey();
            this.persistence.getReferenceDelegate().removeMutationReference(key);
            DocumentReference reference = new DocumentReference(key, batch.getBatchId());
            references = references.remove(reference);
        }
        this.batchesByDocumentKey = references;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void performConsistencyCheck() {
        if (this.queue.isEmpty()) {
            Assert.hardAssert(this.batchesByDocumentKey.isEmpty(), "Document leak -- detected dangling mutation references when queue is empty.", new Object[0]);
        }
    }

    boolean containsKey(DocumentKey key) {
        DocumentReference reference = new DocumentReference(key, 0);
        Iterator<DocumentReference> iterator = this.batchesByDocumentKey.iteratorFrom(reference);
        if (!iterator.hasNext()) {
            return false;
        }
        DocumentKey firstKey = iterator.next().getKey();
        return firstKey.equals(key);
    }

    private int indexOfBatchId(int batchId) {
        if (this.queue.isEmpty()) {
            return 0;
        }
        MutationBatch firstBatch = this.queue.get(0);
        int firstBatchId = firstBatch.getBatchId();
        return batchId - firstBatchId;
    }

    private int indexOfExistingBatchId(int batchId, String action) {
        int index = indexOfBatchId(batchId);
        Assert.hardAssert(index >= 0 && index < this.queue.size(), "Batches must exist to be %s", action);
        return index;
    }

    long getByteSize(LocalSerializer serializer) {
        long count = 0;
        for (MutationBatch batch : this.queue) {
            count += (long) serializer.encodeMutationBatch(batch).getSerializedSize();
        }
        return count;
    }
}
