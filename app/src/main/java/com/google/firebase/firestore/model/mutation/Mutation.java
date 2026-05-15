package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
public abstract class Mutation {
    private final List<FieldTransform> fieldTransforms;
    private final DocumentKey key;
    private final Precondition precondition;

    public abstract FieldMask applyToLocalView(MutableDocument mutableDocument, FieldMask fieldMask, Timestamp timestamp);

    public abstract void applyToRemoteDocument(MutableDocument mutableDocument, MutationResult mutationResult);

    public abstract FieldMask getFieldMask();

    Mutation(DocumentKey key, Precondition precondition) {
        this(key, precondition, new ArrayList());
    }

    Mutation(DocumentKey key, Precondition precondition, List<FieldTransform> fieldTransforms) {
        this.key = key;
        this.precondition = precondition;
        this.fieldTransforms = fieldTransforms;
    }

    public static Mutation calculateOverlayMutation(MutableDocument doc, FieldMask mask) {
        if (!doc.hasLocalMutations()) {
            return null;
        }
        if (mask != null && mask.getMask().isEmpty()) {
            return null;
        }
        if (mask == null) {
            if (doc.isNoDocument()) {
                return new DeleteMutation(doc.getKey(), Precondition.NONE);
            }
            return new SetMutation(doc.getKey(), doc.getData(), Precondition.NONE);
        }
        ObjectValue docValue = doc.getData();
        ObjectValue patchValue = new ObjectValue();
        HashSet<FieldPath> maskSet = new HashSet<>();
        for (FieldPath path : mask.getMask()) {
            if (!maskSet.contains(path)) {
                Value value = docValue.get(path);
                if (value == null && path.length() > 1) {
                    path = path.popLast();
                }
                patchValue.set(path, docValue.get(path));
                maskSet.add(path);
            }
        }
        return new PatchMutation(doc.getKey(), patchValue, FieldMask.fromSet(maskSet), Precondition.NONE);
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public Precondition getPrecondition() {
        return this.precondition;
    }

    public List<FieldTransform> getFieldTransforms() {
        return this.fieldTransforms;
    }

    boolean hasSameKeyAndPrecondition(Mutation other) {
        return this.key.equals(other.key) && this.precondition.equals(other.precondition);
    }

    int keyAndPreconditionHashCode() {
        return (getKey().hashCode() * 31) + this.precondition.hashCode();
    }

    String keyAndPreconditionToString() {
        return "key=" + this.key + ", precondition=" + this.precondition;
    }

    void verifyKeyMatches(MutableDocument document) {
        Assert.hardAssert(document.getKey().equals(getKey()), "Can only apply a mutation to a document with the same key", new Object[0]);
    }

    protected Map<FieldPath, Value> serverTransformResults(MutableDocument mutableDocument, List<Value> serverTransformResults) {
        Map<FieldPath, Value> transformResults = new HashMap<>(this.fieldTransforms.size());
        Assert.hardAssert(this.fieldTransforms.size() == serverTransformResults.size(), "server transform count (%d) should match field transform count (%d)", Integer.valueOf(serverTransformResults.size()), Integer.valueOf(this.fieldTransforms.size()));
        for (int i = 0; i < serverTransformResults.size(); i++) {
            FieldTransform fieldTransform = this.fieldTransforms.get(i);
            TransformOperation transform = fieldTransform.getOperation();
            Value previousValue = mutableDocument.getField(fieldTransform.getFieldPath());
            transformResults.put(fieldTransform.getFieldPath(), transform.applyToRemoteDocument(previousValue, serverTransformResults.get(i)));
        }
        return transformResults;
    }

    protected Map<FieldPath, Value> localTransformResults(Timestamp localWriteTime, MutableDocument mutableDocument) {
        Map<FieldPath, Value> transformResults = new HashMap<>(this.fieldTransforms.size());
        for (FieldTransform fieldTransform : this.fieldTransforms) {
            TransformOperation transform = fieldTransform.getOperation();
            Value previousValue = mutableDocument.getField(fieldTransform.getFieldPath());
            transformResults.put(fieldTransform.getFieldPath(), transform.applyToLocalView(previousValue, localWriteTime));
        }
        return transformResults;
    }

    public ObjectValue extractTransformBaseValue(Document document) {
        ObjectValue baseObject = null;
        for (FieldTransform transform : this.fieldTransforms) {
            Value existingValue = document.getField(transform.getFieldPath());
            Value coercedValue = transform.getOperation().computeBaseValue(existingValue);
            if (coercedValue != null) {
                if (baseObject == null) {
                    baseObject = new ObjectValue();
                }
                baseObject.set(transform.getFieldPath(), coercedValue);
            }
        }
        return baseObject;
    }
}
