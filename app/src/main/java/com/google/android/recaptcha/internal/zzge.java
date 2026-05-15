package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzge {
    private static final zzge zzb = new zzge(true);
    final zziy zza = new zzio(16);
    private boolean zzc;
    private boolean zzd;

    private zzge() {
    }

    public static int zza(zzgd zzgdVar, Object obj) {
        zzjv zzjvVarZzd = zzgdVar.zzd();
        int iZza = zzgdVar.zza();
        zzgdVar.zzg();
        int i = zzfk.zzb;
        int iZzy = zzfk.zzy(iZza << 3);
        if (zzjvVarZzd == zzjv.GROUP) {
            zzhy zzhyVar = (zzhy) obj;
            byte[] bArr = zzgw.zzd;
            if (zzhyVar instanceof zzej) {
                throw null;
            }
            iZzy += iZzy;
        }
        zzjw zzjwVar = zzjw.INT;
        int iZzz = 4;
        switch (zzjvVarZzd) {
            case DOUBLE:
                ((Double) obj).doubleValue();
                iZzz = 8;
                break;
            case FLOAT:
                ((Float) obj).floatValue();
                break;
            case INT64:
                iZzz = zzfk.zzz(((Long) obj).longValue());
                break;
            case UINT64:
                iZzz = zzfk.zzz(((Long) obj).longValue());
                break;
            case INT32:
                iZzz = zzfk.zzu(((Integer) obj).intValue());
                break;
            case FIXED64:
                ((Long) obj).longValue();
                iZzz = 8;
                break;
            case FIXED32:
                ((Integer) obj).intValue();
                break;
            case BOOL:
                ((Boolean) obj).booleanValue();
                iZzz = 1;
                break;
            case STRING:
                if (!(obj instanceof zzez)) {
                    iZzz = zzfk.zzx((String) obj);
                } else {
                    int iZzd = ((zzez) obj).zzd();
                    iZzz = zzfk.zzy(iZzd) + iZzd;
                }
                break;
            case GROUP:
                iZzz = ((zzhy) obj).zzn();
                break;
            case MESSAGE:
                if (!(obj instanceof zzhd)) {
                    iZzz = zzfk.zzv((zzhy) obj);
                } else {
                    int iZza2 = ((zzhd) obj).zza();
                    iZzz = zzfk.zzy(iZza2) + iZza2;
                }
                break;
            case BYTES:
                if (!(obj instanceof zzez)) {
                    int length = ((byte[]) obj).length;
                    iZzz = zzfk.zzy(length) + length;
                } else {
                    int iZzd2 = ((zzez) obj).zzd();
                    iZzz = zzfk.zzy(iZzd2) + iZzd2;
                }
                break;
            case UINT32:
                iZzz = zzfk.zzy(((Integer) obj).intValue());
                break;
            case ENUM:
                iZzz = !(obj instanceof zzgq) ? zzfk.zzu(((Integer) obj).intValue()) : zzfk.zzu(((zzgq) obj).zza());
                break;
            case SFIXED32:
                ((Integer) obj).intValue();
                break;
            case SFIXED64:
                ((Long) obj).longValue();
                iZzz = 8;
                break;
            case SINT32:
                int iIntValue = ((Integer) obj).intValue();
                iZzz = zzfk.zzy((iIntValue >> 31) ^ (iIntValue + iIntValue));
                break;
            case SINT64:
                long jLongValue = ((Long) obj).longValue();
                iZzz = zzfk.zzz((jLongValue >> 63) ^ (jLongValue + jLongValue));
                break;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return iZzy + iZzz;
    }

    public static zzge zzd() {
        return zzb;
    }

    private static Object zzl(Object obj) {
        if (obj instanceof zzid) {
            return ((zzid) obj).zzd();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private final void zzm(Map.Entry entry) {
        zzhy zzhyVarZzj;
        zzgd zzgdVar = (zzgd) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzhd) {
            throw null;
        }
        zzgdVar.zzg();
        if (zzgdVar.zze() != zzjw.MESSAGE) {
            this.zza.put(zzgdVar, zzl(value));
            return;
        }
        Object objZze = zze(zzgdVar);
        if (objZze == null) {
            this.zza.put(zzgdVar, zzl(value));
            return;
        }
        if (objZze instanceof zzid) {
            zzhyVarZzj = zzgdVar.zzc((zzid) objZze, (zzid) value);
        } else {
            zzhx zzhxVarZzW = ((zzhy) objZze).zzW();
            zzgdVar.zzb(zzhxVarZzW, (zzhy) value);
            zzhyVarZzj = zzhxVarZzW.zzj();
        }
        this.zza.put(zzgdVar, zzhyVarZzj);
    }

    private static boolean zzn(Map.Entry entry) {
        zzgd zzgdVar = (zzgd) entry.getKey();
        if (zzgdVar.zze() != zzjw.MESSAGE) {
            return true;
        }
        zzgdVar.zzg();
        Object value = entry.getValue();
        if (value instanceof zzhz) {
            return ((zzhz) value).zzo();
        }
        if (value instanceof zzhd) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzo(Map.Entry entry) {
        zzgd zzgdVar = (zzgd) entry.getKey();
        Object value = entry.getValue();
        if (zzgdVar.zze() != zzjw.MESSAGE) {
            return zza(zzgdVar, value);
        }
        zzgdVar.zzg();
        zzgdVar.zzf();
        if (!(value instanceof zzhd)) {
            int iZzy = zzfk.zzy(((zzgd) entry.getKey()).zza());
            int iZzy2 = zzfk.zzy(24) + zzfk.zzv((zzhy) value);
            int iZzy3 = zzfk.zzy(16);
            int iZzy4 = zzfk.zzy(8);
            return iZzy4 + iZzy4 + iZzy3 + iZzy + iZzy2;
        }
        int iZzy5 = zzfk.zzy(((zzgd) entry.getKey()).zza());
        int iZza = ((zzhd) value).zza();
        int iZzy6 = zzfk.zzy(iZza) + iZza;
        int iZzy7 = zzfk.zzy(24);
        int iZzy8 = zzfk.zzy(16);
        int iZzy9 = zzfk.zzy(8);
        return iZzy9 + iZzy9 + iZzy8 + iZzy5 + iZzy7 + iZzy6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzge) {
            return this.zza.equals(((zzge) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzb() {
        int iZzo = 0;
        for (int i = 0; i < this.zza.zzb(); i++) {
            iZzo += zzo(this.zza.zzg(i));
        }
        Iterator it = this.zza.zzc().iterator();
        while (it.hasNext()) {
            iZzo += zzo((Map.Entry) it.next());
        }
        return iZzo;
    }

    /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final zzge clone() {
        zzge zzgeVar = new zzge();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry entryZzg = this.zza.zzg(i);
            zzgeVar.zzi((zzgd) entryZzg.getKey(), entryZzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzgeVar.zzi((zzgd) entry.getKey(), entry.getValue());
        }
        zzgeVar.zzd = this.zzd;
        return zzgeVar;
    }

    public final Object zze(zzgd zzgdVar) {
        Object obj = this.zza.get(zzgdVar);
        if (!(obj instanceof zzhd)) {
            return obj;
        }
        throw null;
    }

    public final Iterator zzf() {
        return this.zzd ? new zzhc(this.zza.entrySet().iterator()) : this.zza.entrySet().iterator();
    }

    public final void zzg() {
        if (this.zzc) {
            return;
        }
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry entryZzg = this.zza.zzg(i);
            if (entryZzg.getValue() instanceof zzgo) {
                ((zzgo) entryZzg.getValue()).zzA();
            }
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzh(zzge zzgeVar) {
        for (int i = 0; i < zzgeVar.zza.zzb(); i++) {
            zzm(zzgeVar.zza.zzg(i));
        }
        Iterator it = zzgeVar.zza.zzc().iterator();
        while (it.hasNext()) {
            zzm((Map.Entry) it.next());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0021, code lost:
    
        if ((r4 instanceof com.google.android.recaptcha.internal.zzhd) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002a, code lost:
    
        if ((r4 instanceof com.google.android.recaptcha.internal.zzgq) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0033, code lost:
    
        if ((r4 instanceof byte[]) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0047, code lost:
    
        if (r0 == false) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzi(com.google.android.recaptcha.internal.zzgd r3, java.lang.Object r4) {
        /*
            r2 = this;
            r3.zzg()
            com.google.android.recaptcha.internal.zzjv r0 = r3.zzd()
            byte[] r1 = com.google.android.recaptcha.internal.zzgw.zzd
            if (r4 == 0) goto L7e
            com.google.android.recaptcha.internal.zzjv r1 = com.google.android.recaptcha.internal.zzjv.DOUBLE
            com.google.android.recaptcha.internal.zzjw r1 = com.google.android.recaptcha.internal.zzjw.INT
            com.google.android.recaptcha.internal.zzjw r0 = r0.zza()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L45;
                case 1: goto L42;
                case 2: goto L3f;
                case 3: goto L3c;
                case 4: goto L39;
                case 5: goto L36;
                case 6: goto L2d;
                case 7: goto L24;
                case 8: goto L1b;
                default: goto L1a;
            }
        L1a:
            goto L56
        L1b:
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzhy
            if (r0 != 0) goto L49
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzhd
            if (r0 == 0) goto L56
            goto L49
        L24:
            boolean r0 = r4 instanceof java.lang.Integer
            if (r0 != 0) goto L49
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzgq
            if (r0 == 0) goto L56
            goto L49
        L2d:
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzez
            if (r0 != 0) goto L49
            boolean r0 = r4 instanceof byte[]
            if (r0 == 0) goto L56
            goto L49
        L36:
            boolean r0 = r4 instanceof java.lang.String
            goto L47
        L39:
            boolean r0 = r4 instanceof java.lang.Boolean
            goto L47
        L3c:
            boolean r0 = r4 instanceof java.lang.Double
            goto L47
        L3f:
            boolean r0 = r4 instanceof java.lang.Float
            goto L47
        L42:
            boolean r0 = r4 instanceof java.lang.Long
            goto L47
        L45:
            boolean r0 = r4 instanceof java.lang.Integer
        L47:
            if (r0 == 0) goto L56
        L49:
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzhd
            if (r0 == 0) goto L50
            r0 = 1
            r2.zzd = r0
        L50:
            com.google.android.recaptcha.internal.zziy r0 = r2.zza
            r0.put(r3, r4)
            return
        L56:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            int r1 = r3.zza()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.google.android.recaptcha.internal.zzjv r3 = r3.zzd()
            com.google.android.recaptcha.internal.zzjw r3 = r3.zza()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.Object[] r3 = new java.lang.Object[]{r1, r3, r4}
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r0.<init>(r3)
            throw r0
        L7e:
            r3 = 0
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzge.zzi(com.google.android.recaptcha.internal.zzgd, java.lang.Object):void");
    }

    public final boolean zzj() {
        return this.zzc;
    }

    public final boolean zzk() {
        for (int i = 0; i < this.zza.zzb(); i++) {
            if (!zzn(this.zza.zzg(i))) {
                return false;
            }
        }
        Iterator it = this.zza.zzc().iterator();
        while (it.hasNext()) {
            if (!zzn((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    private zzge(boolean z) {
        zzg();
        zzg();
    }
}
