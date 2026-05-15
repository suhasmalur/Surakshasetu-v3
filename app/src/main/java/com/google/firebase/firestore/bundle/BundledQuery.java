package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;

/* JADX INFO: loaded from: classes10.dex */
public class BundledQuery implements BundleElement {
    private final Query.LimitType limitType;
    private final Target target;

    public BundledQuery(Target target, Query.LimitType limitType) {
        this.target = target;
        this.limitType = limitType;
    }

    public Target getTarget() {
        return this.target;
    }

    public Query.LimitType getLimitType() {
        return this.limitType;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BundledQuery that = (BundledQuery) o;
        if (this.target.equals(that.target) && this.limitType == that.limitType) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = this.target.hashCode();
        return (result * 31) + this.limitType.hashCode();
    }
}
