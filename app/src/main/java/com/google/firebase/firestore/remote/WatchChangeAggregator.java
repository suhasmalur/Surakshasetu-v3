package com.google.firebase.firestore.remote;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.BloomFilter;
import com.google.firebase.firestore.remote.TestingHooks;
import com.google.firebase.firestore.remote.WatchChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes10.dex */
public class WatchChangeAggregator {
    private static final String LOG_TAG = "WatchChangeAggregator";
    private final TargetMetadataProvider targetMetadataProvider;
    private final Map<Integer, TargetState> targetStates = new HashMap();
    private Map<DocumentKey, MutableDocument> pendingDocumentUpdates = new HashMap();
    private Map<DocumentKey, Set<Integer>> pendingDocumentTargetMapping = new HashMap();
    private Map<Integer, QueryPurpose> pendingTargetResets = new HashMap();

    enum BloomFilterApplicationStatus {
        SUCCESS,
        SKIPPED,
        FALSE_POSITIVE
    }

    public interface TargetMetadataProvider {
        DatabaseId getDatabaseId();

        ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i);

        TargetData getTargetDataForTarget(int i);
    }

    public WatchChangeAggregator(TargetMetadataProvider targetMetadataProvider) {
        this.targetMetadataProvider = targetMetadataProvider;
    }

    public void handleDocumentChange(WatchChange.DocumentChange documentChange) {
        MutableDocument document = documentChange.getNewDocument();
        DocumentKey documentKey = documentChange.getDocumentKey();
        Iterator<Integer> it = documentChange.getUpdatedTargetIds().iterator();
        while (it.hasNext()) {
            int targetId = it.next().intValue();
            if (document != null && document.isFoundDocument()) {
                addDocumentToTarget(targetId, document);
            } else {
                removeDocumentFromTarget(targetId, documentKey, document);
            }
        }
        Iterator<Integer> it2 = documentChange.getRemovedTargetIds().iterator();
        while (it2.hasNext()) {
            removeDocumentFromTarget(it2.next().intValue(), documentKey, documentChange.getNewDocument());
        }
    }

    public void handleTargetChange(WatchChange.WatchTargetChange targetChange) {
        Iterator<Integer> it = getTargetIds(targetChange).iterator();
        while (it.hasNext()) {
            int targetId = it.next().intValue();
            TargetState targetState = ensureTargetState(targetId);
            switch (targetChange.getChangeType()) {
                case NoChange:
                    if (isActiveTarget(targetId)) {
                        targetState.updateResumeToken(targetChange.getResumeToken());
                    }
                    break;
                case Added:
                    targetState.recordTargetResponse();
                    if (!targetState.isPending()) {
                        targetState.clearChanges();
                    }
                    targetState.updateResumeToken(targetChange.getResumeToken());
                    break;
                case Removed:
                    targetState.recordTargetResponse();
                    if (!targetState.isPending()) {
                        removeTarget(targetId);
                    }
                    Assert.hardAssert(targetChange.getCause() == null, "WatchChangeAggregator does not handle errored targets", new Object[0]);
                    break;
                case Current:
                    if (isActiveTarget(targetId)) {
                        targetState.markCurrent();
                        targetState.updateResumeToken(targetChange.getResumeToken());
                    }
                    break;
                case Reset:
                    if (isActiveTarget(targetId)) {
                        resetTarget(targetId);
                        targetState.updateResumeToken(targetChange.getResumeToken());
                    }
                    break;
                default:
                    throw Assert.fail("Unknown target watch change state: %s", targetChange.getChangeType());
            }
        }
    }

    private Collection<Integer> getTargetIds(WatchChange.WatchTargetChange targetChange) {
        List<Integer> targetIds = targetChange.getTargetIds();
        if (!targetIds.isEmpty()) {
            return targetIds;
        }
        List<Integer> activeIds = new ArrayList<>();
        for (Integer id : this.targetStates.keySet()) {
            if (isActiveTarget(id.intValue())) {
                activeIds.add(id);
            }
        }
        return activeIds;
    }

    public void handleExistenceFilter(WatchChange.ExistenceFilterWatchChange watchChange) {
        BloomFilterApplicationStatus status;
        QueryPurpose purpose;
        int targetId = watchChange.getTargetId();
        int expectedCount = watchChange.getExistenceFilter().getCount();
        TargetData targetData = queryDataForActiveTarget(targetId);
        if (targetData != null) {
            Target target = targetData.getTarget();
            if (target.isDocumentQuery()) {
                if (expectedCount == 0) {
                    DocumentKey key = DocumentKey.fromPath(target.getPath());
                    MutableDocument result = MutableDocument.newNoDocument(key, SnapshotVersion.NONE);
                    removeDocumentFromTarget(targetId, key, result);
                    return;
                }
                Assert.hardAssert(expectedCount == 1, "Single document existence filter with count: %d", Integer.valueOf(expectedCount));
                return;
            }
            int currentSize = getCurrentDocumentCountForTarget(targetId);
            if (currentSize != expectedCount) {
                BloomFilter bloomFilter = parseBloomFilter(watchChange);
                if (bloomFilter != null) {
                    status = applyBloomFilter(bloomFilter, watchChange, currentSize);
                } else {
                    status = BloomFilterApplicationStatus.SKIPPED;
                }
                if (status != BloomFilterApplicationStatus.SUCCESS) {
                    resetTarget(targetId);
                    if (status == BloomFilterApplicationStatus.FALSE_POSITIVE) {
                        purpose = QueryPurpose.EXISTENCE_FILTER_MISMATCH_BLOOM;
                    } else {
                        purpose = QueryPurpose.EXISTENCE_FILTER_MISMATCH;
                    }
                    this.pendingTargetResets.put(Integer.valueOf(targetId), purpose);
                }
                TestingHooks.getInstance().notifyOnExistenceFilterMismatch(TestingHooks.ExistenceFilterMismatchInfo.from(currentSize, watchChange.getExistenceFilter(), this.targetMetadataProvider.getDatabaseId(), bloomFilter, status));
            }
        }
    }

    private BloomFilter parseBloomFilter(WatchChange.ExistenceFilterWatchChange watchChange) {
        com.google.firestore.v1.BloomFilter unchangedNames = watchChange.getExistenceFilter().getUnchangedNames();
        if (unchangedNames == null || !unchangedNames.hasBits()) {
            return null;
        }
        ByteString bitmap = unchangedNames.getBits().getBitmap();
        try {
            BloomFilter bloomFilter = BloomFilter.create(bitmap, unchangedNames.getBits().getPadding(), unchangedNames.getHashCount());
            if (bloomFilter.getBitCount() == 0) {
                return null;
            }
            return bloomFilter;
        } catch (BloomFilter.BloomFilterCreateException e) {
            Logger.warn(LOG_TAG, "Applying bloom filter failed: (" + e.getMessage() + "); ignoring the bloom filter and falling back to full re-query.", new Object[0]);
            return null;
        }
    }

    private BloomFilterApplicationStatus applyBloomFilter(BloomFilter bloomFilter, WatchChange.ExistenceFilterWatchChange watchChange, int currentCount) {
        int expectedCount = watchChange.getExistenceFilter().getCount();
        int removedDocumentCount = filterRemovedDocuments(bloomFilter, watchChange.getTargetId());
        if (expectedCount == currentCount - removedDocumentCount) {
            return BloomFilterApplicationStatus.SUCCESS;
        }
        return BloomFilterApplicationStatus.FALSE_POSITIVE;
    }

    private int filterRemovedDocuments(BloomFilter bloomFilter, int targetId) {
        ImmutableSortedSet<DocumentKey> existingKeys = this.targetMetadataProvider.getRemoteKeysForTarget(targetId);
        int removalCount = 0;
        for (DocumentKey key : existingKeys) {
            DatabaseId databaseId = this.targetMetadataProvider.getDatabaseId();
            String documentPath = "projects/" + databaseId.getProjectId() + "/databases/" + databaseId.getDatabaseId() + "/documents/" + key.getPath().canonicalString();
            if (!bloomFilter.mightContain(documentPath)) {
                removeDocumentFromTarget(targetId, key, null);
                removalCount++;
            }
        }
        return removalCount;
    }

    public RemoteEvent createRemoteEvent(SnapshotVersion snapshotVersion) {
        Map<Integer, TargetChange> targetChanges = new HashMap<>();
        for (Map.Entry<Integer, TargetState> entry : this.targetStates.entrySet()) {
            int targetId = entry.getKey().intValue();
            TargetState targetState = entry.getValue();
            TargetData targetData = queryDataForActiveTarget(targetId);
            if (targetData != null) {
                if (targetState.isCurrent() && targetData.getTarget().isDocumentQuery()) {
                    DocumentKey key = DocumentKey.fromPath(targetData.getTarget().getPath());
                    if (this.pendingDocumentUpdates.get(key) == null && !targetContainsDocument(targetId, key)) {
                        MutableDocument result = MutableDocument.newNoDocument(key, snapshotVersion);
                        removeDocumentFromTarget(targetId, key, result);
                    }
                }
                if (targetState.hasChanges()) {
                    targetChanges.put(Integer.valueOf(targetId), targetState.toTargetChange());
                    targetState.clearChanges();
                }
            }
        }
        Set<DocumentKey> resolvedLimboDocuments = new HashSet<>();
        for (Map.Entry<DocumentKey, Set<Integer>> entry2 : this.pendingDocumentTargetMapping.entrySet()) {
            DocumentKey key2 = entry2.getKey();
            Set<Integer> targets = entry2.getValue();
            boolean isOnlyLimboTarget = true;
            Iterator<Integer> it = targets.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                TargetData targetData2 = queryDataForActiveTarget(it.next().intValue());
                if (targetData2 != null && !targetData2.getPurpose().equals(QueryPurpose.LIMBO_RESOLUTION)) {
                    isOnlyLimboTarget = false;
                    break;
                }
            }
            if (isOnlyLimboTarget) {
                resolvedLimboDocuments.add(key2);
            }
        }
        for (MutableDocument document : this.pendingDocumentUpdates.values()) {
            document.setReadTime(snapshotVersion);
        }
        RemoteEvent remoteEvent = new RemoteEvent(snapshotVersion, Collections.unmodifiableMap(targetChanges), Collections.unmodifiableMap(this.pendingTargetResets), Collections.unmodifiableMap(this.pendingDocumentUpdates), Collections.unmodifiableSet(resolvedLimboDocuments));
        this.pendingDocumentUpdates = new HashMap();
        this.pendingDocumentTargetMapping = new HashMap();
        this.pendingTargetResets = new HashMap();
        return remoteEvent;
    }

    private void addDocumentToTarget(int targetId, MutableDocument document) {
        DocumentViewChange.Type changeType;
        if (!isActiveTarget(targetId)) {
            return;
        }
        if (targetContainsDocument(targetId, document.getKey())) {
            changeType = DocumentViewChange.Type.MODIFIED;
        } else {
            changeType = DocumentViewChange.Type.ADDED;
        }
        TargetState targetState = ensureTargetState(targetId);
        targetState.addDocumentChange(document.getKey(), changeType);
        this.pendingDocumentUpdates.put(document.getKey(), document);
        ensureDocumentTargetMapping(document.getKey()).add(Integer.valueOf(targetId));
    }

    private void removeDocumentFromTarget(int targetId, DocumentKey key, MutableDocument updatedDocument) {
        if (!isActiveTarget(targetId)) {
            return;
        }
        TargetState targetState = ensureTargetState(targetId);
        if (targetContainsDocument(targetId, key)) {
            targetState.addDocumentChange(key, DocumentViewChange.Type.REMOVED);
        } else {
            targetState.removeDocumentChange(key);
        }
        ensureDocumentTargetMapping(key).add(Integer.valueOf(targetId));
        if (updatedDocument != null) {
            this.pendingDocumentUpdates.put(key, updatedDocument);
        }
    }

    void removeTarget(int targetId) {
        this.targetStates.remove(Integer.valueOf(targetId));
    }

    private int getCurrentDocumentCountForTarget(int targetId) {
        TargetState targetState = ensureTargetState(targetId);
        TargetChange targetChange = targetState.toTargetChange();
        return (this.targetMetadataProvider.getRemoteKeysForTarget(targetId).size() + targetChange.getAddedDocuments().size()) - targetChange.getRemovedDocuments().size();
    }

    void recordPendingTargetRequest(int targetId) {
        TargetState targetState = ensureTargetState(targetId);
        targetState.recordPendingTargetRequest();
    }

    private TargetState ensureTargetState(int targetId) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(targetId));
        if (targetState == null) {
            TargetState targetState2 = new TargetState();
            this.targetStates.put(Integer.valueOf(targetId), targetState2);
            return targetState2;
        }
        return targetState;
    }

    private Set<Integer> ensureDocumentTargetMapping(DocumentKey key) {
        Set<Integer> targetMapping = this.pendingDocumentTargetMapping.get(key);
        if (targetMapping == null) {
            Set<Integer> targetMapping2 = new HashSet<>();
            this.pendingDocumentTargetMapping.put(key, targetMapping2);
            return targetMapping2;
        }
        return targetMapping;
    }

    private boolean isActiveTarget(int targetId) {
        return queryDataForActiveTarget(targetId) != null;
    }

    private TargetData queryDataForActiveTarget(int targetId) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(targetId));
        if (targetState != null && targetState.isPending()) {
            return null;
        }
        return this.targetMetadataProvider.getTargetDataForTarget(targetId);
    }

    private void resetTarget(int targetId) {
        Assert.hardAssert((this.targetStates.get(Integer.valueOf(targetId)) == null || this.targetStates.get(Integer.valueOf(targetId)).isPending()) ? false : true, "Should only reset active targets", new Object[0]);
        this.targetStates.put(Integer.valueOf(targetId), new TargetState());
        ImmutableSortedSet<DocumentKey> existingKeys = this.targetMetadataProvider.getRemoteKeysForTarget(targetId);
        for (DocumentKey key : existingKeys) {
            removeDocumentFromTarget(targetId, key, null);
        }
    }

    private boolean targetContainsDocument(int targetId, DocumentKey key) {
        ImmutableSortedSet<DocumentKey> existingKeys = this.targetMetadataProvider.getRemoteKeysForTarget(targetId);
        return existingKeys.contains(key);
    }
}
