package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
public class FirestoreIndexValueWriter {
    public static final int DOCUMENT_NAME_OFFSET = 5;
    public static final int INDEX_TYPE_ARRAY = 50;
    public static final int INDEX_TYPE_BLOB = 30;
    public static final int INDEX_TYPE_BOOLEAN = 10;
    public static final int INDEX_TYPE_GEOPOINT = 45;
    public static final int INDEX_TYPE_MAP = 55;
    public static final int INDEX_TYPE_NAN = 13;
    public static final int INDEX_TYPE_NULL = 5;
    public static final int INDEX_TYPE_NUMBER = 15;
    public static final int INDEX_TYPE_REFERENCE = 37;
    public static final int INDEX_TYPE_REFERENCE_SEGMENT = 60;
    public static final int INDEX_TYPE_STRING = 25;
    public static final int INDEX_TYPE_TIMESTAMP = 20;
    public static final FirestoreIndexValueWriter INSTANCE = new FirestoreIndexValueWriter();
    public static final int NOT_TRUNCATED = 2;

    private FirestoreIndexValueWriter() {
    }

    public void writeIndexValue(Value value, DirectionalIndexByteEncoder encoder) {
        writeIndexValueAux(value, encoder);
        encoder.writeInfinity();
    }

    private void writeIndexValueAux(Value indexValue, DirectionalIndexByteEncoder encoder) {
        switch (indexValue.getValueTypeCase()) {
            case NULL_VALUE:
                writeValueTypeLabel(encoder, 5);
                return;
            case BOOLEAN_VALUE:
                writeValueTypeLabel(encoder, 10);
                encoder.writeLong(indexValue.getBooleanValue() ? 1L : 0L);
                return;
            case DOUBLE_VALUE:
                double number = indexValue.getDoubleValue();
                if (Double.isNaN(number)) {
                    writeValueTypeLabel(encoder, 13);
                    return;
                }
                writeValueTypeLabel(encoder, 15);
                if (number == -0.0d) {
                    encoder.writeDouble(0.0d);
                    return;
                } else {
                    encoder.writeDouble(number);
                    return;
                }
            case INTEGER_VALUE:
                writeValueTypeLabel(encoder, 15);
                encoder.writeDouble(indexValue.getIntegerValue());
                return;
            case TIMESTAMP_VALUE:
                Timestamp timestamp = indexValue.getTimestampValue();
                writeValueTypeLabel(encoder, 20);
                encoder.writeLong(timestamp.getSeconds());
                encoder.writeLong(timestamp.getNanos());
                return;
            case STRING_VALUE:
                writeIndexString(indexValue.getStringValue(), encoder);
                writeTruncationMarker(encoder);
                return;
            case BYTES_VALUE:
                writeValueTypeLabel(encoder, 30);
                encoder.writeBytes(indexValue.getBytesValue());
                writeTruncationMarker(encoder);
                return;
            case REFERENCE_VALUE:
                writeIndexEntityRef(indexValue.getReferenceValue(), encoder);
                return;
            case GEO_POINT_VALUE:
                LatLng geoPoint = indexValue.getGeoPointValue();
                writeValueTypeLabel(encoder, 45);
                encoder.writeDouble(geoPoint.getLatitude());
                encoder.writeDouble(geoPoint.getLongitude());
                return;
            case MAP_VALUE:
                if (Values.isMaxValue(indexValue)) {
                    writeValueTypeLabel(encoder, Integer.MAX_VALUE);
                    return;
                } else {
                    writeIndexMap(indexValue.getMapValue(), encoder);
                    writeTruncationMarker(encoder);
                    return;
                }
            case ARRAY_VALUE:
                writeIndexArray(indexValue.getArrayValue(), encoder);
                writeTruncationMarker(encoder);
                return;
            default:
                throw new IllegalArgumentException("unknown index value type " + indexValue.getValueTypeCase());
        }
    }

    private void writeIndexString(String stringIndexValue, DirectionalIndexByteEncoder encoder) {
        writeValueTypeLabel(encoder, 25);
        writeUnlabeledIndexString(stringIndexValue, encoder);
    }

    private void writeUnlabeledIndexString(String stringIndexValue, DirectionalIndexByteEncoder encoder) {
        encoder.writeString(stringIndexValue);
    }

    private void writeIndexMap(MapValue mapIndexValue, DirectionalIndexByteEncoder encoder) {
        writeValueTypeLabel(encoder, 55);
        for (Map.Entry<String, Value> entry : mapIndexValue.getFieldsMap().entrySet()) {
            String key = entry.getKey();
            Value value = entry.getValue();
            writeIndexString(key, encoder);
            writeIndexValueAux(value, encoder);
        }
    }

    private void writeIndexArray(ArrayValue arrayIndexValue, DirectionalIndexByteEncoder encoder) {
        writeValueTypeLabel(encoder, 50);
        for (Value element : arrayIndexValue.getValuesList()) {
            writeIndexValueAux(element, encoder);
        }
    }

    private void writeIndexEntityRef(String referenceValue, DirectionalIndexByteEncoder encoder) {
        writeValueTypeLabel(encoder, 37);
        ResourcePath path = ResourcePath.fromString(referenceValue);
        int numSegments = path.length();
        for (int index = 5; index < numSegments; index++) {
            String segment = path.getSegment(index);
            writeValueTypeLabel(encoder, 60);
            writeUnlabeledIndexString(segment, encoder);
        }
    }

    private void writeValueTypeLabel(DirectionalIndexByteEncoder encoder, int typeOrder) {
        encoder.writeLong(typeOrder);
    }

    private void writeTruncationMarker(DirectionalIndexByteEncoder encoder) {
        encoder.writeLong(2L);
    }
}
