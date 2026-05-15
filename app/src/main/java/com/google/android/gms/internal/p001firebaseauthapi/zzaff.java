package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzaff {
    private List<zzafc> zza;

    public final List<zzafc> zza() {
        return this.zza;
    }

    public zzaff() {
        this.zza = new ArrayList();
    }

    public zzaff(List<zzafc> list) {
        this.zza = Collections.unmodifiableList(list);
    }
}
