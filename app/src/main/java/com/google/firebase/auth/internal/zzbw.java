package com.google.firebase.auth.internal;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.internal.p001firebaseauthapi.zzafn;
import com.google.firebase.FirebaseApp;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbw {
    private volatile int zza;
    private final zzap zzb;
    private volatile boolean zzc;

    public zzbw(FirebaseApp firebaseApp) {
        this(firebaseApp.getApplicationContext(), new zzap(firebaseApp));
    }

    private zzbw(Context context, zzap zzapVar) {
        this.zzc = false;
        this.zza = 0;
        this.zzb = zzapVar;
        BackgroundDetector.initialize((Application) context.getApplicationContext());
        BackgroundDetector.getInstance().addListener(new zzbz(this));
    }

    public final void zza() {
        this.zzb.zzb();
    }

    public final void zza(int i) {
        if (i > 0 && this.zza == 0) {
            this.zza = i;
            if (zzb()) {
                this.zzb.zzc();
            }
        } else if (i == 0 && this.zza != 0) {
            this.zzb.zzb();
        }
        this.zza = i;
    }

    public final void zza(zzafn zzafnVar) {
        if (zzafnVar == null) {
            return;
        }
        long jZza = zzafnVar.zza();
        if (jZza <= 0) {
            jZza = 3600;
        }
        long jZzb = zzafnVar.zzb() + (jZza * 1000);
        zzap zzapVar = this.zzb;
        zzapVar.zza = jZzb;
        zzapVar.zzb = -1L;
        if (zzb()) {
            this.zzb.zzc();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzb() {
        return this.zza > 0 && !this.zzc;
    }
}
