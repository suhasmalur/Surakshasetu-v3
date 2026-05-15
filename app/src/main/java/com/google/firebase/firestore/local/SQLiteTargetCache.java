package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.util.SparseArray;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.proto.Target;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.protobuf.InvalidProtocolBufferException;

/* JADX INFO: loaded from: classes10.dex */
final class SQLiteTargetCache implements TargetCache {
    private final SQLitePersistence db;
    private int highestTargetId;
    private long lastListenSequenceNumber;
    private SnapshotVersion lastRemoteSnapshotVersion = SnapshotVersion.NONE;
    private final LocalSerializer localSerializer;
    private long targetCount;

    SQLiteTargetCache(SQLitePersistence db, LocalSerializer localSerializer) {
        this.db = db;
        this.localSerializer = localSerializer;
    }

    void start() {
        int found = this.db.query("SELECT highest_target_id, highest_listen_sequence_number, last_remote_snapshot_version_seconds, last_remote_snapshot_version_nanos, target_count FROM target_globals LIMIT 1").first(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteTargetCache$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m371x4f660810((Cursor) obj);
            }
        });
        Assert.hardAssert(found == 1, "Missing target_globals entry", new Object[0]);
    }

    /* JADX INFO: renamed from: lambda$start$0$com-google-firebase-firestore-local-SQLiteTargetCache, reason: not valid java name */
    /* synthetic */ void m371x4f660810(Cursor row) {
        this.highestTargetId = row.getInt(0);
        this.lastListenSequenceNumber = row.getInt(1);
        this.lastRemoteSnapshotVersion = new SnapshotVersion(new Timestamp(row.getLong(2), row.getInt(3)));
        this.targetCount = row.getLong(4);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public int getHighestTargetId() {
        return this.highestTargetId;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public long getHighestListenSequenceNumber() {
        return this.lastListenSequenceNumber;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public long getTargetCount() {
        return this.targetCount;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void forEachTarget(final Consumer<TargetData> consumer) {
        this.db.query("SELECT target_proto FROM targets").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteTargetCache$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m368x1515438a(consumer, (Cursor) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$forEachTarget$1$com-google-firebase-firestore-local-SQLiteTargetCache, reason: not valid java name */
    /* synthetic */ void m368x1515438a(Consumer consumer, Cursor row) {
        consumer.accept(decodeTargetData(row.getBlob(0)));
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void setLastRemoteSnapshotVersion(SnapshotVersion snapshotVersion) {
        this.lastRemoteSnapshotVersion = snapshotVersion;
        writeMetadata();
    }

    private void saveTargetData(TargetData targetData) {
        int targetId = targetData.getTargetId();
        String canonicalId = targetData.getTarget().getCanonicalId();
        Timestamp version = targetData.getSnapshotVersion().getTimestamp();
        Target targetProto = this.localSerializer.encodeTargetData(targetData);
        this.db.execute("INSERT OR REPLACE INTO targets (target_id, canonical_id, snapshot_version_seconds, snapshot_version_nanos, resume_token, last_listen_sequence_number, target_proto) VALUES (?, ?, ?, ?, ?, ?, ?)", Integer.valueOf(targetId), canonicalId, Long.valueOf(version.getSeconds()), Integer.valueOf(version.getNanoseconds()), targetData.getResumeToken().toByteArray(), Long.valueOf(targetData.getSequenceNumber()), targetProto.toByteArray());
    }

    private boolean updateMetadata(TargetData targetData) {
        boolean wasUpdated = false;
        if (targetData.getTargetId() > this.highestTargetId) {
            this.highestTargetId = targetData.getTargetId();
            wasUpdated = true;
        }
        if (targetData.getSequenceNumber() > this.lastListenSequenceNumber) {
            this.lastListenSequenceNumber = targetData.getSequenceNumber();
            return true;
        }
        return wasUpdated;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void addTargetData(TargetData targetData) {
        saveTargetData(targetData);
        updateMetadata(targetData);
        this.targetCount++;
        writeMetadata();
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void updateTargetData(TargetData targetData) {
        saveTargetData(targetData);
        if (updateMetadata(targetData)) {
            writeMetadata();
        }
    }

    private void writeMetadata() {
        this.db.execute("UPDATE target_globals SET highest_target_id = ?, highest_listen_sequence_number = ?, last_remote_snapshot_version_seconds = ?, last_remote_snapshot_version_nanos = ?, target_count = ?", Integer.valueOf(this.highestTargetId), Long.valueOf(this.lastListenSequenceNumber), Long.valueOf(this.lastRemoteSnapshotVersion.getTimestamp().getSeconds()), Integer.valueOf(this.lastRemoteSnapshotVersion.getTimestamp().getNanoseconds()), Long.valueOf(this.targetCount));
    }

    private void removeTarget(int targetId) {
        removeMatchingKeysForTargetId(targetId);
        this.db.execute("DELETE FROM targets WHERE target_id = ?", Integer.valueOf(targetId));
        this.targetCount--;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeTargetData(TargetData targetData) {
        int targetId = targetData.getTargetId();
        removeTarget(targetId);
        writeMetadata();
    }

    int removeQueries(long upperBound, final SparseArray<?> activeTargetIds) {
        final int[] count = new int[1];
        this.db.query("SELECT target_id FROM targets WHERE last_listen_sequence_number <= ?").binding(Long.valueOf(upperBound)).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteTargetCache$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m370x1041d572(activeTargetIds, count, (Cursor) obj);
            }
        });
        writeMetadata();
        return count[0];
    }

    /* JADX INFO: renamed from: lambda$removeQueries$2$com-google-firebase-firestore-local-SQLiteTargetCache, reason: not valid java name */
    /* synthetic */ void m370x1041d572(SparseArray activeTargetIds, int[] count, Cursor row) {
        int targetId = row.getInt(0);
        if (activeTargetIds.get(targetId) == null) {
            removeTarget(targetId);
            count[0] = count[0] + 1;
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public TargetData getTargetData(final com.google.firebase.firestore.core.Target target) {
        String canonicalId = target.getCanonicalId();
        final TargetDataHolder result = new TargetDataHolder();
        this.db.query("SELECT target_proto FROM targets WHERE canonical_id = ?").binding(canonicalId).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteTargetCache$$ExternalSyntheticLambda4
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m369x4f05f442(target, result, (Cursor) obj);
            }
        });
        return result.targetData;
    }

    /* JADX INFO: renamed from: lambda$getTargetData$3$com-google-firebase-firestore-local-SQLiteTargetCache, reason: not valid java name */
    /* synthetic */ void m369x4f05f442(com.google.firebase.firestore.core.Target target, TargetDataHolder result, Cursor row) {
        TargetData found = decodeTargetData(row.getBlob(0));
        if (target.equals(found.getTarget())) {
            result.targetData = found;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class TargetDataHolder {
        TargetData targetData;

        private TargetDataHolder() {
        }
    }

    private TargetData decodeTargetData(byte[] bytes) {
        try {
            return this.localSerializer.decodeTargetData(Target.parseFrom(bytes));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("TargetData failed to parse: %s", e);
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void addMatchingKeys(ImmutableSortedSet<DocumentKey> keys, int targetId) {
        SQLiteStatement inserter = this.db.prepare("INSERT OR IGNORE INTO target_documents (target_id, path) VALUES (?, ?)");
        ReferenceDelegate delegate = this.db.getReferenceDelegate();
        for (DocumentKey key : keys) {
            String path = EncodedPath.encode(key.getPath());
            this.db.execute(inserter, Integer.valueOf(targetId), path);
            delegate.addReference(key);
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeMatchingKeys(ImmutableSortedSet<DocumentKey> keys, int targetId) {
        SQLiteStatement deleter = this.db.prepare("DELETE FROM target_documents WHERE target_id = ? AND path = ?");
        ReferenceDelegate delegate = this.db.getReferenceDelegate();
        for (DocumentKey key : keys) {
            String path = EncodedPath.encode(key.getPath());
            this.db.execute(deleter, Integer.valueOf(targetId), path);
            delegate.removeReference(key);
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeMatchingKeysForTargetId(int targetId) {
        this.db.execute("DELETE FROM target_documents WHERE target_id = ?", Integer.valueOf(targetId));
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public ImmutableSortedSet<DocumentKey> getMatchingKeysForTargetId(int targetId) {
        final DocumentKeysHolder holder = new DocumentKeysHolder();
        this.db.query("SELECT path FROM target_documents WHERE target_id = ?").binding(Integer.valueOf(targetId)).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteTargetCache$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteTargetCache.lambda$getMatchingKeysForTargetId$4(holder, (Cursor) obj);
            }
        });
        return holder.keys;
    }

    static /* synthetic */ void lambda$getMatchingKeysForTargetId$4(DocumentKeysHolder holder, Cursor row) {
        String path = row.getString(0);
        DocumentKey key = DocumentKey.fromPath(EncodedPath.decodeResourcePath(path));
        holder.keys = holder.keys.insert(key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class DocumentKeysHolder {
        ImmutableSortedSet<DocumentKey> keys;

        private DocumentKeysHolder() {
            this.keys = DocumentKey.emptyKeySet();
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public boolean containsKey(DocumentKey key) {
        String path = EncodedPath.encode(key.getPath());
        return !this.db.query("SELECT target_id FROM target_documents WHERE path = ? AND target_id != 0 LIMIT 1").binding(path).isEmpty();
    }
}
