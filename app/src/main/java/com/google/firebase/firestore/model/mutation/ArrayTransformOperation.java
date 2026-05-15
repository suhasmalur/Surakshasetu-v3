package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.Value;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public abstract class ArrayTransformOperation implements TransformOperation {
    private final List<Value> elements;

    protected abstract Value apply(Value value);

    ArrayTransformOperation(List<Value> elements) {
        this.elements = Collections.unmodifiableList(elements);
    }

    public List<Value> getElements() {
        return this.elements;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToLocalView(Value previousValue, Timestamp localWriteTime) {
        return apply(previousValue);
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToRemoteDocument(Value previousValue, Value transformResult) {
        return apply(previousValue);
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value computeBaseValue(Value currentValue) {
        return null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayTransformOperation that = (ArrayTransformOperation) o;
        return this.elements.equals(that.elements);
    }

    public int hashCode() {
        int result = getClass().hashCode();
        return (result * 31) + this.elements.hashCode();
    }

    static ArrayValue.Builder coercedFieldValuesArray(Value value) {
        if (Values.isArray(value)) {
            return value.getArrayValue().toBuilder();
        }
        return ArrayValue.newBuilder();
    }

    public static class Union extends ArrayTransformOperation {
        public Union(List<Value> elements) {
            super(elements);
        }

        @Override // com.google.firebase.firestore.model.mutation.ArrayTransformOperation
        protected Value apply(Value previousValue) {
            ArrayValue.Builder result = coercedFieldValuesArray(previousValue);
            for (Value unionElement : getElements()) {
                if (!Values.contains(result, unionElement)) {
                    result.addValues(unionElement);
                }
            }
            return Value.newBuilder().setArrayValue(result).build();
        }
    }

    public static class Remove extends ArrayTransformOperation {
        public Remove(List<Value> elements) {
            super(elements);
        }

        @Override // com.google.firebase.firestore.model.mutation.ArrayTransformOperation
        protected Value apply(Value previousValue) {
            ArrayValue.Builder result = coercedFieldValuesArray(previousValue);
            for (Value removeElement : getElements()) {
                int i = 0;
                while (i < result.getValuesCount()) {
                    if (Values.equals(result.getValues(i), removeElement)) {
                        result.removeValues(i);
                    } else {
                        i++;
                    }
                }
            }
            return Value.newBuilder().setArrayValue(result).build();
        }
    }
}
