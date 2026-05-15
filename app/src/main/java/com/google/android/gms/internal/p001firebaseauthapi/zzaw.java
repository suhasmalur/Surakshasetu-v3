package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.CheckForNull;
import kotlin.UShort;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaw<K, V> extends zzat<K, V> {
    private static final zzat<Object, Object> zza = new zzaw(null, new Object[0], 0);

    @CheckForNull
    private final transient Object zzb;
    private final transient Object[] zzc;
    private final transient int zzd;

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzat
    final zzak<V> zza() {
        return new zzba(this.zzc, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzat
    final zzau<Map.Entry<K, V>> zzb() {
        return new zzaz(this, this.zzc, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzat
    final zzau<K> zzc() {
        return new zzbb(this, new zzba(this.zzc, 0, this.zzd));
    }

    static <K, V> zzaw<K, V> zza(int i, Object[] objArr, zzas<K, V> zzasVar) {
        int iHighestOneBit;
        char c;
        int i2 = i;
        Object[] objArrCopyOf = objArr;
        if (i2 == 0) {
            return (zzaw) zza;
        }
        zzav zzavVar = null;
        Object obj = null;
        zzav zzavVar2 = null;
        zzav zzavVar3 = null;
        int i3 = 1;
        if (i2 == 1) {
            Object obj2 = objArrCopyOf[0];
            obj2.getClass();
            Object obj3 = objArrCopyOf[1];
            obj3.getClass();
            zzai.zza(obj2, obj3);
            return new zzaw<>(null, objArrCopyOf, 1);
        }
        zzy.zzb(i2, objArrCopyOf.length >> 1);
        int iMax = Math.max(i2, 2);
        if (iMax < 751619276) {
            iHighestOneBit = Integer.highestOneBit(iMax - 1) << 1;
            while (((double) iHighestOneBit) * 0.7d < iMax) {
                iHighestOneBit <<= 1;
            }
        } else {
            iHighestOneBit = 1073741824;
            if (!(iMax < 1073741824)) {
                throw new IllegalArgumentException(String.valueOf("collection too large"));
            }
        }
        if (i2 == 1) {
            Object obj4 = objArrCopyOf[0];
            obj4.getClass();
            Object obj5 = objArrCopyOf[1];
            obj5.getClass();
            zzai.zza(obj4, obj5);
            c = 2;
        } else {
            int i4 = iHighestOneBit - 1;
            int i5 = -1;
            if (iHighestOneBit <= 128) {
                byte[] bArr = new byte[iHighestOneBit];
                Arrays.fill(bArr, (byte) -1);
                int i6 = 0;
                int i7 = 0;
                while (i6 < i2) {
                    int i8 = i6 * 2;
                    int i9 = i7 * 2;
                    Object obj6 = objArrCopyOf[i8];
                    obj6.getClass();
                    Object obj7 = objArrCopyOf[i8 ^ i3];
                    obj7.getClass();
                    zzai.zza(obj6, obj7);
                    int iZza = zzal.zza(obj6.hashCode());
                    while (true) {
                        int i10 = iZza & i4;
                        int i11 = bArr[i10] & 255;
                        if (i11 == 255) {
                            bArr[i10] = (byte) i9;
                            if (i7 < i6) {
                                objArrCopyOf[i9] = obj6;
                                objArrCopyOf[i9 ^ 1] = obj7;
                            }
                            i7++;
                        } else {
                            if (obj6.equals(objArrCopyOf[i11 == true ? 1 : 0])) {
                                int i12 = ~i11;
                                Object obj8 = objArrCopyOf[i12 == true ? 1 : 0];
                                obj8.getClass();
                                zzavVar2 = new zzav(obj6, obj7, obj8);
                                objArrCopyOf[i12 == true ? 1 : 0] = obj7;
                                break;
                            }
                            iZza = i10 + 1;
                        }
                    }
                    i6++;
                    i3 = 1;
                }
                if (i7 == i2) {
                    obj = bArr;
                    c = 2;
                } else {
                    obj = new Object[]{bArr, Integer.valueOf(i7), zzavVar2};
                    c = 2;
                }
            } else if (iHighestOneBit <= 32768) {
                short[] sArr = new short[iHighestOneBit];
                Arrays.fill(sArr, (short) -1);
                int i13 = 0;
                for (int i14 = 0; i14 < i2; i14++) {
                    int i15 = i14 * 2;
                    int i16 = i13 * 2;
                    Object obj9 = objArrCopyOf[i15];
                    obj9.getClass();
                    Object obj10 = objArrCopyOf[i15 ^ 1];
                    obj10.getClass();
                    zzai.zza(obj9, obj10);
                    int iZza2 = zzal.zza(obj9.hashCode());
                    while (true) {
                        int i17 = iZza2 & i4;
                        int i18 = sArr[i17] & UShort.MAX_VALUE;
                        if (i18 == 65535) {
                            sArr[i17] = (short) i16;
                            if (i13 < i14) {
                                objArrCopyOf[i16] = obj9;
                                objArrCopyOf[i16 ^ 1] = obj10;
                            }
                            i13++;
                        } else {
                            if (obj9.equals(objArrCopyOf[i18 == true ? 1 : 0])) {
                                int i19 = ~i18;
                                Object obj11 = objArrCopyOf[i19 == true ? 1 : 0];
                                obj11.getClass();
                                zzavVar3 = new zzav(obj9, obj10, obj11);
                                objArrCopyOf[i19 == true ? 1 : 0] = obj10;
                                break;
                            }
                            iZza2 = i17 + 1;
                        }
                    }
                }
                if (i13 == i2) {
                    obj = sArr;
                    c = 2;
                } else {
                    c = 2;
                    obj = new Object[]{sArr, Integer.valueOf(i13), zzavVar3};
                }
            } else {
                int[] iArr = new int[iHighestOneBit];
                Arrays.fill(iArr, -1);
                int i20 = 0;
                int i21 = 0;
                while (i20 < i2) {
                    int i22 = i20 * 2;
                    int i23 = i21 * 2;
                    Object obj12 = objArrCopyOf[i22];
                    obj12.getClass();
                    Object obj13 = objArrCopyOf[i22 ^ 1];
                    obj13.getClass();
                    zzai.zza(obj12, obj13);
                    int iZza3 = zzal.zza(obj12.hashCode());
                    while (true) {
                        int i24 = iZza3 & i4;
                        int i25 = iArr[i24];
                        if (i25 == i5) {
                            iArr[i24] = i23;
                            if (i21 < i20) {
                                objArrCopyOf[i23] = obj12;
                                objArrCopyOf[i23 ^ 1] = obj13;
                            }
                            i21++;
                        } else {
                            if (obj12.equals(objArrCopyOf[i25])) {
                                int i26 = i25 ^ 1;
                                Object obj14 = objArrCopyOf[i26];
                                obj14.getClass();
                                zzavVar = new zzav(obj12, obj13, obj14);
                                objArrCopyOf[i26] = obj13;
                                break;
                            }
                            iZza3 = i24 + 1;
                            i5 = -1;
                        }
                    }
                    i20++;
                    i5 = -1;
                }
                if (i21 == i2) {
                    obj = iArr;
                    c = 2;
                } else {
                    c = 2;
                    obj = new Object[]{iArr, Integer.valueOf(i21), zzavVar};
                }
            }
        }
        boolean z = obj instanceof Object[];
        Object obj15 = obj;
        if (z) {
            Object[] objArr2 = (Object[]) obj;
            zzav zzavVar4 = (zzav) objArr2[c];
            if (zzasVar == null) {
                throw zzavVar4.zza();
            }
            zzasVar.zza = zzavVar4;
            Object obj16 = objArr2[0];
            int iIntValue = ((Integer) objArr2[1]).intValue();
            objArrCopyOf = Arrays.copyOf(objArrCopyOf, iIntValue << 1);
            obj15 = obj16;
            i2 = iIntValue;
        }
        return new zzaw<>(obj15, objArrCopyOf, i2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzat, java.util.Map
    @CheckForNull
    public final V get(@CheckForNull Object obj) {
        V v;
        Object obj2 = this.zzb;
        Object[] objArr = this.zzc;
        int i = this.zzd;
        if (obj == null) {
            v = null;
        } else if (i == 1) {
            Object obj3 = objArr[0];
            obj3.getClass();
            if (obj3.equals(obj)) {
                v = (V) objArr[1];
                v.getClass();
            } else {
                v = null;
            }
        } else if (obj2 == null) {
            v = null;
        } else if (obj2 instanceof byte[]) {
            byte[] bArr = (byte[]) obj2;
            int length = bArr.length - 1;
            int iZza = zzal.zza(obj.hashCode());
            while (true) {
                int i2 = iZza & length;
                int i3 = bArr[i2] & 255;
                if (i3 == 255) {
                    v = null;
                    break;
                }
                if (obj.equals(objArr[i3])) {
                    v = (V) objArr[i3 ^ 1];
                    break;
                }
                iZza = i2 + 1;
            }
        } else if (obj2 instanceof short[]) {
            short[] sArr = (short[]) obj2;
            int length2 = sArr.length - 1;
            int iZza2 = zzal.zza(obj.hashCode());
            while (true) {
                int i4 = iZza2 & length2;
                int i5 = sArr[i4] & UShort.MAX_VALUE;
                if (i5 == 65535) {
                    v = null;
                    break;
                }
                if (obj.equals(objArr[i5])) {
                    v = (V) objArr[i5 ^ 1];
                    break;
                }
                iZza2 = i4 + 1;
            }
        } else {
            int[] iArr = (int[]) obj2;
            int length3 = iArr.length - 1;
            int iZza3 = zzal.zza(obj.hashCode());
            while (true) {
                int i6 = iZza3 & length3;
                int i7 = iArr[i6];
                if (i7 == -1) {
                    v = null;
                    break;
                }
                if (obj.equals(objArr[i7])) {
                    v = (V) objArr[i7 ^ 1];
                    break;
                }
                iZza3 = i6 + 1;
            }
        }
        if (v == null) {
            return null;
        }
        return v;
    }

    private zzaw(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzb = obj;
        this.zzc = objArr;
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzat
    final boolean zzd() {
        return false;
    }
}
