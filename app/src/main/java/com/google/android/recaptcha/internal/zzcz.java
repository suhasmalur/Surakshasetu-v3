package com.google.android.recaptcha.internal;

import android.os.Build;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzcz extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzda zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcz(zzda zzdaVar, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzdaVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzcz(this.zzb, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzcz) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        switch (i) {
            case 0:
                zzu zzuVar = zzu.zza;
                String strZza = zzu.zza(this.zzb.zze);
                String str = this.zzb.zzd;
                String packageName = this.zzb.zze.getPackageName();
                String str2 = this.zzb.zzh;
                int i2 = Build.VERSION.SDK_INT;
                byte[] bytes = ("k=" + URLEncoder.encode(str, "UTF-8") + "&pk=" + URLEncoder.encode(packageName, "UTF-8") + "&mst=" + URLEncoder.encode(strZza, "UTF-8") + "&msv=" + URLEncoder.encode("18.1.2", "UTF-8") + "&msi=" + URLEncoder.encode(str2, "UTF-8") + "&mov=" + i2).getBytes(Charset.forName("UTF-8"));
                zzai zzaiVar = zzai.zza;
                zzai.zzc(new zzaf(zzkw.INIT_NATIVE, this.zzb.zzg, this.zzb.zzh, this.zzb.zzh, null), this.zzb.zze, this.zzb.zzf);
                zzai.zzb(new zzaf(zzkw.INIT_NETWORK, this.zzb.zzg, this.zzb.zzh, this.zzb.zzh, null), this.zzb.zzd, new zzs());
                zzp zzpVar = zzp.zza;
                BuildersKt__Builders_commonKt.launch$default(zzp.zza(), null, null, new zzcy(this.zzb, strZza, null), 3, null);
                this.zzb.zzn.zzd();
                this.zzb.zzn.zze();
                zzda zzdaVar = this.zzb;
                zzdaVar.zzb().postUrl(zzdaVar.zzf.zza(), bytes);
                Boxing.boxInt(this.zzb.zzm().hashCode());
                CompletableDeferred completableDeferredZzm = this.zzb.zzm();
                this.zza = 1;
                if (completableDeferredZzm.await(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
        }
        return Result.m463boximpl(Result.m464constructorimpl(Unit.INSTANCE));
    }
}
