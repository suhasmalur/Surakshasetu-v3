package com.google.android.gms.internal.p001firebaseauthapi;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.zzf;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzafc {
    private String zza;
    private String zzb;
    private boolean zzc;
    private String zzd;
    private String zze;
    private zzafv zzf;
    private String zzg;
    private String zzh;
    private long zzi;
    private long zzj;
    private boolean zzk;
    private zzf zzl;
    private List<zzafr> zzm;
    private zzap<zzafq> zzn;

    public final long zza() {
        return this.zzi;
    }

    public final long zzb() {
        return this.zzj;
    }

    public final Uri zzc() {
        if (!TextUtils.isEmpty(this.zze)) {
            return Uri.parse(this.zze);
        }
        return null;
    }

    public final zzap<zzafq> zzd() {
        return this.zzn;
    }

    public final zzf zze() {
        return this.zzl;
    }

    public final zzafc zza(zzf zzfVar) {
        this.zzl = zzfVar;
        return this;
    }

    public final zzafc zza(String str) {
        this.zzd = str;
        return this;
    }

    public final zzafc zzb(String str) {
        this.zzb = str;
        return this;
    }

    public final zzafc zza(boolean z) {
        this.zzk = z;
        return this;
    }

    public final zzafc zzc(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzg = str;
        return this;
    }

    public final zzafc zzd(String str) {
        this.zze = str;
        return this;
    }

    public final zzafc zza(List<zzafs> list) {
        Preconditions.checkNotNull(list);
        this.zzf = new zzafv();
        this.zzf.zza().addAll(list);
        return this;
    }

    public final zzafv zzf() {
        return this.zzf;
    }

    public final String zzg() {
        return this.zzd;
    }

    public final String zzh() {
        return this.zzb;
    }

    public final String zzi() {
        return this.zza;
    }

    public final String zzj() {
        return this.zzh;
    }

    public final List<zzafr> zzk() {
        return this.zzm;
    }

    public final List<zzafs> zzl() {
        return this.zzf.zza();
    }

    public zzafc() {
        this.zzf = new zzafv();
        this.zzn = zzap.zzh();
    }

    public zzafc(String str, String str2, boolean z, String str3, String str4, zzafv zzafvVar, String str5, String str6, long j, long j2, boolean z2, zzf zzfVar, List<zzafr> list, zzap<zzafq> zzapVar) {
        zzafv zzafvVar2;
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = str3;
        this.zze = str4;
        if (zzafvVar == null) {
            zzafvVar2 = new zzafv();
        } else {
            List<zzafs> listZza = zzafvVar.zza();
            zzafv zzafvVar3 = new zzafv();
            if (listZza != null) {
                zzafvVar3.zza().addAll(listZza);
            }
            zzafvVar2 = zzafvVar3;
        }
        this.zzf = zzafvVar2;
        this.zzg = str5;
        this.zzh = str6;
        this.zzi = j;
        this.zzj = j2;
        this.zzk = false;
        this.zzl = null;
        this.zzm = list == null ? new ArrayList<>() : list;
        this.zzn = zzapVar;
    }

    public final boolean zzm() {
        return this.zzc;
    }

    public final boolean zzn() {
        return this.zzk;
    }
}
