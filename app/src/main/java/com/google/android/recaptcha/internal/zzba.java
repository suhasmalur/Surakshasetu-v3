package com.google.android.recaptcha.internal;

import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.TuplesKt;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzba implements zzas, zzbi {
    public static final zzat zza = new zzat(null);
    private final zzbc zzb;
    private final CoroutineScope zzc;
    private final zzbn zzd = new zzbn();
    private final Map zze = this.zzd.zzb().zzc();
    private final Map zzf = MapsKt.mapOf(TuplesKt.to(39, zzbt.zza), TuplesKt.to(34, zzcg.zza), TuplesKt.to(35, zzco.zza), TuplesKt.to(25, zzbz.zza), TuplesKt.to(37, zzcn.zza), TuplesKt.to(21, zzbo.zza), TuplesKt.to(22, zzcm.zza), TuplesKt.to(23, zzch.zza), TuplesKt.to(24, zzbw.zza), TuplesKt.to(1, zzcj.zza), TuplesKt.to(2, zzbs.zza), TuplesKt.to(38, zzcl.zza), TuplesKt.to(3, zzca.zza), TuplesKt.to(4, zzcb.zza), TuplesKt.to(17, zzbv.zza), TuplesKt.to(32, zzbp.zza), TuplesKt.to(5, zzcd.zza), TuplesKt.to(31, zzbq.zza), TuplesKt.to(36, zzbr.zza), TuplesKt.to(16, zzbu.zza), TuplesKt.to(26, zzck.zza), TuplesKt.to(6, zzcc.zza), TuplesKt.to(27, zzci.zza), TuplesKt.to(8, zzce.zza), TuplesKt.to(9, zzcf.zza));

    public zzba(zzbc zzbcVar, CoroutineScope coroutineScope, Context context) {
        this.zzb = zzbcVar;
        this.zzc = coroutineScope;
        zzcr zzcrVar = zzcr.zza;
        zzcr.zzb(new int[0]);
        this.zzd.zze(3, context);
    }

    public static final /* synthetic */ void zzh(zzba zzbaVar, int i, List list) throws zzt {
        if (list.isEmpty()) {
            throw new zzt(4, 3, null);
        }
        if (!zzx(list)) {
            throw new zzt(4, 5, null);
        }
        zzmk zzmkVarZzf = zzmn.zzf();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzmu zzmuVar = (zzmu) it.next();
            zzml zzmlVarZzf = zzmm.zzf();
            Object objZza = zzbaVar.zzd.zzb().zza(zzmuVar);
            if (objZza == null) {
                throw new zzt(4, 4, null);
            }
            if (objZza instanceof Integer) {
                zzmlVarZzf.zzt(((Number) objZza).intValue());
            } else if (objZza instanceof Short) {
                zzmlVarZzf.zzs(((Number) objZza).shortValue());
            } else if (objZza instanceof Byte) {
                zzmlVarZzf.zze(zzez.zzm(new byte[]{((Number) objZza).byteValue()}, 0, 1));
            } else if (objZza instanceof Long) {
                zzmlVarZzf.zzu(((Number) objZza).longValue());
            } else if (objZza instanceof Double) {
                zzmlVarZzf.zzq(((Number) objZza).doubleValue());
            } else if (objZza instanceof Float) {
                zzmlVarZzf.zzr(((Number) objZza).floatValue());
            } else if (objZza instanceof Boolean) {
                zzmlVarZzf.zzd(((Boolean) objZza).booleanValue());
            } else if (objZza instanceof Character) {
                zzmlVarZzf.zzp(objZza.toString());
            } else if (objZza instanceof String) {
                zzmlVarZzf.zzv((String) objZza);
            } else {
                zzmlVarZzf.zzv(objZza.toString());
            }
            zzmkVarZzf.zze((zzmm) zzmlVarZzf.zzj());
        }
        zzbm zzbmVarZzb = zzbaVar.zzd.zzb();
        byte[] bArrZzd = ((zzmn) zzmkVarZzf.zzj()).zzd();
        zzbmVarZzb.zzf(i, zzeb.zzh().zzi(bArrZzd, 0, bArrZzd.length));
    }

    public static final /* synthetic */ void zzi(zzba zzbaVar, List list) throws zzt {
        if (list.size() != 2) {
            throw new zzt(4, 3, null);
        }
        Object objZza = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
        if (true != (objZza instanceof String)) {
            objZza = null;
        }
        String str = (String) objZza;
        if (str == null) {
            throw new zzt(4, 5, null);
        }
        Object objZza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(1));
        if (objZza2 == null) {
            throw new zzt(4, 4, null);
        }
        if (!(objZza2 instanceof Integer) && !(objZza2 instanceof Short) && !(objZza2 instanceof Byte) && !(objZza2 instanceof Long) && !(objZza2 instanceof Double) && !(objZza2 instanceof Float) && !(objZza2 instanceof Boolean) && !(objZza2 instanceof Character) && !(objZza2 instanceof String)) {
            throw new zzt(4, 7, null);
        }
        zzbaVar.zzv(str, objZza2.toString());
    }

    public static final /* synthetic */ void zzj(zzba zzbaVar, List list) throws zzt {
        if (!zzx(list)) {
            throw new zzt(4, 5, null);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzbaVar.zzd.zzb().zzb(((zzmu) it.next()).zzi());
        }
    }

    public static final /* synthetic */ void zzk(zzba zzbaVar, int i, List list) throws zzt {
        int iIntValue;
        if (list.size() != 4 && list.size() != 5) {
            throw new zzt(4, 3, null);
        }
        Object objZza = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
        if (true != (objZza instanceof String)) {
            objZza = null;
        }
        String str = (String) objZza;
        if (str == null) {
            throw new zzt(4, 5, null);
        }
        Object objZza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(1));
        if (true != (objZza2 instanceof Object)) {
            objZza2 = null;
        }
        if (objZza2 == null) {
            throw new zzt(4, 5, null);
        }
        Object objZza3 = zzbaVar.zzd.zzb().zza((zzmu) list.get(2));
        if (true != (objZza3 instanceof String)) {
            objZza3 = null;
        }
        String str2 = (String) objZza3;
        if (str2 == null) {
            throw new zzt(4, 5, null);
        }
        String strZza = zzbh.zza(zzbaVar, str2, zzbaVar.zzd.zza());
        Object objZza4 = zzbaVar.zzd.zzb().zza((zzmu) list.get(3));
        if (list.size() == 5) {
            Object objZza5 = zzbaVar.zzd.zzb().zza((zzmu) list.get(4));
            if (true != (objZza5 instanceof Integer)) {
                objZza5 = null;
            }
            Integer num = (Integer) objZza5;
            if (num == null) {
                throw new zzt(4, 5, null);
            }
            iIntValue = num.intValue();
        } else {
            iIntValue = -1;
        }
        try {
            byte bZza = zzbaVar.zzd.zza();
            if (objZza2 instanceof String) {
                objZza2 = zzbh.zza(zzbaVar, (String) objZza2, bZza);
            }
            Class clsZza = zzbk.zza(objZza2);
            zzbaVar.zzd.zzb().zzf(i, Proxy.newProxyInstance(clsZza.getClassLoader(), new Class[]{clsZza}, new zzbe(new zzau(zzbaVar, str, iIntValue), strZza, objZza4)));
        } catch (Exception e) {
            throw new zzt(6, 20, e);
        }
    }

    public static final /* synthetic */ void zzl(zzba zzbaVar, int i, List list) throws zzt {
        if (list.size() != 4 && list.size() != 5) {
            throw new zzt(4, 3, null);
        }
        Object objZza = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
        if (true != (objZza instanceof Integer)) {
            objZza = null;
        }
        Integer num = (Integer) objZza;
        if (num == null) {
            throw new zzt(4, 5, null);
        }
        int iIntValue = num.intValue();
        Object objZza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(1));
        if (true != (objZza2 instanceof Integer)) {
            objZza2 = null;
        }
        Integer num2 = (Integer) objZza2;
        if (num2 == null) {
            throw new zzt(4, 5, null);
        }
        int iIntValue2 = num2.intValue();
        Object objZza3 = zzbaVar.zzd.zzb().zza((zzmu) list.get(2));
        if (true != (objZza3 instanceof String)) {
            objZza3 = null;
        }
        String str = (String) objZza3;
        if (str == null) {
            throw new zzt(4, 5, null);
        }
        String strZza = zzbh.zza(zzbaVar, str, zzbaVar.zzd.zza());
        Object objZza4 = zzbaVar.zzd.zzb().zza((zzmu) list.get(3));
        if (true != (objZza4 instanceof String)) {
            objZza4 = null;
        }
        String str2 = (String) objZza4;
        if (str2 == null) {
            throw new zzt(4, 5, null);
        }
        String strZza2 = zzbh.zza(zzbaVar, str2, zzbaVar.zzd.zza());
        Object objZza5 = list.size() == 5 ? zzbaVar.zzd.zzb().zza((zzmu) list.get(4)) : null;
        zzbf zzbfVar = new zzbf(iIntValue2);
        try {
            Class clsZza = zzbk.zza(strZza);
            zzbaVar.zzd.zzb().zzf(iIntValue, Proxy.newProxyInstance(clsZza.getClassLoader(), new Class[]{clsZza}, new zzbg(zzbfVar, strZza2, objZza5)));
            zzbaVar.zzd.zzb().zzf(i, zzbfVar);
        } catch (Exception e) {
            throw new zzt(6, 20, e);
        }
    }

    public static final /* synthetic */ void zzm(zzba zzbaVar, int i, List list) throws zzt {
        if (list.size() != 2) {
            throw new zzt(4, 3, null);
        }
        Object objZza = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
        if (true != (objZza instanceof Field)) {
            objZza = null;
        }
        Field field = (Field) objZza;
        if (field == null) {
            throw new zzt(4, 5, null);
        }
        try {
            zzbaVar.zzd.zzb().zzf(i, field.get(zzbaVar.zzd.zzb().zza((zzmu) list.get(1))));
        } catch (Exception e) {
            throw new zzt(6, 16, e);
        }
    }

    public static final /* synthetic */ void zzn(zzba zzbaVar, int i, List list) throws zzt {
        if (list.size() != 1) {
            throw new zzt(4, 3, null);
        }
        Object objZza = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
        if (true != (objZza instanceof Field)) {
            objZza = null;
        }
        Field field = (Field) objZza;
        if (field == null) {
            throw new zzt(4, 5, null);
        }
        try {
            zzbaVar.zzd.zzb().zzf(i, field.get(null));
        } catch (Exception e) {
            throw new zzt(6, 16, e);
        }
    }

    public static final /* synthetic */ void zzo(zzba zzbaVar, int i, List list) throws zzt {
        if (list.isEmpty()) {
            throw new zzt(4, 3, null);
        }
        Object objZza = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
        if (true != (objZza instanceof Constructor)) {
            objZza = null;
        }
        Constructor constructor = (Constructor) objZza;
        if (constructor == null) {
            throw new zzt(4, 5, null);
        }
        Object[] objArrZzg = zzbaVar.zzd.zzb().zzg(list.subList(1, list.size()));
        try {
            zzbaVar.zzd.zzb().zzf(i, constructor.newInstance(Arrays.copyOf(objArrZzg, objArrZzg.length)));
        } catch (Exception e) {
            throw new zzt(6, 14, e);
        }
    }

    public static final /* synthetic */ void zzp(zzba zzbaVar, List list) throws zzt {
        if (list.size() != 3) {
            throw new zzt(4, 3, null);
        }
        Object objZza = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
        if (true != (objZza instanceof Field)) {
            objZza = null;
        }
        Field field = (Field) objZza;
        if (field == null) {
            throw new zzt(4, 5, null);
        }
        try {
            field.set(zzbaVar.zzd.zzb().zza((zzmu) list.get(1)), zzbaVar.zzd.zzb().zza((zzmu) list.get(2)));
        } catch (Exception e) {
            throw new zzt(6, 11, e);
        }
    }

    public static final /* synthetic */ void zzq(zzba zzbaVar, List list) throws zzt {
        if (list.size() != 2) {
            throw new zzt(4, 3, null);
        }
        Object objZza = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
        if (true != (objZza instanceof Field)) {
            objZza = null;
        }
        Field field = (Field) objZza;
        if (field == null) {
            throw new zzt(4, 5, null);
        }
        try {
            field.set(null, zzbaVar.zzd.zzb().zza((zzmu) list.get(1)));
        } catch (Exception e) {
            throw new zzt(6, 11, e);
        }
    }

    public static final /* synthetic */ void zzr(zzba zzbaVar, zzn zznVar, int i, List list) throws zzt {
        if (list.size() != 2 && list.size() != 0) {
            throw new zzt(4, 3, null);
        }
        if (list.size() == 0) {
            zzbaVar.zzd.zzb().zzf(i, new zzn());
            return;
        }
        Object objZza = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
        if (true != (objZza instanceof String)) {
            objZza = null;
        }
        String str = (String) objZza;
        if (str == null) {
            throw new zzt(4, 5, null);
        }
        Object objZza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(1));
        if (true != (objZza2 instanceof zzn)) {
            objZza2 = null;
        }
        zzn zznVar2 = (zzn) objZza2;
        if (zznVar2 == null) {
            throw new zzt(4, 5, null);
        }
        byte[] bArrZzd = zzar.zza(zznVar, zznVar2).zzd();
        zzbaVar.zzv(str, zzeb.zzh().zzi(bArrZzd, 0, bArrZzd.length));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzt(List list, zzn zznVar, zzn zznVar2, Continuation continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new zzax(this, list, zznVar, zznVar2, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzu(Exception exc, String str, zzn zznVar, zzn zznVar2, int i, Continuation continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new zzay(exc, i, zznVar, zznVar2, str, this, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzv(String str, String... strArr) {
        this.zzb.zzb(str, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzw(zzmv zzmvVar, zzbl zzblVar) throws zzt {
        zzdk zzdkVarZzb = zzdk.zzb();
        int iZzb = zzblVar.zzb();
        zzby zzbyVar = (zzby) this.zzf.get(Integer.valueOf(zzmvVar.zzf()));
        if (zzbyVar == null) {
            return false;
        }
        Object[] objArrZzg = this.zzd.zzb().zzg(zzmvVar.zzj());
        zzbyVar.zza(zzmvVar.zzg(), zzblVar, Arrays.copyOf(objArrZzg, objArrZzg.length));
        if (iZzb == zzblVar.zzb()) {
            zzblVar.zzg(zzblVar.zzb() + 1);
        }
        zzdkVarZzb.zzf();
        long jZza = zzdkVarZzb.zza(TimeUnit.MICROSECONDS);
        zzj zzjVar = zzj.zza;
        zzj.zza(zzms.zza(zzmvVar.zzk()), jZza);
        zzmvVar.zzk();
        CollectionsKt.joinToString$default(zzmvVar.zzj(), null, null, null, 0, null, new zzav(this), 31, null);
        return true;
    }

    private static final boolean zzx(List list) {
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Boolean.valueOf(((zzmu) it.next()).zzM()));
        }
        return !arrayList.contains(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final zzmh zzy(String str, List list) throws zzt {
        if (str.length() == 0) {
            throw new zzt(3, 17, null);
        }
        try {
            zzcq zzcqVar = new zzcq((short) zzcr.zza(CollectionsKt.toIntArray(list)), (short) 255);
            StringBuilder sb = new StringBuilder(str.length());
            for (int i = 0; i < str.length(); i++) {
                sb.append((char) UInt.m559constructorimpl(UInt.m559constructorimpl(str.charAt(i)) ^ UInt.m559constructorimpl(zzcqVar.zza())));
            }
            return zzmh.zzg(zzeb.zzh().zzj(sb.toString()));
        } catch (Exception e) {
            throw new zzt(3, 18, e);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzas
    public final void zza(String str) {
        BuildersKt__Builders_commonKt.launch$default(this.zzc, null, null, new zzaz(this, str, new zzn(), null), 3, null);
    }

    public final zzbn zzb() {
        return this.zzd;
    }
}
