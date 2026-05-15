package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.Write;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes10.dex */
public class SQLiteDocumentOverlayCache implements DocumentOverlayCache {
    private final SQLitePersistence db;
    private final LocalSerializer serializer;
    private final String uid;

    public SQLiteDocumentOverlayCache(SQLitePersistence db, LocalSerializer serializer, User user) {
        this.db = db;
        this.serializer = serializer;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Overlay getOverlay(DocumentKey key) {
        String collectionPath = EncodedPath.encode(key.getPath().popLast());
        String documentId = key.getPath().getLastSegment();
        return (Overlay) this.db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND document_id = ?").binding(this.uid, collectionPath, documentId).firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda4
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f$0.m334xe0a2a77c((Cursor) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getOverlay$0$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache, reason: not valid java name */
    /* synthetic */ Overlay m334xe0a2a77c(Cursor row) {
        return decodeOverlay(row.getBlob(0), row.getInt(1));
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(SortedSet<DocumentKey> keys) {
        Assert.hardAssert(keys.comparator() == null, "getOverlays() requires natural order", new Object[0]);
        Map<DocumentKey, Overlay> result = new HashMap<>();
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        ResourcePath currentCollection = ResourcePath.EMPTY;
        List<Object> accumulatedDocumentIds = new ArrayList<>();
        for (DocumentKey key : keys) {
            if (!currentCollection.equals(key.getCollectionPath())) {
                processSingleCollection(result, backgroundQueue, currentCollection, accumulatedDocumentIds);
                currentCollection = key.getCollectionPath();
                accumulatedDocumentIds.clear();
            }
            accumulatedDocumentIds.add(key.getDocumentId());
        }
        processSingleCollection(result, backgroundQueue, currentCollection, accumulatedDocumentIds);
        backgroundQueue.drain();
        return result;
    }

    private void processSingleCollection(final Map<DocumentKey, Overlay> result, final BackgroundQueue backgroundQueue, ResourcePath collectionPath, List<Object> documentIds) {
        if (documentIds.isEmpty()) {
            return;
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND document_id IN (", Arrays.asList(this.uid, EncodedPath.encode(collectionPath)), documentIds, ")");
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda5
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m339x634144da(backgroundQueue, result, (Cursor) obj);
                }
            });
        }
    }

    private void saveOverlay(int largestBatchId, DocumentKey key, Mutation mutation) {
        String group = key.getCollectionGroup();
        String collectionPath = EncodedPath.encode(key.getPath().popLast());
        String documentId = key.getPath().getLastSegment();
        this.db.execute("INSERT OR REPLACE INTO document_overlays (uid, collection_group, collection_path, document_id, largest_batch_id, overlay_mutation) VALUES (?, ?, ?, ?, ?, ?)", this.uid, group, collectionPath, documentId, Integer.valueOf(largestBatchId), this.serializer.encodeMutation(mutation).toByteArray());
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void saveOverlays(int largestBatchId, Map<DocumentKey, Mutation> overlays) {
        for (Map.Entry<DocumentKey, Mutation> entry : overlays.entrySet()) {
            DocumentKey key = entry.getKey();
            Mutation overlay = (Mutation) Preconditions.checkNotNull(entry.getValue(), "null value for key: %s", key);
            saveOverlay(largestBatchId, key, overlay);
        }
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void removeOverlaysForBatchId(int batchId) {
        this.db.execute("DELETE FROM document_overlays WHERE uid = ? AND largest_batch_id = ?", this.uid, Integer.valueOf(batchId));
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(ResourcePath collection, int sinceBatchId) {
        final Map<DocumentKey, Overlay> result = new HashMap<>();
        final BackgroundQueue backgroundQueue = new BackgroundQueue();
        this.db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND largest_batch_id > ?").binding(this.uid, EncodedPath.encode(collection), Integer.valueOf(sinceBatchId)).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m335x2236d2b5(backgroundQueue, result, (Cursor) obj);
            }
        });
        backgroundQueue.drain();
        return result;
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Overlay> getOverlays(String collectionGroup, int sinceBatchId, int count) {
        final Map<DocumentKey, Overlay> result = new HashMap<>();
        final String[] lastCollectionPath = new String[1];
        final String[] lastDocumentPath = new String[1];
        final int[] lastLargestBatchId = new int[1];
        final BackgroundQueue backgroundQueue = new BackgroundQueue();
        this.db.query("SELECT overlay_mutation, largest_batch_id, collection_path, document_id  FROM document_overlays WHERE uid = ? AND collection_group = ? AND largest_batch_id > ? ORDER BY largest_batch_id, collection_path, document_id LIMIT ?").binding(this.uid, collectionGroup, Integer.valueOf(sinceBatchId), Integer.valueOf(count)).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m336xa097d694(lastLargestBatchId, lastCollectionPath, lastDocumentPath, backgroundQueue, result, (Cursor) obj);
            }
        });
        if (lastCollectionPath[0] == null) {
            return result;
        }
        this.db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_group = ? AND (collection_path > ? OR (collection_path = ? AND document_id > ?)) AND largest_batch_id = ?").binding(this.uid, collectionGroup, lastCollectionPath[0], lastCollectionPath[0], lastDocumentPath[0], Integer.valueOf(lastLargestBatchId[0])).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m337x1ef8da73(backgroundQueue, result, (Cursor) obj);
            }
        });
        backgroundQueue.drain();
        return result;
    }

    /* JADX INFO: renamed from: lambda$getOverlays$3$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache, reason: not valid java name */
    /* synthetic */ void m336xa097d694(int[] lastLargestBatchId, String[] lastCollectionPath, String[] lastDocumentPath, BackgroundQueue backgroundQueue, Map result, Cursor row) {
        lastLargestBatchId[0] = row.getInt(1);
        lastCollectionPath[0] = row.getString(2);
        lastDocumentPath[0] = row.getString(3);
        m339x634144da(backgroundQueue, result, row);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: processOverlaysInBackground, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void m339x634144da(BackgroundQueue backgroundQueue, final Map<DocumentKey, Overlay> results, Cursor row) {
        final byte[] rawMutation = row.getBlob(0);
        final int largestBatchId = row.getInt(1);
        Executor executor = row.isLast() ? Executors.DIRECT_EXECUTOR : backgroundQueue;
        executor.execute(new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteDocumentOverlayCache$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m338x1d10fe66(rawMutation, largestBatchId, results);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$processOverlaysInBackground$5$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache, reason: not valid java name */
    /* synthetic */ void m338x1d10fe66(byte[] rawMutation, int largestBatchId, Map results) {
        Overlay overlay = decodeOverlay(rawMutation, largestBatchId);
        synchronized (results) {
            results.put(overlay.getKey(), overlay);
        }
    }

    private Overlay decodeOverlay(byte[] rawMutation, int largestBatchId) {
        try {
            Write write = Write.parseFrom(rawMutation);
            Mutation mutation = this.serializer.decodeMutation(write);
            return Overlay.create(largestBatchId, mutation);
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("Overlay failed to parse: %s", e);
        }
    }
}
