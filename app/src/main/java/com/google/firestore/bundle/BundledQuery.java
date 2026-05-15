package com.google.firestore.bundle;

import com.google.firestore.v1.StructuredQuery;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes10.dex */
public final class BundledQuery extends GeneratedMessageLite<BundledQuery, Builder> implements BundledQueryOrBuilder {
    private static final BundledQuery DEFAULT_INSTANCE;
    public static final int LIMIT_TYPE_FIELD_NUMBER = 3;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static volatile Parser<BundledQuery> PARSER = null;
    public static final int STRUCTURED_QUERY_FIELD_NUMBER = 2;
    private int limitType_;
    private Object queryType_;
    private int queryTypeCase_ = 0;
    private String parent_ = "";

    private BundledQuery() {
    }

    public enum LimitType implements Internal.EnumLite {
        FIRST(0),
        LAST(1),
        UNRECOGNIZED(-1);

        public static final int FIRST_VALUE = 0;
        public static final int LAST_VALUE = 1;
        private static final Internal.EnumLiteMap<LimitType> internalValueMap = new Internal.EnumLiteMap<LimitType>() { // from class: com.google.firestore.bundle.BundledQuery.LimitType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LimitType findValueByNumber(int number) {
                return LimitType.forNumber(number);
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
        public static LimitType valueOf(int value) {
            return forNumber(value);
        }

        public static LimitType forNumber(int value) {
            switch (value) {
                case 0:
                    return FIRST;
                case 1:
                    return LAST;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<LimitType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return LimitTypeVerifier.INSTANCE;
        }

        private static final class LimitTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new LimitTypeVerifier();

            private LimitTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return LimitType.forNumber(number) != null;
            }
        }

        LimitType(int value) {
            this.value = value;
        }
    }

    public enum QueryTypeCase {
        STRUCTURED_QUERY(2),
        QUERYTYPE_NOT_SET(0);

        private final int value;

        QueryTypeCase(int value) {
            this.value = value;
        }

        @Deprecated
        public static QueryTypeCase valueOf(int value) {
            return forNumber(value);
        }

        public static QueryTypeCase forNumber(int value) {
            switch (value) {
                case 0:
                    return QUERYTYPE_NOT_SET;
                case 1:
                default:
                    return null;
                case 2:
                    return STRUCTURED_QUERY;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public QueryTypeCase getQueryTypeCase() {
        return QueryTypeCase.forNumber(this.queryTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQueryType() {
        this.queryTypeCase_ = 0;
        this.queryType_ = null;
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public String getParent() {
        return this.parent_;
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public ByteString getParentBytes() {
        return ByteString.copyFromUtf8(this.parent_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParent(String value) {
        value.getClass();
        this.parent_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearParent() {
        this.parent_ = getDefaultInstance().getParent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParentBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.parent_ = value.toStringUtf8();
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public boolean hasStructuredQuery() {
        return this.queryTypeCase_ == 2;
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public StructuredQuery getStructuredQuery() {
        if (this.queryTypeCase_ == 2) {
            return (StructuredQuery) this.queryType_;
        }
        return StructuredQuery.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStructuredQuery(StructuredQuery value) {
        value.getClass();
        this.queryType_ = value;
        this.queryTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStructuredQuery(StructuredQuery value) {
        value.getClass();
        if (this.queryTypeCase_ == 2 && this.queryType_ != StructuredQuery.getDefaultInstance()) {
            this.queryType_ = StructuredQuery.newBuilder((StructuredQuery) this.queryType_).mergeFrom(value).buildPartial();
        } else {
            this.queryType_ = value;
        }
        this.queryTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStructuredQuery() {
        if (this.queryTypeCase_ == 2) {
            this.queryTypeCase_ = 0;
            this.queryType_ = null;
        }
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public int getLimitTypeValue() {
        return this.limitType_;
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public LimitType getLimitType() {
        LimitType result = LimitType.forNumber(this.limitType_);
        return result == null ? LimitType.UNRECOGNIZED : result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLimitTypeValue(int value) {
        this.limitType_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLimitType(LimitType value) {
        this.limitType_ = value.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLimitType() {
        this.limitType_ = 0;
    }

    public static BundledQuery parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundledQuery parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundledQuery parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundledQuery parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundledQuery parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundledQuery parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundledQuery parseFrom(InputStream input) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundledQuery parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundledQuery parseDelimitedFrom(InputStream input) throws IOException {
        return (BundledQuery) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BundledQuery parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundledQuery) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundledQuery parseFrom(CodedInputStream input) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundledQuery parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BundledQuery prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BundledQuery, Builder> implements BundledQueryOrBuilder {
        private Builder() {
            super(BundledQuery.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public QueryTypeCase getQueryTypeCase() {
            return ((BundledQuery) this.instance).getQueryTypeCase();
        }

        public Builder clearQueryType() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearQueryType();
            return this;
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public String getParent() {
            return ((BundledQuery) this.instance).getParent();
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public ByteString getParentBytes() {
            return ((BundledQuery) this.instance).getParentBytes();
        }

        public Builder setParent(String value) {
            copyOnWrite();
            ((BundledQuery) this.instance).setParent(value);
            return this;
        }

        public Builder clearParent() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearParent();
            return this;
        }

        public Builder setParentBytes(ByteString value) {
            copyOnWrite();
            ((BundledQuery) this.instance).setParentBytes(value);
            return this;
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public boolean hasStructuredQuery() {
            return ((BundledQuery) this.instance).hasStructuredQuery();
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public StructuredQuery getStructuredQuery() {
            return ((BundledQuery) this.instance).getStructuredQuery();
        }

        public Builder setStructuredQuery(StructuredQuery value) {
            copyOnWrite();
            ((BundledQuery) this.instance).setStructuredQuery(value);
            return this;
        }

        public Builder setStructuredQuery(StructuredQuery.Builder builderForValue) {
            copyOnWrite();
            ((BundledQuery) this.instance).setStructuredQuery(builderForValue.build());
            return this;
        }

        public Builder mergeStructuredQuery(StructuredQuery value) {
            copyOnWrite();
            ((BundledQuery) this.instance).mergeStructuredQuery(value);
            return this;
        }

        public Builder clearStructuredQuery() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearStructuredQuery();
            return this;
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public int getLimitTypeValue() {
            return ((BundledQuery) this.instance).getLimitTypeValue();
        }

        public Builder setLimitTypeValue(int value) {
            copyOnWrite();
            ((BundledQuery) this.instance).setLimitTypeValue(value);
            return this;
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public LimitType getLimitType() {
            return ((BundledQuery) this.instance).getLimitType();
        }

        public Builder setLimitType(LimitType value) {
            copyOnWrite();
            ((BundledQuery) this.instance).setLimitType(value);
            return this;
        }

        public Builder clearLimitType() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearLimitType();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BundledQuery();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"queryType_", "queryTypeCase_", "parent_", StructuredQuery.class, "limitType_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002<\u0000\u0003\f", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<BundledQuery> parser = PARSER;
                if (parser == null) {
                    synchronized (BundledQuery.class) {
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
        BundledQuery defaultInstance = new BundledQuery();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(BundledQuery.class, defaultInstance);
    }

    public static BundledQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundledQuery> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
