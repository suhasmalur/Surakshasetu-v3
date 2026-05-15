package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzaz extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzba zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ zzn zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaz(zzba zzbaVar, String str, zzn zznVar, Continuation continuation) {
        super(2, continuation);
        this.zzc = zzbaVar;
        this.zzd = str;
        this.zze = zznVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzaz(this.zzc, this.zzd, this.zze, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzaz) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0083 A[ADDED_TO_REGION, REMOVE, RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.Throwable {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.zzb
            switch(r1) {
                case 0: goto L17;
                case 1: goto Le;
                default: goto L9;
            }
        L9:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L84
        Le:
            java.lang.Object r1 = r11.zza
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L14
            goto L84
        L14:
            r12 = move-exception
            r3 = r12
            goto L69
        L17:
            kotlin.ResultKt.throwOnFailure(r12)
            com.google.android.recaptcha.internal.zzn r1 = new com.google.android.recaptcha.internal.zzn
            r1.<init>()
            java.lang.String r12 = r11.zzd
            com.google.android.recaptcha.internal.zzeb r2 = com.google.android.recaptcha.internal.zzeb.zzh()
            byte[] r12 = r2.zzj(r12)
            com.google.android.recaptcha.internal.zzmp r12 = com.google.android.recaptcha.internal.zzmp.zzg(r12)
            com.google.android.recaptcha.internal.zzdk r2 = com.google.android.recaptcha.internal.zzdk.zzb()     // Catch: java.lang.Exception -> L67
            com.google.android.recaptcha.internal.zzba r3 = r11.zzc     // Catch: java.lang.Exception -> L67
            java.lang.String r4 = r12.zzi()     // Catch: java.lang.Exception -> L67
            java.util.List r12 = r12.zzj()     // Catch: java.lang.Exception -> L67
            com.google.android.recaptcha.internal.zzmh r12 = com.google.android.recaptcha.internal.zzba.zzc(r3, r4, r12)     // Catch: java.lang.Exception -> L67
            r2.zzf()     // Catch: java.lang.Exception -> L67
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MICROSECONDS     // Catch: java.lang.Exception -> L67
            long r2 = r2.zza(r3)     // Catch: java.lang.Exception -> L67
            com.google.android.recaptcha.internal.zzj r4 = com.google.android.recaptcha.internal.zzj.zza     // Catch: java.lang.Exception -> L67
            com.google.android.recaptcha.internal.zzl r4 = com.google.android.recaptcha.internal.zzl.zzm     // Catch: java.lang.Exception -> L67
            int r4 = r4.zza()     // Catch: java.lang.Exception -> L67
            com.google.android.recaptcha.internal.zzj.zza(r4, r2)     // Catch: java.lang.Exception -> L67
            com.google.android.recaptcha.internal.zzba r2 = r11.zzc     // Catch: java.lang.Exception -> L67
            java.util.List r12 = r12.zzi()     // Catch: java.lang.Exception -> L67
            com.google.android.recaptcha.internal.zzn r3 = r11.zze     // Catch: java.lang.Exception -> L67
            r11.zza = r1     // Catch: java.lang.Exception -> L67
            r4 = 1
            r11.zzb = r4     // Catch: java.lang.Exception -> L67
            java.lang.Object r12 = com.google.android.recaptcha.internal.zzba.zzd(r2, r12, r3, r1, r11)     // Catch: java.lang.Exception -> L67
            if (r12 != r0) goto L84
            return r0
        L67:
            r12 = move-exception
            r3 = r12
        L69:
            com.google.android.recaptcha.internal.zzba r2 = r11.zzc
            com.google.android.recaptcha.internal.zzn r5 = r11.zze
            r12 = 0
            r11.zza = r12
            r12 = 2
            r11.zzb = r12
            r6 = r1
            com.google.android.recaptcha.internal.zzn r6 = (com.google.android.recaptcha.internal.zzn) r6
            java.lang.String r4 = "recaptcha.m.Main.rge"
            r7 = 0
            r9 = 16
            r10 = 0
            r8 = r11
            java.lang.Object r12 = com.google.android.recaptcha.internal.zzba.zzf(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            if (r12 != r0) goto L84
            return r0
        L84:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzaz.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
