package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.LimboDocumentChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public class View {
    private boolean current;
    private DocumentSet documentSet;
    private final Query query;
    private ImmutableSortedSet<DocumentKey> syncedDocuments;
    private ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
    private ImmutableSortedSet<DocumentKey> limboDocuments = DocumentKey.emptyKeySet();
    private ImmutableSortedSet<DocumentKey> mutatedKeys = DocumentKey.emptyKeySet();

    public static class DocumentChanges {
        final DocumentViewChangeSet changeSet;
        final DocumentSet documentSet;
        final ImmutableSortedSet<DocumentKey> mutatedKeys;
        private final boolean needsRefill;

        private DocumentChanges(DocumentSet newDocuments, DocumentViewChangeSet changes, ImmutableSortedSet<DocumentKey> mutatedKeys, boolean needsRefill) {
            this.documentSet = newDocuments;
            this.changeSet = changes;
            this.mutatedKeys = mutatedKeys;
            this.needsRefill = needsRefill;
        }

        public boolean needsRefill() {
            return this.needsRefill;
        }
    }

    public View(Query query, ImmutableSortedSet<DocumentKey> remoteDocuments) {
        this.query = query;
        this.documentSet = DocumentSet.emptySet(query.comparator());
        this.syncedDocuments = remoteDocuments;
    }

    public ViewSnapshot.SyncState getSyncState() {
        return this.syncState;
    }

    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> docChanges) {
        return computeDocChanges(docChanges, null);
    }

    /* JADX WARN: Incorrect condition in loop: B:27:0x0070 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.firebase.firestore.core.View.DocumentChanges computeDocChanges(com.google.firebase.database.collection.ImmutableSortedMap<com.google.firebase.firestore.model.DocumentKey, com.google.firebase.firestore.model.Document> r21, com.google.firebase.firestore.core.View.DocumentChanges r22) {
        /*
            Method dump skipped, instruction units count: 496
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.View.computeDocChanges(com.google.firebase.database.collection.ImmutableSortedMap, com.google.firebase.firestore.core.View$DocumentChanges):com.google.firebase.firestore.core.View$DocumentChanges");
    }

    private boolean shouldWaitForSyncedDocument(Document oldDoc, Document newDoc) {
        return oldDoc.hasLocalMutations() && newDoc.hasCommittedMutations() && !newDoc.hasLocalMutations();
    }

    public ViewChange applyChanges(DocumentChanges docChanges) {
        return applyChanges(docChanges, null);
    }

    public ViewChange applyChanges(DocumentChanges docChanges, TargetChange targetChange) {
        return applyChanges(docChanges, targetChange, false);
    }

    public ViewChange applyChanges(DocumentChanges docChanges, TargetChange targetChange, boolean targetIsPendingReset) {
        ViewSnapshot snapshot;
        Assert.hardAssert(!docChanges.needsRefill, "Cannot apply changes that need a refill", new Object[0]);
        DocumentSet oldDocumentSet = this.documentSet;
        this.documentSet = docChanges.documentSet;
        this.mutatedKeys = docChanges.mutatedKeys;
        List<DocumentViewChange> viewChanges = docChanges.changeSet.getChanges();
        Collections.sort(viewChanges, new Comparator() { // from class: com.google.firebase.firestore.core.View$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.f$0.m307lambda$applyChanges$0$comgooglefirebasefirestorecoreView((DocumentViewChange) obj, (DocumentViewChange) obj2);
            }
        });
        applyTargetChange(targetChange);
        List<LimboDocumentChange> limboDocumentChanges = targetIsPendingReset ? Collections.emptyList() : updateLimboDocuments();
        boolean synced = this.limboDocuments.size() == 0 && this.current && !targetIsPendingReset;
        ViewSnapshot.SyncState newSyncState = synced ? ViewSnapshot.SyncState.SYNCED : ViewSnapshot.SyncState.LOCAL;
        boolean syncStatedChanged = newSyncState != this.syncState;
        this.syncState = newSyncState;
        if (viewChanges.size() == 0 && !syncStatedChanged) {
            snapshot = null;
        } else {
            boolean fromCache = newSyncState == ViewSnapshot.SyncState.LOCAL;
            boolean hasCachedResults = (targetChange == null || targetChange.getResumeToken().isEmpty()) ? false : true;
            snapshot = new ViewSnapshot(this.query, docChanges.documentSet, oldDocumentSet, viewChanges, fromCache, docChanges.mutatedKeys, syncStatedChanged, false, hasCachedResults);
        }
        return new ViewChange(snapshot, limboDocumentChanges);
    }

    /* JADX INFO: renamed from: lambda$applyChanges$0$com-google-firebase-firestore-core-View, reason: not valid java name */
    /* synthetic */ int m307lambda$applyChanges$0$comgooglefirebasefirestorecoreView(DocumentViewChange o1, DocumentViewChange o2) {
        int typeComp = Util.compareIntegers(changeTypeOrder(o1), changeTypeOrder(o2));
        if (typeComp != 0) {
            return typeComp;
        }
        return this.query.comparator().compare(o1.getDocument(), o2.getDocument());
    }

    public ViewChange applyOnlineStateChange(OnlineState onlineState) {
        if (this.current && onlineState == OnlineState.OFFLINE) {
            this.current = false;
            return applyChanges(new DocumentChanges(this.documentSet, new DocumentViewChangeSet(), this.mutatedKeys, false));
        }
        return new ViewChange(null, Collections.emptyList());
    }

    private void applyTargetChange(TargetChange targetChange) {
        if (targetChange != null) {
            for (DocumentKey documentKey : targetChange.getAddedDocuments()) {
                this.syncedDocuments = this.syncedDocuments.insert(documentKey);
            }
            for (DocumentKey documentKey2 : targetChange.getModifiedDocuments()) {
                Assert.hardAssert(this.syncedDocuments.contains(documentKey2), "Modified document %s not found in view.", documentKey2);
            }
            Iterator<DocumentKey> it = targetChange.getRemovedDocuments().iterator();
            while (it.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.remove(it.next());
            }
            this.current = targetChange.isCurrent();
        }
    }

    private List<LimboDocumentChange> updateLimboDocuments() {
        if (!this.current) {
            return Collections.emptyList();
        }
        ImmutableSortedSet<DocumentKey> oldLimboDocs = this.limboDocuments;
        this.limboDocuments = DocumentKey.emptyKeySet();
        for (Document doc : this.documentSet) {
            if (shouldBeLimboDoc(doc.getKey())) {
                this.limboDocuments = this.limboDocuments.insert(doc.getKey());
            }
        }
        List<LimboDocumentChange> changes = new ArrayList<>(oldLimboDocs.size() + this.limboDocuments.size());
        for (DocumentKey key : oldLimboDocs) {
            if (!this.limboDocuments.contains(key)) {
                changes.add(new LimboDocumentChange(LimboDocumentChange.Type.REMOVED, key));
            }
        }
        for (DocumentKey key2 : this.limboDocuments) {
            if (!oldLimboDocs.contains(key2)) {
                changes.add(new LimboDocumentChange(LimboDocumentChange.Type.ADDED, key2));
            }
        }
        return changes;
    }

    private boolean shouldBeLimboDoc(DocumentKey key) {
        Document doc;
        return (this.syncedDocuments.contains(key) || (doc = this.documentSet.getDocument(key)) == null || doc.hasLocalMutations()) ? false : true;
    }

    ImmutableSortedSet<DocumentKey> getLimboDocuments() {
        return this.limboDocuments;
    }

    ImmutableSortedSet<DocumentKey> getSyncedDocuments() {
        return this.syncedDocuments;
    }

    private static int changeTypeOrder(DocumentViewChange change) {
        switch (change.getType()) {
            case ADDED:
                return 1;
            case MODIFIED:
                return 2;
            case METADATA:
                return 2;
            case REMOVED:
                return 0;
            default:
                throw new IllegalArgumentException("Unknown change type: " + change.getType());
        }
    }
}
