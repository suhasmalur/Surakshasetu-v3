package com.google.android.gms.internal.p001firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.TotpMultiFactorAssertion;
import com.google.firebase.auth.TotpSecret;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzacg {
    private static final Logger zza = new Logger("FirebaseAuth", "FirebaseAuthFallback:");
    private final zzyj zzb;
    private final zzadu zzc;

    zzacg(FirebaseApp firebaseApp, ScheduledExecutorService scheduledExecutorService) {
        Preconditions.checkNotNull(firebaseApp);
        Context applicationContext = firebaseApp.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzb = new zzyj(new zzacu(firebaseApp, zzacr.zza()));
        this.zzc = new zzadu(applicationContext, scheduledExecutorService);
    }

    public final void zza(String str, String str2, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(str, str2, new zzacd(zzaceVar, zza));
    }

    public final void zzb(String str, String str2, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zzb(str, str2, new zzacd(zzaceVar, zza));
    }

    public final void zzc(String str, String str2, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zzc(str, str2, new zzacd(zzaceVar, zza));
    }

    public final void zzd(String str, String str2, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zzd(str, str2, new zzacd(zzaceVar, zza));
    }

    public final void zza(zzxv zzxvVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzxvVar);
        Preconditions.checkNotEmpty(zzxvVar.zza());
        Preconditions.checkNotEmpty(zzxvVar.zzb());
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(zzxvVar.zza(), zzxvVar.zzb(), zzxvVar.zzc(), new zzacd(zzaceVar, zza));
    }

    public final void zza(String str, String str2, String str3, String str4, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(str, str2, str3, str4, new zzacd(zzaceVar, zza));
    }

    public final void zza(String str, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(str, new zzacd(zzaceVar, zza));
    }

    public final void zza(MultiFactorAssertion multiFactorAssertion, String str, String str2, String str3, zzace zzaceVar) {
        zzaer zzaerVarZza;
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotEmpty(str, "cachedTokenState should not be empty.");
        Preconditions.checkNotNull(zzaceVar);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            PhoneAuthCredential phoneAuthCredentialZza = ((PhoneMultiFactorAssertion) multiFactorAssertion).zza();
            zzaerVarZza = zzaev.zza(str, (String) Preconditions.checkNotNull(phoneAuthCredentialZza.zzc()), (String) Preconditions.checkNotNull(phoneAuthCredentialZza.getSmsCode()), str2, str3);
        } else if (multiFactorAssertion instanceof TotpMultiFactorAssertion) {
            TotpMultiFactorAssertion totpMultiFactorAssertion = (TotpMultiFactorAssertion) multiFactorAssertion;
            zzaerVarZza = zzaex.zza(str, Preconditions.checkNotEmpty(str2), Preconditions.checkNotEmpty(((TotpSecret) Preconditions.checkNotNull(totpMultiFactorAssertion.zza())).getSessionInfo()), Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzc()), str3);
        } else {
            throw new IllegalArgumentException("multiFactorAssertion must be either PhoneMultiFactorAssertion or TotpMultiFactorAssertion.");
        }
        this.zzb.zza(zzaerVarZza, str, new zzacd(zzaceVar, zza));
    }

    public final void zza(String str, MultiFactorAssertion multiFactorAssertion, String str2, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotNull(zzaceVar);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            PhoneAuthCredential phoneAuthCredentialZza = ((PhoneMultiFactorAssertion) multiFactorAssertion).zza();
            this.zzb.zza(zzaeu.zza(str, (String) Preconditions.checkNotNull(phoneAuthCredentialZza.zzc()), (String) Preconditions.checkNotNull(phoneAuthCredentialZza.getSmsCode()), str2), new zzacd(zzaceVar, zza));
        } else {
            if (multiFactorAssertion instanceof TotpMultiFactorAssertion) {
                TotpMultiFactorAssertion totpMultiFactorAssertion = (TotpMultiFactorAssertion) multiFactorAssertion;
                this.zzb.zza(zzaew.zza(str, Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzc()), str2, Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzb())), new zzacd(zzaceVar, zza));
                return;
            }
            throw new IllegalArgumentException("multiFactorAssertion must be either PhoneMultiFactorAssertion or TotpMultiFactorAssertion.");
        }
    }

    public final void zzb(String str, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zzb(str, new zzacd(zzaceVar, zza));
    }

    public final void zza(zzxy zzxyVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzxyVar);
        this.zzb.zza(zzafg.zzb(), new zzacd(zzaceVar, zza));
    }

    public final void zze(String str, String str2, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        this.zzb.zze(str, str2, new zzacd(zzaceVar, zza));
    }

    public final void zza(zzxx zzxxVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzxxVar);
        this.zzb.zza(zzafl.zza(zzxxVar.zzb(), zzxxVar.zza()), new zzacd(zzaceVar, zza));
    }

    public final void zza(String str, String str2, String str3, String str4, String str5, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(str, str2, str3, str4, str5, new zzacd(zzaceVar, zza));
    }

    public final void zza(String str, zzagt zzagtVar, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzagtVar);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(str, zzagtVar, new zzacd(zzaceVar, zza));
    }

    public final void zza(zzya zzyaVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzaceVar);
        Preconditions.checkNotNull(zzyaVar);
        PhoneAuthCredential phoneAuthCredential = (PhoneAuthCredential) Preconditions.checkNotNull(zzyaVar.zza());
        this.zzb.zza(Preconditions.checkNotEmpty(zzyaVar.zzb()), zzado.zza(phoneAuthCredential), new zzacd(zzaceVar, zza));
    }

    public final void zzc(String str, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zzc(str, new zzacd(zzaceVar, zza));
    }

    public final void zza(zzafz zzafzVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzafzVar);
        this.zzb.zza(zzafzVar, new zzacd(zzaceVar, zza));
    }

    public final void zza(zzxz zzxzVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzxzVar);
        Preconditions.checkNotEmpty(zzxzVar.zzb());
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(zzxzVar.zzb(), zzxzVar.zza(), new zzacd(zzaceVar, zza));
    }

    public final void zza(zzyc zzycVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzycVar);
        Preconditions.checkNotEmpty(zzycVar.zzc());
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(zzycVar.zzc(), zzycVar.zza(), zzycVar.zzd(), zzycVar.zzb(), new zzacd(zzaceVar, zza));
    }

    public final void zza(zzyb zzybVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzaceVar);
        Preconditions.checkNotNull(zzybVar);
        zzaga zzagaVar = (zzaga) Preconditions.checkNotNull(zzybVar.zza());
        String strZzd = zzagaVar.zzd();
        zzacd zzacdVar = new zzacd(zzaceVar, zza);
        if (this.zzc.zzd(strZzd)) {
            if (zzagaVar.zze()) {
                this.zzc.zzc(strZzd);
            } else {
                this.zzc.zzb(zzacdVar, strZzd);
                return;
            }
        }
        long jZzb = zzagaVar.zzb();
        boolean zZzf = zzagaVar.zzf();
        if (zza(jZzb, zZzf)) {
            zzagaVar.zza(new zzaee(this.zzc.zzb()));
        }
        this.zzc.zza(strZzd, zzacdVar, jZzb, zZzf);
        this.zzb.zza(zzagaVar, this.zzc.zza(zzacdVar, strZzd));
    }

    public final void zza(zzye zzyeVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzyeVar);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zzd(zzyeVar.zza(), new zzacd(zzaceVar, zza));
    }

    public final void zzd(String str, zzace zzaceVar) {
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zze(str, new zzacd(zzaceVar, zza));
    }

    public final void zza(zzagt zzagtVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzagtVar);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(zzagtVar, new zzacd(zzaceVar, zza));
    }

    public final void zza(zzagu zzaguVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzaguVar);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(zzaguVar, new zzacd(zzaceVar, zza));
    }

    public final void zzb(String str, String str2, String str3, String str4, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzaceVar);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zzb(str, str2, str3, str4, new zzacd(zzaceVar, zza));
    }

    public final void zza(zzyd zzydVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzydVar);
        Preconditions.checkNotNull(zzydVar.zza());
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(zzydVar.zza(), zzydVar.zzb(), new zzacd(zzaceVar, zza));
    }

    public final void zza(zzyg zzygVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzaceVar);
        Preconditions.checkNotNull(zzygVar);
        this.zzb.zza(zzado.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzygVar.zza())), new zzacd(zzaceVar, zza));
    }

    public final void zza(String str, String str2, String str3, long j, boolean z, boolean z2, String str4, String str5, boolean z3, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str, "idToken should not be empty.");
        Preconditions.checkNotNull(zzaceVar);
        zzacd zzacdVar = new zzacd(zzaceVar, zza);
        if (this.zzc.zzd(str2)) {
            if (z) {
                this.zzc.zzc(str2);
            } else {
                this.zzc.zzb(zzacdVar, str2);
                return;
            }
        }
        zzagk zzagkVarZza = zzagk.zza(str, str2, str3, str4, str5, null);
        if (zza(j, z3)) {
            zzagkVarZza.zza(new zzaee(this.zzc.zzb()));
        }
        this.zzc.zza(str2, zzacdVar, j, z3);
        this.zzb.zza(zzagkVarZza, this.zzc.zza(zzacdVar, str2));
    }

    public final void zza(zzyf zzyfVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzyfVar);
        Preconditions.checkNotNull(zzaceVar);
        String phoneNumber = zzyfVar.zzb().getPhoneNumber();
        zzacd zzacdVar = new zzacd(zzaceVar, zza);
        if (this.zzc.zzd(phoneNumber)) {
            if (zzyfVar.zzg()) {
                this.zzc.zzc(phoneNumber);
            } else {
                this.zzc.zzb(zzacdVar, phoneNumber);
                return;
            }
        }
        long jZza = zzyfVar.zza();
        boolean zZzh = zzyfVar.zzh();
        zzagi zzagiVarZza = zzagi.zza(zzyfVar.zzd(), zzyfVar.zzb().getUid(), zzyfVar.zzb().getPhoneNumber(), zzyfVar.zzc(), zzyfVar.zzf(), zzyfVar.zze());
        if (zza(jZza, zZzh)) {
            zzagiVarZza.zza(new zzaee(this.zzc.zzb()));
        }
        this.zzc.zza(phoneNumber, zzacdVar, jZza, zZzh);
        this.zzb.zza(zzagiVarZza, this.zzc.zza(zzacdVar, phoneNumber));
    }

    public final void zza(zzagm zzagmVar, zzace zzaceVar) {
        this.zzb.zza(zzagmVar, new zzacd((zzace) Preconditions.checkNotNull(zzaceVar), zza));
    }

    public final void zza(String str, String str2, String str3, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str, "cachedTokenState should not be empty.");
        Preconditions.checkNotEmpty(str2, "uid should not be empty.");
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zzb(str, str2, str3, new zzacd(zzaceVar, zza));
    }

    public final void zze(String str, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zzf(str, new zzacd(zzaceVar, zza));
    }

    public final void zzf(String str, String str2, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zzf(str, str2, new zzacd(zzaceVar, zza));
    }

    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzace zzaceVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzaceVar);
        this.zzb.zza(str, userProfileChangeRequest, new zzacd(zzaceVar, zza));
    }

    public final void zza(zzyi zzyiVar, zzace zzaceVar) {
        Preconditions.checkNotNull(zzyiVar);
        this.zzb.zza(zzafe.zza(zzyiVar.zza(), zzyiVar.zzb(), zzyiVar.zzc()), new zzacd(zzaceVar, zza));
    }

    private static boolean zza(long j, boolean z) {
        if (j <= 0 || !z) {
            zza.w("App hash will not be appended to the request.", new Object[0]);
            return false;
        }
        return true;
    }
}
