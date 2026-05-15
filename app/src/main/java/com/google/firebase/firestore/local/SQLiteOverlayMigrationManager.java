package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes10.dex */
public class SQLiteOverlayMigrationManager implements OverlayMigrationManager {
    private final SQLitePersistence db;

    public SQLiteOverlayMigrationManager(SQLitePersistence persistence) {
        this.db = persistence;
    }

    @Override // com.google.firebase.firestore.local.OverlayMigrationManager
    public void run() {
        buildOverlays();
    }

    private void buildOverlays() {
        this.db.runTransaction("build overlays", new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteOverlayMigrationManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m352xe3799d5d();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$buildOverlays$0$com-google-firebase-firestore-local-SQLiteOverlayMigrationManager, reason: not valid java name */
    /* synthetic */ void m352xe3799d5d() {
        if (!hasPendingOverlayMigration()) {
            return;
        }
        Set<String> userIds = getAllUserIds();
        RemoteDocumentCache remoteDocumentCache = this.db.getRemoteDocumentCache();
        for (String uid : userIds) {
            User user = new User(uid);
            MutationQueue mutationQueue = this.db.getMutationQueue(user, this.db.getIndexManager(user));
            Set<DocumentKey> allDocumentKeys = new HashSet<>();
            List<MutationBatch> batches = mutationQueue.getAllMutationBatches();
            for (MutationBatch batch : batches) {
                allDocumentKeys.addAll(batch.getKeys());
            }
            DocumentOverlayCache documentOverlayCache = this.db.getDocumentOverlayCache(user);
            LocalDocumentsView localView = new LocalDocumentsView(remoteDocumentCache, mutationQueue, documentOverlayCache, this.db.getIndexManager(user));
            localView.recalculateAndSaveOverlays(allDocumentKeys);
        }
        removePendingOverlayMigrations();
    }

    private Set<String> getAllUserIds() {
        final Set<String> uids = new HashSet<>();
        this.db.query("SELECT DISTINCT uid FROM mutation_queues").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteOverlayMigrationManager$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                uids.add(((Cursor) obj).getString(0));
            }
        });
        return uids;
    }

    boolean hasPendingOverlayMigration() {
        final Boolean[] result = {false};
        this.db.query("SELECT migration_name FROM data_migrations").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteOverlayMigrationManager$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteOverlayMigrationManager.lambda$hasPendingOverlayMigration$2(result, (Cursor) obj);
            }
        });
        return result[0].booleanValue();
    }

    static /* synthetic */ void lambda$hasPendingOverlayMigration$2(Boolean[] result, Cursor row) {
        try {
            if (Persistence.DATA_MIGRATION_BUILD_OVERLAYS.equals(row.getString(0))) {
                result[0] = true;
            }
        } catch (IllegalArgumentException e) {
            throw Assert.fail("SQLitePersistence.DataMigration failed to parse: %s", e);
        }
    }

    private void removePendingOverlayMigrations() {
        this.db.execute("DELETE FROM data_migrations WHERE migration_name = ?", Persistence.DATA_MIGRATION_BUILD_OVERLAYS);
    }
}
