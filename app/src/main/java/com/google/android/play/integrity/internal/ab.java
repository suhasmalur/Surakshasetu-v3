package com.google.android.play.integrity.internal;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ab implements aa {
    private static final ab a = new ab(null);
    private final Object b;

    private ab(Object obj) {
        this.b = obj;
    }

    public static aa b(Object obj) {
        return new ab(obj);
    }

    @Override // com.google.android.play.integrity.internal.ac
    public final Object a() {
        return this.b;
    }
}
