package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.FieldPath;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes10.dex */
public final class FieldMask {
    public static FieldMask EMPTY = fromSet(new HashSet());
    private final Set<FieldPath> mask;

    public static FieldMask fromSet(Set<FieldPath> mask) {
        return new FieldMask(mask);
    }

    private FieldMask(Set<FieldPath> mask) {
        this.mask = mask;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldMask fieldMask = (FieldMask) o;
        return this.mask.equals(fieldMask.mask);
    }

    public String toString() {
        return "FieldMask{mask=" + this.mask.toString() + "}";
    }

    public boolean covers(FieldPath fieldPath) {
        for (FieldPath fieldMaskPath : this.mask) {
            if (fieldMaskPath.isPrefixOf(fieldPath)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.mask.hashCode();
    }

    public Set<FieldPath> getMask() {
        return this.mask;
    }
}
