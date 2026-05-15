package com.google.android.recaptcha.internal;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzdx extends zzea {
    final char[] zza;

    zzdx(String str, String str2) {
        zzdw zzdwVar = new zzdw("base16()", "0123456789ABCDEF".toCharArray());
        super(zzdwVar, null);
        this.zza = new char[512];
        zzdi.zza(zzdwVar.zzf.length == 16);
        for (int i = 0; i < 256; i++) {
            this.zza[i] = zzdwVar.zza(i >>> 4);
            this.zza[i | 256] = zzdwVar.zza(i & 15);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzea, com.google.android.recaptcha.internal.zzeb
    final int zza(byte[] bArr, CharSequence charSequence) throws zzdz {
        if (bArr == null) {
            throw null;
        }
        if (charSequence.length() % 2 == 1) {
            throw new zzdz("Invalid input length " + charSequence.length());
        }
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            bArr[i2] = (byte) ((this.zzb.zzb(charSequence.charAt(i)) << 4) | this.zzb.zzb(charSequence.charAt(i + 1)));
            i += 2;
            i2++;
        }
        return i2;
    }

    @Override // com.google.android.recaptcha.internal.zzea, com.google.android.recaptcha.internal.zzeb
    final void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzdi.zzd(0, i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = bArr[i3] & 255;
            appendable.append(this.zza[i4]);
            appendable.append(this.zza[i4 | 256]);
        }
    }
}
