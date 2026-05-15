package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.ClientStreamTracer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
@CheckReturnValue
public final class CallOptions {
    public static final CallOptions DEFAULT;

    @Nullable
    private final String authority;

    @Nullable
    private final String compressorName;

    @Nullable
    private final CallCredentials credentials;
    private final Object[][] customOptions;

    @Nullable
    private final Deadline deadline;

    @Nullable
    private final Executor executor;

    @Nullable
    private final Integer maxInboundMessageSize;

    @Nullable
    private final Integer maxOutboundMessageSize;
    private final List<ClientStreamTracer.Factory> streamTracerFactories;

    @Nullable
    private final Boolean waitForReady;

    static {
        Builder b = new Builder();
        b.customOptions = (Object[][]) Array.newInstance((Class<?>) Object.class, 0, 2);
        b.streamTracerFactories = Collections.emptyList();
        DEFAULT = b.build();
    }

    private CallOptions(Builder builder) {
        this.deadline = builder.deadline;
        this.executor = builder.executor;
        this.authority = builder.authority;
        this.credentials = builder.credentials;
        this.compressorName = builder.compressorName;
        this.customOptions = builder.customOptions;
        this.streamTracerFactories = builder.streamTracerFactories;
        this.waitForReady = builder.waitForReady;
        this.maxInboundMessageSize = builder.maxInboundMessageSize;
        this.maxOutboundMessageSize = builder.maxOutboundMessageSize;
    }

    static class Builder {
        String authority;
        String compressorName;
        CallCredentials credentials;
        Object[][] customOptions;
        Deadline deadline;
        Executor executor;
        Integer maxInboundMessageSize;
        Integer maxOutboundMessageSize;
        List<ClientStreamTracer.Factory> streamTracerFactories;
        Boolean waitForReady;

        Builder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CallOptions build() {
            return new CallOptions(this);
        }
    }

    public CallOptions withAuthority(@Nullable String authority) {
        Builder builder = toBuilder(this);
        builder.authority = authority;
        return builder.build();
    }

    public CallOptions withCallCredentials(@Nullable CallCredentials credentials) {
        Builder builder = toBuilder(this);
        builder.credentials = credentials;
        return builder.build();
    }

    public CallOptions withCompression(@Nullable String compressorName) {
        Builder builder = toBuilder(this);
        builder.compressorName = compressorName;
        return builder.build();
    }

    public CallOptions withDeadline(@Nullable Deadline deadline) {
        Builder builder = toBuilder(this);
        builder.deadline = deadline;
        return builder.build();
    }

    public CallOptions withDeadlineAfter(long duration, TimeUnit unit) {
        return withDeadline(Deadline.after(duration, unit));
    }

    @Nullable
    public Deadline getDeadline() {
        return this.deadline;
    }

    public CallOptions withWaitForReady() {
        Builder builder = toBuilder(this);
        builder.waitForReady = Boolean.TRUE;
        return builder.build();
    }

    public CallOptions withoutWaitForReady() {
        Builder builder = toBuilder(this);
        builder.waitForReady = Boolean.FALSE;
        return builder.build();
    }

    @Nullable
    public String getCompressor() {
        return this.compressorName;
    }

    @Nullable
    public String getAuthority() {
        return this.authority;
    }

    @Nullable
    public CallCredentials getCredentials() {
        return this.credentials;
    }

    public CallOptions withExecutor(@Nullable Executor executor) {
        Builder builder = toBuilder(this);
        builder.executor = executor;
        return builder.build();
    }

    public CallOptions withStreamTracerFactory(ClientStreamTracer.Factory factory) {
        ArrayList<ClientStreamTracer.Factory> newList = new ArrayList<>(this.streamTracerFactories.size() + 1);
        newList.addAll(this.streamTracerFactories);
        newList.add(factory);
        Builder builder = toBuilder(this);
        builder.streamTracerFactories = Collections.unmodifiableList(newList);
        return builder.build();
    }

    public List<ClientStreamTracer.Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }

    public static final class Key<T> {
        private final String debugString;
        private final T defaultValue;

        private Key(String debugString, T defaultValue) {
            this.debugString = debugString;
            this.defaultValue = defaultValue;
        }

        public T getDefault() {
            return this.defaultValue;
        }

        public String toString() {
            return this.debugString;
        }

        @Deprecated
        public static <T> Key<T> of(String debugString, T defaultValue) {
            Preconditions.checkNotNull(debugString, "debugString");
            return new Key<>(debugString, defaultValue);
        }

        public static <T> Key<T> create(String debugString) {
            Preconditions.checkNotNull(debugString, "debugString");
            return new Key<>(debugString, null);
        }

        public static <T> Key<T> createWithDefault(String debugString, T defaultValue) {
            Preconditions.checkNotNull(debugString, "debugString");
            return new Key<>(debugString, defaultValue);
        }
    }

    public <T> CallOptions withOption(Key<T> key, T value) {
        Preconditions.checkNotNull(key, "key");
        Preconditions.checkNotNull(value, "value");
        Builder builder = toBuilder(this);
        int existingIdx = -1;
        int i = 0;
        while (true) {
            if (i >= this.customOptions.length) {
                break;
            }
            if (!key.equals(this.customOptions[i][0])) {
                i++;
            } else {
                existingIdx = i;
                break;
            }
        }
        builder.customOptions = (Object[][]) Array.newInstance((Class<?>) Object.class, this.customOptions.length + (existingIdx == -1 ? 1 : 0), 2);
        System.arraycopy(this.customOptions, 0, builder.customOptions, 0, this.customOptions.length);
        if (existingIdx == -1) {
            builder.customOptions[this.customOptions.length] = new Object[]{key, value};
        } else {
            builder.customOptions[existingIdx] = new Object[]{key, value};
        }
        return builder.build();
    }

    public <T> T getOption(Key<T> key) {
        Preconditions.checkNotNull(key, "key");
        for (int i = 0; i < this.customOptions.length; i++) {
            if (key.equals(this.customOptions[i][0])) {
                return (T) this.customOptions[i][1];
            }
        }
        return (T) ((Key) key).defaultValue;
    }

    @Nullable
    public Executor getExecutor() {
        return this.executor;
    }

    public boolean isWaitForReady() {
        return Boolean.TRUE.equals(this.waitForReady);
    }

    Boolean getWaitForReady() {
        return this.waitForReady;
    }

    public CallOptions withMaxInboundMessageSize(int maxSize) {
        Preconditions.checkArgument(maxSize >= 0, "invalid maxsize %s", maxSize);
        Builder builder = toBuilder(this);
        builder.maxInboundMessageSize = Integer.valueOf(maxSize);
        return builder.build();
    }

    public CallOptions withMaxOutboundMessageSize(int maxSize) {
        Preconditions.checkArgument(maxSize >= 0, "invalid maxsize %s", maxSize);
        Builder builder = toBuilder(this);
        builder.maxOutboundMessageSize = Integer.valueOf(maxSize);
        return builder.build();
    }

    @Nullable
    public Integer getMaxInboundMessageSize() {
        return this.maxInboundMessageSize;
    }

    @Nullable
    public Integer getMaxOutboundMessageSize() {
        return this.maxOutboundMessageSize;
    }

    private static Builder toBuilder(CallOptions other) {
        Builder builder = new Builder();
        builder.deadline = other.deadline;
        builder.executor = other.executor;
        builder.authority = other.authority;
        builder.credentials = other.credentials;
        builder.compressorName = other.compressorName;
        builder.customOptions = other.customOptions;
        builder.streamTracerFactories = other.streamTracerFactories;
        builder.waitForReady = other.waitForReady;
        builder.maxInboundMessageSize = other.maxInboundMessageSize;
        builder.maxOutboundMessageSize = other.maxOutboundMessageSize;
        return builder;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("deadline", this.deadline).add("authority", this.authority).add("callCredentials", this.credentials).add("executor", this.executor != null ? this.executor.getClass() : null).add("compressorName", this.compressorName).add("customOptions", Arrays.deepToString(this.customOptions)).add("waitForReady", isWaitForReady()).add("maxInboundMessageSize", this.maxInboundMessageSize).add("maxOutboundMessageSize", this.maxOutboundMessageSize).add("streamTracerFactories", this.streamTracerFactories).toString();
    }
}
