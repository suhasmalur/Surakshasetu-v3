package com.google.firestore.v1;

import com.google.firestore.v1.AggregationResult;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes10.dex */
public final class RunAggregationQueryResponse extends GeneratedMessageLite<RunAggregationQueryResponse, Builder> implements RunAggregationQueryResponseOrBuilder {
    private static final RunAggregationQueryResponse DEFAULT_INSTANCE;
    private static volatile Parser<RunAggregationQueryResponse> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 3;
    public static final int RESULT_FIELD_NUMBER = 1;
    public static final int TRANSACTION_FIELD_NUMBER = 2;
    private Timestamp readTime_;
    private AggregationResult result_;
    private ByteString transaction_ = ByteString.EMPTY;

    private RunAggregationQueryResponse() {
    }

    @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
    public boolean hasResult() {
        return this.result_ != null;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
    public AggregationResult getResult() {
        return this.result_ == null ? AggregationResult.getDefaultInstance() : this.result_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResult(AggregationResult value) {
        value.getClass();
        this.result_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeResult(AggregationResult value) {
        value.getClass();
        if (this.result_ != null && this.result_ != AggregationResult.getDefaultInstance()) {
            this.result_ = AggregationResult.newBuilder(this.result_).mergeFrom(value).buildPartial();
        } else {
            this.result_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResult() {
        this.result_ = null;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
    public ByteString getTransaction() {
        return this.transaction_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString value) {
        value.getClass();
        this.transaction_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        this.transaction_ = getDefaultInstance().getTransaction();
    }

    @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
    public Timestamp getReadTime() {
        return this.readTime_ == null ? Timestamp.getDefaultInstance() : this.readTime_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp value) {
        value.getClass();
        this.readTime_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadTime(Timestamp value) {
        value.getClass();
        if (this.readTime_ != null && this.readTime_ != Timestamp.getDefaultInstance()) {
            this.readTime_ = Timestamp.newBuilder(this.readTime_).mergeFrom(value).buildPartial();
        } else {
            this.readTime_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadTime() {
        this.readTime_ = null;
    }

    public static RunAggregationQueryResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RunAggregationQueryResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RunAggregationQueryResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RunAggregationQueryResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RunAggregationQueryResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RunAggregationQueryResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RunAggregationQueryResponse parseFrom(InputStream input) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RunAggregationQueryResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RunAggregationQueryResponse parseDelimitedFrom(InputStream input) throws IOException {
        return (RunAggregationQueryResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RunAggregationQueryResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RunAggregationQueryResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RunAggregationQueryResponse parseFrom(CodedInputStream input) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RunAggregationQueryResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RunAggregationQueryResponse prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RunAggregationQueryResponse, Builder> implements RunAggregationQueryResponseOrBuilder {
        private Builder() {
            super(RunAggregationQueryResponse.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
        public boolean hasResult() {
            return ((RunAggregationQueryResponse) this.instance).hasResult();
        }

        @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
        public AggregationResult getResult() {
            return ((RunAggregationQueryResponse) this.instance).getResult();
        }

        public Builder setResult(AggregationResult value) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setResult(value);
            return this;
        }

        public Builder setResult(AggregationResult.Builder builderForValue) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setResult(builderForValue.build());
            return this;
        }

        public Builder mergeResult(AggregationResult value) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).mergeResult(value);
            return this;
        }

        public Builder clearResult() {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).clearResult();
            return this;
        }

        @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
        public ByteString getTransaction() {
            return ((RunAggregationQueryResponse) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString value) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setTransaction(value);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
        public boolean hasReadTime() {
            return ((RunAggregationQueryResponse) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
        public Timestamp getReadTime() {
            return ((RunAggregationQueryResponse) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp value) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setReadTime(value);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builderForValue) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setReadTime(builderForValue.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp value) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).mergeReadTime(value);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).clearReadTime();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RunAggregationQueryResponse();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"result_", "transaction_", "readTime_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\n\u0003\t", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<RunAggregationQueryResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (RunAggregationQueryResponse.class) {
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
        RunAggregationQueryResponse defaultInstance = new RunAggregationQueryResponse();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(RunAggregationQueryResponse.class, defaultInstance);
    }

    public static RunAggregationQueryResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RunAggregationQueryResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
