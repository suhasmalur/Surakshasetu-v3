package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
class zzahz extends zzahw {
    protected final byte[] zzb;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahp
    public byte zza(int i) {
        return this.zzb[i];
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahp
    byte zzb(int i) {
        return this.zzb[i];
    }

    protected int zzh() {
        return 0;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahp
    protected final int zzb(int i, int i2, int i3) {
        return zzajf.zza(i, this.zzb, zzh(), i3);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahp
    public int zzb() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahp
    public final zzahp zza(int i, int i2) {
        int iZza = zza(0, i2, zzb());
        if (iZza == 0) {
            return zzahp.zza;
        }
        return new zzaht(this.zzb, zzh(), iZza);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahp
    public final zzaia zzc() {
        return zzaia.zza(this.zzb, zzh(), zzb(), true);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahp
    protected final String zza(Charset charset) {
        return new String(this.zzb, zzh(), zzb(), charset);
    }

    zzahz(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzb = bArr;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahp
    protected void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzb, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahp
    final void zza(zzahm zzahmVar) throws IOException {
        zzahmVar.zza(this.zzb, zzh(), zzb());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahp
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzahp) || zzb() != ((zzahp) obj).zzb()) {
            return false;
        }
        if (zzb() == 0) {
            return true;
        }
        if (obj instanceof zzahz) {
            zzahz zzahzVar = (zzahz) obj;
            int iZza = zza();
            int iZza2 = zzahzVar.zza();
            if (iZza == 0 || iZza2 == 0 || iZza == iZza2) {
                return zza(zzahzVar, 0, zzb());
            }
            return false;
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahw
    final boolean zza(zzahp zzahpVar, int i, int i2) {
        if (i2 > zzahpVar.zzb()) {
            throw new IllegalArgumentException("Length too large: " + i2 + zzb());
        }
        if (i2 > zzahpVar.zzb()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + i2 + ", " + zzahpVar.zzb());
        }
        if (zzahpVar instanceof zzahz) {
            zzahz zzahzVar = (zzahz) zzahpVar;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzahzVar.zzb;
            int iZzh = zzh() + i2;
            int iZzh2 = zzh();
            int iZzh3 = zzahzVar.zzh();
            while (iZzh2 < iZzh) {
                if (bArr[iZzh2] != bArr2[iZzh3]) {
                    return false;
                }
                iZzh2++;
                iZzh3++;
            }
            return true;
        }
        return zzahpVar.zza(0, i2).equals(zza(0, i2));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahp
    public final boolean zzf() {
        int iZzh = zzh();
        return zzaml.zzc(this.zzb, iZzh, zzb() + iZzh);
    }
}
