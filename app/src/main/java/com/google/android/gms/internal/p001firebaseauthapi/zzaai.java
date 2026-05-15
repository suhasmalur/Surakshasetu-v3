package com.google.android.gms.internal.p001firebaseauthapi;

import android.app.Activity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.TotpMultiFactorAssertion;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzaa;
import com.google.firebase.auth.internal.zzac;
import com.google.firebase.auth.internal.zzal;
import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.internal.zzat;
import com.google.firebase.auth.internal.zzbf;
import com.google.firebase.auth.internal.zzcb;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzw;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzaai extends zzadh {
    public final Task<Void> zza(FirebaseApp firebaseApp, String str, String str2) {
        return zza((zzaah) new zzaah(str, str2).zza(firebaseApp));
    }

    public final Task<ActionCodeResult> zzb(FirebaseApp firebaseApp, String str, String str2) {
        return zza((zzaak) new zzaak(str, str2).zza(firebaseApp));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, String str2, String str3) {
        return zza((zzaaj) new zzaaj(str, str2, str3).zza(firebaseApp));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, String str2, String str3, String str4, zzg zzgVar) {
        return zza((zzaam) new zzaam(str, str2, str3, str4).zza(firebaseApp).zza(zzgVar));
    }

    public final Task<Void> zza(FirebaseUser firebaseUser, zzaq zzaqVar) {
        return zza((zzaal) new zzaal().zza(firebaseUser).zza(zzaqVar).zza((zzat) zzaqVar));
    }

    public final Task<SignInMethodQueryResult> zzc(FirebaseApp firebaseApp, String str, String str2) {
        return zza((zzaao) new zzaao(str, str2).zza(firebaseApp));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, PhoneMultiFactorAssertion phoneMultiFactorAssertion, FirebaseUser firebaseUser, String str, zzg zzgVar) {
        zzadt.zza();
        zzaan zzaanVar = new zzaan(phoneMultiFactorAssertion, firebaseUser.zze(), str, null);
        zzaanVar.zza(firebaseApp).zza(zzgVar);
        return zza(zzaanVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, TotpMultiFactorAssertion totpMultiFactorAssertion, FirebaseUser firebaseUser, String str, String str2, zzg zzgVar) {
        zzaan zzaanVar = new zzaan(totpMultiFactorAssertion, firebaseUser.zze(), str, str2);
        zzaanVar.zza(firebaseApp).zza(zzgVar);
        return zza(zzaanVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneMultiFactorAssertion phoneMultiFactorAssertion, String str, zzg zzgVar) {
        zzadt.zza();
        zzaaq zzaaqVar = new zzaaq(phoneMultiFactorAssertion, str, null);
        zzaaqVar.zza(firebaseApp).zza(zzgVar);
        if (firebaseUser != null) {
            zzaaqVar.zza(firebaseUser);
        }
        return zza(zzaaqVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, TotpMultiFactorAssertion totpMultiFactorAssertion, String str, String str2, zzg zzgVar) {
        zzaaq zzaaqVar = new zzaaq(totpMultiFactorAssertion, str, str2);
        zzaaqVar.zza(firebaseApp).zza(zzgVar);
        if (firebaseUser != null) {
            zzaaqVar.zza(firebaseUser);
        }
        return zza(zzaaqVar);
    }

    public final Task<GetTokenResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzcb zzcbVar) {
        return zza((zzaap) new zzaap(str).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<zzafj> zza() {
        return zza(new zzaas());
    }

    public final Task<zzafk> zza(String str, String str2) {
        return zza(new zzaar(str, str2));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzcb zzcbVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzcbVar);
        List<String> listZzf = firebaseUser.zzf();
        if (listZzf != null && listZzf.contains(authCredential.getProvider())) {
            return Tasks.forException(zzacf.zza(new Status(FirebaseError.ERROR_PROVIDER_ALREADY_LINKED)));
        }
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            if (!emailAuthCredential.zzf()) {
                return zza((zzaau) new zzaau(emailAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
            }
            return zza((zzaav) new zzaav(emailAuthCredential).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
        }
        if (authCredential instanceof PhoneAuthCredential) {
            zzadt.zza();
            return zza((zzaaw) new zzaaw((PhoneAuthCredential) authCredential).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
        }
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzcbVar);
        return zza((zzaat) new zzaat(authCredential).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzcb zzcbVar) {
        return zza((zzaay) new zzaay(authCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<AuthResult> zzc(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzcb zzcbVar) {
        return zza((zzaax) new zzaax(authCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, String str, zzcb zzcbVar) {
        return zza((zzaba) new zzaba(emailAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, String str, zzcb zzcbVar) {
        return zza((zzaaz) new zzaaz(emailAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, String str4, zzcb zzcbVar) {
        return zza((zzabc) new zzabc(str, str2, str3, str4).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, String str4, zzcb zzcbVar) {
        return zza((zzabb) new zzabb(str, str2, str3, str4).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzcb zzcbVar) {
        zzadt.zza();
        return zza((zzabe) new zzabe(phoneAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzcb zzcbVar) {
        zzadt.zza();
        return zza((zzabd) new zzabd(phoneAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, zzcb zzcbVar) {
        return zza((zzabg) new zzabg().zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<Void> zza(String str, String str2, String str3, String str4) {
        return zza(new zzabf(str, str2, str3, str4));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, ActionCodeSettings actionCodeSettings, String str) {
        return zza((zzabi) new zzabi(str, actionCodeSettings).zza(firebaseApp));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2, String str3) {
        actionCodeSettings.zza(1);
        return zza((zzabh) new zzabh(str, actionCodeSettings, str2, str3, "sendPasswordResetEmail").zza(firebaseApp));
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2, String str3) {
        actionCodeSettings.zza(6);
        return zza((zzabh) new zzabh(str, actionCodeSettings, str2, str3, "sendSignInLinkToEmail").zza(firebaseApp));
    }

    public final Task<Void> zza(String str) {
        return zza(new zzabk(str));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, zzg zzgVar, String str) {
        return zza((zzabj) new zzabj(str).zza(firebaseApp).zza(zzgVar));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, AuthCredential authCredential, String str, zzg zzgVar) {
        return zza((zzabm) new zzabm(authCredential, str).zza(firebaseApp).zza(zzgVar));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, String str2, zzg zzgVar) {
        return zza((zzabl) new zzabl(str, str2).zza(firebaseApp).zza(zzgVar));
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, String str, String str2, String str3, String str4, zzg zzgVar) {
        return zza((zzabo) new zzabo(str, str2, str3, str4).zza(firebaseApp).zza(zzgVar));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, EmailAuthCredential emailAuthCredential, String str, zzg zzgVar) {
        return zza((zzabn) new zzabn(emailAuthCredential, str).zza(firebaseApp).zza(zzgVar));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, PhoneAuthCredential phoneAuthCredential, String str, zzg zzgVar) {
        zzadt.zza();
        return zza((zzabq) new zzabq(phoneAuthCredential, str).zza(firebaseApp).zza(zzgVar));
    }

    public final Task<Void> zza(zzal zzalVar, String str, String str2, long j, boolean z, boolean z2, String str3, String str4, boolean z3, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzabp zzabpVar = new zzabp(zzalVar, str, str2, j, z, z2, str3, str4, z3);
        zzabpVar.zza(onVerificationStateChangedCallbacks, activity, executor, str);
        return zza(zzabpVar);
    }

    public final Task<zzagj> zza(zzal zzalVar, String str) {
        return zza(new zzabs(zzalVar, str));
    }

    public final Task<Void> zza(zzal zzalVar, PhoneMultiFactorInfo phoneMultiFactorInfo, String str, long j, boolean z, boolean z2, String str2, String str3, boolean z3, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzabr zzabrVar = new zzabr(phoneMultiFactorInfo, Preconditions.checkNotEmpty(zzalVar.zzc()), str, j, z, z2, str2, str3, z3);
        zzabrVar.zza(onVerificationStateChangedCallbacks, activity, executor, phoneMultiFactorInfo.getUid());
        return zza(zzabrVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, zzcb zzcbVar) {
        return zza((zzabu) new zzabu(firebaseUser.zze(), str, str2).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzcb zzcbVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzcbVar);
        List<String> listZzf = firebaseUser.zzf();
        if ((listZzf != null && !listZzf.contains(str)) || firebaseUser.isAnonymous()) {
            return Tasks.forException(zzacf.zza(new Status(FirebaseError.ERROR_NO_SUCH_PROVIDER, str)));
        }
        switch (str) {
            case "password":
                return zza((zzabt) new zzabt().zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
            default:
                return zza((zzabw) new zzabw(str).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
        }
    }

    public final Task<Void> zzc(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzcb zzcbVar) {
        return zza((zzabv) new zzabv(str).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<Void> zzd(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzcb zzcbVar) {
        return zza((zzaby) new zzaby(str).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, zzcb zzcbVar) {
        zzadt.zza();
        return zza((zzabx) new zzabx(phoneAuthCredential).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest, zzcb zzcbVar) {
        return zza((zzaca) new zzaca(userProfileChangeRequest).zza(firebaseApp).zza(firebaseUser).zza(zzcbVar).zza((zzat) zzcbVar));
    }

    public final Task<Void> zza(String str, String str2, ActionCodeSettings actionCodeSettings) {
        actionCodeSettings.zza(7);
        return zza(new zzabz(str, str2, actionCodeSettings));
    }

    public final Task<String> zzd(FirebaseApp firebaseApp, String str, String str2) {
        return zza((zzacc) new zzacc(str, str2).zza(firebaseApp));
    }

    static zzaa zza(FirebaseApp firebaseApp, zzafc zzafcVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(zzafcVar);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new zzw(zzafcVar, FirebaseAuthProvider.PROVIDER_ID));
        List<zzafs> listZzl = zzafcVar.zzl();
        if (listZzl != null && !listZzl.isEmpty()) {
            for (int i = 0; i < listZzl.size(); i++) {
                arrayList.add(new zzw(listZzl.get(i)));
            }
        }
        zzaa zzaaVar = new zzaa(firebaseApp, arrayList);
        zzaaVar.zza(new zzac(zzafcVar.zzb(), zzafcVar.zza()));
        zzaaVar.zza(zzafcVar.zzn());
        zzaaVar.zza(zzafcVar.zze());
        zzaaVar.zzb(zzbf.zza(zzafcVar.zzk()));
        zzaaVar.zzc(zzafcVar.zzd());
        return zzaaVar;
    }

    public zzaai(FirebaseApp firebaseApp, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        this.zza = new zzacg(firebaseApp, scheduledExecutorService);
        this.zzb = executor;
    }

    public final void zza(FirebaseApp firebaseApp, zzaga zzagaVar, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        zza((zzacb) new zzacb(zzagaVar).zza(firebaseApp).zza(onVerificationStateChangedCallbacks, activity, executor, zzagaVar.zzd()));
    }
}
