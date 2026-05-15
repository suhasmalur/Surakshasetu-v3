package com.google.firebase.firestore.core;

import android.content.Context;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.TransactionOptions;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleReader;
import com.google.firebase.firestore.bundle.BundleSerializer;
import com.google.firebase.firestore.bundle.NamedQuery;
import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.core.View;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.firestore.util.Logger;
import com.google.firestore.v1.Value;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes10.dex */
public final class FirestoreClient {
    private static final String LOG_TAG = "FirestoreClient";
    private static final int MAX_CONCURRENT_LIMBO_RESOLUTIONS = 100;
    private final CredentialsProvider<String> appCheckProvider;
    private final AsyncQueue asyncQueue;
    private final CredentialsProvider<User> authProvider;
    private final BundleSerializer bundleSerializer;
    private final DatabaseInfo databaseInfo;
    private EventManager eventManager;
    private Scheduler gcScheduler;
    private Scheduler indexBackfillScheduler;
    private LocalStore localStore;
    private final GrpcMetadataProvider metadataProvider;
    private Persistence persistence;
    private RemoteStore remoteStore;
    private SyncEngine syncEngine;

    public FirestoreClient(final Context context, DatabaseInfo databaseInfo, final FirebaseFirestoreSettings settings, CredentialsProvider<User> authProvider, CredentialsProvider<String> appCheckProvider, final AsyncQueue asyncQueue, GrpcMetadataProvider metadataProvider) {
        this.databaseInfo = databaseInfo;
        this.authProvider = authProvider;
        this.appCheckProvider = appCheckProvider;
        this.asyncQueue = asyncQueue;
        this.metadataProvider = metadataProvider;
        this.bundleSerializer = new BundleSerializer(new RemoteSerializer(databaseInfo.getDatabaseId()));
        final TaskCompletionSource<User> firstUser = new TaskCompletionSource<>();
        final AtomicBoolean initialized = new AtomicBoolean(false);
        asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m292lambda$new$0$comgooglefirebasefirestorecoreFirestoreClient(firstUser, context, settings);
            }
        });
        authProvider.setChangeListener(new Listener() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda7
            @Override // com.google.firebase.firestore.util.Listener
            public final void onValue(Object obj) {
                this.f$0.m294lambda$new$2$comgooglefirebasefirestorecoreFirestoreClient(initialized, firstUser, asyncQueue, (User) obj);
            }
        });
        appCheckProvider.setChangeListener(new Listener() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda8
            @Override // com.google.firebase.firestore.util.Listener
            public final void onValue(Object obj) {
                FirestoreClient.lambda$new$3((String) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m292lambda$new$0$comgooglefirebasefirestorecoreFirestoreClient(TaskCompletionSource firstUser, Context context, FirebaseFirestoreSettings settings) {
        try {
            User initialUser = (User) Tasks.await(firstUser.getTask());
            initialize(context, initialUser, settings);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: renamed from: lambda$new$2$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m294lambda$new$2$comgooglefirebasefirestorecoreFirestoreClient(AtomicBoolean initialized, TaskCompletionSource firstUser, AsyncQueue asyncQueue, final User user) {
        if (initialized.compareAndSet(false, true)) {
            Assert.hardAssert(true ^ firstUser.getTask().isComplete(), "Already fulfilled first user task", new Object[0]);
            firstUser.setResult(user);
        } else {
            asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m293lambda$new$1$comgooglefirebasefirestorecoreFirestoreClient(user);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$new$1$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m293lambda$new$1$comgooglefirebasefirestorecoreFirestoreClient(User user) {
        Assert.hardAssert(this.syncEngine != null, "SyncEngine not yet initialized", new Object[0]);
        Logger.debug(LOG_TAG, "Credential changed. Current user: %s", user.getUid());
        this.syncEngine.handleCredentialChange(user);
    }

    static /* synthetic */ void lambda$new$3(String appCheckToken) {
    }

    public Task<Void> disableNetwork() {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m285x7ebb7879();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$disableNetwork$4$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m285x7ebb7879() {
        this.remoteStore.disableNetwork();
    }

    public Task<Void> enableNetwork() {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m286x445cc74d();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$enableNetwork$5$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m286x445cc74d() {
        this.remoteStore.enableNetwork();
    }

    public Task<Void> terminate() {
        this.authProvider.removeChangeListener();
        this.appCheckProvider.removeChangeListener();
        return this.asyncQueue.enqueueAndInitiateShutdown(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m299xd01fe2b6();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$terminate$6$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m299xd01fe2b6() {
        this.remoteStore.shutdown();
        this.persistence.shutdown();
        if (this.gcScheduler != null) {
            this.gcScheduler.stop();
        }
        if (this.indexBackfillScheduler != null) {
            this.indexBackfillScheduler.stop();
        }
    }

    public boolean isTerminated() {
        return this.asyncQueue.isShuttingDown();
    }

    public QueryListener listen(Query query, EventManager.ListenOptions options, EventListener<ViewSnapshot> listener) {
        verifyNotTerminated();
        final QueryListener queryListener = new QueryListener(query, options, listener);
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m290xbcc44995(queryListener);
            }
        });
        return queryListener;
    }

    /* JADX INFO: renamed from: lambda$listen$7$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m290xbcc44995(QueryListener queryListener) {
        this.eventManager.addQueryListener(queryListener);
    }

    public void stopListening(final QueryListener listener) {
        if (isTerminated()) {
            return;
        }
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m298x3c424f7c(listener);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$stopListening$8$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m298x3c424f7c(QueryListener listener) {
        this.eventManager.removeQueryListener(listener);
    }

    public Task<Document> getDocumentFromLocalCache(final DocumentKey docKey) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Callable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m287xcef7a7c2(docKey);
            }
        }).continueWith(new Continuation() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirestoreClient.lambda$getDocumentFromLocalCache$10(task);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getDocumentFromLocalCache$9$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ Document m287xcef7a7c2(DocumentKey docKey) throws Exception {
        return this.localStore.readDocument(docKey);
    }

    static /* synthetic */ Document lambda$getDocumentFromLocalCache$10(Task result) throws Exception {
        Document document = (Document) result.getResult();
        if (document.isFoundDocument()) {
            return document;
        }
        if (document.isNoDocument()) {
            return null;
        }
        throw new FirebaseFirestoreException("Failed to get document from cache. (However, this document may exist on the server. Run again without setting source to CACHE to attempt to retrieve the document from the server.)", FirebaseFirestoreException.Code.UNAVAILABLE);
    }

    public Task<ViewSnapshot> getDocumentsFromLocalCache(final Query query) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Callable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m288x6c1df5c8(query);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getDocumentsFromLocalCache$11$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ ViewSnapshot m288x6c1df5c8(Query query) throws Exception {
        QueryResult queryResult = this.localStore.executeQuery(query, true);
        View view = new View(query, queryResult.getRemoteKeys());
        View.DocumentChanges viewDocChanges = view.computeDocChanges(queryResult.getDocuments());
        return view.applyChanges(viewDocChanges).getSnapshot();
    }

    public Task<Void> write(final List<Mutation> mutations) {
        verifyNotTerminated();
        final TaskCompletionSource<Void> source = new TaskCompletionSource<>();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m302xabeef1a9(mutations, source);
            }
        });
        return source.getTask();
    }

    /* JADX INFO: renamed from: lambda$write$12$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m302xabeef1a9(List mutations, TaskCompletionSource source) {
        this.syncEngine.writeMutations(mutations, source);
    }

    public <TResult> Task<TResult> transaction(final TransactionOptions options, final Function<Transaction, Task<TResult>> updateFunction) {
        verifyNotTerminated();
        return AsyncQueue.callTask(this.asyncQueue.getExecutor(), new Callable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda23
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m300xd83b2b87(options, updateFunction);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$transaction$13$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ Task m300xd83b2b87(TransactionOptions options, Function updateFunction) throws Exception {
        return this.syncEngine.transaction(this.asyncQueue, options, updateFunction);
    }

    public Task<Map<String, Value>> runAggregateQuery(final Query query, final List<AggregateField> aggregateFields) {
        verifyNotTerminated();
        final TaskCompletionSource<Map<String, Value>> result = new TaskCompletionSource<>();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m296x8ccaf6ba(query, aggregateFields, result);
            }
        });
        return result.getTask();
    }

    /* JADX INFO: renamed from: lambda$runAggregateQuery$16$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m296x8ccaf6ba(Query query, List aggregateFields, final TaskCompletionSource result) {
        this.syncEngine.runAggregateQuery(query, aggregateFields).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda20
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                result.setResult((Map) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda21
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                result.setException(exc);
            }
        });
    }

    public Task<Void> waitForPendingWrites() {
        verifyNotTerminated();
        final TaskCompletionSource<Void> source = new TaskCompletionSource<>();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m301x5cd85a36(source);
            }
        });
        return source.getTask();
    }

    /* JADX INFO: renamed from: lambda$waitForPendingWrites$17$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m301x5cd85a36(TaskCompletionSource source) {
        this.syncEngine.registerPendingWritesTask(source);
    }

    private void initialize(Context context, User user, FirebaseFirestoreSettings settings) {
        ComponentProvider provider;
        Logger.debug(LOG_TAG, "Initializing. user=%s", user.getUid());
        Datastore datastore = new Datastore(this.databaseInfo, this.asyncQueue, this.authProvider, this.appCheckProvider, context, this.metadataProvider);
        ComponentProvider.Configuration configuration = new ComponentProvider.Configuration(context, this.asyncQueue, this.databaseInfo, datastore, user, 100, settings);
        if (settings.isPersistenceEnabled()) {
            provider = new SQLiteComponentProvider();
        } else {
            provider = new MemoryComponentProvider();
        }
        provider.initialize(configuration);
        this.persistence = provider.getPersistence();
        this.gcScheduler = provider.getGarbageCollectionScheduler();
        this.localStore = provider.getLocalStore();
        this.remoteStore = provider.getRemoteStore();
        this.syncEngine = provider.getSyncEngine();
        this.eventManager = provider.getEventManager();
        IndexBackfiller indexBackfiller = provider.getIndexBackfiller();
        if (this.gcScheduler != null) {
            this.gcScheduler.start();
        }
        if (indexBackfiller != null) {
            this.indexBackfillScheduler = indexBackfiller.getScheduler();
            this.indexBackfillScheduler.start();
        }
    }

    public void addSnapshotsInSyncListener(final EventListener<Void> listener) {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m282x66bcffc0(listener);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$addSnapshotsInSyncListener$18$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m282x66bcffc0(EventListener listener) {
        this.eventManager.addSnapshotsInSyncListener(listener);
    }

    public void loadBundle(InputStream bundleData, final LoadBundleTask resultTask) {
        verifyNotTerminated();
        final BundleReader bundleReader = new BundleReader(this.bundleSerializer, bundleData);
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m291x9f576345(bundleReader, resultTask);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$loadBundle$19$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m291x9f576345(BundleReader bundleReader, LoadBundleTask resultTask) {
        this.syncEngine.loadBundle(bundleReader, resultTask);
    }

    public Task<Query> getNamedQuery(final String queryName) {
        verifyNotTerminated();
        final TaskCompletionSource<Query> completionSource = new TaskCompletionSource<>();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m289xfe455012(queryName, completionSource);
            }
        });
        return completionSource.getTask();
    }

    /* JADX INFO: renamed from: lambda$getNamedQuery$20$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m289xfe455012(String queryName, TaskCompletionSource completionSource) {
        NamedQuery namedQuery = this.localStore.getNamedQuery(queryName);
        if (namedQuery != null) {
            Target target = namedQuery.getBundledQuery().getTarget();
            completionSource.setResult(new Query(target.getPath(), target.getCollectionGroup(), target.getFilters(), target.getOrderBy(), target.getLimit(), namedQuery.getBundledQuery().getLimitType(), target.getStartAt(), target.getEndAt()));
        } else {
            completionSource.setResult(null);
        }
    }

    public Task<Void> configureFieldIndexes(final List<FieldIndex> fieldIndices) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m283x7e96efd8(fieldIndices);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$configureFieldIndexes$21$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m283x7e96efd8(List fieldIndices) {
        this.localStore.configureFieldIndexes(fieldIndices);
    }

    public void setIndexAutoCreationEnabled(final boolean isEnabled) {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda22
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m297x6ec3220e(isEnabled);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setIndexAutoCreationEnabled$22$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m297x6ec3220e(boolean isEnabled) {
        this.localStore.setIndexAutoCreationEnabled(isEnabled);
    }

    public void deleteAllFieldIndexes() {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m284x996f55a6();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$deleteAllFieldIndexes$23$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m284x996f55a6() {
        this.localStore.deleteAllFieldIndexes();
    }

    public void removeSnapshotsInSyncListener(final EventListener<Void> listener) {
        if (isTerminated()) {
            return;
        }
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.core.FirestoreClient$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m295x16f11d28(listener);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$removeSnapshotsInSyncListener$24$com-google-firebase-firestore-core-FirestoreClient, reason: not valid java name */
    /* synthetic */ void m295x16f11d28(EventListener listener) {
        this.eventManager.removeSnapshotsInSyncListener(listener);
    }

    private void verifyNotTerminated() {
        if (isTerminated()) {
            throw new IllegalStateException("The client has already been terminated");
        }
    }
}
