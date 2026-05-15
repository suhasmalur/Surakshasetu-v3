package com.google.firebase.firestore.remote;

/* JADX INFO: loaded from: classes10.dex */
public final class ExistenceFilter {
    private final int count;
    private com.google.firestore.v1.BloomFilter unchangedNames;

    public ExistenceFilter(int count) {
        this.count = count;
    }

    public ExistenceFilter(int count, com.google.firestore.v1.BloomFilter unchangedNames) {
        this.count = count;
        this.unchangedNames = unchangedNames;
    }

    public int getCount() {
        return this.count;
    }

    public com.google.firestore.v1.BloomFilter getUnchangedNames() {
        return this.unchangedNames;
    }

    public String toString() {
        return "ExistenceFilter{count=" + this.count + ", unchangedNames=" + this.unchangedNames + '}';
    }
}
