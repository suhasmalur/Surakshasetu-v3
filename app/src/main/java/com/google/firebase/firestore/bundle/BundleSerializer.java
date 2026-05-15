package com.google.firebase.firestore.bundle;

import android.util.Base64;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.NullValue;
import com.google.type.LatLng;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes10.dex */
public class BundleSerializer {
    private static final long MILLIS_PER_SECOND = 1000;
    private final RemoteSerializer remoteSerializer;
    private final SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

    public BundleSerializer(RemoteSerializer remoteSerializer) {
        this.remoteSerializer = remoteSerializer;
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.setGregorianChange(new Date(Long.MIN_VALUE));
        this.timestampFormat.setCalendar(calendar);
    }

    public NamedQuery decodeNamedQuery(JSONObject namedQuery) throws JSONException {
        String name = namedQuery.getString("name");
        BundledQuery bundledQuery = decodeBundledQuery(namedQuery.getJSONObject("bundledQuery"));
        SnapshotVersion readTime = decodeSnapshotVersion(namedQuery.get("readTime"));
        return new NamedQuery(name, bundledQuery, readTime);
    }

    public BundleMetadata decodeBundleMetadata(JSONObject bundleMetadata) throws JSONException {
        String bundleId = bundleMetadata.getString("id");
        int version = bundleMetadata.getInt("version");
        SnapshotVersion createTime = decodeSnapshotVersion(bundleMetadata.get("createTime"));
        int totalDocuments = bundleMetadata.getInt("totalDocuments");
        long totalBytes = bundleMetadata.getLong("totalBytes");
        return new BundleMetadata(bundleId, version, createTime, totalDocuments, totalBytes);
    }

    public BundledDocumentMetadata decodeBundledDocumentMetadata(JSONObject bundledDocumentMetadata) throws JSONException {
        DocumentKey key = DocumentKey.fromPath(decodeName(bundledDocumentMetadata.getString("name")));
        SnapshotVersion readTime = decodeSnapshotVersion(bundledDocumentMetadata.get("readTime"));
        boolean exists = bundledDocumentMetadata.optBoolean("exists", false);
        JSONArray queriesJson = bundledDocumentMetadata.optJSONArray("queries");
        List<String> queries = new ArrayList<>();
        if (queriesJson != null) {
            for (int i = 0; i < queriesJson.length(); i++) {
                queries.add(queriesJson.getString(i));
            }
        }
        return new BundledDocumentMetadata(key, readTime, exists, queries);
    }

    BundleDocument decodeDocument(JSONObject document) throws JSONException {
        String name = document.getString("name");
        DocumentKey key = DocumentKey.fromPath(decodeName(name));
        SnapshotVersion updateTime = decodeSnapshotVersion(document.get("updateTime"));
        Value.Builder value = Value.newBuilder();
        decodeMapValue(value, document.getJSONObject("fields"));
        return new BundleDocument(MutableDocument.newFoundDocument(key, updateTime, ObjectValue.fromMap(value.getMapValue().getFieldsMap())));
    }

    private ResourcePath decodeName(String name) {
        ResourcePath resourcePath = ResourcePath.fromString(name);
        if (!this.remoteSerializer.isLocalResourceName(resourcePath)) {
            throw new IllegalArgumentException("Resource name is not valid for current instance: " + name);
        }
        return resourcePath.popFirst(5);
    }

    private SnapshotVersion decodeSnapshotVersion(Object timestamp) throws JSONException {
        return new SnapshotVersion(decodeTimestamp(timestamp));
    }

    private BundledQuery decodeBundledQuery(JSONObject bundledQuery) throws JSONException {
        JSONObject structuredQuery = bundledQuery.getJSONObject("structuredQuery");
        verifyNoSelect(structuredQuery);
        ResourcePath parent = decodeName(bundledQuery.getString("parent"));
        JSONArray from = structuredQuery.getJSONArray("from");
        verifyCollectionSelector(from);
        JSONObject collectionSelector = from.getJSONObject(0);
        boolean allDescendants = collectionSelector.optBoolean("allDescendants", false);
        String collectionGroup = null;
        if (allDescendants) {
            collectionGroup = collectionSelector.getString("collectionId");
        } else {
            parent = parent.append(collectionSelector.getString("collectionId"));
        }
        List<Filter> filters = decodeWhere(structuredQuery.optJSONObject("where"));
        List<OrderBy> orderBys = decodeOrderBy(structuredQuery.optJSONArray("orderBy"));
        Bound startAt = decodeStartAtBound(structuredQuery.optJSONObject("startAt"));
        Bound endAt = decodeEndAtBound(structuredQuery.optJSONObject("endAt"));
        verifyNoOffset(structuredQuery);
        int limit = decodeLimit(structuredQuery);
        Query.LimitType limitType = decodeLimitType(bundledQuery);
        return new BundledQuery(new Query(parent, collectionGroup, filters, orderBys, limit, Query.LimitType.LIMIT_TO_FIRST, startAt, endAt).toTarget(), limitType);
    }

    private int decodeLimit(JSONObject structuredQuery) {
        JSONObject limit = structuredQuery.optJSONObject("limit");
        if (limit != null) {
            return limit.optInt("value", -1);
        }
        return structuredQuery.optInt("limit", -1);
    }

    private Bound decodeStartAtBound(JSONObject bound) throws JSONException {
        if (bound != null) {
            boolean before = bound.optBoolean("before", false);
            List<Value> position = decodePosition(bound);
            return new Bound(position, before);
        }
        return null;
    }

    private Bound decodeEndAtBound(JSONObject bound) throws JSONException {
        if (bound != null) {
            boolean before = bound.optBoolean("before", false);
            List<Value> position = decodePosition(bound);
            return new Bound(position, !before);
        }
        return null;
    }

    private List<Value> decodePosition(JSONObject bound) throws JSONException {
        List<Value> cursor = new ArrayList<>();
        JSONArray values = bound.optJSONArray("values");
        if (values != null) {
            for (int i = 0; i < values.length(); i++) {
                cursor.add(decodeValue(values.getJSONObject(i)));
            }
        }
        return cursor;
    }

    private List<OrderBy> decodeOrderBy(JSONArray orderBys) throws JSONException {
        OrderBy.Direction direction;
        List<OrderBy> result = new ArrayList<>();
        if (orderBys != null) {
            for (int i = 0; i < orderBys.length(); i++) {
                JSONObject orderBy = orderBys.getJSONObject(i);
                FieldPath fieldPath = decodeFieldReference(orderBy.getJSONObject("field"));
                String directionString = orderBy.optString("direction", "ASCENDING");
                if (directionString.equals("ASCENDING")) {
                    direction = OrderBy.Direction.ASCENDING;
                } else {
                    direction = OrderBy.Direction.DESCENDING;
                }
                result.add(OrderBy.getInstance(direction, fieldPath));
            }
        }
        return result;
    }

    private List<Filter> decodeWhere(JSONObject where) throws JSONException {
        List<Filter> result = new ArrayList<>();
        if (where != null) {
            decodeFilter(result, where);
        }
        return result;
    }

    private void decodeFilter(List<Filter> result, JSONObject structuredQuery) throws JSONException {
        if (structuredQuery.has("compositeFilter")) {
            decodeCompositeFilter(result, structuredQuery.getJSONObject("compositeFilter"));
        } else if (structuredQuery.has("fieldFilter")) {
            decodeFieldFilter(result, structuredQuery.getJSONObject("fieldFilter"));
        } else if (structuredQuery.has("unaryFilter")) {
            decodeUnaryFilter(result, structuredQuery.getJSONObject("unaryFilter"));
        }
    }

    private void decodeCompositeFilter(List<Filter> result, JSONObject compositeFilter) throws JSONException {
        if (!compositeFilter.getString("op").equals("AND")) {
            throw new IllegalArgumentException("The Android SDK only supports composite filters of type 'AND'");
        }
        JSONArray filters = compositeFilter.optJSONArray("filters");
        if (filters != null) {
            for (int i = 0; i < filters.length(); i++) {
                decodeFilter(result, filters.getJSONObject(i));
            }
        }
    }

    private void decodeFieldFilter(List<Filter> result, JSONObject fieldFilter) throws JSONException {
        FieldPath fieldPath = decodeFieldReference(fieldFilter.getJSONObject("field"));
        FieldFilter.Operator filterOperator = decodeFieldFilterOperator(fieldFilter.getString("op"));
        result.add(FieldFilter.create(fieldPath, filterOperator, decodeValue(fieldFilter.getJSONObject("value"))));
    }

    private Value decodeValue(JSONObject value) throws JSONException {
        Value.Builder builder = Value.newBuilder();
        if (value.has("nullValue")) {
            builder.setNullValue(NullValue.NULL_VALUE);
        } else if (value.has("booleanValue")) {
            builder.setBooleanValue(value.optBoolean("booleanValue", false));
        } else if (value.has("integerValue")) {
            builder.setIntegerValue(value.optLong("integerValue"));
        } else if (value.has("doubleValue")) {
            builder.setDoubleValue(value.optDouble("doubleValue"));
        } else if (value.has("timestampValue")) {
            decodeTimestamp(builder, value.get("timestampValue"));
        } else if (value.has("stringValue")) {
            builder.setStringValue(value.optString("stringValue", ""));
        } else if (value.has("bytesValue")) {
            builder.setBytesValue(ByteString.copyFrom(Base64.decode(value.getString("bytesValue"), 0)));
        } else if (value.has("referenceValue")) {
            builder.setReferenceValue(value.getString("referenceValue"));
        } else if (value.has("geoPointValue")) {
            decodeGeoPoint(builder, value.getJSONObject("geoPointValue"));
        } else if (value.has("arrayValue")) {
            decodeArrayValue(builder, value.getJSONObject("arrayValue").optJSONArray("values"));
        } else if (value.has("mapValue")) {
            decodeMapValue(builder, value.getJSONObject("mapValue").optJSONObject("fields"));
        } else {
            throw new IllegalArgumentException("Unexpected value type: " + value);
        }
        return builder.build();
    }

    private void decodeArrayValue(Value.Builder builder, JSONArray values) throws JSONException {
        ArrayValue.Builder arrayBuilder = ArrayValue.newBuilder();
        if (values != null) {
            for (int i = 0; i < values.length(); i++) {
                arrayBuilder.addValues(decodeValue(values.getJSONObject(i)));
            }
        }
        builder.setArrayValue(arrayBuilder);
    }

    private void decodeMapValue(Value.Builder builder, JSONObject map) throws JSONException {
        MapValue.Builder mapBuilder = MapValue.newBuilder();
        if (map != null) {
            Iterator<String> it = map.keys();
            while (it.hasNext()) {
                String key = it.next();
                mapBuilder.putFields(key, decodeValue(map.getJSONObject(key)));
            }
        }
        builder.setMapValue(mapBuilder);
    }

    private void decodeGeoPoint(Value.Builder builder, JSONObject geoPoint) {
        builder.setGeoPointValue(LatLng.newBuilder().setLatitude(geoPoint.optDouble("latitude")).setLongitude(geoPoint.optDouble("longitude")));
    }

    private Timestamp decodeTimestamp(JSONObject timestamp) {
        return new Timestamp(timestamp.optLong("seconds"), timestamp.optInt("nanos"));
    }

    private Timestamp decodeTimestamp(String timestamp) {
        try {
            int dayOffset = timestamp.indexOf(84);
            try {
                if (dayOffset != -1) {
                    int timezoneOffsetPosition = timestamp.indexOf(90, dayOffset);
                    if (timezoneOffsetPosition == -1) {
                        timezoneOffsetPosition = timestamp.indexOf(43, dayOffset);
                    }
                    if (timezoneOffsetPosition == -1) {
                        timezoneOffsetPosition = timestamp.indexOf(45, dayOffset);
                    }
                    if (timezoneOffsetPosition == -1) {
                        throw new IllegalArgumentException("Invalid timestamp: Missing valid timezone offset: " + timestamp);
                    }
                    int nanos = 0;
                    String timeValue = timestamp.substring(0, timezoneOffsetPosition);
                    String secondValue = timeValue;
                    String nanoValue = "";
                    int pointPosition = timeValue.indexOf(46);
                    if (pointPosition != -1) {
                        secondValue = timeValue.substring(0, pointPosition);
                        nanoValue = timeValue.substring(pointPosition + 1);
                    }
                    Date date = this.timestampFormat.parse(secondValue);
                    long seconds = date.getTime() / 1000;
                    if (!nanoValue.isEmpty()) {
                        nanos = parseNanos(nanoValue);
                    }
                    if (timestamp.charAt(timezoneOffsetPosition) != 'Z') {
                        String offsetValue = timestamp.substring(timezoneOffsetPosition + 1);
                        long offset = decodeTimezoneOffset(offsetValue);
                        if (timestamp.charAt(timezoneOffsetPosition) == '+') {
                            seconds -= offset;
                        } else {
                            seconds += offset;
                        }
                    } else if (timestamp.length() != timezoneOffsetPosition + 1) {
                        throw new IllegalArgumentException("Invalid timestamp: Invalid trailing data \"" + timestamp.substring(timezoneOffsetPosition) + "\"");
                    }
                    return new Timestamp(seconds, nanos);
                }
                throw new IllegalArgumentException("Invalid timestamp: " + timestamp);
            } catch (ParseException e) {
                e = e;
            }
        } catch (ParseException e2) {
            e = e2;
        }
        throw new IllegalArgumentException("Failed to parse timestamp", e);
    }

    private Timestamp decodeTimestamp(Object timestamp) throws JSONException {
        if (timestamp instanceof String) {
            return decodeTimestamp((String) timestamp);
        }
        if (!(timestamp instanceof JSONObject)) {
            throw new IllegalArgumentException("Timestamps must be either ISO 8601-formatted strings or JSON objects");
        }
        return decodeTimestamp((JSONObject) timestamp);
    }

    private void decodeTimestamp(Value.Builder builder, Object timestamp) throws JSONException {
        Timestamp decoded = decodeTimestamp(timestamp);
        builder.setTimestampValue(com.google.protobuf.Timestamp.newBuilder().setSeconds(decoded.getSeconds()).setNanos(decoded.getNanoseconds()));
    }

    private static int parseNanos(String value) {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            result *= 10;
            if (i < value.length()) {
                if (value.charAt(i) < '0' || value.charAt(i) > '9') {
                    throw new IllegalArgumentException("Invalid nanoseconds: " + value);
                }
                result += value.charAt(i) - '0';
            }
        }
        return result;
    }

    private static long decodeTimezoneOffset(String value) {
        int pos = value.indexOf(58);
        if (pos == -1) {
            throw new IllegalArgumentException("Invalid offset value: " + value);
        }
        String hours = value.substring(0, pos);
        String minutes = value.substring(pos + 1);
        return ((Long.parseLong(hours) * 60) + Long.parseLong(minutes)) * 60;
    }

    private FieldFilter.Operator decodeFieldFilterOperator(String operator) {
        return FieldFilter.Operator.valueOf(operator);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void decodeUnaryFilter(java.util.List<com.google.firebase.firestore.core.Filter> r6, org.json.JSONObject r7) throws org.json.JSONException {
        /*
            r5 = this;
            java.lang.String r0 = "field"
            org.json.JSONObject r0 = r7.getJSONObject(r0)
            com.google.firebase.firestore.model.FieldPath r0 = r5.decodeFieldReference(r0)
            java.lang.String r1 = "op"
            java.lang.String r1 = r7.getString(r1)
            int r2 = r1.hashCode()
            switch(r2) {
                case -2125479834: goto L36;
                case -1465346180: goto L2c;
                case -244195494: goto L22;
                case 1019893512: goto L18;
                default: goto L17;
            }
        L17:
            goto L40
        L18:
            java.lang.String r2 = "IS_NOT_NULL"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L17
            r2 = 3
            goto L41
        L22:
            java.lang.String r2 = "IS_NOT_NAN"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L17
            r2 = 2
            goto L41
        L2c:
            java.lang.String r2 = "IS_NULL"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L17
            r2 = 1
            goto L41
        L36:
            java.lang.String r2 = "IS_NAN"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L17
            r2 = 0
            goto L41
        L40:
            r2 = -1
        L41:
            switch(r2) {
                case 0: goto L81;
                case 1: goto L75;
                case 2: goto L69;
                case 3: goto L5d;
                default: goto L44;
            }
        L44:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unexpected unary filter: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L5d:
            com.google.firebase.firestore.core.FieldFilter$Operator r2 = com.google.firebase.firestore.core.FieldFilter.Operator.NOT_EQUAL
            com.google.firestore.v1.Value r3 = com.google.firebase.firestore.model.Values.NULL_VALUE
            com.google.firebase.firestore.core.FieldFilter r2 = com.google.firebase.firestore.core.FieldFilter.create(r0, r2, r3)
            r6.add(r2)
            goto L8d
        L69:
            com.google.firebase.firestore.core.FieldFilter$Operator r2 = com.google.firebase.firestore.core.FieldFilter.Operator.NOT_EQUAL
            com.google.firestore.v1.Value r3 = com.google.firebase.firestore.model.Values.NAN_VALUE
            com.google.firebase.firestore.core.FieldFilter r2 = com.google.firebase.firestore.core.FieldFilter.create(r0, r2, r3)
            r6.add(r2)
            goto L8d
        L75:
            com.google.firebase.firestore.core.FieldFilter$Operator r2 = com.google.firebase.firestore.core.FieldFilter.Operator.EQUAL
            com.google.firestore.v1.Value r3 = com.google.firebase.firestore.model.Values.NULL_VALUE
            com.google.firebase.firestore.core.FieldFilter r2 = com.google.firebase.firestore.core.FieldFilter.create(r0, r2, r3)
            r6.add(r2)
            goto L8d
        L81:
            com.google.firebase.firestore.core.FieldFilter$Operator r2 = com.google.firebase.firestore.core.FieldFilter.Operator.EQUAL
            com.google.firestore.v1.Value r3 = com.google.firebase.firestore.model.Values.NAN_VALUE
            com.google.firebase.firestore.core.FieldFilter r2 = com.google.firebase.firestore.core.FieldFilter.create(r0, r2, r3)
            r6.add(r2)
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.bundle.BundleSerializer.decodeUnaryFilter(java.util.List, org.json.JSONObject):void");
    }

    private FieldPath decodeFieldReference(JSONObject fieldReference) throws JSONException {
        return FieldPath.fromServerFormat(fieldReference.getString("fieldPath"));
    }

    private Query.LimitType decodeLimitType(JSONObject bundledQuery) {
        String limitType = bundledQuery.optString("limitType", "FIRST");
        if (limitType.equals("FIRST")) {
            return Query.LimitType.LIMIT_TO_FIRST;
        }
        if (limitType.equals("LAST")) {
            return Query.LimitType.LIMIT_TO_LAST;
        }
        throw new IllegalArgumentException("Invalid limit type for bundle query: " + limitType);
    }

    private void verifyCollectionSelector(JSONArray from) {
        if (from.length() != 1) {
            throw new IllegalArgumentException("Only queries with a single 'from' clause are supported by the Android SDK");
        }
    }

    private void verifyNoOffset(JSONObject structuredQuery) {
        if (structuredQuery.has(TypedValues.CycleType.S_WAVE_OFFSET)) {
            throw new IllegalArgumentException("Queries with offsets are not supported by the Android SDK");
        }
    }

    private void verifyNoSelect(JSONObject structuredQuery) {
        if (structuredQuery.has("select")) {
            throw new IllegalArgumentException("Queries with 'select' statements are not supported by the Android SDK");
        }
    }
}
