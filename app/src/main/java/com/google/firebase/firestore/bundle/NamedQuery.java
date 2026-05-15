package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.SnapshotVersion;

/* JADX INFO: loaded from: classes10.dex */
public class NamedQuery implements BundleElement {
    private final BundledQuery bundledQuery;
    private final String name;
    private final SnapshotVersion readTime;

    public NamedQuery(String name, BundledQuery bundledQuery, SnapshotVersion readTime) {
        this.name = name;
        this.bundledQuery = bundledQuery;
        this.readTime = readTime;
    }

    public String getName() {
        return this.name;
    }

    public BundledQuery getBundledQuery() {
        return this.bundledQuery;
    }

    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NamedQuery that = (NamedQuery) o;
        if (!this.name.equals(that.name) || !this.bundledQuery.equals(that.bundledQuery)) {
            return false;
        }
        return this.readTime.equals(that.readTime);
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (((result * 31) + this.bundledQuery.hashCode()) * 31) + this.readTime.hashCode();
    }
}
