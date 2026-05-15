package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.DocumentKey;
import java.util.Arrays;

/* JADX INFO: loaded from: classes10.dex */
final class AutoValue_IndexEntry extends IndexEntry {
    private final byte[] arrayValue;
    private final byte[] directionalValue;
    private final DocumentKey documentKey;
    private final int indexId;

    AutoValue_IndexEntry(int indexId, DocumentKey documentKey, byte[] arrayValue, byte[] directionalValue) {
        this.indexId = indexId;
        if (documentKey == null) {
            throw new NullPointerException("Null documentKey");
        }
        this.documentKey = documentKey;
        if (arrayValue == null) {
            throw new NullPointerException("Null arrayValue");
        }
        this.arrayValue = arrayValue;
        if (directionalValue == null) {
            throw new NullPointerException("Null directionalValue");
        }
        this.directionalValue = directionalValue;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public int getIndexId() {
        return this.indexId;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public DocumentKey getDocumentKey() {
        return this.documentKey;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public byte[] getArrayValue() {
        return this.arrayValue;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public byte[] getDirectionalValue() {
        return this.directionalValue;
    }

    public String toString() {
        return "IndexEntry{indexId=" + this.indexId + ", documentKey=" + this.documentKey + ", arrayValue=" + Arrays.toString(this.arrayValue) + ", directionalValue=" + Arrays.toString(this.directionalValue) + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IndexEntry)) {
            return false;
        }
        IndexEntry that = (IndexEntry) o;
        if (this.indexId == that.getIndexId() && this.documentKey.equals(that.getDocumentKey())) {
            if (Arrays.equals(this.arrayValue, that instanceof AutoValue_IndexEntry ? ((AutoValue_IndexEntry) that).arrayValue : that.getArrayValue())) {
                if (Arrays.equals(this.directionalValue, that instanceof AutoValue_IndexEntry ? ((AutoValue_IndexEntry) that).directionalValue : that.getDirectionalValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((((((h$ ^ this.indexId) * 1000003) ^ this.documentKey.hashCode()) * 1000003) ^ Arrays.hashCode(this.arrayValue)) * 1000003) ^ Arrays.hashCode(this.directionalValue);
    }
}
