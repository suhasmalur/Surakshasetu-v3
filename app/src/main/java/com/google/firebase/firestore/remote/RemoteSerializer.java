package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.model.mutation.ArrayTransformOperation;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.FieldTransform;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.model.mutation.NumericIncrementTransformOperation;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.model.mutation.ServerTimestampOperation;
import com.google.firebase.firestore.model.mutation.SetMutation;
import com.google.firebase.firestore.model.mutation.TransformOperation;
import com.google.firebase.firestore.model.mutation.VerifyMutation;
import com.google.firebase.firestore.remote.WatchChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.BatchGetDocumentsResponse;
import com.google.firestore.v1.Cursor;
import com.google.firestore.v1.Document;
import com.google.firestore.v1.DocumentChange;
import com.google.firestore.v1.DocumentDelete;
import com.google.firestore.v1.DocumentMask;
import com.google.firestore.v1.DocumentRemove;
import com.google.firestore.v1.DocumentTransform;
import com.google.firestore.v1.ListenResponse;
import com.google.firestore.v1.Precondition;
import com.google.firestore.v1.StructuredAggregationQuery;
import com.google.firestore.v1.StructuredQuery;
import com.google.firestore.v1.Target;
import com.google.firestore.v1.Value;
import com.google.firestore.v1.Write;
import com.google.firestore.v1.WriteResult;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes10.dex */
public final class RemoteSerializer {
    private final DatabaseId databaseId;
    private final String databaseName;

    public RemoteSerializer(DatabaseId databaseId) {
        this.databaseId = databaseId;
        this.databaseName = encodedDatabaseId(databaseId).canonicalString();
    }

    public Timestamp encodeTimestamp(com.google.firebase.Timestamp timestamp) {
        Timestamp.Builder builder = Timestamp.newBuilder();
        builder.setSeconds(timestamp.getSeconds());
        builder.setNanos(timestamp.getNanoseconds());
        return builder.build();
    }

    public com.google.firebase.Timestamp decodeTimestamp(Timestamp proto) {
        return new com.google.firebase.Timestamp(proto.getSeconds(), proto.getNanos());
    }

    public Timestamp encodeVersion(SnapshotVersion version) {
        return encodeTimestamp(version.getTimestamp());
    }

    public SnapshotVersion decodeVersion(Timestamp proto) {
        if (proto.getSeconds() == 0 && proto.getNanos() == 0) {
            return SnapshotVersion.NONE;
        }
        return new SnapshotVersion(decodeTimestamp(proto));
    }

    public String encodeKey(DocumentKey key) {
        return encodeResourceName(this.databaseId, key.getPath());
    }

    public DocumentKey decodeKey(String name) {
        ResourcePath resource = decodeResourceName(name);
        Assert.hardAssert(resource.getSegment(1).equals(this.databaseId.getProjectId()), "Tried to deserialize key from different project.", new Object[0]);
        Assert.hardAssert(resource.getSegment(3).equals(this.databaseId.getDatabaseId()), "Tried to deserialize key from different database.", new Object[0]);
        return DocumentKey.fromPath(extractLocalPathFromResourceName(resource));
    }

    private String encodeQueryPath(ResourcePath path) {
        return encodeResourceName(this.databaseId, path);
    }

    private ResourcePath decodeQueryPath(String name) {
        ResourcePath resource = decodeResourceName(name);
        if (resource.length() == 4) {
            return ResourcePath.EMPTY;
        }
        return extractLocalPathFromResourceName(resource);
    }

    private String encodeResourceName(DatabaseId databaseId, ResourcePath path) {
        return encodedDatabaseId(databaseId).append("documents").append(path).canonicalString();
    }

    private ResourcePath decodeResourceName(String encoded) {
        ResourcePath resource = ResourcePath.fromString(encoded);
        Assert.hardAssert(isValidResourceName(resource), "Tried to deserialize invalid key %s", resource);
        return resource;
    }

    private static ResourcePath encodedDatabaseId(DatabaseId databaseId) {
        return ResourcePath.fromSegments(Arrays.asList("projects", databaseId.getProjectId(), "databases", databaseId.getDatabaseId()));
    }

    private static ResourcePath extractLocalPathFromResourceName(ResourcePath resourceName) {
        Assert.hardAssert(resourceName.length() > 4 && resourceName.getSegment(4).equals("documents"), "Tried to deserialize invalid key %s", resourceName);
        return resourceName.popFirst(5);
    }

    private static boolean isValidResourceName(ResourcePath path) {
        return path.length() >= 4 && path.getSegment(0).equals("projects") && path.getSegment(2).equals("databases");
    }

    public boolean isLocalResourceName(ResourcePath path) {
        return isValidResourceName(path) && path.getSegment(1).equals(this.databaseId.getProjectId()) && path.getSegment(3).equals(this.databaseId.getDatabaseId());
    }

    public String databaseName() {
        return this.databaseName;
    }

    public Document encodeDocument(DocumentKey key, ObjectValue value) {
        Document.Builder builder = Document.newBuilder();
        builder.setName(encodeKey(key));
        builder.putAllFields(value.getFieldsMap());
        return builder.build();
    }

    public MutableDocument decodeMaybeDocument(BatchGetDocumentsResponse response) {
        if (response.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.FOUND)) {
            return decodeFoundDocument(response);
        }
        if (response.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.MISSING)) {
            return decodeMissingDocument(response);
        }
        throw new IllegalArgumentException("Unknown result case: " + response.getResultCase());
    }

    private MutableDocument decodeFoundDocument(BatchGetDocumentsResponse response) {
        Assert.hardAssert(response.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.FOUND), "Tried to deserialize a found document from a missing document.", new Object[0]);
        DocumentKey key = decodeKey(response.getFound().getName());
        ObjectValue value = ObjectValue.fromMap(response.getFound().getFieldsMap());
        SnapshotVersion version = decodeVersion(response.getFound().getUpdateTime());
        Assert.hardAssert(!version.equals(SnapshotVersion.NONE), "Got a document response with no snapshot version", new Object[0]);
        return MutableDocument.newFoundDocument(key, version, value);
    }

    private MutableDocument decodeMissingDocument(BatchGetDocumentsResponse response) {
        Assert.hardAssert(response.getResultCase().equals(BatchGetDocumentsResponse.ResultCase.MISSING), "Tried to deserialize a missing document from a found document.", new Object[0]);
        DocumentKey key = decodeKey(response.getMissing());
        SnapshotVersion version = decodeVersion(response.getReadTime());
        Assert.hardAssert(!version.equals(SnapshotVersion.NONE), "Got a no document response with no snapshot version", new Object[0]);
        return MutableDocument.newNoDocument(key, version);
    }

    public Write encodeMutation(Mutation mutation) {
        Write.Builder builder = Write.newBuilder();
        if (mutation instanceof SetMutation) {
            builder.setUpdate(encodeDocument(mutation.getKey(), ((SetMutation) mutation).getValue()));
        } else if (mutation instanceof PatchMutation) {
            builder.setUpdate(encodeDocument(mutation.getKey(), ((PatchMutation) mutation).getValue()));
            builder.setUpdateMask(encodeDocumentMask(mutation.getFieldMask()));
        } else if (mutation instanceof DeleteMutation) {
            builder.setDelete(encodeKey(mutation.getKey()));
        } else if (mutation instanceof VerifyMutation) {
            builder.setVerify(encodeKey(mutation.getKey()));
        } else {
            throw Assert.fail("unknown mutation type %s", mutation.getClass());
        }
        for (FieldTransform fieldTransform : mutation.getFieldTransforms()) {
            builder.addUpdateTransforms(encodeFieldTransform(fieldTransform));
        }
        if (!mutation.getPrecondition().isNone()) {
            builder.setCurrentDocument(encodePrecondition(mutation.getPrecondition()));
        }
        return builder.build();
    }

    public Mutation decodeMutation(Write mutation) {
        Precondition precondition;
        if (mutation.hasCurrentDocument()) {
            precondition = decodePrecondition(mutation.getCurrentDocument());
        } else {
            precondition = Precondition.NONE;
        }
        List<FieldTransform> fieldTransforms = new ArrayList<>();
        for (DocumentTransform.FieldTransform fieldTransform : mutation.getUpdateTransformsList()) {
            fieldTransforms.add(decodeFieldTransform(fieldTransform));
        }
        switch (mutation.getOperationCase()) {
            case UPDATE:
                if (mutation.hasUpdateMask()) {
                    return new PatchMutation(decodeKey(mutation.getUpdate().getName()), ObjectValue.fromMap(mutation.getUpdate().getFieldsMap()), decodeDocumentMask(mutation.getUpdateMask()), precondition, fieldTransforms);
                }
                return new SetMutation(decodeKey(mutation.getUpdate().getName()), ObjectValue.fromMap(mutation.getUpdate().getFieldsMap()), precondition, fieldTransforms);
            case DELETE:
                return new DeleteMutation(decodeKey(mutation.getDelete()), precondition);
            case VERIFY:
                return new VerifyMutation(decodeKey(mutation.getVerify()), precondition);
            default:
                throw Assert.fail("Unknown mutation operation: %d", mutation.getOperationCase());
        }
    }

    private com.google.firestore.v1.Precondition encodePrecondition(Precondition precondition) {
        Assert.hardAssert(!precondition.isNone(), "Can't serialize an empty precondition", new Object[0]);
        Precondition.Builder builder = com.google.firestore.v1.Precondition.newBuilder();
        if (precondition.getUpdateTime() != null) {
            return builder.setUpdateTime(encodeVersion(precondition.getUpdateTime())).build();
        }
        if (precondition.getExists() != null) {
            return builder.setExists(precondition.getExists().booleanValue()).build();
        }
        throw Assert.fail("Unknown Precondition", new Object[0]);
    }

    private com.google.firebase.firestore.model.mutation.Precondition decodePrecondition(com.google.firestore.v1.Precondition precondition) {
        switch (precondition.getConditionTypeCase()) {
            case UPDATE_TIME:
                return com.google.firebase.firestore.model.mutation.Precondition.updateTime(decodeVersion(precondition.getUpdateTime()));
            case EXISTS:
                return com.google.firebase.firestore.model.mutation.Precondition.exists(precondition.getExists());
            case CONDITIONTYPE_NOT_SET:
                return com.google.firebase.firestore.model.mutation.Precondition.NONE;
            default:
                throw Assert.fail("Unknown precondition", new Object[0]);
        }
    }

    private DocumentMask encodeDocumentMask(FieldMask mask) {
        DocumentMask.Builder builder = DocumentMask.newBuilder();
        for (FieldPath path : mask.getMask()) {
            builder.addFieldPaths(path.canonicalString());
        }
        return builder.build();
    }

    private FieldMask decodeDocumentMask(DocumentMask mask) {
        int count = mask.getFieldPathsCount();
        Set<FieldPath> paths = new HashSet<>(count);
        for (int i = 0; i < count; i++) {
            paths.add(FieldPath.fromServerFormat(mask.getFieldPaths(i)));
        }
        return FieldMask.fromSet(paths);
    }

    private DocumentTransform.FieldTransform encodeFieldTransform(FieldTransform fieldTransform) {
        TransformOperation transform = fieldTransform.getOperation();
        if (transform instanceof ServerTimestampOperation) {
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setSetToServerValue(DocumentTransform.FieldTransform.ServerValue.REQUEST_TIME).build();
        }
        if (transform instanceof ArrayTransformOperation.Union) {
            ArrayTransformOperation.Union union = (ArrayTransformOperation.Union) transform;
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setAppendMissingElements(ArrayValue.newBuilder().addAllValues(union.getElements())).build();
        }
        if (transform instanceof ArrayTransformOperation.Remove) {
            ArrayTransformOperation.Remove remove = (ArrayTransformOperation.Remove) transform;
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setRemoveAllFromArray(ArrayValue.newBuilder().addAllValues(remove.getElements())).build();
        }
        if (transform instanceof NumericIncrementTransformOperation) {
            NumericIncrementTransformOperation incrementOperation = (NumericIncrementTransformOperation) transform;
            return DocumentTransform.FieldTransform.newBuilder().setFieldPath(fieldTransform.getFieldPath().canonicalString()).setIncrement(incrementOperation.getOperand()).build();
        }
        throw Assert.fail("Unknown transform: %s", transform);
    }

    private FieldTransform decodeFieldTransform(DocumentTransform.FieldTransform fieldTransform) {
        switch (fieldTransform.getTransformTypeCase()) {
            case SET_TO_SERVER_VALUE:
                Assert.hardAssert(fieldTransform.getSetToServerValue() == DocumentTransform.FieldTransform.ServerValue.REQUEST_TIME, "Unknown transform setToServerValue: %s", fieldTransform.getSetToServerValue());
                return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), ServerTimestampOperation.getInstance());
            case APPEND_MISSING_ELEMENTS:
                return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new ArrayTransformOperation.Union(fieldTransform.getAppendMissingElements().getValuesList()));
            case REMOVE_ALL_FROM_ARRAY:
                return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new ArrayTransformOperation.Remove(fieldTransform.getRemoveAllFromArray().getValuesList()));
            case INCREMENT:
                return new FieldTransform(FieldPath.fromServerFormat(fieldTransform.getFieldPath()), new NumericIncrementTransformOperation(fieldTransform.getIncrement()));
            default:
                throw Assert.fail("Unknown FieldTransform proto: %s", fieldTransform);
        }
    }

    public MutationResult decodeMutationResult(WriteResult proto, SnapshotVersion commitVersion) {
        SnapshotVersion version = decodeVersion(proto.getUpdateTime());
        if (SnapshotVersion.NONE.equals(version)) {
            version = commitVersion;
        }
        int transformResultsCount = proto.getTransformResultsCount();
        List<Value> transformResults = new ArrayList<>(transformResultsCount);
        for (int i = 0; i < transformResultsCount; i++) {
            transformResults.add(proto.getTransformResults(i));
        }
        return new MutationResult(version, transformResults);
    }

    public Map<String, String> encodeListenRequestLabels(TargetData targetData) {
        String value = encodeLabel(targetData.getPurpose());
        if (value == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>(1);
        result.put("goog-listen-tags", value);
        return result;
    }

    private String encodeLabel(QueryPurpose purpose) {
        switch (purpose) {
            case LISTEN:
                return null;
            case EXISTENCE_FILTER_MISMATCH:
                return "existence-filter-mismatch";
            case EXISTENCE_FILTER_MISMATCH_BLOOM:
                return "existence-filter-mismatch-bloom";
            case LIMBO_RESOLUTION:
                return "limbo-document";
            default:
                throw Assert.fail("Unrecognized query purpose: %s", purpose);
        }
    }

    public Target encodeTarget(TargetData targetData) {
        Target.Builder builder = Target.newBuilder();
        com.google.firebase.firestore.core.Target target = targetData.getTarget();
        if (target.isDocumentQuery()) {
            builder.setDocuments(encodeDocumentsTarget(target));
        } else {
            builder.setQuery(encodeQueryTarget(target));
        }
        builder.setTargetId(targetData.getTargetId());
        if (targetData.getResumeToken().isEmpty() && targetData.getSnapshotVersion().compareTo(SnapshotVersion.NONE) > 0) {
            builder.setReadTime(encodeTimestamp(targetData.getSnapshotVersion().getTimestamp()));
        } else {
            builder.setResumeToken(targetData.getResumeToken());
        }
        if (targetData.getExpectedCount() != null && (!targetData.getResumeToken().isEmpty() || targetData.getSnapshotVersion().compareTo(SnapshotVersion.NONE) > 0)) {
            builder.setExpectedCount(Int32Value.newBuilder().setValue(targetData.getExpectedCount().intValue()));
        }
        return builder.build();
    }

    public Target.DocumentsTarget encodeDocumentsTarget(com.google.firebase.firestore.core.Target target) {
        Target.DocumentsTarget.Builder builder = Target.DocumentsTarget.newBuilder();
        builder.addDocuments(encodeQueryPath(target.getPath()));
        return builder.build();
    }

    public com.google.firebase.firestore.core.Target decodeDocumentsTarget(Target.DocumentsTarget target) {
        int count = target.getDocumentsCount();
        Assert.hardAssert(count == 1, "DocumentsTarget contained other than 1 document %d", Integer.valueOf(count));
        String name = target.getDocuments(0);
        return Query.atPath(decodeQueryPath(name)).toTarget();
    }

    public Target.QueryTarget encodeQueryTarget(com.google.firebase.firestore.core.Target target) {
        Target.QueryTarget.Builder builder = Target.QueryTarget.newBuilder();
        StructuredQuery.Builder structuredQueryBuilder = StructuredQuery.newBuilder();
        ResourcePath path = target.getPath();
        if (target.getCollectionGroup() != null) {
            Assert.hardAssert(path.length() % 2 == 0, "Collection Group queries should be within a document path or root.", new Object[0]);
            builder.setParent(encodeQueryPath(path));
            StructuredQuery.CollectionSelector.Builder from = StructuredQuery.CollectionSelector.newBuilder();
            from.setCollectionId(target.getCollectionGroup());
            from.setAllDescendants(true);
            structuredQueryBuilder.addFrom(from);
        } else {
            Assert.hardAssert(path.length() % 2 != 0, "Document queries with filters are not supported.", new Object[0]);
            builder.setParent(encodeQueryPath(path.popLast()));
            StructuredQuery.CollectionSelector.Builder from2 = StructuredQuery.CollectionSelector.newBuilder();
            from2.setCollectionId(path.getLastSegment());
            structuredQueryBuilder.addFrom(from2);
        }
        if (target.getFilters().size() > 0) {
            structuredQueryBuilder.setWhere(encodeFilters(target.getFilters()));
        }
        for (OrderBy orderBy : target.getOrderBy()) {
            structuredQueryBuilder.addOrderBy(encodeOrderBy(orderBy));
        }
        if (target.hasLimit()) {
            structuredQueryBuilder.setLimit(Int32Value.newBuilder().setValue((int) target.getLimit()));
        }
        if (target.getStartAt() != null) {
            Cursor.Builder cursor = Cursor.newBuilder();
            cursor.addAllValues(target.getStartAt().getPosition());
            cursor.setBefore(target.getStartAt().isInclusive());
            structuredQueryBuilder.setStartAt(cursor);
        }
        if (target.getEndAt() != null) {
            Cursor.Builder cursor2 = Cursor.newBuilder();
            cursor2.addAllValues(target.getEndAt().getPosition());
            cursor2.setBefore(true ^ target.getEndAt().isInclusive());
            structuredQueryBuilder.setEndAt(cursor2);
        }
        builder.setStructuredQuery(structuredQueryBuilder);
        return builder.build();
    }

    public com.google.firebase.firestore.core.Target decodeQueryTarget(String parent, StructuredQuery query) {
        List<Filter> filterBy;
        List<OrderBy> orderBy;
        long limit;
        Bound startAt;
        Bound endAt;
        boolean z;
        ResourcePath path = decodeQueryPath(parent);
        String collectionGroup = null;
        int fromCount = query.getFromCount();
        if (fromCount > 0) {
            if (fromCount == 1) {
                z = true;
            } else {
                z = false;
            }
            Assert.hardAssert(z, "StructuredQuery.from with more than one collection is not supported.", new Object[0]);
            StructuredQuery.CollectionSelector from = query.getFrom(0);
            if (from.getAllDescendants()) {
                collectionGroup = from.getCollectionId();
            } else {
                path = path.append(from.getCollectionId());
            }
        }
        if (query.hasWhere()) {
            filterBy = decodeFilters(query.getWhere());
        } else {
            filterBy = Collections.emptyList();
        }
        int orderByCount = query.getOrderByCount();
        if (orderByCount > 0) {
            List<OrderBy> orderBy2 = new ArrayList<>(orderByCount);
            for (int i = 0; i < orderByCount; i++) {
                orderBy2.add(decodeOrderBy(query.getOrderBy(i)));
            }
            orderBy = orderBy2;
        } else {
            List<OrderBy> orderBy3 = Collections.emptyList();
            orderBy = orderBy3;
        }
        if (!query.hasLimit()) {
            limit = -1;
        } else {
            long limit2 = query.getLimit().getValue();
            limit = limit2;
        }
        if (!query.hasStartAt()) {
            startAt = null;
        } else {
            Bound startAt2 = new Bound(query.getStartAt().getValuesList(), query.getStartAt().getBefore());
            startAt = startAt2;
        }
        if (!query.hasEndAt()) {
            endAt = null;
        } else {
            Bound endAt2 = new Bound(query.getEndAt().getValuesList(), true ^ query.getEndAt().getBefore());
            endAt = endAt2;
        }
        return new com.google.firebase.firestore.core.Target(path, collectionGroup, filterBy, orderBy, limit, startAt, endAt);
    }

    public com.google.firebase.firestore.core.Target decodeQueryTarget(Target.QueryTarget target) {
        return decodeQueryTarget(target.getParent(), target.getStructuredQuery());
    }

    StructuredAggregationQuery encodeStructuredAggregationQuery(Target.QueryTarget encodedQueryTarget, List<AggregateField> aggregateFields, HashMap<String, String> aliasMap) {
        StructuredAggregationQuery.Builder structuredAggregationQuery = StructuredAggregationQuery.newBuilder();
        structuredAggregationQuery.setStructuredQuery(encodedQueryTarget.getStructuredQuery());
        List<StructuredAggregationQuery.Aggregation> aggregations = new ArrayList<>();
        HashSet<String> uniqueFields = new HashSet<>();
        int aliasID = 1;
        for (AggregateField aggregateField : aggregateFields) {
            if (!uniqueFields.contains(aggregateField.getAlias())) {
                uniqueFields.add(aggregateField.getAlias());
                int aliasID2 = aliasID + 1;
                String serverAlias = "aggregate_" + aliasID;
                aliasMap.put(serverAlias, aggregateField.getAlias());
                StructuredAggregationQuery.Aggregation.Builder aggregation = StructuredAggregationQuery.Aggregation.newBuilder();
                StructuredQuery.FieldReference fieldPath = StructuredQuery.FieldReference.newBuilder().setFieldPath(aggregateField.getFieldPath()).build();
                if (aggregateField instanceof AggregateField.CountAggregateField) {
                    aggregation.setCount(StructuredAggregationQuery.Aggregation.Count.getDefaultInstance());
                } else if (aggregateField instanceof AggregateField.SumAggregateField) {
                    aggregation.setSum(StructuredAggregationQuery.Aggregation.Sum.newBuilder().setField(fieldPath).build());
                } else if (aggregateField instanceof AggregateField.AverageAggregateField) {
                    aggregation.setAvg(StructuredAggregationQuery.Aggregation.Avg.newBuilder().setField(fieldPath).build());
                } else {
                    throw new RuntimeException("Unsupported aggregation");
                }
                aggregation.setAlias(serverAlias);
                aggregations.add(aggregation.build());
                aliasID = aliasID2;
            }
        }
        structuredAggregationQuery.addAllAggregations(aggregations);
        return (StructuredAggregationQuery) structuredAggregationQuery.build();
    }

    private StructuredQuery.Filter encodeFilters(List<Filter> filters) {
        return encodeFilter(new CompositeFilter(filters, CompositeFilter.Operator.AND));
    }

    private List<Filter> decodeFilters(StructuredQuery.Filter proto) {
        Filter result = decodeFilter(proto);
        if (result instanceof CompositeFilter) {
            CompositeFilter compositeFilter = (CompositeFilter) result;
            if (compositeFilter.isFlatConjunction()) {
                return compositeFilter.getFilters();
            }
        }
        return Collections.singletonList(result);
    }

    StructuredQuery.Filter encodeFilter(Filter filter) {
        if (filter instanceof FieldFilter) {
            return encodeUnaryOrFieldFilter((FieldFilter) filter);
        }
        if (filter instanceof CompositeFilter) {
            return encodeCompositeFilter((CompositeFilter) filter);
        }
        throw Assert.fail("Unrecognized filter type %s", filter.toString());
    }

    StructuredQuery.Filter encodeUnaryOrFieldFilter(FieldFilter filter) {
        StructuredQuery.UnaryFilter.Operator operator;
        StructuredQuery.UnaryFilter.Operator operator2;
        if (filter.getOperator() == FieldFilter.Operator.EQUAL || filter.getOperator() == FieldFilter.Operator.NOT_EQUAL) {
            StructuredQuery.UnaryFilter.Builder unaryProto = StructuredQuery.UnaryFilter.newBuilder();
            unaryProto.setField(encodeFieldPath(filter.getField()));
            if (Values.isNanValue(filter.getValue())) {
                if (filter.getOperator() == FieldFilter.Operator.EQUAL) {
                    operator2 = StructuredQuery.UnaryFilter.Operator.IS_NAN;
                } else {
                    operator2 = StructuredQuery.UnaryFilter.Operator.IS_NOT_NAN;
                }
                unaryProto.setOp(operator2);
                return StructuredQuery.Filter.newBuilder().setUnaryFilter(unaryProto).build();
            }
            if (Values.isNullValue(filter.getValue())) {
                if (filter.getOperator() == FieldFilter.Operator.EQUAL) {
                    operator = StructuredQuery.UnaryFilter.Operator.IS_NULL;
                } else {
                    operator = StructuredQuery.UnaryFilter.Operator.IS_NOT_NULL;
                }
                unaryProto.setOp(operator);
                return StructuredQuery.Filter.newBuilder().setUnaryFilter(unaryProto).build();
            }
        }
        StructuredQuery.FieldFilter.Builder proto = StructuredQuery.FieldFilter.newBuilder();
        proto.setField(encodeFieldPath(filter.getField()));
        proto.setOp(encodeFieldFilterOperator(filter.getOperator()));
        proto.setValue(filter.getValue());
        return StructuredQuery.Filter.newBuilder().setFieldFilter(proto).build();
    }

    StructuredQuery.CompositeFilter.Operator encodeCompositeFilterOperator(CompositeFilter.Operator op) {
        switch (op) {
            case AND:
                return StructuredQuery.CompositeFilter.Operator.AND;
            case OR:
                return StructuredQuery.CompositeFilter.Operator.OR;
            default:
                throw Assert.fail("Unrecognized composite filter type.", new Object[0]);
        }
    }

    CompositeFilter.Operator decodeCompositeFilterOperator(StructuredQuery.CompositeFilter.Operator op) {
        switch (op) {
            case AND:
                return CompositeFilter.Operator.AND;
            case OR:
                return CompositeFilter.Operator.OR;
            default:
                throw Assert.fail("Only AND and OR composite filter types are supported.", new Object[0]);
        }
    }

    StructuredQuery.Filter encodeCompositeFilter(CompositeFilter compositeFilter) {
        List<StructuredQuery.Filter> protos = new ArrayList<>(compositeFilter.getFilters().size());
        for (Filter filter : compositeFilter.getFilters()) {
            protos.add(encodeFilter(filter));
        }
        if (protos.size() == 1) {
            return protos.get(0);
        }
        StructuredQuery.CompositeFilter.Builder composite = StructuredQuery.CompositeFilter.newBuilder();
        composite.setOp(encodeCompositeFilterOperator(compositeFilter.getOperator()));
        composite.addAllFilters(protos);
        return StructuredQuery.Filter.newBuilder().setCompositeFilter(composite).build();
    }

    Filter decodeFilter(StructuredQuery.Filter proto) {
        switch (proto.getFilterTypeCase()) {
            case COMPOSITE_FILTER:
                return decodeCompositeFilter(proto.getCompositeFilter());
            case FIELD_FILTER:
                return decodeFieldFilter(proto.getFieldFilter());
            case UNARY_FILTER:
                return decodeUnaryFilter(proto.getUnaryFilter());
            default:
                throw Assert.fail("Unrecognized Filter.filterType %d", proto.getFilterTypeCase());
        }
    }

    FieldFilter decodeFieldFilter(StructuredQuery.FieldFilter proto) {
        FieldPath fieldPath = FieldPath.fromServerFormat(proto.getField().getFieldPath());
        FieldFilter.Operator filterOperator = decodeFieldFilterOperator(proto.getOp());
        return FieldFilter.create(fieldPath, filterOperator, proto.getValue());
    }

    private Filter decodeUnaryFilter(StructuredQuery.UnaryFilter proto) {
        FieldPath fieldPath = FieldPath.fromServerFormat(proto.getField().getFieldPath());
        switch (proto.getOp()) {
            case IS_NAN:
                return FieldFilter.create(fieldPath, FieldFilter.Operator.EQUAL, Values.NAN_VALUE);
            case IS_NULL:
                return FieldFilter.create(fieldPath, FieldFilter.Operator.EQUAL, Values.NULL_VALUE);
            case IS_NOT_NAN:
                return FieldFilter.create(fieldPath, FieldFilter.Operator.NOT_EQUAL, Values.NAN_VALUE);
            case IS_NOT_NULL:
                return FieldFilter.create(fieldPath, FieldFilter.Operator.NOT_EQUAL, Values.NULL_VALUE);
            default:
                throw Assert.fail("Unrecognized UnaryFilter.operator %d", proto.getOp());
        }
    }

    CompositeFilter decodeCompositeFilter(StructuredQuery.CompositeFilter compositeFilter) {
        List<Filter> filters = new ArrayList<>();
        for (StructuredQuery.Filter filter : compositeFilter.getFiltersList()) {
            filters.add(decodeFilter(filter));
        }
        return new CompositeFilter(filters, decodeCompositeFilterOperator(compositeFilter.getOp()));
    }

    private StructuredQuery.FieldReference encodeFieldPath(FieldPath field) {
        return StructuredQuery.FieldReference.newBuilder().setFieldPath(field.canonicalString()).build();
    }

    private StructuredQuery.FieldFilter.Operator encodeFieldFilterOperator(FieldFilter.Operator operator) {
        switch (operator) {
            case LESS_THAN:
                return StructuredQuery.FieldFilter.Operator.LESS_THAN;
            case LESS_THAN_OR_EQUAL:
                return StructuredQuery.FieldFilter.Operator.LESS_THAN_OR_EQUAL;
            case EQUAL:
                return StructuredQuery.FieldFilter.Operator.EQUAL;
            case NOT_EQUAL:
                return StructuredQuery.FieldFilter.Operator.NOT_EQUAL;
            case GREATER_THAN:
                return StructuredQuery.FieldFilter.Operator.GREATER_THAN;
            case GREATER_THAN_OR_EQUAL:
                return StructuredQuery.FieldFilter.Operator.GREATER_THAN_OR_EQUAL;
            case ARRAY_CONTAINS:
                return StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS;
            case IN:
                return StructuredQuery.FieldFilter.Operator.IN;
            case ARRAY_CONTAINS_ANY:
                return StructuredQuery.FieldFilter.Operator.ARRAY_CONTAINS_ANY;
            case NOT_IN:
                return StructuredQuery.FieldFilter.Operator.NOT_IN;
            default:
                throw Assert.fail("Unknown operator %d", operator);
        }
    }

    private FieldFilter.Operator decodeFieldFilterOperator(StructuredQuery.FieldFilter.Operator operator) {
        switch (operator) {
            case LESS_THAN:
                return FieldFilter.Operator.LESS_THAN;
            case LESS_THAN_OR_EQUAL:
                return FieldFilter.Operator.LESS_THAN_OR_EQUAL;
            case EQUAL:
                return FieldFilter.Operator.EQUAL;
            case NOT_EQUAL:
                return FieldFilter.Operator.NOT_EQUAL;
            case GREATER_THAN_OR_EQUAL:
                return FieldFilter.Operator.GREATER_THAN_OR_EQUAL;
            case GREATER_THAN:
                return FieldFilter.Operator.GREATER_THAN;
            case ARRAY_CONTAINS:
                return FieldFilter.Operator.ARRAY_CONTAINS;
            case IN:
                return FieldFilter.Operator.IN;
            case ARRAY_CONTAINS_ANY:
                return FieldFilter.Operator.ARRAY_CONTAINS_ANY;
            case NOT_IN:
                return FieldFilter.Operator.NOT_IN;
            default:
                throw Assert.fail("Unhandled FieldFilter.operator %d", operator);
        }
    }

    private StructuredQuery.Order encodeOrderBy(OrderBy orderBy) {
        StructuredQuery.Order.Builder proto = StructuredQuery.Order.newBuilder();
        if (orderBy.getDirection().equals(OrderBy.Direction.ASCENDING)) {
            proto.setDirection(StructuredQuery.Direction.ASCENDING);
        } else {
            proto.setDirection(StructuredQuery.Direction.DESCENDING);
        }
        proto.setField(encodeFieldPath(orderBy.getField()));
        return proto.build();
    }

    private OrderBy decodeOrderBy(StructuredQuery.Order proto) {
        OrderBy.Direction direction;
        FieldPath fieldPath = FieldPath.fromServerFormat(proto.getField().getFieldPath());
        switch (proto.getDirection()) {
            case ASCENDING:
                direction = OrderBy.Direction.ASCENDING;
                break;
            case DESCENDING:
                direction = OrderBy.Direction.DESCENDING;
                break;
            default:
                throw Assert.fail("Unrecognized direction %d", proto.getDirection());
        }
        return OrderBy.getInstance(direction, fieldPath);
    }

    public WatchChange decodeWatchChange(ListenResponse protoChange) {
        WatchChange.WatchTargetChangeType changeType;
        switch (protoChange.getResponseTypeCase()) {
            case TARGET_CHANGE:
                com.google.firestore.v1.TargetChange targetChange = protoChange.getTargetChange();
                Status cause = null;
                switch (targetChange.getTargetChangeType()) {
                    case NO_CHANGE:
                        changeType = WatchChange.WatchTargetChangeType.NoChange;
                        break;
                    case ADD:
                        changeType = WatchChange.WatchTargetChangeType.Added;
                        break;
                    case REMOVE:
                        changeType = WatchChange.WatchTargetChangeType.Removed;
                        cause = fromStatus(targetChange.getCause());
                        break;
                    case CURRENT:
                        changeType = WatchChange.WatchTargetChangeType.Current;
                        break;
                    case RESET:
                        changeType = WatchChange.WatchTargetChangeType.Reset;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown target change type");
                }
                WatchChange watchChange = new WatchChange.WatchTargetChange(changeType, targetChange.getTargetIdsList(), targetChange.getResumeToken(), cause);
                return watchChange;
            case DOCUMENT_CHANGE:
                DocumentChange docChange = protoChange.getDocumentChange();
                List<Integer> added = docChange.getTargetIdsList();
                List<Integer> removed = docChange.getRemovedTargetIdsList();
                DocumentKey key = decodeKey(docChange.getDocument().getName());
                SnapshotVersion version = decodeVersion(docChange.getDocument().getUpdateTime());
                Assert.hardAssert(!version.equals(SnapshotVersion.NONE), "Got a document change without an update time", new Object[0]);
                ObjectValue data = ObjectValue.fromMap(docChange.getDocument().getFieldsMap());
                MutableDocument document = MutableDocument.newFoundDocument(key, version, data);
                WatchChange watchChange2 = new WatchChange.DocumentChange(added, removed, document.getKey(), document);
                return watchChange2;
            case DOCUMENT_DELETE:
                DocumentDelete docDelete = protoChange.getDocumentDelete();
                List<Integer> removed2 = docDelete.getRemovedTargetIdsList();
                DocumentKey key2 = decodeKey(docDelete.getDocument());
                MutableDocument doc = MutableDocument.newNoDocument(key2, decodeVersion(docDelete.getReadTime()));
                WatchChange watchChange3 = new WatchChange.DocumentChange(Collections.emptyList(), removed2, doc.getKey(), doc);
                return watchChange3;
            case DOCUMENT_REMOVE:
                DocumentRemove docRemove = protoChange.getDocumentRemove();
                List<Integer> removed3 = docRemove.getRemovedTargetIdsList();
                DocumentKey key3 = decodeKey(docRemove.getDocument());
                WatchChange watchChange4 = new WatchChange.DocumentChange(Collections.emptyList(), removed3, key3, null);
                return watchChange4;
            case FILTER:
                com.google.firestore.v1.ExistenceFilter protoFilter = protoChange.getFilter();
                ExistenceFilter filter = new ExistenceFilter(protoFilter.getCount(), protoFilter.getUnchangedNames());
                int targetId = protoFilter.getTargetId();
                WatchChange watchChange5 = new WatchChange.ExistenceFilterWatchChange(targetId, filter);
                return watchChange5;
            default:
                throw new IllegalArgumentException("Unknown change type set");
        }
    }

    public SnapshotVersion decodeVersionFromListenResponse(ListenResponse watchChange) {
        if (watchChange.getResponseTypeCase() != ListenResponse.ResponseTypeCase.TARGET_CHANGE) {
            return SnapshotVersion.NONE;
        }
        if (watchChange.getTargetChange().getTargetIdsCount() != 0) {
            return SnapshotVersion.NONE;
        }
        return decodeVersion(watchChange.getTargetChange().getReadTime());
    }

    private Status fromStatus(com.google.rpc.Status status) {
        return Status.fromCodeValue(status.getCode()).withDescription(status.getMessage());
    }
}
