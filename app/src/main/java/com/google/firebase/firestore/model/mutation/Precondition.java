package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;

/* JADX INFO: loaded from: classes10.dex */
public final class Precondition {
    public static final Precondition NONE = new Precondition(null, null);
    private final Boolean exists;
    private final SnapshotVersion updateTime;

    private Precondition(SnapshotVersion updateTime, Boolean exists) {
        Assert.hardAssert(updateTime == null || exists == null, "Precondition can specify \"exists\" or \"updateTime\" but not both", new Object[0]);
        this.updateTime = updateTime;
        this.exists = exists;
    }

    public static Precondition exists(boolean exists) {
        return new Precondition(null, Boolean.valueOf(exists));
    }

    public static Precondition updateTime(SnapshotVersion version) {
        return new Precondition(version, null);
    }

    public boolean isNone() {
        return this.updateTime == null && this.exists == null;
    }

    public SnapshotVersion getUpdateTime() {
        return this.updateTime;
    }

    public Boolean getExists() {
        return this.exists;
    }

    public boolean isValidFor(MutableDocument doc) {
        if (this.updateTime != null) {
            return doc.isFoundDocument() && doc.getVersion().equals(this.updateTime);
        }
        if (this.exists != null) {
            return this.exists.booleanValue() == doc.isFoundDocument();
        }
        Assert.hardAssert(isNone(), "Precondition should be empty", new Object[0]);
        return true;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Precondition that = (Precondition) o;
        if (this.updateTime == null ? that.updateTime != null : !this.updateTime.equals(that.updateTime)) {
            return false;
        }
        if (this.exists != null) {
            return this.exists.equals(that.exists);
        }
        if (that.exists == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = this.updateTime != null ? this.updateTime.hashCode() : 0;
        return (result * 31) + (this.exists != null ? this.exists.hashCode() : 0);
    }

    public String toString() {
        if (isNone()) {
            return "Precondition{<none>}";
        }
        if (this.updateTime != null) {
            return "Precondition{updateTime=" + this.updateTime + "}";
        }
        if (this.exists != null) {
            return "Precondition{exists=" + this.exists + "}";
        }
        throw Assert.fail("Invalid Precondition", new Object[0]);
    }
}
