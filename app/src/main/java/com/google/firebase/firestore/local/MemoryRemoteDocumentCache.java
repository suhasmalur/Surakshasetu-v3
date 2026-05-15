package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes10.dex */
final class MemoryRemoteDocumentCache implements RemoteDocumentCache {
    private ImmutableSortedMap<DocumentKey, Document> docs = DocumentCollections.emptyDocumentMap();
    private IndexManager indexManager;

    MemoryRemoteDocumentCache() {
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void setIndexManager(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void add(MutableDocument document, SnapshotVersion readTime) {
        Assert.hardAssert(this.indexManager != null, "setIndexManager() not called", new Object[0]);
        Assert.hardAssert(!readTime.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        this.docs = this.docs.insert(document.getKey(), document.mutableCopy().setReadTime(readTime));
        this.indexManager.addToCollectionParentIndex(document.getKey().getCollectionPath());
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void removeAll(Collection<DocumentKey> keys) {
        Assert.hardAssert(this.indexManager != null, "setIndexManager() not called", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> deletedDocs = DocumentCollections.emptyDocumentMap();
        for (DocumentKey key : keys) {
            this.docs = this.docs.remove(key);
            deletedDocs = deletedDocs.insert(key, MutableDocument.newNoDocument(key, SnapshotVersion.NONE));
        }
        this.indexManager.updateIndexEntries(deletedDocs);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public MutableDocument get(DocumentKey key) {
        Document doc = this.docs.get(key);
        return doc != null ? doc.mutableCopy() : MutableDocument.newInvalidDocument(key);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> keys) {
        Map<DocumentKey, MutableDocument> result = new HashMap<>();
        for (DocumentKey key : keys) {
            result.put(key, get(key));
        }
        return result;
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(String collectionGroup, FieldIndex.IndexOffset offset, int limit) {
        throw new UnsupportedOperationException("getAll(String, IndexOffset, int) is not supported.");
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset offset, @Nonnull Set<DocumentKey> mutatedKeys, QueryContext context) {
        Map<DocumentKey, MutableDocument> result = new HashMap<>();
        DocumentKey prefix = DocumentKey.fromPath(query.getPath().append(""));
        Iterator<Map.Entry<DocumentKey, Document>> iterator = this.docs.iteratorFrom(prefix);
        while (iterator.hasNext()) {
            Map.Entry<DocumentKey, Document> entry = iterator.next();
            Document doc = entry.getValue();
            DocumentKey key = entry.getKey();
            if (!query.getPath().isPrefixOf(key.getPath())) {
                break;
            }
            if (key.getPath().length() <= query.getPath().length() + 1 && FieldIndex.IndexOffset.fromDocument(doc).compareTo(offset) > 0 && (mutatedKeys.contains(doc.getKey()) || query.matches(doc))) {
                result.put(doc.getKey(), doc.mutableCopy());
            }
        }
        return result;
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset offset, @Nonnull Set<DocumentKey> mutatedKeys) {
        return getDocumentsMatchingQuery(query, offset, mutatedKeys, null);
    }

    Iterable<Document> getDocuments() {
        return new DocumentIterable();
    }

    long getByteSize(LocalSerializer serializer) {
        long count = 0;
        for (Document doc : new DocumentIterable()) {
            count += (long) serializer.encodeMaybeDocument(doc).getSerializedSize();
        }
        return count;
    }

    private class DocumentIterable implements Iterable<Document> {
        private DocumentIterable() {
        }

        @Override // java.lang.Iterable
        public Iterator<Document> iterator() {
            final Iterator<Map.Entry<DocumentKey, Document>> iterator = MemoryRemoteDocumentCache.this.docs.iterator();
            return new Iterator<Document>() { // from class: com.google.firebase.firestore.local.MemoryRemoteDocumentCache.DocumentIterable.1
                @Override // java.util.Iterator
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public Document next() {
                    return (Document) ((Map.Entry) iterator.next()).getValue();
                }
            };
        }
    }
}
