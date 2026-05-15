package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.IndexManager;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
class MemoryIndexManager implements IndexManager {
    private final MemoryCollectionParentIndex collectionParentsIndex = new MemoryCollectionParentIndex();

    @Override // com.google.firebase.firestore.local.IndexManager
    public void start() {
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void addToCollectionParentIndex(ResourcePath collectionPath) {
        this.collectionParentsIndex.add(collectionPath);
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public List<ResourcePath> getCollectionParents(String collectionId) {
        return this.collectionParentsIndex.getEntries(collectionId);
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void addFieldIndex(FieldIndex index) {
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void deleteFieldIndex(FieldIndex index) {
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void deleteAllFieldIndexes() {
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void createTargetIndexes(Target target) {
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public List<DocumentKey> getDocumentsMatchingTarget(Target target) {
        return null;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public String getNextCollectionGroupToUpdate() {
        return null;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void updateCollectionGroup(String collectionGroup, FieldIndex.IndexOffset offset) {
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public Collection<FieldIndex> getFieldIndexes(String collectionGroup) {
        return Collections.emptyList();
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public Collection<FieldIndex> getFieldIndexes() {
        return Collections.emptyList();
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public FieldIndex.IndexOffset getMinOffset(Target target) {
        return FieldIndex.IndexOffset.NONE;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public FieldIndex.IndexOffset getMinOffset(String collectionGroup) {
        return FieldIndex.IndexOffset.NONE;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public IndexManager.IndexType getIndexType(Target target) {
        return IndexManager.IndexType.NONE;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void updateIndexEntries(ImmutableSortedMap<DocumentKey, Document> documents) {
    }

    static class MemoryCollectionParentIndex {
        private final HashMap<String, HashSet<ResourcePath>> index = new HashMap<>();

        MemoryCollectionParentIndex() {
        }

        boolean add(ResourcePath collectionPath) {
            Assert.hardAssert(collectionPath.length() % 2 == 1, "Expected a collection path.", new Object[0]);
            String collectionId = collectionPath.getLastSegment();
            ResourcePath parentPath = collectionPath.popLast();
            HashSet<ResourcePath> existingParents = this.index.get(collectionId);
            if (existingParents == null) {
                existingParents = new HashSet<>();
                this.index.put(collectionId, existingParents);
            }
            return existingParents.add(parentPath);
        }

        List<ResourcePath> getEntries(String collectionId) {
            HashSet<ResourcePath> existingParents = this.index.get(collectionId);
            return existingParents != null ? new ArrayList(existingParents) : Collections.emptyList();
        }
    }
}
