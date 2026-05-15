package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* JADX INFO: loaded from: classes10.dex */
final class zzja {
    static String zza(zzez zzezVar) {
        StringBuilder sb = new StringBuilder(zzezVar.zzd());
        for (int i = 0; i < zzezVar.zzd(); i++) {
            byte bZza = zzezVar.zza(i);
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
