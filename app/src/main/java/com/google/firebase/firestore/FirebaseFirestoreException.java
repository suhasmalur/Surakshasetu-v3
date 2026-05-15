package com.google.firebase.firestore;

import android.util.SparseArray;
import com.google.firebase.FirebaseException;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Preconditions;

/* JADX INFO: loaded from: classes10.dex */
public class FirebaseFirestoreException extends FirebaseException {
    private final Code code;

    public enum Code {
        OK(0),
        CANCELLED(1),
        UNKNOWN(2),
        INVALID_ARGUMENT(3),
        DEADLINE_EXCEEDED(4),
        NOT_FOUND(5),
        ALREADY_EXISTS(6),
        PERMISSION_DENIED(7),
        RESOURCE_EXHAUSTED(8),
        FAILED_PRECONDITION(9),
        ABORTED(10),
        OUT_OF_RANGE(11),
        UNIMPLEMENTED(12),
        INTERNAL(13),
        UNAVAILABLE(14),
        DATA_LOSS(15),
        UNAUTHENTICATED(16);

        private static final SparseArray<Code> STATUS_LIST = buildStatusList();
        private final int value;

        Code(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }

        private static SparseArray<Code> buildStatusList() {
            SparseArray<Code> codes = new SparseArray<>();
            for (Code c : values()) {
                Code existingValue = codes.get(c.value());
                if (existingValue != null) {
                    throw new IllegalStateException("Code value duplication between " + existingValue + "&" + c.name());
                }
                codes.put(c.value(), c);
            }
            return codes;
        }

        public static Code fromValue(int value) {
            return STATUS_LIST.get(value, UNKNOWN);
        }
    }

    public FirebaseFirestoreException(String detailMessage, Code code) {
        super(detailMessage);
        Preconditions.checkNotNull(detailMessage, "Provided message must not be null.");
        Assert.hardAssert(code != Code.OK, "A FirebaseFirestoreException should never be thrown for OK", new Object[0]);
        this.code = (Code) Preconditions.checkNotNull(code, "Provided code must not be null.");
    }

    public FirebaseFirestoreException(String detailMessage, Code code, Throwable cause) {
        super(detailMessage, cause);
        Preconditions.checkNotNull(detailMessage, "Provided message must not be null.");
        Assert.hardAssert(code != Code.OK, "A FirebaseFirestoreException should never be thrown for OK", new Object[0]);
        this.code = (Code) Preconditions.checkNotNull(code, "Provided code must not be null.");
    }

    public Code getCode() {
        return this.code;
    }
}
