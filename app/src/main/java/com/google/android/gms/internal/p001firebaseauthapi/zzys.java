package com.google.android.gms.internal.p001firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzys implements zzadk<zzagf> {
    private final /* synthetic */ zzagc zza;
    private final /* synthetic */ zzafc zzb;
    private final /* synthetic */ zzacd zzc;
    private final /* synthetic */ zzafn zzd;
    private final /* synthetic */ zzadl zze;
    private final /* synthetic */ zzyj zzf;

    zzys(zzyj zzyjVar, zzagc zzagcVar, zzafc zzafcVar, zzacd zzacdVar, zzafn zzafnVar, zzadl zzadlVar) {
        this.zzf = zzyjVar;
        this.zza = zzagcVar;
        this.zzb = zzafcVar;
        this.zzc = zzacdVar;
        this.zzd = zzafnVar;
        this.zze = zzadlVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zze.zza(str);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzagf zzagfVar) {
        zzagf zzagfVar2 = zzagfVar;
        if (this.zza.zzi("EMAIL")) {
            this.zzb.zzb(null);
        } else if (this.zza.zzc() != null) {
            this.zzb.zzb(this.zza.zzc());
        }
        if (this.zza.zzi("DISPLAY_NAME")) {
            this.zzb.zza((String) null);
        } else if (this.zza.zzb() != null) {
            this.zzb.zza(this.zza.zzb());
        }
        if (this.zza.zzi("PHOTO_URL")) {
            this.zzb.zzd(null);
        } else if (this.zza.zze() != null) {
            this.zzb.zzd(this.zza.zze());
        }
        if (!TextUtils.isEmpty(this.zza.zzd())) {
            this.zzb.zzc(Base64Utils.encode("redacted".getBytes()));
        }
        List<zzafs> listZze = zzagfVar2.zze();
        if (listZze == null) {
            listZze = new ArrayList<>();
        }
        this.zzb.zza(listZze);
        zzacd zzacdVar = this.zzc;
        zzafn zzafnVar = this.zzd;
        Preconditions.checkNotNull(zzafnVar);
        Preconditions.checkNotNull(zzagfVar2);
        String strZzc = zzagfVar2.zzc();
        String strZzd = zzagfVar2.zzd();
        if (!TextUtils.isEmpty(strZzc) && !TextUtils.isEmpty(strZzd)) {
            zzafnVar = new zzafn(strZzd, strZzc, Long.valueOf(zzagfVar2.zza()), zzafnVar.zze());
        }
        zzacdVar.zza(zzafnVar, this.zzb);
    }
}
