package io.grpc.okhttp;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;
import javax.annotation.Nullable;
import okio.Buffer;

/* JADX INFO: loaded from: classes10.dex */
class OutboundFlowController {
    private final FrameWriter frameWriter;
    private final Transport transport;
    private int initialWindowSize = 65535;
    private final StreamState connectionState = new StreamState(0, 65535, null);

    public interface Stream {
        void onSentBytes(int i);
    }

    public interface Transport {
        StreamState[] getActiveStreams();
    }

    public OutboundFlowController(Transport transport, FrameWriter frameWriter) {
        this.transport = (Transport) Preconditions.checkNotNull(transport, NotificationCompat.CATEGORY_TRANSPORT);
        this.frameWriter = (FrameWriter) Preconditions.checkNotNull(frameWriter, "frameWriter");
    }

    public boolean initialOutboundWindowSize(int newWindowSize) {
        if (newWindowSize < 0) {
            throw new IllegalArgumentException("Invalid initial window size: " + newWindowSize);
        }
        int delta = newWindowSize - this.initialWindowSize;
        this.initialWindowSize = newWindowSize;
        for (StreamState state : this.transport.getActiveStreams()) {
            state.incrementStreamWindow(delta);
        }
        return delta > 0;
    }

    public int windowUpdate(@Nullable StreamState state, int delta) {
        if (state == null) {
            int updatedWindow = this.connectionState.incrementStreamWindow(delta);
            writeStreams();
            return updatedWindow;
        }
        int updatedWindow2 = state.incrementStreamWindow(delta);
        WriteStatus writeStatus = new WriteStatus();
        state.writeBytes(state.writableWindow(), writeStatus);
        if (writeStatus.hasWritten()) {
            flush();
            return updatedWindow2;
        }
        return updatedWindow2;
    }

    public void data(boolean outFinished, StreamState state, Buffer source, boolean flush) {
        Preconditions.checkNotNull(source, Constants.ScionAnalytics.PARAM_SOURCE);
        int window = state.writableWindow();
        boolean framesAlreadyQueued = state.hasPendingData();
        int size = (int) source.size();
        if (!framesAlreadyQueued && window >= size) {
            state.write(source, size, outFinished);
        } else {
            if (!framesAlreadyQueued && window > 0) {
                state.write(source, window, false);
            }
            state.enqueueData(source, (int) source.size(), outFinished);
        }
        if (flush) {
            flush();
        }
    }

    public void notifyWhenNoPendingData(StreamState state, Runnable noPendingDataRunnable) {
        Preconditions.checkNotNull(noPendingDataRunnable, "noPendingDataRunnable");
        if (state.hasPendingData()) {
            state.notifyWhenNoPendingData(noPendingDataRunnable);
        } else {
            noPendingDataRunnable.run();
        }
    }

    public void flush() {
        try {
            this.frameWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StreamState createState(Stream stream, int streamId) {
        return new StreamState(streamId, this.initialWindowSize, (Stream) Preconditions.checkNotNull(stream, "stream"));
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public void writeStreams() {
        /*
            r9 = this;
            io.grpc.okhttp.OutboundFlowController$Transport r0 = r9.transport
            io.grpc.okhttp.OutboundFlowController$StreamState[] r0 = r0.getActiveStreams()
            java.util.List r1 = java.util.Arrays.asList(r0)
            java.util.Collections.shuffle(r1)
            io.grpc.okhttp.OutboundFlowController$StreamState r1 = r9.connectionState
            int r1 = r1.window()
            int r2 = r0.length
        L14:
            if (r2 <= 0) goto L4b
            if (r1 <= 0) goto L4b
            r3 = 0
            float r4 = (float) r1
            float r5 = (float) r2
            float r4 = r4 / r5
            double r4 = (double) r4
            double r4 = java.lang.Math.ceil(r4)
            int r4 = (int) r4
            r5 = 0
        L23:
            if (r5 >= r2) goto L49
            if (r1 <= 0) goto L49
            r6 = r0[r5]
            int r7 = r6.unallocatedBytes()
            int r7 = java.lang.Math.min(r7, r4)
            int r7 = java.lang.Math.min(r1, r7)
            if (r7 <= 0) goto L3b
            r6.allocateBytes(r7)
            int r1 = r1 - r7
        L3b:
            int r8 = r6.unallocatedBytes()
            if (r8 <= 0) goto L46
            int r8 = r3 + 1
            r0[r3] = r6
            r3 = r8
        L46:
            int r5 = r5 + 1
            goto L23
        L49:
            r2 = r3
            goto L14
        L4b:
            io.grpc.okhttp.OutboundFlowController$WriteStatus r2 = new io.grpc.okhttp.OutboundFlowController$WriteStatus
            r3 = 0
            r2.<init>()
            io.grpc.okhttp.OutboundFlowController$Transport r3 = r9.transport
            io.grpc.okhttp.OutboundFlowController$StreamState[] r3 = r3.getActiveStreams()
            int r4 = r3.length
            r5 = 0
        L59:
            if (r5 >= r4) goto L6a
            r6 = r3[r5]
            int r7 = r6.allocatedBytes()
            r6.writeBytes(r7, r2)
            r6.clearAllocatedBytes()
            int r5 = r5 + 1
            goto L59
        L6a:
            boolean r3 = r2.hasWritten()
            if (r3 == 0) goto L73
            r9.flush()
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OutboundFlowController.writeStreams():void");
    }

    private static final class WriteStatus {
        int numWrites;

        private WriteStatus() {
        }

        void incrementNumWrites() {
            this.numWrites++;
        }

        boolean hasWritten() {
            return this.numWrites > 0;
        }
    }

    public final class StreamState {
        private int allocatedBytes;
        private Runnable noPendingDataRunnable;
        private final Stream stream;
        private final int streamId;
        private int window;
        private final Buffer pendingWriteBuffer = new Buffer();
        private boolean pendingBufferHasEndOfStream = false;

        StreamState(int streamId, int initialWindowSize, Stream stream) {
            this.streamId = streamId;
            this.window = initialWindowSize;
            this.stream = stream;
        }

        int window() {
            return this.window;
        }

        void allocateBytes(int bytes) {
            this.allocatedBytes += bytes;
        }

        int allocatedBytes() {
            return this.allocatedBytes;
        }

        int unallocatedBytes() {
            return streamableBytes() - this.allocatedBytes;
        }

        void clearAllocatedBytes() {
            this.allocatedBytes = 0;
        }

        int incrementStreamWindow(int delta) {
            if (delta > 0 && Integer.MAX_VALUE - delta < this.window) {
                throw new IllegalArgumentException("Window size overflow for stream: " + this.streamId);
            }
            this.window += delta;
            return this.window;
        }

        int writableWindow() {
            return Math.min(this.window, OutboundFlowController.this.connectionState.window());
        }

        int streamableBytes() {
            return Math.max(0, Math.min(this.window, (int) this.pendingWriteBuffer.size()));
        }

        boolean hasPendingData() {
            return this.pendingWriteBuffer.size() > 0;
        }

        int writeBytes(int bytes, WriteStatus writeStatus) {
            int bytesAttempted = 0;
            int maxBytes = Math.min(bytes, writableWindow());
            while (hasPendingData() && maxBytes > 0) {
                if (maxBytes >= this.pendingWriteBuffer.size()) {
                    bytesAttempted += (int) this.pendingWriteBuffer.size();
                    write(this.pendingWriteBuffer, (int) this.pendingWriteBuffer.size(), this.pendingBufferHasEndOfStream);
                } else {
                    bytesAttempted += maxBytes;
                    write(this.pendingWriteBuffer, maxBytes, false);
                }
                writeStatus.incrementNumWrites();
                maxBytes = Math.min(bytes - bytesAttempted, writableWindow());
            }
            if (!hasPendingData() && this.noPendingDataRunnable != null) {
                this.noPendingDataRunnable.run();
                this.noPendingDataRunnable = null;
            }
            return bytesAttempted;
        }

        void write(Buffer buffer, int bytesToSend, boolean endOfStream) {
            int bytesToWrite = bytesToSend;
            do {
                int frameBytes = Math.min(bytesToWrite, OutboundFlowController.this.frameWriter.maxDataLength());
                OutboundFlowController.this.connectionState.incrementStreamWindow(-frameBytes);
                incrementStreamWindow(-frameBytes);
                try {
                    boolean isEndOfStream = buffer.size() == ((long) frameBytes) && endOfStream;
                    OutboundFlowController.this.frameWriter.data(isEndOfStream, this.streamId, buffer, frameBytes);
                    this.stream.onSentBytes(frameBytes);
                    bytesToWrite -= frameBytes;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } while (bytesToWrite > 0);
        }

        void enqueueData(Buffer buffer, int size, boolean endOfStream) {
            this.pendingWriteBuffer.write(buffer, size);
            this.pendingBufferHasEndOfStream |= endOfStream;
        }

        void notifyWhenNoPendingData(Runnable noPendingDataRunnable) {
            Preconditions.checkState(this.noPendingDataRunnable == null, "pending data notification already requested");
            this.noPendingDataRunnable = noPendingDataRunnable;
        }
    }
}
