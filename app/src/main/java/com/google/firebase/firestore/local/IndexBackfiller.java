package com.google.firebase.firestore.local;

import com.google.common.base.Supplier;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Logger;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes10.dex */
public class IndexBackfiller {
    private static final String LOG_TAG = "IndexBackfiller";
    private static final int MAX_DOCUMENTS_TO_PROCESS = 50;
    private final Supplier<IndexManager> indexManagerOfCurrentUser;
    private final Supplier<LocalDocumentsView> localDocumentsViewOfCurrentUser;
    private int maxDocumentsToProcess;
    private final Persistence persistence;
    private final Scheduler scheduler;
    private static final long INITIAL_BACKFILL_DELAY_MS = TimeUnit.SECONDS.toMillis(15);
    private static final long REGULAR_BACKFILL_DELAY_MS = TimeUnit.MINUTES.toMillis(1);

    public IndexBackfiller(Persistence persistence, AsyncQueue asyncQueue, final LocalStore localStore) {
        Objects.requireNonNull(localStore);
        Supplier supplier = new Supplier() { // from class: com.google.firebase.firestore.local.IndexBackfiller$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return localStore.getIndexManagerForCurrentUser();
            }
        };
        Objects.requireNonNull(localStore);
        this(persistence, asyncQueue, supplier, new Supplier() { // from class: com.google.firebase.firestore.local.IndexBackfiller$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return localStore.getLocalDocumentsForCurrentUser();
            }
        });
    }

    public IndexBackfiller(Persistence persistence, AsyncQueue asyncQueue, Supplier<IndexManager> indexManagerOfCurrentUser, Supplier<LocalDocumentsView> localDocumentsViewOfCurrentUser) {
        this.maxDocumentsToProcess = 50;
        this.persistence = persistence;
        this.scheduler = new Scheduler(asyncQueue);
        this.indexManagerOfCurrentUser = indexManagerOfCurrentUser;
        this.localDocumentsViewOfCurrentUser = localDocumentsViewOfCurrentUser;
    }

    public class Scheduler implements com.google.firebase.firestore.local.Scheduler {
        private final AsyncQueue asyncQueue;
        private AsyncQueue.DelayedTask backfillTask;

        public Scheduler(AsyncQueue asyncQueue) {
            this.asyncQueue = asyncQueue;
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void start() {
            scheduleBackfill(IndexBackfiller.INITIAL_BACKFILL_DELAY_MS);
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void stop() {
            if (this.backfillTask != null) {
                this.backfillTask.cancel();
            }
        }

        private void scheduleBackfill(long delay) {
            this.backfillTask = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.INDEX_BACKFILL, delay, new Runnable() { // from class: com.google.firebase.firestore.local.IndexBackfiller$Scheduler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m312x4c716e24();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$scheduleBackfill$0$com-google-firebase-firestore-local-IndexBackfiller$Scheduler, reason: not valid java name */
        /* synthetic */ void m312x4c716e24() {
            int documentsProcessed = IndexBackfiller.this.backfill();
            Logger.debug(IndexBackfiller.LOG_TAG, "Documents written: %s", Integer.valueOf(documentsProcessed));
            scheduleBackfill(IndexBackfiller.REGULAR_BACKFILL_DELAY_MS);
        }
    }

    public Scheduler getScheduler() {
        return this.scheduler;
    }

    public int backfill() {
        return ((Integer) this.persistence.runTransaction("Backfill Indexes", new com.google.firebase.firestore.util.Supplier() { // from class: com.google.firebase.firestore.local.IndexBackfiller$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m311x14d875f6();
            }
        })).intValue();
    }

    /* JADX INFO: renamed from: lambda$backfill$0$com-google-firebase-firestore-local-IndexBackfiller, reason: not valid java name */
    /* synthetic */ Integer m311x14d875f6() {
        return Integer.valueOf(writeIndexEntries());
    }

    private int writeIndexEntries() {
        IndexManager indexManager = this.indexManagerOfCurrentUser.get();
        Set<String> processedCollectionGroups = new HashSet<>();
        int documentsRemaining = this.maxDocumentsToProcess;
        while (documentsRemaining > 0) {
            String collectionGroup = indexManager.getNextCollectionGroupToUpdate();
            if (collectionGroup == null || processedCollectionGroups.contains(collectionGroup)) {
                break;
            }
            Logger.debug(LOG_TAG, "Processing collection: %s", collectionGroup);
            documentsRemaining -= writeEntriesForCollectionGroup(collectionGroup, documentsRemaining);
            processedCollectionGroups.add(collectionGroup);
        }
        return this.maxDocumentsToProcess - documentsRemaining;
    }

    private int writeEntriesForCollectionGroup(String collectionGroup, int documentsRemainingUnderCap) {
        IndexManager indexManager = this.indexManagerOfCurrentUser.get();
        LocalDocumentsView localDocumentsView = this.localDocumentsViewOfCurrentUser.get();
        FieldIndex.IndexOffset existingOffset = indexManager.getMinOffset(collectionGroup);
        LocalDocumentsResult nextBatch = localDocumentsView.getNextDocuments(collectionGroup, existingOffset, documentsRemainingUnderCap);
        indexManager.updateIndexEntries(nextBatch.getDocuments());
        FieldIndex.IndexOffset newOffset = getNewOffset(existingOffset, nextBatch);
        Logger.debug(LOG_TAG, "Updating offset: %s", newOffset);
        indexManager.updateCollectionGroup(collectionGroup, newOffset);
        return nextBatch.getDocuments().size();
    }

    private FieldIndex.IndexOffset getNewOffset(FieldIndex.IndexOffset existingOffset, LocalDocumentsResult lookupResult) {
        FieldIndex.IndexOffset maxOffset = existingOffset;
        for (Map.Entry<DocumentKey, Document> entry : lookupResult.getDocuments()) {
            FieldIndex.IndexOffset newOffset = FieldIndex.IndexOffset.fromDocument(entry.getValue());
            if (newOffset.compareTo(maxOffset) > 0) {
                maxOffset = newOffset;
            }
        }
        return FieldIndex.IndexOffset.create(maxOffset.getReadTime(), maxOffset.getDocumentKey(), Math.max(lookupResult.getBatchId(), existingOffset.getLargestBatchId()));
    }

    void setMaxDocumentsToProcess(int newMax) {
        this.maxDocumentsToProcess = newMax;
    }
}
