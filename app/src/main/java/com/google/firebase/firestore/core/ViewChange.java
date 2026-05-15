package com.google.firebase.firestore.core;

import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public class ViewChange {
    private final List<LimboDocumentChange> limboChanges;
    private final ViewSnapshot snapshot;

    public ViewChange(ViewSnapshot snapshot, List<LimboDocumentChange> limboChanges) {
        this.snapshot = snapshot;
        this.limboChanges = limboChanges;
    }

    public ViewSnapshot getSnapshot() {
        return this.snapshot;
    }

    public List<LimboDocumentChange> getLimboChanges() {
        return this.limboChanges;
    }
}
