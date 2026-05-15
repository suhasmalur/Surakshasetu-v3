package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzan;
import com.google.firebase.auth.zzf;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzyj {
    private final zzadi zza;

    static /* synthetic */ void zza(zzyj zzyjVar, zzagv zzagvVar, zzacd zzacdVar, zzadl zzadlVar) {
        if (!zzagvVar.zzo()) {
            zzyjVar.zza(new zzafn(zzagvVar.zzi(), zzagvVar.zze(), Long.valueOf(zzagvVar.zza()), "Bearer"), zzagvVar.zzh(), zzagvVar.zzg(), Boolean.valueOf(zzagvVar.zzn()), zzagvVar.zzb(), zzacdVar, zzadlVar);
            return;
        }
        zzacdVar.zza(new zzyh(zzagvVar.zzm() ? new Status(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL) : zzan.zza(zzagvVar.zzd()), zzagvVar.zzb(), zzagvVar.zzc(), zzagvVar.zzj()));
    }

    static /* synthetic */ void zza(zzyj zzyjVar, zzacd zzacdVar, zzage zzageVar, zzadl zzadlVar) {
        Preconditions.checkNotNull(zzacdVar);
        Preconditions.checkNotNull(zzageVar);
        Preconditions.checkNotNull(zzadlVar);
        zzyjVar.zza.zza(zzageVar, new zzzc(zzyjVar, zzacdVar, zzadlVar));
    }

    static /* synthetic */ void zza(zzyj zzyjVar, zzacd zzacdVar, zzafn zzafnVar, zzagc zzagcVar, zzadl zzadlVar) {
        Preconditions.checkNotNull(zzacdVar);
        Preconditions.checkNotNull(zzafnVar);
        Preconditions.checkNotNull(zzagcVar);
        Preconditions.checkNotNull(zzadlVar);
        zzyjVar.zza.zza(new zzafa(zzafnVar.zzc()), new zzyp(zzyjVar, zzadlVar, zzacdVar, zzafnVar, zzagcVar));
    }

    static /* synthetic */ void zza(zzyj zzyjVar, zzacd zzacdVar, zzafn zzafnVar, zzafc zzafcVar, zzagc zzagcVar, zzadl zzadlVar) {
        Preconditions.checkNotNull(zzacdVar);
        Preconditions.checkNotNull(zzafnVar);
        Preconditions.checkNotNull(zzafcVar);
        Preconditions.checkNotNull(zzagcVar);
        Preconditions.checkNotNull(zzadlVar);
        zzyjVar.zza.zza(zzagcVar, new zzys(zzyjVar, zzagcVar, zzafcVar, zzacdVar, zzafnVar, zzadlVar));
    }

    public zzyj(zzadi zzadiVar) {
        this.zza = (zzadi) Preconditions.checkNotNull(zzadiVar);
    }

    public final void zza(String str, String str2, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacdVar);
        zzagc zzagcVar = new zzagc();
        zzagcVar.zze(str);
        zzagcVar.zzh(str2);
        this.zza.zza(zzagcVar, new zzaad(this, zzacdVar));
    }

    public final void zzb(String str, String str2, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacdVar);
        zza(str, new zzaab(this, str2, zzacdVar));
    }

    public final void zzc(String str, String str2, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacdVar);
        zza(str, new zzaae(this, str2, zzacdVar));
    }

    public final void zzd(String str, String str2, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(new zzafx(str, null, str2), new zzyw(this, zzacdVar));
    }

    public final void zza(String str, String str2, String str3, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(new zzafx(str, str2, str3), new zzyy(this, zzacdVar));
    }

    public final void zza(String str, String str2, String str3, String str4, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(new zzage(str, str2, null, str3, str4, null), new zzyl(this, zzacdVar));
    }

    public final void zza(String str, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacdVar);
        zza(str, new zzzw(this, zzacdVar));
    }

    private final void zza(String str, zzadk<zzafn> zzadkVar) {
        Preconditions.checkNotNull(zzadkVar);
        Preconditions.checkNotEmpty(str);
        zzafn zzafnVarZzb = zzafn.zzb(str);
        if (zzafnVarZzb.zzg()) {
            zzadkVar.zza(zzafnVarZzb);
        } else {
            this.zza.zza(new zzafb(zzafnVarZzb.zzd()), new zzaag(this, zzadkVar));
        }
    }

    public final void zza(zzaer zzaerVar, String str, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzaerVar);
        Preconditions.checkNotNull(zzacdVar);
        zza(str, new zzzo(this, zzaerVar, zzacdVar));
    }

    public final void zza(zzaet zzaetVar, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzaetVar);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(zzaetVar, new zzzq(this, zzacdVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzaep zzaepVar, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzaepVar);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(zzaepVar, new zzyq(this, zzacdVar));
    }

    public final void zzb(String str, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(new zzafb(str), new zzym(this, zzacdVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzafn zzafnVar, String str, String str2, Boolean bool, zzf zzfVar, zzacd zzacdVar, zzadl zzadlVar) {
        Preconditions.checkNotNull(zzafnVar);
        Preconditions.checkNotNull(zzadlVar);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(new zzafa(zzafnVar.zzc()), new zzyr(this, zzadlVar, str2, str, bool, zzfVar, zzacdVar, zzafnVar));
    }

    private final void zzb(zzafe zzafeVar, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzafeVar);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(zzafeVar, new zzzx(this, zzacdVar));
    }

    public final void zza(zzafg zzafgVar, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzafgVar);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(zzafgVar, new zzzr(this, zzacdVar));
    }

    public final void zze(String str, String str2, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(new zzaek(str, str2), new zzyu(this, zzacdVar));
    }

    public final void zza(zzafl zzaflVar, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzaflVar);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(zzaflVar, new zzzs(this, zzacdVar));
    }

    public final void zza(String str, String str2, String str3, String str4, String str5, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzacdVar);
        zza(str3, new zzyz(this, str, str2, str4, str5, zzacdVar));
    }

    public final void zza(String str, zzagt zzagtVar, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzagtVar);
        Preconditions.checkNotNull(zzacdVar);
        zza(str, new zzzd(this, zzagtVar, zzacdVar));
    }

    public final void zza(String str, zzagy zzagyVar, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzagyVar);
        Preconditions.checkNotNull(zzacdVar);
        zza(str, new zzzb(this, zzagyVar, zzacdVar));
    }

    public final void zzc(String str, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacdVar);
        zza(str, new zzzu(this, zzacdVar));
    }

    public final void zza(zzafz zzafzVar, zzacd zzacdVar) {
        this.zza.zza(zzafzVar, new zzzz(this, zzacdVar));
    }

    public final void zza(String str, ActionCodeSettings actionCodeSettings, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacdVar);
        zzafe zzafeVar = new zzafe(4);
        zzafeVar.zzd(str);
        if (actionCodeSettings != null) {
            zzafeVar.zza(actionCodeSettings);
        }
        zzb(zzafeVar, zzacdVar);
    }

    public final void zza(String str, ActionCodeSettings actionCodeSettings, String str2, String str3, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacdVar);
        zzafe zzafeVar = new zzafe(actionCodeSettings.zza());
        zzafeVar.zzb(str);
        zzafeVar.zza(actionCodeSettings);
        zzafeVar.zzc(str2);
        zzafeVar.zza(str3);
        this.zza.zza(zzafeVar, new zzyt(this, zzacdVar));
    }

    public final void zza(zzaga zzagaVar, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(zzagaVar.zzd());
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(zzagaVar, new zzyx(this, zzacdVar));
    }

    public final void zzd(String str, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(str, new zzzy(this, zzacdVar));
    }

    public final void zze(String str, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(new zzage(str), new zzaaa(this, zzacdVar));
    }

    public final void zza(zzagt zzagtVar, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzagtVar);
        Preconditions.checkNotNull(zzacdVar);
        zzagtVar.zzb(true);
        this.zza.zza(zzagtVar, new zzzm(this, zzacdVar));
    }

    public final void zza(zzagu zzaguVar, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzaguVar);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(zzaguVar, new zzyv(this, zzacdVar));
    }

    public final void zzb(String str, String str2, String str3, String str4, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(new zzagw(str, str2, str3, str4), new zzyo(this, zzacdVar));
    }

    public final void zza(EmailAuthCredential emailAuthCredential, String str, zzacd zzacdVar) {
        Preconditions.checkNotNull(emailAuthCredential);
        Preconditions.checkNotNull(zzacdVar);
        if (emailAuthCredential.zzg()) {
            zza(emailAuthCredential.zzb(), new zzyn(this, emailAuthCredential, str, zzacdVar));
        } else {
            zza(new zzaep(emailAuthCredential, null, str), zzacdVar);
        }
    }

    public final void zza(zzagy zzagyVar, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzagyVar);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(zzagyVar, new zzza(this, zzacdVar));
    }

    public final void zza(zzagg zzaggVar, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzaggVar);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(zzaggVar, new zzzl(this, zzaggVar, zzacdVar));
    }

    public final void zza(zzagi zzagiVar, zzacd zzacdVar) {
        Preconditions.checkNotNull(zzagiVar);
        Preconditions.checkNotNull(zzacdVar);
        this.zza.zza(zzagiVar, new zzzp(this, zzacdVar));
    }

    public final void zzb(String str, String str2, String str3, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacdVar);
        zza(str, new zzzk(this, str2, str3, zzacdVar));
    }

    public final void zzf(String str, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacdVar);
        zza(str, new zzzf(this, zzacdVar));
    }

    public final void zzf(String str, String str2, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacdVar);
        zza(str2, new zzzi(this, str, zzacdVar));
    }

    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzacd zzacdVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzacdVar);
        zza(str, new zzaac(this, userProfileChangeRequest, zzacdVar));
    }

    public final void zza(zzafe zzafeVar, zzacd zzacdVar) {
        zzb(zzafeVar, zzacdVar);
    }
}
