package com.google.android.recaptcha.internal;

import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzp {
    public static final zzp zza = new zzp();
    private static final CoroutineScope zzb = CoroutineScopeKt.MainScope();
    private static final CoroutineScope zzc;
    private static final CoroutineScope zzd;

    static {
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(ThreadPoolDispatcherKt.newSingleThreadContext("reCaptcha"));
        BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new zzo(null), 3, null);
        zzc = CoroutineScope;
        zzd = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    }

    private zzp() {
    }

    public static final CoroutineScope zza() {
        return zzd;
    }

    public static final CoroutineScope zzb() {
        return zzb;
    }

    public static final CoroutineScope zzc() {
        return zzc;
    }
}
