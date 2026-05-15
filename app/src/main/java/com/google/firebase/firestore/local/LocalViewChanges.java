package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.DocumentKey;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes10.dex */
public final class LocalViewChanges {
    private final ImmutableSortedSet<DocumentKey> added;
    private final boolean fromCache;
    private final ImmutableSortedSet<DocumentKey> removed;
    private final int targetId;

    public static LocalViewChanges fromViewSnapshot(int targetId, ViewSnapshot snapshot) {
        ImmutableSortedSet<DocumentKey> addedKeys = new ImmutableSortedSet<>(new ArrayList(), DocumentKey.comparator());
        ImmutableSortedSet<DocumentKey> removedKeys = new ImmutableSortedSet<>(new ArrayList(), DocumentKey.comparator());
        for (DocumentViewChange docChange : snapshot.getChanges()) {
            switch (docChange.getType()) {
                case ADDED:
                    addedKeys = addedKeys.insert(docChange.getDocument().getKey());
                    break;
                case REMOVED:
                    removedKeys = removedKeys.insert(docChange.getDocument().getKey());
                    break;
            }
        }
        return new LocalViewChanges(targetId, snapshot.isFromCache(), addedKeys, removedKeys);
    }

    public LocalViewChanges(int targetId, boolean fromCache, ImmutableSortedSet<DocumentKey> added, ImmutableSortedSet<DocumentKey> removed) {
        this.targetId = targetId;
        this.fromCache = fromCache;
        this.added = added;
        this.removed = removed;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public boolean isFromCache() {
        return this.fromCache;
    }

    public ImmutableSortedSet<DocumentKey> getAdded() {
        return this.added;
    }

    public ImmutableSortedSet<DocumentKey> getRemoved() {
        return this.removed;
    }
}
