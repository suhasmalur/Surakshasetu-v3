package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.mutation.FieldMask;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public class OverlayedDocument {

    @Nullable
    private FieldMask mutatedFields;
    private Document overlayedDocument;

    OverlayedDocument(Document overlayedDocument, FieldMask mutatedFields) {
        this.overlayedDocument = overlayedDocument;
        this.mutatedFields = mutatedFields;
    }

    public Document getDocument() {
        return this.overlayedDocument;
    }

    @Nullable
    public FieldMask getMutatedFields() {
        return this.mutatedFields;
    }
}
