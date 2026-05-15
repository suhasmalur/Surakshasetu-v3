package com.google.android.recaptcha.internal;

import java.util.HashMap;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbn {
    private final zzbm zza = new zzbm();
    private byte zzb = (byte) RangesKt.random(new IntRange(1, WorkQueueKt.MASK), Random.INSTANCE);
    private final HashMap zzc = new HashMap();

    public zzbn() {
        this.zza.zze(173, this.zzc);
    }

    public final byte zza() {
        return this.zzb;
    }

    public final zzbm zzb() {
        return this.zza;
    }

    public final void zzc() {
        this.zza.zzd();
        this.zza.zze(173, this.zzc);
    }

    public final void zzd(byte b) {
        this.zzb = b;
    }

    public final void zze(int i, Object obj) {
        this.zzc.put(1, obj);
    }
}
