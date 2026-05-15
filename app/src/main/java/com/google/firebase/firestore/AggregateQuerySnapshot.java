package com.google.firebase.firestore;

import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.Value;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes10.dex */
public class AggregateQuerySnapshot {

    @Nonnull
    private final Map<String, Value> data;

    @Nonnull
    private final AggregateQuery query;

    AggregateQuerySnapshot(AggregateQuery query, Map<String, Value> data) {
        Preconditions.checkNotNull(query);
        this.query = query;
        this.data = data;
    }

    static AggregateQuerySnapshot createWithCount(AggregateQuery query, long count) {
        return new AggregateQuerySnapshot(query, Collections.singletonMap(AggregateField.count().getAlias(), Value.newBuilder().setIntegerValue(count).build()));
    }

    public AggregateQuery getQuery() {
        return this.query;
    }

    public long getCount() {
        return get(AggregateField.count());
    }

    public Object get(@Nonnull AggregateField aggregateField) {
        return getInternal(aggregateField);
    }

    public long get(@Nonnull AggregateField.CountAggregateField countAggregateField) {
        Long value = getLong(countAggregateField);
        if (value == null) {
            throw new IllegalArgumentException("RunAggregationQueryResponse alias " + countAggregateField.getAlias() + " is null");
        }
        return value.longValue();
    }

    public Double get(@Nonnull AggregateField.AverageAggregateField averageAggregateField) {
        return getDouble(averageAggregateField);
    }

    public Double getDouble(@Nonnull AggregateField aggregateField) {
        Number val = (Number) getTypedValue(aggregateField, Number.class);
        if (val != null) {
            return Double.valueOf(val.doubleValue());
        }
        return null;
    }

    public Long getLong(@Nonnull AggregateField aggregateField) {
        Number val = (Number) getTypedValue(aggregateField, Number.class);
        if (val != null) {
            return Long.valueOf(val.longValue());
        }
        return null;
    }

    private Object getInternal(@Nonnull AggregateField aggregateField) {
        if (!this.data.containsKey(aggregateField.getAlias())) {
            throw new IllegalArgumentException("'" + aggregateField.getOperator() + "(" + aggregateField.getFieldPath() + ")' was not requested in the aggregation query.");
        }
        Value value = this.data.get(aggregateField.getAlias());
        UserDataWriter userDataWriter = new UserDataWriter(this.query.getQuery().firestore, DocumentSnapshot.ServerTimestampBehavior.DEFAULT);
        return userDataWriter.convertValue(value);
    }

    private <T> T getTypedValue(@Nonnull AggregateField aggregateField, Class<T> cls) {
        return (T) castTypedValue(getInternal(aggregateField), aggregateField, cls);
    }

    private <T> T castTypedValue(Object value, @Nonnull AggregateField aggregateField, Class<T> clazz) {
        if (value == null) {
            return null;
        }
        if (!clazz.isInstance(value)) {
            throw new RuntimeException("AggregateField '" + aggregateField.getAlias() + "' is not a " + clazz.getName());
        }
        return clazz.cast(value);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AggregateQuerySnapshot)) {
            return false;
        }
        AggregateQuerySnapshot other = (AggregateQuerySnapshot) object;
        return this.query.equals(other.query) && this.data.equals(other.data);
    }

    public int hashCode() {
        return Objects.hash(this.query, this.data);
    }
}
