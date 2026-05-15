package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.TransactionOptions;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleElement;
import com.google.firebase.firestore.bundle.BundleLoader;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.BundleReader;
import com.google.firebase.firestore.core.View;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.local.LocalDocumentsResult;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.LocalViewChanges;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.firestore.local.ReferenceSet;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.Value;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes10.dex */
public class SyncEngine implements RemoteStore.RemoteStoreCallback {
    private static final String TAG = SyncEngine.class.getSimpleName();
    private User currentUser;
    private final LocalStore localStore;
    private final int maxConcurrentLimboResolutions;
    private final RemoteStore remoteStore;
    private SyncEngineCallback syncEngineListener;
    private final Map<Query, QueryView> queryViewsByQuery = new HashMap();
    private final Map<Integer, List<Query>> queriesByTarget = new HashMap();
    private final LinkedHashSet<DocumentKey> enqueuedLimboResolutions = new LinkedHashSet<>();
    private final Map<DocumentKey, Integer> activeLimboTargetsByKey = new HashMap();
    private final Map<Integer, LimboResolution> activeLimboResolutionsByTarget = new HashMap();
    private final ReferenceSet limboDocumentRefs = new ReferenceSet();
    private final Map<User, Map<Integer, TaskCompletionSource<Void>>> mutationUserCallbacks = new HashMap();
    private final TargetIdGenerator targetIdGenerator = TargetIdGenerator.forSyncEngine();
    private final Map<Integer, List<TaskCompletionSource<Void>>> pendingWritesCallbacks = new HashMap();

    interface SyncEngineCallback {
        void handleOnlineStateChange(OnlineState onlineState);

        void onError(Query query, Status status);

        void onViewSnapshots(List<ViewSnapshot> list);
    }

    private static class LimboResolution {
        private final DocumentKey key;
        private boolean receivedDocument;

        LimboResolution(DocumentKey key) {
            this.key = key;
        }
    }

    public SyncEngine(LocalStore localStore, RemoteStore remoteStore, User initialUser, int maxConcurrentLimboResolutions) {
        this.localStore = localStore;
        this.remoteStore = remoteStore;
        this.maxConcurrentLimboResolutions = maxConcurrentLimboResolutions;
        this.currentUser = initialUser;
    }

    public void setCallback(SyncEngineCallback callback) {
        this.syncEngineListener = callback;
    }

    private void assertCallback(String method) {
        Assert.hardAssert(this.syncEngineListener != null, "Trying to call %s before setting callback", method);
    }

    public int listen(Query query) {
        assertCallback("listen");
        Assert.hardAssert(!this.queryViewsByQuery.containsKey(query), "We already listen to query: %s", query);
        TargetData targetData = this.localStore.allocateTarget(query.toTarget());
        ViewSnapshot viewSnapshot = initializeViewAndComputeSnapshot(query, targetData.getTargetId(), targetData.getResumeToken());
        this.syncEngineListener.onViewSnapshots(Collections.singletonList(viewSnapshot));
        this.remoteStore.listen(targetData);
        return targetData.getTargetId();
    }

    private ViewSnapshot initializeViewAndComputeSnapshot(Query query, int targetId, ByteString resumeToken) {
        QueryResult queryResult = this.localStore.executeQuery(query, true);
        ViewSnapshot.SyncState currentTargetSyncState = ViewSnapshot.SyncState.NONE;
        if (this.queriesByTarget.get(Integer.valueOf(targetId)) != null) {
            Query mirrorQuery = this.queriesByTarget.get(Integer.valueOf(targetId)).get(0);
            currentTargetSyncState = this.queryViewsByQuery.get(mirrorQuery).getView().getSyncState();
        }
        TargetChange synthesizedCurrentChange = TargetChange.createSynthesizedTargetChangeForCurrentChange(currentTargetSyncState == ViewSnapshot.SyncState.SYNCED, resumeToken);
        View view = new View(query, queryResult.getRemoteKeys());
        View.DocumentChanges viewDocChanges = view.computeDocChanges(queryResult.getDocuments());
        ViewChange viewChange = view.applyChanges(viewDocChanges, synthesizedCurrentChange);
        updateTrackedLimboDocuments(viewChange.getLimboChanges(), targetId);
        QueryView queryView = new QueryView(query, targetId, view);
        this.queryViewsByQuery.put(query, queryView);
        if (!this.queriesByTarget.containsKey(Integer.valueOf(targetId))) {
            this.queriesByTarget.put(Integer.valueOf(targetId), new ArrayList(1));
        }
        this.queriesByTarget.get(Integer.valueOf(targetId)).add(query);
        return viewChange.getSnapshot();
    }

    void stopListening(Query query) {
        assertCallback("stopListening");
        QueryView queryView = this.queryViewsByQuery.get(query);
        Assert.hardAssert(queryView != null, "Trying to stop listening to a query not found", new Object[0]);
        this.queryViewsByQuery.remove(query);
        int targetId = queryView.getTargetId();
        List<Query> targetQueries = this.queriesByTarget.get(Integer.valueOf(targetId));
        targetQueries.remove(query);
        if (targetQueries.isEmpty()) {
            this.localStore.releaseTarget(targetId);
            this.remoteStore.stopListening(targetId);
            removeAndCleanupTarget(targetId, Status.OK);
        }
    }

    public void writeMutations(List<Mutation> mutations, TaskCompletionSource<Void> userTask) {
        assertCallback("writeMutations");
        LocalDocumentsResult result = this.localStore.writeLocally(mutations);
        addUserCallback(result.getBatchId(), userTask);
        emitNewSnapsAndNotifyLocalStore(result.getDocuments(), null);
        this.remoteStore.fillWritePipeline();
    }

    private void addUserCallback(int batchId, TaskCompletionSource<Void> userTask) {
        Map<Integer, TaskCompletionSource<Void>> userTasks = this.mutationUserCallbacks.get(this.currentUser);
        if (userTasks == null) {
            userTasks = new HashMap();
            this.mutationUserCallbacks.put(this.currentUser, userTasks);
        }
        userTasks.put(Integer.valueOf(batchId), userTask);
    }

    public <TResult> Task<TResult> transaction(AsyncQueue asyncQueue, TransactionOptions options, Function<Transaction, Task<TResult>> updateFunction) {
        return new TransactionRunner(asyncQueue, this.remoteStore, options, updateFunction).run();
    }

    public Task<Map<String, Value>> runAggregateQuery(Query query, List<AggregateField> aggregateFields) {
        return this.remoteStore.runAggregateQuery(query, aggregateFields);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleRemoteEvent(RemoteEvent event) {
        assertCallback("handleRemoteEvent");
        for (Map.Entry<Integer, TargetChange> entry : event.getTargetChanges().entrySet()) {
            Integer targetId = entry.getKey();
            TargetChange targetChange = entry.getValue();
            LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(targetId);
            if (limboResolution != null) {
                Assert.hardAssert((targetChange.getAddedDocuments().size() + targetChange.getModifiedDocuments().size()) + targetChange.getRemovedDocuments().size() <= 1, "Limbo resolution for single document contains multiple changes.", new Object[0]);
                if (targetChange.getAddedDocuments().size() > 0) {
                    limboResolution.receivedDocument = true;
                } else if (targetChange.getModifiedDocuments().size() > 0) {
                    Assert.hardAssert(limboResolution.receivedDocument, "Received change for limbo target document without add.", new Object[0]);
                } else if (targetChange.getRemovedDocuments().size() > 0) {
                    Assert.hardAssert(limboResolution.receivedDocument, "Received remove for limbo target document without add.", new Object[0]);
                    limboResolution.receivedDocument = false;
                }
            }
        }
        ImmutableSortedMap<DocumentKey, Document> changes = this.localStore.applyRemoteEvent(event);
        emitNewSnapsAndNotifyLocalStore(changes, event);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleOnlineStateChange(OnlineState onlineState) {
        assertCallback("handleOnlineStateChange");
        ArrayList<ViewSnapshot> newViewSnapshots = new ArrayList<>();
        for (Map.Entry<Query, QueryView> entry : this.queryViewsByQuery.entrySet()) {
            View view = entry.getValue().getView();
            ViewChange viewChange = view.applyOnlineStateChange(onlineState);
            Assert.hardAssert(viewChange.getLimboChanges().isEmpty(), "OnlineState should not affect limbo documents.", new Object[0]);
            if (viewChange.getSnapshot() != null) {
                newViewSnapshots.add(viewChange.getSnapshot());
            }
        }
        this.syncEngineListener.onViewSnapshots(newViewSnapshots);
        this.syncEngineListener.handleOnlineStateChange(onlineState);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int targetId) {
        LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(Integer.valueOf(targetId));
        if (limboResolution != null && limboResolution.receivedDocument) {
            return DocumentKey.emptyKeySet().insert(limboResolution.key);
        }
        ImmutableSortedSet<DocumentKey> remoteKeys = DocumentKey.emptyKeySet();
        if (this.queriesByTarget.containsKey(Integer.valueOf(targetId))) {
            for (Query query : this.queriesByTarget.get(Integer.valueOf(targetId))) {
                if (this.queryViewsByQuery.containsKey(query)) {
                    remoteKeys = remoteKeys.unionWith(this.queryViewsByQuery.get(query).getView().getSyncedDocuments());
                }
            }
        }
        return remoteKeys;
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleRejectedListen(int targetId, Status error) {
        assertCallback("handleRejectedListen");
        LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(Integer.valueOf(targetId));
        DocumentKey limboKey = limboResolution != null ? limboResolution.key : null;
        if (limboKey != null) {
            this.activeLimboTargetsByKey.remove(limboKey);
            this.activeLimboResolutionsByTarget.remove(Integer.valueOf(targetId));
            pumpEnqueuedLimboResolutions();
            MutableDocument result = MutableDocument.newNoDocument(limboKey, SnapshotVersion.NONE);
            Map<DocumentKey, MutableDocument> documentUpdates = Collections.singletonMap(limboKey, result);
            Set<DocumentKey> limboDocuments = Collections.singleton(limboKey);
            RemoteEvent event = new RemoteEvent(SnapshotVersion.NONE, Collections.emptyMap(), Collections.emptyMap(), documentUpdates, limboDocuments);
            handleRemoteEvent(event);
            return;
        }
        this.localStore.releaseTarget(targetId);
        removeAndCleanupTarget(targetId, error);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleSuccessfulWrite(MutationBatchResult mutationBatchResult) {
        assertCallback("handleSuccessfulWrite");
        notifyUser(mutationBatchResult.getBatch().getBatchId(), null);
        resolvePendingWriteTasks(mutationBatchResult.getBatch().getBatchId());
        ImmutableSortedMap<DocumentKey, Document> changes = this.localStore.acknowledgeBatch(mutationBatchResult);
        emitNewSnapsAndNotifyLocalStore(changes, null);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleRejectedWrite(int batchId, Status status) {
        assertCallback("handleRejectedWrite");
        ImmutableSortedMap<DocumentKey, Document> changes = this.localStore.rejectBatch(batchId);
        if (!changes.isEmpty()) {
            logErrorIfInteresting(status, "Write failed at %s", changes.getMinKey().getPath());
        }
        notifyUser(batchId, status);
        resolvePendingWriteTasks(batchId);
        emitNewSnapsAndNotifyLocalStore(changes, null);
    }

    public void registerPendingWritesTask(TaskCompletionSource<Void> userTask) {
        if (!this.remoteStore.canUseNetwork()) {
            Logger.debug(TAG, "The network is disabled. The task returned by 'awaitPendingWrites()' will not complete until the network is enabled.", new Object[0]);
        }
        int largestPendingBatchId = this.localStore.getHighestUnacknowledgedBatchId();
        if (largestPendingBatchId == -1) {
            userTask.setResult(null);
            return;
        }
        if (!this.pendingWritesCallbacks.containsKey(Integer.valueOf(largestPendingBatchId))) {
            this.pendingWritesCallbacks.put(Integer.valueOf(largestPendingBatchId), new ArrayList());
        }
        this.pendingWritesCallbacks.get(Integer.valueOf(largestPendingBatchId)).add(userTask);
    }

    private void resolvePendingWriteTasks(int batchId) {
        if (this.pendingWritesCallbacks.containsKey(Integer.valueOf(batchId))) {
            for (TaskCompletionSource<Void> task : this.pendingWritesCallbacks.get(Integer.valueOf(batchId))) {
                task.setResult(null);
            }
            this.pendingWritesCallbacks.remove(Integer.valueOf(batchId));
        }
    }

    private void failOutstandingPendingWritesAwaitingTasks() {
        for (Map.Entry<Integer, List<TaskCompletionSource<Void>>> entry : this.pendingWritesCallbacks.entrySet()) {
            for (TaskCompletionSource<Void> task : entry.getValue()) {
                task.setException(new FirebaseFirestoreException("'waitForPendingWrites' task is cancelled due to User change.", FirebaseFirestoreException.Code.CANCELLED));
            }
        }
        this.pendingWritesCallbacks.clear();
    }

    public void loadBundle(BundleReader bundleReader, LoadBundleTask resultTask) {
        try {
            try {
                BundleMetadata bundleMetadata = bundleReader.getBundleMetadata();
                boolean hasNewerBundle = this.localStore.hasNewerBundle(bundleMetadata);
                if (hasNewerBundle) {
                    resultTask.setResult(LoadBundleTaskProgress.forSuccess(bundleMetadata));
                    try {
                        bundleReader.close();
                        return;
                    } catch (IOException e) {
                        Logger.warn("SyncEngine", "Exception while closing bundle", e);
                        return;
                    }
                }
                resultTask.updateProgress(LoadBundleTaskProgress.forInitial(bundleMetadata));
                BundleLoader bundleLoader = new BundleLoader(this.localStore, bundleMetadata);
                long currentBytesRead = 0;
                while (true) {
                    BundleElement bundleElement = bundleReader.getNextElement();
                    if (bundleElement == null) {
                        ImmutableSortedMap<DocumentKey, Document> changes = bundleLoader.applyChanges();
                        emitNewSnapsAndNotifyLocalStore(changes, null);
                        this.localStore.saveBundle(bundleMetadata);
                        resultTask.setResult(LoadBundleTaskProgress.forSuccess(bundleMetadata));
                        try {
                            bundleReader.close();
                            return;
                        } catch (IOException e2) {
                            Logger.warn("SyncEngine", "Exception while closing bundle", e2);
                            return;
                        }
                    }
                    long oldBytesRead = currentBytesRead;
                    currentBytesRead = bundleReader.getBytesRead();
                    LoadBundleTaskProgress progress = bundleLoader.addElement(bundleElement, currentBytesRead - oldBytesRead);
                    if (progress != null) {
                        resultTask.updateProgress(progress);
                    }
                }
            } catch (Exception e3) {
                Logger.warn("Firestore", "Loading bundle failed : %s", e3);
                resultTask.setException(new FirebaseFirestoreException("Bundle failed to load", FirebaseFirestoreException.Code.INVALID_ARGUMENT, e3));
                try {
                    bundleReader.close();
                } catch (IOException e4) {
                    Logger.warn("SyncEngine", "Exception while closing bundle", e4);
                }
            }
        } finally {
        }
    }

    private void notifyUser(int batchId, Status status) {
        Integer boxedBatchId;
        TaskCompletionSource<Void> userTask;
        Map<Integer, TaskCompletionSource<Void>> userTasks = this.mutationUserCallbacks.get(this.currentUser);
        if (userTasks != null && (userTask = userTasks.get((boxedBatchId = Integer.valueOf(batchId)))) != null) {
            if (status != null) {
                userTask.setException(Util.exceptionFromStatus(status));
            } else {
                userTask.setResult(null);
            }
            userTasks.remove(boxedBatchId);
        }
    }

    private void removeAndCleanupTarget(int targetId, Status status) {
        for (Query query : this.queriesByTarget.get(Integer.valueOf(targetId))) {
            this.queryViewsByQuery.remove(query);
            if (!status.isOk()) {
                this.syncEngineListener.onError(query, status);
                logErrorIfInteresting(status, "Listen for %s failed", query);
            }
        }
        this.queriesByTarget.remove(Integer.valueOf(targetId));
        ImmutableSortedSet<DocumentKey> limboKeys = this.limboDocumentRefs.referencesForId(targetId);
        this.limboDocumentRefs.removeReferencesForId(targetId);
        for (DocumentKey key : limboKeys) {
            if (!this.limboDocumentRefs.containsKey(key)) {
                removeLimboTarget(key);
            }
        }
    }

    private void removeLimboTarget(DocumentKey key) {
        this.enqueuedLimboResolutions.remove(key);
        Integer targetId = this.activeLimboTargetsByKey.get(key);
        if (targetId != null) {
            this.remoteStore.stopListening(targetId.intValue());
            this.activeLimboTargetsByKey.remove(key);
            this.activeLimboResolutionsByTarget.remove(targetId);
            pumpEnqueuedLimboResolutions();
        }
    }

    private void emitNewSnapsAndNotifyLocalStore(ImmutableSortedMap<DocumentKey, Document> changes, RemoteEvent remoteEvent) {
        List<ViewSnapshot> newSnapshots = new ArrayList<>();
        List<LocalViewChanges> documentChangesInAllViews = new ArrayList<>();
        for (Map.Entry<Query, QueryView> entry : this.queryViewsByQuery.entrySet()) {
            QueryView queryView = entry.getValue();
            View view = queryView.getView();
            View.DocumentChanges viewDocChanges = view.computeDocChanges(changes);
            boolean targetIsPendingReset = false;
            if (viewDocChanges.needsRefill()) {
                QueryResult queryResult = this.localStore.executeQuery(queryView.getQuery(), false);
                viewDocChanges = view.computeDocChanges(queryResult.getDocuments(), viewDocChanges);
            }
            TargetChange targetChange = remoteEvent == null ? null : remoteEvent.getTargetChanges().get(Integer.valueOf(queryView.getTargetId()));
            if (remoteEvent != null && remoteEvent.getTargetMismatches().get(Integer.valueOf(queryView.getTargetId())) != null) {
                targetIsPendingReset = true;
            }
            ViewChange viewChange = queryView.getView().applyChanges(viewDocChanges, targetChange, targetIsPendingReset);
            updateTrackedLimboDocuments(viewChange.getLimboChanges(), queryView.getTargetId());
            if (viewChange.getSnapshot() != null) {
                newSnapshots.add(viewChange.getSnapshot());
                LocalViewChanges docChanges = LocalViewChanges.fromViewSnapshot(queryView.getTargetId(), viewChange.getSnapshot());
                documentChangesInAllViews.add(docChanges);
            }
        }
        this.syncEngineListener.onViewSnapshots(newSnapshots);
        this.localStore.notifyLocalViewChanges(documentChangesInAllViews);
    }

    private void updateTrackedLimboDocuments(List<LimboDocumentChange> limboChanges, int targetId) {
        for (LimboDocumentChange limboChange : limboChanges) {
            switch (limboChange.getType()) {
                case ADDED:
                    this.limboDocumentRefs.addReference(limboChange.getKey(), targetId);
                    trackLimboChange(limboChange);
                    break;
                case REMOVED:
                    Logger.debug(TAG, "Document no longer in limbo: %s", limboChange.getKey());
                    DocumentKey limboDocKey = limboChange.getKey();
                    this.limboDocumentRefs.removeReference(limboDocKey, targetId);
                    if (!this.limboDocumentRefs.containsKey(limboDocKey)) {
                        removeLimboTarget(limboDocKey);
                    }
                    break;
                default:
                    throw Assert.fail("Unknown limbo change type: %s", limboChange.getType());
            }
        }
    }

    private void trackLimboChange(LimboDocumentChange change) {
        DocumentKey key = change.getKey();
        if (!this.activeLimboTargetsByKey.containsKey(key) && !this.enqueuedLimboResolutions.contains(key)) {
            Logger.debug(TAG, "New document in limbo: %s", key);
            this.enqueuedLimboResolutions.add(key);
            pumpEnqueuedLimboResolutions();
        }
    }

    private void pumpEnqueuedLimboResolutions() {
        while (!this.enqueuedLimboResolutions.isEmpty() && this.activeLimboTargetsByKey.size() < this.maxConcurrentLimboResolutions) {
            Iterator<DocumentKey> it = this.enqueuedLimboResolutions.iterator();
            DocumentKey key = it.next();
            it.remove();
            int limboTargetId = this.targetIdGenerator.nextId();
            this.activeLimboResolutionsByTarget.put(Integer.valueOf(limboTargetId), new LimboResolution(key));
            this.activeLimboTargetsByKey.put(key, Integer.valueOf(limboTargetId));
            this.remoteStore.listen(new TargetData(Query.atPath(key.getPath()).toTarget(), limboTargetId, -1L, QueryPurpose.LIMBO_RESOLUTION));
        }
    }

    public Map<DocumentKey, Integer> getActiveLimboDocumentResolutions() {
        return new HashMap(this.activeLimboTargetsByKey);
    }

    public List<DocumentKey> getEnqueuedLimboDocumentResolutions() {
        return new ArrayList(this.enqueuedLimboResolutions);
    }

    public void handleCredentialChange(User user) {
        boolean userChanged = !this.currentUser.equals(user);
        this.currentUser = user;
        if (userChanged) {
            failOutstandingPendingWritesAwaitingTasks();
            ImmutableSortedMap<DocumentKey, Document> changes = this.localStore.handleUserChange(user);
            emitNewSnapsAndNotifyLocalStore(changes, null);
        }
        this.remoteStore.handleCredentialChange();
    }

    private void logErrorIfInteresting(Status error, String contextString, Object... contextArgs) {
        if (errorIsInteresting(error)) {
            String context = String.format(contextString, contextArgs);
            Logger.warn("Firestore", "%s: %s", context, error);
        }
    }

    private boolean errorIsInteresting(Status error) {
        Status.Code code = error.getCode();
        String description = error.getDescription() != null ? error.getDescription() : "";
        return (code == Status.Code.FAILED_PRECONDITION && description.contains("requires an index")) || code == Status.Code.PERMISSION_DENIED;
    }
}
