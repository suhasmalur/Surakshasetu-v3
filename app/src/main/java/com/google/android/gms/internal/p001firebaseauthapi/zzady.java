package com.google.android.gms.internal.p001firebaseauthapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzady extends BroadcastReceiver {
    private final String zza;
    private final /* synthetic */ zzadu zzb;

    public zzady(zzadu zzaduVar, String str) {
        this.zzb = zzaduVar;
        this.zza = str;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            switch (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode()) {
                case 0:
                    String str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                    zzaeb zzaebVar = (zzaeb) this.zzb.zzd.get(this.zza);
                    if (zzaebVar == null) {
                        zzadu.zza.e("Verification code received with no active retrieval session.", new Object[0]);
                    } else {
                        zzaebVar.zze = zzadu.zza(str);
                        if (zzaebVar.zze == null) {
                            zzadu.zza.e("Unable to extract verification code.", new Object[0]);
                        } else if (!zzag.zzc(zzaebVar.zzd)) {
                            zzadu.zza(this.zzb, this.zza);
                        }
                    }
                    break;
            }
            context.getApplicationContext().unregisterReceiver(this);
        }
    }
}
