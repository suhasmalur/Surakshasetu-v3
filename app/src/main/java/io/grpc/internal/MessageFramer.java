package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.Drainable;
import io.grpc.KnownLength;
import io.grpc.Status;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public class MessageFramer implements Framer {
    private static final byte COMPRESSED = 1;
    private static final int HEADER_LENGTH = 5;
    private static final int NO_MAX_OUTBOUND_MESSAGE_SIZE = -1;
    private static final byte UNCOMPRESSED = 0;
    private WritableBuffer buffer;
    private final WritableBufferAllocator bufferAllocator;
    private boolean closed;
    private long currentMessageWireSize;
    private int messagesBuffered;
    private final Sink sink;
    private final StatsTraceContext statsTraceCtx;
    private int maxOutboundMessageSize = -1;
    private Compressor compressor = Codec.Identity.NONE;
    private boolean messageCompression = true;
    private final OutputStreamAdapter outputStreamAdapter = new OutputStreamAdapter();
    private final ByteBuffer headerScratch = ByteBuffer.allocate(5);
    private int currentMessageSeqNo = -1;

    public interface Sink {
        void deliverFrame(@Nullable WritableBuffer writableBuffer, boolean z, boolean z2, int i);
    }

    public MessageFramer(Sink sink, WritableBufferAllocator bufferAllocator, StatsTraceContext statsTraceCtx) {
        this.sink = (Sink) Preconditions.checkNotNull(sink, "sink");
        this.bufferAllocator = (WritableBufferAllocator) Preconditions.checkNotNull(bufferAllocator, "bufferAllocator");
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceCtx, "statsTraceCtx");
    }

    @Override // io.grpc.internal.Framer
    public MessageFramer setCompressor(Compressor compressor) {
        this.compressor = (Compressor) Preconditions.checkNotNull(compressor, "Can't pass an empty compressor");
        return this;
    }

    @Override // io.grpc.internal.Framer
    public MessageFramer setMessageCompression(boolean enable) {
        this.messageCompression = enable;
        return this;
    }

    @Override // io.grpc.internal.Framer
    public void setMaxOutboundMessageSize(int maxSize) {
        Preconditions.checkState(this.maxOutboundMessageSize == -1, "max size already set");
        this.maxOutboundMessageSize = maxSize;
    }

    @Override // io.grpc.internal.Framer
    public void writePayload(InputStream message) {
        int written;
        verifyNotClosed();
        this.messagesBuffered++;
        this.currentMessageSeqNo++;
        this.currentMessageWireSize = 0L;
        this.statsTraceCtx.outboundMessage(this.currentMessageSeqNo);
        boolean compressed = this.messageCompression && this.compressor != Codec.Identity.NONE;
        try {
            int messageLength = getKnownLength(message);
            if (messageLength != 0 && compressed) {
                written = writeCompressed(message, messageLength);
            } else {
                written = writeUncompressed(message, messageLength);
            }
            if (messageLength != -1 && written != messageLength) {
                String err = String.format("Message length inaccurate %s != %s", Integer.valueOf(written), Integer.valueOf(messageLength));
                throw Status.INTERNAL.withDescription(err).asRuntimeException();
            }
            this.statsTraceCtx.outboundUncompressedSize(written);
            this.statsTraceCtx.outboundWireSize(this.currentMessageWireSize);
            this.statsTraceCtx.outboundMessageSent(this.currentMessageSeqNo, this.currentMessageWireSize, written);
        } catch (IOException e) {
            throw Status.INTERNAL.withDescription("Failed to frame message").withCause(e).asRuntimeException();
        } catch (RuntimeException e2) {
            throw Status.INTERNAL.withDescription("Failed to frame message").withCause(e2).asRuntimeException();
        }
    }

    private int writeUncompressed(InputStream message, int messageLength) throws IOException {
        if (messageLength != -1) {
            this.currentMessageWireSize = messageLength;
            return writeKnownLengthUncompressed(message, messageLength);
        }
        BufferChainOutputStream bufferChain = new BufferChainOutputStream();
        int written = writeToOutputStream(message, bufferChain);
        if (this.maxOutboundMessageSize >= 0 && written > this.maxOutboundMessageSize) {
            throw Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "message too large %d > %d", Integer.valueOf(written), Integer.valueOf(this.maxOutboundMessageSize))).asRuntimeException();
        }
        writeBufferChain(bufferChain, false);
        return written;
    }

    private int writeCompressed(InputStream message, int unusedMessageLength) throws IOException {
        BufferChainOutputStream bufferChain = new BufferChainOutputStream();
        OutputStream compressingStream = this.compressor.compress(bufferChain);
        try {
            int written = writeToOutputStream(message, compressingStream);
            compressingStream.close();
            if (this.maxOutboundMessageSize >= 0 && written > this.maxOutboundMessageSize) {
                throw Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "message too large %d > %d", Integer.valueOf(written), Integer.valueOf(this.maxOutboundMessageSize))).asRuntimeException();
            }
            writeBufferChain(bufferChain, true);
            return written;
        } catch (Throwable th) {
            compressingStream.close();
            throw th;
        }
    }

    private int getKnownLength(InputStream inputStream) throws IOException {
        if ((inputStream instanceof KnownLength) || (inputStream instanceof ByteArrayInputStream)) {
            return inputStream.available();
        }
        return -1;
    }

    private int writeKnownLengthUncompressed(InputStream message, int messageLength) throws IOException {
        if (this.maxOutboundMessageSize >= 0 && messageLength > this.maxOutboundMessageSize) {
            throw Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "message too large %d > %d", Integer.valueOf(messageLength), Integer.valueOf(this.maxOutboundMessageSize))).asRuntimeException();
        }
        this.headerScratch.clear();
        this.headerScratch.put((byte) 0).putInt(messageLength);
        if (this.buffer == null) {
            this.buffer = this.bufferAllocator.allocate(this.headerScratch.position() + messageLength);
        }
        writeRaw(this.headerScratch.array(), 0, this.headerScratch.position());
        return writeToOutputStream(message, this.outputStreamAdapter);
    }

    private void writeBufferChain(BufferChainOutputStream bufferChainOutputStream, boolean z) {
        int i = bufferChainOutputStream.readableBytes();
        this.headerScratch.clear();
        this.headerScratch.put(z ? (byte) 1 : (byte) 0).putInt(i);
        WritableBuffer writableBufferAllocate = this.bufferAllocator.allocate(5);
        writableBufferAllocate.write(this.headerScratch.array(), 0, this.headerScratch.position());
        if (i == 0) {
            this.buffer = writableBufferAllocate;
            return;
        }
        this.sink.deliverFrame(writableBufferAllocate, false, false, this.messagesBuffered - 1);
        this.messagesBuffered = 1;
        List list = bufferChainOutputStream.bufferList;
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            this.sink.deliverFrame((WritableBuffer) list.get(i2), false, false, 0);
        }
        this.buffer = (WritableBuffer) list.get(list.size() - 1);
        this.currentMessageWireSize = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int writeToOutputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream instanceof Drainable) {
            return ((Drainable) inputStream).drainTo(outputStream);
        }
        long written = ByteStreams.copy(inputStream, outputStream);
        Preconditions.checkArgument(written <= 2147483647L, "Message size overflow: %s", written);
        return (int) written;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeRaw(byte[] b, int off, int len) {
        while (len > 0) {
            if (this.buffer != null && this.buffer.writableBytes() == 0) {
                commitToSink(false, false);
            }
            if (this.buffer == null) {
                this.buffer = this.bufferAllocator.allocate(len);
            }
            int toWrite = Math.min(len, this.buffer.writableBytes());
            this.buffer.write(b, off, toWrite);
            off += toWrite;
            len -= toWrite;
        }
    }

    @Override // io.grpc.internal.Framer
    public void flush() {
        if (this.buffer != null && this.buffer.readableBytes() > 0) {
            commitToSink(false, true);
        }
    }

    @Override // io.grpc.internal.Framer
    public boolean isClosed() {
        return this.closed;
    }

    @Override // io.grpc.internal.Framer
    public void close() {
        if (!isClosed()) {
            this.closed = true;
            if (this.buffer != null && this.buffer.readableBytes() == 0) {
                releaseBuffer();
            }
            commitToSink(true, true);
        }
    }

    @Override // io.grpc.internal.Framer
    public void dispose() {
        this.closed = true;
        releaseBuffer();
    }

    private void releaseBuffer() {
        if (this.buffer != null) {
            this.buffer.release();
            this.buffer = null;
        }
    }

    private void commitToSink(boolean endOfStream, boolean flush) {
        WritableBuffer buf = this.buffer;
        this.buffer = null;
        this.sink.deliverFrame(buf, endOfStream, flush, this.messagesBuffered);
        this.messagesBuffered = 0;
    }

    private void verifyNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("Framer already closed");
        }
    }

    private class OutputStreamAdapter extends OutputStream {
        private OutputStreamAdapter() {
        }

        @Override // java.io.OutputStream
        public void write(int b) {
            byte[] singleByte = {(byte) b};
            write(singleByte, 0, 1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] b, int off, int len) {
            MessageFramer.this.writeRaw(b, off, len);
        }
    }

    private final class BufferChainOutputStream extends OutputStream {
        private final List<WritableBuffer> bufferList;
        private WritableBuffer current;

        private BufferChainOutputStream() {
            this.bufferList = new ArrayList();
        }

        @Override // java.io.OutputStream
        public void write(int b) throws IOException {
            if (this.current != null && this.current.writableBytes() > 0) {
                this.current.write((byte) b);
            } else {
                byte[] singleByte = {(byte) b};
                write(singleByte, 0, 1);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] b, int off, int len) {
            if (this.current == null) {
                this.current = MessageFramer.this.bufferAllocator.allocate(len);
                this.bufferList.add(this.current);
            }
            while (len > 0) {
                int canWrite = Math.min(len, this.current.writableBytes());
                if (canWrite == 0) {
                    int needed = Math.max(len, this.current.readableBytes() * 2);
                    this.current = MessageFramer.this.bufferAllocator.allocate(needed);
                    this.bufferList.add(this.current);
                } else {
                    this.current.write(b, off, canWrite);
                    off += canWrite;
                    len -= canWrite;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int readableBytes() {
            int readable = 0;
            for (WritableBuffer writableBuffer : this.bufferList) {
                readable += writableBuffer.readableBytes();
            }
            return readable;
        }
    }
}
