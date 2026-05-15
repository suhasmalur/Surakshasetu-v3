package com.google.android.gms.internal.p001firebaseauthapi;

import java.lang.reflect.Type;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public class zzacq {
    private static final String zza = zzacq.class.getName();

    public static Object zza(String str, Type type) throws zzaaf {
        if (type == String.class) {
            try {
                zzael zzaelVar = (zzael) new zzael().zza(str);
                if (zzaelVar.zzb()) {
                    return zzaelVar.zza();
                }
                throw new zzaaf("No error message: " + str);
            } catch (Exception e) {
                throw new zzaaf("Json conversion failed! " + e.getMessage(), e);
            }
        }
        if (type == Void.class) {
            return null;
        }
        try {
            try {
                return ((zzacs) ((Class) type).getConstructor(new Class[0]).newInstance(new Object[0])).zza(str);
            } catch (Exception e2) {
                throw new zzaaf("Json conversion failed! " + e2.getMessage(), e2);
            }
        } catch (Exception e3) {
            throw new zzaaf("Instantiation of JsonResponse failed! " + String.valueOf(type), e3);
        }
    }

    private zzacq() {
    }
}
