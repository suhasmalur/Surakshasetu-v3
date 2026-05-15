package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.FieldPath;

/* JADX INFO: loaded from: classes10.dex */
public final class FieldTransform {
    private final FieldPath fieldPath;
    private final TransformOperation operation;

    public FieldTransform(FieldPath fieldPath, TransformOperation operation) {
        this.fieldPath = fieldPath;
        this.operation = operation;
    }

    public FieldPath getFieldPath() {
        return this.fieldPath;
    }

    public TransformOperation getOperation() {
        return this.operation;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldTransform that = (FieldTransform) o;
        if (!this.fieldPath.equals(that.fieldPath)) {
            return false;
        }
        return this.operation.equals(that.operation);
    }

    public int hashCode() {
        int result = this.fieldPath.hashCode();
        return (result * 31) + this.operation.hashCode();
    }
}
