package com.google.api;

import com.google.api.Authentication;
import com.google.api.Backend;
import com.google.api.Billing;
import com.google.api.Context;
import com.google.api.Control;
import com.google.api.Documentation;
import com.google.api.Endpoint;
import com.google.api.Http;
import com.google.api.LogDescriptor;
import com.google.api.Logging;
import com.google.api.MetricDescriptor;
import com.google.api.MonitoredResourceDescriptor;
import com.google.api.Monitoring;
import com.google.api.Quota;
import com.google.api.SourceInfo;
import com.google.api.SystemParameters;
import com.google.api.Usage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Api;
import com.google.protobuf.ApiOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Enum;
import com.google.protobuf.EnumOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Type;
import com.google.protobuf.TypeOrBuilder;
import com.google.protobuf.UInt32Value;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public final class Service extends GeneratedMessageLite<Service, Builder> implements ServiceOrBuilder {
    public static final int APIS_FIELD_NUMBER = 3;
    public static final int AUTHENTICATION_FIELD_NUMBER = 11;
    public static final int BACKEND_FIELD_NUMBER = 8;
    public static final int BILLING_FIELD_NUMBER = 26;
    public static final int CONFIG_VERSION_FIELD_NUMBER = 20;
    public static final int CONTEXT_FIELD_NUMBER = 12;
    public static final int CONTROL_FIELD_NUMBER = 21;
    private static final Service DEFAULT_INSTANCE;
    public static final int DOCUMENTATION_FIELD_NUMBER = 6;
    public static final int ENDPOINTS_FIELD_NUMBER = 18;
    public static final int ENUMS_FIELD_NUMBER = 5;
    public static final int HTTP_FIELD_NUMBER = 9;
    public static final int ID_FIELD_NUMBER = 33;
    public static final int LOGGING_FIELD_NUMBER = 27;
    public static final int LOGS_FIELD_NUMBER = 23;
    public static final int METRICS_FIELD_NUMBER = 24;
    public static final int MONITORED_RESOURCES_FIELD_NUMBER = 25;
    public static final int MONITORING_FIELD_NUMBER = 28;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<Service> PARSER = null;
    public static final int PRODUCER_PROJECT_ID_FIELD_NUMBER = 22;
    public static final int QUOTA_FIELD_NUMBER = 10;
    public static final int SOURCE_INFO_FIELD_NUMBER = 37;
    public static final int SYSTEM_PARAMETERS_FIELD_NUMBER = 29;
    public static final int TITLE_FIELD_NUMBER = 2;
    public static final int TYPES_FIELD_NUMBER = 4;
    public static final int USAGE_FIELD_NUMBER = 15;
    private Authentication authentication_;
    private Backend backend_;
    private Billing billing_;
    private UInt32Value configVersion_;
    private Context context_;
    private Control control_;
    private Documentation documentation_;
    private Http http_;
    private Logging logging_;
    private Monitoring monitoring_;
    private Quota quota_;
    private SourceInfo sourceInfo_;
    private SystemParameters systemParameters_;
    private Usage usage_;
    private String name_ = "";
    private String id_ = "";
    private String title_ = "";
    private String producerProjectId_ = "";
    private Internal.ProtobufList<Api> apis_ = emptyProtobufList();
    private Internal.ProtobufList<Type> types_ = emptyProtobufList();
    private Internal.ProtobufList<Enum> enums_ = emptyProtobufList();
    private Internal.ProtobufList<Endpoint> endpoints_ = emptyProtobufList();
    private Internal.ProtobufList<LogDescriptor> logs_ = emptyProtobufList();
    private Internal.ProtobufList<MetricDescriptor> metrics_ = emptyProtobufList();
    private Internal.ProtobufList<MonitoredResourceDescriptor> monitoredResources_ = emptyProtobufList();

    private Service() {
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasConfigVersion() {
        return this.configVersion_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public UInt32Value getConfigVersion() {
        return this.configVersion_ == null ? UInt32Value.getDefaultInstance() : this.configVersion_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConfigVersion(UInt32Value value) {
        value.getClass();
        this.configVersion_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeConfigVersion(UInt32Value value) {
        value.getClass();
        if (this.configVersion_ != null && this.configVersion_ != UInt32Value.getDefaultInstance()) {
            this.configVersion_ = UInt32Value.newBuilder(this.configVersion_).mergeFrom(value).buildPartial();
        } else {
            this.configVersion_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConfigVersion() {
        this.configVersion_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.api.ServiceOrBuilder
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

    @Override // com.google.api.ServiceOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setId(String value) {
        value.getClass();
        this.id_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearId() {
        this.id_ = getDefaultInstance().getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIdBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.id_ = value.toStringUtf8();
    }

    @Override // com.google.api.ServiceOrBuilder
    public String getTitle() {
        return this.title_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ByteString getTitleBytes() {
        return ByteString.copyFromUtf8(this.title_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTitle(String value) {
        value.getClass();
        this.title_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTitle() {
        this.title_ = getDefaultInstance().getTitle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTitleBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.title_ = value.toStringUtf8();
    }

    @Override // com.google.api.ServiceOrBuilder
    public String getProducerProjectId() {
        return this.producerProjectId_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public ByteString getProducerProjectIdBytes() {
        return ByteString.copyFromUtf8(this.producerProjectId_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProducerProjectId(String value) {
        value.getClass();
        this.producerProjectId_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProducerProjectId() {
        this.producerProjectId_ = getDefaultInstance().getProducerProjectId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProducerProjectIdBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.producerProjectId_ = value.toStringUtf8();
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Api> getApisList() {
        return this.apis_;
    }

    public List<? extends ApiOrBuilder> getApisOrBuilderList() {
        return this.apis_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getApisCount() {
        return this.apis_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public Api getApis(int index) {
        return this.apis_.get(index);
    }

    public ApiOrBuilder getApisOrBuilder(int index) {
        return this.apis_.get(index);
    }

    private void ensureApisIsMutable() {
        Internal.ProtobufList<Api> tmp = this.apis_;
        if (!tmp.isModifiable()) {
            this.apis_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setApis(int index, Api value) {
        value.getClass();
        ensureApisIsMutable();
        this.apis_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addApis(Api value) {
        value.getClass();
        ensureApisIsMutable();
        this.apis_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addApis(int index, Api value) {
        value.getClass();
        ensureApisIsMutable();
        this.apis_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllApis(Iterable<? extends Api> values) {
        ensureApisIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.apis_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearApis() {
        this.apis_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeApis(int index) {
        ensureApisIsMutable();
        this.apis_.remove(index);
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Type> getTypesList() {
        return this.types_;
    }

    public List<? extends TypeOrBuilder> getTypesOrBuilderList() {
        return this.types_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getTypesCount() {
        return this.types_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public Type getTypes(int index) {
        return this.types_.get(index);
    }

    public TypeOrBuilder getTypesOrBuilder(int index) {
        return this.types_.get(index);
    }

    private void ensureTypesIsMutable() {
        Internal.ProtobufList<Type> tmp = this.types_;
        if (!tmp.isModifiable()) {
            this.types_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTypes(int index, Type value) {
        value.getClass();
        ensureTypesIsMutable();
        this.types_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTypes(Type value) {
        value.getClass();
        ensureTypesIsMutable();
        this.types_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTypes(int index, Type value) {
        value.getClass();
        ensureTypesIsMutable();
        this.types_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllTypes(Iterable<? extends Type> values) {
        ensureTypesIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.types_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTypes() {
        this.types_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeTypes(int index) {
        ensureTypesIsMutable();
        this.types_.remove(index);
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Enum> getEnumsList() {
        return this.enums_;
    }

    public List<? extends EnumOrBuilder> getEnumsOrBuilderList() {
        return this.enums_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getEnumsCount() {
        return this.enums_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public Enum getEnums(int index) {
        return this.enums_.get(index);
    }

    public EnumOrBuilder getEnumsOrBuilder(int index) {
        return this.enums_.get(index);
    }

    private void ensureEnumsIsMutable() {
        Internal.ProtobufList<Enum> tmp = this.enums_;
        if (!tmp.isModifiable()) {
            this.enums_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEnums(int index, Enum value) {
        value.getClass();
        ensureEnumsIsMutable();
        this.enums_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEnums(Enum value) {
        value.getClass();
        ensureEnumsIsMutable();
        this.enums_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEnums(int index, Enum value) {
        value.getClass();
        ensureEnumsIsMutable();
        this.enums_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllEnums(Iterable<? extends Enum> values) {
        ensureEnumsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.enums_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearEnums() {
        this.enums_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeEnums(int index) {
        ensureEnumsIsMutable();
        this.enums_.remove(index);
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasDocumentation() {
        return this.documentation_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Documentation getDocumentation() {
        return this.documentation_ == null ? Documentation.getDefaultInstance() : this.documentation_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentation(Documentation value) {
        value.getClass();
        this.documentation_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentation(Documentation value) {
        value.getClass();
        if (this.documentation_ != null && this.documentation_ != Documentation.getDefaultInstance()) {
            this.documentation_ = Documentation.newBuilder(this.documentation_).mergeFrom(value).buildPartial();
        } else {
            this.documentation_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentation() {
        this.documentation_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasBackend() {
        return this.backend_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Backend getBackend() {
        return this.backend_ == null ? Backend.getDefaultInstance() : this.backend_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBackend(Backend value) {
        value.getClass();
        this.backend_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeBackend(Backend value) {
        value.getClass();
        if (this.backend_ != null && this.backend_ != Backend.getDefaultInstance()) {
            this.backend_ = Backend.newBuilder(this.backend_).mergeFrom(value).buildPartial();
        } else {
            this.backend_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBackend() {
        this.backend_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasHttp() {
        return this.http_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Http getHttp() {
        return this.http_ == null ? Http.getDefaultInstance() : this.http_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHttp(Http value) {
        value.getClass();
        this.http_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeHttp(Http value) {
        value.getClass();
        if (this.http_ != null && this.http_ != Http.getDefaultInstance()) {
            this.http_ = Http.newBuilder(this.http_).mergeFrom(value).buildPartial();
        } else {
            this.http_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHttp() {
        this.http_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasQuota() {
        return this.quota_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Quota getQuota() {
        return this.quota_ == null ? Quota.getDefaultInstance() : this.quota_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQuota(Quota value) {
        value.getClass();
        this.quota_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeQuota(Quota value) {
        value.getClass();
        if (this.quota_ != null && this.quota_ != Quota.getDefaultInstance()) {
            this.quota_ = Quota.newBuilder(this.quota_).mergeFrom(value).buildPartial();
        } else {
            this.quota_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQuota() {
        this.quota_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasAuthentication() {
        return this.authentication_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Authentication getAuthentication() {
        return this.authentication_ == null ? Authentication.getDefaultInstance() : this.authentication_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAuthentication(Authentication value) {
        value.getClass();
        this.authentication_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeAuthentication(Authentication value) {
        value.getClass();
        if (this.authentication_ != null && this.authentication_ != Authentication.getDefaultInstance()) {
            this.authentication_ = Authentication.newBuilder(this.authentication_).mergeFrom(value).buildPartial();
        } else {
            this.authentication_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAuthentication() {
        this.authentication_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasContext() {
        return this.context_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Context getContext() {
        return this.context_ == null ? Context.getDefaultInstance() : this.context_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContext(Context value) {
        value.getClass();
        this.context_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeContext(Context value) {
        value.getClass();
        if (this.context_ != null && this.context_ != Context.getDefaultInstance()) {
            this.context_ = Context.newBuilder(this.context_).mergeFrom(value).buildPartial();
        } else {
            this.context_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearContext() {
        this.context_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasUsage() {
        return this.usage_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Usage getUsage() {
        return this.usage_ == null ? Usage.getDefaultInstance() : this.usage_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUsage(Usage value) {
        value.getClass();
        this.usage_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUsage(Usage value) {
        value.getClass();
        if (this.usage_ != null && this.usage_ != Usage.getDefaultInstance()) {
            this.usage_ = Usage.newBuilder(this.usage_).mergeFrom(value).buildPartial();
        } else {
            this.usage_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUsage() {
        this.usage_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<Endpoint> getEndpointsList() {
        return this.endpoints_;
    }

    public List<? extends EndpointOrBuilder> getEndpointsOrBuilderList() {
        return this.endpoints_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getEndpointsCount() {
        return this.endpoints_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public Endpoint getEndpoints(int index) {
        return this.endpoints_.get(index);
    }

    public EndpointOrBuilder getEndpointsOrBuilder(int index) {
        return this.endpoints_.get(index);
    }

    private void ensureEndpointsIsMutable() {
        Internal.ProtobufList<Endpoint> tmp = this.endpoints_;
        if (!tmp.isModifiable()) {
            this.endpoints_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEndpoints(int index, Endpoint value) {
        value.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEndpoints(Endpoint value) {
        value.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEndpoints(int index, Endpoint value) {
        value.getClass();
        ensureEndpointsIsMutable();
        this.endpoints_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllEndpoints(Iterable<? extends Endpoint> values) {
        ensureEndpointsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.endpoints_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearEndpoints() {
        this.endpoints_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeEndpoints(int index) {
        ensureEndpointsIsMutable();
        this.endpoints_.remove(index);
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasControl() {
        return this.control_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Control getControl() {
        return this.control_ == null ? Control.getDefaultInstance() : this.control_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setControl(Control value) {
        value.getClass();
        this.control_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeControl(Control value) {
        value.getClass();
        if (this.control_ != null && this.control_ != Control.getDefaultInstance()) {
            this.control_ = Control.newBuilder(this.control_).mergeFrom(value).buildPartial();
        } else {
            this.control_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearControl() {
        this.control_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<LogDescriptor> getLogsList() {
        return this.logs_;
    }

    public List<? extends LogDescriptorOrBuilder> getLogsOrBuilderList() {
        return this.logs_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getLogsCount() {
        return this.logs_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public LogDescriptor getLogs(int index) {
        return this.logs_.get(index);
    }

    public LogDescriptorOrBuilder getLogsOrBuilder(int index) {
        return this.logs_.get(index);
    }

    private void ensureLogsIsMutable() {
        Internal.ProtobufList<LogDescriptor> tmp = this.logs_;
        if (!tmp.isModifiable()) {
            this.logs_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLogs(int index, LogDescriptor value) {
        value.getClass();
        ensureLogsIsMutable();
        this.logs_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLogs(LogDescriptor value) {
        value.getClass();
        ensureLogsIsMutable();
        this.logs_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLogs(int index, LogDescriptor value) {
        value.getClass();
        ensureLogsIsMutable();
        this.logs_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllLogs(Iterable<? extends LogDescriptor> values) {
        ensureLogsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.logs_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLogs() {
        this.logs_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeLogs(int index) {
        ensureLogsIsMutable();
        this.logs_.remove(index);
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<MetricDescriptor> getMetricsList() {
        return this.metrics_;
    }

    public List<? extends MetricDescriptorOrBuilder> getMetricsOrBuilderList() {
        return this.metrics_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getMetricsCount() {
        return this.metrics_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public MetricDescriptor getMetrics(int index) {
        return this.metrics_.get(index);
    }

    public MetricDescriptorOrBuilder getMetricsOrBuilder(int index) {
        return this.metrics_.get(index);
    }

    private void ensureMetricsIsMutable() {
        Internal.ProtobufList<MetricDescriptor> tmp = this.metrics_;
        if (!tmp.isModifiable()) {
            this.metrics_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetrics(int index, MetricDescriptor value) {
        value.getClass();
        ensureMetricsIsMutable();
        this.metrics_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMetrics(MetricDescriptor value) {
        value.getClass();
        ensureMetricsIsMutable();
        this.metrics_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMetrics(int index, MetricDescriptor value) {
        value.getClass();
        ensureMetricsIsMutable();
        this.metrics_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllMetrics(Iterable<? extends MetricDescriptor> values) {
        ensureMetricsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.metrics_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMetrics() {
        this.metrics_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeMetrics(int index) {
        ensureMetricsIsMutable();
        this.metrics_.remove(index);
    }

    @Override // com.google.api.ServiceOrBuilder
    public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
        return this.monitoredResources_;
    }

    public List<? extends MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesOrBuilderList() {
        return this.monitoredResources_;
    }

    @Override // com.google.api.ServiceOrBuilder
    public int getMonitoredResourcesCount() {
        return this.monitoredResources_.size();
    }

    @Override // com.google.api.ServiceOrBuilder
    public MonitoredResourceDescriptor getMonitoredResources(int index) {
        return this.monitoredResources_.get(index);
    }

    public MonitoredResourceDescriptorOrBuilder getMonitoredResourcesOrBuilder(int index) {
        return this.monitoredResources_.get(index);
    }

    private void ensureMonitoredResourcesIsMutable() {
        Internal.ProtobufList<MonitoredResourceDescriptor> tmp = this.monitoredResources_;
        if (!tmp.isModifiable()) {
            this.monitoredResources_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMonitoredResources(int index, MonitoredResourceDescriptor value) {
        value.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMonitoredResources(MonitoredResourceDescriptor value) {
        value.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMonitoredResources(int index, MonitoredResourceDescriptor value) {
        value.getClass();
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllMonitoredResources(Iterable<? extends MonitoredResourceDescriptor> values) {
        ensureMonitoredResourcesIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.monitoredResources_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMonitoredResources() {
        this.monitoredResources_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeMonitoredResources(int index) {
        ensureMonitoredResourcesIsMutable();
        this.monitoredResources_.remove(index);
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasBilling() {
        return this.billing_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Billing getBilling() {
        return this.billing_ == null ? Billing.getDefaultInstance() : this.billing_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBilling(Billing value) {
        value.getClass();
        this.billing_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeBilling(Billing value) {
        value.getClass();
        if (this.billing_ != null && this.billing_ != Billing.getDefaultInstance()) {
            this.billing_ = Billing.newBuilder(this.billing_).mergeFrom(value).buildPartial();
        } else {
            this.billing_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBilling() {
        this.billing_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasLogging() {
        return this.logging_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Logging getLogging() {
        return this.logging_ == null ? Logging.getDefaultInstance() : this.logging_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLogging(Logging value) {
        value.getClass();
        this.logging_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLogging(Logging value) {
        value.getClass();
        if (this.logging_ != null && this.logging_ != Logging.getDefaultInstance()) {
            this.logging_ = Logging.newBuilder(this.logging_).mergeFrom(value).buildPartial();
        } else {
            this.logging_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLogging() {
        this.logging_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasMonitoring() {
        return this.monitoring_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public Monitoring getMonitoring() {
        return this.monitoring_ == null ? Monitoring.getDefaultInstance() : this.monitoring_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMonitoring(Monitoring value) {
        value.getClass();
        this.monitoring_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMonitoring(Monitoring value) {
        value.getClass();
        if (this.monitoring_ != null && this.monitoring_ != Monitoring.getDefaultInstance()) {
            this.monitoring_ = Monitoring.newBuilder(this.monitoring_).mergeFrom(value).buildPartial();
        } else {
            this.monitoring_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMonitoring() {
        this.monitoring_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasSystemParameters() {
        return this.systemParameters_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public SystemParameters getSystemParameters() {
        return this.systemParameters_ == null ? SystemParameters.getDefaultInstance() : this.systemParameters_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemParameters(SystemParameters value) {
        value.getClass();
        this.systemParameters_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSystemParameters(SystemParameters value) {
        value.getClass();
        if (this.systemParameters_ != null && this.systemParameters_ != SystemParameters.getDefaultInstance()) {
            this.systemParameters_ = SystemParameters.newBuilder(this.systemParameters_).mergeFrom(value).buildPartial();
        } else {
            this.systemParameters_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSystemParameters() {
        this.systemParameters_ = null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public boolean hasSourceInfo() {
        return this.sourceInfo_ != null;
    }

    @Override // com.google.api.ServiceOrBuilder
    public SourceInfo getSourceInfo() {
        return this.sourceInfo_ == null ? SourceInfo.getDefaultInstance() : this.sourceInfo_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSourceInfo(SourceInfo value) {
        value.getClass();
        this.sourceInfo_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSourceInfo(SourceInfo value) {
        value.getClass();
        if (this.sourceInfo_ != null && this.sourceInfo_ != SourceInfo.getDefaultInstance()) {
            this.sourceInfo_ = SourceInfo.newBuilder(this.sourceInfo_).mergeFrom(value).buildPartial();
        } else {
            this.sourceInfo_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSourceInfo() {
        this.sourceInfo_ = null;
    }

    public static Service parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Service parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Service parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Service parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Service parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Service parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Service parseFrom(InputStream input) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Service parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Service parseDelimitedFrom(InputStream input) throws IOException {
        return (Service) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Service parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Service) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Service parseFrom(CodedInputStream input) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Service parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Service) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Service prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Service, Builder> implements ServiceOrBuilder {
        private Builder() {
            super(Service.DEFAULT_INSTANCE);
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasConfigVersion() {
            return ((Service) this.instance).hasConfigVersion();
        }

        @Override // com.google.api.ServiceOrBuilder
        public UInt32Value getConfigVersion() {
            return ((Service) this.instance).getConfigVersion();
        }

        public Builder setConfigVersion(UInt32Value value) {
            copyOnWrite();
            ((Service) this.instance).setConfigVersion(value);
            return this;
        }

        public Builder setConfigVersion(UInt32Value.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setConfigVersion(builderForValue.build());
            return this;
        }

        public Builder mergeConfigVersion(UInt32Value value) {
            copyOnWrite();
            ((Service) this.instance).mergeConfigVersion(value);
            return this;
        }

        public Builder clearConfigVersion() {
            copyOnWrite();
            ((Service) this.instance).clearConfigVersion();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getName() {
            return ((Service) this.instance).getName();
        }

        @Override // com.google.api.ServiceOrBuilder
        public ByteString getNameBytes() {
            return ((Service) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((Service) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Service) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((Service) this.instance).setNameBytes(value);
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getId() {
            return ((Service) this.instance).getId();
        }

        @Override // com.google.api.ServiceOrBuilder
        public ByteString getIdBytes() {
            return ((Service) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((Service) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((Service) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((Service) this.instance).setIdBytes(value);
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getTitle() {
            return ((Service) this.instance).getTitle();
        }

        @Override // com.google.api.ServiceOrBuilder
        public ByteString getTitleBytes() {
            return ((Service) this.instance).getTitleBytes();
        }

        public Builder setTitle(String value) {
            copyOnWrite();
            ((Service) this.instance).setTitle(value);
            return this;
        }

        public Builder clearTitle() {
            copyOnWrite();
            ((Service) this.instance).clearTitle();
            return this;
        }

        public Builder setTitleBytes(ByteString value) {
            copyOnWrite();
            ((Service) this.instance).setTitleBytes(value);
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public String getProducerProjectId() {
            return ((Service) this.instance).getProducerProjectId();
        }

        @Override // com.google.api.ServiceOrBuilder
        public ByteString getProducerProjectIdBytes() {
            return ((Service) this.instance).getProducerProjectIdBytes();
        }

        public Builder setProducerProjectId(String value) {
            copyOnWrite();
            ((Service) this.instance).setProducerProjectId(value);
            return this;
        }

        public Builder clearProducerProjectId() {
            copyOnWrite();
            ((Service) this.instance).clearProducerProjectId();
            return this;
        }

        public Builder setProducerProjectIdBytes(ByteString value) {
            copyOnWrite();
            ((Service) this.instance).setProducerProjectIdBytes(value);
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Api> getApisList() {
            return Collections.unmodifiableList(((Service) this.instance).getApisList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getApisCount() {
            return ((Service) this.instance).getApisCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Api getApis(int index) {
            return ((Service) this.instance).getApis(index);
        }

        public Builder setApis(int index, Api value) {
            copyOnWrite();
            ((Service) this.instance).setApis(index, value);
            return this;
        }

        public Builder setApis(int index, Api.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setApis(index, builderForValue.build());
            return this;
        }

        public Builder addApis(Api value) {
            copyOnWrite();
            ((Service) this.instance).addApis(value);
            return this;
        }

        public Builder addApis(int index, Api value) {
            copyOnWrite();
            ((Service) this.instance).addApis(index, value);
            return this;
        }

        public Builder addApis(Api.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addApis(builderForValue.build());
            return this;
        }

        public Builder addApis(int index, Api.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addApis(index, builderForValue.build());
            return this;
        }

        public Builder addAllApis(Iterable<? extends Api> values) {
            copyOnWrite();
            ((Service) this.instance).addAllApis(values);
            return this;
        }

        public Builder clearApis() {
            copyOnWrite();
            ((Service) this.instance).clearApis();
            return this;
        }

        public Builder removeApis(int index) {
            copyOnWrite();
            ((Service) this.instance).removeApis(index);
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Type> getTypesList() {
            return Collections.unmodifiableList(((Service) this.instance).getTypesList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getTypesCount() {
            return ((Service) this.instance).getTypesCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Type getTypes(int index) {
            return ((Service) this.instance).getTypes(index);
        }

        public Builder setTypes(int index, Type value) {
            copyOnWrite();
            ((Service) this.instance).setTypes(index, value);
            return this;
        }

        public Builder setTypes(int index, Type.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setTypes(index, builderForValue.build());
            return this;
        }

        public Builder addTypes(Type value) {
            copyOnWrite();
            ((Service) this.instance).addTypes(value);
            return this;
        }

        public Builder addTypes(int index, Type value) {
            copyOnWrite();
            ((Service) this.instance).addTypes(index, value);
            return this;
        }

        public Builder addTypes(Type.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addTypes(builderForValue.build());
            return this;
        }

        public Builder addTypes(int index, Type.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addTypes(index, builderForValue.build());
            return this;
        }

        public Builder addAllTypes(Iterable<? extends Type> values) {
            copyOnWrite();
            ((Service) this.instance).addAllTypes(values);
            return this;
        }

        public Builder clearTypes() {
            copyOnWrite();
            ((Service) this.instance).clearTypes();
            return this;
        }

        public Builder removeTypes(int index) {
            copyOnWrite();
            ((Service) this.instance).removeTypes(index);
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Enum> getEnumsList() {
            return Collections.unmodifiableList(((Service) this.instance).getEnumsList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getEnumsCount() {
            return ((Service) this.instance).getEnumsCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Enum getEnums(int index) {
            return ((Service) this.instance).getEnums(index);
        }

        public Builder setEnums(int index, Enum value) {
            copyOnWrite();
            ((Service) this.instance).setEnums(index, value);
            return this;
        }

        public Builder setEnums(int index, Enum.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setEnums(index, builderForValue.build());
            return this;
        }

        public Builder addEnums(Enum value) {
            copyOnWrite();
            ((Service) this.instance).addEnums(value);
            return this;
        }

        public Builder addEnums(int index, Enum value) {
            copyOnWrite();
            ((Service) this.instance).addEnums(index, value);
            return this;
        }

        public Builder addEnums(Enum.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addEnums(builderForValue.build());
            return this;
        }

        public Builder addEnums(int index, Enum.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addEnums(index, builderForValue.build());
            return this;
        }

        public Builder addAllEnums(Iterable<? extends Enum> values) {
            copyOnWrite();
            ((Service) this.instance).addAllEnums(values);
            return this;
        }

        public Builder clearEnums() {
            copyOnWrite();
            ((Service) this.instance).clearEnums();
            return this;
        }

        public Builder removeEnums(int index) {
            copyOnWrite();
            ((Service) this.instance).removeEnums(index);
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasDocumentation() {
            return ((Service) this.instance).hasDocumentation();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Documentation getDocumentation() {
            return ((Service) this.instance).getDocumentation();
        }

        public Builder setDocumentation(Documentation value) {
            copyOnWrite();
            ((Service) this.instance).setDocumentation(value);
            return this;
        }

        public Builder setDocumentation(Documentation.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setDocumentation(builderForValue.build());
            return this;
        }

        public Builder mergeDocumentation(Documentation value) {
            copyOnWrite();
            ((Service) this.instance).mergeDocumentation(value);
            return this;
        }

        public Builder clearDocumentation() {
            copyOnWrite();
            ((Service) this.instance).clearDocumentation();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasBackend() {
            return ((Service) this.instance).hasBackend();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Backend getBackend() {
            return ((Service) this.instance).getBackend();
        }

        public Builder setBackend(Backend value) {
            copyOnWrite();
            ((Service) this.instance).setBackend(value);
            return this;
        }

        public Builder setBackend(Backend.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setBackend(builderForValue.build());
            return this;
        }

        public Builder mergeBackend(Backend value) {
            copyOnWrite();
            ((Service) this.instance).mergeBackend(value);
            return this;
        }

        public Builder clearBackend() {
            copyOnWrite();
            ((Service) this.instance).clearBackend();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasHttp() {
            return ((Service) this.instance).hasHttp();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Http getHttp() {
            return ((Service) this.instance).getHttp();
        }

        public Builder setHttp(Http value) {
            copyOnWrite();
            ((Service) this.instance).setHttp(value);
            return this;
        }

        public Builder setHttp(Http.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setHttp(builderForValue.build());
            return this;
        }

        public Builder mergeHttp(Http value) {
            copyOnWrite();
            ((Service) this.instance).mergeHttp(value);
            return this;
        }

        public Builder clearHttp() {
            copyOnWrite();
            ((Service) this.instance).clearHttp();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasQuota() {
            return ((Service) this.instance).hasQuota();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Quota getQuota() {
            return ((Service) this.instance).getQuota();
        }

        public Builder setQuota(Quota value) {
            copyOnWrite();
            ((Service) this.instance).setQuota(value);
            return this;
        }

        public Builder setQuota(Quota.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setQuota(builderForValue.build());
            return this;
        }

        public Builder mergeQuota(Quota value) {
            copyOnWrite();
            ((Service) this.instance).mergeQuota(value);
            return this;
        }

        public Builder clearQuota() {
            copyOnWrite();
            ((Service) this.instance).clearQuota();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasAuthentication() {
            return ((Service) this.instance).hasAuthentication();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Authentication getAuthentication() {
            return ((Service) this.instance).getAuthentication();
        }

        public Builder setAuthentication(Authentication value) {
            copyOnWrite();
            ((Service) this.instance).setAuthentication(value);
            return this;
        }

        public Builder setAuthentication(Authentication.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setAuthentication(builderForValue.build());
            return this;
        }

        public Builder mergeAuthentication(Authentication value) {
            copyOnWrite();
            ((Service) this.instance).mergeAuthentication(value);
            return this;
        }

        public Builder clearAuthentication() {
            copyOnWrite();
            ((Service) this.instance).clearAuthentication();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasContext() {
            return ((Service) this.instance).hasContext();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Context getContext() {
            return ((Service) this.instance).getContext();
        }

        public Builder setContext(Context value) {
            copyOnWrite();
            ((Service) this.instance).setContext(value);
            return this;
        }

        public Builder setContext(Context.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setContext(builderForValue.build());
            return this;
        }

        public Builder mergeContext(Context value) {
            copyOnWrite();
            ((Service) this.instance).mergeContext(value);
            return this;
        }

        public Builder clearContext() {
            copyOnWrite();
            ((Service) this.instance).clearContext();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasUsage() {
            return ((Service) this.instance).hasUsage();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Usage getUsage() {
            return ((Service) this.instance).getUsage();
        }

        public Builder setUsage(Usage value) {
            copyOnWrite();
            ((Service) this.instance).setUsage(value);
            return this;
        }

        public Builder setUsage(Usage.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setUsage(builderForValue.build());
            return this;
        }

        public Builder mergeUsage(Usage value) {
            copyOnWrite();
            ((Service) this.instance).mergeUsage(value);
            return this;
        }

        public Builder clearUsage() {
            copyOnWrite();
            ((Service) this.instance).clearUsage();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<Endpoint> getEndpointsList() {
            return Collections.unmodifiableList(((Service) this.instance).getEndpointsList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getEndpointsCount() {
            return ((Service) this.instance).getEndpointsCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Endpoint getEndpoints(int index) {
            return ((Service) this.instance).getEndpoints(index);
        }

        public Builder setEndpoints(int index, Endpoint value) {
            copyOnWrite();
            ((Service) this.instance).setEndpoints(index, value);
            return this;
        }

        public Builder setEndpoints(int index, Endpoint.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setEndpoints(index, builderForValue.build());
            return this;
        }

        public Builder addEndpoints(Endpoint value) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(value);
            return this;
        }

        public Builder addEndpoints(int index, Endpoint value) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(index, value);
            return this;
        }

        public Builder addEndpoints(Endpoint.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(builderForValue.build());
            return this;
        }

        public Builder addEndpoints(int index, Endpoint.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addEndpoints(index, builderForValue.build());
            return this;
        }

        public Builder addAllEndpoints(Iterable<? extends Endpoint> values) {
            copyOnWrite();
            ((Service) this.instance).addAllEndpoints(values);
            return this;
        }

        public Builder clearEndpoints() {
            copyOnWrite();
            ((Service) this.instance).clearEndpoints();
            return this;
        }

        public Builder removeEndpoints(int index) {
            copyOnWrite();
            ((Service) this.instance).removeEndpoints(index);
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasControl() {
            return ((Service) this.instance).hasControl();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Control getControl() {
            return ((Service) this.instance).getControl();
        }

        public Builder setControl(Control value) {
            copyOnWrite();
            ((Service) this.instance).setControl(value);
            return this;
        }

        public Builder setControl(Control.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setControl(builderForValue.build());
            return this;
        }

        public Builder mergeControl(Control value) {
            copyOnWrite();
            ((Service) this.instance).mergeControl(value);
            return this;
        }

        public Builder clearControl() {
            copyOnWrite();
            ((Service) this.instance).clearControl();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<LogDescriptor> getLogsList() {
            return Collections.unmodifiableList(((Service) this.instance).getLogsList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getLogsCount() {
            return ((Service) this.instance).getLogsCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public LogDescriptor getLogs(int index) {
            return ((Service) this.instance).getLogs(index);
        }

        public Builder setLogs(int index, LogDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).setLogs(index, value);
            return this;
        }

        public Builder setLogs(int index, LogDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setLogs(index, builderForValue.build());
            return this;
        }

        public Builder addLogs(LogDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addLogs(value);
            return this;
        }

        public Builder addLogs(int index, LogDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addLogs(index, value);
            return this;
        }

        public Builder addLogs(LogDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addLogs(builderForValue.build());
            return this;
        }

        public Builder addLogs(int index, LogDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addLogs(index, builderForValue.build());
            return this;
        }

        public Builder addAllLogs(Iterable<? extends LogDescriptor> values) {
            copyOnWrite();
            ((Service) this.instance).addAllLogs(values);
            return this;
        }

        public Builder clearLogs() {
            copyOnWrite();
            ((Service) this.instance).clearLogs();
            return this;
        }

        public Builder removeLogs(int index) {
            copyOnWrite();
            ((Service) this.instance).removeLogs(index);
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<MetricDescriptor> getMetricsList() {
            return Collections.unmodifiableList(((Service) this.instance).getMetricsList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getMetricsCount() {
            return ((Service) this.instance).getMetricsCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public MetricDescriptor getMetrics(int index) {
            return ((Service) this.instance).getMetrics(index);
        }

        public Builder setMetrics(int index, MetricDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).setMetrics(index, value);
            return this;
        }

        public Builder setMetrics(int index, MetricDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setMetrics(index, builderForValue.build());
            return this;
        }

        public Builder addMetrics(MetricDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(value);
            return this;
        }

        public Builder addMetrics(int index, MetricDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(index, value);
            return this;
        }

        public Builder addMetrics(MetricDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(builderForValue.build());
            return this;
        }

        public Builder addMetrics(int index, MetricDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addMetrics(index, builderForValue.build());
            return this;
        }

        public Builder addAllMetrics(Iterable<? extends MetricDescriptor> values) {
            copyOnWrite();
            ((Service) this.instance).addAllMetrics(values);
            return this;
        }

        public Builder clearMetrics() {
            copyOnWrite();
            ((Service) this.instance).clearMetrics();
            return this;
        }

        public Builder removeMetrics(int index) {
            copyOnWrite();
            ((Service) this.instance).removeMetrics(index);
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
            return Collections.unmodifiableList(((Service) this.instance).getMonitoredResourcesList());
        }

        @Override // com.google.api.ServiceOrBuilder
        public int getMonitoredResourcesCount() {
            return ((Service) this.instance).getMonitoredResourcesCount();
        }

        @Override // com.google.api.ServiceOrBuilder
        public MonitoredResourceDescriptor getMonitoredResources(int index) {
            return ((Service) this.instance).getMonitoredResources(index);
        }

        public Builder setMonitoredResources(int index, MonitoredResourceDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).setMonitoredResources(index, value);
            return this;
        }

        public Builder setMonitoredResources(int index, MonitoredResourceDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setMonitoredResources(index, builderForValue.build());
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(value);
            return this;
        }

        public Builder addMonitoredResources(int index, MonitoredResourceDescriptor value) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(index, value);
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(builderForValue.build());
            return this;
        }

        public Builder addMonitoredResources(int index, MonitoredResourceDescriptor.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).addMonitoredResources(index, builderForValue.build());
            return this;
        }

        public Builder addAllMonitoredResources(Iterable<? extends MonitoredResourceDescriptor> values) {
            copyOnWrite();
            ((Service) this.instance).addAllMonitoredResources(values);
            return this;
        }

        public Builder clearMonitoredResources() {
            copyOnWrite();
            ((Service) this.instance).clearMonitoredResources();
            return this;
        }

        public Builder removeMonitoredResources(int index) {
            copyOnWrite();
            ((Service) this.instance).removeMonitoredResources(index);
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasBilling() {
            return ((Service) this.instance).hasBilling();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Billing getBilling() {
            return ((Service) this.instance).getBilling();
        }

        public Builder setBilling(Billing value) {
            copyOnWrite();
            ((Service) this.instance).setBilling(value);
            return this;
        }

        public Builder setBilling(Billing.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setBilling(builderForValue.build());
            return this;
        }

        public Builder mergeBilling(Billing value) {
            copyOnWrite();
            ((Service) this.instance).mergeBilling(value);
            return this;
        }

        public Builder clearBilling() {
            copyOnWrite();
            ((Service) this.instance).clearBilling();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasLogging() {
            return ((Service) this.instance).hasLogging();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Logging getLogging() {
            return ((Service) this.instance).getLogging();
        }

        public Builder setLogging(Logging value) {
            copyOnWrite();
            ((Service) this.instance).setLogging(value);
            return this;
        }

        public Builder setLogging(Logging.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setLogging(builderForValue.build());
            return this;
        }

        public Builder mergeLogging(Logging value) {
            copyOnWrite();
            ((Service) this.instance).mergeLogging(value);
            return this;
        }

        public Builder clearLogging() {
            copyOnWrite();
            ((Service) this.instance).clearLogging();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasMonitoring() {
            return ((Service) this.instance).hasMonitoring();
        }

        @Override // com.google.api.ServiceOrBuilder
        public Monitoring getMonitoring() {
            return ((Service) this.instance).getMonitoring();
        }

        public Builder setMonitoring(Monitoring value) {
            copyOnWrite();
            ((Service) this.instance).setMonitoring(value);
            return this;
        }

        public Builder setMonitoring(Monitoring.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setMonitoring(builderForValue.build());
            return this;
        }

        public Builder mergeMonitoring(Monitoring value) {
            copyOnWrite();
            ((Service) this.instance).mergeMonitoring(value);
            return this;
        }

        public Builder clearMonitoring() {
            copyOnWrite();
            ((Service) this.instance).clearMonitoring();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasSystemParameters() {
            return ((Service) this.instance).hasSystemParameters();
        }

        @Override // com.google.api.ServiceOrBuilder
        public SystemParameters getSystemParameters() {
            return ((Service) this.instance).getSystemParameters();
        }

        public Builder setSystemParameters(SystemParameters value) {
            copyOnWrite();
            ((Service) this.instance).setSystemParameters(value);
            return this;
        }

        public Builder setSystemParameters(SystemParameters.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setSystemParameters(builderForValue.build());
            return this;
        }

        public Builder mergeSystemParameters(SystemParameters value) {
            copyOnWrite();
            ((Service) this.instance).mergeSystemParameters(value);
            return this;
        }

        public Builder clearSystemParameters() {
            copyOnWrite();
            ((Service) this.instance).clearSystemParameters();
            return this;
        }

        @Override // com.google.api.ServiceOrBuilder
        public boolean hasSourceInfo() {
            return ((Service) this.instance).hasSourceInfo();
        }

        @Override // com.google.api.ServiceOrBuilder
        public SourceInfo getSourceInfo() {
            return ((Service) this.instance).getSourceInfo();
        }

        public Builder setSourceInfo(SourceInfo value) {
            copyOnWrite();
            ((Service) this.instance).setSourceInfo(value);
            return this;
        }

        public Builder setSourceInfo(SourceInfo.Builder builderForValue) {
            copyOnWrite();
            ((Service) this.instance).setSourceInfo(builderForValue.build());
            return this;
        }

        public Builder mergeSourceInfo(SourceInfo value) {
            copyOnWrite();
            ((Service) this.instance).mergeSourceInfo(value);
            return this;
        }

        public Builder clearSourceInfo() {
            copyOnWrite();
            ((Service) this.instance).clearSourceInfo();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new Service();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"name_", "title_", "apis_", Api.class, "types_", Type.class, "enums_", Enum.class, "documentation_", "backend_", "http_", "quota_", "authentication_", "context_", "usage_", "endpoints_", Endpoint.class, "configVersion_", "control_", "producerProjectId_", "logs_", LogDescriptor.class, "metrics_", MetricDescriptor.class, "monitoredResources_", MonitoredResourceDescriptor.class, "billing_", "logging_", "monitoring_", "systemParameters_", "id_", "sourceInfo_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0019\u0000\u0000\u0001%\u0019\u0000\u0007\u0000\u0001Ȉ\u0002Ȉ\u0003\u001b\u0004\u001b\u0005\u001b\u0006\t\b\t\t\t\n\t\u000b\t\f\t\u000f\t\u0012\u001b\u0014\t\u0015\t\u0016Ȉ\u0017\u001b\u0018\u001b\u0019\u001b\u001a\t\u001b\t\u001c\t\u001d\t!Ȉ%\t", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Service> parser = PARSER;
                if (parser == null) {
                    synchronized (Service.class) {
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
        Service defaultInstance = new Service();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Service.class, defaultInstance);
    }

    public static Service getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Service> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
