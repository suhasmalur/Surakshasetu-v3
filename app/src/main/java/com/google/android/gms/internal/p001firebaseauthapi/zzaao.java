package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.internal.zzam;
import com.google.firebase.auth.internal.zzg;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaao extends zzacx<SignInMethodQueryResult, zzg> {
    private final String zzy;
    private final String zzz;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadj
    public final String zza() {
        return "fetchSignInMethodsForEmail";
    }

    public zzaao(String str, String str2) {
        super(3);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        this.zzy = str;
        this.zzz = str2;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacx
    public final void zzb() {
        List listZzh;
        if (this.zzl.zza() == null) {
            listZzh = zzap.zzh();
        } else {
            listZzh = (List) Preconditions.checkNotNull(this.zzl.zza());
        }
        zzb(new zzam(listZzh));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadj
    public final void zza(TaskCompletionSource taskCompletionSource, zzacg zzacgVar) {
        this.zzg = new zzade(this, taskCompletionSource);
        zzacgVar.zze(this.zzy, this.zzz, this.zzb);
    }
}
