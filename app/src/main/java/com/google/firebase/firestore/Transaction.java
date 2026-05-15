package com.google.firebase.firestore;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes10.dex */
public class Transaction {
    private final FirebaseFirestore firestore;
    private final com.google.firebase.firestore.core.Transaction transaction;

    public interface Function<TResult> {
        TResult apply(Transaction transaction) throws FirebaseFirestoreException;
    }

    Transaction(com.google.firebase.firestore.core.Transaction transaction, FirebaseFirestore firestore) {
        this.transaction = (com.google.firebase.firestore.core.Transaction) Preconditions.checkNotNull(transaction);
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firestore);
    }

    public Transaction set(DocumentReference documentRef, Object data) {
        return set(documentRef, data, SetOptions.OVERWRITE);
    }

    public Transaction set(DocumentReference documentRef, Object data, SetOptions options) {
        UserData.ParsedSetData parsed;
        this.firestore.validateReference(documentRef);
        Preconditions.checkNotNull(data, "Provided data must not be null.");
        Preconditions.checkNotNull(options, "Provided options must not be null.");
        if (options.isMerge()) {
            parsed = this.firestore.getUserDataReader().parseMergeData(data, options.getFieldMask());
        } else {
            parsed = this.firestore.getUserDataReader().parseSetData(data);
        }
        this.transaction.set(documentRef.getKey(), parsed);
        return this;
    }

    public Transaction update(DocumentReference documentRef, Map<String, Object> data) {
        UserData.ParsedUpdateData parsedData = this.firestore.getUserDataReader().parseUpdateData(data);
        return update(documentRef, parsedData);
    }

    public Transaction update(DocumentReference documentRef, String field, Object value, Object... moreFieldsAndValues) {
        UserData.ParsedUpdateData parsedData = this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, field, value, moreFieldsAndValues));
        return update(documentRef, parsedData);
    }

    public Transaction update(DocumentReference documentRef, FieldPath fieldPath, Object value, Object... moreFieldsAndValues) {
        UserData.ParsedUpdateData parsedData = this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, value, moreFieldsAndValues));
        return update(documentRef, parsedData);
    }

    private Transaction update(DocumentReference documentRef, UserData.ParsedUpdateData updateData) {
        this.firestore.validateReference(documentRef);
        this.transaction.update(documentRef.getKey(), updateData);
        return this;
    }

    public Transaction delete(DocumentReference documentRef) {
        this.firestore.validateReference(documentRef);
        this.transaction.delete(documentRef.getKey());
        return this;
    }

    private Task<DocumentSnapshot> getAsync(DocumentReference documentRef) {
        return this.transaction.lookup(Collections.singletonList(documentRef.getKey())).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.Transaction$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m275lambda$getAsync$0$comgooglefirebasefirestoreTransaction(task);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getAsync$0$com-google-firebase-firestore-Transaction, reason: not valid java name */
    /* synthetic */ DocumentSnapshot m275lambda$getAsync$0$comgooglefirebasefirestoreTransaction(Task task) throws Exception {
        if (!task.isSuccessful()) {
            throw task.getException();
        }
        List<MutableDocument> docs = (List) task.getResult();
        if (docs.size() != 1) {
            throw Assert.fail("Mismatch in docs returned from document lookup.", new Object[0]);
        }
        MutableDocument doc = docs.get(0);
        if (doc.isFoundDocument()) {
            return DocumentSnapshot.fromDocument(this.firestore, doc, false, false);
        }
        if (doc.isNoDocument()) {
            return DocumentSnapshot.fromNoDocument(this.firestore, doc.getKey(), false);
        }
        throw Assert.fail("BatchGetDocumentsRequest returned unexpected document type: " + doc.getClass().getCanonicalName(), new Object[0]);
    }

    public DocumentSnapshot get(DocumentReference documentRef) throws FirebaseFirestoreException {
        this.firestore.validateReference(documentRef);
        try {
            return (DocumentSnapshot) Tasks.await(getAsync(documentRef));
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        } catch (ExecutionException ee) {
            if (ee.getCause() instanceof FirebaseFirestoreException) {
                throw ((FirebaseFirestoreException) ee.getCause());
            }
            throw new RuntimeException(ee.getCause());
        }
    }
}
