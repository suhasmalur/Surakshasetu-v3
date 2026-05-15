package com.google.firestore.v1;

import com.google.firestore.v1.BloomFilter;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes10.dex */
public final class ExistenceFilter extends GeneratedMessageLite<ExistenceFilter, Builder> implements ExistenceFilterOrBuilder {
    public static final int COUNT_FIELD_NUMBER = 2;
    private static final ExistenceFilter DEFAULT_INSTANCE;
    private static volatile Parser<ExistenceFilter> PARSER = null;
    public static final int TARGET_ID_FIELD_NUMBER = 1;
    public static final int UNCHANGED_NAMES_FIELD_NUMBER = 3;
    private int count_;
    private int targetId_;
    private BloomFilter unchangedNames_;

    private ExistenceFilter() {
    }

    @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
    public int getTargetId() {
        return this.targetId_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetId(int value) {
        this.targetId_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetId() {
        this.targetId_ = 0;
    }

    @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
    public int getCount() {
        return this.count_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCount(int value) {
        this.count_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCount() {
        this.count_ = 0;
    }

    @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
    public boolean hasUnchangedNames() {
        return this.unchangedNames_ != null;
    }

    @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
    public BloomFilter getUnchangedNames() {
        return this.unchangedNames_ == null ? BloomFilter.getDefaultInstance() : this.unchangedNames_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUnchangedNames(BloomFilter value) {
        value.getClass();
        this.unchangedNames_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUnchangedNames(BloomFilter value) {
        value.getClass();
        if (this.unchangedNames_ != null && this.unchangedNames_ != BloomFilter.getDefaultInstance()) {
            this.unchangedNames_ = BloomFilter.newBuilder(this.unchangedNames_).mergeFrom(value).buildPartial();
        } else {
            this.unchangedNames_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUnchangedNames() {
        this.unchangedNames_ = null;
    }

    public static ExistenceFilter parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ExistenceFilter parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ExistenceFilter parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ExistenceFilter parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ExistenceFilter parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ExistenceFilter parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ExistenceFilter parseFrom(InputStream input) throws IOException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ExistenceFilter parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ExistenceFilter parseDelimitedFrom(InputStream input) throws IOException {
        return (ExistenceFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ExistenceFilter parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ExistenceFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ExistenceFilter parseFrom(CodedInputStream input) throws IOException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ExistenceFilter parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ExistenceFilter prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ExistenceFilter, Builder> implements ExistenceFilterOrBuilder {
        private Builder() {
            super(ExistenceFilter.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
        public int getTargetId() {
            return ((ExistenceFilter) this.instance).getTargetId();
        }

        public Builder setTargetId(int value) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).setTargetId(value);
            return this;
        }

        public Builder clearTargetId() {
            copyOnWrite();
            ((ExistenceFilter) this.instance).clearTargetId();
            return this;
        }

        @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
        public int getCount() {
            return ((ExistenceFilter) this.instance).getCount();
        }

        public Builder setCount(int value) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).setCount(value);
            return this;
        }

        public Builder clearCount() {
            copyOnWrite();
            ((ExistenceFilter) this.instance).clearCount();
            return this;
        }

        @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
        public boolean hasUnchangedNames() {
            return ((ExistenceFilter) this.instance).hasUnchangedNames();
        }

        @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
        public BloomFilter getUnchangedNames() {
            return ((ExistenceFilter) this.instance).getUnchangedNames();
        }

        public Builder setUnchangedNames(BloomFilter value) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).setUnchangedNames(value);
            return this;
        }

        public Builder setUnchangedNames(BloomFilter.Builder builderForValue) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).setUnchangedNames(builderForValue.build());
            return this;
        }

        public Builder mergeUnchangedNames(BloomFilter value) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).mergeUnchangedNames(value);
            return this;
        }

        public Builder clearUnchangedNames() {
            copyOnWrite();
            ((ExistenceFilter) this.instance).clearUnchangedNames();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ExistenceFilter();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"targetId_", "count_", "unchangedNames_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0002\u0004\u0003\t", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ExistenceFilter> parser = PARSER;
                if (parser == null) {
                    synchronized (ExistenceFilter.class) {
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
        ExistenceFilter defaultInstance = new ExistenceFilter();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(ExistenceFilter.class, defaultInstance);
    }

    public static ExistenceFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ExistenceFilter> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
