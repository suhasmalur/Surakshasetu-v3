package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public class zzaji extends IOException {
    private zzakn zza;
    private boolean zzb;

    static zzajl zza() {
        return new zzajl("Protocol message tag had invalid wire type.");
    }

    static zzaji zzb() {
        return new zzaji("Protocol message end-group tag did not match expected tag.");
    }

    static zzaji zzc() {
        return new zzaji("Protocol message contained an invalid tag (zero).");
    }

    static zzaji zzd() {
        return new zzaji("Protocol message had invalid UTF-8.");
    }

    static zzaji zze() {
        return new zzaji("CodedInputStream encountered a malformed varint.");
    }

    static zzaji zzf() {
        return new zzaji("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzaji zzg() {
        return new zzaji("Failed to parse the message.");
    }

    public final zzaji zza(zzakn zzaknVar) {
        this.zza = zzaknVar;
        return this;
    }

    static zzaji zzh() {
        return new zzaji("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    static zzaji zzi() {
        return new zzaji("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public zzaji(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.zza = null;
    }

    public zzaji(String str) {
        super(str);
        this.zza = null;
    }

    final void zzj() {
        this.zzb = true;
    }

    final boolean zzk() {
        return this.zzb;
    }
}
