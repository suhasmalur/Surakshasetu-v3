package com.google.firebase.messaging;

import android.content.Context;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes10.dex */
class TopicsSubscriber {
    static final String ERROR_INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);
    private static final long MIN_DELAY_SEC = 30;
    private static final long RPC_TIMEOUT_SEC = 30;
    private final Context context;
    private final FirebaseMessaging firebaseMessaging;
    private final Metadata metadata;
    private final GmsRpc rpc;
    private final TopicsStore store;
    private final ScheduledExecutorService syncExecutor;
    private final Map<String, ArrayDeque<TaskCompletionSource<Void>>> pendingOperations = new ArrayMap();
    private boolean syncScheduledOrRunning = false;

    static Task<TopicsSubscriber> createInstance(final FirebaseMessaging firebaseMessaging, final Metadata metadata, final GmsRpc rpc, final Context context, final ScheduledExecutorService syncExecutor) {
        return Tasks.call(syncExecutor, new Callable() { // from class: com.google.firebase.messaging.TopicsSubscriber$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return TopicsSubscriber.lambda$createInstance$0(context, syncExecutor, firebaseMessaging, metadata, rpc);
            }
        });
    }

    static /* synthetic */ TopicsSubscriber lambda$createInstance$0(Context context, ScheduledExecutorService syncExecutor, FirebaseMessaging firebaseMessaging, Metadata metadata, GmsRpc rpc) throws Exception {
        TopicsStore topicsStore = TopicsStore.getInstance(context, syncExecutor);
        TopicsSubscriber topicsSubscriber = new TopicsSubscriber(firebaseMessaging, metadata, topicsStore, rpc, context, syncExecutor);
        return topicsSubscriber;
    }

    private TopicsSubscriber(FirebaseMessaging firebaseMessaging, Metadata metadata, TopicsStore store, GmsRpc rpc, Context context, ScheduledExecutorService syncExecutor) {
        this.firebaseMessaging = firebaseMessaging;
        this.metadata = metadata;
        this.store = store;
        this.rpc = rpc;
        this.context = context;
        this.syncExecutor = syncExecutor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Void> subscribeToTopic(String topic) {
        Task<Void> task = scheduleTopicOperation(TopicOperation.subscribe(topic));
        startTopicsSyncIfNecessary();
        return task;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Void> unsubscribeFromTopic(String topic) {
        Task<Void> task = scheduleTopicOperation(TopicOperation.unsubscribe(topic));
        startTopicsSyncIfNecessary();
        return task;
    }

    Task<Void> scheduleTopicOperation(TopicOperation topicOperation) {
        this.store.addTopicOperation(topicOperation);
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        addToPendingOperations(topicOperation, taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    private void addToPendingOperations(TopicOperation topicOperation, TaskCompletionSource<Void> taskCompletionSource) {
        ArrayDeque<TaskCompletionSource<Void>> list;
        synchronized (this.pendingOperations) {
            String key = topicOperation.serialize();
            if (this.pendingOperations.containsKey(key)) {
                list = this.pendingOperations.get(key);
            } else {
                list = new ArrayDeque<>();
                this.pendingOperations.put(key, list);
            }
            list.add(taskCompletionSource);
        }
    }

    boolean hasPendingOperation() {
        return this.store.getNextTopicOperation() != null;
    }

    void startTopicsSyncIfNecessary() {
        if (hasPendingOperation()) {
            startSync();
        }
    }

    private void startSync() {
        if (!isSyncScheduledOrRunning()) {
            syncWithDelaySecondsInternal(0L);
        }
    }

    void syncWithDelaySecondsInternal(long delaySeconds) {
        long retryDelaySeconds = Math.min(Math.max(30L, 2 * delaySeconds), MAX_DELAY_SEC);
        TopicsSyncTask syncTask = new TopicsSyncTask(this, this.context, this.metadata, retryDelaySeconds);
        scheduleSyncTaskWithDelaySeconds(syncTask, delaySeconds);
        setSyncScheduledOrRunning(true);
    }

    void scheduleSyncTaskWithDelaySeconds(Runnable task, long delaySeconds) {
        this.syncExecutor.schedule(task, delaySeconds, TimeUnit.SECONDS);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000e, code lost:
    
        if (isDebugLogEnabled() == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
    
        android.util.Log.d(com.google.firebase.messaging.Constants.TAG, "topic sync succeeded");
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0018, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean syncTopics() throws java.io.IOException {
        /*
            r3 = this;
        L1:
            monitor-enter(r3)
            com.google.firebase.messaging.TopicsStore r0 = r3.store     // Catch: java.lang.Throwable -> L2c
            com.google.firebase.messaging.TopicOperation r0 = r0.getNextTopicOperation()     // Catch: java.lang.Throwable -> L2c
            if (r0 != 0) goto L1a
            boolean r1 = isDebugLogEnabled()     // Catch: java.lang.Throwable -> L2c
            if (r1 == 0) goto L17
            java.lang.String r1 = "FirebaseMessaging"
            java.lang.String r2 = "topic sync succeeded"
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L2c
        L17:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L2c
            r1 = 1
            return r1
        L1a:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L2c
            boolean r1 = r3.performTopicOperation(r0)
            if (r1 != 0) goto L23
            r1 = 0
            return r1
        L23:
            com.google.firebase.messaging.TopicsStore r1 = r3.store
            r1.removeTopicOperation(r0)
            r3.markCompletePendingOperation(r0)
            goto L1
        L2c:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L2c
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.TopicsSubscriber.syncTopics():boolean");
    }

    private void markCompletePendingOperation(TopicOperation topicOperation) {
        synchronized (this.pendingOperations) {
            String key = topicOperation.serialize();
            if (this.pendingOperations.containsKey(key)) {
                ArrayDeque<TaskCompletionSource<Void>> list = this.pendingOperations.get(key);
                TaskCompletionSource<Void> taskCompletionSource = list.poll();
                if (taskCompletionSource != null) {
                    taskCompletionSource.setResult(null);
                }
                if (list.isEmpty()) {
                    this.pendingOperations.remove(key);
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:5:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean performTopicOperation(com.google.firebase.messaging.TopicOperation r7) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.TopicsSubscriber.performTopicOperation(com.google.firebase.messaging.TopicOperation):boolean");
    }

    private void blockingSubscribeToTopic(String topic) throws IOException {
        awaitTask(this.rpc.subscribeToTopic(this.firebaseMessaging.blockingGetToken(), topic));
    }

    private void blockingUnsubscribeFromTopic(String topic) throws IOException {
        awaitTask(this.rpc.unsubscribeFromTopic(this.firebaseMessaging.blockingGetToken(), topic));
    }

    private static <T> void awaitTask(Task<T> task) throws IOException {
        try {
            Tasks.await(task, 30L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e = e;
            throw new IOException(ERROR_SERVICE_NOT_AVAILABLE, e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            }
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new IOException(e2);
        } catch (TimeoutException e3) {
            e = e3;
            throw new IOException(ERROR_SERVICE_NOT_AVAILABLE, e);
        }
    }

    synchronized boolean isSyncScheduledOrRunning() {
        return this.syncScheduledOrRunning;
    }

    synchronized void setSyncScheduledOrRunning(boolean value) {
        this.syncScheduledOrRunning = value;
    }

    static boolean isDebugLogEnabled() {
        return Log.isLoggable(Constants.TAG, 3);
    }

    TopicsStore getStore() {
        return this.store;
    }
}
