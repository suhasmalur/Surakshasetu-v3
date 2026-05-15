package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzakt<T> implements zzalf<T> {
    private final zzakn zza;
    private final zzame<?, ?> zzb;
    private final boolean zzc;
    private final zzaiq<?> zzd;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalf
    public final int zza(T t) {
        zzame<?, ?> zzameVar = this.zzb;
        int iZzb = zzameVar.zzb(zzameVar.zzd(t)) + 0;
        return this.zzc ? iZzb + this.zzd.zza(t).zza() : iZzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalf
    public final int zzb(T t) {
        int iHashCode = this.zzb.zzd(t).hashCode();
        if (this.zzc) {
            return (iHashCode * 53) + this.zzd.zza(t).hashCode();
        }
        return iHashCode;
    }

    static <T> zzakt<T> zza(zzame<?, ?> zzameVar, zzaiq<?> zzaiqVar, zzakn zzaknVar) {
        return new zzakt<>(zzameVar, zzaiqVar, zzaknVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalf
    public final T zza() {
        if (this.zza instanceof zzajc) {
            return (T) ((zzajc) this.zza).zzo();
        }
        return (T) this.zza.zzq().zzg();
    }

    private zzakt(zzame<?, ?> zzameVar, zzaiq<?> zzaiqVar, zzakn zzaknVar) {
        this.zzb = zzameVar;
        this.zzc = zzaiqVar.zza(zzaknVar);
        this.zzd = zzaiqVar;
        this.zza = zzaknVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalf
    public final void zzc(T t) {
        this.zzb.zzf(t);
        this.zzd.zzc(t);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalf
    public final void zza(T t, T t2) {
        zzalh.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzalh.zza(this.zzd, t, t2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x008a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[LOOP:0: B:46:0x000c->B:54:?, LOOP_END, SYNTHETIC] */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r11, com.google.android.gms.internal.p001firebaseauthapi.zzalc r12, com.google.android.gms.internal.p001firebaseauthapi.zzaio r13) throws java.io.IOException {
        /*
            r10 = this;
            com.google.android.gms.internal.firebase-auth-api.zzame<?, ?> r0 = r10.zzb
            com.google.android.gms.internal.firebase-auth-api.zzaiq<?> r1 = r10.zzd
            java.lang.Object r2 = r0.zzc(r11)
            com.google.android.gms.internal.firebase-auth-api.zzaiv r3 = r1.zzb(r11)
        Lc:
            int r4 = r12.zzc()     // Catch: java.lang.Throwable -> L93
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 != r5) goto L19
            r0.zzb(r11, r2)
            return
        L19:
            int r4 = r12.zzd()     // Catch: java.lang.Throwable -> L93
            r6 = 11
            if (r4 == r6) goto L40
        L23:
            r5 = r4 & 7
            r6 = 2
            if (r5 != r6) goto L3b
            com.google.android.gms.internal.firebase-auth-api.zzakn r5 = r10.zza     // Catch: java.lang.Throwable -> L93
            int r4 = r4 >>> 3
            java.lang.Object r4 = r1.zza(r13, r5, r4)     // Catch: java.lang.Throwable -> L93
            if (r4 == 0) goto L36
            r1.zza(r12, r4, r13, r3)     // Catch: java.lang.Throwable -> L93
            goto L87
        L36:
            boolean r4 = r0.zza(r2, r12)     // Catch: java.lang.Throwable -> L93
            goto L88
        L3b:
            boolean r4 = r12.zzt()     // Catch: java.lang.Throwable -> L93
            goto L88
        L40:
            r4 = 0
            r6 = 0
            r7 = r6
            r6 = r4
        L46:
            int r8 = r12.zzc()     // Catch: java.lang.Throwable -> L93
            if (r8 == r5) goto L74
            int r8 = r12.zzd()     // Catch: java.lang.Throwable -> L93
            r9 = 16
            if (r8 != r9) goto L5f
            int r7 = r12.zzj()     // Catch: java.lang.Throwable -> L93
            com.google.android.gms.internal.firebase-auth-api.zzakn r4 = r10.zza     // Catch: java.lang.Throwable -> L93
            java.lang.Object r4 = r1.zza(r13, r4, r7)     // Catch: java.lang.Throwable -> L93
            goto L46
        L5f:
            r9 = 26
            if (r8 != r9) goto L6e
            if (r4 == 0) goto L69
            r1.zza(r12, r4, r13, r3)     // Catch: java.lang.Throwable -> L93
            goto L46
        L69:
            com.google.android.gms.internal.firebase-auth-api.zzahp r6 = r12.zzp()     // Catch: java.lang.Throwable -> L93
            goto L46
        L6e:
            boolean r8 = r12.zzt()     // Catch: java.lang.Throwable -> L93
            if (r8 != 0) goto L46
        L74:
            int r5 = r12.zzd()     // Catch: java.lang.Throwable -> L93
            r8 = 12
            if (r5 != r8) goto L8e
            if (r6 == 0) goto L87
            if (r4 == 0) goto L84
            r1.zza(r6, r4, r13, r3)     // Catch: java.lang.Throwable -> L93
            goto L87
        L84:
            r0.zza(r2, r7, r6)     // Catch: java.lang.Throwable -> L93
        L87:
            r4 = 1
        L88:
            if (r4 != 0) goto Lc
            r0.zzb(r11, r2)
            return
        L8e:
            com.google.android.gms.internal.firebase-auth-api.zzaji r12 = com.google.android.gms.internal.p001firebaseauthapi.zzaji.zzb()     // Catch: java.lang.Throwable -> L93
            throw r12     // Catch: java.lang.Throwable -> L93
        L93:
            r12 = move-exception
            r0.zzb(r11, r2)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p001firebaseauthapi.zzakt.zza(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzalc, com.google.android.gms.internal.firebase-auth-api.zzaio):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009e A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r9, byte[] r10, int r11, int r12, com.google.android.gms.internal.p001firebaseauthapi.zzahk r13) throws java.io.IOException {
        /*
            r8 = this;
            r0 = r9
            com.google.android.gms.internal.firebase-auth-api.zzajc r0 = (com.google.android.gms.internal.p001firebaseauthapi.zzajc) r0
            com.google.android.gms.internal.firebase-auth-api.zzamd r1 = r0.zzb
            com.google.android.gms.internal.firebase-auth-api.zzamd r2 = com.google.android.gms.internal.p001firebaseauthapi.zzamd.zzc()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.firebase-auth-api.zzamd r1 = com.google.android.gms.internal.p001firebaseauthapi.zzamd.zzd()
            r0.zzb = r1
        L11:
            com.google.android.gms.internal.firebase-auth-api.zzajc$zzb r9 = (com.google.android.gms.internal.firebase-auth-api.zzajc.zzb) r9
            r9.zza()
            r9 = 0
            r0 = r9
        L18:
            if (r11 >= r12) goto Lab
            int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzahl.zzc(r10, r11, r13)
            int r2 = r13.zza
            r11 = 11
            r3 = 2
            if (r2 == r11) goto L53
        L26:
            r11 = r2 & 7
            if (r11 != r3) goto L4e
            com.google.android.gms.internal.firebase-auth-api.zzaiq<?> r11 = r8.zzd
            com.google.android.gms.internal.firebase-auth-api.zzaio r0 = r13.zzd
            com.google.android.gms.internal.firebase-auth-api.zzakn r3 = r8.zza
            int r5 = r2 >>> 3
            java.lang.Object r11 = r11.zza(r0, r3, r5)
            r0 = r11
            com.google.android.gms.internal.firebase-auth-api.zzajc$zzd r0 = (com.google.android.gms.internal.firebase-auth-api.zzajc.zzd) r0
            if (r0 != 0) goto L45
        L3c:
            r3 = r10
            r5 = r12
            r6 = r1
            r7 = r13
            int r11 = com.google.android.gms.internal.p001firebaseauthapi.zzahl.zza(r2, r3, r4, r5, r6, r7)
            goto L18
        L45:
            com.google.android.gms.internal.p001firebaseauthapi.zzalb.zza()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L4e:
            int r11 = com.google.android.gms.internal.p001firebaseauthapi.zzahl.zza(r2, r10, r4, r12, r13)
            goto L18
        L53:
            r11 = 0
            r2 = r9
        L56:
            if (r4 >= r12) goto L9e
            int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzahl.zzc(r10, r4, r13)
            int r5 = r13.zza
            int r6 = r5 >>> 3
            r7 = r5 & 7
            switch(r6) {
                case 2: goto L80;
                case 3: goto L6a;
                default: goto L69;
            }
        L69:
            goto L95
        L6a:
            if (r0 != 0) goto L77
            if (r7 != r3) goto L95
            int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzahl.zza(r10, r4, r13)
            java.lang.Object r2 = r13.zzc
            com.google.android.gms.internal.firebase-auth-api.zzahp r2 = (com.google.android.gms.internal.p001firebaseauthapi.zzahp) r2
            goto L56
        L77:
            com.google.android.gms.internal.p001firebaseauthapi.zzalb.zza()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L80:
            if (r7 != 0) goto L95
            int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzahl.zzc(r10, r4, r13)
            int r11 = r13.zza
            com.google.android.gms.internal.firebase-auth-api.zzaiq<?> r0 = r8.zzd
            com.google.android.gms.internal.firebase-auth-api.zzaio r5 = r13.zzd
            com.google.android.gms.internal.firebase-auth-api.zzakn r6 = r8.zza
            java.lang.Object r0 = r0.zza(r5, r6, r11)
            com.google.android.gms.internal.firebase-auth-api.zzajc$zzd r0 = (com.google.android.gms.internal.firebase-auth-api.zzajc.zzd) r0
            goto L56
        L95:
            r6 = 12
            if (r5 == r6) goto L9e
            int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzahl.zza(r5, r10, r4, r12, r13)
            goto L56
        L9e:
            if (r2 == 0) goto La8
        La1:
            int r11 = r11 << 3
            r11 = r11 | r3
            r1.zza(r11, r2)
        La8:
            r11 = r4
            goto L18
        Lab:
            if (r11 != r12) goto Lae
            return
        Lae:
            com.google.android.gms.internal.firebase-auth-api.zzaji r9 = com.google.android.gms.internal.p001firebaseauthapi.zzaji.zzg()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p001firebaseauthapi.zzakt.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzahk):void");
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalf
    public final void zza(T t, zzana zzanaVar) throws IOException {
        Iterator itZzd = this.zzd.zza(t).zzd();
        while (itZzd.hasNext()) {
            Map.Entry entry = (Map.Entry) itZzd.next();
            zzaix zzaixVar = (zzaix) entry.getKey();
            if (zzaixVar.zzc() != zzanb.MESSAGE || zzaixVar.zze() || zzaixVar.zzd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof zzajm) {
                zzanaVar.zza(zzaixVar.zza(), (Object) ((zzajm) entry).zza().zzc());
            } else {
                zzanaVar.zza(zzaixVar.zza(), entry.getValue());
            }
        }
        zzame<?, ?> zzameVar = this.zzb;
        zzameVar.zza(zzameVar.zzd(t), zzanaVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalf
    public final boolean zzb(T t, T t2) {
        if (!this.zzb.zzd(t).equals(this.zzb.zzd(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza(t).equals(this.zzd.zza(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzalf
    public final boolean zzd(T t) {
        return this.zzd.zza(t).zzg();
    }
}
