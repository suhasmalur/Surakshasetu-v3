package com.google.firebase.firestore;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Preconditions;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
public class QueryDocumentSnapshot extends DocumentSnapshot {
    private QueryDocumentSnapshot(FirebaseFirestore firestore, DocumentKey key, Document doc, boolean isFromCache, boolean hasPendingWrites) {
        super(firestore, key, doc, isFromCache, hasPendingWrites);
    }

    static QueryDocumentSnapshot fromDocument(FirebaseFirestore firestore, Document doc, boolean fromCache, boolean hasPendingWrites) {
        return new QueryDocumentSnapshot(firestore, doc.getKey(), doc, fromCache, hasPendingWrites);
    }

    @Override // com.google.firebase.firestore.DocumentSnapshot
    public Map<String, Object> getData() {
        Map<String, Object> result = super.getData();
        Assert.hardAssert(result != null, "Data in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return result;
    }

    @Override // com.google.firebase.firestore.DocumentSnapshot
    public Map<String, Object> getData(DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Map<String, Object> result = super.getData(serverTimestampBehavior);
        Assert.hardAssert(result != null, "Data in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return result;
    }

    @Override // com.google.firebase.firestore.DocumentSnapshot
    public <T> T toObject(Class<T> cls) {
        T t = (T) super.toObject(cls);
        Assert.hardAssert(t != null, "Object in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return t;
    }

    @Override // com.google.firebase.firestore.DocumentSnapshot
    public <T> T toObject(Class<T> cls, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        T t = (T) super.toObject(cls, serverTimestampBehavior);
        Assert.hardAssert(t != null, "Object in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return t;
    }
}
