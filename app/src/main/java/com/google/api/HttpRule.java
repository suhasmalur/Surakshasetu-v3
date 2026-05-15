package com.google.api;

import com.google.api.CustomHttpPattern;
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
public final class HttpRule extends GeneratedMessageLite<HttpRule, Builder> implements HttpRuleOrBuilder {
    public static final int ADDITIONAL_BINDINGS_FIELD_NUMBER = 11;
    public static final int BODY_FIELD_NUMBER = 7;
    public static final int CUSTOM_FIELD_NUMBER = 8;
    private static final HttpRule DEFAULT_INSTANCE;
    public static final int DELETE_FIELD_NUMBER = 5;
    public static final int GET_FIELD_NUMBER = 2;
    private static volatile Parser<HttpRule> PARSER = null;
    public static final int PATCH_FIELD_NUMBER = 6;
    public static final int POST_FIELD_NUMBER = 4;
    public static final int PUT_FIELD_NUMBER = 3;
    public static final int RESPONSE_BODY_FIELD_NUMBER = 12;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private Object pattern_;
    private int patternCase_ = 0;
    private String selector_ = "";
    private String body_ = "";
    private String responseBody_ = "";
    private Internal.ProtobufList<HttpRule> additionalBindings_ = emptyProtobufList();

    private HttpRule() {
    }

    public enum PatternCase {
        GET(2),
        PUT(3),
        POST(4),
        DELETE(5),
        PATCH(6),
        CUSTOM(8),
        PATTERN_NOT_SET(0);

        private final int value;

        PatternCase(int value) {
            this.value = value;
        }

        @Deprecated
        public static PatternCase valueOf(int value) {
            return forNumber(value);
        }

        public static PatternCase forNumber(int value) {
            switch (value) {
                case 0:
                    return PATTERN_NOT_SET;
                case 1:
                case 7:
                default:
                    return null;
                case 2:
                    return GET;
                case 3:
                    return PUT;
                case 4:
                    return POST;
                case 5:
                    return DELETE;
                case 6:
                    return PATCH;
                case 8:
                    return CUSTOM;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public PatternCase getPatternCase() {
        return PatternCase.forNumber(this.patternCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPattern() {
        this.patternCase_ = 0;
        this.pattern_ = null;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getSelector() {
        return this.selector_;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelector(String value) {
        value.getClass();
        this.selector_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = getDefaultInstance().getSelector();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectorBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.selector_ = value.toStringUtf8();
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getGet() {
        if (this.patternCase_ != 2) {
            return "";
        }
        String ref = (String) this.pattern_;
        return ref;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getGetBytes() {
        String ref = "";
        if (this.patternCase_ == 2) {
            ref = (String) this.pattern_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGet(String value) {
        value.getClass();
        this.patternCase_ = 2;
        this.pattern_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearGet() {
        if (this.patternCase_ == 2) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGetBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.pattern_ = value.toStringUtf8();
        this.patternCase_ = 2;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPut() {
        if (this.patternCase_ != 3) {
            return "";
        }
        String ref = (String) this.pattern_;
        return ref;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPutBytes() {
        String ref = "";
        if (this.patternCase_ == 3) {
            ref = (String) this.pattern_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPut(String value) {
        value.getClass();
        this.patternCase_ = 3;
        this.pattern_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPut() {
        if (this.patternCase_ == 3) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPutBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.pattern_ = value.toStringUtf8();
        this.patternCase_ = 3;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPost() {
        if (this.patternCase_ != 4) {
            return "";
        }
        String ref = (String) this.pattern_;
        return ref;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPostBytes() {
        String ref = "";
        if (this.patternCase_ == 4) {
            ref = (String) this.pattern_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPost(String value) {
        value.getClass();
        this.patternCase_ = 4;
        this.pattern_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPost() {
        if (this.patternCase_ == 4) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPostBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.pattern_ = value.toStringUtf8();
        this.patternCase_ = 4;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getDelete() {
        if (this.patternCase_ != 5) {
            return "";
        }
        String ref = (String) this.pattern_;
        return ref;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getDeleteBytes() {
        String ref = "";
        if (this.patternCase_ == 5) {
            ref = (String) this.pattern_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDelete(String value) {
        value.getClass();
        this.patternCase_ = 5;
        this.pattern_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDelete() {
        if (this.patternCase_ == 5) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeleteBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.pattern_ = value.toStringUtf8();
        this.patternCase_ = 5;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getPatch() {
        if (this.patternCase_ != 6) {
            return "";
        }
        String ref = (String) this.pattern_;
        return ref;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getPatchBytes() {
        String ref = "";
        if (this.patternCase_ == 6) {
            ref = (String) this.pattern_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPatch(String value) {
        value.getClass();
        this.patternCase_ = 6;
        this.pattern_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPatch() {
        if (this.patternCase_ == 6) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPatchBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.pattern_ = value.toStringUtf8();
        this.patternCase_ = 6;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public boolean hasCustom() {
        return this.patternCase_ == 8;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public CustomHttpPattern getCustom() {
        if (this.patternCase_ == 8) {
            return (CustomHttpPattern) this.pattern_;
        }
        return CustomHttpPattern.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCustom(CustomHttpPattern value) {
        value.getClass();
        this.pattern_ = value;
        this.patternCase_ = 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCustom(CustomHttpPattern value) {
        value.getClass();
        if (this.patternCase_ == 8 && this.pattern_ != CustomHttpPattern.getDefaultInstance()) {
            this.pattern_ = CustomHttpPattern.newBuilder((CustomHttpPattern) this.pattern_).mergeFrom(value).buildPartial();
        } else {
            this.pattern_ = value;
        }
        this.patternCase_ = 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCustom() {
        if (this.patternCase_ == 8) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getBody() {
        return this.body_;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getBodyBytes() {
        return ByteString.copyFromUtf8(this.body_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBody(String value) {
        value.getClass();
        this.body_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBody() {
        this.body_ = getDefaultInstance().getBody();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBodyBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.body_ = value.toStringUtf8();
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public String getResponseBody() {
        return this.responseBody_;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public ByteString getResponseBodyBytes() {
        return ByteString.copyFromUtf8(this.responseBody_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResponseBody(String value) {
        value.getClass();
        this.responseBody_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResponseBody() {
        this.responseBody_ = getDefaultInstance().getResponseBody();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResponseBodyBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.responseBody_ = value.toStringUtf8();
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public List<HttpRule> getAdditionalBindingsList() {
        return this.additionalBindings_;
    }

    public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
        return this.additionalBindings_;
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public int getAdditionalBindingsCount() {
        return this.additionalBindings_.size();
    }

    @Override // com.google.api.HttpRuleOrBuilder
    public HttpRule getAdditionalBindings(int index) {
        return this.additionalBindings_.get(index);
    }

    public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int index) {
        return this.additionalBindings_.get(index);
    }

    private void ensureAdditionalBindingsIsMutable() {
        Internal.ProtobufList<HttpRule> tmp = this.additionalBindings_;
        if (!tmp.isModifiable()) {
            this.additionalBindings_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdditionalBindings(int index, HttpRule value) {
        value.getClass();
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAdditionalBindings(HttpRule value) {
        value.getClass();
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAdditionalBindings(int index, HttpRule value) {
        value.getClass();
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllAdditionalBindings(Iterable<? extends HttpRule> values) {
        ensureAdditionalBindingsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.additionalBindings_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAdditionalBindings() {
        this.additionalBindings_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeAdditionalBindings(int index) {
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.remove(index);
    }

    public static HttpRule parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpRule parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpRule parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpRule parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpRule parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HttpRule parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HttpRule parseFrom(InputStream input) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpRule parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static HttpRule parseDelimitedFrom(InputStream input) throws IOException {
        return (HttpRule) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpRule parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpRule) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static HttpRule parseFrom(CodedInputStream input) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static HttpRule parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HttpRule prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HttpRule, Builder> implements HttpRuleOrBuilder {
        private Builder() {
            super(HttpRule.DEFAULT_INSTANCE);
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public PatternCase getPatternCase() {
            return ((HttpRule) this.instance).getPatternCase();
        }

        public Builder clearPattern() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPattern();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getSelector() {
            return ((HttpRule) this.instance).getSelector();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getSelectorBytes() {
            return ((HttpRule) this.instance).getSelectorBytes();
        }

        public Builder setSelector(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setSelector(value);
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((HttpRule) this.instance).clearSelector();
            return this;
        }

        public Builder setSelectorBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setSelectorBytes(value);
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getGet() {
            return ((HttpRule) this.instance).getGet();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getGetBytes() {
            return ((HttpRule) this.instance).getGetBytes();
        }

        public Builder setGet(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setGet(value);
            return this;
        }

        public Builder clearGet() {
            copyOnWrite();
            ((HttpRule) this.instance).clearGet();
            return this;
        }

        public Builder setGetBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setGetBytes(value);
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPut() {
            return ((HttpRule) this.instance).getPut();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPutBytes() {
            return ((HttpRule) this.instance).getPutBytes();
        }

        public Builder setPut(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPut(value);
            return this;
        }

        public Builder clearPut() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPut();
            return this;
        }

        public Builder setPutBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPutBytes(value);
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPost() {
            return ((HttpRule) this.instance).getPost();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPostBytes() {
            return ((HttpRule) this.instance).getPostBytes();
        }

        public Builder setPost(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPost(value);
            return this;
        }

        public Builder clearPost() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPost();
            return this;
        }

        public Builder setPostBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPostBytes(value);
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getDelete() {
            return ((HttpRule) this.instance).getDelete();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getDeleteBytes() {
            return ((HttpRule) this.instance).getDeleteBytes();
        }

        public Builder setDelete(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setDelete(value);
            return this;
        }

        public Builder clearDelete() {
            copyOnWrite();
            ((HttpRule) this.instance).clearDelete();
            return this;
        }

        public Builder setDeleteBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setDeleteBytes(value);
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getPatch() {
            return ((HttpRule) this.instance).getPatch();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getPatchBytes() {
            return ((HttpRule) this.instance).getPatchBytes();
        }

        public Builder setPatch(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPatch(value);
            return this;
        }

        public Builder clearPatch() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPatch();
            return this;
        }

        public Builder setPatchBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setPatchBytes(value);
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public boolean hasCustom() {
            return ((HttpRule) this.instance).hasCustom();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public CustomHttpPattern getCustom() {
            return ((HttpRule) this.instance).getCustom();
        }

        public Builder setCustom(CustomHttpPattern value) {
            copyOnWrite();
            ((HttpRule) this.instance).setCustom(value);
            return this;
        }

        public Builder setCustom(CustomHttpPattern.Builder builderForValue) {
            copyOnWrite();
            ((HttpRule) this.instance).setCustom(builderForValue.build());
            return this;
        }

        public Builder mergeCustom(CustomHttpPattern value) {
            copyOnWrite();
            ((HttpRule) this.instance).mergeCustom(value);
            return this;
        }

        public Builder clearCustom() {
            copyOnWrite();
            ((HttpRule) this.instance).clearCustom();
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getBody() {
            return ((HttpRule) this.instance).getBody();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getBodyBytes() {
            return ((HttpRule) this.instance).getBodyBytes();
        }

        public Builder setBody(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setBody(value);
            return this;
        }

        public Builder clearBody() {
            copyOnWrite();
            ((HttpRule) this.instance).clearBody();
            return this;
        }

        public Builder setBodyBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setBodyBytes(value);
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public String getResponseBody() {
            return ((HttpRule) this.instance).getResponseBody();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public ByteString getResponseBodyBytes() {
            return ((HttpRule) this.instance).getResponseBodyBytes();
        }

        public Builder setResponseBody(String value) {
            copyOnWrite();
            ((HttpRule) this.instance).setResponseBody(value);
            return this;
        }

        public Builder clearResponseBody() {
            copyOnWrite();
            ((HttpRule) this.instance).clearResponseBody();
            return this;
        }

        public Builder setResponseBodyBytes(ByteString value) {
            copyOnWrite();
            ((HttpRule) this.instance).setResponseBodyBytes(value);
            return this;
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public List<HttpRule> getAdditionalBindingsList() {
            return Collections.unmodifiableList(((HttpRule) this.instance).getAdditionalBindingsList());
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public int getAdditionalBindingsCount() {
            return ((HttpRule) this.instance).getAdditionalBindingsCount();
        }

        @Override // com.google.api.HttpRuleOrBuilder
        public HttpRule getAdditionalBindings(int index) {
            return ((HttpRule) this.instance).getAdditionalBindings(index);
        }

        public Builder setAdditionalBindings(int index, HttpRule value) {
            copyOnWrite();
            ((HttpRule) this.instance).setAdditionalBindings(index, value);
            return this;
        }

        public Builder setAdditionalBindings(int index, Builder builderForValue) {
            copyOnWrite();
            ((HttpRule) this.instance).setAdditionalBindings(index, builderForValue.build());
            return this;
        }

        public Builder addAdditionalBindings(HttpRule value) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(value);
            return this;
        }

        public Builder addAdditionalBindings(int index, HttpRule value) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(index, value);
            return this;
        }

        public Builder addAdditionalBindings(Builder builderForValue) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(builderForValue.build());
            return this;
        }

        public Builder addAdditionalBindings(int index, Builder builderForValue) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(index, builderForValue.build());
            return this;
        }

        public Builder addAllAdditionalBindings(Iterable<? extends HttpRule> values) {
            copyOnWrite();
            ((HttpRule) this.instance).addAllAdditionalBindings(values);
            return this;
        }

        public Builder clearAdditionalBindings() {
            copyOnWrite();
            ((HttpRule) this.instance).clearAdditionalBindings();
            return this;
        }

        public Builder removeAdditionalBindings(int index) {
            copyOnWrite();
            ((HttpRule) this.instance).removeAdditionalBindings(index);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new HttpRule();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"pattern_", "patternCase_", "selector_", "body_", CustomHttpPattern.class, "additionalBindings_", HttpRule.class, "responseBody_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\n\u0001\u0000\u0001\f\n\u0000\u0001\u0000\u0001Ȉ\u0002Ȼ\u0000\u0003Ȼ\u0000\u0004Ȼ\u0000\u0005Ȼ\u0000\u0006Ȼ\u0000\u0007Ȉ\b<\u0000\u000b\u001b\fȈ", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<HttpRule> parser = PARSER;
                if (parser == null) {
                    synchronized (HttpRule.class) {
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
        HttpRule defaultInstance = new HttpRule();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(HttpRule.class, defaultInstance);
    }

    public static HttpRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
