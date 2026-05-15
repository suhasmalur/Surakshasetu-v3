package com.google.android.recaptcha.internal;

import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzdy extends zzea {
    zzdy(String str, String str2, @CheckForNull Character ch) {
        zzdw zzdwVar = new zzdw(str, str2.toCharArray());
        super(zzdwVar, ch);
        zzdi.zza(zzdwVar.zzf.length == 64);
    }

    @Override // com.google.android.recaptcha.internal.zzea, com.google.android.recaptcha.internal.zzeb
    final int zza(byte[] bArr, CharSequence charSequence) throws zzdz {
        if (bArr == null) {
            throw null;
        }
        CharSequence charSequenceZze = zze(charSequence);
        if (!this.zzb.zzc(charSequenceZze.length())) {
            throw new zzdz("Invalid input length " + charSequenceZze.length());
        }
        int i = 0;
        int i2 = 0;
        while (i < charSequenceZze.length()) {
            int i3 = i + 1;
            int i4 = i2 + 1;
            int iZzb = (this.zzb.zzb(charSequenceZze.charAt(i)) << 18) | (this.zzb.zzb(charSequenceZze.charAt(i3)) << 12);
            bArr[i2] = (byte) (iZzb >>> 16);
            int i5 = i3 + 1;
            if (i5 < charSequenceZze.length()) {
                int i6 = i5 + 1;
                int iZzb2 = iZzb | (this.zzb.zzb(charSequenceZze.charAt(i5)) << 6);
                i2 = i4 + 1;
                bArr[i4] = (byte) ((iZzb2 >>> 8) & 255);
                if (i6 < charSequenceZze.length()) {
                    bArr[i2] = (byte) ((iZzb2 | this.zzb.zzb(charSequenceZze.charAt(i6))) & 255);
                    i2++;
                    i = i6 + 1;
                } else {
                    i = i6;
                }
            } else {
                i = i5;
                i2 = i4;
            }
        }
        return i2;
    }

    @Override // com.google.android.recaptcha.internal.zzea, com.google.android.recaptcha.internal.zzeb
    final void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzdi.zzd(0, i2, bArr.length);
        for (int i4 = i2; i4 >= 3; i4 -= 3) {
            int i5 = i3 + 1;
            int i6 = bArr[i3] & 255;
            int i7 = bArr[i5] & 255;
            int i8 = i5 + 1;
            int i9 = (i6 << 16) | (i7 << 8) | (bArr[i8] & 255);
            appendable.append(this.zzb.zza(i9 >>> 18));
            appendable.append(this.zzb.zza((i9 >>> 12) & 63));
            appendable.append(this.zzb.zza((i9 >>> 6) & 63));
            appendable.append(this.zzb.zza(i9 & 63));
            i3 = i8 + 1;
        }
        if (i3 < i2) {
            zzf(appendable, bArr, i3, i2 - i3);
        }
    }
}
