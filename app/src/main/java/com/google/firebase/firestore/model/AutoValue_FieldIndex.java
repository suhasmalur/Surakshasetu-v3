package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
final class AutoValue_FieldIndex extends FieldIndex {
    private final String collectionGroup;
    private final int indexId;
    private final FieldIndex.IndexState indexState;
    private final List<FieldIndex.Segment> segments;

    AutoValue_FieldIndex(int indexId, String collectionGroup, List<FieldIndex.Segment> segments, FieldIndex.IndexState indexState) {
        this.indexId = indexId;
        if (collectionGroup == null) {
            throw new NullPointerException("Null collectionGroup");
        }
        this.collectionGroup = collectionGroup;
        if (segments == null) {
            throw new NullPointerException("Null segments");
        }
        this.segments = segments;
        if (indexState == null) {
            throw new NullPointerException("Null indexState");
        }
        this.indexState = indexState;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public int getIndexId() {
        return this.indexId;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public List<FieldIndex.Segment> getSegments() {
        return this.segments;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public FieldIndex.IndexState getIndexState() {
        return this.indexState;
    }

    public String toString() {
        return "FieldIndex{indexId=" + this.indexId + ", collectionGroup=" + this.collectionGroup + ", segments=" + this.segments + ", indexState=" + this.indexState + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FieldIndex)) {
            return false;
        }
        FieldIndex that = (FieldIndex) o;
        return this.indexId == that.getIndexId() && this.collectionGroup.equals(that.getCollectionGroup()) && this.segments.equals(that.getSegments()) && this.indexState.equals(that.getIndexState());
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((((((h$ ^ this.indexId) * 1000003) ^ this.collectionGroup.hashCode()) * 1000003) ^ this.segments.hashCode()) * 1000003) ^ this.indexState.hashCode();
    }
}
