package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public class FieldFilter extends Filter {
    private final FieldPath field;
    private final Operator operator;
    private final Value value;

    public enum Operator {
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL("<="),
        EQUAL("=="),
        NOT_EQUAL("!="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL(">="),
        ARRAY_CONTAINS("array_contains"),
        ARRAY_CONTAINS_ANY("array_contains_any"),
        IN("in"),
        NOT_IN("not_in");

        private final String text;

        Operator(String text) {
            this.text = text;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    protected FieldFilter(FieldPath field, Operator operator, Value value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public FieldPath getField() {
        return this.field;
    }

    public Value getValue() {
        return this.value;
    }

    public static FieldFilter create(FieldPath path, Operator operator, Value value) {
        if (path.isKeyField()) {
            if (operator == Operator.IN) {
                return new KeyFieldInFilter(path, value);
            }
            if (operator == Operator.NOT_IN) {
                return new KeyFieldNotInFilter(path, value);
            }
            Assert.hardAssert((operator == Operator.ARRAY_CONTAINS || operator == Operator.ARRAY_CONTAINS_ANY) ? false : true, operator.toString() + "queries don't make sense on document keys", new Object[0]);
            return new KeyFieldFilter(path, operator, value);
        }
        if (operator == Operator.ARRAY_CONTAINS) {
            return new ArrayContainsFilter(path, value);
        }
        if (operator == Operator.IN) {
            return new InFilter(path, value);
        }
        if (operator == Operator.ARRAY_CONTAINS_ANY) {
            return new ArrayContainsAnyFilter(path, value);
        }
        if (operator == Operator.NOT_IN) {
            return new NotInFilter(path, value);
        }
        return new FieldFilter(path, operator, value);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public boolean matches(Document doc) {
        Value other = doc.getField(this.field);
        return this.operator == Operator.NOT_EQUAL ? other != null && matchesComparison(Values.compare(other, this.value)) : other != null && Values.typeOrder(other) == Values.typeOrder(this.value) && matchesComparison(Values.compare(other, this.value));
    }

    protected boolean matchesComparison(int comp) {
        switch (this.operator) {
            case LESS_THAN:
                return comp < 0;
            case LESS_THAN_OR_EQUAL:
                return comp <= 0;
            case EQUAL:
                return comp == 0;
            case NOT_EQUAL:
                return comp != 0;
            case GREATER_THAN:
                return comp > 0;
            case GREATER_THAN_OR_EQUAL:
                return comp >= 0;
            default:
                throw Assert.fail("Unknown FieldFilter operator: %s", this.operator);
        }
    }

    public boolean isInequality() {
        return Arrays.asList(Operator.LESS_THAN, Operator.LESS_THAN_OR_EQUAL, Operator.GREATER_THAN, Operator.GREATER_THAN_OR_EQUAL, Operator.NOT_EQUAL, Operator.NOT_IN).contains(this.operator);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public String getCanonicalId() {
        return getField().canonicalString() + getOperator().toString() + Values.canonicalId(getValue());
    }

    @Override // com.google.firebase.firestore.core.Filter
    public List<FieldFilter> getFlattenedFilters() {
        return Collections.singletonList(this);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public List<Filter> getFilters() {
        return Collections.singletonList(this);
    }

    public String toString() {
        return getCanonicalId();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof FieldFilter)) {
            return false;
        }
        FieldFilter other = (FieldFilter) o;
        return this.operator == other.operator && this.field.equals(other.field) && this.value.equals(other.value);
    }

    public int hashCode() {
        int result = (37 * 31) + this.operator.hashCode();
        return (((result * 31) + this.field.hashCode()) * 31) + this.value.hashCode();
    }
}
