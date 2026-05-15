package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.DocumentKey;

/* JADX INFO: loaded from: classes10.dex */
public class LimboDocumentChange {
    private final DocumentKey key;
    private final Type type;

    public enum Type {
        ADDED,
        REMOVED
    }

    public LimboDocumentChange(Type type, DocumentKey key) {
        this.type = type;
        this.key = key;
    }

    public Type getType() {
        return this.type;
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public boolean equals(Object o) {
        if (!(o instanceof LimboDocumentChange)) {
            return false;
        }
        LimboDocumentChange other = (LimboDocumentChange) o;
        return this.type.equals(other.getType()) && this.key.equals(other.getKey());
    }

    public int hashCode() {
        int res = (67 * 31) + this.type.hashCode();
        return (res * 31) + this.key.hashCode();
    }
}
