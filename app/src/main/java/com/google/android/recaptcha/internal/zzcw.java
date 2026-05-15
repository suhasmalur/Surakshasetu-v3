package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzcw extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    int zzd;
    final /* synthetic */ RecaptchaAction zze;
    final /* synthetic */ zzda zzf;
    final /* synthetic */ String zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcw(RecaptchaAction recaptchaAction, zzda zzdaVar, String str, Continuation continuation) {
        super(2, continuation);
        this.zze = recaptchaAction;
        this.zzf = zzdaVar;
        this.zzg = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzcw(this.zze, this.zzf, this.zzg, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzcw) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zzd;
        ResultKt.throwOnFailure(obj);
        switch (i) {
            case 0:
                zzda zzdaVar = this.zzf;
                String str = this.zzg;
                RecaptchaAction recaptchaAction = this.zze;
                this.zza = zzdaVar;
                this.zzb = str;
                this.zzc = recaptchaAction;
                this.zzd = 1;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(this), 1);
                cancellableContinuationImpl.initCancellability();
                zzdaVar.zzk.put(str, cancellableContinuationImpl);
                zzma zzmaVarZzf = zzmb.zzf();
                zzmaVarZzf.zze(str);
                zzmaVarZzf.zzd(recaptchaAction.getAction());
                byte[] bArrZzd = ((zzmb) zzmaVarZzf.zzj()).zzd();
                String strZzi = zzeb.zzh().zzi(bArrZzd, 0, bArrZzd.length);
                zzai zzaiVar = zzai.zza;
                zzai.zzc(new zzaf(zzkw.EXECUTE_NATIVE, zzdaVar.zzg, zzdaVar.zzh, str, null), zzdaVar.zze, zzdaVar.zzf);
                zzdaVar.zzb().evaluateJavascript("recaptcha.m.Main.execute(\"" + strZzi + "\")", null);
                obj = cancellableContinuationImpl.getResult();
                if (obj == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(this);
                }
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            default:
                return obj;
        }
    }
}
