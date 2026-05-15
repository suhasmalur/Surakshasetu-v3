package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes10.dex */
public class DocumentViewChangeSet {
    private final TreeMap<DocumentKey, DocumentViewChange> changes = new TreeMap<>();

    public void addChange(DocumentViewChange change) {
        DocumentKey key = change.getDocument().getKey();
        DocumentViewChange old = this.changes.get(key);
        if (old == null) {
            this.changes.put(key, change);
            return;
        }
        DocumentViewChange.Type oldType = old.getType();
        DocumentViewChange.Type newType = change.getType();
        if (newType != DocumentViewChange.Type.ADDED && oldType == DocumentViewChange.Type.METADATA) {
            this.changes.put(key, change);
            return;
        }
        if (newType == DocumentViewChange.Type.METADATA && oldType != DocumentViewChange.Type.REMOVED) {
            DocumentViewChange newChange = DocumentViewChange.create(oldType, change.getDocument());
            this.changes.put(key, newChange);
            return;
        }
        if (newType == DocumentViewChange.Type.MODIFIED && oldType == DocumentViewChange.Type.MODIFIED) {
            DocumentViewChange newChange2 = DocumentViewChange.create(DocumentViewChange.Type.MODIFIED, change.getDocument());
            this.changes.put(key, newChange2);
            return;
        }
        if (newType == DocumentViewChange.Type.MODIFIED && oldType == DocumentViewChange.Type.ADDED) {
            DocumentViewChange newChange3 = DocumentViewChange.create(DocumentViewChange.Type.ADDED, change.getDocument());
            this.changes.put(key, newChange3);
            return;
        }
        if (newType == DocumentViewChange.Type.REMOVED && oldType == DocumentViewChange.Type.ADDED) {
            this.changes.remove(key);
            return;
        }
        if (newType == DocumentViewChange.Type.REMOVED && oldType == DocumentViewChange.Type.MODIFIED) {
            DocumentViewChange newChange4 = DocumentViewChange.create(DocumentViewChange.Type.REMOVED, old.getDocument());
            this.changes.put(key, newChange4);
        } else {
            if (newType == DocumentViewChange.Type.ADDED && oldType == DocumentViewChange.Type.REMOVED) {
                DocumentViewChange newChange5 = DocumentViewChange.create(DocumentViewChange.Type.MODIFIED, change.getDocument());
                this.changes.put(key, newChange5);
                return;
            }
            throw Assert.fail("Unsupported combination of changes %s after %s", newType, oldType);
        }
    }

    List<DocumentViewChange> getChanges() {
        return new ArrayList(this.changes.values());
    }
}
