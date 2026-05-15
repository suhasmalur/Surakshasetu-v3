package com.google.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.ListenerRegistrationImpl;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.QueryListener;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes10.dex */
public class Query {
    final FirebaseFirestore firestore;
    final com.google.firebase.firestore.core.Query query;

    public enum Direction {
        ASCENDING,
        DESCENDING
    }

    Query(com.google.firebase.firestore.core.Query query, FirebaseFirestore firestore) {
        this.query = (com.google.firebase.firestore.core.Query) Preconditions.checkNotNull(query);
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firestore);
    }

    public FirebaseFirestore getFirestore() {
        return this.firestore;
    }

    public Query whereEqualTo(String field, Object value) {
        return where(Filter.equalTo(field, value));
    }

    public Query whereEqualTo(FieldPath fieldPath, Object value) {
        return where(Filter.equalTo(fieldPath, value));
    }

    public Query whereNotEqualTo(String field, Object value) {
        return where(Filter.notEqualTo(field, value));
    }

    public Query whereNotEqualTo(FieldPath fieldPath, Object value) {
        return where(Filter.notEqualTo(fieldPath, value));
    }

    public Query whereLessThan(String field, Object value) {
        return where(Filter.lessThan(field, value));
    }

    public Query whereLessThan(FieldPath fieldPath, Object value) {
        return where(Filter.lessThan(fieldPath, value));
    }

    public Query whereLessThanOrEqualTo(String field, Object value) {
        return where(Filter.lessThanOrEqualTo(field, value));
    }

    public Query whereLessThanOrEqualTo(FieldPath fieldPath, Object value) {
        return where(Filter.lessThanOrEqualTo(fieldPath, value));
    }

    public Query whereGreaterThan(String field, Object value) {
        return where(Filter.greaterThan(field, value));
    }

    public Query whereGreaterThan(FieldPath fieldPath, Object value) {
        return where(Filter.greaterThan(fieldPath, value));
    }

    public Query whereGreaterThanOrEqualTo(String field, Object value) {
        return where(Filter.greaterThanOrEqualTo(field, value));
    }

    public Query whereGreaterThanOrEqualTo(FieldPath fieldPath, Object value) {
        return where(Filter.greaterThanOrEqualTo(fieldPath, value));
    }

    public Query whereArrayContains(String field, Object value) {
        return where(Filter.arrayContains(field, value));
    }

    public Query whereArrayContains(FieldPath fieldPath, Object value) {
        return where(Filter.arrayContains(fieldPath, value));
    }

    public Query whereArrayContainsAny(String field, List<? extends Object> values) {
        return where(Filter.arrayContainsAny(field, values));
    }

    public Query whereArrayContainsAny(FieldPath fieldPath, List<? extends Object> values) {
        return where(Filter.arrayContainsAny(fieldPath, values));
    }

    public Query whereIn(String field, List<? extends Object> values) {
        return where(Filter.inArray(field, values));
    }

    public Query whereIn(FieldPath fieldPath, List<? extends Object> values) {
        return where(Filter.inArray(fieldPath, values));
    }

    public Query whereNotIn(String field, List<? extends Object> values) {
        return where(Filter.notInArray(field, values));
    }

    public Query whereNotIn(FieldPath fieldPath, List<? extends Object> values) {
        return where(Filter.notInArray(fieldPath, values));
    }

    public Query where(Filter filter) {
        com.google.firebase.firestore.core.Filter parsedFilter = parseFilter(filter);
        if (parsedFilter.getFilters().isEmpty()) {
            return this;
        }
        validateNewFilter(parsedFilter);
        return new Query(this.query.filter(parsedFilter), this.firestore);
    }

    private FieldFilter parseFieldFilter(Filter.UnaryFilter fieldFilterData) {
        Value fieldValue;
        FieldPath fieldPath = fieldFilterData.getField();
        FieldFilter.Operator op = fieldFilterData.getOperator();
        Object value = fieldFilterData.getValue();
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Preconditions.checkNotNull(op, "Provided op must not be null.");
        com.google.firebase.firestore.model.FieldPath internalPath = fieldPath.getInternalPath();
        if (internalPath.isKeyField()) {
            if (op == FieldFilter.Operator.ARRAY_CONTAINS || op == FieldFilter.Operator.ARRAY_CONTAINS_ANY) {
                throw new IllegalArgumentException("Invalid query. You can't perform '" + op.toString() + "' queries on FieldPath.documentId().");
            }
            if (op == FieldFilter.Operator.IN || op == FieldFilter.Operator.NOT_IN) {
                validateDisjunctiveFilterElements(value, op);
                ArrayValue.Builder referenceList = ArrayValue.newBuilder();
                for (Object arrayValue : (List) value) {
                    referenceList.addValues(parseDocumentIdValue(arrayValue));
                }
                fieldValue = Value.newBuilder().setArrayValue(referenceList).build();
            } else {
                fieldValue = parseDocumentIdValue(value);
            }
        } else {
            if (op == FieldFilter.Operator.IN || op == FieldFilter.Operator.NOT_IN || op == FieldFilter.Operator.ARRAY_CONTAINS_ANY) {
                validateDisjunctiveFilterElements(value, op);
            }
            fieldValue = this.firestore.getUserDataReader().parseQueryValue(value, op == FieldFilter.Operator.IN || op == FieldFilter.Operator.NOT_IN);
        }
        FieldFilter filter = FieldFilter.create(fieldPath.getInternalPath(), op, fieldValue);
        return filter;
    }

    private com.google.firebase.firestore.core.Filter parseCompositeFilter(Filter.CompositeFilter compositeFilterData) {
        List<com.google.firebase.firestore.core.Filter> parsedFilters = new ArrayList<>();
        for (Filter filter : compositeFilterData.getFilters()) {
            com.google.firebase.firestore.core.Filter parsedFilter = parseFilter(filter);
            if (!parsedFilter.getFilters().isEmpty()) {
                parsedFilters.add(parsedFilter);
            }
        }
        if (parsedFilters.size() == 1) {
            return parsedFilters.get(0);
        }
        return new CompositeFilter(parsedFilters, compositeFilterData.getOperator());
    }

    private com.google.firebase.firestore.core.Filter parseFilter(Filter filter) {
        Assert.hardAssert((filter instanceof Filter.UnaryFilter) || (filter instanceof Filter.CompositeFilter), "Parsing is only supported for Filter.UnaryFilter and Filter.CompositeFilter.", new Object[0]);
        if (filter instanceof Filter.UnaryFilter) {
            return parseFieldFilter((Filter.UnaryFilter) filter);
        }
        return parseCompositeFilter((Filter.CompositeFilter) filter);
    }

    private Value parseDocumentIdValue(Object documentIdValue) {
        if (documentIdValue instanceof String) {
            String documentId = (String) documentIdValue;
            if (documentId.isEmpty()) {
                throw new IllegalArgumentException("Invalid query. When querying with FieldPath.documentId() you must provide a valid document ID, but it was an empty string.");
            }
            if (!this.query.isCollectionGroupQuery() && documentId.contains("/")) {
                throw new IllegalArgumentException("Invalid query. When querying a collection by FieldPath.documentId() you must provide a plain document ID, but '" + documentId + "' contains a '/' character.");
            }
            ResourcePath path = this.query.getPath().append(ResourcePath.fromString(documentId));
            if (!DocumentKey.isDocumentKey(path)) {
                throw new IllegalArgumentException("Invalid query. When querying a collection group by FieldPath.documentId(), the value provided must result in a valid document path, but '" + path + "' is not because it has an odd number of segments (" + path.length() + ").");
            }
            return Values.refValue(getFirestore().getDatabaseId(), DocumentKey.fromPath(path));
        }
        if (documentIdValue instanceof DocumentReference) {
            DocumentReference ref = (DocumentReference) documentIdValue;
            return Values.refValue(getFirestore().getDatabaseId(), ref.getKey());
        }
        throw new IllegalArgumentException("Invalid query. When querying with FieldPath.documentId() you must provide a valid String or DocumentReference, but it was of type: " + Util.typeName(documentIdValue));
    }

    private void validateDisjunctiveFilterElements(Object value, FieldFilter.Operator op) {
        if (!(value instanceof List) || ((List) value).size() == 0) {
            throw new IllegalArgumentException("Invalid Query. A non-empty array is required for '" + op.toString() + "' filters.");
        }
    }

    private List<FieldFilter.Operator> conflictingOps(FieldFilter.Operator op) {
        switch (op) {
            case NOT_EQUAL:
                return Arrays.asList(FieldFilter.Operator.NOT_EQUAL, FieldFilter.Operator.NOT_IN);
            case ARRAY_CONTAINS_ANY:
            case IN:
                return Arrays.asList(FieldFilter.Operator.NOT_IN);
            case NOT_IN:
                return Arrays.asList(FieldFilter.Operator.ARRAY_CONTAINS_ANY, FieldFilter.Operator.IN, FieldFilter.Operator.NOT_IN, FieldFilter.Operator.NOT_EQUAL);
            default:
                return new ArrayList();
        }
    }

    private void validateNewFieldFilter(com.google.firebase.firestore.core.Query query, FieldFilter fieldFilter) {
        FieldFilter.Operator filterOp = fieldFilter.getOperator();
        FieldFilter.Operator conflictingOp = findOpInsideFilters(query.getFilters(), conflictingOps(filterOp));
        if (conflictingOp != null) {
            if (conflictingOp == filterOp) {
                throw new IllegalArgumentException("Invalid Query. You cannot use more than one '" + filterOp.toString() + "' filter.");
            }
            throw new IllegalArgumentException("Invalid Query. You cannot use '" + filterOp.toString() + "' filters with '" + conflictingOp.toString() + "' filters.");
        }
    }

    private void validateNewFilter(com.google.firebase.firestore.core.Filter filter) {
        com.google.firebase.firestore.core.Query testQuery = this.query;
        for (FieldFilter subfilter : filter.getFlattenedFilters()) {
            validateNewFieldFilter(testQuery, subfilter);
            testQuery = testQuery.filter(subfilter);
        }
    }

    private FieldFilter.Operator findOpInsideFilters(List<com.google.firebase.firestore.core.Filter> filters, List<FieldFilter.Operator> operators) {
        for (com.google.firebase.firestore.core.Filter filter : filters) {
            for (FieldFilter fieldFilter : filter.getFlattenedFilters()) {
                if (operators.contains(fieldFilter.getOperator())) {
                    return fieldFilter.getOperator();
                }
            }
        }
        return null;
    }

    public Query orderBy(String field) {
        return orderBy(FieldPath.fromDotSeparatedPath(field), Direction.ASCENDING);
    }

    public Query orderBy(FieldPath fieldPath) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        return orderBy(fieldPath.getInternalPath(), Direction.ASCENDING);
    }

    public Query orderBy(String field, Direction direction) {
        return orderBy(FieldPath.fromDotSeparatedPath(field), direction);
    }

    public Query orderBy(FieldPath fieldPath, Direction direction) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        return orderBy(fieldPath.getInternalPath(), direction);
    }

    private Query orderBy(com.google.firebase.firestore.model.FieldPath fieldPath, Direction direction) {
        OrderBy.Direction dir;
        Preconditions.checkNotNull(direction, "Provided direction must not be null.");
        if (this.query.getStartAt() != null) {
            throw new IllegalArgumentException("Invalid query. You must not call Query.startAt() or Query.startAfter() before calling Query.orderBy().");
        }
        if (this.query.getEndAt() != null) {
            throw new IllegalArgumentException("Invalid query. You must not call Query.endAt() or Query.endBefore() before calling Query.orderBy().");
        }
        if (direction == Direction.ASCENDING) {
            dir = OrderBy.Direction.ASCENDING;
        } else {
            dir = OrderBy.Direction.DESCENDING;
        }
        return new Query(this.query.orderBy(OrderBy.getInstance(dir, fieldPath)), this.firestore);
    }

    public Query limit(long limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Invalid Query. Query limit (" + limit + ") is invalid. Limit must be positive.");
        }
        return new Query(this.query.limitToFirst(limit), this.firestore);
    }

    public Query limitToLast(long limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Invalid Query. Query limitToLast (" + limit + ") is invalid. Limit must be positive.");
        }
        return new Query(this.query.limitToLast(limit), this.firestore);
    }

    public Query startAt(DocumentSnapshot snapshot) {
        Bound bound = boundFromDocumentSnapshot("startAt", snapshot, true);
        return new Query(this.query.startAt(bound), this.firestore);
    }

    public Query startAt(Object... fieldValues) {
        Bound bound = boundFromFields("startAt", fieldValues, true);
        return new Query(this.query.startAt(bound), this.firestore);
    }

    public Query startAfter(DocumentSnapshot snapshot) {
        Bound bound = boundFromDocumentSnapshot("startAfter", snapshot, false);
        return new Query(this.query.startAt(bound), this.firestore);
    }

    public Query startAfter(Object... fieldValues) {
        Bound bound = boundFromFields("startAfter", fieldValues, false);
        return new Query(this.query.startAt(bound), this.firestore);
    }

    public Query endBefore(DocumentSnapshot snapshot) {
        Bound bound = boundFromDocumentSnapshot("endBefore", snapshot, false);
        return new Query(this.query.endAt(bound), this.firestore);
    }

    public Query endBefore(Object... fieldValues) {
        Bound bound = boundFromFields("endBefore", fieldValues, false);
        return new Query(this.query.endAt(bound), this.firestore);
    }

    public Query endAt(DocumentSnapshot snapshot) {
        Bound bound = boundFromDocumentSnapshot("endAt", snapshot, true);
        return new Query(this.query.endAt(bound), this.firestore);
    }

    public Query endAt(Object... fieldValues) {
        Bound bound = boundFromFields("endAt", fieldValues, true);
        return new Query(this.query.endAt(bound), this.firestore);
    }

    private Bound boundFromDocumentSnapshot(String methodName, DocumentSnapshot snapshot, boolean inclusive) {
        Preconditions.checkNotNull(snapshot, "Provided snapshot must not be null.");
        if (!snapshot.exists()) {
            throw new IllegalArgumentException("Can't use a DocumentSnapshot for a document that doesn't exist for " + methodName + "().");
        }
        Document document = snapshot.getDocument();
        List<Value> components = new ArrayList<>();
        for (OrderBy orderBy : this.query.getNormalizedOrderBy()) {
            if (orderBy.getField().equals(com.google.firebase.firestore.model.FieldPath.KEY_PATH)) {
                components.add(Values.refValue(this.firestore.getDatabaseId(), document.getKey()));
            } else {
                Value value = document.getField(orderBy.getField());
                if (ServerTimestamps.isServerTimestamp(value)) {
                    throw new IllegalArgumentException("Invalid query. You are trying to start or end a query using a document for which the field '" + orderBy.getField() + "' is an uncommitted server timestamp. (Since the value of this field is unknown, you cannot start/end a query with it.)");
                }
                if (value != null) {
                    components.add(value);
                } else {
                    throw new IllegalArgumentException("Invalid query. You are trying to start or end a query using a document for which the field '" + orderBy.getField() + "' (used as the orderBy) does not exist.");
                }
            }
        }
        return new Bound(components, inclusive);
    }

    private Bound boundFromFields(String methodName, Object[] values, boolean inclusive) {
        List<OrderBy> explicitOrderBy = this.query.getExplicitOrderBy();
        if (values.length > explicitOrderBy.size()) {
            throw new IllegalArgumentException("Too many arguments provided to " + methodName + "(). The number of arguments must be less than or equal to the number of orderBy() clauses.");
        }
        List<Value> components = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Object rawValue = values[i];
            OrderBy orderBy = explicitOrderBy.get(i);
            if (orderBy.getField().equals(com.google.firebase.firestore.model.FieldPath.KEY_PATH)) {
                if (!(rawValue instanceof String)) {
                    throw new IllegalArgumentException("Invalid query. Expected a string for document ID in " + methodName + "(), but got " + rawValue + ".");
                }
                String documentId = (String) rawValue;
                if (!this.query.isCollectionGroupQuery() && documentId.contains("/")) {
                    throw new IllegalArgumentException("Invalid query. When querying a collection and ordering by FieldPath.documentId(), the value passed to " + methodName + "() must be a plain document ID, but '" + documentId + "' contains a slash.");
                }
                ResourcePath path = this.query.getPath().append(ResourcePath.fromString(documentId));
                if (!DocumentKey.isDocumentKey(path)) {
                    throw new IllegalArgumentException("Invalid query. When querying a collection group and ordering by FieldPath.documentId(), the value passed to " + methodName + "() must result in a valid document path, but '" + path + "' is not because it contains an odd number of segments.");
                }
                DocumentKey key = DocumentKey.fromPath(path);
                components.add(Values.refValue(this.firestore.getDatabaseId(), key));
            } else {
                Value wrapped = this.firestore.getUserDataReader().parseQueryValue(rawValue);
                components.add(wrapped);
            }
        }
        return new Bound(components, inclusive);
    }

    public Task<QuerySnapshot> get() {
        return get(Source.DEFAULT);
    }

    public Task<QuerySnapshot> get(Source source) {
        validateHasExplicitOrderByForLimitToLast();
        if (source == Source.CACHE) {
            return this.firestore.getClient().getDocumentsFromLocalCache(this.query).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.Query$$ExternalSyntheticLambda2
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return this.f$0.m274lambda$get$0$comgooglefirebasefirestoreQuery(task);
                }
            });
        }
        return getViaSnapshotListener(source);
    }

    /* JADX INFO: renamed from: lambda$get$0$com-google-firebase-firestore-Query, reason: not valid java name */
    /* synthetic */ QuerySnapshot m274lambda$get$0$comgooglefirebasefirestoreQuery(Task viewSnap) throws Exception {
        return new QuerySnapshot(new Query(this.query, this.firestore), (ViewSnapshot) viewSnap.getResult(), this.firestore);
    }

    private Task<QuerySnapshot> getViaSnapshotListener(final Source source) {
        final TaskCompletionSource<QuerySnapshot> res = new TaskCompletionSource<>();
        final TaskCompletionSource<ListenerRegistration> registration = new TaskCompletionSource<>();
        EventManager.ListenOptions options = new EventManager.ListenOptions();
        options.includeDocumentMetadataChanges = true;
        options.includeQueryMetadataChanges = true;
        options.waitForSyncWhenOnline = true;
        ListenerRegistration listenerRegistration = addSnapshotListenerInternal(Executors.DIRECT_EXECUTOR, options, null, new EventListener() { // from class: com.google.firebase.firestore.Query$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                Query.lambda$getViaSnapshotListener$1(res, registration, source, (QuerySnapshot) obj, firebaseFirestoreException);
            }
        });
        registration.setResult(listenerRegistration);
        return res.getTask();
    }

    static /* synthetic */ void lambda$getViaSnapshotListener$1(TaskCompletionSource res, TaskCompletionSource registration, Source source, QuerySnapshot snapshot, FirebaseFirestoreException error) {
        if (error != null) {
            res.setException(error);
            return;
        }
        try {
            ListenerRegistration actualRegistration = (ListenerRegistration) Tasks.await(registration.getTask());
            actualRegistration.remove();
            if (snapshot.getMetadata().isFromCache() && source == Source.SERVER) {
                res.setException(new FirebaseFirestoreException("Failed to get documents from server. (However, these documents may exist in the local cache. Run again without setting source to SERVER to retrieve the cached documents.)", FirebaseFirestoreException.Code.UNAVAILABLE));
            } else {
                res.setResult(snapshot);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw Assert.fail(e, "Failed to register a listener for a query result", new Object[0]);
        } catch (ExecutionException e2) {
            throw Assert.fail(e2, "Failed to register a listener for a query result", new Object[0]);
        }
    }

    public ListenerRegistration addSnapshotListener(EventListener<QuerySnapshot> listener) {
        return addSnapshotListener(MetadataChanges.EXCLUDE, listener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, EventListener<QuerySnapshot> listener) {
        return addSnapshotListener(executor, MetadataChanges.EXCLUDE, listener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, EventListener<QuerySnapshot> listener) {
        return addSnapshotListener(activity, MetadataChanges.EXCLUDE, listener);
    }

    public ListenerRegistration addSnapshotListener(MetadataChanges metadataChanges, EventListener<QuerySnapshot> listener) {
        return addSnapshotListener(Executors.DEFAULT_CALLBACK_EXECUTOR, metadataChanges, listener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, MetadataChanges metadataChanges, EventListener<QuerySnapshot> listener) {
        Preconditions.checkNotNull(executor, "Provided executor must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(listener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(executor, internalOptions(metadataChanges), null, listener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, MetadataChanges metadataChanges, EventListener<QuerySnapshot> listener) {
        Preconditions.checkNotNull(activity, "Provided activity must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(listener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(Executors.DEFAULT_CALLBACK_EXECUTOR, internalOptions(metadataChanges), activity, listener);
    }

    private ListenerRegistration addSnapshotListenerInternal(Executor executor, EventManager.ListenOptions options, Activity activity, final EventListener<QuerySnapshot> userListener) {
        validateHasExplicitOrderByForLimitToLast();
        EventListener<ViewSnapshot> viewListener = new EventListener() { // from class: com.google.firebase.firestore.Query$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                this.f$0.m273xb1c289cb(userListener, (ViewSnapshot) obj, firebaseFirestoreException);
            }
        };
        AsyncEventListener<ViewSnapshot> asyncListener = new AsyncEventListener<>(executor, viewListener);
        QueryListener queryListener = this.firestore.getClient().listen(this.query, options, asyncListener);
        return ActivityScope.bind(activity, new ListenerRegistrationImpl(this.firestore.getClient(), queryListener, asyncListener));
    }

    /* JADX INFO: renamed from: lambda$addSnapshotListenerInternal$2$com-google-firebase-firestore-Query, reason: not valid java name */
    /* synthetic */ void m273xb1c289cb(EventListener userListener, ViewSnapshot snapshot, FirebaseFirestoreException error) {
        if (error != null) {
            userListener.onEvent(null, error);
            return;
        }
        Assert.hardAssert(snapshot != null, "Got event without value or error set", new Object[0]);
        QuerySnapshot querySnapshot = new QuerySnapshot(this, snapshot, this.firestore);
        userListener.onEvent(querySnapshot, null);
    }

    private void validateHasExplicitOrderByForLimitToLast() {
        if (this.query.getLimitType().equals(Query.LimitType.LIMIT_TO_LAST) && this.query.getExplicitOrderBy().isEmpty()) {
            throw new IllegalStateException("limitToLast() queries require specifying at least one orderBy() clause");
        }
    }

    public AggregateQuery count() {
        return new AggregateQuery(this, Collections.singletonList(AggregateField.count()));
    }

    public AggregateQuery aggregate(final AggregateField aggregateField, AggregateField... aggregateFields) {
        List<AggregateField> fields = new ArrayList<AggregateField>() { // from class: com.google.firebase.firestore.Query.1
            {
                add(aggregateField);
            }
        };
        fields.addAll(Arrays.asList(aggregateFields));
        return new AggregateQuery(this, fields);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Query)) {
            return false;
        }
        Query that = (Query) o;
        return this.query.equals(that.query) && this.firestore.equals(that.firestore);
    }

    public int hashCode() {
        int result = this.query.hashCode();
        return (result * 31) + this.firestore.hashCode();
    }

    private static EventManager.ListenOptions internalOptions(MetadataChanges metadataChanges) {
        EventManager.ListenOptions internalOptions = new EventManager.ListenOptions();
        internalOptions.includeDocumentMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        internalOptions.includeQueryMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        internalOptions.waitForSyncWhenOnline = false;
        return internalOptions;
    }
}
