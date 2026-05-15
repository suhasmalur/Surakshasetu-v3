package com.google.android.gms.internal.p001firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzan;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzacz implements zzace {
    final /* synthetic */ zzacx zza;

    zzacz(zzacx zzacxVar) {
        this.zza = zzacxVar;
    }

    private final void zza(zzadf zzadfVar) {
        this.zza.zzi.execute(new zzadc(this, zzadfVar));
    }

    private final void zza(Status status, AuthCredential authCredential, String str, String str2) {
        zzacx.zza(this.zza, status);
        this.zza.zzp = authCredential;
        this.zza.zzq = str;
        this.zza.zzr = str2;
        if (this.zza.zzf != null) {
            this.zza.zzf.zza(status);
        }
        this.zza.zza(status);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(String str) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 8, "Unexpected response type " + this.zza.zza);
        this.zza.zzo = str;
        this.zza.zzz = true;
        this.zza.zzx = true;
        zza(new zzada(this, str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zzb(String str) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 8, "Unexpected response type " + this.zza.zza);
        this.zza.zzo = str;
        zza(new zzacy(this, str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(zzaen zzaenVar) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 3, "Unexpected response type " + this.zza.zza);
        this.zza.zzl = zzaenVar;
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza() throws RemoteException {
        Preconditions.checkState(this.zza.zza == 5, "Unexpected response type " + this.zza.zza);
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(zzyh zzyhVar) {
        zza(zzyhVar.zza(), zzyhVar.zzb(), zzyhVar.zzc(), zzyhVar.zzd());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(zzyk zzykVar) {
        this.zza.zzs = zzykVar;
        this.zza.zza(zzan.zza("REQUIRES_SECOND_FACTOR_AUTH"));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 2, "Unexpected response type " + this.zza.zza);
        zza(status, phoneAuthCredential, null, null);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(Status status) throws RemoteException {
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null) {
            if (statusMessage.contains("MISSING_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17081);
            } else if (statusMessage.contains("MISSING_MFA_ENROLLMENT_ID")) {
                status = new Status(17082);
            } else if (statusMessage.contains("INVALID_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17083);
            } else if (statusMessage.contains("MFA_ENROLLMENT_NOT_FOUND")) {
                status = new Status(17084);
            } else if (statusMessage.contains("ADMIN_ONLY_OPERATION")) {
                status = new Status(17085);
            } else if (statusMessage.contains("UNVERIFIED_EMAIL")) {
                status = new Status(17086);
            } else if (statusMessage.contains("SECOND_FACTOR_EXISTS")) {
                status = new Status(17087);
            } else if (statusMessage.contains("SECOND_FACTOR_LIMIT_EXCEEDED")) {
                status = new Status(17088);
            } else if (statusMessage.contains("UNSUPPORTED_FIRST_FACTOR")) {
                status = new Status(17089);
            } else if (statusMessage.contains("EMAIL_CHANGE_NEEDS_VERIFICATION")) {
                status = new Status(17090);
            }
        }
        if (this.zza.zza == 8) {
            this.zza.zzz = true;
            this.zza.zzx = false;
            zza(new zzadd(this, status));
        } else {
            zzacx.zza(this.zza, status);
            this.zza.zza(status);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(zzafj zzafjVar) throws RemoteException {
        this.zza.zzu = zzafjVar;
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(zzafk zzafkVar) throws RemoteException {
        this.zza.zzt = zzafkVar;
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(zzafn zzafnVar, zzafc zzafcVar) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 2, "Unexpected response type: " + this.zza.zza);
        this.zza.zzj = zzafnVar;
        this.zza.zzk = zzafcVar;
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(zzafw zzafwVar) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 4, "Unexpected response type " + this.zza.zza);
        this.zza.zzm = zzafwVar;
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(zzagb zzagbVar) throws RemoteException {
        this.zza.zzw = zzagbVar;
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zzb() throws RemoteException {
        Preconditions.checkState(this.zza.zza == 6, "Unexpected response type " + this.zza.zza);
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zzc(String str) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 7, "Unexpected response type " + this.zza.zza);
        this.zza.zzn = str;
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zzc() throws RemoteException {
        Preconditions.checkState(this.zza.zza == 9, "Unexpected response type " + this.zza.zza);
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(zzagj zzagjVar) throws RemoteException {
        this.zza.zzv = zzagjVar;
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(zzafn zzafnVar) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 1, "Unexpected response type: " + this.zza.zza);
        this.zza.zzj = zzafnVar;
        zzacx.zza(this.zza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzace
    public final void zza(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 8, "Unexpected response type " + this.zza.zza);
        this.zza.zzz = true;
        this.zza.zzx = true;
        zza(new zzadb(this, phoneAuthCredential));
    }
}
