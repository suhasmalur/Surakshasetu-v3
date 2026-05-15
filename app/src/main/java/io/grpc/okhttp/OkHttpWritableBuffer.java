package io.grpc.okhttp;

import io.grpc.internal.WritableBuffer;
import okio.Buffer;

/* JADX INFO: loaded from: classes10.dex */
class OkHttpWritableBuffer implements WritableBuffer {
    private final Buffer buffer;
    private int readableBytes;
    private int writableBytes;

    OkHttpWritableBuffer(Buffer buffer, int capacity) {
        this.buffer = buffer;
        this.writableBytes = capacity;
    }

    @Override // io.grpc.internal.WritableBuffer
    public void write(byte[] src, int srcIndex, int length) {
        this.buffer.write(src, srcIndex, length);
        this.writableBytes -= length;
        this.readableBytes += length;
    }

    @Override // io.grpc.internal.WritableBuffer
    public void write(byte b) {
        this.buffer.writeByte((int) b);
        this.writableBytes--;
        this.readableBytes++;
    }

    @Override // io.grpc.internal.WritableBuffer
    public int writableBytes() {
        return this.writableBytes;
    }

    @Override // io.grpc.internal.WritableBuffer
    public int readableBytes() {
        return this.readableBytes;
    }

    @Override // io.grpc.internal.WritableBuffer
    public void release() {
    }

    Buffer buffer() {
        return this.buffer;
    }
}
