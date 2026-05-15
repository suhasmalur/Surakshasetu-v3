package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.util.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes10.dex */
public class MemoryDocumentOverlayCache implements DocumentOverlayCache {
    private final TreeMap<DocumentKey, Overlay> overlays = new TreeMap<>();
    private final Map<Integer, Set<DocumentKey>> overlayByBatchId = new HashMap();

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Overlay getOverlay(DocumentKey key) {
        return this.overlays.get(key);
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(SortedSet<DocumentKey> keys) {
        Map<DocumentKey, Overlay> result = new HashMap<>();
        for (DocumentKey key : keys) {
            Overlay overlay = this.overlays.get(key);
            if (overlay != null) {
                result.put(key, overlay);
            }
        }
        return result;
    }

    private void saveOverlay(int largestBatchId, Mutation mutation) {
        Overlay existing = this.overlays.get(mutation.getKey());
        if (existing != null) {
            this.overlayByBatchId.get(Integer.valueOf(existing.getLargestBatchId())).remove(mutation.getKey());
        }
        this.overlays.put(mutation.getKey(), Overlay.create(largestBatchId, mutation));
        if (this.overlayByBatchId.get(Integer.valueOf(largestBatchId)) == null) {
            this.overlayByBatchId.put(Integer.valueOf(largestBatchId), new HashSet());
        }
        this.overlayByBatchId.get(Integer.valueOf(largestBatchId)).add(mutation.getKey());
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void saveOverlays(int largestBatchId, Map<DocumentKey, Mutation> overlays) {
        for (Map.Entry<DocumentKey, Mutation> entry : overlays.entrySet()) {
            Mutation overlay = (Mutation) Preconditions.checkNotNull(entry.getValue(), "null value for key: %s", entry.getKey());
            saveOverlay(largestBatchId, overlay);
        }
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void removeOverlaysForBatchId(int batchId) {
        if (this.overlayByBatchId.containsKey(Integer.valueOf(batchId))) {
            Set<DocumentKey> keys = this.overlayByBatchId.get(Integer.valueOf(batchId));
            this.overlayByBatchId.remove(Integer.valueOf(batchId));
            for (DocumentKey key : keys) {
                this.overlays.remove(key);
            }
        }
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(ResourcePath collection, int sinceBatchId) {
        Map<DocumentKey, Overlay> result = new HashMap<>();
        int immediateChildrenPathLength = collection.length() + 1;
        DocumentKey prefix = DocumentKey.fromPath(collection.append(""));
        Map<DocumentKey, Overlay> view = this.overlays.tailMap(prefix);
        for (Overlay overlay : view.values()) {
            DocumentKey key = overlay.getKey();
            if (!collection.isPrefixOf(key.getPath())) {
                break;
            }
            if (key.getPath().length() == immediateChildrenPathLength && overlay.getLargestBatchId() > sinceBatchId) {
                result.put(overlay.getKey(), overlay);
            }
        }
        return result;
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(String collectionGroup, int sinceBatchId, int count) {
        SortedMap<Integer, Map<DocumentKey, Overlay>> batchIdToOverlays = new TreeMap<>();
        for (Overlay overlay : this.overlays.values()) {
            DocumentKey key = overlay.getKey();
            if (key.getCollectionGroup().equals(collectionGroup) && overlay.getLargestBatchId() > sinceBatchId) {
                Map<DocumentKey, Overlay> overlays = batchIdToOverlays.get(Integer.valueOf(overlay.getLargestBatchId()));
                if (overlays == null) {
                    overlays = new HashMap<>();
                    batchIdToOverlays.put(Integer.valueOf(overlay.getLargestBatchId()), overlays);
                }
                overlays.put(overlay.getKey(), overlay);
            }
        }
        Map<DocumentKey, Overlay> result = new HashMap<>();
        Iterator<Map<DocumentKey, Overlay>> it = batchIdToOverlays.values().iterator();
        while (it.hasNext()) {
            result.putAll(it.next());
            if (result.size() >= count) {
                break;
            }
        }
        return result;
    }
}
