package com.google.android.recaptcha.internal;

import java.math.RoundingMode;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzed {
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004d, code lost:
    
        if (r3 == false) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int zza(int r5, int r6, java.math.RoundingMode r7) {
        /*
            if (r7 == 0) goto L5d
            if (r6 == 0) goto L55
            int r0 = r5 / r6
            int r1 = r6 * r0
            int r1 = r5 - r1
            if (r1 != 0) goto Ld
            return r0
        Ld:
            r5 = r5 ^ r6
            int[] r2 = com.google.android.recaptcha.internal.zzec.zza
            int r3 = r7.ordinal()
            r2 = r2[r3]
            int r5 = r5 >> 31
            r3 = 1
            r5 = r5 | r3
            r4 = 0
            switch(r2) {
                case 1: goto L51;
                case 2: goto L54;
                case 3: goto L49;
                case 4: goto L4f;
                case 5: goto L44;
                case 6: goto L24;
                case 7: goto L24;
                case 8: goto L24;
                default: goto L1e;
            }
        L1e:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>()
            throw r5
        L24:
            int r1 = java.lang.Math.abs(r1)
            int r6 = java.lang.Math.abs(r6)
            int r6 = r6 - r1
            int r1 = r1 - r6
            if (r1 != 0) goto L41
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_UP
            if (r7 == r6) goto L4b
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_EVEN
            if (r7 != r6) goto L3a
            r6 = r3
            goto L3b
        L3a:
            r6 = r4
        L3b:
            r7 = r0 & 1
            r6 = r6 & r7
            if (r6 == 0) goto L4c
            goto L4b
        L41:
            if (r1 <= 0) goto L4c
            goto L4b
        L44:
            if (r5 <= 0) goto L47
            goto L4b
        L47:
            r3 = r4
            goto L4d
        L49:
            if (r5 >= 0) goto L4c
        L4b:
            goto L4d
        L4c:
            r3 = r4
        L4d:
            if (r3 == 0) goto L54
        L4f:
            int r0 = r0 + r5
            return r0
        L51:
            com.google.android.recaptcha.internal.zzef.zzb(r4)
        L54:
            return r0
        L55:
            java.lang.ArithmeticException r5 = new java.lang.ArithmeticException
            java.lang.String r6 = "/ by zero"
            r5.<init>(r6)
            throw r5
        L5d:
            r5 = 0
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzed.zza(int, int, java.math.RoundingMode):int");
    }

    public static int zzb(int i, RoundingMode roundingMode) {
        if (i <= 0) {
            throw new IllegalArgumentException("x (0) must be > 0");
        }
        switch (zzec.zza[roundingMode.ordinal()]) {
            case 1:
                zzef.zzb(((i + (-1)) & i) == 0);
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(i - 1);
            case 6:
            case 7:
            case 8:
                int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(i);
                return (31 - iNumberOfLeadingZeros) + ((((-1257966797) >>> iNumberOfLeadingZeros) - i) >>> 31);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(i);
    }
}
