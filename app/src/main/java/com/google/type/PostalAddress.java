package com.google.type;

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
public final class PostalAddress extends GeneratedMessageLite<PostalAddress, Builder> implements PostalAddressOrBuilder {
    public static final int ADDRESS_LINES_FIELD_NUMBER = 9;
    public static final int ADMINISTRATIVE_AREA_FIELD_NUMBER = 6;
    private static final PostalAddress DEFAULT_INSTANCE;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 3;
    public static final int LOCALITY_FIELD_NUMBER = 7;
    public static final int ORGANIZATION_FIELD_NUMBER = 11;
    private static volatile Parser<PostalAddress> PARSER = null;
    public static final int POSTAL_CODE_FIELD_NUMBER = 4;
    public static final int RECIPIENTS_FIELD_NUMBER = 10;
    public static final int REGION_CODE_FIELD_NUMBER = 2;
    public static final int REVISION_FIELD_NUMBER = 1;
    public static final int SORTING_CODE_FIELD_NUMBER = 5;
    public static final int SUBLOCALITY_FIELD_NUMBER = 8;
    private int revision_;
    private String regionCode_ = "";
    private String languageCode_ = "";
    private String postalCode_ = "";
    private String sortingCode_ = "";
    private String administrativeArea_ = "";
    private String locality_ = "";
    private String sublocality_ = "";
    private Internal.ProtobufList<String> addressLines_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<String> recipients_ = GeneratedMessageLite.emptyProtobufList();
    private String organization_ = "";

    private PostalAddress() {
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public int getRevision() {
        return this.revision_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRevision(int value) {
        this.revision_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRevision() {
        this.revision_ = 0;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getRegionCode() {
        return this.regionCode_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getRegionCodeBytes() {
        return ByteString.copyFromUtf8(this.regionCode_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRegionCode(String value) {
        value.getClass();
        this.regionCode_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRegionCode() {
        this.regionCode_ = getDefaultInstance().getRegionCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRegionCodeBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.regionCode_ = value.toStringUtf8();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getLanguageCode() {
        return this.languageCode_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getLanguageCodeBytes() {
        return ByteString.copyFromUtf8(this.languageCode_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLanguageCode(String value) {
        value.getClass();
        this.languageCode_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLanguageCode() {
        this.languageCode_ = getDefaultInstance().getLanguageCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLanguageCodeBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.languageCode_ = value.toStringUtf8();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getPostalCode() {
        return this.postalCode_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getPostalCodeBytes() {
        return ByteString.copyFromUtf8(this.postalCode_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPostalCode(String value) {
        value.getClass();
        this.postalCode_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPostalCode() {
        this.postalCode_ = getDefaultInstance().getPostalCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPostalCodeBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.postalCode_ = value.toStringUtf8();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getSortingCode() {
        return this.sortingCode_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getSortingCodeBytes() {
        return ByteString.copyFromUtf8(this.sortingCode_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSortingCode(String value) {
        value.getClass();
        this.sortingCode_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSortingCode() {
        this.sortingCode_ = getDefaultInstance().getSortingCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSortingCodeBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.sortingCode_ = value.toStringUtf8();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getAdministrativeArea() {
        return this.administrativeArea_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getAdministrativeAreaBytes() {
        return ByteString.copyFromUtf8(this.administrativeArea_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdministrativeArea(String value) {
        value.getClass();
        this.administrativeArea_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAdministrativeArea() {
        this.administrativeArea_ = getDefaultInstance().getAdministrativeArea();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdministrativeAreaBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.administrativeArea_ = value.toStringUtf8();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getLocality() {
        return this.locality_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getLocalityBytes() {
        return ByteString.copyFromUtf8(this.locality_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocality(String value) {
        value.getClass();
        this.locality_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLocality() {
        this.locality_ = getDefaultInstance().getLocality();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocalityBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.locality_ = value.toStringUtf8();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getSublocality() {
        return this.sublocality_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getSublocalityBytes() {
        return ByteString.copyFromUtf8(this.sublocality_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSublocality(String value) {
        value.getClass();
        this.sublocality_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSublocality() {
        this.sublocality_ = getDefaultInstance().getSublocality();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSublocalityBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.sublocality_ = value.toStringUtf8();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public List<String> getAddressLinesList() {
        return this.addressLines_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public int getAddressLinesCount() {
        return this.addressLines_.size();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getAddressLines(int index) {
        return this.addressLines_.get(index);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getAddressLinesBytes(int index) {
        return ByteString.copyFromUtf8(this.addressLines_.get(index));
    }

    private void ensureAddressLinesIsMutable() {
        Internal.ProtobufList<String> tmp = this.addressLines_;
        if (!tmp.isModifiable()) {
            this.addressLines_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAddressLines(int index, String value) {
        value.getClass();
        ensureAddressLinesIsMutable();
        this.addressLines_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAddressLines(String value) {
        value.getClass();
        ensureAddressLinesIsMutable();
        this.addressLines_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllAddressLines(Iterable<String> values) {
        ensureAddressLinesIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.addressLines_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAddressLines() {
        this.addressLines_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAddressLinesBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensureAddressLinesIsMutable();
        this.addressLines_.add(value.toStringUtf8());
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public List<String> getRecipientsList() {
        return this.recipients_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public int getRecipientsCount() {
        return this.recipients_.size();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getRecipients(int index) {
        return this.recipients_.get(index);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getRecipientsBytes(int index) {
        return ByteString.copyFromUtf8(this.recipients_.get(index));
    }

    private void ensureRecipientsIsMutable() {
        Internal.ProtobufList<String> tmp = this.recipients_;
        if (!tmp.isModifiable()) {
            this.recipients_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRecipients(int index, String value) {
        value.getClass();
        ensureRecipientsIsMutable();
        this.recipients_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRecipients(String value) {
        value.getClass();
        ensureRecipientsIsMutable();
        this.recipients_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllRecipients(Iterable<String> values) {
        ensureRecipientsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.recipients_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRecipients() {
        this.recipients_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRecipientsBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        ensureRecipientsIsMutable();
        this.recipients_.add(value.toStringUtf8());
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getOrganization() {
        return this.organization_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getOrganizationBytes() {
        return ByteString.copyFromUtf8(this.organization_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOrganization(String value) {
        value.getClass();
        this.organization_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOrganization() {
        this.organization_ = getDefaultInstance().getOrganization();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOrganizationBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.organization_ = value.toStringUtf8();
    }

    public static PostalAddress parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PostalAddress parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PostalAddress parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PostalAddress parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PostalAddress parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PostalAddress parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PostalAddress parseFrom(InputStream input) throws IOException {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PostalAddress parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PostalAddress parseDelimitedFrom(InputStream input) throws IOException {
        return (PostalAddress) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PostalAddress parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PostalAddress) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PostalAddress parseFrom(CodedInputStream input) throws IOException {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PostalAddress parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PostalAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(PostalAddress prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PostalAddress, Builder> implements PostalAddressOrBuilder {
        private Builder() {
            super(PostalAddress.DEFAULT_INSTANCE);
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public int getRevision() {
            return ((PostalAddress) this.instance).getRevision();
        }

        public Builder setRevision(int value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setRevision(value);
            return this;
        }

        public Builder clearRevision() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearRevision();
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getRegionCode() {
            return ((PostalAddress) this.instance).getRegionCode();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getRegionCodeBytes() {
            return ((PostalAddress) this.instance).getRegionCodeBytes();
        }

        public Builder setRegionCode(String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setRegionCode(value);
            return this;
        }

        public Builder clearRegionCode() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearRegionCode();
            return this;
        }

        public Builder setRegionCodeBytes(ByteString value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setRegionCodeBytes(value);
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getLanguageCode() {
            return ((PostalAddress) this.instance).getLanguageCode();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getLanguageCodeBytes() {
            return ((PostalAddress) this.instance).getLanguageCodeBytes();
        }

        public Builder setLanguageCode(String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setLanguageCode(value);
            return this;
        }

        public Builder clearLanguageCode() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearLanguageCode();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setLanguageCodeBytes(value);
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getPostalCode() {
            return ((PostalAddress) this.instance).getPostalCode();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getPostalCodeBytes() {
            return ((PostalAddress) this.instance).getPostalCodeBytes();
        }

        public Builder setPostalCode(String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setPostalCode(value);
            return this;
        }

        public Builder clearPostalCode() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearPostalCode();
            return this;
        }

        public Builder setPostalCodeBytes(ByteString value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setPostalCodeBytes(value);
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getSortingCode() {
            return ((PostalAddress) this.instance).getSortingCode();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getSortingCodeBytes() {
            return ((PostalAddress) this.instance).getSortingCodeBytes();
        }

        public Builder setSortingCode(String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setSortingCode(value);
            return this;
        }

        public Builder clearSortingCode() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearSortingCode();
            return this;
        }

        public Builder setSortingCodeBytes(ByteString value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setSortingCodeBytes(value);
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getAdministrativeArea() {
            return ((PostalAddress) this.instance).getAdministrativeArea();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getAdministrativeAreaBytes() {
            return ((PostalAddress) this.instance).getAdministrativeAreaBytes();
        }

        public Builder setAdministrativeArea(String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setAdministrativeArea(value);
            return this;
        }

        public Builder clearAdministrativeArea() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearAdministrativeArea();
            return this;
        }

        public Builder setAdministrativeAreaBytes(ByteString value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setAdministrativeAreaBytes(value);
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getLocality() {
            return ((PostalAddress) this.instance).getLocality();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getLocalityBytes() {
            return ((PostalAddress) this.instance).getLocalityBytes();
        }

        public Builder setLocality(String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setLocality(value);
            return this;
        }

        public Builder clearLocality() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearLocality();
            return this;
        }

        public Builder setLocalityBytes(ByteString value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setLocalityBytes(value);
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getSublocality() {
            return ((PostalAddress) this.instance).getSublocality();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getSublocalityBytes() {
            return ((PostalAddress) this.instance).getSublocalityBytes();
        }

        public Builder setSublocality(String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setSublocality(value);
            return this;
        }

        public Builder clearSublocality() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearSublocality();
            return this;
        }

        public Builder setSublocalityBytes(ByteString value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setSublocalityBytes(value);
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public List<String> getAddressLinesList() {
            return Collections.unmodifiableList(((PostalAddress) this.instance).getAddressLinesList());
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public int getAddressLinesCount() {
            return ((PostalAddress) this.instance).getAddressLinesCount();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getAddressLines(int index) {
            return ((PostalAddress) this.instance).getAddressLines(index);
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getAddressLinesBytes(int index) {
            return ((PostalAddress) this.instance).getAddressLinesBytes(index);
        }

        public Builder setAddressLines(int index, String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setAddressLines(index, value);
            return this;
        }

        public Builder addAddressLines(String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).addAddressLines(value);
            return this;
        }

        public Builder addAllAddressLines(Iterable<String> values) {
            copyOnWrite();
            ((PostalAddress) this.instance).addAllAddressLines(values);
            return this;
        }

        public Builder clearAddressLines() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearAddressLines();
            return this;
        }

        public Builder addAddressLinesBytes(ByteString value) {
            copyOnWrite();
            ((PostalAddress) this.instance).addAddressLinesBytes(value);
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public List<String> getRecipientsList() {
            return Collections.unmodifiableList(((PostalAddress) this.instance).getRecipientsList());
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public int getRecipientsCount() {
            return ((PostalAddress) this.instance).getRecipientsCount();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getRecipients(int index) {
            return ((PostalAddress) this.instance).getRecipients(index);
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getRecipientsBytes(int index) {
            return ((PostalAddress) this.instance).getRecipientsBytes(index);
        }

        public Builder setRecipients(int index, String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setRecipients(index, value);
            return this;
        }

        public Builder addRecipients(String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).addRecipients(value);
            return this;
        }

        public Builder addAllRecipients(Iterable<String> values) {
            copyOnWrite();
            ((PostalAddress) this.instance).addAllRecipients(values);
            return this;
        }

        public Builder clearRecipients() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearRecipients();
            return this;
        }

        public Builder addRecipientsBytes(ByteString value) {
            copyOnWrite();
            ((PostalAddress) this.instance).addRecipientsBytes(value);
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getOrganization() {
            return ((PostalAddress) this.instance).getOrganization();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getOrganizationBytes() {
            return ((PostalAddress) this.instance).getOrganizationBytes();
        }

        public Builder setOrganization(String value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setOrganization(value);
            return this;
        }

        public Builder clearOrganization() {
            copyOnWrite();
            ((PostalAddress) this.instance).clearOrganization();
            return this;
        }

        public Builder setOrganizationBytes(ByteString value) {
            copyOnWrite();
            ((PostalAddress) this.instance).setOrganizationBytes(value);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PostalAddress();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"revision_", "regionCode_", "languageCode_", "postalCode_", "sortingCode_", "administrativeArea_", "locality_", "sublocality_", "addressLines_", "recipients_", "organization_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000b\u0000\u0000\u0001\u000b\u000b\u0000\u0002\u0000\u0001\u0004\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007Ȉ\bȈ\tȚ\nȚ\u000bȈ", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<PostalAddress> parser = PARSER;
                if (parser == null) {
                    synchronized (PostalAddress.class) {
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
        PostalAddress defaultInstance = new PostalAddress();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(PostalAddress.class, defaultInstance);
    }

    public static PostalAddress getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PostalAddress> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
