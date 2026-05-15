package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes10.dex */
public class LruGarbageCollector {
    private static final long INITIAL_GC_DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    private static final long REGULAR_GC_DELAY_MS = TimeUnit.MINUTES.toMillis(5);
    private final LruDelegate delegate;
    private final Params params;

    public static class Params {
        private static final long COLLECTION_DISABLED = -1;
        private static final long DEFAULT_CACHE_SIZE_BYTES = 104857600;
        private static final int DEFAULT_COLLECTION_PERCENTILE = 10;
        private static final int DEFAULT_MAX_SEQUENCE_NUMBERS_TO_COLLECT = 1000;
        final int maximumSequenceNumbersToCollect;
        long minBytesThreshold;
        int percentileToCollect;

        public static Params Default() {
            return new Params(DEFAULT_CACHE_SIZE_BYTES, 10, 1000);
        }

        public static Params Disabled() {
            return new Params(-1L, 0, 0);
        }

        public static Params WithCacheSizeBytes(long cacheSizeBytes) {
            return new Params(cacheSizeBytes, 10, 1000);
        }

        Params(long minBytesThreshold, int percentileToCollect, int maximumSequenceNumbersToCollect) {
            this.minBytesThreshold = minBytesThreshold;
            this.percentileToCollect = percentileToCollect;
            this.maximumSequenceNumbersToCollect = maximumSequenceNumbersToCollect;
        }
    }

    public static class Results {
        private final int documentsRemoved;
        private final boolean hasRun;
        private final int sequenceNumbersCollected;
        private final int targetsRemoved;

        static Results DidNotRun() {
            return new Results(false, 0, 0, 0);
        }

        Results(boolean hasRun, int sequenceNumbersCollected, int targetsRemoved, int documentsRemoved) {
            this.hasRun = hasRun;
            this.sequenceNumbersCollected = sequenceNumbersCollected;
            this.targetsRemoved = targetsRemoved;
            this.documentsRemoved = documentsRemoved;
        }

        public boolean hasRun() {
            return this.hasRun;
        }

        public int getSequenceNumbersCollected() {
            return this.sequenceNumbersCollected;
        }

        public int getTargetsRemoved() {
            return this.targetsRemoved;
        }

        public int getDocumentsRemoved() {
            return this.documentsRemoved;
        }
    }

    public class GCScheduler implements Scheduler {
        private final AsyncQueue asyncQueue;
        private AsyncQueue.DelayedTask gcTask;
        private boolean hasRun = false;
        private final LocalStore localStore;

        public GCScheduler(AsyncQueue asyncQueue, LocalStore localStore) {
            this.asyncQueue = asyncQueue;
            this.localStore = localStore;
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void start() {
            if (LruGarbageCollector.this.params.minBytesThreshold != -1) {
                scheduleGC();
            }
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void stop() {
            if (this.gcTask != null) {
                this.gcTask.cancel();
            }
        }

        private void scheduleGC() {
            long delay = this.hasRun ? LruGarbageCollector.REGULAR_GC_DELAY_MS : LruGarbageCollector.INITIAL_GC_DELAY_MS;
            this.gcTask = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.GARBAGE_COLLECTION, delay, new Runnable() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$GCScheduler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m332xb32188f8();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$scheduleGC$0$com-google-firebase-firestore-local-LruGarbageCollector$GCScheduler, reason: not valid java name */
        /* synthetic */ void m332xb32188f8() {
            this.localStore.collectGarbage(LruGarbageCollector.this);
            this.hasRun = true;
            scheduleGC();
        }
    }

    LruGarbageCollector(LruDelegate delegate, Params params) {
        this.delegate = delegate;
        this.params = params;
    }

    public GCScheduler newScheduler(AsyncQueue asyncQueue, LocalStore localStore) {
        return new GCScheduler(asyncQueue, localStore);
    }

    public LruGarbageCollector withNewThreshold(long cacheThreshold) {
        this.params.minBytesThreshold = cacheThreshold;
        this.params.percentileToCollect = 100;
        return this;
    }

    int calculateQueryCount(int percentile) {
        long targetCount = this.delegate.getSequenceNumberCount();
        return (int) ((percentile / 100.0f) * targetCount);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class RollingSequenceNumberBuffer {
        private static final Comparator<Long> COMPARATOR = new Comparator() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$RollingSequenceNumberBuffer$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Long) obj2).compareTo((Long) obj);
            }
        };
        private final int maxElements;
        private final PriorityQueue<Long> queue;

        RollingSequenceNumberBuffer(int count) {
            this.maxElements = count;
            this.queue = new PriorityQueue<>(count, COMPARATOR);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void addElement(Long sequenceNumber) {
            if (this.queue.size() < this.maxElements) {
                this.queue.add(sequenceNumber);
                return;
            }
            Long highestValue = this.queue.peek();
            if (sequenceNumber.longValue() < highestValue.longValue()) {
                this.queue.poll();
                this.queue.add(sequenceNumber);
            }
        }

        long getMaxValue() {
            return this.queue.peek().longValue();
        }
    }

    long getNthSequenceNumber(int count) {
        if (count == 0) {
            return -1L;
        }
        final RollingSequenceNumberBuffer buffer = new RollingSequenceNumberBuffer(count);
        this.delegate.forEachTarget(new Consumer() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                buffer.addElement(Long.valueOf(((TargetData) obj).getSequenceNumber()));
            }
        });
        LruDelegate lruDelegate = this.delegate;
        Objects.requireNonNull(buffer);
        lruDelegate.forEachOrphanedDocumentSequenceNumber(new Consumer() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                buffer.addElement((Long) obj);
            }
        });
        return buffer.getMaxValue();
    }

    int removeTargets(long upperBound, SparseArray<?> activeTargetIds) {
        return this.delegate.removeTargets(upperBound, activeTargetIds);
    }

    int removeOrphanedDocuments(long upperBound) {
        return this.delegate.removeOrphanedDocuments(upperBound);
    }

    Results collect(SparseArray<?> activeTargetIds) {
        if (this.params.minBytesThreshold == -1) {
            Logger.debug("LruGarbageCollector", "Garbage collection skipped; disabled", new Object[0]);
            return Results.DidNotRun();
        }
        long cacheSize = getByteSize();
        if (cacheSize < this.params.minBytesThreshold) {
            Logger.debug("LruGarbageCollector", "Garbage collection skipped; Cache size " + cacheSize + " is lower than threshold " + this.params.minBytesThreshold, new Object[0]);
            return Results.DidNotRun();
        }
        return runGarbageCollection(activeTargetIds);
    }

    private Results runGarbageCollection(SparseArray<?> liveTargetIds) {
        long startTs = System.currentTimeMillis();
        int sequenceNumbers = calculateQueryCount(this.params.percentileToCollect);
        if (sequenceNumbers > this.params.maximumSequenceNumbersToCollect) {
            Logger.debug("LruGarbageCollector", "Capping sequence numbers to collect down to the maximum of " + this.params.maximumSequenceNumbersToCollect + " from " + sequenceNumbers, new Object[0]);
            sequenceNumbers = this.params.maximumSequenceNumbersToCollect;
        }
        long countedTargetsTs = System.currentTimeMillis();
        long upperBound = getNthSequenceNumber(sequenceNumbers);
        long foundUpperBoundTs = System.currentTimeMillis();
        int numTargetsRemoved = removeTargets(upperBound, liveTargetIds);
        long removedTargetsTs = System.currentTimeMillis();
        int numDocumentsRemoved = removeOrphanedDocuments(upperBound);
        long removedDocumentsTs = System.currentTimeMillis();
        if (Logger.isDebugEnabled()) {
            String desc = "LRU Garbage Collection:\n\tCounted targets in " + (countedTargetsTs - startTs) + "ms\n";
            Logger.debug("LruGarbageCollector", (((desc + String.format(Locale.ROOT, "\tDetermined least recently used %d sequence numbers in %dms\n", Integer.valueOf(sequenceNumbers), Long.valueOf(foundUpperBoundTs - countedTargetsTs))) + String.format(Locale.ROOT, "\tRemoved %d targets in %dms\n", Integer.valueOf(numTargetsRemoved), Long.valueOf(removedTargetsTs - foundUpperBoundTs))) + String.format(Locale.ROOT, "\tRemoved %d documents in %dms\n", Integer.valueOf(numDocumentsRemoved), Long.valueOf(removedDocumentsTs - removedTargetsTs))) + String.format(Locale.ROOT, "Total Duration: %dms", Long.valueOf(removedDocumentsTs - startTs)), new Object[0]);
        }
        return new Results(true, sequenceNumbers, numTargetsRemoved, numDocumentsRemoved);
    }

    long getByteSize() {
        return this.delegate.getByteSize();
    }
}
