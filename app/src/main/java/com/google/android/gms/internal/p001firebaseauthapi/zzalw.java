package com.google.android.gms.internal.p001firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzalw {
    static String zza(zzahp zzahpVar) {
        zzalz zzalzVar = new zzalz(zzahpVar);
        StringBuilder sb = new StringBuilder(zzalzVar.zza());
        for (int i = 0; i < zzalzVar.zza(); i++) {
            byte bZza = zzalzVar.zza(i);
            switch (bZza) {
                case 7:
                    sb.append("\\a");
                    break;
                case 8:
                    sb.append("\\b");
                    break;
                case 9:
                    sb.append("\\t");
                    break;
                case 10:
                    sb.append("\\n");
                    break;
                case 11:
                    sb.append("\\v");
                    break;
                case 12:
                    sb.append("\\f");
                    break;
                case 13:
                    sb.append("\\r");
                    break;
                case 34:
                    sb.append("\\\"");
                    break;
                case 39:
                    sb.append("\\'");
                    break;
                case 92:
                    sb.append("\\\\");
                    break;
                default:
                    if (bZza < 32 || bZza > 126) {
                        sb.append('\\');
                        sb.append((char) (((bZza >>> 6) & 3) + 48));
                        sb.append((char) (((bZza >>> 3) & 7) + 48));
                        sb.append((char) ((bZza & 7) + 48));
                    } else {
                        sb.append((char) bZza);
                    }
                    break;
            }
        }
        return sb.toString();
    }
}
