package com.google.firebase.firestore.model;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.util.Assert;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public final class DocumentKey implements Comparable<DocumentKey> {
    private static final Comparator<DocumentKey> COMPARATOR = new Comparator() { // from class: com.google.firebase.firestore.model.DocumentKey$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ((DocumentKey) obj).compareTo((DocumentKey) obj2);
        }
    };
    private static final ImmutableSortedSet<DocumentKey> EMPTY_KEY_SET = new ImmutableSortedSet<>(Collections.emptyList(), COMPARATOR);
    public static final String KEY_FIELD_NAME = "__name__";
    private final ResourcePath path;

    public static Comparator<DocumentKey> comparator() {
        return COMPARATOR;
    }

    public static ImmutableSortedSet<DocumentKey> emptyKeySet() {
        return EMPTY_KEY_SET;
    }

    public static DocumentKey empty() {
        return fromSegments(Collections.emptyList());
    }

    public static DocumentKey fromName(String name) {
        ResourcePath resourceName = ResourcePath.fromString(name);
        boolean z = false;
        if (resourceName.length() > 4 && resourceName.getSegment(0).equals("projects") && resourceName.getSegment(2).equals("databases") && resourceName.getSegment(4).equals("documents")) {
            z = true;
        }
        Assert.hardAssert(z, "Tried to parse an invalid key: %s", resourceName);
        return fromPath(resourceName.popFirst(5));
    }

    public static DocumentKey fromPath(ResourcePath path) {
        return new DocumentKey(path);
    }

    public static DocumentKey fromSegments(List<String> segments) {
        return new DocumentKey(ResourcePath.fromSegments(segments));
    }

    public static DocumentKey fromPathString(String path) {
        return new DocumentKey(ResourcePath.fromString(path));
    }

    public static boolean isDocumentKey(ResourcePath path) {
        return path.length() % 2 == 0;
    }

    private DocumentKey(ResourcePath path) {
        Assert.hardAssert(isDocumentKey(path), "Not a document key path: %s", path);
        this.path = path;
    }

    public ResourcePath getPath() {
        return this.path;
    }

    public String getCollectionGroup() {
        return this.path.getSegment(this.path.length() - 2);
    }

    public ResourcePath getCollectionPath() {
        return this.path.popLast();
    }

    public String getDocumentId() {
        return this.path.getLastSegment();
    }

    public boolean hasCollectionId(String collectionId) {
        return this.path.length() >= 2 && this.path.segments.get(this.path.length() - 2).equals(collectionId);
    }

    @Override // java.lang.Comparable
    public int compareTo(DocumentKey another) {
        return this.path.compareTo(another.path);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DocumentKey that = (DocumentKey) o;
        return this.path.equals(that.path);
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public String toString() {
        return this.path.toString();
    }
}
