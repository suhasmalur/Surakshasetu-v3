package com.google.firestore.bundle;

import com.google.firestore.bundle.BundleMetadata;
import com.google.firestore.bundle.BundledDocumentMetadata;
import com.google.firestore.bundle.NamedQuery;
import com.google.firestore.v1.Document;
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
public final class BundleElement extends GeneratedMessageLite<BundleElement, Builder> implements BundleElementOrBuilder {
    private static final BundleElement DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 4;
    public static final int DOCUMENT_METADATA_FIELD_NUMBER = 3;
    public static final int METADATA_FIELD_NUMBER = 1;
    public static final int NAMED_QUERY_FIELD_NUMBER = 2;
    private static volatile Parser<BundleElement> PARSER;
    private int elementTypeCase_ = 0;
    private Object elementType_;

    private BundleElement() {
    }

    public enum ElementTypeCase {
        METADATA(1),
        NAMED_QUERY(2),
        DOCUMENT_METADATA(3),
        DOCUMENT(4),
        ELEMENTTYPE_NOT_SET(0);

        private final int value;

        ElementTypeCase(int value) {
            this.value = value;
        }

        @Deprecated
        public static ElementTypeCase valueOf(int value) {
            return forNumber(value);
        }

        public static ElementTypeCase forNumber(int value) {
            switch (value) {
                case 0:
                    return ELEMENTTYPE_NOT_SET;
                case 1:
                    return METADATA;
                case 2:
                    return NAMED_QUERY;
                case 3:
                    return DOCUMENT_METADATA;
                case 4:
                    return DOCUMENT;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public ElementTypeCase getElementTypeCase() {
        return ElementTypeCase.forNumber(this.elementTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearElementType() {
        this.elementTypeCase_ = 0;
        this.elementType_ = null;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasMetadata() {
        return this.elementTypeCase_ == 1;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public BundleMetadata getMetadata() {
        if (this.elementTypeCase_ == 1) {
            return (BundleMetadata) this.elementType_;
        }
        return BundleMetadata.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetadata(BundleMetadata value) {
        value.getClass();
        this.elementType_ = value;
        this.elementTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMetadata(BundleMetadata value) {
        value.getClass();
        if (this.elementTypeCase_ == 1 && this.elementType_ != BundleMetadata.getDefaultInstance()) {
            this.elementType_ = BundleMetadata.newBuilder((BundleMetadata) this.elementType_).mergeFrom(value).buildPartial();
        } else {
            this.elementType_ = value;
        }
        this.elementTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMetadata() {
        if (this.elementTypeCase_ == 1) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasNamedQuery() {
        return this.elementTypeCase_ == 2;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public NamedQuery getNamedQuery() {
        if (this.elementTypeCase_ == 2) {
            return (NamedQuery) this.elementType_;
        }
        return NamedQuery.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNamedQuery(NamedQuery value) {
        value.getClass();
        this.elementType_ = value;
        this.elementTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeNamedQuery(NamedQuery value) {
        value.getClass();
        if (this.elementTypeCase_ == 2 && this.elementType_ != NamedQuery.getDefaultInstance()) {
            this.elementType_ = NamedQuery.newBuilder((NamedQuery) this.elementType_).mergeFrom(value).buildPartial();
        } else {
            this.elementType_ = value;
        }
        this.elementTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNamedQuery() {
        if (this.elementTypeCase_ == 2) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasDocumentMetadata() {
        return this.elementTypeCase_ == 3;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public BundledDocumentMetadata getDocumentMetadata() {
        if (this.elementTypeCase_ == 3) {
            return (BundledDocumentMetadata) this.elementType_;
        }
        return BundledDocumentMetadata.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentMetadata(BundledDocumentMetadata value) {
        value.getClass();
        this.elementType_ = value;
        this.elementTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentMetadata(BundledDocumentMetadata value) {
        value.getClass();
        if (this.elementTypeCase_ == 3 && this.elementType_ != BundledDocumentMetadata.getDefaultInstance()) {
            this.elementType_ = BundledDocumentMetadata.newBuilder((BundledDocumentMetadata) this.elementType_).mergeFrom(value).buildPartial();
        } else {
            this.elementType_ = value;
        }
        this.elementTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentMetadata() {
        if (this.elementTypeCase_ == 3) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasDocument() {
        return this.elementTypeCase_ == 4;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public Document getDocument() {
        if (this.elementTypeCase_ == 4) {
            return (Document) this.elementType_;
        }
        return Document.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocument(Document value) {
        value.getClass();
        this.elementType_ = value;
        this.elementTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocument(Document value) {
        value.getClass();
        if (this.elementTypeCase_ == 4 && this.elementType_ != Document.getDefaultInstance()) {
            this.elementType_ = Document.newBuilder((Document) this.elementType_).mergeFrom(value).buildPartial();
        } else {
            this.elementType_ = value;
        }
        this.elementTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocument() {
        if (this.elementTypeCase_ == 4) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    public static BundleElement parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundleElement parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundleElement parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundleElement parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundleElement parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundleElement parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundleElement parseFrom(InputStream input) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundleElement parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundleElement parseDelimitedFrom(InputStream input) throws IOException {
        return (BundleElement) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BundleElement parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundleElement) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundleElement parseFrom(CodedInputStream input) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundleElement parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BundleElement prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BundleElement, Builder> implements BundleElementOrBuilder {
        private Builder() {
            super(BundleElement.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public ElementTypeCase getElementTypeCase() {
            return ((BundleElement) this.instance).getElementTypeCase();
        }

        public Builder clearElementType() {
            copyOnWrite();
            ((BundleElement) this.instance).clearElementType();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasMetadata() {
            return ((BundleElement) this.instance).hasMetadata();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public BundleMetadata getMetadata() {
            return ((BundleElement) this.instance).getMetadata();
        }

        public Builder setMetadata(BundleMetadata value) {
            copyOnWrite();
            ((BundleElement) this.instance).setMetadata(value);
            return this;
        }

        public Builder setMetadata(BundleMetadata.Builder builderForValue) {
            copyOnWrite();
            ((BundleElement) this.instance).setMetadata(builderForValue.build());
            return this;
        }

        public Builder mergeMetadata(BundleMetadata value) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeMetadata(value);
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((BundleElement) this.instance).clearMetadata();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasNamedQuery() {
            return ((BundleElement) this.instance).hasNamedQuery();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public NamedQuery getNamedQuery() {
            return ((BundleElement) this.instance).getNamedQuery();
        }

        public Builder setNamedQuery(NamedQuery value) {
            copyOnWrite();
            ((BundleElement) this.instance).setNamedQuery(value);
            return this;
        }

        public Builder setNamedQuery(NamedQuery.Builder builderForValue) {
            copyOnWrite();
            ((BundleElement) this.instance).setNamedQuery(builderForValue.build());
            return this;
        }

        public Builder mergeNamedQuery(NamedQuery value) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeNamedQuery(value);
            return this;
        }

        public Builder clearNamedQuery() {
            copyOnWrite();
            ((BundleElement) this.instance).clearNamedQuery();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasDocumentMetadata() {
            return ((BundleElement) this.instance).hasDocumentMetadata();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public BundledDocumentMetadata getDocumentMetadata() {
            return ((BundleElement) this.instance).getDocumentMetadata();
        }

        public Builder setDocumentMetadata(BundledDocumentMetadata value) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocumentMetadata(value);
            return this;
        }

        public Builder setDocumentMetadata(BundledDocumentMetadata.Builder builderForValue) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocumentMetadata(builderForValue.build());
            return this;
        }

        public Builder mergeDocumentMetadata(BundledDocumentMetadata value) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeDocumentMetadata(value);
            return this;
        }

        public Builder clearDocumentMetadata() {
            copyOnWrite();
            ((BundleElement) this.instance).clearDocumentMetadata();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasDocument() {
            return ((BundleElement) this.instance).hasDocument();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public Document getDocument() {
            return ((BundleElement) this.instance).getDocument();
        }

        public Builder setDocument(Document value) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocument(value);
            return this;
        }

        public Builder setDocument(Document.Builder builderForValue) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocument(builderForValue.build());
            return this;
        }

        public Builder mergeDocument(Document value) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeDocument(value);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((BundleElement) this.instance).clearDocument();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BundleElement();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"elementType_", "elementTypeCase_", BundleMetadata.class, NamedQuery.class, BundledDocumentMetadata.class, Document.class};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<BundleElement> parser = PARSER;
                if (parser == null) {
                    synchronized (BundleElement.class) {
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
        BundleElement defaultInstance = new BundleElement();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(BundleElement.class, defaultInstance);
    }

    public static BundleElement getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundleElement> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
