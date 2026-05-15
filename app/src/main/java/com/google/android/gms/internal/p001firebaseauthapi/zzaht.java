package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzaht extends zzahz {
    private final int zzc;
    private final int zzd;

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahz, com.google.android.gms.internal.p001firebaseauthapi.zzahp
    public final byte zza(int i) {
        int iZzb = zzb();
        if (((iZzb - (i + 1)) | i) >= 0) {
            return this.zzb[this.zzc + i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + iZzb);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahz, com.google.android.gms.internal.p001firebaseauthapi.zzahp
    final byte zzb(int i) {
        return this.zzb[this.zzc + i];
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahz
    protected final int zzh() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahz, com.google.android.gms.internal.p001firebaseauthapi.zzahp
    public final int zzb() {
        return this.zzd;
    }

    zzaht(byte[] bArr, int i, int i2) {
        super(bArr);
        zza(i, i + i2, bArr.length);
        this.zzc = i;
        this.zzd = i2;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahz, com.google.android.gms.internal.p001firebaseauthapi.zzahp
    protected final void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzb, zzh(), bArr, 0, i3);
    }
}
