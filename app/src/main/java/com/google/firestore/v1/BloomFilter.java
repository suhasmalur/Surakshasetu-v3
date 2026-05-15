package com.google.firestore.v1;

import com.google.firestore.v1.BitSequence;
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
public final class BloomFilter extends GeneratedMessageLite<BloomFilter, Builder> implements BloomFilterOrBuilder {
    public static final int BITS_FIELD_NUMBER = 1;
    private static final BloomFilter DEFAULT_INSTANCE;
    public static final int HASH_COUNT_FIELD_NUMBER = 2;
    private static volatile Parser<BloomFilter> PARSER;
    private BitSequence bits_;
    private int hashCount_;

    private BloomFilter() {
    }

    @Override // com.google.firestore.v1.BloomFilterOrBuilder
    public boolean hasBits() {
        return this.bits_ != null;
    }

    @Override // com.google.firestore.v1.BloomFilterOrBuilder
    public BitSequence getBits() {
        return this.bits_ == null ? BitSequence.getDefaultInstance() : this.bits_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBits(BitSequence value) {
        value.getClass();
        this.bits_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeBits(BitSequence value) {
        value.getClass();
        if (this.bits_ != null && this.bits_ != BitSequence.getDefaultInstance()) {
            this.bits_ = BitSequence.newBuilder(this.bits_).mergeFrom(value).buildPartial();
        } else {
            this.bits_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBits() {
        this.bits_ = null;
    }

    @Override // com.google.firestore.v1.BloomFilterOrBuilder
    public int getHashCount() {
        return this.hashCount_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHashCount(int value) {
        this.hashCount_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHashCount() {
        this.hashCount_ = 0;
    }

    public static BloomFilter parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BloomFilter parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BloomFilter parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BloomFilter parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BloomFilter parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BloomFilter parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BloomFilter parseFrom(InputStream input) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BloomFilter parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BloomFilter parseDelimitedFrom(InputStream input) throws IOException {
        return (BloomFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BloomFilter parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BloomFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BloomFilter parseFrom(CodedInputStream input) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BloomFilter parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BloomFilter prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BloomFilter, Builder> implements BloomFilterOrBuilder {
        private Builder() {
            super(BloomFilter.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.BloomFilterOrBuilder
        public boolean hasBits() {
            return ((BloomFilter) this.instance).hasBits();
        }

        @Override // com.google.firestore.v1.BloomFilterOrBuilder
        public BitSequence getBits() {
            return ((BloomFilter) this.instance).getBits();
        }

        public Builder setBits(BitSequence value) {
            copyOnWrite();
            ((BloomFilter) this.instance).setBits(value);
            return this;
        }

        public Builder setBits(BitSequence.Builder builderForValue) {
            copyOnWrite();
            ((BloomFilter) this.instance).setBits(builderForValue.build());
            return this;
        }

        public Builder mergeBits(BitSequence value) {
            copyOnWrite();
            ((BloomFilter) this.instance).mergeBits(value);
            return this;
        }

        public Builder clearBits() {
            copyOnWrite();
            ((BloomFilter) this.instance).clearBits();
            return this;
        }

        @Override // com.google.firestore.v1.BloomFilterOrBuilder
        public int getHashCount() {
            return ((BloomFilter) this.instance).getHashCount();
        }

        public Builder setHashCount(int value) {
            copyOnWrite();
            ((BloomFilter) this.instance).setHashCount(value);
            return this;
        }

        public Builder clearHashCount() {
            copyOnWrite();
            ((BloomFilter) this.instance).clearHashCount();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BloomFilter();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"bits_", "hashCount_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u0004", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<BloomFilter> parser = PARSER;
                if (parser == null) {
                    synchronized (BloomFilter.class) {
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
        BloomFilter defaultInstance = new BloomFilter();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(BloomFilter.class, defaultInstance);
    }

    public static BloomFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BloomFilter> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
