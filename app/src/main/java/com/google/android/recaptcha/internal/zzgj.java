package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public class zzgj extends zzgi implements zzhz {
    protected zzgj(zzgk zzgkVar) {
        super(zzgkVar);
    }

    @Override // com.google.android.recaptcha.internal.zzgi, com.google.android.recaptcha.internal.zzhx
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzgk zzk() {
        if (!((zzgk) this.zza).zzF()) {
            return (zzgk) this.zza;
        }
        ((zzgk) this.zza).zzb.zzg();
        return (zzgk) super.zzk();
    }

    @Override // com.google.android.recaptcha.internal.zzgi
    protected final void zzn() {
        super.zzn();
        if (((zzgk) this.zza).zzb != zzge.zzd()) {
            zzgk zzgkVar = (zzgk) this.zza;
            zzgkVar.zzb = zzgkVar.zzb.clone();
        }
    }
}
