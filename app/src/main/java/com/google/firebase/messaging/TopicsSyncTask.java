package com.google.firebase.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.util.Log;
import java.io.IOException;

/* JADX INFO: loaded from: classes10.dex */
class TopicsSyncTask implements Runnable {
    private final Context context;
    private final Metadata metadata;
    private final long nextDelaySeconds;
    private final PowerManager.WakeLock syncWakeLock;
    private final TopicsSubscriber topicsSubscriber;
    private static final Object TOPIC_SYNC_TASK_LOCK = new Object();
    private static Boolean hasWakeLockPermission = null;
    private static Boolean hasAccessNetworkStatePermission = null;

    TopicsSyncTask(TopicsSubscriber topicsSubscriber, Context context, Metadata metadata, long nextDelaySeconds) {
        this.topicsSubscriber = topicsSubscriber;
        this.context = context;
        this.nextDelaySeconds = nextDelaySeconds;
        this.metadata = metadata;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.syncWakeLock = powerManager.newWakeLock(1, Constants.FCM_WAKE_LOCK);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (hasWakeLockPermission(this.context)) {
            this.syncWakeLock.acquire(Constants.WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);
        }
        try {
            try {
                try {
                    this.topicsSubscriber.setSyncScheduledOrRunning(true);
                    if (!this.metadata.isGmscorePresent()) {
                        this.topicsSubscriber.setSyncScheduledOrRunning(false);
                        if (hasWakeLockPermission(this.context)) {
                            try {
                                this.syncWakeLock.release();
                                return;
                            } catch (RuntimeException e) {
                                Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                                return;
                            }
                        }
                        return;
                    }
                    if (!hasAccessNetworkStatePermission(this.context) || isDeviceConnected()) {
                        if (this.topicsSubscriber.syncTopics()) {
                            this.topicsSubscriber.setSyncScheduledOrRunning(false);
                        } else {
                            this.topicsSubscriber.syncWithDelaySecondsInternal(this.nextDelaySeconds);
                        }
                        if (hasWakeLockPermission(this.context)) {
                            this.syncWakeLock.release();
                            return;
                        }
                        return;
                    }
                    ConnectivityChangeReceiver receiver = new ConnectivityChangeReceiver(this);
                    receiver.registerReceiver();
                    if (hasWakeLockPermission(this.context)) {
                        try {
                            this.syncWakeLock.release();
                        } catch (RuntimeException e2) {
                            Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                        }
                    }
                } catch (IOException e3) {
                    Log.e(Constants.TAG, "Failed to sync topics. Won't retry sync. " + e3.getMessage());
                    this.topicsSubscriber.setSyncScheduledOrRunning(false);
                    if (hasWakeLockPermission(this.context)) {
                        this.syncWakeLock.release();
                    }
                }
            } catch (RuntimeException e4) {
                Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
            }
        } catch (Throwable th) {
            if (hasWakeLockPermission(this.context)) {
                try {
                    this.syncWakeLock.release();
                } catch (RuntimeException e5) {
                    Log.i(Constants.TAG, "TopicsSyncTask's wakelock was already released due to timeout.");
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean isDeviceConnected() {
        /*
            r3 = this;
            monitor-enter(r3)
            android.content.Context r0 = r3.context     // Catch: java.lang.Throwable -> L20
            java.lang.String r1 = "connectivity"
            java.lang.Object r0 = r0.getSystemService(r1)     // Catch: java.lang.Throwable -> L20
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0     // Catch: java.lang.Throwable -> L20
            if (r0 == 0) goto L12
            android.net.NetworkInfo r1 = r0.getActiveNetworkInfo()     // Catch: java.lang.Throwable -> L20
            goto L13
        L12:
            r1 = 0
        L13:
            if (r1 == 0) goto L1d
            boolean r2 = r1.isConnected()     // Catch: java.lang.Throwable -> L20
            if (r2 == 0) goto L1d
            r2 = 1
            goto L1e
        L1d:
            r2 = 0
        L1e:
            monitor-exit(r3)
            return r2
        L20:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.TopicsSyncTask.isDeviceConnected():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isLoggable() {
        return Log.isLoggable(Constants.TAG, 3);
    }

    private static boolean hasWakeLockPermission(Context context) {
        boolean zBooleanValue;
        boolean zBooleanValue2;
        synchronized (TOPIC_SYNC_TASK_LOCK) {
            if (hasWakeLockPermission == null) {
                zBooleanValue = hasPermission(context, "android.permission.WAKE_LOCK", hasWakeLockPermission);
            } else {
                zBooleanValue = hasWakeLockPermission.booleanValue();
            }
            hasWakeLockPermission = Boolean.valueOf(zBooleanValue);
            zBooleanValue2 = hasWakeLockPermission.booleanValue();
        }
        return zBooleanValue2;
    }

    private static boolean hasAccessNetworkStatePermission(Context context) {
        boolean zBooleanValue;
        boolean zBooleanValue2;
        synchronized (TOPIC_SYNC_TASK_LOCK) {
            if (hasAccessNetworkStatePermission == null) {
                zBooleanValue = hasPermission(context, "android.permission.ACCESS_NETWORK_STATE", hasAccessNetworkStatePermission);
            } else {
                zBooleanValue = hasAccessNetworkStatePermission.booleanValue();
            }
            hasAccessNetworkStatePermission = Boolean.valueOf(zBooleanValue);
            zBooleanValue2 = hasAccessNetworkStatePermission.booleanValue();
        }
        return zBooleanValue2;
    }

    private static boolean hasPermission(Context context, String permission, Boolean cachedState) {
        if (cachedState != null) {
            return cachedState.booleanValue();
        }
        boolean granted = context.checkCallingOrSelfPermission(permission) == 0;
        if (!granted && Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, createPermissionMissingLog(permission));
        }
        return granted;
    }

    private static String createPermissionMissingLog(String permission) {
        return "Missing Permission: " + permission + ". This permission should normally be included by the manifest merger, but may needed to be manually added to your manifest";
    }

    class ConnectivityChangeReceiver extends BroadcastReceiver {
        private TopicsSyncTask task;

        public ConnectivityChangeReceiver(TopicsSyncTask task) {
            this.task = task;
        }

        @Override // android.content.BroadcastReceiver
        public synchronized void onReceive(Context context, Intent intent) {
            if (this.task == null) {
                return;
            }
            if (this.task.isDeviceConnected()) {
                if (TopicsSyncTask.isLoggable()) {
                    Log.d(Constants.TAG, "Connectivity changed. Starting background sync.");
                }
                this.task.topicsSubscriber.scheduleSyncTaskWithDelaySeconds(this.task, 0L);
                context.unregisterReceiver(this);
                this.task = null;
            }
        }

        public void registerReceiver() {
            if (TopicsSyncTask.isLoggable()) {
                Log.d(Constants.TAG, "Connectivity change received registered");
            }
            TopicsSyncTask.this.context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }
}
