package com.google.android.gms.internal.p001firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
final class zzmf implements zzcd {
    private final SharedPreferences.Editor zza;
    private final String zzb;

    public zzmf(Context context, String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
        this.zzb = str;
        Context applicationContext = context.getApplicationContext();
        if (str2 == null) {
            this.zza = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit();
        } else {
            this.zza = applicationContext.getSharedPreferences(str2, 0).edit();
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcd
    public final void zza(zzua zzuaVar) throws IOException {
        if (!this.zza.putString(this.zzb, zzxj.zza(zzuaVar.zzj())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzcd
    public final void zza(zzvg zzvgVar) throws IOException {
        if (!this.zza.putString(this.zzb, zzxj.zza(zzvgVar.zzj())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }
}
