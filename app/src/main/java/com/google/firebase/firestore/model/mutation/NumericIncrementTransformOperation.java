package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;

/* JADX INFO: loaded from: classes10.dex */
public class NumericIncrementTransformOperation implements TransformOperation {
    private Value operand;

    public NumericIncrementTransformOperation(Value operand) {
        Assert.hardAssert(Values.isNumber(operand), "NumericIncrementTransformOperation expects a NumberValue operand", new Object[0]);
        this.operand = operand;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToLocalView(Value previousValue, Timestamp localWriteTime) {
        Value baseValue = computeBaseValue(previousValue);
        if (Values.isInteger(baseValue) && Values.isInteger(this.operand)) {
            long sum = safeIncrement(baseValue.getIntegerValue(), operandAsLong());
            return Value.newBuilder().setIntegerValue(sum).build();
        }
        if (Values.isInteger(baseValue)) {
            double sum2 = baseValue.getIntegerValue() + operandAsDouble();
            return Value.newBuilder().setDoubleValue(sum2).build();
        }
        Assert.hardAssert(Values.isDouble(baseValue), "Expected NumberValue to be of type DoubleValue, but was ", previousValue.getClass().getCanonicalName());
        double sum3 = baseValue.getDoubleValue() + operandAsDouble();
        return Value.newBuilder().setDoubleValue(sum3).build();
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToRemoteDocument(Value previousValue, Value transformResult) {
        return transformResult;
    }

    public Value getOperand() {
        return this.operand;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value computeBaseValue(Value previousValue) {
        if (Values.isNumber(previousValue)) {
            return previousValue;
        }
        return Value.newBuilder().setIntegerValue(0L).build();
    }

    private long safeIncrement(long x, long y) {
        long r = x + y;
        if (((x ^ r) & (y ^ r)) >= 0) {
            return r;
        }
        if (r >= 0) {
            return Long.MIN_VALUE;
        }
        return Long.MAX_VALUE;
    }

    private double operandAsDouble() {
        if (Values.isDouble(this.operand)) {
            return this.operand.getDoubleValue();
        }
        if (Values.isInteger(this.operand)) {
            return this.operand.getIntegerValue();
        }
        throw Assert.fail("Expected 'operand' to be of Number type, but was " + this.operand.getClass().getCanonicalName(), new Object[0]);
    }

    private long operandAsLong() {
        if (Values.isDouble(this.operand)) {
            return (long) this.operand.getDoubleValue();
        }
        if (Values.isInteger(this.operand)) {
            return this.operand.getIntegerValue();
        }
        throw Assert.fail("Expected 'operand' to be of Number type, but was " + this.operand.getClass().getCanonicalName(), new Object[0]);
    }
}
