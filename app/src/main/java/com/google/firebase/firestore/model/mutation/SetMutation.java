package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
public final class SetMutation extends Mutation {
    private final ObjectValue value;

    public SetMutation(DocumentKey key, ObjectValue value, Precondition precondition) {
        this(key, value, precondition, new ArrayList());
    }

    public SetMutation(DocumentKey key, ObjectValue value, Precondition precondition, List<FieldTransform> fieldTransforms) {
        super(key, precondition, fieldTransforms);
        this.value = value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetMutation that = (SetMutation) o;
        if (hasSameKeyAndPrecondition(that) && this.value.equals(that.value) && getFieldTransforms().equals(that.getFieldTransforms())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = keyAndPreconditionHashCode();
        return (result * 31) + this.value.hashCode();
    }

    public String toString() {
        return "SetMutation{" + keyAndPreconditionToString() + ", value=" + this.value + "}";
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public void applyToRemoteDocument(MutableDocument document, MutationResult mutationResult) {
        verifyKeyMatches(document);
        ObjectValue newData = this.value.m372clone();
        Map<FieldPath, Value> transformResults = serverTransformResults(document, mutationResult.getTransformResults());
        newData.setAll(transformResults);
        document.convertToFoundDocument(mutationResult.getVersion(), newData).setHasCommittedMutations();
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public FieldMask applyToLocalView(MutableDocument document, FieldMask previousMask, Timestamp localWriteTime) {
        verifyKeyMatches(document);
        if (!getPrecondition().isValidFor(document)) {
            return previousMask;
        }
        Map<FieldPath, Value> transformResults = localTransformResults(localWriteTime, document);
        ObjectValue localValue = this.value.m372clone();
        localValue.setAll(transformResults);
        document.convertToFoundDocument(document.getVersion(), localValue).setHasLocalMutations();
        return null;
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public FieldMask getFieldMask() {
        return null;
    }

    public ObjectValue getValue() {
        return this.value;
    }
}
