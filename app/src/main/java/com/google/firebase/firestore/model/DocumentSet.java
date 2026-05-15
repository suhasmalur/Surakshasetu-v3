package com.google.firebase.firestore.model;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public final class DocumentSet implements Iterable<Document> {
    private final ImmutableSortedMap<DocumentKey, Document> keyIndex;
    private final ImmutableSortedSet<Document> sortedSet;

    public static DocumentSet emptySet(final Comparator<Document> comparator) {
        Comparator<Document> adjustedComparator = new Comparator() { // from class: com.google.firebase.firestore.model.DocumentSet$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DocumentSet.lambda$emptySet$0(comparator, (Document) obj, (Document) obj2);
            }
        };
        return new DocumentSet(DocumentCollections.emptyDocumentMap(), new ImmutableSortedSet(Collections.emptyList(), adjustedComparator));
    }

    static /* synthetic */ int lambda$emptySet$0(Comparator comparator, Document left, Document right) {
        int comparison = comparator.compare(left, right);
        if (comparison == 0) {
            return Document.KEY_COMPARATOR.compare(left, right);
        }
        return comparison;
    }

    private DocumentSet(ImmutableSortedMap<DocumentKey, Document> keyIndex, ImmutableSortedSet<Document> sortedSet) {
        this.keyIndex = keyIndex;
        this.sortedSet = sortedSet;
    }

    public int size() {
        return this.keyIndex.size();
    }

    public boolean isEmpty() {
        return this.keyIndex.isEmpty();
    }

    public boolean contains(DocumentKey key) {
        return this.keyIndex.containsKey(key);
    }

    public Document getDocument(DocumentKey key) {
        return this.keyIndex.get(key);
    }

    public Document getFirstDocument() {
        return this.sortedSet.getMinEntry();
    }

    public Document getLastDocument() {
        return this.sortedSet.getMaxEntry();
    }

    public Document getPredecessor(DocumentKey key) {
        Document document = this.keyIndex.get(key);
        if (document == null) {
            throw new IllegalArgumentException("Key not contained in DocumentSet: " + key);
        }
        return this.sortedSet.getPredecessorEntry(document);
    }

    public int indexOf(DocumentKey key) {
        Document document = this.keyIndex.get(key);
        if (document == null) {
            return -1;
        }
        return this.sortedSet.indexOf(document);
    }

    public DocumentSet add(Document document) {
        DocumentSet removed = remove(document.getKey());
        ImmutableSortedMap<DocumentKey, Document> newKeyIndex = removed.keyIndex.insert(document.getKey(), document);
        ImmutableSortedSet<Document> newSortedSet = removed.sortedSet.insert(document);
        return new DocumentSet(newKeyIndex, newSortedSet);
    }

    public DocumentSet remove(DocumentKey key) {
        Document document = this.keyIndex.get(key);
        if (document == null) {
            return this;
        }
        ImmutableSortedMap<DocumentKey, Document> newKeyIndex = this.keyIndex.remove(key);
        ImmutableSortedSet<Document> newSortedSet = this.sortedSet.remove(document);
        return new DocumentSet(newKeyIndex, newSortedSet);
    }

    public List<Document> toList() {
        List<Document> documents = new ArrayList<>(size());
        for (Document document : this) {
            documents.add(document);
        }
        return documents;
    }

    @Override // java.lang.Iterable
    public Iterator<Document> iterator() {
        return this.sortedSet.iterator();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        DocumentSet documentSet = (DocumentSet) other;
        if (size() != documentSet.size()) {
            return false;
        }
        Iterator<Document> otherIterator = documentSet.iterator();
        for (Document thisDoc : this) {
            Document otherDoc = otherIterator.next();
            if (!thisDoc.equals(otherDoc)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int result = 0;
        for (Document document : this) {
            result = (((result * 31) + document.getKey().hashCode()) * 31) + document.getData().hashCode();
        }
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        boolean first = true;
        for (Document doc : this) {
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append(doc);
        }
        builder.append("]");
        return builder.toString();
    }
}
