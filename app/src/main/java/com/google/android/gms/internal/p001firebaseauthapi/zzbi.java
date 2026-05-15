package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbi implements zzcd {
    private final OutputStream zza;

    public static zzcd zza(OutputStream outputStream) {
        return new zzbi(outputStream);
    }

    private zzbi(OutputStream outputStream) {
        this.zza = outputStream;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcd
    public final void zza(zzua zzuaVar) throws IOException {
        try {
            zzuaVar.zza(this.zza);
        } finally {
            this.zza.close();
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcd
    public final void zza(zzvg zzvgVar) throws IOException {
        try {
            zzvgVar.zza(this.zza);
        } finally {
            this.zza.close();
        }
    }
}
