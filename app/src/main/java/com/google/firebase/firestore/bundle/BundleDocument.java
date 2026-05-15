package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;

/* JADX INFO: loaded from: classes10.dex */
public class BundleDocument implements BundleElement {
    private MutableDocument document;

    public BundleDocument(MutableDocument document) {
        this.document = document;
    }

    public DocumentKey getKey() {
        return this.document.getKey();
    }

    public MutableDocument getDocument() {
        return this.document;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BundleDocument that = (BundleDocument) o;
        return this.document.equals(that.document);
    }

    public int hashCode() {
        return this.document.hashCode();
    }
}
