package com.suraksha.setu.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.suraksha.setu.R;
import com.suraksha.setu.ui.main.MainActivity;
import com.suraksha.setu.util.EmergencyNotificationHelper;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyFirebaseMessagingService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0002¨\u0006\r"}, d2 = {"Lcom/suraksha/setu/service/MyFirebaseMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "onMessageReceived", "", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "showEmergencyNotification", "title", "body", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
        boolean z = !data.isEmpty();
        String str = remoteMessage.getData().get("title");
        if (str == null) {
            str = "Emergency Alert";
        }
        String str2 = remoteMessage.getData().get("body");
        if (str2 == null) {
            str2 = "Someone near you needs help!";
        }
        showEmergencyNotification(str, str2);
        RemoteMessage.Notification it = remoteMessage.getNotification();
        if (it != null) {
            String title = it.getTitle();
            if (title == null) {
                title = "Alert";
            }
            Intrinsics.checkNotNull(title);
            String body = it.getBody();
            if (body == null) {
                body = "Details...";
            }
            Intrinsics.checkNotNull(body);
            showEmergencyNotification(title, body);
        }
    }

    private final void showEmergencyNotification(String title, String body) {
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.setFlags(268468224);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, EmergencyNotificationHelper.CHANNEL_ID).setSmallIcon(R.drawable.app_logo).setContentTitle(title).setContentText(body).setPriority(1).setAutoCancel(true).setContentIntent(pendingIntent);
        Intrinsics.checkNotNullExpressionValue(builder, "setContentIntent(...)");
        Object systemService = getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        super.onNewToken(token);
    }
}
