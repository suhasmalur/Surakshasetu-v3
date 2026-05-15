package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbj implements zzca {
    private final InputStream zza;

    public static zzca zza(byte[] bArr) {
        return new zzbj(new ByteArrayInputStream(bArr));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzca
    public final zzua zza() throws IOException {
        try {
            return zzua.zza(this.zza, zzaio.zza());
        } finally {
            this.zza.close();
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzca
    public final zzvg zzb() throws IOException {
        try {
            return zzvg.zza(this.zza, zzaio.zza());
        } finally {
            this.zza.close();
        }
    }

    private zzbj(InputStream inputStream) {
        this.zza = inputStream;
    }
}
