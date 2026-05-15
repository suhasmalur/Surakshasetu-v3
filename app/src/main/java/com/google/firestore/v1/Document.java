package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
public final class Document extends GeneratedMessageLite<Document, Builder> implements DocumentOrBuilder {
    public static final int CREATE_TIME_FIELD_NUMBER = 3;
    private static final Document DEFAULT_INSTANCE;
    public static final int FIELDS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<Document> PARSER = null;
    public static final int UPDATE_TIME_FIELD_NUMBER = 4;
    private Timestamp createTime_;
    private MapFieldLite<String, Value> fields_ = MapFieldLite.emptyMapField();
    private String name_ = "";
    private Timestamp updateTime_;

    private Document() {
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
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

    private static final class FieldsDefaultEntryHolder {
        static final MapEntryLite<String, Value> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance());

        private FieldsDefaultEntryHolder() {
        }
    }

    private MapFieldLite<String, Value> internalGetFields() {
        return this.fields_;
    }

    private MapFieldLite<String, Value> internalGetMutableFields() {
        if (!this.fields_.isMutable()) {
            this.fields_ = this.fields_.mutableCopy();
        }
        return this.fields_;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public int getFieldsCount() {
        return internalGetFields().size();
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public boolean containsFields(String key) {
        key.getClass();
        return internalGetFields().containsKey(key);
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    @Deprecated
    public Map<String, Value> getFields() {
        return getFieldsMap();
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Map<String, Value> getFieldsMap() {
        return Collections.unmodifiableMap(internalGetFields());
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Value getFieldsOrDefault(String key, Value defaultValue) {
        key.getClass();
        Map<String, Value> map = internalGetFields();
        return map.containsKey(key) ? map.get(key) : defaultValue;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Value getFieldsOrThrow(String key) {
        key.getClass();
        Map<String, Value> map = internalGetFields();
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return map.get(key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, Value> getMutableFieldsMap() {
        return internalGetMutableFields();
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public boolean hasCreateTime() {
        return this.createTime_ != null;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Timestamp getCreateTime() {
        return this.createTime_ == null ? Timestamp.getDefaultInstance() : this.createTime_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCreateTime(Timestamp value) {
        value.getClass();
        this.createTime_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCreateTime(Timestamp value) {
        value.getClass();
        if (this.createTime_ != null && this.createTime_ != Timestamp.getDefaultInstance()) {
            this.createTime_ = Timestamp.newBuilder(this.createTime_).mergeFrom(value).buildPartial();
        } else {
            this.createTime_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCreateTime() {
        this.createTime_ = null;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public boolean hasUpdateTime() {
        return this.updateTime_ != null;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Timestamp getUpdateTime() {
        return this.updateTime_ == null ? Timestamp.getDefaultInstance() : this.updateTime_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpdateTime(Timestamp value) {
        value.getClass();
        this.updateTime_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUpdateTime(Timestamp value) {
        value.getClass();
        if (this.updateTime_ != null && this.updateTime_ != Timestamp.getDefaultInstance()) {
            this.updateTime_ = Timestamp.newBuilder(this.updateTime_).mergeFrom(value).buildPartial();
        } else {
            this.updateTime_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpdateTime() {
        this.updateTime_ = null;
    }

    public static Document parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Document parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Document parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Document parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Document parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Document parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Document parseFrom(InputStream input) throws IOException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Document parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Document parseDelimitedFrom(InputStream input) throws IOException {
        return (Document) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Document parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Document) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Document parseFrom(CodedInputStream input) throws IOException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Document parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Document prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Document, Builder> implements DocumentOrBuilder {
        private Builder() {
            super(Document.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public String getName() {
            return ((Document) this.instance).getName();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public ByteString getNameBytes() {
            return ((Document) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((Document) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Document) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((Document) this.instance).setNameBytes(value);
            return this;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public int getFieldsCount() {
            return ((Document) this.instance).getFieldsMap().size();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public boolean containsFields(String key) {
            key.getClass();
            return ((Document) this.instance).getFieldsMap().containsKey(key);
        }

        public Builder clearFields() {
            copyOnWrite();
            ((Document) this.instance).getMutableFieldsMap().clear();
            return this;
        }

        public Builder removeFields(String key) {
            key.getClass();
            copyOnWrite();
            ((Document) this.instance).getMutableFieldsMap().remove(key);
            return this;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        @Deprecated
        public Map<String, Value> getFields() {
            return getFieldsMap();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Map<String, Value> getFieldsMap() {
            return Collections.unmodifiableMap(((Document) this.instance).getFieldsMap());
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Value getFieldsOrDefault(String key, Value defaultValue) {
            key.getClass();
            Map<String, Value> map = ((Document) this.instance).getFieldsMap();
            return map.containsKey(key) ? map.get(key) : defaultValue;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Value getFieldsOrThrow(String key) {
            key.getClass();
            Map<String, Value> map = ((Document) this.instance).getFieldsMap();
            if (!map.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            return map.get(key);
        }

        public Builder putFields(String key, Value value) {
            key.getClass();
            value.getClass();
            copyOnWrite();
            ((Document) this.instance).getMutableFieldsMap().put(key, value);
            return this;
        }

        public Builder putAllFields(Map<String, Value> values) {
            copyOnWrite();
            ((Document) this.instance).getMutableFieldsMap().putAll(values);
            return this;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public boolean hasCreateTime() {
            return ((Document) this.instance).hasCreateTime();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Timestamp getCreateTime() {
            return ((Document) this.instance).getCreateTime();
        }

        public Builder setCreateTime(Timestamp value) {
            copyOnWrite();
            ((Document) this.instance).setCreateTime(value);
            return this;
        }

        public Builder setCreateTime(Timestamp.Builder builderForValue) {
            copyOnWrite();
            ((Document) this.instance).setCreateTime(builderForValue.build());
            return this;
        }

        public Builder mergeCreateTime(Timestamp value) {
            copyOnWrite();
            ((Document) this.instance).mergeCreateTime(value);
            return this;
        }

        public Builder clearCreateTime() {
            copyOnWrite();
            ((Document) this.instance).clearCreateTime();
            return this;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public boolean hasUpdateTime() {
            return ((Document) this.instance).hasUpdateTime();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Timestamp getUpdateTime() {
            return ((Document) this.instance).getUpdateTime();
        }

        public Builder setUpdateTime(Timestamp value) {
            copyOnWrite();
            ((Document) this.instance).setUpdateTime(value);
            return this;
        }

        public Builder setUpdateTime(Timestamp.Builder builderForValue) {
            copyOnWrite();
            ((Document) this.instance).setUpdateTime(builderForValue.build());
            return this;
        }

        public Builder mergeUpdateTime(Timestamp value) {
            copyOnWrite();
            ((Document) this.instance).mergeUpdateTime(value);
            return this;
        }

        public Builder clearUpdateTime() {
            copyOnWrite();
            ((Document) this.instance).clearUpdateTime();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new Document();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"name_", "fields_", FieldsDefaultEntryHolder.defaultEntry, "createTime_", "updateTime_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0001Ȉ\u00022\u0003\t\u0004\t", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Document> parser = PARSER;
                if (parser == null) {
                    synchronized (Document.class) {
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
        Document defaultInstance = new Document();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Document.class, defaultInstance);
    }

    public static Document getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Document> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
