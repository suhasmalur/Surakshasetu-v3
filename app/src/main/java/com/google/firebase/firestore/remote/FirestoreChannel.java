package com.google.firebase.firestore.remote;

import android.content.Context;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.BuildConfig;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Util;
import io.grpc.ClientCall;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

/* JADX INFO: loaded from: classes10.dex */
public class FirestoreChannel {
    private final CredentialsProvider<String> appCheckProvider;
    private final AsyncQueue asyncQueue;
    private final CredentialsProvider<User> authProvider;
    private final GrpcCallProvider callProvider;
    private final GrpcMetadataProvider metadataProvider;
    private final String resourcePrefixValue;
    private static final Metadata.Key<String> X_GOOG_API_CLIENT_HEADER = Metadata.Key.of("x-goog-api-client", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> RESOURCE_PREFIX_HEADER = Metadata.Key.of("google-cloud-resource-prefix", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> X_GOOG_REQUEST_PARAMS_HEADER = Metadata.Key.of("x-goog-request-params", Metadata.ASCII_STRING_MARSHALLER);
    private static volatile String clientLanguage = "gl-java/";

    public static abstract class StreamingListener<T> {
        public void onMessage(T message) {
        }

        public void onClose(Status status) {
        }
    }

    FirestoreChannel(AsyncQueue asyncQueue, Context context, CredentialsProvider<User> authProvider, CredentialsProvider<String> appCheckProvider, DatabaseInfo databaseInfo, GrpcMetadataProvider metadataProvider) {
        this.asyncQueue = asyncQueue;
        this.metadataProvider = metadataProvider;
        this.authProvider = authProvider;
        this.appCheckProvider = appCheckProvider;
        FirestoreCallCredentials firestoreHeaders = new FirestoreCallCredentials(authProvider, appCheckProvider);
        this.callProvider = new GrpcCallProvider(asyncQueue, context, databaseInfo, firestoreHeaders);
        DatabaseId databaseId = databaseInfo.getDatabaseId();
        this.resourcePrefixValue = String.format("projects/%s/databases/%s", databaseId.getProjectId(), databaseId.getDatabaseId());
    }

    public void shutdown() {
        this.callProvider.shutdown();
    }

    <ReqT, RespT> ClientCall<ReqT, RespT> runBidiStreamingRpc(MethodDescriptor<ReqT, RespT> method, final IncomingStreamObserver<RespT> observer) {
        final ClientCall<ReqT, RespT>[] call = {null};
        Task<ClientCall<ReqT, RespT>> clientCall = this.callProvider.createClientCall(method);
        clientCall.addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: com.google.firebase.firestore.remote.FirestoreChannel$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.m383xae2b8084(call, observer, task);
            }
        });
        return new AnonymousClass2(call, clientCall);
    }

    /* JADX INFO: renamed from: lambda$runBidiStreamingRpc$0$com-google-firebase-firestore-remote-FirestoreChannel, reason: not valid java name */
    /* synthetic */ void m383xae2b8084(final ClientCall[] call, final IncomingStreamObserver observer, Task result) {
        call[0] = (ClientCall) result.getResult();
        call[0].start(new ClientCall.Listener<RespT>() { // from class: com.google.firebase.firestore.remote.FirestoreChannel.1
            @Override // io.grpc.ClientCall.Listener
            public void onHeaders(Metadata headers) {
                try {
                    observer.onHeaders(headers);
                } catch (Throwable t) {
                    FirestoreChannel.this.asyncQueue.panic(t);
                }
            }

            @Override // io.grpc.ClientCall.Listener
            public void onMessage(RespT respt) {
                try {
                    observer.onNext(respt);
                    call[0].request(1);
                } catch (Throwable t) {
                    FirestoreChannel.this.asyncQueue.panic(t);
                }
            }

            @Override // io.grpc.ClientCall.Listener
            public void onClose(Status status, Metadata trailers) {
                try {
                    observer.onClose(status);
                } catch (Throwable t) {
                    FirestoreChannel.this.asyncQueue.panic(t);
                }
            }

            @Override // io.grpc.ClientCall.Listener
            public void onReady() {
            }
        }, requestHeaders());
        observer.onOpen();
        call[0].request(1);
    }

    /* JADX INFO: Add missing generic type declarations: [ReqT, RespT] */
    /* JADX INFO: renamed from: com.google.firebase.firestore.remote.FirestoreChannel$2, reason: invalid class name */
    class AnonymousClass2<ReqT, RespT> extends ForwardingClientCall<ReqT, RespT> {
        final /* synthetic */ ClientCall[] val$call;
        final /* synthetic */ Task val$clientCall;

        AnonymousClass2(ClientCall[] clientCallArr, Task task) {
            this.val$call = clientCallArr;
            this.val$clientCall = task;
        }

        @Override // io.grpc.ForwardingClientCall, io.grpc.PartialForwardingClientCall
        protected ClientCall<ReqT, RespT> delegate() {
            Assert.hardAssert(this.val$call[0] != null, "ClientCall used before onOpen() callback", new Object[0]);
            return this.val$call[0];
        }

        @Override // io.grpc.ForwardingClientCall, io.grpc.PartialForwardingClientCall, io.grpc.ClientCall
        public void halfClose() {
            if (this.val$call[0] == null) {
                this.val$clientCall.addOnSuccessListener(FirestoreChannel.this.asyncQueue.getExecutor(), new OnSuccessListener() { // from class: com.google.firebase.firestore.remote.FirestoreChannel$2$$ExternalSyntheticLambda0
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        ((ClientCall) obj).halfClose();
                    }
                });
            } else {
                super.halfClose();
            }
        }
    }

    <ReqT, RespT> void runStreamingResponseRpc(MethodDescriptor<ReqT, RespT> method, final ReqT request, final StreamingListener<RespT> callback) {
        this.callProvider.createClientCall(method).addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: com.google.firebase.firestore.remote.FirestoreChannel$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.m385x26428698(callback, request, task);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$runStreamingResponseRpc$1$com-google-firebase-firestore-remote-FirestoreChannel, reason: not valid java name */
    /* synthetic */ void m385x26428698(final StreamingListener callback, Object request, Task result) {
        final ClientCall clientCall = (ClientCall) result.getResult();
        clientCall.start(new ClientCall.Listener<RespT>() { // from class: com.google.firebase.firestore.remote.FirestoreChannel.3
            @Override // io.grpc.ClientCall.Listener
            public void onMessage(RespT respt) {
                callback.onMessage(respt);
                clientCall.request(1);
            }

            @Override // io.grpc.ClientCall.Listener
            public void onClose(Status status, Metadata trailers) {
                callback.onClose(status);
            }
        }, requestHeaders());
        clientCall.request(1);
        clientCall.sendMessage(request);
        clientCall.halfClose();
    }

    <ReqT, RespT> Task<RespT> runRpc(MethodDescriptor<ReqT, RespT> method, final ReqT request) {
        final TaskCompletionSource<RespT> tcs = new TaskCompletionSource<>();
        this.callProvider.createClientCall(method).addOnCompleteListener(this.asyncQueue.getExecutor(), new OnCompleteListener() { // from class: com.google.firebase.firestore.remote.FirestoreChannel$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.m384x7c1edc40(tcs, request, task);
            }
        });
        return tcs.getTask();
    }

    /* JADX INFO: renamed from: lambda$runRpc$2$com-google-firebase-firestore-remote-FirestoreChannel, reason: not valid java name */
    /* synthetic */ void m384x7c1edc40(final TaskCompletionSource tcs, Object request, Task result) {
        ClientCall clientCall = (ClientCall) result.getResult();
        clientCall.start(new ClientCall.Listener<RespT>() { // from class: com.google.firebase.firestore.remote.FirestoreChannel.4
            @Override // io.grpc.ClientCall.Listener
            public void onMessage(RespT respt) {
                tcs.setResult(respt);
            }

            @Override // io.grpc.ClientCall.Listener
            public void onClose(Status status, Metadata trailers) {
                if (!status.isOk()) {
                    tcs.setException(FirestoreChannel.this.exceptionFromStatus(status));
                } else if (!tcs.getTask().isComplete()) {
                    tcs.setException(new FirebaseFirestoreException("Received onClose with status OK, but no message.", FirebaseFirestoreException.Code.INTERNAL));
                }
            }
        }, requestHeaders());
        clientCall.request(2);
        clientCall.sendMessage(request);
        clientCall.halfClose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FirebaseFirestoreException exceptionFromStatus(Status status) {
        if (Datastore.isMissingSslCiphers(status)) {
            return new FirebaseFirestoreException("The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.", FirebaseFirestoreException.Code.fromValue(status.getCode().value()), status.getCause());
        }
        return Util.exceptionFromStatus(status);
    }

    public void invalidateToken() {
        this.authProvider.invalidateToken();
        this.appCheckProvider.invalidateToken();
    }

    public static void setClientLanguage(String languageToken) {
        clientLanguage = languageToken;
    }

    private String getGoogApiClientValue() {
        return String.format("%s fire/%s grpc/", clientLanguage, BuildConfig.VERSION_NAME);
    }

    private Metadata requestHeaders() {
        Metadata headers = new Metadata();
        headers.put(X_GOOG_API_CLIENT_HEADER, getGoogApiClientValue());
        headers.put(RESOURCE_PREFIX_HEADER, this.resourcePrefixValue);
        headers.put(X_GOOG_REQUEST_PARAMS_HEADER, this.resourcePrefixValue);
        if (this.metadataProvider != null) {
            this.metadataProvider.updateMetadata(headers);
        }
        return headers;
    }
}
