package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzax extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzba zzb;
    final /* synthetic */ List zzc;
    final /* synthetic */ zzn zzd;
    final /* synthetic */ zzn zze;
    private /* synthetic */ Object zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzax(zzba zzbaVar, List list, zzn zznVar, zzn zznVar2, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzbaVar;
        this.zzc = list;
        this.zzd = zznVar;
        this.zze = zznVar2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        zzax zzaxVar = new zzax(this.zzb, this.zzc, this.zzd, this.zze, continuation);
        zzaxVar.zzf = obj;
        return zzaxVar;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzax) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        switch (i) {
            case 0:
                CoroutineScope coroutineScope = (CoroutineScope) this.zzf;
                zzbl zzblVar = new zzbl(this.zzb.zzb());
                zzdk zzdkVarZzb = zzdk.zzb();
                while (zzblVar.zzb() >= 0 && zzblVar.zzb() < this.zzc.size() && CoroutineScopeKt.isActive(coroutineScope)) {
                    zzmv zzmvVar = (zzmv) this.zzc.get(zzblVar.zzb());
                    try {
                        int iZzk = zzmvVar.zzk();
                        int iZzg = zzmvVar.zzg();
                        List listZzj = zzmvVar.zzj();
                        if (!this.zzb.zzw(zzmvVar, zzblVar)) {
                            zzdk zzdkVarZzb2 = zzdk.zzb();
                            switch (iZzk - 2) {
                                case 7:
                                    zzba.zzo(this.zzb, iZzg, listZzj);
                                    zzdkVarZzb2.zzf();
                                    long jZza = zzdkVarZzb2.zza(TimeUnit.MICROSECONDS);
                                    zzj zzjVar = zzj.zza;
                                    zzj.zza(zzms.zza(iZzk), jZza);
                                    Boxing.boxLong(jZza);
                                    Boxing.boxInt(iZzg);
                                    CollectionsKt.joinToString$default(listZzj, null, null, null, 0, null, new zzaw(this.zzb), 31, null);
                                    zzblVar.zzg(zzblVar.zzb() + 1);
                                    break;
                                case 10:
                                    zzba.zzm(this.zzb, iZzg, listZzj);
                                    zzdkVarZzb2.zzf();
                                    long jZza2 = zzdkVarZzb2.zza(TimeUnit.MICROSECONDS);
                                    zzj zzjVar2 = zzj.zza;
                                    zzj.zza(zzms.zza(iZzk), jZza2);
                                    Boxing.boxLong(jZza2);
                                    Boxing.boxInt(iZzg);
                                    CollectionsKt.joinToString$default(listZzj, null, null, null, 0, null, new zzaw(this.zzb), 31, null);
                                    zzblVar.zzg(zzblVar.zzb() + 1);
                                    break;
                                case 11:
                                    zzba.zzn(this.zzb, iZzg, listZzj);
                                    zzdkVarZzb2.zzf();
                                    long jZza22 = zzdkVarZzb2.zza(TimeUnit.MICROSECONDS);
                                    zzj zzjVar22 = zzj.zza;
                                    zzj.zza(zzms.zza(iZzk), jZza22);
                                    Boxing.boxLong(jZza22);
                                    Boxing.boxInt(iZzg);
                                    CollectionsKt.joinToString$default(listZzj, null, null, null, 0, null, new zzaw(this.zzb), 31, null);
                                    zzblVar.zzg(zzblVar.zzb() + 1);
                                    break;
                                case 12:
                                    zzba.zzp(this.zzb, listZzj);
                                    zzdkVarZzb2.zzf();
                                    long jZza222 = zzdkVarZzb2.zza(TimeUnit.MICROSECONDS);
                                    zzj zzjVar222 = zzj.zza;
                                    zzj.zza(zzms.zza(iZzk), jZza222);
                                    Boxing.boxLong(jZza222);
                                    Boxing.boxInt(iZzg);
                                    CollectionsKt.joinToString$default(listZzj, null, null, null, 0, null, new zzaw(this.zzb), 31, null);
                                    zzblVar.zzg(zzblVar.zzb() + 1);
                                    break;
                                case 13:
                                    zzba.zzq(this.zzb, listZzj);
                                    zzdkVarZzb2.zzf();
                                    long jZza2222 = zzdkVarZzb2.zza(TimeUnit.MICROSECONDS);
                                    zzj zzjVar2222 = zzj.zza;
                                    zzj.zza(zzms.zza(iZzk), jZza2222);
                                    Boxing.boxLong(jZza2222);
                                    Boxing.boxInt(iZzg);
                                    CollectionsKt.joinToString$default(listZzj, null, null, null, 0, null, new zzaw(this.zzb), 31, null);
                                    zzblVar.zzg(zzblVar.zzb() + 1);
                                    break;
                                case 15:
                                    zzba.zzi(this.zzb, listZzj);
                                    zzdkVarZzb2.zzf();
                                    long jZza22222 = zzdkVarZzb2.zza(TimeUnit.MICROSECONDS);
                                    zzj zzjVar22222 = zzj.zza;
                                    zzj.zza(zzms.zza(iZzk), jZza22222);
                                    Boxing.boxLong(jZza22222);
                                    Boxing.boxInt(iZzg);
                                    CollectionsKt.joinToString$default(listZzj, null, null, null, 0, null, new zzaw(this.zzb), 31, null);
                                    zzblVar.zzg(zzblVar.zzb() + 1);
                                    break;
                                case 18:
                                    zzba.zzk(this.zzb, iZzg, listZzj);
                                    zzdkVarZzb2.zzf();
                                    long jZza222222 = zzdkVarZzb2.zza(TimeUnit.MICROSECONDS);
                                    zzj zzjVar222222 = zzj.zza;
                                    zzj.zza(zzms.zza(iZzk), jZza222222);
                                    Boxing.boxLong(jZza222222);
                                    Boxing.boxInt(iZzg);
                                    CollectionsKt.joinToString$default(listZzj, null, null, null, 0, null, new zzaw(this.zzb), 31, null);
                                    zzblVar.zzg(zzblVar.zzb() + 1);
                                    break;
                                case 19:
                                    zzba.zzl(this.zzb, iZzg, listZzj);
                                    zzdkVarZzb2.zzf();
                                    long jZza2222222 = zzdkVarZzb2.zza(TimeUnit.MICROSECONDS);
                                    zzj zzjVar2222222 = zzj.zza;
                                    zzj.zza(zzms.zza(iZzk), jZza2222222);
                                    Boxing.boxLong(jZza2222222);
                                    Boxing.boxInt(iZzg);
                                    CollectionsKt.joinToString$default(listZzj, null, null, null, 0, null, new zzaw(this.zzb), 31, null);
                                    zzblVar.zzg(zzblVar.zzb() + 1);
                                    break;
                                case 20:
                                    zzba.zzj(this.zzb, listZzj);
                                    zzdkVarZzb2.zzf();
                                    long jZza22222222 = zzdkVarZzb2.zza(TimeUnit.MICROSECONDS);
                                    zzj zzjVar22222222 = zzj.zza;
                                    zzj.zza(zzms.zza(iZzk), jZza22222222);
                                    Boxing.boxLong(jZza22222222);
                                    Boxing.boxInt(iZzg);
                                    CollectionsKt.joinToString$default(listZzj, null, null, null, 0, null, new zzaw(this.zzb), 31, null);
                                    zzblVar.zzg(zzblVar.zzb() + 1);
                                    break;
                                case 30:
                                    zzba.zzh(this.zzb, iZzg, listZzj);
                                    zzdkVarZzb2.zzf();
                                    long jZza222222222 = zzdkVarZzb2.zza(TimeUnit.MICROSECONDS);
                                    zzj zzjVar222222222 = zzj.zza;
                                    zzj.zza(zzms.zza(iZzk), jZza222222222);
                                    Boxing.boxLong(jZza222222222);
                                    Boxing.boxInt(iZzg);
                                    CollectionsKt.joinToString$default(listZzj, null, null, null, 0, null, new zzaw(this.zzb), 31, null);
                                    zzblVar.zzg(zzblVar.zzb() + 1);
                                    break;
                                case 40:
                                    zzba.zzr(this.zzb, this.zzd, iZzg, listZzj);
                                    zzdkVarZzb2.zzf();
                                    long jZza2222222222 = zzdkVarZzb2.zza(TimeUnit.MICROSECONDS);
                                    zzj zzjVar2222222222 = zzj.zza;
                                    zzj.zza(zzms.zza(iZzk), jZza2222222222);
                                    Boxing.boxLong(jZza2222222222);
                                    Boxing.boxInt(iZzg);
                                    CollectionsKt.joinToString$default(listZzj, null, null, null, 0, null, new zzaw(this.zzb), 31, null);
                                    zzblVar.zzg(zzblVar.zzb() + 1);
                                    break;
                                default:
                                    throw new zzt(5, 2, null);
                            }
                        }
                    } catch (Exception e) {
                        zzba zzbaVar = this.zzb;
                        String strZzd = zzblVar.zzd();
                        zzn zznVar = this.zzd;
                        zzn zznVar2 = this.zze;
                        int iZzb = zzblVar.zzb();
                        this.zza = 1;
                        if (zzbaVar.zzu(e, strZzd, zznVar, zznVar2, iZzb, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                zzdkVarZzb.zzf();
                Boxing.boxLong(zzdkVarZzb.zza(TimeUnit.MICROSECONDS));
                return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
