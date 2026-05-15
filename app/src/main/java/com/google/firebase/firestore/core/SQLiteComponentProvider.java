package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalSerializer;
import com.google.firebase.firestore.local.LruDelegate;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.remote.RemoteSerializer;

/* JADX INFO: loaded from: classes10.dex */
public class SQLiteComponentProvider extends MemoryComponentProvider {
    @Override // com.google.firebase.firestore.core.MemoryComponentProvider, com.google.firebase.firestore.core.ComponentProvider
    protected Scheduler createGarbageCollectionScheduler(ComponentProvider.Configuration configuration) {
        LruDelegate lruDelegate = ((SQLitePersistence) getPersistence()).getReferenceDelegate();
        LruGarbageCollector gc = lruDelegate.getGarbageCollector();
        return gc.newScheduler(configuration.getAsyncQueue(), getLocalStore());
    }

    @Override // com.google.firebase.firestore.core.MemoryComponentProvider, com.google.firebase.firestore.core.ComponentProvider
    protected IndexBackfiller createIndexBackfiller(ComponentProvider.Configuration configuration) {
        return new IndexBackfiller(getPersistence(), configuration.getAsyncQueue(), getLocalStore());
    }

    @Override // com.google.firebase.firestore.core.MemoryComponentProvider, com.google.firebase.firestore.core.ComponentProvider
    protected Persistence createPersistence(ComponentProvider.Configuration configuration) {
        LocalSerializer serializer = new LocalSerializer(new RemoteSerializer(configuration.getDatabaseInfo().getDatabaseId()));
        LruGarbageCollector.Params params = LruGarbageCollector.Params.WithCacheSizeBytes(configuration.getSettings().getCacheSizeBytes());
        return new SQLitePersistence(configuration.getContext(), configuration.getDatabaseInfo().getPersistenceKey(), configuration.getDatabaseInfo().getDatabaseId(), serializer, params);
    }
}
