package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzab {
    private final zzj zza;
    private final boolean zzb;
    private final zzah zzc;
    private final int zzd;

    public static zzab zza(char c) {
        zzl zzlVar = new zzl(c);
        zzy.zza(zzlVar);
        return new zzab(new zzaa(zzlVar));
    }

    public static zzab zza(String str) {
        zzs zzsVarZza = zzx.zza(str);
        if (!(!zzsVarZza.zza("").zzc())) {
            throw new IllegalArgumentException(zzag.zza("The pattern may not match the empty string: %s", zzsVarZza));
        }
        return new zzab(new zzac(zzsVarZza));
    }

    public final List<String> zza(CharSequence charSequence) {
        zzy.zza(charSequence);
        Iterator<String> itZza = this.zzc.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (itZza.hasNext()) {
            arrayList.add(itZza.next());
        }
        return Collections.unmodifiableList(arrayList);
    }

    private zzab(zzah zzahVar) {
        this(zzahVar, false, zzn.zza, Integer.MAX_VALUE);
    }

    private zzab(zzah zzahVar, boolean z, zzj zzjVar, int i) {
        this.zzc = zzahVar;
        this.zzb = false;
        this.zza = zzjVar;
        this.zzd = Integer.MAX_VALUE;
    }
}
