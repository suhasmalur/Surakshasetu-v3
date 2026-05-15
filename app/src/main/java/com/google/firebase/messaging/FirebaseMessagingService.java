package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.cloudmessaging.Rpc;
import com.google.firebase.messaging.Constants;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes10.dex */
public class FirebaseMessagingService extends EnhancedIntentService {
    public static final String ACTION_DIRECT_BOOT_REMOTE_INTENT = "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT";
    static final String ACTION_NEW_TOKEN = "com.google.firebase.messaging.NEW_TOKEN";
    static final String ACTION_REMOTE_INTENT = "com.google.android.c2dm.intent.RECEIVE";
    static final String EXTRA_TOKEN = "token";
    private static final int RECENTLY_RECEIVED_MESSAGE_IDS_MAX_SIZE = 10;
    private static final Queue<String> recentlyReceivedMessageIds = new ArrayDeque(10);
    private Rpc rpc;

    public void onMessageReceived(RemoteMessage message) {
    }

    public void onDeletedMessages() {
    }

    public void onMessageSent(String msgId) {
    }

    public void onSendError(String msgId, Exception exception) {
    }

    public void onNewToken(String token) {
    }

    @Override // com.google.firebase.messaging.EnhancedIntentService
    protected Intent getStartCommandIntent(Intent originalIntent) {
        return ServiceStarter.getInstance().getMessagingEvent();
    }

    @Override // com.google.firebase.messaging.EnhancedIntentService
    public void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (ACTION_REMOTE_INTENT.equals(action) || ACTION_DIRECT_BOOT_REMOTE_INTENT.equals(action)) {
            handleMessageIntent(intent);
        } else if (ACTION_NEW_TOKEN.equals(action)) {
            onNewToken(intent.getStringExtra(EXTRA_TOKEN));
        } else {
            Log.d(Constants.TAG, "Unknown intent action: " + intent.getAction());
        }
    }

    private void handleMessageIntent(Intent intent) {
        String messageId = intent.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        if (!alreadyReceivedMessage(messageId)) {
            passMessageIntentToSdk(intent);
        }
        getRpc(this).messageHandled(new CloudMessage(intent));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void passMessageIntentToSdk(android.content.Intent r5) {
        /*
            r4 = this;
            java.lang.String r0 = "message_type"
            java.lang.String r0 = r5.getStringExtra(r0)
            if (r0 != 0) goto La
            java.lang.String r0 = "gcm"
        La:
            int r1 = r0.hashCode()
            switch(r1) {
                case -2062414158: goto L30;
                case 102161: goto L26;
                case 814694033: goto L1c;
                case 814800675: goto L12;
                default: goto L11;
            }
        L11:
            goto L3a
        L12:
            java.lang.String r1 = "send_event"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L11
            r1 = 2
            goto L3b
        L1c:
            java.lang.String r1 = "send_error"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L11
            r1 = 3
            goto L3b
        L26:
            java.lang.String r1 = "gcm"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L11
            r1 = 0
            goto L3b
        L30:
            java.lang.String r1 = "deleted_messages"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L11
            r1 = 1
            goto L3b
        L3a:
            r1 = -1
        L3b:
            switch(r1) {
                case 0: goto L79;
                case 1: goto L75;
                case 2: goto L6b;
                case 3: goto L57;
                default: goto L3e;
            }
        L3e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Received message with unknown type: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "FirebaseMessaging"
            android.util.Log.w(r2, r1)
            goto L80
        L57:
            java.lang.String r1 = r4.getMessageId(r5)
            com.google.firebase.messaging.SendException r2 = new com.google.firebase.messaging.SendException
            java.lang.String r3 = "error"
            java.lang.String r3 = r5.getStringExtra(r3)
            r2.<init>(r3)
            r4.onSendError(r1, r2)
            goto L80
        L6b:
            java.lang.String r1 = "google.message_id"
            java.lang.String r1 = r5.getStringExtra(r1)
            r4.onMessageSent(r1)
            goto L80
        L75:
            r4.onDeletedMessages()
            goto L80
        L79:
            com.google.firebase.messaging.MessagingAnalytics.logNotificationReceived(r5)
            r4.dispatchMessage(r5)
        L80:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.passMessageIntentToSdk(android.content.Intent):void");
    }

    private void dispatchMessage(Intent intent) {
        Bundle data = intent.getExtras();
        if (data == null) {
            data = new Bundle();
        }
        data.remove("androidx.content.wakelockid");
        if (NotificationParams.isNotification(data)) {
            NotificationParams params = new NotificationParams(data);
            ExecutorService executor = FcmExecutors.newNetworkIOExecutor();
            DisplayNotification displayNotification = new DisplayNotification(this, params, executor);
            try {
                if (displayNotification.handleNotification()) {
                    return;
                }
                executor.shutdown();
                if (MessagingAnalytics.shouldUploadScionMetrics(intent)) {
                    MessagingAnalytics.logNotificationForeground(intent);
                }
            } finally {
                executor.shutdown();
            }
        }
        onMessageReceived(new RemoteMessage(data));
    }

    private boolean alreadyReceivedMessage(String messageId) {
        if (TextUtils.isEmpty(messageId)) {
            return false;
        }
        if (recentlyReceivedMessageIds.contains(messageId)) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "Received duplicate message: " + messageId);
                return true;
            }
            return true;
        }
        if (recentlyReceivedMessageIds.size() >= 10) {
            recentlyReceivedMessageIds.remove();
        }
        recentlyReceivedMessageIds.add(messageId);
        return false;
    }

    private String getMessageId(Intent intent) {
        String messageId = intent.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        if (messageId == null) {
            return intent.getStringExtra(Constants.MessagePayloadKeys.MSGID_SERVER);
        }
        return messageId;
    }

    private Rpc getRpc(Context context) {
        if (this.rpc == null) {
            this.rpc = new Rpc(context.getApplicationContext());
        }
        return this.rpc;
    }

    static void resetForTesting() {
        recentlyReceivedMessageIds.clear();
    }

    void setRpcForTesting(Rpc rpc) {
        this.rpc = rpc;
    }
}
