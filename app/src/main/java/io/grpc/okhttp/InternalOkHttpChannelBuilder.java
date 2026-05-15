package io.grpc.okhttp;

/* JADX INFO: loaded from: classes10.dex */
public final class InternalOkHttpChannelBuilder {
    public static void setStatsEnabled(OkHttpChannelBuilder builder, boolean value) {
        builder.setStatsEnabled(value);
    }

    public static void disableCheckAuthority(OkHttpChannelBuilder builder) {
        builder.disableCheckAuthority();
    }

    public static void enableCheckAuthority(OkHttpChannelBuilder builder) {
        builder.enableCheckAuthority();
    }

    private InternalOkHttpChannelBuilder() {
    }
}
