package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzao implements zzaj {
    public static final zzak zza = new zzak(null);
    private static Timer zzb;
    private final zzap zzc;
    private final CoroutineScope zzd;
    private final zzad zze;

    public /* synthetic */ zzao(Context context, zzap zzapVar, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        zzp zzpVar = zzp.zza;
        CoroutineScope coroutineScopeZza = zzp.zza();
        this.zzc = zzapVar;
        this.zzd = coroutineScopeZza;
        zzad zzadVar = zzad.zzb;
        zzadVar = zzadVar == null ? new zzad(context, null) : zzadVar;
        zzad.zzb = zzadVar;
        this.zze = zzadVar;
        zzh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg() {
        for (List list : CollectionsKt.windowed(this.zze.zzd(), 20, 20, true)) {
            zzkz zzkzVarZzf = zzla.zzf();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                zzkzVarZzf.zzd(zzkx.zzG(zzeb.zzg().zzj(((zzae) it.next()).zzc())));
            }
            if (this.zzc.zza(((zzla) zzkzVarZzf.zzj()).zzd())) {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    this.zze.zzf((zzae) it2.next());
                }
            }
        }
    }

    private final void zzh() {
        if (zzb == null) {
            zzb = new Timer();
            Timer timer = zzb;
            if (timer != null) {
                timer.schedule(new zzal(this), 120000L, 120000L);
            }
        }
    }

    public final void zzf(zzkx zzkxVar) {
        BuildersKt__Builders_commonKt.launch$default(this.zzd, null, null, new zzan(zzkxVar, this, null), 3, null);
        zzh();
    }
}
