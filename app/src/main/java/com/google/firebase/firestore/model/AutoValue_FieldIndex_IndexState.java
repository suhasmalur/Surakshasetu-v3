package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;

/* JADX INFO: loaded from: classes10.dex */
final class AutoValue_FieldIndex_IndexState extends FieldIndex.IndexState {
    private final FieldIndex.IndexOffset offset;
    private final long sequenceNumber;

    AutoValue_FieldIndex_IndexState(long sequenceNumber, FieldIndex.IndexOffset offset) {
        this.sequenceNumber = sequenceNumber;
        if (offset == null) {
            throw new NullPointerException("Null offset");
        }
        this.offset = offset;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.IndexState
    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.IndexState
    public FieldIndex.IndexOffset getOffset() {
        return this.offset;
    }

    public String toString() {
        return "IndexState{sequenceNumber=" + this.sequenceNumber + ", offset=" + this.offset + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FieldIndex.IndexState)) {
            return false;
        }
        FieldIndex.IndexState that = (FieldIndex.IndexState) o;
        return this.sequenceNumber == that.getSequenceNumber() && this.offset.equals(that.getOffset());
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((h$ ^ ((int) ((this.sequenceNumber >>> 32) ^ this.sequenceNumber))) * 1000003) ^ this.offset.hashCode();
    }
}
