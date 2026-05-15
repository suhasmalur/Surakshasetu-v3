package com.google.firebase.firestore.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteTransactionListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.FileUtil;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import com.google.firebase.firestore.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public final class SQLitePersistence extends Persistence {
    public static final int MAX_ARGS = 900;
    private final SQLiteBundleCache bundleCache;
    private SQLiteDatabase db;
    private final OpenHelper opener;
    private final SQLiteLruReferenceDelegate referenceDelegate;
    private final SQLiteRemoteDocumentCache remoteDocumentCache;
    private final LocalSerializer serializer;
    private boolean started;
    private final SQLiteTargetCache targetCache;
    private final SQLiteTransactionListener transactionListener;

    public static String databaseName(String persistenceKey, DatabaseId databaseId) {
        try {
            return "firestore." + URLEncoder.encode(persistenceKey, "utf-8") + "." + URLEncoder.encode(databaseId.getProjectId(), "utf-8") + "." + URLEncoder.encode(databaseId.getDatabaseId(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public SQLitePersistence(Context context, String persistenceKey, DatabaseId databaseId, LocalSerializer serializer, LruGarbageCollector.Params params) {
        this(serializer, params, new OpenHelper(context, serializer, databaseName(persistenceKey, databaseId)));
    }

    public SQLitePersistence(LocalSerializer serializer, LruGarbageCollector.Params params, OpenHelper openHelper) {
        this.transactionListener = new SQLiteTransactionListener() { // from class: com.google.firebase.firestore.local.SQLitePersistence.1
            @Override // android.database.sqlite.SQLiteTransactionListener
            public void onBegin() {
                SQLitePersistence.this.referenceDelegate.onTransactionStarted();
            }

            @Override // android.database.sqlite.SQLiteTransactionListener
            public void onCommit() {
                SQLitePersistence.this.referenceDelegate.onTransactionCommitted();
            }

            @Override // android.database.sqlite.SQLiteTransactionListener
            public void onRollback() {
            }
        };
        this.opener = openHelper;
        this.serializer = serializer;
        this.targetCache = new SQLiteTargetCache(this, this.serializer);
        this.bundleCache = new SQLiteBundleCache(this, this.serializer);
        this.remoteDocumentCache = new SQLiteRemoteDocumentCache(this, this.serializer);
        this.referenceDelegate = new SQLiteLruReferenceDelegate(this, params);
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public void start() {
        Assert.hardAssert(!this.started, "SQLitePersistence double-started!", new Object[0]);
        this.started = true;
        try {
            this.db = this.opener.getWritableDatabase();
            this.targetCache.start();
            this.referenceDelegate.start(this.targetCache.getHighestListenSequenceNumber());
        } catch (SQLiteDatabaseLockedException e) {
            throw new RuntimeException("Failed to gain exclusive lock to the Cloud Firestore client's offline persistence. This generally means you are using Cloud Firestore from multiple processes in your app. Keep in mind that multi-process Android apps execute the code in your Application class in all processes, so you may need to avoid initializing Cloud Firestore in your Application class. If you are intentionally using Cloud Firestore from multiple processes, you can only enable offline persistence (that is, call setPersistenceEnabled(true)) in one of them.", e);
        }
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public void shutdown() {
        Assert.hardAssert(this.started, "SQLitePersistence shutdown without start!", new Object[0]);
        this.started = false;
        this.db.close();
        this.db = null;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public boolean isStarted() {
        return this.started;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public SQLiteLruReferenceDelegate getReferenceDelegate() {
        return this.referenceDelegate;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    MutationQueue getMutationQueue(User user, IndexManager indexManager) {
        return new SQLiteMutationQueue(this, this.serializer, user, indexManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public SQLiteTargetCache getTargetCache() {
        return this.targetCache;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    IndexManager getIndexManager(User user) {
        return new SQLiteIndexManager(this, this.serializer, user);
    }

    @Override // com.google.firebase.firestore.local.Persistence
    BundleCache getBundleCache() {
        return this.bundleCache;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    DocumentOverlayCache getDocumentOverlayCache(User user) {
        return new SQLiteDocumentOverlayCache(this, this.serializer, user);
    }

    @Override // com.google.firebase.firestore.local.Persistence
    OverlayMigrationManager getOverlayMigrationManager() {
        return new SQLiteOverlayMigrationManager(this);
    }

    @Override // com.google.firebase.firestore.local.Persistence
    RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    void runTransaction(String action, Runnable operation) {
        Logger.debug(TAG, "Starting transaction: %s", action);
        this.db.beginTransactionWithListener(this.transactionListener);
        try {
            operation.run();
            this.db.setTransactionSuccessful();
        } finally {
            this.db.endTransaction();
        }
    }

    @Override // com.google.firebase.firestore.local.Persistence
    <T> T runTransaction(String action, Supplier<T> operation) {
        Logger.debug(TAG, "Starting transaction: %s", action);
        this.db.beginTransactionWithListener(this.transactionListener);
        try {
            T value = operation.get();
            this.db.setTransactionSuccessful();
            return value;
        } finally {
            this.db.endTransaction();
        }
    }

    public static void clearPersistence(Context context, DatabaseId databaseId, String persistenceKey) throws FirebaseFirestoreException {
        String databaseName = databaseName(persistenceKey, databaseId);
        String sqLitePath = context.getDatabasePath(databaseName).getPath();
        String journalPath = sqLitePath + "-journal";
        String walPath = sqLitePath + "-wal";
        File sqLiteFile = new File(sqLitePath);
        File journalFile = new File(journalPath);
        File walFile = new File(walPath);
        try {
            FileUtil.delete(sqLiteFile);
            FileUtil.delete(journalFile);
            FileUtil.delete(walFile);
        } catch (IOException e) {
            throw new FirebaseFirestoreException("Failed to clear persistence." + e, FirebaseFirestoreException.Code.UNKNOWN);
        }
    }

    long getByteSize() {
        return getPageCount() * getPageSize();
    }

    private long getPageSize() {
        return ((Long) query("PRAGMA page_size").firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLitePersistence$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return Long.valueOf(((Cursor) obj).getLong(0));
            }
        })).longValue();
    }

    private long getPageCount() {
        return ((Long) query("PRAGMA page_count").firstValue(new Function() { // from class: com.google.firebase.firestore.local.SQLitePersistence$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return Long.valueOf(((Cursor) obj).getLong(0));
            }
        })).longValue();
    }

    static class OpenHelper extends SQLiteOpenHelper {
        private boolean configured;
        private final LocalSerializer serializer;

        private OpenHelper(Context context, LocalSerializer serializer, String databaseName) {
            this(context, serializer, databaseName, 16);
        }

        OpenHelper(Context context, LocalSerializer serializer, String databaseName, int schemaVersion) {
            super(context, databaseName, (SQLiteDatabase.CursorFactory) null, schemaVersion);
            this.serializer = serializer;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase db) {
            this.configured = true;
            Cursor cursor = db.rawQuery("PRAGMA locking_mode = EXCLUSIVE", new String[0]);
            cursor.close();
        }

        private void ensureConfigured(SQLiteDatabase db) {
            if (!this.configured) {
                onConfigure(db);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            ensureConfigured(db);
            SQLiteSchema schema = new SQLiteSchema(db, this.serializer);
            schema.runSchemaUpgrades(0);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            ensureConfigured(db);
            SQLiteSchema schema = new SQLiteSchema(db, this.serializer);
            schema.runSchemaUpgrades(oldVersion);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            ensureConfigured(db);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase db) {
            ensureConfigured(db);
        }
    }

    void execute(String sql, Object... args) {
        this.db.execSQL(sql, args);
    }

    SQLiteStatement prepare(String sql) {
        return this.db.compileStatement(sql);
    }

    int execute(SQLiteStatement statement, Object... args) {
        statement.clearBindings();
        bind(statement, args);
        return statement.executeUpdateDelete();
    }

    Query query(String sql) {
        return new Query(this.db, sql);
    }

    static class Query {
        private SQLiteDatabase.CursorFactory cursorFactory;
        private final SQLiteDatabase db;
        private final String sql;

        Query(SQLiteDatabase db, String sql) {
            this.db = db;
            this.sql = sql;
        }

        Query binding(final Object... args) {
            this.cursorFactory = new SQLiteDatabase.CursorFactory() { // from class: com.google.firebase.firestore.local.SQLitePersistence$Query$$ExternalSyntheticLambda0
                @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
                public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                    return SQLitePersistence.Query.lambda$binding$0(args, sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
                }
            };
            return this;
        }

        static /* synthetic */ Cursor lambda$binding$0(Object[] args, SQLiteDatabase db1, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
            SQLitePersistence.bind(query, args);
            return new SQLiteCursor(masterQuery, editTable, query);
        }

        int forEach(Consumer<Cursor> consumer) {
            int rowsProcessed = 0;
            Cursor cursor = startQuery();
            while (cursor.moveToNext()) {
                try {
                    rowsProcessed++;
                    consumer.accept(cursor);
                } catch (Throwable th) {
                    if (cursor != null) {
                        try {
                            cursor.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return rowsProcessed;
        }

        int first(Consumer<Cursor> consumer) {
            Cursor cursor = startQuery();
            try {
                if (cursor.moveToFirst()) {
                    consumer.accept(cursor);
                    if (cursor != null) {
                        cursor.close();
                        return 1;
                    }
                    return 1;
                }
                if (cursor != null) {
                    cursor.close();
                    return 0;
                }
                return 0;
            } catch (Throwable th) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        <T> T firstValue(Function<Cursor, T> function) {
            Cursor cursor = startQuery();
            try {
                if (cursor.moveToFirst()) {
                    T tApply = function.apply(cursor);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return tApply;
                }
                if (cursor != null) {
                    cursor.close();
                    return null;
                }
                return null;
            } catch (Throwable th) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        boolean isEmpty() {
            Cursor cursor = startQuery();
            try {
                boolean z = !cursor.moveToFirst();
                if (cursor != null) {
                    cursor.close();
                }
                return z;
            } catch (Throwable th) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        private Cursor startQuery() {
            if (this.cursorFactory != null) {
                return this.db.rawQueryWithFactory(this.cursorFactory, this.sql, null, null);
            }
            return this.db.rawQuery(this.sql, null);
        }
    }

    static class LongQuery {
        private static final int LIMIT = 900;
        private final List<Object> argsHead;
        private final Iterator<Object> argsIter;
        private final SQLitePersistence db;
        private final String head;
        private int subqueriesPerformed;
        private final String tail;

        LongQuery(SQLitePersistence db, String head, List<Object> allArgs, String tail) {
            this.subqueriesPerformed = 0;
            this.db = db;
            this.head = head;
            this.argsHead = Collections.emptyList();
            this.tail = tail;
            this.argsIter = allArgs.iterator();
        }

        LongQuery(SQLitePersistence db, String head, List<Object> argsHead, List<Object> allArgs, String tail) {
            this.subqueriesPerformed = 0;
            this.db = db;
            this.head = head;
            this.argsHead = argsHead;
            this.tail = tail;
            this.argsIter = allArgs.iterator();
        }

        boolean hasMoreSubqueries() {
            return this.argsIter.hasNext();
        }

        private Object[] getNextSubqueryArgs() {
            List<Object> subqueryArgs = new ArrayList<>(this.argsHead);
            for (int i = 0; this.argsIter.hasNext() && i < 900 - this.argsHead.size(); i++) {
                subqueryArgs.add(this.argsIter.next());
            }
            return subqueryArgs.toArray();
        }

        Query performNextSubquery() {
            this.subqueriesPerformed++;
            Object[] subqueryArgs = getNextSubqueryArgs();
            return this.db.query(this.head + ((Object) Util.repeatSequence("?", subqueryArgs.length, ", ")) + this.tail).binding(subqueryArgs);
        }

        void executeNextSubquery() {
            this.subqueriesPerformed++;
            Object[] subqueryArgs = getNextSubqueryArgs();
            this.db.execute(this.head + ((Object) Util.repeatSequence("?", subqueryArgs.length, ", ")) + this.tail, subqueryArgs);
        }

        int getSubqueriesPerformed() {
            return this.subqueriesPerformed;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void bind(SQLiteProgram program, Object[] bindArgs) {
        for (int i = 0; i < bindArgs.length; i++) {
            Object arg = bindArgs[i];
            if (arg == null) {
                program.bindNull(i + 1);
            } else if (arg instanceof String) {
                program.bindString(i + 1, (String) arg);
            } else if (arg instanceof Integer) {
                program.bindLong(i + 1, ((Integer) arg).intValue());
            } else if (arg instanceof Long) {
                program.bindLong(i + 1, ((Long) arg).longValue());
            } else if (arg instanceof Double) {
                program.bindDouble(i + 1, ((Double) arg).doubleValue());
            } else if (arg instanceof byte[]) {
                program.bindBlob(i + 1, (byte[]) arg);
            } else {
                throw Assert.fail("Unknown argument %s of type %s", arg, arg.getClass());
            }
        }
    }
}
