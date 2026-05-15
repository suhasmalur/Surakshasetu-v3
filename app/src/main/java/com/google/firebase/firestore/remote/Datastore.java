package com.google.firebase.firestore.remote;

import android.content.Context;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.remote.FirestoreChannel;
import com.google.firebase.firestore.remote.WatchStream;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.BatchGetDocumentsRequest;
import com.google.firestore.v1.BatchGetDocumentsResponse;
import com.google.firestore.v1.CommitRequest;
import com.google.firestore.v1.CommitResponse;
import com.google.firestore.v1.FirestoreGrpc;
import com.google.firestore.v1.RunAggregationQueryRequest;
import com.google.firestore.v1.RunAggregationQueryResponse;
import com.google.firestore.v1.StructuredAggregationQuery;
import com.google.firestore.v1.Target;
import com.google.firestore.v1.Value;
import com.google.firestore.v1.WriteResult;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLHandshakeException;

/* JADX INFO: loaded from: classes10.dex */
public class Datastore {
    static final String SSL_DEPENDENCY_ERROR_MESSAGE = "The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.";
    static final Set<String> WHITE_LISTED_HEADERS = new HashSet(Arrays.asList("date", "x-google-backends", "x-google-netmon-label", "x-google-service", "x-google-gfe-request-trace"));
    private final FirestoreChannel channel;
    private final DatabaseInfo databaseInfo;
    private final RemoteSerializer serializer;
    private final AsyncQueue workerQueue;

    public Datastore(DatabaseInfo databaseInfo, AsyncQueue workerQueue, CredentialsProvider<User> authProvider, CredentialsProvider<String> appCheckProvider, Context context, GrpcMetadataProvider metadataProvider) {
        this.databaseInfo = databaseInfo;
        this.workerQueue = workerQueue;
        this.serializer = new RemoteSerializer(databaseInfo.getDatabaseId());
        this.channel = initializeChannel(databaseInfo, workerQueue, authProvider, appCheckProvider, context, metadataProvider);
    }

    FirestoreChannel initializeChannel(DatabaseInfo databaseInfo, AsyncQueue workerQueue, CredentialsProvider<User> authProvider, CredentialsProvider<String> appCheckProvider, Context context, GrpcMetadataProvider metadataProvider) {
        return new FirestoreChannel(workerQueue, context, authProvider, appCheckProvider, databaseInfo, metadataProvider);
    }

    void shutdown() {
        this.channel.shutdown();
    }

    AsyncQueue getWorkerQueue() {
        return this.workerQueue;
    }

    DatabaseInfo getDatabaseInfo() {
        return this.databaseInfo;
    }

    WatchStream createWatchStream(WatchStream.Callback listener) {
        return new WatchStream(this.channel, this.workerQueue, this.serializer, listener);
    }

    WriteStream createWriteStream(WriteStream.Callback listener) {
        return new WriteStream(this.channel, this.workerQueue, this.serializer, listener);
    }

    public Task<List<MutationResult>> commit(List<Mutation> mutations) {
        CommitRequest.Builder builder = CommitRequest.newBuilder();
        builder.setDatabase(this.serializer.databaseName());
        for (Mutation mutation : mutations) {
            builder.addWrites(this.serializer.encodeMutation(mutation));
        }
        return this.channel.runRpc(FirestoreGrpc.getCommitMethod(), builder.build()).continueWith(this.workerQueue.getExecutor(), new Continuation() { // from class: com.google.firebase.firestore.remote.Datastore$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m381lambda$commit$0$comgooglefirebasefirestoreremoteDatastore(task);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$commit$0$com-google-firebase-firestore-remote-Datastore, reason: not valid java name */
    /* synthetic */ List m381lambda$commit$0$comgooglefirebasefirestoreremoteDatastore(Task task) throws Exception {
        if (!task.isSuccessful()) {
            if ((task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                this.channel.invalidateToken();
            }
            throw task.getException();
        }
        CommitResponse response = (CommitResponse) task.getResult();
        SnapshotVersion commitVersion = this.serializer.decodeVersion(response.getCommitTime());
        int count = response.getWriteResultsCount();
        ArrayList<MutationResult> results = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            WriteResult result = response.getWriteResults(i);
            results.add(this.serializer.decodeMutationResult(result, commitVersion));
        }
        return results;
    }

    public Task<List<MutableDocument>> lookup(final List<DocumentKey> keys) {
        BatchGetDocumentsRequest.Builder builder = BatchGetDocumentsRequest.newBuilder();
        builder.setDatabase(this.serializer.databaseName());
        for (DocumentKey key : keys) {
            builder.addDocuments(this.serializer.encodeKey(key));
        }
        final List<BatchGetDocumentsResponse> responses = new ArrayList<>();
        final TaskCompletionSource<List<MutableDocument>> completionSource = new TaskCompletionSource<>();
        this.channel.runStreamingResponseRpc(FirestoreGrpc.getBatchGetDocumentsMethod(), builder.build(), new FirestoreChannel.StreamingListener<BatchGetDocumentsResponse>() { // from class: com.google.firebase.firestore.remote.Datastore.1
            @Override // com.google.firebase.firestore.remote.FirestoreChannel.StreamingListener
            public void onMessage(BatchGetDocumentsResponse message) {
                responses.add(message);
                if (responses.size() == keys.size()) {
                    Map<DocumentKey, MutableDocument> resultMap = new HashMap<>();
                    for (BatchGetDocumentsResponse response : responses) {
                        MutableDocument doc = Datastore.this.serializer.decodeMaybeDocument(response);
                        resultMap.put(doc.getKey(), doc);
                    }
                    ArrayList arrayList = new ArrayList();
                    for (DocumentKey key2 : keys) {
                        arrayList.add(resultMap.get(key2));
                    }
                    completionSource.trySetResult(arrayList);
                }
            }

            @Override // com.google.firebase.firestore.remote.FirestoreChannel.StreamingListener
            public void onClose(Status status) {
                if (status.isOk()) {
                    completionSource.trySetResult(Collections.emptyList());
                    return;
                }
                FirebaseFirestoreException exception = Util.exceptionFromStatus(status);
                if (exception.getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                    Datastore.this.channel.invalidateToken();
                }
                completionSource.trySetException(exception);
            }
        });
        return completionSource.getTask();
    }

    public Task<Map<String, Value>> runAggregateQuery(Query query, List<AggregateField> aggregateFields) {
        Target.QueryTarget encodedQueryTarget = this.serializer.encodeQueryTarget(query.toAggregateTarget());
        final HashMap<String, String> aliasMap = new HashMap<>();
        StructuredAggregationQuery structuredAggregationQuery = this.serializer.encodeStructuredAggregationQuery(encodedQueryTarget, aggregateFields, aliasMap);
        RunAggregationQueryRequest.Builder request = RunAggregationQueryRequest.newBuilder();
        request.setParent(encodedQueryTarget.getParent());
        request.setStructuredAggregationQuery(structuredAggregationQuery);
        return this.channel.runRpc(FirestoreGrpc.getRunAggregationQueryMethod(), request.build()).continueWith(this.workerQueue.getExecutor(), new Continuation() { // from class: com.google.firebase.firestore.remote.Datastore$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m382xb2492930(aliasMap, task);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$runAggregateQuery$1$com-google-firebase-firestore-remote-Datastore, reason: not valid java name */
    /* synthetic */ Map m382xb2492930(HashMap aliasMap, Task task) throws Exception {
        if (!task.isSuccessful()) {
            if ((task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                this.channel.invalidateToken();
            }
            throw task.getException();
        }
        Map<String, Value> result = new HashMap<>();
        RunAggregationQueryResponse response = (RunAggregationQueryResponse) task.getResult();
        for (Map.Entry<String, Value> entry : response.getResult().getAggregateFieldsMap().entrySet()) {
            Assert.hardAssert(aliasMap.containsKey(entry.getKey()), "%s not present in aliasMap", entry.getKey());
            result.put((String) aliasMap.get(entry.getKey()), entry.getValue());
        }
        return result;
    }

    public static boolean isPermanentError(Status status) {
        return isPermanentError(FirebaseFirestoreException.Code.fromValue(status.getCode().value()));
    }

    public static boolean isPermanentError(FirebaseFirestoreException.Code code) {
        switch (code) {
            case OK:
                throw new IllegalArgumentException("Treated status OK as error");
            case CANCELLED:
            case UNKNOWN:
            case DEADLINE_EXCEEDED:
            case RESOURCE_EXHAUSTED:
            case INTERNAL:
            case UNAVAILABLE:
            case UNAUTHENTICATED:
                return false;
            case INVALID_ARGUMENT:
            case NOT_FOUND:
            case ALREADY_EXISTS:
            case PERMISSION_DENIED:
            case FAILED_PRECONDITION:
            case ABORTED:
            case OUT_OF_RANGE:
            case UNIMPLEMENTED:
            case DATA_LOSS:
                return true;
            default:
                throw new IllegalArgumentException("Unknown gRPC status code: " + code);
        }
    }

    public static boolean isMissingSslCiphers(Status status) {
        status.getCode();
        Throwable t = status.getCause();
        return (!(t instanceof SSLHandshakeException) || t.getMessage().contains("no ciphers available")) ? false : false;
    }

    public static boolean isPermanentWriteError(Status status) {
        return isPermanentError(status) && !status.getCode().equals(Status.Code.ABORTED);
    }
}
