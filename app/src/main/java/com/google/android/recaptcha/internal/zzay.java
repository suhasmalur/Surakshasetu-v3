package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzay extends SuspendLambda implements Function2 {
    final /* synthetic */ Exception zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ zzn zzc;
    final /* synthetic */ zzn zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ zzba zzf;
    private /* synthetic */ Object zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzay(Exception exc, int i, zzn zznVar, zzn zznVar2, String str, zzba zzbaVar, Continuation continuation) {
        super(2, continuation);
        this.zza = exc;
        this.zzb = i;
        this.zzc = zznVar;
        this.zzd = zznVar2;
        this.zze = str;
        this.zzf = zzbaVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        zzay zzayVar = new zzay(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, continuation);
        zzayVar.zzg = obj;
        return zzayVar;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzay) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        zzmi zzmiVarZzf;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.zzg;
        Exception exc = this.zza;
        if (exc instanceof zzt) {
            zzmiVarZzf = ((zzt) exc).zza();
            zzmiVarZzf.zzd(this.zzb);
        } else {
            zzmiVarZzf = zzmj.zzf();
            zzmiVarZzf.zzd(this.zzb);
            zzmiVarZzf.zzp(2);
            zzmiVarZzf.zze(2);
        }
        zzmj zzmjVar = (zzmj) zzmiVarZzf.zzj();
        zzmjVar.zzk();
        zzmjVar.zzj();
        Reflection.getOrCreateKotlinClass(this.zza.getClass()).getSimpleName();
        this.zza.getMessage();
        zzlg zzlgVarZza = zzar.zza(this.zzc, this.zzd);
        String str = this.zze;
        if (str.length() == 0) {
            str = "recaptcha.m.Main.rge";
        }
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            zzba zzbaVar = this.zzf;
            zzeb zzebVarZzh = zzeb.zzh();
            byte[] bArrZzd = zzmjVar.zzd();
            zzeb zzebVarZzh2 = zzeb.zzh();
            byte[] bArrZzd2 = zzlgVarZza.zzd();
            zzbaVar.zzv(str, zzebVarZzh.zzi(bArrZzd, 0, bArrZzd.length), zzebVarZzh2.zzi(bArrZzd2, 0, bArrZzd2.length));
        }
        return Unit.INSTANCE;
    }
}
