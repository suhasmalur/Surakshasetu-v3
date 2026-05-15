package com.google.firestore.admin.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public final class Index extends GeneratedMessageLite<Index, Builder> implements IndexOrBuilder {
    private static final Index DEFAULT_INSTANCE;
    public static final int FIELDS_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<Index> PARSER = null;
    public static final int QUERY_SCOPE_FIELD_NUMBER = 2;
    public static final int STATE_FIELD_NUMBER = 4;
    private int queryScope_;
    private int state_;
    private String name_ = "";
    private Internal.ProtobufList<IndexField> fields_ = emptyProtobufList();

    public interface IndexFieldOrBuilder extends MessageLiteOrBuilder {
        IndexField.ArrayConfig getArrayConfig();

        int getArrayConfigValue();

        String getFieldPath();

        ByteString getFieldPathBytes();

        IndexField.Order getOrder();

        int getOrderValue();

        IndexField.ValueModeCase getValueModeCase();

        boolean hasArrayConfig();

        boolean hasOrder();
    }

    private Index() {
    }

    public enum QueryScope implements Internal.EnumLite {
        QUERY_SCOPE_UNSPECIFIED(0),
        COLLECTION(1),
        COLLECTION_GROUP(2),
        UNRECOGNIZED(-1);

        public static final int COLLECTION_GROUP_VALUE = 2;
        public static final int COLLECTION_VALUE = 1;
        public static final int QUERY_SCOPE_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<QueryScope> internalValueMap = new Internal.EnumLiteMap<QueryScope>() { // from class: com.google.firestore.admin.v1.Index.QueryScope.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public QueryScope findValueByNumber(int number) {
                return QueryScope.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static QueryScope valueOf(int value) {
            return forNumber(value);
        }

        public static QueryScope forNumber(int value) {
            switch (value) {
                case 0:
                    return QUERY_SCOPE_UNSPECIFIED;
                case 1:
                    return COLLECTION;
                case 2:
                    return COLLECTION_GROUP;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<QueryScope> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return QueryScopeVerifier.INSTANCE;
        }

        private static final class QueryScopeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new QueryScopeVerifier();

            private QueryScopeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return QueryScope.forNumber(number) != null;
            }
        }

        QueryScope(int value) {
            this.value = value;
        }
    }

    public enum State implements Internal.EnumLite {
        STATE_UNSPECIFIED(0),
        CREATING(1),
        READY(2),
        NEEDS_REPAIR(3),
        UNRECOGNIZED(-1);

        public static final int CREATING_VALUE = 1;
        public static final int NEEDS_REPAIR_VALUE = 3;
        public static final int READY_VALUE = 2;
        public static final int STATE_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() { // from class: com.google.firestore.admin.v1.Index.State.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public State findValueByNumber(int number) {
                return State.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static State valueOf(int value) {
            return forNumber(value);
        }

        public static State forNumber(int value) {
            switch (value) {
                case 0:
                    return STATE_UNSPECIFIED;
                case 1:
                    return CREATING;
                case 2:
                    return READY;
                case 3:
                    return NEEDS_REPAIR;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<State> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return StateVerifier.INSTANCE;
        }

        private static final class StateVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new StateVerifier();

            private StateVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return State.forNumber(number) != null;
            }
        }

        State(int value) {
            this.value = value;
        }
    }

    public static final class IndexField extends GeneratedMessageLite<IndexField, Builder> implements IndexFieldOrBuilder {
        public static final int ARRAY_CONFIG_FIELD_NUMBER = 3;
        private static final IndexField DEFAULT_INSTANCE;
        public static final int FIELD_PATH_FIELD_NUMBER = 1;
        public static final int ORDER_FIELD_NUMBER = 2;
        private static volatile Parser<IndexField> PARSER;
        private Object valueMode_;
        private int valueModeCase_ = 0;
        private String fieldPath_ = "";

        private IndexField() {
        }

        public enum Order implements Internal.EnumLite {
            ORDER_UNSPECIFIED(0),
            ASCENDING(1),
            DESCENDING(2),
            UNRECOGNIZED(-1);

            public static final int ASCENDING_VALUE = 1;
            public static final int DESCENDING_VALUE = 2;
            public static final int ORDER_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<Order> internalValueMap = new Internal.EnumLiteMap<Order>() { // from class: com.google.firestore.admin.v1.Index.IndexField.Order.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Order findValueByNumber(int number) {
                    return Order.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                return this.value;
            }

            @Deprecated
            public static Order valueOf(int value) {
                return forNumber(value);
            }

            public static Order forNumber(int value) {
                switch (value) {
                    case 0:
                        return ORDER_UNSPECIFIED;
                    case 1:
                        return ASCENDING;
                    case 2:
                        return DESCENDING;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Order> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OrderVerifier.INSTANCE;
            }

            private static final class OrderVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new OrderVerifier();

                private OrderVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int number) {
                    return Order.forNumber(number) != null;
                }
            }

            Order(int value) {
                this.value = value;
            }
        }

        public enum ArrayConfig implements Internal.EnumLite {
            ARRAY_CONFIG_UNSPECIFIED(0),
            CONTAINS(1),
            UNRECOGNIZED(-1);

            public static final int ARRAY_CONFIG_UNSPECIFIED_VALUE = 0;
            public static final int CONTAINS_VALUE = 1;
            private static final Internal.EnumLiteMap<ArrayConfig> internalValueMap = new Internal.EnumLiteMap<ArrayConfig>() { // from class: com.google.firestore.admin.v1.Index.IndexField.ArrayConfig.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ArrayConfig findValueByNumber(int number) {
                    return ArrayConfig.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                return this.value;
            }

            @Deprecated
            public static ArrayConfig valueOf(int value) {
                return forNumber(value);
            }

            public static ArrayConfig forNumber(int value) {
                switch (value) {
                    case 0:
                        return ARRAY_CONFIG_UNSPECIFIED;
                    case 1:
                        return CONTAINS;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<ArrayConfig> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ArrayConfigVerifier.INSTANCE;
            }

            private static final class ArrayConfigVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new ArrayConfigVerifier();

                private ArrayConfigVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int number) {
                    return ArrayConfig.forNumber(number) != null;
                }
            }

            ArrayConfig(int value) {
                this.value = value;
            }
        }

        public enum ValueModeCase {
            ORDER(2),
            ARRAY_CONFIG(3),
            VALUEMODE_NOT_SET(0);

            private final int value;

            ValueModeCase(int value) {
                this.value = value;
            }

            @Deprecated
            public static ValueModeCase valueOf(int value) {
                return forNumber(value);
            }

            public static ValueModeCase forNumber(int value) {
                switch (value) {
                    case 0:
                        return VALUEMODE_NOT_SET;
                    case 1:
                    default:
                        return null;
                    case 2:
                        return ORDER;
                    case 3:
                        return ARRAY_CONFIG;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
        public ValueModeCase getValueModeCase() {
            return ValueModeCase.forNumber(this.valueModeCase_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValueMode() {
            this.valueModeCase_ = 0;
            this.valueMode_ = null;
        }

        @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
        public String getFieldPath() {
            return this.fieldPath_;
        }

        @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
        public ByteString getFieldPathBytes() {
            return ByteString.copyFromUtf8(this.fieldPath_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFieldPath(String value) {
            value.getClass();
            this.fieldPath_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFieldPath() {
            this.fieldPath_ = getDefaultInstance().getFieldPath();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFieldPathBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.fieldPath_ = value.toStringUtf8();
        }

        @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
        public boolean hasOrder() {
            return this.valueModeCase_ == 2;
        }

        @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
        public int getOrderValue() {
            if (this.valueModeCase_ == 2) {
                return ((Integer) this.valueMode_).intValue();
            }
            return 0;
        }

        @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
        public Order getOrder() {
            if (this.valueModeCase_ == 2) {
                Order result = Order.forNumber(((Integer) this.valueMode_).intValue());
                return result == null ? Order.UNRECOGNIZED : result;
            }
            return Order.ORDER_UNSPECIFIED;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOrderValue(int value) {
            this.valueModeCase_ = 2;
            this.valueMode_ = Integer.valueOf(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOrder(Order value) {
            this.valueMode_ = Integer.valueOf(value.getNumber());
            this.valueModeCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOrder() {
            if (this.valueModeCase_ == 2) {
                this.valueModeCase_ = 0;
                this.valueMode_ = null;
            }
        }

        @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
        public boolean hasArrayConfig() {
            return this.valueModeCase_ == 3;
        }

        @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
        public int getArrayConfigValue() {
            if (this.valueModeCase_ == 3) {
                return ((Integer) this.valueMode_).intValue();
            }
            return 0;
        }

        @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
        public ArrayConfig getArrayConfig() {
            if (this.valueModeCase_ == 3) {
                ArrayConfig result = ArrayConfig.forNumber(((Integer) this.valueMode_).intValue());
                return result == null ? ArrayConfig.UNRECOGNIZED : result;
            }
            return ArrayConfig.ARRAY_CONFIG_UNSPECIFIED;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArrayConfigValue(int value) {
            this.valueModeCase_ = 3;
            this.valueMode_ = Integer.valueOf(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArrayConfig(ArrayConfig value) {
            this.valueMode_ = Integer.valueOf(value.getNumber());
            this.valueModeCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArrayConfig() {
            if (this.valueModeCase_ == 3) {
                this.valueModeCase_ = 0;
                this.valueMode_ = null;
            }
        }

        public static IndexField parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (IndexField) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static IndexField parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (IndexField) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static IndexField parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (IndexField) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static IndexField parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (IndexField) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static IndexField parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (IndexField) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static IndexField parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (IndexField) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static IndexField parseFrom(InputStream input) throws IOException {
            return (IndexField) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static IndexField parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IndexField) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static IndexField parseDelimitedFrom(InputStream input) throws IOException {
            return (IndexField) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static IndexField parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IndexField) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static IndexField parseFrom(CodedInputStream input) throws IOException {
            return (IndexField) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static IndexField parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (IndexField) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(IndexField prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<IndexField, Builder> implements IndexFieldOrBuilder {
            private Builder() {
                super(IndexField.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
            public ValueModeCase getValueModeCase() {
                return ((IndexField) this.instance).getValueModeCase();
            }

            public Builder clearValueMode() {
                copyOnWrite();
                ((IndexField) this.instance).clearValueMode();
                return this;
            }

            @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
            public String getFieldPath() {
                return ((IndexField) this.instance).getFieldPath();
            }

            @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
            public ByteString getFieldPathBytes() {
                return ((IndexField) this.instance).getFieldPathBytes();
            }

            public Builder setFieldPath(String value) {
                copyOnWrite();
                ((IndexField) this.instance).setFieldPath(value);
                return this;
            }

            public Builder clearFieldPath() {
                copyOnWrite();
                ((IndexField) this.instance).clearFieldPath();
                return this;
            }

            public Builder setFieldPathBytes(ByteString value) {
                copyOnWrite();
                ((IndexField) this.instance).setFieldPathBytes(value);
                return this;
            }

            @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
            public boolean hasOrder() {
                return ((IndexField) this.instance).hasOrder();
            }

            @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
            public int getOrderValue() {
                return ((IndexField) this.instance).getOrderValue();
            }

            public Builder setOrderValue(int value) {
                copyOnWrite();
                ((IndexField) this.instance).setOrderValue(value);
                return this;
            }

            @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
            public Order getOrder() {
                return ((IndexField) this.instance).getOrder();
            }

            public Builder setOrder(Order value) {
                copyOnWrite();
                ((IndexField) this.instance).setOrder(value);
                return this;
            }

            public Builder clearOrder() {
                copyOnWrite();
                ((IndexField) this.instance).clearOrder();
                return this;
            }

            @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
            public boolean hasArrayConfig() {
                return ((IndexField) this.instance).hasArrayConfig();
            }

            @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
            public int getArrayConfigValue() {
                return ((IndexField) this.instance).getArrayConfigValue();
            }

            public Builder setArrayConfigValue(int value) {
                copyOnWrite();
                ((IndexField) this.instance).setArrayConfigValue(value);
                return this;
            }

            @Override // com.google.firestore.admin.v1.Index.IndexFieldOrBuilder
            public ArrayConfig getArrayConfig() {
                return ((IndexField) this.instance).getArrayConfig();
            }

            public Builder setArrayConfig(ArrayConfig value) {
                copyOnWrite();
                ((IndexField) this.instance).setArrayConfig(value);
                return this;
            }

            public Builder clearArrayConfig() {
                copyOnWrite();
                ((IndexField) this.instance).clearArrayConfig();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new IndexField();
                case NEW_BUILDER:
                    return new Builder();
                case BUILD_MESSAGE_INFO:
                    Object[] objects = {"valueMode_", "valueModeCase_", "fieldPath_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002?\u0000\u0003?\u0000", objects);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<IndexField> parser = PARSER;
                    if (parser == null) {
                        synchronized (IndexField.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            IndexField defaultInstance = new IndexField();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(IndexField.class, defaultInstance);
        }

        public static IndexField getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<IndexField> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.google.firestore.admin.v1.IndexOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firestore.admin.v1.IndexOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String value) {
        value.getClass();
        this.name_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = getDefaultInstance().getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.name_ = value.toStringUtf8();
    }

    @Override // com.google.firestore.admin.v1.IndexOrBuilder
    public int getQueryScopeValue() {
        return this.queryScope_;
    }

    @Override // com.google.firestore.admin.v1.IndexOrBuilder
    public QueryScope getQueryScope() {
        QueryScope result = QueryScope.forNumber(this.queryScope_);
        return result == null ? QueryScope.UNRECOGNIZED : result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQueryScopeValue(int value) {
        this.queryScope_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQueryScope(QueryScope value) {
        this.queryScope_ = value.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQueryScope() {
        this.queryScope_ = 0;
    }

    @Override // com.google.firestore.admin.v1.IndexOrBuilder
    public List<IndexField> getFieldsList() {
        return this.fields_;
    }

    public List<? extends IndexFieldOrBuilder> getFieldsOrBuilderList() {
        return this.fields_;
    }

    @Override // com.google.firestore.admin.v1.IndexOrBuilder
    public int getFieldsCount() {
        return this.fields_.size();
    }

    @Override // com.google.firestore.admin.v1.IndexOrBuilder
    public IndexField getFields(int index) {
        return this.fields_.get(index);
    }

    public IndexFieldOrBuilder getFieldsOrBuilder(int index) {
        return this.fields_.get(index);
    }

    private void ensureFieldsIsMutable() {
        Internal.ProtobufList<IndexField> tmp = this.fields_;
        if (!tmp.isModifiable()) {
            this.fields_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFields(int index, IndexField value) {
        value.getClass();
        ensureFieldsIsMutable();
        this.fields_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFields(IndexField value) {
        value.getClass();
        ensureFieldsIsMutable();
        this.fields_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFields(int index, IndexField value) {
        value.getClass();
        ensureFieldsIsMutable();
        this.fields_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllFields(Iterable<? extends IndexField> values) {
        ensureFieldsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.fields_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFields() {
        this.fields_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeFields(int index) {
        ensureFieldsIsMutable();
        this.fields_.remove(index);
    }

    @Override // com.google.firestore.admin.v1.IndexOrBuilder
    public int getStateValue() {
        return this.state_;
    }

    @Override // com.google.firestore.admin.v1.IndexOrBuilder
    public State getState() {
        State result = State.forNumber(this.state_);
        return result == null ? State.UNRECOGNIZED : result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStateValue(int value) {
        this.state_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(State value) {
        this.state_ = value.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearState() {
        this.state_ = 0;
    }

    public static Index parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Index) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Index parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Index) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Index parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Index) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Index parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Index) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Index parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Index) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Index parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Index) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Index parseFrom(InputStream input) throws IOException {
        return (Index) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Index parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Index) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Index parseDelimitedFrom(InputStream input) throws IOException {
        return (Index) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Index parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Index) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Index parseFrom(CodedInputStream input) throws IOException {
        return (Index) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Index parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Index) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Index prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Index, Builder> implements IndexOrBuilder {
        private Builder() {
            super(Index.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.admin.v1.IndexOrBuilder
        public String getName() {
            return ((Index) this.instance).getName();
        }

        @Override // com.google.firestore.admin.v1.IndexOrBuilder
        public ByteString getNameBytes() {
            return ((Index) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((Index) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Index) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((Index) this.instance).setNameBytes(value);
            return this;
        }

        @Override // com.google.firestore.admin.v1.IndexOrBuilder
        public int getQueryScopeValue() {
            return ((Index) this.instance).getQueryScopeValue();
        }

        public Builder setQueryScopeValue(int value) {
            copyOnWrite();
            ((Index) this.instance).setQueryScopeValue(value);
            return this;
        }

        @Override // com.google.firestore.admin.v1.IndexOrBuilder
        public QueryScope getQueryScope() {
            return ((Index) this.instance).getQueryScope();
        }

        public Builder setQueryScope(QueryScope value) {
            copyOnWrite();
            ((Index) this.instance).setQueryScope(value);
            return this;
        }

        public Builder clearQueryScope() {
            copyOnWrite();
            ((Index) this.instance).clearQueryScope();
            return this;
        }

        @Override // com.google.firestore.admin.v1.IndexOrBuilder
        public List<IndexField> getFieldsList() {
            return Collections.unmodifiableList(((Index) this.instance).getFieldsList());
        }

        @Override // com.google.firestore.admin.v1.IndexOrBuilder
        public int getFieldsCount() {
            return ((Index) this.instance).getFieldsCount();
        }

        @Override // com.google.firestore.admin.v1.IndexOrBuilder
        public IndexField getFields(int index) {
            return ((Index) this.instance).getFields(index);
        }

        public Builder setFields(int index, IndexField value) {
            copyOnWrite();
            ((Index) this.instance).setFields(index, value);
            return this;
        }

        public Builder setFields(int index, IndexField.Builder builderForValue) {
            copyOnWrite();
            ((Index) this.instance).setFields(index, builderForValue.build());
            return this;
        }

        public Builder addFields(IndexField value) {
            copyOnWrite();
            ((Index) this.instance).addFields(value);
            return this;
        }

        public Builder addFields(int index, IndexField value) {
            copyOnWrite();
            ((Index) this.instance).addFields(index, value);
            return this;
        }

        public Builder addFields(IndexField.Builder builderForValue) {
            copyOnWrite();
            ((Index) this.instance).addFields(builderForValue.build());
            return this;
        }

        public Builder addFields(int index, IndexField.Builder builderForValue) {
            copyOnWrite();
            ((Index) this.instance).addFields(index, builderForValue.build());
            return this;
        }

        public Builder addAllFields(Iterable<? extends IndexField> values) {
            copyOnWrite();
            ((Index) this.instance).addAllFields(values);
            return this;
        }

        public Builder clearFields() {
            copyOnWrite();
            ((Index) this.instance).clearFields();
            return this;
        }

        public Builder removeFields(int index) {
            copyOnWrite();
            ((Index) this.instance).removeFields(index);
            return this;
        }

        @Override // com.google.firestore.admin.v1.IndexOrBuilder
        public int getStateValue() {
            return ((Index) this.instance).getStateValue();
        }

        public Builder setStateValue(int value) {
            copyOnWrite();
            ((Index) this.instance).setStateValue(value);
            return this;
        }

        @Override // com.google.firestore.admin.v1.IndexOrBuilder
        public State getState() {
            return ((Index) this.instance).getState();
        }

        public Builder setState(State value) {
            copyOnWrite();
            ((Index) this.instance).setState(value);
            return this;
        }

        public Builder clearState() {
            copyOnWrite();
            ((Index) this.instance).clearState();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new Index();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"name_", "queryScope_", "fields_", IndexField.class, "state_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001Ȉ\u0002\f\u0003\u001b\u0004\f", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Index> parser = PARSER;
                if (parser == null) {
                    synchronized (Index.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                        break;
                    }
                }
                return parser;
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        Index defaultInstance = new Index();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Index.class, defaultInstance);
    }

    public static Index getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Index> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
