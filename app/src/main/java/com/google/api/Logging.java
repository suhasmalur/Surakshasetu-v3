package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public final class Logging extends GeneratedMessageLite<Logging, Builder> implements LoggingOrBuilder {
    public static final int CONSUMER_DESTINATIONS_FIELD_NUMBER = 2;
    private static final Logging DEFAULT_INSTANCE;
    private static volatile Parser<Logging> PARSER = null;
    public static final int PRODUCER_DESTINATIONS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<LoggingDestination> producerDestinations_ = emptyProtobufList();
    private Internal.ProtobufList<LoggingDestination> consumerDestinations_ = emptyProtobufList();

    public interface LoggingDestinationOrBuilder extends MessageLiteOrBuilder {
        String getLogs(int i);

        ByteString getLogsBytes(int i);

        int getLogsCount();

        List<String> getLogsList();

        String getMonitoredResource();

        ByteString getMonitoredResourceBytes();
    }

    private Logging() {
    }

    public static final class LoggingDestination extends GeneratedMessageLite<LoggingDestination, Builder> implements LoggingDestinationOrBuilder {
        private static final LoggingDestination DEFAULT_INSTANCE;
        public static final int LOGS_FIELD_NUMBER = 1;
        public static final int MONITORED_RESOURCE_FIELD_NUMBER = 3;
        private static volatile Parser<LoggingDestination> PARSER;
        private String monitoredResource_ = "";
        private Internal.ProtobufList<String> logs_ = GeneratedMessageLite.emptyProtobufList();

        private LoggingDestination() {
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public String getMonitoredResource() {
            return this.monitoredResource_;
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public ByteString getMonitoredResourceBytes() {
            return ByteString.copyFromUtf8(this.monitoredResource_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMonitoredResource(String value) {
            value.getClass();
            this.monitoredResource_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMonitoredResource() {
            this.monitoredResource_ = getDefaultInstance().getMonitoredResource();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMonitoredResourceBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.monitoredResource_ = value.toStringUtf8();
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public List<String> getLogsList() {
            return this.logs_;
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public int getLogsCount() {
            return this.logs_.size();
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public String getLogs(int index) {
            return this.logs_.get(index);
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public ByteString getLogsBytes(int index) {
            return ByteString.copyFromUtf8(this.logs_.get(index));
        }

        private void ensureLogsIsMutable() {
            Internal.ProtobufList<String> tmp = this.logs_;
            if (!tmp.isModifiable()) {
                this.logs_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLogs(int index, String value) {
            value.getClass();
            ensureLogsIsMutable();
            this.logs_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLogs(String value) {
            value.getClass();
            ensureLogsIsMutable();
            this.logs_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllLogs(Iterable<String> values) {
            ensureLogsIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.logs_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLogs() {
            this.logs_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLogsBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            ensureLogsIsMutable();
            this.logs_.add(value.toStringUtf8());
        }

        public static LoggingDestination parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LoggingDestination parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LoggingDestination parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LoggingDestination parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LoggingDestination parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static LoggingDestination parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static LoggingDestination parseFrom(InputStream input) throws IOException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LoggingDestination parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LoggingDestination parseDelimitedFrom(InputStream input) throws IOException {
            return (LoggingDestination) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static LoggingDestination parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LoggingDestination) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static LoggingDestination parseFrom(CodedInputStream input) throws IOException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static LoggingDestination parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (LoggingDestination) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(LoggingDestination prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<LoggingDestination, Builder> implements LoggingDestinationOrBuilder {
            private Builder() {
                super(LoggingDestination.DEFAULT_INSTANCE);
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public String getMonitoredResource() {
                return ((LoggingDestination) this.instance).getMonitoredResource();
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public ByteString getMonitoredResourceBytes() {
                return ((LoggingDestination) this.instance).getMonitoredResourceBytes();
            }

            public Builder setMonitoredResource(String value) {
                copyOnWrite();
                ((LoggingDestination) this.instance).setMonitoredResource(value);
                return this;
            }

            public Builder clearMonitoredResource() {
                copyOnWrite();
                ((LoggingDestination) this.instance).clearMonitoredResource();
                return this;
            }

            public Builder setMonitoredResourceBytes(ByteString value) {
                copyOnWrite();
                ((LoggingDestination) this.instance).setMonitoredResourceBytes(value);
                return this;
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public List<String> getLogsList() {
                return Collections.unmodifiableList(((LoggingDestination) this.instance).getLogsList());
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public int getLogsCount() {
                return ((LoggingDestination) this.instance).getLogsCount();
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public String getLogs(int index) {
                return ((LoggingDestination) this.instance).getLogs(index);
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public ByteString getLogsBytes(int index) {
                return ((LoggingDestination) this.instance).getLogsBytes(index);
            }

            public Builder setLogs(int index, String value) {
                copyOnWrite();
                ((LoggingDestination) this.instance).setLogs(index, value);
                return this;
            }

            public Builder addLogs(String value) {
                copyOnWrite();
                ((LoggingDestination) this.instance).addLogs(value);
                return this;
            }

            public Builder addAllLogs(Iterable<String> values) {
                copyOnWrite();
                ((LoggingDestination) this.instance).addAllLogs(values);
                return this;
            }

            public Builder clearLogs() {
                copyOnWrite();
                ((LoggingDestination) this.instance).clearLogs();
                return this;
            }

            public Builder addLogsBytes(ByteString value) {
                copyOnWrite();
                ((LoggingDestination) this.instance).addLogsBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new LoggingDestination();
                case NEW_BUILDER:
                    return new Builder();
                case BUILD_MESSAGE_INFO:
                    Object[] objects = {"logs_", "monitoredResource_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0001\u0000\u0001Ț\u0003Ȉ", objects);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<LoggingDestination> parser = PARSER;
                    if (parser == null) {
                        synchronized (LoggingDestination.class) {
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
            LoggingDestination defaultInstance = new LoggingDestination();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(LoggingDestination.class, defaultInstance);
        }

        public static LoggingDestination getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LoggingDestination> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.google.api.LoggingOrBuilder
    public List<LoggingDestination> getProducerDestinationsList() {
        return this.producerDestinations_;
    }

    public List<? extends LoggingDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
        return this.producerDestinations_;
    }

    @Override // com.google.api.LoggingOrBuilder
    public int getProducerDestinationsCount() {
        return this.producerDestinations_.size();
    }

    @Override // com.google.api.LoggingOrBuilder
    public LoggingDestination getProducerDestinations(int index) {
        return this.producerDestinations_.get(index);
    }

    public LoggingDestinationOrBuilder getProducerDestinationsOrBuilder(int index) {
        return this.producerDestinations_.get(index);
    }

    private void ensureProducerDestinationsIsMutable() {
        Internal.ProtobufList<LoggingDestination> tmp = this.producerDestinations_;
        if (!tmp.isModifiable()) {
            this.producerDestinations_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProducerDestinations(int index, LoggingDestination value) {
        value.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProducerDestinations(LoggingDestination value) {
        value.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProducerDestinations(int index, LoggingDestination value) {
        value.getClass();
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllProducerDestinations(Iterable<? extends LoggingDestination> values) {
        ensureProducerDestinationsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.producerDestinations_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProducerDestinations() {
        this.producerDestinations_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeProducerDestinations(int index) {
        ensureProducerDestinationsIsMutable();
        this.producerDestinations_.remove(index);
    }

    @Override // com.google.api.LoggingOrBuilder
    public List<LoggingDestination> getConsumerDestinationsList() {
        return this.consumerDestinations_;
    }

    public List<? extends LoggingDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
        return this.consumerDestinations_;
    }

    @Override // com.google.api.LoggingOrBuilder
    public int getConsumerDestinationsCount() {
        return this.consumerDestinations_.size();
    }

    @Override // com.google.api.LoggingOrBuilder
    public LoggingDestination getConsumerDestinations(int index) {
        return this.consumerDestinations_.get(index);
    }

    public LoggingDestinationOrBuilder getConsumerDestinationsOrBuilder(int index) {
        return this.consumerDestinations_.get(index);
    }

    private void ensureConsumerDestinationsIsMutable() {
        Internal.ProtobufList<LoggingDestination> tmp = this.consumerDestinations_;
        if (!tmp.isModifiable()) {
            this.consumerDestinations_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConsumerDestinations(int index, LoggingDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addConsumerDestinations(LoggingDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addConsumerDestinations(int index, LoggingDestination value) {
        value.getClass();
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllConsumerDestinations(Iterable<? extends LoggingDestination> values) {
        ensureConsumerDestinationsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.consumerDestinations_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConsumerDestinations() {
        this.consumerDestinations_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeConsumerDestinations(int index) {
        ensureConsumerDestinationsIsMutable();
        this.consumerDestinations_.remove(index);
    }

    public static Logging parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Logging parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Logging parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Logging parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Logging parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Logging parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Logging parseFrom(InputStream input) throws IOException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Logging parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Logging parseDelimitedFrom(InputStream input) throws IOException {
        return (Logging) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Logging parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Logging) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Logging parseFrom(CodedInputStream input) throws IOException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Logging parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Logging) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Logging prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Logging, Builder> implements LoggingOrBuilder {
        private Builder() {
            super(Logging.DEFAULT_INSTANCE);
        }

        @Override // com.google.api.LoggingOrBuilder
        public List<LoggingDestination> getProducerDestinationsList() {
            return Collections.unmodifiableList(((Logging) this.instance).getProducerDestinationsList());
        }

        @Override // com.google.api.LoggingOrBuilder
        public int getProducerDestinationsCount() {
            return ((Logging) this.instance).getProducerDestinationsCount();
        }

        @Override // com.google.api.LoggingOrBuilder
        public LoggingDestination getProducerDestinations(int index) {
            return ((Logging) this.instance).getProducerDestinations(index);
        }

        public Builder setProducerDestinations(int index, LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).setProducerDestinations(index, value);
            return this;
        }

        public Builder setProducerDestinations(int index, LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).setProducerDestinations(index, builderForValue.build());
            return this;
        }

        public Builder addProducerDestinations(LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations(value);
            return this;
        }

        public Builder addProducerDestinations(int index, LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations(index, value);
            return this;
        }

        public Builder addProducerDestinations(LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations(builderForValue.build());
            return this;
        }

        public Builder addProducerDestinations(int index, LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).addProducerDestinations(index, builderForValue.build());
            return this;
        }

        public Builder addAllProducerDestinations(Iterable<? extends LoggingDestination> values) {
            copyOnWrite();
            ((Logging) this.instance).addAllProducerDestinations(values);
            return this;
        }

        public Builder clearProducerDestinations() {
            copyOnWrite();
            ((Logging) this.instance).clearProducerDestinations();
            return this;
        }

        public Builder removeProducerDestinations(int index) {
            copyOnWrite();
            ((Logging) this.instance).removeProducerDestinations(index);
            return this;
        }

        @Override // com.google.api.LoggingOrBuilder
        public List<LoggingDestination> getConsumerDestinationsList() {
            return Collections.unmodifiableList(((Logging) this.instance).getConsumerDestinationsList());
        }

        @Override // com.google.api.LoggingOrBuilder
        public int getConsumerDestinationsCount() {
            return ((Logging) this.instance).getConsumerDestinationsCount();
        }

        @Override // com.google.api.LoggingOrBuilder
        public LoggingDestination getConsumerDestinations(int index) {
            return ((Logging) this.instance).getConsumerDestinations(index);
        }

        public Builder setConsumerDestinations(int index, LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).setConsumerDestinations(index, value);
            return this;
        }

        public Builder setConsumerDestinations(int index, LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).setConsumerDestinations(index, builderForValue.build());
            return this;
        }

        public Builder addConsumerDestinations(LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations(value);
            return this;
        }

        public Builder addConsumerDestinations(int index, LoggingDestination value) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations(index, value);
            return this;
        }

        public Builder addConsumerDestinations(LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations(builderForValue.build());
            return this;
        }

        public Builder addConsumerDestinations(int index, LoggingDestination.Builder builderForValue) {
            copyOnWrite();
            ((Logging) this.instance).addConsumerDestinations(index, builderForValue.build());
            return this;
        }

        public Builder addAllConsumerDestinations(Iterable<? extends LoggingDestination> values) {
            copyOnWrite();
            ((Logging) this.instance).addAllConsumerDestinations(values);
            return this;
        }

        public Builder clearConsumerDestinations() {
            copyOnWrite();
            ((Logging) this.instance).clearConsumerDestinations();
            return this;
        }

        public Builder removeConsumerDestinations(int index) {
            copyOnWrite();
            ((Logging) this.instance).removeConsumerDestinations(index);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new Logging();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"producerDestinations_", LoggingDestination.class, "consumerDestinations_", LoggingDestination.class};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Logging> parser = PARSER;
                if (parser == null) {
                    synchronized (Logging.class) {
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
        Logging defaultInstance = new Logging();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Logging.class, defaultInstance);
    }

    public static Logging getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Logging> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
