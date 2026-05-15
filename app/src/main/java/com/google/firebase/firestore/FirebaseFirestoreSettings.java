package com.google.firebase.firestore;

import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Preconditions;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public final class FirebaseFirestoreSettings {
    public static final long CACHE_SIZE_UNLIMITED = -1;
    static final long DEFAULT_CACHE_SIZE_BYTES = 104857600;
    public static final String DEFAULT_HOST = "firestore.googleapis.com";
    static final long MINIMUM_CACHE_BYTES = 1048576;
    private LocalCacheSettings cacheSettings;
    private final long cacheSizeBytes;
    private final String host;
    private final boolean persistenceEnabled;
    private final boolean sslEnabled;

    public static final class Builder {
        private LocalCacheSettings cacheSettings;
        private long cacheSizeBytes;
        private String host;
        private boolean persistenceEnabled;
        private boolean sslEnabled;
        private boolean usedLegacyCacheSettings;

        public Builder() {
            this.usedLegacyCacheSettings = false;
            this.host = FirebaseFirestoreSettings.DEFAULT_HOST;
            this.sslEnabled = true;
            this.persistenceEnabled = true;
            this.cacheSizeBytes = FirebaseFirestoreSettings.DEFAULT_CACHE_SIZE_BYTES;
        }

        public Builder(FirebaseFirestoreSettings settings) {
            this.usedLegacyCacheSettings = false;
            Preconditions.checkNotNull(settings, "Provided settings must not be null.");
            this.host = settings.host;
            this.sslEnabled = settings.sslEnabled;
            this.persistenceEnabled = settings.persistenceEnabled;
            this.cacheSizeBytes = settings.cacheSizeBytes;
            if (!this.persistenceEnabled || this.cacheSizeBytes != FirebaseFirestoreSettings.DEFAULT_CACHE_SIZE_BYTES) {
                this.usedLegacyCacheSettings = true;
            }
            if (!this.usedLegacyCacheSettings) {
                this.cacheSettings = settings.cacheSettings;
            } else {
                Assert.hardAssert(settings.cacheSettings == null, "Given settings object mixes both cache config APIs, which is impossible.", new Object[0]);
            }
        }

        public Builder setHost(String host) {
            this.host = (String) Preconditions.checkNotNull(host, "Provided host must not be null.");
            return this;
        }

        public Builder setSslEnabled(boolean value) {
            this.sslEnabled = value;
            return this;
        }

        @Deprecated
        public Builder setPersistenceEnabled(boolean value) {
            if (this.cacheSettings != null) {
                throw new IllegalStateException("New cache config API setLocalCacheSettings() is already used.");
            }
            this.persistenceEnabled = value;
            this.usedLegacyCacheSettings = true;
            return this;
        }

        @Deprecated
        public Builder setCacheSizeBytes(long value) {
            if (this.cacheSettings != null) {
                throw new IllegalStateException("New cache config API setLocalCacheSettings() is already used.");
            }
            if (value != -1 && value < FirebaseFirestoreSettings.MINIMUM_CACHE_BYTES) {
                throw new IllegalArgumentException("Cache size must be set to at least 1048576 bytes");
            }
            this.cacheSizeBytes = value;
            this.usedLegacyCacheSettings = true;
            return this;
        }

        public Builder setLocalCacheSettings(LocalCacheSettings settings) {
            if (this.usedLegacyCacheSettings) {
                throw new IllegalStateException("Deprecated setPersistenceEnabled() or setCacheSizeBytes() is already used, remove those first.");
            }
            if (!(settings instanceof MemoryCacheSettings) && !(settings instanceof PersistentCacheSettings)) {
                throw new IllegalArgumentException("Only MemoryCacheSettings and PersistentCacheSettings are accepted");
            }
            this.cacheSettings = settings;
            return this;
        }

        public String getHost() {
            return this.host;
        }

        public boolean isSslEnabled() {
            return this.sslEnabled;
        }

        @Deprecated
        public boolean isPersistenceEnabled() {
            return this.persistenceEnabled;
        }

        @Deprecated
        public long getCacheSizeBytes() {
            return this.cacheSizeBytes;
        }

        public FirebaseFirestoreSettings build() {
            if (!this.sslEnabled && this.host.equals(FirebaseFirestoreSettings.DEFAULT_HOST)) {
                throw new IllegalStateException("You can't set the 'sslEnabled' setting unless you also set a non-default 'host'.");
            }
            return new FirebaseFirestoreSettings(this);
        }
    }

    private FirebaseFirestoreSettings(Builder builder) {
        this.host = builder.host;
        this.sslEnabled = builder.sslEnabled;
        this.persistenceEnabled = builder.persistenceEnabled;
        this.cacheSizeBytes = builder.cacheSizeBytes;
        this.cacheSettings = builder.cacheSettings;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirebaseFirestoreSettings that = (FirebaseFirestoreSettings) o;
        if (this.sslEnabled != that.sslEnabled || this.persistenceEnabled != that.persistenceEnabled || this.cacheSizeBytes != that.cacheSizeBytes || !this.host.equals(that.host)) {
            return false;
        }
        return Objects.equals(this.cacheSettings, that.cacheSettings);
    }

    public int hashCode() {
        return (((((((this.host.hashCode() * 31) + (this.sslEnabled ? 1 : 0)) * 31) + (this.persistenceEnabled ? 1 : 0)) * 31) + ((int) (this.cacheSizeBytes ^ (this.cacheSizeBytes >>> 32)))) * 31) + (this.cacheSettings != null ? this.cacheSettings.hashCode() : 0);
    }

    public String toString() {
        if (("FirebaseFirestoreSettings{host=" + this.host + ", sslEnabled=" + this.sslEnabled + ", persistenceEnabled=" + this.persistenceEnabled + ", cacheSizeBytes=" + this.cacheSizeBytes + ", cacheSettings=" + this.cacheSettings) == null) {
            return "null";
        }
        return this.cacheSettings.toString() + "}";
    }

    public String getHost() {
        return this.host;
    }

    public boolean isSslEnabled() {
        return this.sslEnabled;
    }

    @Deprecated
    public boolean isPersistenceEnabled() {
        if (this.cacheSettings != null) {
            return this.cacheSettings instanceof PersistentCacheSettings;
        }
        return this.persistenceEnabled;
    }

    @Deprecated
    public long getCacheSizeBytes() {
        if (this.cacheSettings != null) {
            if (this.cacheSettings instanceof PersistentCacheSettings) {
                return ((PersistentCacheSettings) this.cacheSettings).getSizeBytes();
            }
            MemoryCacheSettings memorySettings = (MemoryCacheSettings) this.cacheSettings;
            if (memorySettings.getGarbageCollectorSettings() instanceof MemoryLruGcSettings) {
                return ((MemoryLruGcSettings) memorySettings.getGarbageCollectorSettings()).getSizeBytes();
            }
            return -1L;
        }
        return this.cacheSizeBytes;
    }

    @Nullable
    public LocalCacheSettings getCacheSettings() {
        return this.cacheSettings;
    }
}
