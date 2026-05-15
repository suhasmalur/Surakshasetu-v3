package io.grpc.okhttp;

import io.grpc.internal.AbstractReadableBuffer;
import io.grpc.internal.ReadableBuffer;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import okio.Buffer;

/* JADX INFO: loaded from: classes10.dex */
class OkHttpReadableBuffer extends AbstractReadableBuffer {
    private final Buffer buffer;

    OkHttpReadableBuffer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int readableBytes() {
        return (int) this.buffer.size();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int readUnsignedByte() {
        try {
            fakeEofExceptionMethod();
            return this.buffer.readByte() & 255;
        } catch (EOFException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }

    private void fakeEofExceptionMethod() throws EOFException {
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void skipBytes(int length) {
        try {
            this.buffer.skip(length);
        } catch (EOFException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(byte[] dest, int destOffset, int length) {
        while (length > 0) {
            int bytesRead = this.buffer.read(dest, destOffset, length);
            if (bytesRead == -1) {
                throw new IndexOutOfBoundsException("EOF trying to read " + length + " bytes");
            }
            length -= bytesRead;
            destOffset += bytesRead;
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(ByteBuffer dest) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(OutputStream dest, int length) throws IOException {
        this.buffer.writeTo(dest, length);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public ReadableBuffer readBytes(int length) {
        Buffer buf = new Buffer();
        buf.write(this.buffer, length);
        return new OkHttpReadableBuffer(buf);
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.buffer.clear();
    }
}
