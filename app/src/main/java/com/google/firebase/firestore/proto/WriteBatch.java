package com.google.firebase.firestore.proto;

import com.google.firestore.v1.Write;
import com.google.firestore.v1.WriteOrBuilder;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public final class WriteBatch extends GeneratedMessageLite<WriteBatch, Builder> implements WriteBatchOrBuilder {
    public static final int BASE_WRITES_FIELD_NUMBER = 4;
    public static final int BATCH_ID_FIELD_NUMBER = 1;
    private static final WriteBatch DEFAULT_INSTANCE;
    public static final int LOCAL_WRITE_TIME_FIELD_NUMBER = 3;
    private static volatile Parser<WriteBatch> PARSER = null;
    public static final int WRITES_FIELD_NUMBER = 2;
    private int batchId_;
    private Timestamp localWriteTime_;
    private Internal.ProtobufList<Write> writes_ = emptyProtobufList();
    private Internal.ProtobufList<Write> baseWrites_ = emptyProtobufList();

    private WriteBatch() {
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public int getBatchId() {
        return this.batchId_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBatchId(int value) {
        this.batchId_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBatchId() {
        this.batchId_ = 0;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public List<Write> getWritesList() {
        return this.writes_;
    }

    public List<? extends WriteOrBuilder> getWritesOrBuilderList() {
        return this.writes_;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public int getWritesCount() {
        return this.writes_.size();
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public Write getWrites(int index) {
        return this.writes_.get(index);
    }

    public WriteOrBuilder getWritesOrBuilder(int index) {
        return this.writes_.get(index);
    }

    private void ensureWritesIsMutable() {
        Internal.ProtobufList<Write> tmp = this.writes_;
        if (!tmp.isModifiable()) {
            this.writes_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWrites(int index, Write value) {
        value.getClass();
        ensureWritesIsMutable();
        this.writes_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWrites(Write value) {
        value.getClass();
        ensureWritesIsMutable();
        this.writes_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWrites(int index, Write value) {
        value.getClass();
        ensureWritesIsMutable();
        this.writes_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllWrites(Iterable<? extends Write> values) {
        ensureWritesIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.writes_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearWrites() {
        this.writes_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeWrites(int index) {
        ensureWritesIsMutable();
        this.writes_.remove(index);
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public boolean hasLocalWriteTime() {
        return this.localWriteTime_ != null;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public Timestamp getLocalWriteTime() {
        return this.localWriteTime_ == null ? Timestamp.getDefaultInstance() : this.localWriteTime_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocalWriteTime(Timestamp value) {
        value.getClass();
        this.localWriteTime_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLocalWriteTime(Timestamp value) {
        value.getClass();
        if (this.localWriteTime_ != null && this.localWriteTime_ != Timestamp.getDefaultInstance()) {
            this.localWriteTime_ = Timestamp.newBuilder(this.localWriteTime_).mergeFrom(value).buildPartial();
        } else {
            this.localWriteTime_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLocalWriteTime() {
        this.localWriteTime_ = null;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public List<Write> getBaseWritesList() {
        return this.baseWrites_;
    }

    public List<? extends WriteOrBuilder> getBaseWritesOrBuilderList() {
        return this.baseWrites_;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public int getBaseWritesCount() {
        return this.baseWrites_.size();
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public Write getBaseWrites(int index) {
        return this.baseWrites_.get(index);
    }

    public WriteOrBuilder getBaseWritesOrBuilder(int index) {
        return this.baseWrites_.get(index);
    }

    private void ensureBaseWritesIsMutable() {
        Internal.ProtobufList<Write> tmp = this.baseWrites_;
        if (!tmp.isModifiable()) {
            this.baseWrites_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBaseWrites(int index, Write value) {
        value.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBaseWrites(Write value) {
        value.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBaseWrites(int index, Write value) {
        value.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllBaseWrites(Iterable<? extends Write> values) {
        ensureBaseWritesIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.baseWrites_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBaseWrites() {
        this.baseWrites_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeBaseWrites(int index) {
        ensureBaseWritesIsMutable();
        this.baseWrites_.remove(index);
    }

    public static WriteBatch parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WriteBatch parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WriteBatch parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WriteBatch parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WriteBatch parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WriteBatch parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WriteBatch parseFrom(InputStream input) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WriteBatch parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WriteBatch parseDelimitedFrom(InputStream input) throws IOException {
        return (WriteBatch) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WriteBatch parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WriteBatch) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WriteBatch parseFrom(CodedInputStream input) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WriteBatch parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(WriteBatch prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WriteBatch, Builder> implements WriteBatchOrBuilder {
        private Builder() {
            super(WriteBatch.DEFAULT_INSTANCE);
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public int getBatchId() {
            return ((WriteBatch) this.instance).getBatchId();
        }

        public Builder setBatchId(int value) {
            copyOnWrite();
            ((WriteBatch) this.instance).setBatchId(value);
            return this;
        }

        public Builder clearBatchId() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearBatchId();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public List<Write> getWritesList() {
            return Collections.unmodifiableList(((WriteBatch) this.instance).getWritesList());
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public int getWritesCount() {
            return ((WriteBatch) this.instance).getWritesCount();
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public Write getWrites(int index) {
            return ((WriteBatch) this.instance).getWrites(index);
        }

        public Builder setWrites(int index, Write value) {
            copyOnWrite();
            ((WriteBatch) this.instance).setWrites(index, value);
            return this;
        }

        public Builder setWrites(int index, Write.Builder builderForValue) {
            copyOnWrite();
            ((WriteBatch) this.instance).setWrites(index, builderForValue.build());
            return this;
        }

        public Builder addWrites(Write value) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(value);
            return this;
        }

        public Builder addWrites(int index, Write value) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(index, value);
            return this;
        }

        public Builder addWrites(Write.Builder builderForValue) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(builderForValue.build());
            return this;
        }

        public Builder addWrites(int index, Write.Builder builderForValue) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(index, builderForValue.build());
            return this;
        }

        public Builder addAllWrites(Iterable<? extends Write> values) {
            copyOnWrite();
            ((WriteBatch) this.instance).addAllWrites(values);
            return this;
        }

        public Builder clearWrites() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearWrites();
            return this;
        }

        public Builder removeWrites(int index) {
            copyOnWrite();
            ((WriteBatch) this.instance).removeWrites(index);
            return this;
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public boolean hasLocalWriteTime() {
            return ((WriteBatch) this.instance).hasLocalWriteTime();
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public Timestamp getLocalWriteTime() {
            return ((WriteBatch) this.instance).getLocalWriteTime();
        }

        public Builder setLocalWriteTime(Timestamp value) {
            copyOnWrite();
            ((WriteBatch) this.instance).setLocalWriteTime(value);
            return this;
        }

        public Builder setLocalWriteTime(Timestamp.Builder builderForValue) {
            copyOnWrite();
            ((WriteBatch) this.instance).setLocalWriteTime(builderForValue.build());
            return this;
        }

        public Builder mergeLocalWriteTime(Timestamp value) {
            copyOnWrite();
            ((WriteBatch) this.instance).mergeLocalWriteTime(value);
            return this;
        }

        public Builder clearLocalWriteTime() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearLocalWriteTime();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public List<Write> getBaseWritesList() {
            return Collections.unmodifiableList(((WriteBatch) this.instance).getBaseWritesList());
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public int getBaseWritesCount() {
            return ((WriteBatch) this.instance).getBaseWritesCount();
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public Write getBaseWrites(int index) {
            return ((WriteBatch) this.instance).getBaseWrites(index);
        }

        public Builder setBaseWrites(int index, Write value) {
            copyOnWrite();
            ((WriteBatch) this.instance).setBaseWrites(index, value);
            return this;
        }

        public Builder setBaseWrites(int index, Write.Builder builderForValue) {
            copyOnWrite();
            ((WriteBatch) this.instance).setBaseWrites(index, builderForValue.build());
            return this;
        }

        public Builder addBaseWrites(Write value) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(value);
            return this;
        }

        public Builder addBaseWrites(int index, Write value) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(index, value);
            return this;
        }

        public Builder addBaseWrites(Write.Builder builderForValue) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(builderForValue.build());
            return this;
        }

        public Builder addBaseWrites(int index, Write.Builder builderForValue) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(index, builderForValue.build());
            return this;
        }

        public Builder addAllBaseWrites(Iterable<? extends Write> values) {
            copyOnWrite();
            ((WriteBatch) this.instance).addAllBaseWrites(values);
            return this;
        }

        public Builder clearBaseWrites() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearBaseWrites();
            return this;
        }

        public Builder removeBaseWrites(int index) {
            copyOnWrite();
            ((WriteBatch) this.instance).removeBaseWrites(index);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WriteBatch();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"batchId_", "writes_", Write.class, "localWriteTime_", "baseWrites_", Write.class};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0002\u0000\u0001\u0004\u0002\u001b\u0003\t\u0004\u001b", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<WriteBatch> parser = PARSER;
                if (parser == null) {
                    synchronized (WriteBatch.class) {
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
        WriteBatch defaultInstance = new WriteBatch();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(WriteBatch.class, defaultInstance);
    }

    public static WriteBatch getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WriteBatch> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
