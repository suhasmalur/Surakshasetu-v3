package com.google.firebase.firestore.remote;

import android.util.Base64;
import com.google.protobuf.ByteString;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes10.dex */
public final class BloomFilter {
    private final int bitCount;
    private final ByteString bitmap;
    private final int hashCount;
    private final MessageDigest md5HashMessageDigest;

    public BloomFilter(ByteString bitmap, int padding, int hashCount) {
        if (padding < 0 || padding >= 8) {
            throw new IllegalArgumentException("Invalid padding: " + padding);
        }
        if (hashCount < 0) {
            throw new IllegalArgumentException("Invalid hash count: " + hashCount);
        }
        if (bitmap.size() > 0 && hashCount == 0) {
            throw new IllegalArgumentException("Invalid hash count: " + hashCount);
        }
        if (bitmap.size() == 0 && padding != 0) {
            throw new IllegalArgumentException("Expected padding of 0 when bitmap length is 0, but got " + padding);
        }
        this.bitmap = bitmap;
        this.hashCount = hashCount;
        this.bitCount = (bitmap.size() * 8) - padding;
        this.md5HashMessageDigest = createMd5HashMessageDigest();
    }

    public static BloomFilter create(ByteString bitmap, int padding, int hashCount) throws BloomFilterCreateException {
        if (padding < 0 || padding >= 8) {
            throw new BloomFilterCreateException("Invalid padding: " + padding);
        }
        if (hashCount < 0) {
            throw new BloomFilterCreateException("Invalid hash count: " + hashCount);
        }
        if (bitmap.size() > 0 && hashCount == 0) {
            throw new BloomFilterCreateException("Invalid hash count: " + hashCount);
        }
        if (bitmap.size() == 0 && padding != 0) {
            throw new BloomFilterCreateException("Expected padding of 0 when bitmap length is 0, but got " + padding);
        }
        return new BloomFilter(bitmap, padding, hashCount);
    }

    public static final class BloomFilterCreateException extends Exception {
        public BloomFilterCreateException(String message) {
            super(message);
        }
    }

    int getBitCount() {
        return this.bitCount;
    }

    public boolean mightContain(String value) {
        if (this.bitCount == 0) {
            return false;
        }
        byte[] hashedValue = md5HashDigest(value);
        if (hashedValue.length != 16) {
            throw new RuntimeException("Invalid md5 hash array length: " + hashedValue.length + " (expected 16)");
        }
        long hash1 = getLongLittleEndian(hashedValue, 0);
        long hash2 = getLongLittleEndian(hashedValue, 8);
        for (int i = 0; i < this.hashCount; i++) {
            int index = getBitIndex(hash1, hash2, i);
            if (!isBitSet(index)) {
                return false;
            }
        }
        return true;
    }

    private byte[] md5HashDigest(String value) {
        return this.md5HashMessageDigest.digest(value.getBytes(StandardCharsets.UTF_8));
    }

    private static MessageDigest createMd5HashMessageDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Missing MD5 MessageDigest provider: ", e);
        }
    }

    private static long getLongLittleEndian(byte[] bytes, int offset) {
        long result = 0;
        for (int i = 0; i < 8; i++) {
            result |= (((long) bytes[offset + i]) & 255) << (i * 8);
        }
        return result;
    }

    private int getBitIndex(long hash1, long hash2, int hashIndex) {
        long combinedHash = (((long) hashIndex) * hash2) + hash1;
        long modulo = unsignedRemainder(combinedHash, this.bitCount);
        return (int) modulo;
    }

    private static long unsignedRemainder(long dividend, long divisor) {
        long quotient = ((dividend >>> 1) / divisor) << 1;
        long remainder = dividend - (quotient * divisor);
        return remainder - (remainder >= divisor ? divisor : 0L);
    }

    private boolean isBitSet(int index) {
        byte byteAtIndex = this.bitmap.byteAt(index / 8);
        int offset = index % 8;
        return ((1 << offset) & byteAtIndex) != 0;
    }

    public String toString() {
        return "BloomFilter{hashCount=" + this.hashCount + ", size=" + this.bitCount + ", bitmap=\"" + Base64.encodeToString(this.bitmap.toByteArray(), 2) + "\"}";
    }
}
