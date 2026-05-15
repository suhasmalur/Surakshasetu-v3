package com.google.firestore.v1;

import com.google.firestore.v1.Document;
import com.google.firestore.v1.DocumentMask;
import com.google.firestore.v1.DocumentTransform;
import com.google.firestore.v1.Precondition;
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
public final class Write extends GeneratedMessageLite<Write, Builder> implements WriteOrBuilder {
    public static final int CURRENT_DOCUMENT_FIELD_NUMBER = 4;
    private static final Write DEFAULT_INSTANCE;
    public static final int DELETE_FIELD_NUMBER = 2;
    private static volatile Parser<Write> PARSER = null;
    public static final int TRANSFORM_FIELD_NUMBER = 6;
    public static final int UPDATE_FIELD_NUMBER = 1;
    public static final int UPDATE_MASK_FIELD_NUMBER = 3;
    public static final int UPDATE_TRANSFORMS_FIELD_NUMBER = 7;
    public static final int VERIFY_FIELD_NUMBER = 5;
    private Precondition currentDocument_;
    private Object operation_;
    private DocumentMask updateMask_;
    private int operationCase_ = 0;
    private Internal.ProtobufList<DocumentTransform.FieldTransform> updateTransforms_ = emptyProtobufList();

    private Write() {
    }

    public enum OperationCase {
        UPDATE(1),
        DELETE(2),
        VERIFY(5),
        TRANSFORM(6),
        OPERATION_NOT_SET(0);

        private final int value;

        OperationCase(int value) {
            this.value = value;
        }

        @Deprecated
        public static OperationCase valueOf(int value) {
            return forNumber(value);
        }

        public static OperationCase forNumber(int value) {
            switch (value) {
                case 0:
                    return OPERATION_NOT_SET;
                case 1:
                    return UPDATE;
                case 2:
                    return DELETE;
                case 3:
                case 4:
                default:
                    return null;
                case 5:
                    return VERIFY;
                case 6:
                    return TRANSFORM;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public OperationCase getOperationCase() {
        return OperationCase.forNumber(this.operationCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOperation() {
        this.operationCase_ = 0;
        this.operation_ = null;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasUpdate() {
        return this.operationCase_ == 1;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public Document getUpdate() {
        if (this.operationCase_ == 1) {
            return (Document) this.operation_;
        }
        return Document.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpdate(Document value) {
        value.getClass();
        this.operation_ = value;
        this.operationCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUpdate(Document value) {
        value.getClass();
        if (this.operationCase_ == 1 && this.operation_ != Document.getDefaultInstance()) {
            this.operation_ = Document.newBuilder((Document) this.operation_).mergeFrom(value).buildPartial();
        } else {
            this.operation_ = value;
        }
        this.operationCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpdate() {
        if (this.operationCase_ == 1) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasDelete() {
        return this.operationCase_ == 2;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public String getDelete() {
        if (this.operationCase_ != 2) {
            return "";
        }
        String ref = (String) this.operation_;
        return ref;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public ByteString getDeleteBytes() {
        String ref = "";
        if (this.operationCase_ == 2) {
            ref = (String) this.operation_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDelete(String value) {
        value.getClass();
        this.operationCase_ = 2;
        this.operation_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDelete() {
        if (this.operationCase_ == 2) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeleteBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.operation_ = value.toStringUtf8();
        this.operationCase_ = 2;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasVerify() {
        return this.operationCase_ == 5;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public String getVerify() {
        if (this.operationCase_ != 5) {
            return "";
        }
        String ref = (String) this.operation_;
        return ref;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public ByteString getVerifyBytes() {
        String ref = "";
        if (this.operationCase_ == 5) {
            ref = (String) this.operation_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVerify(String value) {
        value.getClass();
        this.operationCase_ = 5;
        this.operation_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearVerify() {
        if (this.operationCase_ == 5) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVerifyBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.operation_ = value.toStringUtf8();
        this.operationCase_ = 5;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasTransform() {
        return this.operationCase_ == 6;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public DocumentTransform getTransform() {
        if (this.operationCase_ == 6) {
            return (DocumentTransform) this.operation_;
        }
        return DocumentTransform.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransform(DocumentTransform value) {
        value.getClass();
        this.operation_ = value;
        this.operationCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeTransform(DocumentTransform value) {
        value.getClass();
        if (this.operationCase_ == 6 && this.operation_ != DocumentTransform.getDefaultInstance()) {
            this.operation_ = DocumentTransform.newBuilder((DocumentTransform) this.operation_).mergeFrom(value).buildPartial();
        } else {
            this.operation_ = value;
        }
        this.operationCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransform() {
        if (this.operationCase_ == 6) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public DocumentMask getUpdateMask() {
        return this.updateMask_ == null ? DocumentMask.getDefaultInstance() : this.updateMask_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpdateMask(DocumentMask value) {
        value.getClass();
        this.updateMask_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUpdateMask(DocumentMask value) {
        value.getClass();
        if (this.updateMask_ != null && this.updateMask_ != DocumentMask.getDefaultInstance()) {
            this.updateMask_ = DocumentMask.newBuilder(this.updateMask_).mergeFrom(value).buildPartial();
        } else {
            this.updateMask_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpdateMask() {
        this.updateMask_ = null;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public List<DocumentTransform.FieldTransform> getUpdateTransformsList() {
        return this.updateTransforms_;
    }

    public List<? extends DocumentTransform.FieldTransformOrBuilder> getUpdateTransformsOrBuilderList() {
        return this.updateTransforms_;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public int getUpdateTransformsCount() {
        return this.updateTransforms_.size();
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public DocumentTransform.FieldTransform getUpdateTransforms(int index) {
        return this.updateTransforms_.get(index);
    }

    public DocumentTransform.FieldTransformOrBuilder getUpdateTransformsOrBuilder(int index) {
        return this.updateTransforms_.get(index);
    }

    private void ensureUpdateTransformsIsMutable() {
        Internal.ProtobufList<DocumentTransform.FieldTransform> tmp = this.updateTransforms_;
        if (!tmp.isModifiable()) {
            this.updateTransforms_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpdateTransforms(int index, DocumentTransform.FieldTransform value) {
        value.getClass();
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUpdateTransforms(DocumentTransform.FieldTransform value) {
        value.getClass();
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUpdateTransforms(int index, DocumentTransform.FieldTransform value) {
        value.getClass();
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllUpdateTransforms(Iterable<? extends DocumentTransform.FieldTransform> values) {
        ensureUpdateTransformsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.updateTransforms_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpdateTransforms() {
        this.updateTransforms_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeUpdateTransforms(int index) {
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.remove(index);
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasCurrentDocument() {
        return this.currentDocument_ != null;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public Precondition getCurrentDocument() {
        return this.currentDocument_ == null ? Precondition.getDefaultInstance() : this.currentDocument_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentDocument(Precondition value) {
        value.getClass();
        this.currentDocument_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCurrentDocument(Precondition value) {
        value.getClass();
        if (this.currentDocument_ != null && this.currentDocument_ != Precondition.getDefaultInstance()) {
            this.currentDocument_ = Precondition.newBuilder(this.currentDocument_).mergeFrom(value).buildPartial();
        } else {
            this.currentDocument_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCurrentDocument() {
        this.currentDocument_ = null;
    }

    public static Write parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Write parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Write parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Write parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Write parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Write parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Write parseFrom(InputStream input) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Write parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Write parseDelimitedFrom(InputStream input) throws IOException {
        return (Write) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Write parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Write) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Write parseFrom(CodedInputStream input) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Write parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Write prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Write, Builder> implements WriteOrBuilder {
        private Builder() {
            super(Write.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public OperationCase getOperationCase() {
            return ((Write) this.instance).getOperationCase();
        }

        public Builder clearOperation() {
            copyOnWrite();
            ((Write) this.instance).clearOperation();
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasUpdate() {
            return ((Write) this.instance).hasUpdate();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public Document getUpdate() {
            return ((Write) this.instance).getUpdate();
        }

        public Builder setUpdate(Document value) {
            copyOnWrite();
            ((Write) this.instance).setUpdate(value);
            return this;
        }

        public Builder setUpdate(Document.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).setUpdate(builderForValue.build());
            return this;
        }

        public Builder mergeUpdate(Document value) {
            copyOnWrite();
            ((Write) this.instance).mergeUpdate(value);
            return this;
        }

        public Builder clearUpdate() {
            copyOnWrite();
            ((Write) this.instance).clearUpdate();
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasDelete() {
            return ((Write) this.instance).hasDelete();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public String getDelete() {
            return ((Write) this.instance).getDelete();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public ByteString getDeleteBytes() {
            return ((Write) this.instance).getDeleteBytes();
        }

        public Builder setDelete(String value) {
            copyOnWrite();
            ((Write) this.instance).setDelete(value);
            return this;
        }

        public Builder clearDelete() {
            copyOnWrite();
            ((Write) this.instance).clearDelete();
            return this;
        }

        public Builder setDeleteBytes(ByteString value) {
            copyOnWrite();
            ((Write) this.instance).setDeleteBytes(value);
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasVerify() {
            return ((Write) this.instance).hasVerify();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public String getVerify() {
            return ((Write) this.instance).getVerify();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public ByteString getVerifyBytes() {
            return ((Write) this.instance).getVerifyBytes();
        }

        public Builder setVerify(String value) {
            copyOnWrite();
            ((Write) this.instance).setVerify(value);
            return this;
        }

        public Builder clearVerify() {
            copyOnWrite();
            ((Write) this.instance).clearVerify();
            return this;
        }

        public Builder setVerifyBytes(ByteString value) {
            copyOnWrite();
            ((Write) this.instance).setVerifyBytes(value);
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasTransform() {
            return ((Write) this.instance).hasTransform();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public DocumentTransform getTransform() {
            return ((Write) this.instance).getTransform();
        }

        public Builder setTransform(DocumentTransform value) {
            copyOnWrite();
            ((Write) this.instance).setTransform(value);
            return this;
        }

        public Builder setTransform(DocumentTransform.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).setTransform(builderForValue.build());
            return this;
        }

        public Builder mergeTransform(DocumentTransform value) {
            copyOnWrite();
            ((Write) this.instance).mergeTransform(value);
            return this;
        }

        public Builder clearTransform() {
            copyOnWrite();
            ((Write) this.instance).clearTransform();
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasUpdateMask() {
            return ((Write) this.instance).hasUpdateMask();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public DocumentMask getUpdateMask() {
            return ((Write) this.instance).getUpdateMask();
        }

        public Builder setUpdateMask(DocumentMask value) {
            copyOnWrite();
            ((Write) this.instance).setUpdateMask(value);
            return this;
        }

        public Builder setUpdateMask(DocumentMask.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).setUpdateMask(builderForValue.build());
            return this;
        }

        public Builder mergeUpdateMask(DocumentMask value) {
            copyOnWrite();
            ((Write) this.instance).mergeUpdateMask(value);
            return this;
        }

        public Builder clearUpdateMask() {
            copyOnWrite();
            ((Write) this.instance).clearUpdateMask();
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public List<DocumentTransform.FieldTransform> getUpdateTransformsList() {
            return Collections.unmodifiableList(((Write) this.instance).getUpdateTransformsList());
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public int getUpdateTransformsCount() {
            return ((Write) this.instance).getUpdateTransformsCount();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public DocumentTransform.FieldTransform getUpdateTransforms(int index) {
            return ((Write) this.instance).getUpdateTransforms(index);
        }

        public Builder setUpdateTransforms(int index, DocumentTransform.FieldTransform value) {
            copyOnWrite();
            ((Write) this.instance).setUpdateTransforms(index, value);
            return this;
        }

        public Builder setUpdateTransforms(int index, DocumentTransform.FieldTransform.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).setUpdateTransforms(index, builderForValue.build());
            return this;
        }

        public Builder addUpdateTransforms(DocumentTransform.FieldTransform value) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms(value);
            return this;
        }

        public Builder addUpdateTransforms(int index, DocumentTransform.FieldTransform value) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms(index, value);
            return this;
        }

        public Builder addUpdateTransforms(DocumentTransform.FieldTransform.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms(builderForValue.build());
            return this;
        }

        public Builder addUpdateTransforms(int index, DocumentTransform.FieldTransform.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms(index, builderForValue.build());
            return this;
        }

        public Builder addAllUpdateTransforms(Iterable<? extends DocumentTransform.FieldTransform> values) {
            copyOnWrite();
            ((Write) this.instance).addAllUpdateTransforms(values);
            return this;
        }

        public Builder clearUpdateTransforms() {
            copyOnWrite();
            ((Write) this.instance).clearUpdateTransforms();
            return this;
        }

        public Builder removeUpdateTransforms(int index) {
            copyOnWrite();
            ((Write) this.instance).removeUpdateTransforms(index);
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasCurrentDocument() {
            return ((Write) this.instance).hasCurrentDocument();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public Precondition getCurrentDocument() {
            return ((Write) this.instance).getCurrentDocument();
        }

        public Builder setCurrentDocument(Precondition value) {
            copyOnWrite();
            ((Write) this.instance).setCurrentDocument(value);
            return this;
        }

        public Builder setCurrentDocument(Precondition.Builder builderForValue) {
            copyOnWrite();
            ((Write) this.instance).setCurrentDocument(builderForValue.build());
            return this;
        }

        public Builder mergeCurrentDocument(Precondition value) {
            copyOnWrite();
            ((Write) this.instance).mergeCurrentDocument(value);
            return this;
        }

        public Builder clearCurrentDocument() {
            copyOnWrite();
            ((Write) this.instance).clearCurrentDocument();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new Write();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"operation_", "operationCase_", Document.class, "updateMask_", "currentDocument_", DocumentTransform.class, "updateTransforms_", DocumentTransform.FieldTransform.class};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0001\u0000\u0001\u0007\u0007\u0000\u0001\u0000\u0001<\u0000\u0002Ȼ\u0000\u0003\t\u0004\t\u0005Ȼ\u0000\u0006<\u0000\u0007\u001b", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Write> parser = PARSER;
                if (parser == null) {
                    synchronized (Write.class) {
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
        Write defaultInstance = new Write();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Write.class, defaultInstance);
    }

    public static Write getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Write> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
