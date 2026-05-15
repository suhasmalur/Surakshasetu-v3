package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.util.SparseArray;
import com.google.firebase.firestore.core.ListenSequence;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Function;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes10.dex */
public class SQLiteLruReferenceDelegate implements ReferenceDelegate, LruDelegate {
    static final int REMOVE_ORPHANED_DOCUMENTS_BATCH_SIZE = 100;
    private long currentSequenceNumber = -1;
    private final LruGarbageCollector garbageCollector;
    private ReferenceSet inMemoryPins;
    private ListenSequence listenSequence;
    private final SQLitePersistence persistence;

    SQLiteLruReferenceDelegate(SQLitePersistence persistence, LruGarbageCollector.Params params) {
        this.persistence = persistence;
        this.garbageCollector = new LruGarbageCollector(this, params);
    }

    void start(long highestSequenceNumber) {
        this.listenSequence = new ListenSequence(highestSequenceNumber);
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
    public LruGarbageCollector getGarbageCollector() {
        return this.garbageCollector;
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public long getSequenceNumberCount() {
        long targetCount = this.persistence.getTargetCache().getTargetCount();
        long orphanedDocumentCount = ((Long) this.persistence.query("SELECT COUNT(*) FROM (SELECT sequence_number FROM target_documents GROUP BY path HAVING COUNT(*) = 1 AND target_id = 0)").firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteLruReferenceDelegate$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return Long.valueOf(((Cursor) obj).getLong(0));
            }
        })).longValue();
        return targetCount + orphanedDocumentCount;
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public void forEachTarget(Consumer<TargetData> consumer) {
        this.persistence.getTargetCache().forEachTarget(consumer);
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public void forEachOrphanedDocumentSequenceNumber(final Consumer<Long> consumer) {
        this.persistence.query("select sequence_number from target_documents group by path having COUNT(*) = 1 AND target_id = 0").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteLruReferenceDelegate$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                consumer.accept(Long.valueOf(((Cursor) obj).getLong(0)));
            }
        });
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void setInMemoryPins(ReferenceSet inMemoryPins) {
        this.inMemoryPins = inMemoryPins;
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void addReference(DocumentKey key) {
        writeSentinel(key);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeReference(DocumentKey key) {
        writeSentinel(key);
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public int removeTargets(long upperBound, SparseArray<?> activeTargetIds) {
        return this.persistence.getTargetCache().removeQueries(upperBound, activeTargetIds);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeMutationReference(DocumentKey key) {
        writeSentinel(key);
    }

    private boolean mutationQueuesContainKey(DocumentKey key) {
        return !this.persistence.query("SELECT 1 FROM document_mutations WHERE path = ?").binding(EncodedPath.encode(key.getPath())).isEmpty();
    }

    private boolean isPinned(DocumentKey key) {
        if (this.inMemoryPins.containsKey(key)) {
            return true;
        }
        return mutationQueuesContainKey(key);
    }

    private void removeSentinel(DocumentKey key) {
        this.persistence.execute("DELETE FROM target_documents WHERE path = ? AND target_id = 0", EncodedPath.encode(key.getPath()));
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public int removeOrphanedDocuments(long upperBound) {
        final int[] count = new int[1];
        boolean resultsRemaining = true;
        final List<DocumentKey> docsToRemove = new ArrayList<>();
        while (true) {
            boolean z = false;
            if (resultsRemaining) {
                int rowsProccessed = this.persistence.query("select path from target_documents group by path having COUNT(*) = 1 AND target_id = 0 AND sequence_number <= ? LIMIT ?").binding(Long.valueOf(upperBound), 100).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteLruReferenceDelegate$$ExternalSyntheticLambda1
                    @Override // com.google.firebase.firestore.util.Consumer
                    public final void accept(Object obj) {
                        this.f$0.m343x5fd3c655(count, docsToRemove, (Cursor) obj);
                    }
                });
                if (rowsProccessed == 100) {
                    z = true;
                }
                resultsRemaining = z;
            } else {
                this.persistence.getRemoteDocumentCache().removeAll(docsToRemove);
                return count[0];
            }
        }
    }

    /* JADX INFO: renamed from: lambda$removeOrphanedDocuments$2$com-google-firebase-firestore-local-SQLiteLruReferenceDelegate, reason: not valid java name */
    /* synthetic */ void m343x5fd3c655(int[] count, List docsToRemove, Cursor row) {
        ResourcePath path = EncodedPath.decodeResourcePath(row.getString(0));
        DocumentKey key = DocumentKey.fromPath(path);
        if (!isPinned(key)) {
            count[0] = count[0] + 1;
            docsToRemove.add(key);
            removeSentinel(key);
        }
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeTarget(TargetData targetData) {
        TargetData updated = targetData.withSequenceNumber(getCurrentSequenceNumber());
        this.persistence.getTargetCache().updateTargetData(updated);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void updateLimboDocument(DocumentKey key) {
        writeSentinel(key);
    }

    private void writeSentinel(DocumentKey key) {
        String path = EncodedPath.encode(key.getPath());
        this.persistence.execute("INSERT OR REPLACE INTO target_documents (target_id, path, sequence_number) VALUES (0, ?, ?)", path, Long.valueOf(getCurrentSequenceNumber()));
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public long getByteSize() {
        return this.persistence.getByteSize();
    }
}
