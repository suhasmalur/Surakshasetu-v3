package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.NamedQuery;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Function;
import com.google.firestore.bundle.BundledQuery;
import com.google.protobuf.InvalidProtocolBufferException;

/* JADX INFO: loaded from: classes10.dex */
class SQLiteBundleCache implements BundleCache {
    private final SQLitePersistence db;
    private final LocalSerializer serializer;

    SQLiteBundleCache(SQLitePersistence persistence, LocalSerializer serializer) {
        this.db = persistence;
        this.serializer = serializer;
    }

    @Override // com.google.firebase.firestore.local.BundleCache
    public BundleMetadata getBundleMetadata(final String bundleId) {
        return (BundleMetadata) this.db.query("SELECT schema_version, create_time_seconds, create_time_nanos, total_documents,  total_bytes FROM bundles WHERE bundle_id = ?").binding(bundleId).firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteBundleCache$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return SQLiteBundleCache.lambda$getBundleMetadata$0(bundleId, (Cursor) obj);
            }
        });
    }

    static /* synthetic */ BundleMetadata lambda$getBundleMetadata$0(String bundleId, Cursor row) {
        if (row == null) {
            return null;
        }
        return new BundleMetadata(bundleId, row.getInt(0), new SnapshotVersion(new Timestamp(row.getLong(1), row.getInt(2))), row.getInt(3), row.getLong(4));
    }

    @Override // com.google.firebase.firestore.local.BundleCache
    public void saveBundleMetadata(BundleMetadata metadata) {
        this.db.execute("INSERT OR REPLACE INTO bundles (bundle_id, schema_version, create_time_seconds, create_time_nanos, total_documents, total_bytes) VALUES (?, ?, ?, ?, ?, ?)", metadata.getBundleId(), Integer.valueOf(metadata.getSchemaVersion()), Long.valueOf(metadata.getCreateTime().getTimestamp().getSeconds()), Integer.valueOf(metadata.getCreateTime().getTimestamp().getNanoseconds()), Integer.valueOf(metadata.getTotalDocuments()), Long.valueOf(metadata.getTotalBytes()));
    }

    @Override // com.google.firebase.firestore.local.BundleCache
    public NamedQuery getNamedQuery(final String queryName) {
        return (NamedQuery) this.db.query("SELECT read_time_seconds, read_time_nanos, bundled_query_proto FROM named_queries WHERE name = ?").binding(queryName).firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLiteBundleCache$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f$0.m333x512655a3(queryName, (Cursor) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getNamedQuery$1$com-google-firebase-firestore-local-SQLiteBundleCache, reason: not valid java name */
    /* synthetic */ NamedQuery m333x512655a3(String queryName, Cursor row) {
        if (row != null) {
            try {
                BundledQuery bundledQuery = BundledQuery.parseFrom(row.getBlob(2));
                return new NamedQuery(queryName, this.serializer.decodeBundledQuery(bundledQuery), new SnapshotVersion(new Timestamp(row.getLong(0), row.getInt(1))));
            } catch (InvalidProtocolBufferException e) {
                throw Assert.fail("NamedQuery failed to parse: %s", e);
            }
        }
        return null;
    }

    @Override // com.google.firebase.firestore.local.BundleCache
    public void saveNamedQuery(NamedQuery query) {
        BundledQuery bundledQuery = this.serializer.encodeBundledQuery(query.getBundledQuery());
        this.db.execute("INSERT OR REPLACE INTO named_queries (name, read_time_seconds, read_time_nanos, bundled_query_proto) VALUES (?, ?, ?, ?)", query.getName(), Long.valueOf(query.getReadTime().getTimestamp().getSeconds()), Integer.valueOf(query.getReadTime().getTimestamp().getNanoseconds()), bundledQuery.toByteArray());
    }
}
