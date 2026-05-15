package com.google.android.gms.internal.p001firebaseauthapi;

import androidx.collection.ArrayMap;
import com.google.firebase.FirebaseApp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzaed {
    private static final Map<String, zzaec> zza = new ArrayMap();
    private static final Map<String, List<WeakReference<zzaef>>> zzb = new ArrayMap();

    private static String zza(String str, int i, boolean z) {
        return z ? "http://[" + str + "]:" + i + "/" : "http://" + str + ":" + i + "/";
    }

    public static String zza(String str) {
        zzaec zzaecVar;
        synchronized (zza) {
            zzaecVar = zza.get(str);
        }
        if (zzaecVar == null) {
            throw new IllegalStateException("Tried to get the emulator widget endpoint, but no emulator endpoint overrides found.");
        }
        return zza(zzaecVar.zzb(), zzaecVar.zza(), zzaecVar.zzb().contains(":")) + "emulator/auth/handler";
    }

    public static String zzb(String str) {
        zzaec zzaecVar;
        String str2;
        synchronized (zza) {
            zzaecVar = zza.get(str);
        }
        if (zzaecVar == null) {
            str2 = "https://";
        } else {
            str2 = "" + zza(zzaecVar.zzb(), zzaecVar.zza(), zzaecVar.zzb().contains(":"));
        }
        return str2 + "www.googleapis.com/identitytoolkit/v3/relyingparty";
    }

    public static String zzc(String str) {
        zzaec zzaecVar;
        String str2;
        synchronized (zza) {
            zzaecVar = zza.get(str);
        }
        if (zzaecVar == null) {
            str2 = "https://";
        } else {
            str2 = "" + zza(zzaecVar.zzb(), zzaecVar.zza(), zzaecVar.zzb().contains(":"));
        }
        return str2 + "identitytoolkit.googleapis.com/v2";
    }

    public static String zzd(String str) {
        zzaec zzaecVar;
        String str2;
        synchronized (zza) {
            zzaecVar = zza.get(str);
        }
        if (zzaecVar == null) {
            str2 = "https://";
        } else {
            str2 = "" + zza(zzaecVar.zzb(), zzaecVar.zza(), zzaecVar.zzb().contains(":"));
        }
        return str2 + "securetoken.googleapis.com/v1";
    }

    public static void zza(String str, zzaef zzaefVar) {
        synchronized (zzb) {
            if (zzb.containsKey(str)) {
                zzb.get(str).add(new WeakReference<>(zzaefVar));
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new WeakReference(zzaefVar));
                zzb.put(str, arrayList);
            }
        }
    }

    public static void zza(FirebaseApp firebaseApp, String str, int i) {
        String apiKey = firebaseApp.getOptions().getApiKey();
        synchronized (zza) {
            zza.put(apiKey, new zzaec(str, i));
        }
        synchronized (zzb) {
            if (zzb.containsKey(apiKey)) {
                Iterator<WeakReference<zzaef>> it = zzb.get(apiKey).iterator();
                boolean z = false;
                while (it.hasNext()) {
                    zzaef zzaefVar = it.next().get();
                    if (zzaefVar != null) {
                        zzaefVar.zza();
                        z = true;
                    }
                }
                if (!z) {
                    zza.remove(apiKey);
                }
            }
        }
    }

    public static boolean zza(FirebaseApp firebaseApp) {
        return zza.containsKey(firebaseApp.getOptions().getApiKey());
    }
}
