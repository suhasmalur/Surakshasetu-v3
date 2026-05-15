package com.google.api;

import com.google.api.AuthProvider;
import com.google.api.AuthenticationRule;
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
public final class Authentication extends GeneratedMessageLite<Authentication, Builder> implements AuthenticationOrBuilder {
    private static final Authentication DEFAULT_INSTANCE;
    private static volatile Parser<Authentication> PARSER = null;
    public static final int PROVIDERS_FIELD_NUMBER = 4;
    public static final int RULES_FIELD_NUMBER = 3;
    private Internal.ProtobufList<AuthenticationRule> rules_ = emptyProtobufList();
    private Internal.ProtobufList<AuthProvider> providers_ = emptyProtobufList();

    private Authentication() {
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public List<AuthenticationRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends AuthenticationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public AuthenticationRule getRules(int index) {
        return this.rules_.get(index);
    }

    public AuthenticationRuleOrBuilder getRulesOrBuilder(int index) {
        return this.rules_.get(index);
    }

    private void ensureRulesIsMutable() {
        Internal.ProtobufList<AuthenticationRule> tmp = this.rules_;
        if (!tmp.isModifiable()) {
            this.rules_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRules(int index, AuthenticationRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(AuthenticationRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(int index, AuthenticationRule value) {
        value.getClass();
        ensureRulesIsMutable();
        this.rules_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllRules(Iterable<? extends AuthenticationRule> values) {
        ensureRulesIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.rules_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRules() {
        this.rules_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeRules(int index) {
        ensureRulesIsMutable();
        this.rules_.remove(index);
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public List<AuthProvider> getProvidersList() {
        return this.providers_;
    }

    public List<? extends AuthProviderOrBuilder> getProvidersOrBuilderList() {
        return this.providers_;
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public int getProvidersCount() {
        return this.providers_.size();
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public AuthProvider getProviders(int index) {
        return this.providers_.get(index);
    }

    public AuthProviderOrBuilder getProvidersOrBuilder(int index) {
        return this.providers_.get(index);
    }

    private void ensureProvidersIsMutable() {
        Internal.ProtobufList<AuthProvider> tmp = this.providers_;
        if (!tmp.isModifiable()) {
            this.providers_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProviders(int index, AuthProvider value) {
        value.getClass();
        ensureProvidersIsMutable();
        this.providers_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProviders(AuthProvider value) {
        value.getClass();
        ensureProvidersIsMutable();
        this.providers_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProviders(int index, AuthProvider value) {
        value.getClass();
        ensureProvidersIsMutable();
        this.providers_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllProviders(Iterable<? extends AuthProvider> values) {
        ensureProvidersIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.providers_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProviders() {
        this.providers_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeProviders(int index) {
        ensureProvidersIsMutable();
        this.providers_.remove(index);
    }

    public static Authentication parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Authentication parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Authentication parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Authentication parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Authentication parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Authentication parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Authentication parseFrom(InputStream input) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Authentication parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Authentication parseDelimitedFrom(InputStream input) throws IOException {
        return (Authentication) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Authentication parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Authentication) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Authentication parseFrom(CodedInputStream input) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Authentication parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Authentication prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Authentication, Builder> implements AuthenticationOrBuilder {
        private Builder() {
            super(Authentication.DEFAULT_INSTANCE);
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public List<AuthenticationRule> getRulesList() {
            return Collections.unmodifiableList(((Authentication) this.instance).getRulesList());
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public int getRulesCount() {
            return ((Authentication) this.instance).getRulesCount();
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public AuthenticationRule getRules(int index) {
            return ((Authentication) this.instance).getRules(index);
        }

        public Builder setRules(int index, AuthenticationRule value) {
            copyOnWrite();
            ((Authentication) this.instance).setRules(index, value);
            return this;
        }

        public Builder setRules(int index, AuthenticationRule.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).setRules(index, builderForValue.build());
            return this;
        }

        public Builder addRules(AuthenticationRule value) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(value);
            return this;
        }

        public Builder addRules(int index, AuthenticationRule value) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(index, value);
            return this;
        }

        public Builder addRules(AuthenticationRule.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(builderForValue.build());
            return this;
        }

        public Builder addRules(int index, AuthenticationRule.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(index, builderForValue.build());
            return this;
        }

        public Builder addAllRules(Iterable<? extends AuthenticationRule> values) {
            copyOnWrite();
            ((Authentication) this.instance).addAllRules(values);
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((Authentication) this.instance).clearRules();
            return this;
        }

        public Builder removeRules(int index) {
            copyOnWrite();
            ((Authentication) this.instance).removeRules(index);
            return this;
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public List<AuthProvider> getProvidersList() {
            return Collections.unmodifiableList(((Authentication) this.instance).getProvidersList());
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public int getProvidersCount() {
            return ((Authentication) this.instance).getProvidersCount();
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public AuthProvider getProviders(int index) {
            return ((Authentication) this.instance).getProviders(index);
        }

        public Builder setProviders(int index, AuthProvider value) {
            copyOnWrite();
            ((Authentication) this.instance).setProviders(index, value);
            return this;
        }

        public Builder setProviders(int index, AuthProvider.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).setProviders(index, builderForValue.build());
            return this;
        }

        public Builder addProviders(AuthProvider value) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(value);
            return this;
        }

        public Builder addProviders(int index, AuthProvider value) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(index, value);
            return this;
        }

        public Builder addProviders(AuthProvider.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(builderForValue.build());
            return this;
        }

        public Builder addProviders(int index, AuthProvider.Builder builderForValue) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(index, builderForValue.build());
            return this;
        }

        public Builder addAllProviders(Iterable<? extends AuthProvider> values) {
            copyOnWrite();
            ((Authentication) this.instance).addAllProviders(values);
            return this;
        }

        public Builder clearProviders() {
            copyOnWrite();
            ((Authentication) this.instance).clearProviders();
            return this;
        }

        public Builder removeProviders(int index) {
            copyOnWrite();
            ((Authentication) this.instance).removeProviders(index);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new Authentication();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"rules_", AuthenticationRule.class, "providers_", AuthProvider.class};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0003\u0004\u0002\u0000\u0002\u0000\u0003\u001b\u0004\u001b", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Authentication> parser = PARSER;
                if (parser == null) {
                    synchronized (Authentication.class) {
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
        Authentication defaultInstance = new Authentication();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Authentication.class, defaultInstance);
    }

    public static Authentication getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Authentication> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
