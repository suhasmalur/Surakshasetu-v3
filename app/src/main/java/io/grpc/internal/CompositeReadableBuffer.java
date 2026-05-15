package io.grpc.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public class CompositeReadableBuffer extends AbstractReadableBuffer {
    private boolean marked;
    private final Deque<ReadableBuffer> readableBuffers;
    private int readableBytes;
    private Deque<ReadableBuffer> rewindableBuffers;
    private static final NoThrowReadOperation<Void> UBYTE_OP = new NoThrowReadOperation<Void>() { // from class: io.grpc.internal.CompositeReadableBuffer.1
        @Override // io.grpc.internal.CompositeReadableBuffer.NoThrowReadOperation, io.grpc.internal.CompositeReadableBuffer.ReadOperation
        public int read(ReadableBuffer buffer, int length, Void unused, int value) {
            return buffer.readUnsignedByte();
        }
    };
    private static final NoThrowReadOperation<Void> SKIP_OP = new NoThrowReadOperation<Void>() { // from class: io.grpc.internal.CompositeReadableBuffer.2
        @Override // io.grpc.internal.CompositeReadableBuffer.NoThrowReadOperation, io.grpc.internal.CompositeReadableBuffer.ReadOperation
        public int read(ReadableBuffer buffer, int length, Void unused, int unused2) {
            buffer.skipBytes(length);
            return 0;
        }
    };
    private static final NoThrowReadOperation<byte[]> BYTE_ARRAY_OP = new NoThrowReadOperation<byte[]>() { // from class: io.grpc.internal.CompositeReadableBuffer.3
        @Override // io.grpc.internal.CompositeReadableBuffer.NoThrowReadOperation, io.grpc.internal.CompositeReadableBuffer.ReadOperation
        public int read(ReadableBuffer buffer, int length, byte[] dest, int offset) {
            buffer.readBytes(dest, offset, length);
            return offset + length;
        }
    };
    private static final NoThrowReadOperation<ByteBuffer> BYTE_BUF_OP = new NoThrowReadOperation<ByteBuffer>() { // from class: io.grpc.internal.CompositeReadableBuffer.4
        @Override // io.grpc.internal.CompositeReadableBuffer.NoThrowReadOperation, io.grpc.internal.CompositeReadableBuffer.ReadOperation
        public int read(ReadableBuffer buffer, int length, ByteBuffer dest, int unused) {
            int prevLimit = dest.limit();
            dest.limit(dest.position() + length);
            buffer.readBytes(dest);
            dest.limit(prevLimit);
            return 0;
        }
    };
    private static final ReadOperation<OutputStream> STREAM_OP = new ReadOperation<OutputStream>() { // from class: io.grpc.internal.CompositeReadableBuffer.5
        @Override // io.grpc.internal.CompositeReadableBuffer.ReadOperation
        public int read(ReadableBuffer buffer, int length, OutputStream dest, int unused) throws IOException {
            buffer.readBytes(dest, length);
            return 0;
        }
    };

    private interface NoThrowReadOperation<T> extends ReadOperation<T> {
        @Override // io.grpc.internal.CompositeReadableBuffer.ReadOperation
        int read(ReadableBuffer readableBuffer, int i, T t, int i2);
    }

    private interface ReadOperation<T> {
        int read(ReadableBuffer readableBuffer, int i, T t, int i2) throws IOException;
    }

    public CompositeReadableBuffer(int initialCapacity) {
        this.readableBuffers = new ArrayDeque(initialCapacity);
    }

    public CompositeReadableBuffer() {
        this.readableBuffers = new ArrayDeque();
    }

    public void addBuffer(ReadableBuffer buffer) {
        boolean markHead = this.marked && this.readableBuffers.isEmpty();
        enqueueBuffer(buffer);
        if (markHead) {
            this.readableBuffers.peek().mark();
        }
    }

    private void enqueueBuffer(ReadableBuffer buffer) {
        if (!(buffer instanceof CompositeReadableBuffer)) {
            this.readableBuffers.add(buffer);
            this.readableBytes += buffer.readableBytes();
            return;
        }
        CompositeReadableBuffer compositeBuffer = (CompositeReadableBuffer) buffer;
        while (!compositeBuffer.readableBuffers.isEmpty()) {
            ReadableBuffer subBuffer = compositeBuffer.readableBuffers.remove();
            this.readableBuffers.add(subBuffer);
        }
        this.readableBytes += compositeBuffer.readableBytes;
        compositeBuffer.readableBytes = 0;
        compositeBuffer.close();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int readableBytes() {
        return this.readableBytes;
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int readUnsignedByte() {
        return executeNoThrow(UBYTE_OP, 1, null, 0);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void skipBytes(int length) {
        executeNoThrow(SKIP_OP, length, null, 0);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(byte[] dest, int destOffset, int length) {
        executeNoThrow(BYTE_ARRAY_OP, length, dest, destOffset);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(ByteBuffer dest) {
        executeNoThrow(BYTE_BUF_OP, dest.remaining(), dest, 0);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(OutputStream dest, int length) throws IOException {
        execute(STREAM_OP, length, dest, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [io.grpc.internal.ReadableBuffer] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [io.grpc.internal.ReadableBuffer] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [io.grpc.internal.CompositeReadableBuffer] */
    /* JADX WARN: Type inference failed for: r1v3, types: [io.grpc.internal.CompositeReadableBuffer] */
    /* JADX WARN: Type inference failed for: r1v4 */
    @Override // io.grpc.internal.ReadableBuffer
    public ReadableBuffer readBytes(int length) {
        ReadableBuffer readBuffer;
        if (length <= 0) {
            return ReadableBuffers.empty();
        }
        checkReadable(length);
        this.readableBytes -= length;
        ?? r0 = 0;
        ?? compositeReadableBuffer = 0;
        do {
            ReadableBuffer buffer = this.readableBuffers.peek();
            int readable = buffer.readableBytes();
            if (readable > length) {
                readBuffer = buffer.readBytes(length);
                length = 0;
            } else {
                if (this.marked) {
                    readBuffer = buffer.readBytes(readable);
                    advanceBuffer();
                } else {
                    readBuffer = this.readableBuffers.poll();
                }
                length -= readable;
            }
            if (r0 == 0) {
                r0 = readBuffer;
            } else {
                if (compositeReadableBuffer == 0) {
                    compositeReadableBuffer = new CompositeReadableBuffer(length != 0 ? Math.min(this.readableBuffers.size() + 2, 16) : 2);
                    compositeReadableBuffer.addBuffer(r0);
                    r0 = compositeReadableBuffer;
                }
                compositeReadableBuffer.addBuffer(readBuffer);
            }
        } while (length > 0);
        return r0;
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
    public boolean markSupported() {
        for (ReadableBuffer buffer : this.readableBuffers) {
            if (!buffer.markSupported()) {
                return false;
            }
        }
        return true;
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
    public void mark() {
        if (this.rewindableBuffers == null) {
            this.rewindableBuffers = new ArrayDeque(Math.min(this.readableBuffers.size(), 16));
        }
        while (!this.rewindableBuffers.isEmpty()) {
            this.rewindableBuffers.remove().close();
        }
        this.marked = true;
        ReadableBuffer buffer = this.readableBuffers.peek();
        if (buffer != null) {
            buffer.mark();
        }
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
    public void reset() {
        if (!this.marked) {
            throw new InvalidMarkException();
        }
        ReadableBuffer buffer = this.readableBuffers.peek();
        if (buffer != null) {
            int currentRemain = buffer.readableBytes();
            buffer.reset();
            this.readableBytes += buffer.readableBytes() - currentRemain;
        }
        while (true) {
            ReadableBuffer buffer2 = this.rewindableBuffers.pollLast();
            if (buffer2 != null) {
                buffer2.reset();
                this.readableBuffers.addFirst(buffer2);
                this.readableBytes += buffer2.readableBytes();
            } else {
                return;
            }
        }
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
    public boolean byteBufferSupported() {
        for (ReadableBuffer buffer : this.readableBuffers) {
            if (!buffer.byteBufferSupported()) {
                return false;
            }
        }
        return true;
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
    @Nullable
    public ByteBuffer getByteBuffer() {
        if (this.readableBuffers.isEmpty()) {
            return null;
        }
        return this.readableBuffers.peek().getByteBuffer();
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        while (!this.readableBuffers.isEmpty()) {
            this.readableBuffers.remove().close();
        }
        if (this.rewindableBuffers != null) {
            while (!this.rewindableBuffers.isEmpty()) {
                this.rewindableBuffers.remove().close();
            }
        }
    }

    private <T> int execute(ReadOperation<T> op, int length, T dest, int value) throws IOException {
        checkReadable(length);
        if (!this.readableBuffers.isEmpty()) {
            advanceBufferIfNecessary();
        }
        while (length > 0 && !this.readableBuffers.isEmpty()) {
            ReadableBuffer buffer = this.readableBuffers.peek();
            int lengthToCopy = Math.min(length, buffer.readableBytes());
            value = op.read(buffer, lengthToCopy, dest, value);
            length -= lengthToCopy;
            this.readableBytes -= lengthToCopy;
            advanceBufferIfNecessary();
        }
        if (length > 0) {
            throw new AssertionError("Failed executing read operation");
        }
        return value;
    }

    private <T> int executeNoThrow(NoThrowReadOperation<T> op, int length, T dest, int value) {
        try {
            return execute(op, length, dest, value);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private void advanceBufferIfNecessary() {
        ReadableBuffer buffer = this.readableBuffers.peek();
        if (buffer.readableBytes() == 0) {
            advanceBuffer();
        }
    }

    private void advanceBuffer() {
        if (this.marked) {
            this.rewindableBuffers.add(this.readableBuffers.remove());
            ReadableBuffer next = this.readableBuffers.peek();
            if (next != null) {
                next.mark();
                return;
            }
            return;
        }
        this.readableBuffers.remove().close();
    }
}
