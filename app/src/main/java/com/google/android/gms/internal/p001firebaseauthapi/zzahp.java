package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzahp implements Serializable, Iterable<Byte> {
    public static final zzahp zza = new zzahz(zzajf.zzb);
    private static final zzahs zzb = new zzahy();
    private static final Comparator<zzahp> zzc = new zzahr();
    private int zzd = 0;

    static /* synthetic */ int zza(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract byte zza(int i);

    public abstract zzahp zza(int i, int i2);

    protected abstract String zza(Charset charset);

    abstract void zza(zzahm zzahmVar) throws IOException;

    protected abstract void zza(byte[] bArr, int i, int i2, int i3);

    abstract byte zzb(int i);

    public abstract int zzb();

    protected abstract int zzb(int i, int i2, int i3);

    public abstract zzaia zzc();

    public abstract boolean zzf();

    static int zza(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) < 0) {
            if (i < 0) {
                throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
            }
            if (i2 < i) {
                throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
            }
            throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
        }
        return i4;
    }

    public final int hashCode() {
        int iZzb = this.zzd;
        if (iZzb == 0) {
            int iZzb2 = zzb();
            iZzb = zzb(iZzb2, 0, iZzb2);
            if (iZzb == 0) {
                iZzb = 1;
            }
            this.zzd = iZzb;
        }
        return iZzb;
    }

    protected final int zza() {
        return this.zzd;
    }

    static zzahu zzc(int i) {
        return new zzahu(i);
    }

    public static zzahp zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    public static zzahp zza(byte[] bArr, int i, int i2) {
        zza(i, i + i2, bArr.length);
        return new zzahz(zzb.zza(bArr, i, i2));
    }

    public static zzahp zza(String str) {
        return new zzahz(str.getBytes(zzajf.zza));
    }

    static zzahp zzb(byte[] bArr) {
        return new zzahz(bArr);
    }

    public final String toString() {
        String strZza;
        Locale locale = Locale.ROOT;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        Integer numValueOf = Integer.valueOf(zzb());
        if (zzb() <= 50) {
            strZza = zzalw.zza(this);
        } else {
            strZza = zzalw.zza(zza(0, 47)) + "...";
        }
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", hexString, numValueOf, strZza);
    }

    public final String zzd() {
        return zzb() == 0 ? "" : zza(zzajf.zza);
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzaho(this);
    }

    zzahp() {
    }

    public final boolean zze() {
        return zzb() == 0;
    }

    public final byte[] zzg() {
        int iZzb = zzb();
        if (iZzb == 0) {
            return zzajf.zzb;
        }
        byte[] bArr = new byte[iZzb];
        zza(bArr, 0, 0, iZzb);
        return bArr;
    }
}
