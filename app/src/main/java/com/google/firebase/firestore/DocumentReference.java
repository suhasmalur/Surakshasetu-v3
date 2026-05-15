package com.google.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.core.ListenerRegistrationImpl;
import com.google.firebase.firestore.core.QueryListener;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes10.dex */
public class DocumentReference {
    private final FirebaseFirestore firestore;
    private final DocumentKey key;

    DocumentReference(DocumentKey key, FirebaseFirestore firestore) {
        this.key = (DocumentKey) Preconditions.checkNotNull(key);
        this.firestore = firestore;
    }

    static DocumentReference forPath(ResourcePath path, FirebaseFirestore firestore) {
        if (path.length() % 2 != 0) {
            throw new IllegalArgumentException("Invalid document reference. Document references must have an even number of segments, but " + path.canonicalString() + " has " + path.length());
        }
        return new DocumentReference(DocumentKey.fromPath(path), firestore);
    }

    DocumentKey getKey() {
        return this.key;
    }

    public FirebaseFirestore getFirestore() {
        return this.firestore;
    }

    public String getId() {
        return this.key.getDocumentId();
    }

    public CollectionReference getParent() {
        return new CollectionReference(this.key.getCollectionPath(), this.firestore);
    }

    public String getPath() {
        return this.key.getPath().canonicalString();
    }

    public CollectionReference collection(String collectionPath) {
        Preconditions.checkNotNull(collectionPath, "Provided collection path must not be null.");
        return new CollectionReference(this.key.getPath().append(ResourcePath.fromString(collectionPath)), this.firestore);
    }

    public Task<Void> set(Object data) {
        return set(data, SetOptions.OVERWRITE);
    }

    public Task<Void> set(Object data, SetOptions options) {
        UserData.ParsedSetData parsed;
        Preconditions.checkNotNull(data, "Provided data must not be null.");
        Preconditions.checkNotNull(options, "Provided options must not be null.");
        if (options.isMerge()) {
            parsed = this.firestore.getUserDataReader().parseMergeData(data, options.getFieldMask());
        } else {
            parsed = this.firestore.getUserDataReader().parseSetData(data);
        }
        return this.firestore.getClient().write(Collections.singletonList(parsed.toMutation(this.key, Precondition.NONE))).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<Void> update(Map<String, Object> data) {
        UserData.ParsedUpdateData parsedData = this.firestore.getUserDataReader().parseUpdateData(data);
        return update(parsedData);
    }

    public Task<Void> update(String field, Object value, Object... moreFieldsAndValues) {
        UserData.ParsedUpdateData parsedData = this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, field, value, moreFieldsAndValues));
        return update(parsedData);
    }

    public Task<Void> update(FieldPath fieldPath, Object value, Object... moreFieldsAndValues) {
        UserData.ParsedUpdateData parsedData = this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, value, moreFieldsAndValues));
        return update(parsedData);
    }

    private Task<Void> update(UserData.ParsedUpdateData parsedData) {
        return this.firestore.getClient().write(Collections.singletonList(parsedData.toMutation(this.key, Precondition.exists(true)))).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<Void> delete() {
        return this.firestore.getClient().write(Collections.singletonList(new DeleteMutation(this.key, Precondition.NONE))).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<DocumentSnapshot> get() {
        return get(Source.DEFAULT);
    }

    public Task<DocumentSnapshot> get(Source source) {
        if (source == Source.CACHE) {
            return this.firestore.getClient().getDocumentFromLocalCache(this.key).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.DocumentReference$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return this.f$0.m262lambda$get$0$comgooglefirebasefirestoreDocumentReference(task);
                }
            });
        }
        return getViaSnapshotListener(source);
    }

    /* JADX INFO: renamed from: lambda$get$0$com-google-firebase-firestore-DocumentReference, reason: not valid java name */
    /* synthetic */ DocumentSnapshot m262lambda$get$0$comgooglefirebasefirestoreDocumentReference(Task task) throws Exception {
        Document doc = (Document) task.getResult();
        boolean hasPendingWrites = doc != null && doc.hasLocalMutations();
        return new DocumentSnapshot(this.firestore, this.key, doc, true, hasPendingWrites);
    }

    private Task<DocumentSnapshot> getViaSnapshotListener(final Source source) {
        final TaskCompletionSource<DocumentSnapshot> res = new TaskCompletionSource<>();
        final TaskCompletionSource<ListenerRegistration> registration = new TaskCompletionSource<>();
        EventManager.ListenOptions options = new EventManager.ListenOptions();
        options.includeDocumentMetadataChanges = true;
        options.includeQueryMetadataChanges = true;
        options.waitForSyncWhenOnline = true;
        ListenerRegistration listenerRegistration = addSnapshotListenerInternal(Executors.DIRECT_EXECUTOR, options, null, new EventListener() { // from class: com.google.firebase.firestore.DocumentReference$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                DocumentReference.lambda$getViaSnapshotListener$1(res, registration, source, (DocumentSnapshot) obj, firebaseFirestoreException);
            }
        });
        registration.setResult(listenerRegistration);
        return res.getTask();
    }

    static /* synthetic */ void lambda$getViaSnapshotListener$1(TaskCompletionSource res, TaskCompletionSource registration, Source source, DocumentSnapshot snapshot, FirebaseFirestoreException error) {
        if (error != null) {
            res.setException(error);
            return;
        }
        try {
            ListenerRegistration actualRegistration = (ListenerRegistration) Tasks.await(registration.getTask());
            actualRegistration.remove();
            if (!snapshot.exists() && snapshot.getMetadata().isFromCache()) {
                res.setException(new FirebaseFirestoreException("Failed to get document because the client is offline.", FirebaseFirestoreException.Code.UNAVAILABLE));
            } else if (snapshot.exists() && snapshot.getMetadata().isFromCache() && source == Source.SERVER) {
                res.setException(new FirebaseFirestoreException("Failed to get document from server. (However, this document does exist in the local cache. Run again without setting source to SERVER to retrieve the cached document.)", FirebaseFirestoreException.Code.UNAVAILABLE));
            } else {
                res.setResult(snapshot);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw Assert.fail(e, "Failed to register a listener for a single document", new Object[0]);
        } catch (ExecutionException e2) {
            throw Assert.fail(e2, "Failed to register a listener for a single document", new Object[0]);
        }
    }

    public ListenerRegistration addSnapshotListener(EventListener<DocumentSnapshot> listener) {
        return addSnapshotListener(MetadataChanges.EXCLUDE, listener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, EventListener<DocumentSnapshot> listener) {
        return addSnapshotListener(executor, MetadataChanges.EXCLUDE, listener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, EventListener<DocumentSnapshot> listener) {
        return addSnapshotListener(activity, MetadataChanges.EXCLUDE, listener);
    }

    public ListenerRegistration addSnapshotListener(MetadataChanges metadataChanges, EventListener<DocumentSnapshot> listener) {
        return addSnapshotListener(Executors.DEFAULT_CALLBACK_EXECUTOR, metadataChanges, listener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, MetadataChanges metadataChanges, EventListener<DocumentSnapshot> listener) {
        Preconditions.checkNotNull(executor, "Provided executor must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(listener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(executor, internalOptions(metadataChanges), null, listener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, MetadataChanges metadataChanges, EventListener<DocumentSnapshot> listener) {
        Preconditions.checkNotNull(activity, "Provided activity must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(listener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(Executors.DEFAULT_CALLBACK_EXECUTOR, internalOptions(metadataChanges), activity, listener);
    }

    private ListenerRegistration addSnapshotListenerInternal(Executor userExecutor, EventManager.ListenOptions options, Activity activity, final EventListener<DocumentSnapshot> userListener) {
        EventListener<ViewSnapshot> viewListener = new EventListener() { // from class: com.google.firebase.firestore.DocumentReference$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                this.f$0.m261xb5c5a0f3(userListener, (ViewSnapshot) obj, firebaseFirestoreException);
            }
        };
        AsyncEventListener<ViewSnapshot> asyncListener = new AsyncEventListener<>(userExecutor, viewListener);
        com.google.firebase.firestore.core.Query query = asQuery();
        QueryListener queryListener = this.firestore.getClient().listen(query, options, asyncListener);
        return ActivityScope.bind(activity, new ListenerRegistrationImpl(this.firestore.getClient(), queryListener, asyncListener));
    }

    /* JADX INFO: renamed from: lambda$addSnapshotListenerInternal$2$com-google-firebase-firestore-DocumentReference, reason: not valid java name */
    /* synthetic */ void m261xb5c5a0f3(EventListener userListener, ViewSnapshot snapshot, FirebaseFirestoreException error) {
        DocumentSnapshot documentSnapshot;
        if (error != null) {
            userListener.onEvent(null, error);
            return;
        }
        Assert.hardAssert(snapshot != null, "Got event without value or error set", new Object[0]);
        Assert.hardAssert(snapshot.getDocuments().size() <= 1, "Too many documents returned on a document query", new Object[0]);
        Document document = snapshot.getDocuments().getDocument(this.key);
        if (document != null) {
            boolean hasPendingWrites = snapshot.getMutatedKeys().contains(document.getKey());
            documentSnapshot = DocumentSnapshot.fromDocument(this.firestore, document, snapshot.isFromCache(), hasPendingWrites);
        } else {
            documentSnapshot = DocumentSnapshot.fromNoDocument(this.firestore, this.key, snapshot.isFromCache());
        }
        userListener.onEvent(documentSnapshot, null);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentReference)) {
            return false;
        }
        DocumentReference that = (DocumentReference) o;
        return this.key.equals(that.key) && this.firestore.equals(that.firestore);
    }

    public int hashCode() {
        int result = this.key.hashCode();
        return (result * 31) + this.firestore.hashCode();
    }

    private com.google.firebase.firestore.core.Query asQuery() {
        return com.google.firebase.firestore.core.Query.atPath(this.key.getPath());
    }

    private static EventManager.ListenOptions internalOptions(MetadataChanges metadataChanges) {
        EventManager.ListenOptions internalOptions = new EventManager.ListenOptions();
        internalOptions.includeDocumentMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        internalOptions.includeQueryMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        internalOptions.waitForSyncWhenOnline = false;
        return internalOptions;
    }
}
