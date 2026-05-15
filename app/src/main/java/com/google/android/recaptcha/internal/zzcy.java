package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzcy extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    int zzd;
    final /* synthetic */ zzda zze;
    final /* synthetic */ String zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcy(zzda zzdaVar, String str, Continuation continuation) {
        super(2, continuation);
        this.zze = zzdaVar;
        this.zzf = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzcy(this.zze, this.zzf, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzcy) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0072 A[RETURN] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v3, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.zzd
            r2 = 0
            switch(r1) {
                case 0: goto L17;
                case 1: goto Ld;
                default: goto La;
            }
        La:
            java.lang.Object r4 = r13.zza
            goto L76
        Ld:
            java.lang.Object r1 = r13.zzc
            java.lang.Object r3 = r13.zzb
            java.lang.Object r4 = r13.zza
            kotlin.ResultKt.throwOnFailure(r14)
            goto L31
        L17:
            kotlin.ResultKt.throwOnFailure(r14)
            com.google.android.recaptcha.internal.zzda r3 = r13.zze
            kotlinx.coroutines.sync.Mutex r4 = com.google.android.recaptcha.internal.zzda.zzn(r3)
            java.lang.String r1 = r13.zzf
            r13.zza = r4
            r13.zzb = r3
            r13.zzc = r1
            r14 = 1
            r13.zzd = r14
            java.lang.Object r14 = r4.lock(r2, r13)
            if (r14 == r0) goto L75
        L31:
            r14 = r3
            com.google.android.recaptcha.internal.zzda r14 = (com.google.android.recaptcha.internal.zzda) r14     // Catch: java.lang.Throwable -> L73
            com.google.android.recaptcha.internal.zzr r14 = com.google.android.recaptcha.internal.zzda.zzc(r14)     // Catch: java.lang.Throwable -> L73
            java.lang.String r5 = r14.zzb()     // Catch: java.lang.Throwable -> L73
            r14 = r3
            com.google.android.recaptcha.internal.zzda r14 = (com.google.android.recaptcha.internal.zzda) r14     // Catch: java.lang.Throwable -> L73
            java.lang.String r6 = com.google.android.recaptcha.internal.zzda.zzj(r14)     // Catch: java.lang.Throwable -> L73
            r14 = r3
            com.google.android.recaptcha.internal.zzda r14 = (com.google.android.recaptcha.internal.zzda) r14     // Catch: java.lang.Throwable -> L73
            java.lang.String r8 = com.google.android.recaptcha.internal.zzda.zzi(r14)     // Catch: java.lang.Throwable -> L73
            r14 = r3
            com.google.android.recaptcha.internal.zzda r14 = (com.google.android.recaptcha.internal.zzda) r14     // Catch: java.lang.Throwable -> L73
            java.lang.String r9 = com.google.android.recaptcha.internal.zzda.zzh(r14)     // Catch: java.lang.Throwable -> L73
            r14 = r3
            com.google.android.recaptcha.internal.zzda r14 = (com.google.android.recaptcha.internal.zzda) r14     // Catch: java.lang.Throwable -> L73
            android.content.Context r10 = com.google.android.recaptcha.internal.zzda.zza(r14)     // Catch: java.lang.Throwable -> L73
            com.google.android.recaptcha.internal.zzda r3 = (com.google.android.recaptcha.internal.zzda) r3     // Catch: java.lang.Throwable -> L73
            com.google.android.recaptcha.internal.zzr r11 = com.google.android.recaptcha.internal.zzda.zzc(r3)     // Catch: java.lang.Throwable -> L73
            r13.zza = r4     // Catch: java.lang.Throwable -> L73
            r13.zzb = r2     // Catch: java.lang.Throwable -> L73
            r13.zzc = r2     // Catch: java.lang.Throwable -> L73
            r14 = 2
            r13.zzd = r14     // Catch: java.lang.Throwable -> L73
            r7 = r1
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Throwable -> L73
            r12 = r13
            java.lang.Object r14 = com.google.android.recaptcha.internal.zzbj.zzb(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L73
            if (r14 == r0) goto L72
            goto L79
        L72:
            return r0
        L73:
            r14 = move-exception
            goto L82
        L75:
            return r0
        L76:
            kotlin.ResultKt.throwOnFailure(r14)     // Catch: java.lang.Throwable -> L81
        L79:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L73
            r4.unlock(r2)
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L81:
            r14 = move-exception
        L82:
            r4.unlock(r2)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzcy.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
