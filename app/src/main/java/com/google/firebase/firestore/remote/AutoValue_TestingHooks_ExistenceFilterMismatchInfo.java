package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.TestingHooks;

/* JADX INFO: loaded from: classes10.dex */
final class AutoValue_TestingHooks_ExistenceFilterMismatchInfo extends TestingHooks.ExistenceFilterMismatchInfo {
    private final TestingHooks.ExistenceFilterBloomFilterInfo bloomFilter;
    private final String databaseId;
    private final int existenceFilterCount;
    private final int localCacheCount;
    private final String projectId;

    AutoValue_TestingHooks_ExistenceFilterMismatchInfo(int localCacheCount, int existenceFilterCount, String projectId, String databaseId, TestingHooks.ExistenceFilterBloomFilterInfo bloomFilter) {
        this.localCacheCount = localCacheCount;
        this.existenceFilterCount = existenceFilterCount;
        if (projectId == null) {
            throw new NullPointerException("Null projectId");
        }
        this.projectId = projectId;
        if (databaseId == null) {
            throw new NullPointerException("Null databaseId");
        }
        this.databaseId = databaseId;
        this.bloomFilter = bloomFilter;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    int localCacheCount() {
        return this.localCacheCount;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    int existenceFilterCount() {
        return this.existenceFilterCount;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    String projectId() {
        return this.projectId;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    String databaseId() {
        return this.databaseId;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    TestingHooks.ExistenceFilterBloomFilterInfo bloomFilter() {
        return this.bloomFilter;
    }

    public String toString() {
        return "ExistenceFilterMismatchInfo{localCacheCount=" + this.localCacheCount + ", existenceFilterCount=" + this.existenceFilterCount + ", projectId=" + this.projectId + ", databaseId=" + this.databaseId + ", bloomFilter=" + this.bloomFilter + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TestingHooks.ExistenceFilterMismatchInfo)) {
            return false;
        }
        TestingHooks.ExistenceFilterMismatchInfo that = (TestingHooks.ExistenceFilterMismatchInfo) o;
        if (this.localCacheCount == that.localCacheCount() && this.existenceFilterCount == that.existenceFilterCount() && this.projectId.equals(that.projectId()) && this.databaseId.equals(that.databaseId())) {
            if (this.bloomFilter == null) {
                if (that.bloomFilter() == null) {
                    return true;
                }
            } else if (this.bloomFilter.equals(that.bloomFilter())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((((((((h$ ^ this.localCacheCount) * 1000003) ^ this.existenceFilterCount) * 1000003) ^ this.projectId.hashCode()) * 1000003) ^ this.databaseId.hashCode()) * 1000003) ^ (this.bloomFilter == null ? 0 : this.bloomFilter.hashCode());
    }
}
