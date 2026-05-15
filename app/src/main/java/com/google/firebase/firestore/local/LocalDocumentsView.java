package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.util.Assert;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes10.dex */
class LocalDocumentsView {
    private final DocumentOverlayCache documentOverlayCache;
    private final IndexManager indexManager;
    private final MutationQueue mutationQueue;
    private final RemoteDocumentCache remoteDocumentCache;

    LocalDocumentsView(RemoteDocumentCache remoteDocumentCache, MutationQueue mutationQueue, DocumentOverlayCache documentOverlayCache, IndexManager indexManager) {
        this.remoteDocumentCache = remoteDocumentCache;
        this.mutationQueue = mutationQueue;
        this.documentOverlayCache = documentOverlayCache;
        this.indexManager = indexManager;
    }

    RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    MutationQueue getMutationQueue() {
        return this.mutationQueue;
    }

    DocumentOverlayCache getDocumentOverlayCache() {
        return this.documentOverlayCache;
    }

    Document getDocument(DocumentKey key) {
        Overlay overlay = this.documentOverlayCache.getOverlay(key);
        MutableDocument document = getBaseDocument(key, overlay);
        if (overlay != null) {
            overlay.getMutation().applyToLocalView(document, FieldMask.EMPTY, Timestamp.now());
        }
        return document;
    }

    ImmutableSortedMap<DocumentKey, Document> getDocuments(Iterable<DocumentKey> keys) {
        Map<DocumentKey, MutableDocument> docs = this.remoteDocumentCache.getAll(keys);
        return getLocalViewOfDocuments(docs, new HashSet());
    }

    ImmutableSortedMap<DocumentKey, Document> getLocalViewOfDocuments(Map<DocumentKey, MutableDocument> docs, Set<DocumentKey> existenceStateChanged) {
        Map<DocumentKey, Overlay> overlays = new HashMap<>();
        populateOverlays(overlays, docs.keySet());
        ImmutableSortedMap<DocumentKey, Document> result = DocumentCollections.emptyDocumentMap();
        for (Map.Entry<DocumentKey, OverlayedDocument> entry : computeViews(docs, overlays, existenceStateChanged).entrySet()) {
            result = result.insert(entry.getKey(), entry.getValue().getDocument());
        }
        return result;
    }

    Map<DocumentKey, OverlayedDocument> getOverlayedDocuments(Map<DocumentKey, MutableDocument> docs) {
        Map<DocumentKey, Overlay> overlays = new HashMap<>();
        populateOverlays(overlays, docs.keySet());
        return computeViews(docs, overlays, new HashSet());
    }

    private Map<DocumentKey, OverlayedDocument> computeViews(Map<DocumentKey, MutableDocument> docs, Map<DocumentKey, Overlay> overlays, Set<DocumentKey> existenceStateChanged) {
        Map<DocumentKey, MutableDocument> recalculateDocuments = new HashMap<>();
        Map<DocumentKey, FieldMask> mutatedFields = new HashMap<>();
        for (MutableDocument doc : docs.values()) {
            Overlay overlay = overlays.get(doc.getKey());
            if (existenceStateChanged.contains(doc.getKey()) && (overlay == null || (overlay.getMutation() instanceof PatchMutation))) {
                recalculateDocuments.put(doc.getKey(), doc);
            } else if (overlay != null) {
                mutatedFields.put(doc.getKey(), overlay.getMutation().getFieldMask());
                overlay.getMutation().applyToLocalView(doc, overlay.getMutation().getFieldMask(), Timestamp.now());
            } else {
                mutatedFields.put(doc.getKey(), FieldMask.EMPTY);
            }
        }
        Map<DocumentKey, FieldMask> recalculatedFields = recalculateAndSaveOverlays(recalculateDocuments);
        mutatedFields.putAll(recalculatedFields);
        Map<DocumentKey, OverlayedDocument> result = new HashMap<>();
        for (Map.Entry<DocumentKey, MutableDocument> entry : docs.entrySet()) {
            result.put(entry.getKey(), new OverlayedDocument(entry.getValue(), mutatedFields.get(entry.getKey())));
        }
        return result;
    }

    private Map<DocumentKey, FieldMask> recalculateAndSaveOverlays(Map<DocumentKey, MutableDocument> docs) {
        List<MutationBatch> batches = this.mutationQueue.getAllMutationBatchesAffectingDocumentKeys(docs.keySet());
        Map<DocumentKey, FieldMask> masks = new HashMap<>();
        TreeMap<Integer, Set<DocumentKey>> documentsByBatchId = new TreeMap<>();
        for (MutationBatch batch : batches) {
            for (DocumentKey key : batch.getKeys()) {
                MutableDocument baseDoc = docs.get(key);
                if (baseDoc != null) {
                    FieldMask mask = masks.containsKey(key) ? masks.get(key) : FieldMask.EMPTY;
                    masks.put(key, batch.applyToLocalView(baseDoc, mask));
                    int batchId = batch.getBatchId();
                    if (!documentsByBatchId.containsKey(Integer.valueOf(batchId))) {
                        documentsByBatchId.put(Integer.valueOf(batchId), new HashSet<>());
                    }
                    documentsByBatchId.get(Integer.valueOf(batchId)).add(key);
                }
            }
        }
        Set<DocumentKey> processed = new HashSet<>();
        for (Map.Entry<Integer, Set<DocumentKey>> entry : documentsByBatchId.descendingMap().entrySet()) {
            Map<DocumentKey, Mutation> overlays = new HashMap<>();
            for (DocumentKey key2 : entry.getValue()) {
                if (!processed.contains(key2)) {
                    Mutation mutation = Mutation.calculateOverlayMutation(docs.get(key2), masks.get(key2));
                    if (mutation != null) {
                        overlays.put(key2, mutation);
                    }
                    processed.add(key2);
                }
            }
            this.documentOverlayCache.saveOverlays(entry.getKey().intValue(), overlays);
        }
        return masks;
    }

    void recalculateAndSaveOverlays(Set<DocumentKey> documentKeys) {
        Map<DocumentKey, MutableDocument> docs = this.remoteDocumentCache.getAll(documentKeys);
        recalculateAndSaveOverlays(docs);
    }

    ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset offset, QueryContext context) {
        ResourcePath path = query.getPath();
        if (query.isDocumentQuery()) {
            return getDocumentsMatchingDocumentQuery(path);
        }
        if (query.isCollectionGroupQuery()) {
            return getDocumentsMatchingCollectionGroupQuery(query, offset, context);
        }
        return getDocumentsMatchingCollectionQuery(query, offset, context);
    }

    ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset offset) {
        return getDocumentsMatchingQuery(query, offset, null);
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingDocumentQuery(ResourcePath path) {
        ImmutableSortedMap<DocumentKey, Document> result = DocumentCollections.emptyDocumentMap();
        Document doc = getDocument(DocumentKey.fromPath(path));
        if (doc.isFoundDocument()) {
            return result.insert(doc.getKey(), doc);
        }
        return result;
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionGroupQuery(Query query, FieldIndex.IndexOffset offset, QueryContext context) {
        Assert.hardAssert(query.getPath().isEmpty(), "Currently we only support collection group queries at the root.", new Object[0]);
        String collectionId = query.getCollectionGroup();
        ImmutableSortedMap<DocumentKey, Document> results = DocumentCollections.emptyDocumentMap();
        List<ResourcePath> parents = this.indexManager.getCollectionParents(collectionId);
        for (ResourcePath parent : parents) {
            Query collectionQuery = query.asCollectionQueryAtPath(parent.append(collectionId));
            ImmutableSortedMap<DocumentKey, Document> collectionResults = getDocumentsMatchingCollectionQuery(collectionQuery, offset, context);
            for (Map.Entry<DocumentKey, Document> docEntry : collectionResults) {
                results = results.insert(docEntry.getKey(), docEntry.getValue());
            }
        }
        return results;
    }

    LocalDocumentsResult getNextDocuments(String collectionGroup, FieldIndex.IndexOffset offset, int count) {
        Map<DocumentKey, Overlay> overlays;
        Map<DocumentKey, MutableDocument> docs = this.remoteDocumentCache.getAll(collectionGroup, offset, count);
        if (count - docs.size() > 0) {
            overlays = this.documentOverlayCache.getOverlays(collectionGroup, offset.getLargestBatchId(), count - docs.size());
        } else {
            overlays = Collections.emptyMap();
        }
        int largestBatchId = -1;
        for (Overlay overlay : overlays.values()) {
            if (!docs.containsKey(overlay.getKey())) {
                docs.put(overlay.getKey(), getBaseDocument(overlay.getKey(), overlay));
            }
            largestBatchId = Math.max(largestBatchId, overlay.getLargestBatchId());
        }
        populateOverlays(overlays, docs.keySet());
        Map<DocumentKey, OverlayedDocument> localDocs = computeViews(docs, overlays, Collections.emptySet());
        return LocalDocumentsResult.fromOverlayedDocuments(largestBatchId, localDocs);
    }

    private void populateOverlays(Map<DocumentKey, Overlay> overlays, Set<DocumentKey> keys) {
        SortedSet<DocumentKey> missingOverlays = new TreeSet<>();
        for (DocumentKey key : keys) {
            if (!overlays.containsKey(key)) {
                missingOverlays.add(key);
            }
        }
        overlays.putAll(this.documentOverlayCache.getOverlays(missingOverlays));
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionQuery(Query query, FieldIndex.IndexOffset offset, QueryContext context) {
        Map<DocumentKey, Overlay> overlays = this.documentOverlayCache.getOverlays(query.getPath(), offset.getLargestBatchId());
        Map<DocumentKey, MutableDocument> remoteDocuments = this.remoteDocumentCache.getDocumentsMatchingQuery(query, offset, overlays.keySet(), context);
        for (Map.Entry<DocumentKey, Overlay> entry : overlays.entrySet()) {
            if (!remoteDocuments.containsKey(entry.getKey())) {
                remoteDocuments.put(entry.getKey(), MutableDocument.newInvalidDocument(entry.getKey()));
            }
        }
        ImmutableSortedMap<DocumentKey, Document> results = DocumentCollections.emptyDocumentMap();
        for (Map.Entry<DocumentKey, MutableDocument> docEntry : remoteDocuments.entrySet()) {
            Overlay overlay = overlays.get(docEntry.getKey());
            if (overlay != null) {
                overlay.getMutation().applyToLocalView(docEntry.getValue(), FieldMask.EMPTY, Timestamp.now());
            }
            if (query.matches(docEntry.getValue())) {
                results = results.insert(docEntry.getKey(), docEntry.getValue());
            }
        }
        return results;
    }

    private MutableDocument getBaseDocument(DocumentKey key, Overlay overlay) {
        if (overlay == null || (overlay.getMutation() instanceof PatchMutation)) {
            return this.remoteDocumentCache.get(key);
        }
        return MutableDocument.newInvalidDocument(key);
    }
}
