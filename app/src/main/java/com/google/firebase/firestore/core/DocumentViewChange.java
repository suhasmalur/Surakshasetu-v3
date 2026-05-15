package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;

/* JADX INFO: loaded from: classes10.dex */
public class DocumentViewChange {
    private final Document document;
    private final Type type;

    public enum Type {
        REMOVED,
        ADDED,
        MODIFIED,
        METADATA
    }

    public static DocumentViewChange create(Type type, Document document) {
        return new DocumentViewChange(type, document);
    }

    private DocumentViewChange(Type type, Document document) {
        this.type = type;
        this.document = document;
    }

    public Document getDocument() {
        return this.document;
    }

    public Type getType() {
        return this.type;
    }

    public boolean equals(Object o) {
        if (!(o instanceof DocumentViewChange)) {
            return false;
        }
        DocumentViewChange other = (DocumentViewChange) o;
        return this.type.equals(other.type) && this.document.equals(other.document);
    }

    public int hashCode() {
        int res = (61 * 31) + this.type.hashCode();
        return (((res * 31) + this.document.getKey().hashCode()) * 31) + this.document.getData().hashCode();
    }

    public String toString() {
        return "DocumentViewChange(" + this.document + "," + this.type + ")";
    }
}
