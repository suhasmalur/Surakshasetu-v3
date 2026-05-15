package com.google.android.play.integrity.internal;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class z implements ac {
    private static final Object a = new Object();
    private volatile ac b;
    private volatile Object c = a;

    private z(ac acVar) {
        this.b = acVar;
    }

    public static ac b(ac acVar) {
        if (acVar != null) {
            return acVar instanceof z ? acVar : new z(acVar);
        }
        throw null;
    }

    @Override // com.google.android.play.integrity.internal.ac
    public final Object a() {
        Object objA = this.c;
        if (objA == a) {
            synchronized (this) {
                objA = this.c;
                if (objA == a) {
                    objA = this.b.a();
                    Object obj = this.c;
                    if (obj != a && obj != objA) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + objA + ". This is likely due to a circular dependency.");
                    }
                    this.c = objA;
                    this.b = null;
                }
            }
        }
        return objA;
    }
}
