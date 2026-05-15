package com.google.cloud.audit;

import com.google.cloud.audit.AuthenticationInfo;
import com.google.cloud.audit.AuthorizationInfo;
import com.google.cloud.audit.RequestMetadata;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Struct;
import com.google.rpc.Status;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public final class AuditLog extends GeneratedMessageLite<AuditLog, Builder> implements AuditLogOrBuilder {
    public static final int AUTHENTICATION_INFO_FIELD_NUMBER = 3;
    public static final int AUTHORIZATION_INFO_FIELD_NUMBER = 9;
    private static final AuditLog DEFAULT_INSTANCE;
    public static final int METHOD_NAME_FIELD_NUMBER = 8;
    public static final int NUM_RESPONSE_ITEMS_FIELD_NUMBER = 12;
    private static volatile Parser<AuditLog> PARSER = null;
    public static final int REQUEST_FIELD_NUMBER = 16;
    public static final int REQUEST_METADATA_FIELD_NUMBER = 4;
    public static final int RESOURCE_NAME_FIELD_NUMBER = 11;
    public static final int RESPONSE_FIELD_NUMBER = 17;
    public static final int SERVICE_DATA_FIELD_NUMBER = 15;
    public static final int SERVICE_NAME_FIELD_NUMBER = 7;
    public static final int STATUS_FIELD_NUMBER = 2;
    private AuthenticationInfo authenticationInfo_;
    private long numResponseItems_;
    private RequestMetadata requestMetadata_;
    private Struct request_;
    private Struct response_;
    private Any serviceData_;
    private Status status_;
    private String serviceName_ = "";
    private String methodName_ = "";
    private String resourceName_ = "";
    private Internal.ProtobufList<AuthorizationInfo> authorizationInfo_ = emptyProtobufList();

    private AuditLog() {
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getServiceName() {
        return this.serviceName_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getServiceNameBytes() {
        return ByteString.copyFromUtf8(this.serviceName_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServiceName(String value) {
        value.getClass();
        this.serviceName_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearServiceName() {
        this.serviceName_ = getDefaultInstance().getServiceName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServiceNameBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.serviceName_ = value.toStringUtf8();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getMethodName() {
        return this.methodName_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getMethodNameBytes() {
        return ByteString.copyFromUtf8(this.methodName_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMethodName(String value) {
        value.getClass();
        this.methodName_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMethodName() {
        this.methodName_ = getDefaultInstance().getMethodName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMethodNameBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.methodName_ = value.toStringUtf8();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public String getResourceName() {
        return this.resourceName_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public ByteString getResourceNameBytes() {
        return ByteString.copyFromUtf8(this.resourceName_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResourceName(String value) {
        value.getClass();
        this.resourceName_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResourceName() {
        this.resourceName_ = getDefaultInstance().getResourceName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResourceNameBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.resourceName_ = value.toStringUtf8();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public long getNumResponseItems() {
        return this.numResponseItems_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNumResponseItems(long value) {
        this.numResponseItems_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNumResponseItems() {
        this.numResponseItems_ = 0L;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasStatus() {
        return this.status_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Status getStatus() {
        return this.status_ == null ? Status.getDefaultInstance() : this.status_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatus(Status value) {
        value.getClass();
        this.status_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStatus(Status value) {
        value.getClass();
        if (this.status_ != null && this.status_ != Status.getDefaultInstance()) {
            this.status_ = Status.newBuilder(this.status_).mergeFrom(value).buildPartial();
        } else {
            this.status_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStatus() {
        this.status_ = null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasAuthenticationInfo() {
        return this.authenticationInfo_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthenticationInfo getAuthenticationInfo() {
        return this.authenticationInfo_ == null ? AuthenticationInfo.getDefaultInstance() : this.authenticationInfo_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAuthenticationInfo(AuthenticationInfo value) {
        value.getClass();
        this.authenticationInfo_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeAuthenticationInfo(AuthenticationInfo value) {
        value.getClass();
        if (this.authenticationInfo_ != null && this.authenticationInfo_ != AuthenticationInfo.getDefaultInstance()) {
            this.authenticationInfo_ = AuthenticationInfo.newBuilder(this.authenticationInfo_).mergeFrom(value).buildPartial();
        } else {
            this.authenticationInfo_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAuthenticationInfo() {
        this.authenticationInfo_ = null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public List<AuthorizationInfo> getAuthorizationInfoList() {
        return this.authorizationInfo_;
    }

    public List<? extends AuthorizationInfoOrBuilder> getAuthorizationInfoOrBuilderList() {
        return this.authorizationInfo_;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public int getAuthorizationInfoCount() {
        return this.authorizationInfo_.size();
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public AuthorizationInfo getAuthorizationInfo(int index) {
        return this.authorizationInfo_.get(index);
    }

    public AuthorizationInfoOrBuilder getAuthorizationInfoOrBuilder(int index) {
        return this.authorizationInfo_.get(index);
    }

    private void ensureAuthorizationInfoIsMutable() {
        Internal.ProtobufList<AuthorizationInfo> tmp = this.authorizationInfo_;
        if (!tmp.isModifiable()) {
            this.authorizationInfo_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAuthorizationInfo(int index, AuthorizationInfo value) {
        value.getClass();
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAuthorizationInfo(AuthorizationInfo value) {
        value.getClass();
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAuthorizationInfo(int index, AuthorizationInfo value) {
        value.getClass();
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllAuthorizationInfo(Iterable<? extends AuthorizationInfo> values) {
        ensureAuthorizationInfoIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.authorizationInfo_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAuthorizationInfo() {
        this.authorizationInfo_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeAuthorizationInfo(int index) {
        ensureAuthorizationInfoIsMutable();
        this.authorizationInfo_.remove(index);
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasRequestMetadata() {
        return this.requestMetadata_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public RequestMetadata getRequestMetadata() {
        return this.requestMetadata_ == null ? RequestMetadata.getDefaultInstance() : this.requestMetadata_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestMetadata(RequestMetadata value) {
        value.getClass();
        this.requestMetadata_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeRequestMetadata(RequestMetadata value) {
        value.getClass();
        if (this.requestMetadata_ != null && this.requestMetadata_ != RequestMetadata.getDefaultInstance()) {
            this.requestMetadata_ = RequestMetadata.newBuilder(this.requestMetadata_).mergeFrom(value).buildPartial();
        } else {
            this.requestMetadata_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRequestMetadata() {
        this.requestMetadata_ = null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasRequest() {
        return this.request_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Struct getRequest() {
        return this.request_ == null ? Struct.getDefaultInstance() : this.request_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequest(Struct value) {
        value.getClass();
        this.request_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeRequest(Struct value) {
        value.getClass();
        if (this.request_ != null && this.request_ != Struct.getDefaultInstance()) {
            this.request_ = Struct.newBuilder(this.request_).mergeFrom(value).buildPartial();
        } else {
            this.request_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRequest() {
        this.request_ = null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasResponse() {
        return this.response_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Struct getResponse() {
        return this.response_ == null ? Struct.getDefaultInstance() : this.response_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResponse(Struct value) {
        value.getClass();
        this.response_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeResponse(Struct value) {
        value.getClass();
        if (this.response_ != null && this.response_ != Struct.getDefaultInstance()) {
            this.response_ = Struct.newBuilder(this.response_).mergeFrom(value).buildPartial();
        } else {
            this.response_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResponse() {
        this.response_ = null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public boolean hasServiceData() {
        return this.serviceData_ != null;
    }

    @Override // com.google.cloud.audit.AuditLogOrBuilder
    public Any getServiceData() {
        return this.serviceData_ == null ? Any.getDefaultInstance() : this.serviceData_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServiceData(Any value) {
        value.getClass();
        this.serviceData_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeServiceData(Any value) {
        value.getClass();
        if (this.serviceData_ != null && this.serviceData_ != Any.getDefaultInstance()) {
            this.serviceData_ = Any.newBuilder(this.serviceData_).mergeFrom(value).buildPartial();
        } else {
            this.serviceData_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearServiceData() {
        this.serviceData_ = null;
    }

    public static AuditLog parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuditLog parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuditLog parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuditLog parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuditLog parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AuditLog parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AuditLog parseFrom(InputStream input) throws IOException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AuditLog parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AuditLog parseDelimitedFrom(InputStream input) throws IOException {
        return (AuditLog) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AuditLog parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuditLog) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AuditLog parseFrom(CodedInputStream input) throws IOException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AuditLog parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AuditLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AuditLog prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AuditLog, Builder> implements AuditLogOrBuilder {
        private Builder() {
            super(AuditLog.DEFAULT_INSTANCE);
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getServiceName() {
            return ((AuditLog) this.instance).getServiceName();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ByteString getServiceNameBytes() {
            return ((AuditLog) this.instance).getServiceNameBytes();
        }

        public Builder setServiceName(String value) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceName(value);
            return this;
        }

        public Builder clearServiceName() {
            copyOnWrite();
            ((AuditLog) this.instance).clearServiceName();
            return this;
        }

        public Builder setServiceNameBytes(ByteString value) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceNameBytes(value);
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getMethodName() {
            return ((AuditLog) this.instance).getMethodName();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ByteString getMethodNameBytes() {
            return ((AuditLog) this.instance).getMethodNameBytes();
        }

        public Builder setMethodName(String value) {
            copyOnWrite();
            ((AuditLog) this.instance).setMethodName(value);
            return this;
        }

        public Builder clearMethodName() {
            copyOnWrite();
            ((AuditLog) this.instance).clearMethodName();
            return this;
        }

        public Builder setMethodNameBytes(ByteString value) {
            copyOnWrite();
            ((AuditLog) this.instance).setMethodNameBytes(value);
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public String getResourceName() {
            return ((AuditLog) this.instance).getResourceName();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public ByteString getResourceNameBytes() {
            return ((AuditLog) this.instance).getResourceNameBytes();
        }

        public Builder setResourceName(String value) {
            copyOnWrite();
            ((AuditLog) this.instance).setResourceName(value);
            return this;
        }

        public Builder clearResourceName() {
            copyOnWrite();
            ((AuditLog) this.instance).clearResourceName();
            return this;
        }

        public Builder setResourceNameBytes(ByteString value) {
            copyOnWrite();
            ((AuditLog) this.instance).setResourceNameBytes(value);
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public long getNumResponseItems() {
            return ((AuditLog) this.instance).getNumResponseItems();
        }

        public Builder setNumResponseItems(long value) {
            copyOnWrite();
            ((AuditLog) this.instance).setNumResponseItems(value);
            return this;
        }

        public Builder clearNumResponseItems() {
            copyOnWrite();
            ((AuditLog) this.instance).clearNumResponseItems();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasStatus() {
            return ((AuditLog) this.instance).hasStatus();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Status getStatus() {
            return ((AuditLog) this.instance).getStatus();
        }

        public Builder setStatus(Status value) {
            copyOnWrite();
            ((AuditLog) this.instance).setStatus(value);
            return this;
        }

        public Builder setStatus(Status.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setStatus(builderForValue.build());
            return this;
        }

        public Builder mergeStatus(Status value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeStatus(value);
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((AuditLog) this.instance).clearStatus();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasAuthenticationInfo() {
            return ((AuditLog) this.instance).hasAuthenticationInfo();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthenticationInfo getAuthenticationInfo() {
            return ((AuditLog) this.instance).getAuthenticationInfo();
        }

        public Builder setAuthenticationInfo(AuthenticationInfo value) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthenticationInfo(value);
            return this;
        }

        public Builder setAuthenticationInfo(AuthenticationInfo.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthenticationInfo(builderForValue.build());
            return this;
        }

        public Builder mergeAuthenticationInfo(AuthenticationInfo value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeAuthenticationInfo(value);
            return this;
        }

        public Builder clearAuthenticationInfo() {
            copyOnWrite();
            ((AuditLog) this.instance).clearAuthenticationInfo();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public List<AuthorizationInfo> getAuthorizationInfoList() {
            return Collections.unmodifiableList(((AuditLog) this.instance).getAuthorizationInfoList());
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public int getAuthorizationInfoCount() {
            return ((AuditLog) this.instance).getAuthorizationInfoCount();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public AuthorizationInfo getAuthorizationInfo(int index) {
            return ((AuditLog) this.instance).getAuthorizationInfo(index);
        }

        public Builder setAuthorizationInfo(int index, AuthorizationInfo value) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthorizationInfo(index, value);
            return this;
        }

        public Builder setAuthorizationInfo(int index, AuthorizationInfo.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setAuthorizationInfo(index, builderForValue.build());
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo value) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo(value);
            return this;
        }

        public Builder addAuthorizationInfo(int index, AuthorizationInfo value) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo(index, value);
            return this;
        }

        public Builder addAuthorizationInfo(AuthorizationInfo.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo(builderForValue.build());
            return this;
        }

        public Builder addAuthorizationInfo(int index, AuthorizationInfo.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).addAuthorizationInfo(index, builderForValue.build());
            return this;
        }

        public Builder addAllAuthorizationInfo(Iterable<? extends AuthorizationInfo> values) {
            copyOnWrite();
            ((AuditLog) this.instance).addAllAuthorizationInfo(values);
            return this;
        }

        public Builder clearAuthorizationInfo() {
            copyOnWrite();
            ((AuditLog) this.instance).clearAuthorizationInfo();
            return this;
        }

        public Builder removeAuthorizationInfo(int index) {
            copyOnWrite();
            ((AuditLog) this.instance).removeAuthorizationInfo(index);
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasRequestMetadata() {
            return ((AuditLog) this.instance).hasRequestMetadata();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public RequestMetadata getRequestMetadata() {
            return ((AuditLog) this.instance).getRequestMetadata();
        }

        public Builder setRequestMetadata(RequestMetadata value) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequestMetadata(value);
            return this;
        }

        public Builder setRequestMetadata(RequestMetadata.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequestMetadata(builderForValue.build());
            return this;
        }

        public Builder mergeRequestMetadata(RequestMetadata value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeRequestMetadata(value);
            return this;
        }

        public Builder clearRequestMetadata() {
            copyOnWrite();
            ((AuditLog) this.instance).clearRequestMetadata();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasRequest() {
            return ((AuditLog) this.instance).hasRequest();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Struct getRequest() {
            return ((AuditLog) this.instance).getRequest();
        }

        public Builder setRequest(Struct value) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequest(value);
            return this;
        }

        public Builder setRequest(Struct.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setRequest(builderForValue.build());
            return this;
        }

        public Builder mergeRequest(Struct value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeRequest(value);
            return this;
        }

        public Builder clearRequest() {
            copyOnWrite();
            ((AuditLog) this.instance).clearRequest();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasResponse() {
            return ((AuditLog) this.instance).hasResponse();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Struct getResponse() {
            return ((AuditLog) this.instance).getResponse();
        }

        public Builder setResponse(Struct value) {
            copyOnWrite();
            ((AuditLog) this.instance).setResponse(value);
            return this;
        }

        public Builder setResponse(Struct.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setResponse(builderForValue.build());
            return this;
        }

        public Builder mergeResponse(Struct value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeResponse(value);
            return this;
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((AuditLog) this.instance).clearResponse();
            return this;
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public boolean hasServiceData() {
            return ((AuditLog) this.instance).hasServiceData();
        }

        @Override // com.google.cloud.audit.AuditLogOrBuilder
        public Any getServiceData() {
            return ((AuditLog) this.instance).getServiceData();
        }

        public Builder setServiceData(Any value) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceData(value);
            return this;
        }

        public Builder setServiceData(Any.Builder builderForValue) {
            copyOnWrite();
            ((AuditLog) this.instance).setServiceData(builderForValue.build());
            return this;
        }

        public Builder mergeServiceData(Any value) {
            copyOnWrite();
            ((AuditLog) this.instance).mergeServiceData(value);
            return this;
        }

        public Builder clearServiceData() {
            copyOnWrite();
            ((AuditLog) this.instance).clearServiceData();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AuditLog();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"status_", "authenticationInfo_", "requestMetadata_", "serviceName_", "methodName_", "authorizationInfo_", AuthorizationInfo.class, "resourceName_", "numResponseItems_", "serviceData_", "request_", "response_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000b\u0000\u0000\u0002\u0011\u000b\u0000\u0001\u0000\u0002\t\u0003\t\u0004\t\u0007Ȉ\bȈ\t\u001b\u000bȈ\f\u0002\u000f\t\u0010\t\u0011\t", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<AuditLog> parser = PARSER;
                if (parser == null) {
                    synchronized (AuditLog.class) {
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
        AuditLog defaultInstance = new AuditLog();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(AuditLog.class, defaultInstance);
    }

    public static AuditLog getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuditLog> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
