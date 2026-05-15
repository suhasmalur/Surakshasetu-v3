package com.google.firebase.firestore;

import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.util.Preconditions;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes10.dex */
public final class SetOptions {
    private final FieldMask fieldMask;
    private final boolean merge;
    static final SetOptions OVERWRITE = new SetOptions(false, null);
    private static final SetOptions MERGE_ALL_FIELDS = new SetOptions(true, null);

    private SetOptions(boolean merge, FieldMask fieldMask) {
        Preconditions.checkArgument(fieldMask == null || merge, "Cannot specify a fieldMask for non-merge sets()", new Object[0]);
        this.merge = merge;
        this.fieldMask = fieldMask;
    }

    boolean isMerge() {
        return this.merge;
    }

    public FieldMask getFieldMask() {
        return this.fieldMask;
    }

    public static SetOptions merge() {
        return MERGE_ALL_FIELDS;
    }

    public static SetOptions mergeFields(List<String> fields) {
        Set<com.google.firebase.firestore.model.FieldPath> fieldPaths = new HashSet<>();
        for (String field : fields) {
            fieldPaths.add(FieldPath.fromDotSeparatedPath(field).getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(fieldPaths));
    }

    public static SetOptions mergeFields(String... fields) {
        Set<com.google.firebase.firestore.model.FieldPath> fieldPaths = new HashSet<>();
        for (String field : fields) {
            fieldPaths.add(FieldPath.fromDotSeparatedPath(field).getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(fieldPaths));
    }

    public static SetOptions mergeFieldPaths(List<FieldPath> fields) {
        Set<com.google.firebase.firestore.model.FieldPath> fieldPaths = new HashSet<>();
        for (FieldPath field : fields) {
            fieldPaths.add(field.getInternalPath());
        }
        return new SetOptions(true, FieldMask.fromSet(fieldPaths));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetOptions that = (SetOptions) o;
        if (this.merge != that.merge) {
            return false;
        }
        if (this.fieldMask != null) {
            return this.fieldMask.equals(that.fieldMask);
        }
        if (that.fieldMask == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.merge ? 1 : 0) * 31) + (this.fieldMask != null ? this.fieldMask.hashCode() : 0);
    }
}
