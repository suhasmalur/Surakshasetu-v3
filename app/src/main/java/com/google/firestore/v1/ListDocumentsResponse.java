package com.google.firestore.v1;

import com.google.firestore.v1.Document;
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
public final class ListDocumentsResponse extends GeneratedMessageLite<ListDocumentsResponse, Builder> implements ListDocumentsResponseOrBuilder {
    private static final ListDocumentsResponse DEFAULT_INSTANCE;
    public static final int DOCUMENTS_FIELD_NUMBER = 1;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private static volatile Parser<ListDocumentsResponse> PARSER;
    private Internal.ProtobufList<Document> documents_ = emptyProtobufList();
    private String nextPageToken_ = "";

    private ListDocumentsResponse() {
    }

    @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
    public List<Document> getDocumentsList() {
        return this.documents_;
    }

    public List<? extends DocumentOrBuilder> getDocumentsOrBuilderList() {
        return this.documents_;
    }

    @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
    public int getDocumentsCount() {
        return this.documents_.size();
    }

    @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
    public Document getDocuments(int index) {
        return this.documents_.get(index);
    }

    public DocumentOrBuilder getDocumentsOrBuilder(int index) {
        return this.documents_.get(index);
    }

    private void ensureDocumentsIsMutable() {
        Internal.ProtobufList<Document> tmp = this.documents_;
        if (!tmp.isModifiable()) {
            this.documents_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocuments(int index, Document value) {
        value.getClass();
        ensureDocumentsIsMutable();
        this.documents_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDocuments(Document value) {
        value.getClass();
        ensureDocumentsIsMutable();
        this.documents_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDocuments(int index, Document value) {
        value.getClass();
        ensureDocumentsIsMutable();
        this.documents_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllDocuments(Iterable<? extends Document> values) {
        ensureDocumentsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.documents_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocuments() {
        this.documents_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDocuments(int index) {
        ensureDocumentsIsMutable();
        this.documents_.remove(index);
    }

    @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
    public String getNextPageToken() {
        return this.nextPageToken_;
    }

    @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
    public ByteString getNextPageTokenBytes() {
        return ByteString.copyFromUtf8(this.nextPageToken_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNextPageToken(String value) {
        value.getClass();
        this.nextPageToken_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNextPageToken() {
        this.nextPageToken_ = getDefaultInstance().getNextPageToken();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNextPageTokenBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.nextPageToken_ = value.toStringUtf8();
    }

    public static ListDocumentsResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListDocumentsResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListDocumentsResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListDocumentsResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListDocumentsResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListDocumentsResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListDocumentsResponse parseFrom(InputStream input) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ListDocumentsResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ListDocumentsResponse parseDelimitedFrom(InputStream input) throws IOException {
        return (ListDocumentsResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ListDocumentsResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListDocumentsResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ListDocumentsResponse parseFrom(CodedInputStream input) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ListDocumentsResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ListDocumentsResponse prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ListDocumentsResponse, Builder> implements ListDocumentsResponseOrBuilder {
        private Builder() {
            super(ListDocumentsResponse.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
        public List<Document> getDocumentsList() {
            return Collections.unmodifiableList(((ListDocumentsResponse) this.instance).getDocumentsList());
        }

        @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
        public int getDocumentsCount() {
            return ((ListDocumentsResponse) this.instance).getDocumentsCount();
        }

        @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
        public Document getDocuments(int index) {
            return ((ListDocumentsResponse) this.instance).getDocuments(index);
        }

        public Builder setDocuments(int index, Document value) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setDocuments(index, value);
            return this;
        }

        public Builder setDocuments(int index, Document.Builder builderForValue) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setDocuments(index, builderForValue.build());
            return this;
        }

        public Builder addDocuments(Document value) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments(value);
            return this;
        }

        public Builder addDocuments(int index, Document value) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments(index, value);
            return this;
        }

        public Builder addDocuments(Document.Builder builderForValue) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments(builderForValue.build());
            return this;
        }

        public Builder addDocuments(int index, Document.Builder builderForValue) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments(index, builderForValue.build());
            return this;
        }

        public Builder addAllDocuments(Iterable<? extends Document> values) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addAllDocuments(values);
            return this;
        }

        public Builder clearDocuments() {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).clearDocuments();
            return this;
        }

        public Builder removeDocuments(int index) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).removeDocuments(index);
            return this;
        }

        @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
        public String getNextPageToken() {
            return ((ListDocumentsResponse) this.instance).getNextPageToken();
        }

        @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
        public ByteString getNextPageTokenBytes() {
            return ((ListDocumentsResponse) this.instance).getNextPageTokenBytes();
        }

        public Builder setNextPageToken(String value) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setNextPageToken(value);
            return this;
        }

        public Builder clearNextPageToken() {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).clearNextPageToken();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString value) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setNextPageTokenBytes(value);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ListDocumentsResponse();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"documents_", Document.class, "nextPageToken_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002Ȉ", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ListDocumentsResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (ListDocumentsResponse.class) {
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
        ListDocumentsResponse defaultInstance = new ListDocumentsResponse();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(ListDocumentsResponse.class, defaultInstance);
    }

    public static ListDocumentsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListDocumentsResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
