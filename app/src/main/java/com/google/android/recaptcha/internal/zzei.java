package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzeh;
import com.google.android.recaptcha.internal.zzei;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public abstract class zzei<MessageType extends zzei<MessageType, BuilderType>, BuilderType extends zzeh<MessageType, BuilderType>> implements zzhy {
    protected int zza = 0;

    /* JADX WARN: Multi-variable type inference failed */
    protected static void zzc(Iterable iterable, List list) {
        byte[] bArr = zzgw.zzd;
        if (iterable == 0) {
            throw null;
        }
        if (iterable instanceof zzhg) {
            List listZzh = ((zzhg) iterable).zzh();
            zzhg zzhgVar = (zzhg) list;
            int size = list.size();
            for (Object obj : listZzh) {
                if (obj == null) {
                    String str = "Element at index " + (zzhgVar.size() - size) + " is null.";
                    for (int size2 = zzhgVar.size() - 1; size2 >= size; size2--) {
                        zzhgVar.remove(size2);
                    }
                    throw new NullPointerException(str);
                }
                if (obj instanceof zzez) {
                    zzhgVar.zzi((zzez) obj);
                } else {
                    zzhgVar.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof zzig) {
            list.addAll(iterable);
            return;
        }
        if (list instanceof ArrayList) {
            ((ArrayList) list).ensureCapacity(list.size() + iterable.size());
        }
        int size3 = list.size();
        for (Object obj2 : iterable) {
            if (obj2 == null) {
                String str2 = "Element at index " + (list.size() - size3) + " is null.";
                for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                    list.remove(size4);
                }
                throw new NullPointerException(str2);
            }
            list.add(obj2);
        }
    }

    int zza(zzil zzilVar) {
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzhy
    public final zzez zzb() {
        try {
            int iZzn = zzn();
            zzez zzezVar = zzez.zzb;
            byte[] bArr = new byte[iZzn];
            zzfk zzfkVarZzA = zzfk.zzA(bArr, 0, iZzn);
            zze(zzfkVarZzA);
            zzfkVarZzA.zzB();
            return new zzew(bArr);
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public final byte[] zzd() {
        try {
            int iZzn = zzn();
            byte[] bArr = new byte[iZzn];
            zzfk zzfkVarZzA = zzfk.zzA(bArr, 0, iZzn);
            zze(zzfkVarZzA);
            zzfkVarZzA.zzB();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
