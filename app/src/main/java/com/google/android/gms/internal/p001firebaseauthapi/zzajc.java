package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.firebase-auth-api.zzajc.zza;
import com.google.android.gms.internal.p001firebaseauthapi.zzajc;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzajc<MessageType extends zzajc<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzahf<MessageType, BuilderType> {
    private static Map<Object, zzajc<?, ?>> zzc = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzamd zzb = zzamd.zzc();

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static abstract class zza<MessageType extends zzajc<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzahe<MessageType, BuilderType> {
        protected MessageType zza;
        private final MessageType zzb;

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahe
        /* JADX INFO: renamed from: zzc */
        public final /* synthetic */ zzahe clone() {
            return (zza) clone();
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb.equals(messagetype)) {
                return this;
            }
            if (!this.zza.zzu()) {
                zzj();
            }
            zza(this.zza, messagetype);
            return this;
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakm
        /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
        public final MessageType zzf() {
            MessageType messagetype = (MessageType) zzg();
            if (!messagetype.zzk()) {
                throw new zzamc(messagetype);
            }
            return messagetype;
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakm
        /* JADX INFO: renamed from: zze, reason: merged with bridge method [inline-methods] */
        public MessageType zzg() {
            if (!this.zza.zzu()) {
                return this.zza;
            }
            this.zza.zzs();
            return this.zza;
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakp
        public final /* synthetic */ zzakn zzh() {
            return this.zzb;
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahe
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zzaVar = (zza) this.zzb.zza(zzf.zze, null, null);
            zzaVar.zza = (MessageType) zzg();
            return zzaVar;
        }

        protected zza(MessageType messagetype) {
            this.zzb = messagetype;
            if (messagetype.zzu()) {
                throw new IllegalArgumentException("Default instance must be immutable.");
            }
            this.zza = (MessageType) this.zzb.zzo();
        }

        protected final void zzi() {
            if (!this.zza.zzu()) {
                zzj();
            }
        }

        protected void zzj() {
            MessageType messagetype = (MessageType) this.zzb.zzo();
            zza(messagetype, this.zza);
            this.zza = messagetype;
        }

        private static <MessageType> void zza(MessageType messagetype, MessageType messagetype2) {
            zzalb.zza().zza(messagetype).zza(messagetype, messagetype2);
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakp
        public final boolean zzk() {
            return zzajc.zza(this.zza, false);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static abstract class zzb<MessageType extends zzb<MessageType, BuilderType>, BuilderType> extends zzajc<MessageType, BuilderType> implements zzakp {
        protected zzaiv<zze> zzc = zzaiv.zzb();

        final zzaiv<zze> zza() {
            if (this.zzc.zzf()) {
                this.zzc = (zzaiv) this.zzc.clone();
            }
            return this.zzc;
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    protected static class zzc<T extends zzajc<T, ?>> extends zzahg<T> {
        private final T zza;

        public zzc(T t) {
            this.zza = t;
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public static class zzd<ContainingType extends zzakn, Type> extends zzaip<ContainingType, Type> {
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    static final class zze implements zzaix<zze> {
        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaix
        public final int zza() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaix
        public final zzakm zza(zzakm zzakmVar, zzakn zzaknVar) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaix
        public final zzaks zza(zzaks zzaksVar, zzaks zzaksVar2) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaix
        public final zzamr zzb() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaix
        public final zzanb zzc() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaix
        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaix
        public final boolean zze() {
            throw new NoSuchMethodError();
        }
    }

    private final int zza() {
        return zzalb.zza().zza(this).zzb(this);
    }

    protected abstract Object zza(int i, Object obj, Object obj2);

    private final int zzb(zzalf<?> zzalfVar) {
        return zzalfVar == null ? zzalb.zza().zza(this).zza(this) : zzalfVar.zza(this);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahf
    final int a_() {
        return this.zzd & Integer.MAX_VALUE;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakn
    public final int zzl() {
        return zza((zzalf) null);
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
    public enum zzf {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        private static final /* synthetic */ int[] zzh = {zza, zzb, zzc, zzd, zze, zzf, zzg};

        public static int[] zza() {
            return (int[]) zzh.clone();
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahf
    final int zza(zzalf zzalfVar) {
        if (zzu()) {
            int iZzb = zzb((zzalf<?>) zzalfVar);
            if (iZzb < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + iZzb);
            }
            return iZzb;
        }
        if (a_() != Integer.MAX_VALUE) {
            return a_();
        }
        int iZzb2 = zzb((zzalf<?>) zzalfVar);
        zzb(iZzb2);
        return iZzb2;
    }

    public int hashCode() {
        if (zzu()) {
            return zza();
        }
        if (this.zza == 0) {
            this.zza = zza();
        }
        return this.zza;
    }

    protected final <MessageType extends zzajc<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzm() {
        return (BuilderType) zza(zzf.zze, (Object) null, (Object) null);
    }

    public final BuilderType zzn() {
        return (BuilderType) ((zza) zza(zzf.zze, (Object) null, (Object) null)).zza(this);
    }

    private static <T extends zzajc<T, ?>> T zza(T t) throws zzaji {
        if (t != null && !t.zzk()) {
            throw new zzamc(t).zza().zza(t);
        }
        return t;
    }

    static <T extends zzajc<?, ?>> T zza(Class<T> cls) {
        zzajc<?, ?> zzajcVar = zzc.get(cls);
        if (zzajcVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzajcVar = zzc.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzajcVar == null) {
            zzajcVar = (T) ((zzajc) zzamk.zza(cls)).zza(zzf.zzf, (Object) null, (Object) null);
            if (zzajcVar == null) {
                throw new IllegalStateException();
            }
            zzc.put(cls, zzajcVar);
        }
        return (T) zzajcVar;
    }

    final MessageType zzo() {
        return (MessageType) zza(zzf.zzd, (Object) null, (Object) null);
    }

    protected static <T extends zzajc<T, ?>> T zza(T t, zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        return (T) zza(zzb(t, zzahpVar, zzaioVar));
    }

    protected static <T extends zzajc<T, ?>> T zza(T t, InputStream inputStream, zzaio zzaioVar) throws zzaji {
        zzaia zzaifVar;
        if (inputStream == null) {
            byte[] bArr = zzajf.zzb;
            zzaifVar = zzaia.zza(bArr, 0, bArr.length, false);
        } else {
            zzaifVar = new zzaif(inputStream);
        }
        return (T) zza(zza(t, zzaifVar, zzaioVar));
    }

    protected static <T extends zzajc<T, ?>> T zza(T t, byte[] bArr, zzaio zzaioVar) throws zzaji {
        return (T) zza(zza(t, bArr, 0, bArr.length, zzaioVar));
    }

    private static <T extends zzajc<T, ?>> T zzb(T t, zzahp zzahpVar, zzaio zzaioVar) throws zzaji {
        zzaia zzaiaVarZzc = zzahpVar.zzc();
        T t2 = (T) zza(t, zzaiaVarZzc, zzaioVar);
        try {
            zzaiaVarZzc.zzc(0);
            return t2;
        } catch (zzaji e) {
            throw e.zza(t2);
        }
    }

    private static <T extends zzajc<T, ?>> T zza(T t, zzaia zzaiaVar, zzaio zzaioVar) throws zzaji {
        T t2 = (T) t.zzo();
        try {
            zzalf zzalfVarZza = zzalb.zza().zza(t2);
            zzalfVarZza.zza(t2, zzaij.zza(zzaiaVar), zzaioVar);
            zzalfVarZza.zzc(t2);
            return t2;
        } catch (zzaji e) {
            e = e;
            if (e.zzk()) {
                e = new zzaji(e);
            }
            throw e.zza(t2);
        } catch (zzamc e2) {
            throw e2.zza().zza(t2);
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzaji) {
                throw ((zzaji) e3.getCause());
            }
            throw new zzaji(e3).zza(t2);
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof zzaji) {
                throw ((zzaji) e4.getCause());
            }
            throw e4;
        }
    }

    private static <T extends zzajc<T, ?>> T zza(T t, byte[] bArr, int i, int i2, zzaio zzaioVar) throws zzaji {
        T t2 = (T) t.zzo();
        try {
            zzalf zzalfVarZza = zzalb.zza().zza(t2);
            zzalfVarZza.zza(t2, bArr, 0, i2, new zzahk(zzaioVar));
            zzalfVarZza.zzc(t2);
            return t2;
        } catch (zzaji e) {
            e = e;
            if (e.zzk()) {
                e = new zzaji(e);
            }
            throw e.zza(t2);
        } catch (zzamc e2) {
            throw e2.zza().zza(t2);
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzaji) {
                throw ((zzaji) e3.getCause());
            }
            throw new zzaji(e3).zza(t2);
        } catch (IndexOutOfBoundsException e4) {
            throw zzaji.zzi().zza(t2);
        }
    }

    protected static <E> zzajj<E> zzp() {
        return zzala.zzd();
    }

    protected static <E> zzajj<E> zza(zzajj<E> zzajjVar) {
        int size = zzajjVar.size();
        return zzajjVar.zza(size == 0 ? 10 : size << 1);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakn
    public final /* synthetic */ zzakm zzq() {
        return (zza) zza(zzf.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakn
    public final /* synthetic */ zzakm zzr() {
        return ((zza) zza(zzf.zze, (Object) null, (Object) null)).zza(this);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakp
    public final /* synthetic */ zzakn zzh() {
        return (zzajc) zza(zzf.zzf, (Object) null, (Object) null);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static Object zza(zzakn zzaknVar, String str, Object[] objArr) {
        return new zzald(zzaknVar, str, objArr);
    }

    public String toString() {
        return zzako.zza(this, super.toString());
    }

    protected final void zzs() {
        zzalb.zza().zza(this).zzc(this);
        zzt();
    }

    final void zzt() {
        this.zzd &= Integer.MAX_VALUE;
    }

    protected static <T extends zzajc<?, ?>> void zza(Class<T> cls, T t) {
        t.zzt();
        zzc.put(cls, t);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzahf
    final void zzb(int i) {
        if (i < 0) {
            throw new IllegalStateException("serialized size must be non-negative, was " + i);
        }
        this.zzd = (i & Integer.MAX_VALUE) | (this.zzd & Integer.MIN_VALUE);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakn
    public final void zza(zzaik zzaikVar) throws IOException {
        zzalb.zza().zza(this).zza(this, zzain.zza(zzaikVar));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzalb.zza().zza(this).zzb(this, (zzajc) obj);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzakp
    public final boolean zzk() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    protected static final <T extends zzajc<T, ?>> boolean zza(T t, boolean z) {
        byte bByteValue = ((Byte) t.zza(zzf.zza, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzd = zzalb.zza().zza(t).zzd(t);
        if (z) {
            t.zza(zzf.zzb, zZzd ? t : null, null);
        }
        return zZzd;
    }

    final boolean zzu() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }
}
