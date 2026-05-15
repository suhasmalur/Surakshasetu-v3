package com.google.firebase.firestore.local;

import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Supplier;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
public final class MemoryPersistence extends Persistence {
    private ReferenceDelegate referenceDelegate;
    private boolean started;
    private final Map<User, MemoryMutationQueue> mutationQueues = new HashMap();
    private final MemoryIndexManager indexManager = new MemoryIndexManager();
    private final MemoryTargetCache targetCache = new MemoryTargetCache(this);
    private final MemoryBundleCache bundleCache = new MemoryBundleCache();
    private final MemoryRemoteDocumentCache remoteDocumentCache = new MemoryRemoteDocumentCache();
    private final Map<User, MemoryDocumentOverlayCache> overlays = new HashMap();

    public static MemoryPersistence createEagerGcMemoryPersistence() {
        MemoryPersistence persistence = new MemoryPersistence();
        persistence.setReferenceDelegate(new MemoryEagerReferenceDelegate(persistence));
        return persistence;
    }

    public static MemoryPersistence createLruGcMemoryPersistence(LruGarbageCollector.Params params, LocalSerializer serializer) {
        MemoryPersistence persistence = new MemoryPersistence();
        persistence.setReferenceDelegate(new MemoryLruReferenceDelegate(persistence, params, serializer));
        return persistence;
    }

    private MemoryPersistence() {
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public void start() {
        Assert.hardAssert(!this.started, "MemoryPersistence double-started!", new Object[0]);
        this.started = true;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public void shutdown() {
        Assert.hardAssert(this.started, "MemoryPersistence shutdown without start", new Object[0]);
        this.started = false;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public boolean isStarted() {
        return this.started;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public ReferenceDelegate getReferenceDelegate() {
        return this.referenceDelegate;
    }

    private void setReferenceDelegate(ReferenceDelegate delegate) {
        this.referenceDelegate = delegate;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    MutationQueue getMutationQueue(User user, IndexManager indexManager) {
        MemoryMutationQueue queue = this.mutationQueues.get(user);
        if (queue == null) {
            MemoryMutationQueue queue2 = new MemoryMutationQueue(this, user);
            this.mutationQueues.put(user, queue2);
            return queue2;
        }
        return queue;
    }

    Iterable<MemoryMutationQueue> getMutationQueues() {
        return this.mutationQueues.values();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public MemoryTargetCache getTargetCache() {
        return this.targetCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public MemoryRemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public MemoryIndexManager getIndexManager(User user) {
        return this.indexManager;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    BundleCache getBundleCache() {
        return this.bundleCache;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    DocumentOverlayCache getDocumentOverlayCache(User user) {
        MemoryDocumentOverlayCache overlay = this.overlays.get(user);
        if (overlay == null) {
            MemoryDocumentOverlayCache overlay2 = new MemoryDocumentOverlayCache();
            this.overlays.put(user, overlay2);
            return overlay2;
        }
        return overlay;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    OverlayMigrationManager getOverlayMigrationManager() {
        return new MemoryOverlayMigrationManager();
    }

    @Override // com.google.firebase.firestore.local.Persistence
    void runTransaction(String action, Runnable operation) {
        this.referenceDelegate.onTransactionStarted();
        try {
            operation.run();
        } finally {
            this.referenceDelegate.onTransactionCommitted();
        }
    }

    @Override // com.google.firebase.firestore.local.Persistence
    <T> T runTransaction(String action, Supplier<T> operation) {
        this.referenceDelegate.onTransactionStarted();
        try {
            T result = operation.get();
            return result;
        } finally {
            this.referenceDelegate.onTransactionCommitted();
        }
    }
}
