package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzame<T, B> {
    zzame() {
    }

    abstract int zza(T t);

    abstract B zza();

    abstract T zza(T t, T t2);

    abstract void zza(B b, int i, int i2);

    abstract void zza(B b, int i, long j);

    abstract void zza(B b, int i, zzahp zzahpVar);

    abstract void zza(B b, int i, T t);

    abstract void zza(T t, zzana zzanaVar) throws IOException;

    abstract boolean zza(zzalc zzalcVar);

    abstract int zzb(T t);

    abstract void zzb(B b, int i, long j);

    abstract void zzb(T t, zzana zzanaVar) throws IOException;

    abstract void zzb(Object obj, B b);

    abstract B zzc(Object obj);

    abstract void zzc(Object obj, T t);

    abstract T zzd(Object obj);

    abstract T zze(B b);

    abstract void zzf(Object obj);

    final boolean zza(B b, zzalc zzalcVar) throws IOException {
        int iZzd = zzalcVar.zzd();
        int i = iZzd >>> 3;
        switch (iZzd & 7) {
            case 0:
                zzb(b, i, zzalcVar.zzl());
                return true;
            case 1:
                zza(b, i, zzalcVar.zzk());
                return true;
            case 2:
                zza((Object) b, i, zzalcVar.zzp());
                return true;
            case 3:
                B bZza = zza();
                int i2 = (i << 3) | 4;
                while (zzalcVar.zzc() != Integer.MAX_VALUE && zza((Object) bZza, zzalcVar)) {
                }
                if (i2 != zzalcVar.zzd()) {
                    throw zzaji.zzb();
                }
                zza(b, i, zze(bZza));
                return true;
            case 4:
                return false;
            case 5:
                zza((Object) b, i, zzalcVar.zzf());
                return true;
            default:
                throw zzaji.zza();
        }
    }
}
