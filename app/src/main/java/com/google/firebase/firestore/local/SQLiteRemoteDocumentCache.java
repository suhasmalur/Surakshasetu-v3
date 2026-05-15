package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.proto.MaybeDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
final class SQLiteRemoteDocumentCache implements RemoteDocumentCache {
    static final int BINDS_PER_STATEMENT = 9;
    private final SQLitePersistence db;
    private IndexManager indexManager;
    private final LocalSerializer serializer;

    SQLiteRemoteDocumentCache(SQLitePersistence persistence, LocalSerializer serializer) {
        this.db = persistence;
        this.serializer = serializer;
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void setIndexManager(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void add(MutableDocument document, SnapshotVersion readTime) {
        Assert.hardAssert(!readTime.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        DocumentKey documentKey = document.getKey();
        Timestamp timestamp = readTime.getTimestamp();
        MessageLite message = this.serializer.encodeMaybeDocument(document);
        this.db.execute("INSERT OR REPLACE INTO remote_documents (path, path_length, read_time_seconds, read_time_nanos, contents) VALUES (?, ?, ?, ?, ?)", EncodedPath.encode(documentKey.getPath()), Integer.valueOf(documentKey.getPath().length()), Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanoseconds()), message.toByteArray());
        this.indexManager.addToCollectionParentIndex(document.getKey().getCollectionPath());
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void removeAll(Collection<DocumentKey> keys) {
        if (keys.isEmpty()) {
            return;
        }
        List<Object> encodedPaths = new ArrayList<>();
        ImmutableSortedMap<DocumentKey, Document> deletedDocs = DocumentCollections.emptyDocumentMap();
        for (DocumentKey key : keys) {
            encodedPaths.add(EncodedPath.encode(key.getPath()));
            deletedDocs = deletedDocs.insert(key, MutableDocument.newNoDocument(key, SnapshotVersion.NONE));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "DELETE FROM remote_documents WHERE path IN (", encodedPaths, ")");
        while (longQuery.hasMoreSubqueries()) {
            longQuery.executeNextSubquery();
        }
        this.indexManager.updateIndexEntries(deletedDocs);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public MutableDocument get(DocumentKey documentKey) {
        return getAll(Collections.singletonList(documentKey)).get(documentKey);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> documentKeys) {
        final Map<DocumentKey, MutableDocument> results = new HashMap<>();
        List<Object> bindVars = new ArrayList<>();
        for (DocumentKey key : documentKeys) {
            bindVars.add(EncodedPath.encode(key.getPath()));
            results.put(key, MutableDocument.newInvalidDocument(key));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT contents, read_time_seconds, read_time_nanos FROM remote_documents WHERE path IN (", bindVars, ") ORDER BY path");
        final BackgroundQueue backgroundQueue = new BackgroundQueue();
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda2
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m353xca14d1c3(backgroundQueue, results, (Cursor) obj);
                }
            });
        }
        backgroundQueue.drain();
        return results;
    }

    /* JADX INFO: renamed from: lambda$getAll$0$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache, reason: not valid java name */
    /* synthetic */ void m353xca14d1c3(BackgroundQueue backgroundQueue, Map results, Cursor row) {
        processRowInBackground(backgroundQueue, results, row, null);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(String collectionGroup, FieldIndex.IndexOffset offset, int limit) {
        List<ResourcePath> collectionParents = this.indexManager.getCollectionParents(collectionGroup);
        List<ResourcePath> collections = new ArrayList<>(collectionParents.size());
        for (ResourcePath collectionParent : collectionParents) {
            collections.add(collectionParent.append(collectionGroup));
        }
        if (collections.isEmpty()) {
            return Collections.emptyMap();
        }
        if (collections.size() * 9 < 900) {
            return getAll(collections, offset, limit, null);
        }
        Map<DocumentKey, MutableDocument> results = new HashMap<>();
        for (int i = 0; i < collections.size(); i += 100) {
            results.putAll(getAll(collections.subList(i, Math.min(collections.size(), i + 100)), offset, limit, null));
        }
        return Util.firstNEntries(results, limit, FieldIndex.IndexOffset.DOCUMENT_COMPARATOR);
    }

    private Map<DocumentKey, MutableDocument> getAll(List<ResourcePath> collections, FieldIndex.IndexOffset offset, int count, @Nullable final Function<MutableDocument, Boolean> filter, @Nullable final QueryContext context) {
        Timestamp readTime = offset.getReadTime().getTimestamp();
        DocumentKey documentKey = offset.getDocumentKey();
        StringBuilder sql = Util.repeatSequence("SELECT contents, read_time_seconds, read_time_nanos, path FROM remote_documents WHERE path >= ? AND path < ? AND path_length = ? AND (read_time_seconds > ? OR ( read_time_seconds = ? AND read_time_nanos > ?) OR ( read_time_seconds = ? AND read_time_nanos = ? and path > ?)) ", collections.size(), " UNION ");
        sql.append("ORDER BY read_time_seconds, read_time_nanos, path LIMIT ?");
        Object[] bindVars = new Object[(collections.size() * 9) + 1];
        int i = 0;
        for (ResourcePath collection : collections) {
            String prefixPath = EncodedPath.encode(collection);
            int i2 = i + 1;
            bindVars[i] = prefixPath;
            int i3 = i2 + 1;
            bindVars[i2] = EncodedPath.prefixSuccessor(prefixPath);
            int i4 = i3 + 1;
            bindVars[i3] = Integer.valueOf(collection.length() + 1);
            int i5 = i4 + 1;
            bindVars[i4] = Long.valueOf(readTime.getSeconds());
            int i6 = i5 + 1;
            bindVars[i5] = Long.valueOf(readTime.getSeconds());
            int i7 = i6 + 1;
            bindVars[i6] = Integer.valueOf(readTime.getNanoseconds());
            int i8 = i7 + 1;
            bindVars[i7] = Long.valueOf(readTime.getSeconds());
            int i9 = i8 + 1;
            bindVars[i8] = Integer.valueOf(readTime.getNanoseconds());
            bindVars[i9] = EncodedPath.encode(documentKey.getPath());
            i = i9 + 1;
        }
        bindVars[i] = Integer.valueOf(count);
        final BackgroundQueue backgroundQueue = new BackgroundQueue();
        final Map<DocumentKey, MutableDocument> results = new HashMap<>();
        this.db.query(sql.toString()).binding(bindVars).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m354xbda45604(backgroundQueue, results, filter, context, (Cursor) obj);
            }
        });
        backgroundQueue.drain();
        return results;
    }

    /* JADX INFO: renamed from: lambda$getAll$1$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache, reason: not valid java name */
    /* synthetic */ void m354xbda45604(BackgroundQueue backgroundQueue, Map results, Function filter, QueryContext context, Cursor row) {
        processRowInBackground(backgroundQueue, results, row, filter);
        if (context != null) {
            context.incrementDocumentReadCount();
        }
    }

    private Map<DocumentKey, MutableDocument> getAll(List<ResourcePath> collections, FieldIndex.IndexOffset offset, int count, @Nullable Function<MutableDocument, Boolean> filter) {
        return getAll(collections, offset, count, filter, null);
    }

    private void processRowInBackground(BackgroundQueue backgroundQueue, final Map<DocumentKey, MutableDocument> results, Cursor row, @Nullable final Function<MutableDocument, Boolean> filter) {
        final byte[] rawDocument = row.getBlob(0);
        final int readTimeSeconds = row.getInt(1);
        final int readTimeNanos = row.getInt(2);
        Executor executor = row.isLast() ? Executors.DIRECT_EXECUTOR : backgroundQueue;
        executor.execute(new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m355x16ef698(rawDocument, readTimeSeconds, readTimeNanos, filter, results);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$processRowInBackground$2$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache, reason: not valid java name */
    /* synthetic */ void m355x16ef698(byte[] rawDocument, int readTimeSeconds, int readTimeNanos, Function filter, Map results) {
        MutableDocument document = decodeMaybeDocument(rawDocument, readTimeSeconds, readTimeNanos);
        if (filter == null || ((Boolean) filter.apply(document)).booleanValue()) {
            synchronized (results) {
                results.put(document.getKey(), document);
            }
        }
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset offset, @Nonnull Set<DocumentKey> mutatedKeys) {
        return getDocumentsMatchingQuery(query, offset, mutatedKeys, null);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(final Query query, FieldIndex.IndexOffset offset, @Nonnull final Set<DocumentKey> mutatedKeys, @Nullable QueryContext context) {
        return getAll(Collections.singletonList(query.getPath()), offset, Integer.MAX_VALUE, new Function() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                MutableDocument mutableDocument = (MutableDocument) obj;
                return Boolean.valueOf(query.matches(mutableDocument) || mutatedKeys.contains(mutableDocument.getKey()));
            }
        }, context);
    }

    private MutableDocument decodeMaybeDocument(byte[] bytes, int readTimeSeconds, int readTimeNanos) {
        try {
            return this.serializer.decodeMaybeDocument(MaybeDocument.parseFrom(bytes)).setReadTime(new SnapshotVersion(new Timestamp(readTimeSeconds, readTimeNanos)));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("MaybeDocument failed to parse: %s", e);
        }
    }
}
