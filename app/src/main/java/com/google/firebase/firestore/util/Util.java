package com.google.firebase.firestore.util;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.cloud.datastore.core.number.NumberComparisonHelper;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes10.dex */
public class Util {
    private static final String AUTO_ID_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int AUTO_ID_LENGTH = 20;
    private static final Random rand = new SecureRandom();
    private static final Continuation<Void, Void> VOID_ERROR_TRANSFORMER = new Continuation() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda1
        @Override // com.google.android.gms.tasks.Continuation
        public final Object then(Task task) {
            return Util.lambda$static$0(task);
        }
    };

    public static String autoId() {
        StringBuilder builder = new StringBuilder();
        int maxRandom = AUTO_ID_ALPHABET.length();
        for (int i = 0; i < 20; i++) {
            builder.append(AUTO_ID_ALPHABET.charAt(rand.nextInt(maxRandom)));
        }
        return builder.toString();
    }

    public static int compareBooleans(boolean b1, boolean b2) {
        if (b1 == b2) {
            return 0;
        }
        if (b1) {
            return 1;
        }
        return -1;
    }

    public static int compareIntegers(int i1, int i2) {
        if (i1 < i2) {
            return -1;
        }
        if (i1 > i2) {
            return 1;
        }
        return 0;
    }

    public static int compareLongs(long i1, long i2) {
        return NumberComparisonHelper.compareLongs(i1, i2);
    }

    public static int compareDoubles(double i1, double i2) {
        return NumberComparisonHelper.firestoreCompareDoubles(i1, i2);
    }

    public static int compareMixed(double doubleValue, long longValue) {
        return NumberComparisonHelper.firestoreCompareDoubleWithLong(doubleValue, longValue);
    }

    public static <T extends Comparable<T>> Comparator<T> comparator() {
        return new Comparator() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda4
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Comparable) obj).compareTo((Comparable) obj2);
            }
        };
    }

    public static FirebaseFirestoreException exceptionFromStatus(Status error) {
        StatusException statusException = error.asException();
        return new FirebaseFirestoreException(statusException.getMessage(), FirebaseFirestoreException.Code.fromValue(error.getCode().value()), statusException);
    }

    private static Exception convertStatusException(Exception e) {
        if (e instanceof StatusException) {
            StatusException statusException = (StatusException) e;
            return exceptionFromStatus(statusException.getStatus());
        }
        if (e instanceof StatusRuntimeException) {
            StatusRuntimeException statusRuntimeException = (StatusRuntimeException) e;
            return exceptionFromStatus(statusRuntimeException.getStatus());
        }
        return e;
    }

    public static Exception convertThrowableToException(Throwable t) {
        if (t instanceof Exception) {
            return convertStatusException((Exception) t);
        }
        return new Exception(t);
    }

    static /* synthetic */ Void lambda$static$0(Task task) throws Exception {
        if (task.isSuccessful()) {
            return (Void) task.getResult();
        }
        Exception e = convertStatusException(task.getException());
        if (e instanceof FirebaseFirestoreException) {
            throw e;
        }
        throw new FirebaseFirestoreException(e.getMessage(), FirebaseFirestoreException.Code.UNKNOWN, e);
    }

    public static Continuation<Void, Void> voidErrorTransformer() {
        return VOID_ERROR_TRANSFORMER;
    }

    public static List<Object> collectUpdateArguments(int fieldPathOffset, Object field, Object val, Object... fieldsAndValues) {
        if (fieldsAndValues.length % 2 == 1) {
            throw new IllegalArgumentException("Missing value in call to update().  There must be an even number of arguments that alternate between field names and values");
        }
        List<Object> argumentList = new ArrayList<>();
        argumentList.add(field);
        argumentList.add(val);
        Collections.addAll(argumentList, fieldsAndValues);
        for (int i = 0; i < argumentList.size(); i += 2) {
            Object fieldPath = argumentList.get(i);
            if (!(fieldPath instanceof String) && !(fieldPath instanceof FieldPath)) {
                throw new IllegalArgumentException("Excepted field name at argument position " + (i + fieldPathOffset + 1) + " but got " + fieldPath + " in call to update.  The arguments to update should alternate between field names and values");
            }
        }
        return argumentList;
    }

    public static String toDebugString(ByteString bytes) {
        int size = bytes.size();
        StringBuilder result = new StringBuilder(size * 2);
        for (int i = 0; i < size; i++) {
            int value = bytes.byteAt(i) & 255;
            result.append(Character.forDigit(value >>> 4, 16));
            result.append(Character.forDigit(value & 15, 16));
        }
        return result.toString();
    }

    public static String typeName(Object obj) {
        return obj == null ? "null" : obj.getClass().getName();
    }

    public static void crashMainThread(final RuntimeException exception) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Util.lambda$crashMainThread$1(exception);
            }
        });
    }

    static /* synthetic */ void lambda$crashMainThread$1(RuntimeException exception) {
        throw exception;
    }

    public static int compareByteArrays(byte[] left, byte[] right) {
        int size = Math.min(left.length, right.length);
        for (int i = 0; i < size; i++) {
            int thisByte = left[i] & 255;
            int otherByte = right[i] & 255;
            if (thisByte < otherByte) {
                return -1;
            }
            if (thisByte > otherByte) {
                return 1;
            }
        }
        int i2 = left.length;
        return compareIntegers(i2, right.length);
    }

    public static int compareByteStrings(ByteString left, ByteString right) {
        int size = Math.min(left.size(), right.size());
        for (int i = 0; i < size; i++) {
            int thisByte = left.byteAt(i) & 255;
            int otherByte = right.byteAt(i) & 255;
            if (thisByte < otherByte) {
                return -1;
            }
            if (thisByte > otherByte) {
                return 1;
            }
        }
        int i2 = left.size();
        return compareIntegers(i2, right.size());
    }

    public static StringBuilder repeatSequence(CharSequence sequence, int count, CharSequence delimiter) {
        StringBuilder sb = new StringBuilder();
        if (count != 0) {
            sb.append(sequence);
            for (int i = 1; i < count; i++) {
                sb.append(delimiter);
                sb.append(sequence);
            }
        }
        return sb;
    }

    public static <T> void diffCollections(Collection<T> before, Collection<T> after, Comparator<T> comparator, Consumer<T> onAdd, Consumer<T> onRemove) {
        List<T> beforeEntries = new ArrayList<>((Collection<? extends T>) before);
        Collections.sort(beforeEntries, comparator);
        List<T> afterEntries = new ArrayList<>((Collection<? extends T>) after);
        Collections.sort(afterEntries, comparator);
        diffCollections(beforeEntries.iterator(), afterEntries.iterator(), comparator, onAdd, onRemove);
    }

    public static <T extends Comparable<T>> void diffCollections(SortedSet<T> before, SortedSet<T> after, Consumer<T> onAdd, Consumer<T> onRemove) {
        diffCollections(before.iterator(), after.iterator(), before.comparator() != null ? before.comparator() : new Comparator() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda2
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Comparable) obj).compareTo((Comparable) obj2);
            }
        }, onAdd, onRemove);
    }

    private static <T> void diffCollections(Iterator<T> it, Iterator<T> it2, Comparator<? super T> comparator, Consumer<T> consumer, Consumer<T> consumer2) {
        T t = (Object) advanceIterator(it);
        T t2 = (Object) advanceIterator(it2);
        while (true) {
            if (t != null || t2 != null) {
                boolean z = false;
                boolean z2 = false;
                if (t != null && t2 != null) {
                    int iCompare = comparator.compare(t, t2);
                    if (iCompare < 0) {
                        z2 = true;
                    } else if (iCompare > 0) {
                        z = true;
                    }
                } else if (t != null) {
                    z2 = true;
                } else {
                    z = true;
                }
                if (z) {
                    consumer.accept(t2);
                    t2 = (Object) advanceIterator(it2);
                } else if (z2) {
                    consumer2.accept(t);
                    t = (Object) advanceIterator(it);
                } else {
                    t = (Object) advanceIterator(it);
                    t2 = (Object) advanceIterator(it2);
                }
            } else {
                return;
            }
        }
    }

    private static <T> T advanceIterator(Iterator<T> it) {
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static <K, V> Iterable<V> values(final Iterable<Map.Entry<K, V>> map) {
        return new Iterable() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda5
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return Util.lambda$values$3(map);
            }
        };
    }

    static /* synthetic */ Iterator lambda$values$3(Iterable map) {
        final Iterator it = map.iterator();
        return new Iterator<V>() { // from class: com.google.firebase.firestore.util.Util.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                return (V) ((Map.Entry) it.next()).getValue();
            }
        };
    }

    public static <K, V> Map<K, V> firstNEntries(Map<K, V> data, int n, final Comparator<V> comp) {
        if (data.size() <= n) {
            return data;
        }
        List<Map.Entry<K, V>> sortedValues = new ArrayList<>(data.entrySet());
        Collections.sort(sortedValues, new Comparator() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda3
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return comp.compare(((Map.Entry) obj).getValue(), ((Map.Entry) obj2).getValue());
            }
        });
        Map<K, V> result = new HashMap<>();
        for (int i = 0; i < n; i++) {
            result.put(sortedValues.get(i).getKey(), sortedValues.get(i).getValue());
        }
        return result;
    }
}
