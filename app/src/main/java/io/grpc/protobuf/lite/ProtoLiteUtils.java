package io.grpc.protobuf.lite;

import com.google.common.base.Preconditions;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;

/* JADX INFO: loaded from: classes10.dex */
public final class ProtoLiteUtils {
    private static final int BUF_SIZE = 8192;
    static final int DEFAULT_MAX_MESSAGE_SIZE = 4194304;
    static volatile ExtensionRegistryLite globalRegistry = ExtensionRegistryLite.getEmptyRegistry();

    public static void setExtensionRegistry(ExtensionRegistryLite newRegistry) {
        globalRegistry = (ExtensionRegistryLite) Preconditions.checkNotNull(newRegistry, "newRegistry");
    }

    public static <T extends MessageLite> MethodDescriptor.Marshaller<T> marshaller(T defaultInstance) {
        return new MessageMarshaller(defaultInstance);
    }

    public static <T extends MessageLite> Metadata.BinaryMarshaller<T> metadataMarshaller(T defaultInstance) {
        return new MetadataMarshaller(defaultInstance);
    }

    static long copy(InputStream from, OutputStream to) throws IOException {
        Preconditions.checkNotNull(from, "inputStream cannot be null!");
        Preconditions.checkNotNull(to, "outputStream cannot be null!");
        byte[] buf = new byte[8192];
        long total = 0;
        while (true) {
            int r = from.read(buf);
            if (r != -1) {
                to.write(buf, 0, r);
                total += (long) r;
            } else {
                return total;
            }
        }
    }

    private ProtoLiteUtils() {
    }

    private static final class MessageMarshaller<T extends MessageLite> implements MethodDescriptor.PrototypeMarshaller<T> {
        private static final ThreadLocal<Reference<byte[]>> bufs = new ThreadLocal<>();
        private final T defaultInstance;
        private final Parser<T> parser;

        MessageMarshaller(T t) {
            this.defaultInstance = t;
            this.parser = (Parser<T>) t.getParserForType();
        }

        @Override // io.grpc.MethodDescriptor.ReflectableMarshaller
        public Class<T> getMessageClass() {
            return (Class<T>) this.defaultInstance.getClass();
        }

        @Override // io.grpc.MethodDescriptor.PrototypeMarshaller
        public T getMessagePrototype() {
            return this.defaultInstance;
        }

        @Override // io.grpc.MethodDescriptor.Marshaller
        public InputStream stream(T value) {
            return new ProtoInputStream(value, this.parser);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x003e A[Catch: IOException -> 0x00b1, TryCatch #0 {IOException -> 0x00b1, blocks: (B:10:0x0019, B:12:0x001d, B:16:0x0027, B:18:0x0032, B:20:0x003b, B:25:0x004e, B:28:0x0058, B:30:0x005c, B:32:0x0063, B:33:0x0087, B:22:0x003e, B:35:0x008a), top: B:49:0x0019 }] */
        @Override // io.grpc.MethodDescriptor.Marshaller
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public T parse(java.io.InputStream r10) {
            /*
                r9 = this;
                boolean r0 = r10 instanceof io.grpc.protobuf.lite.ProtoInputStream
                if (r0 == 0) goto L18
                r0 = r10
                io.grpc.protobuf.lite.ProtoInputStream r0 = (io.grpc.protobuf.lite.ProtoInputStream) r0
                com.google.protobuf.Parser r1 = r0.parser()
                com.google.protobuf.Parser<T extends com.google.protobuf.MessageLite> r2 = r9.parser
                if (r1 != r2) goto L18
                r1 = r10
                io.grpc.protobuf.lite.ProtoInputStream r1 = (io.grpc.protobuf.lite.ProtoInputStream) r1     // Catch: java.lang.IllegalStateException -> L17
                com.google.protobuf.MessageLite r1 = r1.message()     // Catch: java.lang.IllegalStateException -> L17
                return r1
            L17:
                r1 = move-exception
            L18:
                r0 = 0
                boolean r1 = r10 instanceof io.grpc.KnownLength     // Catch: java.io.IOException -> Lb1
                if (r1 == 0) goto L8d
                int r1 = r10.available()     // Catch: java.io.IOException -> Lb1
                if (r1 <= 0) goto L88
                r2 = 4194304(0x400000, float:5.877472E-39)
                if (r1 > r2) goto L88
                java.lang.ThreadLocal<java.lang.ref.Reference<byte[]>> r2 = io.grpc.protobuf.lite.ProtoLiteUtils.MessageMarshaller.bufs     // Catch: java.io.IOException -> Lb1
                java.lang.Object r2 = r2.get()     // Catch: java.io.IOException -> Lb1
                java.lang.ref.Reference r2 = (java.lang.ref.Reference) r2     // Catch: java.io.IOException -> Lb1
                r3 = r2
                if (r2 == 0) goto L3e
                java.lang.Object r2 = r3.get()     // Catch: java.io.IOException -> Lb1
                byte[] r2 = (byte[]) r2     // Catch: java.io.IOException -> Lb1
                r4 = r2
                if (r2 == 0) goto L3e
                int r2 = r4.length     // Catch: java.io.IOException -> Lb1
                if (r2 >= r1) goto L4b
            L3e:
                byte[] r2 = new byte[r1]     // Catch: java.io.IOException -> Lb1
                r4 = r2
                java.lang.ThreadLocal<java.lang.ref.Reference<byte[]>> r2 = io.grpc.protobuf.lite.ProtoLiteUtils.MessageMarshaller.bufs     // Catch: java.io.IOException -> Lb1
                java.lang.ref.WeakReference r5 = new java.lang.ref.WeakReference     // Catch: java.io.IOException -> Lb1
                r5.<init>(r4)     // Catch: java.io.IOException -> Lb1
                r2.set(r5)     // Catch: java.io.IOException -> Lb1
            L4b:
                r2 = r1
            L4c:
                if (r2 <= 0) goto L5a
                int r5 = r1 - r2
                int r6 = r10.read(r4, r5, r2)     // Catch: java.io.IOException -> Lb1
                r7 = -1
                if (r6 != r7) goto L58
                goto L5a
            L58:
                int r2 = r2 - r6
                goto L4c
            L5a:
                if (r2 != 0) goto L63
                r5 = 0
                com.google.protobuf.CodedInputStream r5 = com.google.protobuf.CodedInputStream.newInstance(r4, r5, r1)     // Catch: java.io.IOException -> Lb1
                r0 = r5
            L62:
                goto L8d
            L63:
                int r5 = r1 - r2
                java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch: java.io.IOException -> Lb1
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.io.IOException -> Lb1
                r7.<init>()     // Catch: java.io.IOException -> Lb1
                java.lang.String r8 = "size inaccurate: "
                java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.io.IOException -> Lb1
                java.lang.StringBuilder r7 = r7.append(r1)     // Catch: java.io.IOException -> Lb1
                java.lang.String r8 = " != "
                java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.io.IOException -> Lb1
                java.lang.StringBuilder r7 = r7.append(r5)     // Catch: java.io.IOException -> Lb1
                java.lang.String r7 = r7.toString()     // Catch: java.io.IOException -> Lb1
                r6.<init>(r7)     // Catch: java.io.IOException -> Lb1
                throw r6     // Catch: java.io.IOException -> Lb1
            L88:
                if (r1 != 0) goto L62
                T extends com.google.protobuf.MessageLite r2 = r9.defaultInstance     // Catch: java.io.IOException -> Lb1
                return r2
            L8d:
                if (r0 != 0) goto L94
                com.google.protobuf.CodedInputStream r0 = com.google.protobuf.CodedInputStream.newInstance(r10)
            L94:
                r1 = 2147483647(0x7fffffff, float:NaN)
                r0.setSizeLimit(r1)
                com.google.protobuf.MessageLite r1 = r9.parseFrom(r0)     // Catch: com.google.protobuf.InvalidProtocolBufferException -> L9f
                return r1
            L9f:
                r1 = move-exception
                io.grpc.Status r2 = io.grpc.Status.INTERNAL
                java.lang.String r3 = "Invalid protobuf byte sequence"
                io.grpc.Status r2 = r2.withDescription(r3)
                io.grpc.Status r2 = r2.withCause(r1)
                io.grpc.StatusRuntimeException r2 = r2.asRuntimeException()
                throw r2
            Lb1:
                r1 = move-exception
                java.lang.RuntimeException r2 = new java.lang.RuntimeException
                r2.<init>(r1)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.protobuf.lite.ProtoLiteUtils.MessageMarshaller.parse(java.io.InputStream):com.google.protobuf.MessageLite");
        }

        private T parseFrom(CodedInputStream stream) throws InvalidProtocolBufferException {
            T message = this.parser.parseFrom(stream, ProtoLiteUtils.globalRegistry);
            try {
                stream.checkLastTagWas(0);
                return message;
            } catch (InvalidProtocolBufferException e) {
                e.setUnfinishedMessage(message);
                throw e;
            }
        }
    }

    private static final class MetadataMarshaller<T extends MessageLite> implements Metadata.BinaryMarshaller<T> {
        private final T defaultInstance;

        MetadataMarshaller(T defaultInstance) {
            this.defaultInstance = defaultInstance;
        }

        @Override // io.grpc.Metadata.BinaryMarshaller
        public byte[] toBytes(T value) {
            return value.toByteArray();
        }

        @Override // io.grpc.Metadata.BinaryMarshaller
        public T parseBytes(byte[] serialized) {
            try {
                return (T) this.defaultInstance.getParserForType().parseFrom(serialized, ProtoLiteUtils.globalRegistry);
            } catch (InvalidProtocolBufferException ipbe) {
                throw new IllegalArgumentException(ipbe);
            }
        }
    }
}
