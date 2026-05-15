package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.model.mutation.VerifyMutation;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes10.dex */
public class Transaction {
    private static final Executor defaultExecutor = createDefaultExecutor();
    private boolean committed;
    private final Datastore datastore;
    private FirebaseFirestoreException lastWriteError;
    private final HashMap<DocumentKey, SnapshotVersion> readVersions = new HashMap<>();
    private final ArrayList<Mutation> mutations = new ArrayList<>();
    private Set<DocumentKey> writtenDocs = new HashSet();

    public Transaction(Datastore d) {
        this.datastore = d;
    }

    public Task<List<MutableDocument>> lookup(List<DocumentKey> keys) {
        ensureCommitNotCalled();
        if (this.mutations.size() != 0) {
            return Tasks.forException(new FirebaseFirestoreException("Firestore transactions require all reads to be executed before all writes.", FirebaseFirestoreException.Code.INVALID_ARGUMENT));
        }
        return this.datastore.lookup(keys).continueWithTask(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.core.Transaction$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m303lambda$lookup$0$comgooglefirebasefirestorecoreTransaction(task);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$lookup$0$com-google-firebase-firestore-core-Transaction, reason: not valid java name */
    /* synthetic */ Task m303lambda$lookup$0$comgooglefirebasefirestorecoreTransaction(Task task) throws Exception {
        if (task.isSuccessful()) {
            for (MutableDocument doc : (List) task.getResult()) {
                recordVersion(doc);
            }
        }
        return task;
    }

    public void set(DocumentKey key, UserData.ParsedSetData data) {
        write(Collections.singletonList(data.toMutation(key, precondition(key))));
        this.writtenDocs.add(key);
    }

    public void update(DocumentKey key, UserData.ParsedUpdateData data) {
        try {
            write(Collections.singletonList(data.toMutation(key, preconditionForUpdate(key))));
        } catch (FirebaseFirestoreException e) {
            this.lastWriteError = e;
        }
        this.writtenDocs.add(key);
    }

    public void delete(DocumentKey key) {
        write(Collections.singletonList(new DeleteMutation(key, precondition(key))));
        this.writtenDocs.add(key);
    }

    public Task<Void> commit() {
        ensureCommitNotCalled();
        if (this.lastWriteError != null) {
            return Tasks.forException(this.lastWriteError);
        }
        HashSet<DocumentKey> unwritten = new HashSet<>(this.readVersions.keySet());
        for (Mutation mutation : this.mutations) {
            unwritten.remove(mutation.getKey());
        }
        for (DocumentKey key : unwritten) {
            this.mutations.add(new VerifyMutation(key, precondition(key)));
        }
        this.committed = true;
        return this.datastore.commit(this.mutations).continueWithTask(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.core.Transaction$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return Transaction.lambda$commit$1(task);
            }
        });
    }

    static /* synthetic */ Task lambda$commit$1(Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(null);
        }
        return Tasks.forException(task.getException());
    }

    private static Executor createDefaultExecutor() {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.SECONDS, queue);
        executor.allowCoreThreadTimeOut(true);
        return executor;
    }

    private void recordVersion(MutableDocument doc) throws FirebaseFirestoreException {
        SnapshotVersion docVersion;
        if (doc.isFoundDocument()) {
            docVersion = doc.getVersion();
        } else if (doc.isNoDocument()) {
            docVersion = SnapshotVersion.NONE;
        } else {
            throw Assert.fail("Unexpected document type in transaction: " + doc, new Object[0]);
        }
        if (this.readVersions.containsKey(doc.getKey())) {
            SnapshotVersion existingVersion = this.readVersions.get(doc.getKey());
            if (!existingVersion.equals(doc.getVersion())) {
                throw new FirebaseFirestoreException("Document version changed between two reads.", FirebaseFirestoreException.Code.ABORTED);
            }
            return;
        }
        this.readVersions.put(doc.getKey(), docVersion);
    }

    private Precondition precondition(DocumentKey key) {
        SnapshotVersion version = this.readVersions.get(key);
        if (!this.writtenDocs.contains(key) && version != null) {
            if (version.equals(SnapshotVersion.NONE)) {
                return Precondition.exists(false);
            }
            return Precondition.updateTime(version);
        }
        return Precondition.NONE;
    }

    private Precondition preconditionForUpdate(DocumentKey key) throws FirebaseFirestoreException {
        SnapshotVersion version = this.readVersions.get(key);
        if (!this.writtenDocs.contains(key) && version != null) {
            if (version != null && version.equals(SnapshotVersion.NONE)) {
                throw new FirebaseFirestoreException("Can't update a document that doesn't exist.", FirebaseFirestoreException.Code.INVALID_ARGUMENT);
            }
            return Precondition.updateTime(version);
        }
        return Precondition.exists(true);
    }

    private void write(List<Mutation> mutations) {
        ensureCommitNotCalled();
        this.mutations.addAll(mutations);
    }

    private void ensureCommitNotCalled() {
        Assert.hardAssert(!this.committed, "A transaction object cannot be used after its update callback has been invoked.", new Object[0]);
    }

    public static Executor getDefaultExecutor() {
        return defaultExecutor;
    }
}
