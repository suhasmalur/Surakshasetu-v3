package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.remote.WatchChangeAggregator;
import com.google.firebase.firestore.util.Preconditions;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes10.dex */
final class TestingHooks {
    private static final TestingHooks instance = new TestingHooks();
    private final CopyOnWriteArrayList<AtomicReference<ExistenceFilterMismatchListener>> existenceFilterMismatchListeners = new CopyOnWriteArrayList<>();

    interface ExistenceFilterMismatchListener {
        void onExistenceFilterMismatch(ExistenceFilterMismatchInfo existenceFilterMismatchInfo);
    }

    private TestingHooks() {
    }

    static TestingHooks getInstance() {
        return instance;
    }

    void notifyOnExistenceFilterMismatch(ExistenceFilterMismatchInfo info) {
        for (AtomicReference<ExistenceFilterMismatchListener> listenerRef : this.existenceFilterMismatchListeners) {
            ExistenceFilterMismatchListener listener = listenerRef.get();
            if (listener != null) {
                listener.onExistenceFilterMismatch(info);
            }
        }
    }

    ListenerRegistration addExistenceFilterMismatchListener(ExistenceFilterMismatchListener listener) {
        Preconditions.checkNotNull(listener, "a null listener is not allowed");
        final AtomicReference<ExistenceFilterMismatchListener> listenerRef = new AtomicReference<>(listener);
        this.existenceFilterMismatchListeners.add(listenerRef);
        return new ListenerRegistration() { // from class: com.google.firebase.firestore.remote.TestingHooks$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.ListenerRegistration
            public final void remove() {
                this.f$0.m396xc9d8f707(listenerRef);
            }
        };
    }

    /* JADX INFO: renamed from: lambda$addExistenceFilterMismatchListener$0$com-google-firebase-firestore-remote-TestingHooks, reason: not valid java name */
    /* synthetic */ void m396xc9d8f707(AtomicReference listenerRef) {
        listenerRef.set(null);
        this.existenceFilterMismatchListeners.remove(listenerRef);
    }

    static abstract class ExistenceFilterMismatchInfo {
        abstract ExistenceFilterBloomFilterInfo bloomFilter();

        abstract String databaseId();

        abstract int existenceFilterCount();

        abstract int localCacheCount();

        abstract String projectId();

        ExistenceFilterMismatchInfo() {
        }

        static ExistenceFilterMismatchInfo create(int localCacheCount, int existenceFilterCount, String projectId, String databaseId, ExistenceFilterBloomFilterInfo bloomFilter) {
            return new AutoValue_TestingHooks_ExistenceFilterMismatchInfo(localCacheCount, existenceFilterCount, projectId, databaseId, bloomFilter);
        }

        static ExistenceFilterMismatchInfo from(int localCacheCount, ExistenceFilter existenceFilter, DatabaseId databaseId, BloomFilter bloomFilter, WatchChangeAggregator.BloomFilterApplicationStatus bloomFilterStatus) {
            return create(localCacheCount, existenceFilter.getCount(), databaseId.getProjectId(), databaseId.getDatabaseId(), ExistenceFilterBloomFilterInfo.from(bloomFilter, bloomFilterStatus, existenceFilter));
        }
    }

    static abstract class ExistenceFilterBloomFilterInfo {
        abstract boolean applied();

        abstract int bitmapLength();

        abstract BloomFilter bloomFilter();

        abstract int hashCount();

        abstract int padding();

        ExistenceFilterBloomFilterInfo() {
        }

        static ExistenceFilterBloomFilterInfo create(BloomFilter bloomFilter, boolean applied, int hashCount, int bitmapLength, int padding) {
            return new AutoValue_TestingHooks_ExistenceFilterBloomFilterInfo(bloomFilter, applied, hashCount, bitmapLength, padding);
        }

        static ExistenceFilterBloomFilterInfo from(BloomFilter bloomFilter, WatchChangeAggregator.BloomFilterApplicationStatus bloomFilterStatus, ExistenceFilter existenceFilter) {
            com.google.firestore.v1.BloomFilter unchangedNames = existenceFilter.getUnchangedNames();
            if (unchangedNames == null) {
                return null;
            }
            return create(bloomFilter, bloomFilterStatus == WatchChangeAggregator.BloomFilterApplicationStatus.SUCCESS, unchangedNames.getHashCount(), unchangedNames.getBits().getBitmap().size(), unchangedNames.getBits().getPadding());
        }
    }
}
