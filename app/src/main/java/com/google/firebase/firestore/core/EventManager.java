package com.google.firebase.firestore.core;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.core.SyncEngine;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes10.dex */
public final class EventManager implements SyncEngine.SyncEngineCallback {
    private final SyncEngine syncEngine;
    private final Set<EventListener<Void>> snapshotsInSyncListeners = new HashSet();
    private OnlineState onlineState = OnlineState.UNKNOWN;
    private final Map<Query, QueryListenersInfo> queries = new HashMap();

    public static class ListenOptions {
        public boolean includeDocumentMetadataChanges;
        public boolean includeQueryMetadataChanges;
        public boolean waitForSyncWhenOnline;
    }

    private static class QueryListenersInfo {
        private final List<QueryListener> listeners = new ArrayList();
        private int targetId;
        private ViewSnapshot viewSnapshot;

        QueryListenersInfo() {
        }
    }

    public EventManager(SyncEngine syncEngine) {
        this.syncEngine = syncEngine;
        syncEngine.setCallback(this);
    }

    public int addQueryListener(QueryListener queryListener) {
        Query query = queryListener.getQuery();
        QueryListenersInfo queryInfo = this.queries.get(query);
        boolean firstListen = queryInfo == null;
        if (firstListen) {
            queryInfo = new QueryListenersInfo();
            this.queries.put(query, queryInfo);
        }
        queryInfo.listeners.add(queryListener);
        boolean raisedEvent = queryListener.onOnlineStateChanged(this.onlineState);
        Assert.hardAssert(!raisedEvent, "onOnlineStateChanged() shouldn't raise an event for brand-new listeners.", new Object[0]);
        if (queryInfo.viewSnapshot != null) {
            boolean raisedEvent2 = queryListener.onViewSnapshot(queryInfo.viewSnapshot);
            if (raisedEvent2) {
                raiseSnapshotsInSyncEvent();
            }
        }
        if (firstListen) {
            queryInfo.targetId = this.syncEngine.listen(query);
        }
        return queryInfo.targetId;
    }

    public void removeQueryListener(QueryListener listener) {
        Query query = listener.getQuery();
        QueryListenersInfo queryInfo = this.queries.get(query);
        boolean lastListen = false;
        if (queryInfo != null) {
            queryInfo.listeners.remove(listener);
            lastListen = queryInfo.listeners.isEmpty();
        }
        if (lastListen) {
            this.queries.remove(query);
            this.syncEngine.stopListening(query);
        }
    }

    public void addSnapshotsInSyncListener(EventListener<Void> listener) {
        this.snapshotsInSyncListeners.add(listener);
        listener.onEvent(null, null);
    }

    public void removeSnapshotsInSyncListener(EventListener<Void> listener) {
        this.snapshotsInSyncListeners.remove(listener);
    }

    private void raiseSnapshotsInSyncEvent() {
        for (EventListener<Void> listener : this.snapshotsInSyncListeners) {
            listener.onEvent(null, null);
        }
    }

    @Override // com.google.firebase.firestore.core.SyncEngine.SyncEngineCallback
    public void onViewSnapshots(List<ViewSnapshot> snapshotList) {
        boolean raisedEvent = false;
        for (ViewSnapshot viewSnapshot : snapshotList) {
            Query query = viewSnapshot.getQuery();
            QueryListenersInfo info = this.queries.get(query);
            if (info != null) {
                for (QueryListener listener : info.listeners) {
                    if (listener.onViewSnapshot(viewSnapshot)) {
                        raisedEvent = true;
                    }
                }
                info.viewSnapshot = viewSnapshot;
            }
        }
        if (raisedEvent) {
            raiseSnapshotsInSyncEvent();
        }
    }

    @Override // com.google.firebase.firestore.core.SyncEngine.SyncEngineCallback
    public void onError(Query query, Status error) {
        QueryListenersInfo info = this.queries.get(query);
        if (info != null) {
            for (QueryListener listener : info.listeners) {
                listener.onError(Util.exceptionFromStatus(error));
            }
        }
        this.queries.remove(query);
    }

    @Override // com.google.firebase.firestore.core.SyncEngine.SyncEngineCallback
    public void handleOnlineStateChange(OnlineState onlineState) {
        boolean raisedEvent = false;
        this.onlineState = onlineState;
        for (QueryListenersInfo info : this.queries.values()) {
            for (QueryListener listener : info.listeners) {
                if (listener.onOnlineStateChanged(onlineState)) {
                    raisedEvent = true;
                }
            }
        }
        if (raisedEvent) {
            raiseSnapshotsInSyncEvent();
        }
    }
}
