package com.google.firebase.firestore.model;

import com.google.firebase.Timestamp;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;

/* JADX INFO: loaded from: classes10.dex */
public final class ServerTimestamps {
    private static final String LOCAL_WRITE_TIME_KEY = "__local_write_time__";
    private static final String PREVIOUS_VALUE_KEY = "__previous_value__";
    private static final String SERVER_TIMESTAMP_SENTINEL = "server_timestamp";
    private static final String TYPE_KEY = "__type__";

    private ServerTimestamps() {
    }

    public static boolean isServerTimestamp(Value value) {
        Value type = value != null ? value.getMapValue().getFieldsOrDefault(TYPE_KEY, null) : null;
        return type != null && SERVER_TIMESTAMP_SENTINEL.equals(type.getStringValue());
    }

    public static Value valueOf(Timestamp localWriteTime, Value previousValue) {
        Value encodedType = Value.newBuilder().setStringValue(SERVER_TIMESTAMP_SENTINEL).build();
        Value encodeWriteTime = Value.newBuilder().setTimestampValue(com.google.protobuf.Timestamp.newBuilder().setSeconds(localWriteTime.getSeconds()).setNanos(localWriteTime.getNanoseconds())).build();
        MapValue.Builder mapRepresentation = MapValue.newBuilder().putFields(TYPE_KEY, encodedType).putFields(LOCAL_WRITE_TIME_KEY, encodeWriteTime);
        Value previousValue2 = isServerTimestamp(previousValue) ? getPreviousValue(previousValue) : previousValue;
        if (previousValue2 != null) {
            mapRepresentation.putFields(PREVIOUS_VALUE_KEY, previousValue2);
        }
        return Value.newBuilder().setMapValue(mapRepresentation).build();
    }

    public static Value getPreviousValue(Value serverTimestampValue) {
        Value previousValue = serverTimestampValue.getMapValue().getFieldsOrDefault(PREVIOUS_VALUE_KEY, null);
        if (isServerTimestamp(previousValue)) {
            return getPreviousValue(previousValue);
        }
        return previousValue;
    }

    public static com.google.protobuf.Timestamp getLocalWriteTime(Value serverTimestampValue) {
        return serverTimestampValue.getMapValue().getFieldsOrThrow(LOCAL_WRITE_TIME_KEY).getTimestampValue();
    }
}
