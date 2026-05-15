package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Header;
import io.grpc.okhttp.internal.framed.Settings;
import java.util.EnumMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.ByteString;

/* JADX INFO: loaded from: classes10.dex */
class OkHttpFrameLogger {
    private static final int BUFFER_LENGTH_THRESHOLD = 64;
    private final Level level;
    private final Logger logger;

    enum Direction {
        INBOUND,
        OUTBOUND
    }

    OkHttpFrameLogger(Level level, Class<?> clazz) {
        this(level, Logger.getLogger(clazz.getName()));
    }

    OkHttpFrameLogger(Level level, Logger logger) {
        this.level = (Level) Preconditions.checkNotNull(level, "level");
        this.logger = (Logger) Preconditions.checkNotNull(logger, "logger");
    }

    private static String toString(Settings settings) {
        EnumMap<SettingParams, Integer> map = new EnumMap<>(SettingParams.class);
        for (SettingParams p : SettingParams.values()) {
            if (settings.isSet(p.getBit())) {
                map.put(p, Integer.valueOf(settings.get(p.getBit())));
            }
        }
        return map.toString();
    }

    private static String toString(Buffer buf) {
        if (buf.size() <= 64) {
            return buf.snapshot().hex();
        }
        int length = (int) Math.min(buf.size(), 64L);
        return buf.snapshot(length).hex() + "...";
    }

    private boolean isEnabled() {
        return this.logger.isLoggable(this.level);
    }

    void logData(Direction direction, int streamId, Buffer data, int length, boolean endStream) {
        if (isEnabled()) {
            this.logger.log(this.level, direction + " DATA: streamId=" + streamId + " endStream=" + endStream + " length=" + length + " bytes=" + toString(data));
        }
    }

    void logHeaders(Direction direction, int streamId, List<Header> headers, boolean endStream) {
        if (isEnabled()) {
            this.logger.log(this.level, direction + " HEADERS: streamId=" + streamId + " headers=" + headers + " endStream=" + endStream);
        }
    }

    public void logPriority(Direction direction, int streamId, int streamDependency, int weight, boolean exclusive) {
        if (isEnabled()) {
            this.logger.log(this.level, direction + " PRIORITY: streamId=" + streamId + " streamDependency=" + streamDependency + " weight=" + weight + " exclusive=" + exclusive);
        }
    }

    void logRstStream(Direction direction, int streamId, ErrorCode errorCode) {
        if (isEnabled()) {
            this.logger.log(this.level, direction + " RST_STREAM: streamId=" + streamId + " errorCode=" + errorCode);
        }
    }

    void logSettingsAck(Direction direction) {
        if (isEnabled()) {
            this.logger.log(this.level, direction + " SETTINGS: ack=true");
        }
    }

    void logSettings(Direction direction, Settings settings) {
        if (isEnabled()) {
            this.logger.log(this.level, direction + " SETTINGS: ack=false settings=" + toString(settings));
        }
    }

    void logPing(Direction direction, long data) {
        if (isEnabled()) {
            this.logger.log(this.level, direction + " PING: ack=false bytes=" + data);
        }
    }

    void logPingAck(Direction direction, long data) {
        if (isEnabled()) {
            this.logger.log(this.level, direction + " PING: ack=true bytes=" + data);
        }
    }

    void logPushPromise(Direction direction, int streamId, int promisedStreamId, List<Header> headers) {
        if (isEnabled()) {
            this.logger.log(this.level, direction + " PUSH_PROMISE: streamId=" + streamId + " promisedStreamId=" + promisedStreamId + " headers=" + headers);
        }
    }

    void logGoAway(Direction direction, int lastStreamId, ErrorCode errorCode, ByteString debugData) {
        if (isEnabled()) {
            this.logger.log(this.level, direction + " GO_AWAY: lastStreamId=" + lastStreamId + " errorCode=" + errorCode + " length=" + debugData.size() + " bytes=" + toString(new Buffer().write(debugData)));
        }
    }

    void logWindowsUpdate(Direction direction, int streamId, long windowSizeIncrement) {
        if (isEnabled()) {
            this.logger.log(this.level, direction + " WINDOW_UPDATE: streamId=" + streamId + " windowSizeIncrement=" + windowSizeIncrement);
        }
    }

    private enum SettingParams {
        HEADER_TABLE_SIZE(1),
        ENABLE_PUSH(2),
        MAX_CONCURRENT_STREAMS(4),
        MAX_FRAME_SIZE(5),
        MAX_HEADER_LIST_SIZE(6),
        INITIAL_WINDOW_SIZE(7);

        private final int bit;

        SettingParams(int bit) {
            this.bit = bit;
        }

        public int getBit() {
            return this.bit;
        }
    }
}
