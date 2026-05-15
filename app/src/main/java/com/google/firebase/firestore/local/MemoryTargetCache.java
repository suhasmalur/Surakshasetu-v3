package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Consumer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
final class MemoryTargetCache implements TargetCache {
    private int highestTargetId;
    private final MemoryPersistence persistence;
    private final Map<Target, TargetData> targets = new HashMap();
    private final ReferenceSet references = new ReferenceSet();
    private SnapshotVersion lastRemoteSnapshotVersion = SnapshotVersion.NONE;
    private long highestSequenceNumber = 0;

    MemoryTargetCache(MemoryPersistence persistence) {
        this.persistence = persistence;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public int getHighestTargetId() {
        return this.highestTargetId;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public long getTargetCount() {
        return this.targets.size();
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void forEachTarget(Consumer<TargetData> consumer) {
        for (TargetData targetData : this.targets.values()) {
            consumer.accept(targetData);
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public long getHighestListenSequenceNumber() {
        return this.highestSequenceNumber;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void setLastRemoteSnapshotVersion(SnapshotVersion snapshotVersion) {
        this.lastRemoteSnapshotVersion = snapshotVersion;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void addTargetData(TargetData targetData) {
        this.targets.put(targetData.getTarget(), targetData);
        int targetId = targetData.getTargetId();
        if (targetId > this.highestTargetId) {
            this.highestTargetId = targetId;
        }
        if (targetData.getSequenceNumber() > this.highestSequenceNumber) {
            this.highestSequenceNumber = targetData.getSequenceNumber();
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void updateTargetData(TargetData targetData) {
        addTargetData(targetData);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeTargetData(TargetData targetData) {
        this.targets.remove(targetData.getTarget());
        this.references.removeReferencesForId(targetData.getTargetId());
    }

    int removeQueries(long upperBound, SparseArray<?> activeTargetIds) {
        int removed = 0;
        Iterator<Map.Entry<Target, TargetData>> it = this.targets.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Target, TargetData> entry = it.next();
            int targetId = entry.getValue().getTargetId();
            long sequenceNumber = entry.getValue().getSequenceNumber();
            if (sequenceNumber <= upperBound && activeTargetIds.get(targetId) == null) {
                it.remove();
                removeMatchingKeysForTargetId(targetId);
                removed++;
            }
        }
        return removed;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public TargetData getTargetData(Target target) {
        return this.targets.get(target);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void addMatchingKeys(ImmutableSortedSet<DocumentKey> keys, int targetId) {
        this.references.addReferences(keys, targetId);
        ReferenceDelegate referenceDelegate = this.persistence.getReferenceDelegate();
        for (DocumentKey key : keys) {
            referenceDelegate.addReference(key);
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeMatchingKeys(ImmutableSortedSet<DocumentKey> keys, int targetId) {
        this.references.removeReferences(keys, targetId);
        ReferenceDelegate referenceDelegate = this.persistence.getReferenceDelegate();
        for (DocumentKey key : keys) {
            referenceDelegate.removeReference(key);
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeMatchingKeysForTargetId(int targetId) {
        this.references.removeReferencesForId(targetId);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public ImmutableSortedSet<DocumentKey> getMatchingKeysForTargetId(int targetId) {
        return this.references.referencesForId(targetId);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public boolean containsKey(DocumentKey key) {
        return this.references.containsKey(key);
    }

    long getByteSize(LocalSerializer serializer) {
        long count = 0;
        for (Map.Entry<Target, TargetData> entry : this.targets.entrySet()) {
            count += (long) serializer.encodeTargetData(entry.getValue()).getSerializedSize();
        }
        return count;
    }
}
