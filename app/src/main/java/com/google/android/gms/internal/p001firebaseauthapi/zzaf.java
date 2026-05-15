package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaf extends zzae {
    private final /* synthetic */ zzp zzb;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzae
    public final int zza(int i) {
        return this.zzb.zza();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzae
    public final int zzb(int i) {
        if (this.zzb.zza(i)) {
            return this.zzb.zzb();
        }
        return -1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaf(zzac zzacVar, zzab zzabVar, CharSequence charSequence, zzp zzpVar) {
        super(zzabVar, charSequence);
        this.zzb = zzpVar;
    }
}
