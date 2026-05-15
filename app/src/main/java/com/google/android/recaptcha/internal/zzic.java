package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzic implements zzil {
    private final zzhy zza;
    private final zzjf zzb;
    private final boolean zzc;
    private final zzga zzd;

    private zzic(zzjf zzjfVar, zzga zzgaVar, zzhy zzhyVar) {
        this.zzb = zzjfVar;
        this.zzc = zzgaVar.zzj(zzhyVar);
        this.zzd = zzgaVar;
        this.zza = zzhyVar;
    }

    static zzic zzc(zzjf zzjfVar, zzga zzgaVar, zzhy zzhyVar) {
        return new zzic(zzjfVar, zzgaVar, zzhyVar);
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final int zza(Object obj) {
        zzjf zzjfVar = this.zzb;
        int iZzb = zzjfVar.zzb(zzjfVar.zzd(obj));
        return this.zzc ? iZzb + this.zzd.zzb(obj).zzb() : iZzb;
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final int zzb(Object obj) {
        int iHashCode = this.zzb.zzd(obj).hashCode();
        return this.zzc ? (iHashCode * 53) + this.zzd.zzb(obj).zza.hashCode() : iHashCode;
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final Object zze() {
        zzhy zzhyVar = this.zza;
        return zzhyVar instanceof zzgo ? ((zzgo) zzhyVar).zzs() : zzhyVar.zzV().zzk();
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final void zzf(Object obj) {
        this.zzb.zzm(obj);
        this.zzd.zzf(obj);
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final void zzg(Object obj, Object obj2) {
        zzin.zzE(this.zzb, obj, obj2);
        if (this.zzc) {
            zzin.zzD(this.zzd, obj, obj2);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final void zzh(Object obj, zzik zzikVar, zzfz zzfzVar) throws IOException {
        boolean zZzO;
        zzjf zzjfVar = this.zzb;
        zzga zzgaVar = this.zzd;
        Object objZzc = zzjfVar.zzc(obj);
        zzge zzgeVarZzc = zzgaVar.zzc(obj);
        while (zzikVar.zzc() != Integer.MAX_VALUE) {
            try {
                int iZzd = zzikVar.zzd();
                if (iZzd != 11) {
                    if ((iZzd & 7) == 2) {
                        Object objZzd = zzgaVar.zzd(zzfzVar, this.zza, iZzd >>> 3);
                        if (objZzd != null) {
                            zzgaVar.zzg(zzikVar, objZzd, zzfzVar, zzgeVarZzc);
                        } else {
                            zZzO = zzjfVar.zzr(objZzc, zzikVar);
                        }
                    } else {
                        zZzO = zzikVar.zzO();
                    }
                    if (!zZzO) {
                        break;
                    }
                } else {
                    Object objZzd2 = null;
                    int iZzj = 0;
                    zzez zzezVarZzp = null;
                    while (zzikVar.zzc() != Integer.MAX_VALUE) {
                        int iZzd2 = zzikVar.zzd();
                        if (iZzd2 == 16) {
                            iZzj = zzikVar.zzj();
                            objZzd2 = zzgaVar.zzd(zzfzVar, this.zza, iZzj);
                        } else if (iZzd2 == 26) {
                            if (objZzd2 != null) {
                                zzgaVar.zzg(zzikVar, objZzd2, zzfzVar, zzgeVarZzc);
                            } else {
                                zzezVarZzp = zzikVar.zzp();
                            }
                        } else if (!zzikVar.zzO()) {
                            break;
                        }
                    }
                    if (zzikVar.zzd() != 12) {
                        throw zzgy.zzb();
                    }
                    if (zzezVarZzp != null) {
                        if (objZzd2 != null) {
                            zzgaVar.zzh(zzezVarZzp, objZzd2, zzfzVar, zzgeVarZzc);
                        } else {
                            zzjfVar.zzk(objZzc, iZzj, zzezVarZzp);
                        }
                    }
                }
            } finally {
                zzjfVar.zzn(obj, objZzc);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0088 A[SYNTHETIC] */
    @Override // com.google.android.recaptcha.internal.zzil
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzi(java.lang.Object r9, byte[] r10, int r11, int r12, com.google.android.recaptcha.internal.zzem r13) throws java.io.IOException {
        /*
            r8 = this;
            r0 = r9
            com.google.android.recaptcha.internal.zzgo r0 = (com.google.android.recaptcha.internal.zzgo) r0
            com.google.android.recaptcha.internal.zzjg r1 = r0.zzc
            com.google.android.recaptcha.internal.zzjg r2 = com.google.android.recaptcha.internal.zzjg.zzc()
            if (r1 != r2) goto L12
            com.google.android.recaptcha.internal.zzjg r1 = com.google.android.recaptcha.internal.zzjg.zzf()
            r0.zzc = r1
            goto L13
        L12:
        L13:
            com.google.android.recaptcha.internal.zzgk r9 = (com.google.android.recaptcha.internal.zzgk) r9
            r9.zzi()
            r9 = 0
            r0 = r9
        L1a:
            if (r11 >= r12) goto L94
            int r4 = com.google.android.recaptcha.internal.zzen.zzj(r10, r11, r13)
            int r2 = r13.zza
            r11 = 11
            r3 = 2
            if (r2 == r11) goto L4a
            r11 = r2 & 7
            if (r11 != r3) goto L45
            com.google.android.recaptcha.internal.zzga r11 = r8.zzd
            com.google.android.recaptcha.internal.zzfz r0 = r13.zzd
            com.google.android.recaptcha.internal.zzhy r3 = r8.zza
            int r5 = r2 >>> 3
            java.lang.Object r0 = r11.zzd(r0, r3, r5)
            if (r0 != 0) goto L42
            r3 = r10
            r5 = r12
            r6 = r1
            r7 = r13
            int r11 = com.google.android.recaptcha.internal.zzen.zzi(r2, r3, r4, r5, r6, r7)
            goto L1a
        L42:
            int r10 = com.google.android.recaptcha.internal.zzih.zza
            throw r9
        L45:
            int r11 = com.google.android.recaptcha.internal.zzen.zzp(r2, r10, r4, r12, r13)
            goto L1a
        L4a:
            r11 = 0
            r2 = r9
        L4c:
            if (r4 >= r12) goto L88
            int r4 = com.google.android.recaptcha.internal.zzen.zzj(r10, r4, r13)
            int r5 = r13.zza
            int r6 = r5 >>> 3
            r7 = r5 & 7
            switch(r6) {
                case 2: goto L6c;
                case 3: goto L5c;
                default: goto L5b;
            }
        L5b:
            goto L7f
        L5c:
            if (r0 != 0) goto L69
            if (r7 != r3) goto L7f
            int r4 = com.google.android.recaptcha.internal.zzen.zza(r10, r4, r13)
            java.lang.Object r2 = r13.zzc
            com.google.android.recaptcha.internal.zzez r2 = (com.google.android.recaptcha.internal.zzez) r2
            goto L4c
        L69:
            int r10 = com.google.android.recaptcha.internal.zzih.zza
            throw r9
        L6c:
            if (r7 != 0) goto L7f
            int r4 = com.google.android.recaptcha.internal.zzen.zzj(r10, r4, r13)
            int r11 = r13.zza
            com.google.android.recaptcha.internal.zzga r0 = r8.zzd
            com.google.android.recaptcha.internal.zzfz r5 = r13.zzd
            com.google.android.recaptcha.internal.zzhy r6 = r8.zza
            java.lang.Object r0 = r0.zzd(r5, r6, r11)
            goto L4c
        L7f:
            r6 = 12
            if (r5 == r6) goto L88
            int r4 = com.google.android.recaptcha.internal.zzen.zzp(r5, r10, r4, r12, r13)
            goto L4c
        L88:
            if (r2 == 0) goto L91
            int r11 = r11 << 3
            r11 = r11 | r3
            r1.zzj(r11, r2)
            goto L92
        L91:
        L92:
            r11 = r4
            goto L1a
        L94:
            if (r11 != r12) goto L97
            return
        L97:
            com.google.android.recaptcha.internal.zzgy r9 = com.google.android.recaptcha.internal.zzgy.zzg()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzic.zzi(java.lang.Object, byte[], int, int, com.google.android.recaptcha.internal.zzem):void");
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final void zzj(Object obj, zzjx zzjxVar) throws IOException {
        Iterator itZzf = this.zzd.zzb(obj).zzf();
        while (itZzf.hasNext()) {
            Map.Entry entry = (Map.Entry) itZzf.next();
            zzgd zzgdVar = (zzgd) entry.getKey();
            if (zzgdVar.zze() != zzjw.MESSAGE) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            zzgdVar.zzg();
            zzgdVar.zzf();
            if (entry instanceof zzhb) {
                zzjxVar.zzw(zzgdVar.zza(), ((zzhb) entry).zza().zzb());
            } else {
                zzjxVar.zzw(zzgdVar.zza(), entry.getValue());
            }
        }
        zzjf zzjfVar = this.zzb;
        zzjfVar.zzp(zzjfVar.zzd(obj), zzjxVar);
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final boolean zzk(Object obj, Object obj2) {
        if (!this.zzb.zzd(obj).equals(this.zzb.zzd(obj2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zzb(obj).equals(this.zzd.zzb(obj2));
        }
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final boolean zzl(Object obj) {
        return this.zzd.zzb(obj).zzk();
    }
}
