package com.google.android.gms.internal.p001firebaseauthapi;

import java.lang.reflect.Type;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public enum zzaiw {
    DOUBLE(0, zzaiy.SCALAR, zzajk.DOUBLE),
    FLOAT(1, zzaiy.SCALAR, zzajk.FLOAT),
    INT64(2, zzaiy.SCALAR, zzajk.LONG),
    UINT64(3, zzaiy.SCALAR, zzajk.LONG),
    INT32(4, zzaiy.SCALAR, zzajk.INT),
    FIXED64(5, zzaiy.SCALAR, zzajk.LONG),
    FIXED32(6, zzaiy.SCALAR, zzajk.INT),
    BOOL(7, zzaiy.SCALAR, zzajk.BOOLEAN),
    STRING(8, zzaiy.SCALAR, zzajk.STRING),
    MESSAGE(9, zzaiy.SCALAR, zzajk.MESSAGE),
    BYTES(10, zzaiy.SCALAR, zzajk.BYTE_STRING),
    UINT32(11, zzaiy.SCALAR, zzajk.INT),
    ENUM(12, zzaiy.SCALAR, zzajk.ENUM),
    SFIXED32(13, zzaiy.SCALAR, zzajk.INT),
    SFIXED64(14, zzaiy.SCALAR, zzajk.LONG),
    SINT32(15, zzaiy.SCALAR, zzajk.INT),
    SINT64(16, zzaiy.SCALAR, zzajk.LONG),
    GROUP(17, zzaiy.SCALAR, zzajk.MESSAGE),
    DOUBLE_LIST(18, zzaiy.VECTOR, zzajk.DOUBLE),
    FLOAT_LIST(19, zzaiy.VECTOR, zzajk.FLOAT),
    INT64_LIST(20, zzaiy.VECTOR, zzajk.LONG),
    UINT64_LIST(21, zzaiy.VECTOR, zzajk.LONG),
    INT32_LIST(22, zzaiy.VECTOR, zzajk.INT),
    FIXED64_LIST(23, zzaiy.VECTOR, zzajk.LONG),
    FIXED32_LIST(24, zzaiy.VECTOR, zzajk.INT),
    BOOL_LIST(25, zzaiy.VECTOR, zzajk.BOOLEAN),
    STRING_LIST(26, zzaiy.VECTOR, zzajk.STRING),
    MESSAGE_LIST(27, zzaiy.VECTOR, zzajk.MESSAGE),
    BYTES_LIST(28, zzaiy.VECTOR, zzajk.BYTE_STRING),
    UINT32_LIST(29, zzaiy.VECTOR, zzajk.INT),
    ENUM_LIST(30, zzaiy.VECTOR, zzajk.ENUM),
    SFIXED32_LIST(31, zzaiy.VECTOR, zzajk.INT),
    SFIXED64_LIST(32, zzaiy.VECTOR, zzajk.LONG),
    SINT32_LIST(33, zzaiy.VECTOR, zzajk.INT),
    SINT64_LIST(34, zzaiy.VECTOR, zzajk.LONG),
    DOUBLE_LIST_PACKED(35, zzaiy.PACKED_VECTOR, zzajk.DOUBLE),
    FLOAT_LIST_PACKED(36, zzaiy.PACKED_VECTOR, zzajk.FLOAT),
    INT64_LIST_PACKED(37, zzaiy.PACKED_VECTOR, zzajk.LONG),
    UINT64_LIST_PACKED(38, zzaiy.PACKED_VECTOR, zzajk.LONG),
    INT32_LIST_PACKED(39, zzaiy.PACKED_VECTOR, zzajk.INT),
    FIXED64_LIST_PACKED(40, zzaiy.PACKED_VECTOR, zzajk.LONG),
    FIXED32_LIST_PACKED(41, zzaiy.PACKED_VECTOR, zzajk.INT),
    BOOL_LIST_PACKED(42, zzaiy.PACKED_VECTOR, zzajk.BOOLEAN),
    UINT32_LIST_PACKED(43, zzaiy.PACKED_VECTOR, zzajk.INT),
    ENUM_LIST_PACKED(44, zzaiy.PACKED_VECTOR, zzajk.ENUM),
    SFIXED32_LIST_PACKED(45, zzaiy.PACKED_VECTOR, zzajk.INT),
    SFIXED64_LIST_PACKED(46, zzaiy.PACKED_VECTOR, zzajk.LONG),
    SINT32_LIST_PACKED(47, zzaiy.PACKED_VECTOR, zzajk.INT),
    SINT64_LIST_PACKED(48, zzaiy.PACKED_VECTOR, zzajk.LONG),
    GROUP_LIST(49, zzaiy.VECTOR, zzajk.MESSAGE),
    MAP(50, zzaiy.MAP, zzajk.VOID);

    private static final zzaiw[] zzaz;
    private static final Type[] zzba = new Type[0];
    private final zzajk zzbc;
    private final int zzbd;
    private final zzaiy zzbe;
    private final Class<?> zzbf;
    private final boolean zzbg;

    public final int zza() {
        return this.zzbd;
    }

    static {
        zzaiw[] zzaiwVarArrValues = values();
        zzaz = new zzaiw[zzaiwVarArrValues.length];
        for (zzaiw zzaiwVar : zzaiwVarArrValues) {
            zzaz[zzaiwVar.zzbd] = zzaiwVar;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    zzaiw(int r3, com.google.android.gms.internal.p001firebaseauthapi.zzaiy r4, com.google.android.gms.internal.p001firebaseauthapi.zzajk r5) {
        /*
            r0 = this;
            r0.<init>(r1, r2)
            r0.zzbd = r3
            r0.zzbe = r4
            r0.zzbc = r5
            int r1 = r4.ordinal()
            switch(r1) {
                case 1: goto L1b;
                case 2: goto L10;
                case 3: goto L14;
                default: goto L10;
            }
        L10:
            r1 = 0
            r0.zzbf = r1
            goto L22
        L14:
            java.lang.Class r1 = r5.zza()
            r0.zzbf = r1
            goto L22
        L1b:
            java.lang.Class r1 = r5.zza()
            r0.zzbf = r1
        L22:
            com.google.android.gms.internal.firebase-auth-api.zzaiy r1 = com.google.android.gms.internal.p001firebaseauthapi.zzaiy.SCALAR
            if (r4 != r1) goto L35
            int[] r1 = com.google.android.gms.internal.p001firebaseauthapi.zzaiz.zza
            int r2 = r5.ordinal()
            r1 = r1[r2]
            switch(r1) {
                case 1: goto L34;
                case 2: goto L34;
                case 3: goto L34;
                default: goto L32;
            }
        L32:
            r1 = 1
            goto L36
        L34:
        L35:
            r1 = 0
        L36:
            r0.zzbg = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p001firebaseauthapi.zzaiw.<init>(java.lang.String, int, int, com.google.android.gms.internal.firebase-auth-api.zzaiy, com.google.android.gms.internal.firebase-auth-api.zzajk):void");
    }
}
