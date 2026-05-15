package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
public final class PatchMutation extends Mutation {
    private final FieldMask mask;
    private final ObjectValue value;

    public PatchMutation(DocumentKey key, ObjectValue value, FieldMask mask, Precondition precondition) {
        this(key, value, mask, precondition, new ArrayList());
    }

    public PatchMutation(DocumentKey key, ObjectValue value, FieldMask mask, Precondition precondition, List<FieldTransform> fieldTransforms) {
        super(key, precondition, fieldTransforms);
        this.value = value;
        this.mask = mask;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PatchMutation that = (PatchMutation) o;
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
        return "PatchMutation{" + keyAndPreconditionToString() + ", mask=" + this.mask + ", value=" + this.value + "}";
    }

    public ObjectValue getValue() {
        return this.value;
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public FieldMask getFieldMask() {
        return this.mask;
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public void applyToRemoteDocument(MutableDocument document, MutationResult mutationResult) {
        verifyKeyMatches(document);
        if (!getPrecondition().isValidFor(document)) {
            document.convertToUnknownDocument(mutationResult.getVersion());
            return;
        }
        Map<FieldPath, Value> transformResults = serverTransformResults(document, mutationResult.getTransformResults());
        ObjectValue value = document.getData();
        value.setAll(getPatch());
        value.setAll(transformResults);
        document.convertToFoundDocument(mutationResult.getVersion(), document.getData()).setHasCommittedMutations();
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public FieldMask applyToLocalView(MutableDocument document, FieldMask previousMask, Timestamp localWriteTime) {
        verifyKeyMatches(document);
        if (!getPrecondition().isValidFor(document)) {
            return previousMask;
        }
        Map<FieldPath, Value> transformResults = localTransformResults(localWriteTime, document);
        Map<FieldPath, Value> patches = getPatch();
        ObjectValue value = document.getData();
        value.setAll(patches);
        value.setAll(transformResults);
        document.convertToFoundDocument(document.getVersion(), document.getData()).setHasLocalMutations();
        if (previousMask == null) {
            return null;
        }
        HashSet<FieldPath> mergedMaskSet = new HashSet<>(previousMask.getMask());
        mergedMaskSet.addAll(this.mask.getMask());
        mergedMaskSet.addAll(getFieldTransformPaths());
        return FieldMask.fromSet(mergedMaskSet);
    }

    private List<FieldPath> getFieldTransformPaths() {
        List<FieldPath> result = new ArrayList<>();
        for (FieldTransform fieldTransform : getFieldTransforms()) {
            result.add(fieldTransform.getFieldPath());
        }
        return result;
    }

    private Map<FieldPath, Value> getPatch() {
        Map<FieldPath, Value> result = new HashMap<>();
        for (FieldPath path : this.mask.getMask()) {
            if (!path.isEmpty()) {
                result.put(path, this.value.get(path));
            }
        }
        return result;
    }
}
