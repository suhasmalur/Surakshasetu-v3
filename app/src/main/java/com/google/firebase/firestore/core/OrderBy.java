package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;

/* JADX INFO: loaded from: classes10.dex */
public class OrderBy {
    private final Direction direction;
    final FieldPath field;

    public enum Direction {
        ASCENDING(1),
        DESCENDING(-1);

        private final int comparisonModifier;

        Direction(int comparisonModifier) {
            this.comparisonModifier = comparisonModifier;
        }

        int getComparisonModifier() {
            return this.comparisonModifier;
        }
    }

    public static OrderBy getInstance(Direction direction, FieldPath path) {
        return new OrderBy(direction, path);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public FieldPath getField() {
        return this.field;
    }

    private OrderBy(Direction direction, FieldPath field) {
        this.direction = direction;
        this.field = field;
    }

    int compare(Document d1, Document d2) {
        if (this.field.equals(FieldPath.KEY_PATH)) {
            return this.direction.getComparisonModifier() * d1.getKey().compareTo(d2.getKey());
        }
        Value v1 = d1.getField(this.field);
        Value v2 = d2.getField(this.field);
        Assert.hardAssert((v1 == null || v2 == null) ? false : true, "Trying to compare documents on fields that don't exist.", new Object[0]);
        return this.direction.getComparisonModifier() * Values.compare(v1, v2);
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof OrderBy)) {
            return false;
        }
        OrderBy other = (OrderBy) o;
        return this.direction == other.direction && this.field.equals(other.field);
    }

    public int hashCode() {
        int result = (29 * 31) + this.direction.hashCode();
        return (result * 31) + this.field.hashCode();
    }

    public String toString() {
        return (this.direction == Direction.ASCENDING ? "" : "-") + this.field.canonicalString();
    }
}
