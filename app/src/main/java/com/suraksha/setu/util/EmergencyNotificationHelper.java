package com.suraksha.setu.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.suraksha.setu.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EmergencyNotificationHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/suraksha/setu/util/EmergencyNotificationHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "createNotificationChannel", "", "getBaseNotificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class EmergencyNotificationHelper {
    public static final String CHANNEL_ID = "emergency_channel";
    public static final int NOTIFICATION_ID = 1001;
    private final Context context;

    public EmergencyNotificationHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        createNotificationChannel();
    }

    private final void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Emergency Alerts", 4);
            channel.setDescription("Notifications for high-priority emergency alerts");
            Object systemService = this.context.getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            NotificationManager notificationManager = (NotificationManager) systemService;
            notificationManager.createNotificationChannel(channel);
        }
    }

    public final NotificationCompat.Builder getBaseNotificationBuilder() {
        NotificationCompat.Builder category = new NotificationCompat.Builder(this.context, CHANNEL_ID).setSmallIcon(R.drawable.app_logo).setContentTitle("Emergency SOS Active").setPriority(2).setOngoing(true).setCategory(NotificationCompat.CATEGORY_SERVICE);
        Intrinsics.checkNotNullExpressionValue(category, "setCategory(...)");
        return category;
    }
}
