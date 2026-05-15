package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzago {
    private final int zza;
    private List<String> zzb;

    public static zzago zza() {
        return new zzago(null);
    }

    public final List<String> zzb() {
        return this.zzb;
    }

    public zzago() {
        this(null);
    }

    private zzago(List<String> list) {
        this.zza = 1;
        this.zzb = new ArrayList();
    }

    public zzago(int i, List<String> list) {
        this.zza = 1;
        if (list == null || list.isEmpty()) {
            this.zzb = Collections.emptyList();
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            list.set(i2, Strings.emptyToNull(list.get(i2)));
        }
        this.zzb = Collections.unmodifiableList(list);
    }
}
