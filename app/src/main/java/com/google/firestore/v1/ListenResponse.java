package com.google.firestore.v1;

import com.google.firestore.v1.DocumentChange;
import com.google.firestore.v1.DocumentDelete;
import com.google.firestore.v1.DocumentRemove;
import com.google.firestore.v1.ExistenceFilter;
import com.google.firestore.v1.TargetChange;
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
public final class ListenResponse extends GeneratedMessageLite<ListenResponse, Builder> implements ListenResponseOrBuilder {
    private static final ListenResponse DEFAULT_INSTANCE;
    public static final int DOCUMENT_CHANGE_FIELD_NUMBER = 3;
    public static final int DOCUMENT_DELETE_FIELD_NUMBER = 4;
    public static final int DOCUMENT_REMOVE_FIELD_NUMBER = 6;
    public static final int FILTER_FIELD_NUMBER = 5;
    private static volatile Parser<ListenResponse> PARSER = null;
    public static final int TARGET_CHANGE_FIELD_NUMBER = 2;
    private int responseTypeCase_ = 0;
    private Object responseType_;

    private ListenResponse() {
    }

    public enum ResponseTypeCase {
        TARGET_CHANGE(2),
        DOCUMENT_CHANGE(3),
        DOCUMENT_DELETE(4),
        DOCUMENT_REMOVE(6),
        FILTER(5),
        RESPONSETYPE_NOT_SET(0);

        private final int value;

        ResponseTypeCase(int value) {
            this.value = value;
        }

        @Deprecated
        public static ResponseTypeCase valueOf(int value) {
            return forNumber(value);
        }

        public static ResponseTypeCase forNumber(int value) {
            switch (value) {
                case 0:
                    return RESPONSETYPE_NOT_SET;
                case 1:
                default:
                    return null;
                case 2:
                    return TARGET_CHANGE;
                case 3:
                    return DOCUMENT_CHANGE;
                case 4:
                    return DOCUMENT_DELETE;
                case 5:
                    return FILTER;
                case 6:
                    return DOCUMENT_REMOVE;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public ResponseTypeCase getResponseTypeCase() {
        return ResponseTypeCase.forNumber(this.responseTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResponseType() {
        this.responseTypeCase_ = 0;
        this.responseType_ = null;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasTargetChange() {
        return this.responseTypeCase_ == 2;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public TargetChange getTargetChange() {
        if (this.responseTypeCase_ == 2) {
            return (TargetChange) this.responseType_;
        }
        return TargetChange.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetChange(TargetChange value) {
        value.getClass();
        this.responseType_ = value;
        this.responseTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeTargetChange(TargetChange value) {
        value.getClass();
        if (this.responseTypeCase_ == 2 && this.responseType_ != TargetChange.getDefaultInstance()) {
            this.responseType_ = TargetChange.newBuilder((TargetChange) this.responseType_).mergeFrom(value).buildPartial();
        } else {
            this.responseType_ = value;
        }
        this.responseTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetChange() {
        if (this.responseTypeCase_ == 2) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasDocumentChange() {
        return this.responseTypeCase_ == 3;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public DocumentChange getDocumentChange() {
        if (this.responseTypeCase_ == 3) {
            return (DocumentChange) this.responseType_;
        }
        return DocumentChange.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentChange(DocumentChange value) {
        value.getClass();
        this.responseType_ = value;
        this.responseTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentChange(DocumentChange value) {
        value.getClass();
        if (this.responseTypeCase_ == 3 && this.responseType_ != DocumentChange.getDefaultInstance()) {
            this.responseType_ = DocumentChange.newBuilder((DocumentChange) this.responseType_).mergeFrom(value).buildPartial();
        } else {
            this.responseType_ = value;
        }
        this.responseTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentChange() {
        if (this.responseTypeCase_ == 3) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasDocumentDelete() {
        return this.responseTypeCase_ == 4;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public DocumentDelete getDocumentDelete() {
        if (this.responseTypeCase_ == 4) {
            return (DocumentDelete) this.responseType_;
        }
        return DocumentDelete.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentDelete(DocumentDelete value) {
        value.getClass();
        this.responseType_ = value;
        this.responseTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentDelete(DocumentDelete value) {
        value.getClass();
        if (this.responseTypeCase_ == 4 && this.responseType_ != DocumentDelete.getDefaultInstance()) {
            this.responseType_ = DocumentDelete.newBuilder((DocumentDelete) this.responseType_).mergeFrom(value).buildPartial();
        } else {
            this.responseType_ = value;
        }
        this.responseTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentDelete() {
        if (this.responseTypeCase_ == 4) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasDocumentRemove() {
        return this.responseTypeCase_ == 6;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public DocumentRemove getDocumentRemove() {
        if (this.responseTypeCase_ == 6) {
            return (DocumentRemove) this.responseType_;
        }
        return DocumentRemove.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentRemove(DocumentRemove value) {
        value.getClass();
        this.responseType_ = value;
        this.responseTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentRemove(DocumentRemove value) {
        value.getClass();
        if (this.responseTypeCase_ == 6 && this.responseType_ != DocumentRemove.getDefaultInstance()) {
            this.responseType_ = DocumentRemove.newBuilder((DocumentRemove) this.responseType_).mergeFrom(value).buildPartial();
        } else {
            this.responseType_ = value;
        }
        this.responseTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentRemove() {
        if (this.responseTypeCase_ == 6) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasFilter() {
        return this.responseTypeCase_ == 5;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public ExistenceFilter getFilter() {
        if (this.responseTypeCase_ == 5) {
            return (ExistenceFilter) this.responseType_;
        }
        return ExistenceFilter.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFilter(ExistenceFilter value) {
        value.getClass();
        this.responseType_ = value;
        this.responseTypeCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeFilter(ExistenceFilter value) {
        value.getClass();
        if (this.responseTypeCase_ == 5 && this.responseType_ != ExistenceFilter.getDefaultInstance()) {
            this.responseType_ = ExistenceFilter.newBuilder((ExistenceFilter) this.responseType_).mergeFrom(value).buildPartial();
        } else {
            this.responseType_ = value;
        }
        this.responseTypeCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFilter() {
        if (this.responseTypeCase_ == 5) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public static ListenResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListenResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListenResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListenResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListenResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListenResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListenResponse parseFrom(InputStream input) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ListenResponse parseDelimitedFrom(InputStream input) throws IOException {
        return (ListenResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ListenResponse parseFrom(CodedInputStream input) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ListenResponse prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ListenResponse, Builder> implements ListenResponseOrBuilder {
        private Builder() {
            super(ListenResponse.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public ResponseTypeCase getResponseTypeCase() {
            return ((ListenResponse) this.instance).getResponseTypeCase();
        }

        public Builder clearResponseType() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearResponseType();
            return this;
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasTargetChange() {
            return ((ListenResponse) this.instance).hasTargetChange();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public TargetChange getTargetChange() {
            return ((ListenResponse) this.instance).getTargetChange();
        }

        public Builder setTargetChange(TargetChange value) {
            copyOnWrite();
            ((ListenResponse) this.instance).setTargetChange(value);
            return this;
        }

        public Builder setTargetChange(TargetChange.Builder builderForValue) {
            copyOnWrite();
            ((ListenResponse) this.instance).setTargetChange(builderForValue.build());
            return this;
        }

        public Builder mergeTargetChange(TargetChange value) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeTargetChange(value);
            return this;
        }

        public Builder clearTargetChange() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearTargetChange();
            return this;
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasDocumentChange() {
            return ((ListenResponse) this.instance).hasDocumentChange();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public DocumentChange getDocumentChange() {
            return ((ListenResponse) this.instance).getDocumentChange();
        }

        public Builder setDocumentChange(DocumentChange value) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentChange(value);
            return this;
        }

        public Builder setDocumentChange(DocumentChange.Builder builderForValue) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentChange(builderForValue.build());
            return this;
        }

        public Builder mergeDocumentChange(DocumentChange value) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentChange(value);
            return this;
        }

        public Builder clearDocumentChange() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentChange();
            return this;
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasDocumentDelete() {
            return ((ListenResponse) this.instance).hasDocumentDelete();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public DocumentDelete getDocumentDelete() {
            return ((ListenResponse) this.instance).getDocumentDelete();
        }

        public Builder setDocumentDelete(DocumentDelete value) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentDelete(value);
            return this;
        }

        public Builder setDocumentDelete(DocumentDelete.Builder builderForValue) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentDelete(builderForValue.build());
            return this;
        }

        public Builder mergeDocumentDelete(DocumentDelete value) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentDelete(value);
            return this;
        }

        public Builder clearDocumentDelete() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentDelete();
            return this;
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasDocumentRemove() {
            return ((ListenResponse) this.instance).hasDocumentRemove();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public DocumentRemove getDocumentRemove() {
            return ((ListenResponse) this.instance).getDocumentRemove();
        }

        public Builder setDocumentRemove(DocumentRemove value) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentRemove(value);
            return this;
        }

        public Builder setDocumentRemove(DocumentRemove.Builder builderForValue) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentRemove(builderForValue.build());
            return this;
        }

        public Builder mergeDocumentRemove(DocumentRemove value) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentRemove(value);
            return this;
        }

        public Builder clearDocumentRemove() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentRemove();
            return this;
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasFilter() {
            return ((ListenResponse) this.instance).hasFilter();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public ExistenceFilter getFilter() {
            return ((ListenResponse) this.instance).getFilter();
        }

        public Builder setFilter(ExistenceFilter value) {
            copyOnWrite();
            ((ListenResponse) this.instance).setFilter(value);
            return this;
        }

        public Builder setFilter(ExistenceFilter.Builder builderForValue) {
            copyOnWrite();
            ((ListenResponse) this.instance).setFilter(builderForValue.build());
            return this;
        }

        public Builder mergeFilter(ExistenceFilter value) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeFilter(value);
            return this;
        }

        public Builder clearFilter() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearFilter();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ListenResponse();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"responseType_", "responseTypeCase_", TargetChange.class, DocumentChange.class, DocumentDelete.class, ExistenceFilter.class, DocumentRemove.class};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0001\u0000\u0002\u0006\u0005\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ListenResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (ListenResponse.class) {
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
        ListenResponse defaultInstance = new ListenResponse();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(ListenResponse.class, defaultInstance);
    }

    public static ListenResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListenResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
