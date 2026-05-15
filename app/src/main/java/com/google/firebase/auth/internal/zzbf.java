package com.google.firebase.auth.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p001firebaseauthapi.zzafr;
import com.google.android.gms.internal.p001firebaseauthapi.zzagr;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbf {
    @Nullable
    public static MultiFactorInfo zza(zzafr zzafrVar) {
        if (zzafrVar == null) {
            return null;
        }
        if (!TextUtils.isEmpty(zzafrVar.zze())) {
            return new PhoneMultiFactorInfo(zzafrVar.zzd(), zzafrVar.zzc(), zzafrVar.zza(), Preconditions.checkNotEmpty(zzafrVar.zze()));
        }
        if (zzafrVar.zzb() != null) {
            return new TotpMultiFactorInfo(zzafrVar.zzd(), zzafrVar.zzc(), zzafrVar.zza(), (zzagr) Preconditions.checkNotNull(zzafrVar.zzb(), "totpInfo cannot be null."));
        }
        return null;
    }

    public static List<MultiFactorInfo> zza(List<zzafr> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<zzafr> it = list.iterator();
        while (it.hasNext()) {
            MultiFactorInfo multiFactorInfoZza = zza(it.next());
            if (multiFactorInfoZza != null) {
                arrayList.add(multiFactorInfoZza);
            }
        }
        return arrayList;
    }
}
