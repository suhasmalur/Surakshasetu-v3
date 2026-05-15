package com.google.firebase.firestore.remote;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.protobuf.ByteString;

/* JADX INFO: loaded from: classes10.dex */
public final class TargetChange {
    private final ImmutableSortedSet<DocumentKey> addedDocuments;
    private final boolean current;
    private final ImmutableSortedSet<DocumentKey> modifiedDocuments;
    private final ImmutableSortedSet<DocumentKey> removedDocuments;
    private final ByteString resumeToken;

    public static TargetChange createSynthesizedTargetChangeForCurrentChange(boolean isCurrent, ByteString resumeToken) {
        return new TargetChange(resumeToken, isCurrent, DocumentKey.emptyKeySet(), DocumentKey.emptyKeySet(), DocumentKey.emptyKeySet());
    }

    public TargetChange(ByteString resumeToken, boolean current, ImmutableSortedSet<DocumentKey> addedDocuments, ImmutableSortedSet<DocumentKey> modifiedDocuments, ImmutableSortedSet<DocumentKey> removedDocuments) {
        this.resumeToken = resumeToken;
        this.current = current;
        this.addedDocuments = addedDocuments;
        this.modifiedDocuments = modifiedDocuments;
        this.removedDocuments = removedDocuments;
    }

    public ByteString getResumeToken() {
        return this.resumeToken;
    }

    public boolean isCurrent() {
        return this.current;
    }

    public ImmutableSortedSet<DocumentKey> getAddedDocuments() {
        return this.addedDocuments;
    }

    public ImmutableSortedSet<DocumentKey> getModifiedDocuments() {
        return this.modifiedDocuments;
    }

    public ImmutableSortedSet<DocumentKey> getRemovedDocuments() {
        return this.removedDocuments;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TargetChange that = (TargetChange) o;
        if (this.current != that.current || !this.resumeToken.equals(that.resumeToken) || !this.addedDocuments.equals(that.addedDocuments) || !this.modifiedDocuments.equals(that.modifiedDocuments)) {
            return false;
        }
        return this.removedDocuments.equals(that.removedDocuments);
    }

    public int hashCode() {
        return (((((((this.resumeToken.hashCode() * 31) + (this.current ? 1 : 0)) * 31) + this.addedDocuments.hashCode()) * 31) + this.modifiedDocuments.hashCode()) * 31) + this.removedDocuments.hashCode();
    }
}
