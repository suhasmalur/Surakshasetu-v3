package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.proto.WriteBatch;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes10.dex */
final class SQLiteMutationQueue implements MutationQueue {
    private static final int BLOB_MAX_INLINE_LENGTH = 1000000;
    private final SQLitePersistence db;
    private final IndexManager indexManager;
    private ByteString lastStreamToken;
    private int nextBatchId;
    private final LocalSerializer serializer;
    private final String uid;

    SQLiteMutationQueue(SQLitePersistence persistence, LocalSerializer serializer, User user, IndexManager indexManager) {
        this.db = persistence;
        this.serializer = serializer;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
        this.lastStreamToken = WriteStream.EMPTY_STREAM_TOKEN;
        this.indexManager = indexManager;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void start() {
        loadNextBatchIdAcrossAllUsers();
        int rows = this.db.query("SELECT last_stream_token FROM mutation_queues WHERE uid = ?").binding(this.uid).first(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda5
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m351xf87fd3c7((Cursor) obj);
            }
        });
        if (rows == 0) {
            writeMutationQueueMetadata();
        }
    }

    /* JADX INFO: renamed from: lambda$start$0$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    /* synthetic */ void m351xf87fd3c7(Cursor row) {
        this.lastStreamToken = ByteString.copyFrom(row.getBlob(0));
    }

    private void loadNextBatchIdAcrossAllUsers() {
        final List<String> uids = new ArrayList<>();
        this.db.query("SELECT uid FROM mutation_queues").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda8
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                uids.add(((Cursor) obj).getString(0));
            }
        });
        this.nextBatchId = 0;
        for (String uid : uids) {
            this.db.query("SELECT MAX(batch_id) FROM mutations WHERE uid = ?").binding(uid).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda9
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m349xa3a174c9((Cursor) obj);
                }
            });
        }
        this.nextBatchId++;
    }

    /* JADX INFO: renamed from: lambda$loadNextBatchIdAcrossAllUsers$2$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    /* synthetic */ void m349xa3a174c9(Cursor row) {
        this.nextBatchId = Math.max(this.nextBatchId, row.getInt(0));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public boolean isEmpty() {
        return this.db.query("SELECT batch_id FROM mutations WHERE uid = ? LIMIT 1").binding(this.uid).isEmpty();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void acknowledgeBatch(MutationBatch batch, ByteString streamToken) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(streamToken);
        writeMutationQueueMetadata();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public ByteString getLastStreamToken() {
        return this.lastStreamToken;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void setLastStreamToken(ByteString streamToken) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(streamToken);
        writeMutationQueueMetadata();
    }

    private void writeMutationQueueMetadata() {
        this.db.execute("INSERT OR REPLACE INTO mutation_queues (uid, last_acknowledged_batch_id, last_stream_token) VALUES (?, ?, ?)", this.uid, -1, this.lastStreamToken.toByteArray());
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch addMutationBatch(Timestamp localWriteTime, List<Mutation> baseMutations, List<Mutation> mutations) {
        int batchId = this.nextBatchId;
        this.nextBatchId++;
        MutationBatch batch = new MutationBatch(batchId, localWriteTime, baseMutations, mutations);
        MessageLite proto = this.serializer.encodeMutationBatch(batch);
        this.db.execute("INSERT INTO mutations (uid, batch_id, mutations) VALUES (?, ?, ?)", this.uid, Integer.valueOf(batchId), proto.toByteArray());
        Set<DocumentKey> inserted = new HashSet<>();
        SQLiteStatement indexInserter = this.db.prepare("INSERT INTO document_mutations (uid, path, batch_id) VALUES (?, ?, ?)");
        for (Mutation mutation : mutations) {
            DocumentKey key = mutation.getKey();
            if (inserted.add(key)) {
                String path = EncodedPath.encode(key.getPath());
                this.db.execute(indexInserter, this.uid, path, Integer.valueOf(batchId));
                this.indexManager.addToCollectionParentIndex(key.getCollectionPath());
            }
        }
        return batch;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch lookupMutationBatch(final int batchId) {
        return (MutationBatch) this.db.query("SELECT SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? AND batch_id = ?").binding(1000000, this.uid, Integer.valueOf(batchId)).firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f$0.m350xb685bf9f(batchId, (Cursor) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$lookupMutationBatch$3$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    /* synthetic */ MutationBatch m350xb685bf9f(int batchId, Cursor row) {
        return decodeInlineMutationBatch(batchId, row.getBlob(0));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch getNextMutationBatchAfterBatchId(int batchId) {
        int nextBatchId = batchId + 1;
        return (MutationBatch) this.db.query("SELECT batch_id, SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? AND batch_id >= ? ORDER BY batch_id ASC LIMIT 1").binding(1000000, this.uid, Integer.valueOf(nextBatchId)).firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda10
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f$0.m348x6512fc44((Cursor) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getNextMutationBatchAfterBatchId$4$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    /* synthetic */ MutationBatch m348x6512fc44(Cursor row) {
        return decodeInlineMutationBatch(row.getInt(0), row.getBlob(1));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public int getHighestUnacknowledgedBatchId() {
        return ((Integer) this.db.query("SELECT IFNULL(MAX(batch_id), ?) FROM mutations WHERE uid = ?").binding(-1, this.uid).firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda4
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((Cursor) obj).getInt(0));
            }
        })).intValue();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatches() {
        final List<MutationBatch> result = new ArrayList<>();
        this.db.query("SELECT batch_id, SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? ORDER BY batch_id ASC").binding(1000000, this.uid).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda6
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m344x425a097f(result, (Cursor) obj);
            }
        });
        return result;
    }

    /* JADX INFO: renamed from: lambda$getAllMutationBatches$6$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    /* synthetic */ void m344x425a097f(List result, Cursor row) {
        result.add(decodeInlineMutationBatch(row.getInt(0), row.getBlob(1)));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKey(DocumentKey documentKey) {
        String path = EncodedPath.encode(documentKey.getPath());
        final List<MutationBatch> result = new ArrayList<>();
        this.db.query("SELECT m.batch_id, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path = ? AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id").binding(1000000, this.uid, path).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m345xb3096ab7(result, (Cursor) obj);
            }
        });
        return result;
    }

    /* JADX INFO: renamed from: lambda$getAllMutationBatchesAffectingDocumentKey$7$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    /* synthetic */ void m345xb3096ab7(List result, Cursor row) {
        result.add(decodeInlineMutationBatch(row.getInt(0), row.getBlob(1)));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKeys(Iterable<DocumentKey> documentKeys) {
        List<Object> args = new ArrayList<>();
        for (DocumentKey key : documentKeys) {
            args.add(EncodedPath.encode(key.getPath()));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT DISTINCT dm.batch_id, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path IN (", Arrays.asList(1000000, this.uid), args, ") AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id");
        final List<MutationBatch> result = new ArrayList<>();
        final Set<Integer> uniqueBatchIds = new HashSet<>();
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda11
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m346x1f85c24f(uniqueBatchIds, result, (Cursor) obj);
                }
            });
        }
        if (longQuery.getSubqueriesPerformed() > 1) {
            Collections.sort(result, new Comparator() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return Util.compareIntegers(((MutationBatch) obj).getBatchId(), ((MutationBatch) obj2).getBatchId());
                }
            });
        }
        return result;
    }

    /* JADX INFO: renamed from: lambda$getAllMutationBatchesAffectingDocumentKeys$8$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    /* synthetic */ void m346x1f85c24f(Set uniqueBatchIds, List result, Cursor row) {
        int batchId = row.getInt(0);
        if (!uniqueBatchIds.contains(Integer.valueOf(batchId))) {
            uniqueBatchIds.add(Integer.valueOf(batchId));
            result.add(decodeInlineMutationBatch(batchId, row.getBlob(1)));
        }
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingQuery(Query query) {
        Assert.hardAssert(!query.isCollectionGroupQuery(), "CollectionGroup queries should be handled in LocalDocumentsView", new Object[0]);
        ResourcePath prefix = query.getPath();
        final int immediateChildrenPathLength = prefix.length() + 1;
        String prefixPath = EncodedPath.encode(prefix);
        String prefixSuccessorPath = EncodedPath.prefixSuccessor(prefixPath);
        final List<MutationBatch> result = new ArrayList<>();
        this.db.query("SELECT dm.batch_id, dm.path, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path >= ? AND dm.path < ? AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id").binding(1000000, this.uid, prefixPath, prefixSuccessorPath).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m347xb8a43fc7(result, immediateChildrenPathLength, (Cursor) obj);
            }
        });
        return result;
    }

    /* JADX INFO: renamed from: lambda$getAllMutationBatchesAffectingQuery$10$com-google-firebase-firestore-local-SQLiteMutationQueue, reason: not valid java name */
    /* synthetic */ void m347xb8a43fc7(List result, int immediateChildrenPathLength, Cursor row) {
        int batchId = row.getInt(0);
        int size = result.size();
        if (size > 0 && batchId == ((MutationBatch) result.get(size - 1)).getBatchId()) {
            return;
        }
        ResourcePath path = EncodedPath.decodeResourcePath(row.getString(1));
        if (path.length() != immediateChildrenPathLength) {
            return;
        }
        result.add(decodeInlineMutationBatch(batchId, row.getBlob(2)));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void removeMutationBatch(MutationBatch batch) {
        SQLiteStatement mutationDeleter = this.db.prepare("DELETE FROM mutations WHERE uid = ? AND batch_id = ?");
        SQLiteStatement indexDeleter = this.db.prepare("DELETE FROM document_mutations WHERE uid = ? AND path = ? AND batch_id = ?");
        int batchId = batch.getBatchId();
        int deleted = this.db.execute(mutationDeleter, this.uid, Integer.valueOf(batchId));
        Assert.hardAssert(deleted != 0, "Mutation batch (%s, %d) did not exist", this.uid, Integer.valueOf(batch.getBatchId()));
        for (Mutation mutation : batch.getMutations()) {
            DocumentKey key = mutation.getKey();
            String path = EncodedPath.encode(key.getPath());
            this.db.execute(indexDeleter, this.uid, path, Integer.valueOf(batchId));
            this.db.getReferenceDelegate().removeMutationReference(key);
        }
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void performConsistencyCheck() {
        if (!isEmpty()) {
            return;
        }
        final List<ResourcePath> danglingMutationReferences = new ArrayList<>();
        this.db.query("SELECT path FROM document_mutations WHERE uid = ?").binding(this.uid).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteMutationQueue$$ExternalSyntheticLambda7
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteMutationQueue.lambda$performConsistencyCheck$11(danglingMutationReferences, (Cursor) obj);
            }
        });
        Assert.hardAssert(danglingMutationReferences.isEmpty(), "Document leak -- detected dangling mutation references when queue is empty. Dangling keys: %s", danglingMutationReferences);
    }

    static /* synthetic */ void lambda$performConsistencyCheck$11(List danglingMutationReferences, Cursor row) {
        ResourcePath path = EncodedPath.decodeResourcePath(row.getString(0));
        danglingMutationReferences.add(path);
    }

    private MutationBatch decodeInlineMutationBatch(int batchId, byte[] bytes) {
        try {
            if (bytes.length < 1000000) {
                return this.serializer.decodeMutationBatch(WriteBatch.parseFrom(bytes));
            }
            BlobAccumulator accumulator = new BlobAccumulator(bytes);
            while (accumulator.more) {
                int start = (accumulator.numChunks() * 1000000) + 1;
                this.db.query("SELECT SUBSTR(mutations, ?, ?) FROM mutations WHERE uid = ? AND batch_id = ?").binding(Integer.valueOf(start), 1000000, this.uid, Integer.valueOf(batchId)).first(accumulator);
            }
            ByteString blob = accumulator.result();
            return this.serializer.decodeMutationBatch(WriteBatch.parseFrom(blob));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("MutationBatch failed to parse: %s", e);
        }
    }

    private static class BlobAccumulator implements Consumer<Cursor> {
        private final ArrayList<ByteString> chunks = new ArrayList<>();
        private boolean more = true;

        BlobAccumulator(byte[] firstChunk) {
            addChunk(firstChunk);
        }

        int numChunks() {
            return this.chunks.size();
        }

        ByteString result() {
            return ByteString.copyFrom(this.chunks);
        }

        @Override // com.google.firebase.firestore.util.Consumer
        public void accept(Cursor row) {
            byte[] bytes = row.getBlob(0);
            addChunk(bytes);
            if (bytes.length < 1000000) {
                this.more = false;
            }
        }

        private void addChunk(byte[] bytes) {
            ByteString wrapped = ByteString.copyFrom(bytes);
            this.chunks.add(wrapped);
        }
    }
}
