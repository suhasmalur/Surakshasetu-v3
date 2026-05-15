package com.google.firebase.firestore.local;

import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.NamedQuery;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
class MemoryBundleCache implements BundleCache {
    private final Map<String, BundleMetadata> bundles = new HashMap();
    private final Map<String, NamedQuery> namedQueries = new HashMap();

    MemoryBundleCache() {
    }

    @Override // com.google.firebase.firestore.local.BundleCache
    public BundleMetadata getBundleMetadata(String bundleId) {
        return this.bundles.get(bundleId);
    }

    @Override // com.google.firebase.firestore.local.BundleCache
    public void saveBundleMetadata(BundleMetadata metadata) {
        this.bundles.put(metadata.getBundleId(), metadata);
    }

    @Override // com.google.firebase.firestore.local.BundleCache
    public NamedQuery getNamedQuery(String queryName) {
        return this.namedQueries.get(queryName);
    }

    @Override // com.google.firebase.firestore.local.BundleCache
    public void saveNamedQuery(NamedQuery query) {
        this.namedQueries.put(query.getName(), query);
    }
}
