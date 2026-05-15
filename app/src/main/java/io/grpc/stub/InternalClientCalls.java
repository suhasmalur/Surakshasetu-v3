package io.grpc.stub;

import io.grpc.CallOptions;
import io.grpc.stub.ClientCalls;

/* JADX INFO: loaded from: classes10.dex */
public final class InternalClientCalls {
    public static CallOptions.Key<ClientCalls.StubType> getStubTypeOption() {
        return ClientCalls.STUB_TYPE_OPTION;
    }

    public static StubType getStubType(CallOptions callOptions) {
        return StubType.of((ClientCalls.StubType) callOptions.getOption(ClientCalls.STUB_TYPE_OPTION));
    }

    public static CallOptions setStubType(CallOptions callOptions, StubType stubType) {
        return callOptions.withOption(ClientCalls.STUB_TYPE_OPTION, stubType.internalType);
    }

    public enum StubType {
        BLOCKING(ClientCalls.StubType.BLOCKING),
        ASYNC(ClientCalls.StubType.ASYNC),
        FUTURE(ClientCalls.StubType.FUTURE);

        private final ClientCalls.StubType internalType;

        StubType(ClientCalls.StubType internalType) {
            this.internalType = internalType;
        }

        public static StubType of(ClientCalls.StubType internal) {
            for (StubType value : values()) {
                if (value.internalType == internal) {
                    return value;
                }
            }
            throw new AssertionError("Unknown StubType: " + internal.name());
        }
    }
}
