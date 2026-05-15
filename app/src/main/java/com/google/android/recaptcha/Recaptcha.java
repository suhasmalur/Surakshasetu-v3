package com.google.android.recaptcha;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.internal.zzaa;
import com.google.android.recaptcha.internal.zzb;
import com.google.android.recaptcha.internal.zzp;
import com.google.android.recaptcha.internal.zzr;
import com.google.android.recaptcha.internal.zzw;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002ø\u0001\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/google/android/recaptcha/Recaptcha;", "", "()V", "getClient", "Lkotlin/Result;", "Lcom/google/android/recaptcha/RecaptchaClient;", "application", "Landroid/app/Application;", "siteKey", "", "getClient-0E7RQCE", "(Landroid/app/Application;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTasksClient", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/recaptcha/RecaptchaTasksClient;", "java.com.google.android.libraries.abuse.recaptcha.enterprise_enterprise"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class Recaptcha {
    public static final Recaptcha INSTANCE = new Recaptcha();

    /* JADX INFO: renamed from: com.google.android.recaptcha.Recaptcha$getTasksClient$1, reason: invalid class name */
    /* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super zzaa>, Object> {
        int zza;
        final /* synthetic */ Application zzb;
        final /* synthetic */ String zzc;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Application application, String str, Continuation continuation) {
            super(2, continuation);
            this.zzb = application;
            this.zzc = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.zzb, this.zzc, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super zzaa> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.zza;
            ResultKt.throwOnFailure(obj);
            switch (i) {
                case 0:
                    zzw zzwVar = zzaa.zza;
                    Application application = this.zzb;
                    String str = this.zzc;
                    this.zza = 1;
                    obj = zzwVar.zza(application, str, new zzr(null, 0L, 0L, 7, null), null, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                default:
                    return obj;
            }
        }
    }

    private Recaptcha() {
    }

    @JvmStatic
    public static final Task<RecaptchaTasksClient> getTasksClient(Application application, String siteKey) {
        zzp zzpVar = zzp.zza;
        return zzb.zza(BuildersKt__Builders_commonKt.async$default(zzp.zzb(), null, null, new AnonymousClass1(application, siteKey, null), 3, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: getClient-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m236getClient0E7RQCE(android.app.Application r5, java.lang.String r6, kotlin.coroutines.Continuation<? super kotlin.Result<? extends com.google.android.recaptcha.RecaptchaClient>> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.google.android.recaptcha.Recaptcha$getClient$1
            if (r0 == 0) goto L13
            r0 = r7
            com.google.android.recaptcha.Recaptcha$getClient$1 r0 = (com.google.android.recaptcha.Recaptcha$getClient$1) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.zzc = r1
            goto L18
        L13:
            com.google.android.recaptcha.Recaptcha$getClient$1 r0 = new com.google.android.recaptcha.Recaptcha$getClient$1
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r7 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            switch(r2) {
                case 0: goto L31;
                case 1: goto L2b;
                default: goto L23;
            }
        L23:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2b:
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L2f
            goto L50
        L2f:
            r5 = move-exception
            goto L57
        L31:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.Result$Companion r7 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L2f
            com.google.android.recaptcha.internal.zzp r7 = com.google.android.recaptcha.internal.zzp.zza     // Catch: java.lang.Throwable -> L2f
            kotlinx.coroutines.CoroutineScope r7 = com.google.android.recaptcha.internal.zzp.zzb()     // Catch: java.lang.Throwable -> L2f
            kotlin.coroutines.CoroutineContext r7 = r7.getCoroutineContext()     // Catch: java.lang.Throwable -> L2f
            com.google.android.recaptcha.Recaptcha$getClient$2$1 r2 = new com.google.android.recaptcha.Recaptcha$getClient$2$1     // Catch: java.lang.Throwable -> L2f
            r3 = 0
            r2.<init>(r5, r6, r3)     // Catch: java.lang.Throwable -> L2f
            r5 = 1
            r0.zzc = r5     // Catch: java.lang.Throwable -> L2f
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r2, r0)     // Catch: java.lang.Throwable -> L2f
            if (r7 != r1) goto L50
            return r1
        L50:
            com.google.android.recaptcha.internal.zzaa r7 = (com.google.android.recaptcha.internal.zzaa) r7     // Catch: java.lang.Throwable -> L2f
            java.lang.Object r5 = kotlin.Result.m464constructorimpl(r7)     // Catch: java.lang.Throwable -> L2f
            goto L61
        L57:
            kotlin.Result$Companion r6 = kotlin.Result.INSTANCE
            java.lang.Object r5 = kotlin.ResultKt.createFailure(r5)
            java.lang.Object r5 = kotlin.Result.m464constructorimpl(r5)
        L61:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.Recaptcha.m236getClient0E7RQCE(android.app.Application, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
