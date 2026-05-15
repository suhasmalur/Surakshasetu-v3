package com.google.firebase.firestore.core;

import android.text.TextUtils;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.util.Function;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public class CompositeFilter extends Filter {
    private final List<Filter> filters;
    private List<FieldFilter> memoizedFlattenedFilters;
    private final Operator operator;

    public enum Operator {
        AND("and"),
        OR("or");

        private final String text;

        Operator(String text) {
            this.text = text;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    public CompositeFilter(List<Filter> filters, Operator operator) {
        this.filters = new ArrayList(filters);
        this.operator = operator;
    }

    @Override // com.google.firebase.firestore.core.Filter
    public List<Filter> getFilters() {
        return Collections.unmodifiableList(this.filters);
    }

    public Operator getOperator() {
        return this.operator;
    }

    @Override // com.google.firebase.firestore.core.Filter
    public List<FieldFilter> getFlattenedFilters() {
        if (this.memoizedFlattenedFilters != null) {
            return Collections.unmodifiableList(this.memoizedFlattenedFilters);
        }
        this.memoizedFlattenedFilters = new ArrayList();
        for (Filter subfilter : this.filters) {
            this.memoizedFlattenedFilters.addAll(subfilter.getFlattenedFilters());
        }
        return Collections.unmodifiableList(this.memoizedFlattenedFilters);
    }

    public boolean isConjunction() {
        return this.operator == Operator.AND;
    }

    public boolean isDisjunction() {
        return this.operator == Operator.OR;
    }

    public boolean isFlatConjunction() {
        return isFlat() && isConjunction();
    }

    public boolean isFlat() {
        for (Filter filter : this.filters) {
            if (filter instanceof CompositeFilter) {
                return false;
            }
        }
        return true;
    }

    public CompositeFilter withAddedFilters(List<Filter> otherFilters) {
        List<Filter> mergedFilters = new ArrayList<>(this.filters);
        mergedFilters.addAll(otherFilters);
        return new CompositeFilter(mergedFilters, this.operator);
    }

    private FieldFilter findFirstMatchingFilter(Function<FieldFilter, Boolean> condition) {
        for (FieldFilter filter : getFlattenedFilters()) {
            if (condition.apply(filter).booleanValue()) {
                return filter;
            }
        }
        return null;
    }

    @Override // com.google.firebase.firestore.core.Filter
    public boolean matches(Document doc) {
        if (isConjunction()) {
            for (Filter filter : this.filters) {
                if (!filter.matches(doc)) {
                    return false;
                }
            }
            return true;
        }
        for (Filter filter2 : this.filters) {
            if (filter2.matches(doc)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.firebase.firestore.core.Filter
    public String getCanonicalId() {
        StringBuilder builder = new StringBuilder();
        if (isFlatConjunction()) {
            for (Filter filter : this.filters) {
                builder.append(filter.getCanonicalId());
            }
            return builder.toString();
        }
        builder.append(this.operator.toString() + "(");
        builder.append(TextUtils.join(",", this.filters));
        builder.append(")");
        return builder.toString();
    }

    public String toString() {
        return getCanonicalId();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof CompositeFilter)) {
            return false;
        }
        CompositeFilter other = (CompositeFilter) o;
        return this.operator == other.operator && this.filters.equals(other.filters);
    }

    public int hashCode() {
        int result = (37 * 31) + this.operator.hashCode();
        return (result * 31) + this.filters.hashCode();
    }
}
