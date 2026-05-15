package io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import io.grpc.Detachable;
import io.grpc.HasByteBuffer;
import io.grpc.KnownLength;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public final class ReadableBuffers {
    private static final ReadableBuffer EMPTY_BUFFER = new ByteArrayWrapper(new byte[0]);

    public static ReadableBuffer empty() {
        return EMPTY_BUFFER;
    }

    public static ReadableBuffer wrap(byte[] bytes) {
        return new ByteArrayWrapper(bytes, 0, bytes.length);
    }

    public static ReadableBuffer wrap(byte[] bytes, int offset, int length) {
        return new ByteArrayWrapper(bytes, offset, length);
    }

    public static ReadableBuffer wrap(ByteBuffer bytes) {
        return new ByteReadableBufferWrapper(bytes);
    }

    public static byte[] readArray(ReadableBuffer buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        int length = buffer.readableBytes();
        byte[] bytes = new byte[length];
        buffer.readBytes(bytes, 0, length);
        return bytes;
    }

    public static String readAsString(ReadableBuffer buffer, Charset charset) {
        Preconditions.checkNotNull(charset, "charset");
        byte[] bytes = readArray(buffer);
        return new String(bytes, charset);
    }

    public static String readAsStringUtf8(ReadableBuffer buffer) {
        return readAsString(buffer, Charsets.UTF_8);
    }

    public static InputStream openStream(ReadableBuffer buffer, boolean owner) {
        return new BufferInputStream(owner ? buffer : ignoreClose(buffer));
    }

    public static ReadableBuffer ignoreClose(ReadableBuffer buffer) {
        return new ForwardingReadableBuffer(buffer) { // from class: io.grpc.internal.ReadableBuffers.1
            @Override // io.grpc.internal.ForwardingReadableBuffer, io.grpc.internal.ReadableBuffer, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }
        };
    }

    private static class ByteArrayWrapper extends AbstractReadableBuffer {
        final byte[] bytes;
        final int end;
        int mark;
        int offset;

        ByteArrayWrapper(byte[] bytes) {
            this(bytes, 0, bytes.length);
        }

        ByteArrayWrapper(byte[] bytes, int offset, int length) {
            this.mark = -1;
            Preconditions.checkArgument(offset >= 0, "offset must be >= 0");
            Preconditions.checkArgument(length >= 0, "length must be >= 0");
            Preconditions.checkArgument(offset + length <= bytes.length, "offset + length exceeds array boundary");
            this.bytes = (byte[]) Preconditions.checkNotNull(bytes, "bytes");
            this.offset = offset;
            this.end = offset + length;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public int readableBytes() {
            return this.end - this.offset;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void skipBytes(int length) {
            checkReadable(length);
            this.offset += length;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public int readUnsignedByte() {
            checkReadable(1);
            byte[] bArr = this.bytes;
            int i = this.offset;
            this.offset = i + 1;
            return bArr[i] & 255;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(byte[] dest, int destIndex, int length) {
            System.arraycopy(this.bytes, this.offset, dest, destIndex, length);
            this.offset += length;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(ByteBuffer dest) {
            Preconditions.checkNotNull(dest, "dest");
            int length = dest.remaining();
            checkReadable(length);
            dest.put(this.bytes, this.offset, length);
            this.offset += length;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(OutputStream dest, int length) throws IOException {
            checkReadable(length);
            dest.write(this.bytes, this.offset, length);
            this.offset += length;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public ByteArrayWrapper readBytes(int length) {
            checkReadable(length);
            int originalOffset = this.offset;
            this.offset += length;
            return new ByteArrayWrapper(this.bytes, originalOffset, length);
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public boolean hasArray() {
            return true;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public byte[] array() {
            return this.bytes;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public int arrayOffset() {
            return this.offset;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public boolean markSupported() {
            return true;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public void mark() {
            this.mark = this.offset;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public void reset() {
            if (this.mark == -1) {
                throw new InvalidMarkException();
            }
            this.offset = this.mark;
        }
    }

    private static class ByteReadableBufferWrapper extends AbstractReadableBuffer {
        final ByteBuffer bytes;

        ByteReadableBufferWrapper(ByteBuffer bytes) {
            this.bytes = (ByteBuffer) Preconditions.checkNotNull(bytes, "bytes");
        }

        @Override // io.grpc.internal.ReadableBuffer
        public int readableBytes() {
            return this.bytes.remaining();
        }

        @Override // io.grpc.internal.ReadableBuffer
        public int readUnsignedByte() {
            checkReadable(1);
            return this.bytes.get() & 255;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void skipBytes(int length) {
            checkReadable(length);
            this.bytes.position(this.bytes.position() + length);
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(byte[] dest, int destOffset, int length) {
            checkReadable(length);
            this.bytes.get(dest, destOffset, length);
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(ByteBuffer dest) {
            Preconditions.checkNotNull(dest, "dest");
            int length = dest.remaining();
            checkReadable(length);
            int prevLimit = this.bytes.limit();
            this.bytes.limit(this.bytes.position() + length);
            dest.put(this.bytes);
            this.bytes.limit(prevLimit);
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(OutputStream dest, int length) throws IOException {
            checkReadable(length);
            if (hasArray()) {
                dest.write(array(), arrayOffset(), length);
                this.bytes.position(this.bytes.position() + length);
            } else {
                byte[] array = new byte[length];
                this.bytes.get(array);
                dest.write(array);
            }
        }

        @Override // io.grpc.internal.ReadableBuffer
        public ByteReadableBufferWrapper readBytes(int length) {
            checkReadable(length);
            ByteBuffer buffer = this.bytes.duplicate();
            buffer.limit(this.bytes.position() + length);
            this.bytes.position(this.bytes.position() + length);
            return new ByteReadableBufferWrapper(buffer);
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public boolean hasArray() {
            return this.bytes.hasArray();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public byte[] array() {
            return this.bytes.array();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public int arrayOffset() {
            return this.bytes.arrayOffset() + this.bytes.position();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public boolean markSupported() {
            return true;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public void mark() {
            this.bytes.mark();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public void reset() {
            this.bytes.reset();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public boolean byteBufferSupported() {
            return true;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public ByteBuffer getByteBuffer() {
            return this.bytes.slice();
        }
    }

    private static final class BufferInputStream extends InputStream implements KnownLength, HasByteBuffer, Detachable {
        private ReadableBuffer buffer;

        public BufferInputStream(ReadableBuffer buffer) {
            this.buffer = (ReadableBuffer) Preconditions.checkNotNull(buffer, "buffer");
        }

        @Override // java.io.InputStream, io.grpc.KnownLength
        public int available() throws IOException {
            return this.buffer.readableBytes();
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            return this.buffer.readUnsignedByte();
        }

        @Override // java.io.InputStream
        public int read(byte[] dest, int destOffset, int length) throws IOException {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            int length2 = Math.min(this.buffer.readableBytes(), length);
            this.buffer.readBytes(dest, destOffset, length2);
            return length2;
        }

        @Override // java.io.InputStream
        public long skip(long n) throws IOException {
            int length = (int) Math.min(this.buffer.readableBytes(), n);
            this.buffer.skipBytes(length);
            return length;
        }

        @Override // java.io.InputStream
        public void mark(int readlimit) {
            this.buffer.mark();
        }

        @Override // java.io.InputStream
        public void reset() throws IOException {
            this.buffer.reset();
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return this.buffer.markSupported();
        }

        @Override // io.grpc.HasByteBuffer
        public boolean byteBufferSupported() {
            return this.buffer.byteBufferSupported();
        }

        @Override // io.grpc.HasByteBuffer
        @Nullable
        public ByteBuffer getByteBuffer() {
            return this.buffer.getByteBuffer();
        }

        @Override // io.grpc.Detachable
        public InputStream detach() {
            ReadableBuffer detachedBuffer = this.buffer;
            this.buffer = this.buffer.readBytes(0);
            return new BufferInputStream(detachedBuffer);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.buffer.close();
        }
    }

    private ReadableBuffers() {
    }
}
