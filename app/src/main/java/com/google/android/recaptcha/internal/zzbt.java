package com.google.android.recaptcha.internal;

import java.util.Collection;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbt implements zzby {
    public static final zzbt zza = new zzbt();

    private zzbt() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        String strJoinToString$default;
        if (objArr.length != 1) {
            throw new zzt(4, 3, null);
        }
        Object obj = objArr[0];
        if (true != (obj instanceof Object)) {
            obj = null;
        }
        if (obj == null) {
            throw new zzt(4, 5, null);
        }
        if (obj instanceof int[]) {
            strJoinToString$default = ArraysKt.joinToString$default((int[]) obj, (CharSequence) ",", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
        } else if (obj instanceof byte[]) {
            strJoinToString$default = new String((byte[]) obj, Charsets.UTF_8);
        } else if (obj instanceof long[]) {
            strJoinToString$default = ArraysKt.joinToString$default((long[]) obj, (CharSequence) ",", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
        } else if (obj instanceof short[]) {
            strJoinToString$default = ArraysKt.joinToString$default((short[]) obj, (CharSequence) ",", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
        } else if (obj instanceof float[]) {
            strJoinToString$default = ArraysKt.joinToString$default((float[]) obj, (CharSequence) ",", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
        } else if (obj instanceof double[]) {
            strJoinToString$default = ArraysKt.joinToString$default((double[]) obj, ",", "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
        } else if (obj instanceof char[]) {
            strJoinToString$default = new String((char[]) obj);
        } else if (obj instanceof Object[]) {
            strJoinToString$default = ArraysKt.joinToString$default((Object[]) obj, ",", "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
        } else {
            if (!(obj instanceof Collection)) {
                throw new zzt(4, 5, null);
            }
            strJoinToString$default = CollectionsKt.joinToString$default((Iterable) obj, ",", "[", "]", 0, null, null, 56, null);
        }
        zzblVar.zzc().zzf(i, strJoinToString$default);
    }
}
