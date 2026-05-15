package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzadz extends zzacd {
    private final String zza;
    private final /* synthetic */ zzadu zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzadz(zzadu zzaduVar, zzacd zzacdVar, String str) {
        super(zzacdVar);
        this.zzb = zzaduVar;
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacd
    public final void zzb(String str) {
        zzadu.zza.d("onCodeSent", new Object[0]);
        zzaeb zzaebVar = (zzaeb) this.zzb.zzd.get(this.zza);
        if (zzaebVar == null) {
            return;
        }
        Iterator<zzacd> it = zzaebVar.zzb.iterator();
        while (it.hasNext()) {
            it.next().zzb(str);
        }
        zzaebVar.zzg = true;
        zzaebVar.zzd = str;
        if (zzaebVar.zza <= 0) {
            this.zzb.zzb(this.zza);
        } else if (!zzaebVar.zzc) {
            this.zzb.zze(this.zza);
        } else if (!zzag.zzc(zzaebVar.zze)) {
            zzadu.zza(this.zzb, this.zza);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacd
    public final void zza(Status status) {
        zzadu.zza.e("SMS verification code request failed: " + CommonStatusCodes.getStatusCodeString(status.getStatusCode()) + " " + status.getStatusMessage(), new Object[0]);
        zzaeb zzaebVar = (zzaeb) this.zzb.zzd.get(this.zza);
        if (zzaebVar == null) {
            return;
        }
        Iterator<zzacd> it = zzaebVar.zzb.iterator();
        while (it.hasNext()) {
            it.next().zza(status);
        }
        this.zzb.zzc(this.zza);
    }
}
