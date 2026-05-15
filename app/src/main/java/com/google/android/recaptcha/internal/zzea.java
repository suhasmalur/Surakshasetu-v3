package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.math.RoundingMode;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
class zzea extends zzeb {
    final zzdw zzb;

    @CheckForNull
    final Character zzc;

    zzea(zzdw zzdwVar, @CheckForNull Character ch) {
        this.zzb = zzdwVar;
        boolean z = true;
        if (ch != null) {
            ch.charValue();
            if (zzdwVar.zzd('=')) {
                z = false;
            }
        }
        if (!z) {
            throw new IllegalArgumentException(zzdl.zza("Padding character %s was already in alphabet", ch));
        }
        this.zzc = ch;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzea) {
            zzea zzeaVar = (zzea) obj;
            if (this.zzb.equals(zzeaVar.zzb)) {
                Character ch = this.zzc;
                Character ch2 = zzeaVar.zzc;
                if (ch == ch2) {
                    return true;
                }
                if (ch != null && ch.equals(ch2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = this.zzb.hashCode();
        Character ch = this.zzc;
        return iHashCode ^ (ch == null ? 0 : ch.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.zzb);
        if (8 % this.zzb.zzb != 0) {
            if (this.zzc == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.zzc);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    @Override // com.google.android.recaptcha.internal.zzeb
    int zza(byte[] bArr, CharSequence charSequence) throws zzdz {
        zzdw zzdwVar;
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
            long jZzb = 0;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                zzdwVar = this.zzb;
                if (i3 >= zzdwVar.zzc) {
                    break;
                }
                jZzb <<= zzdwVar.zzb;
                if (i + i3 < charSequenceZze.length()) {
                    jZzb |= (long) this.zzb.zzb(charSequenceZze.charAt(i4 + i));
                    i4++;
                }
                i3++;
            }
            int i5 = zzdwVar.zzd;
            int i6 = i5 * 8;
            int i7 = i4 * zzdwVar.zzb;
            int i8 = (i5 - 1) * 8;
            while (i8 >= i6 - i7) {
                bArr[i2] = (byte) ((jZzb >>> i8) & 255);
                i8 -= 8;
                i2++;
            }
            i += this.zzb.zzc;
        }
        return i2;
    }

    @Override // com.google.android.recaptcha.internal.zzeb
    void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzdi.zzd(0, i2, bArr.length);
        while (i3 < i2) {
            zzf(appendable, bArr, i3, Math.min(this.zzb.zzd, i2 - i3));
            i3 += this.zzb.zzd;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzeb
    final int zzc(int i) {
        return (int) (((((long) this.zzb.zzb) * ((long) i)) + 7) / 8);
    }

    @Override // com.google.android.recaptcha.internal.zzeb
    final int zzd(int i) {
        zzdw zzdwVar = this.zzb;
        return zzdwVar.zzc * zzed.zza(i, zzdwVar.zzd, RoundingMode.CEILING);
    }

    @Override // com.google.android.recaptcha.internal.zzeb
    final CharSequence zze(CharSequence charSequence) {
        if (charSequence == null) {
            throw null;
        }
        Character ch = this.zzc;
        if (ch == null) {
            return charSequence;
        }
        ch.charValue();
        int length = charSequence.length() - 1;
        while (length >= 0 && charSequence.charAt(length) == '=') {
            length--;
        }
        return charSequence.subSequence(0, length + 1);
    }

    final void zzf(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzdi.zzd(i, i + i2, bArr.length);
        int i3 = 0;
        zzdi.zza(i2 <= this.zzb.zzd);
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | ((long) (bArr[i + i4] & 255))) << 8;
        }
        int i5 = ((i2 + 1) * 8) - this.zzb.zzb;
        while (i3 < i2 * 8) {
            zzdw zzdwVar = this.zzb;
            appendable.append(zzdwVar.zza(zzdwVar.zza & ((int) (j >>> (i5 - i3)))));
            i3 += this.zzb.zzb;
        }
        if (this.zzc != null) {
            while (i3 < this.zzb.zzd * 8) {
                this.zzc.charValue();
                appendable.append('=');
                i3 += this.zzb.zzb;
            }
        }
    }

    zzea(String str, String str2, @CheckForNull Character ch) {
        this(new zzdw(str, str2.toCharArray()), ch);
    }
}
