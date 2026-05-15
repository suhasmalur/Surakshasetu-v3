package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzakb implements zzale {
    private static final zzakk zza = new zzaka();
    private final zzakk zzb;

    private static zzakk zza() {
        try {
            return (zzakk) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return zza;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzale
    public final <T> zzalf<T> zza(Class<T> cls) {
        zzalh.zza((Class<?>) cls);
        zzakl zzaklVarZza = this.zzb.zza(cls);
        if (zzaklVarZza.zzc()) {
            if (zzajc.class.isAssignableFrom(cls)) {
                return zzakt.zza(zzalh.zzb(), zzais.zzb(), zzaklVarZza.zza());
            }
            return zzakt.zza(zzalh.zza(), zzais.zza(), zzaklVarZza.zza());
        }
        if (zzajc.class.isAssignableFrom(cls)) {
            if (zza(zzaklVarZza)) {
                return zzakr.zza(cls, zzaklVarZza, zzakx.zzb(), zzajs.zzb(), zzalh.zzb(), zzais.zzb(), zzaki.zzb());
            }
            return zzakr.zza(cls, zzaklVarZza, zzakx.zzb(), zzajs.zzb(), zzalh.zzb(), (zzaiq<?>) null, zzaki.zzb());
        }
        if (zza(zzaklVarZza)) {
            return zzakr.zza(cls, zzaklVarZza, zzakx.zza(), zzajs.zza(), zzalh.zza(), zzais.zza(), zzaki.zza());
        }
        return zzakr.zza(cls, zzaklVarZza, zzakx.zza(), zzajs.zza(), zzalh.zza(), (zzaiq<?>) null, zzaki.zza());
    }

    public zzakb() {
        this(new zzakc(zzaja.zza(), zza()));
    }

    private zzakb(zzakk zzakkVar) {
        this.zzb = (zzakk) zzajf.zza(zzakkVar, "messageInfoFactory");
    }

    private static boolean zza(zzakl zzaklVar) {
        switch (zzaklVar.zzb()) {
            case PROTO3:
                return false;
            default:
                return true;
        }
    }
}
