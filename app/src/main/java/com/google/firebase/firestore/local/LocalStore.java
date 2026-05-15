package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleCallback;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.NamedQuery;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.core.TargetIdGenerator;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes10.dex */
public final class LocalStore implements BundleCallback {
    private static final long RESUME_TOKEN_MAX_AGE_SECONDS = TimeUnit.MINUTES.toSeconds(5);
    private final BundleCache bundleCache;
    private DocumentOverlayCache documentOverlayCache;
    private IndexManager indexManager;
    private LocalDocumentsView localDocuments;
    private final ReferenceSet localViewReferences;
    private MutationQueue mutationQueue;
    private final Persistence persistence;
    private final SparseArray<TargetData> queryDataByTarget;
    private final QueryEngine queryEngine;
    private final RemoteDocumentCache remoteDocuments;
    private final TargetCache targetCache;
    private final Map<Target, Integer> targetIdByTarget;
    private final TargetIdGenerator targetIdGenerator;

    public LocalStore(Persistence persistence, QueryEngine queryEngine, User initialUser) {
        Assert.hardAssert(persistence.isStarted(), "LocalStore was passed an unstarted persistence implementation", new Object[0]);
        this.persistence = persistence;
        this.queryEngine = queryEngine;
        this.targetCache = persistence.getTargetCache();
        this.bundleCache = persistence.getBundleCache();
        this.targetIdGenerator = TargetIdGenerator.forTargetCache(this.targetCache.getHighestTargetId());
        this.remoteDocuments = persistence.getRemoteDocumentCache();
        this.localViewReferences = new ReferenceSet();
        this.queryDataByTarget = new SparseArray<>();
        this.targetIdByTarget = new HashMap();
        persistence.getReferenceDelegate().setInMemoryPins(this.localViewReferences);
        initializeUserComponents(initialUser);
    }

    private void initializeUserComponents(User user) {
        this.indexManager = this.persistence.getIndexManager(user);
        this.mutationQueue = this.persistence.getMutationQueue(user, this.indexManager);
        this.documentOverlayCache = this.persistence.getDocumentOverlayCache(user);
        this.localDocuments = new LocalDocumentsView(this.remoteDocuments, this.mutationQueue, this.documentOverlayCache, this.indexManager);
        this.remoteDocuments.setIndexManager(this.indexManager);
        this.queryEngine.initialize(this.localDocuments, this.indexManager);
    }

    public void start() {
        this.persistence.getOverlayMigrationManager().run();
        startIndexManager();
        startMutationQueue();
    }

    private void startIndexManager() {
        this.persistence.runTransaction("Start IndexManager", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m329xf9470190();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$startIndexManager$0$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m329xf9470190() {
        this.indexManager.start();
    }

    private void startMutationQueue() {
        this.persistence.runTransaction("Start MutationQueue", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m330xa2ea7b8c();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$startMutationQueue$1$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m330xa2ea7b8c() {
        this.mutationQueue.start();
    }

    public IndexManager getIndexManagerForCurrentUser() {
        return this.indexManager;
    }

    public LocalDocumentsView getLocalDocumentsForCurrentUser() {
        return this.localDocuments;
    }

    public ImmutableSortedMap<DocumentKey, Document> handleUserChange(User user) {
        List<MutationBatch> oldBatches = this.mutationQueue.getAllMutationBatches();
        initializeUserComponents(user);
        startIndexManager();
        startMutationQueue();
        List<MutationBatch> newBatches = this.mutationQueue.getAllMutationBatches();
        ImmutableSortedSet<DocumentKey> changedKeys = DocumentKey.emptyKeySet();
        for (List<MutationBatch> batches : Arrays.asList(oldBatches, newBatches)) {
            for (MutationBatch batch : batches) {
                for (Mutation mutation : batch.getMutations()) {
                    changedKeys = changedKeys.insert(mutation.getKey());
                }
            }
        }
        return this.localDocuments.getDocuments(changedKeys);
    }

    public LocalDocumentsResult writeLocally(final List<Mutation> mutations) {
        final Timestamp localWriteTime = Timestamp.now();
        final Set<DocumentKey> keys = new HashSet<>();
        for (Mutation mutation : mutations) {
            keys.add(mutation.getKey());
        }
        return (LocalDocumentsResult) this.persistence.runTransaction("Locally write mutations", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda12
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m331x4f0ff958(keys, mutations, localWriteTime);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$writeLocally$2$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ LocalDocumentsResult m331x4f0ff958(Set keys, List mutations, Timestamp localWriteTime) {
        Map<DocumentKey, MutableDocument> remoteDocs = this.remoteDocuments.getAll(keys);
        Set<DocumentKey> docsWithoutRemoteVersion = new HashSet<>();
        for (Map.Entry<DocumentKey, MutableDocument> entry : remoteDocs.entrySet()) {
            if (!entry.getValue().isValidDocument()) {
                docsWithoutRemoteVersion.add(entry.getKey());
            }
        }
        Map<DocumentKey, OverlayedDocument> overlayedDocuments = this.localDocuments.getOverlayedDocuments(remoteDocs);
        List<Mutation> baseMutations = new ArrayList<>();
        Iterator it = mutations.iterator();
        while (it.hasNext()) {
            Mutation mutation = (Mutation) it.next();
            ObjectValue baseValue = mutation.extractTransformBaseValue(overlayedDocuments.get(mutation.getKey()).getDocument());
            if (baseValue != null) {
                baseMutations.add(new PatchMutation(mutation.getKey(), baseValue, baseValue.getFieldMask(), Precondition.exists(true)));
            }
        }
        MutationBatch batch = this.mutationQueue.addMutationBatch(localWriteTime, baseMutations, mutations);
        Map<DocumentKey, Mutation> overlays = batch.applyToLocalDocumentSet(overlayedDocuments, docsWithoutRemoteVersion);
        this.documentOverlayCache.saveOverlays(batch.getBatchId(), overlays);
        return LocalDocumentsResult.fromOverlayedDocuments(batch.getBatchId(), overlayedDocuments);
    }

    public ImmutableSortedMap<DocumentKey, Document> acknowledgeBatch(final MutationBatchResult batchResult) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Acknowledge batch", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda5
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m313xd0c8f3b2(batchResult);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$acknowledgeBatch$3$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ ImmutableSortedMap m313xd0c8f3b2(MutationBatchResult batchResult) {
        MutationBatch batch = batchResult.getBatch();
        this.mutationQueue.acknowledgeBatch(batch, batchResult.getStreamToken());
        applyWriteToRemoteDocuments(batchResult);
        this.mutationQueue.performConsistencyCheck();
        this.documentOverlayCache.removeOverlaysForBatchId(batchResult.getBatch().getBatchId());
        this.localDocuments.recalculateAndSaveOverlays(getKeysWithTransformResults(batchResult));
        return this.localDocuments.getDocuments(batch.getKeys());
    }

    private Set<DocumentKey> getKeysWithTransformResults(MutationBatchResult batchResult) {
        Set<DocumentKey> result = new HashSet<>();
        for (int i = 0; i < batchResult.getMutationResults().size(); i++) {
            MutationResult mutationResult = batchResult.getMutationResults().get(i);
            if (!mutationResult.getTransformResults().isEmpty()) {
                result.add(batchResult.getBatch().getMutations().get(i).getKey());
            }
        }
        return result;
    }

    public ImmutableSortedMap<DocumentKey, Document> rejectBatch(final int batchId) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Reject batch", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda9
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m324x1ab75fae(batchId);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$rejectBatch$4$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ ImmutableSortedMap m324x1ab75fae(int batchId) {
        MutationBatch toReject = this.mutationQueue.lookupMutationBatch(batchId);
        Assert.hardAssert(toReject != null, "Attempt to reject nonexistent batch!", new Object[0]);
        this.mutationQueue.removeMutationBatch(toReject);
        this.mutationQueue.performConsistencyCheck();
        this.documentOverlayCache.removeOverlaysForBatchId(batchId);
        this.localDocuments.recalculateAndSaveOverlays(toReject.getKeys());
        return this.localDocuments.getDocuments(toReject.getKeys());
    }

    public int getHighestUnacknowledgedBatchId() {
        return this.mutationQueue.getHighestUnacknowledgedBatchId();
    }

    public ByteString getLastStreamToken() {
        return this.mutationQueue.getLastStreamToken();
    }

    public void setLastStreamToken(final ByteString streamToken) {
        this.persistence.runTransaction("Set stream token", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m328x6c1835ed(streamToken);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setLastStreamToken$5$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m328x6c1835ed(ByteString streamToken) {
        this.mutationQueue.setLastStreamToken(streamToken);
    }

    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.targetCache.getLastRemoteSnapshotVersion();
    }

    public ImmutableSortedMap<DocumentKey, Document> applyRemoteEvent(final RemoteEvent remoteEvent) {
        final SnapshotVersion remoteVersion = remoteEvent.getSnapshotVersion();
        return (ImmutableSortedMap) this.persistence.runTransaction("Apply remote event", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda14
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m316xfb616aa7(remoteEvent, remoteVersion);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$applyRemoteEvent$6$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ ImmutableSortedMap m316xfb616aa7(RemoteEvent remoteEvent, SnapshotVersion remoteVersion) {
        Map<Integer, TargetChange> targetChanges = remoteEvent.getTargetChanges();
        long sequenceNumber = this.persistence.getReferenceDelegate().getCurrentSequenceNumber();
        for (Map.Entry<Integer, TargetChange> entry : targetChanges.entrySet()) {
            Integer boxedTargetId = entry.getKey();
            int targetId = boxedTargetId.intValue();
            TargetChange change = entry.getValue();
            TargetData oldTargetData = this.queryDataByTarget.get(targetId);
            if (oldTargetData != null) {
                this.targetCache.removeMatchingKeys(change.getRemovedDocuments(), targetId);
                this.targetCache.addMatchingKeys(change.getAddedDocuments(), targetId);
                TargetData newTargetData = oldTargetData.withSequenceNumber(sequenceNumber);
                if (remoteEvent.getTargetMismatches().containsKey(Integer.valueOf(targetId))) {
                    newTargetData = newTargetData.withResumeToken(ByteString.EMPTY, SnapshotVersion.NONE).withLastLimboFreeSnapshotVersion(SnapshotVersion.NONE);
                } else if (!change.getResumeToken().isEmpty()) {
                    newTargetData = newTargetData.withResumeToken(change.getResumeToken(), remoteEvent.getSnapshotVersion());
                }
                this.queryDataByTarget.put(targetId, newTargetData);
                if (shouldPersistTargetData(oldTargetData, newTargetData, change)) {
                    this.targetCache.updateTargetData(newTargetData);
                }
            }
        }
        Map<DocumentKey, MutableDocument> documentUpdates = remoteEvent.getDocumentUpdates();
        Set<DocumentKey> limboDocuments = remoteEvent.getResolvedLimboDocuments();
        for (DocumentKey key : documentUpdates.keySet()) {
            if (limboDocuments.contains(key)) {
                this.persistence.getReferenceDelegate().updateLimboDocument(key);
            }
        }
        DocumentChangeResult result = populateDocumentChanges(documentUpdates);
        Map<DocumentKey, MutableDocument> changedDocs = result.changedDocuments;
        SnapshotVersion lastRemoteVersion = this.targetCache.getLastRemoteSnapshotVersion();
        if (!remoteVersion.equals(SnapshotVersion.NONE)) {
            Assert.hardAssert(remoteVersion.compareTo(lastRemoteVersion) >= 0, "Watch stream reverted to previous snapshot?? (%s < %s)", remoteVersion, lastRemoteVersion);
            this.targetCache.setLastRemoteSnapshotVersion(remoteVersion);
        }
        return this.localDocuments.getLocalViewOfDocuments(changedDocs, result.existenceChangedKeys);
    }

    private static class DocumentChangeResult {
        private final Map<DocumentKey, MutableDocument> changedDocuments;
        private final Set<DocumentKey> existenceChangedKeys;

        private DocumentChangeResult(Map<DocumentKey, MutableDocument> changedDocuments, Set<DocumentKey> existenceChangedKeys) {
            this.changedDocuments = changedDocuments;
            this.existenceChangedKeys = existenceChangedKeys;
        }
    }

    private DocumentChangeResult populateDocumentChanges(Map<DocumentKey, MutableDocument> documents) {
        Map<DocumentKey, MutableDocument> changedDocs = new HashMap<>();
        List<DocumentKey> removedDocs = new ArrayList<>();
        Set<DocumentKey> conditionChanged = new HashSet<>();
        Map<DocumentKey, MutableDocument> existingDocs = this.remoteDocuments.getAll(documents.keySet());
        for (Map.Entry<DocumentKey, MutableDocument> entry : documents.entrySet()) {
            DocumentKey key = entry.getKey();
            MutableDocument doc = entry.getValue();
            MutableDocument existingDoc = existingDocs.get(key);
            if (doc.isFoundDocument() != existingDoc.isFoundDocument()) {
                conditionChanged.add(key);
            }
            if (doc.isNoDocument() && doc.getVersion().equals(SnapshotVersion.NONE)) {
                removedDocs.add(doc.getKey());
                changedDocs.put(key, doc);
            } else if (!existingDoc.isValidDocument() || doc.getVersion().compareTo(existingDoc.getVersion()) > 0 || (doc.getVersion().compareTo(existingDoc.getVersion()) == 0 && existingDoc.hasPendingWrites())) {
                Assert.hardAssert(!SnapshotVersion.NONE.equals(doc.getReadTime()), "Cannot add a document when the remote version is zero", new Object[0]);
                this.remoteDocuments.add(doc, doc.getReadTime());
                changedDocs.put(key, doc);
            } else {
                Logger.debug("LocalStore", "Ignoring outdated watch update for %s.Current version: %s  Watch version: %s", key, existingDoc.getVersion(), doc.getVersion());
            }
        }
        this.remoteDocuments.removeAll(removedDocs);
        return new DocumentChangeResult(changedDocs, conditionChanged);
    }

    private static boolean shouldPersistTargetData(TargetData oldTargetData, TargetData newTargetData, TargetChange change) {
        if (oldTargetData.getResumeToken().isEmpty()) {
            return true;
        }
        long newSeconds = newTargetData.getSnapshotVersion().getTimestamp().getSeconds();
        long oldSeconds = oldTargetData.getSnapshotVersion().getTimestamp().getSeconds();
        long timeDelta = newSeconds - oldSeconds;
        if (timeDelta >= RESUME_TOKEN_MAX_AGE_SECONDS) {
            return true;
        }
        long newLimboFreeSeconds = newTargetData.getLastLimboFreeSnapshotVersion().getTimestamp().getSeconds();
        long oldLimboFreeSeconds = oldTargetData.getLastLimboFreeSnapshotVersion().getTimestamp().getSeconds();
        long limboFreeTimeDelta = newLimboFreeSeconds - oldLimboFreeSeconds;
        if (limboFreeTimeDelta >= RESUME_TOKEN_MAX_AGE_SECONDS) {
            return true;
        }
        if (change == null) {
            return false;
        }
        int changes = change.getAddedDocuments().size() + change.getModifiedDocuments().size() + change.getRemovedDocuments().size();
        return changes > 0;
    }

    public void notifyLocalViewChanges(final List<LocalViewChanges> viewChanges) {
        this.persistence.runTransaction("notifyLocalViewChanges", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m323x6c8bed50(viewChanges);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$notifyLocalViewChanges$7$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m323x6c8bed50(List viewChanges) {
        Iterator it = viewChanges.iterator();
        while (it.hasNext()) {
            LocalViewChanges viewChange = (LocalViewChanges) it.next();
            int targetId = viewChange.getTargetId();
            this.localViewReferences.addReferences(viewChange.getAdded(), targetId);
            ImmutableSortedSet<DocumentKey> removed = viewChange.getRemoved();
            for (DocumentKey key : removed) {
                this.persistence.getReferenceDelegate().removeReference(key);
            }
            this.localViewReferences.removeReferences(removed, targetId);
            if (!viewChange.isFromCache()) {
                TargetData targetData = this.queryDataByTarget.get(targetId);
                Assert.hardAssert(targetData != null, "Can't set limbo-free snapshot version for unknown target: %s", Integer.valueOf(targetId));
                SnapshotVersion lastLimboFreeSnapshotVersion = targetData.getSnapshotVersion();
                TargetData updatedTargetData = targetData.withLastLimboFreeSnapshotVersion(lastLimboFreeSnapshotVersion);
                this.queryDataByTarget.put(targetId, updatedTargetData);
                if (shouldPersistTargetData(targetData, updatedTargetData, null)) {
                    this.targetCache.updateTargetData(updatedTargetData);
                }
            }
        }
    }

    public MutationBatch getNextMutationBatch(int afterBatchId) {
        return this.mutationQueue.getNextMutationBatchAfterBatchId(afterBatchId);
    }

    public Document readDocument(DocumentKey key) {
        return this.localDocuments.getDocument(key);
    }

    public TargetData allocateTarget(final Target target) {
        int targetId;
        TargetData cached = this.targetCache.getTargetData(target);
        if (cached != null) {
            targetId = cached.getTargetId();
        } else {
            final AllocateQueryHolder holder = new AllocateQueryHolder();
            this.persistence.runTransaction("Allocate target", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m314x5de91a9d(holder, target);
                }
            });
            int targetId2 = holder.targetId;
            cached = holder.cached;
            targetId = targetId2;
        }
        if (this.queryDataByTarget.get(targetId) == null) {
            this.queryDataByTarget.put(targetId, cached);
            this.targetIdByTarget.put(target, Integer.valueOf(targetId));
        }
        return cached;
    }

    /* JADX INFO: renamed from: lambda$allocateTarget$8$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m314x5de91a9d(AllocateQueryHolder holder, Target target) {
        holder.targetId = this.targetIdGenerator.nextId();
        holder.cached = new TargetData(target, holder.targetId, this.persistence.getReferenceDelegate().getCurrentSequenceNumber(), QueryPurpose.LISTEN);
        this.targetCache.addTargetData(holder.cached);
    }

    TargetData getTargetData(Target target) {
        Integer targetId = this.targetIdByTarget.get(target);
        if (targetId != null) {
            return this.queryDataByTarget.get(targetId.intValue());
        }
        return this.targetCache.getTargetData(target);
    }

    public boolean hasNewerBundle(final BundleMetadata bundleMetadata) {
        return ((Boolean) this.persistence.runTransaction("Has newer bundle", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda17
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m322x622d6455(bundleMetadata);
            }
        })).booleanValue();
    }

    /* JADX INFO: renamed from: lambda$hasNewerBundle$9$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ Boolean m322x622d6455(BundleMetadata bundleMetadata) {
        BundleMetadata cachedMetadata = this.bundleCache.getBundleMetadata(bundleMetadata.getBundleId());
        return Boolean.valueOf(cachedMetadata != null && cachedMetadata.getCreateTime().compareTo(bundleMetadata.getCreateTime()) >= 0);
    }

    @Override // com.google.firebase.firestore.bundle.BundleCallback
    public void saveBundle(final BundleMetadata bundleMetadata) {
        this.persistence.runTransaction("Save bundle", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m326x98d21eb5(bundleMetadata);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$saveBundle$10$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m326x98d21eb5(BundleMetadata bundleMetadata) {
        this.bundleCache.saveBundleMetadata(bundleMetadata);
    }

    @Override // com.google.firebase.firestore.bundle.BundleCallback
    public ImmutableSortedMap<DocumentKey, Document> applyBundledDocuments(final ImmutableSortedMap<DocumentKey, MutableDocument> documents, String bundleId) {
        final TargetData umbrellaTargetData = allocateTarget(newUmbrellaTarget(bundleId));
        return (ImmutableSortedMap) this.persistence.runTransaction("Apply bundle documents", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda15
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m315xda7cc59f(documents, umbrellaTargetData);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$applyBundledDocuments$11$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ ImmutableSortedMap m315xda7cc59f(ImmutableSortedMap documents, TargetData umbrellaTargetData) {
        ImmutableSortedSet<DocumentKey> documentKeys = DocumentKey.emptyKeySet();
        Map<DocumentKey, MutableDocument> documentMap = new HashMap<>();
        Iterator it = documents.iterator();
        while (it.hasNext()) {
            Map.Entry<DocumentKey, MutableDocument> entry = (Map.Entry) it.next();
            DocumentKey documentKey = entry.getKey();
            MutableDocument document = entry.getValue();
            if (document.isFoundDocument()) {
                documentKeys = documentKeys.insert(documentKey);
            }
            documentMap.put(documentKey, document);
        }
        this.targetCache.removeMatchingKeysForTargetId(umbrellaTargetData.getTargetId());
        this.targetCache.addMatchingKeys(documentKeys, umbrellaTargetData.getTargetId());
        DocumentChangeResult result = populateDocumentChanges(documentMap);
        Map<DocumentKey, MutableDocument> changedDocs = result.changedDocuments;
        return this.localDocuments.getLocalViewOfDocuments(changedDocs, result.existenceChangedKeys);
    }

    @Override // com.google.firebase.firestore.bundle.BundleCallback
    public void saveNamedQuery(final NamedQuery namedQuery, final ImmutableSortedSet<DocumentKey> documentKeys) {
        final TargetData existingTargetData = allocateTarget(namedQuery.getBundledQuery().getTarget());
        final int targetId = existingTargetData.getTargetId();
        this.persistence.runTransaction("Saved named query", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m327x3730c4c0(namedQuery, existingTargetData, targetId, documentKeys);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$saveNamedQuery$12$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m327x3730c4c0(NamedQuery namedQuery, TargetData existingTargetData, int targetId, ImmutableSortedSet documentKeys) {
        if (namedQuery.getReadTime().compareTo(existingTargetData.getSnapshotVersion()) > 0) {
            TargetData newTargetData = existingTargetData.withResumeToken(ByteString.EMPTY, namedQuery.getReadTime());
            this.queryDataByTarget.append(targetId, newTargetData);
            this.targetCache.updateTargetData(newTargetData);
            this.targetCache.removeMatchingKeysForTargetId(targetId);
            this.targetCache.addMatchingKeys(documentKeys, targetId);
        }
        this.bundleCache.saveNamedQuery(namedQuery);
    }

    public NamedQuery getNamedQuery(final String queryName) {
        return (NamedQuery) this.persistence.runTransaction("Get named query", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m321x4cee2ade(queryName);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getNamedQuery$13$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ NamedQuery m321x4cee2ade(String queryName) {
        return this.bundleCache.getNamedQuery(queryName);
    }

    Collection<FieldIndex> getFieldIndexes() {
        return (Collection) this.persistence.runTransaction("Get indexes", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda20
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m320x64aa3c54();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getFieldIndexes$14$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ Collection m320x64aa3c54() {
        return this.indexManager.getFieldIndexes();
    }

    public void configureFieldIndexes(final List<FieldIndex> newFieldIndexes) {
        this.persistence.runTransaction("Configure indexes", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m318x4fad3f83(newFieldIndexes);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$configureFieldIndexes$15$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m318x4fad3f83(List newFieldIndexes) {
        Collection<FieldIndex> fieldIndexes = this.indexManager.getFieldIndexes();
        Comparator<FieldIndex> comparator = FieldIndex.SEMANTIC_COMPARATOR;
        final IndexManager indexManager = this.indexManager;
        Objects.requireNonNull(indexManager);
        Consumer consumer = new Consumer() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                indexManager.addFieldIndex((FieldIndex) obj);
            }
        };
        final IndexManager indexManager2 = this.indexManager;
        Objects.requireNonNull(indexManager2);
        Util.diffCollections(fieldIndexes, newFieldIndexes, comparator, consumer, new Consumer() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda11
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                indexManager2.deleteFieldIndex((FieldIndex) obj);
            }
        });
    }

    public void deleteAllFieldIndexes() {
        this.persistence.runTransaction("Delete All Indexes", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m319x39821a72();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$deleteAllFieldIndexes$16$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m319x39821a72() {
        this.indexManager.deleteAllFieldIndexes();
    }

    public void setIndexAutoCreationEnabled(boolean isEnabled) {
        this.queryEngine.setIndexAutoCreationEnabled(isEnabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class AllocateQueryHolder {
        TargetData cached;
        int targetId;

        private AllocateQueryHolder() {
        }
    }

    public void releaseTarget(final int targetId) {
        this.persistence.runTransaction("Release target", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m325x57c7cf0d(targetId);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$releaseTarget$17$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m325x57c7cf0d(int targetId) {
        TargetData targetData = this.queryDataByTarget.get(targetId);
        Assert.hardAssert(targetData != null, "Tried to release nonexistent target: %s", Integer.valueOf(targetId));
        ImmutableSortedSet<DocumentKey> removedReferences = this.localViewReferences.removeReferencesForId(targetId);
        for (DocumentKey key : removedReferences) {
            this.persistence.getReferenceDelegate().removeReference(key);
        }
        this.persistence.getReferenceDelegate().removeTarget(targetData);
        this.queryDataByTarget.remove(targetId);
        this.targetIdByTarget.remove(targetData.getTarget());
    }

    public QueryResult executeQuery(Query query, boolean usePreviousResults) {
        TargetData targetData = getTargetData(query.toTarget());
        SnapshotVersion lastLimboFreeSnapshotVersion = SnapshotVersion.NONE;
        ImmutableSortedSet<DocumentKey> remoteKeys = DocumentKey.emptyKeySet();
        if (targetData != null) {
            lastLimboFreeSnapshotVersion = targetData.getLastLimboFreeSnapshotVersion();
            remoteKeys = this.targetCache.getMatchingKeysForTargetId(targetData.getTargetId());
        }
        ImmutableSortedMap<DocumentKey, Document> documents = this.queryEngine.getDocumentsMatchingQuery(query, usePreviousResults ? lastLimboFreeSnapshotVersion : SnapshotVersion.NONE, remoteKeys);
        return new QueryResult(documents, remoteKeys);
    }

    public ImmutableSortedSet<DocumentKey> getRemoteDocumentKeys(int targetId) {
        return this.targetCache.getMatchingKeysForTargetId(targetId);
    }

    private void applyWriteToRemoteDocuments(MutationBatchResult batchResult) {
        MutationBatch batch = batchResult.getBatch();
        Set<DocumentKey> docKeys = batch.getKeys();
        for (DocumentKey docKey : docKeys) {
            MutableDocument doc = this.remoteDocuments.get(docKey);
            SnapshotVersion ackVersion = batchResult.getDocVersions().get(docKey);
            Assert.hardAssert(ackVersion != null, "docVersions should contain every doc in the write.", new Object[0]);
            if (doc.getVersion().compareTo(ackVersion) < 0) {
                batch.applyToRemoteDocument(doc, batchResult);
                if (doc.isValidDocument()) {
                    this.remoteDocuments.add(doc, batchResult.getCommitVersion());
                }
            }
        }
        this.mutationQueue.removeMutationBatch(batch);
    }

    public LruGarbageCollector.Results collectGarbage(final LruGarbageCollector garbageCollector) {
        return (LruGarbageCollector.Results) this.persistence.runTransaction("Collect garbage", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda13
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m317x577feb79(garbageCollector);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$collectGarbage$18$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ LruGarbageCollector.Results m317x577feb79(LruGarbageCollector garbageCollector) {
        return garbageCollector.collect(this.queryDataByTarget);
    }

    private static Target newUmbrellaTarget(String bundleName) {
        return Query.atPath(ResourcePath.fromString("__bundle__/docs/" + bundleName)).toTarget();
    }
}
