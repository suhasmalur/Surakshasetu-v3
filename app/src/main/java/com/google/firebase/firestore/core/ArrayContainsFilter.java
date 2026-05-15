package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.Value;

/* JADX INFO: loaded from: classes10.dex */
public class ArrayContainsFilter extends FieldFilter {
    ArrayContainsFilter(FieldPath field, Value value) {
        super(field, FieldFilter.Operator.ARRAY_CONTAINS, value);
    }

    @Override // com.google.firebase.firestore.core.FieldFilter, com.google.firebase.firestore.core.Filter
    public boolean matches(Document doc) {
        Value other = doc.getField(getField());
        return Values.isArray(other) && Values.contains(other.getArrayValue(), getValue());
    }
}
