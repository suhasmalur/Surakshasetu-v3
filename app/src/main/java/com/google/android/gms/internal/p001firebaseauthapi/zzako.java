package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzajc;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.text.Typography;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzako {
    private static final char[] zza;

    static String zza(zzakn zzaknVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(str);
        zza(zzaknVar, sb, 0);
        return sb.toString();
    }

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, ' ');
    }

    private static void zza(int i, StringBuilder sb) {
        int length;
        while (i > 0) {
            if (i <= zza.length) {
                length = i;
            } else {
                length = zza.length;
            }
            sb.append(zza, 0, length);
            i -= length;
        }
    }

    static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zza(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zza(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        zza(i, sb);
        if (!str.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Character.toLowerCase(str.charAt(0)));
            for (int i2 = 1; i2 < str.length(); i2++) {
                char cCharAt = str.charAt(i2);
                if (Character.isUpperCase(cCharAt)) {
                    sb2.append("_");
                }
                sb2.append(Character.toLowerCase(cCharAt));
            }
            str = sb2.toString();
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"").append(zzalw.zza(zzahp.zza((String) obj))).append(Typography.quote);
            return;
        }
        if (obj instanceof zzahp) {
            sb.append(": \"").append(zzalw.zza((zzahp) obj)).append(Typography.quote);
            return;
        }
        if (obj instanceof zzajc) {
            sb.append(" {");
            zza((zzajc) obj, sb, i + 2);
            sb.append("\n");
            zza(i, sb);
            sb.append("}");
            return;
        }
        if (obj instanceof Map.Entry) {
            sb.append(" {");
            Map.Entry entry = (Map.Entry) obj;
            int i3 = i + 2;
            zza(sb, i3, "key", entry.getKey());
            zza(sb, i3, "value", entry.getValue());
            sb.append("\n");
            zza(i, sb);
            sb.append("}");
            return;
        }
        sb.append(": ").append(obj);
    }

    private static void zza(zzakn zzaknVar, StringBuilder sb, int i) {
        int i2;
        int i3;
        boolean zBooleanValue;
        boolean zEquals;
        Method method;
        HashSet hashSet = new HashSet();
        HashMap map = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = zzaknVar.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i4 = 0;
        while (true) {
            i2 = 3;
            if (i4 >= length) {
                break;
            }
            Method method2 = declaredMethods[i4];
            if (!Modifier.isStatic(method2.getModifiers()) && method2.getName().length() >= 3) {
                if (method2.getName().startsWith("set")) {
                    hashSet.add(method2.getName());
                } else if (Modifier.isPublic(method2.getModifiers()) && method2.getParameterTypes().length == 0) {
                    if (method2.getName().startsWith("has")) {
                        map.put(method2.getName(), method2);
                    } else if (method2.getName().startsWith("get")) {
                        treeMap.put(method2.getName(), method2);
                    }
                }
            }
            i4++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String strSubstring = ((String) entry.getKey()).substring(i2);
            if (strSubstring.endsWith("List") && !strSubstring.endsWith("OrBuilderList") && !strSubstring.equals("List") && (method = (Method) entry.getValue()) != null && method.getReturnType().equals(List.class)) {
                zza(sb, i, strSubstring.substring(0, strSubstring.length() - 4), zzajc.zza(method, zzaknVar, new Object[0]));
                i2 = 3;
            } else {
                if (!strSubstring.endsWith("Map")) {
                    i3 = 3;
                } else if (strSubstring.equals("Map")) {
                    i3 = 3;
                } else {
                    Method method3 = (Method) entry.getValue();
                    if (method3 == null) {
                        i3 = 3;
                    } else if (!method3.getReturnType().equals(Map.class)) {
                        i3 = 3;
                    } else if (method3.isAnnotationPresent(Deprecated.class)) {
                        i3 = 3;
                    } else if (!Modifier.isPublic(method3.getModifiers())) {
                        i3 = 3;
                    } else {
                        zza(sb, i, strSubstring.substring(0, strSubstring.length() - 3), zzajc.zza(method3, zzaknVar, new Object[0]));
                        i2 = 3;
                    }
                }
                if (!hashSet.contains("set" + strSubstring)) {
                    i2 = i3;
                } else {
                    if (strSubstring.endsWith("Bytes")) {
                        if (treeMap.containsKey("get" + strSubstring.substring(0, strSubstring.length() - 5))) {
                            i2 = i3;
                        }
                    }
                    Method method4 = (Method) entry.getValue();
                    Method method5 = (Method) map.get("has" + strSubstring);
                    if (method4 != null) {
                        Object objZza = zzajc.zza(method4, zzaknVar, new Object[0]);
                        if (method5 != null) {
                            zBooleanValue = ((Boolean) zzajc.zza(method5, zzaknVar, new Object[0])).booleanValue();
                        } else {
                            zBooleanValue = true;
                            if (objZza instanceof Boolean) {
                                zEquals = !((Boolean) objZza).booleanValue();
                            } else if (objZza instanceof Integer) {
                                zEquals = ((Integer) objZza).intValue() == 0;
                            } else if (objZza instanceof Float) {
                                zEquals = Float.floatToRawIntBits(((Float) objZza).floatValue()) == 0;
                            } else if (objZza instanceof Double) {
                                zEquals = Double.doubleToRawLongBits(((Double) objZza).doubleValue()) == 0;
                            } else if (objZza instanceof String) {
                                zEquals = objZza.equals("");
                            } else if (objZza instanceof zzahp) {
                                zEquals = objZza.equals(zzahp.zza);
                            } else if (objZza instanceof zzakn) {
                                zEquals = objZza == ((zzakn) objZza).zzh();
                            } else {
                                zEquals = (objZza instanceof Enum) && ((Enum) objZza).ordinal() == 0;
                            }
                            if (zEquals) {
                                zBooleanValue = false;
                            }
                        }
                        if (!zBooleanValue) {
                            i2 = i3;
                        } else {
                            zza(sb, i, strSubstring, objZza);
                            i2 = i3;
                        }
                    } else {
                        i2 = i3;
                    }
                }
            }
        }
        if (zzaknVar instanceof zzajc.zzb) {
            Iterator<Map.Entry<T, Object>> itZzd = ((zzajc.zzb) zzaknVar).zzc.zzd();
            if (itZzd.hasNext()) {
                throw new NoSuchMethodError();
            }
        }
        zzajc zzajcVar = (zzajc) zzaknVar;
        if (zzajcVar.zzb != null) {
            zzajcVar.zzb.zza(sb, i);
        }
    }
}
