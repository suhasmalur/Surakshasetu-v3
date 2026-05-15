package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbg extends zzbd {
    private final zzbf zza;
    private final String zzb;

    public zzbg(zzbf zzbfVar, String str, Object obj) {
        super(obj);
        this.zza = zzbfVar;
        this.zzb = str;
    }

    @Override // com.google.android.recaptcha.internal.zzbd
    public final boolean zza(Object obj, Method method, Object[] objArr) {
        List listEmptyList;
        if (!Intrinsics.areEqual(method.getName(), this.zzb)) {
            return false;
        }
        zzbf zzbfVar = this.zza;
        if (objArr == null || (listEmptyList = ArraysKt.asList(objArr)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        zzbfVar.zzb(listEmptyList);
        return true;
    }
}
