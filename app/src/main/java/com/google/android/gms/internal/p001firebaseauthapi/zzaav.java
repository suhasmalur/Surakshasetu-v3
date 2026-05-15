package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzaa;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzu;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaav extends zzacx<AuthResult, zzg> {
    private final EmailAuthCredential zzy;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadj
    public final String zza() {
        return "linkEmailAuthCredential";
    }

    public zzaav(EmailAuthCredential emailAuthCredential) {
        super(2);
        this.zzy = (EmailAuthCredential) Preconditions.checkNotNull(emailAuthCredential, "credential cannot be null");
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacx
    public final void zzb() {
        zzaa zzaaVarZza = zzaai.zza(this.zzc, this.zzk);
        ((zzg) this.zze).zza(this.zzj, zzaaVarZza);
        zzb(new zzu(zzaaVarZza));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadj
    public final void zza(TaskCompletionSource taskCompletionSource, zzacg zzacgVar) {
        this.zzg = new zzade(this, taskCompletionSource);
        zzacgVar.zza(new zzyd(this.zzy.zza(this.zzd), null), this.zzb);
    }
}
