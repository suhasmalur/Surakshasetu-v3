package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import io.grpc.Codec;
import io.grpc.Decompressor;
import io.grpc.Status;
import io.grpc.internal.StreamListener;
import java.io.Closeable;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.zip.DataFormatException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public class MessageDeframer implements Closeable, Deframer {
    private static final int COMPRESSED_FLAG_MASK = 1;
    private static final int HEADER_LENGTH = 5;
    private static final int MAX_BUFFER_SIZE = 2097152;
    private static final int RESERVED_MASK = 254;
    private boolean compressedFlag;
    private Decompressor decompressor;
    private GzipInflatingBuffer fullStreamDecompressor;
    private int inboundBodyWireSize;
    private byte[] inflatedBuffer;
    private int inflatedIndex;
    private Listener listener;
    private int maxInboundMessageSize;
    private CompositeReadableBuffer nextFrame;
    private long pendingDeliveries;
    private final StatsTraceContext statsTraceCtx;
    private final TransportTracer transportTracer;
    private State state = State.HEADER;
    private int requiredLength = 5;
    private CompositeReadableBuffer unprocessed = new CompositeReadableBuffer();
    private boolean inDelivery = false;
    private int currentMessageSeqNo = -1;
    private boolean closeWhenComplete = false;
    private volatile boolean stopDelivery = false;

    public interface Listener {
        void bytesRead(int i);

        void deframeFailed(Throwable th);

        void deframerClosed(boolean z);

        void messagesAvailable(StreamListener.MessageProducer messageProducer);
    }

    private enum State {
        HEADER,
        BODY
    }

    public MessageDeframer(Listener listener, Decompressor decompressor, int maxMessageSize, StatsTraceContext statsTraceCtx, TransportTracer transportTracer) {
        this.listener = (Listener) Preconditions.checkNotNull(listener, "sink");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor, "decompressor");
        this.maxInboundMessageSize = maxMessageSize;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceCtx, "statsTraceCtx");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
    }

    void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override // io.grpc.internal.Deframer
    public void setMaxInboundMessageSize(int messageSize) {
        this.maxInboundMessageSize = messageSize;
    }

    @Override // io.grpc.internal.Deframer
    public void setDecompressor(Decompressor decompressor) {
        Preconditions.checkState(this.fullStreamDecompressor == null, "Already set full stream decompressor");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor, "Can't pass an empty decompressor");
    }

    @Override // io.grpc.internal.Deframer
    public void setFullStreamDecompressor(GzipInflatingBuffer fullStreamDecompressor) {
        Preconditions.checkState(this.decompressor == Codec.Identity.NONE, "per-message decompressor already set");
        Preconditions.checkState(this.fullStreamDecompressor == null, "full stream decompressor already set");
        this.fullStreamDecompressor = (GzipInflatingBuffer) Preconditions.checkNotNull(fullStreamDecompressor, "Can't pass a null full stream decompressor");
        this.unprocessed = null;
    }

    @Override // io.grpc.internal.Deframer
    public void request(int numMessages) {
        Preconditions.checkArgument(numMessages > 0, "numMessages must be > 0");
        if (isClosed()) {
            return;
        }
        this.pendingDeliveries += (long) numMessages;
        deliver();
    }

    @Override // io.grpc.internal.Deframer
    public void deframe(ReadableBuffer data) {
        Preconditions.checkNotNull(data, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        boolean needToCloseData = true;
        try {
            if (!isClosedOrScheduledToClose()) {
                if (this.fullStreamDecompressor != null) {
                    this.fullStreamDecompressor.addGzippedBytes(data);
                } else {
                    this.unprocessed.addBuffer(data);
                }
                needToCloseData = false;
                deliver();
            }
        } finally {
            if (needToCloseData) {
                data.close();
            }
        }
    }

    @Override // io.grpc.internal.Deframer
    public void closeWhenComplete() {
        if (isClosed()) {
            return;
        }
        if (isStalled()) {
            close();
        } else {
            this.closeWhenComplete = true;
        }
    }

    void stopDelivery() {
        this.stopDelivery = true;
    }

    boolean hasPendingDeliveries() {
        return this.pendingDeliveries != 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, io.grpc.internal.Deframer
    public void close() {
        if (isClosed()) {
            return;
        }
        boolean z = true;
        boolean hasPartialMessage = this.nextFrame != null && this.nextFrame.readableBytes() > 0;
        try {
            if (this.fullStreamDecompressor != null) {
                if (!hasPartialMessage && !this.fullStreamDecompressor.hasPartialData()) {
                    z = false;
                }
                hasPartialMessage = z;
                this.fullStreamDecompressor.close();
            }
            if (this.unprocessed != null) {
                this.unprocessed.close();
            }
            if (this.nextFrame != null) {
                this.nextFrame.close();
            }
            this.fullStreamDecompressor = null;
            this.unprocessed = null;
            this.nextFrame = null;
            this.listener.deframerClosed(hasPartialMessage);
        } catch (Throwable th) {
            this.fullStreamDecompressor = null;
            this.unprocessed = null;
            this.nextFrame = null;
            throw th;
        }
    }

    public boolean isClosed() {
        return this.unprocessed == null && this.fullStreamDecompressor == null;
    }

    private boolean isClosedOrScheduledToClose() {
        return isClosed() || this.closeWhenComplete;
    }

    private boolean isStalled() {
        if (this.fullStreamDecompressor != null) {
            return this.fullStreamDecompressor.isStalled();
        }
        return this.unprocessed.readableBytes() == 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0055, code lost:
    
        if (r5.stopDelivery == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0057, code lost:
    
        close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005c, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005f, code lost:
    
        if (r5.closeWhenComplete == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0065, code lost:
    
        if (isStalled() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0067, code lost:
    
        close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006d, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void deliver() {
        /*
            r5 = this;
            boolean r0 = r5.inDelivery
            if (r0 == 0) goto L5
            return
        L5:
            r0 = 1
            r5.inDelivery = r0
        L8:
            r0 = 0
            boolean r1 = r5.stopDelivery     // Catch: java.lang.Throwable -> L6e
            if (r1 != 0) goto L53
            long r1 = r5.pendingDeliveries     // Catch: java.lang.Throwable -> L6e
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L53
            boolean r1 = r5.readRequiredBytes()     // Catch: java.lang.Throwable -> L6e
            if (r1 == 0) goto L53
            int[] r1 = io.grpc.internal.MessageDeframer.AnonymousClass1.$SwitchMap$io$grpc$internal$MessageDeframer$State     // Catch: java.lang.Throwable -> L6e
            io.grpc.internal.MessageDeframer$State r2 = r5.state     // Catch: java.lang.Throwable -> L6e
            int r2 = r2.ordinal()     // Catch: java.lang.Throwable -> L6e
            r1 = r1[r2]     // Catch: java.lang.Throwable -> L6e
            switch(r1) {
                case 1: goto L36;
                case 2: goto L2b;
                default: goto L28;
            }     // Catch: java.lang.Throwable -> L6e
        L28:
            java.lang.AssertionError r1 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L6e
            goto L3a
        L2b:
            r5.processBody()     // Catch: java.lang.Throwable -> L6e
            long r1 = r5.pendingDeliveries     // Catch: java.lang.Throwable -> L6e
            r3 = 1
            long r1 = r1 - r3
            r5.pendingDeliveries = r1     // Catch: java.lang.Throwable -> L6e
            goto L8
        L36:
            r5.processHeader()     // Catch: java.lang.Throwable -> L6e
            goto L8
        L3a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6e
            r2.<init>()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r3 = "Invalid state: "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L6e
            io.grpc.internal.MessageDeframer$State r3 = r5.state     // Catch: java.lang.Throwable -> L6e
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L6e
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L6e
            throw r1     // Catch: java.lang.Throwable -> L6e
        L53:
            boolean r1 = r5.stopDelivery     // Catch: java.lang.Throwable -> L6e
            if (r1 == 0) goto L5d
            r5.close()     // Catch: java.lang.Throwable -> L6e
            r5.inDelivery = r0
            return
        L5d:
            boolean r1 = r5.closeWhenComplete     // Catch: java.lang.Throwable -> L6e
            if (r1 == 0) goto L6a
            boolean r1 = r5.isStalled()     // Catch: java.lang.Throwable -> L6e
            if (r1 == 0) goto L6a
            r5.close()     // Catch: java.lang.Throwable -> L6e
        L6a:
            r5.inDelivery = r0
            return
        L6e:
            r1 = move-exception
            r5.inDelivery = r0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.MessageDeframer.deliver():void");
    }

    private boolean readRequiredBytes() {
        int totalBytesRead = 0;
        int deflatedBytesRead = 0;
        try {
            if (this.nextFrame == null) {
                this.nextFrame = new CompositeReadableBuffer();
            }
            while (true) {
                int missingBytes = this.requiredLength - this.nextFrame.readableBytes();
                if (missingBytes <= 0) {
                    if (totalBytesRead <= 0) {
                        return true;
                    }
                    this.listener.bytesRead(totalBytesRead);
                    if (this.state != State.BODY) {
                        return true;
                    }
                    if (this.fullStreamDecompressor != null) {
                        this.statsTraceCtx.inboundWireSize(deflatedBytesRead);
                        this.inboundBodyWireSize += deflatedBytesRead;
                        return true;
                    }
                    this.statsTraceCtx.inboundWireSize(totalBytesRead);
                    this.inboundBodyWireSize += totalBytesRead;
                    return true;
                }
                if (this.fullStreamDecompressor != null) {
                    try {
                        if (this.inflatedBuffer == null || this.inflatedIndex == this.inflatedBuffer.length) {
                            this.inflatedBuffer = new byte[Math.min(missingBytes, 2097152)];
                            this.inflatedIndex = 0;
                        }
                        int bytesToRead = Math.min(missingBytes, this.inflatedBuffer.length - this.inflatedIndex);
                        int n = this.fullStreamDecompressor.inflateBytes(this.inflatedBuffer, this.inflatedIndex, bytesToRead);
                        totalBytesRead += this.fullStreamDecompressor.getAndResetBytesConsumed();
                        deflatedBytesRead += this.fullStreamDecompressor.getAndResetDeflatedBytesConsumed();
                        if (n == 0) {
                            return false;
                        }
                        this.nextFrame.addBuffer(ReadableBuffers.wrap(this.inflatedBuffer, this.inflatedIndex, n));
                        this.inflatedIndex += n;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (DataFormatException e2) {
                        throw new RuntimeException(e2);
                    }
                } else {
                    if (this.unprocessed.readableBytes() == 0) {
                        if (totalBytesRead > 0) {
                            this.listener.bytesRead(totalBytesRead);
                            if (this.state == State.BODY) {
                                if (this.fullStreamDecompressor != null) {
                                    this.statsTraceCtx.inboundWireSize(deflatedBytesRead);
                                    this.inboundBodyWireSize += deflatedBytesRead;
                                } else {
                                    this.statsTraceCtx.inboundWireSize(totalBytesRead);
                                    this.inboundBodyWireSize += totalBytesRead;
                                }
                            }
                        }
                        return false;
                    }
                    int toRead = Math.min(missingBytes, this.unprocessed.readableBytes());
                    totalBytesRead += toRead;
                    this.nextFrame.addBuffer(this.unprocessed.readBytes(toRead));
                }
            }
        } finally {
            if (totalBytesRead > 0) {
                this.listener.bytesRead(totalBytesRead);
                if (this.state == State.BODY) {
                    if (this.fullStreamDecompressor != null) {
                        this.statsTraceCtx.inboundWireSize(deflatedBytesRead);
                        this.inboundBodyWireSize += deflatedBytesRead;
                    } else {
                        this.statsTraceCtx.inboundWireSize(totalBytesRead);
                        this.inboundBodyWireSize += totalBytesRead;
                    }
                }
            }
        }
    }

    private void processHeader() {
        int type = this.nextFrame.readUnsignedByte();
        if ((type & RESERVED_MASK) != 0) {
            throw Status.INTERNAL.withDescription("gRPC frame header malformed: reserved bits not zero").asRuntimeException();
        }
        this.compressedFlag = (type & 1) != 0;
        this.requiredLength = this.nextFrame.readInt();
        if (this.requiredLength < 0 || this.requiredLength > this.maxInboundMessageSize) {
            throw Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "gRPC message exceeds maximum size %d: %d", Integer.valueOf(this.maxInboundMessageSize), Integer.valueOf(this.requiredLength))).asRuntimeException();
        }
        this.currentMessageSeqNo++;
        this.statsTraceCtx.inboundMessage(this.currentMessageSeqNo);
        this.transportTracer.reportMessageReceived();
        this.state = State.BODY;
    }

    private void processBody() {
        this.statsTraceCtx.inboundMessageRead(this.currentMessageSeqNo, this.inboundBodyWireSize, -1L);
        this.inboundBodyWireSize = 0;
        InputStream stream = this.compressedFlag ? getCompressedBody() : getUncompressedBody();
        this.nextFrame = null;
        this.listener.messagesAvailable(new SingleMessageProducer(stream));
        this.state = State.HEADER;
        this.requiredLength = 5;
    }

    private InputStream getUncompressedBody() {
        this.statsTraceCtx.inboundUncompressedSize(this.nextFrame.readableBytes());
        return ReadableBuffers.openStream(this.nextFrame, true);
    }

    private InputStream getCompressedBody() {
        if (this.decompressor == Codec.Identity.NONE) {
            throw Status.INTERNAL.withDescription("Can't decode compressed gRPC message as compression not configured").asRuntimeException();
        }
        try {
            InputStream unlimitedStream = this.decompressor.decompress(ReadableBuffers.openStream(this.nextFrame, true));
            return new SizeEnforcingInputStream(unlimitedStream, this.maxInboundMessageSize, this.statsTraceCtx);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static final class SizeEnforcingInputStream extends FilterInputStream {
        private long count;
        private long mark;
        private long maxCount;
        private final int maxMessageSize;
        private final StatsTraceContext statsTraceCtx;

        SizeEnforcingInputStream(InputStream in, int maxMessageSize, StatsTraceContext statsTraceCtx) {
            super(in);
            this.mark = -1L;
            this.maxMessageSize = maxMessageSize;
            this.statsTraceCtx = statsTraceCtx;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int result = this.in.read();
            if (result != -1) {
                this.count++;
            }
            verifySize();
            reportCount();
            return result;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] b, int off, int len) throws IOException {
            int result = this.in.read(b, off, len);
            if (result != -1) {
                this.count += (long) result;
            }
            verifySize();
            reportCount();
            return result;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long n) throws IOException {
            long result = this.in.skip(n);
            this.count += result;
            verifySize();
            reportCount();
            return result;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void mark(int readlimit) {
            this.in.mark(readlimit);
            this.mark = this.count;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            }
            if (this.mark == -1) {
                throw new IOException("Mark not set");
            }
            this.in.reset();
            this.count = this.mark;
        }

        private void reportCount() {
            if (this.count > this.maxCount) {
                this.statsTraceCtx.inboundUncompressedSize(this.count - this.maxCount);
                this.maxCount = this.count;
            }
        }

        private void verifySize() {
            if (this.count > this.maxMessageSize) {
                throw Status.RESOURCE_EXHAUSTED.withDescription("Decompressed gRPC message exceeds maximum size " + this.maxMessageSize).asRuntimeException();
            }
        }
    }

    private static class SingleMessageProducer implements StreamListener.MessageProducer {
        private InputStream message;

        private SingleMessageProducer(InputStream message) {
            this.message = message;
        }

        @Override // io.grpc.internal.StreamListener.MessageProducer
        @Nullable
        public InputStream next() {
            InputStream messageToReturn = this.message;
            this.message = null;
            return messageToReturn;
        }
    }
}
