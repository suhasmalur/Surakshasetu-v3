package com.google.firebase.firestore.core;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public class QueryListener {
    private final EventListener<ViewSnapshot> listener;
    private final EventManager.ListenOptions options;
    private final Query query;
    private ViewSnapshot snapshot;
    private boolean raisedInitialEvent = false;
    private OnlineState onlineState = OnlineState.UNKNOWN;

    public QueryListener(Query query, EventManager.ListenOptions options, EventListener<ViewSnapshot> listener) {
        this.query = query;
        this.listener = listener;
        this.options = options;
    }

    public Query getQuery() {
        return this.query;
    }

    public boolean onViewSnapshot(ViewSnapshot newSnapshot) {
        Assert.hardAssert(!newSnapshot.getChanges().isEmpty() || newSnapshot.didSyncStateChange(), "We got a new snapshot with no changes?", new Object[0]);
        boolean raisedEvent = false;
        if (!this.options.includeDocumentMetadataChanges) {
            List<DocumentViewChange> documentChanges = new ArrayList<>();
            for (DocumentViewChange change : newSnapshot.getChanges()) {
                if (change.getType() != DocumentViewChange.Type.METADATA) {
                    documentChanges.add(change);
                }
            }
            newSnapshot = new ViewSnapshot(newSnapshot.getQuery(), newSnapshot.getDocuments(), newSnapshot.getOldDocuments(), documentChanges, newSnapshot.isFromCache(), newSnapshot.getMutatedKeys(), newSnapshot.didSyncStateChange(), true, newSnapshot.hasCachedResults());
        }
        if (!this.raisedInitialEvent) {
            if (shouldRaiseInitialEvent(newSnapshot, this.onlineState)) {
                raiseInitialEvent(newSnapshot);
                raisedEvent = true;
            }
        } else if (shouldRaiseEvent(newSnapshot)) {
            this.listener.onEvent(newSnapshot, null);
            raisedEvent = true;
        }
        this.snapshot = newSnapshot;
        return raisedEvent;
    }

    public void onError(FirebaseFirestoreException error) {
        this.listener.onEvent(null, error);
    }

    public boolean onOnlineStateChanged(OnlineState onlineState) {
        this.onlineState = onlineState;
        if (this.snapshot == null || this.raisedInitialEvent || !shouldRaiseInitialEvent(this.snapshot, onlineState)) {
            return false;
        }
        raiseInitialEvent(this.snapshot);
        return true;
    }

    private boolean shouldRaiseInitialEvent(ViewSnapshot snapshot, OnlineState onlineState) {
        Assert.hardAssert(!this.raisedInitialEvent, "Determining whether to raise first event but already had first event.", new Object[0]);
        if (!snapshot.isFromCache()) {
            return true;
        }
        boolean maybeOnline = !onlineState.equals(OnlineState.OFFLINE);
        if (!this.options.waitForSyncWhenOnline || !maybeOnline) {
            return !snapshot.getDocuments().isEmpty() || snapshot.hasCachedResults() || onlineState.equals(OnlineState.OFFLINE);
        }
        Assert.hardAssert(snapshot.isFromCache(), "Waiting for sync, but snapshot is not from cache", new Object[0]);
        return false;
    }

    private boolean shouldRaiseEvent(ViewSnapshot snapshot) {
        if (!snapshot.getChanges().isEmpty()) {
            return true;
        }
        boolean hasPendingWritesChanged = (this.snapshot == null || this.snapshot.hasPendingWrites() == snapshot.hasPendingWrites()) ? false : true;
        if (snapshot.didSyncStateChange() || hasPendingWritesChanged) {
            return this.options.includeQueryMetadataChanges;
        }
        return false;
    }

    private void raiseInitialEvent(ViewSnapshot snapshot) {
        Assert.hardAssert(!this.raisedInitialEvent, "Trying to raise initial event for second time", new Object[0]);
        ViewSnapshot snapshot2 = ViewSnapshot.fromInitialDocuments(snapshot.getQuery(), snapshot.getDocuments(), snapshot.getMutatedKeys(), snapshot.isFromCache(), snapshot.excludesMetadataChanges(), snapshot.hasCachedResults());
        this.raisedInitialEvent = true;
        this.listener.onEvent(snapshot2, null);
    }
}
