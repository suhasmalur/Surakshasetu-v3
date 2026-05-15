package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaij implements zzalc {
    private final zzaia zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final double zza() throws IOException {
        zzb(1);
        return this.zza.zza();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final float zzb() throws IOException {
        zzb(5);
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final int zzc() throws IOException {
        if (this.zzd != 0) {
            this.zzb = this.zzd;
            this.zzd = 0;
        } else {
            this.zzb = this.zza.zzi();
        }
        if (this.zzb == 0 || this.zzb == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return this.zzb >>> 3;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final int zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final int zze() throws IOException {
        zzb(0);
        return this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final int zzf() throws IOException {
        zzb(5);
        return this.zza.zze();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final int zzg() throws IOException {
        zzb(0);
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final int zzh() throws IOException {
        zzb(5);
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final int zzi() throws IOException {
        zzb(0);
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final int zzj() throws IOException {
        zzb(0);
        return this.zza.zzj();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final long zzk() throws IOException {
        zzb(1);
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final long zzl() throws IOException {
        zzb(0);
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final long zzm() throws IOException {
        zzb(1);
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final long zzn() throws IOException {
        zzb(0);
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final long zzo() throws IOException {
        zzb(0);
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final zzahp zzp() throws IOException {
        zzb(2);
        return this.zza.zzq();
    }

    public static zzaij zza(zzaia zzaiaVar) {
        if (zzaiaVar.zzd != null) {
            return zzaiaVar.zzd;
        }
        return new zzaij(zzaiaVar);
    }

    private final Object zza(zzamr zzamrVar, Class<?> cls, zzaio zzaioVar) throws IOException {
        switch (zzaii.zza[zzamrVar.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzs());
            case 2:
                return zzp();
            case 3:
                return Double.valueOf(zza());
            case 4:
                return Integer.valueOf(zze());
            case 5:
                return Integer.valueOf(zzf());
            case 6:
                return Long.valueOf(zzk());
            case 7:
                return Float.valueOf(zzb());
            case 8:
                return Integer.valueOf(zzg());
            case 9:
                return Long.valueOf(zzl());
            case 10:
                zzb(2);
                return zzb(zzalb.zza().zza((Class) cls), zzaioVar);
            case 11:
                return Integer.valueOf(zzh());
            case 12:
                return Long.valueOf(zzm());
            case 13:
                return Integer.valueOf(zzi());
            case 14:
                return Long.valueOf(zzn());
            case 15:
                return zzr();
            case 16:
                return Integer.valueOf(zzj());
            case 17:
                return Long.valueOf(zzo());
            default:
                throw new IllegalArgumentException("unsupported field type.");
        }
    }

    private final <T> T zza(zzalf<T> zzalfVar, zzaio zzaioVar) throws IOException {
        T tZza = zzalfVar.zza();
        zzc(tZza, zzalfVar, zzaioVar);
        zzalfVar.zzc(tZza);
        return tZza;
    }

    private final <T> T zzb(zzalf<T> zzalfVar, zzaio zzaioVar) throws IOException {
        T tZza = zzalfVar.zza();
        zzd(tZza, zzalfVar, zzaioVar);
        zzalfVar.zzc(tZza);
        return tZza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final String zzq() throws IOException {
        zzb(2);
        return this.zza.zzr();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final String zzr() throws IOException {
        zzb(2);
        return this.zza.zzs();
    }

    private zzaij(zzaia zzaiaVar) {
        this.zza = (zzaia) zzajf.zza(zzaiaVar, "input");
        this.zza.zzd = this;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final <T> void zza(T t, zzalf<T> zzalfVar, zzaio zzaioVar) throws IOException {
        zzb(3);
        zzc(t, zzalfVar, zzaioVar);
    }

    private final <T> void zzc(T t, zzalf<T> zzalfVar, zzaio zzaioVar) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            zzalfVar.zza(t, this, zzaioVar);
            if (this.zzb != this.zzc) {
                throw zzaji.zzg();
            }
        } finally {
            this.zzc = i;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final <T> void zzb(T t, zzalf<T> zzalfVar, zzaio zzaioVar) throws IOException {
        zzb(2);
        zzd(t, zzalfVar, zzaioVar);
    }

    private final <T> void zzd(T t, zzalf<T> zzalfVar, zzaio zzaioVar) throws IOException {
        int iZzj = this.zza.zzj();
        if (this.zza.zza >= this.zza.zzb) {
            throw new zzaji("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iZzb = this.zza.zzb(iZzj);
        this.zza.zza++;
        zzalfVar.zza(t, this, zzaioVar);
        this.zza.zzc(0);
        zzaia zzaiaVar = this.zza;
        zzaiaVar.zza--;
        this.zza.zzd(iZzb);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zza(List<Boolean> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzahn) {
            zzahn zzahnVar = (zzahn) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzaji.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzahnVar.zza(this.zza.zzu());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzahnVar.zza(this.zza.zzu());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzaji.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Boolean.valueOf(this.zza.zzu()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Boolean.valueOf(this.zza.zzu()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzb(List<zzahp> list) throws IOException {
        int iZzi;
        if ((this.zzb & 7) != 2) {
            throw zzaji.zza();
        }
        do {
            list.add(zzp());
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzc(List<Double> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzaim) {
            zzaim zzaimVar = (zzaim) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int iZzj = this.zza.zzj();
                    zzd(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzaimVar.zza(this.zza.zza());
                    } while (this.zza.zzc() < iZzc);
                    return;
                default:
                    throw zzaji.zza();
            }
            do {
                zzaimVar.zza(this.zza.zza());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int iZzj2 = this.zza.zzj();
                zzd(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Double.valueOf(this.zza.zza()));
                } while (this.zza.zzc() < iZzc2);
                return;
            default:
                throw zzaji.zza();
        }
        do {
            list.add(Double.valueOf(this.zza.zza()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzd(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajd) {
            zzajd zzajdVar = (zzajd) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzaji.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzajdVar.zzc(this.zza.zzd());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzajdVar.zzc(this.zza.zzd());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzaji.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzd()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Integer.valueOf(this.zza.zzd()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zze(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajd) {
            zzajd zzajdVar = (zzajd) list;
            switch (this.zzb & 7) {
                case 2:
                    int iZzj = this.zza.zzj();
                    zzc(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzajdVar.zzc(this.zza.zze());
                    } while (this.zza.zzc() < iZzc);
                    return;
                case 5:
                    break;
                default:
                    throw zzaji.zza();
            }
            do {
                zzajdVar.zzc(this.zza.zze());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int iZzj2 = this.zza.zzj();
                zzc(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Integer.valueOf(this.zza.zze()));
                } while (this.zza.zzc() < iZzc2);
                return;
            case 5:
                break;
            default:
                throw zzaji.zza();
        }
        do {
            list.add(Integer.valueOf(this.zza.zze()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzf(List<Long> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajy) {
            zzajy zzajyVar = (zzajy) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int iZzj = this.zza.zzj();
                    zzd(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzajyVar.zza(this.zza.zzk());
                    } while (this.zza.zzc() < iZzc);
                    return;
                default:
                    throw zzaji.zza();
            }
            do {
                zzajyVar.zza(this.zza.zzk());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int iZzj2 = this.zza.zzj();
                zzd(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Long.valueOf(this.zza.zzk()));
                } while (this.zza.zzc() < iZzc2);
                return;
            default:
                throw zzaji.zza();
        }
        do {
            list.add(Long.valueOf(this.zza.zzk()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzg(List<Float> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajb) {
            zzajb zzajbVar = (zzajb) list;
            switch (this.zzb & 7) {
                case 2:
                    int iZzj = this.zza.zzj();
                    zzc(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzajbVar.zza(this.zza.zzb());
                    } while (this.zza.zzc() < iZzc);
                    return;
                case 5:
                    break;
                default:
                    throw zzaji.zza();
            }
            do {
                zzajbVar.zza(this.zza.zzb());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int iZzj2 = this.zza.zzj();
                zzc(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Float.valueOf(this.zza.zzb()));
                } while (this.zza.zzc() < iZzc2);
                return;
            case 5:
                break;
            default:
                throw zzaji.zza();
        }
        do {
            list.add(Float.valueOf(this.zza.zzb()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    @Deprecated
    public final <T> void zza(List<T> list, zzalf<T> zzalfVar, zzaio zzaioVar) throws IOException {
        int iZzi;
        if ((this.zzb & 7) != 3) {
            throw zzaji.zza();
        }
        int i = this.zzb;
        do {
            list.add(zza(zzalfVar, zzaioVar));
            if (this.zza.zzt() || this.zzd != 0) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == i);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzh(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajd) {
            zzajd zzajdVar = (zzajd) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzaji.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzajdVar.zzc(this.zza.zzf());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzajdVar.zzc(this.zza.zzf());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzaji.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Integer.valueOf(this.zza.zzf()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzi(List<Long> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajy) {
            zzajy zzajyVar = (zzajy) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzaji.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzajyVar.zza(this.zza.zzl());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzajyVar.zza(this.zza.zzl());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzaji.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Long.valueOf(this.zza.zzl()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Long.valueOf(this.zza.zzl()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005c, code lost:
    
        r7.put(r1, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0064, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <K, V> void zza(java.util.Map<K, V> r7, com.google.android.gms.internal.p001firebaseauthapi.zzake<K, V> r8, com.google.android.gms.internal.p001firebaseauthapi.zzaio r9) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 2
            r6.zzb(r0)
            com.google.android.gms.internal.firebase-auth-api.zzaia r0 = r6.zza
            int r0 = r0.zzj()
            com.google.android.gms.internal.firebase-auth-api.zzaia r1 = r6.zza
            int r0 = r1.zzb(r0)
            K r1 = r8.zzb
            V r2 = r8.zzd
        L14:
            int r3 = r6.zzc()     // Catch: java.lang.Throwable -> L65
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L5c
            com.google.android.gms.internal.firebase-auth-api.zzaia r4 = r6.zza     // Catch: java.lang.Throwable -> L65
            boolean r4 = r4.zzt()     // Catch: java.lang.Throwable -> L65
            if (r4 != 0) goto L5c
            java.lang.String r4 = "Unable to parse map entry."
            switch(r3) {
                case 1: goto L3c;
                case 2: goto L2f;
                default: goto L2a;
            }
        L2a:
            boolean r3 = r6.zzt()     // Catch: com.google.android.gms.internal.p001firebaseauthapi.zzajl -> L44 java.lang.Throwable -> L65
            goto L46
        L2f:
            com.google.android.gms.internal.firebase-auth-api.zzamr r3 = r8.zzc     // Catch: com.google.android.gms.internal.p001firebaseauthapi.zzajl -> L44 java.lang.Throwable -> L65
            V r5 = r8.zzd     // Catch: com.google.android.gms.internal.p001firebaseauthapi.zzajl -> L44 java.lang.Throwable -> L65
            java.lang.Class r5 = r5.getClass()     // Catch: com.google.android.gms.internal.p001firebaseauthapi.zzajl -> L44 java.lang.Throwable -> L65
            java.lang.Object r2 = r6.zza(r3, r5, r9)     // Catch: com.google.android.gms.internal.p001firebaseauthapi.zzajl -> L44 java.lang.Throwable -> L65
            goto L14
        L3c:
            com.google.android.gms.internal.firebase-auth-api.zzamr r3 = r8.zza     // Catch: com.google.android.gms.internal.p001firebaseauthapi.zzajl -> L44 java.lang.Throwable -> L65
            r5 = 0
            java.lang.Object r1 = r6.zza(r3, r5, r5)     // Catch: com.google.android.gms.internal.p001firebaseauthapi.zzajl -> L44 java.lang.Throwable -> L65
            goto L14
        L44:
            r3 = move-exception
            goto L4f
        L46:
            if (r3 == 0) goto L49
            goto L14
        L49:
            com.google.android.gms.internal.firebase-auth-api.zzaji r3 = new com.google.android.gms.internal.firebase-auth-api.zzaji     // Catch: com.google.android.gms.internal.p001firebaseauthapi.zzajl -> L44 java.lang.Throwable -> L65
            r3.<init>(r4)     // Catch: com.google.android.gms.internal.p001firebaseauthapi.zzajl -> L44 java.lang.Throwable -> L65
            throw r3     // Catch: com.google.android.gms.internal.p001firebaseauthapi.zzajl -> L44 java.lang.Throwable -> L65
        L4f:
            boolean r3 = r6.zzt()     // Catch: java.lang.Throwable -> L65
            if (r3 == 0) goto L56
            goto L14
        L56:
            com.google.android.gms.internal.firebase-auth-api.zzaji r7 = new com.google.android.gms.internal.firebase-auth-api.zzaji     // Catch: java.lang.Throwable -> L65
            r7.<init>(r4)     // Catch: java.lang.Throwable -> L65
            throw r7     // Catch: java.lang.Throwable -> L65
        L5c:
            r7.put(r1, r2)     // Catch: java.lang.Throwable -> L65
            com.google.android.gms.internal.firebase-auth-api.zzaia r7 = r6.zza
            r7.zzd(r0)
            return
        L65:
            r7 = move-exception
            com.google.android.gms.internal.firebase-auth-api.zzaia r8 = r6.zza
            r8.zzd(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p001firebaseauthapi.zzaij.zza(java.util.Map, com.google.android.gms.internal.firebase-auth-api.zzake, com.google.android.gms.internal.firebase-auth-api.zzaio):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final <T> void zzb(List<T> list, zzalf<T> zzalfVar, zzaio zzaioVar) throws IOException {
        int iZzi;
        if ((this.zzb & 7) != 2) {
            throw zzaji.zza();
        }
        int i = this.zzb;
        do {
            list.add(zzb(zzalfVar, zzaioVar));
            if (this.zza.zzt() || this.zzd != 0) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == i);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzj(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajd) {
            zzajd zzajdVar = (zzajd) list;
            switch (this.zzb & 7) {
                case 2:
                    int iZzj = this.zza.zzj();
                    zzc(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzajdVar.zzc(this.zza.zzg());
                    } while (this.zza.zzc() < iZzc);
                    return;
                case 5:
                    break;
                default:
                    throw zzaji.zza();
            }
            do {
                zzajdVar.zzc(this.zza.zzg());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int iZzj2 = this.zza.zzj();
                zzc(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                } while (this.zza.zzc() < iZzc2);
                return;
            case 5:
                break;
            default:
                throw zzaji.zza();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzg()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzk(List<Long> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajy) {
            zzajy zzajyVar = (zzajy) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int iZzj = this.zza.zzj();
                    zzd(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzajyVar.zza(this.zza.zzn());
                    } while (this.zza.zzc() < iZzc);
                    return;
                default:
                    throw zzaji.zza();
            }
            do {
                zzajyVar.zza(this.zza.zzn());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int iZzj2 = this.zza.zzj();
                zzd(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Long.valueOf(this.zza.zzn()));
                } while (this.zza.zzc() < iZzc2);
                return;
            default:
                throw zzaji.zza();
        }
        do {
            list.add(Long.valueOf(this.zza.zzn()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzl(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajd) {
            zzajd zzajdVar = (zzajd) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzaji.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzajdVar.zzc(this.zza.zzh());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzajdVar.zzc(this.zza.zzh());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzaji.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Integer.valueOf(this.zza.zzh()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzm(List<Long> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajy) {
            zzajy zzajyVar = (zzajy) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzaji.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzajyVar.zza(this.zza.zzo());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzajyVar.zza(this.zza.zzo());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzaji.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Long.valueOf(this.zza.zzo()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzn(List<String> list) throws IOException {
        zza(list, false);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int iZzi;
        int iZzi2;
        if ((this.zzb & 7) != 2) {
            throw zzaji.zza();
        }
        if ((list instanceof zzajt) && !z) {
            zzajt zzajtVar = (zzajt) list;
            do {
                zzajtVar.zza(zzp());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        do {
            list.add(z ? zzr() : zzq());
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzo(List<String> list) throws IOException {
        zza(list, true);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzp(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajd) {
            zzajd zzajdVar = (zzajd) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzaji.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzajdVar.zzc(this.zza.zzj());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzajdVar.zzc(this.zza.zzj());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzaji.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzj()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Integer.valueOf(this.zza.zzj()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final void zzq(List<Long> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajy) {
            zzajy zzajyVar = (zzajy) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzaji.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzajyVar.zza(this.zza.zzp());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzajyVar.zza(this.zza.zzp());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzaji.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Long.valueOf(this.zza.zzp()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    private final void zza(int i) throws IOException {
        if (this.zza.zzc() != i) {
            throw zzaji.zzi();
        }
    }

    private final void zzb(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzaji.zza();
        }
    }

    private static void zzc(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzaji.zzg();
        }
    }

    private static void zzd(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzaji.zzg();
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final boolean zzs() throws IOException {
        zzb(0);
        return this.zza.zzu();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalc
    public final boolean zzt() throws IOException {
        if (this.zza.zzt() || this.zzb == this.zzc) {
            return false;
        }
        return this.zza.zze(this.zzb);
    }
}
