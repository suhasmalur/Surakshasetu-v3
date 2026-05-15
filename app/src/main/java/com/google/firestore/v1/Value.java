package com.google.firestore.v1;

import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.NullValue;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes10.dex */
public final class Value extends GeneratedMessageLite<Value, Builder> implements ValueOrBuilder {
    public static final int ARRAY_VALUE_FIELD_NUMBER = 9;
    public static final int BOOLEAN_VALUE_FIELD_NUMBER = 1;
    public static final int BYTES_VALUE_FIELD_NUMBER = 18;
    private static final Value DEFAULT_INSTANCE;
    public static final int DOUBLE_VALUE_FIELD_NUMBER = 3;
    public static final int GEO_POINT_VALUE_FIELD_NUMBER = 8;
    public static final int INTEGER_VALUE_FIELD_NUMBER = 2;
    public static final int MAP_VALUE_FIELD_NUMBER = 6;
    public static final int NULL_VALUE_FIELD_NUMBER = 11;
    private static volatile Parser<Value> PARSER = null;
    public static final int REFERENCE_VALUE_FIELD_NUMBER = 5;
    public static final int STRING_VALUE_FIELD_NUMBER = 17;
    public static final int TIMESTAMP_VALUE_FIELD_NUMBER = 10;
    private int valueTypeCase_ = 0;
    private Object valueType_;

    private Value() {
    }

    public enum ValueTypeCase {
        NULL_VALUE(11),
        BOOLEAN_VALUE(1),
        INTEGER_VALUE(2),
        DOUBLE_VALUE(3),
        TIMESTAMP_VALUE(10),
        STRING_VALUE(17),
        BYTES_VALUE(18),
        REFERENCE_VALUE(5),
        GEO_POINT_VALUE(8),
        ARRAY_VALUE(9),
        MAP_VALUE(6),
        VALUETYPE_NOT_SET(0);

        private final int value;

        ValueTypeCase(int value) {
            this.value = value;
        }

        @Deprecated
        public static ValueTypeCase valueOf(int value) {
            return forNumber(value);
        }

        public static ValueTypeCase forNumber(int value) {
            switch (value) {
                case 0:
                    return VALUETYPE_NOT_SET;
                case 1:
                    return BOOLEAN_VALUE;
                case 2:
                    return INTEGER_VALUE;
                case 3:
                    return DOUBLE_VALUE;
                case 4:
                case 7:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                default:
                    return null;
                case 5:
                    return REFERENCE_VALUE;
                case 6:
                    return MAP_VALUE;
                case 8:
                    return GEO_POINT_VALUE;
                case 9:
                    return ARRAY_VALUE;
                case 10:
                    return TIMESTAMP_VALUE;
                case 11:
                    return NULL_VALUE;
                case 17:
                    return STRING_VALUE;
                case 18:
                    return BYTES_VALUE;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ValueTypeCase getValueTypeCase() {
        return ValueTypeCase.forNumber(this.valueTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearValueType() {
        this.valueTypeCase_ = 0;
        this.valueType_ = null;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasNullValue() {
        return this.valueTypeCase_ == 11;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public int getNullValueValue() {
        if (this.valueTypeCase_ == 11) {
            return ((Integer) this.valueType_).intValue();
        }
        return 0;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public NullValue getNullValue() {
        if (this.valueTypeCase_ == 11) {
            NullValue result = NullValue.forNumber(((Integer) this.valueType_).intValue());
            return result == null ? NullValue.UNRECOGNIZED : result;
        }
        return NullValue.NULL_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNullValueValue(int value) {
        this.valueTypeCase_ = 11;
        this.valueType_ = Integer.valueOf(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNullValue(NullValue value) {
        this.valueType_ = Integer.valueOf(value.getNumber());
        this.valueTypeCase_ = 11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNullValue() {
        if (this.valueTypeCase_ == 11) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasBooleanValue() {
        return this.valueTypeCase_ == 1;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean getBooleanValue() {
        if (this.valueTypeCase_ == 1) {
            return ((Boolean) this.valueType_).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBooleanValue(boolean value) {
        this.valueTypeCase_ = 1;
        this.valueType_ = Boolean.valueOf(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBooleanValue() {
        if (this.valueTypeCase_ == 1) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasIntegerValue() {
        return this.valueTypeCase_ == 2;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public long getIntegerValue() {
        if (this.valueTypeCase_ == 2) {
            return ((Long) this.valueType_).longValue();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIntegerValue(long value) {
        this.valueTypeCase_ = 2;
        this.valueType_ = Long.valueOf(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIntegerValue() {
        if (this.valueTypeCase_ == 2) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasDoubleValue() {
        return this.valueTypeCase_ == 3;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public double getDoubleValue() {
        if (this.valueTypeCase_ == 3) {
            return ((Double) this.valueType_).doubleValue();
        }
        return 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDoubleValue(double value) {
        this.valueTypeCase_ = 3;
        this.valueType_ = Double.valueOf(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDoubleValue() {
        if (this.valueTypeCase_ == 3) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasTimestampValue() {
        return this.valueTypeCase_ == 10;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public Timestamp getTimestampValue() {
        if (this.valueTypeCase_ == 10) {
            return (Timestamp) this.valueType_;
        }
        return Timestamp.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimestampValue(Timestamp value) {
        value.getClass();
        this.valueType_ = value;
        this.valueTypeCase_ = 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeTimestampValue(Timestamp value) {
        value.getClass();
        if (this.valueTypeCase_ == 10 && this.valueType_ != Timestamp.getDefaultInstance()) {
            this.valueType_ = Timestamp.newBuilder((Timestamp) this.valueType_).mergeFrom(value).buildPartial();
        } else {
            this.valueType_ = value;
        }
        this.valueTypeCase_ = 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTimestampValue() {
        if (this.valueTypeCase_ == 10) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasStringValue() {
        return this.valueTypeCase_ == 17;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public String getStringValue() {
        if (this.valueTypeCase_ != 17) {
            return "";
        }
        String ref = (String) this.valueType_;
        return ref;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ByteString getStringValueBytes() {
        String ref = "";
        if (this.valueTypeCase_ == 17) {
            ref = (String) this.valueType_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStringValue(String value) {
        value.getClass();
        this.valueTypeCase_ = 17;
        this.valueType_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStringValue() {
        if (this.valueTypeCase_ == 17) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStringValueBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.valueType_ = value.toStringUtf8();
        this.valueTypeCase_ = 17;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasBytesValue() {
        return this.valueTypeCase_ == 18;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ByteString getBytesValue() {
        if (this.valueTypeCase_ == 18) {
            return (ByteString) this.valueType_;
        }
        return ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBytesValue(ByteString value) {
        value.getClass();
        this.valueTypeCase_ = 18;
        this.valueType_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBytesValue() {
        if (this.valueTypeCase_ == 18) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasReferenceValue() {
        return this.valueTypeCase_ == 5;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public String getReferenceValue() {
        if (this.valueTypeCase_ != 5) {
            return "";
        }
        String ref = (String) this.valueType_;
        return ref;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ByteString getReferenceValueBytes() {
        String ref = "";
        if (this.valueTypeCase_ == 5) {
            ref = (String) this.valueType_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReferenceValue(String value) {
        value.getClass();
        this.valueTypeCase_ = 5;
        this.valueType_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReferenceValue() {
        if (this.valueTypeCase_ == 5) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReferenceValueBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.valueType_ = value.toStringUtf8();
        this.valueTypeCase_ = 5;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasGeoPointValue() {
        return this.valueTypeCase_ == 8;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public LatLng getGeoPointValue() {
        if (this.valueTypeCase_ == 8) {
            return (LatLng) this.valueType_;
        }
        return LatLng.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGeoPointValue(LatLng value) {
        value.getClass();
        this.valueType_ = value;
        this.valueTypeCase_ = 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeGeoPointValue(LatLng value) {
        value.getClass();
        if (this.valueTypeCase_ == 8 && this.valueType_ != LatLng.getDefaultInstance()) {
            this.valueType_ = LatLng.newBuilder((LatLng) this.valueType_).mergeFrom(value).buildPartial();
        } else {
            this.valueType_ = value;
        }
        this.valueTypeCase_ = 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearGeoPointValue() {
        if (this.valueTypeCase_ == 8) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasArrayValue() {
        return this.valueTypeCase_ == 9;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ArrayValue getArrayValue() {
        if (this.valueTypeCase_ == 9) {
            return (ArrayValue) this.valueType_;
        }
        return ArrayValue.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setArrayValue(ArrayValue value) {
        value.getClass();
        this.valueType_ = value;
        this.valueTypeCase_ = 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeArrayValue(ArrayValue value) {
        value.getClass();
        if (this.valueTypeCase_ == 9 && this.valueType_ != ArrayValue.getDefaultInstance()) {
            this.valueType_ = ArrayValue.newBuilder((ArrayValue) this.valueType_).mergeFrom(value).buildPartial();
        } else {
            this.valueType_ = value;
        }
        this.valueTypeCase_ = 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearArrayValue() {
        if (this.valueTypeCase_ == 9) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasMapValue() {
        return this.valueTypeCase_ == 6;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public MapValue getMapValue() {
        if (this.valueTypeCase_ == 6) {
            return (MapValue) this.valueType_;
        }
        return MapValue.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMapValue(MapValue value) {
        value.getClass();
        this.valueType_ = value;
        this.valueTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMapValue(MapValue value) {
        value.getClass();
        if (this.valueTypeCase_ == 6 && this.valueType_ != MapValue.getDefaultInstance()) {
            this.valueType_ = MapValue.newBuilder((MapValue) this.valueType_).mergeFrom(value).buildPartial();
        } else {
            this.valueType_ = value;
        }
        this.valueTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMapValue() {
        if (this.valueTypeCase_ == 6) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    public static Value parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Value parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Value parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Value parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Value parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Value parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Value parseFrom(InputStream input) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Value parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Value parseDelimitedFrom(InputStream input) throws IOException {
        return (Value) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Value parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Value) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Value parseFrom(CodedInputStream input) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Value parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Value prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Value, Builder> implements ValueOrBuilder {
        private Builder() {
            super(Value.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ValueTypeCase getValueTypeCase() {
            return ((Value) this.instance).getValueTypeCase();
        }

        public Builder clearValueType() {
            copyOnWrite();
            ((Value) this.instance).clearValueType();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasNullValue() {
            return ((Value) this.instance).hasNullValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public int getNullValueValue() {
            return ((Value) this.instance).getNullValueValue();
        }

        public Builder setNullValueValue(int value) {
            copyOnWrite();
            ((Value) this.instance).setNullValueValue(value);
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public NullValue getNullValue() {
            return ((Value) this.instance).getNullValue();
        }

        public Builder setNullValue(NullValue value) {
            copyOnWrite();
            ((Value) this.instance).setNullValue(value);
            return this;
        }

        public Builder clearNullValue() {
            copyOnWrite();
            ((Value) this.instance).clearNullValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasBooleanValue() {
            return ((Value) this.instance).hasBooleanValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean getBooleanValue() {
            return ((Value) this.instance).getBooleanValue();
        }

        public Builder setBooleanValue(boolean value) {
            copyOnWrite();
            ((Value) this.instance).setBooleanValue(value);
            return this;
        }

        public Builder clearBooleanValue() {
            copyOnWrite();
            ((Value) this.instance).clearBooleanValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasIntegerValue() {
            return ((Value) this.instance).hasIntegerValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public long getIntegerValue() {
            return ((Value) this.instance).getIntegerValue();
        }

        public Builder setIntegerValue(long value) {
            copyOnWrite();
            ((Value) this.instance).setIntegerValue(value);
            return this;
        }

        public Builder clearIntegerValue() {
            copyOnWrite();
            ((Value) this.instance).clearIntegerValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasDoubleValue() {
            return ((Value) this.instance).hasDoubleValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public double getDoubleValue() {
            return ((Value) this.instance).getDoubleValue();
        }

        public Builder setDoubleValue(double value) {
            copyOnWrite();
            ((Value) this.instance).setDoubleValue(value);
            return this;
        }

        public Builder clearDoubleValue() {
            copyOnWrite();
            ((Value) this.instance).clearDoubleValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasTimestampValue() {
            return ((Value) this.instance).hasTimestampValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public Timestamp getTimestampValue() {
            return ((Value) this.instance).getTimestampValue();
        }

        public Builder setTimestampValue(Timestamp value) {
            copyOnWrite();
            ((Value) this.instance).setTimestampValue(value);
            return this;
        }

        public Builder setTimestampValue(Timestamp.Builder builderForValue) {
            copyOnWrite();
            ((Value) this.instance).setTimestampValue(builderForValue.build());
            return this;
        }

        public Builder mergeTimestampValue(Timestamp value) {
            copyOnWrite();
            ((Value) this.instance).mergeTimestampValue(value);
            return this;
        }

        public Builder clearTimestampValue() {
            copyOnWrite();
            ((Value) this.instance).clearTimestampValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasStringValue() {
            return ((Value) this.instance).hasStringValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public String getStringValue() {
            return ((Value) this.instance).getStringValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ByteString getStringValueBytes() {
            return ((Value) this.instance).getStringValueBytes();
        }

        public Builder setStringValue(String value) {
            copyOnWrite();
            ((Value) this.instance).setStringValue(value);
            return this;
        }

        public Builder clearStringValue() {
            copyOnWrite();
            ((Value) this.instance).clearStringValue();
            return this;
        }

        public Builder setStringValueBytes(ByteString value) {
            copyOnWrite();
            ((Value) this.instance).setStringValueBytes(value);
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasBytesValue() {
            return ((Value) this.instance).hasBytesValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ByteString getBytesValue() {
            return ((Value) this.instance).getBytesValue();
        }

        public Builder setBytesValue(ByteString value) {
            copyOnWrite();
            ((Value) this.instance).setBytesValue(value);
            return this;
        }

        public Builder clearBytesValue() {
            copyOnWrite();
            ((Value) this.instance).clearBytesValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasReferenceValue() {
            return ((Value) this.instance).hasReferenceValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public String getReferenceValue() {
            return ((Value) this.instance).getReferenceValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ByteString getReferenceValueBytes() {
            return ((Value) this.instance).getReferenceValueBytes();
        }

        public Builder setReferenceValue(String value) {
            copyOnWrite();
            ((Value) this.instance).setReferenceValue(value);
            return this;
        }

        public Builder clearReferenceValue() {
            copyOnWrite();
            ((Value) this.instance).clearReferenceValue();
            return this;
        }

        public Builder setReferenceValueBytes(ByteString value) {
            copyOnWrite();
            ((Value) this.instance).setReferenceValueBytes(value);
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasGeoPointValue() {
            return ((Value) this.instance).hasGeoPointValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public LatLng getGeoPointValue() {
            return ((Value) this.instance).getGeoPointValue();
        }

        public Builder setGeoPointValue(LatLng value) {
            copyOnWrite();
            ((Value) this.instance).setGeoPointValue(value);
            return this;
        }

        public Builder setGeoPointValue(LatLng.Builder builderForValue) {
            copyOnWrite();
            ((Value) this.instance).setGeoPointValue(builderForValue.build());
            return this;
        }

        public Builder mergeGeoPointValue(LatLng value) {
            copyOnWrite();
            ((Value) this.instance).mergeGeoPointValue(value);
            return this;
        }

        public Builder clearGeoPointValue() {
            copyOnWrite();
            ((Value) this.instance).clearGeoPointValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasArrayValue() {
            return ((Value) this.instance).hasArrayValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ArrayValue getArrayValue() {
            return ((Value) this.instance).getArrayValue();
        }

        public Builder setArrayValue(ArrayValue value) {
            copyOnWrite();
            ((Value) this.instance).setArrayValue(value);
            return this;
        }

        public Builder setArrayValue(ArrayValue.Builder builderForValue) {
            copyOnWrite();
            ((Value) this.instance).setArrayValue(builderForValue.build());
            return this;
        }

        public Builder mergeArrayValue(ArrayValue value) {
            copyOnWrite();
            ((Value) this.instance).mergeArrayValue(value);
            return this;
        }

        public Builder clearArrayValue() {
            copyOnWrite();
            ((Value) this.instance).clearArrayValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasMapValue() {
            return ((Value) this.instance).hasMapValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public MapValue getMapValue() {
            return ((Value) this.instance).getMapValue();
        }

        public Builder setMapValue(MapValue value) {
            copyOnWrite();
            ((Value) this.instance).setMapValue(value);
            return this;
        }

        public Builder setMapValue(MapValue.Builder builderForValue) {
            copyOnWrite();
            ((Value) this.instance).setMapValue(builderForValue.build());
            return this;
        }

        public Builder mergeMapValue(MapValue value) {
            copyOnWrite();
            ((Value) this.instance).mergeMapValue(value);
            return this;
        }

        public Builder clearMapValue() {
            copyOnWrite();
            ((Value) this.instance).clearMapValue();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new Value();
            case NEW_BUILDER:
                return new Builder();
            case BUILD_MESSAGE_INFO:
                Object[] objects = {"valueType_", "valueTypeCase_", MapValue.class, LatLng.class, ArrayValue.class, Timestamp.class};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000b\u0001\u0000\u0001\u0012\u000b\u0000\u0000\u0000\u0001:\u0000\u00025\u0000\u00033\u0000\u0005Ȼ\u0000\u0006<\u0000\b<\u0000\t<\u0000\n<\u0000\u000b?\u0000\u0011Ȼ\u0000\u0012=\u0000", objects);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Value> parser = PARSER;
                if (parser == null) {
                    synchronized (Value.class) {
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
        Value defaultInstance = new Value();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(Value.class, defaultInstance);
    }

    public static Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Value> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
