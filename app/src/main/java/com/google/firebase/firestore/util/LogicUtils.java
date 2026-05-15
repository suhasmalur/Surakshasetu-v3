package com.google.firebase.firestore.util;

import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.InFilter;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public class LogicUtils {
    private static void assertFieldFilterOrCompositeFilter(Filter filter) {
        Assert.hardAssert((filter instanceof FieldFilter) || (filter instanceof CompositeFilter), "Only field filters and composite filters are accepted.", new Object[0]);
    }

    private static boolean isSingleFieldFilter(Filter filter) {
        return filter instanceof FieldFilter;
    }

    private static boolean isFlatConjunction(Filter filter) {
        return (filter instanceof CompositeFilter) && ((CompositeFilter) filter).isFlatConjunction();
    }

    private static boolean isDisjunctionOfFieldFiltersAndFlatConjunctions(Filter filter) {
        if (filter instanceof CompositeFilter) {
            CompositeFilter compositeFilter = (CompositeFilter) filter;
            if (compositeFilter.isDisjunction()) {
                for (Filter subfilter : compositeFilter.getFilters()) {
                    if (!isSingleFieldFilter(subfilter) && !isFlatConjunction(subfilter)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static boolean isDisjunctiveNormalForm(Filter filter) {
        return isSingleFieldFilter(filter) || isFlatConjunction(filter) || isDisjunctionOfFieldFiltersAndFlatConjunctions(filter);
    }

    protected static Filter applyAssociation(Filter filter) {
        assertFieldFilterOrCompositeFilter(filter);
        if (isSingleFieldFilter(filter)) {
            return filter;
        }
        CompositeFilter compositeFilter = (CompositeFilter) filter;
        List<Filter> filters = compositeFilter.getFilters();
        if (filters.size() == 1) {
            return applyAssociation(filters.get(0));
        }
        if (compositeFilter.isFlat()) {
            return compositeFilter;
        }
        List<Filter> updatedFilters = new ArrayList<>();
        Iterator<Filter> it = filters.iterator();
        while (it.hasNext()) {
            updatedFilters.add(applyAssociation(it.next()));
        }
        List<Filter> newSubfilters = new ArrayList<>();
        for (Filter subfilter : updatedFilters) {
            if (subfilter instanceof FieldFilter) {
                newSubfilters.add(subfilter);
            } else if (subfilter instanceof CompositeFilter) {
                CompositeFilter compositeSubfilter = (CompositeFilter) subfilter;
                if (compositeSubfilter.getOperator().equals(compositeFilter.getOperator())) {
                    newSubfilters.addAll(compositeSubfilter.getFilters());
                } else {
                    newSubfilters.add(compositeSubfilter);
                }
            }
        }
        if (newSubfilters.size() == 1) {
            return newSubfilters.get(0);
        }
        return new CompositeFilter(newSubfilters, compositeFilter.getOperator());
    }

    protected static Filter applyDistribution(Filter lhs, Filter rhs) {
        Filter result;
        assertFieldFilterOrCompositeFilter(lhs);
        assertFieldFilterOrCompositeFilter(rhs);
        if ((lhs instanceof FieldFilter) && (rhs instanceof FieldFilter)) {
            result = applyDistribution((FieldFilter) lhs, (FieldFilter) rhs);
        } else if ((lhs instanceof FieldFilter) && (rhs instanceof CompositeFilter)) {
            result = applyDistribution((FieldFilter) lhs, (CompositeFilter) rhs);
        } else if ((lhs instanceof CompositeFilter) && (rhs instanceof FieldFilter)) {
            result = applyDistribution((FieldFilter) rhs, (CompositeFilter) lhs);
        } else {
            result = applyDistribution((CompositeFilter) lhs, (CompositeFilter) rhs);
        }
        return applyAssociation(result);
    }

    private static Filter applyDistribution(FieldFilter lhs, FieldFilter rhs) {
        return new CompositeFilter(Arrays.asList(lhs, rhs), CompositeFilter.Operator.AND);
    }

    private static Filter applyDistribution(FieldFilter fieldFilter, CompositeFilter compositeFilter) {
        if (compositeFilter.isConjunction()) {
            return compositeFilter.withAddedFilters(Collections.singletonList(fieldFilter));
        }
        List<Filter> newFilters = new ArrayList<>();
        for (Filter subfilter : compositeFilter.getFilters()) {
            newFilters.add(applyDistribution(fieldFilter, subfilter));
        }
        return new CompositeFilter(newFilters, CompositeFilter.Operator.OR);
    }

    private static Filter applyDistribution(CompositeFilter lhs, CompositeFilter rhs) {
        Assert.hardAssert((lhs.getFilters().isEmpty() || rhs.getFilters().isEmpty()) ? false : true, "Found an empty composite filter", new Object[0]);
        if (lhs.isConjunction() && rhs.isConjunction()) {
            return lhs.withAddedFilters(rhs.getFilters());
        }
        CompositeFilter disjunctionSide = lhs.isDisjunction() ? lhs : rhs;
        CompositeFilter otherSide = lhs.isDisjunction() ? rhs : lhs;
        List<Filter> results = new ArrayList<>();
        for (Filter subfilter : disjunctionSide.getFilters()) {
            results.add(applyDistribution(subfilter, otherSide));
        }
        return new CompositeFilter(results, CompositeFilter.Operator.OR);
    }

    protected static Filter computeDistributedNormalForm(Filter filter) {
        assertFieldFilterOrCompositeFilter(filter);
        if (filter instanceof FieldFilter) {
            return filter;
        }
        CompositeFilter compositeFilter = (CompositeFilter) filter;
        if (compositeFilter.getFilters().size() == 1) {
            return computeDistributedNormalForm(filter.getFilters().get(0));
        }
        List<Filter> result = new ArrayList<>();
        for (Filter subfilter : compositeFilter.getFilters()) {
            result.add(computeDistributedNormalForm(subfilter));
        }
        Filter newFilter = applyAssociation(new CompositeFilter(result, compositeFilter.getOperator()));
        if (isDisjunctiveNormalForm(newFilter)) {
            return newFilter;
        }
        Assert.hardAssert(newFilter instanceof CompositeFilter, "field filters are already in DNF form.", new Object[0]);
        CompositeFilter newCompositeFilter = (CompositeFilter) newFilter;
        Assert.hardAssert(newCompositeFilter.isConjunction(), "Disjunction of filters all of which are already in DNF form is itself in DNF form.", new Object[0]);
        Assert.hardAssert(newCompositeFilter.getFilters().size() > 1, "Single-filter composite filters are already in DNF form.", new Object[0]);
        Filter runningResult = newCompositeFilter.getFilters().get(0);
        for (int i = 1; i < newCompositeFilter.getFilters().size(); i++) {
            runningResult = applyDistribution(runningResult, newCompositeFilter.getFilters().get(i));
        }
        return runningResult;
    }

    protected static Filter computeInExpansion(Filter filter) {
        assertFieldFilterOrCompositeFilter(filter);
        List<Filter> expandedFilters = new ArrayList<>();
        if (filter instanceof FieldFilter) {
            if (filter instanceof InFilter) {
                for (Value value : ((InFilter) filter).getValue().getArrayValue().getValuesList()) {
                    expandedFilters.add(FieldFilter.create(((InFilter) filter).getField(), FieldFilter.Operator.EQUAL, value));
                }
                return new CompositeFilter(expandedFilters, CompositeFilter.Operator.OR);
            }
            return filter;
        }
        CompositeFilter compositeFilter = (CompositeFilter) filter;
        for (Filter subfilter : compositeFilter.getFilters()) {
            expandedFilters.add(computeInExpansion(subfilter));
        }
        return new CompositeFilter(expandedFilters, compositeFilter.getOperator());
    }

    public static List<Filter> getDnfTerms(CompositeFilter filter) {
        if (filter.getFilters().isEmpty()) {
            return Collections.emptyList();
        }
        Filter result = computeDistributedNormalForm(computeInExpansion(filter));
        Assert.hardAssert(isDisjunctiveNormalForm(result), "computeDistributedNormalForm did not result in disjunctive normal form", new Object[0]);
        if (isSingleFieldFilter(result) || isFlatConjunction(result)) {
            return Collections.singletonList(result);
        }
        return result.getFilters();
    }
}
