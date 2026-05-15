package com.google.android.gms.internal.p001firebaseauthapi;

import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzae extends zzi<String> {
    final CharSequence zza;
    private final zzj zzb;
    private int zze;
    private int zzd = 0;
    private final boolean zzc = false;

    abstract int zza(int i);

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzi
    @CheckForNull
    protected final /* synthetic */ String zza() {
        int i = this.zzd;
        while (this.zzd != -1) {
            int iZzb = zzb(this.zzd);
            if (iZzb == -1) {
                iZzb = this.zza.length();
                this.zzd = -1;
            } else {
                this.zzd = zza(iZzb);
            }
            if (this.zzd != i) {
                while (i < iZzb && this.zzb.zza(this.zza.charAt(i))) {
                    i++;
                }
                while (iZzb > i && this.zzb.zza(this.zza.charAt(iZzb - 1))) {
                    iZzb--;
                }
                if (this.zze == 1) {
                    iZzb = this.zza.length();
                    this.zzd = -1;
                    while (iZzb > i && this.zzb.zza(this.zza.charAt(iZzb - 1))) {
                        iZzb--;
                    }
                } else {
                    this.zze--;
                }
                return this.zza.subSequence(i, iZzb).toString();
            }
            this.zzd++;
            if (this.zzd > this.zza.length()) {
                this.zzd = -1;
            }
        }
        zzb();
        return null;
    }

    abstract int zzb(int i);

    protected zzae(zzab zzabVar, CharSequence charSequence) {
        this.zzb = zzabVar.zza;
        this.zze = zzabVar.zzd;
        this.zza = charSequence;
    }
}
