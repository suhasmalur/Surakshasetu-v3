package com.google.firebase.firestore;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.FirebaseAppCheckTokenProvider;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.remote.FirestoreChannel;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ByteBufferInputStream;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.inject.Deferred;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes10.dex */
public class FirebaseFirestore {
    private static final String TAG = "FirebaseFirestore";
    private final CredentialsProvider<String> appCheckProvider;
    private final AsyncQueue asyncQueue;
    private final CredentialsProvider<User> authProvider;
    private volatile FirestoreClient client;
    private final Context context;
    private final DatabaseId databaseId;
    private EmulatedServiceSettings emulatorSettings;
    private final FirebaseApp firebaseApp;
    private final InstanceRegistry instanceRegistry;
    private final GrpcMetadataProvider metadataProvider;
    private final String persistenceKey;
    private PersistentCacheIndexManager persistentCacheIndexManager;
    private FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
    private final UserDataReader userDataReader;

    public interface InstanceRegistry {
        void remove(String str);
    }

    private static FirebaseApp getDefaultFirebaseApp() {
        FirebaseApp app = FirebaseApp.getInstance();
        if (app == null) {
            throw new IllegalStateException("You must call FirebaseApp.initializeApp first.");
        }
        return app;
    }

    public static FirebaseFirestore getInstance() {
        return getInstance(getDefaultFirebaseApp(), "(default)");
    }

    public static FirebaseFirestore getInstance(FirebaseApp app) {
        return getInstance(app, "(default)");
    }

    public static FirebaseFirestore getInstance(String database) {
        return getInstance(getDefaultFirebaseApp(), database);
    }

    public static FirebaseFirestore getInstance(FirebaseApp app, String database) {
        Preconditions.checkNotNull(app, "Provided FirebaseApp must not be null.");
        Preconditions.checkNotNull(database, "Provided database name must not be null.");
        FirestoreMultiDbComponent component = (FirestoreMultiDbComponent) app.get(FirestoreMultiDbComponent.class);
        Preconditions.checkNotNull(component, "Firestore component is not present.");
        return component.get(database);
    }

    static FirebaseFirestore newInstance(Context context, FirebaseApp app, Deferred<InternalAuthProvider> deferredAuthProvider, Deferred<InteropAppCheckTokenProvider> deferredAppCheckTokenProvider, String database, InstanceRegistry instanceRegistry, GrpcMetadataProvider metadataProvider) {
        String projectId = app.getOptions().getProjectId();
        if (projectId == null) {
            throw new IllegalArgumentException("FirebaseOptions.getProjectId() cannot be null");
        }
        DatabaseId databaseId = DatabaseId.forDatabase(projectId, database);
        AsyncQueue queue = new AsyncQueue();
        CredentialsProvider<User> authProvider = new FirebaseAuthCredentialsProvider(deferredAuthProvider);
        CredentialsProvider<String> appCheckProvider = new FirebaseAppCheckTokenProvider(deferredAppCheckTokenProvider);
        String persistenceKey = app.getName();
        FirebaseFirestore firestore = new FirebaseFirestore(context, databaseId, persistenceKey, authProvider, appCheckProvider, queue, app, instanceRegistry, metadataProvider);
        return firestore;
    }

    FirebaseFirestore(Context context, DatabaseId databaseId, String persistenceKey, CredentialsProvider<User> authProvider, CredentialsProvider<String> appCheckProvider, AsyncQueue asyncQueue, FirebaseApp firebaseApp, InstanceRegistry instanceRegistry, GrpcMetadataProvider metadataProvider) {
        this.context = (Context) Preconditions.checkNotNull(context);
        this.databaseId = (DatabaseId) Preconditions.checkNotNull((DatabaseId) Preconditions.checkNotNull(databaseId));
        this.userDataReader = new UserDataReader(databaseId);
        this.persistenceKey = (String) Preconditions.checkNotNull(persistenceKey);
        this.authProvider = (CredentialsProvider) Preconditions.checkNotNull(authProvider);
        this.appCheckProvider = (CredentialsProvider) Preconditions.checkNotNull(appCheckProvider);
        this.asyncQueue = (AsyncQueue) Preconditions.checkNotNull(asyncQueue);
        this.firebaseApp = firebaseApp;
        this.instanceRegistry = instanceRegistry;
        this.metadataProvider = metadataProvider;
    }

    public FirebaseFirestoreSettings getFirestoreSettings() {
        return this.settings;
    }

    public void setFirestoreSettings(FirebaseFirestoreSettings settings) {
        FirebaseFirestoreSettings settings2 = mergeEmulatorSettings(settings, this.emulatorSettings);
        synchronized (this.databaseId) {
            Preconditions.checkNotNull(settings2, "Provided settings must not be null.");
            if (this.client != null && !this.settings.equals(settings2)) {
                throw new IllegalStateException("FirebaseFirestore has already been started and its settings can no longer be changed. You can only call setFirestoreSettings() before calling any other methods on a FirebaseFirestore object.");
            }
            this.settings = settings2;
        }
    }

    public void useEmulator(String host, int port) {
        if (this.client != null) {
            throw new IllegalStateException("Cannot call useEmulator() after instance has already been initialized.");
        }
        this.emulatorSettings = new EmulatedServiceSettings(host, port);
        this.settings = mergeEmulatorSettings(this.settings, this.emulatorSettings);
    }

    private void ensureClientConfigured() {
        if (this.client != null) {
            return;
        }
        synchronized (this.databaseId) {
            if (this.client != null) {
                return;
            }
            DatabaseInfo databaseInfo = new DatabaseInfo(this.databaseId, this.persistenceKey, this.settings.getHost(), this.settings.isSslEnabled());
            this.client = new FirestoreClient(this.context, databaseInfo, this.settings, this.authProvider, this.appCheckProvider, this.asyncQueue, this.metadataProvider);
        }
    }

    private FirebaseFirestoreSettings mergeEmulatorSettings(FirebaseFirestoreSettings settings, EmulatedServiceSettings emulatorSettings) {
        if (emulatorSettings == null) {
            return settings;
        }
        if (!FirebaseFirestoreSettings.DEFAULT_HOST.equals(settings.getHost())) {
            Logger.warn(TAG, "Host has been set in FirebaseFirestoreSettings and useEmulator, emulator host will be used.", new Object[0]);
        }
        return new FirebaseFirestoreSettings.Builder(settings).setHost(emulatorSettings.getHost() + ":" + emulatorSettings.getPort()).setSslEnabled(false).build();
    }

    public FirebaseApp getApp() {
        return this.firebaseApp;
    }

    @Deprecated
    public Task<Void> setIndexConfiguration(String json) {
        ensureClientConfigured();
        Preconditions.checkState(this.settings.isPersistenceEnabled(), "Cannot enable indexes when persistence is disabled");
        List<FieldIndex> parsedIndexes = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("indexes")) {
                JSONArray indexes = jsonObject.getJSONArray("indexes");
                for (int i = 0; i < indexes.length(); i++) {
                    JSONObject definition = indexes.getJSONObject(i);
                    String collectionGroup = definition.getString("collectionGroup");
                    List<FieldIndex.Segment> segments = new ArrayList<>();
                    JSONArray fields = definition.optJSONArray("fields");
                    for (int f = 0; fields != null && f < fields.length(); f++) {
                        JSONObject field = fields.getJSONObject(f);
                        com.google.firebase.firestore.model.FieldPath fieldPath = com.google.firebase.firestore.model.FieldPath.fromServerFormat(field.getString("fieldPath"));
                        if ("CONTAINS".equals(field.optString("arrayConfig"))) {
                            segments.add(FieldIndex.Segment.create(fieldPath, FieldIndex.Segment.Kind.CONTAINS));
                        } else if ("ASCENDING".equals(field.optString("order"))) {
                            segments.add(FieldIndex.Segment.create(fieldPath, FieldIndex.Segment.Kind.ASCENDING));
                        } else {
                            segments.add(FieldIndex.Segment.create(fieldPath, FieldIndex.Segment.Kind.DESCENDING));
                        }
                    }
                    parsedIndexes.add(FieldIndex.create(-1, collectionGroup, segments, FieldIndex.INITIAL_STATE));
                }
            }
            return this.client.configureFieldIndexes(parsedIndexes);
        } catch (JSONException e) {
            throw new IllegalArgumentException("Failed to parse index configuration", e);
        }
    }

    public synchronized PersistentCacheIndexManager getPersistentCacheIndexManager() {
        ensureClientConfigured();
        if (this.persistentCacheIndexManager == null && (this.settings.isPersistenceEnabled() || (this.settings.getCacheSettings() instanceof PersistentCacheSettings))) {
            this.persistentCacheIndexManager = new PersistentCacheIndexManager(this.client);
        }
        return this.persistentCacheIndexManager;
    }

    public CollectionReference collection(String collectionPath) {
        Preconditions.checkNotNull(collectionPath, "Provided collection path must not be null.");
        ensureClientConfigured();
        return new CollectionReference(ResourcePath.fromString(collectionPath), this);
    }

    public DocumentReference document(String documentPath) {
        Preconditions.checkNotNull(documentPath, "Provided document path must not be null.");
        ensureClientConfigured();
        return DocumentReference.forPath(ResourcePath.fromString(documentPath), this);
    }

    public Query collectionGroup(String collectionId) {
        Preconditions.checkNotNull(collectionId, "Provided collection ID must not be null.");
        if (collectionId.contains("/")) {
            throw new IllegalArgumentException(String.format("Invalid collectionId '%s'. Collection IDs must not contain '/'.", collectionId));
        }
        ensureClientConfigured();
        return new Query(new com.google.firebase.firestore.core.Query(ResourcePath.EMPTY, collectionId), this);
    }

    private <ResultT> Task<ResultT> runTransaction(TransactionOptions options, final Transaction.Function<ResultT> updateFunction, final Executor executor) {
        ensureClientConfigured();
        Function<com.google.firebase.firestore.core.Transaction, Task<ResultT>> wrappedUpdateFunction = new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f$0.m267x911046dd(executor, updateFunction, (com.google.firebase.firestore.core.Transaction) obj);
            }
        };
        return this.client.transaction(options, wrappedUpdateFunction);
    }

    /* JADX INFO: renamed from: lambda$runTransaction$1$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ Task m267x911046dd(Executor executor, final Transaction.Function updateFunction, final com.google.firebase.firestore.core.Transaction internalTransaction) {
        return Tasks.call(executor, new Callable() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m266x9186acdc(updateFunction, internalTransaction);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$runTransaction$0$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ Object m266x9186acdc(Transaction.Function updateFunction, com.google.firebase.firestore.core.Transaction internalTransaction) throws Exception {
        return updateFunction.apply(new Transaction(internalTransaction, this));
    }

    public <TResult> Task<TResult> runTransaction(Transaction.Function<TResult> updateFunction) {
        return runTransaction(TransactionOptions.DEFAULT, updateFunction);
    }

    public <TResult> Task<TResult> runTransaction(TransactionOptions options, Transaction.Function<TResult> updateFunction) {
        Preconditions.checkNotNull(updateFunction, "Provided transaction update function must not be null.");
        return runTransaction(options, updateFunction, com.google.firebase.firestore.core.Transaction.getDefaultExecutor());
    }

    public WriteBatch batch() {
        ensureClientConfigured();
        return new WriteBatch(this);
    }

    public Task<Void> runBatch(WriteBatch.Function batchFunction) {
        WriteBatch batch = batch();
        batchFunction.apply(batch);
        return batch.commit();
    }

    public Task<Void> terminate() {
        this.instanceRegistry.remove(getDatabaseId().getDatabaseId());
        ensureClientConfigured();
        return this.client.terminate();
    }

    public Task<Void> waitForPendingWrites() {
        ensureClientConfigured();
        return this.client.waitForPendingWrites();
    }

    AsyncQueue getAsyncQueue() {
        return this.asyncQueue;
    }

    public Task<Void> enableNetwork() {
        ensureClientConfigured();
        return this.client.enableNetwork();
    }

    public Task<Void> disableNetwork() {
        ensureClientConfigured();
        return this.client.disableNetwork();
    }

    public static void setLoggingEnabled(boolean loggingEnabled) {
        if (loggingEnabled) {
            Logger.setLogLevel(Logger.Level.DEBUG);
        } else {
            Logger.setLogLevel(Logger.Level.WARN);
        }
    }

    public Task<Void> clearPersistence() {
        final TaskCompletionSource<Void> source = new TaskCompletionSource<>();
        this.asyncQueue.enqueueAndForgetEvenAfterShutdown(new Runnable() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m264x422b1e9d(source);
            }
        });
        return source.getTask();
    }

    /* JADX INFO: renamed from: lambda$clearPersistence$2$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ void m264x422b1e9d(TaskCompletionSource source) {
        try {
            if (this.client != null && !this.client.isTerminated()) {
                throw new FirebaseFirestoreException("Persistence cannot be cleared while the firestore instance is running.", FirebaseFirestoreException.Code.FAILED_PRECONDITION);
            }
            SQLitePersistence.clearPersistence(this.context, this.databaseId, this.persistenceKey);
            source.setResult(null);
        } catch (FirebaseFirestoreException e) {
            source.setException(e);
        }
    }

    public ListenerRegistration addSnapshotsInSyncListener(Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, runnable);
    }

    public ListenerRegistration addSnapshotsInSyncListener(Activity activity, Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, activity, runnable);
    }

    public ListenerRegistration addSnapshotsInSyncListener(Executor executor, Runnable runnable) {
        return addSnapshotsInSyncListener(executor, null, runnable);
    }

    public LoadBundleTask loadBundle(InputStream bundleData) {
        ensureClientConfigured();
        LoadBundleTask resultTask = new LoadBundleTask();
        this.client.loadBundle(bundleData, resultTask);
        return resultTask;
    }

    public LoadBundleTask loadBundle(byte[] bundleData) {
        return loadBundle(new ByteArrayInputStream(bundleData));
    }

    public LoadBundleTask loadBundle(ByteBuffer bundleData) {
        return loadBundle(new ByteBufferInputStream(bundleData));
    }

    public Task<Query> getNamedQuery(String name) {
        ensureClientConfigured();
        return this.client.getNamedQuery(name).continueWith(new Continuation() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m265xe4dfadf7(task);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getNamedQuery$3$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ Query m265xe4dfadf7(Task task) throws Exception {
        com.google.firebase.firestore.core.Query query = (com.google.firebase.firestore.core.Query) task.getResult();
        if (query != null) {
            return new Query(query, this);
        }
        return null;
    }

    private ListenerRegistration addSnapshotsInSyncListener(Executor userExecutor, Activity activity, final Runnable runnable) {
        ensureClientConfigured();
        EventListener<Void> eventListener = new EventListener() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda4
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                FirebaseFirestore.lambda$addSnapshotsInSyncListener$4(runnable, (Void) obj, firebaseFirestoreException);
            }
        };
        final AsyncEventListener<Void> asyncListener = new AsyncEventListener<>(userExecutor, eventListener);
        this.client.addSnapshotsInSyncListener(asyncListener);
        return ActivityScope.bind(activity, new ListenerRegistration() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda5
            @Override // com.google.firebase.firestore.ListenerRegistration
            public final void remove() {
                this.f$0.m263xb65623b0(asyncListener);
            }
        });
    }

    static /* synthetic */ void lambda$addSnapshotsInSyncListener$4(Runnable runnable, Void v, FirebaseFirestoreException error) {
        Assert.hardAssert(error == null, "snapshots-in-sync listeners should never get errors.", new Object[0]);
        runnable.run();
    }

    /* JADX INFO: renamed from: lambda$addSnapshotsInSyncListener$5$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ void m263xb65623b0(AsyncEventListener asyncListener) {
        asyncListener.mute();
        this.client.removeSnapshotsInSyncListener(asyncListener);
    }

    FirestoreClient getClient() {
        return this.client;
    }

    DatabaseId getDatabaseId() {
        return this.databaseId;
    }

    UserDataReader getUserDataReader() {
        return this.userDataReader;
    }

    void validateReference(DocumentReference docRef) {
        Preconditions.checkNotNull(docRef, "Provided DocumentReference must not be null.");
        if (docRef.getFirestore() != this) {
            throw new IllegalArgumentException("Provided document reference is from a different Cloud Firestore instance.");
        }
    }

    static void setClientLanguage(String languageToken) {
        FirestoreChannel.setClientLanguage(languageToken);
    }
}
