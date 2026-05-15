package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzgb extends zzga {
    zzgb() {
    }

    @Override // com.google.android.recaptcha.internal.zzga
    final int zza(Map.Entry entry) {
        return ((zzgl) entry.getKey()).zza;
    }

    @Override // com.google.android.recaptcha.internal.zzga
    final zzge zzb(Object obj) {
        return ((zzgk) obj).zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzga
    final zzge zzc(Object obj) {
        return ((zzgk) obj).zzi();
    }

    @Override // com.google.android.recaptcha.internal.zzga
    final Object zzd(zzfz zzfzVar, zzhy zzhyVar, int i) {
        return zzfzVar.zza(zzhyVar, i);
    }

    @Override // com.google.android.recaptcha.internal.zzga
    final Object zze(Object obj, zzik zzikVar, Object obj2, zzfz zzfzVar, zzge zzgeVar, Object obj3, zzjf zzjfVar) throws IOException {
        zzgm zzgmVar = (zzgm) obj2;
        Object objZzk = null;
        if (zzgmVar.zzb.zzb == zzjv.ENUM) {
            zzikVar.zzg();
            throw null;
        }
        switch (zzgmVar.zzb.zzb) {
            case DOUBLE:
                objZzk = Double.valueOf(zzikVar.zza());
                break;
            case FLOAT:
                objZzk = Float.valueOf(zzikVar.zzb());
                break;
            case INT64:
                objZzk = Long.valueOf(zzikVar.zzl());
                break;
            case UINT64:
                objZzk = Long.valueOf(zzikVar.zzo());
                break;
            case INT32:
                objZzk = Integer.valueOf(zzikVar.zzg());
                break;
            case FIXED64:
                objZzk = Long.valueOf(zzikVar.zzk());
                break;
            case FIXED32:
                objZzk = Integer.valueOf(zzikVar.zzf());
                break;
            case BOOL:
                objZzk = Boolean.valueOf(zzikVar.zzN());
                break;
            case STRING:
                objZzk = zzikVar.zzr();
                break;
            case GROUP:
                Object objZze = zzgeVar.zze(zzgmVar.zzb);
                if (!(objZze instanceof zzgo)) {
                    throw null;
                }
                zzil zzilVarZzb = zzih.zza().zzb(objZze.getClass());
                if (!((zzgo) objZze).zzF()) {
                    Object objZze2 = zzilVarZzb.zze();
                    zzilVarZzb.zzg(objZze2, objZze);
                    zzgeVar.zzi(zzgmVar.zzb, objZze2);
                    objZze = objZze2;
                }
                zzikVar.zzt(objZze, zzilVarZzb, zzfzVar);
                return obj3;
            case MESSAGE:
                Object objZze3 = zzgeVar.zze(zzgmVar.zzb);
                if (!(objZze3 instanceof zzgo)) {
                    throw null;
                }
                zzil zzilVarZzb2 = zzih.zza().zzb(objZze3.getClass());
                if (!((zzgo) objZze3).zzF()) {
                    Object objZze4 = zzilVarZzb2.zze();
                    zzilVarZzb2.zzg(objZze4, objZze3);
                    zzgeVar.zzi(zzgmVar.zzb, objZze4);
                    objZze3 = objZze4;
                }
                zzikVar.zzu(objZze3, zzilVarZzb2, zzfzVar);
                return obj3;
            case BYTES:
                objZzk = zzikVar.zzp();
                break;
            case UINT32:
                objZzk = Integer.valueOf(zzikVar.zzj());
                break;
            case ENUM:
                throw new IllegalStateException("Shouldn't reach here.");
            case SFIXED32:
                objZzk = Integer.valueOf(zzikVar.zzh());
                break;
            case SFIXED64:
                objZzk = Long.valueOf(zzikVar.zzm());
                break;
            case SINT32:
                objZzk = Integer.valueOf(zzikVar.zzi());
                break;
            case SINT64:
                objZzk = Long.valueOf(zzikVar.zzn());
                break;
        }
        switch (zzgmVar.zzb.zzb.ordinal()) {
            case 9:
            case 10:
                Object objZze5 = zzgeVar.zze(zzgmVar.zzb);
                if (objZze5 != null) {
                    byte[] bArr = zzgw.zzd;
                    objZzk = ((zzhy) objZze5).zzW().zzc((zzhy) objZzk).zzk();
                }
                break;
        }
        zzgeVar.zzi(zzgmVar.zzb, objZzk);
        return obj3;
    }

    @Override // com.google.android.recaptcha.internal.zzga
    final void zzf(Object obj) {
        ((zzgk) obj).zzb.zzg();
    }

    @Override // com.google.android.recaptcha.internal.zzga
    final void zzg(zzik zzikVar, Object obj, zzfz zzfzVar, zzge zzgeVar) throws IOException {
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzga
    final void zzh(zzez zzezVar, Object obj, zzfz zzfzVar, zzge zzgeVar) throws IOException {
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzga
    final void zzi(zzjx zzjxVar, Map.Entry entry) throws IOException {
        zzgl zzglVar = (zzgl) entry.getKey();
        zzjv zzjvVar = zzjv.DOUBLE;
        switch (zzglVar.zzb) {
            case DOUBLE:
                zzjxVar.zzf(zzglVar.zza, ((Double) entry.getValue()).doubleValue());
                break;
            case FLOAT:
                zzjxVar.zzo(zzglVar.zza, ((Float) entry.getValue()).floatValue());
                break;
            case INT64:
                zzjxVar.zzt(zzglVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case UINT64:
                zzjxVar.zzK(zzglVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case INT32:
                zzjxVar.zzr(zzglVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case FIXED64:
                zzjxVar.zzm(zzglVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case FIXED32:
                zzjxVar.zzk(zzglVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case BOOL:
                zzjxVar.zzb(zzglVar.zza, ((Boolean) entry.getValue()).booleanValue());
                break;
            case STRING:
                zzjxVar.zzG(zzglVar.zza, (String) entry.getValue());
                break;
            case GROUP:
                zzjxVar.zzq(zzglVar.zza, entry.getValue(), zzih.zza().zzb(entry.getValue().getClass()));
                break;
            case MESSAGE:
                zzjxVar.zzv(zzglVar.zza, entry.getValue(), zzih.zza().zzb(entry.getValue().getClass()));
                break;
            case BYTES:
                zzjxVar.zzd(zzglVar.zza, (zzez) entry.getValue());
                break;
            case UINT32:
                zzjxVar.zzI(zzglVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case ENUM:
                zzjxVar.zzr(zzglVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SFIXED32:
                zzjxVar.zzx(zzglVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SFIXED64:
                zzjxVar.zzz(zzglVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case SINT32:
                zzjxVar.zzB(zzglVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SINT64:
                zzjxVar.zzD(zzglVar.zza, ((Long) entry.getValue()).longValue());
                break;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzga
    final boolean zzj(zzhy zzhyVar) {
        return zzhyVar instanceof zzgk;
    }
}
