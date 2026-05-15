package com.google.api;

import com.google.api.Advice;
import com.google.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public final class ConfigChange extends GeneratedMessageLite<ConfigChange, Builder> implements ConfigChangeOrBuilder {
    public static final int ADVICES_FIELD_NUMBER = 5;
    public static final int CHANGE_TYPE_FIELD_NUMBER = 4;
    private static final ConfigChange DEFAULT_INSTANCE;
    public static final int ELEMENT_FIELD_NUMBER = 1;
    public static final int NEW_VALUE_FIELD_NUMBER = 3;
    public static final int OLD_VALUE_FIELD_NUMBER = 2;
    private static volatile Parser<ConfigChange> PARSER;
    private int changeType_;
    private String element_ = "";
    private String oldValue_ = "";
    private String newValue_ = "";
    private Internal.ProtobufList<Advice> advices_ = emptyProtobufList();

    private ConfigChange() {
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getElement() {
        return this.element_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getElementBytes() {
        return ByteString.copyFromUtf8(this.element_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setElement(String value) {
        value.getClass();
        this.element_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearElement() {
        this.element_ = getDefaultInstance().getElement();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setElementBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.element_ = value.toStringUtf8();
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getOldValue() {
        return this.oldValue_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getOldValueBytes() {
        return ByteString.copyFromUtf8(this.oldValue_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOldValue(String value) {
        value.getClass();
        this.oldValue_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOldValue() {
        this.oldValue_ = getDefaultInstance().getOldValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOldValueBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.oldValue_ = value.toStringUtf8();
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getNewValue() {
        return this.newValue_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getNewValueBytes() {
        return ByteString.copyFromUtf8(this.newValue_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNewValue(String value) {
        value.getClass();
        this.newValue_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNewValue() {
        this.newValue_ = getDefaultInstance().getNewValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNewValueBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.newValue_ = value.toStringUtf8();
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public int getChangeTypeValue() {
        return this.changeType_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ChangeType getChangeType() {
        ChangeType result = ChangeType.forNumber(this.changeType_);
        return result == null ? ChangeType.UNRECOGNIZED : result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChangeTypeValue(int value) {
        this.changeType_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChangeType(ChangeType value) {
        this.changeType_ = value.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearChangeType() {
        this.changeType_ = 0;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public List<Advice> getAdvicesList() {
        return this.advices_;
    }

    public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
        return this.advices_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public int getAdvicesCount() {
        return this.advices_.size();
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public Advice getAdvices(int index) {
        return this.advices_.get(index);
    }

    public AdviceOrBuilder getAdvicesOrBuilder(int index) {
        return this.advices_.get(index);
    }

    private void ensureAdvicesIsMutable() {
        Internal.ProtobufList<Advice> tmp = this.advices_;
        if (!tmp.isModifiable()) {
            this.advices_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdvices(int index, Advice value) {
        value.getClass();
        ensureAdvicesIsMutable();
        this.advices_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAdvices(Advice value) {
        value.getClass();
        ensureAdvicesIsMutable();
        this.advices_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAdvices(int index, Advice value) {
        value.getClass();
        ensureAdvicesIsMutable();
        this.advices_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllAdvices(Iterable<? extends Advice> values) {
        ensureAdvicesIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.advices_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAdvices() {
        this.advices_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeAdvices(int index) {
        ensureAdvicesIsMutable();
        this.advices_.remove(index);
    }

    public static ConfigChange parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConfigChange parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConfigChange parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConfigChange parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConfigChange parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConfigChange parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConfigChange parseFrom(InputStream input) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigChange parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConfigChange parseDelimitedFrom(InputStream input) throws IOException {
        return (ConfigChange) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigChange parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigChange) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConfigChange parseFrom(CodedInputStream input) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConfigChange parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ConfigChange prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ConfigChange, Builder> implements ConfigChangeOrBuilder {
        private Builder() {
            super(ConfigChange.DEFAULT_INSTANCE);
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getElement() {
            return ((ConfigChange) this.instance).getElement();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getElementBytes() {
            return ((ConfigChange) this.instance).getElementBytes();
        }

        public Builder setElement(String value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setElement(value);
            return this;
        }

        public Builder clearElement() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearElement();
            return this;
        }

        public Builder setElementBytes(ByteString value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setElementBytes(value);
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getOldValue() {
            return ((ConfigChange) this.instance).getOldValue();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getOldValueBytes() {
            return ((ConfigChange) this.instance).getOldValueBytes();
        }

        public Builder setOldValue(String value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setOldValue(value);
            return this;
        }

        public Builder clearOldValue() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearOldValue();
            return this;
        }

        public Builder setOldValueBytes(ByteString value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setOldValueBytes(value);
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getNewValue() {
            return ((ConfigChange) this.instance).getNewValue();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getNewValueBytes() {
            return ((ConfigChange) this.instance).getNewValueBytes();
        }

        public Builder setNewValue(String value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setNewValue(value);
            return this;
        }

        public Builder clearNewValue() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearNewValue();
            return this;
        }

        public Builder setNewValueBytes(ByteString value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setNewValueBytes(value);
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public int getChangeTypeValue() {
            return ((ConfigChange) this.instance).getChangeTypeValue();
        }

        public Builder setChangeTypeValue(int value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setChangeTypeValue(value);
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ChangeType getChangeType() {
            return ((ConfigChange) this.instance).getChangeType();
        }

        public Builder setChangeType(ChangeType value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setChangeType(value);
            return this;
        }

        public Builder clearChangeType() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearChangeType();
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public List<Advice> getAdvicesList() {
            return Collections.unmodifiableList(((ConfigChange) this.instance).getAdvicesList());
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public int getAdvicesCount() {
            return ((ConfigChange) this.instance).getAdvicesCount();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public Advice getAdvices(int index) {
            return ((ConfigChange) this.instance).getAdvices(index);
        }

        public Builder setAdvices(int index, Advice value) {
            copyOnWrite();
            ((ConfigChange) this.instance).setAdvices(index, value);
            return this;
        }

        public Builder setAdvices(int index, Advice.Builder builderForValue) {
            copyOnWrite();
            ((ConfigChange) this.instance).setAdvices(index, builderForValue.build());
            return this;
        }

        public Builder addAdvices(Advice value) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(value);
            return this;
        }

        public Builder addAdvices(int index, Advice value) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(index, value);
            return this;
        }

        public Builder addAdvices(Advice.Builder builderForValue) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(builderForValue.build());
            return this;
        }

        public Builder addAdvices(int index, Advice.Builder builderForValue) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(index, builderForValue.build());
            return this;
        }

        public Builder addAllAdvices(Iterable<? extends Advice> values) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAllAdvices(values);
            return this;
        }

        public Builder clearAdvices() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearAdvices();
            return this;
        }

        public Builder removeAdvices(int index) {
            copyOnWrite();
            ((ConfigChange) this.instance).removeAdvices(index);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ConfigChange();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"element_", "oldValue_", "newValue_", "changeType_", "advices_", Advice.class};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\f\u0005\u001b", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ConfigChange> parser = PARSER;
                if (parser == null) {
                    synchronized (ConfigChange.class) {
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
        ConfigChange defaultInstance = new ConfigChange();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(ConfigChange.class, defaultInstance);
    }

    public static ConfigChange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConfigChange> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
