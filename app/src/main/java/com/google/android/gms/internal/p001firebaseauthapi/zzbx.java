package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzuy;
import com.google.android.gms.internal.p001firebaseauthapi.zzvg;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbx {
    private final zzvg zza;
    private final List<zzbz> zzb;
    private final zzrn zzc = zzrn.zza;

    static final zzbx zza(zzvg zzvgVar) throws GeneralSecurityException {
        zzc(zzvgVar);
        return new zzbx(zzvgVar, zzb(zzvgVar));
    }

    public final zzbx zza() throws GeneralSecurityException {
        if (this.zza == null) {
            throw new GeneralSecurityException("cleartext keyset is not available");
        }
        zzvg.zza zzaVarZzc = zzvg.zzc();
        for (zzvg.zzb zzbVar : this.zza.zze()) {
            zzuy zzuyVarZzb = zzbVar.zzb();
            if (zzuyVarZzb.zzb() != zzuy.zza.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException("The keyset contains a non-private key");
            }
            zzuy zzuyVarZza = zzct.zza(zzuyVarZzb.zzf(), zzuyVarZzb.zze());
            zzct.zza(zzuyVarZza);
            zzaVarZzc.zza((zzvg.zzb) ((zzajc) zzbVar.zzn().zza(zzuyVarZza).zzf()));
        }
        zzaVarZzc.zza(this.zza.zzb());
        return zza((zzvg) ((zzajc) zzaVarZzc.zzf()));
    }

    public static final zzbx zza(zzca zzcaVar, zzbg zzbgVar) throws GeneralSecurityException, IOException {
        byte[] bArr = new byte[0];
        zzua zzuaVarZza = zzcaVar.zza();
        if (zzuaVarZza == null || zzuaVarZza.zzc().zzb() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        return zza(zza(zzuaVarZza, zzbgVar, bArr));
    }

    private static zzos zza(zzvg.zzb zzbVar) {
        try {
            return zzos.zza(zzbVar.zzb().zzf(), zzbVar.zzb().zze(), zzbVar.zzb().zzb(), zzbVar.zzf(), zzbVar.zzf() == zzvs.RAW ? null : Integer.valueOf(zzbVar.zza()));
        } catch (GeneralSecurityException e) {
            throw new zzpd("Creating a protokey serialization failed", e);
        }
    }

    private static zzua zza(zzvg zzvgVar, zzbg zzbgVar, byte[] bArr) throws GeneralSecurityException {
        byte[] bArrZzb = zzbgVar.zzb(zzvgVar.zzj(), bArr);
        try {
            if (!zzvg.zza(zzbgVar.zza(bArrZzb, bArr), zzaio.zza()).equals(zzvgVar)) {
                throw new GeneralSecurityException("cannot encrypt keyset");
            }
            return (zzua) ((zzajc) zzua.zza().zza(zzahp.zza(bArrZzb)).zza(zzcx.zza(zzvgVar)).zzf());
        } catch (zzaji e) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    private static zzvg zza(zzua zzuaVar, zzbg zzbgVar, byte[] bArr) throws GeneralSecurityException {
        try {
            zzvg zzvgVarZza = zzvg.zza(zzbgVar.zza(zzuaVar.zzc().zzg(), bArr), zzaio.zza());
            zzc(zzvgVarZza);
            return zzvgVarZza;
        } catch (zzaji e) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    final zzvg zzb() {
        return this.zza;
    }

    public final zzvj zzc() {
        return zzcx.zza(this.zza);
    }

    @Nullable
    private static <B> B zza(zzmp zzmpVar, zzbt zzbtVar, Class<B> cls) throws GeneralSecurityException {
        try {
            return (B) zzmpVar.zza(zzbtVar, cls);
        } catch (GeneralSecurityException e) {
            return null;
        }
    }

    @Nullable
    private static <B> B zza(zzmp zzmpVar, zzvg.zzb zzbVar, Class<B> cls) throws GeneralSecurityException {
        try {
            return (B) zzmpVar.zza(zzbVar.zzb(), cls);
        } catch (UnsupportedOperationException e) {
            return null;
        } catch (GeneralSecurityException e2) {
            if (e2.getMessage().contains("No key manager found for key type ") || e2.getMessage().contains(" not supported by key manager of type ")) {
                return null;
            }
            throw e2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <P> P zza(Class<P> cls) throws GeneralSecurityException {
        Object objZza;
        zzow zzowVarZza = zzow.zza();
        if (!(zzowVarZza instanceof zzmp)) {
            throw new GeneralSecurityException("Currently only subclasses of InternalConfiguration are accepted");
        }
        zzow zzowVar = zzowVarZza;
        Class<?> clsZza = zzowVar.zza(cls);
        if (clsZza == null) {
            throw new GeneralSecurityException("No wrapper found for " + cls.getName());
        }
        zzcx.zzb(this.zza);
        zzcj zzcjVar = new zzcj(clsZza);
        zzcjVar.zza(this.zzc);
        for (int i = 0; i < this.zza.zza(); i++) {
            zzvg.zzb zzbVarZza = this.zza.zza(i);
            if (zzbVarZza.zzc().equals(zzuz.ENABLED)) {
                Object objZza2 = zza(zzowVar, zzbVarZza, clsZza);
                if (this.zzb.get(i) == null) {
                    objZza = null;
                } else {
                    objZza = zza(zzowVar, this.zzb.get(i).zza(), clsZza);
                }
                if (objZza == null && objZza2 == null) {
                    throw new GeneralSecurityException("Unable to get primitive " + String.valueOf(clsZza) + " for key of type " + zzbVarZza.zzb().zzf());
                }
                if (zzbVarZza.zza() == this.zza.zzb()) {
                    zzcjVar.zzb(objZza, objZza2, zzbVarZza);
                } else {
                    zzcjVar.zza(objZza, objZza2, zzbVarZza);
                }
            }
        }
        return (P) zzowVar.zza(zzcjVar.zza(), cls);
    }

    public final String toString() {
        return zzcx.zza(this.zza).toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List<com.google.android.gms.internal.p001firebaseauthapi.zzbz> zzb(com.google.android.gms.internal.p001firebaseauthapi.zzvg r10) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r10.zza()
            r0.<init>(r1)
            java.util.List r1 = r10.zze()
            java.util.Iterator r1 = r1.iterator()
        L11:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L6f
            java.lang.Object r2 = r1.next()
            com.google.android.gms.internal.firebase-auth-api.zzvg$zzb r2 = (com.google.android.gms.internal.firebase-auth-api.zzvg.zzb) r2
            int r6 = r2.zza()
            com.google.android.gms.internal.firebase-auth-api.zzos r3 = zza(r2)
            com.google.android.gms.internal.firebase-auth-api.zznu r4 = com.google.android.gms.internal.p001firebaseauthapi.zznu.zza()     // Catch: java.security.GeneralSecurityException -> L69
            com.google.android.gms.internal.firebase-auth-api.zzcs r5 = com.google.android.gms.internal.p001firebaseauthapi.zzcs.zza()     // Catch: java.security.GeneralSecurityException -> L69
            com.google.android.gms.internal.firebase-auth-api.zzbt r4 = r4.zza(r3, r5)     // Catch: java.security.GeneralSecurityException -> L69
            com.google.android.gms.internal.firebase-auth-api.zzbz r9 = new com.google.android.gms.internal.firebase-auth-api.zzbz     // Catch: java.security.GeneralSecurityException -> L69
            com.google.android.gms.internal.firebase-auth-api.zzuz r2 = r2.zzc()     // Catch: java.security.GeneralSecurityException -> L69
            int[] r3 = com.google.android.gms.internal.p001firebaseauthapi.zzbw.zza     // Catch: java.security.GeneralSecurityException -> L69
            int r2 = r2.ordinal()     // Catch: java.security.GeneralSecurityException -> L69
            r2 = r3[r2]     // Catch: java.security.GeneralSecurityException -> L69
            switch(r2) {
                case 1: goto L4d;
                case 2: goto L49;
                case 3: goto L45;
                default: goto L42;
            }     // Catch: java.security.GeneralSecurityException -> L69
        L42:
            java.security.GeneralSecurityException r2 = new java.security.GeneralSecurityException     // Catch: java.security.GeneralSecurityException -> L69
            goto L63
        L45:
            com.google.android.gms.internal.firebase-auth-api.zzbv r2 = com.google.android.gms.internal.p001firebaseauthapi.zzbv.zzc     // Catch: java.security.GeneralSecurityException -> L69
            r5 = r2
            goto L50
        L49:
            com.google.android.gms.internal.firebase-auth-api.zzbv r2 = com.google.android.gms.internal.p001firebaseauthapi.zzbv.zzb     // Catch: java.security.GeneralSecurityException -> L69
            r5 = r2
            goto L50
        L4d:
            com.google.android.gms.internal.firebase-auth-api.zzbv r2 = com.google.android.gms.internal.p001firebaseauthapi.zzbv.zza     // Catch: java.security.GeneralSecurityException -> L69
            r5 = r2
        L50:
            int r2 = r10.zzb()     // Catch: java.security.GeneralSecurityException -> L69
            if (r6 != r2) goto L58
            r2 = 1
            goto L59
        L58:
            r2 = 0
        L59:
            r7 = r2
            r8 = 0
            r3 = r9
            r3.<init>(r4, r5, r6, r7)     // Catch: java.security.GeneralSecurityException -> L69
            r0.add(r9)     // Catch: java.security.GeneralSecurityException -> L69
            goto L11
        L63:
            java.lang.String r3 = "Unknown key status"
            r2.<init>(r3)     // Catch: java.security.GeneralSecurityException -> L69
            throw r2     // Catch: java.security.GeneralSecurityException -> L69
        L69:
            r2 = move-exception
            r2 = 0
            r0.add(r2)
            goto L11
        L6f:
            java.util.List r10 = java.util.Collections.unmodifiableList(r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p001firebaseauthapi.zzbx.zzb(com.google.android.gms.internal.firebase-auth-api.zzvg):java.util.List");
    }

    private zzbx(zzvg zzvgVar, List<zzbz> list) {
        this.zza = zzvgVar;
        this.zzb = list;
    }

    private static void zzc(zzvg zzvgVar) throws GeneralSecurityException {
        if (zzvgVar == null || zzvgVar.zza() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public final void zza(zzcd zzcdVar, zzbg zzbgVar) throws GeneralSecurityException, IOException {
        zzcdVar.zza(zza(this.zza, zzbgVar, new byte[0]));
    }

    public final void zza(zzcd zzcdVar) throws GeneralSecurityException, IOException {
        for (zzvg.zzb zzbVar : this.zza.zze()) {
            if (zzbVar.zzb().zzb() == zzuy.zza.UNKNOWN_KEYMATERIAL || zzbVar.zzb().zzb() == zzuy.zza.SYMMETRIC || zzbVar.zzb().zzb() == zzuy.zza.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException(String.format("keyset contains key material of type %s for type url %s", zzbVar.zzb().zzb().name(), zzbVar.zzb().zzf()));
            }
        }
        zzcdVar.zza(this.zza);
    }
}
