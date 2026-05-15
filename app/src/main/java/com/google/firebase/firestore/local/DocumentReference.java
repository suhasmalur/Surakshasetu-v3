package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Util;
import java.util.Comparator;

/* JADX INFO: loaded from: classes10.dex */
class DocumentReference {
    static final Comparator<DocumentReference> BY_KEY = new Comparator() { // from class: com.google.firebase.firestore.local.DocumentReference$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return DocumentReference.lambda$static$0((DocumentReference) obj, (DocumentReference) obj2);
        }
    };
    static final Comparator<DocumentReference> BY_TARGET = new Comparator() { // from class: com.google.firebase.firestore.local.DocumentReference$$ExternalSyntheticLambda1
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return DocumentReference.lambda$static$1((DocumentReference) obj, (DocumentReference) obj2);
        }
    };
    private final DocumentKey key;
    private final int targetOrBatchId;

    public DocumentReference(DocumentKey key, int targetOrBatchId) {
        this.key = key;
        this.targetOrBatchId = targetOrBatchId;
    }

    DocumentKey getKey() {
        return this.key;
    }

    int getId() {
        return this.targetOrBatchId;
    }

    static /* synthetic */ int lambda$static$0(DocumentReference o1, DocumentReference o2) {
        int keyComp = o1.key.compareTo(o2.key);
        if (keyComp != 0) {
            return keyComp;
        }
        return Util.compareIntegers(o1.targetOrBatchId, o2.targetOrBatchId);
    }

    static /* synthetic */ int lambda$static$1(DocumentReference o1, DocumentReference o2) {
        int targetComp = Util.compareIntegers(o1.targetOrBatchId, o2.targetOrBatchId);
        if (targetComp != 0) {
            return targetComp;
        }
        return o1.key.compareTo(o2.key);
    }
}
