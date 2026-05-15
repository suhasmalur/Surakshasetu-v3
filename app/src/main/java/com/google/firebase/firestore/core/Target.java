package com.google.firebase.firestore.core;

import android.util.Pair;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes10.dex */
public final class Target {
    public static final long NO_LIMIT = -1;
    private final String collectionGroup;
    private final Bound endAt;
    private final List<Filter> filters;
    private final long limit;
    private String memoizedCanonicalId;
    private final List<OrderBy> orderBys;
    private final ResourcePath path;
    private final Bound startAt;

    public Target(ResourcePath path, String collectionGroup, List<Filter> filters, List<OrderBy> orderBys, long limit, Bound startAt, Bound endAt) {
        this.path = path;
        this.collectionGroup = collectionGroup;
        this.orderBys = orderBys;
        this.filters = filters;
        this.limit = limit;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public ResourcePath getPath() {
        return this.path;
    }

    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    public boolean isDocumentQuery() {
        return DocumentKey.isDocumentKey(this.path) && this.collectionGroup == null && this.filters.isEmpty();
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public long getLimit() {
        return this.limit;
    }

    public boolean hasLimit() {
        return this.limit != -1;
    }

    public Bound getStartAt() {
        return this.startAt;
    }

    public Bound getEndAt() {
        return this.endAt;
    }

    private List<FieldFilter> getFieldFiltersForPath(FieldPath path) {
        List<FieldFilter> result = new ArrayList<>();
        for (Filter filter : this.filters) {
            if ((filter instanceof FieldFilter) && ((FieldFilter) filter).getField().equals(path)) {
                result.add((FieldFilter) filter);
            }
        }
        return result;
    }

    public List<Value> getArrayValues(FieldIndex fieldIndex) {
        FieldIndex.Segment segment = fieldIndex.getArraySegment();
        if (segment == null) {
            return null;
        }
        for (FieldFilter fieldFilter : getFieldFiltersForPath(segment.getFieldPath())) {
            switch (fieldFilter.getOperator()) {
                case ARRAY_CONTAINS_ANY:
                    return fieldFilter.getValue().getArrayValue().getValuesList();
                case ARRAY_CONTAINS:
                    return Collections.singletonList(fieldFilter.getValue());
            }
        }
        return null;
    }

    public Collection<Value> getNotInValues(FieldIndex fieldIndex) {
        LinkedHashMap<FieldPath, Value> values = new LinkedHashMap<>();
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            for (FieldFilter fieldFilter : getFieldFiltersForPath(segment.getFieldPath())) {
                switch (fieldFilter.getOperator()) {
                    case EQUAL:
                    case IN:
                        values.put(segment.getFieldPath(), fieldFilter.getValue());
                        break;
                    case NOT_IN:
                    case NOT_EQUAL:
                        values.put(segment.getFieldPath(), fieldFilter.getValue());
                        return values.values();
                }
            }
        }
        return null;
    }

    public Bound getLowerBound(FieldIndex fieldIndex) {
        Pair<Value, Boolean> segmentBound;
        List<Value> values = new ArrayList<>();
        boolean inclusive = true;
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            if (segment.getKind().equals(FieldIndex.Segment.Kind.ASCENDING)) {
                segmentBound = getAscendingBound(segment, this.startAt);
            } else {
                segmentBound = getDescendingBound(segment, this.startAt);
            }
            values.add((Value) segmentBound.first);
            inclusive &= ((Boolean) segmentBound.second).booleanValue();
        }
        return new Bound(values, inclusive);
    }

    public Bound getUpperBound(FieldIndex fieldIndex) {
        Pair<Value, Boolean> segmentBound;
        List<Value> values = new ArrayList<>();
        boolean inclusive = true;
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            if (segment.getKind().equals(FieldIndex.Segment.Kind.ASCENDING)) {
                segmentBound = getDescendingBound(segment, this.endAt);
            } else {
                segmentBound = getAscendingBound(segment, this.endAt);
            }
            values.add((Value) segmentBound.first);
            inclusive &= ((Boolean) segmentBound.second).booleanValue();
        }
        return new Bound(values, inclusive);
    }

    private Pair<Value, Boolean> getAscendingBound(FieldIndex.Segment segment, Bound bound) {
        Value segmentValue = Values.MIN_VALUE;
        boolean segmentInclusive = true;
        for (FieldFilter fieldFilter : getFieldFiltersForPath(segment.getFieldPath())) {
            Value filterValue = Values.MIN_VALUE;
            boolean filterInclusive = true;
            switch (fieldFilter.getOperator()) {
                case EQUAL:
                case IN:
                case GREATER_THAN_OR_EQUAL:
                    filterValue = fieldFilter.getValue();
                    break;
                case NOT_IN:
                case NOT_EQUAL:
                    filterValue = Values.MIN_VALUE;
                    break;
                case LESS_THAN:
                case LESS_THAN_OR_EQUAL:
                    filterValue = Values.getLowerBound(fieldFilter.getValue().getValueTypeCase());
                    break;
                case GREATER_THAN:
                    filterValue = fieldFilter.getValue();
                    filterInclusive = false;
                    break;
            }
            if (Values.lowerBoundCompare(segmentValue, segmentInclusive, filterValue, filterInclusive) < 0) {
                segmentValue = filterValue;
                segmentInclusive = filterInclusive;
            }
        }
        if (bound != null) {
            int i = 0;
            while (true) {
                if (i < this.orderBys.size()) {
                    OrderBy orderBy = this.orderBys.get(i);
                    if (!orderBy.getField().equals(segment.getFieldPath())) {
                        i++;
                    } else {
                        Value cursorValue = bound.getPosition().get(i);
                        if (Values.lowerBoundCompare(segmentValue, segmentInclusive, cursorValue, bound.isInclusive()) < 0) {
                            segmentValue = cursorValue;
                            segmentInclusive = bound.isInclusive();
                        }
                    }
                }
            }
        }
        return new Pair<>(segmentValue, Boolean.valueOf(segmentInclusive));
    }

    private Pair<Value, Boolean> getDescendingBound(FieldIndex.Segment segment, Bound bound) {
        Value segmentValue = Values.MAX_VALUE;
        boolean segmentInclusive = true;
        for (FieldFilter fieldFilter : getFieldFiltersForPath(segment.getFieldPath())) {
            Value filterValue = Values.MAX_VALUE;
            boolean filterInclusive = true;
            switch (fieldFilter.getOperator()) {
                case EQUAL:
                case IN:
                case LESS_THAN_OR_EQUAL:
                    filterValue = fieldFilter.getValue();
                    break;
                case NOT_IN:
                case NOT_EQUAL:
                    filterValue = Values.MAX_VALUE;
                    break;
                case LESS_THAN:
                    filterValue = fieldFilter.getValue();
                    filterInclusive = false;
                    break;
                case GREATER_THAN_OR_EQUAL:
                case GREATER_THAN:
                    filterValue = Values.getUpperBound(fieldFilter.getValue().getValueTypeCase());
                    filterInclusive = false;
                    break;
            }
            if (Values.upperBoundCompare(segmentValue, segmentInclusive, filterValue, filterInclusive) > 0) {
                segmentValue = filterValue;
                segmentInclusive = filterInclusive;
            }
        }
        if (bound != null) {
            int i = 0;
            while (true) {
                if (i < this.orderBys.size()) {
                    OrderBy orderBy = this.orderBys.get(i);
                    if (!orderBy.getField().equals(segment.getFieldPath())) {
                        i++;
                    } else {
                        Value cursorValue = bound.getPosition().get(i);
                        if (Values.upperBoundCompare(segmentValue, segmentInclusive, cursorValue, bound.isInclusive()) > 0) {
                            segmentValue = cursorValue;
                            segmentInclusive = bound.isInclusive();
                        }
                    }
                }
            }
        }
        return new Pair<>(segmentValue, Boolean.valueOf(segmentInclusive));
    }

    public List<OrderBy> getOrderBy() {
        return this.orderBys;
    }

    public OrderBy.Direction getKeyOrder() {
        return this.orderBys.get(this.orderBys.size() - 1).getDirection();
    }

    public int getSegmentCount() {
        Set<FieldPath> fields = new HashSet<>();
        int i = 0;
        for (Filter filter : this.filters) {
            for (FieldFilter subFilter : filter.getFlattenedFilters()) {
                if (!subFilter.getField().isKeyField()) {
                    if (subFilter.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS) || subFilter.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS_ANY)) {
                        i = 1;
                    } else {
                        fields.add(subFilter.getField());
                    }
                }
            }
        }
        for (OrderBy orderBy : this.orderBys) {
            if (!orderBy.getField().isKeyField()) {
                fields.add(orderBy.getField());
            }
        }
        return fields.size() + i;
    }

    public String getCanonicalId() {
        if (this.memoizedCanonicalId != null) {
            return this.memoizedCanonicalId;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(getPath().canonicalString());
        if (this.collectionGroup != null) {
            builder.append("|cg:");
            builder.append(this.collectionGroup);
        }
        builder.append("|f:");
        for (Filter filter : getFilters()) {
            builder.append(filter.getCanonicalId());
        }
        builder.append("|ob:");
        for (OrderBy orderBy : getOrderBy()) {
            builder.append(orderBy.getField().canonicalString());
            builder.append(orderBy.getDirection().equals(OrderBy.Direction.ASCENDING) ? "asc" : "desc");
        }
        if (hasLimit()) {
            builder.append("|l:");
            builder.append(getLimit());
        }
        if (this.startAt != null) {
            builder.append("|lb:");
            builder.append(this.startAt.isInclusive() ? "b:" : "a:");
            builder.append(this.startAt.positionString());
        }
        if (this.endAt != null) {
            builder.append("|ub:");
            builder.append(this.endAt.isInclusive() ? "a:" : "b:");
            builder.append(this.endAt.positionString());
        }
        this.memoizedCanonicalId = builder.toString();
        return this.memoizedCanonicalId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Target target = (Target) o;
        if (this.collectionGroup == null ? target.collectionGroup != null : !this.collectionGroup.equals(target.collectionGroup)) {
            return false;
        }
        if (this.limit != target.limit || !this.orderBys.equals(target.orderBys) || !this.filters.equals(target.filters) || !this.path.equals(target.path)) {
            return false;
        }
        if (this.startAt == null ? target.startAt != null : !this.startAt.equals(target.startAt)) {
            return false;
        }
        if (this.endAt != null) {
            return this.endAt.equals(target.endAt);
        }
        if (target.endAt == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = this.orderBys.hashCode();
        return (((((((((((result * 31) + (this.collectionGroup != null ? this.collectionGroup.hashCode() : 0)) * 31) + this.filters.hashCode()) * 31) + this.path.hashCode()) * 31) + ((int) (this.limit ^ (this.limit >>> 32)))) * 31) + (this.startAt != null ? this.startAt.hashCode() : 0)) * 31) + (this.endAt != null ? this.endAt.hashCode() : 0);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Query(");
        builder.append(this.path.canonicalString());
        if (this.collectionGroup != null) {
            builder.append(" collectionGroup=");
            builder.append(this.collectionGroup);
        }
        if (!this.filters.isEmpty()) {
            builder.append(" where ");
            for (int i = 0; i < this.filters.size(); i++) {
                if (i > 0) {
                    builder.append(" and ");
                }
                builder.append(this.filters.get(i));
            }
        }
        if (!this.orderBys.isEmpty()) {
            builder.append(" order by ");
            for (int i2 = 0; i2 < this.orderBys.size(); i2++) {
                if (i2 > 0) {
                    builder.append(", ");
                }
                builder.append(this.orderBys.get(i2));
            }
        }
        builder.append(")");
        return builder.toString();
    }
}
