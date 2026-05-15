package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbe extends zzbd implements zzbi {
    private final Function2 zza;
    private final String zzb;

    public zzbe(Function2 function2, String str, Object obj) {
        super(obj);
        this.zza = function2;
        this.zzb = str;
    }

    @Override // com.google.android.recaptcha.internal.zzbd
    public final boolean zza(Object obj, Method method, Object[] objArr) {
        Collection collectionEmptyList;
        if (!Intrinsics.areEqual(method.getName(), this.zzb)) {
            return false;
        }
        zzmk zzmkVarZzf = zzmn.zzf();
        if (objArr != null) {
            collectionEmptyList = new ArrayList(objArr.length);
            for (Object obj2 : objArr) {
                zzml zzmlVarZzf = zzmm.zzf();
                zzmlVarZzf.zzv(obj2.toString());
                collectionEmptyList.add((zzmm) zzmlVarZzf.zzj());
            }
        } else {
            collectionEmptyList = CollectionsKt.emptyList();
        }
        zzmkVarZzf.zzd(collectionEmptyList);
        zzmn zzmnVar = (zzmn) zzmkVarZzf.zzj();
        Function2 function2 = this.zza;
        byte[] bArrZzd = zzmnVar.zzd();
        function2.invoke(objArr, zzeb.zzh().zzi(bArrZzd, 0, bArrZzd.length));
        return true;
    }
}
