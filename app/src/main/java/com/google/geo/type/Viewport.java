package com.google.geo.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.type.LatLng;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes10.dex */
public final class Viewport extends GeneratedMessageLite<Viewport, Builder> implements ViewportOrBuilder {
    private static final Viewport DEFAULT_INSTANCE;
    public static final int HIGH_FIELD_NUMBER = 2;
    public static final int LOW_FIELD_NUMBER = 1;
    private static volatile Parser<Viewport> PARSER;
    private LatLng high_;
    private LatLng low_;

    private Viewport() {
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public boolean hasLow() {
        return this.low_ != null;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLng getLow() {
        return this.low_ == null ? LatLng.getDefaultInstance() : this.low_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLow(LatLng value) {
        value.getClass();
        this.low_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLow(LatLng value) {
        value.getClass();
        if (this.low_ != null && this.low_ != LatLng.getDefaultInstance()) {
            this.low_ = LatLng.newBuilder(this.low_).mergeFrom(value).buildPartial();
        } else {
            this.low_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLow() {
        this.low_ = null;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public boolean hasHigh() {
        return this.high_ != null;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLng getHigh() {
        return this.high_ == null ? LatLng.getDefaultInstance() : this.high_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHigh(LatLng value) {
        value.getClass();
        this.high_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeHigh(LatLng value) {
        value.getClass();
        if (this.high_ != null && this.high_ != LatLng.getDefaultInstance()) {
            this.high_ = LatLng.newBuilder(this.high_).mergeFrom(value).buildPartial();
        } else {
            this.high_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHigh() {
        this.high_ = null;
    }

    public static Viewport parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Viewport parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Viewport parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Viewport parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Viewport parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Viewport parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Viewport parseFrom(InputStream input) throws IOException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Viewport parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Viewport parseDelimitedFrom(InputStream input) throws IOException {
        return (Viewport) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Viewport parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Viewport) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Viewport parseFrom(CodedInputStream input) throws IOException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Viewport parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Viewport) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Viewport prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Viewport, Builder> implements ViewportOrBuilder {
        private Builder() {
            super(Viewport.DEFAULT_INSTANCE);
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public boolean hasLow() {
            return ((Viewport) this.instance).hasLow();
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLng getLow() {
            return ((Viewport) this.instance).getLow();
        }

        public Builder setLow(LatLng value) {
            copyOnWrite();
            ((Viewport) this.instance).setLow(value);
            return this;
        }

        public Builder setLow(LatLng.Builder builderForValue) {
            copyOnWrite();
            ((Viewport) this.instance).setLow(builderForValue.build());
            return this;
        }

        public Builder mergeLow(LatLng value) {
            copyOnWrite();
            ((Viewport) this.instance).mergeLow(value);
            return this;
        }

        public Builder clearLow() {
            copyOnWrite();
            ((Viewport) this.instance).clearLow();
            return this;
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public boolean hasHigh() {
            return ((Viewport) this.instance).hasHigh();
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLng getHigh() {
            return ((Viewport) this.instance).getHigh();
        }

        public Builder setHigh(LatLng value) {
            copyOnWrite();
            ((Viewport) this.instance).setHigh(value);
            return this;
        }

        public Builder setHigh(LatLng.Builder builderForValue) {
            copyOnWrite();
            ((Viewport) this.instance).setHigh(builderForValue.build());
            return this;
        }

        public Builder mergeHigh(LatLng value) {
            copyOnWrite();
            ((Viewport) this.instance).mergeHigh(value);
            return this;
        }

        public Builder clearHigh() {
            copyOnWrite();
            ((Viewport) this.instance).clearHigh();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new Viewport();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"low_", "high_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Viewport> parser = PARSER;
                if (parser == null) {
                    synchronized (Viewport.class) {
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
        Viewport defaultInstance = new Viewport();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Viewport.class, defaultInstance);
    }

    public static Viewport getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Viewport> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
