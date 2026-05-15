package com.google.android.gms.internal.p001firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.zzf;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzyr implements zzadk<zzafd> {
    private final /* synthetic */ zzadl zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ Boolean zzd;
    private final /* synthetic */ zzf zze;
    private final /* synthetic */ zzacd zzf;
    private final /* synthetic */ zzafn zzg;

    zzyr(zzyj zzyjVar, zzadl zzadlVar, String str, String str2, Boolean bool, zzf zzfVar, zzacd zzacdVar, zzafn zzafnVar) {
        this.zza = zzadlVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bool;
        this.zze = zzfVar;
        this.zzf = zzacdVar;
        this.zzg = zzafnVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadl
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadk
    public final /* synthetic */ void zza(zzafd zzafdVar) {
        List<zzafc> listZza = zzafdVar.zza();
        if (listZza == null || listZza.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        zzafc zzafcVar = listZza.get(0);
        zzafv zzafvVarZzf = zzafcVar.zzf();
        List<zzafs> listZza2 = zzafvVarZzf != null ? zzafvVarZzf.zza() : null;
        if (listZza2 != null && !listZza2.isEmpty()) {
            if (!TextUtils.isEmpty(this.zzb)) {
                int i = 0;
                while (true) {
                    if (i >= listZza2.size()) {
                        break;
                    }
                    if (listZza2.get(i).zzf().equals(this.zzb)) {
                        listZza2.get(i).zza(this.zzc);
                        break;
                    }
                    i++;
                }
            } else {
                listZza2.get(0).zza(this.zzc);
            }
        }
        if (this.zzd != null) {
            zzafcVar.zza(this.zzd.booleanValue());
        } else {
            zzafcVar.zza(zzafcVar.zzb() - zzafcVar.zza() < 1000);
        }
        zzafcVar.zza(this.zze);
        this.zzf.zza(this.zzg, zzafcVar);
    }
}
