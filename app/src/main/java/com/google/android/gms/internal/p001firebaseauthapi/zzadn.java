package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.common.net.HttpHeaders;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.json.JSONException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzadn {
    private static void zza(HttpURLConnection httpURLConnection, zzadk<?> zzadkVar, Type type) {
        try {
            try {
                int responseCode = httpURLConnection.getResponseCode();
                InputStream inputStream = zza(responseCode) ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream();
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        } else {
                            sb.append(line);
                        }
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th2) {
                            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                        }
                        throw th;
                    }
                }
                bufferedReader.close();
                String string = sb.toString();
                if (zza(responseCode)) {
                    zzadkVar.zza((zzacs) zzacq.zza(string, type));
                } else {
                    zzadkVar.zza((String) zzacq.zza(string, String.class));
                }
                httpURLConnection.disconnect();
            } catch (zzaaf e) {
                e = e;
                zzadkVar.zza(e.getMessage());
                httpURLConnection.disconnect();
            } catch (SocketTimeoutException e2) {
                zzadkVar.zza("TIMEOUT");
                httpURLConnection.disconnect();
            } catch (IOException e3) {
                e = e3;
                zzadkVar.zza(e.getMessage());
                httpURLConnection.disconnect();
            }
        } catch (Throwable th3) {
            httpURLConnection.disconnect();
            throw th3;
        }
    }

    public static void zza(String str, zzadk<?> zzadkVar, Type type, zzact zzactVar) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(60000);
            zzactVar.zza(httpURLConnection);
            zza(httpURLConnection, zzadkVar, type);
        } catch (SocketTimeoutException e) {
            zzadkVar.zza("TIMEOUT");
        } catch (UnknownHostException e2) {
            zzadkVar.zza("<<Network Error>>");
        } catch (IOException e3) {
            zzadkVar.zza(e3.getMessage());
        }
    }

    public static void zza(String str, zzacp zzacpVar, zzadk<?> zzadkVar, Type type, zzact zzactVar) throws IllegalAccessException, InvocationTargetException {
        try {
            Preconditions.checkNotNull(zzacpVar);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            byte[] bytes = zzacpVar.zza().getBytes(Charset.defaultCharset());
            httpURLConnection.setFixedLengthStreamingMode(bytes.length);
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
            httpURLConnection.setConnectTimeout(60000);
            zzactVar.zza(httpURLConnection);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream(), bytes.length);
            try {
                bufferedOutputStream.write(bytes, 0, bytes.length);
                bufferedOutputStream.close();
                zza(httpURLConnection, zzadkVar, type);
            } catch (Throwable th) {
                try {
                    bufferedOutputStream.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
                throw th;
            }
        } catch (NullPointerException e) {
            e = e;
            zzadkVar.zza(e.getMessage());
        } catch (SocketTimeoutException e2) {
            zzadkVar.zza("TIMEOUT");
        } catch (UnknownHostException e3) {
            zzadkVar.zza("<<Network Error>>");
        } catch (IOException e4) {
            e = e4;
            zzadkVar.zza(e.getMessage());
        } catch (JSONException e5) {
            e = e5;
            zzadkVar.zza(e.getMessage());
        }
    }

    private static final boolean zza(int i) {
        return i >= 200 && i < 300;
    }
}
