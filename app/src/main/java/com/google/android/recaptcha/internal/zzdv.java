package com.google.android.recaptcha.internal;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzdv {
    public static boolean zza(Collection collection, Iterator it) {
        if (it == null) {
            throw null;
        }
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= collection.add(it.next());
        }
        return zAdd;
    }
}
