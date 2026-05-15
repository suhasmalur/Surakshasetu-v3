package com.google.firebase.firestore;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.mutation.ArrayTransformOperation;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.NumericIncrementTransformOperation;
import com.google.firebase.firestore.model.mutation.ServerTimestampOperation;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.CustomClassMapper;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.NullValue;
import com.google.type.LatLng;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
public final class UserDataReader {
    private final DatabaseId databaseId;

    public UserDataReader(DatabaseId databaseId) {
        this.databaseId = databaseId;
    }

    public UserData.ParsedSetData parseSetData(Object input) {
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(UserData.Source.Set);
        ObjectValue updateData = convertAndParseDocumentData(input, accumulator.rootContext());
        return accumulator.toSetData(updateData);
    }

    public UserData.ParsedSetData parseMergeData(Object input, FieldMask fieldMask) {
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(UserData.Source.MergeSet);
        ObjectValue updateData = convertAndParseDocumentData(input, accumulator.rootContext());
        if (fieldMask != null) {
            for (com.google.firebase.firestore.model.FieldPath field : fieldMask.getMask()) {
                if (!accumulator.contains(field)) {
                    throw new IllegalArgumentException("Field '" + field.toString() + "' is specified in your field mask but not in your input data.");
                }
            }
            return accumulator.toMergeData(updateData, fieldMask);
        }
        return accumulator.toMergeData(updateData);
    }

    public UserData.ParsedUpdateData parseUpdateData(Map<String, Object> data) {
        Preconditions.checkNotNull(data, "Provided update data must not be null.");
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(UserData.Source.Update);
        UserData.ParseContext context = accumulator.rootContext();
        ObjectValue updateData = new ObjectValue();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            com.google.firebase.firestore.model.FieldPath fieldPath = FieldPath.fromDotSeparatedPath(entry.getKey()).getInternalPath();
            Object fieldValue = entry.getValue();
            if (fieldValue instanceof FieldValue.DeleteFieldValue) {
                context.addToFieldMask(fieldPath);
            } else {
                Value parsedValue = convertAndParseFieldData(fieldValue, context.childContext(fieldPath));
                if (parsedValue != null) {
                    context.addToFieldMask(fieldPath);
                    updateData.set(fieldPath, parsedValue);
                }
            }
        }
        return accumulator.toUpdateData(updateData);
    }

    public UserData.ParsedUpdateData parseUpdateData(List<Object> fieldsAndValues) {
        com.google.firebase.firestore.model.FieldPath parsedField;
        Assert.hardAssert(fieldsAndValues.size() % 2 == 0, "Expected fieldAndValues to contain an even number of elements", new Object[0]);
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(UserData.Source.Update);
        UserData.ParseContext context = accumulator.rootContext();
        ObjectValue updateData = new ObjectValue();
        Iterator<Object> iterator = fieldsAndValues.iterator();
        while (iterator.hasNext()) {
            Object fieldPath = iterator.next();
            Object fieldValue = iterator.next();
            Assert.hardAssert((fieldPath instanceof String) || (fieldPath instanceof FieldPath), "Expected argument to be String or FieldPath.", new Object[0]);
            if (fieldPath instanceof String) {
                parsedField = FieldPath.fromDotSeparatedPath((String) fieldPath).getInternalPath();
            } else {
                parsedField = ((FieldPath) fieldPath).getInternalPath();
            }
            if (fieldValue instanceof FieldValue.DeleteFieldValue) {
                context.addToFieldMask(parsedField);
            } else {
                Value parsedValue = convertAndParseFieldData(fieldValue, context.childContext(parsedField));
                if (parsedValue != null) {
                    context.addToFieldMask(parsedField);
                    updateData.set(parsedField, parsedValue);
                }
            }
        }
        return accumulator.toUpdateData(updateData);
    }

    public Value parseQueryValue(Object input) {
        return parseQueryValue(input, false);
    }

    public Value parseQueryValue(Object input, boolean allowArrays) {
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(allowArrays ? UserData.Source.ArrayArgument : UserData.Source.Argument);
        Value parsed = convertAndParseFieldData(input, accumulator.rootContext());
        Assert.hardAssert(parsed != null, "Parsed data should not be null.", new Object[0]);
        Assert.hardAssert(accumulator.getFieldTransforms().isEmpty(), "Field transforms should have been disallowed.", new Object[0]);
        return parsed;
    }

    public Value convertAndParseFieldData(Object input, UserData.ParseContext context) {
        Object converted = CustomClassMapper.convertToPlainJavaTypes(input);
        return parseData(converted, context);
    }

    private ObjectValue convertAndParseDocumentData(Object input, UserData.ParseContext context) {
        if (input.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid data. Data must be a Map<String, Object> or a suitable POJO object, but it was an array");
        }
        Object converted = CustomClassMapper.convertToPlainJavaTypes(input);
        Value parsedValue = parseData(converted, context);
        if (parsedValue.getValueTypeCase() != Value.ValueTypeCase.MAP_VALUE) {
            throw new IllegalArgumentException("Invalid data. Data must be a Map<String, Object> or a suitable POJO object, but it was of type: " + Util.typeName(input));
        }
        return new ObjectValue(parsedValue);
    }

    private Value parseData(Object input, UserData.ParseContext context) {
        if (input instanceof Map) {
            return parseMap((Map) input, context);
        }
        if (input instanceof FieldValue) {
            parseSentinelFieldValue((FieldValue) input, context);
            return null;
        }
        if (context.getPath() != null) {
            context.addToFieldMask(context.getPath());
        }
        if (input instanceof List) {
            if (context.isArrayElement() && context.getDataSource() != UserData.Source.ArrayArgument) {
                throw context.createError("Nested arrays are not supported");
            }
            return parseList((List) input, context);
        }
        return parseScalarValue(input, context);
    }

    private <K, V> Value parseMap(Map<K, V> map, UserData.ParseContext parseContext) {
        if (map.isEmpty()) {
            if (parseContext.getPath() != null && !parseContext.getPath().isEmpty()) {
                parseContext.addToFieldMask(parseContext.getPath());
            }
            return Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build();
        }
        MapValue.Builder builderNewBuilder = MapValue.newBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (!(entry.getKey() instanceof String)) {
                throw parseContext.createError(String.format("Non-String Map key (%s) is not allowed", entry.getValue()));
            }
            String str = (String) entry.getKey();
            Value data = parseData(entry.getValue(), parseContext.childContext(str));
            if (data != null) {
                builderNewBuilder.putFields(str, data);
            }
        }
        return Value.newBuilder().setMapValue(builderNewBuilder).build();
    }

    private <T> Value parseList(List<T> list, UserData.ParseContext context) {
        ArrayValue.Builder arrayBuilder = ArrayValue.newBuilder();
        int entryIndex = 0;
        for (T entry : list) {
            Value parsedEntry = parseData(entry, context.childContext(entryIndex));
            if (parsedEntry == null) {
                parsedEntry = Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
            }
            arrayBuilder.addValues(parsedEntry);
            entryIndex++;
        }
        return Value.newBuilder().setArrayValue(arrayBuilder).build();
    }

    private void parseSentinelFieldValue(FieldValue value, UserData.ParseContext context) {
        if (!context.isWrite()) {
            throw context.createError(String.format("%s() can only be used with set() and update()", value.getMethodName()));
        }
        if (context.getPath() == null) {
            throw context.createError(String.format("%s() is not currently supported inside arrays", value.getMethodName()));
        }
        if (value instanceof FieldValue.DeleteFieldValue) {
            if (context.getDataSource() == UserData.Source.MergeSet) {
                context.addToFieldMask(context.getPath());
                return;
            } else {
                if (context.getDataSource() == UserData.Source.Update) {
                    Assert.hardAssert(context.getPath().length() > 0, "FieldValue.delete() at the top level should have already been handled.", new Object[0]);
                    throw context.createError("FieldValue.delete() can only appear at the top level of your update data");
                }
                throw context.createError("FieldValue.delete() can only be used with update() and set() with SetOptions.merge()");
            }
        }
        if (value instanceof FieldValue.ServerTimestampFieldValue) {
            context.addToFieldTransforms(context.getPath(), ServerTimestampOperation.getInstance());
            return;
        }
        if (value instanceof FieldValue.ArrayUnionFieldValue) {
            List<Value> parsedElements = parseArrayTransformElements(((FieldValue.ArrayUnionFieldValue) value).getElements());
            ArrayTransformOperation arrayUnion = new ArrayTransformOperation.Union(parsedElements);
            context.addToFieldTransforms(context.getPath(), arrayUnion);
        } else if (value instanceof FieldValue.ArrayRemoveFieldValue) {
            List<Value> parsedElements2 = parseArrayTransformElements(((FieldValue.ArrayRemoveFieldValue) value).getElements());
            ArrayTransformOperation arrayRemove = new ArrayTransformOperation.Remove(parsedElements2);
            context.addToFieldTransforms(context.getPath(), arrayRemove);
        } else {
            if (value instanceof FieldValue.NumericIncrementFieldValue) {
                FieldValue.NumericIncrementFieldValue numericIncrementFieldValue = (FieldValue.NumericIncrementFieldValue) value;
                Value operand = parseQueryValue(numericIncrementFieldValue.getOperand());
                NumericIncrementTransformOperation incrementOperation = new NumericIncrementTransformOperation(operand);
                context.addToFieldTransforms(context.getPath(), incrementOperation);
                return;
            }
            throw Assert.fail("Unknown FieldValue type: %s", Util.typeName(value));
        }
    }

    private Value parseScalarValue(Object input, UserData.ParseContext context) {
        if (input == null) {
            return Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
        }
        if (input instanceof Integer) {
            return Value.newBuilder().setIntegerValue(((Integer) input).intValue()).build();
        }
        if (input instanceof Long) {
            return Value.newBuilder().setIntegerValue(((Long) input).longValue()).build();
        }
        if (input instanceof Float) {
            return Value.newBuilder().setDoubleValue(((Float) input).doubleValue()).build();
        }
        if (input instanceof Double) {
            return Value.newBuilder().setDoubleValue(((Double) input).doubleValue()).build();
        }
        if (input instanceof Boolean) {
            return Value.newBuilder().setBooleanValue(((Boolean) input).booleanValue()).build();
        }
        if (input instanceof String) {
            return Value.newBuilder().setStringValue((String) input).build();
        }
        if (input instanceof Date) {
            Timestamp timestamp = new Timestamp((Date) input);
            return parseTimestamp(timestamp);
        }
        if (input instanceof Timestamp) {
            Timestamp timestamp2 = (Timestamp) input;
            return parseTimestamp(timestamp2);
        }
        if (input instanceof GeoPoint) {
            GeoPoint geoPoint = (GeoPoint) input;
            return Value.newBuilder().setGeoPointValue(LatLng.newBuilder().setLatitude(geoPoint.getLatitude()).setLongitude(geoPoint.getLongitude())).build();
        }
        if (input instanceof Blob) {
            return Value.newBuilder().setBytesValue(((Blob) input).toByteString()).build();
        }
        if (input instanceof DocumentReference) {
            DocumentReference ref = (DocumentReference) input;
            if (ref.getFirestore() != null) {
                DatabaseId otherDb = ref.getFirestore().getDatabaseId();
                if (!otherDb.equals(this.databaseId)) {
                    throw context.createError(String.format("Document reference is for database %s/%s but should be for database %s/%s", otherDb.getProjectId(), otherDb.getDatabaseId(), this.databaseId.getProjectId(), this.databaseId.getDatabaseId()));
                }
            }
            return Value.newBuilder().setReferenceValue(String.format("projects/%s/databases/%s/documents/%s", this.databaseId.getProjectId(), this.databaseId.getDatabaseId(), ((DocumentReference) input).getPath())).build();
        }
        if (input.getClass().isArray()) {
            throw context.createError("Arrays are not supported; use a List instead");
        }
        throw context.createError("Unsupported type: " + Util.typeName(input));
    }

    private Value parseTimestamp(Timestamp timestamp) {
        int truncatedNanoseconds = (timestamp.getNanoseconds() / 1000) * 1000;
        return Value.newBuilder().setTimestampValue(com.google.protobuf.Timestamp.newBuilder().setSeconds(timestamp.getSeconds()).setNanos(truncatedNanoseconds)).build();
    }

    private List<Value> parseArrayTransformElements(List<Object> elements) {
        UserData.ParseAccumulator accumulator = new UserData.ParseAccumulator(UserData.Source.Argument);
        List<Value> result = new ArrayList<>(elements.size());
        for (int i = 0; i < elements.size(); i++) {
            Object element = elements.get(i);
            UserData.ParseContext context = accumulator.rootContext();
            result.add(convertAndParseFieldData(element, context.childContext(i)));
        }
        return result;
    }
}
